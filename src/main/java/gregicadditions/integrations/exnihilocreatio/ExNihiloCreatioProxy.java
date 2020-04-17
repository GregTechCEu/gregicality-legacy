package gregicadditions.integrations.exnihilocreatio;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.Siftable;
import gregicadditions.GAConfig;
import gregicadditions.GregicAdditions;
import gregicadditions.integrations.exnihilocreatio.items.ExNihiloEnums;
import gregicadditions.integrations.exnihilocreatio.items.ExNihiloItems;
import gregicadditions.integrations.exnihilocreatio.items.ExNihiloMetaItems;
import gregicadditions.integrations.exnihilocreatio.items.ExNihiloPebble;
import gregicadditions.integrations.exnihilocreatio.machines.MetaTileEntityRockBreaker;
import gregicadditions.integrations.exnihilocreatio.machines.MetaTileEntitySieve;
import gregicadditions.integrations.exnihilocreatio.machines.SteamRockBreaker;
import gregicadditions.integrations.exnihilocreatio.machines.SteamSieve;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static gregicadditions.machines.GATileEntities.location;
import static gregicadditions.recipes.GACraftingComponents.*;
import static gregicadditions.recipes.MachineCraftingRecipes.registerMachineRecipe;

@Mod.EventBusSubscriber(modid = GregicAdditions.MODID)
public class ExNihiloCreatioProxy {

    public static SimpleMachineMetaTileEntity[] SIEVES = new SimpleMachineMetaTileEntity[8];
    public static MetaTileEntityRockBreaker[] ROCK_BREAKER = new MetaTileEntityRockBreaker[8];

    public static SteamSieve STEAM_SIEVE;
    public static SteamRockBreaker STEAM_BREAKER;


