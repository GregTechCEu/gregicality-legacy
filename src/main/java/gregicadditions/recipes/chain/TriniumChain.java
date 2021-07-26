package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class TriniumChain {
    public static void init(){
        //Carborane

        //B(OH)3 + 3 C2H5OH + 4 NaH -> 3 C2H5ONa + NaBH4 + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .duration(120)
                .EUt(480)
                .inputs(SodiumHydride.getItemStack(8))
                .fluidInputs(BoricAcid.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(3000))
                .notConsumable(SulfuricAcid.getFluid(250))
                .outputs(SodiumBorohydride.getItemStack(6))
                .outputs(SodiumEthoxide.getItemStack(27))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(150).EUt(125)
                .fluidInputs(Diethylether.getFluid(1000))
                .fluidInputs(BoronFluoride.getFluid(1000))
                .fluidOutputs(BoronTrifluorideEtherate.getFluid(1000))
                .buildAndRegister();

        //17 NaBH4 + 20 BF3·(CH2CH3)2O + 4 H2O2 + 2 HF -> 2 NaF + 2 H2O + 2 B10H14 + 2 B(OH)3 + 15 NaBF4 + 20 H2 + 20 (CH2CH3)2O
        //divided by two, and optimal choice as a gate
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(380).EUt(2000)
                .inputs(SodiumBorohydride.getItemStack(51))
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(2000))
                .fluidInputs(BoronTrifluorideEtherate.getFluid(10000))
                .outputs(Decaborane.getItemStack(24))
                .output(dust, SodiumFluoride, 2)
                .outputs(SodiumTetrafluoroborate.getItemStack(45))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(20000))
                .fluidOutputs(Diethylether.getFluid(10000))
                .buildAndRegister();

        //NaBF4 -> NaF + BF3
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(125)
                .inputs(SodiumTetrafluoroborate.getItemStack(6))
                .output(dust, SodiumFluoride, 2)
                .fluidOutputs(BoronFluoride.getFluid(1000))
                .buildAndRegister();

        //B10H14 + NaCN + CsOH + 2 HCl + 3 CH3OH -> CsB10H12CN(CH3)3Cl + 4 H2O + NaCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .inputs(Decaborane.getItemStack(24))
                .inputs(CaesiumHydroxide.getItemStack(3))
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Methanol.getFluid(3000))
                .notConsumable(SulfuricAcid.getFluid(250))
                .outputs(CesiumCarboranePrecusor.getItemStack(38))
                .output(dust, Salt, 2)
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(165).EUt(125)
                .fluidInputs(Dimethylsulfide.getFluid(2000))
                .fluidInputs(Diborane.getFluid(1000))
                .fluidOutputs(BoraneDimethylsulfide.getFluid(2000))
                .buildAndRegister();

        //CsB10H12CN(CH3)3Cl + NaH + BH3.(CH3)2S -> CsCB11H12 + N(CH3)3 + NaCl + H2S + 2 CH4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(500)
                .inputs(CesiumCarboranePrecusor.getItemStack(38))
                .inputs(SodiumHydride.getItemStack(2))
                .fluidInputs(BoraneDimethylsulfide.getFluid(1000))
                .outputs(CesiumCarborane.getItemStack(25))
                .output(dust, Salt, 2)
                .fluidOutputs(Trimethylamine.getFluid(1000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Methane.getFluid(2000))
                .buildAndRegister();

        //2 CsCB11H12 + 2 AgNO3 + 44F + 2I + HCl + (CH3)3SiH -> 2 H(CHB11F11) + 2CsNO3 + 2AgI + 22HF + (CH3)3SiCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .inputs(CesiumCarborane.getItemStack(50))
                .inputs(SilverNitrate.getItemStack(10))
                .input(dust, Iodine, 2)
                .fluidInputs(Fluorine.getFluid(44000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Trimethylsilane.getFluid(1000))
                .outputs(Fluorocarborane.getItemStack(50))
                .outputs(CaesiumNitrate.getItemStack(10))
                .outputs(SilverIodide.getItemStack(4))
                .fluidOutputs(HydrofluoricAcid.getFluid(22000))
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .buildAndRegister();

        //Ag + 2 HNO3 -> AgNO3 + NO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(125)
                .input(dust, Silver)
                .fluidInputs(NitricAcid.getFluid(2000))
                .outputs(SilverNitrate.getItemStack(5))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        //Ag2O + 2 HNO3 -> 2 AgNO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(125)
                .input(dust, SilverOxide, 3)
                .fluidInputs(NitricAcid.getFluid(2000))
                .outputs(SilverNitrate.getItemStack(10))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(175).EUt(32)
                .inputs(CaesiumNitrate.getItemStack())
                .output(dust, Caesium)
                .fluidOutputs(Nitrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(210).EUt(125).blastFurnaceTemp(1100)
                .inputs(SilverIodide.getItemStack(4))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, SilverOxide, 3)
                .output(dust, Iodine, 2)
                .buildAndRegister();

        //Trinium Chain Proper

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(125)
                .input(dust, SodiumNitrate, 5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(FumingNitricAcid.getFluid(1000))
                .output(dust, SodiumBisulfate, 7)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(180).EUt(125)
                .fluidInputs(FumingNitricAcid.getFluid(1000))
                .outputs(PureCrystallineNitricAcid.getItemStack(5))
                .buildAndRegister();

        //2 Ke3Ac2Se4At4 + 8 HNO3 + 8 SO2 + NaClO4 ->  8 SeO2 + 8At + [NaCl + 4 H2O + 2 Ke3Ac2S4(NO3)4 + ?]
        BLAST_RECIPES.recipeBuilder().duration(265).EUt(131072).blastFurnaceTemp(10000)
                .input(dust, Triniite, 16)
                .inputs(PureCrystallineNitricAcid.getItemStack(40))
                .inputs(SodiumPerchlorate.getItemStack(6))
                .fluidInputs(SulfurDioxide.getFluid(8000))
                .output(dust, Astatine, 8)
                .outputs(SeleniumOxide.getItemStack(24))
                .fluidOutputs(NitratedTriniiteSolution.getFluid(4000))
                .buildAndRegister();

        //1/2[NaCl + 4 H2O + 2 Ke3Ac2S4(NO3)4 + ?] + 12 NaOH -> 4 Na2S + Ke3Ac2(OH)12? + [0.5 NaCl + 4 NaNO3 + 2 H2O + ?]
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(500)
                .input(dust, SodiumHydroxide, 36)
                .fluidInputs(NitratedTriniiteSolution.getFluid(2000))
                .outputs(ActiniumTriniumHydroxides.getItemStack(29))
                .output(dust, SodiumSulfide, 12)
                .fluidOutputs(ResidualTriniiteSolution.getFluid(2000))
                .buildAndRegister();

        //0.5 NaCl + 4 NaNO3 + 2 H2O + ? -> 0.5 NaCl + 4 NaNO3 + 0.75 Nq + 0.5 Nq + 0.444 Nq*
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(8100)
                .fluidInputs(ResidualTriniiteSolution.getFluid(2000))
                .output(dust, Salt)
                .output(dust, SodiumNitrate, 20)
                .output(dustSmall, NaquadriaticCompound, 3)
                .output(dustSmall, EnrichedNaquadricCompound, 2)
                .output(dustTiny, NaquadriaticCompound, 4)
                .buildAndRegister();

        // 6 KF + C6H6 + 6 Cl -> C6F6 + 6 KCl + 6 H
        CHEMICAL_RECIPES.recipeBuilder().duration(185).EUt(125)
                .input(dust, PotassiumFluoride, 12)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Benzene.getFluid(1000))
                .notConsumable(dust, Rhenium)
                .fluidOutputs(Perfluorobenzene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .output(dust, RockSalt, 12)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(32768)
                .input(wireFine, CarbonNanotubes)
                .inputs(Fullerene.getItemStack())
                .inputs(Fluorocarborane.getItemStack(125))
                .fluidInputs(Perfluorobenzene.getFluid(2000))
                .output(PROTONATED_FULLERENE_SIEVING_MATRIX.getMetaItem())
                .buildAndRegister();

        //C65H70B55F55 + 6 Ra + 2 Ke3Ac2(OH)12? -> 2 Ra3Ac2(OH)12? + Ke6C65H70B55F55
        LARGE_MIXER_RECIPES.recipeBuilder().duration(210).EUt(262000)
                .input(dust, Radium, 6)
                .inputs(ActiniumTriniumHydroxides.getItemStack(58))
                .inputs(PROTONATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidInputs(Water.getFluid(2000))
                .outputs(SATURATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidOutputs(ActiniumRadiumHydroxideSolution.getFluid(2000))
                .buildAndRegister();

        //Ke6C65H70B55F55 + 13 H2SbF7 + 59 KrF2 -> 59 Kr + 13 SbF3 + 6 KeF4 + 32 C2H2 + CF4 + 55 BF3 + 32 HF
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(131000)
                .inputs(SATURATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidInputs(FluoroantimonicAcid.getFluid(13000))
                .fluidInputs(KryptonDifluoride.getFluid(59000))
                .outputs(AntimonyTrifluoride.getItemStack(52))
                .chancedOutput(OreDictUnifier.get(wireFine, CarbonNanotubes, 6), 9500, 0)
                .fluidOutputs(Krypton.getFluid(59000))
                .fluidOutputs(HeavilyFluorinatedTriniumSolution.getFluid(2000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(350).EUt(32760)
                .fluidInputs(HeavilyFluorinatedTriniumSolution.getFluid(2000))
                .outputs(TriniumTetrafluoride.getItemStack(30))
                .fluidOutputs(Perfluorobenzene.getFluid(2000))
                .fluidOutputs(Acetylene.getFluid(32000))
                .fluidOutputs(BoronFluoride.getFluid(55000))
                .fluidOutputs(CarbonFluoride.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(32000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(160).EUt(32)
                .input(dust, Calcium)
                .fluidInputs(Fluorite.getFluid(432))
                .fluidOutputs(MoltenCalciumSalts.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(2000)
                .inputs(TriniumTetrafluoride.getItemStack(5))
                .fluidInputs(MoltenCalciumSalts.getFluid(1000))
                .output(dust, Trinium)
                .output(dust, Calcium, 2)
                .fluidOutputs(Fluorine.getFluid(7000))
                .buildAndRegister();

        //QoL to hook up the acetylene output into the fullerene chain

        FLUID_HEATER_RECIPES.recipeBuilder().duration(190).EUt(500)
                .fluidInputs(Acetylene.getFluid(3000))
                .notConsumable(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                .fluidOutputs(Benzene.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(500)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Acetylene.getFluid(2000))
                .notConsumable(OreDictUnifier.get(dust, Olivine))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidOutputs(Naphthalene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        //Actinium Side

        //C2H2O + HCl -> C2H3OCl
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(125)
                .fluidInputs(Ethenone.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AcetylChloride.getFluid(1000))
                .buildAndRegister();

        //C2H5OH + C2H2O + 3 HF -> C4H5F3O2 + 6 H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(500)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(AcetylChloride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(3000))
                .fluidOutputs(EthylTrifluoroacetate.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(500)
                .input(dust, Phosphorus, 4)
                .input(dust, Sulfur, 10)
                .outputs(PhosphorousPentasulfide.getItemStack(14))
                .buildAndRegister();

        //P4S10 + 10 C4H6O2 + 10 C2H3OCl -> P4O10 + 10 C6H6SO + 10 [HCl + H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(500)
                .inputs(PhosphorousPentasulfide.getItemStack(7))
                .fluidInputs(Succinaldehyde.getFluid(5000))
                .fluidInputs(AcetylChloride.getFluid(5000))
                .fluidOutputs(Acetothienone.getFluid(5000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(10000))
                .output(dust, PhosphorousPentoxide, 7)
                .buildAndRegister();

        //C2H5ONa + C6H6SO + C4H5F3O2 + HCl -> NaCl + 2 C2H5OH + C8H5F3O2S
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(500)
                .inputs(SodiumEthoxide.getItemStack(9))
                .fluidInputs(EthylTrifluoroacetate.getFluid(1000))
                .fluidInputs(Acetothienone.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(TheonylTrifluoroacetate.getFluid(1000))
                .fluidOutputs(Ethanol.getFluid(2000))
                .output(dust, Salt, 2)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(8100)
                .fluidInputs(ActiniumRadiumHydroxideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(12000))
                .fluidOutputs(ActiniumRadiumNitrateSolution.getFluid(13000))
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(32700)
                .fluidInputs(ActiniumRadiumHydroxideSolution.getFluid(13000))
                .notConsumable(TheonylTrifluoroacetate.getFluid(500))
                .outputs(ActiniumNitrate.getItemStack(26))
                .outputs(RadiumNitrate.getItemStack(27))
                .output(dustSmall, Thorium)
                .output(dustSmall, Protactinium.getMaterial())
                .output(dustSmall, Francium)
                .output(dustSmall, Radium)
                .fluidOutputs(Water.getFluid(13000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(160).EUt(500)
                .inputs(ActiniumNitrate.getItemStack(13))
                .output(dust, Actinium)
                .fluidOutputs(Nitrogen.getFluid(3000))
                .fluidOutputs(Oxygen.getFluid(9000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(160).EUt(500)
                .inputs(RadiumNitrate.getItemStack(9))
                .output(dust, Radium)
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .buildAndRegister();
    }
}
