package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.MotorCasing;
import gregicadditions.item.components.PumpCasing;
import gregicadditions.item.metal.MetalCasing1;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
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

public class TileEntityLargeExtractor extends LargeSimpleRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY, MultiblockAbility.MAINTENANCE_HATCH};

    public TileEntityLargeExtractor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.EXTRACTOR_RECIPES, GAConfig.multis.largeExtractor.euPercentage, GAConfig.multis.largeExtractor.durationPercentage, GAConfig.multis.largeExtractor.chancedBoostPercentage, GAConfig.multis.largeExtractor.stack);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeExtractor(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "X###X", "X###X", "XXXXX")
                .aisle("XXXXX", "#XXX#", "#XXX#", "XXXXX")
                .aisle("XXXXX", "#PpM#", "#XpX#", "XXXXX")
                .aisle("XXXXX", "#XSX#", "#XXX#", "XXXXX")
                .aisle("XXXXX", "X###X", "X###X", "XXXXX")
                .setAmountAtLeast('L', 12)
                .where('S', selfPredicate())
                .where('L', statePredicate(casingState))
                .where('X', statePredicate(casingState).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('p', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE)))
                .where('#', (tile) -> true)
                .where('P', pumpPredicate())
                .where('M', motorPredicate())
                .build();
    }

    private static final IBlockState casingState = GAMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.TALONITE);

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return ClientHandler.TALONITE_CASING;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        PumpCasing.CasingType pump = context.getOrDefault("Pump", PumpCasing.CasingType.PUMP_LV);
        MotorCasing.CasingType motor = context.getOrDefault("Motor", MotorCasing.CasingType.MOTOR_LV);
        int min = Math.min(pump.getTier(), motor.getTier());
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
        return Textures.EXTRACTOR_OVERLAY;
    }
}
