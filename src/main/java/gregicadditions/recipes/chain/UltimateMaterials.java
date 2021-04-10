package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregicadditions.item.fusion.GAFusionCasing;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;

import static gregicadditions.GAEnums.GAOrePrefix.plateCurved;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class UltimateMaterials {
    public static void init() {

        LARGE_MIXER_RECIPES.recipeBuilder().duration(270).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Ruthenium),
                        OreDictUnifier.get(dust, Rhodium),
                        OreDictUnifier.get(dust, Palladium),
                        OreDictUnifier.get(dust, Silver),
                        OreDictUnifier.get(dust, Rhenium),
                        OreDictUnifier.get(dust, Osmium),
                        OreDictUnifier.get(dust, Iridium),
                        OreDictUnifier.get(dust, Platinum),
                        OreDictUnifier.get(dust, Gold))
                .outputs(PreciousMetals.getItemStack(9))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(210).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Zirconium),
                        OreDictUnifier.get(dust, Hafnium),
                        OreDictUnifier.get(dust, Niobium),
                        OreDictUnifier.get(dust, Tantalum),
                        OreDictUnifier.get(dust, Molybdenum),
                        OreDictUnifier.get(dust, Tungsten),
                        OreDictUnifier.get(dust, Technetium))
                .outputs(RefractoryMetals.getItemStack(7))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(240).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Titanium),
                        OreDictUnifier.get(dust, Vanadium),
                        OreDictUnifier.get(dust, Manganese),
                        OreDictUnifier.get(dust, Chrome),
                        OreDictUnifier.get(dust, Iron),
                        OreDictUnifier.get(dust, Nickel),
                        OreDictUnifier.get(dust, Cobalt),
                        OreDictUnifier.get(dust, Copper))
                .outputs(LightTranstionMetals.getItemStack(8))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(420).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Beryllium),
                        OreDictUnifier.get(dust, Magnesium),
                        OreDictUnifier.get(dust, Calcium),
                        OreDictUnifier.get(dust, Strontium),
                        OreDictUnifier.get(dust, Barium),
                        OreDictUnifier.get(dust, Radium),
                        OreDictUnifier.get(dust, Scandium),
                        OreDictUnifier.get(dust, Yttrium))
                .fluidInputs(Lithium.getFluid(144),
                        Sodium.getFluid(144),
                        Potassium.getFluid(144),
                        Rubidium.getFluid(144),
                        Caesium.getFluid(144),
                        Francium.getFluid(144))
                .outputs(Alkalis.getItemStack(14))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(420).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Zinc),
                        OreDictUnifier.get(dust, Cadmium),
                        OreDictUnifier.get(dust, Aluminium),
                        OreDictUnifier.get(dust, Silicon),
                        OreDictUnifier.get(dust, Germanium),
                        OreDictUnifier.get(dust, Antimony),
                        OreDictUnifier.get(dust, Thallium),
                        OreDictUnifier.get(dust, Lead))
                .fluidInputs(Mercury.getFluid(144),
                        Tin.getFluid(144),
                        Gallium.getFluid(144),
                        Indium.getFluid(144),
                        Bismuth.getFluid(144),
                        Polonium.getFluid(144))
                .outputs(PostTransitionMetals.getItemStack(14))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(450).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Samarium),
                        OreDictUnifier.get(dust, Gadolinium),
                        OreDictUnifier.get(dust, Terbium),
                        OreDictUnifier.get(dust, Thulium),
                        OreDictUnifier.get(dust, Holmium),
                        OreDictUnifier.get(dust, Lutetium),
                        OreDictUnifier.get(dust, Scandium),
                        OreDictUnifier.get(dust, Yttrium))
                .fluidInputs(Lanthanum.getFluid(144),
                        Cerium.getFluid(144),
                        Praseodymium.getFluid(144),
                        Neodymium.getFluid(144),
                        Europium.getFluid(144),
                        Ytterbium.getFluid(144))
                .outputs(Lanthanoids.getItemStack(15))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(390).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Actinium),
                        OreDictUnifier.get(dust, Thorium),
                        OreDictUnifier.get(dust, Protactinium.getMaterial()),
                        OreDictUnifier.get(dust, UraniumRadioactive.getMaterial()),
                        OreDictUnifier.get(dust, Americium),
                        OreDictUnifier.get(dust, Curium.getMaterial()),
                        OreDictUnifier.get(dust, Berkelium.getMaterial()),
                        OreDictUnifier.get(dust, Fermium.getMaterial()),
                        OreDictUnifier.get(dust, Californium.getMaterial()))
                .fluidInputs(Neptunium.getMaterial().getFluid(144),
                        Plutonium.getFluid(144),
                        Mendelevium.getMaterial().getFluid(144),
                        Einsteinium.getMaterial().getFluid(144))
                .outputs(Actinoids.getItemStack(13))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(450).EUt(250000)
                .inputs(OreDictUnifier.get(dust, Boron),
                        OreDictUnifier.get(dust, Carbon),
                        OreDictUnifier.get(dust, Phosphorus),
                        OreDictUnifier.get(dust, Sulfur),
                        OreDictUnifier.get(dust, Arsenic),
                        OreDictUnifier.get(dust, Selenium),
                        OreDictUnifier.get(dust, Tellurium),
                        OreDictUnifier.get(dust, Iodine),
                        OreDictUnifier.get(dust, Astatine))
                .fluidInputs(Oxygen.getFluid(1000),
                        Nitrogen.getFluid(1000),
                        Hydrogen.getFluid(1000),
                        Fluorine.getFluid(1000),
                        Chlorine.getFluid(1000),
                        Bromine.getFluid(1000))
                .fluidOutputs(NonMetals.getFluid(15000))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(180).EUt(250000)
                .fluidInputs(Helium.getFluid(1000),
                        Neon.getFluid(1000),
                        Argon.getFluid(1000),
                        Krypton.getFluid(1000),
                        Xenon.getFluid(1000),
                        Radon.getFluid(1000))
                .fluidOutputs(GAMaterials.NobleGases.getFluid(6000))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(360).EUt(5400000)
                .inputs(OreDictUnifier.get(dust, SuperheavyLAlloy, 7),
                        OreDictUnifier.get(dust, SuperheavyHAlloy, 7),
                        Alkalis.getItemStack(14),
                        RefractoryMetals.getItemStack(7),
                        LightTranstionMetals.getItemStack(8),
                        PreciousMetals.getItemStack(9),
                        PostTransitionMetals.getItemStack(14),
                        Lanthanoids.getItemStack(15),
                        Actinoids.getItemStack(13))
                .fluidInputs(NonMetals.getFluid(15000),
                        GAMaterials.NobleGases.getFluid(6000))
                .outputs(OreDictUnifier.get(dust, Periodicium, 115))
                .buildAndRegister();

        BLAST_ALLOY_RECIPES.recipeBuilder().duration(360).EUt(54000000)
                .inputs(OreDictUnifier.get(dust, SuperheavyLAlloy, 7),
                        OreDictUnifier.get(dust, SuperheavyHAlloy, 7),
                        Alkalis.getItemStack(14),
                        RefractoryMetals.getItemStack(7),
                        LightTranstionMetals.getItemStack(8),
                        PreciousMetals.getItemStack(9),
                        PostTransitionMetals.getItemStack(14),
                        Lanthanoids.getItemStack(15),
                        Actinoids.getItemStack(13))
                .fluidInputs(NonMetals.getFluid(15000),
                        GAMaterials.NobleGases.getFluid(6000))
                .fluidOutputs(Periodicium.getFluid(16560))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(20).EUt(122880)
                .fluidInputs(Helium3.getFluid(5000))
                .fluidOutputs(LiquidHelium3.getFluid(5000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(20).EUt(61400)
                .fluidInputs(Nitrogen.getFluid(5000))
                .fluidOutputs(LiquidNitrogen.getFluid(5000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(8000)
                .fluidInputs(LiquidHelium.getFluid(1000))
                .fluidInputs(LiquidHelium3.getFluid(1000))
                .fluidOutputs(LiquidEnrichedHelium.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(150).EUt(725000)
                .fluidInputs(LiquidEnrichedHelium.getFluid(2000))
                .fluidInputs(LiquidNitrogen.getFluid(1000))
                .fluidOutputs(SuperfluidHelium.getFluid(1000))
                .fluidOutputs(LiquidHelium3.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .buildAndRegister();

        OrePrefix plateB = plate;
        if (GAConfig.GT6.addCurvedPlates) {
            plateB = plateCurved;
        }

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(150).EUt(15000000)
                .input(frameGt, QCDMatter)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_4))
                .inputs(ELECTRIC_PUMP_UMV.getStackForm(2))
                .input(circuit, UIV)
                .input(pipeLarge, Neutronium, 4)
                .input(plateB, Neutronium, 12)
                .fluidInputs(ProtoAdamantium.getFluid(2592))
                .fluidInputs(Taranium.getFluid(1584))
                .outputs(EXTREMELY_DURABLE_PLASMA_CELL.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(125000000)
                .input(block, Neutronium, 5)
                .input(block, HeavyQuarkDegenerateMatter, 5)
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE))
                .fluidInputs(HeavyLeptonMix.getFluid(6000))
                .fluidInputs(Gluons.getFluid(6000))
                .fluidInputs(Periodicium.getFluid(2736))
                .fluidOutputs(DenseNeutronPlasma.getFluid(6000))
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder().duration(90).EUt(62500000)
                .inputs(EXTREMELY_DURABLE_PLASMA_CELL.getStackForm())
                .fluidInputs(DenseNeutronPlasma.getFluid(1000))
                .outputs(DENSE_NEUTRON_PLASMA_CELL.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(125000000)
                .inputs(DENSE_NEUTRON_PLASMA_CELL.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE,2))
                .outputs(COSMIC_NEUTRON_PLASMA_CELL.getStackForm())
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(500).EUt(10000000)
                .inputs(COSMIC_NEUTRON_PLASMA_CELL.getStackForm())
                .fluidInputs(SuperfluidHelium.getFluid(48000))
                .fluidOutputs(Helium.getFluid(48000))
                .notConsumable(INGOT_FIELD_SHAPE.getStackForm())
                .output(ingot, CosmicNeutronium)
                .outputs(EXTREMELY_DURABLE_PLASMA_CELL.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(320000)
                .fluidInputs(Cycloparaphenylene.getFluid(200))
                .fluidInputs(Methane.getFluid(800))
                .input(dust, Neutronium)
                .notConsumable(plate, Rhenium)
                .fluidOutputs(NeutroniumDopedNanotubes.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(84500000)
                .input(plate, CarbonNanotubes, 3)
                .input(plate, CosmicNeutronium, 3)
                .input(plate, FullerenePolymerMatrix, 3)
                .fluidInputs(NeutroniumDopedNanotubes.getFluid(2000))
                .outputs(HIGHLY_DENSE_POLYMER_PLATE.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(800000000)
                .inputs(HIGHLY_DENSE_POLYMER_PLATE.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE))
                .fluidOutputs(CosmicMeshPlasma.getFluid(1000))
                .buildAndRegister();

        FLUID_CANNER_RECIPES.recipeBuilder().duration(90).EUt(125000000)
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .fluidInputs(CosmicMeshPlasma.getFluid(1000))
                .outputs(COSMIC_MESH_CONTAINMENT_UNIT.getStackForm())
                .buildAndRegister();

        MetaItem<?>.MetaValueItem[] fieldShapes = { PLATE_FIELD_SHAPE, INGOT_FIELD_SHAPE, WIRE_FIELD_SHAPE, SPHERE_FIELD_SHAPE, ROD_FIELD_SHAPE };
        MetaItem<?>.MetaValueItem[] molds = { SHAPE_MOLD_PLATE, SHAPE_MOLD_INGOT, SHAPE_EXTRUDER_WIRE, SHAPE_MOLD_BALL, SHAPE_EXTRUDER_ROD };
        for (int i = 0; i < fieldShapes.length; i++) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(340).EUt(85000)
                    .inputs(molds[i].getStackForm())
                    .input(plate, Polybenzimidazole, 2)
                    .input(plate, Polyetheretherketone, 2)
                    .input(circuit, MarkerMaterials.Tier.Master)
                    .outputs(fieldShapes[i].getStackForm())
                    .buildAndRegister();
        }

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(500).EUt(10000000)
                .inputs(COSMIC_MESH_CONTAINMENT_UNIT.getStackForm())
                .fluidInputs(SuperfluidHelium.getFluid(24000))
                .fluidOutputs(Helium.getFluid(24000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(COSMIC_MESH.getStackForm())
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(500).EUt(10000000)
                .inputs(COSMIC_MESH_CONTAINMENT_UNIT.getStackForm())
                .fluidInputs(SuperfluidHelium.getFluid(24000))
                .fluidInputs(Xenon.getFluid(500))
                .fluidOutputs(Helium.getFluid(24000))
                .notConsumable(WIRE_FIELD_SHAPE.getStackForm())
                .outputs(COSMIC_FABRIC.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(10000000)
                .inputs(COSMIC_FABRIC.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE))
                .fluidOutputs(CosmicMeshPlasma.getFluid(1000))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(10000000)
                .inputs(COSMIC_MESH.getStackForm())
                .inputs(GAMetaBlocks.SIMPLE_BLOCK.getItemVariant(GASimpleBlock.CasingType.QCD_CHARGE))
                .fluidOutputs(CosmicMeshPlasma.getFluid(1000))
                .buildAndRegister();
    }
}
