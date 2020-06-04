package gregicadditions.integrations.mysticalagriculture.block;

import gregicadditions.blocks.AbstractBlockModelFactory;
import gregtech.api.model.ResourcePackHook;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.Block;

public class CropBlockModelFactory extends AbstractBlockModelFactory {

    private static final String VARIANT_DEFINITION = "\"age=7\": { \"model\": \"$TEXTURE$\" }";


    public static void init() {
        CropBlockModelFactory factory = new CropBlockModelFactory();
        ResourcePackHook.addResourcePackFileHook(factory);
    }

    private CropBlockModelFactory() {
        super("crop_block", "crop_");
    }

    @Override
    protected String fillSample(Block block, String blockStateSample) {
        Material material = ((BlockCrop) block).getMaterial();

        return blockStateSample.replace("$LAST_AGE$", VARIANT_DEFINITION
                .replace("$TEXTURE$", MaterialIconType.valueOf("crop").getBlockPath(material.materialIconSet).toString()));
    }
}
