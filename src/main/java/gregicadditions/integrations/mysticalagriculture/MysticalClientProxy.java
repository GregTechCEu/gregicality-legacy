package gregicadditions.integrations.mysticalagriculture;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import gregicadditions.Gregicality;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Gregicality.MODID, value = Side.CLIENT)
public class MysticalClientProxy extends MysticalCommonProxy {

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    public void preInit() {
        super.preInit();
    }

    @Optional.Method(modid = MysticalAgriculture.MOD_ID)
    @Mod.EventHandler
    public void init() {
        super.init();
    }
}
