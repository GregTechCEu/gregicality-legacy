package gregicadditions.blocks;

import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.ore.StoneType;
import gregtech.common.blocks.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;

public class GABlockOre extends BlockOre {

    public GABlockOre(DustMaterial material, StoneType[] allowedValues) {
        super(material, allowedValues);
        setTranslationKey("block_ore2");
    }
}
