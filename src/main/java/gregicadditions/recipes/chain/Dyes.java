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
import static gregtech.common.items.MetaItems.SHAPE_MOLD_PLATE;

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

        // Ca5(PO4)3Cl + H2SO4 -> 5 CaS(H2O)2O4 + [3 H3PO4 + HCl + ? + ?]
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, Apatite, 9)
                .fluidInputs(SulfuricAcid.getFluid(5000))
                .output(dust, Gypsum, 40)
                .fluidOutputs(ApatiteAcidicLeach.getFluid(4000))
                .buildAndRegister();

        // 3 [3 H3PO4 + HCl] + 3 HCl + SiO2 + Na2CO3 -> 9 H3PO4 + [Na2SiCl6 + 0.25 Fe2O3] + CO2 + 3 H2O (voided)
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, SodaAsh, 6)
                .fluidInputs(ApatiteAcidicLeach.getFluid(12000))
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(PhosphorousArsenicSolution.getFluid(3000))
                .outputs(ApatiteSolidResidue.getItemStack(11))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // [Na2SiCl6 + 0.25 Fe2O3] + 1.5 HCl -> SiCl4 + 2 NaCl + 0.5 FeCl3 + 0.75 H2O
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HydrochloricAcid.getFluid(1500))
                .inputs(ApatiteSolidResidue.getItemStack(11))
                .outputs(SiliconChloride.getItemStack(5))
                .output(dust, Salt, 2)
                .fluidOutputs(IronChloride.getFluid(500))
                .fluidOutputs(Water.getFluid(750))
                .buildAndRegister();

        // Ca5(PO4)3Cl + H2SO4 -> 5 CaS(H2O)2O4 + [3 H3PO4 + HCl + ? + ?]
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, FluoroApatite, 9)
                .fluidInputs(SulfuricAcid.getFluid(5000))
                .output(dust, Gypsum, 40)
                .fluidOutputs(FluoroapatiteAcidicLeach.getFluid(4000))
                .buildAndRegister();

        // 3 [3 H3PO4 + HF] + 3 HF + SiO2 + Na2CO3 -> 9 H3PO4? + [Na2SiF6 + 0.25 Fe2O3] + CO2 + 3 H2O (voided)
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust, SodaAsh, 6)
                .fluidInputs(FluoroapatiteAcidicLeach.getFluid(12000))
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(PhosphorousArsenicSolution.getFluid(3000))
                .outputs(FluoroapatiteSolidResidue.getItemStack(11))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // [Na2SiF6 + 0.25 Fe2O3] + 3.5 HCl -> H2SiF6 + 2 NaCl + 0.5 FeCl3 + 0.75 H2O
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HydrochloricAcid.getFluid(3500))
                .inputs(FluoroapatiteSolidResidue.getItemStack(11))
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(IronChloride.getFluid(500))
                .fluidOutputs(Water.getFluid(750))
                .buildAndRegister();

        // 15H3PO4? + 2 Na2S + 2 NaOH -> 2 Na3AsO4 + 2 CdS + 15H3PO4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(500)
                .fluidInputs(PhosphorousArsenicSolution.getFluid(12000))
                .input(dust, SodiumSulfide,6)
                .input(dust, SodiumHydroxide, 6)
                .outputs(SodiumArsenate.getItemStack(16))
                .outputs(CadmiumSulfide.getItemStack(4))
                .fluidOutputs(PhosphoricAcid.getFluid(12000))
                .buildAndRegister();

        // 2Na3AsO4 + 5 H2SO4 + 4 NaI -> As2O3 + 5 Na2SO4(H2O) + 4 I
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(1100)
                .inputs(SodiumArsenate.getItemStack(16))
                .fluidInputs(SulfuricAcid.getFluid(5000))
                .inputs(SodiumIodide.getItemStack(8))
                .outputs(OreDictUnifier.get(dust, ArsenicTrioxide, 5))
                .fluidOutputs(SodiumSulfateSolution.getFluid(5000))
                .output(dust, Iodine, 4)
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

        MACERATOR_RECIPES.recipeBuilder().duration(190).EUt(120)
                .input(dust, Rutile)
                .outputs(FinelyPowderedRutile.getItemStack())
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(310).EUt(30)
                .inputs(FinelyPowderedRutile.getItemStack())
                .notConsumable(SHAPE_MOLD_PLATE)
                .output(plate, Rutile)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(450).EUt(480)
                .inputs(FinelyPowderedRutile.getItemStack(3))
                .input(dust, Carbon)
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(TitaniumTetrachloride.getFluid(1000))
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

        // Hg + 4 HNO3 -> 2 H2O + Hg(NO3)2 + 2 NO2
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(500)
                .fluidInputs(Mercury.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(4000))
                .fluidInputs(NitrogenDioxide.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
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

        // NH4VO3 + [Bi(NO3)3 + H2O] + 2 NH3 + H2O -> 3 NH4NO3 + BiVO4(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(640)
                .inputs(AmmoniumVanadate.getItemStack(9))
                .fluidInputs(BismuthNitrateSoluton.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(AmmoniumNitrate.getFluid(3000))
                .fluidOutputs(BismuthVanadateSolution.getFluid(1000))
                .buildAndRegister();

        // BiVO4(H2O) -> BiVO4 (H2O lost because it is a dehydration step)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(600)
                .fluidInputs(BismuthVanadateSolution.getFluid(1000))
                .outputs(BismuthVanadate.getItemStack(6))
                .buildAndRegister();

        // As2O3 + 2 [CuSO4 + H2O] + 2 [NaOH + H2O] + Na2CO3 -> 2 CuAsHO3 + 2 [Na2SO4 + H2O] + CO2 (2 H2O voided)
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(125)
                .fluidInputs(CopperSulfateSolution.getFluid(2000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(2000))
                .input(dust, ArsenicTrioxide, 5)
                .input(dust, SodaAsh, 6)
                .outputs(CopperArsenite.getItemStack(12))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(2000))
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

        // 6HCN + FeCl2 + 4 KOH -> K4Fe(CN)6 + 4H2O + 2 HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(640)
                .fluidInputs(Iron2Chloride.getFluid(1000))
                .fluidInputs(HydrogenCyanide.getFluid(6000))
                .fluidInputs(PotassiumHydroxide.getFluid(4000))
                .outputs(PotassiumFerrocyanide.getItemStack(17))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // 3 K4Fe(CN)6 + 4 FeCl3 -> Fe7(CN)18 + 12 KCl
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(720)
                .inputs(PotassiumFerrocyanide.getItemStack(51))
                .fluidInputs(IronChloride.getFluid(4000))
                .outputs(PrussianBlue.getItemStack(43))
                .output(dust, RockSalt, 24)
                .buildAndRegister();

        // 20 TiO2 + Sb2O3 + NiO -> NiO·Sb2O3·20TiO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(600)
                .input(dust, Rutile, 60)
                .input(dust, AntimonyTrioxide, 5)
                .input(dust, Garnierite, 2)
                .outputs(TitaniumYellow.getItemStack(67))
                .buildAndRegister();

        // 2 MnO2 + Zn + H2SO4 -> ZnSO4 + Mn2O3 + H2O
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(500)
                .input(dust, Pyrolusite, 6)
                .input(dust, Zinc)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, ZincSulfate, 6)
                .outputs(ManganeseIIIOxide.getItemStack(5))
                .fluidOutputs(Steam.getFluid(1000))
                .buildAndRegister();

        // Mn2O3 + 2NH3 + 4H3PO4 -> 2 NH4MnP2O7 + 5H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .inputs(ManganeseIIIOxide.getItemStack(5))
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(PhosphoricAcid.getFluid(4000))
                .outputs(AmmoniumManganesePhosphate.getItemStack(30))
                .fluidOutputs(Water.getFluid(5000))
                .buildAndRegister();

        // Cu2CH2O5 + 2BaCO3 + 4SiO2 -> 2BaCuSi2O6 + 3CO2 + H2O(lost)
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(500).blastFurnaceTemp(1000)
                .input(dust, Malachite, 10)
                .inputs(BariumCarbonate.getItemStack(10))
                .input(dust, SiliconDioxide,12)
                .outputs(HanPurple.getItemStack(20))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .buildAndRegister();

        // 2Pb(NO3)2 + K2Cr2O7 + H2O -> 2PbCrO4 + 2 KNO3 + 2 HNO3
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(500)
                .input(dust, LeadNitrate, 18)
                .input(dust, PotassiumDichromate, 11)
                .fluidInputs(Water.getFluid(1000))
                .outputs(ChromeYellow.getItemStack(12))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .output(dust, Saltpeter, 10)
                .buildAndRegister();

        // 2PbCrO4 + 2 NaOH -> Pb2CrO5 + [Na2CrO4 + H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(ChromeYellow.getItemStack(12))
                .input(dust, SodiumHydroxide, 6)
                .fluidOutputs(SodiumChromateSolution.getFluid(1000))
                .outputs(ChromeOrange.getItemStack(8))
                .buildAndRegister();

        // C7H8 + [H2SO4 + HNO3] -> C7H7(NO2) + (H2SO4)2(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1350)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Nitrotoluene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .buildAndRegister();

        //2C7H7(NO2) + 2H2SO4 + 2NaClO + 12 H -> C14H14N2O6S2 + 2NaCl + 8 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1240)
                .notConsumable(dust, Zinc)
                .inputs(SodiumHypochlorite.getItemStack(3))
                .fluidInputs(Nitrotoluene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .outputs(DiaminostilbenedisulfonicAcid.getItemStack(19))
                .output(dust, Salt, 2)
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // 4 C6H7N + 2 C6H5NO2 + HCl + 2 H2SO4 + 2 NaOH -> C36H26N5ClNa2S2O6 + 8 H2O + NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1400)
                .fluidInputs(NitroBenzene.getFluid(1000))
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(500))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .notConsumable(dust, Copper)
                .outputs(Nigrosin.getItemStack(39))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(Ammonia.getFluid(500))
                .buildAndRegister();

        // NaOH + H2SO4 + C6H5NH2 -> 2 H2O + C6H6NNaO3S
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1800)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(SodiumSulfanilate.getItemStack(18))
                .buildAndRegister();

        //C10H8 + HNO3 + H2SO4 + 6 H -> C10H9N + H2SO4 + 3 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1400)
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(Naphthalene.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidOutputs(Naphthylamine.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // C6H6NNaO3S + C10H9N + HCl + NaNO2 -> NaCl + C16H12N3NaO3S + 2 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .inputs(SodiumSulfanilate.getItemStack(18))
                .fluidInputs(Naphthylamine.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .fluidOutputs(Water.getFluid(2000))
                .outputs(DirectBrown.getItemStack(36))
                .buildAndRegister();

        // 4 NaOH + 2 H2SO4 + 2 C4H6O4 + 2 C6H5NH2 + O -> 7 H2O + C20H16N2O4 + 2 [Na2SO4 + H2O]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1600)
                .input(dust, SodiumHydroxide, 12)
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .inputs(SuccinicAcid.getItemStack(28))
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(Ethanol)
                .fluidOutputs(Water.getFluid(7000))
                .fluidOutputs(SodiumSulfateSolution.getFluid(2000))
                .outputs(DianilineterephthalicAcid.getItemStack(42))
                .buildAndRegister();

        // 	C20H16N2O4  -> C20H12N2O2 (2H2O lost)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(1200)
                .inputs(DianilineterephthalicAcid.getItemStack(7))
                .outputs(Quinacridone.getItemStack(6))
                .buildAndRegister();

        // 2 C2H2O + C6H5NH2  -> C10H11NO2
        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1350)
                .fluidInputs(Ethenone.getFluid(2000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidOutputs(Acetoacetanilide.getFluid(1000))
                .buildAndRegister();

        // 2 C10H11NO2 + C12H10Cl2N2 + 2 HCl + 2 NaNO2 -> 2NaCl + C32H26Cl2N6O4 + 4H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .fluidInputs(Acetoacetanilide.getFluid(1000))
                .fluidInputs(Dichlorobenzidine.getFluid(500))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack(4))
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(DiarylideYellow.getItemStack(35))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // C7H8 + SO3 + NaCl -> C7H7SO3Na + HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(950)
                .input(dust, Salt, 2)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Toluenesulfonate.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // C6H4(OH)2 + C8H4O3 -> C14H8O4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1400)
                .fluidInputs(Hydroquinone.getFluid(1000))
                .input(dust, PhthalicAnhydride, 15)
                .notConsumable(Toluenesulfonate.getFluid(1))
                .fluidOutputs(Quinizarin.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        //C14H8O4 + 2 C7H9N + 2 H2SO4 + 2 NaOH -> C28H20N2Na2O8S2 + 6 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .fluidInputs(Quinizarin.getFluid(1000))
                .fluidInputs(Toluidine.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .input(dust, SodiumHydroxide, 6)
                .notConsumable(BoricAcid.getFluid(1))
                .notConsumable(TinChloride.getItemStack())
                .outputs(AlizarineCyanineGreen.getItemStack(31))
                .fluidOutputs(Water.getFluid(6000))
                .buildAndRegister();

        //C8H4O3 + C6H6 -> C14H8O2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Benzene.getFluid(1000))
                .outputs(Anthraquinone.getItemStack(24))
                .fluidOutputs(Water.getFluid(1000))
                .notConsumable(AluminiumChloride.getItemStack())
                .buildAndRegister();

        //C14H10 + K2Cr2O7 + H2SO4 -> C14H8O2 + Cr2O3 + K2SO4 + 2 H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(200).EUt(1600)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Anthracene.getFluid(1000))
                .input(dust, PotassiumDichromate, 11)
                .outputs(ChromiumIIIOxide.getItemStack(5))
                .outputs(Anthraquinone.getItemStack(24))
                .outputs(PotassiumSulfate.getItemStack(7))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();


        //C14H8O2 + SO3 + H2O2 + NH3 -> C14H9NO2 + H2SO4 + H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(1250)
                .notConsumable(Mercury.getFluid(10))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .inputs(Anthraquinone.getItemStack(24))
                .outputs(Aminoanthraquinone.getItemStack(26))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(3000))
                .buildAndRegister();

        //2 C14H9NO2 + 2 NaClO -> 2 NaCl + 2 H2O + C28H14N2O4
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(1400).blastFurnaceTemp(700)
                .inputs(Aminoanthraquinone.getItemStack(52))
                .notConsumable(PotassiumHydroxide.getFluid(1))
                .inputs(SodiumHypochlorite.getItemStack(6))
                .output(dust, Salt, 4)
                .outputs(IndanthroneBlue.getItemStack(48))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();


        //C7H7NO2 + 6 H -> C7H9N + 2 H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(1100)
                .fluidInputs(Nitrotoluene.getFluid(1000))
                .notConsumable(SulfuricAcid.getFluid(10))
                .fluidInputs(Hydrogen.getFluid(6000))
                .notConsumable(dust,Palladium)
                .fluidOutputs(Toluidine.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        //C6H7N + 3 C7H9N + 2 KMnO4 + 7 HCl -> C27H25N4Cl + 2 KCl + 8 H2O + 2 MnCl2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(1340)
                .inputs(PotassiumPermanganate.getItemStack(6))
                .fluidInputs(Aniline.getFluid(500))
                .fluidInputs(Toluidine.getFluid(1500))
                .fluidInputs(HydrochloricAcid.getFluid(3500))
                .fluidOutputs(Water.getFluid(4000))
                .output(dust, RockSalt, 2)
                .outputs(CopperChloride.getItemStack(3))
                .outputs(Mauveine.getItemStack(28))
                .buildAndRegister();

        //3 C6H7N + 9 C7H9N + 5 K2Cr2O7 + 3 HCl -> 3 C27H25N4Cl + 10 KOH + 10 H2O + 5 Cr2O3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(1340)
                .notConsumable(dust,Palladium)
                .input(dust, PotassiumDichromate, 11)
                .fluidInputs(Aniline.getFluid(600))
                .fluidInputs(Toluidine.getFluid(1800))
                .fluidInputs(HydrochloricAcid.getFluid(600))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(PotassiumHydroxide.getFluid(2000))
                .outputs(ChromiumIIIOxide.getItemStack(5))
                .outputs(Mauveine.getItemStack(34))
                .buildAndRegister();

        //C4H10 + 7 O -> C4H2O3 + 4 H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(520).EUt(1340)
                .notConsumable(BismuthVanadate.getItemStack())
                .fluidInputs(Butane.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(7000))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(MaleicAnhydride.getFluid(1000))
                .buildAndRegister();

        //C4H2O3 + 2H + H2O -> C4H6O4
        CHEMICAL_RECIPES.recipeBuilder().duration(440).EUt(1320)
                .fluidInputs(MaleicAnhydride.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .notConsumable(dust, RhodiumPlatedPalladium)
                .outputs(SuccinicAcid.getItemStack(14))
                .buildAndRegister();

        // C4H6O4 + 2 C3H8O -> C10H18O4 + 2 H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1400)
                .inputs(SuccinicAcid.getItemStack(14))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .fluidOutputs(Isopropylsuccinate.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        //C7H8 + NH3 + 3 O -> C7H5N + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1700)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(Benzonitrile.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        //2 C7H5N + C10H18O4 -> 2 C3H8O + C18H12N2O2
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1700)
                .fluidInputs(Isopropylsuccinate.getFluid(1000))
                .fluidInputs(Benzonitrile.getFluid(1000))
                .notConsumable(SodiumEthoxide.getItemStack())
                .notConsumable(IsopropylAlcohol.getFluid(10))
                .fluidOutputs(IsopropylAlcohol.getFluid(1000))
                .outputs(Diketopyrrolopyrrole.getItemStack(32))
                .buildAndRegister();

        //2 CH2O + 2 HCN + 2 C6H7N + 2O -> C16H10N2O2 + 2 H2O + 2 NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1100)
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(PotassiumHydroxide.getFluid(10))
                .notConsumable(SodiumAzanide.getItemStack())
                .notConsumable(dust, SodiumHydroxide)
                .outputs(Indigo.getItemStack(15))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .buildAndRegister();

        //C16H10N2O2 + 4 Br -> C16H6Br4N2O2 + 4 H
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(850)
                .inputs(Indigo.getItemStack(30))
                .fluidInputs(Bromine.getFluid(4000))
                .outputs(Tetrabromoindigo.getItemStack(30))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(250)
                .inputs(Indigo.getItemStack())
                .inputs(Tetrabromoindigo.getItemStack())
                .outputs(CyanIndigoDye.getItemStack(2))
                .buildAndRegister();

        //C8H4O3 + 2 C6H6O2 -> 	C20H12O5 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1150)
                .input(dust, PhthalicAnhydride, 15)
                .fluidInputs(Resorcinol.getFluid(2000))
                .outputs(Fluorescein.getItemStack(37))
                .fluidOutputs(Water.getFluid(2000))
                .notConsumable(ZincChloride.getItemStack())
                .buildAndRegister();

        //C20H12O5 + 4 I + 2 NaOH -> C20H6I4Na2O5 + 4 H + 2 H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(750)
                .input(dust, Iodine, 4)
                .input(dust, SodiumHydroxide, 6)
                .inputs(Fluorescein.getItemStack(37))
                .outputs(Erythrosine.getItemStack(37))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        ItemStack[][] color_dyes = {{OreDictUnifier.get(dust,Barite),FinelyPowderedRutile.getItemStack(),OreDictUnifier.get(dust,LeadNitrate), DiaminostilbenedisulfonicAcid.getItemStack()},
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
