package gregicadditions.client;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.MetaItems;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.HashMap;
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
     *
     * Examples of the methods:
     *
     * Normal Blocks and Items:
     * <code>
     *     setLocalization(EN_US, "tile.wire_coil.cupronickel.name", "funni coil");
     * </code>
     *
     * GTCE MetaItems:
     * <code>
     *     setMetaItemName(RU_RU, MetaItems.CREDIT_COPPER.getMetaItem(), "модный кредит");
     * </code>
     *
     * GTCE MetaItem Tooltips:
     * <code>
     *     setMetaItemTooltip(ZH_CN, MetaItems.CREDIT_COPPER.getMetaItem(), "最值钱!");
     * </code>
     */
    public static void registerOverrides() {
    }


    /**
     * Implementation details below.
     */
    private static final Map<String, String> TRANSLATIONS = LanguageMap.instance.languageList;
    public static final List<SetTranslation> TRANSLATION_LIST = new ArrayList<>();
    public static final Map<MetaItem<?>, MetaItemName> META_ITEM_TRANSLATIONS = new HashMap<>();

    private static void setLocalization(String lang, String key, String value) {
        SetTranslation translation = new SetTranslation(lang, key, value);
        TRANSLATION_LIST.add(translation);
        translation.apply();
    }

    private static void setLocalization(String key, String value) {
        setLocalization(null, key, value);
    }

    private static void setMetaItemName(String locale, MetaItem<?> item, String name) {
        if (META_ITEM_TRANSLATIONS.containsKey(item))
            META_ITEM_TRANSLATIONS.put(item, META_ITEM_TRANSLATIONS.get(item).setName(locale, name));
        else META_ITEM_TRANSLATIONS.put(item, new MetaItemName().setName(locale, name));
    }

    private static void setMetaItemName(MetaItem<?> item, String name) {
        setMetaItemName(EN_US, item, name);
        setMetaItemName(RU_RU, item, name);
        setMetaItemName(ZH_CN, item, name);
    }

    private static void setMetaItemTooltip(String locale, MetaItem<?> item, String tooltip) {
        if (META_ITEM_TRANSLATIONS.containsKey(item))
            META_ITEM_TRANSLATIONS.put(item, META_ITEM_TRANSLATIONS.get(item).setTooltip(locale, tooltip));
        else META_ITEM_TRANSLATIONS.put(item, new MetaItemName().setTooltip(locale, tooltip));
    }

    private static void setMetaItemTooltip(MetaItem<?> item, String name) {
        setMetaItemTooltip(EN_US, item, name);
        setMetaItemTooltip(RU_RU, item, name);
        setMetaItemTooltip(ZH_CN, item, name);
    }

    private static String getLanguage() {
        return FMLCommonHandler.instance().getSide() == Side.SERVER ? null : FMLClientHandler.instance().getCurrentLanguage();
    }

    private static boolean checkLanguage(String lang) {
        String current = getLanguage();
        return current != null && current.equals(lang);
    }

    public static class SetTranslation {

        private final String lang;
        private final String key;
        private final String text;

        private SetTranslation(String lang, String key, String text) {
            this.lang = lang;
            this.key = key;
            this.text = text;
        }

        public void apply() {
            if (lang == null || checkLanguage(lang))
                TRANSLATIONS.put(key, text);
        }
    }

    public static class MetaItemName {

        private final Map<String, String> names;
        private final Map<String, String> tooltips;

        private MetaItemName() {
            names = new HashMap<>();
            tooltips = new HashMap<>();
        }

        private MetaItemName setName(String locale, String name) {
            this.names.put(locale, name);
            return this;
        }

        private MetaItemName setTooltip(String locale, String tooltip) {
            this.tooltips.put(locale, tooltip);
            return this;
        }

        public String getName() {
            String name = names.get(getLanguage());
            if (name == null && !names.isEmpty())
                name = (String) names.keySet().toArray()[0];
            return name;
        }

        public String getTooltip() {
            String tooltip = tooltips.get(getLanguage());
            if (tooltip == null && !tooltips.isEmpty())
                tooltip = (String) tooltips.keySet().toArray()[0];
            return tooltip;
        }
    }
}
