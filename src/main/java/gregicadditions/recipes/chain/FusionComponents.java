package gregicadditions.recipes.chain;

import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.fusion.GACryostatCasing;
import gregicadditions.item.fusion.GADivertorCasing;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.item.fusion.GAVacuumCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class FusionComponents {
    public static void init() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(500000)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_1),
                        OreDictUnifier.get(plate, TantalumHafniumSeaborgiumCarbide, 8),
                        OreDictUnifier.get(plate, Einsteinium.getMaterial(), 8),
                        FIELD_GENERATOR_UV.getStackForm(2),
                        HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                        HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                        OreDictUnifier.get(wireGtQuadruple, UVSuperconductor, 64))
                .input(circuit, MarkerMaterials.Tier.Infinite, 4)
                .fluidInputs(SolderingAlloy.getFluid(2880 * 2))
                .outputs(GATileEntities.ADVANCED_FUSION_REACTOR.getStackForm()).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(wireGtOctal, UVSuperconductor, 4)
                .input(plate, TantalumHafniumSeaborgiumCarbide, 2)
                .input(plate, Einsteinium.getMaterial(), 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(4))
                .input(circuit, MarkerMaterials.Tier.Infinite)
                .inputs(FIELD_GENERATOR_UV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_1, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(wireGtOctal, UHVSuperconductor, 4)
                .input(plate, Bohrium, 2)
                .input(plate, Fermium.getMaterial(), 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(16))
                .input(circuit, UEV)
                .inputs(FIELD_GENERATOR_UHV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_2, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(wireGtOctal, UEVSuperconductor, 4)
                .input(plate, Vibranium, 2)
                .input(plate, Mendelevium.getMaterial(), 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(64))
                .input(circuit, UIV)
                .inputs(FIELD_GENERATOR_UEV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(5660))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_COIL_3, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(frameGt, TungstenSteel, 4)
                .input(plate, TungstenSteel, 16)
                .input(plate, Tungsten, 32)
                .input(screw, LithiumTitanate, 16)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(GAMetaBlocks.DIVERTOR_CASING.getItemVariant(GADivertorCasing.CasingType.DIVERTOR_1, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(frameGt, TungstenSteel, 8)
                .input(plate, TungstenSteel, 32)
                .input(plate, TungstenTitaniumCarbide, 32)
                .input(screw, LithiumTitanate, 32)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING, 4))
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GAMetaBlocks.DIVERTOR_CASING.getItemVariant(GADivertorCasing.CasingType.DIVERTOR_2, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(frameGt, TungstenSteel, 16)
                .input(plate, TungstenSteel, 64)
                .input(plate, TitanSteel, 32)
                .input(screw, LithiumTitanate, 64)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING, 8))
                .fluidInputs(SolderingAlloy.getFluid(5660))
                .outputs(GAMetaBlocks.DIVERTOR_CASING.getItemVariant(GADivertorCasing.CasingType.DIVERTOR_3, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(frameGt, StainlessSteel, 8)
                .input(plate, StainlessSteel, 32)
                .input(pipeTiny, Copper, 64)
                .input(screw, LithiumTitanate, 16)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(GAMetaBlocks.VACUUM_CASING.getItemVariant(GAVacuumCasing.CasingType.VACUUM_1, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(frameGt, StainlessSteel, 16)
                .input(plate, StainlessSteel, 64)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(screw, LithiumTitanate, 32)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING, 4))
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GAMetaBlocks.VACUUM_CASING.getItemVariant(GAVacuumCasing.CasingType.VACUUM_2, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(frameGt, StainlessSteel, 32)
                .input(plate, StainlessSteel, 64)
                .input(plate, StainlessSteel, 64)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(screw, LithiumTitanate, 64)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING, 8))
                .fluidInputs(SolderingAlloy.getFluid(5660))
                .outputs(GAMetaBlocks.VACUUM_CASING.getItemVariant(GAVacuumCasing.CasingType.VACUUM_3, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(frameGt, StainlessSteel, 8)
                .input(plate, Titanium, 32)
                .input(pipeTiny, Copper, 64)
                .input(screw, LithiumTitanate, 16)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .outputs(GAMetaBlocks.CRYOSTAT_CASING.getItemVariant(GACryostatCasing.CasingType.CRYOSTAT_1, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(frameGt, StainlessSteel, 16)
                .input(plate, Nitinol60, 32)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(screw, LithiumTitanate, 32)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING, 4))
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .outputs(GAMetaBlocks.CRYOSTAT_CASING.getItemVariant(GACryostatCasing.CasingType.CRYOSTAT_2, 4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(frameGt, StainlessSteel, 32)
                .input(plate, TungstenTitaniumCarbide, 32)
                .input(plate, TungstenTitaniumCarbide, 32)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(pipeTiny, Copper, 64)
                .input(screw, LithiumTitanate, 64)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING, 8))
                .fluidInputs(SolderingAlloy.getFluid(5760))
                .outputs(GAMetaBlocks.CRYOSTAT_CASING.getItemVariant(GACryostatCasing.CasingType.CRYOSTAT_3, 4))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).
                inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_3))
                .input(plate, TantalumHafniumSeaborgiumCarbide, 6)
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.ADV_FUSION_CASING))
                .duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).duration(50)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .input(plate, TungstenTitaniumCarbide, 4)
                .input(frameGt, TungstenTitaniumCarbide)
                .input(pipeSmall, Zeron100, 4)
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.BLANKET_BASE))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).duration(50)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .input(plate, Beryllium, 16)
                .input(plateDense, Copper, 2)
                .input(plateDense, StainlessSteel, 2)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.BLANKET_BASE))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.FUSION_BLANKET))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).duration(50)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .input(plate, LithiumTitanate, 4)
                .input(plateDense, Copper, 2)
                .input(plateDense, StainlessSteel, 2)
                .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.BLANKET_BASE))
                .outputs(GAMetaBlocks.FUSION_CASING.getItemVariant(GAFusionCasing.CasingType.BREEDING_BLANKET))
                .buildAndRegister();
    }
}