    @Optional.Method(modid = "exnihilocreatio")
    @Mod.EventHandler
    public void preInit() {
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            ExNihiloEnums.preInit();
            ExNihiloMetaItems.preInit();
            new ExNihiloItems();
            SieveDrops.addSieveRecipe();
            ExNihiloRegistryManager.registerSieveDefaultRecipeHandler(new GASieveDrops());
            SIEVES[0] = GregTechAPI.registerMetaTileEntity(2224, new MetaTileEntitySieve(location("sieve.lv"), 1));
            SIEVES[1] = GregTechAPI.registerMetaTileEntity(2225, new MetaTileEntitySieve(location("sieve.mv"), 2));
            SIEVES[2] = GregTechAPI.registerMetaTileEntity(2226, new MetaTileEntitySieve(location("sieve.hv"), 3));
            SIEVES[3] = GregTechAPI.registerMetaTileEntity(2227, new MetaTileEntitySieve(location("sieve.ev"), 4));
            if (GAConfig.exNihilo.highTierSieve) {
                SIEVES[4] = GregTechAPI.registerMetaTileEntity(2228, new MetaTileEntitySieve(location("sieve.iv"), 5));
                SIEVES[5] = GregTechAPI.registerMetaTileEntity(2229, new MetaTileEntitySieve(location("sieve.luv"), 6));
                SIEVES[6] = GregTechAPI.registerMetaTileEntity(2230, new MetaTileEntitySieve(location("sieve.zpm"), 7));
                SIEVES[7] = GregTechAPI.registerMetaTileEntity(2231, new MetaTileEntitySieve(location("sieve.uv"), 8));
            }

            ROCK_BREAKER[0] = GregTechAPI.registerMetaTileEntity(4000, new MetaTileEntityRockBreaker(location("rock_breaker.lv"), 1));
            ROCK_BREAKER[1] = GregTechAPI.registerMetaTileEntity(4001, new MetaTileEntityRockBreaker(location("rock_breaker.mv"), 2));
            ROCK_BREAKER[2] = GregTechAPI.registerMetaTileEntity(4002, new MetaTileEntityRockBreaker(location("rock_breaker.hv"), 3));
            ROCK_BREAKER[3] = GregTechAPI.registerMetaTileEntity(4003, new MetaTileEntityRockBreaker(location("rock_breaker.ev"), 4));
            if (GAConfig.exNihilo.highTierSieve) {
                ROCK_BREAKER[4] = GregTechAPI.registerMetaTileEntity(4004, new MetaTileEntityRockBreaker(location("rock_breaker.iv"), 5));
                ROCK_BREAKER[5] = GregTechAPI.registerMetaTileEntity(4005, new MetaTileEntityRockBreaker(location("rock_breaker.luv"), 6));
                ROCK_BREAKER[6] = GregTechAPI.registerMetaTileEntity(4006, new MetaTileEntityRockBreaker(location("rock_breaker.zpm"), 7));
                ROCK_BREAKER[7] = GregTechAPI.registerMetaTileEntity(4007, new MetaTileEntityRockBreaker(location("rock_breaker.uv"), 8));
            }

            STEAM_BREAKER = GregTechAPI.registerMetaTileEntity(2767, new SteamRockBreaker(location("rock_breaker.steam")));
            STEAM_SIEVE = GregTechAPI.registerMetaTileEntity(2749, new SteamSieve(location("sieve.steam"), false));

        }

    }

    @Optional.Method(modid = "exnihilocreatio")
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            MinecraftForge.EVENT_BUS.register(new StoneGenEvents());
            registerMachineRecipe(SIEVES, "CPC", "FMF", "OSO", 'M', HULL, 'C', CIRCUIT, 'O', CABLE_SINGLE, 'F', CONVEYOR, 'S', new ItemStack(ModBlocks.sieve), 'P', PISTON);
            registerMachineRecipe(ROCK_BREAKER, "CPC", "CMC", "GGG", 'M', HULL, 'C', PIPE, 'G', GLASS, 'P', PISTON);
            ModHandler.addShapedRecipe("steam_sieve", STEAM_SIEVE.getStackForm(), "BPB", "BMB", "BSB", 'B', "pipeSmallBronze", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'S', new ItemStack(ModBlocks.sieve), 'P', new ItemStack(Blocks.PISTON));
            ModHandler.addShapedRecipe("steam_rock_breaker", STEAM_BREAKER.getStackForm(), "BPB", "BMB", "GGG", 'P', new ItemStack(Blocks.PISTON), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.BRONZE_HULL), 'B', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'G', new ItemStack(Blocks.GLASS));
            for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
                for (ItemStack stack : recipe.getSievables()) {
                    SimpleRecipeBuilder builder = GARecipeMaps.SIEVE_RECIPES.recipeBuilder();
                    builder.notConsumable(recipe.getMesh()).inputs(stack);
                    for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(stack)) {
                        if (siftable.getMeshLevel() == recipe.getMesh().getMetadata())
                            builder.chancedOutput(siftable.getDrop().getItemStack(), (int) (siftable.getChance() * (float) Recipe.getMaxChancedValue()), 500);
                    }
                    builder.duration(100).EUt(4);
                    builder.buildAndRegister();
                }
            }
            ModHandler.addShapedRecipe("pebbles_to_basalt", MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', ExNihiloPebble.getPebbleStack("basalt"));
            ModHandler.addShapedRecipe("pebbles_to_black_granite", MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', ExNihiloPebble.getPebbleStack("black_granite"));
            ModHandler.addShapedRecipe("pebbles_to_marble", MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', ExNihiloPebble.getPebbleStack("marble"));
            ModHandler.addShapedRecipe("pebbles_to_red_granite", MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.CRACKED), "PP", "PP", 'P', ExNihiloPebble.getPebbleStack("red_granite"));

            ModHandler.addSmeltingRecipe(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.BASALT, StoneBlock.ChiselingVariant.NORMAL));
            ModHandler.addSmeltingRecipe(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.BLACK_GRANITE, StoneBlock.ChiselingVariant.NORMAL));
            ModHandler.addSmeltingRecipe(MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.MINERAL.getItemVariant(BlockMineral.MineralVariant.MARBLE, StoneBlock.ChiselingVariant.NORMAL));
            ModHandler.addSmeltingRecipe(MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.CRACKED), MetaBlocks.GRANITE.getItemVariant(BlockGranite.GraniteVariant.RED_GRANITE, StoneBlock.ChiselingVariant.NORMAL));
        }
    }

    @Optional.Method(modid = "exnihilocreatio")
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        ExNihiloCreatioProxy.register();
    }

    public static void register() {
        OrePrefix.valueOf("oreChunk").addProcessingHandler(DustMaterial.class, ExNihiloCreatioProxy::processOre);
        OrePrefix.valueOf("oreEnderChunk").addProcessingHandler(DustMaterial.class, ExNihiloCreatioProxy::processOre);
        OrePrefix.valueOf("oreNetherChunk").addProcessingHandler(DustMaterial.class, ExNihiloCreatioProxy::processOre);
        OrePrefix.valueOf("oreSandyChunk").addProcessingHandler(DustMaterial.class, ExNihiloCreatioProxy::processOre);
    }

    public static void processOre(OrePrefix orePrefix, DustMaterial material) {
        ItemStack ingotStack = null;
        DustMaterial smeltingMaterial = material;
        if (material.directSmelting != null) {
            smeltingMaterial = material.directSmelting;
        }
        if (smeltingMaterial instanceof IngotMaterial)
            ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        else if (smeltingMaterial instanceof GemMaterial)
            ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);

        if (ingotStack != null) {
            ingotStack.setCount(material.oreMultiplier);

            if (!ingotStack.isEmpty() && doesMaterialUseNormalFurnace(material)) {
                ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
            }
        }
    }

    private static boolean doesMaterialUseNormalFurnace(Material material) {
        return !(material instanceof IngotMaterial) ||
                ((IngotMaterial) material).blastFurnaceTemperature <= 0;
    }


}
