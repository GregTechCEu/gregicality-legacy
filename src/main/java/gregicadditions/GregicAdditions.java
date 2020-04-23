package gregicadditions;

import gregicadditions.blocks.GAMetalCasingItemBlock;
import gregicadditions.blocks.factories.GAMetalCasingBlockFactory;
import gregicadditions.input.Keybinds;
import gregicadditions.integrations.bees.ForestryCommonProxy;
import gregicadditions.integrations.bees.GTBees;
import gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy;
import gregicadditions.integrations.tconstruct.TinkersMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.machines.GATileEntities;
import gregicadditions.network.NetworkHandler;
import gregicadditions.recipes.*;
import gregicadditions.theoneprobe.TheOneProbeCompatibility;
import gregtech.api.GTValues;
import gregtech.api.util.GTLog;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

@Mod(modid = GregicAdditions.MODID, name = GregicAdditions.NAME, version = GregicAdditions.VERSION,
        dependencies = "required-after:gregtech;" +
                "after:forestry;" +
                "after:tconstruct;" +
                "after:exnihilocreatio"
)
public class GregicAdditions {
    public static final String MODID = "gtadditions";
    public static final String NAME = "Gregic Additions Rework";
    public static final String VERSION = "@VERSION@";


    static {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            GAMetalCasingBlockFactory.init();
        }
    }

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.integrations.bees.ForestryClientProxy", serverSide = "gregicadditions.integrations.bees.ForestryCommonProxy")
    public static ForestryCommonProxy forestryProxy;

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy", serverSide = "gregicadditions.integrations.exnihilocreatio.ExNihiloCreatioProxy")
    public static ExNihiloCreatioProxy exNihiloCreatioProxy;

    @SidedProxy(modId = MODID, clientSide = "gregicadditions.ClientProxy", serverSide = "gregicadditions.CommonProxy")
    public static CommonProxy proxy;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public GregicAdditions() {
        GAEnums.preInit();

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        NetworkHandler.init();
        proxy.preLoad();
        Keybinds.register();
        MinecraftForge.EVENT_BUS.register(new GAEventHandler());
        GAMetaItems.init();
        GAMetaBlocks.init();
        GATileEntities.init();
        if (GAConfig.GregsConstruct.EnableGregsConstruct && Loader.isModLoaded("tconstruct"))
            TinkersMaterials.preInit();
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            exNihiloCreatioProxy.preInit();
        }
        if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) {
            forestryProxy.preInit();
        }
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.onLoad();
        if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) {
            GTBees.initBees();
            forestryProxy.init();
        }
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            exNihiloCreatioProxy.init(event);
        }
        GARecipeAddition.generatedRecipes();

        if (GTValues.isModLoaded(GTValues.MODID_TOP)) {
            GTLog.logger.info("TheOneProbe found. Enabling integration...");
            TheOneProbeCompatibility.registerCompatibility();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(GAMetaBlocks.MUTLIBLOCK_CASING);
        registry.register(GAMetaBlocks.TRANSPARENT_CASING);
        GAMetaBlocks.METAL_CASING.values().stream().distinct().forEach(registry::register);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(createItemBlock(GAMetaBlocks.MUTLIBLOCK_CASING, VariantItemBlock::new));
        registry.register(createItemBlock(GAMetaBlocks.TRANSPARENT_CASING, VariantItemBlock::new));

        GAMetaBlocks.METAL_CASING.values()
                .stream().distinct()
                .map(block -> createItemBlock(block, GAMetalCasingItemBlock::new))
                .forEach(registry::register);
    }

    @SubscribeEvent
    public void registerOrePrefix(RegistryEvent.Register<IRecipe> event) {
        RecipeHandler.register();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        GAMachineRecipeRemoval.init();
        GARecipeAddition.init();
        GARecipeAddition.init2();
        GARecipeAddition.forestrySupport();
        MatterReplication.init();
        MachineCraftingRecipes.init();
        GeneratorFuels.init();
        GAMetaItems.registerOreDict();
        GAMetaItems.registerRecipes();
        GAMetaBlocks.registerOreDict();
        RecipeHandler.registerLargeChemicalRecipes();
        RecipeHandler.registerLargeMixerRecipes();
        RecipeHandler.registerLargeForgeHammerRecipes();
        RecipeHandler.registerAlloyBlastRecipes();
        RecipeHandler.registerChemicalPlantRecipes();
        VoidMinerOres.init();
    }

    private <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return itemBlock;
    }
}
