package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregtech.api.recipes.*;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Color;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class GAMachineRecipeRemoval {

    private static final MaterialStack[] solderingList = {new MaterialStack(Tin, 2L), new MaterialStack(SolderingAlloy, 1L), new MaterialStack(Lead, 4L)};

    public static void init() {
        for (Material m : Material.MATERIAL_REGISTRY) {

            //Remove Old Rotor Recipe
            if (!OreDictUnifier.get(rotor, m).isEmpty() && GAConfig.GT6.BendingRotors && GAConfig.GT6.BendingCylinders)
                removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, m, 4), OreDictUnifier.get(ring, m)}, new FluidStack[]{SolderingAlloy.getFluid(32)});

            //Remove Old Wrench Recipes
            if (m instanceof IngotMaterial && !m.hasFlag(DustMaterial.MatFlags.NO_SMASHING) && GAConfig.GT6.ExpensiveWrenches) {
                ModHandler.removeRecipeByName(new ResourceLocation(String.format("gregtech:wrench_%s", m.toString())));
            }

            //Remove GTCE's weird fine wire recipes
            if (!OreDictUnifier.get(wireFine, m).isEmpty() && !OreDictUnifier.get(stick, m).isEmpty() && GAConfig.GT5U.OldFineWireRecipes) {
                removeRecipesByInputs(RecipeMaps.WIREMILL_RECIPES, OreDictUnifier.get(stick, m));
            }

        }
        //Remove Old Bucket Recipe
        if (GAConfig.GT6.BendingCurvedPlates) {
            removeRecipesByInputs(RecipeMaps.BENDER_RECIPES, OreDictUnifier.get(plate, Iron, 12), IntCircuitIngredient.getIntegratedCircuit(1));
            removeRecipesByInputs(RecipeMaps.BENDER_RECIPES, OreDictUnifier.get(plate, WroughtIron, 12), IntCircuitIngredient.getIntegratedCircuit(1));
        }

        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, WroughtIron, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Steel, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Aluminium, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, StainlessSteel, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Titanium, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Tungsten, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, TungstenSteel, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Chrome, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Iridium, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Osmium, 8), IntCircuitIngredient.getIntegratedCircuit(8));


        //Fix Brick Exploit
