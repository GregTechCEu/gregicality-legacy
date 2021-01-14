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


public class Dyes {
    public static void init(){
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust,Apatite)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust,Gypsum))
                .outputs(ApatiteAcidicLeach.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust,SodaAsh)
                .inputs(ApatiteAcidicLeach.getItemStack())
                .notConsumable(dust,Bentonite)
                .input(dust,SiliconDioxide)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PhosphorousArsenicSolution.getFluid(3000))
                .outputs(ApatiteSolidResidue.getItemStack())
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HydrochloricAcid.getFluid(5000))
                .inputs(ApatiteSolidResidue.getItemStack())
                .outputs(SiliconChloride.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt))
                .fluidOutputs(IronChloride.getFluid(200))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust,FluoroApatite)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust,Gypsum))
                .outputs(FluoroapatiteAcidicLeach.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust,SodaAsh)
                .inputs(FluoroapatiteAcidicLeach.getItemStack())
                .notConsumable(dust,Bentonite)
                .input(dust,SiliconDioxide)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PhosphorousArsenicSolution.getFluid(3000))
                .outputs(FluoroapatiteSolidResidue.getItemStack())
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(500)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .inputs(FluoroapatiteSolidResidue.getItemStack())
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .fluidOutputs(IronChloride.getFluid(200))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(220).EUt(500)
                .fluidInputs(PhosphorousArsenicSolution.getFluid(10000))
                .input(dust, SodiumSulfide,4)
                .outputs(SodiumArsenate.getItemStack(2))
                .outputs(CadmiumSulfide.getItemStack(2))
                .fluidOutputs(PhosphoricAcid.getFluid(10000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(1100)
                .inputs(SodiumArsenate.getItemStack(2))
                .inputs(SodiumIodide.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, ArsenicTrioxide))
                .outputs(OreDictUnifier.get(dust, Iodine))
                .fluidOutputs(SodiumSulfateSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(240).EUt(300)
                .input(dust,Cadmium)
                .input(dust,Sulfur)
                .outputs(CadmiumSulfide.getItemStack(2))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(CadmiumSulfide.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Sulfur))
                .outputs(OreDictUnifier.get(dust, Cadmium))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(32)
                .input(dust,BrownLimonite,2)
                .input(dustTiny,SiliconDioxide)
                .input(dustTiny,Pyrolusite)
                .outputs(RawSienna.getItemStack(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(32)
                .input(dust,YellowLimonite,2)
                .input(dustTiny,SiliconDioxide)
                .input(dustTiny,Pyrolusite)
                .outputs(RawSienna.getItemStack(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(32)
                .input(dust,BandedIron,2)
                .input(dustTiny,SiliconDioxide)
                .input(dustTiny,Pyrolusite)
                .outputs(RawSienna.getItemStack(2))
                .buildAndRegister();

        ModHandler.addSmeltingRecipe(RawSienna.getItemStack(), BurnedSienna.getItemStack());

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(500)
                .fluidInputs(Mercury.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(DiluteNitricAcid.getFluid(2000))
                .fluidOutputs(MercuryNitrate.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(500)
                .fluidInputs(MercuryNitrate.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(MercuryChloride.getItemStack())
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(720).blastFurnaceTemp(700)
                .inputs(MercuryChloride.getItemStack())
                .input(dust,Iodine)
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .outputs(MercuryIodide.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, RockSalt))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(640)
                .inputs(AmmoniumVanadate.getItemStack())
                .fluidInputs(BismuthNitrateSoluton.getFluid(1000))
                .fluidOutputs(AmmoniaNitrate.getFluid(1000))
                .fluidOutputs(BismuthVanadateSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(600)
                .fluidInputs(BismuthVanadateSolution.getFluid(1000))
                .outputs(BismuthVanadate.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(220).EUt(125)
                .fluidInputs(CopperSulfateSolution.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .input(dust, ArsenicTrioxide)
                .input(dust, SodaAsh)
                .outputs(CopperArsenite.getItemStack(3))
                .buildAndRegister();

        ModHandler.addSmeltingRecipe(CopperArsenite.getItemStack(),ScheelesGreen.getItemStack());

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(500)
                .input(dust, Zincite,4)
                .input(dust, CobaltOxide)
                .outputs(CobaltZincOxide.getItemStack(5))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(500)
                .input(dust, CobaltOxide)
                .inputs(Alumina.getItemStack())
                .outputs(CobaltAluminate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(640)
                .fluidInputs(HydrogenCyanide.getFluid(6000))
                .fluidInputs(IronChloride.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(4000))
                .outputs(PotassiumFerrocyanate.getItemStack())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(720)
                .inputs(PotassiumFerrocyanate.getItemStack(3))
                .fluidInputs(IronChloride.getFluid(4000))
                .outputs(PrussianBlue.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, RockSalt, 12))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(125).blastFurnaceTemp(600)
                .input(dust, Rutile, 18)
                .input(dust, AntimonyTrioxide)
                .input(dust, Garnierite)
                .outputs(TitaniumYellow.getItemStack(20))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(500).blastFurnaceTemp(600)
                .input(dust, Pyrolusite)
                .input(dust, Zinc)
                .outputs(OreDictUnifier.get(dust, Zincite))
                .outputs(ManganeseIIIOxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .inputs(ManganeseIIIOxide.getItemStack())
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(PhosphoricAcid.getFluid(2000))
                .outputs(AmmoniaManganesePhosphate.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(500).blastFurnaceTemp(1000)
                .input(dust, Malachite)
                .inputs(BariumCarbonate.getItemStack(2))
                .input(dust, SiliconDioxide,8)
                .outputs(HanPurple.getItemStack(4))
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(280).EUt(500)
                .input(dust, LeadNitrate)
                .input(dust, Potassiumdichromate)
                .outputs(ChromeYellow.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(500)
                .inputs(ChromeYellow.getItemStack(2))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .outputs(ChromeOrange.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1350)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(NitrationMixture.getFluid(1000))
                .fluidOutputs(Nitrotoluene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1240)
                .notConsumable(dust, Iron)
                .fluidInputs(Nitrotoluene.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .inputs(SodiumHypochlorite.getItemStack())
                .outputs(OreDictUnifier.get(dust, Sodium))
                .outputs(DiaminostibenedisulfonicAcid.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1400)
                .fluidInputs(NitroBenzene.getFluid(1000))
                .fluidInputs(Aniline.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .notConsumable(dust, Copper)
                .outputs(Nigrosin.getItemStack(4))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1800)
                .input(dust, SodiumHydroxide)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(SodiumSulfanilate.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(1400)
                .input(dust, SodiumHydroxide)
                .notConsumable(ZincChloride.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Naphtalene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Naphtaleneamine.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 2))
                .fluidOutputs(Naphtaleneamine.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .inputs(SodiumSulfanilate.getItemStack())
                .fluidInputs(Naphtaleneamine.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(DirectBrown.getItemStack(2))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1600)
                .input(dust, SodiumHydroxide)
                .inputs(SuccinicAcid.getItemStack())
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, SodiumSulfate, 2))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(AminoterephtalicAcid.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(1200)
                .inputs(AminoterephtalicAcid.getItemStack())
                .outputs(Quinacridone.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(1350)
                .fluidInputs(Acetone.getFluid(2000))
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(CarbonSulfide.getFluid(10))
                .fluidOutputs(Acetoacetanilide.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .fluidInputs(Acetoacetanilide.getFluid(1000))
                .fluidInputs(Dichlorobenzidine.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(DiarylideYellow.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(950)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Toluenesulfonate.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1400)
                .fluidInputs(Hydroquinone.getFluid(1000))
                .input(dust, PhthalicAnhydride)
                .fluidInputs(Toluenesulfonate.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Diaminoanthraquinone.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .fluidInputs(Diaminoanthraquinone.getFluid(1000))
                .fluidInputs(Toluenesulfonate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .inputs(SodiumNitrite.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 2))
                .outputs(AlizarineCyanineGreen.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1800)
                .input(dust, PhthalicAnhydride)
                .fluidInputs(Benzene.getFluid(1000))
                .outputs(Anthraquinone.getItemStack())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(200).EUt(1600)
                .fluidInputs(SulfuricAcid.getFluid(10))
                .fluidInputs(Anthracene.getFluid(1000))
                .input(dust,Potassiumdichromate)
                .outputs(ChromiumOxide.getItemStack())
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
                .outputs(IndathroneBlue.getItemStack())
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
                .outputs(Mauvine.getItemStack(2))
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
                .inputs(SuccinicAcid.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1400)
                .inputs(SuccinicAcid.getItemStack())
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
                .input(dust, PhthalicAnhydride)
                .fluidInputs(Resorcinol.getFluid(1000))
                .outputs(Fluorescein.getItemStack())
                .notConsumable(ZincChloride.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(750)
                .input(dust, Iodine)
                .inputs(Fluorescein.getItemStack())
                .outputs(Erythrosine.getItemStack())
                .buildAndRegister();

        ItemStack[][] color_dyes = {{OreDictUnifier.get(dust,Barite),OreDictUnifier.get(dust,Rutile),OreDictUnifier.get(dust,LeadNitrate),DiaminostibenedisulfonicAcid.getItemStack()},
                {OreDictUnifier.get(dust,Carbon),OreDictUnifier.get(dust,Pyrolusite),Nigrosin.getItemStack()},
                {RawSienna.getItemStack(),DirectBrown.getItemStack()},
                {BurnedSienna.getItemStack(),MercuryIodide.getItemStack(),OreDictUnifier.get(dust,Cinnabar),Quinacridone.getItemStack()},
                {CadmiumSulfide.getItemStack(),BismuthVanadate.getItemStack(),TitaniumYellow.getItemStack(),ChromeYellow.getItemStack(),DiarylideYellow.getItemStack()},
                {OreDictUnifier.get(dust,Malachite),ScheelesGreen.getItemStack(),CobaltZincOxide.getItemStack(),AlizarineCyanineGreen.getItemStack()},
                {CobaltAluminate.getItemStack(),PrussianBlue.getItemStack(),IndathroneBlue.getItemStack(),Indigo.getItemStack()},
                {AmmoniaManganesePhosphate.getItemStack(),HanPurple.getItemStack(),Mauvine.getItemStack()},
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
