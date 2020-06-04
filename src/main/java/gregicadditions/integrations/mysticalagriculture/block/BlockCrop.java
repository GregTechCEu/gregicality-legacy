package gregicadditions.integrations.mysticalagriculture.block;

import com.blakebr0.cucumber.util.Utils;
import com.blakebr0.mysticalagradditions.lib.MAHelper;
import com.blakebr0.mysticalagriculture.blocks.crop.BlockMysticalCrop;
import gregtech.api.unification.material.type.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockCrop extends BlockMysticalCrop {

    public static final Map<Material, BlockCrop> REGISTRY = new HashMap<>();

    private static final AxisAlignedBB CROPS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D);
    private IBlockState root;
    private Item seed;
    private Item crop;

    private final Material material;

    public BlockCrop(String name, Material material) {
        super(name);
        this.material = material;
        REGISTRY.put(material, this);
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        this.checkAndDropBlock(world, pos, state);
        if (world.getBlockState(pos.down(2)) != this.getRoot())
            return;

        int i = this.getAge(state);
        if (world.getLightFromNeighbors(pos.up()) >= 9) {
            if (world.getBlockState(pos.down(2)) == this.getRoot()) {
                if (i < this.getMaxAge()) {
                    float f = getGrowthChance(this, world, pos);
                    if (rand.nextInt((int) (35.0F / f) + 1) == 0) {
                        world.setBlockState(pos, this.withAge(i + 1), 2);
                    }
                }
            }
        }
    }

    @Override
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        if (world.getBlockState(pos.down(2)) != this.getRoot())
            return;

        super.grow(world, rand, pos, state);
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return state.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CROPS_AABB;
    }

    public IBlockState setRoot(IBlockState root) {
        this.root = root;
        return this.root;
    }

    public IBlockState getRoot() {
        return this.root;
    }

    public BlockMysticalCrop setSeed(Item seed) {
        this.seed = seed;
        return this;
    }

    @Override
    public Item getSeed() {
        return this.seed;
    }

    public BlockMysticalCrop setCrop(Item crop) {
        this.crop = crop;
        return this;
    }

    @Override
    public Item getCrop() {
        return this.crop;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        int age = state.getValue(AGE);
        Random rand = Utils.rand;

        int essence = 0;
        int fertilizer = 0;

        if (age == 7) {
            if (MAHelper.config.confFertilizedEssenceChance > 0) {
                if (rand.nextInt(100 / MAHelper.config.confFertilizedEssenceChance) > 0) {
                    fertilizer = 0;
                } else {
                    fertilizer = 1;
                }
            } else
                fertilizer = 0;
        }

        if (age == 7) {
            if (MAHelper.config.confEssenceChance > 0) {
                if (rand.nextInt(100 / MAHelper.config.confEssenceChance) > 0) {
                    essence = 1;
                } else {
                    essence = 2;
                }
            } else
                essence = 1;
        }

        drops.add(new ItemStack(this.getSeed(), 1, 0));
        if (essence > 0) {
            drops.add(new ItemStack(this.getCrop(), essence, 0));
        }
        if (fertilizer > 0) {
            drops.add(new ItemStack(MAHelper.items.itemFertilizedEssence, fertilizer, 0));
        }
    }
}
