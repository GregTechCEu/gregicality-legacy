package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.items.ModItems;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class MysticalCommonProxy {

    public static final ModRegistry REGISTRY = ModRegistry.create(Gregicality.MODID);

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @Mod.EventHandler
    public void preInit() {
        MysticalAgricultureEnum.preInit();
        ModItems.preInit();
    }

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @Mod.EventHandler
    public void init() {

    }

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @SubscribeEvent
    public void registerOrePrefix(RegistryEvent.Register<IRecipe> event) {
        ModItems.registerOreDict();

    }

    @SubscribeEvent
    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

    }

}
