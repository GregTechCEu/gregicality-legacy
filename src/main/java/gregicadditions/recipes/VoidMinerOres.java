package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.utils.GALog;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.Item;
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
        addItemsToList(GAConfig.multis.voidMiner.oreWhitelist, ORES);
        addItemsToList(GAConfig.multis.voidMiner.oreWhitelistUHV, ORES_2);
        addItemsToList(GAConfig.multis.voidMiner.oreWhitelistUEV, ORES_3);
    }
    private static void addItemsToList(String[] itemStrings, List<ItemStack> list) {
        for (String item : itemStrings) {
            if (!item.isEmpty()) {
                long count = item.chars().filter(c -> c == ':').count();
                int meta = 0;
                //Invalid string
                if (count > 2 || count < 1) {
                    GALog.logger.warn("Invalid amount of : in Void Miner whitelist string: " + item);
                    continue;
                }
                String itemStr = item;
                //Has metadata
                if (count == 2) {
                    int index = item.lastIndexOf(':');
                    meta = Integer.parseInt(item.substring(index + 1));
                    itemStr = item.substring(0, index);
                }
                Item tempItem = Item.getByNameOrId(itemStr);
                if (tempItem == null) {
                    GALog.logger.warn("Invalid item in Void Miner whitelist string: " + item);
                    continue;
                }
                ItemStack itemStack = new ItemStack(tempItem, 1, meta);
                list.add(itemStack);
            }
        }
    }
}
