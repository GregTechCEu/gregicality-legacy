package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.components.ConveyorCasing;
import gregicadditions.item.components.RobotArmCasing;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static gregicadditions.client.ClientHandler.HG_1223_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;

public class TileEntityLargePackager extends MultiRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};

    public TileEntityLargePackager(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap, GAConfig.multis.largePackager.euPercentage, GAConfig.multis.largePackager.durationPercentage, GAConfig.multis.largePackager.chancedBoostPercentage, GAConfig.multis.largePackager.stack, new RecipeMap<?>[]{
                RecipeMaps.PACKER_RECIPES, RecipeMaps.UNPACKER_RECIPES});
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargePackager(metaTileEntityId, RecipeMaps.PACKER_RECIPES);
    }

    @Override
    public OrientedOverlayRenderer getRecipeMapOverlay(int recipeMapIndex) {
        if (recipeMapIndex == 1)
            return Textures.UNPACKER_OVERLAY;
        return Textures.PACKER_OVERLAY;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "XCX", "XRX").setRepeatable(1, 6)
                .aisle("XXX", "XSX", "XXX")
                .setAmountAtLeast('L', 9)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .where('R', robotArmPredicate())
                .where('C', conveyorPredicate())
                .build();
    }

    private static final IBlockState defaultCasingState = METAL_CASING_1.getState(MetalCasing1.CasingType.HG_1223);
    public static final IBlockState casingState = MultiUtils.getConfigCasing(GAConfig.multis.largePackager.casingMaterial, defaultCasingState);


    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MultiUtils.getConfigCasingTexture(GAConfig.multis.largePackager.casingMaterial, HG_1223_CASING);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        ConveyorCasing.CasingType conveyor = context.getOrDefault("Conveyor", ConveyorCasing.CasingType.CONVEYOR_LV);
        RobotArmCasing.CasingType robotArm = context.getOrDefault("RobotArm", RobotArmCasing.CasingType.ROBOT_ARM_LV);
        int min = Collections.min(Arrays.asList(conveyor.getTier(), robotArm.getTier()));
        maxVoltage = (long) (Math.pow(4, min) * 8);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return (this.getRecipeMapIndex() == 1) ? Textures.UNPACKER_OVERLAY : Textures.PACKER_OVERLAY;
    }
}
