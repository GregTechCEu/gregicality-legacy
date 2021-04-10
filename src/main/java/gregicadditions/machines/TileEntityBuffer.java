package gregicadditions.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Vector3;
import gregicadditions.client.ClientHandler;
import gregtech.api.capability.impl.FilteredFluidHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

public class TileEntityBuffer extends MetaTileEntity implements ITieredMetaTileEntity {

    private final int tier;
    protected FluidTankList fluids;
    private ItemStackHandler inventory;
    private static final double[] rotations = new double[]{180.0, 0.0, -90.0, 90.0};


    public TileEntityBuffer(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId);
        this.tier = tier;
        initializeInventory();
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        FilteredFluidHandler[] fluidsHandlers = new FilteredFluidHandler[this.tier];
        for (int i = 0; i < this.tier; i++) {
            fluidsHandlers[i] = new FilteredFluidHandler(16000);
        }
        this.fluids = new FluidTankList(false, fluidsHandlers);
        //this.importFluids = new FluidTankList(false, fluids);
        //this.exportFluids = new FluidTankList(false, fluids);
        //this.fluidInventory = new FluidHandlerProxy(fluids, fluids);
        this.fluidInventory = fluids;
        this.inventory = new ItemStackHandler(tier * tier);
        this.itemInventory = inventory;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityBuffer(metaTileEntityId, tier);
    }

    @Override
    public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
        return Pair.of(ClientHandler.VOLTAGE_CASINGS[this.tier].getParticleSprite(), this.getPaintingColor());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND,
                176, Math.max(166, 18 + 18 * tier + 94));//176, 166
        for (int i = 0; i < this.fluids.getTanks(); i++) {
            builder.widget(new TankWidget(this.fluids.getTankAt(i), 176 - 8 - 18, 18 + 18 * i, 18, 18)
                    .setAlwaysShowFull(true)
                    .setBackgroundTexture(GuiTextures.FLUID_SLOT)
                    .setContainerClicking(true, true));
        }
        for (int y = 0; y < tier; y++) {
            for (int x = 0; x < tier; x++) {
                int index = y * tier + x;
                builder.slot(inventory, index, 8 + x * 18, 18 + y * 18, GuiTextures.SLOT);
            }
        }
        return builder.label(6, 6, getMetaFullName())
                .bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 8, 18 + 18 * tier + 12)
                .build(getHolder(), entityPlayer);
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        Textures.VOLTAGE_CASINGS[this.tier].render(renderState, translation, ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()))));
        translation.translate(0.5, 0.001, 0.5);
        translation.rotate(Math.toRadians(rotations[getFrontFacing().getIndex() - 2]), new Vector3(0.0, 1.0, 0.0));
        translation.translate(-0.5, 0.0, -0.5);
        Textures.SCREEN.renderSided(EnumFacing.UP, renderState, translation, pipeline);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("Inventory", inventory.serializeNBT());
        data.setTag("FluidInventory", fluids.serializeNBT());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.inventory.deserializeNBT(data.getCompoundTag("Inventory"));
        this.fluids.deserializeNBT(data.getCompoundTag("FluidInventory"));
    }

    @Override
    protected boolean shouldSerializeInventories() {
        return false;
    }

}
