package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.machines.multi.MultiUtils;
import gregicadditions.recipes.GARecipeMaps;
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

public class TileEntityLargeBrewery extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH};

    public TileEntityLargeBrewery(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeBrewery.euPercentage, GAConfig.multis.largeBrewery.durationPercentage, GAConfig.multis.largeBrewery.chancedBoostPercentage, GAConfig.multis.largeBrewery.stack,
                new RecipeMap[]{RecipeMaps.BREWING_RECIPES, RecipeMaps.FERMENTING_RECIPES, GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES}, true, true, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeBrewery(metaTileEntityId, RecipeMaps.BREWING_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX#", "#XGX#", "#XGX#", "#XXX#", "#####")
                .aisle("XXXXX", "XAAAX", "XAAAX", "XAAAX", "XXXXX")
                .aisle("XXMXX", "XApAX", "PApAP", "XApAX", "XXmXX")
                .aisle("XXXXX", "XAAAX", "XAAAX", "XAAAX", "XXXXX")
                .aisle("#XXX#", "#XSX#", "#XGX#", "#XXX#", "#####")
                .setAmountAtLeast('L', 14)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('p', statePredicate(GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE)))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS)))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('M', motorPredicate())
                .where('P', pumpPredicate())
                .where('m', abilityPartPredicate(GregicAdditionsCapabilities.MUFFLER_HATCH))
                .build();
    }

    private static final IBlockState defaultCasingState = GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.GRISIUM);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeBrewery.casingMaterial, defaultCasingState);

    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeBrewery.casingMaterial, ClientHandler.GRISIUM_CASING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.recipe", this.recipeMap.getLocalizedName()));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        int min = Collections.min(Arrays.asList(motor.getTier(), pump.getTier()));
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        switch (this.getRecipeMapIndex()) {
            case 1:
                return Textures.FERMENTER_OVERLAY;
            case 2:
                return Textures.SIFTER_OVERLAY;
            default:
                return Textures.BREWERY_OVERLAY;
        }
    }


    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        switch (this.getRecipeMapIndex()) {
            case 1:
                return Textures.FERMENTER_OVERLAY;
            case 2:
                return Textures.SIFTER_OVERLAY;
            default:
                return Textures.BREWERY_OVERLAY;
        }
    }
}
