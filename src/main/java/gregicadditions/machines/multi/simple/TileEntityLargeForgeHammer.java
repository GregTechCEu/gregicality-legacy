package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.item.metal.MetalCasing2;
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
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static gregicadditions.client.ClientHandler.IRON_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;
import static gregtech.api.recipes.RecipeMaps.COMPRESSOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.FORGE_HAMMER_RECIPES;
import static net.minecraft.block.BlockDirectional.FACING;

public class TileEntityLargeForgeHammer extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};


    public TileEntityLargeForgeHammer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.LARGE_FORGE_HAMMER_RECIPES, GAConfig.multis.largeForgeHammer.euPercentage, GAConfig.multis.largeForgeHammer.durationPercentage, GAConfig.multis.largeForgeHammer.chancedBoostPercentage, GAConfig.multis.largeForgeHammer.stack,
                new RecipeMap<?>[]{FORGE_HAMMER_RECIPES, COMPRESSOR_RECIPES});
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "X#X", "XPX", "XpX").setRepeatable(0, 4)
                .aisle("SXX", "X#X", "XPX", "XpX")
                .setAmountAtLeast('Y', 3)
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('P', statePredicate(Blocks.PISTON.getDefaultState().withProperty(FACING, EnumFacing.DOWN)))
                .where('#', isAirPredicate())
                .where('p', pistonPredicate())
                .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    private static final IBlockState defaultCasingState = METAL_CASING_2.getState(MetalCasing2.CasingType.IRON);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largeForgeHammer.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largeForgeHammer.casingMaterial, IRON_CASING);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityLargeForgeHammer(metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        PistonCasing.CasingType piston = context.getOrDefault("Piston", PistonCasing.CasingType.PISTON_LV);
        int min = piston.getTier();
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return (getRecipeMapIndex() == 0) ? Textures.FORGE_HAMMER_OVERLAY : Textures.COMPRESSOR_OVERLAY;
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        return (getRecipeMapIndex() == 0) ? Textures.FORGE_HAMMER_OVERLAY : Textures.COMPRESSOR_OVERLAY;
    }
}
