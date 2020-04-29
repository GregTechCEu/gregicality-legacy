package gregicadditions.recipes;

import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.items.OreDictNames;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.GTValues.W;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public enum GACraftingComponents {
    CIRCUIT {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Primitive);
                case 1:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Basic);
                case 2:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Good);
                case 3:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Advanced);
                case 4:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Extreme);
                case 5:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Elite);
                case 6:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Master);
                case 7:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Ultimate);
                case 8:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Superconductor);
                default:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Infinite);
            }
        }
    },
    BETTER_CIRCUIT {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier + 1) {
                case 0:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Primitive);
                case 1:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Basic);
                case 2:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Good);
                case 3:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Advanced);
                case 4:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Extreme);
                case 5:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Elite);
                case 6:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Master);
                case 7:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Ultimate);
                case 8:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Superconductor);
                default:
                    return OreDictUnifier.get(circuit, MarkerMaterials.Tier.Infinite);
            }
        }
    },
    CIRCUIT_PLATE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(plate, WroughtIron);
                case 1:
                    return OreDictUnifier.get(plate, Steel);
                case 2:
                    return OreDictUnifier.get(plate, Aluminium);
                case 3:
                    return OreDictUnifier.get(plate, StainlessSteel);
                case 4:
                    return OreDictUnifier.get(plate, Titanium);
                case 5:
                    return OreDictUnifier.get(plate, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(plateDense, HSSG);
                case 7:
                    return OreDictUnifier.get(plateDense, HSSE);
                default:
                    return OreDictUnifier.get(plateDense, Neutronium);

            }
        }
    },
    CIRCUIT_WIRE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(wireFine, Lead);
                case 1:
                    return OreDictUnifier.get(wireFine, Tin);
                case 2:
                    return OreDictUnifier.get(wireGtSingle, Copper);
                case 3:
                    return OreDictUnifier.get(wireGtSingle, Gold);
                case 4:
                    return OreDictUnifier.get(wireGtDouble, Aluminium);
                case 5:
                    return OreDictUnifier.get(cableGtDouble, Platinum);
                case 6:
                    return OreDictUnifier.get(cableGtDouble, NiobiumTitanium);
                case 7:
                    return OreDictUnifier.get(cableGtOctal, Naquadah);
                case 8:
                    return OreDictUnifier.get(cableGtOctal, NaquadahAlloy);
                default:
                    return OreDictUnifier.get(wireGtHex, MarkerMaterials.Tier.Superconductor);

            }
        }
    },
    PUMP {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ELECTRIC_PUMP_LV.getStackForm();
                case 2:
                    return MetaItems.ELECTRIC_PUMP_MV.getStackForm();
                case 3:
                    return MetaItems.ELECTRIC_PUMP_HV.getStackForm();
                case 4:
                    return MetaItems.ELECTRIC_PUMP_EV.getStackForm();
                case 5:
                    return MetaItems.ELECTRIC_PUMP_IV.getStackForm();
                case 6:
                    return MetaItems.ELECTRIC_PUMP_LUV.getStackForm();
                case 7:
                    return MetaItems.ELECTRIC_PUMP_ZPM.getStackForm();
                default:
                    return MetaItems.ELECTRIC_PUMP_UV.getStackForm();
            }
        }
    },
    CABLE_SINGLE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(cableGtSingle, Lead);
                case 1:
                    return OreDictUnifier.get(cableGtSingle, Tin);
                case 2:
                    return OreDictUnifier.get(cableGtSingle, Copper);
                case 3:
                    return OreDictUnifier.get(cableGtSingle, Gold);
                case 4:
                    return OreDictUnifier.get(cableGtSingle, Aluminium);
                case 5:
                    return OreDictUnifier.get(cableGtSingle, Platinum);
                case 6:
                    return OreDictUnifier.get(cableGtSingle, NiobiumTitanium);
                case 7:
                    return OreDictUnifier.get(cableGtSingle, Naquadah);
                case 8:
                    return OreDictUnifier.get(wireGtQuadruple, NaquadahAlloy);
                default:
                    return OreDictUnifier.get(wireGtSingle, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
    CABLE_DOUBLE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(cableGtDouble, Lead);
                case 1:
                    return OreDictUnifier.get(cableGtDouble, Tin);
                case 2:
                    return OreDictUnifier.get(cableGtDouble, Copper);
                case 3:
                    return OreDictUnifier.get(cableGtDouble, Gold);
                case 4:
                    return OreDictUnifier.get(cableGtDouble, Aluminium);
                case 5:
                    return OreDictUnifier.get(cableGtDouble, Platinum);
                case 6:
                    return OreDictUnifier.get(cableGtDouble, NiobiumTitanium);
                case 7:
                    return OreDictUnifier.get(cableGtDouble, Naquadah);
                case 8:
                    return OreDictUnifier.get(wireGtDouble, NaquadahAlloy);
                default:
                    return OreDictUnifier.get(wireGtDouble, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
    CABLE_QUAD {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(cableGtQuadruple, Lead);
                case 1:
                    return OreDictUnifier.get(cableGtQuadruple, Tin);
                case 2:
                    return OreDictUnifier.get(cableGtQuadruple, Copper);
                case 3:
                    return OreDictUnifier.get(cableGtQuadruple, Gold);
                case 4:
                    return OreDictUnifier.get(cableGtQuadruple, Aluminium);
                case 5:
                    return OreDictUnifier.get(cableGtQuadruple, Platinum);
                case 6:
                    return OreDictUnifier.get(cableGtQuadruple, NiobiumTitanium);
                case 7:
                    return OreDictUnifier.get(cableGtQuadruple, Naquadah);
                default:
                    return OreDictUnifier.get(cableGtQuadruple, NaquadahAlloy);
            }
        }
    },
    CABLE_OCTAL {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(cableGtOctal, Lead);
                case 1:
                    return OreDictUnifier.get(cableGtOctal, Tin);
                case 2:
                    return OreDictUnifier.get(cableGtOctal, Copper);
                case 3:
                    return OreDictUnifier.get(cableGtOctal, Gold);
                case 4:
                    return OreDictUnifier.get(cableGtOctal, Aluminium);
                case 5:
                    return OreDictUnifier.get(cableGtOctal, Platinum);
                case 6:
                    return OreDictUnifier.get(cableGtOctal, NiobiumTitanium);
                case 7:
                    return OreDictUnifier.get(cableGtOctal, Naquadah);
                default:
                    return OreDictUnifier.get(cableGtOctal, NaquadahAlloy);
            }
        }
    },

    CABLE_HEX {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(cableGtHex, Lead);
                case 1:
                    return OreDictUnifier.get(cableGtHex, Tin);
                case 2:
                    return OreDictUnifier.get(cableGtHex, Copper);
                case 3:
                    return OreDictUnifier.get(cableGtHex, Gold);
                case 4:
                    return OreDictUnifier.get(cableGtHex, Aluminium);
                case 5:
                    return OreDictUnifier.get(cableGtHex, Platinum);
                case 6:
                    return OreDictUnifier.get(cableGtHex, NiobiumTitanium);
                case 7:
                    return OreDictUnifier.get(cableGtHex, Naquadah);
                default:
                    return OreDictUnifier.get(cableGtHex, NaquadahAlloy);
            }
        }
    },
    WIRE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(wireGtSingle, Gold);
                case 2:
                    return OreDictUnifier.get(wireGtSingle, Silver);
                case 3:
                    return OreDictUnifier.get(wireGtSingle, Electrum);
                case 4:
                    return OreDictUnifier.get(wireGtSingle, Platinum);
                default:
                    return OreDictUnifier.get(wireGtSingle, Osmium);
            }
        }
    },

    HULL {
        @Override
        public ItemStack getIngredient(int tier) {
            return MetaTileEntities.HULL[tier].getStackForm();
        }
    },
    WORSE_HULL {
        @Override
        public ItemStack getIngredient(int tier) {
            return MetaTileEntities.HULL[tier - 1].getStackForm();
        }
    },
    PIPE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(pipeMedium, Bronze);
                case 2:
                    return OreDictUnifier.get(pipeMedium, Steel);
                case 3:
                    return OreDictUnifier.get(pipeMedium, StainlessSteel);
                case 4:
                    return OreDictUnifier.get(pipeMedium, Titanium);
                case 5:
                    return OreDictUnifier.get(pipeMedium, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(pipeMedium, Enderium);
                case 7:
                    return OreDictUnifier.get(pipeMedium, Naquadah);
                default:
                    return OreDictUnifier.get(pipeMedium, Neutronium);

            }
        }
    },
    GLASS {
        @Override
        public ItemStack getIngredient(int tier) {
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
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(plate, Steel);
                case 2:
                    return OreDictUnifier.get(plate, Aluminium);
                case 3:
                    return OreDictUnifier.get(plate, StainlessSteel);
                case 4:
                    return OreDictUnifier.get(plate, Titanium);
                case 5:
                    return OreDictUnifier.get(plate, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(plate, HSSG);
                case 7:
                    return OreDictUnifier.get(plate, HSSE);
                default:
                    return OreDictUnifier.get(plate, Neutronium);

            }
        }
    },
    MOTOR {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ELECTRIC_MOTOR_LV.getStackForm();
                case 2:
                    return MetaItems.ELECTRIC_MOTOR_MV.getStackForm();
                case 3:
                    return MetaItems.ELECTRIC_MOTOR_HV.getStackForm();
                case 4:
                    return MetaItems.ELECTRIC_MOTOR_EV.getStackForm();
                case 5:
                    return MetaItems.ELECTRIC_MOTOR_IV.getStackForm();
                case 6:
                    return MetaItems.ELECTRIC_MOTOR_LUV.getStackForm();
                case 7:
                    return MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm();
                default:
                    return MetaItems.ELECTRIC_MOTOR_UV.getStackForm();
            }
        }
    },
    ROTOR {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(rotor, Tin);
                case 2:
                    return OreDictUnifier.get(rotor, Bronze);
                case 3:
                    return OreDictUnifier.get(rotor, Steel);
                case 4:
                    return OreDictUnifier.get(rotor, StainlessSteel);
                case 5:
                    return OreDictUnifier.get(rotor, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(rotor, Chrome);
                case 7:
                    return OreDictUnifier.get(rotor, Iridium);
                default:
                    return OreDictUnifier.get(rotor, Osmium);
            }
        }
    },
    SENSOR {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.SENSOR_LV.getStackForm();
                case 2:
                    return MetaItems.SENSOR_MV.getStackForm();
                case 3:
                    return MetaItems.SENSOR_HV.getStackForm();
                case 4:
                    return MetaItems.SENSOR_EV.getStackForm();
                case 5:
                    return MetaItems.SENSOR_IV.getStackForm();
                case 6:
                    return MetaItems.SENSOR_LUV.getStackForm();
                case 7:
                    return MetaItems.SENSOR_ZPM.getStackForm();
                default:
                    return MetaItems.SENSOR_UV.getStackForm();
            }
        }
    },
    GRINDER {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                case 2:
                    return OreDictUnifier.get(gem, Diamond);
                default:
                    return OreDictUnifier.get(gem, Diamond);
            }
        }
    },
    DIAMOND {
        @Override
        public ItemStack getIngredient(int tier) {
            return OreDictUnifier.get(gem, Diamond);
        }
    },
    PISTON {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ELECTRIC_PISTON_LV.getStackForm();
                case 2:
                    return MetaItems.ELECTRIC_PISTON_MV.getStackForm();
                case 3:
                    return MetaItems.ELECTRIC_PISTON_HV.getStackForm();
                case 4:
                    return MetaItems.ELECTRIC_PISTON_EV.getStackForm();
                case 5:
                    return MetaItems.ELECTRIC_PISTON_IV.getStackForm();
                case 6:
                    return MetaItems.ELECTRIC_PISTON_LUV.getStackForm();
                case 7:
                    return MetaItems.ELECTRIC_PISTON_ZPM.getStackForm();
                default:
                    return MetaItems.ELECTRIC_PISTON_UV.getStackForm();
            }
        }
    },
    EMITTER {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.EMITTER_LV.getStackForm();
                case 2:
                    return MetaItems.EMITTER_MV.getStackForm();
                case 3:
                    return MetaItems.EMITTER_HV.getStackForm();
                case 4:
                    return MetaItems.EMITTER_EV.getStackForm();
                case 5:
                    return MetaItems.EMITTER_IV.getStackForm();
                case 6:
                    return MetaItems.EMITTER_LUV.getStackForm();
                case 7:
                    return MetaItems.EMITTER_ZPM.getStackForm();
                default:
                    return MetaItems.EMITTER_UV.getStackForm();
            }
        }
    },
    CONVEYOR {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.CONVEYOR_MODULE_LV.getStackForm();
                case 2:
                    return MetaItems.CONVEYOR_MODULE_MV.getStackForm();
                case 3:
                    return MetaItems.CONVEYOR_MODULE_HV.getStackForm();
                case 4:
                    return MetaItems.CONVEYOR_MODULE_EV.getStackForm();
                case 5:
                    return MetaItems.CONVEYOR_MODULE_IV.getStackForm();
                case 6:
                    return MetaItems.CONVEYOR_MODULE_LUV.getStackForm();
                case 7:
                    return MetaItems.CONVEYOR_MODULE_ZPM.getStackForm();
                default:
                    return MetaItems.CONVEYOR_MODULE_UV.getStackForm();
            }
        }
    },
    ROBOT_ARM {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.ROBOT_ARM_LV.getStackForm();
                case 2:
                    return MetaItems.ROBOT_ARM_MV.getStackForm();
                case 3:
                    return MetaItems.ROBOT_ARM_HV.getStackForm();
                case 4:
                    return MetaItems.ROBOT_ARM_EV.getStackForm();
                case 5:
                    return MetaItems.ROBOT_ARM_IV.getStackForm();
                case 6:
                    return MetaItems.ROBOT_ARM_LUV.getStackForm();
                case 7:
                    return MetaItems.ROBOT_ARM_ZPM.getStackForm();
                default:
                    return MetaItems.ROBOT_ARM_UV.getStackForm();
            }
        }
    },
    COIL_HEATING {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(wireGtDouble, Copper);
                case 2:
                    return OreDictUnifier.get(wireGtDouble, Cupronickel);
                case 3:
                    return OreDictUnifier.get(wireGtDouble, Kanthal);
                case 4:
                    return OreDictUnifier.get(wireGtDouble, Nichrome);
                case 5:
                    return OreDictUnifier.get(wireGtDouble, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(wireGtDouble, HSSG);
                case 7:
                    return OreDictUnifier.get(wireGtDouble, Naquadah);
                default:
                    return OreDictUnifier.get(wireGtDouble, NaquadahAlloy);

            }
        }
    },
    COIL_ELECTRIC {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                    return OreDictUnifier.get(wireGtSingle, Tin);
                case 1:
                    return OreDictUnifier.get(wireGtDouble, Tin);
                case 2:
                    return OreDictUnifier.get(wireGtDouble, Copper);
                case 3:
                    return OreDictUnifier.get(wireGtQuadruple, Copper);
                case 4:
                    return OreDictUnifier.get(wireGtOctal, AnnealedCopper);
                case 5:
                    return OreDictUnifier.get(wireGtOctal, AnnealedCopper);
                case 6:
                    return OreDictUnifier.get(wireGtQuadruple, YttriumBariumCuprate);
                case 7:
                    return OreDictUnifier.get(wireGtOctal, MarkerMaterials.Tier.Superconductor);
                default:
                    return OreDictUnifier.get(wireGtHex, MarkerMaterials.Tier.Superconductor);
            }
        }
    },
    STICK_MAGNETIC {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(stick, IronMagnetic);
                case 2:
                case 3:
                    return OreDictUnifier.get(stick, SteelMagnetic);
                case 4:
                case 5:
                    return OreDictUnifier.get(stick, NeodymiumMagnetic);
                case 6:
                case 7:
                    return OreDictUnifier.get(stickLong, NeodymiumMagnetic);
                default:
                    return OreDictUnifier.get(block, NeodymiumMagnetic);
            }
        }
    },
    STICK_DISTILLATION {
        @Override
        public ItemStack getIngredient(int tier) {
            return OreDictUnifier.get(stick, Blaze);
        }
    },
    FIELD_GENERATOR {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return MetaItems.FIELD_GENERATOR_LV.getStackForm();
                case 2:
                    return MetaItems.FIELD_GENERATOR_MV.getStackForm();
                case 3:
                    return MetaItems.FIELD_GENERATOR_HV.getStackForm();
                case 4:
                    return MetaItems.FIELD_GENERATOR_EV.getStackForm();
                case 5:
                    return MetaItems.FIELD_GENERATOR_IV.getStackForm();
                case 6:
                    return MetaItems.FIELD_GENERATOR_LUV.getStackForm();
                case 7:
                    return MetaItems.FIELD_GENERATOR_ZPM.getStackForm();
                default:
                    return MetaItems.FIELD_GENERATOR_UV.getStackForm();
            }
        }
    },
    COIL_HEATING_DOUBLE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(wireGtQuadruple, Copper);
                case 2:
                    return OreDictUnifier.get(wireGtQuadruple, Cupronickel);
                case 3:
                    return OreDictUnifier.get(wireGtQuadruple, Kanthal);
                case 4:
                    return OreDictUnifier.get(wireGtQuadruple, Nichrome);
                case 5:
                    return OreDictUnifier.get(wireGtQuadruple, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(wireGtQuadruple, HSSG);
                case 7:
                    return OreDictUnifier.get(wireGtQuadruple, Naquadah);
                default:
                    return OreDictUnifier.get(wireGtQuadruple, NaquadahAlloy);
            }
        }
    },
    STICK_ELECTROMAGNETIC {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(stick, Iron);
                case 2:
                case 3:
                    return OreDictUnifier.get(stick, Steel);
                case 4:
                    return OreDictUnifier.get(stick, Neodymium);
                default:
                    return OreDictUnifier.get(stick, VanadiumGallium);
            }
        }
    },
    STICK_RADIOACTIVE {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 3:
                    return OreDictUnifier.get(stick, Thorium);
                case 4:
                    return OreDictUnifier.get(stick, Uranium235);
                case 5:
                    return OreDictUnifier.get(stick, Plutonium241);
                case 6:
                    return OreDictUnifier.get(stick, NaquadahEnriched);
                case 7:
                    return OreDictUnifier.get(stick, Americium);
                default:
                    return OreDictUnifier.get(stick, Tritanium);
            }
        }
    },
    GEAR {
        @Override
        public ItemStack getIngredient(int tier) {
            switch (tier) {
                case 0:
                case 1:
                    return OreDictUnifier.get(plate, Steel);
                case 2:
                    return OreDictUnifier.get(plate, Aluminium);
                case 3:
                    return OreDictUnifier.get(plate, StainlessSteel);
                case 4:
                    return OreDictUnifier.get(plate, Titanium);
                case 5:
                    return OreDictUnifier.get(plate, TungstenSteel);
                case 6:
                    return OreDictUnifier.get(plate, HSSG);
                case 7:
                    return OreDictUnifier.get(plate, HSSE);
                default:
                    return OreDictUnifier.get(plate, Neutronium);
            }
        }
    },
    ;

    public abstract ItemStack getIngredient(int tier);
}