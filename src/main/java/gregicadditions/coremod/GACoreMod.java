package gregicadditions.coremod;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import javax.annotation.Nullable;

import java.util.Map;

public class GACoreMod implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] { GACoreMod.class.getPackage().getName() + ".GAClassTransformer" };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
