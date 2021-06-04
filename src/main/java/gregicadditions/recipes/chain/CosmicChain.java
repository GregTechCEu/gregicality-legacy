package gregicadditions.recipes.chain;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAExplosive;
import gregtech.api.unification.material.MarkerMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CosmicChain { // TODO

    public static void init() {

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(60).EUt(14000000)
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.LEPTONIC_CHARGE))
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .fluidOutputs(QuarkGluonPlasma.getFluid(2000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .input(wireFine, YttriumBariumCuprate, 64)
                .input(wireFine, NiobiumTitanium, 64)
                .input(plate, NiobiumNitride, 8)
                .input(foil, Polybenzimidazole, 16)
                .input(stickLong, VanadiumGallium)
                .outputs(SEPARATION_ELECTROMAGNET.getStackForm())
                .buildAndRegister();

        LARGE_CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(1200000)
                .fluidInputs(QuarkGluonPlasma.getFluid(1000))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(HeavyQuarks.getFluid(200))
                .fluidOutputs(Gluons.getFluid(200))
                .fluidOutputs(LightQuarks.getFluid(600))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(100).EUt(2400000)
                .fluidInputs(HeavyLeptonMix.getFluid(1000))
                .fluidInputs(HeavyQuarks.getFluid(1000))
                .fluidInputs(Gluons.getFluid(1000))
                .fluidOutputs(CosmicComputingMix.getFluid(3000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(3250000)
                .fluidInputs(HeavyQuarks.getFluid(750))
                .fluidInputs(LightQuarks.getFluid(250))
                .fluidOutputs(HeavyQuarkEnrichedMix.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(130).EUt(450000)
                .fluidInputs(Titanium50.getFluid(144))
                .fluidInputs(Scandium.getFluid(144))
                .fluidOutputs(ScandiumTitanium50Mix.getFluid(288))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(130).EUt(450000)
                .fluidInputs(Radon.getFluid(1000))
                .fluidInputs(Radium.getFluid(144))
                .fluidOutputs(RadonRadiumMix.getFluid(288))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(140).EUt(4500000)
                .fluidInputs(Deuterium.getFluid(2000))
                .fluidInputs(MetastableHassium.getFluid(144))
                .fluidInputs(MetastableFlerovium.getFluid(144))
                .fluidInputs(MetastableOganesson.getFluid(144))
                .fluidOutputs(DeuteriumSuperheavyMix.getFluid(2592))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(1100000).coilTier(3).euStart(18000000000L).euReturn(50)
                .fluidInputs(DeuteriumSuperheavyMix.getFluid(144))
                .fluidInputs(HeavyQuarkEnrichedMix.getFluid(144))
                .fluidOutputs(HeavyQuarkDegenerateMatter.getFluid(144))
                .buildAndRegister();

        // 3HCl + Fl -> FlCl3 + 3H
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(7680)
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .input(dust, MetastableFlerovium)
                .fluidOutputs(Trichloroferane.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .buildAndRegister();

        // C6H5F + 2Na -> C6H5Na + NaF
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(480)
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .input(dust, Sodium, 2)
                .fluidOutputs(Phenylsodium.getFluid(1000))
                .output(dust, SodiumFluoride, 2)
                .buildAndRegister();

        // LiAlH4 + C4H6O4 -> C4H6O2 + Li + Al + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(15360)
                .inputs(LithiumAluminiumHydride.getItemStack(4))
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidOutputs(Succinaldehyde.getFluid(1000))
                .output(dust, Lithium)
                .output(dust, Aluminium)
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2KF + H + C6H4Cl2 + N -> 2KCl + C6H5F2N
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .input(dust, PotassiumFluoride, 4)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Dichlorobenzene.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .notConsumable(AluminiumChloride.getItemStack())
                .output(dust, RockSalt, 4)
                .fluidOutputs(Difluoroaniline.getFluid(1000))
                .buildAndRegister();

        // C6H5F2N + C4H6O2 -> C10H7F2N + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(480)
                .fluidInputs(Difluoroaniline.getFluid(1000))
                .fluidInputs(Succinaldehyde.getFluid(1000))
                .notConsumable(dust, PhosphorousPentoxide)
                .fluidOutputs(NDifluorophenylpyrrole.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2H2O + AgBF4 + 8C6H5Na + 2C8H20NBr + AgClO4 + C5H5Cl3Ti + 8C10H7F2N -> 2AgCl + 2NaBr + 6H2ONaCl + ?
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(30720)
                .fluidInputs(Water.getFluid(2000))
                .fluidInputs(Silvertetrafluoroborate.getFluid(2000))
                .fluidInputs(Phenylsodium.getFluid(8000))
                .fluidInputs(TetraethylammoniumBromide.getFluid(2000))
                .inputs(SilverPerchlorate.getItemStack(6))
                .inputs(TitaniumCyclopentadienyl.getItemStack(14))
                .fluidInputs(NDifluorophenylpyrrole.getFluid(8000))
                .output(dust, SilverChloride, 4)
                .outputs(SodiumBromide.getItemStack(4))
                .fluidOutputs(SaltWater.getFluid(6000))
                .fluidOutputs(PhotopolymerSolution.getFluid(6000))
                .buildAndRegister();

        // 3NaClO -> 2NaCl + NaClO3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(210).EUt(120)
                .inputs(SodiumHypochlorite.getItemStack(9))
                .output(dust, Salt, 4)
                .outputs(SodiumChlorate.getItemStack(5))
                .buildAndRegister();

        // NaClO3 + H2O2 -> NaClO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .inputs(SodiumChlorate.getItemStack(5))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .outputs(SodiumPerchlorate.getItemStack(6))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // Ag2O + 2NaClO4 + HCl -> 2AgClO4 + Na2O + dil.HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(480)
                .input(dust, SilverOxide, 3)
                .inputs(SodiumPerchlorate.getItemStack(12))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(SilverPerchlorate.getItemStack(12))
                .outputs(SodiumOxide.getItemStack(3))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // 2AgCl + H2O -> Ag2O + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, SilverChloride, 4)
                .notConsumable(dust, SodiumHydroxide)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SilverOxide, 3)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // Ag2O + C -> 2Ag + CO
        BLAST_RECIPES.recipeBuilder().duration(80).EUt(120).blastFurnaceTemp(1200)
                .input(dust, SilverOxide, 3)
                .input(dust, Carbon)
                .output(ingot, Silver, 2)
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // Ge + 2S -> GeS2
        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(120)
                .input(dust, Germanium)
                .input(dust, Sulfur, 2)
                .outputs(GermaniumSulfide.getItemStack(3))
                .buildAndRegister();

        // GeS2 + 6O -> GeO2 + 2SO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(2500)
                .inputs(GermaniumSulfide.getItemStack(3))
                .fluidInputs(Oxygen.getFluid(6000))
                .outputs(GermaniumOxide.getItemStack(3))
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        // WC + 4O -> WO3 + CO
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(480)
                .input(dust, TungstenCarbide, 2)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, TungstenTrioxide, 4)
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // CdS + WO3 + 3O -> CdWO4 + SO2
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(120).blastFurnaceTemp(2800)
                .inputs(CadmiumSulfide.getItemStack(2))
                .input(dust, TungstenTrioxide, 4)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(CadmiumTungstate.getItemStack(6))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        // CsI + Tm + Tl -> CsITmTl
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(2853)
                .inputs(CesiumIodide.getItemStack())
                .input(dustSmall, Thulium, 2)
                .input(dustSmall, Thallium, 2)
                .outputs(TlTmCesiumIodide.getItemStack(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(7680)
                .fluidInputs(Anthracene.getFluid(1000))
                .inputs(Tetracene.getItemStack(2))
                .outputs(PolycyclicAromaticMix.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(120)
                .input(dust, Caesium)
                .input(dust, Iodine)
                .outputs(CesiumIodide.getItemStack(2))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(500000)
                .fluidInputs(BismuthNitrateSoluton.getFluid(4000))
                .inputs(GermaniumOxide.getItemStack(3))
                .notConsumable(LOW_FREQUENCY_LASER.getStackForm())
                .outputs(BismuthGermanate.getItemStack(33))
                .output(dust, Potash, 18)
                .fluidOutputs(NitrogenDioxide.getFluid(12000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();
    }
}
