package gregicadditions.blocks;

import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.blocks.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;

public class GABlockOre extends BlockOre {

    private OrePrefix orePrefix;

    public GABlockOre(DustMaterial material, StoneType[] allowedValues, OrePrefix orePrefix) {
        super(material, allowedValues);
        this.orePrefix = orePrefix;
    }

    public OrePrefix getOrePrefix() {
        return orePrefix;
    }
}
