package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.GAMaterials;
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
import gregtech.api.unification.material.Materials;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import gregtech.integration.jei.multiblock.infos.LargeTurbineInfo;
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

    public GAMultiblockInfoCategory(IJeiHelpers helpers) {
        this.background = helpers.getGuiHelper().createBlankDrawable(176, 166);
    }

    public static void registerRecipes(IModRegistry registry) {
        registry.addRecipes(Lists.newArrayList(
                new MultiblockInfoRecipeWrapper(new ElectricBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new CrackerUnitInfo()),
                new MultiblockInfoRecipeWrapper(new DieselEngineInfo()),
                new MultiblockInfoRecipeWrapper(new DistillationTowerInfo()),
                new MultiblockInfoRecipeWrapper(new ImplosionCompressorInfo()),
                new MultiblockInfoRecipeWrapper(new MultiSmelterInfo()),
                new MultiblockInfoRecipeWrapper(new VacuumFreezerInfo()),
                new MultiblockInfoRecipeWrapper(new PyrolyseOvenInfo()),
                new MultiblockInfoRecipeWrapper(new AssemblyLineInfo()),
                new MultiblockInfoRecipeWrapper(new FusionReactor1Info()),
                new MultiblockInfoRecipeWrapper(new FusionReactor2Info()),
                new MultiblockInfoRecipeWrapper(new FusionReactor3Info()),
                new MultiblockInfoRecipeWrapper(new ProcessingArrayInfo()),
                new MultiblockInfoRecipeWrapper(new LargeThermalCentrifugeInfo()),
                new MultiblockInfoRecipeWrapper(new LargeElectrolyzerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeCentrifugeInfo()),
                new MultiblockInfoRecipeWrapper(new LargeCuttingInfo()),
                new MultiblockInfoRecipeWrapper(new LargeMixerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeMultiUseInfo()),
                new MultiblockInfoRecipeWrapper(new LargeMaceratorInfo()),
                new MultiblockInfoRecipeWrapper(new LargeSifterInfo()),
                new MultiblockInfoRecipeWrapper(new LargeWashingPlantInfo()),
                new MultiblockInfoRecipeWrapper(new LargeWiremillInfo()),
                new MultiblockInfoRecipeWrapper(new LargeChemicalReactorInfo()),
                new MultiblockInfoRecipeWrapper(new LargeExtruderInfo()),
                new MultiblockInfoRecipeWrapper(new VolcanusInfo()),
                new MultiblockInfoRecipeWrapper(new LargeAssemblerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeBenderAndFormingInfo()),
                new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[0])),
                new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[1])),
                new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[2])),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_STEAM_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_GAS_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_PLASMA_TURBINE)),
                new MultiblockInfoRecipeWrapper(new HotCoolantTurbineInfo(GATileEntities.HOT_COOLANT_TURBINE)),
                new MultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_REACTOR)),
                new MultiblockInfoRecipeWrapper(new NuclearReactorInfo(GATileEntities.NUCLEAR_BREEDER)),
                new MultiblockInfoRecipeWrapper(new LargeCircuitAssemblyLineInfo()),
                new MultiblockInfoRecipeWrapper(new VoidMinerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeTransformerInfo()),
                new MultiblockInfoRecipeWrapper(new IndustrialPrimitiveBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new AdvancedDistillationTowerInfo()),
                new MultiblockInfoRecipeWrapper(new CryogenicFreezerInfo()),
                new MultiblockInfoRecipeWrapper(new ChemicalPlantInfo()),
                new MultiblockInfoRecipeWrapper(new LargeRocketEngineInfo()),
                new MultiblockInfoRecipeWrapper(new AlloyBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new LargeForgeHammerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeNaquadahReactorInfo()),
                new MultiblockInfoRecipeWrapper(new BatteryTowerInfo()),
                new MultiblockInfoRecipeWrapper(new HyperReactor1Info()),
                new MultiblockInfoRecipeWrapper(new HyperReactor2Info()),
                new MultiblockInfoRecipeWrapper(new HyperReactor3Info()),
                new MultiblockInfoRecipeWrapper(new FusionReactor4Info()),
                new MultiblockInfoRecipeWrapper(new GasCentrifugeInfo()),
                new MultiblockInfoRecipeWrapper(new QubitComputerInfo()),
                new MultiblockInfoRecipeWrapper(new DrillingRigInfo()),
                new MultiblockInfoRecipeWrapper(new StellarForgeInfo()),
                new MultiblockInfoRecipeWrapper(new LargeEngraverInfo()),
                new MultiblockInfoRecipeWrapper(new VoidMinerInfo2()),
                new MultiblockInfoRecipeWrapper(new VoidMinerInfo3())
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
        recipeWrapper.setRecipeLayout((RecipeLayout) recipeLayout);
    }
}
