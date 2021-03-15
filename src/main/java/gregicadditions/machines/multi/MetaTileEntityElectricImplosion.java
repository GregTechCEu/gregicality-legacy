package gregicadditions.machines.multi;

import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import java.util.function.Predicate;

import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityElectricImplosion extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityElectricImplosion(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.ELECTRIC_IMPLOSION_RECIPES);
        this.recipeMapWorkable = new ElectricImplosionWorkable(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityElectricImplosion(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "CCC", "XXX")
                .aisle("XXX", "CNC", "CNC", "CNC", "XXX")
                .aisle("XXX", "CCC", "CSC", "CCC", "XXX")
                .setAmountAtLeast('L', 11) // TODO Should I keep this?
                .where('L', statePredicate(casingPredicate()))
                .where('S', selfPredicate())
                .where('X', statePredicate(casingPredicate()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('C', statePredicate(coilPredicate()))
                .where('N', statePredicate(neutroniumPredicate()))
                .build();
    }

    private static IBlockState coilPredicate() {
        return null;
    }

    private static IBlockState casingPredicate() {
        return GAMetaBlocks.getMetalCasingBlockState(Steel);
    }

    private static IBlockState neutroniumPredicate() {
        return null;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }

    // TODO Do I even need this?
    private static class ElectricImplosionWorkable extends GAMultiblockRecipeLogic {

        public ElectricImplosionWorkable(RecipeMapMultiblockController mte) {
            super(mte);
        }
    }
}
