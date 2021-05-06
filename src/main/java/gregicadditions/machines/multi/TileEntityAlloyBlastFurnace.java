package gregicadditions.machines.multi;

import gregicadditions.capabilities.impl.GARecipeMapMultiblockController;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static gregicadditions.GAMaterials.Staballoy;
import static gregicadditions.GAMaterials.ZirconiumCarbide;
import static gregicadditions.client.ClientHandler.ZIRCONIUM_CARBIDE_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class TileEntityAlloyBlastFurnace extends GARecipeMapMultiblockController {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.EXPORT_FLUIDS,
            MultiblockAbility.INPUT_ENERGY
    };

    public TileEntityAlloyBlastFurnace(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GARecipeMaps.BLAST_ALLOY_RECIPES);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.alloy_blast_furnace.tooltip.1"));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "YYY", "YYY", "XXX")
                .aisle("XXX", "Y#Y", "Y#Y", "XXX")
                .aisle("XSX", "YYY", "YYY", "XXX")
                .where('S', selfPredicate())
                .where('Y', statePredicate(getCoilState()))
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('#', isAirPredicate())
                .build();
    }


    protected IBlockState getCoilState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.STABALLOY);
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.ZIRCONIUM_CARBIDE);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ZIRCONIUM_CARBIDE_CASING;
    }


    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new TileEntityAlloyBlastFurnace(metaTileEntityId);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY;
    }
}
