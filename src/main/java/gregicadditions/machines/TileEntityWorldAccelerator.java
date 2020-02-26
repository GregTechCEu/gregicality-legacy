package gregicadditions.machines;

import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.util.GTLog;
import gregtech.common.pipelike.cable.tile.TileEntityCable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public class TileEntityWorldAccelerator extends TieredMetaTileEntity {

	private final long energyPerTick;

	public TileEntityWorldAccelerator(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId, tier);
		//consume 8 amps
		this.energyPerTick = GTValues.V[tier] * 8;
	}

	@Override
	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new TileEntityWorldAccelerator(metaTileEntityId, getTier());
	}

	@Override
	protected ModularUI createUI(EntityPlayer entityPlayer) {
		return null;
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
			WorldServer world = (WorldServer) this.getWorld();
			BlockPos blockPos = getPos();
			BlockPos[] neighbours = new BlockPos[]{blockPos.down(), blockPos.up(), blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west()};
			for (BlockPos neighbour : neighbours) {
				TileEntity targetTE = world.getTileEntity(neighbour);
				List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(neighbour).grow(0.2));
				GTLog.logger.info(entities);
				if (targetTE instanceof TileEntityCable) {
					continue;
				}
				boolean horror = false;
				if (clazz != null && targetTE instanceof ITickable) {
					horror = clazz.isInstance(targetTE);
				}
				if (targetTE instanceof ITickable && (!horror || !world.isRemote)) {
					energyContainer.removeEnergy(energyPerTick);
					IntStream.range(0, getTier() * 1000).forEach(value -> ((ITickable) targetTE).update());
				}
				if (world.rand.nextInt(1365) == 0) {
					energyContainer.removeEnergy(energyPerTick);
					IntStream.range(0, getTier() * 1000).forEach(value -> {
						IBlockState targetBlock = world.getBlockState(neighbour);
						if (targetBlock.getBlock().getTickRandomly()) {
							targetBlock.getBlock().randomTick(world, neighbour, targetBlock, world.rand);
						}
					});

				}


			}
		}

	}

	static Class clazz;

	static {
		try {
			clazz = Class.forName("cofh.core.block.TileCore");
		} catch (Exception e) {

		}
	}
}
