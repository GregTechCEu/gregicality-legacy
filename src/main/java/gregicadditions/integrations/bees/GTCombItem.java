package gregicadditions.integrations.bees;

import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.core.config.Constants;
import forestry.core.items.IColoredItem;
import forestry.core.utils.ItemTooltipUtil;
import forestry.core.utils.Translator;
import gregicadditions.GAValues;
import gregicadditions.Gregicality;
import gregtech.api.GTValues;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;


@Optional.InterfaceList({@Optional.Interface(iface = "forestry.api.core.IItemModelRegister", modid = GAValues.MODID_FR), @Optional.Interface(iface = "forestry.core.items.IColoredItem", modid = GAValues.MODID_FR)})
public class GTCombItem extends Item implements IColoredItem, IItemModelRegister {
	public GTCombItem() {
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(Tabs.tabApiculture);
		setRegistryName(Gregicality.MODID, "comb");
		setTranslationKey(Gregicality.MODID + ":comb");
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@Override
	public boolean isDamageable() {
		return false;
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@Override
	public boolean isRepairable() {
		return false;
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel(Item item, IModelManager manager) {
		manager.registerItemModel(item, 0);
		for (int i = 0; i < GTCombs.VALUES.length; i++) {
			manager.registerItemModel(item, i, Constants.MOD_ID, "comb");
		}
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@Override
	public String getTranslationKey(ItemStack stack) {
		GTCombs honeyComb = GTCombs.get(stack.getItemDamage());
		return super.getTranslationKey(stack) + "." + honeyComb.name;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		GTCombs honeyComb = GTCombs.get(stack.getItemDamage());
		//automatic name for fluid combs, avoiding localized stuff.
		if (honeyComb.ordinal() >= GTCombs.WATER.ordinal()){
			Fluid fluid = GTBees.getFluidMaterial(GTBees.getUid(honeyComb.name));
			if (fluid != null){
				return Translator.translateToLocal(fluid.getUnlocalizedName()) + " " + Translator.translateToLocal("item.gtadditions:comb.name");
			}
		}
		return super.getItemStackDisplayName(stack);
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (tab == Tabs.tabApiculture) for (int i = 0; i < GTCombs.VALUES.length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	public static ItemStack getComb(GTCombs honeyComb, int amount) {
		return new ItemStack(GTCombs.combItem, amount, honeyComb.ordinal());
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@Override
	public int getColorFromItemstack(ItemStack itemstack, int tintIndex) {
		GTCombs honeyComb = GTCombs.get(itemstack.getItemDamage());
		if (tintIndex == 1) {
			return honeyComb.primaryColor;
		} else {
			return honeyComb.secondaryColor;
		}
	}

	@Optional.Method(modid = GAValues.MODID_FR)
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, world, tooltip, advanced);
		ItemTooltipUtil.addInformation(stack, world, tooltip, advanced);
	}

	public ItemStack getItemStack() {
		return new ItemStack(this);
	}

	public ItemStack getItemStack(int amount) {
		return new ItemStack(this, amount);
	}

	public ItemStack getWildcard() {
		return new ItemStack(this, 1, GTValues.W);
	}

}