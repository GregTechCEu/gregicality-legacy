package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.items.MysticalAgricultureItems;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class MysticalCommonProxy {

    public static ModRegistry REGISTRY;

    static {
        if (Loader.isModLoaded(MysticalAgriculture.MOD_ID))
            REGISTRY = ModRegistry.create(Gregicality.MODID);
    }

    @Optional.Method(modid = Gregicality.MODID)
    @Mod.EventHandler
    public void preInit() {


        MysticalAgricultureEnum.preInit();
        MysticalAgricultureItems.preInit1();
        CropType.init();
        MysticalAgricultureItems.preInit2();
    }

    @Optional.Method(modid = Gregicality.MODID)
    @Mod.EventHandler
    public void init() {
    }

    @Optional.Method(modid = Gregicality.MODID)
    @SubscribeEvent(priority = EventPriority.LOW)
    public void registerRecipesLow(RegistryEvent.Register<IRecipe> event) {
        MysticalAgricultureItems.removeMARecipe();
    }

    @Optional.Method(modid = Gregicality.MODID)
    @SubscribeEvent
    public void registerOrePrefix(RegistryEvent.Register<IRecipe> event) {
        MysticalAgricultureItems.registerOreDict();

    }

    @SubscribeEvent
    @Optional.Method(modid = Gregicality.MODID)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        MysticalAgricultureItems.registerRecipe();

    }

}
