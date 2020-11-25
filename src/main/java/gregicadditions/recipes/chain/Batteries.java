package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class Batteries {
    public static void init() {
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Titanium, 4)
                .input(cableGtSingle, Aluminium, 8)
                .EUt(1920)
                .duration(150)
                .outputs(BATTERY_SMALL_VANADIUM_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, TungstenSteel, 4)
                .input(cableGtSingle, Platinum, 8)
                .EUt(1920 * 4)
                .duration(150)
                .outputs(BATTERY_MEDIUM_VANADIUM_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, NiobiumTitanium, 4)
                .input(cableGtSingle, RhodiumPlatedPalladium, 8)
                .EUt(1920 * 16)
                .duration(150)
                .outputs(BATTERY_LARGE_VANADIUM_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, HSSS, 4)
                .input(cableGtSingle, Naquadah, 8)
                .EUt(1920 * 64)
                .duration(150)
                .outputs(BATTERY_MEDIUM_NAQUADRIA_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Tritanium, 4)
                .input(cableGtSingle, NaquadahAlloy, 8)
                .EUt(1920 * 64 * 4)
                .duration(150)
                .outputs(BATTERY_LARGE_NAQUADRIA_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Seaborgium, 4)
                .input(cableGtSingle, Seaborgium, 8)
                .EUt(1920 * 64 * 16)
                .duration(150)
                .outputs(BATTERY_SMALL_NEUTRONIUM_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Bohrium, 4)
                .input(cableGtSingle, Bohrium, 8)
                .EUt(1920 * 64 * 64)
                .duration(150)
                .outputs(BATTERY_MEDIUM_NEUTRONIUM_EMPTY.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Neutronium, 4)
                .input(cableGtSingle, Neutronium, 8)
                .EUt(1920 * 64 * 64)
                .duration(150)
                .outputs(BATTERY_LARGE_NEUTRONIUM_EMPTY.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Vanadium, 16)
                .inputs(BATTERY_SMALL_VANADIUM_EMPTY.getStackForm())
                .EUt(480)
                .duration(60)
                .outputs(BATTERY_SMALL_VANADIUM.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Vanadium, 32)
                .inputs(BATTERY_MEDIUM_VANADIUM_EMPTY.getStackForm())
                .EUt(480 * 4)
                .duration(60)
                .outputs(BATTERY_MEDIUM_VANADIUM.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Vanadium, 64)
                .inputs(BATTERY_LARGE_VANADIUM_EMPTY.getStackForm())
                .EUt(480 * 16)
                .duration(60)
                .outputs(BATTERY_LARGE_VANADIUM.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Naquadria, 32)
                .inputs(BATTERY_MEDIUM_NAQUADRIA_EMPTY.getStackForm())
                .EUt(480 * 64)
                .duration(60)
                .outputs(BATTERY_MEDIUM_NAQUADRIA.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Naquadria, 64)
                .inputs(BATTERY_LARGE_NAQUADRIA_EMPTY.getStackForm())
                .EUt(480 * 64 * 4)
                .duration(60)
                .outputs(BATTERY_LARGE_NAQUADRIA.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Neutronium, 16)
                .inputs(BATTERY_SMALL_NEUTRONIUM_EMPTY.getStackForm())
                .EUt(480 * 64 * 16)
                .duration(60)
                .outputs(BATTERY_SMALL_NEUTRONIUM.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Neutronium, 32)
                .inputs(BATTERY_MEDIUM_NEUTRONIUM_EMPTY.getStackForm())
                .EUt(480 * 64 * 64)
                .duration(60)
                .outputs(BATTERY_MEDIUM_NEUTRONIUM.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .input(dust, Neutronium, 64)
                .inputs(BATTERY_LARGE_NEUTRONIUM_EMPTY.getStackForm())
                .EUt(480 * 64 * 64 * 4)
                .duration(60)
                .outputs(BATTERY_LARGE_NEUTRONIUM.getStackForm())
                .buildAndRegister();
        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Iron)
                .outputs(OreDictUnifier.get(dust, Iron, 128))
                .EUt(1)
                .duration(1)
                .buildAndRegister();


        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? MAX_BATTERY : ZPM2).getStackForm();

        if (GAConfig.GT5U.enableZPMandUVBats) {
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Rutherfordium, 16)).input(circuit, MarkerMaterials.Tier.Ultimate).input(circuit, MarkerMaterials.Tier.Ultimate).input(circuit, MarkerMaterials.Tier.Ultimate).input(circuit, MarkerMaterials.Tier.Ultimate).inputs(ENERGY_LAPOTRONIC_ORB2.getStackForm(8), FIELD_GENERATOR_LUV.getStackForm(2), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64), NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64), SMD_DIODE.getStackForm(8), OreDictUnifier.get(cableGtSingle, Naquadah, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(8000)).outputs(ENERGY_MODULE.getStackForm()).duration(2000).EUt(100000).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Dubnium, 16)).input(circuit, MarkerMaterials.Tier.Superconductor).input(circuit, MarkerMaterials.Tier.Superconductor).input(circuit, MarkerMaterials.Tier.Superconductor).input(circuit, MarkerMaterials.Tier.Superconductor).inputs(ENERGY_MODULE.getStackForm(8), FIELD_GENERATOR_ZPM.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), SMD_DIODE.getStackForm(16), OreDictUnifier.get(cableGtSingle, NaquadahAlloy, 32)).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000)).outputs(ENERGY_CLUSTER.getStackForm()).duration(2000).EUt(200000).buildAndRegister();
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Neutronium, 16), ENERGY_CLUSTER.getStackForm(8), FIELD_GENERATOR_UV.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), SMD_DIODE.getStackForm(16), OreDictUnifier.get(wireGtSingle, MarkerMaterials.Tier.Superconductor, 32)).input(circuit, MarkerMaterials.Tier.Infinite).input(circuit, MarkerMaterials.Tier.Infinite).input(circuit, MarkerMaterials.Tier.Infinite).input(circuit, MarkerMaterials.Tier.Infinite).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000), Naquadria.getFluid(1152)).outputs(last_bat).duration(2000).EUt(300000).buildAndRegister();
        } else
            ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(OreDictUnifier.get(plate, Neutronium, 16), ENERGY_LAPOTRONIC_ORB2.getStackForm(8), FIELD_GENERATOR_UV.getStackForm(2), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), SMD_DIODE.getStackForm(16), OreDictUnifier.get(wireGtSingle, MarkerMaterials.Tier.Superconductor, 32)).input(circuit, MarkerMaterials.Tier.Infinite).input(circuit, MarkerMaterials.Tier.Infinite).input(circuit, MarkerMaterials.Tier.Infinite).input(circuit, MarkerMaterials.Tier.Infinite).fluidInputs(SolderingAlloy.getFluid(2880), Water.getFluid(16000)).outputs(last_bat).duration(2000).EUt(300000).buildAndRegister();


    }
}
