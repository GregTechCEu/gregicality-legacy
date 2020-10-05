package gregicadditions.covers;

import gregicadditions.item.GAMetaItems;
import gregtech.api.GTValues;
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
        registerBehavior(100, new ResourceLocation(GTValues.MODID, "conveyor.uhv"), GAMetaItems.CONVEYOR_MODULE_UHV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(101, new ResourceLocation(GTValues.MODID, "conveyor.uev"), GAMetaItems.CONVEYOR_MODULE_UEV, (tile, side) -> new CoverConveyor(tile, side, 9, 16 * 64));
        registerBehavior(102, new ResourceLocation(GTValues.MODID, "robotic_arm.uhv"), GAMetaItems.ROBOT_ARM_UHV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(103, new ResourceLocation(GTValues.MODID, "robotic_arm.uev"), GAMetaItems.ROBOT_ARM_UEV, (tile, side) -> new CoverRoboticArm(tile, side, 9, 16 * 64));
        registerBehavior(104, new ResourceLocation(GTValues.MODID, "pump.uhv"), GAMetaItems.ELECTRIC_PUMP_UHV, (tile, side) -> new CoverPump(tile, side, 9, 20971520 * 4));
        registerBehavior(105, new ResourceLocation(GTValues.MODID, "pump.uev"), GAMetaItems.ELECTRIC_PUMP_UEV, (tile, side) -> new CoverPump(tile, side, 9, 20971520 * 16));

    }
    public static void registerBehavior(int coverNetworkId, ResourceLocation coverId, MetaItem.MetaValueItem placerItem, BiFunction<ICoverable, EnumFacing, CoverBehavior> behaviorCreator) {
        CoverDefinition coverDefinition = new CoverDefinition(coverId, behaviorCreator, placerItem.getStackForm());
        CoverDefinition.registerCover(coverNetworkId, coverDefinition);
        placerItem.addComponents(new CoverPlaceBehavior(coverDefinition));
    }
}
