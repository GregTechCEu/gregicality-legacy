package gregicadditions.machines.multi.override;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

import static gregtech.api.recipes.RecipeMaps.DISTILLATION_RECIPES;
import static gregtech.api.render.Textures.CLEAN_STAINLESS_STEEL_CASING;

public class MetaTileEntityDistillationTower extends GARecipeMapMultiblockController {
    public MetaTileEntityDistillationTower(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, DISTILLATION_RECIPES);
        this.recipeMapWorkable = new GAMultiblockRecipeLogic(this);
    }

    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityDistillationTower(this.metaTileEntityId);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<BlockWorldState> fluidExportPredicate = this.countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS));
        Predicate<PatternMatchContext> exactlyOneHatch = (context) -> {
            return context.getInt("HatchesAmount") == 1;
        };

        return FactoryBlockPattern.start(BlockPattern.RelativeDirection.RIGHT, BlockPattern.RelativeDirection.FRONT, BlockPattern.RelativeDirection.UP)
                .aisle("YSY", "YYY", "YYY")
                .aisle("XXX", "X#X", "XXX").setRepeatable(0, 11)
                .aisle("XXX", "XXX", "XXX")
                .where('S', this.selfPredicate())
                .where('Y', statePredicate(new IBlockState[]{this.getCasingState()}).or(abilityPartPredicate(MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY, MultiblockAbility.IMPORT_FLUIDS, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY)))
                .where('X', fluidExportPredicate.or(statePredicate(this.getCasingState())).or(abilityPartPredicate(GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY)))
                .where('#', isAirPredicate())
                .validateLayer(1, exactlyOneHatch)
                .validateLayer(2, exactlyOneHatch)
                .build();
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }
}
