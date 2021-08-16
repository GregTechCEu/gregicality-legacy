package gregicadditions.recipes.helper;

import com.google.common.collect.ImmutableMap;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.GTValues;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.loaders.recipe.CraftingComponent;
import gregtech.loaders.recipe.component.IComponentHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

@IComponentHandler.RegisterComponentHandler
public class GACraftingComponents implements IComponentHandler {

    @Override
    public void onComponentsInit() {
        CraftingComponent.PUMP.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.ELECTRIC_PUMP_UHV,
                10, GAMetaItems.ELECTRIC_PUMP_UEV,
                11, GAMetaItems.ELECTRIC_PUMP_UIV,
                12, GAMetaItems.ELECTRIC_PUMP_UMV,
                13, GAMetaItems.ELECTRIC_PUMP_UXV));
        CraftingComponent.PUMP.appendIngredients(ImmutableMap.of(14, GAMetaItems.ELECTRIC_PUMP_MAX));

        CraftingComponent.CABLE.appendIngredients(ImmutableMap.of(
                9, new UnificationEntry(cableGtSingle, AbyssalAlloy),
                10, new UnificationEntry(cableGtSingle, TitanSteel),
                11, new UnificationEntry(cableGtSingle, BlackTitanium),
                12, new UnificationEntry(cableGtSingle, Neutronium),
                13, new UnificationEntry(cableGtSingle, Neutronium)));
        CraftingComponent.CABLE.appendIngredients(ImmutableMap.of(14, new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor)));

        // TODO Wire

        CraftingComponent.CABLE_QUAD.appendIngredients(ImmutableMap.of(
                9, new UnificationEntry(cableGtQuadruple, AbyssalAlloy),
                10, new UnificationEntry(cableGtQuadruple, TitanSteel),
                11, new UnificationEntry(cableGtQuadruple, BlackTitanium),
                12, new UnificationEntry(cableGtQuadruple, Neutronium),
                13, new UnificationEntry(cableGtQuadruple, Neutronium)));
        CraftingComponent.CABLE_QUAD.appendIngredients(ImmutableMap.of(14, new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor)));

        CraftingComponent.PIPE_NORMAL.appendIngredients(ImmutableMap.of(
                6, new UnificationEntry(pipeNormalFluid, Enderium),
                7, new UnificationEntry(pipeNormalFluid, Naquadah),
                8, new UnificationEntry(pipeNormalFluid, Ultimet),
                9, new UnificationEntry(pipeNormalFluid, Zeron100),
                10, new UnificationEntry(pipeNormalFluid, Lafium)));
        CraftingComponent.PIPE_NORMAL.appendIngredients(ImmutableMap.of(GTValues.FALLBACK, new UnificationEntry(pipeNormalFluid, Neutronium)));

        // TODO Pipe Large

        CraftingComponent.GLASS.appendIngredients(ImmutableMap.of(
                0, new ItemStack(Blocks.GLASS, 1, GTValues.W),
                1, new ItemStack(Blocks.GLASS, 1, GTValues.W),
                2, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS),
                3, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.BOROSILICATE_GLASS),
                4, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.NICKEL_GLASS)));
        CraftingComponent.GLASS.appendIngredients(ImmutableMap.of(
                5, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.CHROME_GLASS),
                6, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.TUNGSTEN_GLASS),
                7, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.IRIDIUM_GLASS),
                GTValues.FALLBACK, GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS)));

        CraftingComponent.PLATE.appendIngredients(ImmutableMap.of(
                6, new UnificationEntry(plate, RhodiumPlatedPalladium),
                7, new UnificationEntry(plate, HSSS),
                8, new UnificationEntry(plate, Tritanium),
                9, new UnificationEntry(plate, Seaborgium),
                10, new UnificationEntry(plate, Bohrium)));
        CraftingComponent.PLATE.appendIngredients(ImmutableMap.of(
                11, new UnificationEntry(plate, Quantum),
                GTValues.FALLBACK, new UnificationEntry(plate, Neutronium)));

        CraftingComponent.MOTOR.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.ELECTRIC_MOTOR_UHV,
                10, GAMetaItems.ELECTRIC_MOTOR_UEV,
                11, GAMetaItems.ELECTRIC_MOTOR_UIV,
                12, GAMetaItems.ELECTRIC_MOTOR_UMV,
                13, GAMetaItems.ELECTRIC_MOTOR_UXV));
        CraftingComponent.MOTOR.appendIngredients(ImmutableMap.of(14, GAMetaItems.ELECTRIC_MOTOR_MAX));

        CraftingComponent.ROTOR.appendIngredients(ImmutableMap.of(
                9, new UnificationEntry(rotor, Ruridit),
                GTValues.FALLBACK, new UnificationEntry(rotor, Neutronium)));

        CraftingComponent.SENSOR.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.SENSOR_UHV,
                10, GAMetaItems.SENSOR_UEV,
                11, GAMetaItems.SENSOR_UIV,
                12, GAMetaItems.SENSOR_UMV,
                13, GAMetaItems.SENSOR_UXV));
        CraftingComponent.SENSOR.appendIngredients(ImmutableMap.of(14, GAMetaItems.SENSOR_MAX));

        // Grinder, no changes needed yet

        // Diamond, no changes needed yet

        CraftingComponent.PISTON.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.ELECTRIC_PISTON_UHV,
                10, GAMetaItems.ELECTRIC_PISTON_UEV,
                11, GAMetaItems.ELECTRIC_PISTON_UIV,
                12, GAMetaItems.ELECTRIC_PISTON_UMV,
                13, GAMetaItems.ELECTRIC_PISTON_UXV));
        CraftingComponent.PISTON.appendIngredients(ImmutableMap.of(14, GAMetaItems.ELECTRIC_PISTON_MAX));

        CraftingComponent.EMITTER.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.EMITTER_UHV,
                10, GAMetaItems.EMITTER_UEV,
                11, GAMetaItems.EMITTER_UIV,
                12, GAMetaItems.EMITTER_UMV,
                13, GAMetaItems.EMITTER_UXV));
        CraftingComponent.EMITTER.appendIngredients(ImmutableMap.of(14, GAMetaItems.EMITTER_MAX));

        CraftingComponent.CONVEYOR.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.CONVEYOR_MODULE_UHV,
                10, GAMetaItems.CONVEYOR_MODULE_UEV,
                11, GAMetaItems.CONVEYOR_MODULE_UIV,
                12, GAMetaItems.CONVEYOR_MODULE_UMV,
                13, GAMetaItems.CONVEYOR_MODULE_UXV));
        CraftingComponent.CONVEYOR.appendIngredients(ImmutableMap.of(14, GAMetaItems.CONVEYOR_MODULE_MAX));

        CraftingComponent.ROBOT_ARM.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.ROBOT_ARM_UHV,
                10, GAMetaItems.ROBOT_ARM_UEV,
                11, GAMetaItems.ROBOT_ARM_UIV,
                12, GAMetaItems.ROBOT_ARM_UMV,
                13, GAMetaItems.ROBOT_ARM_UXV));
        CraftingComponent.ROBOT_ARM.appendIngredients(ImmutableMap.of(14, GAMetaItems.ROBOT_ARM_MAX));

        CraftingComponent.COIL_HEATING.appendIngredients(ImmutableMap.of(GTValues.FALLBACK, new UnificationEntry(wireGtDouble, EnrichedNaquadahAlloy)));

        CraftingComponent.COIL_HEATING_DOUBLE.appendIngredients(ImmutableMap.of(GTValues.FALLBACK, new UnificationEntry(wireGtQuadruple, EnrichedNaquadahAlloy)));

        CraftingComponent.COIL_ELECTRIC.appendIngredients(ImmutableMap.of(
                1, new UnificationEntry(wireGtDouble, Copper),
                2, new UnificationEntry(wireGtQuadruple, Cupronickel),
                3, new UnificationEntry(wireGtQuadruple, Electrum),
                4, new UnificationEntry(wireGtQuadruple, AnnealedCopper),
                5, new UnificationEntry(wireGtQuadruple, Graphene)));
        CraftingComponent.COIL_ELECTRIC.appendIngredients(ImmutableMap.of(
                6, new UnificationEntry(wireGtQuadruple, NiobiumTitanium),
                7, new UnificationEntry(wireGtQuadruple, VanadiumGallium),
                8, new UnificationEntry(wireGtOctal, VanadiumGallium), // set this to override CEu's entry
                GTValues.FALLBACK, new UnificationEntry(wireGtOctal, VanadiumGallium)));

        // Stick Magnetic, no changes needed yet

        // Stick Distillation, no changes needed yet

        CraftingComponent.FIELD_GENERATOR.appendIngredients(ImmutableMap.of(
                9, GAMetaItems.FIELD_GENERATOR_UHV,
                10, GAMetaItems.FIELD_GENERATOR_UEV,
                11, GAMetaItems.FIELD_GENERATOR_UIV,
                12, GAMetaItems.FIELD_GENERATOR_UMV,
                13, GAMetaItems.FIELD_GENERATOR_UXV));
        CraftingComponent.FIELD_GENERATOR.appendIngredients(ImmutableMap.of(14, GAMetaItems.FIELD_GENERATOR_MAX));

        // Stick Electromagnetic, no changes needed yet

        CraftingComponent.STICK_RADIOACTIVE.appendIngredients(ImmutableMap.of(
                2, new UnificationEntry(stick, Uranium235),
                3, new UnificationEntry(stick, Plutonium241),
                4, new UnificationEntry(stick, Curium247.getMaterial()),
                5, new UnificationEntry(stick, Californium253.getMaterial()),
                6, new UnificationEntry(stick, Fermium259.getMaterial())));
        CraftingComponent.STICK_RADIOACTIVE.appendIngredients(ImmutableMap.of(
                7, new UnificationEntry(stick, Naquadria),
                GTValues.FALLBACK, new UnificationEntry(stick, Tritanium)));

        // TODO Pipe Reactor

        // TODO Hermetic Casings?
    }

    /* Not in GTCEu, maybe not needed?
    GEAR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(plate, Steel);
                case 2:
                    return new UnificationEntry(plate, Aluminium);
                case 3:
                    return new UnificationEntry(plate, StainlessSteel);
                case 4:
                    return new UnificationEntry(plate, Titanium);
                case 5:
                    return new UnificationEntry(plate, TungstenSteel);
                case 6:
                    return new UnificationEntry(plate, RhodiumPlatedPalladium);
                case 7:
                    return new UnificationEntry(plate, HSSS);
                case 8:
                    return new UnificationEntry(plate, Tritanium);
                case 9:
                    return new UnificationEntry(plate, Seaborgium);
                case 10:
                    return new UnificationEntry(plate, Bohrium);
                case 11:
                    return new UnificationEntry(plate, Quantum);
                default:
                    return new UnificationEntry(plate, Neutronium);
            }
        }
    },
    PLATE_DENSE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(plateDense, Steel);
                case 2:
                    return new UnificationEntry(plateDense, Aluminium);
                case 3:
                    return new UnificationEntry(plateDense, StainlessSteel);
                case 4:
                    return new UnificationEntry(plateDense, Titanium);
                case 5:
                    return new UnificationEntry(plateDense, TungstenSteel);
                case 6:
                    return new UnificationEntry(plateDense, RhodiumPlatedPalladium);
                case 7:
                    return new UnificationEntry(plateDense, HSSS);
                case 8:
                    return new UnificationEntry(plate, Tritanium);
                case 9:
                    return new UnificationEntry(plate, Seaborgium);
                case 10:
                    return new UnificationEntry(plate, Bohrium);
                case 11:
                    return new UnificationEntry(plate, Quantum);
                default:
                    return new UnificationEntry(plate, Neutronium);

            }
        }
    };

    CABLE_DOUBLE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(cableGtDouble, Lead);
                case 1:
                    return new UnificationEntry(cableGtDouble, Tin);
                case 2:
                    return new UnificationEntry(cableGtDouble, Copper);
                case 3:
                    return new UnificationEntry(cableGtDouble, Gold);
                case 4:
                    return new UnificationEntry(cableGtDouble, Aluminium);
                case 5:
                    return new UnificationEntry(cableGtDouble, Platinum);
                case 6:
                    return new UnificationEntry(cableGtDouble, NiobiumTitanium);
                case 7:
                    return new UnificationEntry(cableGtDouble, Naquadah);
                case 8:
                    return new UnificationEntry(cableGtDouble, NaquadahAlloy);
                case 9:
                    return new UnificationEntry(cableGtDouble, AbyssalAlloy);
                case 10:
                    return new UnificationEntry(cableGtDouble, TitanSteel);
                case 11:
                    return new UnificationEntry(cableGtDouble, BlackTitanium);
                case 12:
                case 13:
                    return new UnificationEntry(cableGtDouble, Neutronium);
                case 14:
                default:
                    return new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor);
            }
        }
    }
    CABLE_OCTAL {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(cableGtOctal, Lead);
                case 1:
                    return new UnificationEntry(cableGtOctal, Tin);
                case 2:
                    return new UnificationEntry(cableGtOctal, Copper);
                case 3:
                    return new UnificationEntry(cableGtOctal, Gold);
                case 4:
                    return new UnificationEntry(cableGtOctal, Aluminium);
                case 5:
                    return new UnificationEntry(cableGtOctal, Platinum);
                case 6:
                    return new UnificationEntry(cableGtOctal, NiobiumTitanium);
                case 7:
                    return new UnificationEntry(cableGtOctal, Naquadah);
                case 8:
                    return new UnificationEntry(cableGtOctal, NaquadahAlloy);
                case 9:
                    return new UnificationEntry(cableGtOctal, AbyssalAlloy);
                case 10:
                    return new UnificationEntry(cableGtOctal, TitanSteel);
                case 11:
                    return new UnificationEntry(cableGtOctal, BlackTitanium);
                case 12:
                case 13:
                    return new UnificationEntry(cableGtOctal, Neutronium);
                case 14:
                default:
                    return new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
    CABLE_HEX {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(cableGtHex, Lead);
                case 1:
                    return new UnificationEntry(cableGtHex, Tin);
                case 2:
                    return new UnificationEntry(cableGtHex, Copper);
                case 3:
                    return new UnificationEntry(cableGtHex, Gold);
                case 4:
                    return new UnificationEntry(cableGtHex, Aluminium);
                case 5:
                    return new UnificationEntry(cableGtHex, Platinum);
                case 6:
                    return new UnificationEntry(cableGtHex, NiobiumTitanium);
                case 7:
                    return new UnificationEntry(cableGtHex, Naquadah);
                case 8:
                    return new UnificationEntry(cableGtHex, NaquadahAlloy);
                case 9:
                    return new UnificationEntry(cableGtHex, AbyssalAlloy);
                case 10:
                    return new UnificationEntry(cableGtHex, TitanSteel);
                case 11:
                    return new UnificationEntry(cableGtHex, BlackTitanium);
                case 12:
                case 13:
                    return new UnificationEntry(cableGtHex, Neutronium);
                case 14:
                default:
                    return new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
    CABLE_SINGLE_WORSE {
        @Override
        public Object getIngredient(int tier) {
            return CABLE_SINGLE.getIngredient(tier == 0 ? tier : tier - 1);
        }
    },
    CABLE_DOUBLE_WORSE {
        @Override
        public Object getIngredient(int tier) {
            return CABLE_DOUBLE.getIngredient(tier == 0 ? tier : tier - 1);
        }
    },
    CABLE_QUAD_WORSE {
        @Override
        public Object getIngredient(int tier) {
            return CABLE_QUAD.getIngredient(tier == 0 ? tier : tier - 1);
        }
    },
    CABLE_OCTAL_WORSE {
        @Override
        public Object getIngredient(int tier) {
            return CABLE_OCTAL.getIngredient(tier == 0 ? tier : tier - 1);
        }
    },
    CABLE_HEX_WORSE {
        @Override
        public Object getIngredient(int tier) {
            return CABLE_HEX.getIngredient(tier == 0 ? tier : tier - 1);
        }
    },
    WORSE_HULL {
        @Override
        public Object getIngredient(int tier) {
            return tier == 0 ? MetaTileEntities.HULL[0] : HULL.getIngredient(tier - 1);
        }
    },
     */
}
