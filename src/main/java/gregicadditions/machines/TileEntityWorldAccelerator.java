package gregicadditions.machines;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.pipenet.block.material.TileEntityMaterialPipeBase;
import gregtech.api.render.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

public class TileEntityWorldAccelerator extends TieredMetaTileEntity {

	private final long energyPerTick;
	private boolean tileMode = true;
	private boolean isActive = false;

	public TileEntityWorldAccelerator(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId, tier);
		//consume 8 amps
		this.energyPerTick = GTValues.V[tier] * 8;
		initializeInventory();
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
	protected long getMaxInputOutputAmperage() {
		return 8L;
	}

	@Override
	public void update() {
		super.update();
		if (!getWorld().isRemote && energyContainer.getEnergyStored() >= energyPerTick) {
			isActive = true;
			energyContainer.removeEnergy(energyPerTick);
			WorldServer world = (WorldServer) this.getWorld();
			BlockPos worldAcceleratorPos = getPos();
			if (tileMode) {
				BlockPos[] neighbours = new BlockPos[]{worldAcceleratorPos.down(), worldAcceleratorPos.up(), worldAcceleratorPos.north(), worldAcceleratorPos.south(), worldAcceleratorPos.east(), worldAcceleratorPos.west()};
				for (BlockPos neighbour : neighbours) {
					TileEntity targetTE = world.getTileEntity(neighbour);
					if (targetTE == null || targetTE instanceof TileEntityMaterialPipeBase || targetTE instanceof MetaTileEntityHolder) {
						continue;
					}
					boolean horror = false;
					if (clazz != null && targetTE instanceof ITickable) {
						horror = clazz.isInstance(targetTE);
					}
					if (targetTE instanceof ITickable && (!horror || !world.isRemote)) {
						IntStream.range(0, (int) Math.pow(2, getTier())).forEach(value -> {
							((ITickable) targetTE).update();
						});
					}
				}
			} else {
				BlockPos upperConner = worldAcceleratorPos.north(getTier()).east(getTier());
				for (int x = 0; x < (getTier() * 2) + 1; x++) {
					BlockPos row = upperConner.south(x);
					for (int y = 0; y < (getTier() * 2) + 1; y++) {
						BlockPos cell = row.west(y);

						IBlockState targetBlock = world.getBlockState(cell);
						IntStream.range(0, (int) Math.pow(2, getTier())).forEach(value -> {
							if (world.rand.nextInt(100) == 0) {
								if (targetBlock.getBlock().getTickRandomly()) {
									targetBlock.getBlock().randomTick(world, cell, targetBlock, world.rand);
								}
							}
						});
					}
				}
			}
		}else {
			isActive = false;
		}

	}

	static Class clazz;

	static {
		try {
			clazz = Class.forName("cofh.core.block.TileCore");
		} catch (Exception e) {

		}
	}

	@Override
	public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
		super.renderMetaTileEntity(renderState, translation, pipeline);
		Textures.AMPLIFAB_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
	}


	@Override
	public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
		tileMode = !tileMode;
		playerIn.sendStatusMessage(new TextComponentTranslation(tileMode ? "Tile entity mode" : "Entity mode"), false);
		return true;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setTag("TileMode", new NBTTagString(Boolean.valueOf(tileMode).toString()));
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tileMode = Boolean.parseBoolean(data.getString("TileMode"));
	}
}
