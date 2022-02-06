package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAExplosive;
import gregicadditions.item.GAMetaBlocks;
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
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class UltimateMaterials {
    public static void init() {

        LARGE_MIXER_RECIPES.recipeBuilder().duration(270).EUt(250000)
                .input(dust, Ruthenium)
                .input(dust, Rhodium)
                .input(dust, Palladium)
                .input(dust, Silver)
                .input(dust, Rhenium)
                .input(dust, Osmium)
                .input(dust, Iridium)
                .input(dust, Platinum)
                .input(dust, Gold)
                .outputs(PreciousMetals.getItemStack(9))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(210).EUt(250000)
                .input(dust, Zirconium)
                .input(dust, Hafnium)
                .input(dust, Niobium)
                .input(dust, Tantalum)
                .input(dust, Molybdenum)
                .input(dust, Tungsten)
                .input(dust, Technetium)
                .outputs(RefractoryMetals.getItemStack(7))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(240).EUt(250000)
                .input(dust, Titanium)
                .input(dust, Vanadium)
                .input(dust, Manganese)
                .input(dust, Chrome)
                .input(dust, Iron)
                .input(dust, Nickel)
                .input(dust, Cobalt)
                .input(dust, Copper)
                .outputs(LightTranstionMetals.getItemStack(8))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(420).EUt(250000)
                .input(dust, Beryllium)
                .input(dust, Magnesium)
                .input(dust, Calcium)
                .input(dust, Strontium)
                .input(dust, Barium)
                .input(dust, Radium)
                .input(dust, Scandium)
                .input(dust, Yttrium)
                .fluidInputs(Lithium.getFluid(144))
                .fluidInputs(Sodium.getFluid(144))
                .fluidInputs(Potassium.getFluid(144))
                .fluidInputs(Rubidium.getFluid(144))
                .fluidInputs(Caesium.getFluid(144))
                .fluidInputs(Francium.getFluid(144))
                .outputs(Alkalis.getItemStack(14))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(420).EUt(250000)
                .input(dust, Zinc)
                .input(dust, Cadmium)
                .input(dust, Aluminium)
                .input(dust, Silicon)
                .input(dust, Germanium)
                .input(dust, Antimony)
                .input(dust, Thallium)
                .input(dust, Lead)
                .fluidInputs(Mercury.getFluid(144))
                .fluidInputs(Tin.getFluid(144))
                .fluidInputs(Gallium.getFluid(144))
                .fluidInputs(Indium.getFluid(144))
                .fluidInputs(Bismuth.getFluid(144))
                .fluidInputs(Polonium.getFluid(144))
                .outputs(PostTransitionMetals.getItemStack(14))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(450).EUt(250000)
                .input(dust, Samarium)
                .input(dust, Gadolinium)
                .input(dust, Terbium)
                .input(dust, Thulium)
                .input(dust, Holmium)
                .input(dust, Lutetium)
                .input(dust, Promethium)
                .input(dust, Erbium)
                .input(dust, Dysprosium)
                .fluidInputs(Lanthanum.getFluid(144))
                .fluidInputs(Cerium.getFluid(144))
                .fluidInputs(Praseodymium.getFluid(144))
                .fluidInputs(Neodymium.getFluid(144))
                .fluidInputs(Europium.getFluid(144))
                .fluidInputs(Ytterbium.getFluid(144))
                .outputs(Lanthanoids.getItemStack(15))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(390).EUt(250000)
                .input(dust, Actinium)
                .input(dust, Thorium)
                .input(dust, Protactinium.getMaterial())
                .input(dust, UraniumRadioactive.getMaterial())
                .input(dust, Americium)
                .input(dust, Curium.getMaterial())
                .input(dust, Berkelium.getMaterial())
                .input(dust, Fermium.getMaterial())
                .input(dust, Californium.getMaterial())
                .fluidInputs(Neptunium.getMaterial().getFluid(144))
                .fluidInputs(Plutonium.getFluid(144))
                .fluidInputs(Mendelevium.getMaterial().getFluid(144))
                .fluidInputs(Einsteinium.getMaterial().getFluid(144))
                .outputs(Actinoids.getItemStack(13))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(450).EUt(250000)
                .input(dust, Boron)
                .input(dust, Carbon)
                .input(dust, Phosphorus)
                .input(dust, Sulfur)
                .input(dust, Arsenic)
                .input(dust, Selenium)
                .input(dust, Tellurium)
                .input(dust, Iodine)
                .input(dust, Astatine)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .fluidOutputs(NonMetals.getFluid(15000))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(180).EUt(250000)
                .fluidInputs(Helium.getFluid(1000))
                .fluidInputs(Neon.getFluid(1000))
                .fluidInputs(Argon.getFluid(1000))
                .fluidInputs(Krypton.getFluid(1000))
                .fluidInputs(Xenon.getFluid(1000))
                .fluidInputs(Radon.getFluid(1000))
                .fluidOutputs(GAMaterials.NobleGases.getFluid(6000))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().duration(360).EUt(5400000)
                .input(dust, SuperheavyLAlloy, 7)
                .input(dust, SuperheavyHAlloy, 7)
                .inputs(Alkalis.getItemStack(14))
                .inputs(RefractoryMetals.getItemStack(7))
                .inputs(LightTranstionMetals.getItemStack(8))
                .inputs(PreciousMetals.getItemStack(9))
                .inputs(PostTransitionMetals.getItemStack(14))
                .inputs(Lanthanoids.getItemStack(15))
                .inputs(Actinoids.getItemStack(13))
                .fluidInputs(NonMetals.getFluid(15000))
                .fluidInputs(GAMaterials.NobleGases.getFluid(6000))
                .output(dust, Periodicium, 115)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(20).EUt(30720)
                .fluidInputs(Helium3.getFluid(5000))
                .fluidOutputs(LiquidHelium3.getFluid(5000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(20).EUt(480)
                .fluidInputs(Nitrogen.getFluid(5000))
                .fluidOutputs(LiquidNitrogen.getFluid(5000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(7680)
                .fluidInputs(LiquidHelium.getFluid(1000))
                .fluidInputs(LiquidHelium3.getFluid(1000))
                .fluidOutputs(LiquidEnrichedHelium.getFluid(2000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(725000)
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
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.QCD_CHARGE))
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
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.QCD_CHARGE,2))
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

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(100000000)
                .inputs(HIGHLY_DENSE_POLYMER_PLATE.getStackForm())
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.QCD_CHARGE))
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
            ASSEMBLER_RECIPES.recipeBuilder().duration(340).EUt(122880)
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
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.QCD_CHARGE))
                .fluidOutputs(CosmicMeshPlasma.getFluid(1000))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(10).EUt(10000000)
                .inputs(COSMIC_MESH.getStackForm())
                .inputs(GAMetaBlocks.EXPLOSIVE.getItemVariant(GAExplosive.ExplosiveType.QCD_CHARGE))
                .fluidOutputs(CosmicMeshPlasma.getFluid(1000))
                .buildAndRegister();
    }
}
