package gregicadditions.blocks.factories;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import gregicadditions.blocks.GAMetalCasing;
import gregtech.api.model.AbstractBlockModelFactory;
import gregtech.api.model.ResourcePackHook;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class GAMetalCasingBlockFactory extends AbstractBlockModelFactory {


    private static final String VARIANT_DEFINITION =
            "\"$MATERIAL$\": {\n" +
                    "        \"textures\": {\n" +
                    "          \"all\": \"$TEXTURE$\",\n" +
                    "          \"particle\": \"$TEXTURE$\"\n" +
                    "        }\n" +
                    "      }";

    private static final Joiner COMMA_JOINER = Joiner.on(',');


    public static void init() {
        GAMetalCasingBlockFactory factory = new GAMetalCasingBlockFactory();
        ResourcePackHook.addResourcePackFileHook(factory);
    }

    private GAMetalCasingBlockFactory() {
        super("metal_casing_block", "metal_casing_");
    }

    @Override
    protected String fillSample(Block block, String blockStateSample) {
        ImmutableList<Material> allowedValues = ((GAMetalCasing) block).variantProperty.getAllowedValues();
        ArrayList<String> variants = new ArrayList<>();
        for (Material material : allowedValues) {
            variants.add(VARIANT_DEFINITION
                    .replace("$MATERIAL$", material.toString())
                    .replace("$TEXTURE$", MaterialIconType.valueOf("gtMetalCasing").getBlockPath(material.materialIconSet).toString())
            );
        }
        return blockStateSample.replace("$VARIANTS$", COMMA_JOINER.join(variants));
    }
}
