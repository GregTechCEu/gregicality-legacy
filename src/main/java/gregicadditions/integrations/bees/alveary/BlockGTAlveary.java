package gregicadditions.integrations.bees.alveary;

import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.apiculture.MaterialBeehive;
import forestry.core.blocks.BlockStructure;
import forestry.core.config.Constants;
import forestry.core.tiles.TileUtil;
import forestry.core.utils.ItemTooltipUtil;
import forestry.core.utils.Translator;
import gregicadditions.Gregicality;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

public class BlockGTAlveary extends BlockStructure {
    private static final PropertyEnum<State> STATE = PropertyEnum.create("state", State.class);

    private enum State implements IStringSerializable {
        ON, OFF;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ENGLISH);
        }
    }

    public BlockGTAlveary() {
        super(new MaterialBeehive(false));
        IBlockState defaultState = this.blockState.getBaseState();
        this.setDefaultState(defaultState);
        this.setHardness(1.0F);
        this.setTranslationKey(Gregicality.MODID + ":" + "gt_alveary");
        this.setCreativeTab(Tabs.tabApiculture);
        this.setHarvestLevel("axe", 0);
        this.setSoundType(SoundType.WOOD);
    }

    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileGTAlveary();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATE);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel(Item item, IModelManager manager) {
        manager.registerItemModel(item, 0, Constants.MOD_ID,"gt_alveary");
    }

    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileGTAlveary tile = TileUtil.getTile(world, pos, TileGTAlveary.class);
        if (tile == null) {
            return super.getActualState(state, world, pos);
        }
        if (tile.isActive()){
            state = state.withProperty(STATE, State.ON);
        } else {
            state = state.withProperty(STATE, State.OFF);
        }

        return super.getActualState(state, world, pos);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(Translator.translateToLocal("tile.for.gt_alveary.tooltip"));
        } else {
            ItemTooltipUtil.addShiftInformation(stack, world, tooltip, flag);
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
                         int fortune) {
        super.getDrops(drops, world, pos, state, fortune);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileGTAlveary) {
            drops.addAll(((TileGTAlveary) te).getDrops());
        }
    }
}
