package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.client.ClientHandler;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.components.PistonCasing;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.unification.material.type.Material;
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

import static gregtech.api.unification.material.Materials.Iron;
import static net.minecraft.block.BlockDirectional.FACING;

public class TileEntityLargeForgeHammer extends LargeSimpleRecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};


    public TileEntityLargeForgeHammer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.LARGE_FORGE_HAMMER_RECIPES, GAConfig.multis.largeForgeHammer.euPercentage, GAConfig.multis.largeForgeHammer.durationPercentage, GAConfig.multis.largeForgeHammer.chancedBoostPercentage, GAConfig.multis.largeForgeHammer.stack);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("SBX", "X#X", "XPX", "XpX")
                .setAmountAtLeast('X', 4)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('B', blockPredicate(Blocks.IRON_BLOCK))
                .where('P', statePredicate(Blocks.PISTON.getDefaultState().withProperty(FACING, EnumFacing.DOWN)))
                .where('#', isAirPredicate())
                .where('p', pistonPredicate())
                .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.large_forge_hammer.tooltip"));
    }
    private static final Material defaultMaterial = Iron;
    public static final Material casingMaterial = getCasingMaterial(defaultMaterial, GAConfig.multis.largeForgeHammer.casingMaterial);


    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(casingMaterial);
    }


    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(casingMaterial);
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
        return ClientHandler.IMPLOSION_OVERLAY;
    }
}
