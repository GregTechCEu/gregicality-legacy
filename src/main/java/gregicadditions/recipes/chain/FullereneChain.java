package gregicadditions.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.craftingLens;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.material.MarkerMaterials.Color.Magenta;

public class FullereneChain {
    public static void init() {

        // 2NaOCN + 2C10H8 -> 2NaOH + 2C10H7CN
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphthalene.getFluid(2000))
                .fluidInputs(SodiumCyanide.getFluid(2000))
                .output(dust, SodiumHydroxide, 6)
                .output(dust, Cyanonaphthalene, 38)
                .EUt(1920)
                .duration(80)
                .buildAndRegister();

        // C10H7CN + H2O + 3HCl -> C11H8O + NH4Cl + 2Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Cyanonaphthalene, 19)
                .notConsumable(dust, TinChloride)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(Naphthaldehyde.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .EUt(1920)
                .duration(80)
                .buildAndRegister();

        // NH4Cl -> HCl + NH3
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .duration(200)
                .EUt(120)
                .buildAndRegister();

        // C11H8O + C8H10 + O -> C19H14 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphthaldehyde.getFluid(1000))
                .fluidInputs(Ethylbenzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(dust, Iodine)
                .notConsumable(dust, Triphenylphosphine)
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, Methylbenzophenanthrene, 33)
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // C19H14 + KCN + C4H4BrNO2 -> C20H13N + KBr + C4H5NO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Methylbenzophenanthrene, 33)
                .input(dust, PotassiumCyanide, 3)
                .input(dust, Bromosuccinimide, 12)
                .output(dust, Benzophenanthrenylacetonitrile, 34)
                .output(dust, PotassiumBromide, 2)
                .output(dust, Succinimide, 12)
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // 3C20H13N -> C60H30 + 3NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Benzophenanthrenylacetonitrile, 102)
                .notConsumable(dust, TiAlChloride)
                .output(dust, UnfoldedFullerene)
                .fluidOutputs(Ammonia.getFluid(3000))
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        // C60H30 + 15O -> C60 + 15H2O
        LARGE_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Magenta)
                .input(dust, UnfoldedFullerene)
                .fluidInputs(Oxygen.getFluid(15000))
                .output(dust, Fullerene)
                .fluidOutputs(Water.getFluid(15000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // 6Na + PCl3 + 3C6H5Cl -> 6NaCl + C18H15P
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium, 6)
                .fluidInputs(PhosphorusTrichloride.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(3000))
                .output(dust, Salt, 12)
                .output(dust, Triphenylphosphine, 34)
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, EschericiaColi)
                .input(dust, Sugar)
                .EUt(480)
                .output(dust, SuccinicAcid, 14)
                .duration(200)
                .buildAndRegister();

        // C4H6O4 + NH3 -> C4H5NO2 + 2H2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust, SuccinicAcid, 14)
                .fluidInputs(Ammonia.getFluid(1000))
                .output(dust, Succinimide, 12)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(120)
                .duration(500)
                .blastFurnaceTemp(2100)
                .buildAndRegister();

