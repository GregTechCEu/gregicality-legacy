package gregicadditions.machines.multi.mega;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.recipes.RecipeMaps.VACUUM_RECIPES;

public class MetaTileEntityMegaVacuumFreezer extends MegaMultiblockRecipeMapController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    public MetaTileEntityMegaVacuumFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, VACUUM_RECIPES, 100, 200, 100, 0);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityMegaVacuumFreezer(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#")
                .aisle("XXXXXXX", "XPAFAPX", "XPAAAPX", "XPPPPPX", "XPAAAPX", "XPAFAPX", "XXXXXXX")
                .aisle("XXXXXXX", "XAAAAAX", "XAAAAAX", "XPGGGPX", "XAAAAAX", "XAAAAAX", "XXXXXXX").setRepeatable(3)
                .aisle("XXXXXXX", "XPAFAPX", "XPAAAPX", "XPPPPPX", "XPAAAPX", "XPAFAPX", "XXXXXXX")
                .aisle("#XXXXX#", "#XXSXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#", "#XXXXX#")
                .setAmountAtLeast('L', 100)
                .where('S', selfPredicate())
                .where('L', statePredicate(getCasingState()))
                .where('X', abilityPartPredicate(ALLOWED_ABILITIES).or(statePredicate(getCasingState())))
                .where('F', frameworkPredicate().or(frameworkPredicate2()))
                .where('P', statePredicate(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('G', statePredicate(MetaBlocks.MUTLIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('#', (tile) -> true)
                .where('A', isAirPredicate())
                .build();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.2"));
        tooltip.add(I18n.format("gtadditions.multiblock.mega_logic.tooltip.3", 1.0));
    }

    public static final IBlockState casingState = MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF);

    public IBlockState getCasingState() {
        return casingState;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.FROST_PROOF_CASING;
    }

}
