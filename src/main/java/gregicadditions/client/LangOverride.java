package gregicadditions.client;

import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class used for overriding GTCE (or other) lang entries in a way that
 * is guaranteed to choose our translation over the original mod's.
 */
public class LangOverride {

    /**
     * Our currently supported translation languages.
     */
    private static final String EN_US = "en_us";
    private static final String RU_RU = "ru_ru";
    private static final String ZH_CN = "zh_cn";

    /**
     * Register your translation overrides here.
     *
     * If `lang` is unspecified, it will apply to all translations.
     *
     * If we get a sufficiently large number of overrides here, we should break it
     * into methods for each language to keep it clean.
     */
    public static void registerOverrides() {
        /*
         * Example implementation:
         * setLocalization(EN_US, "tile.wire_coil.cupronickel.name", "funni coil");
         */
        setLocalization("tile.chest.name", "Some nonsense idk");
        setLocalization("tile.wire_coil.cupronickel.name", "funni coil");
        setLocalization("metaitem.credit.copper.name", "idk man this better work");
        setLocalization("material.salt", "exquisitely salty");
    }


    /**
     * Implementation details below.
     */
    private static final Map<String, String> TRANSLATIONS = LanguageMap.instance.languageList;
    public static final List<SetTranslation> TRANSLATION_LIST = new ArrayList<>();

    private static void setLocalization(String lang, String key, String value) {
        SetTranslation translation = new SetTranslation(lang, key, value);
        TRANSLATION_LIST.add(translation);
        translation.apply();
    }

    private static void setLocalization(String key, String value) {
        setLocalization(null, key, value);
    }

    public static class SetTranslation {

        private final String lang;
        private final String key;
        private final String text;

        public SetTranslation(String lang, String key, String text) {
            this.lang = lang;
            this.key = key;
            this.text = text;
        }

        public void apply() {
            if (lang == null || checkLanguage(lang))
                TRANSLATIONS.put(key, text);
        }

        private static boolean checkLanguage(String lang) {
            String current = FMLCommonHandler.instance().getSide() == Side.SERVER ? null : FMLClientHandler.instance().getCurrentLanguage();
            return current != null && current.equals(lang);
        }
    }
}
