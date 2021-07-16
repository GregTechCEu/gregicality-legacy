package gregicadditions.jei;

import com.google.common.collect.ImmutableMap;
import gregicadditions.jei.multi.*;
import gregicadditions.jei.multi.advance.*;
import gregicadditions.jei.multi.mega.*;
import gregicadditions.jei.multi.miner.*;
import gregicadditions.jei.multi.nuclear.*;
import gregicadditions.jei.multi.override.*;
import gregicadditions.jei.multi.quantum.*;
import gregicadditions.jei.multi.simple.*;
import gregicadditions.machines.GATileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import gregtech.integration.jei.multiblock.infos.LargeTurbineInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.gui.recipes.RecipeLayout;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

public class GAMultiblockInfoCategory implements IRecipeCategory<MultiblockInfoRecipeWrapper> {
    private final IDrawable background;
    private final IGuiHelper guiHelper;
    private static ImmutableMap<String, MultiblockInfoRecipeWrapper> multiblockRecipes;

    public GAMultiblockInfoCategory(IJeiHelpers helpers) {
        this.guiHelper = helpers.getGuiHelper();
        this.background = guiHelper.createBlankDrawable(176, 166);
    }

    public static ImmutableMap<String, MultiblockInfoRecipeWrapper> getRecipes() {
        if(multiblockRecipes == null) {
            multiblockRecipes = new ImmutableMap.Builder<String, MultiblockInfoRecipeWrapper>()
                    .put("central_monitor", new MultiblockInfoRecipeWrapper(new CentralMonitorInfo()))
                    .put("electric_blast_furnace", new MultiblockInfoRecipeWrapper(new ElectricBlastFurnaceInfo()))
                    .put("cracker_unit", new MultiblockInfoRecipeWrapper(new CrackerUnitInfo()))
                    .put("diesel_engine", new MultiblockInfoRecipeWrapper(new LargeCombustionEngineInfo()))
                    .put("distillation_tower", new MultiblockInfoRecipeWrapper(new DistillationTowerInfo()))
                    .put("implosion_compressor", new MultiblockInfoRecipeWrapper(new ImplosionCompressorInfo()))
                    .put("multi_smelter", new MultiblockInfoRecipeWrapper(new MultiSmelterInfo()))
                    .put("vacuum_freezer", new MultiblockInfoRecipeWrapper(new VacuumFreezerInfo()))
                    .put("pyrolyse_oven", new MultiblockInfoRecipeWrapper(new PyrolyseOvenInfo()))
                    .put("assembly_line", new MultiblockInfoRecipeWrapper(new AssemblyLineInfo()))
                    .put("fusion_reactor_1", new MultiblockInfoRecipeWrapper(new FusionReactor1Info()))
                    .put("fusion_reactor_2", new MultiblockInfoRecipeWrapper(new FusionReactor2Info()))
                    .put("fusion_reactor_3", new MultiblockInfoRecipeWrapper(new FusionReactor3Info()))
                    .put("processing_array", new MultiblockInfoRecipeWrapper(new ProcessingArrayInfo()))
                    .put("large_thermal_centrifuge", new MultiblockInfoRecipeWrapper(new LargeThermalCentrifugeInfo()))
                    .put("large_electrolyzer", new MultiblockInfoRecipeWrapper(new LargeElectrolyzerInfo()))
                    .put("large_centrifuge", new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()))
                    .put("large_cutting", new MultiblockInfoRecipeWrapper(new LargeCuttingInfo()))
                    .put("large_mixer", new MultiblockInfoRecipeWrapper(new LargeMixerInfo()))
                    .put("large_multi_use", new MultiblockInfoRecipeWrapper(new LargeMultiUseInfo()))
                    .put("large_macerator", new MultiblockInfoRecipeWrapper(new LargeMaceratorInfo()))
                    .put("large_sifter", new MultiblockInfoRecipeWrapper(new LargeSifterInfo()))
                    .put("large_washing_plant", new MultiblockInfoRecipeWrapper(new LargeWashingPlantInfo()))
                    .put("large_wiremill", new MultiblockInfoRecipeWrapper(new LargeWiremillInfo()))
                    .put("large_chemical_reactor", new MultiblockInfoRecipeWrapper(new LargeChemicalReactorInfo()))
                    .put("large_extruder", new MultiblockInfoRecipeWrapper(new LargeExtruderInfo()))
                    .put("volcanus", new MultiblockInfoRecipeWrapper(new VolcanusInfo()))
                    .put("large_assembler", new MultiblockInfoRecipeWrapper(new LargeAssemblerInfo()))
                    .put("large_bending_forming", new MultiblockInfoRecipeWrapper(new LargeBenderAndFormingInfo()))
                    .put("large_miner_1", new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[0])))
                    .put("large_miner_2", new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[1])))
                    .put("large_miner_3", new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[2])))
                    .put("large_steam_turbine", new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_STEAM_TURBINE)))
                    .put("large_gas_turbine", new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_GAS_TURBINE)))
                    .put("large_plasma_turbine", new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_PLASMA_TURBINE)))
                    .put("hot_coolant_turbine", new MultiblockInfoRecipeWrapper(new HotCoolantTurbineInfo(GATileEntities.HOT_COOLANT_TURBINE)))
                    .put("nuclear_reactor", new MultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_REACTOR)))
                    .put("nuclear_breeder", new MultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_BREEDER)))
                    .put("large_circuit_assembly_line", new MultiblockInfoRecipeWrapper(new LargeCircuitAssemblyLineInfo()))
                    .put("void_miner", new MultiblockInfoRecipeWrapper(new VoidMinerInfo()))
                    .put("large_transformer", new MultiblockInfoRecipeWrapper(new LargeTransformerInfo()))
                    .put("industrial_primitive_blast_furnace", new MultiblockInfoRecipeWrapper(new IndustrialPrimitiveBlastFurnaceInfo()))
                    .put("advanced_distillation_tower", new MultiblockInfoRecipeWrapper(new AdvancedDistillationTowerInfo()))
                    .put("cryogenic_freezer", new MultiblockInfoRecipeWrapper(new CryogenicFreezerInfo()))
                    .put("chemical_plant", new MultiblockInfoRecipeWrapper(new ChemicalPlantInfo()))
                    .put("large_rocket_engine", new MultiblockInfoRecipeWrapper(new LargeRocketEngineInfo()))
                    .put("alloy_blast_furnace", new MultiblockInfoRecipeWrapper(new AlloyBlastFurnaceInfo()))
                    .put("large_forge_hammer", new MultiblockInfoRecipeWrapper(new LargeForgeHammerInfo()))
                    .put("large_naquadah_reactor", new MultiblockInfoRecipeWrapper(new LargeNaquadahReactorInfo()))
                    .put("battery_tower", new MultiblockInfoRecipeWrapper(new BatteryTowerInfo()))
                    .put("hyper_reactor_1", new MultiblockInfoRecipeWrapper(new HyperReactor1Info()))
                    .put("hyper_reactor_2", new MultiblockInfoRecipeWrapper(new HyperReactor2Info()))
                    .put("hyper_reactor_3", new MultiblockInfoRecipeWrapper(new HyperReactor3Info()))
                    .put("fusion_reactor_4", new MultiblockInfoRecipeWrapper(new FusionReactor4Info()))
                    .put("qbit_computer", new MultiblockInfoRecipeWrapper(new QubitComputerInfo()))
                    .put("drilling_rig", new MultiblockInfoRecipeWrapper(new DrillingRigInfo()))
                    .put("stellar_forge", new MultiblockInfoRecipeWrapper(new StellarForgeInfo()))
                    .put("large_engraver", new MultiblockInfoRecipeWrapper(new LargeEngraverInfo()))
                    .put("void_miner_2", new MultiblockInfoRecipeWrapper(new VoidMinerInfo2()))
                    .put("void_miner_3", new MultiblockInfoRecipeWrapper(new VoidMinerInfo3()))
                    .put("bio_reactor", new MultiblockInfoRecipeWrapper(new BioReactorInfo()))
                    .put("plasma_condenser", new MultiblockInfoRecipeWrapper(new PlasmaCondenserInfo()))
                    .put("large_packager", new MultiblockInfoRecipeWrapper(new LargePackagerInfo()))
                    .put("steam_grinder", new MultiblockInfoRecipeWrapper(new SteamGrinderInfo()))
                    .put("steam_oven", new MultiblockInfoRecipeWrapper(new SteamOvenInfo()))
                    .put("cosmic_ray_detector", new MultiblockInfoRecipeWrapper(new CosmicRayDetectorInfo()))
                    .put("electric_implosion", new MultiblockInfoRecipeWrapper(new ElectricImplosionInfo()))
                    .build();
        }

        return multiblockRecipes;
    }

    public static void registerRecipes(IModRegistry registry) {
        List<MultiblockInfoRecipeWrapper> recipeWrappers = new ArrayList<MultiblockInfoRecipeWrapper>() {
            {
                add(new MultiblockInfoRecipeWrapper(new CentralMonitorInfo()));
                add(new MultiblockInfoRecipeWrapper(new ElectricBlastFurnaceInfo()));
                add(new MultiblockInfoRecipeWrapper(new CrackerUnitInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeCombustionEngineInfo()));
                add(new MultiblockInfoRecipeWrapper(new DistillationTowerInfo()));
                add(new MultiblockInfoRecipeWrapper(new ImplosionCompressorInfo()));
                add(new MultiblockInfoRecipeWrapper(new MultiSmelterInfo()));
                add(new MultiblockInfoRecipeWrapper(new VacuumFreezerInfo()));
                add(new MultiblockInfoRecipeWrapper(new PyrolyseOvenInfo()));
                add(new MultiblockInfoRecipeWrapper(new AssemblyLineInfo()));
                add(new MultiblockInfoRecipeWrapper(new FusionReactor1Info()));
                add(new MultiblockInfoRecipeWrapper(new FusionReactor2Info()));
                add(new MultiblockInfoRecipeWrapper(new FusionReactor3Info()));
                add(new MultiblockInfoRecipeWrapper(new ProcessingArrayInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeThermalCentrifugeInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeElectrolyzerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeCuttingInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeMixerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeMultiUseInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeMaceratorInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeSifterInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeWashingPlantInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeWiremillInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeChemicalReactorInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeExtruderInfo()));
                add(new MultiblockInfoRecipeWrapper(new VolcanusInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeAssemblerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeBenderAndFormingInfo()));
                add(new MultiblockInfoRecipeWrapper(new AdvancedDistillationTowerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[0])));
                add(new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[1])));
                add(new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[2])));
                add(new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_STEAM_TURBINE)));
                add(new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_GAS_TURBINE)));
                add(new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_PLASMA_TURBINE)));
                add(new MultiblockInfoRecipeWrapper(new HotCoolantTurbineInfo(GATileEntities.HOT_COOLANT_TURBINE)));
                add(new MultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_REACTOR)));
                add(new MultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_BREEDER)));
                add(new MultiblockInfoRecipeWrapper(new LargeCircuitAssemblyLineInfo()));
                add(new MultiblockInfoRecipeWrapper(new VoidMinerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeTransformerInfo()));
                add(new MultiblockInfoRecipeWrapper(new IndustrialPrimitiveBlastFurnaceInfo()));
                add(new MultiblockInfoRecipeWrapper(new CryogenicFreezerInfo()));
                add(new MultiblockInfoRecipeWrapper(new ChemicalPlantInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeRocketEngineInfo()));
                add(new MultiblockInfoRecipeWrapper(new AlloyBlastFurnaceInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeForgeHammerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeNaquadahReactorInfo()));
                add(new MultiblockInfoRecipeWrapper(new BatteryTowerInfo()));
                add(new MultiblockInfoRecipeWrapper(new HyperReactor1Info()));
                add(new MultiblockInfoRecipeWrapper(new HyperReactor2Info()));
                add(new MultiblockInfoRecipeWrapper(new HyperReactor3Info()));
                add(new MultiblockInfoRecipeWrapper(new FusionReactor4Info()));
                add(new MultiblockInfoRecipeWrapper(new GasCentrifugeInfo()));
                add(new MultiblockInfoRecipeWrapper(new QubitComputerInfo()));
                add(new MultiblockInfoRecipeWrapper(new DrillingRigInfo()));
                add(new MultiblockInfoRecipeWrapper(new StellarForgeInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeEngraverInfo()));
                add(new MultiblockInfoRecipeWrapper(new VoidMinerInfo2()));
                add(new MultiblockInfoRecipeWrapper(new VoidMinerInfo3()));
                add(new MultiblockInfoRecipeWrapper(new BioReactorInfo()));
                add(new MultiblockInfoRecipeWrapper(new PlasmaCondenserInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargePackagerInfo()));
                add(new MultiblockInfoRecipeWrapper(new SteamGrinderInfo()));
                add(new MultiblockInfoRecipeWrapper(new SteamOvenInfo()));
                add(new MultiblockInfoRecipeWrapper(new CosmicRayDetectorInfo()));
                add(new MultiblockInfoRecipeWrapper(new ElectricImplosionInfo()));
                add(new MultiblockInfoRecipeWrapper(new MegaDistillationTowerInfo()));
                add(new MultiblockInfoRecipeWrapper(new MegaBlastFurnaceInfo()));
                add(new MultiblockInfoRecipeWrapper(new MegaVacuumFreezerInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeArcFurnaceInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeBreweryInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeCanningMachineInfo()));
                add(new MultiblockInfoRecipeWrapper(new AdvancedChemicalReactorInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeElectromagnetInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeExtractorInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeMassFabricatorInfo()));
                add(new MultiblockInfoRecipeWrapper(new LargeReplicatorInfo()));
            }
        };

        for (MultiblockInfoRecipeWrapper recipeWrapper : recipeWrappers) {
            registry.addRecipes(new ArrayList<MultiblockInfoRecipeWrapper>() {{ add(recipeWrapper); }}, "gregtech:multiblock_info");
        }
    }

    @Override
    public String getUid() {
        return "gtadditions:multiblock_info2";
    }

    @Override
    public String getTitle() {
        return I18n.format("gregtech.multiblock.title");
    }

    @Override
    public String getModName() {
        return "gtadditions";
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, MultiblockInfoRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeWrapper.setRecipeLayout((RecipeLayout) recipeLayout, guiHelper);

        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        itemStackGroup.addTooltipCallback(recipeWrapper::addBlockTooltips);
    }
}
