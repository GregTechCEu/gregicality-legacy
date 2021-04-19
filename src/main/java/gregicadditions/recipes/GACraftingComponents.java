package gregicadditions.recipes;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.items.OreDictNames;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.GTValues.W;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public enum GACraftingComponents {
    CIRCUIT {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Primitive);
                case 1:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Basic);
                case 2:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Good);
                case 3:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Advanced);
                case 4:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme);
                case 5:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Elite);
                case 6:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Master);
                case 7:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Ultimate);
                case 8:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Superconductor);
                case 9:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite);
                case 10:
                    return new UnificationEntry(circuit, UEV);
                case 11:
                    return new UnificationEntry(circuit, UIV);
                case 12:
                    return new UnificationEntry(circuit, UMV);
                case 13:
                    return new UnificationEntry(circuit, UXV);
                case 14:
                default:
                    return new UnificationEntry(circuit, MAX);
            }
        }
    },
    BETTER_CIRCUIT {
        @Override
        public Object getIngredient(int tier) {
            switch (tier + 1) {
                case 0:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Primitive);
                case 1:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Basic);
                case 2:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Good);
                case 3:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Advanced);
                case 4:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Extreme);
                case 5:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Elite);
                case 6:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Master);
                case 7:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Ultimate);
                case 8:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Superconductor);
                case 9:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite);
                case 10:
                    return new UnificationEntry(circuit, UEV);
                case 11:
                    return new UnificationEntry(circuit, UIV);
                case 12:
                    return new UnificationEntry(circuit, UMV);
                case 13:
                    return new UnificationEntry(circuit, UXV);
                case 14:
                default:
                    return new UnificationEntry(circuit, MAX);
            }
        }
    },
    PUMP {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ELECTRIC_PUMP_LV;
                case 2:
                    return MetaItems.ELECTRIC_PUMP_MV;
                case 3:
                    return MetaItems.ELECTRIC_PUMP_HV;
                case 4:
                    return MetaItems.ELECTRIC_PUMP_EV;
                case 5:
                    return MetaItems.ELECTRIC_PUMP_IV;
                case 6:
                    return MetaItems.ELECTRIC_PUMP_LUV;
                case 7:
                    return MetaItems.ELECTRIC_PUMP_ZPM;
                case 8:
                    return MetaItems.ELECTRIC_PUMP_UV;
                case 9:
                    return GAMetaItems.ELECTRIC_PUMP_UHV;
                case 10:
                    return GAMetaItems.ELECTRIC_PUMP_UEV;
                case 11:
                    return GAMetaItems.ELECTRIC_PUMP_UIV;
                case 12:
                    return GAMetaItems.ELECTRIC_PUMP_UMV;
                case 13:
                    return GAMetaItems.ELECTRIC_PUMP_UXV;
                case 14:
                default:
                    return GAMetaItems.ELECTRIC_PUMP_MAX;
            }
        }
    },
    CABLE_SINGLE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(cableGtSingle, Lead);
                case 1:
                    return new UnificationEntry(cableGtSingle, Tin);
                case 2:
                    return new UnificationEntry(cableGtSingle, Copper);
                case 3:
                    return new UnificationEntry(cableGtSingle, Gold);
                case 4:
                    return new UnificationEntry(cableGtSingle, Aluminium);
                case 5:
                    return new UnificationEntry(cableGtSingle, Platinum);
                case 6:
                    return new UnificationEntry(cableGtSingle, NiobiumTitanium);
                case 7:
                    return new UnificationEntry(cableGtSingle, Naquadah);
                case 8:
                    return new UnificationEntry(cableGtSingle, NaquadahAlloy);
                case 9:
                    return new UnificationEntry(cableGtSingle, AbyssalAlloy);
                case 10:
                    return new UnificationEntry(cableGtSingle, TitanSteel);
                case 11:
                    return new UnificationEntry(cableGtSingle, BlackTitanium);
                case 12:
                case 13:
                    return new UnificationEntry(cableGtSingle, Neutronium);
                case 14:
                default:
                    return new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
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
    },
    CABLE_QUAD {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(cableGtQuadruple, Lead);
                case 1:
                    return new UnificationEntry(cableGtQuadruple, Tin);
                case 2:
                    return new UnificationEntry(cableGtQuadruple, Copper);
                case 3:
                    return new UnificationEntry(cableGtQuadruple, Gold);
                case 4:
                    return new UnificationEntry(cableGtQuadruple, Aluminium);
                case 5:
                    return new UnificationEntry(cableGtQuadruple, Platinum);
                case 6:
                    return new UnificationEntry(cableGtQuadruple, NiobiumTitanium);
                case 7:
                    return new UnificationEntry(cableGtQuadruple, Naquadah);
                case 8:
                    return new UnificationEntry(cableGtQuadruple, NaquadahAlloy);
                case 9:
                    return new UnificationEntry(cableGtQuadruple, AbyssalAlloy);
                case 10:
                    return new UnificationEntry(cableGtQuadruple, TitanSteel);
                case 11:
                    return new UnificationEntry(cableGtQuadruple, BlackTitanium);
                case 12:
                case 13:
                    return new UnificationEntry(cableGtQuadruple, Neutronium);
                case 14:
                default:
                    return new UnificationEntry(wireGtQuadruple, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
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
    WIRE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(wireGtSingle, Gold);
                case 2:
                    return new UnificationEntry(wireGtSingle, Silver);
                case 3:
                    return new UnificationEntry(wireGtSingle, Electrum);
                case 4:
                    return new UnificationEntry(wireGtSingle, Platinum);
                default:
                    return new UnificationEntry(wireGtSingle, Osmium);
            }
        }
    },

    HULL {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 9:
                    return GATileEntities.GA_HULLS[0].getStackForm();
                case 10:
                    return GATileEntities.GA_HULLS[1].getStackForm();
                case 11:
                    return GATileEntities.GA_HULLS[2].getStackForm();
                case 12:
                    return GATileEntities.GA_HULLS[3].getStackForm();
                case 13:
                    return GATileEntities.GA_HULLS[4].getStackForm();
                case 14:
                    return MetaTileEntities.HULL[GTValues.MAX].getStackForm();
                default:
                    return MetaTileEntities.HULL[tier].getStackForm();
            }
        }
    },
    WORSE_HULL {
        @Override
        public Object getIngredient(int tier) {
            return tier == 0 ? MetaTileEntities.HULL[0] : HULL.getIngredient(tier - 1);
        }
    },
    PIPE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(pipeMedium, Bronze);
                case 2:
                    return new UnificationEntry(pipeMedium, Steel);
                case 3:
                    return new UnificationEntry(pipeMedium, StainlessSteel);
                case 4:
                    return new UnificationEntry(pipeMedium, Titanium);
                case 5:
                    return new UnificationEntry(pipeMedium, TungstenSteel);
                case 6:
                    return new UnificationEntry(pipeMedium, Enderium);
                case 7:
                    return new UnificationEntry(pipeMedium, Naquadah);
                case 8:
                    return new UnificationEntry(pipeMedium, Ultimet);
                case 9:
                    return new UnificationEntry(pipeMedium, Zeron100);
                case 10:
                    return new UnificationEntry(pipeMedium, Lafium);
                default:
                    return new UnificationEntry(pipeMedium, Neutronium);

            }
        }
    },
    GLASS {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new ItemStack(Blocks.GLASS, 1, W);
                case 2:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS);
                case 3:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.BOROSILICATE_GLASS);
                case 4:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.NICKEL_GLASS);
                case 5:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.CHROME_GLASS);
                case 6:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.TUNGSTEN_GLASS);
                case 7:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.IRIDIUM_GLASS);
                default:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.OSMIRIDIUM_GLASS);
            }
        }
    },
    PLATE {
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
    MOTOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ELECTRIC_MOTOR_LV;
                case 2:
                    return MetaItems.ELECTRIC_MOTOR_MV;
                case 3:
                    return MetaItems.ELECTRIC_MOTOR_HV;
                case 4:
                    return MetaItems.ELECTRIC_MOTOR_EV;
                case 5:
                    return MetaItems.ELECTRIC_MOTOR_IV;
                case 6:
                    return MetaItems.ELECTRIC_MOTOR_LUV;
                case 7:
                    return MetaItems.ELECTRIC_MOTOR_ZPM;
                case 8:
                    return MetaItems.ELECTRIC_MOTOR_UV;
                case 9:
                    return GAMetaItems.ELECTRIC_MOTOR_UHV;
                case 10:
                    return GAMetaItems.ELECTRIC_MOTOR_UEV;
                case 11:
                    return GAMetaItems.ELECTRIC_MOTOR_UIV;
                case 12:
                    return GAMetaItems.ELECTRIC_MOTOR_UMV;
                case 13:
                    return GAMetaItems.ELECTRIC_MOTOR_UXV;
                case 14:
                default:
                    return GAMetaItems.ELECTRIC_MOTOR_MAX;

            }
        }
    },
    ROTOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(rotor, Tin);
                case 2:
                    return new UnificationEntry(rotor, Bronze);
                case 3:
                    return new UnificationEntry(rotor, Steel);
                case 4:
                    return new UnificationEntry(rotor, StainlessSteel);
                case 5:
                    return new UnificationEntry(rotor, TungstenSteel);
                case 6:
                    return new UnificationEntry(rotor, Chrome);
                case 7:
                    return new UnificationEntry(rotor, Iridium);
                case 8:
                    return new UnificationEntry(rotor, Osmium);
                case 9:
                    return new UnificationEntry(rotor, Ruridit);
                default:
                    return new UnificationEntry(rotor, Neutronium);
            }
        }
    },
    SENSOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.SENSOR_LV;
                case 2:
                    return MetaItems.SENSOR_MV;
                case 3:
                    return MetaItems.SENSOR_HV;
                case 4:
                    return MetaItems.SENSOR_EV;
                case 5:
                    return MetaItems.SENSOR_IV;
                case 6:
                    return MetaItems.SENSOR_LUV;
                case 7:
                    return MetaItems.SENSOR_ZPM;
                case 8:
                    return MetaItems.SENSOR_UV;
                case 9:
                    return GAMetaItems.SENSOR_UHV;
                case 10:
                    return GAMetaItems.SENSOR_UEV;
                case 11:
                    return GAMetaItems.SENSOR_UIV;
                case 12:
                    return GAMetaItems.SENSOR_UMV;
                case 13:
                    return GAMetaItems.SENSOR_UXV;
                case 14:
                default:
                    return GAMetaItems.SENSOR_MAX;
            }
        }
    },
    GRINDER {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                case 2:
                    return new UnificationEntry(gem, Diamond);
                default:
                    return OreDictNames.craftingGrinder;
            }
        }
    },
    DIAMOND {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(gem, Diamond);
        }
    },
    PISTON {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ELECTRIC_PISTON_LV;
                case 2:
                    return MetaItems.ELECTRIC_PISTON_MV;
                case 3:
                    return MetaItems.ELECTRIC_PISTON_HV;
                case 4:
                    return MetaItems.ELECTRIC_PISTON_EV;
                case 5:
                    return MetaItems.ELECTRIC_PISTON_IV;
                case 6:
                    return MetaItems.ELECTRIC_PISTON_LUV;
                case 7:
                    return MetaItems.ELECTRIC_PISTON_ZPM;
                case 8:
                    return MetaItems.ELECTRIC_PISTON_UV;
                case 9:
                    return GAMetaItems.ELECTRIC_PISTON_UHV;
                case 10:
                    return GAMetaItems.ELECTRIC_PISTON_UEV;
                case 11:
                    return GAMetaItems.ELECTRIC_PISTON_UIV;
                case 12:
                    return GAMetaItems.ELECTRIC_PISTON_UMV;
                case 13:
                    return GAMetaItems.ELECTRIC_PISTON_UXV;
                case 14:
                default:
                    return GAMetaItems.ELECTRIC_PISTON_MAX;

            }
        }
    },
    EMITTER {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.EMITTER_LV;
                case 2:
                    return MetaItems.EMITTER_MV;
                case 3:
                    return MetaItems.EMITTER_HV;
                case 4:
                    return MetaItems.EMITTER_EV;
                case 5:
                    return MetaItems.EMITTER_IV;
                case 6:
                    return MetaItems.EMITTER_LUV;
                case 7:
                    return MetaItems.EMITTER_ZPM;
                case 8:
                    return MetaItems.EMITTER_UV;
                case 9:
                    return GAMetaItems.EMITTER_UHV;
                case 10:
                    return GAMetaItems.EMITTER_UEV;
                case 11:
                    return GAMetaItems.EMITTER_UIV;
                case 12:
                    return GAMetaItems.EMITTER_UMV;
                case 13:
                    return GAMetaItems.EMITTER_UXV;
                case 14:
                default:
                    return GAMetaItems.EMITTER_MAX;
            }
        }
    },
    CONVEYOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.CONVEYOR_MODULE_LV;
                case 2:
                    return MetaItems.CONVEYOR_MODULE_MV;
                case 3:
                    return MetaItems.CONVEYOR_MODULE_HV;
                case 4:
                    return MetaItems.CONVEYOR_MODULE_EV;
                case 5:
                    return MetaItems.CONVEYOR_MODULE_IV;
                case 6:
                    return MetaItems.CONVEYOR_MODULE_LUV;
                case 7:
                    return MetaItems.CONVEYOR_MODULE_ZPM;
                case 8:
                    return MetaItems.CONVEYOR_MODULE_UV;
                case 9:
                    return GAMetaItems.CONVEYOR_MODULE_UHV;
                case 10:
                    return GAMetaItems.CONVEYOR_MODULE_UEV;
                case 11:
                    return GAMetaItems.CONVEYOR_MODULE_UIV;
                case 12:
                    return GAMetaItems.CONVEYOR_MODULE_UMV;
                case 13:
                    return GAMetaItems.CONVEYOR_MODULE_UXV;
                case 14:
                default:
                    return GAMetaItems.CONVEYOR_MODULE_MAX;
            }
        }
    },
    ROBOT_ARM {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ROBOT_ARM_LV;
                case 2:
                    return MetaItems.ROBOT_ARM_MV;
                case 3:
                    return MetaItems.ROBOT_ARM_HV;
                case 4:
                    return MetaItems.ROBOT_ARM_EV;
                case 5:
                    return MetaItems.ROBOT_ARM_IV;
                case 6:
                    return MetaItems.ROBOT_ARM_LUV;
                case 7:
                    return MetaItems.ROBOT_ARM_ZPM;
                case 8:
                    return MetaItems.ROBOT_ARM_UV;
                case 9:
                    return GAMetaItems.ROBOT_ARM_UHV;
                case 10:
                    return GAMetaItems.ROBOT_ARM_UEV;
                case 11:
                    return GAMetaItems.ROBOT_ARM_UIV;
                case 12:
                    return GAMetaItems.ROBOT_ARM_UMV;
                case 13:
                    return GAMetaItems.ROBOT_ARM_UXV;
                case 14:
                default:
                    return GAMetaItems.ROBOT_ARM_MAX;
            }
        }
    },
    COIL_HEATING {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(wireGtDouble, Copper);
                case 2:
                    return new UnificationEntry(wireGtDouble, Cupronickel);
                case 3:
                    return new UnificationEntry(wireGtDouble, Kanthal);
                case 4:
                    return new UnificationEntry(wireGtDouble, Nichrome);
                case 5:
                    return new UnificationEntry(wireGtDouble, TungstenSteel);
                case 6:
                    return new UnificationEntry(wireGtDouble, HSSG);
                case 7:
                    return new UnificationEntry(wireGtDouble, Naquadah);
                case 8:
                    return new UnificationEntry(wireGtDouble, NaquadahAlloy);
                default:
                    return new UnificationEntry(wireGtDouble, EnrichedNaquadahAlloy);

            }
        }
    },
    COIL_ELECTRIC {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(wireGtSingle, Tin);
                case 1:
                    return new UnificationEntry(wireGtDouble, Tin);
                case 2:
                    return new UnificationEntry(wireGtDouble, Copper);
                case 3:
                    return new UnificationEntry(wireGtQuadruple, Copper);
                case 4:
                case 5:
                    return new UnificationEntry(wireGtOctal, AnnealedCopper);
                case 6:
                    return new UnificationEntry(wireGtQuadruple, YttriumBariumCuprate);
                case 7:
                    return new UnificationEntry(wireGtOctal, MarkerMaterials.Tier.Superconductor);
                default:
                    return new UnificationEntry(wireGtHex, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
    STICK_MAGNETIC {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(stick, IronMagnetic);
                case 2:
                case 3:
                    return new UnificationEntry(stick, SteelMagnetic);
                case 4:
                case 5:
                    return new UnificationEntry(stick, NeodymiumMagnetic);
                case 6:
                case 7:
                    return new UnificationEntry(stickLong, NeodymiumMagnetic);
                default:
                    return new UnificationEntry(block, NeodymiumMagnetic);
            }
        }
    },
    STICK_DISTILLATION {
        @Override
        public Object getIngredient(int tier) {
            return new UnificationEntry(stick, Blaze);
        }
    },
    FIELD_GENERATOR {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.FIELD_GENERATOR_LV;
                case 2:
                    return MetaItems.FIELD_GENERATOR_MV;
                case 3:
                    return MetaItems.FIELD_GENERATOR_HV;
                case 4:
                    return MetaItems.FIELD_GENERATOR_EV;
                case 5:
                    return MetaItems.FIELD_GENERATOR_IV;
                case 6:
                    return MetaItems.FIELD_GENERATOR_LUV;
                case 7:
                    return MetaItems.FIELD_GENERATOR_ZPM;
                case 8:
                    return MetaItems.FIELD_GENERATOR_UV;
                case 9:
                    return GAMetaItems.FIELD_GENERATOR_UHV;
                case 10:
                    return GAMetaItems.FIELD_GENERATOR_UEV;
                case 11:
                    return GAMetaItems.FIELD_GENERATOR_UIV;
                case 12:
                    return GAMetaItems.FIELD_GENERATOR_UMV;
                case 13:
                    return GAMetaItems.FIELD_GENERATOR_UXV;
                case 14:
                default:
                    return GAMetaItems.FIELD_GENERATOR_MAX;
            }
        }
    },
    COIL_HEATING_DOUBLE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(wireGtQuadruple, Copper);
                case 2:
                    return new UnificationEntry(wireGtQuadruple, Cupronickel);
                case 3:
                    return new UnificationEntry(wireGtQuadruple, Kanthal);
                case 4:
                    return new UnificationEntry(wireGtQuadruple, Nichrome);
                case 5:
                    return new UnificationEntry(wireGtQuadruple, TungstenSteel);
                case 6:
                    return new UnificationEntry(wireGtQuadruple, HSSG);
                case 7:
                    return new UnificationEntry(wireGtQuadruple, Naquadah);
                case 8:
                    return new UnificationEntry(wireGtQuadruple, NaquadahAlloy);
                default:
                    return new UnificationEntry(wireGtQuadruple, EnrichedNaquadahAlloy);
            }
        }
    },
    STICK_ELECTROMAGNETIC {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return new UnificationEntry(stick, Iron);
                case 2:
                case 3:
                    return new UnificationEntry(stick, Steel);
                case 4:
                    return new UnificationEntry(stick, Neodymium);
                default:
                    return new UnificationEntry(stick, VanadiumGallium);
            }
        }
    },
    STICK_RADIOACTIVE {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 2:
                    return new UnificationEntry(stick, Uranium235);
                case 3:
                    return new UnificationEntry(stick, Plutonium241);
                case 4:
                    return new UnificationEntry(stick, Curium247.getMaterial());
                case 5:
                    return new UnificationEntry(stick, Californium253.getMaterial());
                case 6:
                    return new UnificationEntry(stick, Fermium259.getMaterial());
                case 7:
                    return new UnificationEntry(stick, Naquadria);
                default:
                    return new UnificationEntry(stick, Tritanium);
            }
        }
    },
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
    },
    ;

    public abstract Object getIngredient(int tier);
}