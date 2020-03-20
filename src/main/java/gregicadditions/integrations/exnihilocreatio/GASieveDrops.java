package gregicadditions.integrations.exnihilocreatio;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.registries.manager.ISieveDefaultRegistryProvider;
import exnihilocreatio.registries.registries.SieveRegistry;
import exnihilocreatio.util.ItemInfo;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTLog;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GASieveDrops implements ISieveDefaultRegistryProvider {
	@Override
	public void registerRecipeDefaults(@NotNull SieveRegistry registry) {
        GTLog.logger.info("vcowe4rhgvoweyuirgvo");
		for (SieveDrops recipe : SieveDrops.drops) {
			String type = recipe.input.equals("sand") ? "oreSandyChunk" : recipe.input.equals("nether") ? "oreNetherChunk" : recipe.input.equals("end") ? "oreEnderChunk" : "oreChunk";
			ItemStack stack = OreDictUnifier.get(OrePrefix.valueOf(type), recipe.material);
			if (!recipe.input.equals("nether") && !recipe.input.equals("end"))
				registry.register(recipe.input, new ItemInfo(stack.getItem(), stack.getMetadata()), recipe.chance, recipe.level);
			else
				registry.register(new ItemStack(recipe.input.equals("nether") ? ModBlocks.netherrackCrushed : ModBlocks.endstoneCrushed), new ItemInfo(stack.getItem(), stack.getMetadata()), recipe.chance, recipe.level);
		}
	}
}