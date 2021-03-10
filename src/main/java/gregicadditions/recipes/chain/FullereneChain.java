package gregicadditions.recipes.chain;

import gregicadditions.GAValues;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.craftingLens;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class FullereneChain {
    public static void init() {
        // 2 NaOCN + 2 C10H8 = 2 Na + 2 C10H7CN + H2O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphtalene.getFluid(2000))
                .fluidInputs(SodiumCyanide.getFluid(2000))
                .fluidOutputs(HydrogenPeroxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Sodium, 2))
                .outputs(Cyanonaphtalene.getItemStack(38))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();
        // C10H7CN + 2H2O + CO = C11H8O + NH3 + CO2
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Cyanonaphtalene.getItemStack(19))
                .notConsumable(TinChloride.getItemStack())
                .fluidInputs(Water.getFluid(2000))
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .fluidOutputs(Naphthaldehyde.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .EUt(2000000)
                .duration(20)
                .buildAndRegister();
        // NH4Cl = HCl + NH3
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .duration(400)
                .EUt(120)
                .buildAndRegister();
        // C11H8O + C8H10 + CaO = C19H14 + 2H2O + Ca
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Naphthaldehyde.getFluid(1000))
                .fluidInputs(EthylBenzene.getFluid(1000))
                .input(dust, Calcite, 2)
                .notConsumable(dust, Iodine)
                .notConsumable(Triphenylphosphine.getItemStack())
                .fluidOutputs(Water.getFluid(2000))
                .outputs(Methylbenzophenanthrene.getItemStack(33))
                .outputs(OreDictUnifier.get(dust, Calcium, 1))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();
        // 3C19H14 = 3C18H12 + C3H6
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Methylbenzophenanthrene.getItemStack(33))
                .outputs(Benzophenanthrenylacetonitrile.getItemStack(30))
                .fluidOutputs(Propene.getFluid(1000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();
        // 10 C18H12 + 60 O -> 3 C60 + 60 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Benzophenanthrenylacetonitrile.getItemStack(300))
                .notConsumable(TiAlChloride.getItemStack())
                .fluidInputs(Oxygen.getFluid(60000))
                .outputs(UnfoldedFullerene.getItemStack(3))
                .fluidOutputs(Water.getFluid(60000))
                .EUt(2000000)
                .duration(250)
                .buildAndRegister();
        LARGE_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, MarkerMaterials.Color.Magenta)
                .inputs(UnfoldedFullerene.getItemStack())
                .outputs(Fullerene.getItemStack())
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();
        // 3Cl + P = PCl3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Chlorine.getFluid(3000))
                .input(dust, Phosphorus)
                .fluidOutputs(PhosphorusChloride.getFluid(1000))
                .EUt(480)
                .duration(300)
                .buildAndRegister();
        // 6Na + PCl3 + 3C6H5Cl = 6NaCl + C18H15P
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium, 6)
                .fluidInputs(PhosphorusChloride.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, Salt, 12))
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
        // C4H6O4 + NH3 = C4H5NO2 + 2H2O
        BLAST_RECIPES.recipeBuilder()
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidInputs(Ammonia.getFluid(1000))
                .outputs(Succinimide.getItemStack(10))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(2000000)
                .duration(100)
                .blastFurnaceTemp(10000)
                .buildAndRegister();
        // C4H5NO2 + 2Br = C4H4BrNO2 + HBr
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Succinimide.getItemStack(14))
                .fluidInputs(Bromine.getFluid(2000))
                .outputs(Bromosuccinimide.getItemStack(10))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();
        // SO3 + S + 2Cl = SO2 + SOCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .input(dust, Sulfur)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(ThionylChloride.getFluid(2000))
                .EUt(2000000)
                .duration(400)
                .buildAndRegister();
        //  Ti + Al + Cl = TiAlCl
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Titanium)
                .input(dust, Aluminium)
                .fluidInputs(Chlorine.getFluid(1000))
                .EUt(2000000)
                .duration(50)
                .outputs(TiAlChloride.getItemStack(3))
                .buildAndRegister();
        // HCN + KOH = KCN + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .outputs(PotassiumCyanide.getItemStack())
                .fluidOutputs(Water.getFluid(1000))
                .EUt(7680)
                .duration(1000)
                .buildAndRegister();
        // KCl + H2O = KOH + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(7680)
                .duration(600)
                .buildAndRegister();
        // 9Br + 9Mg + 10C6H12O6 + 6FeCl3 + 60 Na + 60 H2O = 60[NaOH + H2O] + 6C10H10Fe + 9MgBrCl2
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(IronChloride.getFluid(6000))
                .fluidInputs(Magnesium.getFluid(1296))
                .fluidInputs(Bromine.getFluid(9000))
                .fluidInputs(Water.getFluid(60000))
                .input(dust, Sodium, 60)
                .inputs(Fructose.getItemStack(240))
                .inputs(ZeoliteSievingPellets.getItemStack())
                .notConsumable(PdIrReOCeOS.getItemStack())
                .fluidOutputs(Ferrocene.getFluid(6000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(60000))
                .outputs(MgClBrominide.getItemStack(36))
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
        // 2C10H10Fe + 2C60 + C2H4 + 2C3H7NO2 + 4CO = 2[C10H10Fe + C60 + C4H9N] + 4CO2
        // C2H4 + 2C3H7NO2 = 2C4H9N + 4O
        // TODO: Catalysts: Chloroform, Toluene
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .inputs(Fullerene.getItemStack(2))
                .inputs(NMethylglicine.getItemStack(22))
                .notConsumable(SodiumEthoxide.getItemStack())
                .notConsumable(AluminiumChloride.getItemStack())
                .fluidInputs(Ferrocene.getFluid(2000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(CarbonMonoxde.getFluid(4000))
                .fluidInputs(Chloroform.getFluid(10))
                .fluidInputs(Toluene.getFluid(10))
                .fluidOutputs(Ferrocenylfulleropyrrolidine.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(4000))
                .EUt(2000000)
                .duration(750)
                .buildAndRegister();
        // NaOH + C2H5OH = H2O + C2H5ONa
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(SodiumEthoxide.getItemStack(9))
                .EUt(500000)
                .duration(10)
                .buildAndRegister();
        // CH3NH2 + 2Cl + CH3COOH = C3H7NO2 + 2HCl
        // TODO: Catalysts: Acetic Anhydride
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(10))
                .outputs(NMethylglicine.getItemStack(11))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();
        // NH3 + CH4O = CH3NH2 + H2O
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
        // [C10H10Fe + C60 + C4H9N] + Pd + CH3COOH + HNO3 = PdC60
        // Calling this fine
        // TODO: Catalysts: Nitric Acid
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ferrocenylfulleropyrrolidine.getFluid(1000))
                .input(dust, Palladium)
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(10))
                .outputs(PdFullereneMatrix.getItemStack())
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();
        // H2S + 2 CH4O = C2H6S + 2 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidInputs(Methanol.getFluid(21000))
                .fluidOutputs(Dimethylsulfide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(2000000)
                .duration(100)
                .buildAndRegister();
        // 2C7H8 + SOCl2 + 4KMnO4 = H2O + 4 MnO2 + 4 KOH + SO2 + 2 C7H5ClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(PotassiumPermanganate.getItemStack(24))
                .fluidInputs(ThionylChloride.getFluid(1000))
                .fluidInputs(Toluene.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Pyrolusite, 12))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .fluidOutputs(BenzoylChloride.getFluid(2000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();
        // H2O2 + 2C7H5ClO = C14H10O4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenPeroxide.getFluid(2000))
                .fluidInputs(BenzoylChloride.getFluid(2000))
                .fluidOutputs(BenzylPeroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(2000000)
                .duration(60)
                .buildAndRegister();
        // 24CO + 15C4H8 + 8C6H6 = 12C11H14O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(24000))
                .fluidInputs(Butene.getFluid(15000))
                .fluidInputs(Benzene.getFluid(8000))
                .fluidOutputs(Phenylpentanoicacid.getFluid(12000))
                .EUt(2000000)
                .duration(500)
                .buildAndRegister();
        /*
        *
        * C60 (fullerene) + C11H14O2 (phenylpentanoic acid) + C2H6S (dimethylsulfide) + C6H5Cl (chlorobenzene) -> C72H14O2 (PCBA = phenyl-C61-butyric acid) + C7H8 + H2S + HCl
        *
        */
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Fullerene.getItemStack())
                .fluidInputs(Phenylpentanoicacid.getFluid(1000))
                .fluidInputs(Dimethylsulfide.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .fluidOutputs(Toluene.getFluid(1000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(PCBA.getFluid(1000))
                .EUt(2000000)
                .duration(450)
                .buildAndRegister();
        // 3Ag2O + 8BF3 + = 6AgBF4 + B2O3
        // TODO: Catalyst: Benzene
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BoronFluoride.getFluid(8000))
                .input(dust, SilverOxide, 9)
                .fluidInputs(Benzene.getFluid(10))
                .fluidOutputs(Silvertetrafluoroborate.getFluid(6000))
                .outputs(BoronOxide.getItemStack(5))
                .EUt(122880)
                .duration(50)
                .buildAndRegister();
        // 2NH3 + COCl2 + 2C3H8O + 3C = C7H14N2 + 3H2O + 3CO2 + 2HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Phosgene.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .input(dust, Carbon, 3)
                .notConsumable(Triphenylphosphine.getItemStack())
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .fluidOutputs(Diisopropylcarbodiimide.getFluid(1000))
                .EUt(2000000)
                .duration(250)
                .buildAndRegister();
        // CO + 2Cl = COCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .notConsumable(dust, Carbon)
                .fluidOutputs(Phosgene.getFluid(1000))
                .EUt(2000)
                .duration(800)
                .buildAndRegister();
        // (CH3)2NH + C5H5N + O = H2O + C7H10N2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(Pyridine.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(Dimethylaminopyridine.getItemStack(19))
                .EUt(2000000)
                .duration(400)
                .buildAndRegister();
        /*
         *
         * 8C72H14O2 (PCBA) + 7C8H8 (Styrene) + 8CH2Cl2 (dichloromethane) = 8C80H21O2 (PCBS) + 16HCl
         *
         */
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PCBA.getFluid(8000))
                .fluidInputs(Styrene.getFluid(7000))
                .fluidInputs(Dichloromethane.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(16000))
                .fluidOutputs(PCBS.getFluid(8000))
                .EUt(524288)
                .duration(38571)
                .buildAndRegister();
        // PdC60 + C80H21O2 (PCBS) = [PdC60 + C80H21O2]
        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(PCBS.getFluid(1000))
                .inputs(PdFullereneMatrix.getItemStack())
                .outputs(OreDictUnifier.get(dust, FullerenePolymerMatrix, 2))
                .EUt(8000000)
                .duration(100)
                .buildAndRegister();

    }
}
