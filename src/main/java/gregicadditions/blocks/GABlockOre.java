package gregicadditions.blocks;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.blocks.BlockOre;

public class GABlockOre extends BlockOre {

    public final OrePrefix orePrefix;
    public Material material;

    public GABlockOre(Material material, StoneType[] allowedValues, OrePrefix orePrefix) {
        super(material, allowedValues);
        this.orePrefix = orePrefix;
        this.material = material;
    }
}
