package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK;
import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;


public class VariousChains {
    public static void init() {

        // Various Sodium Hypochlorite Recipes
        // 2NaOH + 2Cl -> H2O + NaCl + NaClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(480)
                .fluidInputs(Chlorine.getFluid(2000))
                .input(dust, SodiumHydroxide, 6)
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, Salt, 2)
                .outputs(SodiumHypochlorite.getItemStack(3))
                .buildAndRegister();

        // HClO + NaOH -> H2O + NaClO
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(256)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidOutputs(Water.getFluid(1000))
                .outputs(SodiumHypochlorite.getItemStack(3))
                .buildAndRegister();

        // NaClO + HCl -> NaCl + HClO
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(256)
                .inputs(SodiumHypochlorite.getItemStack(3))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .buildAndRegister();

        // ATL
        // HClO + C2H4 -> C2H5ClO
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidOutputs(Chloroethanol.getFluid(1000))
                .buildAndRegister();

        // C2H5ClO + C3H9N + Na -> C5H14NO + NaCl
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(480)
                .input(dust, Sodium)
                .fluidInputs(Chloroethanol.getFluid(1000))
                .fluidInputs(Trimethylamine.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(Choline.getFluid(1000))
                .buildAndRegister();

        // C2(H2O)4C + C5H14NO -> ATL
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(256)
                .input(dust, Lignite)
                .fluidInputs(Choline.getFluid(1000))
                .fluidOutputs(ATL.getFluid(1000))
                .buildAndRegister();

        // Lignite Processing
        FLUID_HEATER_RECIPES.recipeBuilder().duration(320).EUt(120)
                .circuitMeta(0)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(HotNitrogen.getFluid(1000))
                .buildAndRegister();

        // C2(H2O)4C + N(hot) -> N + 3C + 4H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(120)
                .input(dust, Lignite)
                .fluidInputs(HotNitrogen.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .outputs(DehydratedLignite.getItemStack(3))
                .fluidOutputs(Steam.getFluid(4000))
                .buildAndRegister();

        // TODO: Make BCE Pellet a furnace fuel with same duration as coal
        COMPRESSOR_RECIPES.recipeBuilder().duration(230).EUt(64)
                .inputs(DehydratedLignite.getItemStack(3))
                .outputs(BCEPellet.getItemStack())
                .buildAndRegister();

        // Polyurethane (Memory Foam)
        // C2H4O + H2O -> [C2H4O + H2O]
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(WetEthyleneOxide.getFluid(1000))
                .buildAndRegister();

        // [C2H4O + H2O] -> C2H6O2
        FLUID_HEATER_RECIPES.recipeBuilder().duration(230).EUt(120)
                .circuitMeta(0)
                .fluidInputs(WetEthyleneOxide.getFluid(1000))
                .fluidOutputs(EthyleneGlycol.getFluid(1000))
                .buildAndRegister();

        // C7H8 + 2COCl2 + 2HNO3 -> C9H6N2O2 + 4HClO + 2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(960)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Phosgene.getFluid(2000))
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidOutputs(TolueneDiisocyanate.getFluid(2000))
                .fluidOutputs(HypochlorousAcid.getFluid(4000))
                .fluidOutputs(Oxygen.getFluid(2000))
                .buildAndRegister();

        // C9H6N2O2 + 4C2H6O2 + O -> C17H16N2O4 + 7H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(960)
                .fluidInputs(TolueneDiisocyanate.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(4000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Polyurethane.getFluid(1000))
                .fluidOutputs(Water.getFluid(7000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(230).EUt(720)
                .fluidInputs(Polyurethane.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .input(dust, Calcite, 5)
                .fluidOutputs(ViscoelasticPolyurethane.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(250).EUt(720)
                .fluidInputs(ViscoelasticPolyurethane.getFluid(1000))
                .fluidInputs(Air.getFluid(1000))
                .fluidOutputs(ViscoelasticPolyurethaneFoam.getFluid(2000))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(200).EUt(960)
                .fluidInputs(ViscoelasticPolyurethaneFoam.getFluid(1000))
                .notConsumable(SHAPE_MOLD_BLOCK)
                .outputs(MEMORY_FOAM_BLOCK.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(1000)
                .input(stick, Wood, 3)
                .inputs(MEMORY_FOAM_BLOCK.getStackForm(3))
                .outputs(new ItemStack(Items.BED))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(980000)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .inputs(SMD_DIODE_BIOWARE.getStackForm())
                .input(craftingLens, MarkerMaterials.Color.Magenta)
                .input(wireFine, Gold, 3)
                .outputs(LASER_DIODE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1200000)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .input(wireFine, Gold, 4)
                .input(valueOf("gtMetalCasing"), Aluminium)
                .inputs(LASER_DIODE.getStackForm())
                .input(circuit, MarkerMaterials.Tier.Ultimate)
                .outputs(LASER_COOLING_UNIT.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(480).EUt(1000000)
                .fluidInputs(SolderingAlloy.getFluid(432))
                .input(wireGtDouble, UVSuperconductor, 2)
                .input(valueOf("gtMetalCasing"), Aluminium)
                .outputs(MAGNETIC_TRAP.getStackForm())
                .buildAndRegister();

        OrePrefix plateB = plate;
        if (GAConfig.GT6.addCurvedPlates) {
            plateB = plateCurved;
        }

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(380).EUt(1150000)
                .input(plateB, Steel, 64)
                .input(plateB, Steel, 64)
                .input(plate, Steel, 64)
                .input(plate, Steel, 64)
                .inputs(LASER_COOLING_UNIT.getStackForm())
                .inputs(MAGNETIC_TRAP.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder().duration(280).EUt(90000)
                .inputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .fluidInputs(Rubidium.getFluid(288))
                .outputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(BariumSulfateSolution.getFluid(1000))
                .fluidInputs(CalciumCarbonateSolution.getFluid(1000))
                .fluidInputs(BentoniteClaySlurry.getFluid(1000))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(ATL.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidOutputs(DrillingMud.getFluid(6000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .fluidInputs(BariumSulfateSolution.getFluid(1000))
                .fluidInputs(CalciumCarbonateSolution.getFluid(1000))
                .fluidOutputs(CaCBaSMixture.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .fluidInputs(BentoniteClaySlurry.getFluid(1000))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidOutputs(LubricantClaySlurry.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .fluidInputs(ATL.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidOutputs(ATLEthylene.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .fluidInputs(CaCBaSMixture.getFluid(2000))
                .fluidInputs(LubricantClaySlurry.getFluid(2000))
                .fluidOutputs(DrillingMudMixture.getFluid(4000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .fluidInputs(DrillingMudMixture.getFluid(4000))
                .fluidInputs(ATLEthylene.getFluid(2000))
                .fluidOutputs(DrillingMud.getFluid(6000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(120).EUt(480)
                .fluidInputs(UsedDrillingMud.getFluid(1000))
                .fluidOutputs(DrillingMud.getFluid(950))
                .outputs(new ItemStack(Blocks.GRAVEL))
                .buildAndRegister();

        // These are fine (just fixed Iron III Chloride output)
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(28000)
                .inputs(GRAPHENE_IRON_PLATE.getStackForm(2))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(IronChloride.getFluid(2000))
                .output(plate, Graphene)
                .buildAndRegister();

        // These are fine (just fixed Iron III Chloride output)
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(28000)
                .inputs(GRAPHENE_IRON_PLATE.getStackForm(2))
                .fluidInputs(AquaRegia.getFluid(3000))
                .fluidOutputs(IronChloride.getFluid(2000))
                .outputs(GrapheneOxide.getItemStack(3))
                .fluidOutputs(NitrogenDioxide.getFluid(1500))
                .buildAndRegister();
    }
}
