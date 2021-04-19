package gregicadditions.recipes.chain;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.LARGE_CHEMICAL_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class LithiumChain {
    public static void init() {

        // LiAlSi2O6 = LiAlSi2O6
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1400)
                .input(dust, Spodumene, 10)
                .outputs(RoastedSpodumene.getItemStack(10))
                .buildAndRegister();

        // KLi3Al4F2O10 + CaO = CaF2 + (KLi3Al4O10)O
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(1400)
                .input(dust, Lepidolite, 20)
                .input(dust, Quicklime, 2)
                .outputs(RoastedLepidolite.getItemStack(19))
                .output(dust, Fluorite, 3)
                .buildAndRegister();

        // LiAlSi2O6 + H2SO4 = [LiAlO2 + H2SO4] + 2SiO2
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(120)
                .inputs(RoastedSpodumene.getItemStack(10))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(DissolvedLithiumOre.getFluid(1000))
                .output(dust, SiliconDioxide, 6)
                .buildAndRegister();

        // (KLi3Al4O10)O + Al + 3H2SO4 = 3[LiAlO2 + H2SO4] + Al2O3 + K2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(120)
                .inputs(RoastedLepidolite.getItemStack(19))
                .input(dust, Aluminium)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidOutputs(DissolvedLithiumOre.getFluid(3000))
                .output(dust, Potash, 3)
                .outputs(Alumina.getItemStack(5))
                .buildAndRegister();

        // 2[LiAlO2 + H2SO4] + H2SO4 + CO2 = Al2(SO4)3 + [Li2CO3 + H2O] + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(DissolvedLithiumOre.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .outputs(AluminiumSulfate.getItemStack(17))
                .fluidOutputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // K2SO4 -> 2K + S + 4O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .inputs(PotassiumSulfate.getItemStack(7))
                .output(dust, Potassium, 2)
                .output(dust, Sulfur)
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        // Al2(SO4)3 = 2Al + 3S + 12O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .inputs(AluminiumSulfate.getItemStack(17))
                .output(dust, Aluminium, 2)
                .output(dust, Sulfur, 3)
                .fluidOutputs(Oxygen.getFluid(12000))
                .buildAndRegister();

        // [Li2CO3 + H2O] + 2HCl + 2Na = 2[LiCl + H2O] + Na2CO3
        // off by 1 oxygen, which is fine since water is lost in dehydrator step
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(120)
                .input(dust, Sodium, 2)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, SodaAsh, 6)
                .fluidOutputs(LithiumChlorideSolution.getFluid(2000))
                .buildAndRegister();

        // [LiCl + H2O] = LiCl + H2O (H2O Voided - Dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(LithiumChlorideSolution.getFluid(1000))
                .outputs(LithiumChloride.getItemStack(2))
                .buildAndRegister();

        // LiCl + KCl = [LiCl + KCl]X
        MIXER_RECIPES.recipeBuilder().duration(90).EUt(120)
                .inputs(LithiumChloride.getItemStack(2))
                .input(dust, RockSalt, 2)
                .outputs(LiKChlorideEutetic.getItemStack(4))
                .buildAndRegister();

        // 0.25[LiCl + KCl]X = 0.25[LiCl + KCl]Z
        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(150).EUt(120)
                .inputs(LiKChlorideEutetic.getItemStack())
                .fluidOutputs(MoltenLiKChlorideEutetic.getFluid(250))
                .buildAndRegister();

        // [LiCl + KCl]Z = 2Cl + Li + K
        ELECTROLYZER_RECIPES.recipeBuilder().duration(900).EUt(125)
                .fluidInputs(MoltenLiKChlorideEutetic.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .output(dust, Lithium)
                .output(dust, Potassium)
                .buildAndRegister();
    }
}
