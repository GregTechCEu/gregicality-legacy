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

public class NuclearCasing extends VariantBlock<NuclearCasing.CasingType> {

    public NuclearCasing() {
        super(Material.IRON);
        setTranslationKey("ga_nuclear_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(NuclearCasing.CasingType.THORIUM));
    }

    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        THORIUM("casing_thorium", ThoriumRadioactive.getMaterial()),
        PROTACTINIUM("casing_protactinium", Protactinium.getMaterial()),
        URANIUM("casing_uranium", UraniumRadioactive.getMaterial()),
        NEPTUNIUM("casing_neptunium", Neptunium.getMaterial()),
        PLUTONIUM("casing_plutonium", PlutoniumRadioactive.getMaterial()),
        AMERICIUM("casing_americium", AmericiumRadioactive.getMaterial()),
        CURIUM("casing_curium", Curium.getMaterial()),
        BERKELIUM("casing_berkelium", Berkelium.getMaterial()),
        CALIFORNIUM("casing_californium", Californium.getMaterial()),
        EINSTEINIUM("casing_einsteinium", Einsteinium.getMaterial()),
        FERMIUM("casing_fermium", Fermium.getMaterial()),
        MENDELEVIUM("casing_mendelevium", Mendelevium.getMaterial());


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
                case "casing_thorium": { return THORIUM_CASING; }
                case "casing_protactinium": { return PROTACTINIUM_CASING; }
                case "casing_uranium": { return URANIUM_CASING; }
                case "casing_neptunium": { return NEPTUNIUM_CASING; }
                case "casing_plutonium": { return PLUTONIUM_CASING; }
                case "casing_americium": { return AMERICIUM_CASING; }
                case "casing_curium": { return CURIUM_CASING; }
                case "casing_berkelium": { return BERKELIUM_CASING; }
                case "casing_californium": { return CALIFORNIUM_CASING; }
                case "casing_einsteinium": { return EINSTEINIUM_CASING; }
                case "casing_fermium": { return FERMIUM_CASING; }
                default: { return MENDELEVIUM_CASING; }
            }
        }
    }
}
