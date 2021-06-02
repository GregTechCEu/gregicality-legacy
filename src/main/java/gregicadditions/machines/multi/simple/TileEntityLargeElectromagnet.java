package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.FieldGenCasing;
import gregicadditions.item.metal.MetalCasing1;
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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TileEntityLargeElectromagnet extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};

    public TileEntityLargeElectromagnet(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeElectromagnet.euPercentage, GAConfig.multis.largeElectromagnet.durationPercentage, GAConfig.multis.largeElectromagnet.chancedBoostPercentage, GAConfig.multis.largeElectromagnet.stack,
                new RecipeMap[]{RecipeMaps.POLARIZER_RECIPES, RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeElectromagnet(metaTileEntityId, RecipeMaps.POLARIZER_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXX#", "#XXX#", "#XXX#")
                .aisle("XXXXX", "XAAAX", "XGXGX")
                .aisle("XXXXX", "XAFAX", "XGXGX")
                .aisle("XXXXX", "XAAAX", "XGXGX")
                .aisle("#XXX#", "#XSX#", "#XXX#")
                .setAmountAtLeast('L', 8)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('A', isAirPredicate())
                .where('#', (tile) -> true)
                .where('F', fieldGenPredicate())
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.NICKEL_GLASS)))
                .build();
    }

    private static final IBlockState defaultCasingState = GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.BABBITT_ALLOY);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeElectromagnet.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeElectromagnet.casingMaterial, ClientHandler.BABBITT_ALLOY_CASING);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int min = context.getOrDefault("FieldGen", FieldGenCasing.CasingType.FIELD_GENERATOR_LV).getTier();
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
        return (getRecipeMapIndex() == 0) ? Textures.POLARIZER_OVERLAY : Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY;
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        return (getRecipeMapIndex() == 0) ? Textures.POLARIZER_OVERLAY : Textures.ELECTROMAGNETIC_SEPARATOR_OVERLAY;
    }
}
