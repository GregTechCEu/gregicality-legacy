package gregicadditions.machines.multi.nuclear;

import gregicadditions.capabilities.impl.GAMultiblockRecipeLogic;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import static gregicadditions.recipes.GARecipeMaps.GAS_CENTRIFUGE_RECIPES;
import static gregtech.api.render.Textures.CLEAN_STAINLESS_STEEL_CASING;
import static gregtech.api.unification.material.Materials.StainlessSteel;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityGasCentrifuge extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, MultiblockAbility.IMPORT_ITEMS};

    public MetaTileEntityGasCentrifuge(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GAS_CENTRIFUGE_RECIPES);
        this.recipeMapWorkable = new GAMultiblockRecipeLogic(this) {
            {
                allowOverclocking = false;
            }
        };
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#YYY#", "#YYY#", "#####", "#####", "#####", "#####", "#####")
                .aisle("YYYYY", "YYYYY", "#ZCZ#", "#Z#Z#", "#Z#Z#", "#Z#Z#", "#Z#Z#")
                .aisle("YYYYY", "YYYYY", "#CCC#", "#####", "#####", "#####", "#####")
                .aisle("YYYYY", "YYYYY", "#ZCZ#", "#Z#Z#", "#Z#Z#", "#Z#Z#", "#Z#Z#")
                .aisle("#YYY#", "#YSY#", "#####", "#####", "#####", "#####", "#####")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getBaseState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('Z', statePredicate(getCasingState()))
                .where('C', statePredicate(getVentCasing()))
                .where('#', (tile) -> true)
                .build();
    }


    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    public IBlockState getBaseState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    public IBlockState getVentCasing() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityGasCentrifuge(metaTileEntityId);
    }
}
