package gregicadditions.machines.multi.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.util.XSTR;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityMultiblockPart;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public class MetaTileEntityMufflerHatch extends MetaTileEntityMultiblockPart implements IMultiblockAbilityPart<MetaTileEntityMufflerHatch> {

    private final int recoveryAmount;
    private final ItemStackHandler inventory;
    private final XSTR random;

    private boolean frontFaceFree;

    public MetaTileEntityMufflerHatch(ResourceLocation metaTileEntityId, int tier, int recoveryAmount) {
        super(metaTileEntityId, tier);
        this.recoveryAmount = recoveryAmount;
        this.inventory = new ItemStackHandler(4);
        this.frontFaceFree = false;
        this.random = new XSTR();
    }

    public ItemStackHandler getRecoveryInventory() {
        return inventory;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityMufflerHatch(metaTileEntityId, getTier(), recoveryAmount);
    }


    @Override
    public void update() {
        super.update();

        if (!getWorld().isRemote) {
            if (getOffsetTimer() % 10 == 0)
                this.frontFaceFree = checkFrontFaceFree(); // todo do more with this
        }
    }


    public void recoverItems(List<ItemStack> recoveryItems) {
        float random = this.random.nextInt(10000);
        if (random > this.recoveryAmount)
            IntStream.range(0, Math.min(recoveryItems.size(), inventory.getSlots())).forEach(index ->
                    this.inventory.insertItem(index, recoveryItems.get(index), false));
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        // TODO if needed
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("RecoveryInventory", inventory.serializeNBT());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.inventory.deserializeNBT(data.getCompoundTag("RecoveryInventory"));
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        // TODO
    }

    @Override
    protected ModularUI createUI(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176,
                18 + 18 + 94 + 9)
                .label(10, 5, getMetaFullName());

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                int index = y * 2 + x;
                builder.widget(new SlotWidget(inventory, index, 89 - 2 * 9 + x * 18, 18 + y * 18, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT));
            }
        }
        builder.bindPlayerInventory(player.inventory, GuiTextures.SLOT, 8, 18 + 18 * 2 + 12);
        return builder.build(getHolder(), player);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.muffler.recovery", recoveryAmount)); // TODO lang key
    }

    @Override
    public MultiblockAbility<MetaTileEntityMufflerHatch> getAbility() {
        return GregicAdditionsCapabilities.MUFFLER_HATCH;
    }

    @Override
    public void registerAbilities(List<MetaTileEntityMufflerHatch> abilityList) {
        abilityList.add(this);
    }

    /**
     * @return true if front face is free and contains only air blocks in 3x3 area
     */
    public boolean isFrontFaceFree() {
        return frontFaceFree;
    }

    private boolean checkFrontFaceFree() {
        EnumFacing facing = getFrontFacing();
        BlockPos frontPos = new BlockPos(facing.getDirectionVec());
        IBlockState blockState = getWorld().getBlockState(frontPos);
        return blockState.getBlock().isAir(blockState, getWorld(), frontPos);
    }
}
