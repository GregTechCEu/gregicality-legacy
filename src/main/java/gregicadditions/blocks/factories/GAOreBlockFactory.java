package gregicadditions.blocks.factories;

import com.google.common.base.Joiner;
import gregicadditions.blocks.AbstractBlockModelFactory;
import gregtech.api.model.ResourcePackHook;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.common.blocks.BlockOre;
import net.minecraft.block.Block;

import java.util.stream.Collectors;

public class GAOreBlockFactory extends AbstractBlockModelFactory {

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
        GAOreBlockFactory factory = new GAOreBlockFactory();
        ResourcePackHook.addResourcePackFileHook(factory);
    }

    public GAOreBlockFactory() {
        super("dense_ore_block", "dense_ore_");
    }

    @Override
    protected String fillSample(Block block, String blockStateSample) {
        return blockStateSample
                .replace("$STONE_TYPES$", COMMA_JOINER.join(((BlockOre) block).STONE_TYPE.getAllowedValues().stream()
                        .map(stoneType -> VARIANT_DEFINITION
                                .replace("$STONE_TYPE$", stoneType.name)
                                .replace("$BASE_TEXTURE_TOP$", stoneType.backgroundTopTexture.toString())
                                .replace("$BASE_TEXTURE_SIDE$", stoneType.backgroundSideTexture.toString()))
                        .collect(Collectors.toList())))
                .replace("$MATERIAL_TEXTURE_NORMAL$", MaterialIconType.ore.getBlockPath(((BlockOre) block).material.materialIconSet).toString());
    }
}
