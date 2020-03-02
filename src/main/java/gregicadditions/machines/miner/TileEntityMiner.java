package gregicadditions.machines.miner;

import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.List;

public class TileEntityMiner extends TieredMetaTileEntity {

	private final int inventorySize;
	private final long energyPerTick;
	private Miner.Type type;
	private long x, y, z;
	private List<ItemStack> ores;

	public TileEntityMiner(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId, tier);
		this.inventorySize = (tier + 1) * (tier + 1);
		this.energyPerTick = GTValues.V[tier];
		ores = OreDictionary.getOres("ore");
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityMiner(metaTileEntityId, getTier());
	}

	@Override
	protected ModularUI createUI(EntityPlayer entityPlayer) {
		int rowSize = (int) Math.sqrt(inventorySize);

		ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176,
				18 + 18 * rowSize + 94)
				.label(10, 5, getMetaFullName());

		for (int y = 0; y < rowSize; y++) {
			for (int x = 0; x < rowSize; x++) {
				int index = y * rowSize + x;
				builder.widget(new SlotWidget(exportItems, index, 89 - rowSize * 9 + x * 18, 18 + y * 18, true, false)
						.setBackgroundTexture(GuiTextures.SLOT));
			}
		}

		builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 8, 18 + 18 * rowSize + 12);
		return builder.build(getHolder(), entityPlayer);
	}


	@Override
	protected IItemHandlerModifiable createExportItemHandler() {
		return new ItemStackHandler(inventorySize);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("gregtech.universal.tooltip.voltage_in", energyContainer.getInputVoltage(), GTValues.VN[getTier()]));
		tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
	}


	@Override
	public void update() {
		super.update();
		if (!getWorld().isRemote && energyContainer.getEnergyStored() >= energyPerTick) {
			energyContainer.removeEnergy(energyPerTick);
			WorldServer world = (WorldServer) this.getWorld();
			Chunk chuck = world.getChunk(getPos());
			ChunkPos chunkPos = chuck.getPos();

			if (x < chunkPos.getXEnd() && getTimer() % 4 == 0) {
				if (z < chunkPos.getZEnd()) {
					if (y >= 0) {
						NonNullList<ItemStack> itemStacks = NonNullList.create();
						BlockPos blockPos = new BlockPos(x, y, z);
						IBlockState blockState = world.getBlockState(blockPos);
						Block block = world.getBlockState(blockPos).getBlock();
						ItemStack itemStack = Item.getItemFromBlock(block).getDefaultInstance();
						boolean isOre = OreDictionary.containsMatch(false, NonNullList.withSize(1, itemStack), ores.toArray(new ItemStack[0]));
						if (isOre) {
							block.getDrops(itemStacks, world, blockPos, blockState, 0);
							if (addItemsToItemHandler(exportItems, true, itemStacks)) {
								addItemsToItemHandler(exportItems, false, itemStacks);
							}
							world.removeTileEntity(blockPos);
						}
						y--;
					} else {
						y = getPos().getY();
					}
					z++;
				} else {
					z = chunkPos.getZStart();
					x++;
				}
			}

			if (!getWorld().isRemote && getTimer() % 5 == 0) {
				pushItemsIntoNearbyHandlers(getFrontFacing());
			}
		}
	}
}
