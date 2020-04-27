package gregicadditions.integrations.exnihilocreatio.items;

import com.google.common.collect.Lists;
import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.entities.ProjectileStone;
import exnihilocreatio.util.Data;
import exnihilocreatio.util.IHasModel;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExNihiloPebble extends Item implements IHasModel {

    private static final List<String> names = Lists.newArrayList("basalt", "black_granite", "marble", "red_granite");

    public ExNihiloPebble() {
        setTranslationKey("gbpebble");
        setRegistryName("gbpebble");
        setCreativeTab(ExNihiloCreatio.tabExNihilo);
        setHasSubtypes(true);

        Data.ITEMS.add(this);
    }

    public static ItemStack getPebbleStack(String name) {
        return new ItemStack(ExNihiloItems.pebble, 1, names.indexOf(name));
    }


    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        return getTranslationKey() + "." + names.get(stack.getItemDamage());
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab))
            for (int i = 0; i < names.size(); i++) {
                list.add(new ItemStack(this, 1, i));
            }
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        stack.shrink(1);
        world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            ItemStack thrown = stack.copy();
            thrown.setCount(1);

            ProjectileStone projectile = new ProjectileStone(world, player);
            projectile.setStack(thrown);
            projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 0.5F);
            world.spawnEntity(projectile);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    public String getPebbleType(ItemStack stack) {
        if (stack.getMetadata() >= names.size() || stack.getMetadata() < 0) {
            return names.get(0);
        } else {
            return names.get(stack.getMetadata());
        }
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void initModel(ModelRegistryEvent e) {
        List<ModelResourceLocation> locations = new ArrayList<>();
        for (String name : names) {
            locations.add(new ModelResourceLocation(getRegistryName(), "type=" + name));
        }

        ModelBakery.registerItemVariants(this, locations.toArray(new ModelResourceLocation[0]));
        ModelLoader.setCustomMeshDefinition(this, stack -> locations.get(stack.getMetadata()));

    }
}
