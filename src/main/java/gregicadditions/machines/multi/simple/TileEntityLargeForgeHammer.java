package gregicadditions.machines.multi.simple;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
                .aisle("SBX", "X#X", "XPX", "XXX")
                .setAmountAtLeast('X', 4)
                .where('S', selfPredicate())
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('B', blockPredicate(Blocks.IRON_BLOCK))
                .where('P', statePredicate(Blocks.PISTON.getDefaultState().withProperty(FACING, EnumFacing.DOWN)))
                .where('#', isAirPredicate())
                .build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtech.multiblock.large_forge_hammer.tooltip"));
    }


    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Iron);
    }


    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(Iron);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityLargeForgeHammer(metaTileEntityId);
    }
}
