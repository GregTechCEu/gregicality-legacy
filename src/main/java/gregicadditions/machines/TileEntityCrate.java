package gregicadditions.machines;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.client.ClientHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.ModularUI.Builder;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.util.GTUtility;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCrate extends MetaTileEntity {

	private final SolidMaterial material;
	private final int inventorySize;
	private ItemStackHandler inventory;

	public TileEntityCrate(ResourceLocation metaTileEntityId, SolidMaterial material, int inventorySize) {
		super(metaTileEntityId);
		this.material = material;
		this.inventorySize = inventorySize;
		initializeInventory();
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityCrate(metaTileEntityId, material, inventorySize);
	}

	@Override
	public boolean hasFrontFacing() {
		return false;
	}

	@Override
	public int getLightOpacity() {
		return 1;
	}

	@Override
	public String getHarvestTool() {
		return material.toString().contains("wood") ? "axe" : "pickaxe";
	}

	@Override
	protected void initializeInventory() {
		super.initializeInventory();
		this.inventory = new ItemStackHandler(inventorySize) {
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
			}
		};
		this.itemInventory = inventory;
	}

	@Override
	public int getActualComparatorValue() {
		return ItemHandlerHelper.calcRedstoneFromInventory(inventory);
	}

	@Override
	public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
		clearInventory(itemBuffer, inventory);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Pair<TextureAtlasSprite, Integer> getParticleTexture() {
		return Pair.of(material.toString().contains("wood") ? Textures.WOODEN_CHEST.getParticleTexture() : ClientHandler.METAL_CRATE.getParticleTexture(), 16777215);
	}

	@Override
	public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
		if (material.toString().contains("wood")) {
			ClientHandler.WOODEN_CRATE.render(renderState, translation, GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()), pipeline);
		} else {
			int baseColor = ColourRGBA.multiply(GTUtility.convertRGBtoOpaqueRGBA_CL(material.materialRGB), GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()));
			ClientHandler.METAL_CRATE.render(renderState, translation, baseColor, pipeline);
		}
	}

	@Override
	protected ModularUI createUI(EntityPlayer entityPlayer) {
		Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 338, 8 + inventorySize + 104).label(5, 5, getMetaFullName());
		for (int i = 0; i < inventorySize; i++) {
			builder.slot(inventory, i, 8 + i % 18 * 18, 18 + i / 18 * 18, GuiTextures.SLOT);
		}
		builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 90, 18 + inventorySize + 12);
		return builder.build(getHolder(), entityPlayer);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setTag("Inventory", inventory.serializeNBT());
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		this.inventory.deserializeNBT(data.getCompoundTag("Inventory"));
	}

	@Override
	protected boolean shouldSerializeInventories() {
		return false;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("gregtech.universal.tooltip.item_storage_capacity", inventorySize));
	}
}
