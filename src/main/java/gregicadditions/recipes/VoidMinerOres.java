package gregicadditions.recipes;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class VoidMinerOres {


    public static List<ItemStack> ORES = new ArrayList<>();

    public static void init() {
        for (Material material : Material.MATERIAL_REGISTRY) {
            ORES.addAll(OreDictUnifier.getAll(new UnificationEntry(OrePrefix.ore, material)));
        }

    }
}
