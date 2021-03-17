package gregicadditions.covers;

import gregicadditions.Gregicality;
import gregicadditions.item.GAMetaItems;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.CoverDefinition;
import gregtech.api.cover.ICoverable;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.covers.CoverConveyor;
import gregtech.common.covers.CoverPump;
import gregtech.common.covers.CoverRoboticArm;
import gregtech.common.items.behaviors.CoverPlaceBehavior;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiFunction;

public class CoverBehaviors {

    public static void init() {
        registerBehavior(100, new ResourceLocation(Gregicality.MODID, "conveyor.uhv"), GAMetaItems.CONVEYOR_MODULE_UHV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(101, new ResourceLocation(Gregicality.MODID, "conveyor.uev"), GAMetaItems.CONVEYOR_MODULE_UEV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(102, new ResourceLocation(Gregicality.MODID, "conveyor.uiv"), GAMetaItems.CONVEYOR_MODULE_UIV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(103, new ResourceLocation(Gregicality.MODID, "conveyor.umv"), GAMetaItems.CONVEYOR_MODULE_UMV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(104, new ResourceLocation(Gregicality.MODID, "conveyor.uxv"), GAMetaItems.CONVEYOR_MODULE_UXV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(105, new ResourceLocation(Gregicality.MODID, "conveyor.max"), GAMetaItems.CONVEYOR_MODULE_MAX, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(106, new ResourceLocation(Gregicality.MODID, "robotic_arm.uhv"), GAMetaItems.ROBOT_ARM_UHV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(107, new ResourceLocation(Gregicality.MODID, "robotic_arm.uev"), GAMetaItems.ROBOT_ARM_UEV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(108, new ResourceLocation(Gregicality.MODID, "robotic_arm.uiv"), GAMetaItems.ROBOT_ARM_UIV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(109, new ResourceLocation(Gregicality.MODID, "robotic_arm.umv"), GAMetaItems.ROBOT_ARM_UMV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(110, new ResourceLocation(Gregicality.MODID, "robotic_arm.uxv"), GAMetaItems.ROBOT_ARM_UXV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(111, new ResourceLocation(Gregicality.MODID, "robotic_arm.max"), GAMetaItems.ROBOT_ARM_MAX, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(112, new ResourceLocation(Gregicality.MODID, "pump.uhv"), GAMetaItems.ELECTRIC_PUMP_UHV, (tile, side) -> new CoverPump(tile, side, 9, 20971520 * 4));
        registerBehavior(113, new ResourceLocation(Gregicality.MODID, "pump.uev"), GAMetaItems.ELECTRIC_PUMP_UEV, (tile, side) -> new CoverPump(tile, side, 9, 20971520 * 16));
        registerBehavior(114, new ResourceLocation(Gregicality.MODID, "pump.uiv"), GAMetaItems.ELECTRIC_PUMP_UIV, (tile, side) -> new CoverPump(tile, side, 9, 20971520 * 64));
        registerBehavior(115, new ResourceLocation(Gregicality.MODID, "pump.umv"), GAMetaItems.ELECTRIC_PUMP_UMV, (tile, side) -> new CoverPump(tile, side, 9, Integer.MAX_VALUE));
        registerBehavior(116, new ResourceLocation(Gregicality.MODID, "pump.uxv"), GAMetaItems.ELECTRIC_PUMP_UXV, (tile, side) -> new CoverPump(tile, side, 9, Integer.MAX_VALUE));
        registerBehavior(117, new ResourceLocation(Gregicality.MODID, "pump.max"), GAMetaItems.ELECTRIC_PUMP_MAX, (tile, side) -> new CoverPump(tile, side, 9, Integer.MAX_VALUE));

        registerBehavior(118, new ResourceLocation(Gregicality.MODID, CoverDigitalInterface.path), GAMetaItems.COVER_DIGITAL_INTERFACE, CoverDigitalInterface::new);

        registerBehavior(119, new ResourceLocation(Gregicality.MODID, "infinite.water.mv"), GAMetaItems.MV_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 2));
        registerBehavior(120, new ResourceLocation(Gregicality.MODID, "infinite.water.hv"), GAMetaItems.HV_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 3));
        registerBehavior(121, new ResourceLocation(Gregicality.MODID, "infinite.water.ev"), GAMetaItems.EV_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 4));
        registerBehavior(122, new ResourceLocation(Gregicality.MODID, "infinite.water.iv"), GAMetaItems.IV_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 5));
        registerBehavior(123, new ResourceLocation(Gregicality.MODID, "infinite.water.luv"), GAMetaItems.LuV_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 6));
        registerBehavior(124, new ResourceLocation(Gregicality.MODID, "infinite.water.zpm"), GAMetaItems.ZPM_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 7));
        registerBehavior(125, new ResourceLocation(Gregicality.MODID, "infinite.water.uv"), GAMetaItems.UV_INFINITE_WATER_SOURCE, (tile, side) -> new CoverInfiniteWater(tile, side, 8));

        registerBehavior(126, new ResourceLocation(Gregicality.MODID, "wireless_receiver"), GAMetaItems.WIRELESS_RECEIVER, CoverWirelessReceiver::new);
    }

    public static void registerBehavior(int coverNetworkId, ResourceLocation coverId, MetaItem<?>.MetaValueItem placerItem, BiFunction<ICoverable, EnumFacing, CoverBehavior> behaviorCreator) {
        CoverDefinition coverDefinition = new CoverDefinition(coverId, behaviorCreator, placerItem.getStackForm());
        CoverDefinition.registerCover(coverNetworkId, coverDefinition);
        placerItem.addComponents(new CoverPlaceBehavior(coverDefinition));
    }
}
