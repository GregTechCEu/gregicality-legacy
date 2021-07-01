package gregicadditions.jei;

import com.google.common.collect.ImmutableMap;
import gregicadditions.jei.multi.*;
import gregicadditions.jei.multi.advance.*;
import gregicadditions.jei.multi.miner.LargeMinerInfo;
import gregicadditions.jei.multi.miner.VoidMinerInfo;
import gregicadditions.jei.multi.miner.VoidMinerInfo2;
import gregicadditions.jei.multi.miner.VoidMinerInfo3;
import gregicadditions.jei.multi.nuclear.HotCoolantTurbineInfo;
import gregicadditions.jei.multi.nuclear.NuclearReactorInfo;
import gregicadditions.jei.multi.override.*;
import gregicadditions.jei.multi.quantum.QubitComputerInfo;
import gregicadditions.jei.multi.simple.*;
import gregicadditions.machines.GATileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import gregtech.integration.jei.multiblock.infos.LargeTurbineInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.gui.recipes.RecipeLayout;
import net.minecraft.client.resources.I18n;

public class GAMultiblockInfoCategory implements IRecipeCategory<MultiblockInfoRecipeWrapper> {
    private final IDrawable background;
    private final IGuiHelper guiHelper;

    public GAMultiblockInfoCategory(IJeiHelpers helpers) {
        this.guiHelper = helpers.getGuiHelper();
        this.background = guiHelper.createBlankDrawable(176, 166);
    }

