package gregicadditions.item.metal;

import gregtech.api.render.ICubeRenderer;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.common.blocks.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.client.ClientHandler.*;

public class MetalCasing1 extends VariantBlock<MetalCasing1.CasingType> {

    public MetalCasing1() {
        super(Material.IRON);
        setTranslationKey("ga_metal_casing_1");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(MetalCasing1.CasingType.HASTELLOY_X78));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        HASTELLOY_X78("casing_hastelloy_x78", HastelloyX78),
        HASTELLOY_N("casing_hastelloy_n", HastelloyN),
        HASTELLOY_K243("casing_hastelloy_k243", HastelloyK243),
        INCOLOY_813("casing_incoloy_813", Incoloy813),
        INCOLOY_MA956("casing_incoloy_ma956", IncoloyMA956),
        MARAGING_STEEL_250("casing_maraging_steel_250", MaragingSteel250),
        NITINOL_60("casing_nitinol_60", Nitinol60),
        INCONEL_625("casing_inconel_625", Inconel625),
        GRISIUM("casing_grisium", Grisium),
        EGLIN_STEEL("casing_eglin_steel", EglinSteel),
        BABBITT_ALLOY("casing_babbitt_alloy", BabbittAlloy),
        HG_1223("casing_hg_1223", HG1223),
        TUMBAGA("casing_tumbaga", Tumbaga),
        TALONITE("casing_talonite", Talonite),
        ZIRCONIUM_CARBIDE("casing_zirconium_carbide", ZirconiumCarbide),
        POTIN("casing_potin", Potin);

        private final String name;
        private final IngotMaterial material;

        CasingType(String name, IngotMaterial material) {
            this.name = name;
            this.material = material;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public IngotMaterial getMaterial() { return this.material; }

        public ICubeRenderer getTexture() {
            switch (name) {
                case "casing_hastelloy_x78": { return HASTELLOY_X78_CASING; }
                case "casing_hastelloy_n": { return HASTELLOY_N_CASING; }
                case "casing_hastelloy_k243": { return HASTELLOY_K243_CASING; }
                case "casing_incoloy_813": { return INCOLOY_813_CASING; }
                case "casing_incoloy_ma956": { return INCOLOY_MA956_CASING; }
                case "casing_maraging_steel_250": { return MARAGING_STEEL_250_CASING; }
                case "casing_nitinol_60": { return NITINOL_60_CASING; }
                case "casing_inconel_625": { return INCONEL_625_CASING; }
                case "casing_grisium": { return GRISIUM_CASING; }
                case "casing_eglin_steel": { return EGLIN_STEEL_CASING; }
                case "casing_babbitt_alloy": { return BABBITT_ALLOY_CASING; }
                case "casing_hg_1223": { return HG_1223_CASING; }
                case "casing_tumbaga": { return TUMBAGA_CASING; }
                case "casing_talonite": { return TALONITE_CASING; }
                case "casing_zirconium_carbide": { return ZIRCONIUM_CARBIDE_CASING; }
                default: { return POTIN_CASING; }
            }
        }
    }
}
