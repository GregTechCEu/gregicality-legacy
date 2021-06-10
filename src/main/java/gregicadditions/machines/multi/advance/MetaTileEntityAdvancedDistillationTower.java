package gregicadditions.machines.multi.advance;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GAMultiblockCasing2;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.MultiUtils;
import gregicadditions.machines.multi.simple.MultiRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.*;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

import static gregicadditions.client.ClientHandler.BABBITT_ALLOY_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;

public class MetaTileEntityAdvancedDistillationTower extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY,
            GregicAdditionsCapabilities.MAINTENANCE_HATCH};

    public MetaTileEntityAdvancedDistillationTower(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, 100, 100, 100, GAConfig.multis.distillationTower.distillationMultiplier,
                new RecipeMap<?>[]{RecipeMaps.DISTILLATION_RECIPES, RecipeMaps.DISTILLERY_RECIPES, RecipeMaps.FLUID_HEATER_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityAdvancedDistillationTower(metaTileEntityId, RecipeMaps.DISTILLATION_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        Predicate<BlockWorldState> fluidExportPredicate = this.countMatch("HatchesAmount", abilityPartPredicate(MultiblockAbility.EXPORT_FLUIDS));
        Predicate<PatternMatchContext> exactlyOneHatch = (context) -> context.getInt("HatchesAmount") == 1;

        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("YSY", "YYY", "YYY")
                .aisle("XXX", "XPX", "XXX").setRepeatable(0, 11)
                .aisle("XXX", "XXX", "XXX")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('X', fluidExportPredicate.or(statePredicate(getCasingState())).or(abilityPartPredicate(GregicAdditionsCapabilities.MAINTENANCE_HATCH)))
                .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .validateLayer(1, exactlyOneHatch)
                .validateLayer(2, exactlyOneHatch)
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        int min = pump.getTier();
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    private static final IBlockState defaultCasingState = METAL_CASING_1.getState(MetalCasing1.CasingType.BABBITT_ALLOY);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.distillationTower.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.distillationTower.casingMaterial, BABBITT_ALLOY_CASING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description1"));
        tooltip.add(I18n.format("gregtech.multiblock.universal.framework.tooltip"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description2"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description3"));
        tooltip.add(I18n.format("gregtech.multiblock.advanced_distillation_tower.description4"));
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return (getRecipeMapIndex() < 2) ? Textures.DISTILLERY_OVERLAY : Textures.FLUID_HEATER_OVERLAY;
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        return (getRecipeMapIndex() < 2) ? Textures.DISTILLERY_OVERLAY : Textures.FLUID_HEATER_OVERLAY;
    }
}
