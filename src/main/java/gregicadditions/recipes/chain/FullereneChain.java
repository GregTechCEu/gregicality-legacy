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

        // 2NaOCN + 2C10H8 -> 2Na + 2C10H7CN + H2O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphtalene.getFluid(2000))
                .fluidInputs(SodiumCyanide.getFluid(2000))
                .fluidOutputs(HydrogenPeroxide.getFluid(1000))
                .output(dust, Sodium, 2)
                .outputs(Cyanonaphtalene.getItemStack(38))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();

        // C10H7CN + H2O + 3HCl -> C11H8O + NH4Cl + 2Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Cyanonaphtalene.getItemStack(19))
                .notConsumable(TinChloride.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(Naphthaldehyde.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();

        // NH4Cl -> HCl + NH3
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        // C11H8O + C8H10 + O -> C19H14 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphthaldehyde.getFluid(1000))
                .fluidInputs(EthylBenzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(dust, Iodine)
                .notConsumable(Triphenylphosphine.getItemStack())
                .fluidOutputs(Water.getFluid(2000))
                .outputs(Methylbenzophenanthrene.getItemStack(33))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();

        // C19H14 + KCN + C4H4BrNO2 -> C20H13N + KBr + C4H5NO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Methylbenzophenanthrene.getItemStack(33))
                .inputs(PotassiumCyanide.getItemStack(3))
                .inputs(Bromosuccinimide.getItemStack(12))
                .outputs(Benzophenanthrenylacetonitrile.getItemStack(30))
                .outputs(PotassiumBromide.getItemStack(2))
                .outputs(Succinimide.getItemStack(12))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // 3C20H13N -> C60H30 + 3NH3
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Benzophenanthrenylacetonitrile.getItemStack(102))
                .notConsumable(TiAlChloride.getItemStack())
                .outputs(UnfoldedFullerene.getItemStack())
                .fluidOutputs(Ammonia.getFluid(3000))
                .EUt(2000000)
                .duration(250)
                .buildAndRegister();

        // C60H30 + 15O -> C60 + 15H2O
        LARGE_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Magenta)
                .inputs(UnfoldedFullerene.getItemStack())
                .fluidInputs(Oxygen.getFluid(15000))
                .outputs(Fullerene.getItemStack())
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
                .outputs(Triphenylphosphine.getItemStack(34))
                .EUt(2000000)
                .duration(250)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(EschericiaColi.getItemStack())
                .input(dust, Sugar)
                .EUt(125000)
                .outputs(SuccinicAcid.getItemStack(14))
                .duration(500)
                .buildAndRegister();

        // C4H6O4 + NH3 -> C4H5NO2 + 2H2O
        BLAST_RECIPES.recipeBuilder()
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidInputs(Ammonia.getFluid(1000))
                .outputs(Succinimide.getItemStack(12))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(2000000)
                .duration(100)
                .blastFurnaceTemp(10000)
                .buildAndRegister();

        // C4H5NO2 + 2Br -> C4H4BrNO2 + HBr
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Succinimide.getItemStack(12))
                .fluidInputs(Bromine.getFluid(2000))
                .outputs(Bromosuccinimide.getItemStack(12))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // C4H4BrNO2 -> HBr + NO2 + 4C + 3H
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(Bromosuccinimide.getItemStack(12))
                .output(dust, Carbon, 4)
                .fluidOutputs(Hydrogen.getFluid(3000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .duration(158)
                .EUt(2000)
                .buildAndRegister();


        // SO3 + S + 2Cl -> SO2 + SOCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .input(dust, Sulfur)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(ThionylChloride.getFluid(1000))
                .EUt(2000000)
                .duration(400)
                .buildAndRegister();

        // TiCl4 + AlCl3 -> TiAlCl7
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .inputs(AluminiumChloride.getItemStack(4))
                .EUt(2000000)
                .duration(50)
                .outputs(TiAlChloride.getItemStack(9))
                .buildAndRegister();

        // HCN + KOH -> KCN + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(PotassiumCyanide.getItemStack(3))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(7680)
                .duration(1000)
                .buildAndRegister();

        // KCN -> K + C + N
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(PotassiumCyanide.getItemStack(3))
                .output(dust, Potassium)
                .output(dust, Carbon)
                .fluidOutputs(Nitrogen.getFluid(1000))
                .duration(150)
                .EUt(60)
                .buildAndRegister();

        // KCl + H2O -> KOH + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(300)
                .duration(300)
                .buildAndRegister();

        // K + Cl -> KCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium)
                .fluidInputs(Chlorine.getFluid(1000))
                .output(dust, RockSalt, 2)
                .EUt(240)
                .duration(350)
                .buildAndRegister();

        // 2C5H6 + FeCl2 -> C10H10Fe + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(ZeoliteSievingPellets.getItemStack())
                .notConsumable(PdIrReOCeOS.getItemStack())
                .fluidInputs(IronChloride.getFluid(1000))
                .fluidInputs(Cyclopentadiene.getFluid(2000))
                .fluidOutputs(Ferrocene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .outputs(WetZeoliteSievingPellets.getItemStack())
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .input(dust, Palladium)
                .input(dust, Iridium)
                .input(dust, Rhenium)
                .input(dust, Cerium)
                .input(dust, Osmium)
                .input(dust, Silicon)
                .fluidInputs(Oxygen.getFluid(4000))
                .outputs(PdIrReOCeOS.getItemStack(10))
                .EUt(2000000)
                .duration(5)
                .buildAndRegister();

        // 2C10H10Fe + 2C60 + C2H4 + 2C3H7NO2 + 4CO -> 2[C10H10Fe + C60 + C4H9N] + 4CO2
        // C2H4 + 2C3H7NO2 -> 2C4H9N + 4O
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .inputs(Fullerene.getItemStack(2))
                .inputs(Sarcosine.getItemStack(26))
                .fluidInputs(Ferrocene.getFluid(2000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(CarbonMonoxde.getFluid(4000))
                .notConsumable(SodiumEthoxide.getItemStack())
                .notConsumable(AluminiumChloride.getItemStack())
                .notConsumable(Chloroform)
                .notConsumable(Toluene)
                .fluidOutputs(Ferrocenylfulleropyrrolidine.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(4000))
                .EUt(2000000)
                .duration(750)
                .buildAndRegister();

        // NaOH + C2H5OH -> H2O + C2H5ONa
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(SodiumEthoxide.getItemStack(9))
                .EUt(500000)
                .duration(10)
                .buildAndRegister();

        // CH3NH2 + 2Cl + CH3COOH -> C3H7NO2 + 2HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .notConsumable(AceticAnhydride.getFluid(0))
                .outputs(Sarcosine.getItemStack(13))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // NH3 + CH4O -> CH3NH2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .notConsumable(dust, AluminoSilicateWool)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidOutputs(Methylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(122880)
                .duration(200)
                .buildAndRegister();

        // [C10H10Fe + C60 + C4H9N] + Pd + CH3COOH + HNO3 -> PdC60
        // Calling this fine
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ferrocenylfulleropyrrolidine.getFluid(1000))
                .input(dust, Palladium)
                .fluidInputs(AceticAcid.getFluid(1000))
                .notConsumable(NitricAcid)
                .outputs(PdFullereneMatrix.getItemStack())
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // H2S + 2CH4O -> C2H6S + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidInputs(Methanol.getFluid(21000))
                .fluidOutputs(Dimethylsulfide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();

        // 2C7H8 + SOCl2 + 4KMnO4 -> H2O + 4MnO2 + 4KOH + SO2 + 2C7H5ClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(PotassiumPermanganate.getItemStack(24))
                .fluidInputs(ThionylChloride.getFluid(1000))
                .fluidInputs(Toluene.getFluid(2000))
                .output(dust, Pyrolusite, 12)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(BenzoylChloride.getFluid(2000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // H2O2 + 2C7H5ClO -> C14H10O4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(BenzoylChloride.getFluid(2000))
                .fluidOutputs(BenzoylPeroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(2000000)
                .duration(60)
                .buildAndRegister();

        // 24CO + 15C4H8 + 8C6H6 -> 12C11H14O2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(24000))
                .fluidInputs(Butene.getFluid(15000))
                .fluidInputs(Benzene.getFluid(8000))
                .fluidOutputs(Phenylpentanoicacid.getFluid(12000))
                .EUt(2000000)
                .duration(500)
                .buildAndRegister();

        // C60 + C11H14O2 + C2H6S + C6H5Cl -> C72H14O2 + C7H8 + H2S + HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Fullerene.getItemStack())
                .fluidInputs(Phenylpentanoicacid.getFluid(1000))
                .fluidInputs(Dimethylsulfide.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(Toluene.getFluid(1000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(PCBA.getFluid(1000))
                .EUt(2000000)
                .duration(450)
                .buildAndRegister();

        // 3Ag2O + 8BF3 -> 6AgBF4 + B2O3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BoronFluoride.getFluid(8000))
                .input(dust, SilverOxide, 9)
                .notConsumable(Benzene)
                .fluidOutputs(Silvertetrafluoroborate.getFluid(6000))
                .outputs(BoronOxide.getItemStack(5))
                .EUt(122880)
                .duration(50)
                .buildAndRegister();

        // 2NH3 + COCl2 + 2C3H8O -> C7H14N2 + 3H2O + 2HCl
        // loses 1B water, but keeps it out of an LCR. Good tradeoff I think
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Phosgene.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .notConsumable(Triphenylphosphine.getItemStack())
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(4000))
                .fluidOutputs(Diisopropylcarbodiimide.getFluid(1000))
                .EUt(2000000)
                .duration(250)
                .buildAndRegister();

        // CO + 2Cl -> COCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .notConsumable(dust, Carbon)
                .fluidOutputs(Phosgene.getFluid(1000))
                .EUt(2000)
                .duration(800)
                .buildAndRegister();

        // (CH3)2NH + C5H5N -> H2 + C7H10N2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(Pyridine.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .outputs(Dimethylaminopyridine.getItemStack(19))
                .EUt(2000000)
                .duration(400)
                .buildAndRegister();

         // 8C72H14O2 + 7C8H8 + 8CH2Cl2 -> 8C80H21O2 + 16HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(Dimethylaminopyridine.getItemStack())
                .fluidInputs(PCBA.getFluid(8000))
                .fluidInputs(Styrene.getFluid(7000))
                .fluidInputs(Dichloromethane.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(16000))
                .fluidOutputs(PCBS.getFluid(8000))
                .EUt(524288)
                .duration(38571)
                .buildAndRegister();

        // PdC60 + C80H21O2 -> [PdC60 + C80H21O2]
        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(PCBS.getFluid(1000))
                .inputs(PdFullereneMatrix.getItemStack())
                .output(dust, FullerenePolymerMatrix, 2)
                .EUt(8000000)
                .duration(100)
                .buildAndRegister();
    }
}
