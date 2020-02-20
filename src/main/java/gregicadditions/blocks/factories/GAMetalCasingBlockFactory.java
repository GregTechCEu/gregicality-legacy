package gregicadditions.blocks.factories;

import gregicadditions.blocks.GAMetalCasing;
import gregtech.api.model.AbstractBlockModelFactory;
import gregtech.api.model.ResourcePackHook;
import gregtech.api.unification.material.MaterialIconType;
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.Block;

public class GAMetalCasingBlockFactory extends AbstractBlockModelFactory {
	public static void init() {
		GAMetalCasingBlockFactory factory = new GAMetalCasingBlockFactory();
		ResourcePackHook.addResourcePackFileHook(factory);
	}

	private GAMetalCasingBlockFactory() {
		super("metal_casing_block", "metal_casing_");
	}

	@Override
	protected String fillSample(Block block, String blockStateSample) {
		Material material = ((GAMetalCasing) block).getMetalCasingMaterial();
		return blockStateSample.replace("$MATERIAL$", material.toString())
				.replace("$TEXTURE$", MaterialIconType.valueOf("gtMetalCasing").getBlockPath(material.materialIconSet).toString());
	}
}
