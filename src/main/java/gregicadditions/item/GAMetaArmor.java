package gregicadditions.item;

import gregicadditions.GAConfig;
import gregicadditions.armor.*;
import gregtech.api.items.armor.ArmorMetaItem;
import net.minecraft.inventory.EntityEquipmentSlot;

public class GAMetaArmor extends ArmorMetaItem<ArmorMetaItem<?>.ArmorMetaValueItem> {
    @Override
    public void registerSubItems() {
        GAConfig.Equipment e = GAConfig.equipment;
        GAMetaItems.NANO_MUSCLE_SUITE_CHESTPLATE = addItem(0, "nms.chestplate").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.CHEST, e.nanoSuit.energyPerUse, e.nanoSuit.capacity));
        GAMetaItems.NANO_MUSCLE_SUITE_LEGGINS = addItem(1, "nms.leggins").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.LEGS, e.nanoSuit.energyPerUse, e.nanoSuit.capacity));
        GAMetaItems.NANO_MUSCLE_SUITE_HELMET = addItem(2, "nms.helmet").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.HEAD, e.nanoSuit.energyPerUse, e.nanoSuit.capacity));
        GAMetaItems.NANO_MUSCLE_SUITE_BOOTS = addItem(3, "nms.boots").setArmorLogic(new NanoMuscleSuite(EntityEquipmentSlot.FEET, e.nanoSuit.energyPerUse, e.nanoSuit.capacity));

        GAMetaItems.QUARK_TECH_SUITE_CHESTPLATE = addItem(4, "qts.chestplate").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.CHEST, e.quarkTechSuit.energyPerUse, e.quarkTechSuit.capacity, e.quarkTechSuit.voltageTier));
        GAMetaItems.QUARK_TECH_SUITE_LEGGINS = addItem(5, "qts.leggins").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.LEGS, e.quarkTechSuit.energyPerUse, e.quarkTechSuit.capacity, e.quarkTechSuit.voltageTier));
        GAMetaItems.QUARK_TECH_SUITE_HELMET = addItem(6, "qts.helmet").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.HEAD, e.quarkTechSuit.energyPerUse, e.quarkTechSuit.capacity, e.quarkTechSuit.voltageTier));
        GAMetaItems.QUARK_TECH_SUITE_BOOTS = addItem(7, "qts.boots").setArmorLogic(new QuarkTechSuite(EntityEquipmentSlot.FEET, e.quarkTechSuit.energyPerUse, e.quarkTechSuit.capacity, e.quarkTechSuit.voltageTier));

        GAMetaItems.SEMIFLUID_JETPACK = addItem(8, "liquid_fuel_jetpack").setArmorLogic(new PowerlessJetpack(e.semiFluidJetpack.capacity, e.semiFluidJetpack.voltageTier));
        GAMetaItems.IMPELLER_JETPACK = addItem(9, "impeller_jetpack").setArmorLogic(new Jetpack(e.impellerJetpack.energyPerUse, e.impellerJetpack.capacity, e.impellerJetpack.voltageTier));

        GAMetaItems.BATPACK_LV = addItem(10, "battery_pack.lv").setArmorLogic(new BatteryPack(0, e.batpackLv.capacity, e.batpackLv.voltageTier));
        GAMetaItems.BATPACK_MV = addItem(11, "battery_pack.mv").setArmorLogic(new BatteryPack(0, e.batpackMv.capacity, e.batpackMv.voltageTier));
        GAMetaItems.BATPACK_HV = addItem(12, "battery_pack.hv").setArmorLogic(new BatteryPack(0, e.batpackHv.capacity, e.batpackHv.voltageTier));

        GAMetaItems.ADVANCED_QAURK_TECH_SUITE_CHESTPLATE = addItem(13, "qts.advanced_chestplate").setArmorLogic(new AdvancedQuarkTechSuite());
        GAMetaItems.ADVANCED_NANO_MUSCLE_CHESTPLATE = addItem(14, "nms.advanced_chestplate").setArmorLogic(new AdvancedNanoMuscleSuite());
        GAMetaItems.ADVANCED_IMPELLER_JETPACK = addItem(15, "advanced_impeller_jetpack").setArmorLogic(new AdvancedJetpack(e.advImpellerJetpack.energyPerUse, e.advImpellerJetpack.capacity, e.advImpellerJetpack.voltageTier));

        GAMetaItems.NIGHTVISION_GOGGLES = addItem(16, "nightvision_goggles").setArmorLogic(new NightvisionGoggles());

        GAMetaItems.IMPELLER_JETPACK.setModelAmount(8);
        GAMetaItems.BATPACK_LV.setModelAmount(8);
        GAMetaItems.BATPACK_MV.setModelAmount(8);
        GAMetaItems.BATPACK_HV.setModelAmount(8);
    }
}
