package gregicadditions.jei;

import gregicadditions.Gregicality;
import gregicadditions.machines.multi.impl.HotCoolantRecipeLogic;
import gregicadditions.recipes.nuclear.GTHotCoolantRecipeWrapper;
import gregicadditions.recipes.nuclear.HotCoolantRecipeMap;
import gregicadditions.recipes.nuclear.HotCoolantRecipeMapCategory;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.items.MetaItems;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

@JEIPlugin
public class JEIGAPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GAMultiblockInfoCategory(registry.getJeiHelpers()));

        for (HotCoolantRecipeMap hotCoolantRecipeMap : HotCoolantRecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new HotCoolantRecipeMapCategory(hotCoolantRecipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
    }


    @Override
    public void register(IModRegistry registry) {
        GAMultiblockInfoCategory.registerRecipes(registry);
        registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(MetaItems.DATA_CONTROL_CIRCUIT_IV.getStackForm());

        for (HotCoolantRecipeMap hotCoolantRecipeMap : HotCoolantRecipeMap.getRecipeMaps()) {
            List<GTHotCoolantRecipeWrapper> recipeList = hotCoolantRecipeMap.getRecipeList().stream()
                    .map(GTHotCoolantRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(recipeList, Gregicality.MODID + ":" + hotCoolantRecipeMap.unlocalizedName);
        }

        for (ResourceLocation metaTileEntityId : GregTechAPI.META_TILE_ENTITY_REGISTRY.getKeys()) {
            MetaTileEntity metaTileEntity = GregTechAPI.META_TILE_ENTITY_REGISTRY.getObject(metaTileEntityId);
            //noinspection ConstantConditions
            if (metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null) != null) {
                IControllable workableCapability = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
                if (workableCapability instanceof HotCoolantRecipeLogic) {
                    HotCoolantRecipeMap recipeMap = ((HotCoolantRecipeLogic) workableCapability).recipeMap;
                    registry.addRecipeCatalyst(metaTileEntity.getStackForm(), Gregicality.MODID + ":" + recipeMap.unlocalizedName);
                }
            }
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        IRecipeRegistry recipeRegistry = jeiRuntime.getRecipeRegistry();
        recipeRegistry.hideRecipeCategory("gregtech:multiblock_info");
    }
}
