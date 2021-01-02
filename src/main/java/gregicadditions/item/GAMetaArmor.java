package gregicadditions.item;

import gregicadditions.GAValues;
import gregicadditions.armor.*;
import gregtech.api.items.armor.ArmorMetaItem;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GAMetaArmor extends ArmorMetaItem<ArmorMetaItem<?>.ArmorMetaValueItem> {
    @Override
    public void registerSubItems() {
        GAMetaItems.NANO_MUSCLE_SUITE_CHESTPLATE = addItem(0, "nms.chestplate").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.CHEST, 5000, 1600000));
        GAMetaItems.NANO_MUSCLE_SUITE_LEGGINS = addItem(1, "nms.leggins").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.LEGS, 5000, 1600000));
        GAMetaItems.NANO_MUSCLE_SUITE_HELMET = addItem(2, "nms.helmet").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.HEAD, 5000, 1600000));
        GAMetaItems.NANO_MUSCLE_SUITE_BOOTS = addItem(3, "nms.boots").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.FEET, 5000, 1600000));

        GAMetaItems.QUARK_TECH_SUITE_CHESTPLATE = addItem(4, "qts.chestplate").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.CHEST, 10000, 8000000, GAValues.EV));
        GAMetaItems.QUARK_TECH_SUITE_LEGGINS = addItem(5, "qts.leggins").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.LEGS, 10000, 8000000, GAValues.EV));
        GAMetaItems.QUARK_TECH_SUITE_HELMET = addItem(6, "qts.helmet").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.HEAD, 10000, 8000000, GAValues.EV));
        GAMetaItems.QUARK_TECH_SUITE_BOOTS = addItem(7, "qts.boots").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.FEET, 10000, 8000000, GAValues.EV));

        GAMetaItems.SEMIFLUID_JETPACK = addItem(8, "liquid_fuel_jetpack").setArmorLogic(new PowerlessJetpack(12000, GAValues.MV));
        GAMetaItems.IMPELLER_JETPACK = addItem(9, "impeller_jetpack").setArmorLogic(new Jetpack(128, 2520000, GAValues.MV));

        GAMetaItems.BATPACK_LV = addItem(10, "battery_pack.lv").setArmorLogic(new BatteryPack(0, 600000, GAValues.LV));
        GAMetaItems.BATPACK_MV = addItem(11, "battery_pack.mv").setArmorLogic(new BatteryPack(0, 2400000, GAValues.MV));
        GAMetaItems.BATPACK_HV = addItem(12, "battery_pack.hv").setArmorLogic(new BatteryPack(0, 9600000, GAValues.HV));

        GAMetaItems.ADVANCED_QAURK_TECH_SUITE_CHESTPLATE = addItem(13, "qts.advanced_chestplate").setArmorLogic(new AdvancedQurakTechSuite());
        GAMetaItems.ADVANCED_NANO_MUSCLE_CHESTPLATE = addItem(14, "nms.advanced_chestplate").setArmorLogic(new AdvancedNanoMuscleSuite());
        GAMetaItems.ADVANCED_IMPELLER_JETPACK = addItem(15, "advanced_impeller_jetpack").setArmorLogic(new AdvancedJetpack(512, 11400000, GAValues.HV));

        GAMetaItems.IMPELLER_JETPACK.setModelAmount(8);
        GAMetaItems.BATPACK_LV.setModelAmount(8);
        GAMetaItems.BATPACK_MV.setModelAmount(8);
        GAMetaItems.BATPACK_HV.setModelAmount(8);
    }
}
