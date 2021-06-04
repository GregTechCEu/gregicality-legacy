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
import static gregtech.api.unification.material.Materials.*;

public class MetalCasing2 extends VariantBlock<MetalCasing2.CasingType> {

    public MetalCasing2() {
        super(Material.IRON);
        setTranslationKey("ga_metal_casing_2");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.STABALLOY));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        STABALLOY("casing_staballoy", Staballoy),
        STELLITE("casing_stellite", Stellite),
        ENRICHED_NAQUADAH_ALLOY("casing_enriched_naquadah_alloy", EnrichedNaquadahAlloy),
        QUANTUM("casing_quantum", Quantum),
        TRITANIUM("casing_tritanium", Tritanium),
        BLACK_STEEL("casing_black_steel", BlackSteel),
        RED_STEEL("casing_red_steel", RedSteel),
        GOLD("casing_gold", Gold),
        IRON("casing_iron", Iron),
        HSS_G("casing_hss_g", HSSG),
        HSS_S("casing_hss_s", HSSS),
        LEAD("casing_lead", Lead),
        NAQUADRIA("casing_naquadria", Naquadria);


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
                case "casing_staballoy": { return STABALLOY_CASING; }
                case "casing_stellite": { return STELLITE_CASING; }
                case "casing_enriched_naquadah_alloy": { return ENRICHED_NAQUADAH_ALLOY_CASING; }
                case "casing_quantum": { return QUANTUM_CASING; }
                case "casing_tritanium": { return TRITANIUM_CASING; }
                case "casing_black_steel": { return BLACK_STEEL_CASING; }
                case "casing_red_steel": { return RED_STEEL_CASING; }
                case "casing_gold": { return GOLD_CASING; }
                case "casing_iron": { return IRON_CASING; }
                case "casing_hss_g": { return HSS_G_CASING; }
                case "casing_hss_s": { return HSS_S_CASING; }
                case "casing_lead": { return LEAD_CASING; }
                default: { return NAQUADRIA_CASING; }
            }
        }
    }
}
