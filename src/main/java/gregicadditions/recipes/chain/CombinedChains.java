package gregicadditions.recipes.chain;

import static gregicadditions.GAEnums.GAOrePrefix.dioxide;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CombinedChains {

    public static void init() {

        // Fullerenes ================================================================================================

        // 10C10H8 + 10C8H10 -> 3C60H30 + 90H (H voided)
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(40).EUt(31457280) // UIV
                .fluidInputs(Naphthalene.getFluid(10000))
                .fluidInputs(EthylBenzene.getFluid(10000))
                .outputs(UnfoldedFullerene.getItemStack(3))
                .buildAndRegister();


        // Naquadah ==================================================================================================

        // Naquadric Compound [Nq] + KHSO5 + 2H -> Nq + KOH + H2SO4
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(50).EUt(491520) // UV
                .input(dust, NaquadricCompound)
                .inputs(PotassiumPeroxymonosulfate.getItemStack(8))
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, Naquadah)
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .buildAndRegister();

        // Enriched Naquadric Compound [Nq+] + Au2Cl6 + Xe + 3O -> Nq+ + 2Au + 6Cl + XeO3
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(50).EUt(491520) // UV
                .input(dust, EnrichedNaquadricCompound)
                .fluidInputs(AuricChloride.getFluid(1000))
                .fluidInputs(Xenon.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, NaquadahEnriched)
                .output(dust, Gold, 2)
                .fluidOutputs(Chlorine.getFluid(6000))
                .fluidOutputs(XenonTrioxide.getFluid(1000))
                .buildAndRegister();

        // Naquadriatic Compound [*Nq*] + Rn + Xe + 6O -> *Nq* + RnO3 + XeO3
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(50).EUt(491520) // UV
                .input(dust, NaquadriaticCompound)
                .fluidInputs(Radon.getFluid(1000))
                .fluidInputs(Xenon.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(6000))
                .output(dust, Naquadria)
                .fluidOutputs(RadonTrioxide.getFluid(1000))
                .fluidOutputs(XenonTrioxide.getFluid(1000))
                .buildAndRegister();


        // Polymers ==================================================================================================


        // Rubber (unneeded)

        // Polyethylene (unneeded)

        // Polyvinyl Chloride (unneeded)

        // Polystyrene (unneeded)

        // Styrene-Butadiene Rubber (unneeded)

        // Polytetrafluoroethylene (unneeded)

        // Epoxy
        // 2C6H6 + 8O + 2C2H4 + C3H6 -> C2H4O + CO2 + 3H2O (gtce epoxy is not balanced)
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(60).EUt(30720) // LuV
                .fluidInputs(Benzene.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(8000))
                .fluidInputs(Ethylene.getFluid(2000))
                .fluidInputs(Propene.getFluid(1000))
                .notConsumable(HydrochloricAcid.getFluid(0))
                .fluidOutputs(Epoxid.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 4,4'-Oxydianiline-Pyromellitimide
        // C6H4(CH3)2 + 2CH3Cl + 12O + 2C6H5NH2 + C2H5OH -> C22H14N2O7 + 2CH4 + 6H2O + 2HCl
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(80).EUt(491520) // UV
                .notConsumable(dust, Tin)
                .fluidInputs(OrthoXylene.getFluid(1000))
                .fluidInputs(Chloromethane.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(12000))
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(Phenol.getFluid(1000))
                .fluidOutputs(Polyimide.getFluid(1008))
                .fluidOutputs(Methane.getFluid(2000))
                .fluidOutputs(Water.getFluid(6000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // Fluorinated-Ethylene Proplyene (unneeded)

        // Polybenzimidazole
        // 2NH3 + 2HNO3 + 3C6H6 + 3O + C2H4 -> C20H12N4 + 9H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(70).EUt(1966080) // UHV
                .notConsumable(dust, Zinc)
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(Benzene.getFluid(3000))
                .fluidOutputs(Polybenzimidazole.getFluid(1008))
                .fluidOutputs(Water.getFluid(10000))
                .buildAndRegister();

        // PolyEtherEtherKetone
        // C6H6O2 + 2C6H5F + Na2CO3 -> [(OC6H4)3C]n + 2NaF + 2H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(120).EUt(31457280) // UIV
                .input(dust, SodaAsh, 6)
                .fluidInputs(Hydroquinone.getFluid(1000))
                .fluidInputs(FluoroBenzene.getFluid(2000))
                .fluidOutputs(Polyetheretherketone.getFluid(2592))
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, SodiumFluoride, 4)
                .EUt(122880)
                .duration(250)
                .buildAndRegister();

        // Zylon
        // C6H6O2 + 2HNO3 + C8H10 -> C14H6N2O2 + 6H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(30).EUt(31457280) // UIV
                .notConsumable(AuPdCCatalyst.getItemStack())
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(OrthoXylene.getFluid(1000))
                .output(dust, Zylon)
                .fluidOutputs(Water.getFluid(6000))
                .buildAndRegister();

        // Fullerene Polymer Matrix
        // 2Pd + 2CH3COOH + 2C10H10Fe + 2C60 + C2H4 + 2C3H7NO2 -> 2PdC60 + 4O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(60).EUt(503316480) // UXV
                .inputs(Fullerene.getItemStack(2))
                .inputs(Sarcosine.getItemStack(26))
                .input(dust, Palladium, 2)
                .fluidInputs(Ferrocene.getFluid(2000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(2000))
                .notConsumable(SodiumEthoxide.getItemStack())
                .notConsumable(AluminiumChloride.getItemStack())
                .notConsumable(Chloroform)
                .notConsumable(Toluene)
                .outputs(PdFullereneMatrix.getItemStack(2))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();


        // 7C8H8 + 8CH2Cl2 + 8C60 + 8C11H14O2 + 8C2H6S + 8C6H5Cl -> 8C7H8 + 8H2S + 8C80H21O2 + 24HCl
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(60).EUt(503316480) // UXV
                .notConsumable(Dimethylaminopyridine.getItemStack())
                .inputs(Fullerene.getItemStack(8))
                .fluidInputs(Styrene.getFluid(7000))
                .fluidInputs(Dichloromethane.getFluid(8000))
                .fluidInputs(Phenylpentanoicacid.getFluid(8000))
                .fluidInputs(Dimethylsulfide.getFluid(8000))
                .fluidInputs(Chlorobenzene.getFluid(8000))
                .fluidOutputs(Toluene.getFluid(8000))
                .fluidOutputs(HydrogenSulfide.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(24000))
                .fluidOutputs(PCBS.getFluid(8000))
                .buildAndRegister();

        // Combined Process - Uraninite
        CHEMICAL_PLANT_RECIPES.recipeBuilder().EUt(7680).duration(864)
                .input(dust, Uraninite, 3)
                .fluidInputs(NitrationMixture.getFluid(4000))
                .output(dioxide, UraniumRadioactive.getMaterial(), 3)
                .fluidOutputs(UraniumSulfateWasteSolution.getFluid(1000))
                .fluidOutputs(UraniumRefinementWasteSolution.getFluid(1000))
                .fluidOutputs(ThoriumNitrateSolution.getFluid(1000))
                .buildAndRegister();
    }
}
