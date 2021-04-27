package gregicadditions.client.model;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public interface IReTexturedModel {
    /***
     * register model. you can also do config checking here.
     * of course, you could register the RexTexturedModel anywhere in your way, without calling this function.
     *
     * @param state state with variant
     * @param path model path
     */
    @SideOnly(Side.CLIENT)
    default void register(IBlockState state, ResourceLocation path) {
        ReTexturedModelLoader.register(this, path, new ReTexturedModel(getModels(state)));
    }

    /***
     * resource location of models. e.g. return new ResourceLocation[]{m_1, m_2, m_3, ....};.
     * although all models (m_1, m_2,...,m_n) will be rendered together in the end.
     * but additional config about the model (such as item camera perspective) is determined by m1.
     * all you need to be careful about is setting up such config in m1.
     *
     * @param blockState state with variant
     * @return
     */
    @SideOnly(Side.CLIENT)
    ResourceLocation[] getModels(IBlockState blockState);

    /***
     * replacing the texture here. For example, you define textures in your model.json as follow:
     * {
     *     "textures": {
     * 		"0": "gtadditions:blocks/casing/lv",
     *     },
     * }
     * you could return a map with (key, value) => (texture id, new texture path), to replace it.
     * e.g. return new ImmutableMap.Builder<String,String>().put("0", "gtadditions:blocks/casing/mv").build();
     * Note: the alternative texture must already be registered in the game.
     *
     * @param blockState state with variant
     * @param side render facing
     * @param model model you post in {@link IReTexturedModel#getModels(IBlockState)}
     * @return return null if no need to replace textures.
     */
    @SideOnly(Side.CLIENT)
    default ImmutableMap<String, String> reTextured(IBlockState blockState, EnumFacing side, ResourceLocation model) {
        return null;
    }

    /***
     * Here, you have more freedom to adjust the model. You can modify quads before they enter the pipeline.
     * Here you can do coloring, dynamic texture replacement, UV trans, perspective, etc. Almost everything.
     *
     * @param blockState state with variant
     * @param side render facing
     * @param model model you post in {@link IReTexturedModel#getModels(IBlockState)}
     * @param base origin quads
     * @return you'd better return something, even if it's an empty list.
     */
    @SideOnly(Side.CLIENT)
    default List<BakedQuad> reBakedQuad(IBlockState blockState, EnumFacing side, ResourceLocation model, List<BakedQuad> base) {
        return base;
    }

    /***
     * Multilayer rendering is also supported. What's the use? For example,
     * when want to render translucent models (like glass), you must do it in TRANSLUCENT {@link BlockRenderLayer}.
     * But other models may need to render in CUT, to record depth and avoid Z-fighting.
     *
     * @param blockState state with variant
     * @param side render facing
     * @param model model you post in {@link IReTexturedModel#getModels(IBlockState)}
     * @param layer layer
     * @return should Render In Layer
     */
    @SideOnly(Side.CLIENT)
    default boolean shouldRenderInLayer(IBlockState blockState, EnumFacing side, ResourceLocation model, BlockRenderLayer layer) {
        return blockState != null && blockState.getBlock().canRenderInLayer(blockState, layer);
    }

    /***
     * you post the path of your models before {@link IReTexturedModel#getModels(IBlockState)}, but they just resourcelocation.
     * this function will be called when they are first  loaded as IModel.
     * For example, you can replace textures for different TIER machine based on the Variant registered.
     *
     * @param variant
     * @param modelRes
     * @param model
     * @return
     */
    @SideOnly(Side.CLIENT)
    default IModel loadModel(ResourceLocation variant, ResourceLocation modelRes, IModel model) {
        return model;
    }
}
