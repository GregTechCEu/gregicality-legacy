package gregicadditions.blocks.factories;

import com.google.common.base.Joiner;
import gregicadditions.blocks.AbstractBlockModelFactory;
import gregicadditions.blocks.GABlockOre;
import gregtech.api.model.ResourcePackHook;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.common.blocks.BlockOre;
import net.minecraft.block.Block;

import java.util.stream.Collectors;

public class GAOreBlockFactory extends AbstractBlockModelFactory {

    private final String orePrefix;

    private static final String VARIANT_DEFINITION =
            "      \"$STONE_TYPE$\": {\n" +
                    "        \"textures\": {\n" +
                    "          \"base_down\": \"$BASE_TEXTURE_TOP$\",\n" +
                    "          \"base_up\": \"$BASE_TEXTURE_TOP$\",\n" +
                    "          \"base_north\": \"$BASE_TEXTURE_SIDE$\",\n" +
                    "          \"base_south\": \"$BASE_TEXTURE_SIDE$\",\n" +
                    "          \"base_west\": \"$BASE_TEXTURE_SIDE$\",\n" +
                    "          \"base_east\": \"$BASE_TEXTURE_SIDE$\",\n" +
                    "          \"particle\": \"$BASE_TEXTURE_SIDE$\"\n" +
                    "        }\n" +
                    "      }";

    private static final Joiner COMMA_JOINER = Joiner.on(",\n");

    public static void init() {
        GAOreBlockFactory factory = new GAOreBlockFactory("rich");
        ResourcePackHook.addResourcePackFileHook(factory);
        GAOreBlockFactory factory2 = new GAOreBlockFactory("pure");
        ResourcePackHook.addResourcePackFileHook(factory2);
        GAOreBlockFactory factory3 = new GAOreBlockFactory("poor");
        ResourcePackHook.addResourcePackFileHook(factory3);

    }

    public GAOreBlockFactory(String orePrefix) {
        super(orePrefix + "_ore_block", orePrefix + "_ore_");
        this.orePrefix = orePrefix;
    }

    @Override
    protected String fillSample(Block block, String blockStateSample) {
        MaterialIconType ore = MaterialIconType.valueOf("ore" + orePrefix.substring(0, 1).toUpperCase() + orePrefix.substring(1));
        return blockStateSample
                .replace("$STONE_TYPES$", COMMA_JOINER.join(((BlockOre) block).STONE_TYPE.getAllowedValues().stream()
                        .map(stoneType -> VARIANT_DEFINITION
                                .replace("$STONE_TYPE$", stoneType.name)
                                .replace("$BASE_TEXTURE_TOP$", stoneType.backgroundTopTexture.toString())
                                .replace("$BASE_TEXTURE_SIDE$", stoneType.backgroundSideTexture.toString()))
                        .collect(Collectors.toList())))
                .replace("$MATERIAL_TEXTURE_NORMAL$", ore.getBlockPath(((GABlockOre) block).material.materialIconSet).toString());
    }
}
