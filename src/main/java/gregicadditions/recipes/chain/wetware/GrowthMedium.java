package gregicadditions.recipes.chain.wetware;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import net.minecraft.item.ItemStack;

import static net.minecraft.init.Items.APPLE;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.ULTRASONIC_HOMOGENIZER;
import static gregtech.common.items.MetaItems.RUBBER_DROP;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class GrowthMedium {
    public static void init() {

        // 2CaO + 5C -> CO2 + 2CaC2
        BLAST_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 4)
                .input(dust, Carbon, 5)
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .outputs(CalciumCarbide.getItemStack(6))
                .blastFurnaceTemp(2500)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // CaC2 + 2H2O -> Ca(OH)2 + C2H2
        BLAST_RECIPES.recipeBuilder()
                .inputs(CalciumCarbide.getItemStack(3))
                .fluidInputs(Steam.getFluid(2000))
                .outputs(CalciumHydroxide.getItemStack(5))
                .fluidOutputs(Acetylene.getFluid(1000))
                .blastFurnaceTemp(2300)
                .EUt(30720)
                .duration(10)
                .buildAndRegister();

        // Ca(OH)2 + 2HCl -> 2H2O + CaCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(CalciumHydroxide.getItemStack(5))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, CalciumChloride, 3)
                .EUt(480)
                .duration(50)
                .buildAndRegister();

        // O + CH3OH -> CH2O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Silver)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Formaldehyde.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(4096)
                .duration(100)
                .buildAndRegister();

        // CH2O + C2H2 -> C3H4O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Copper)
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidOutputs(PropargylAlcohol.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        // C3H4O + 2Cl -> C3H3Cl + HClO
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PropargylAlcohol.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(PropargylChloride.getFluid(1000))
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .inputs(RUBBER_DROP.getStackForm())
                .fluidOutputs(Resin.getFluid(100))
                .EUt(32)
                .duration(100)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(Resin.getFluid(1000))
                .fluidOutputs(Turpentine.getFluid(200))
                .EUt(480)
                .duration(500)
                .buildAndRegister();

        // C10H16 + H2SO4 -> C10H16 + H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Turpentine.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(BetaPinene.getItemStack(26))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        // C10H16 + 2C5H8 + 2O -> 2C10H16O
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(BetaPinene.getItemStack(26))
                .fluidInputs(Isoprene.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(Citral.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        // C10H16O + C3H6O -> C13H20O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Citral.getFluid(1000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(BetaIonone.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        // 25C13H20O + 5C3H3Cl -> 17C20H30O + 8O + 5HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BetaIonone.getFluid(25000))
                .fluidInputs(PropargylChloride.getFluid(5000))
                .fluidOutputs(VitaminA.getFluid(17000))
                .fluidOutputs(Oxygen.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(5000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        // This needs to be better, Zalgo was working on something
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(APPLE))
                .chancedOutput(Yeast.getItemStack(), 500, 250)
                .EUt(120)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Biomass.getFluid(1000))
                .inputs(Yeast.getItemStack())
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .fluidOutputs(LinoleicAcid.getFluid(1000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        // C2H4 + O -> C2H4O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(10))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(EthyleneOxide.getFluid(1000))
                .EUt(500)
                .duration(400)
                .buildAndRegister();

        // NH3 + C2H4O -> C2H7NO
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidOutputs(Ethanolamine.getFluid(1000))
                .EUt(30720)
                .duration(150)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(CupriavidusNecator.getItemStack())
                .input(dust, Sugar)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Biotin.getFluid(2000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Biotin.getFluid(1000))
                .fluidInputs(LinoleicAcid.getFluid(1000))
                .fluidInputs(Catalase.getFluid(1000))
                .fluidInputs(VitaminA.getFluid(1000))
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidOutputs(B27Supplement.getFluid(5000))
                .EUt(30720)
                .duration(250)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CleanAmmoniaSolution.getFluid(2000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(CleanAmmoniaSolution.getFluid(1000))
                .inputs(BrevibacteriumFlavium.getItemStack())
                .input(dust, Sugar)
                .outputs(Glutamine.getItemStack(40))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Meat)
                .fluidOutputs(Blood.getFluid(250))
                .EUt(32)
                .duration(50)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(Blood.getFluid(1000))
                .fluidOutputs(BloodCells.getFluid(450))
                .fluidOutputs(BloodPlasma.getFluid(550))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BloodPlasma.getFluid(1000))
                .fluidOutputs(Catalase.getFluid(10))
                .fluidOutputs(BFGF.getFluid(10))
                .fluidOutputs(EGF.getFluid(10))
                .EUt(480)
                .duration(50)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(B27Supplement.getFluid(1000))
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .inputs(Glutamine.getItemStack(20))
                .fluidInputs(BFGF.getFluid(1000))
                .fluidInputs(EGF.getFluid(1000))
                .fluidOutputs(RawGrowthMedium.getFluid(4000))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BloodCells.getFluid(1000))
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BacterialGrowthMedium.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
    }
}
