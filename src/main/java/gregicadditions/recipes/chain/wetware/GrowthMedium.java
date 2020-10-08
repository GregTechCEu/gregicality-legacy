package gregicadditions.recipes.chain.wetware;

import gregtech.common.items.MetaItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class GrowthMedium {
    public static void init() {
        BLAST_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 10)
                .input(dust, Carbon, 10)
                .fluidOutputs(CarbonMonoxde.getFluid(10000))
                .outputs(CalciumCarbide.getItemStack(10))
                .blastFurnaceTemp(2500)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(CalciumCarbide.getItemStack())
                .fluidInputs(Steam.getFluid(1000))
                .outputs(CalciumHydroxide.getItemStack())
                .fluidOutputs(Acetylene.getFluid(1000))
                .blastFurnaceTemp(2300)
                .EUt(30720)
                .duration(10)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Silver)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Formaldehyde.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Copper)
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidOutputs(PropargylAlcohol.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PropargylAlcohol.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(PropargylChloride.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .inputs(MetaItems.RUBBER_DROP.getStackForm())
                .fluidOutputs(Resin.getFluid(100))
                .EUt(32)
                .duration(100)
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(Resin.getFluid(1000))
                .fluidOutputs(Turpentine.getFluid(200))
                .EUt(480)
                .duration(500)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Turpentine.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .outputs(BetaPinene.getItemStack(2))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(BetaPinene.getItemStack())
                .fluidInputs(Isoprene.getFluid(1000))
                .fluidOutputs(Citral.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Citral.getFluid(1000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(BetaIonone.getFluid(2000))
                .EUt(30720)
                .duration(250)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BetaIonone.getFluid(1000))
                .fluidInputs(PropargylChloride.getFluid(1000))
                .fluidOutputs(VitaminA.getFluid(2000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.APPLE))
                .chancedOutput(Yeast.getItemStack(), 500, 250)
                .EUt(120)
                .duration(50)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Biomass.getFluid(1000))
                .inputs(Yeast.getItemStack())
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .fluidOutputs(LinoleicAcid.getFluid(1000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(EthyleneOxide.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidOutputs(Ethanolamine.getFluid(2000))
                .EUt(30720)
                .duration(150)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(CupriavidusNecator.getItemStack())
                .input(dust, Sugar)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Biotin.getFluid(2000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(Biotin.getFluid(1000))
                .fluidInputs(LinoleicAcid.getFluid(1000))
                .fluidInputs(Catalase.getFluid(1000))
                .fluidOutputs(B27Supplement.getFluid(3000))
                .EUt(30720)
                .duration(250)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CleanAmmoniaSolution.getFluid(2000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(CleanAmmoniaSolution.getFluid(1000))
                .inputs(BrevibacteriumFlavium.getItemStack())
                .input(dust, Sugar)
                .outputs(Glutamine.getItemStack(2))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(Blood.getFluid(1000))
                .fluidOutputs(BloodCells.getFluid(450))
                .fluidOutputs(BloodPlasma.getFluid(550))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BloodPlasma.getFluid(1000))
                .fluidOutputs(Catalase.getFluid(10))
                .fluidOutputs(BFGF.getFluid(10))
                .fluidOutputs(EGF.getFluid(10))
                .EUt(480)
                .duration(50)
                .buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(B27Supplement.getFluid(1000))
                .fluidInputs(AmmoniaNitrate.getFluid(1000))
                .inputs(Glutamine.getItemStack())
                .fluidInputs(BFGF.getFluid(1000))
                .fluidInputs(EGF.getFluid(1000))
                .fluidOutputs(RawGrowthMedium.getFluid(4000))
                .EUt(30720)
                .duration(500)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BloodCells.getFluid(1000))
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BacterialGrowthMedium.getFluid(2000))
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
    }
}
