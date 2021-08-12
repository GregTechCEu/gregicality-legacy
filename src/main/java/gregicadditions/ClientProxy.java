package gregicadditions;

import codechicken.lib.texture.TextureUtils;
import gregicadditions.blocks.GABlockOre;
import gregicadditions.blocks.GAMetalCasing;
import gregicadditions.client.model.ReTexturedModelLoader;
import gregicadditions.client.renderer.OpticalFiberRenderer;
import gregicadditions.fluid.GAMetaFluids;
import gregicadditions.input.Keybinds;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.utils.GALog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public static final IBlockColor METAL_CASING_BLOCK_COLOR = (IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) ->
            state.getValue(((GAMetalCasing) state.getBlock()).variantProperty).getMaterialRGB();

    public static final IItemColor METAL_CASING_ITEM_COLOR = (stack, tintIndex) -> {
        GAMetalCasing block = (GAMetalCasing) ((ItemBlock) stack.getItem()).getBlock();
        IBlockState state = block.getStateFromMeta(stack.getItemDamage());
        return state.getValue(block.variantProperty).getMaterialRGB();
    };

    public static final IBlockColor ORE_BLOCK_COLOR = (IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) ->
            tintIndex == 1 ? ((GABlockOre) state.getBlock()).material.getMaterialRGB() : 0xFFFFFF;

    public static final IItemColor ORE_ITEM_COLOR = (stack, tintIndex) ->
            tintIndex == 1 ? ((GABlockOre) ((ItemBlock) stack.getItem()).getBlock()).material.getMaterialRGB() : 0xFFFFFF;

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
        TextureUtils.addIconRegister(GAMetaFluids::registerSprites);
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
}
