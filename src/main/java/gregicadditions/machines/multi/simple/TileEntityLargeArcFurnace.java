package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.machines.multi.MultiUtils;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TileEntityLargeArcFurnace extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};

    public TileEntityLargeArcFurnace(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeArcFurnace.euPercentage, GAConfig.multis.largeArcFurnace.durationPercentage, GAConfig.multis.largeArcFurnace.chancedBoostPercentage, GAConfig.multis.largeArcFurnace.stack,
                new RecipeMap[]{RecipeMaps.ARC_FURNACE_RECIPES, RecipeMaps.PLASMA_ARC_FURNACE_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeArcFurnace(metaTileEntityId, RecipeMaps.ARC_FURNACE_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "XCPCX", "#XGX#")
                .aisle("XXXXX", "XCpCX", "#XGX#")
                .aisle("XXXXX", "XCPCX", "#XGX#")
                .aisle("#XXX#", "#XSX#", "#XXX#")
                .setAmountAtLeast('L', 10)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TITANIUM_PIPE)))
                .where('p', pumpPredicate())
                .where('C', statePredicate(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.CUPRONICKEL)))
                .where('#', (tile) -> true)
                .where('G', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .build();
    }

    private static final IBlockState defaultCasingState = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.INVAR_HEATPROOF);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeArcFurnace.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeArcFurnace.casingMaterial, Textures.HEAT_PROOF_CASING);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int min = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV).getTier();
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.recipe", this.recipeMap.getLocalizedName()));
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return (getRecipeMapIndex() == 0) ? Textures.ARC_FURNACE_OVERLAY : Textures.PLASMA_ARC_FURNACE_OVERLAY;
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        return (getRecipeMapIndex() == 0) ? Textures.ARC_FURNACE_OVERLAY : Textures.PLASMA_ARC_FURNACE_OVERLAY;
    }
}
