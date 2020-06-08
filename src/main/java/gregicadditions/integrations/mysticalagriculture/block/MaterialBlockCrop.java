package gregicadditions.integrations.mysticalagriculture.block;

import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import gregtech.api.unification.material.type.Material;

import java.util.HashMap;
import java.util.Map;

public class MaterialBlockCrop extends BlockMysticalCrop {

    public static final Map<Material, MaterialBlockCrop> REGISTRY = new HashMap<>();

    private final Material material;

    public MaterialBlockCrop(String name, Material material) {
        super(name);
        this.material = material;
        REGISTRY.put(material, this);
    }

    public Material getMaterial() {
        return material;
    }

}
