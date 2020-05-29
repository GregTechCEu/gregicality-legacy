package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.cucumber.registry.ModRegistry;
import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import gregicadditions.Gregicality;
import gregicadditions.integrations.mysticalagriculture.block.ModBlocks;
import gregicadditions.integrations.mysticalagriculture.items.ModItems;
import gregicadditions.integrations.mysticalagriculture.items.MysticalAgricultureMetaItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;

@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class MysticalCommonProxy {

    public static final ModRegistry REGISTRY = ModRegistry.create(Gregicality.MODID);

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    public void preInit() {
        MysticalAgricultureEnum.preInit();
        MysticalAgricultureMetaItems.preInit();
        ModBlocks.init();
        ModItems.init();
        CropType.init();
    }

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @Mod.EventHandler
    public void init() {

    }
}
