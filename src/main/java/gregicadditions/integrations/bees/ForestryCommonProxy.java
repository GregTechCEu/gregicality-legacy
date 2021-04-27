package gregicadditions.integrations.bees;

import binnie.extrabees.genetics.ExtraBeeDefinition;
import forestry.api.apiculture.BeeManager;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.recipes.ICentrifugeRecipe;
import forestry.api.recipes.ISqueezerRecipe;
import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.genetics.BeeDefinition;
import forestry.core.ModuleCore;
import forestry.core.config.Constants;
import forestry.core.fluids.Fluids;
import forestry.core.items.ItemBlockForestry;
import forestry.core.items.ItemFluidContainerForestry;
import gregicadditions.GAConfig;
import gregicadditions.client.ClientHandler;
import gregicadditions.integrations.bees.alveary.BlockGTAlveary;
import gregicadditions.integrations.bees.alveary.TileGTAlveary;
import gregicadditions.integrations.bees.effects.GTBeesEffects;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtech.common.items.MetaItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static gregicadditions.machines.GATileEntities.location;
import static gregicadditions.recipes.GACraftingComponents.*;
import static gregicadditions.recipes.GAMachineRecipeRemoval.removeRecipesByInputs;
import static gregicadditions.recipes.MachineCraftingRecipes.registerMachineRecipe;

@Mod.EventBusSubscriber()
public class ForestryCommonProxy {

    public static SimpleMachineMetaTileEntity[] BEE_ATTRACTOR = new SimpleMachineMetaTileEntity[8];

