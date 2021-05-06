package gregicadditions.machines.multi.steam;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.RecipeMapSteamMultiblockController;
import gregicadditions.capabilities.impl.SteamMultiWorkable;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockFireboxCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import static gregtech.api.unification.material.Materials.Bronze;

public class MetaTileEntitySteamOven extends RecipeMapSteamMultiblockController {

    private static final double CONVERSION_RATE = GAConfig.multis.steamMultis.steamToEU;
    private boolean isActive;

    public MetaTileEntitySteamOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.FURNACE_RECIPES, CONVERSION_RATE);
        this.recipeMapWorkable = new SteamMultiWorkable(this, CONVERSION_RATE, 8);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntitySteamOven(metaTileEntityId);
    }

    private void setActive(boolean active) {
        this.isActive = active;
        if (!getWorld().isRemote) {
            if (isStructureFormed()) {
                replaceFireboxAsActive(active);
            }
            writeCustomData(100, buf -> buf.writeBoolean(isActive));
            markDirty();
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "#C#")
                .aisle("XXX", "C#C", "#C#")
                .aisle("XXX", "CSC", "#C#")
                .setAmountAtLeast('L', 6)
                .setAmountAtMost('H', 1)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('H', abilityPartPredicate(GregicAdditionsCapabilities.STEAM))
                .where('X', state -> statePredicate(GTUtility.getAllPropertyValues(getFireboxState(), BlockFireboxCasing.ACTIVE))
                        .or(abilityPartPredicate(GregicAdditionsCapabilities.STEAM)).test(state))
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(
                        GregicAdditionsCapabilities.STEAM_IMPORT_ITEMS, GregicAdditionsCapabilities.STEAM_EXPORT_ITEMS)))
                .where('#', isAirPredicate())
                .build();
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS);
    }

    public IBlockState getFireboxState() {
        return MetaBlocks.BOILER_FIREBOX_CASING.getState(BlockFireboxCasing.FireboxCasingType.BRONZE_FIREBOX);
    }

    private boolean isFireboxPart(IMultiblockPart sourcePart) {
        return isStructureFormed() && (((MetaTileEntity) sourcePart).getPos().getY() < getPos().getY());
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        if (sourcePart != null && isFireboxPart(sourcePart)) {
            return isActive ? Textures.BRONZE_FIREBOX_ACTIVE : Textures.BRONZE_FIREBOX;
        }
        return Textures.BRONZE_PLATED_BRICKS;
    }

    @Override
    public boolean shouldRenderOverlay(IMultiblockPart sourcePart) {
        return sourcePart == null || !isFireboxPart(sourcePart);
    }

    private void replaceFireboxAsActive(boolean isActive) {
        BlockPos centerPos = getPos().offset(getFrontFacing().getOpposite()).down();
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos blockPos = centerPos.add(x, 0, z);
                IBlockState blockState = getWorld().getBlockState(blockPos);
                if (blockState.getBlock() instanceof BlockFireboxCasing) {
                    blockState = blockState.withProperty(BlockFireboxCasing.ACTIVE, isActive);
                    getWorld().setBlockState(blockPos, blockState);
                }
            }
        }
    }

    @Override
    protected void updateFormedValid() {
        super.updateFormedValid();
        if (isActive != recipeMapWorkable.isActive()) {
            setActive(recipeMapWorkable.isActive());
        }
    }

    @Override
    public void onRemoval() {
        super.onRemoval();
        if (!getWorld().isRemote && isStructureFormed()) {
            replaceFireboxAsActive(false);
        }
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.isActive = false;
        replaceFireboxAsActive(false);
    }

    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null ? 0 : (isActive ? 15 : 0);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 100) {
            this.isActive = buf.readBoolean();
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.FURNACE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
    }
}
