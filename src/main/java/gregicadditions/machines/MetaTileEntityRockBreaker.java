package gregicadditions.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.render.Textures;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntityRockBreaker extends TieredMetaTileEntity {

    public MetaTileEntityRockBreaker(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityRockBreaker(metaTileEntityId, getTier());
    }


    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote && getTimer() % 20 == 0 && checkSides(Blocks.LAVA) && checkSides(Blocks.WATER) && energyContainer.getEnergyStored() >= getEnergyPerBlockBreak()) {
            int stack = (int) Math.pow(2, getTier());

            ItemStack diorite = new ItemStack(Blocks.STONE, stack, 3);
            ItemStack granite = new ItemStack(Blocks.STONE, stack, 1);
            ItemStack andesite = new ItemStack(Blocks.STONE, stack, 5);
            ItemStack cobble = new ItemStack(Blocks.COBBLESTONE, stack);

            for (int i = 0; i < 4; i++) {
                cobble = exportItems.insertItem(i, cobble, false);
            }
            for (int i = 4; i < 8; i++) {
                diorite = exportItems.insertItem(i, diorite, false);
            }
            for (int i = 8; i < 12; i++) {
                granite = exportItems.insertItem(i, granite, false);
            }
            for (int i = 12; i < 16; i++) {
                andesite = exportItems.insertItem(i, andesite, false);
            }
            if (cobble.getCount() != stack || diorite.getCount() != stack || granite.getCount() != stack || andesite.getCount() != stack)
                energyContainer.removeEnergy(getEnergyPerBlockBreak());
        }
    }

    private boolean checkSides(BlockStaticLiquid liquid) {
        EnumFacing frontFacing = getFrontFacing();
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side == frontFacing || side == EnumFacing.DOWN || side == EnumFacing.UP) continue;
            if (getWorld().getBlockState(getPos().offset(side)) == liquid.getDefaultState())
                return true;
        }
        return false;
    }

    private int getEnergyPerBlockBreak() {
        return (int) GTValues.V[getTier()];
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(16);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        int rowSize = 4;
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 18 + 18 * rowSize + 94)
                .label(10, 5, getMetaFullName());

        for (int y = 0; y < rowSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                int index = y * rowSize + x;
                builder.widget(new SlotWidget(exportItems, index, 89 - rowSize * 9 + x * 18, 18 + y * 18, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT));
            }
        }
        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 8, 18 + 18 * rowSize + 12);
        return builder.build(getHolder(), entityPlayer);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.HAMMER_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), false);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.machine.rock_breaker.description"));
    }
}
