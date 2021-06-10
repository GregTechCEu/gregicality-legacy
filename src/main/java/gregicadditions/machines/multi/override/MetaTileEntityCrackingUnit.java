package gregicadditions.machines.multi.override;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static gregtech.api.recipes.RecipeMaps.CRACKING_RECIPES;
import static gregtech.api.render.Textures.CLEAN_STAINLESS_STEEL_CASING;

public class MetaTileEntityCrackingUnit extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    protected int heatingCoilTier = 0;

    public MetaTileEntityCrackingUnit(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, CRACKING_RECIPES);
        this.recipeMapWorkable = new CrackingUnitWorkable(this);
    }

    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityCrackingUnit(this.metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        BlockWireCoil.CoilType coilType = context.getOrDefault("coilType", HEATING_COILS[0]);
        int coilTier = Arrays.asList(HEATING_COILS).indexOf(coilType);

        coilTier = Math.max(0, coilTier);
        this.heatingCoilTier = coilTier;
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof BlockWireCoil))
                return false;
            BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
            BlockWireCoil.CoilType coilType = blockWireCoil.getState(blockState);
            if (Arrays.asList(GAConfig.multis.heatingCoils.gtceHeatingCoilsBlacklist).contains(coilType.getName()))
                return false;
            BlockWireCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("coilType", coilType);
            return coilType.equals(currentCoilType);
        };
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.heatingCoilTier = 0;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (isStructureFormed() && !hasProblems()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.energy_usage", 100 - (this.heatingCoilTier*5)).setStyle(new Style().setColor(TextFormatting.AQUA)));
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("HCHCH", "HCHCH", "HCHCH")
                .aisle("HCHCH", "H###H", "HCHCH")
                .aisle("HCHCH", "HCOCH", "HCHCH")
                .setAmountAtLeast('L', 16)
                .where('O', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('H', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .where('C', heatingCoilPredicate())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return CLEAN_STAINLESS_STEEL_CASING;
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    protected class CrackingUnitWorkable extends LargeSimpleRecipeMapMultiblockController.LargeSimpleMultiblockRecipeLogic {

        public CrackingUnitWorkable(RecipeMapMultiblockController tileEntity) {
            super(tileEntity, 100 - (heatingCoilTier*5), 100, 100, 0);
        }

    }
}
