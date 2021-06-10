package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
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
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
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
import java.util.List;

public class TileEntityLargeCanningMachine extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH};

    public TileEntityLargeCanningMachine(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largeCanningMachine.euPercentage, GAConfig.multis.largeCanningMachine.durationPercentage, GAConfig.multis.largeCanningMachine.chancedBoostPercentage, GAConfig.multis.largeCanningMachine.stack,
                new RecipeMap[]{RecipeMaps.CANNER_RECIPES, RecipeMaps.FLUID_CANNER_RECIPES, RecipeMaps.FLUID_SOLIDFICATION_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeCanningMachine(metaTileEntityId, RecipeMaps.CANNER_RECIPES);
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        switch(recipeMapIndex) {
            case 1: return Textures.FLUID_CANNER_OVERLAY;
            case 2: return Textures.FLUID_SOLIDIFIER_OVERLAY;
            default: return Textures.CANNER_OVERLAY;
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XpX", "XXX").setRepeatable(2, 8)
                .aisle("PPP", "PSP", "PPP")
                .setAmountAtLeast('L', 8)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('p', pumpPredicate())
                .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .build();
    }

    private static final IBlockState defaultCasingState = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeCanningMachine.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeCanningMachine.casingMaterial, Textures.SOLID_STEEL_CASING);
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
        int min  = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV).getTier();
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        switch (this.getRecipeMapIndex()) {
            case 1: return Textures.FLUID_CANNER_OVERLAY;
            case 2: return Textures.FLUID_SOLIDIFIER_OVERLAY;
            default: return Textures.CANNER_OVERLAY;
        }
    }
}
