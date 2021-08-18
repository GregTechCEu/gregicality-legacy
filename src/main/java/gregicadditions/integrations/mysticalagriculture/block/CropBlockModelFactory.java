package gregicadditions.integrations.mysticalagriculture.block;

import gregtech.api.model.AbstractBlockModelFactory;
import gregtech.api.model.ResourcePackHook;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconType;
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
        Material material = ((MaterialBlockCrop) block).getMaterial();

        return blockStateSample.replace("$LAST_AGE$", VARIANT_DEFINITION
                .replace("$TEXTURE$", MaterialIconType.crop.getBlockPath(material.getMaterialIconSet()).toString()));
    }
}
