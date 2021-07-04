package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.jei.multi.*;
import gregicadditions.jei.multi.advance.*;
import gregicadditions.jei.multi.miner.LargeMinerInfo;
import gregicadditions.jei.multi.miner.VoidMinerInfo;
import gregicadditions.jei.multi.miner.VoidMinerInfo2;
import gregicadditions.jei.multi.miner.VoidMinerInfo3;
import gregicadditions.jei.multi.nuclear.GasCentrifugeInfo;
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

    public static void registerRecipes(IModRegistry registry) {
        registry.addRecipes(Lists.newArrayList(
                new MultiblockInfoRecipeWrapper(new CentralMonitorInfo()),
                new MultiblockInfoRecipeWrapper(new ElectricBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new CrackerUnitInfo()),
                new MultiblockInfoRecipeWrapper(new DieselEngineInfo()),
                new LargeMultiblockInfoRecipeWrapper(new DistillationTowerInfo()),
                new MultiblockInfoRecipeWrapper(new ImplosionCompressorInfo()),
                new MultiblockInfoRecipeWrapper(new MultiSmelterInfo()),
                new MultiblockInfoRecipeWrapper(new VacuumFreezerInfo()),
                new MultiblockInfoRecipeWrapper(new PyrolyseOvenInfo()),
                new MultiblockInfoRecipeWrapper(new AssemblyLineInfo()),
                new LargeMultiblockInfoRecipeWrapper(new FusionReactor1Info()),
                new LargeMultiblockInfoRecipeWrapper(new FusionReactor2Info()),
                new LargeMultiblockInfoRecipeWrapper(new FusionReactor3Info()),
                new MultiblockInfoRecipeWrapper(new ProcessingArrayInfo()),
                new MultiblockInfoRecipeWrapper(new LargeThermalCentrifugeInfo()),
                new MultiblockInfoRecipeWrapper(new LargeElectrolyzerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()),
                new MultiblockInfoRecipeWrapper(new LargeCuttingInfo()),
                new MultiblockInfoRecipeWrapper(new LargeMixerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeMultiUseInfo()),
                new LargeMultiblockInfoRecipeWrapper(new LargeMaceratorInfo()),
                new MultiblockInfoRecipeWrapper(new LargeSifterInfo()),
                new MultiblockInfoRecipeWrapper(new LargeWashingPlantInfo()),
                new MultiblockInfoRecipeWrapper(new LargeWiremillInfo()),
                new MultiblockInfoRecipeWrapper(new LargeChemicalReactorInfo()),
                new MultiblockInfoRecipeWrapper(new LargeExtruderInfo()),
                new MultiblockInfoRecipeWrapper(new VolcanusInfo()),
                new LargeMultiblockInfoRecipeWrapper(new LargeAssemblerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeBenderAndFormingInfo()),
                new LargeMultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[0])),
                new LargeMultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[1])),
                new LargeMultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[2])),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_STEAM_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_GAS_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_PLASMA_TURBINE)),
                new LargeMultiblockInfoRecipeWrapper(new HotCoolantTurbineInfo(GATileEntities.HOT_COOLANT_TURBINE)),
                new LargeMultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_REACTOR)),
                new LargeMultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_BREEDER)),
                new LargeMultiblockInfoRecipeWrapper(new LargeCircuitAssemblyLineInfo()),
                new LargeMultiblockInfoRecipeWrapper(new VoidMinerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeTransformerInfo()),
                new MultiblockInfoRecipeWrapper(new IndustrialPrimitiveBlastFurnaceInfo()),
                new LargeMultiblockInfoRecipeWrapper(new AdvancedDistillationTowerInfo()),
                new MultiblockInfoRecipeWrapper(new CryogenicFreezerInfo()),
                new LargeMultiblockInfoRecipeWrapper(new ChemicalPlantInfo()),
                new LargeMultiblockInfoRecipeWrapper(new LargeRocketEngineInfo()),
                new MultiblockInfoRecipeWrapper(new AlloyBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new LargeForgeHammerInfo()),
                new LargeMultiblockInfoRecipeWrapper(new LargeNaquadahReactorInfo()),
                new LargeMultiblockInfoRecipeWrapper(new BatteryTowerInfo()),
                new LargeMultiblockInfoRecipeWrapper(new HyperReactor1Info()),
                new LargeMultiblockInfoRecipeWrapper(new HyperReactor2Info()),
                new LargeMultiblockInfoRecipeWrapper(new HyperReactor3Info()),
                new LargeMultiblockInfoRecipeWrapper(new FusionReactor4Info()),
                new LargeMultiblockInfoRecipeWrapper(new GasCentrifugeInfo()),
                new MultiblockInfoRecipeWrapper(new QubitComputerInfo()),
                new LargeMultiblockInfoRecipeWrapper(new DrillingRigInfo()),
                new LargeMultiblockInfoRecipeWrapper(new StellarForgeInfo()),
                new MultiblockInfoRecipeWrapper(new LargeEngraverInfo()),
                new LargeMultiblockInfoRecipeWrapper(new VoidMinerInfo2()),
                new LargeMultiblockInfoRecipeWrapper(new VoidMinerInfo3()),
                new LargeMultiblockInfoRecipeWrapper(new BioReactorInfo()),
                new MultiblockInfoRecipeWrapper(new PlasmaCondenserInfo()),
                new MultiblockInfoRecipeWrapper(new LargePackagerInfo()),
                new MultiblockInfoRecipeWrapper(new SteamGrinderInfo()),
                new MultiblockInfoRecipeWrapper(new SteamOvenInfo()),
                new MultiblockInfoRecipeWrapper(new CosmicRayDetectorInfo()),
                new MultiblockInfoRecipeWrapper(new CVDUnitInfo())
        ), "gregtech:multiblock_info");
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
