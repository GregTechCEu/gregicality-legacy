package gregicadditions;

import gregicadditions.blocks.GABlockOre;
import gregicadditions.blocks.GAMetalCasing;
import gregicadditions.client.model.ReTexturedModelLoader;
import gregicadditions.client.renderer.OpticalFiberRenderer;
import gregicadditions.input.Keybinds;
import gregicadditions.item.GADustItem;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.materials.SimpleDustMaterial;
import gregicadditions.utils.GALog;
import gregtech.api.unification.OreDictUnifier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Optional;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public static final IBlockColor METAL_CASING_BLOCK_COLOR = (IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) ->
            state.getValue(((GAMetalCasing) state.getBlock()).variantProperty).materialRGB;

    public static final IItemColor METAL_CASING_ITEM_COLOR = (stack, tintIndex) -> {
        GAMetalCasing block = (GAMetalCasing) ((ItemBlock) stack.getItem()).getBlock();
        IBlockState state = block.getStateFromMeta(stack.getItemDamage());
        return state.getValue(block.variantProperty).materialRGB;
    };

    public static final IBlockColor ORE_BLOCK_COLOR = (IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) ->
            tintIndex == 1 ? ((GABlockOre) state.getBlock()).material.materialRGB : 0xFFFFFF;

    public static final IItemColor ORE_ITEM_COLOR = (stack, tintIndex) ->
            tintIndex == 1 ? ((GABlockOre) ((ItemBlock) stack.getItem()).getBlock()).material.materialRGB : 0xFFFFFF;

    @Override
    public void preLoad() {
        super.preLoad();
        if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled()) {
            Minecraft.getMinecraft().getFramebuffer().enableStencil();
        }
        GALog.logger.info(Minecraft.getMinecraft().getFramebuffer().isStencilEnabled());
        Keybinds.initBinds();
        OpticalFiberRenderer.preInit();
        ModelLoaderRegistry.registerLoader(new ReTexturedModelLoader());
    }


    @Override
    public void onLoad() throws IOException {
        super.onLoad();
        Keybinds.registerClient();
        GAMetaBlocks.registerColors();
    }


    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        GAMetaBlocks.registerStateMappers();
        GAMetaBlocks.registerItemModels();
    }

    @SubscribeEvent
    public static void addMaterialFormulaHandler(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (!(itemStack.getItem() instanceof ItemBlock)) {
            Optional<String> oreDictName = OreDictUnifier.getOreDictionaryNames(itemStack).stream().findFirst();
            if (oreDictName.isPresent() && GADustItem.oreDictToSimpleDust.containsKey(oreDictName.get())) {
                SimpleDustMaterial material = SimpleDustMaterial.GA_DUSTS.get((short) itemStack.getItemDamage());
                if (material != null) {
                    String formula = material.getFormula();
                    if (formula != null && !formula.isEmpty() && event.getToolTip().size() == 0) {
                        event.getToolTip().add(1, TextFormatting.GRAY.toString() + material.getFormula());
                    }
                }
            }
        }
    }
}
