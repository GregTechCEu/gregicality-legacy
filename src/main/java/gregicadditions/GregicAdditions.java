package gregicadditions;

import gregicadditions.bees.CommonProxy;
import gregicadditions.bees.GTBees;
import gregicadditions.blocks.GAMetalCasing;
import gregicadditions.blocks.GAMetalCasingItemBlock;
import gregicadditions.blocks.factories.GAMetalCasingBlockFactory;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.machines.GATileEntities;
import gregicadditions.recipes.*;
import gregicadditions.tconstruct.TinkersMaterials;
import gregtech.api.unification.material.type.Material;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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

import static gregicadditions.item.GAMetaBlocks.METAL_CASING;

@Mod(modid = GregicAdditions.MODID, name = GregicAdditions.NAME, version = GregicAdditions.VERSION, dependencies = "required-after:gregtech;after:forestry;after:tconstruct")
public class GregicAdditions {
	public static final String MODID = "gtadditions";
	public static final String NAME = "Gregic Additions Rework";
	public static final String VERSION = "@VERSION@";

	public static final IBlockColor METAL_CASING_BLOCK_COLOR = (IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) -> {
		Material material = ((GAMetalCasing) state.getBlock()).getMetalCasingMaterial();
		return material.materialRGB;
	};

	public static final IItemColor METAL_CASING_ITEM_COLOR = (stack, tintIndex) -> {
		IBlockState metalCasingState = ((GAMetalCasingItemBlock) stack.getItem()).getBlockState(stack);
		GAMetalCasing block = (GAMetalCasing) metalCasingState.getBlock();
		return block.getMetalCasingMaterial().materialRGB;
	};

	static{
		if (FMLCommonHandler.instance().getSide().isClient()) {
			GAMetalCasingBlockFactory.init();
		}
	}

	@SidedProxy(modId = MODID, clientSide = "gregicadditions.bees.ClientProxy", serverSide = "gregicadditions.bees.CommonProxy")
	public static CommonProxy proxy;

	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public GregicAdditions() {
		GAEnums.preInit();

	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GAMetaItems.init();
		GAMetaBlocks.init();
		GATileEntities.init();
		if (GAConfig.GregsConstruct.EnableGregsConstruct && Loader.isModLoaded("tconstruct")) TinkersMaterials.preInit();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) GTBees.initBees();
		GAMetaBlocks.registerColors();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		GARecipeAddition.generatedRecipes();
		if (GAConfig.GTBees.EnableGTCEBees && Loader.isModLoaded("forestry")) proxy.postInit();
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		registry.register(GAMetaBlocks.MUTLIBLOCK_CASING);
		registry.register(GAMetaBlocks.TRANSPARENT_CASING);
		METAL_CASING.values().stream().distinct().forEach(registry::register);
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		registry.register(createItemBlock(GAMetaBlocks.MUTLIBLOCK_CASING, VariantItemBlock::new));
		registry.register(createItemBlock(GAMetaBlocks.TRANSPARENT_CASING, VariantItemBlock::new));
		METAL_CASING.values()
				.stream().distinct()
				.map(block -> createItemBlock(block, GAMetalCasingItemBlock::new))
				.forEach(registry::register);
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
		RecipeHandler.register();
	}

	private <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
		ItemBlock itemBlock = producer.apply(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return itemBlock;
	}
}
