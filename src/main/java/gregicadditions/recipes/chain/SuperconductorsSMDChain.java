package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregicadditions.materials.SimpleDustMaterial;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class SuperconductorsSMDChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(320)
                .input(dust, Copper)
                .input(dust, Indium)
                .input(dust, Gallium)
                .outputs(CopperGalliumIndiumMix.getItemStack(3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(670)
                .fluidInputs(Hydrogen.getFluid(1000))
                .input(dust, Selenium)
                .fluidOutputs(HydroselenicAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(820)
                .fluidInputs(HydroselenicAcid.getFluid(1300))
                .inputs(CopperGalliumIndiumMix.getItemStack())
                .outputs(CopperGalliumIndiumSelenide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(740)
                .input(dust, Pyrolusite)
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .fluidInputs(Air.getFluid(1000))
                .outputs(PotassiumManganate.getItemStack())
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(380).EUt(1200).blastFurnaceTemp(900)
                .inputs(LanthanumOxide.getItemStack())
                .input(dust, Quicklime)
                .inputs(PotassiumManganate.getItemStack())
                .outputs(LanthanumCalciumManganate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Potash, 2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(230).EUt(250)
                .input(dust, Iron)
                .input(dust, Platinum)
                .outputs(IronPlatinumCatalyst.getItemStack(2))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(1300)
                .fluidInputs(Phenol.getFluid(1000))
                .fluidInputs(NitrationMixture.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .notConsumable(IronPlatinumCatalyst.getItemStack())
                .fluidOutputs(DilutedSulfuricAcid.getFluid(2000))
                .fluidOutputs(Aminophenol.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(780)
                .fluidInputs(Aminophenol.getFluid(1000))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(NitroBenzene.getFluid(10))
                .fluidOutputs(Hydroquinoline.getFluid(1500))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(350).EUt(1400)
                .fluidInputs(Hydroquinoline.getFluid(1000))
                .input(dust, Aluminium)
                .outputs(AluminiumComplex.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(1500).blastFurnaceTemp(1250)
                .input(dust, SodiumRuthenate)
                .fluidInputs(Hydrogen.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, RutheniumDioxide))
                .outputs(OreDictUnifier.get(dust, SodiumHydroxide))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(3200)
                .input(dust, Bismuth)
                .fluidInputs(NitricAcid.getFluid(6000))
                .fluidOutputs(BismuthNitrateSoluton.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(3000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1500).blastFurnaceTemp(1250)
                .input(dust, SodiumRuthenate)
                .fluidInputs(BismuthNitrateSoluton.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, BismuthRuthenate))
                .outputs(OreDictUnifier.get(dust, SodiumNitrate))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(300).EUt(3500).blastFurnaceTemp(1900)
                .input(dust, IridiumDioxide)
                .fluidInputs(BismuthNitrateSoluton.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, BismuthIridiate))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(500)
                .input(dust, Barium)
                .fluidInputs(Chlorine.getFluid(1000))
                .outputs(BariumChloride.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(230).EUt(250)
                .inputs(BariumChloride.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BariumChlorideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(4500)
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidInputs(BariumChlorideSolution.getFluid(1000))
                .fluidOutputs(BariumTitanatePreparation.getFluid(3000))
                .fluidOutputs(SodiumChlorideSolution.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(290).EUt(2300)
                .fluidInputs(BariumTitanatePreparation.getFluid(1500))
                .outputs(OreDictUnifier.get(dust, BariumTitanate))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(230).EUt(4960)
                .input(dust, Phosphorus)
                .input(dust, Sulfur)
                .inputs(SuccinicAcid.getItemStack())
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(Perbromothiophene.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(3200)
                .notConsumable(dust, Zinc)
                .notConsumable(dust, SodiumHydroxide)
                .notConsumable(new IntCircuitIngredient(10))
                .fluidInputs(Perbromothiophene.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Dimetoxythiophene.getFluid(2000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(2000)
                .fluidInputs(Toluene.getFluid(10))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidInputs(Dimetoxythiophene.getFluid(1000))
                .fluidOutputs(EDOT.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(3200)
                .fluidInputs(Polystyrene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(EDOT.getFluid(1000))
                .fluidInputs(SodiumPersulfate.getFluid(10))
                .notConsumable(IronSulfateDust.getItemStack())
                .outputs(OreDictUnifier.get(dust, PEDOT, 3))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(500)
                .input(dust, Iron)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(IronSulfateDust.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1000)
                .inputs(ZirconiumTetrachloride.getItemStack())
                .fluidInputs(Water.getFluid(2000))
                .outputs(ZirconylChloride.getItemStack())
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(8000).duration(250)
                .input(dust, Lead)
                .fluidInputs(NitrogenTetroxide.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, LeadNitrate, 3))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1700).blastFurnaceTemp(1600)
                .inputs(ZirconylChloride.getItemStack())
                .input(dust, Rutile)
                .input(dust, LeadNitrate)
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, LeadZirconateTitanate))
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().duration(560).EUt(2000)
                .input(dust, LeadZirconateTitanate, 4)
                .fluidInputs(DistilledWater.getFluid(2000))
                .outputs(OreDictUnifier.get(gemExquisite, LeadZirconateTitanate))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(430).EUt(30720)
                .input(gemExquisite, LeadZirconateTitanate, 2)
                .input(wireFine, Gold)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(500).EUt(1900).blastFurnaceTemp(2400)
                .input(dust, Tungsten, 9)
                .input(GAEnums.GAOrePrefix.oxide, Thorium)
                .outputs(OreDictUnifier.get(ingot, ThoriumDopedTungsten, 10))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(800).blastFurnaceTemp(1800)
                .input(dust, Quartzite)
                .inputs(Alumina.getItemStack())
                .notConsumable(SHAPE_MOLD_CYLINDER)
                .outputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(290).EUt(1700)
                .input(dust, SiliconDioxide)
                .inputs(BariumOxide.getItemStack())
                .input(dust, Garnierite)
                .input(dust, SodaAsh)
                .fluidOutputs(WoodsGlass.getFluid(576))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Iron)
                .input(dust, Iodine, 2)
                .outputs(IronIodide.getItemStack())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Thallium)
                .input(dust, Iodine)
                .outputs(ThalliumIodide.getItemStack())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Rubidium)
                .input(dust, Iodine)
                .outputs(RubidiumIodide.getItemStack())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Potassium)
                .input(dust, Iodine)
                .outputs(PotassiumIodide.getItemStack())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Indium)
                .input(dust, Iodine, 3)
                .outputs(IndiumIodide.getItemStack())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Gallium)
                .input(dust, Iodine, 3)
                .outputs(GalliumIodide.getItemStack())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(780)
                .input(dust, Scandium)
                .input(dust, Iodine, 3)
                .outputs(ScandiumIodide.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(680)
                .inputs(IronIodide.getItemStack(5))
                .fluidInputs(CarbonMonoxde.getFluid(20000))
                .notConsumable(block, Copper)
                .outputs(OreDictUnifier.get(dust, Iodine, 5))
                .outputs(OreDictUnifier.get(dust, Iron))
                .fluidOutputs(IronCarbonyl.getFluid(4000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(300).EUt(780)
                .fluidInputs(IronCarbonyl.getFluid(1000))
                .fluidOutputs(PurifiedIronCarbonyl.getFluid(900))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(340).EUt(900)
                .fluidInputs(PurifiedIronCarbonyl.getFluid(1000))
                .outputs(CarbonylPurifiedIron.getItemStack())
                .fluidOutputs(CarbonMonoxde.getFluid(5000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(2000)
                .inputs(SMALL_COIL.getStackForm(4))
                .inputs(CarbonylPurifiedIron.getItemStack())
                .input(wireFine, AnnealedCopper, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(INDUCTOR.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1500)
                .inputs(RESISTOR.getStackForm())
                .inputs(INDUCTOR.getStackForm())
                .input(wireFine, Cupronickel, 2)
                .outputs(BALLAST.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(100).EUt(1920).blastFurnaceTemp(650)
                .input(dust, Vanadium, 2)
                .input(dust, SodaAsh, 5)
                .fluidInputs(Water.getFluid(4000))
                .fluidOutputs(CarbonMonoxde.getFluid(5000))
                .outputs(SodiumMetavanadate.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, SodiumHydroxide, 8))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(1920)
                .inputs(SodiumMetavanadate.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .outputs(SodiumHexavanadate.getItemStack())
                .fluidOutputs(SaltWater.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1920)
                .inputs(SodiumHexavanadate.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .outputs(VanadiumOxide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 4))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(3400).blastFurnaceTemp(1200)
                .input(dust, YttriumOxide)
                .inputs(EuropiumOxide.getItemStack())
                .inputs(VanadiumOxide.getItemStack())
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(YttriumEuropiumVanadate.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, Sodium))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .inputs(StrontiumChloride.getItemStack())
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(StrontiumSulfate.getItemStack())
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(3400).blastFurnaceTemp(1200)
                .inputs(StrontiumOxide.getItemStack())
                .inputs(EuropiumOxide.getItemStack())
                .inputs(Alumina.getItemStack())
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(StrontiumEuropiumAluminate.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, Sodium))
                .buildAndRegister();

        ItemStack[] halides = {ThalliumIodide.getItemStack(), RubidiumIodide.getItemStack(), IndiumIodide.getItemStack(), ScandiumIodide.getItemStack(), GalliumIodide.getItemStack()};
        SimpleDustMaterial[] mixtures = {GreenHalideMix, RedHalideMix, BlueHalideMix, WhiteHalideMix, UVAHalideMix};
        ItemStack[] lamp_cores = {GREEN_LAMP_CORE.getStackForm(), RED_LAMP_CORE.getStackForm(), BLUE_LAMP_CORE.getStackForm(), WHITE_LAMP_CORE.getStackForm(), UVA_LAMP_CORE.getStackForm()};
        MetaItem.MetaValueItem[] halide_lamp = {GREEN_HALIDE_LAMP, RED_HALIDE_LAMP, BLUE_HALIDE_LAMP, WHITE_HALIDE_LAMP, UVA_HALIDE_LAMP};

        for (int i = 0; i < 5; i++) {
            MIXER_RECIPES.recipeBuilder().duration(320).EUt(720)
                    .inputs(halides[i])
                    .inputs(PotassiumIodide.getItemStack())
                    .fluidInputs(Mercury.getFluid(1000))
                    .outputs(mixtures[i].getItemStack(3))
                    .buildAndRegister();

            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(240).EUt(2000)
                    .inputs(mixtures[i].getItemStack())
                    .input(foil, Molybdenum, 2)
                    .input(wireFine, ThoriumDopedTungsten, 4)
                    .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                    .input(plate, CubicZirconia, 2)
                    .fluidInputs(Argon.getFluid(1000))
                    .fluidInputs(SolderingAlloy.getFluid(288))
                    .outputs(lamp_cores[i])
                    .buildAndRegister();

            if (i == 4) {
                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(2000)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .inputs(YttriumEuropiumVanadate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(2000)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .inputs(StrontiumEuropiumAluminate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(410).EUt(6000)
                        .inputs(mixtures[i].getItemStack())
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(500))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .inputs(YttriumEuropiumVanadate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(410).EUt(6000)
                        .inputs(mixtures[i].getItemStack())
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(500))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .inputs(StrontiumEuropiumAluminate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

            } else {
                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(2000)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .inputs(YttriumEuropiumVanadate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(2000)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .inputs(StrontiumEuropiumAluminate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(410).EUt(6000)
                        .inputs(mixtures[i].getItemStack())
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(500))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .inputs(YttriumEuropiumVanadate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(410).EUt(6000)
                        .inputs(mixtures[i].getItemStack())
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(500))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .inputs(StrontiumEuropiumAluminate.getItemStack())
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

            }
        }

        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(740)
                .notConsumable(dust, Iodine)
                .fluidInputs(Fluorine.getFluid(3000))
                .fluidInputs(CarbonSulfide.getFluid(2000))
                .fluidOutputs(Biperfluoromethanedisulfide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(960)
                .fluidInputs(Mercury.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(Biperfluoromethanedisulfide.getFluid(1000))
                .inputs(BariumCarbonate.getItemStack())
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidOutputs(BariumTriflateSolution.getFluid(3000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(320).EUt(1200)
                .fluidInputs(BariumTriflateSolution.getFluid(3000))
                .outputs(BariumTriflate.getItemStack())
                .fluidOutputs(Mercury.getFluid(1000))
                .fluidOutputs(Water.getFluid(10000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1100)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .input(dust, Scandium)
                .inputs(BariumTriflate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Barite))
                .outputs(ScandiumTriflate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(8000)
                .fluidInputs(NitricAcid.getFluid(1000))
                .inputs(BariumSulfide.getItemStack())
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .outputs(BariumNitrate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(8000)
                .fluidInputs(NitricAcid.getFluid(2000))
                .input(dust, Copper)
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(CopperNitrate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(8000)
                .fluidInputs(NitricAcid.getFluid(1000))
                .input(dust, YttriumOxide)
                .fluidOutputs(Water.getFluid(1000))
                .outputs(YttriumNitrate.getItemStack())
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(6200)
                .fluidInputs(HydrogenCyanide.getFluid(2000))
                .inputs(PotassiumCyanide.getItemStack())
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(4000))
                .notConsumable(dust, Potassiumdichromate)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidOutputs(CitricAcid.getFluid(4000))
                .fluidOutputs(AmmoniumChloride.getFluid(3000))
                .outputs(OreDictUnifier.get(dust, RockSalt))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(300).EUt(6400)
                .inputs(CopperNitrate.getItemStack(3))
                .inputs(BariumNitrate.getItemStack(2))
                .inputs(YttriumNitrate.getItemStack())
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(CitricAcid.getFluid(4000))
                .outputs(WellMixedYBCOxides.getItemStack(6))
                .fluidOutputs(GasMixture.getFluid(6000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(3200)
                .fluidInputs(GasMixture.getFluid(16000))
                .fluidOutputs(CarbonDioxide.getFluid(5500))
                .fluidOutputs(Oxygen.getFluid(2500))
                .fluidOutputs(Nitrogen.getFluid(3500))
                .fluidOutputs(NitrogenDioxide.getFluid(4500))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(750).EUt(8000).blastFurnaceTemp(4500)
                .inputs(WellMixedYBCOxides.getItemStack(6))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(OreDictUnifier.get(ingotHot, YttriumBariumCuprate, 7))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(360).EUt(128000)
                .input(foil, Thallium, 2)
                .input(foil, Barium, 2)
                .input(foil, Copper, 3)
                .input(foil, Calcium, 2)
                .outputs(PiledTBCC.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(104000).blastFurnaceTemp(1800)
                .inputs(PiledTBCC.getItemStack())
                .fluidInputs(Oxygen.getFluid(10000))
                .outputs(TBCCODust.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(20000)
                .notConsumable(VanadiumOxide.getItemStack())
                .fluidInputs(NitricAcid.getFluid(1000))
                .inputs(Glucose.getItemStack())
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(OxalicAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(5600000)
                .fluidInputs(OxalicAcid.getFluid(1000))
                .input(dust, Actinium)
                .outputs(ActiniumOxalate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(CarbonTetrachloride.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(230).EUt(6000000)
                .blastFurnaceTemp(10000)
                .inputs(ActiniumOxalate.getItemStack())
                .inputs(SodiumHydride.getItemStack(2))
                .input(dust, Sodium, 2)
                .fluidInputs(CarbonTetrachloride.getFluid(1000))
                .outputs(ActiniumHydride.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 4))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(260).EUt(7800000)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.NAQUADRIA_CHARGE))
                .inputs(ActiniumHydride.getItemStack(18))
                .fluidInputs(Hydrogen.getFluid(162000))
                .fluidOutputs(ActiniumSuperhydridePlasma.getFluid(18000))
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(ActiniumSuperhydridePlasma.getFluid(1000))
                .outputs(ACTINIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(750000)
                .duration(20)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(340).EUt(8740000)
                .inputs(ACTINIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(LiquidHelium.getFluid(24000))
                .outputs(ActiniumSuperhydride.getItemStack())
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidOutputs(Helium.getFluid(24000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(4800)
                .input(dust, Lanthanum)
                .inputs(UnfoldedFullerene.getItemStack())
                .outputs(LanthanumFullereneMix.getItemStack())
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(320).EUt(125000)
                .inputs(LanthanumFullereneMix.getItemStack())
                .notConsumable(craftingLens, MarkerMaterials.Color.Magenta)
                .outputs(LanthanumEmbeddedFullerene.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(280).EUt(1400000).blastFurnaceTemp(2400)
                .inputs(LanthanumEmbeddedFullerene.getItemStack(1))
                .input(dust, Rubidium, 3)
                .input(dust, Caesium, 3)
                .outputs(FullereneSuperconductiveDust.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(310).EUt(840)
                .fluidInputs(Chloroform.getFluid(3000))
                .input(dust, Silicon)
                .fluidOutputs(Trimethylchlorosilane.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(6000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(360).EUt(8000)
                .fluidInputs(FormicAcid.getFluid(2000))
                .fluidInputs(Bromine.getFluid(2000))
                .input(dust, Sodium)
                .fluidInputs(Trimethylchlorosilane.getFluid(20))
                .fluidOutputs(Bromoacrolein.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(210).EUt(6400)
                .blastFurnaceTemp(4500)
                .input(dust, SodiumHydroxide, 3)
                .input(dust, Sulfur, 3)
                .outputs(OreDictUnifier.get(dust, SodiumSulfide))
                .outputs(SodiumThiosulfate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(3200)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(Ethane.getFluid(1000))
                .fluidOutputs(Chloroethane.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(10000)
                .fluidInputs(Chloroethane.getFluid(1000))
                .inputs(SodiumThiosulfate.getItemStack())
                .fluidInputs(Bromoacrolein.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Salt))
                .fluidOutputs(Bromohydrothiine.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(970000)
                .fluidInputs(Bromohydrothiine.getFluid(1000))
                .fluidInputs(ButhylLithium.getFluid(2000))
                .input(dust, Selenium, 2)
                .fluidOutputs(Bromobutane.getFluid(2000))
                .fluidOutputs(Lithiumthiinediselenide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(260).EUt(840000)
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(Butadiene.getFluid(1000))
                .fluidInputs(Ethylene.getFluid(1000))
                .notConsumable(ScandiumTriflate.getItemStack())
                .fluidOutputs(Chlorine.getFluid(2000))
                .outputs(TitaniumCyclopentanyl.getItemStack(2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(720000).blastFurnaceTemp(3500)
                .fluidInputs(Lithiumthiinediselenide.getFluid(1000))
                .inputs(TitaniumCyclopentanyl.getItemStack(1))
                .outputs(OreDictUnifier.get(dust, Lithium, 2))
                .outputs(BETS.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(250).EUt(1300000).blastFurnaceTemp(5000)
                .inputs(BETS.getItemStack())
                .fluidInputs(AmmoniumPerrhenate.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .outputs(BETSPerrhenate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(4000)
                .fluidInputs(Bromobutane.getFluid(1000))
                .input(dust, SodiumHydroxide)
                .outputs(SodiumBromide.getItemStack())
                .fluidOutputs(Butene.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(260).EUt(500)
                .inputs(SodiumBromide.getItemStack())
                .outputs(OreDictUnifier.get(dust, Sodium))
                .fluidOutputs(Bromine.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(740000)
                .input(dust, Francium)
                .fluidInputs(Acetylene.getFluid(1000))
                .outputs(FranciumCarbide.getItemStack())
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(84000).blastFurnaceTemp(4000)
                .input(dust, Boron, 3)
                .input(dust, Carbon, 4)
                .outputs(BoronCarbide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(980000)
                .inputs(FranciumCarbide.getItemStack())
                .inputs(BoronCarbide.getItemStack())
                .outputs(BoronFranciumCarbide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(640000)
                .input(dust, Astatine)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(AstatideSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(380).EUt(2000000)
                .fluidInputs(AstatideSolution.getFluid(4000))
                .input(dust, Holmium)
                .input(dust, Thulium)
                .input(dust, Copernicium)
                .input(dust, MetastableFlerovium)
                .fluidOutputs(SulfuricAcid.getFluid(4000))
                .outputs(MixedAstatideSalts.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(400).EUt(1300000).blastFurnaceTemp(10000)
                .inputs(BoronFranciumCarbide.getItemStack(2))
                .inputs(FranciumCarbide.getItemStack(2))
                .inputs(MixedAstatideSalts.getItemStack(2))
                .fluidInputs(Water.getFluid(2000))
                .outputs(BorocarbideDust.getItemStack())
                .fluidOutputs(FranciumAstatideSolution.getFluid(2000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(320).EUt(84000)
                .fluidInputs(FranciumAstatideSolution.getFluid(1000))
                .outputs(OreDictUnifier.get(dust, Francium))
                .outputs(OreDictUnifier.get(dust, Astatine))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(260).EUt(4000)
                .input(dust, Iodine, 3)
                .fluidInputs(SodiumHydroxideSolution.getFluid(6000))
                .outputs(SodiumIodate.getItemStack())
                .outputs(SodiumIodide.getItemStack(5))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(290).EUt(4900)
                .fluidInputs(CopperSulfateSolution.getFluid(3000))
                .inputs(SodiumIodide.getItemStack())
                .input(dust, SodiumHydroxide, 6)
                .outputs(SodiumIodate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Copper, 3))
                .fluidOutputs(SodiumSulfateSolution.getFluid(3000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();
        
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(3400)
                .inputs(SodiumIodate.getItemStack())
                .inputs(SodiumHypochlorite.getItemStack())
                .outputs(SodiumPeriodate.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(680000)
                .inputs(SodiumPeriodate.getItemStack(3))
                .input(dust, Ruthenium, 4)
                .fluidInputs(SodiumHydroxideSolution.getFluid(4000))
                .outputs(SodiumIodide.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, SodiumRuthenate, 4))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(720000)
                .input(dust, Seaborgium)
                .input(dust, SodiumHydroxide, 2)
                .fluidInputs(Fluorine.getFluid(3000))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .outputs(SodiumSeaborgate.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(1920)
                .input(dust, Strontium)
                .fluidInputs(Chlorine.getFluid(2000))
                .outputs(StrontiumChloride.getItemStack(3))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(360).EUt(810000).blastFurnaceTemp(4500)
                .input(dust, SodiumRuthenate)
                .inputs(SodiumSeaborgate.getItemStack())
                .inputs(StrontiumChloride.getItemStack(3))
                .outputs(OreDictUnifier.get(dust, Salt, 4))
                .outputs(StrontiumSuperconductorDust.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(520).EUt(4800).blastFurnaceTemp(1000)
                .input(dust, Osmium)
                .fluidInputs(Oxygen.getFluid(4000))
                .outputs(OsmiumTetroxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(6000)
                .notConsumable(OsmiumTetroxide.getItemStack())
                .inputs(SodiumPeriodate.getItemStack(1))
                .outputs(SodiumIodate.getItemStack(1))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(Formaldehyde.getFluid(1000))
                .fluidOutputs(Acetaldehyde.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(5600)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidInputs(Butane.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(Bromobutane.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .buildAndRegister();
        
                BLAST_RECIPES.recipeBuilder().duration(280).EUt(5000).blastFurnaceTemp(700)
                .notConsumable(dust, Salt)
                .input(dust, Iridium)
                .fluidInputs(Oxygen.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, IridiumDioxide))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(7200)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidInputs(Krypton.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(KryptonDifluoride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(4900)
                .input(dust, Manganese)
                .fluidInputs(KryptonDifluoride.getFluid(2000))
                .outputs(ManganeseFluoride.getItemStack())
                .fluidOutputs(Krypton.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(5200)
                .inputs(ManganeseFluoride.getItemStack())
                .fluidInputs(Water.getFluid(2000))
                .outputs(OreDictUnifier.get(dust, Pyrolusite))
                .fluidOutputs(HydrofluoricAcid.getFluid(4000))
                .buildAndRegister();
        
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(4400)
                .input(dust, Lanthanum)
                .fluidInputs(Oxygen.getFluid(1500))
                .outputs(LanthanumOxide.getItemStack())
                .buildAndRegister();


    }
}
