package gregicadditions;

import com.blakebr0.mysticalagradditions.MysticalAgradditions;
import gregicadditions.blocks.GAMetalCasingItemBlock;
import gregicadditions.blocks.GAOreItemBlock;
import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.integrations.mysticalagriculture.items.MysticalAgricultureItems;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.network.IPSaveData;
import gregicadditions.network.MessageReservoirListSync;
import gregicadditions.network.NetworkHandler;
import gregicadditions.pipelike.cable.GAItemBlockCable;
import gregicadditions.pipelike.opticalfiber.ItemBlockOpticalFiber;
import gregicadditions.recipes.*;
import gregicadditions.utils.GALog;
import gregicadditions.worldgen.PumpjackHandler;
import gregicadditions.worldgen.StoneGenEvents;
import gregicadditions.worldgen.WorldGenRegister;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static gregicadditions.item.GAMetaBlocks.GA_ORES;
import static gregicadditions.item.GAMetaBlocks.OPTICAL_FIBER;


@Mod.EventBusSubscriber(modid = Gregicality.MODID)
public class CommonProxy {


    public void preLoad() {
        GAMetaItems.init();
        GAMetaFluids.init();
        WorldGenRegister.preInit();

    }

    public void onLoad() throws IOException {
        if (GAConfig.Misc.reverseAfterCT)
            registerRecipesAfterCT();
        WorldGenRegister.init();
        if (GAConfig.Misc.multiStoneGen) {
            MinecraftForge.EVENT_BUS.register(new StoneGenEvents());
        }
    }

