package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoidMinerOres {


    public static List<ItemStack> ORES = new ArrayList<>();
    public static List<ItemStack> ORES_2 = new ArrayList<>();
    public static List<ItemStack> ORES_3 = new ArrayList<>();

    public static void init() {
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (GAConfig.multis.voidMiner.oreVariants) {
                if (!Arrays.asList(GAConfig.multis.voidMiner.oreBlacklist).contains(material.toString())) {
                    ORES.addAll(OreDictUnifier.getAll(new UnificationEntry(OrePrefix.ore, material)));
                }
                if (!Arrays.asList(GAConfig.multis.voidMiner.oreBlacklistUHV).contains(material.toString())) {
                    ORES_2.addAll(OreDictUnifier.getAll(new UnificationEntry(OrePrefix.ore, material)));
                }
                if (!Arrays.asList(GAConfig.multis.voidMiner.oreBlacklistUEV).contains(material.toString())) {
                    ORES_3.addAll(OreDictUnifier.getAll(new UnificationEntry(OrePrefix.ore, material)));
                }
            } else {
                if (!Arrays.asList(GAConfig.multis.voidMiner.oreBlacklist).contains(material.toString())) {
                    ORES.add(OreDictUnifier.get(new UnificationEntry(OrePrefix.ore, material)));
                }
                if (!Arrays.asList(GAConfig.multis.voidMiner.oreBlacklistUHV).contains(material.toString())) {
                    ORES_2.add(OreDictUnifier.get(new UnificationEntry(OrePrefix.ore, material)));
                }
                if (!Arrays.asList(GAConfig.multis.voidMiner.oreBlacklistUEV).contains(material.toString())) {
                    ORES_3.add(OreDictUnifier.get(new UnificationEntry(OrePrefix.ore, material)));
                }
            }
        }

    }
}
