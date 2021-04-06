package gregicadditions.recipes.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.type.MarkerMaterial;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

/**
 * This chain is not fully balanced. This chain is so terrible
 * to work on, that I can't imagine any player ever actually
 * making anything in it aside from the bare minimum to progress.
 *
 * Considering the name of the chain, I think that this chain
 * deserves to die. At this point, it probably has a positive loop
 * in it somewhere since I gave up halfway through balancing it.
 * So let that be even further encouragement.
 */
public class Dyes {
    public static void init(){

        /*
         * Unknown compounds key:
         * - ApatiteAcidicLeach: Ca4P3O102Cl TREAT AS 1
         * - FluoroapatiteAcidicLeach: Ca4P3O10F TREAT AS 1
         */

        // Ca5(PO4)3Cl + H2SO4 -> CaS(H2O)2O4 + Ca4P3O102Cl
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, Apatite, 9)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Gypsum, 8))
                .outputs(ApatiteAcidicLeach.getItemStack())
                .buildAndRegister();

        // Na2CO3 + Ca6(PO4)3ClO + SiO2 + H2O -> ? + ??
        MIXER_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, SodaAsh, 6)
                .inputs(ApatiteAcidicLeach.getItemStack())
                .notConsumable(dust, Bentonite)
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PhosphorousArsenicSolution.getFluid(3000))
                .outputs(ApatiteSolidResidue.getItemStack())
                .buildAndRegister();

        // 5HCl + ?? -> SiCl4 + NaCl + 0.2FeCl3
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HydrochloricAcid.getFluid(5000))
                .inputs(ApatiteSolidResidue.getItemStack())
                .outputs(SiliconChloride.getItemStack(5))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .fluidOutputs(IronChloride.getFluid(200))
                .buildAndRegister();

        // Ca5(PO4)3F + H2SO4 -> CaS(H2O)2O4 + Ca4P3O10F
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, FluoroApatite, 9)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Gypsum, 8))
                .outputs(FluoroapatiteAcidicLeach.getItemStack())
                .buildAndRegister();

        // Na2CO3 + Ca4P3O10F + SiO2 + H2O ->
        MIXER_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, SodaAsh, 6)
                .inputs(FluoroapatiteAcidicLeach.getItemStack())
                .notConsumable(dust, Bentonite)
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PhosphorousArsenicSolution.getFluid(3000))
                .outputs(FluoroapatiteSolidResidue.getItemStack())
                .buildAndRegister();

        // 2HCl + ?? -> H2SiF6 + NaCl + 0.2FeCl3
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .inputs(FluoroapatiteSolidResidue.getItemStack())
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .fluidOutputs(IronChloride.getFluid(200))
                .buildAndRegister();

        // ? + 4Na2S -> Na3AsO4 + CdS + 10H3PO4
        CENTRIFUGE_RECIPES.recipeBuilder().duration(220).EUt(500)
                .fluidInputs(PhosphorousArsenicSolution.getFluid(10000))
                .input(dust, SodiumSulfide,4)
                .outputs(SodiumArsenate.getItemStack(16))
                .outputs(CadmiumSulfide.getItemStack(2))
                .fluidOutputs(PhosphoricAcid.getFluid(10000))
                .buildAndRegister();

        // 2Na3AsO4 + 2H2SO4 -> As2O3 + 2Na2SO4(H2O) + H2O + Na2O + 4O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(1100)
                .inputs(SodiumArsenate.getItemStack(16))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, ArsenicTrioxide, 5))
                .outputs(SodiumOxide.getItemStack(3))
                .fluidOutputs(SodiumSulfateSolution.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        // Na2O -> 2Na + O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(240).EUt(30)
                .inputs(SodiumOxide.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, Sodium, 2))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

        // Cd + S -> CdS
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(240).EUt(300)
                .input(dust, Cadmium)
                .input(dust, Sulfur)
                .outputs(CadmiumSulfide.getItemStack(2))
                .buildAndRegister();

        // CdS -> Cd + S
        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(CadmiumSulfide.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .outputs(OreDictUnifier.get(dust, Cadmium))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(32)
                .input(dust, BrownLimonite,2)
                .input(dustTiny, SiliconDioxide)
                .input(dustTiny, Pyrolusite)
                .outputs(RawSienna.getItemStack(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(32)
                .input(dust, YellowLimonite,2)
                .input(dustTiny, SiliconDioxide)
                .input(dustTiny, Pyrolusite)
                .outputs(RawSienna.getItemStack(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(32)
                .input(dust, BandedIron,2)
                .input(dustTiny, SiliconDioxide)
                .input(dustTiny, Pyrolusite)
                .outputs(RawSienna.getItemStack(2))
                .buildAndRegister();

        ModHandler.addSmeltingRecipe(RawSienna.getItemStack(), BurnedSienna.getItemStack());

        // Hg + 2HNO3 + O -> H2O + Hg(NO3)2
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(500)
                .fluidInputs(Mercury.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(MercuryNitrate.getFluid(1000))
                .buildAndRegister();

        // Hg(NO3)2 + 2HCl -> HgCl2 + 2HNO3
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(500)
                .fluidInputs(MercuryNitrate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .outputs(MercuryChloride.getItemStack(3))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .buildAndRegister();

        // HgCl2 + 2I + 2K -> HgI2 + 2KCl
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(720).blastFurnaceTemp(700)
                .inputs(MercuryChloride.getItemStack(3))
                .input(dust, Iodine, 2)
                .input(dust, Potassium, 2)
                .outputs(MercuryIodide.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, RockSalt, 4))
                .buildAndRegister();

        // NH4VO3 + ? + 8H -> NH4NO3 + BiVO4(H2O) + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(640)
                .inputs(AmmoniumVanadate.getItemStack(9))
                .fluidInputs(BismuthNitrateSoluton.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(8000))
                .fluidOutputs(AmmoniumNitrate.getFluid(1000))
                .fluidOutputs(BismuthVanadateSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // BiVO4(H2O) -> BiVO4
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(600)
                .fluidInputs(BismuthVanadateSolution.getFluid(1000))
                .outputs(BismuthVanadate.getItemStack(6))
                .buildAndRegister();

        // ? + H2O + As2O3 + Na2CO3 -> 2AsCuHO3
        MIXER_RECIPES.recipeBuilder().duration(220).EUt(125)
                .fluidInputs(CopperSulfateSolution.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .input(dust, ArsenicTrioxide, 5)
                .input(dust, SodaAsh)
                .outputs(CopperArsenite.getItemStack(5))
                .buildAndRegister();

        ModHandler.addSmeltingRecipe(CopperArsenite.getItemStack(), ScheelesGreen.getItemStack());

        // 4ZnO + CoO -> Zn4CoO5
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(500)
                .input(dust, Zincite,8)
                .input(dust, CobaltOxide, 2)
                .outputs(CobaltZincOxide.getItemStack(10))
                .buildAndRegister();

        // 2CoO + Al2O3 -> Al2Co2O5
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(500)
                .input(dust, CobaltOxide, 4)
                .inputs(Alumina.getItemStack(5))
                .outputs(CobaltAluminate.getItemStack(9))
                .buildAndRegister();

        // 12HCN + 2Fe + 8KOH + C -> 2K4Fe(CN)6(H2O)3 + 4H2O + CO
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(640)
                .input(dust, Iron, 2)
                .input(dust, Carbon)
                .fluidInputs(HydrogenCyanide.getFluid(12000))
                .fluidInputs(PotassiumHydroxide.getFluid(8000))
                .outputs(PotassiumFerrocyanide.getItemStack(40))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(CarbonMonoxde.getFluid(1000))
                .buildAndRegister();

        // 3K4Fe(CN)6(H2O)3 + 4FeCl3 -> Fe7(CN)18 + 12KCl + 9H2O
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(720)
                .inputs(PotassiumFerrocyanide.getItemStack(3))
                .fluidInputs(IronChloride.getFluid(4000))
                .outputs(PrussianBlue.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, RockSalt, 12))
                .buildAndRegister();

        // 20TiO2 + Sb2O3 + NiO -> NiO·Sb2O3·20TiO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(600)
                .input(dust, Rutile, 60)
                .input(dust, AntimonyTrioxide, 5)
                .input(dust, Garnierite, 2)
                .outputs(TitaniumYellow.getItemStack(67))
                .buildAndRegister();

        // 2MnO2 + Zn -> ZnO + Mn2O3
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(600)
                .input(dust, Pyrolusite, 4)
                .input(dust, Zinc)
                .outputs(OreDictUnifier.get(dust, Zincite, 2))
                .outputs(ManganeseIIIOxide.getItemStack(5))
                .buildAndRegister();

        // Mn2O3 + 2NH3 + 4H3PO4 + 6C -> 2NH4MnPO4 + 5H2O + 6CO
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .inputs(ManganeseIIIOxide.getItemStack(5))
                .input(dust, Carbon, 6)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(PhosphoricAcid.getFluid(4000))
                .outputs(AmmoniumManganesePhosphate.getItemStack(22))
                .fluidOutputs(Water.getFluid(5000))
                .fluidOutputs(CarbonMonoxde.getFluid(6000))
                .buildAndRegister();

        // Cu2CH2O5 + 2BaCO3 + 4SiO2 -> 2BaCuSi2O6 + 3CO2 + H2O(lost)
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(500).blastFurnaceTemp(1000)
                .input(dust, Malachite, 10)
                .inputs(BariumCarbonate.getItemStack(10))
                .input(dust, SiliconDioxide,12)
                .outputs(HanPurple.getItemStack(20))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .buildAndRegister();

        // 2Pb(NO3)2 + K2Cr2O7 -> 2PbCrO4 // Loses 3N, K, O
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(500)
                .input(dust, LeadNitrate, 9)
                .input(dust, Potassiumdichromate, 11)
                .outputs(ChromeYellow.getItemStack(2))
                .buildAndRegister();

        // 2PbCrO4(KNO4) + H2O -> KOH + HNO3 + Pb2Cr2O5
        // is really Pb2CrO5, but nowhere for the chrome to go
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(ChromeYellow.getItemStack(2))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .outputs(ChromeOrange.getItemStack())
                .buildAndRegister();

        // C7H8 + [H2SO4 + HNO3] -> C7H7(NO2) + (H2SO4)2(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1350)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Nitrotoluene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .buildAndRegister();

        // 2C7H7(NO2) + 2H2SO4 + 4NaCl -> C14H14N2O6S2 + 4HClO + 2Na2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1240)
                .notConsumable(dust, Zinc)
                .input(dust, Salt, 8)
                .fluidInputs(Nitrotoluene.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .outputs(DiaminostilbenedisulfonicAcid.getItemStack(38))
                .outputs(SodiumOxide.getItemStack(6))
                .fluidOutputs(HypochlorousAcid.getFluid(4000))
                .buildAndRegister();

        // C7H7(NO2) + 2C6H5NH2 + HCl -> C30H23N5      Need C11, H5, N2
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1400)
                .fluidInputs(NitroBenzene.getFluid(1000))
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .notConsumable(dust, Copper)
                .outputs(Nigrosin.getItemStack(58))
                .buildAndRegister();

        // NaOH + H2SO4 + C6H5NH2 -> 2H2O + C6H6NNaO3S
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1800)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(SodiumSulfanilate.getItemStack(18))
                .buildAndRegister();

        // NaOH + H2SO4 + C10H8 + NH3 -> C10H9N + NaSO4 + 4H
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1400)
                .input(dust, SodiumHydroxide, 3)
                .notConsumable(ZincChloride.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Naphtalene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Naphthylamine.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 6))
                .buildAndRegister();

        // C6H6NNaO3S + C10H9N + HCl + NaNO2 -> NaCl + C26H19N6NaO3S
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .inputs(SodiumSulfanilate.getItemStack())
                .fluidInputs(Naphthylamine.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(DirectBrown.getItemStack(2))
                .buildAndRegister();

        // 2NaOH + C4H6O4 + C2H5OH + C6H5NH2 + H2SO4 -> Na2SO4 + 3H2O + C8H7NO4 + C4H7O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1600)
                .input(dust, SodiumHydroxide, 6)
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 7))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(Butanol.getFluid(1000))
                .outputs(AminoterephthalicAcid.getItemStack(20))
                .buildAndRegister();

        // C8H7NO4 -> C20H12N2O2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(1200)
                .inputs(AminoterephthalicAcid.getItemStack(20))
                .outputs(Quinacridone.getItemStack(10))
                .buildAndRegister();

        // C3H6O + C6H5NH2 + CO2 -> C10H11NO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1350)
                .fluidInputs(Acetone.getFluid(2000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Acetoacetanilide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // C10H11NO2 + C12H10Cl2N2 + HCl + NaNO2 -> 2NaCl + C36H34Cl2N6O4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .fluidInputs(Acetoacetanilide.getFluid(1000))
                .fluidInputs(Dichlorobenzidine.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(DiarylideYellow.getItemStack(2))
                .buildAndRegister();

        // C7H8 + H2SO4 + NaCl -> C7H7SO3Na + (H2O)(HCl)
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(950)
                .input(dust, Salt, 2)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Toluenesulfonate.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // C6H4(OH)2 + C8H4O3 + C7H7SO3Na + NH3 + HCl -> C14H10N2O2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1400)
                .fluidInputs(Hydroquinone.getFluid(1000))
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Toluenesulfonate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Diaminoanthraquinone.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .fluidInputs(Diaminoanthraquinone.getFluid(1000))
                .fluidInputs(Toluenesulfonate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(AlizarineCyanineGreen.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Benzene.getFluid(1000))
                .outputs(Anthraquinone.getItemStack())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(200).EUt(1600)
                .fluidInputs(SulfuricAcid.getFluid(10))
                .fluidInputs(Anthracene.getFluid(1000))
                .input(dust,Potassiumdichromate, 11)
                .output(dust, ChromiumTrioxide, 4)
                .outputs(Anthraquinone.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(1250)
                .fluidInputs(Mercury.getFluid(10))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .inputs(Anthraquinone.getItemStack())
                .outputs(Aminoanthraquinone.getItemStack())
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1400).blastFurnaceTemp(700)
                .inputs(Aminoanthraquinone.getItemStack())
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .notConsumable(dust,RockSalt)
                .outputs(IndanthroneBlue.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1340)
                .notConsumable(dust,Palladium)
                .notConsumable(dust,Potassiumdichromate)
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Nitrotoluene.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .fluidOutputs(Water.getFluid(2500))
                .outputs(Mauveine.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(520).EUt(1340)
                .notConsumable(MolybdenumTrioxide.getItemStack())
                .fluidInputs(Benzene.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .fluidOutputs(MaleicAnhydride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(440).EUt(1320)
                .fluidInputs(MaleicAnhydride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .outputs(SuccinicAcid.getItemStack(14))
                .buildAndRegister();

        // C4H6O4 + C3H8O -> C7H12O4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1400)
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidInputs(IsopropylAlcohol.getFluid(1000))
                .fluidOutputs(Isopropylsuccinate.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1700)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Benzonitrile.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1700)
                .fluidInputs(Isopropylsuccinate.getFluid(1000))
                .fluidInputs(Benzonitrile.getFluid(1000))
                .input(dust,Sodium)
                .fluidOutputs(IsopropylAlcohol.getFluid(1000))
                .outputs(Diketopyrrolopyrrole.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1100)
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .notConsumable(SodiumAzanide.getItemStack())
                .notConsumable(dust, SodiumHydroxide)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(Indigo.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, RockSalt))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(850)
                .inputs(Indigo.getItemStack())
                .fluidInputs(Bromine.getFluid(2000))
                .outputs(Tetrabromoindigo.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(250)
                .inputs(Indigo.getItemStack())
                .inputs(Tetrabromoindigo.getItemStack())
                .outputs(CyanIndigoDye.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1150)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Resorcinol.getFluid(1000))
                .outputs(Fluorescein.getItemStack())
                .notConsumable(ZincChloride.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(750)
                .input(dust, Iodine, 4)
                .inputs(Fluorescein.getItemStack(37))
                .outputs(Erythrosine.getItemStack(37))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        ItemStack[][] color_dyes = {{OreDictUnifier.get(dust,Barite),OreDictUnifier.get(dust,Rutile),OreDictUnifier.get(dust,LeadNitrate), DiaminostilbenedisulfonicAcid.getItemStack()},
                {OreDictUnifier.get(dust,Carbon),OreDictUnifier.get(dust,Pyrolusite),Nigrosin.getItemStack()},
                {RawSienna.getItemStack(),DirectBrown.getItemStack()},
                {BurnedSienna.getItemStack(),MercuryIodide.getItemStack(),OreDictUnifier.get(dust,Cinnabar),Quinacridone.getItemStack()},
                {CadmiumSulfide.getItemStack(),BismuthVanadate.getItemStack(),TitaniumYellow.getItemStack(),ChromeYellow.getItemStack(),DiarylideYellow.getItemStack()},
                {OreDictUnifier.get(dust,Malachite),ScheelesGreen.getItemStack(),CobaltZincOxide.getItemStack(),AlizarineCyanineGreen.getItemStack()},
                {CobaltAluminate.getItemStack(),PrussianBlue.getItemStack(),IndanthroneBlue.getItemStack(),Indigo.getItemStack()},
                {AmmoniumManganesePhosphate.getItemStack(),HanPurple.getItemStack(),Mauveine.getItemStack()},
                {ChromeOrange.getItemStack(),Diketopyrrolopyrrole.getItemStack()},
                {CyanIndigoDye.getItemStack()},{Erythrosine.getItemStack()}};
        MarkerMaterial[] colors = {MarkerMaterials.Color.White,MarkerMaterials.Color.Black,MarkerMaterials.Color.Brown,MarkerMaterials.Color.Red,MarkerMaterials.Color.Yellow,
                MarkerMaterials.Color.Green,MarkerMaterials.Color.Blue,MarkerMaterials.Color.Purple,MarkerMaterials.Color.Orange,MarkerMaterials.Color.Cyan,MarkerMaterials.Color.Pink};

        for (int i = 0; i < color_dyes.length ; i++) {
            for (ItemStack color: color_dyes[i]) {
                OreDictUnifier.registerOre(color, OrePrefix.dye, colors[i]);
            }
        }
    }

}
