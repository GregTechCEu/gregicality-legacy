package gregicadditions.recipes.chain;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class NuclearChain {

    public static void init() {
        //NUCLEAR PROCESSING
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(30)
                .inputs(THORIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 560, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Protactinium233.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Uranium, 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(60)
                .inputs(URANIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 760, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Uranium, 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Neptunium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(120)
                .inputs(NEPTUNIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 1000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Neptunium.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, PlutoniumRadioactive.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(240)
                .inputs(PLUTONIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 1330, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, PlutoniumRadioactive.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, AmericiumRadioactive.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(480)
                .inputs(AMERICIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 1780, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, AmericiumRadioactive.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Curium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(960)
                .inputs(CURIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 2370, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Curium.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Berkelium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(1920)
                .inputs(BERKELIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 3160, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Berkelium.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Californium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(3840)
                .inputs(CALIFORNIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 4220, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Californium.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Einsteinium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(7680)
                .inputs(EINSTEINIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 5630, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Einsteinium.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Fermium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(15360)
                .inputs(FERMIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 7500, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Fermium.getMaterial(), 1), 8000, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Mendelevium.getMaterial(), 3), 8000, 200)
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(3000).EUt(30720)
                .inputs(MENDELEVIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 10000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Mendelevium.getMaterial(), 1), 8000, 200)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(dust, Potassium, 3)
                .input(dust, Sodium, 7)
                .outputs(OreDictUnifier.get(dust, SodiumPotassiumAlloy, 10))
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .fluidInputs(LithiumHydroxideSolution.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, LithiumFluoride))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Sodium)
                .fluidInputs(Fluorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumFluoride))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Potassium)
                .fluidInputs(Fluorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, PotassiumFluoride))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(64).duration(600)
                .input(dust, LithiumFluoride)
                .input(dust, SodiumFluoride)
                .input(dust, PotassiumFluoride)
                .outputs(OreDictUnifier.get(dust, FLiNaK, 3))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(300)
                .input(dust, Beryllium)
                .fluidInputs(Fluorine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, BerylliumFluoride))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().duration(600).EUt(64)
                .input(dust, LithiumFluoride)
                .input(dust, BerylliumFluoride)
                .outputs(OreDictUnifier.get(dust, FLiBe, 2))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1000).EUt(16)
                .input(dust, Lead, 3)
                .input(dust, Bismuth, 7)
                .outputs(OreDictUnifier.get(dust, LeadBismuthEutectic, 10))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(560).duration(2000).input(dust, LeadBismuthEutectic).fluidOutputs(LeadBismuthEutectic.getFluid(GTValues.L)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(480).duration(2000).input(dust, FLiBe).fluidOutputs(FLiBe.getFluid(GTValues.L)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(480).duration(1000).input(dust, FLiNaK).fluidOutputs(FLiNaK.getFluid(GTValues.L)).buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(250).duration(60).input(dust, SodiumPotassiumAlloy).fluidOutputs(SodiumPotassiumAlloy.getFluid(GTValues.L)).buildAndRegister();
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Protactinium233.getMaterial()), OreDictUnifier.get(ingot, Protactinium.getMaterial()));
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Protactinium.getMaterial()), OreDictUnifier.get(ingot, Protactinium233.getMaterial()));


        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE_LANTHANIDE_A.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_LANTHANIDE_B.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_ALKALINE.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_HEAVY_METAL.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_A.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_B.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_C.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_REACTIVE_NONMETAL.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METALOID.getStackForm(), 1111, 111).buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_HEAVY_METAL.getStackForm())
                .fluidOutputs(Mercury.getFluid(250))
                .chancedOutput(OreDictUnifier.get(dustTiny, Zinc, 2), 5555, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Gallium, 2), 5555, 300)
                .chancedOutput(OreDictUnifier.get(dustTiny, Cadmium, 2), 5555, 400)
                .chancedOutput(OreDictUnifier.get(dustTiny, Indium, 2), 5555, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Tin, 2), 5555, 600)
                .chancedOutput(OreDictUnifier.get(dustTiny, Thallium, 2), 5555, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Lead, 2), 5555, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Bismuth, 2), 5555, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Polonium, 2), 5555, 1000)
                .buildAndRegister();


        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_LANTHANIDE_A.getStackForm())
                .chancedOutput(OreDictUnifier.get(dustTiny, Dysprosium, 2), 8333, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Holmium, 2), 8333, 600)
                .chancedOutput(OreDictUnifier.get(dustTiny, Erbium, 2), 8333, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Thulium, 2), 8333, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Ytterbium, 2), 8333, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Lutetium, 2), 8333, 1000)
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_LANTHANIDE_B.getStackForm())
                .chancedOutput(OreDictUnifier.get(dustTiny, Lanthanum, 2), 5555, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Cerium, 2), 5555, 300)
                .chancedOutput(OreDictUnifier.get(dustTiny, Praseodymium, 2), 5555, 400)
                .chancedOutput(OreDictUnifier.get(dustTiny, Neodymium, 2), 5555, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Promethium, 2), 5555, 600)
                .chancedOutput(OreDictUnifier.get(dustTiny, Samarium, 2), 5555, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Europium, 2), 5555, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Gadolinium, 2), 5555, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Terbium, 2), 5555, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_METAL_A.getStackForm())
                .chancedOutput(OreDictUnifier.get(dustTiny, Hafnium, 2), 6250, 300)
                .chancedOutput(OreDictUnifier.get(dustTiny, Tantalum, 2), 6250, 400)
                .chancedOutput(OreDictUnifier.get(dustTiny, Tungsten, 2), 6250, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Rhenium, 2), 6250, 600)// can stay here, the change to get infinite rhenium is really small
                .chancedOutput(OreDictUnifier.get(dustTiny, Osmium, 2), 6250, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iridium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Platinum, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Gold, 2), 6250, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_METAL_B.getStackForm())
                .chancedOutput(OreDictUnifier.get(dustTiny, Yttrium, 2), 5555, 200)
                .chancedOutput(OreDictUnifier.get(dustTiny, Zirconium, 2), 5555, 300)
                .chancedOutput(OreDictUnifier.get(dustTiny, Niobium, 2), 5555, 400)
                .chancedOutput(OreDictUnifier.get(dustTiny, Molybdenum, 2), 5555, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Technetium, 2), 5555, 600)
                .chancedOutput(OreDictUnifier.get(dustTiny, Ruthenium, 2), 5555, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Rhodium, 2), 5555, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Palladium, 2), 5555, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Silver, 2), 5555, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_METAL_C.getStackForm())
                .outputs(OreDictUnifier.get(dustTiny, Iron, 2))
                .outputs(OreDictUnifier.get(dustTiny, Cobalt, 2))
                .outputs(OreDictUnifier.get(dustTiny, Nickel, 2))
                .outputs(OreDictUnifier.get(dustTiny, Copper, 2))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_METALOID.getStackForm())
                .chancedOutput(OreDictUnifier.get(dustTiny, Germanium, 2), 6250, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Arsenic, 2), 6250, 600)
                .chancedOutput(OreDictUnifier.get(dustTiny, Antimony, 2), 6250, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Tellurium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Astatine, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Actinium, 2), 6250, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_ALKALINE.getStackForm())
                .chancedOutput(OreDictUnifier.get(dustTiny, Rubidium, 2), 6250, 500)
                .chancedOutput(OreDictUnifier.get(dustTiny, Strontium, 2), 6250, 600)
                .chancedOutput(OreDictUnifier.get(dustTiny, Caesium, 2), 6250, 700)
                .chancedOutput(OreDictUnifier.get(dustTiny, Barium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Francium, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Radium, 2), 6250, 1000)
                .buildAndRegister();


        CENTRIFUGE_RECIPES.recipeBuilder().EUt(32).duration(3000)
                .inputs(NUCLEAR_WASTE_REACTIVE_NONMETAL.getStackForm())
                .fluidOutputs(Krypton.getFluid(250))
                .fluidOutputs(Xenon.getFluid(500))
                .fluidOutputs(Radon.getFluid(1000))
                .chancedOutput(OreDictUnifier.get(dustTiny, Selenium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dustTiny, Bromine, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iodine, 2), 6250, 1000)
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .EUt(60)
                .duration((int) Uraninite.getAverageProtons() * 3 * 8)
                .input(dust, Uraninite, 3)
                .outputs(OreDictUnifier.get(dust, UraniumRadioactive.getMaterial()))
                .fluidOutputs(Oxygen.getFluid(2000))
                .buildAndRegister();


    }
}
