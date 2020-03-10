package gregicadditions.integrations.exnihilocreatio;

import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.Siftable;
import gregicadditions.GAConfig;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ExNihiloCreatioEvents {

    @Optional.Method(modid = "tconstruct")
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
                for (ItemStack sievable : recipe.getSievables()) {
                    ItemStack mesh = recipe.getMesh();
                    SimpleRecipeBuilder builder = GARecipeMaps.SIEVE_RECIPES.recipeBuilder()
                            .notConsumable(mesh)
                            .inputs(sievable)
                            .duration(100)
                            .EUt(4);
                    for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(sievable)) {
                        if (siftable.getMeshLevel() == mesh.getMetadata()) {
                            builder.chancedOutput(
                                    siftable.getDrop().getItemStack(),
                                    floatChanceToIntChance(siftable.getChance()),
                                    750
                            );
                        }
                    }
                    builder.buildAndRegister();
                }
            }
        }
    }

    private static int floatChanceToIntChance(float chance) {
        float getMaxChancedValueAsFloat = (float) Recipe.getMaxChancedValue();
        return (int) (chance * getMaxChancedValueAsFloat);
    }
}