    public static ImmutableMap<String, MultiblockInfoRecipeWrapper> multiblockRecipes = new ImmutableMap.Builder<String, MultiblockInfoRecipeWrapper>()
            .put("central_monitor", new MultiblockInfoRecipeWrapper(new CentralMonitorInfo()))
            .put("electric_blast_furnace", new MultiblockInfoRecipeWrapper(new ElectricBlastFurnaceInfo()))
            .put("cracker_unit", new MultiblockInfoRecipeWrapper(new CrackerUnitInfo()))
            .put("diesel_engine", new MultiblockInfoRecipeWrapper(new DieselEngineInfo()))
            .put("distillation_tower", new LargeMultiblockInfoRecipeWrapper(new DistillationTowerInfo()))
            .put("implosion_compressor", new MultiblockInfoRecipeWrapper(new ImplosionCompressorInfo()))
            .put("multi_smelter", new MultiblockInfoRecipeWrapper(new MultiSmelterInfo()))
            .put("vacuum_freezer", new MultiblockInfoRecipeWrapper(new VacuumFreezerInfo()))
            .put("pyrolyse_oven", new MultiblockInfoRecipeWrapper(new PyrolyseOvenInfo()))
            .put("assembly_line", new MultiblockInfoRecipeWrapper(new AssemblyLineInfo()))
            .put("fusion_reactor_1", new LargeMultiblockInfoRecipeWrapper(new FusionReactor1Info()))
            .put("fusion_reactor_2", new LargeMultiblockInfoRecipeWrapper(new FusionReactor2Info()))
            .put("fusion_reactor_3", new LargeMultiblockInfoRecipeWrapper(new FusionReactor3Info()))
            .put("processing_array", new MultiblockInfoRecipeWrapper(new ProcessingArrayInfo()))
            .put("large_thermal_centrifuge", new MultiblockInfoRecipeWrapper(new LargeThermalCentrifugeInfo()))
            .put("large_electrolyzer", new MultiblockInfoRecipeWrapper(new LargeElectrolyzerInfo()))
            .put("large_centrifuge", new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()))
            .put("large_cutting", new MultiblockInfoRecipeWrapper(new LargeCuttingInfo()))
            .put("large_mixer", new MultiblockInfoRecipeWrapper(new LargeMixerInfo()))
            .put("large_multi_use", new MultiblockInfoRecipeWrapper(new LargeMultiUseInfo()))
            .put("large_macerator", new LargeMultiblockInfoRecipeWrapper(new LargeMaceratorInfo()))
            .put("large_sifter", new MultiblockInfoRecipeWrapper(new LargeSifterInfo()))
            .put("large_washing_plant", new MultiblockInfoRecipeWrapper(new LargeWashingPlantInfo()))
            .put("large_wiremill", new MultiblockInfoRecipeWrapper(new LargeWiremillInfo()))
            .put("large_chemical_reactor", new MultiblockInfoRecipeWrapper(new LargeChemicalReactorInfo()))
            .put("large_extruder", new MultiblockInfoRecipeWrapper(new LargeExtruderInfo()))
            .put("volcanus", new MultiblockInfoRecipeWrapper(new VolcanusInfo()))
            .put("large_assembler", new LargeMultiblockInfoRecipeWrapper(new LargeAssemblerInfo()))
            .put("large_bending_forming", new MultiblockInfoRecipeWrapper(new LargeBenderAndFormingInfo()))
            .put("large_miner_1", new LargeMultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[0])))
            .put("large_miner_2", new LargeMultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[1])))
            .put("large_miner_3", new LargeMultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[2])))
            .put("large_steam_turbine", new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_STEAM_TURBINE)))
            .put("large_gas_turbine", new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_GAS_TURBINE)))
            .put("large_plasma_turbine", new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_PLASMA_TURBINE)))
            .put("hot_coolant_turbine", new LargeMultiblockInfoRecipeWrapper(new HotCoolantTurbineInfo(GATileEntities.HOT_COOLANT_TURBINE)))
            .put("nuclear_reactor", new LargeMultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_REACTOR)))
            .put("nuclear_breeder", new LargeMultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_BREEDER)))
            .put("large_circuit_assembly_line", new LargeMultiblockInfoRecipeWrapper(new LargeCircuitAssemblyLineInfo()))
            .put("void_miner", new LargeMultiblockInfoRecipeWrapper(new VoidMinerInfo()))
            .put("large_transformer", new MultiblockInfoRecipeWrapper(new LargeTransformerInfo()))
            .put("industrial_primitive_blast_furnace", new MultiblockInfoRecipeWrapper(new IndustrialPrimitiveBlastFurnaceInfo()))
            .put("advanced_distillation_tower", new LargeMultiblockInfoRecipeWrapper(new AdvancedDistillationTowerInfo()))
            .put("cryogenic_freezer", new MultiblockInfoRecipeWrapper(new CryogenicFreezerInfo()))
            .put("chemical_plant", new LargeMultiblockInfoRecipeWrapper(new ChemicalPlantInfo()))
            .put("large_rocket_engine", new LargeMultiblockInfoRecipeWrapper(new LargeRocketEngineInfo()))
            .put("alloy_blast_furnace", new MultiblockInfoRecipeWrapper(new AlloyBlastFurnaceInfo()))
            .put("large_forge_hammer", new MultiblockInfoRecipeWrapper(new LargeForgeHammerInfo()))
            .put("large_naquadah_reactor", new LargeMultiblockInfoRecipeWrapper(new LargeNaquadahReactorInfo()))
            .put("battery_tower", new LargeMultiblockInfoRecipeWrapper(new BatteryTowerInfo()))
            .put("hyper_reactor_1", new LargeMultiblockInfoRecipeWrapper(new HyperReactor1Info()))
            .put("hyper_reactor_2", new LargeMultiblockInfoRecipeWrapper(new HyperReactor2Info()))
            .put("hyper_reactor_3", new LargeMultiblockInfoRecipeWrapper(new HyperReactor3Info()))
            .put("fusion_reactor_4", new LargeMultiblockInfoRecipeWrapper(new FusionReactor4Info()))
            .put("qbit_computer", new MultiblockInfoRecipeWrapper(new QubitComputerInfo()))
            .put("drilling_rig", new LargeMultiblockInfoRecipeWrapper(new DrillingRigInfo()))
            .put("stellar_forge", new LargeMultiblockInfoRecipeWrapper(new StellarForgeInfo()))
            .put("large_engraver", new MultiblockInfoRecipeWrapper(new LargeEngraverInfo()))
            .put("void_miner_2", new LargeMultiblockInfoRecipeWrapper(new VoidMinerInfo2()))
            .put("void_miner_3", new LargeMultiblockInfoRecipeWrapper(new VoidMinerInfo3()))
            .put("bio_reactor", new LargeMultiblockInfoRecipeWrapper(new BioReactorInfo()))
            .put("plasma_condenser", new MultiblockInfoRecipeWrapper(new PlasmaCondenserInfo()))
            .put("large_packager", new MultiblockInfoRecipeWrapper(new LargePackagerInfo()))
            .put("steam_grinder", new MultiblockInfoRecipeWrapper(new SteamGrinderInfo()))
            .put("steam_oven", new MultiblockInfoRecipeWrapper(new SteamOvenInfo()))
            .put("cosmic_ray_detector", new MultiblockInfoRecipeWrapper(new CosmicRayDetectorInfo()))
            .put("electric_implosion", new MultiblockInfoRecipeWrapper(new ElectricImplosionInfo()))
            .build();


    public static void registerRecipes(IModRegistry registry) {
        registry.addRecipes(multiblockRecipes.values(), "gregtech:multiblock_info");
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
    }
}
