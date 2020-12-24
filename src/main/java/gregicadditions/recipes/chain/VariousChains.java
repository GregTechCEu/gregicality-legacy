package gregicadditions.recipes.chain;

import gregicadditions.GAEnums;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_BLOCK;


public class VariousChains {
    public static void init(){
        //lignite
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(Water.getFluid(1000),Chlorine.getFluid(1000))
                .input(dust,SodaAsh)
                .outputs(SodiumBicarbonate.getItemStack())
                .fluidOutputs(DichlorineMonoxide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(500)
                .fluidInputs(DichlorineMonoxide.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(500)
                .fluidInputs(Chlorine.getFluid(1000))
                .input(dust,SodiumHydroxide,2)
                .fluidOutputs(Water.getFluid(1000))
                .outputs(OreDictUnifier.get(dust,Salt))
                .outputs(SodiumHypochlorite.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(250)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .input(dust,SodiumHydroxide)
                .fluidOutputs(Water.getFluid(1000))
                .outputs(SodiumHypochlorite.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(250)
                .inputs(SodiumHypochlorite.getItemStack())
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .outputs(OreDictUnifier.get(dust,Salt))
                .fluidOutputs(HypochlorousAcid.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidOutputs(Chloroethanol.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(500)
                .fluidInputs(Chloroethanol.getFluid(1000))
                .fluidInputs(Trimethylamine.getFluid(1000))
                .fluidOutputs(Choline.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(230)
                .input(dust,Lignite)
                .fluidInputs(Choline.getFluid(1000))
                .fluidOutputs(ATL.getFluid(1000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(320).EUt(125)
                .circuitMeta(0)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(HotNitrogen.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(125)
                .input(dust,Lignite,2)
                .fluidInputs(HotNitrogen.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .outputs(DehydratedLignite.getItemStack())
                .fluidOutputs(Steam.getFluid(1000))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().duration(230).EUt(125)
                .inputs(DehydratedLignite.getItemStack())
                .outputs(BCEPellet.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(WetEthyleneOxide.getFluid(1000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(230).EUt(125)
                .circuitMeta(0)
                .fluidInputs(WetEthyleneOxide.getFluid(1000))
                .fluidOutputs(EthyleneGlycol.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(230).EUt(850)
                .fluidInputs(Toluene.getFluid(1000))
                .fluidInputs(Phosgene.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(TolueneDiisocyanate.getFluid(2000))
                .fluidOutputs(DiluteNitricAcid.getFluid(1500))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(850)
                .fluidInputs(TolueneDiisocyanate.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidOutputs(Polyurethane.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(230).EUt(750)
                .fluidInputs(Polyurethane.getFluid(1000))
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .input(dust,Calcite)
                .fluidOutputs(ViscoelasticPolyurethane.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(250).EUt(750)
                .fluidInputs(ViscoelasticPolyurethane.getFluid(1000))
                .fluidInputs(Air.getFluid(1000))
                .fluidOutputs(ViscoelasticPolyurethaneFoam.getFluid(2000))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(200).EUt(820)
                .fluidInputs(ViscoelasticPolyurethaneFoam.getFluid(1000))
                .notConsumable(SHAPE_MOLD_BLOCK)
                .outputs(MEMORY_FOAM_BLOCK.getStackForm())
                .buildAndRegister();

        //not working
//        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(1000)
//                .input(stick,Wood,3)
//                .inputs(MEMORY_FOAM_BLOCK.getStackForm(3))
//                .outputs(new ItemStack(Blocks.BED))
//                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(980000)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .inputs(SMD_DIODE_BIOWARE.getStackForm())
                .input(craftingLens, MarkerMaterials.Color.Magenta)
                .input(wireFine, Gold, 3)
                .outputs(LASER_DIODE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1200000)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .input(wireFine,Gold,4)
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .inputs(LASER_DIODE.getStackForm())
                .input(circuit,MarkerMaterials.Tier.Infinite)
                .outputs(LASER_COOLING_UNIT.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(480).EUt(1000000)
                .fluidInputs(SolderingAlloy.getFluid(432))
                .input(wireGtDouble,UVSuperconductor,2)
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .outputs(MAGNETIC_TRAP.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(380).EUt(1150000)
                .input(GAEnums.GAOrePrefix.plateCurved, Steel, 64)
                .input(GAEnums.GAOrePrefix.plateCurved, Steel, 64)
                .input(plate, Steel, 64)
                .input(plate, Steel, 64)
                .inputs(LASER_COOLING_UNIT.getStackForm())
                .inputs(MAGNETIC_TRAP.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(720))
                .outputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder().duration(280).EUt(90000)
                .inputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .fluidInputs(Rubidium.getFluid(288))
                .outputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

    }
}
