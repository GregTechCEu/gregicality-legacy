package gregicadditions.machines.multi;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static gregtech.api.unification.material.Materials.StainlessSteel;

public class MetaTileEntityCVDUnit extends GARecipeMapMultiblockController {

    int maxVoltage;

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{
            MultiblockAbility.IMPORT_ITEMS,
            MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    public MetaTileEntityCVDUnit(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.CVD_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCC", "CCCCC", "CCCCC", "CC#CC")
                .aisle("CCCCC", "GHPHG", "GIIIG", "C###C")
                .aisle("CCCCC", "CCSCC", "CCCCC", "CC#CC")
                .where('S', selfPredicate())
                .where('C', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('H', heatingCoilPredicate().or(heatingCoilPredicate2()))
                .where('P', pumpPredicate())
                .where('I', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS)))
                .where('#', isAirPredicate())
                .build();
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(StainlessSteel);
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
            int heatingCoilDiscount = coilType.getEnergyDiscount();
            int currentCoilDiscount = blockWorldState.getMatchContext().getOrPut("heatingCoilDiscount", heatingCoilDiscount);
            int heatingCoilLevel = coilType.getLevel();
            int currentCoilLevel = blockWorldState.getMatchContext().getOrPut("heatingCoilLevel", heatingCoilLevel);
            return currentCoilDiscount == heatingCoilDiscount && heatingCoilLevel == currentCoilLevel;
        };
    }

    public static Predicate<BlockWorldState> heatingCoilPredicate2() {
        return blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAHeatingCoil))
                return false;
            GAHeatingCoil blockWireCoil = (GAHeatingCoil) blockState.getBlock();
            GAHeatingCoil.CoilType coilType = blockWireCoil.getState(blockState);
            if (Arrays.asList(GAConfig.multis.heatingCoils.gregicalityheatingCoilsBlacklist).contains(coilType.getName()))
                return false;
            int heatingCoilDiscount = coilType.getEnergyDiscount();
            int currentCoilDiscount = blockWorldState.getMatchContext().getOrPut("heatingCoilDiscount", heatingCoilDiscount);
            int heatingCoilLevel = coilType.getLevel();
            int currentCoilLevel = blockWorldState.getMatchContext().getOrPut("heatingCoilLevel", heatingCoilLevel);
            return currentCoilDiscount == heatingCoilDiscount && heatingCoilLevel == currentCoilLevel;
        };
    }

    public static Predicate<BlockWorldState> pumpPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof PumpCasing)) {
                return false;
            } else {
                int maxVoltage;
                PumpCasing motorCasing = (PumpCasing) blockState.getBlock();
                PumpCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                PumpCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Pump", tieredCasingType);
                maxVoltage = (int) Math.pow(4, currentCasing.getTier()) * 8;

                int currentMaxVoltage = blockWorldState.getMatchContext().getOrPut("maxVoltage", maxVoltage);
                return currentMaxVoltage == maxVoltage;
            }
        };
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        maxVoltage = context.getOrDefault("maxVoltage", 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.cvd_unit.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.cvd_unit.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.cvd_unit.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.cvd_unit.tooltip.4"));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(StainlessSteel);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityCVDUnit(metaTileEntityId);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.CHEMICAL_REACTOR_OVERLAY;
    }

    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getEUt() < maxVoltage;
    }
}