    @SubscribeEvent
    public static void syncConfigValues(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Gregicality.MODID)) {
            ConfigManager.sync(Gregicality.MODID, Config.Type.INSTANCE);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        GALog.logger.info("Registering blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(GAMetaBlocks.MUTLIBLOCK_CASING);
        registry.register(GAMetaBlocks.MUTLIBLOCK_CASING2);
        registry.register(GAMetaBlocks.SIMPLE_BLOCK);
        registry.register(GAMetaBlocks.QUANTUM_CASING);
        registry.register(GAMetaBlocks.REACTOR_CASING);
        registry.register(GAMetaBlocks.FUSION_CASING);
        registry.register(GAMetaBlocks.VACUUM_CASING);

        registry.register(GAMetaBlocks.HEATING_COIL);
        registry.register(GAMetaBlocks.DIVERTOR_CASING);
        registry.register(GAMetaBlocks.CRYOSTAT_CASING);
        registry.register(GAMetaBlocks.MACHINE_CASING);
        registry.register(GAMetaBlocks.TRANSPARENT_CASING);
        registry.register(GAMetaBlocks.CELL_CASING);
        registry.register(GAMetaBlocks.CONVEYOR_CASING);
        registry.register(GAMetaBlocks.FIELD_GEN_CASING);
        registry.register(GAMetaBlocks.MOTOR_CASING);
        registry.register(GAMetaBlocks.PISTON_CASING);
        registry.register(GAMetaBlocks.PUMP_CASING);
        registry.register(GAMetaBlocks.ROBOT_ARM_CASING);
        registry.register(GAMetaBlocks.SENSOR_CASING);
        registry.register(GAMetaBlocks.EMITTER_CASING);
        registry.register(OPTICAL_FIBER);
        GAMetaBlocks.METAL_CASING.values().stream().distinct().forEach(registry::register);
        GA_ORES.forEach(registry::register);
        registry.register(GAMetaBlocks.GA_CABLE);
    }


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GALog.logger.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(GAMetaBlocks.GA_CABLE, GAItemBlockCable::new));
        registry.register(createItemBlock(GAMetaBlocks.OPTICAL_FIBER, ItemBlockOpticalFiber::new));
        registry.register(createItemBlock(GAMetaBlocks.MUTLIBLOCK_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.MUTLIBLOCK_CASING2, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.SIMPLE_BLOCK, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.QUANTUM_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.REACTOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.MACHINE_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.FUSION_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.VACUUM_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.HEATING_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.DIVERTOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.CRYOSTAT_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.TRANSPARENT_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.CELL_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.CONVEYOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.FIELD_GEN_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.MOTOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.PISTON_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.PUMP_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.ROBOT_ARM_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.SENSOR_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.EMITTER_CASING, VariantItemBlock::new));

        GAMetaBlocks.METAL_CASING.values()
                .stream().distinct()
                .map(block -> createItemBlock(block, GAMetalCasingItemBlock::new))
                .forEach(registry::register);

        GA_ORES.stream()
                .map(block -> createItemBlock(block, GAOreItemBlock::new))
                .forEach(registry::register);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GALog.logger.info("Registering recipe low...");
        if (Loader.isModLoaded(MysticalAgradditions.MOD_ID) && !GAConfig.mysticalAgriculture.disable) {
            MysticalAgricultureItems.removeMARecipe();
        }
        ConfigCircuitRecipeRemoval.init();
        GAMachineRecipeRemoval.init();
        GARecipeAddition.generatedRecipes();
        RecipeHandler.registerLargeChemicalRecipes();
        RecipeHandler.registerLargeMixerRecipes();
        RecipeHandler.registerLargeForgeHammerRecipes();
        RecipeHandler.registerAlloyBlastRecipes();
        RecipeHandler.registerChemicalPlantRecipes();
        RecipeHandler.registerGreenHouseRecipes();
        RecipeHandler.registerLargeCentrifugeRecipes();
        RecipeHandler.registerLaserEngraverRecipes();
        VoidMinerOres.init();
    }

    @SubscribeEvent
    public static void registerOrePrefix(RegistryEvent.Register<IRecipe> event) {
        GALog.logger.info("Registering ore prefix...");
        RecipeHandler.register();
        NuclearHandler.register();
        OreRecipeHandler.register();
        GARecipeRemoval.init();
        GARecipeAddition.init();
        GAMetaItems.registerOreDict();
        GAMetaBlocks.registerOreDict();
        OrePrefix.runMaterialHandlers();
        GAMetaItems.registerRecipes();
        GARecipeAddition.init2();
        GARecipeAddition.init3();
        GARecipeAddition.forestrySupport();
        MatterReplication.init();
        MachineCraftingRecipes.init();
        GeneratorFuels.init();

        if (Loader.isModLoaded(MysticalAgradditions.MOD_ID) && !GAConfig.mysticalAgriculture.disable) {
            MysticalAgricultureItems.registerOreDict();
        }

    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerRecipesLowest(RegistryEvent.Register<IRecipe> event) {
        RecipeHandler.runRecipeGeneration();
        if (!GAConfig.Misc.reverseAfterCT)
            registerRecipesAfterCT();
    }

    // These recipes are generated at the beginning of the init() phase with the proper config set.
    // This is not great practice, but ensures that they are run AFTER CraftTweaker,
    // meaning they will follow the recipes in the map with CraftTweaker changes,
    // being significantly easier for modpack authors.
    private static void registerRecipesAfterCT() {
        ElectricImplosionHandler.buildElectricImplosionRecipes();
        if (GAConfig.Misc.enableDisassembly)
            DisassemblyHandler.buildDisassemblerRecipes();
    }


    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.world.isRemote) {
            HashMap<PumpjackHandler.ReservoirType, Integer> packetMap = new HashMap<>();
            for (Map.Entry<PumpjackHandler.ReservoirType, Integer> e : PumpjackHandler.reservoirList.entrySet()) {
                if (e.getKey() != null && e.getValue() != null)
                    packetMap.put(e.getKey(), e.getValue());
            }
            NetworkHandler.INSTANCE.sendToAll(new MessageReservoirListSync(packetMap));
        }
    }

    @SubscribeEvent
    public static void onSave(WorldEvent.Save event) {
        IPSaveData.setDirty(0);
    }

    @SubscribeEvent
    public static void onUnload(WorldEvent.Unload event) {
        IPSaveData.setDirty(0);
    }

}
