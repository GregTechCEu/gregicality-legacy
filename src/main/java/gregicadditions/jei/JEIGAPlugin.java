package gregicadditions.jei;

import gregicadditions.GAConfig;
import gregicadditions.GAEnums;
import gregicadditions.Gregicality;
import gregicadditions.machines.multi.impl.HotCoolantRecipeLogic;
import gregicadditions.machines.multi.simple.MultiRecipeMapMultiblockController;
import gregicadditions.recipes.impl.nuclear.GTHotCoolantRecipeWrapper;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMapCategory;
import gregicadditions.recipes.compat.jei.GADrillingRigCategory;
import gregicadditions.recipes.compat.jei.GADrillingRigRecipeWrapper;
import gregicadditions.recipes.compat.jei.GARecipeMapCategory;
import gregicadditions.recipes.compat.jei.GARecipeWrapper;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;

import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.machines.GATileEntities.FLUID_DRILLING_PLANT;

@JEIPlugin
public class JEIGAPlugin implements IModPlugin {

    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    public static IJeiRuntime jeiRuntime;

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new GAMultiblockInfoCategory(registry.getJeiHelpers()));

        for (HotCoolantRecipeMap hotCoolantRecipeMap : HotCoolantRecipeMap.getRecipeMaps()) {
            registry.addRecipeCategories(new HotCoolantRecipeMapCategory(hotCoolantRecipeMap, registry.getJeiHelpers().getGuiHelper()));
        }
        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            if(!recipeMap.isHidden)
                registry.addRecipeCategories(new GARecipeMapCategory(recipeMap, registry.getJeiHelpers().getGuiHelper()));
        }

        registry.addRecipeCategories(new GADrillingRigCategory(registry.getJeiHelpers().getGuiHelper()));

    }


    @Override
    public void register(IModRegistry registry) {
        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();
        GAMultiblockInfoCategory.registerRecipes(registry);
        registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(MetaItems.DATA_CONTROL_CIRCUIT_IV.getStackForm());

        for (HotCoolantRecipeMap hotCoolantRecipeMap : HotCoolantRecipeMap.getRecipeMaps()) {
            List<GTHotCoolantRecipeWrapper> recipeList = hotCoolantRecipeMap.getRecipeList().stream()
                    .map(GTHotCoolantRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(recipeList, Gregicality.MODID + ":" + hotCoolantRecipeMap.unlocalizedName);
        }

        List<IRecipeWrapper> fluidRecipe = PumpjackHandler.reservoirList.entrySet().stream()
                .map(reservoirTypeIntegerEntry -> new GADrillingRigRecipeWrapper(reservoirTypeIntegerEntry.getKey(), reservoirTypeIntegerEntry.getValue()))
                .collect(Collectors.toList());
        registry.addRecipes(fluidRecipe, Gregicality.MODID + ":drilling_rig");
        registry.addRecipeCatalyst(FLUID_DRILLING_PLANT[0].getStackForm(), Gregicality.MODID + ":drilling_rig");
        registry.addRecipeCatalyst(FLUID_DRILLING_PLANT[1].getStackForm(), Gregicality.MODID + ":drilling_rig");
        registry.addRecipeCatalyst(FLUID_DRILLING_PLANT[2].getStackForm(), Gregicality.MODID + ":drilling_rig");

        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            List<GARecipeWrapper> recipesList = recipeMap.getRecipeList()
                    .stream().filter(recipe -> !recipe.isHidden() && recipe.hasValidInputsForDisplay())
                    .map(r -> new GARecipeWrapper(r))
                    .collect(Collectors.toList());
            registry.addRecipes(recipesList, Gregicality.MODID + ":" + recipeMap.unlocalizedName);
        }

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
                    } else {
                        RecipeMap<?> recipeMap = ((AbstractRecipeLogic) workableCapability).recipeMap;
                        registry.addRecipeCatalyst(metaTileEntity.getStackForm(), Gregicality.MODID + ":" + recipeMap.unlocalizedName);
                    }
                }
            }
        }

        //Multiblock info page registration
        GAMultiblockInfoCategory.getRecipes().values().forEach(v -> {
            MultiblockInfoPage infoPage = v.getInfoPage();
            registry.addIngredientInfo(infoPage.getController().getStackForm(),
                    VanillaTypes.ITEM,
                    infoPage.getDescription());
        });

        //Hide Rich, Poor, and Pure ores in JEI
        if(GAConfig.Misc.oreVariants && GAConfig.Misc.hideOreVariants) {
            IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

            for(Material mat : Material.MATERIAL_REGISTRY) {
                if(mat instanceof DustMaterial && mat.hasFlag(DustMaterial.MatFlags.GENERATE_ORE)) {

                    //Hide the ore variants based on defined orePrefixes. JEI will error on an empty itemStack
                    for(OrePrefix prefix : GAEnums.PURE_ORES) {
                        ItemStack ore = OreDictUnifier.get(prefix, mat);
                        if(!ore.isEmpty()) {
                            blacklist.addIngredientToBlacklist(ore);
                        }
                    }
                    for(OrePrefix prefix : GAEnums.RICH_ORES) {
                        ItemStack ore = OreDictUnifier.get(prefix, mat);
                        if(!ore.isEmpty()) {
                            blacklist.addIngredientToBlacklist(ore);
                        }
                    }
                    for(OrePrefix prefix : GAEnums.POOR_ORES) {
                        ItemStack ore = OreDictUnifier.get(prefix, mat);
                        if(!ore.isEmpty()) {
                            blacklist.addIngredientToBlacklist(ore);
                        }
                    }
                    //This config option will generate ores for all stone types, even those that do not get prefixes in GAEnums
                    //These ores do not have unique prefixes in GTCE, so we cannot look up by OrePrefix
                    if(GAConfig.Misc.oreVariantsStoneTypes) {
                        String name = mat.toString();

                        String[] splitName = name.split("_");
                        StringBuilder wholeName = new StringBuilder();
                        for(int i = 0; i < splitName.length; i++) {
                            splitName[i] = splitName[i].substring(0, 1).toUpperCase() + splitName[i].substring(1);
                            wholeName.append(splitName[i]);
                        }


                        List<ItemStack> extraPureOres = OreDictUnifier.getAllWithOreDictionaryName("orePure" + wholeName);
                        List<ItemStack> extraPureSandOres = OreDictUnifier.getAllWithOreDictionaryName("orePureSand" + wholeName);

                        List<ItemStack> extraPoorOres = OreDictUnifier.getAllWithOreDictionaryName("orePoor" + wholeName);
                        List<ItemStack> extraPoorSandOres = OreDictUnifier.getAllWithOreDictionaryName("orePoorSand" + wholeName);

                        List<ItemStack> extraRichOres = OreDictUnifier.getAllWithOreDictionaryName("oreRich" + wholeName);
                        List<ItemStack> extraRichSandOres = OreDictUnifier.getAllWithOreDictionaryName("oreRichSand" + wholeName);


                        if(!extraPureOres.isEmpty()) {
                            extraPureOres.forEach(blacklist::addIngredientToBlacklist);
                        }

                        if(!extraPureSandOres.isEmpty()) {
                            extraPureSandOres.forEach(blacklist::addIngredientToBlacklist);
                        }

                        if(!extraPoorOres.isEmpty()) {
                            extraPoorOres.forEach(blacklist::addIngredientToBlacklist);
                        }

                        if(!extraPoorSandOres.isEmpty()) {
                            extraPoorSandOres.forEach(blacklist::addIngredientToBlacklist);
                        }

                        if(!extraRichOres.isEmpty()) {
                            extraRichOres.forEach(blacklist::addIngredientToBlacklist);
                        }

                        if(!extraRichSandOres.isEmpty()) {
                            extraRichSandOres.forEach(blacklist::addIngredientToBlacklist);
                        }

                    }
                }
            }
        }

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        this.jeiRuntime = jeiRuntime;
        IRecipeRegistry recipeRegistry = jeiRuntime.getRecipeRegistry();
        IRecipeCategory recipeCategory = recipeRegistry.getRecipeCategory("gregtech:multiblock_info");

        for (RecipeMap<?> recipeMap : RecipeMap.getRecipeMaps()) {
            recipeRegistry.hideRecipeCategory(GTValues.MODID + ":" + recipeMap.unlocalizedName);
        }


        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.DIESEL_ENGINE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.CRACKER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.DISTILLATION_TOWER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.LARGE_GAS_TURBINE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.MULTI_FURNACE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.VACUUM_FREEZER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(MetaTileEntities.PYROLYSE_OVEN.getStackForm());


        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.DIESEL_ENGINE.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.CRACKER.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.DISTILLATION_TOWER.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.LARGE_GAS_TURBINE.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.MULTI_FURNACE.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.VACUUM_FREEZER.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));
        recipeRegistry.getRecipeWrappers(recipeCategory, recipeRegistry.createFocus(IFocus.Mode.OUTPUT, MetaTileEntities.PYROLYSE_OVEN.getStackForm())).forEach(iRecipeWrapper -> recipeRegistry.hideRecipe((IRecipeWrapper) iRecipeWrapper, "gregtech:multiblock_info"));

    }
}