        // C4H5NO2 + 2Br -> C4H4BrNO2 + HBr
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Succinimide, 12)
                .fluidInputs(Bromine.getFluid(2000))
                .output(dust, Bromosuccinimide, 12)
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // C4H4BrNO2 -> HBr + NO2 + 4C + 3H
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Bromosuccinimide, 12)
                .output(dust, Carbon, 4)
                .fluidOutputs(Hydrogen.getFluid(3000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .duration(158)
                .EUt(120)
                .buildAndRegister();


        // SO3 + S + 2Cl -> SO2 + SOCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .input(dust, Sulfur)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(ThionylChloride.getFluid(1000))
                .EUt(480)
                .duration(400)
                .buildAndRegister();

        // TiCl4 + AlCl3 -> TiAlCl7
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .input(dust, AluminiumChloride, 4)
                .EUt(7680)
                .duration(50)
                .output(dust, TiAlChloride, 9)
                .buildAndRegister();

        // HCN + KOH -> KCN + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .output(dust, PotassiumCyanide, 3)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        // KCl + H2O -> KOH + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(480)
                .duration(270)
                .buildAndRegister();

        // K + Cl -> KCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium)
                .fluidInputs(Chlorine.getFluid(1000))
                .output(dust, RockSalt, 2)
                .EUt(30)
                .duration(220)
                .buildAndRegister();

        // H + FeCl3 -> FeCl2 + HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(30)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Iron3Chloride.getFluid(1000))
                .fluidOutputs(Iron2Chloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // 2C5H6 + FeCl2 -> C10H10Fe + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ZeoliteSievingPellets)
                .notConsumable(dust, PdIrReOCeOS)
                .fluidInputs(Iron2Chloride.getFluid(1000))
                .fluidInputs(Cyclopentadiene.getFluid(2000))
                .fluidOutputs(Ferrocene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .output(dust, WetZeoliteSievingPellets)
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .input(dust, Palladium)
                .input(dust, Iridium)
                .input(dust, Rhenium)
                .input(dust, Cerium)
                .input(dust, Osmium)
                .input(dust, Silicon)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, PdIrReOCeOS, 10)
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        // 2C10H10Fe + 2C60 + C2H4 + 2C3H7NO2 + 4CO -> 2[C10H10Fe + C60 + C4H9N] + 4CO2
        // C2H4 + 2C3H7NO2 -> 2C4H9N + 4O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, Fullerene, 2)
                .input(dust, Sarcosine, 26)
                .fluidInputs(Ferrocene.getFluid(2000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(CarbonMonoxide.getFluid(4000))
                .notConsumable(dust, SodiumEthoxide)
                .notConsumable(dust, AluminiumChloride)
                .notConsumable(Chloroform.getFluid(0))
                .notConsumable(Toluene.getFluid(0))
                .fluidOutputs(Ferrocenylfulleropyrrolidine.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(4000))
                .EUt(500000)
                .duration(750)
                .buildAndRegister();

        // NaOH + C2H5OH -> H2O + C2H5ONa
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, SodiumEthoxide, 9)
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        // CH3NH2 + 2Cl + CH3COOH -> C3H7NO2 + 2HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .notConsumable(AceticAnhydride.getFluid(0))
                .output(dust, Sarcosine, 13)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // NH3 + CH4O -> CH3NH2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .notConsumable(dust, Alumina)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidOutputs(Methylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // [C10H10Fe + C60 + C4H9N] + Pd + CH3COOH -> PdC60
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ferrocenylfulleropyrrolidine.getFluid(1000))
                .input(dust, Palladium)
                .fluidInputs(AceticAcid.getFluid(1000))
                .notConsumable(NitricAcid.getFluid(0))
                .output(dust, PdFullereneMatrix)
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // H2S + 2CH4O -> C2H6S + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(Dimethylsulfide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // 2C7H8 + SOCl2 + 4KMnO4 -> H2O + 4MnO2 + 4KOH + SO2 + 2C7H5ClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumPermanganate, 24)
                .fluidInputs(ThionylChloride.getFluid(1000))
                .fluidInputs(Toluene.getFluid(2000))
                .output(dust, Pyrolusite, 12)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(BenzoylChloride.getFluid(2000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // H2O2 + 2C7H5ClO -> C14H10O4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(BenzoylChloride.getFluid(2000))
                .fluidOutputs(BenzoylPeroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(1920)
                .duration(160)
                .buildAndRegister();

        // 24CO + 15C4H8 + 8C6H6 -> 12C11H14O2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxide.getFluid(24000))
                .fluidInputs(Butene.getFluid(15000))
                .fluidInputs(Benzene.getFluid(8000))
                .fluidOutputs(Phenylpentanoicacid.getFluid(12000))
                .EUt(1920)
                .duration(500)
                .buildAndRegister();

        // C60 + C11H14O2 + C2H6S + C6H5Cl -> C72H14O2 + C7H8 + H2S + HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Fullerene)
                .fluidInputs(Phenylpentanoicacid.getFluid(1000))
                .fluidInputs(Dimethylsulfide.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .notConsumable(BenzoylPeroxide.getFluid(0))
                .fluidOutputs(Toluene.getFluid(1000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(PCBA.getFluid(1000))
                .EUt(500000)
                .duration(450)
                .buildAndRegister();

        // Ag + Cl -> AgCl
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(120)
                .input(dust, Silver)
                .fluidInputs(Chlorine.getFluid(1000))
                .output(dust, SilverChloride, 2)
                .buildAndRegister();

        // 3Ag2O + 8BF3 -> 6AgBF4 + B2O3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BoronFluoride.getFluid(8000))
                .input(dust, SilverOxide, 9)
                .notConsumable(Benzene.getFluid(0))
                .fluidOutputs(Silvertetrafluoroborate.getFluid(6000))
                .output(dust, BoronOxide, 5)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // 2NH3 + COCl2 + 2C3H8O -> C7H14N2 + 3H2O + 2HCl
        // loses 1B water, but keeps it out of an LCR. Good tradeoff I think
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Phosgene.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .notConsumable(dust, Triphenylphosphine)
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(4000))
                .fluidOutputs(Diisopropylcarbodiimide.getFluid(1000))
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        // CO + 2Cl -> COCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .notConsumable(dust, Carbon)
                .fluidOutputs(Phosgene.getFluid(1000))
                .EUt(1920)
                .duration(400)
                .buildAndRegister();

        // (CH3)2NH + C5H5N -> H2 + C7H10N2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(Pyridine.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .output(dust, Dimethylaminopyridine, 19)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

         // 8C72H14O2 + 7C8H8 + 8CH2Cl2 -> 8C80H21O2 + 16HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Dimethylaminopyridine)
                .fluidInputs(PCBA.getFluid(8000))
                .fluidInputs(Styrene.getFluid(7000))
                .fluidInputs(Dichloromethane.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(16000))
                .fluidOutputs(PCBS.getFluid(8000))
                .EUt(524288)
                .duration(400)
                .buildAndRegister();

        // PdC60 + C80H21O2 -> [PdC60 + C80H21O2]
        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(PCBS.getFluid(1000))
                .input(dust, PdFullereneMatrix)
                .output(dust, FullerenePolymerMatrix, 2)
                .EUt(8000000)
                .duration(40)
                .buildAndRegister();
    }
}