    @Optional.Method(modid = "forestry")
    public void preInit() {
        if (!GAConfig.GTBees.EnableGTCEBees || !Loader.isModLoaded("forestry")) return;
        BEE_ATTRACTOR[0] = GregTechAPI.registerMetaTileEntity(2759, new SimpleMachineMetaTileEntity(location("attractor.lv"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 1));
        BEE_ATTRACTOR[1] = GregTechAPI.registerMetaTileEntity(2760, new SimpleMachineMetaTileEntity(location("attractor.mv"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 2));
        BEE_ATTRACTOR[2] = GregTechAPI.registerMetaTileEntity(2761, new SimpleMachineMetaTileEntity(location("attractor.hv"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 3));
        BEE_ATTRACTOR[3] = GregTechAPI.registerMetaTileEntity(2762, new SimpleMachineMetaTileEntity(location("attractor.ev"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 4));
        BEE_ATTRACTOR[4] = GregTechAPI.registerMetaTileEntity(2763, new SimpleMachineMetaTileEntity(location("attractor.iv"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 5));
        BEE_ATTRACTOR[5] = GregTechAPI.registerMetaTileEntity(2764, new SimpleMachineMetaTileEntity(location("attractor.luv"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 6));
        BEE_ATTRACTOR[6] = GregTechAPI.registerMetaTileEntity(2765, new SimpleMachineMetaTileEntity(location("attractor.zpm"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 7));
        BEE_ATTRACTOR[7] = GregTechAPI.registerMetaTileEntity(2766, new SimpleMachineMetaTileEntity(location("attractor.uv"), GARecipeMaps.ATTRACTOR_RECIPES, ClientHandler.BEE_ATTRACTOR, 8));
    }

    @Optional.Method(modid = "forestry")
    @Mod.EventHandler
    public void init() {
        if (!GAConfig.GTBees.EnableGTCEBees || !Loader.isModLoaded("forestry")) return;
        GTBeesEffects.initEffects();
        GTBees.initBees();
        registerMachineRecipe(BEE_ATTRACTOR, "CGC", "FMF", "SPS", 'M', HULL, 'C', CABLE_SINGLE, 'G', GLASS, 'F', ModuleCore.getItems().impregnatedCasing.getItemStack(), 'S', CIRCUIT, 'P', PUMP);

        if (GAConfig.GTBees.GenerateCentrifugeRecipes)
            for (ICentrifugeRecipe recipe : RecipeManagers.centrifugeManager.recipes()) {
                SimpleRecipeBuilder builder = RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder();
                builder.inputs(recipe.getInput().copy());
                for (ItemStack stack : recipe.getAllProducts().keySet()) {
                    builder.chancedOutput(stack.copy(), (int) (recipe.getAllProducts().get(stack) * Recipe.getMaxChancedValue()), 1000);
                }
                builder.EUt(5);
                builder.duration(128);
                builder.buildAndRegister();
            }

        if (GAConfig.GTBees.GenerateExtractorRecipes) {
            for (ISqueezerRecipe recipe : RecipeManagers.squeezerManager.recipes()) {
                if (recipe.getResources().size() != 1 || recipe.getResources().get(0).getItem() instanceof ItemFluidContainerForestry)
                    continue;
                if (RecipeMaps.FLUID_EXTRACTION_RECIPES.findRecipe(Integer.MAX_VALUE, recipe.getResources(), Collections.emptyList(), Integer.MAX_VALUE) != null)
                    continue;
                SimpleRecipeBuilder builder = RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder();
                builder.inputs(recipe.getResources().get(0).copy());
                if (!recipe.getRemnants().isEmpty())
                    builder.chancedOutput(recipe.getRemnants().copy(), (int) (recipe.getRemnantsChance() * Recipe.getMaxChancedValue()), 1000);
                if (recipe.getFluidOutput() != null) builder.fluidOutputs(recipe.getFluidOutput());
                builder.EUt(5);
                builder.duration(128);
                builder.buildAndRegister();
            }

            //Fix Seed Oil Recipe
            removeRecipesByInputs(RecipeMaps.FLUID_EXTRACTION_RECIPES, new ItemStack(Items.WHEAT_SEEDS));
            removeRecipesByInputs(RecipeMaps.FLUID_EXTRACTION_RECIPES, new ItemStack(Items.MELON_SEEDS));
            removeRecipesByInputs(RecipeMaps.FLUID_EXTRACTION_RECIPES, new ItemStack(Items.PUMPKIN_SEEDS));
        }
        //Bees
        List<ItemStack> allFlowers = OreDictionary.getOres("flower").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        for (ItemStack stack : allFlowers)
            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(GTUtility.copyAmount(1, stack)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.FOREST.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.FOREST.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MEADOWS.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MEADOWS.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();

        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MARSHY.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.SNOW)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.WINTRY.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.WINTRY.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Items.CHORUS_FRUIT)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.ENDED.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.ENDED.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.CACTUS)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MODEST.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MODEST.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.VINE)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.TROPICAL.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.TROPICAL.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        if (Loader.isModLoaded("extrabees") && ExtraBeeDefinition.WATER.getGenome() != null) {
            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.WATERLILY)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.WATER.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.WATER.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();

            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(OreDictUnifier.get(OrePrefix.stone, null)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.ROCK.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.ROCK.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();

            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Items.NETHER_WART)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.BASALT.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(ExtraBeeDefinition.BASALT.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();
        } else {
            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Blocks.WATERLILY)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();

            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(OreDictUnifier.get(OrePrefix.stone, null)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();

            GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(new ItemStack(Items.NETHER_WART)).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                    .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                    .EUt(26).duration(200).buildAndRegister();
        }


        GARecipeMaps.ATTRACTOR_RECIPES.recipeBuilder().notConsumable(MetaItems.COIN_GOLD_ANCIENT.getStackForm()).fluidInputs(Fluids.SEED_OIL.getFluid(100))
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MONASTIC.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.MONASTIC.getIndividual(), EnumBeeType.DRONE), 300, 5000)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.STEADFAST.getIndividual(), EnumBeeType.PRINCESS), 1000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.STEADFAST.getIndividual(), EnumBeeType.DRONE), 3000, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.PRINCESS), 100, 500)
                .chancedOutput(BeeManager.beeRoot.getMemberStack(BeeDefinition.VALIANT.getIndividual(), EnumBeeType.DRONE), 300, 500)
                .EUt(26).duration(200).buildAndRegister();

        GTMachineRecipes.postInit();

    }

    public static BlockGTAlveary GT_ALVEARY;


    @Optional.Method(modid = "forestry")
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        if (!GAConfig.GTBees.EnableGTCEBees || !Loader.isModLoaded("forestry")) return;
        IForgeRegistry<Block> registry = event.getRegistry();
        GT_ALVEARY = new BlockGTAlveary();
        GT_ALVEARY.setRegistryName(Constants.MOD_ID, "gt_alveary");
        registry.register(GT_ALVEARY);
        GameRegistry.registerTileEntity(TileGTAlveary.class, new ResourceLocation(Constants.MOD_ID, "gt_alveary"));
    }

    @Optional.Method(modid = "forestry")
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        if (!GAConfig.GTBees.EnableGTCEBees || !Loader.isModLoaded("forestry")) return;
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(GTCombs.combItem);
        registry.register(new ItemBlockForestry<>(GT_ALVEARY).setRegistryName(GT_ALVEARY.getRegistryName()));
    }

    @Optional.Method(modid = "forestry")
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (!GAConfig.GTBees.EnableGTCEBees || !Loader.isModLoaded("forestry")) return;
        ForestryMachineRecipes.init();
    }
}