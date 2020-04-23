package gregicadditions.jei;

import com.google.common.collect.Lists;
import gregicadditions.jei.multi.*;
import gregicadditions.jei.multi.advance.*;
import gregicadditions.jei.multi.miner.*;
import gregicadditions.jei.multi.nuclear.BoilingWaterReactorInfo;
import gregicadditions.jei.multi.override.CrackerUnitInfo;
import gregicadditions.jei.multi.override.DieselEngineInfo;
import gregicadditions.jei.multi.override.DistillationTowerInfo;
import gregicadditions.jei.multi.override.ElectricBlastFurnaceInfo;
import gregicadditions.jei.multi.override.ImplosionCompressorInfo;
import gregicadditions.jei.multi.override.MultiSmelterInfo;
import gregicadditions.jei.multi.override.VacuumFreezerInfo;
import gregicadditions.jei.multi.simple.*;
import gregicadditions.machines.GATileEntities;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoRecipeWrapper;
import gregtech.integration.jei.multiblock.infos.*;
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
                new MultiblockInfoRecipeWrapper(new PrimitiveBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new PyrolyzeOvenInfo()),
                new MultiblockInfoRecipeWrapper(new CokeOvenInfo()),
                new MultiblockInfoRecipeWrapper(new CrackerUnitInfo()),
                new MultiblockInfoRecipeWrapper(new DieselEngineInfo()),
                new MultiblockInfoRecipeWrapper(new DistillationTowerInfo()),
                new MultiblockInfoRecipeWrapper(new ElectricBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new ImplosionCompressorInfo()),
                new MultiblockInfoRecipeWrapper(new MultiSmelterInfo()),
                new MultiblockInfoRecipeWrapper(new VacuumFreezerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeBoilerInfo(MetaTileEntities.LARGE_BRONZE_BOILER)),
                new MultiblockInfoRecipeWrapper(new LargeBoilerInfo(MetaTileEntities.LARGE_STEEL_BOILER)),
                new MultiblockInfoRecipeWrapper(new LargeBoilerInfo(MetaTileEntities.LARGE_TITANIUM_BOILER)),
                new MultiblockInfoRecipeWrapper(new LargeBoilerInfo(MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER)),
                new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[0])),
                new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[1])),
                new MultiblockInfoRecipeWrapper(new LargeMinerInfo(GATileEntities.LARGE_MINER[2])),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_STEAM_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_GAS_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_PLASMA_TURBINE)),
                new MultiblockInfoRecipeWrapper(new LargeTurbineInfo(GATileEntities.LARGE_HIGH_PRESSURE_STEAM_TURBINE)),
                new MultiblockInfoRecipeWrapper(new BoilingWaterReactorInfo(GATileEntities.BOILING_WATER_URANIUM_REACTOR)),
                new MultiblockInfoRecipeWrapper(new BoilingWaterReactorInfo(GATileEntities.BOILING_WATER_PLUTONIUM_REACTOR)),
                new MultiblockInfoRecipeWrapper(new BoilingWaterReactorInfo(GATileEntities.BOILING_WATER_THORIUM_REACTOR)),
                new MultiblockInfoRecipeWrapper(new LargeCircuitAssemblyLineInfo()),
                new MultiblockInfoRecipeWrapper(new VoidMinerInfo()),
                new MultiblockInfoRecipeWrapper(new LargeTransformerInfo()),
                new MultiblockInfoRecipeWrapper(new IndustrialPrimitiveBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new AdvancedDistillationTowerInfo()),
                new MultiblockInfoRecipeWrapper(new CryogenicFreezerInfo()),
                new MultiblockInfoRecipeWrapper(new ChemicalPlantInfo()),
                new MultiblockInfoRecipeWrapper(new LargeRocketEngineInfo()),
                new MultiblockInfoRecipeWrapper(new AlloyBlastFurnaceInfo()),
                new MultiblockInfoRecipeWrapper(new LargeForgeHammerInfo())
        ), "gtadditions:multiblock_info2");
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
