package gregicadditions.recipes;

import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.items.OreDictNames;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.GTValues.W;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public enum GACraftingComponents     {
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
                default:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite);
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
                default:
                    return new UnificationEntry(circuit, MarkerMaterials.Tier.Infinite);
            }
        }
    },
    CIRCUIT_PLATE{
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(plate, WroughtIron);
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
                    return new UnificationEntry(plateDense, HSSG);
                case 7:
                    return new UnificationEntry(plateDense, HSSE);
                default:
                    return new UnificationEntry(plateDense, Neutronium);

            }
        }
    },
    CIRCUIT_WIRE{
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return new UnificationEntry(wireFine, Lead);
                case 1:
                    return new UnificationEntry(wireFine, Tin);
                case 2:
                    return new UnificationEntry(wireGtSingle, Copper);
                case 3:
                    return new UnificationEntry(wireGtSingle, Gold);
                case 4:
                    return new UnificationEntry(wireGtDouble, Aluminium);
                case 5:
                    return new UnificationEntry(cableGtDouble, Platinum);
                case 6:
                    return new UnificationEntry(cableGtDouble, NiobiumTitanium);
                case 7:
                    return new UnificationEntry(cableGtOctal, Naquadah);
                case 8:
                    return new UnificationEntry(cableGtOctal, NaquadahAlloy);
                default:
                    return new UnificationEntry(wireGtHex, MarkerMaterials.Tier.Superconductor);

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
                default:
                    return MetaItems.ELECTRIC_PUMP_UV;
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
                    return new UnificationEntry(wireGtQuadruple, NaquadahAlloy);
                default:
                    return new UnificationEntry(wireGtSingle, MarkerMaterials.Tier.Superconductor);
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
                    return new UnificationEntry(wireGtDouble, NaquadahAlloy);
                default:
                    return new UnificationEntry(wireGtDouble, MarkerMaterials.Tier.Superconductor);
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
                default:
                    return new UnificationEntry(cableGtQuadruple, NaquadahAlloy);
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
                default:
                    return new UnificationEntry(cableGtOctal, NaquadahAlloy);
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
                default:
                    return new UnificationEntry(cableGtHex, NaquadahAlloy);
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
            return MetaTileEntities.HULL[tier].getStackForm();
        }
    },
    WORSE_HULL {
        @Override
        public Object getIngredient(int tier) {
            return MetaTileEntities.HULL[tier - 1].getStackForm();
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
                default:
                    return new UnificationEntry(pipeMedium, Neutronium);

            }
        }
    },
    GLASS {
        @Override
        public Object getIngredient(int tier) {
            switch (tier) {
                case 6:
                case 7:
                case 8:
                    return GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS);
                default:
                    return new ItemStack(Blocks.GLASS, 1, W);
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
                    return new UnificationEntry(plate, HSSG);
                case 7:
                    return new UnificationEntry(plate, HSSE);
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
                default:
                    return MetaItems.ELECTRIC_MOTOR_UV;
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
                default:
                    return new UnificationEntry(rotor, Osmium);
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
                default:
                    return MetaItems.SENSOR_UV;
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
                default:
                    return MetaItems.ELECTRIC_PISTON_UV;
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
                default:
                    return MetaItems.EMITTER_UV;
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
                default:
                    return MetaItems.CONVEYOR_MODULE_UV;
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
                default:
                    return MetaItems.ROBOT_ARM_UV;
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
                default:
                    return new UnificationEntry(wireGtDouble, NaquadahAlloy);

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
                    return new UnificationEntry(wireGtOctal, AnnealedCopper);
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
                default:
                    return MetaItems.FIELD_GENERATOR_UV;
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
                default:
                    return new UnificationEntry(wireGtQuadruple, NaquadahAlloy);
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
                case 4:
                    return new UnificationEntry(stick, Uranium235);
                case 5:
                    return new UnificationEntry(stick, Plutonium241);
                case 6:
                    return new UnificationEntry(stick, NaquadahEnriched);
                case 7:
                    return new UnificationEntry(stick, Americium);
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
                    return new UnificationEntry(plate, HSSG);
                case 7:
                    return new UnificationEntry(plate, HSSE);
                default:
                    return new UnificationEntry(plate, Neutronium);
            }
        }
    },;

    public abstract Object getIngredient(int tier);
}