//        removeRecipesByInputs(RecipeMaps.MACERATOR_RECIPES, new ItemStack(Items.BRICK));

        //Remove GTCE Circuit recipes
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Silicon, 2), OreDictUnifier.get(plate, Polytetrafluoroethylene));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Silicon), OreDictUnifier.get(plate, Plastic));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(foil, Platinum), OreDictUnifier.get(craftingLens, Color.Red));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(foil, Gold), OreDictUnifier.get(craftingLens, Color.Red));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(foil, Electrum), OreDictUnifier.get(craftingLens, Color.Red));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(foil, Copper), OreDictUnifier.get(craftingLens, Color.Red));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(foil, AnnealedCopper), OreDictUnifier.get(craftingLens, Color.Red));
        removeRecipesByInputs(RecipeMaps.FORMING_PRESS_RECIPES, OreDictUnifier.get(plate, Lapis), OreDictUnifier.get(dust, Glowstone));
        removeRecipesByInputs(RecipeMaps.FORMING_PRESS_RECIPES, OreDictUnifier.get(plate, Lazurite), OreDictUnifier.get(dust, Glowstone));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(plate, Lazurite, 15), OreDictUnifier.get(lens, Diamond));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(plate, Emerald), OreDictUnifier.get(craftingLens, Color.Lime));
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, OreDictUnifier.get(plate, Olivine), OreDictUnifier.get(craftingLens, Color.Lime));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm(8), OreDictUnifier.get(plate, Europium, 4));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8), OreDictUnifier.get(plate, Darmstadtium, 16));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Tantalum), OreDictUnifier.get(plate, Manganese)}, new FluidStack[]{Plastic.getFluid(144)});

        for (MaterialStack stack : solderingList) {
            IngotMaterial material = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;

            //ModHandler.removeRecipeByName(new ResourceLocation("gregtech:basic_circuit"));
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Plastic), OreDictUnifier.get(wireGtSingle, RedAlloy)}, new FluidStack[]{material.getFluid(18 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, RedAlloy, 2), OreDictUnifier.get(plate, Steel)}, new FluidStack[]{material.getFluid(18 * multiplier)});
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:primitive_circuit"));
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Plastic, 2), MetaItems.DATA_CONTROL_CIRCUIT_IV.getStackForm()}, new FluidStack[]{material.getFluid(72 * multiplier)});
        }


        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Materials.HypochlorousAcid.getFluid(1000), Materials.Ammonia.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Materials.Ammonia.getFluid(1000), Materials.Methanol.getFluid(2000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Materials.Ethylene.getFluid(1000), Materials.Benzene.getFluid(1000));
        //Circuit Rabbit Hole-Related Recipe Removal
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Silicon)}, new FluidStack[]{Epichlorhydrin.getFluid(144)});


        //remove easy rocket fuel
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, new ItemStack[]{IntCircuitIngredient.getIntegratedCircuit(1)}, new FluidStack[]{NitrogenDioxide.getFluid(1000), Hydrogen.getFluid(3000), Oxygen.getFluid(500), Water.getFluid(4000)});

        //Remove GT5 Ash Centrifuging
        removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, DarkAsh, 2));
        removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Ash));

        //Remove Alloy Smelter Rubber Recipe
        removeRecipesByInputs(RecipeMaps.ALLOY_SMELTER_RECIPES, OreDictUnifier.get(dust, RawRubber, 3), OreDictUnifier.get(dust, Sulfur));

        //Star Recipes
        removeRecipesByInputs(RecipeMaps.AUTOCLAVE_RECIPES, new ItemStack[]{new ItemStack(Items.NETHER_STAR)}, new FluidStack[]{Darmstadtium.getFluid(288)});
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(ingot, Plutonium, 6));

        //Remove Simple Copper Cable Recipes
        removeRecipesByInputs(RecipeMaps.ALLOY_SMELTER_RECIPES, OreDictUnifier.get(ingot, Rubber, 2), OreDictUnifier.get(wireGtSingle, Copper));

        //Remove Deprecated Recipes
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Naphtha.getFluid(288), Air.getFluid(2000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dustTiny, Titanium)}, new FluidStack[]{Naphtha.getFluid(1296), Oxygen.getFluid(16000)});
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Epichlorhydrin.getFluid(432), Naphtha.getFluid(3000), Fluorine.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Carbon)}, new FluidStack[]{LPG.getFluid(432), Chlorine.getFluid(1000)});

        //MAX Hull Recipes
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Darmstadtium, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), OreDictUnifier.get(wireGtSingle, MarkerMaterials.Tier.Superconductor, 2)}, new FluidStack[]{Polytetrafluoroethylene.getFluid(288)});

        //Electrolyzing Fixes
        removeRecipesByInputs(RecipeMaps.ELECTROLYZER_RECIPES, OreDictUnifier.get(dust, Sphalerite, 2));
        removeRecipesByInputs(RecipeMaps.ELECTROLYZER_RECIPES, OreDictUnifier.get(dust, Bentonite, 66));

        //Remove Silicon incompatability with Boule recipes
        removeRecipesByInputs(RecipeMaps.BLAST_RECIPES, OreDictUnifier.get(dust, Silicon));

        //Remove Default Recipes Without Circuits
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Oxygen.getFluid(500), Hydrogen.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Hydrogen.getFluid(2000), Oxygen.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Carbon)}, new FluidStack[]{Hydrogen.getFluid(4000)});
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Nitrogen.getFluid(1000), Oxygen.getFluid(2000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Oxygen.getFluid(2000), Nitrogen.getFluid(1000));

        //Remove Simple Cetane-Boosted Diesel Recipes
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Glyceryl.getFluid(250), LightFuel.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Glyceryl.getFluid(250), Fuel.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Fuel.getFluid(4000), Glyceryl.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, LightFuel.getFluid(4000), Glyceryl.getFluid(1000));


        //remove biomas distilery
        removeRecipesByInputs(RecipeMaps.DISTILLATION_RECIPES, Biomass.getFluid(1000));
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(0).getMatchingStacks(), new FluidStack[]{Biomass.getFluid(1000)});
        removeRecipesByInputs(RecipeMaps.DISTILLERY_RECIPES, new IntCircuitIngredient(1).getMatchingStacks(), new FluidStack[]{Biomass.getFluid(1000)});


        //Remove Cheap Diesel Recipe
        removeRecipesByInputs(RecipeMaps.MIXER_RECIPES, LightFuel.getFluid(5000), HeavyFuel.getFluid(1000));

        //Fix Seed Oil Recipe
        removeRecipesByInputs(RecipeMaps.FLUID_EXTRACTION_RECIPES, new ItemStack(Items.WHEAT_SEEDS));
        removeRecipesByInputs(RecipeMaps.FLUID_EXTRACTION_RECIPES, new ItemStack(Items.MELON_SEEDS));
        removeRecipesByInputs(RecipeMaps.FLUID_EXTRACTION_RECIPES, new ItemStack(Items.PUMPKIN_SEEDS));

        //Remove expensive Iridium recipe
        removeRecipesByInputs(RecipeMaps.FUSION_RECIPES, Lithium.getFluid(16), Tungsten.getFluid(16));

        //Remove Conflicting Redstone Plate Recipe
        removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES, OreDictUnifier.get(dust, Redstone));

        //Remove Incorrect Quartz Plate Recipes
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{Water.getFluid(73)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{Water.getFluid(73)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{DistilledWater.getFluid(55)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{DistilledWater.getFluid(55)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{new ItemStack(Blocks.QUARTZ_BLOCK)}, new FluidStack[]{Lubricant.getFluid(18)});
        removeRecipesByInputs(RecipeMaps.CUTTER_RECIPES, new ItemStack[]{OreDictUnifier.get(block, CertusQuartz)}, new FluidStack[]{Lubricant.getFluid(18)});

        //Remove GTCE Circuit recipes
        for (MaterialStack stack : solderingList) {
            IngotMaterial material = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;

            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PHENOLIC_BOARD.getStackForm(), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(), MetaItems.RESISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Copper)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PHENOLIC_BOARD.getStackForm(), MetaItems.INTEGRATED_LOGIC_CIRCUIT.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Copper)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(4), MetaItems.RESISTOR.getStackForm(4), MetaItems.CAPACITOR.getStackForm(4), MetaItems.TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(4), MetaItems.SMD_RESISTOR.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.SMD_TRANSISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(4), OreDictUnifier.get(wireFine, Copper, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PHENOLIC_BOARD.getStackForm(), MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), MetaItems.RESISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 8)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PHENOLIC_BOARD.getStackForm(), MetaItems.BASIC_ELECTRONIC_CIRCUIT_LV.getStackForm(3), MetaItems.SMD_RESISTOR.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 8)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.RESISTOR.getStackForm(2), MetaItems.CAPACITOR.getStackForm(2), MetaItems.TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, RedAlloy, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, RedAlloy, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, RedAlloy, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 12)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 12)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.EPOXY_BOARD.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_RESISTOR.getStackForm(2), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Electrum, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.EPOXY_BOARD.getStackForm(), MetaItems.SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, Electrum, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.EPOXY_BOARD.getStackForm(), MetaItems.NANO_PROCESSOR_HV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Electrum, 6)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.FIBER_BOARD.getStackForm(), MetaItems.QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, Platinum, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.FIBER_BOARD.getStackForm(), MetaItems.ADVANCED_SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, Platinum, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.FIBER_BOARD.getStackForm(), MetaItems.QUANTUM_PROCESSOR_EV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, Platinum, 6)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, NiobiumTitanium, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), MetaItems.CRYSTAL_SYSTEM_ON_CHIP.getStackForm(), OreDictUnifier.get(wireFine, NiobiumTitanium, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), MetaItems.CRYSTAL_PROCESSOR_IV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, NiobiumTitanium, 6)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.WETWARE_BOARD.getStackForm(), MetaItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.NANO_CENTRAL_PROCESSING_UNIT.getStackForm(), MetaItems.SMD_CAPACITOR.getStackForm(2), MetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 2)}, new FluidStack[]{material.getFluid(72 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.WETWARE_BOARD.getStackForm(), MetaItems.WETWARE_PROCESSOR_LUV.getStackForm(2), MetaItems.SMALL_COIL.getStackForm(4), MetaItems.SMD_CAPACITOR.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 6)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.WETWARE_BOARD.getStackForm(2), MetaItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(3), MetaItems.SMD_DIODE.getStackForm(4), MetaItems.NOR_MEMORY_CHIP.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, YttriumBariumCuprate, 6)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.ADVANCED_CIRCUIT_MV.getStackForm(), MetaItems.NAND_MEMORY_CHIP.getStackForm(32), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), OreDictUnifier.get(wireFine, RedAlloy, 8), OreDictUnifier.get(plate, Plastic, 4)}, new FluidStack[]{material.getFluid(144 * multiplier)});
            removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.EPOXY_BOARD.getStackForm(), MetaItems.NANO_PROCESSOR_HV.getStackForm(), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(4), MetaItems.RANDOM_ACCESS_MEMORY.getStackForm(32), MetaItems.NAND_MEMORY_CHIP.getStackForm(64), OreDictUnifier.get(wireFine, Platinum, 32)}, new FluidStack[]{material.getFluid(144 * multiplier)});
        }

        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), OreDictUnifier.get(circuit, MarkerMaterials.Tier.Good)}, new FluidStack[]{Polystyrene.getFluid(144)});
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{MetaItems.CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(), MetaItems.CARBON_FIBERS.getStackForm(16)}, new FluidStack[]{Glowstone.getFluid(576)});

        //Remove GTCE's Engraved Crystal Chip recipes
        removeRecipesByInputs(RecipeMaps.BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Emerald, 10), OreDictUnifier.get(gemExquisite, Emerald)}, new FluidStack[]{Helium.getFluid(5000)});
        removeRecipesByInputs(RecipeMaps.BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Olivine, 10), OreDictUnifier.get(gemExquisite, Olivine)}, new FluidStack[]{Helium.getFluid(5000)});
        removeRecipesByInputs(RecipeMaps.LASER_ENGRAVER_RECIPES, MetaItems.ENGRAVED_CRYSTAL_CHIP.getStackForm(), OreDictUnifier.get(craftingLens, MarkerMaterials.Color.Lime));
        //Remove Simple Superconductor recipe
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, YttriumBariumCuprate, 3), OreDictUnifier.get(plate, TungstenSteel, 3), MetaItems.ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, NiobiumTitanium, 3), OreDictUnifier.get(plate, TungstenSteel, 3), MetaItems.ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtSingle, VanadiumGallium, 3), OreDictUnifier.get(plate, TungstenSteel, 3), MetaItems.ELECTRIC_PUMP_LV.getStackForm()}, new FluidStack[]{Nitrogen.getFluid(2000)});

        //Remove Simple Coil Recipes
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Cupronickel, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Kanthal, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Nichrome, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, TungstenSteel, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, HSSG, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Naquadah, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, NaquadahAlloy, 8), IntCircuitIngredient.getIntegratedCircuit(8));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, MarkerMaterials.Tier.Superconductor, 8), IntCircuitIngredient.getIntegratedCircuit(8));

        //Remove Circuit Component Recipes
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Wood, 8), MetaItems.RUBBER_DROP.getStackForm()}, new FluidStack[]{Glue.getFluid(100)});
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireFine, AnnealedCopper, 4), OreDictUnifier.get(dustSmall, Gallium)}, new FluidStack[]{Plastic.getFluid(288)});
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireFine, Platinum, 4), OreDictUnifier.get(dustSmall, Gallium)}, new FluidStack[]{Plastic.getFluid(288)});
        removeRecipesByInputs(RecipeMaps.BLAST_RECIPES, OreDictUnifier.get(dust, Silicon, 32), OreDictUnifier.get(dustTiny, Gallium), IntCircuitIngredient.getIntegratedCircuit(1));
        removeRecipesByInputs(RecipeMaps.ASSEMBLER_RECIPES, OreDictUnifier.get(wireFine, Copper, 4), OreDictUnifier.get(dust, Coal));

        //Remove Nuclear processing
        removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Uranium));
        removeRecipesByInputs(RecipeMaps.CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Plutonium));

        //remove Brewing


        //remove old casing
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Invar, 6), OreDictUnifier.get(frameGt, Invar, 1));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Steel, 6), OreDictUnifier.get(frameGt, Steel, 1));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Aluminium, 6), OreDictUnifier.get(frameGt, Aluminium, 1));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, TungstenSteel, 6), OreDictUnifier.get(frameGt, TungstenSteel, 1));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, StainlessSteel, 6), OreDictUnifier.get(frameGt, StainlessSteel, 1));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Titanium, 6), OreDictUnifier.get(frameGt, Titanium, 1));

        //remove platinum and palladium
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(crushedPurified, Chalcopyrite)}, new FluidStack[]{NitricAcid.getFluid(1000)});
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(crushedPurified, Pentlandite)}, new FluidStack[]{NitricAcid.getFluid(1000)});
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, PlatinumGroupSludge));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, Endstone));

        //change rare earth
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, RareEarth));

        //remove old uranium recipe
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, Uraninite), OreDictUnifier.get(dust, Aluminium));
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, Uraninite), OreDictUnifier.get(dust, Magnesium));


    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, ItemStack... itemInputs) {
        List<ItemStack> inputs = new ArrayList<>();
        for (ItemStack s : itemInputs)
            inputs.add(s);
        map.removeRecipe(map.findRecipe(Long.MAX_VALUE, inputs, Collections.emptyList(), Integer.MAX_VALUE));
    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, FluidStack... fluidInputs) {
        List<FluidStack> inputs = new ArrayList<>();
        for (FluidStack s : fluidInputs)
            inputs.add(s);
        map.removeRecipe(map.findRecipe(Long.MAX_VALUE, Collections.emptyList(), inputs, Integer.MAX_VALUE));
    }

    public static <R extends RecipeBuilder<R>> void removeRecipesByInputs(RecipeMap<R> map, ItemStack[] itemInputs, FluidStack[] fluidInputs) {
        List<ItemStack> itemIn = new ArrayList<>();
        for (ItemStack s : itemInputs)
            itemIn.add(s);
        List<FluidStack> fluidIn = new ArrayList<>();
        for (FluidStack s : fluidInputs)
            fluidIn.add(s);
        map.removeRecipe(map.findRecipe(Long.MAX_VALUE, itemIn, fluidIn, Integer.MAX_VALUE));
    }

    public static <R extends RecipeBuilder<R>> void removeAllRecipes(RecipeMap<R> map) {

        List<Recipe> recipes = new ArrayList<>();
        recipes.addAll(map.getRecipeList());

        for (Recipe r : recipes)
            map.removeRecipe(r);
    }

}
