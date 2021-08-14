package gregicadditions.jei;

import gregicadditions.Gregicality;
import gregicadditions.machines.multi.impl.HotCoolantRecipeLogic;
import gregicadditions.machines.multi.simple.MultiRecipeMapMultiblockController;
import gregicadditions.recipes.impl.nuclear.GTHotCoolantRecipeWrapper;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMapCategory;
import gregicadditions.recipes.compat.jei.GADrillingRigCategory;
import gregicadditions.recipes.compat.jei.GADrillingRigRecipeWrapper;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.*;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.machines.GATileEntities.FLUID_DRILLING_PLANT;

@JEIPlugin
public class JEIGAPlugin implements IModPlugin {

    public static IJeiRuntime jeiRuntime;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GAMultiblockInfoCategory(registry.getJeiHelpers()));
        registry.addRecipeCategories(new GADrillingRigCategory(registry.getJeiHelpers().getGuiHelper()));
        for (HotCoolantRecipeMap hotCoolantRecipeMap : HotCoolantRecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new HotCoolantRecipeMapCategory(hotCoolantRecipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
    }


    @Override
    public void register(@Nonnull IModRegistry registry) {
        GAMultiblockInfoCategory.registerRecipes(registry);

        // Register Hot Coolant RecipeMaps
        for (HotCoolantRecipeMap hotCoolantRecipeMap : HotCoolantRecipeMap.getRecipeMaps()) {
            List<GTHotCoolantRecipeWrapper> recipeList = hotCoolantRecipeMap.getRecipeList().stream()
                    .map(GTHotCoolantRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(recipeList, Gregicality.MODID + ":" + hotCoolantRecipeMap.unlocalizedName);
        }

        // Register Fluid Drilling Recipes and Catalysts
        List<IRecipeWrapper> fluidRecipe = PumpjackHandler.reservoirList.entrySet().stream()
                .map(reservoirTypeIntegerEntry -> new GADrillingRigRecipeWrapper(reservoirTypeIntegerEntry.getKey(), reservoirTypeIntegerEntry.getValue()))
                .collect(Collectors.toList());
        registry.addRecipes(fluidRecipe, Gregicality.MODID + ":drilling_rig");
        registry.addRecipeCatalyst(FLUID_DRILLING_PLANT[0].getStackForm(), Gregicality.MODID + ":drilling_rig");
        registry.addRecipeCatalyst(FLUID_DRILLING_PLANT[1].getStackForm(), Gregicality.MODID + ":drilling_rig");
        registry.addRecipeCatalyst(FLUID_DRILLING_PLANT[2].getStackForm(), Gregicality.MODID + ":drilling_rig");

        // todo clean this up
        // Register something?
        for (ResourceLocation metaTileEntityId : GregTechAPI.META_TILE_ENTITY_REGISTRY.getKeys()) {
            MetaTileEntity metaTileEntity = GregTechAPI.META_TILE_ENTITY_REGISTRY.getObject(metaTileEntityId);
            //noinspection ConstantConditions
            if (metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null) != null) {
                IControllable workableCapability = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
                if (workableCapability instanceof HotCoolantRecipeLogic) {
                    HotCoolantRecipeMap recipeMap = ((HotCoolantRecipeLogic) workableCapability).recipeMap;
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), Gregicality.MODID + ":" + recipeMap.unlocalizedName);
                } else if (workableCapability instanceof AbstractRecipeLogic) {
                    if (metaTileEntity instanceof MultiRecipeMapMultiblockController) {
                        for (RecipeMap<?> recipeMap : ((MultiRecipeMapMultiblockController) metaTileEntity).getRecipeMaps()) {
                            registry.addRecipeCatalyst(metaTileEntity.getStackForm(), Gregicality.MODID + ":" + recipeMap.unlocalizedName);
                        }
                    } else { // todo is this else needed?
                        RecipeMap<?> recipeMap = ((AbstractRecipeLogic) workableCapability).recipeMap;
                        registry.addRecipeCatalyst(metaTileEntity.getStackForm(), Gregicality.MODID + ":" + recipeMap.unlocalizedName);
                    }
                }
            }
        }

        // Register Multiblock Info Pages
        GAMultiblockInfoCategory.getRecipes().values().forEach(v -> {
            MultiblockInfoPage infoPage = v.getInfoPage();
            registry.addIngredientInfo(infoPage.getController().getStackForm(),
                    VanillaTypes.ITEM,
                    infoPage.getDescription());
        });
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {
        JEIGAPlugin.jeiRuntime = jeiRuntime;
    }
}
