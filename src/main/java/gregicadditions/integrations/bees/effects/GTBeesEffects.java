package gregicadditions.integrations.bees.effects;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeekeepingLogic;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IEffectData;
import forestry.api.multiblock.IMultiblockComponent;
import forestry.apiculture.multiblock.AlvearyController;
import forestry.core.render.ParticleRender;
import gregicadditions.integrations.bees.GTBees;
import gregicadditions.integrations.bees.alveary.TileGTAlveary;
import gregicadditions.machines.TileEntityWorldAccelerator;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.common.items.MetaTool;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.List;
import java.util.stream.IntStream;

public enum GTBeesEffects implements IAlleleBeeEffect {
    GT_ACCELERATE {
        @Override
        public IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
            if (!housing.getWorldObj().isRemote) {
                BlockPos coordinates = housing.getCoordinates();
                BlockPos[] neighbours =
                        new BlockPos[] {coordinates.down(), coordinates.up(), coordinates.north(), coordinates.south(),
                                coordinates.east(), coordinates.west()};
                for (BlockPos neighbour : neighbours) {
                    TileEntity te = housing.getWorldObj().getTileEntity(neighbour);
                    if (te instanceof MetaTileEntityHolder) {
                        if (((MetaTileEntityHolder) te).getMetaTileEntity() instanceof TileEntityWorldAccelerator) {
                            continue;
                        }
                        IntStream.range(0, (int) (genome.getSpeed() * 5 + genome.getLifespan() * 0.03)).forEach(value -> {
                            ((MetaTileEntityHolder) te).update();
                        });
                    }
                }
            }
            return storedData;
        }
    },
    GT_FIX {
        @Override
        public IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
            if (housing.getWorldObj().rand.nextInt(100) < 1){
                final Vec3i area = genome.getTerritory();
                BlockPos coordinates = housing.getCoordinates();
                int[] offset = {-Math.round(area.getX() / 2), -Math.round(area.getY() / 2), -Math.round(area.getZ() / 2)};
                int[] min = {coordinates.getX() + offset[0], coordinates.getY() + offset[1], coordinates.getZ() + offset[2]};
                int[] max = {coordinates.getX() + offset[0] + area.getX(), coordinates.getY() + offset[1] + area.getY(), coordinates.getZ() + offset[2] + area.getZ()};
                AxisAlignedBB box = new AxisAlignedBB(min[0], min[1], min[2], max[0], max[1], max[2]);
                List<EntityPlayer> players = housing.getWorldObj().getEntitiesWithinAABB(EntityPlayer.class, box);
                for (EntityPlayer player : players) {
                    int regainDur = (int) (genome.getSpeed() * genome.getLifespan());
                    for(ItemStack itemStack : player.inventory.mainInventory) {
                        if (itemStack.getItem() instanceof MetaTool) {
                            regainDur -= ((MetaTool)itemStack.getItem()).regainItemDurability(itemStack, regainDur);
                            if (regainDur == 0) {
                                break;
                            }
                        }
                    }
                }
            }
            return storedData;
        }
    },
    GT_ENERGY {
        @Override
        public IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
            if (!housing.getWorldObj().isRemote && storedData.getInteger(0) != FMLCommonHandler.instance().getMinecraftServerInstance().getTickCounter()) {
                storedData.setInteger(0, FMLCommonHandler.instance().getMinecraftServerInstance().getTickCounter());
                long chargeE = (long) (genome.getSpeed() * genome.getLifespan() * 10);
                if (housing instanceof AlvearyController){
                    for(IMultiblockComponent component : ((AlvearyController) housing).getComponents()) {
                        if (component instanceof TileGTAlveary){
                            chargeE -= ((TileGTAlveary) component).changeEnergy(chargeE);
                            if (chargeE <= 0)
                                return storedData;
                        }
                    }
                }
            }
            return storedData;
        }

        @Override
        public IEffectData validateStorage(IEffectData storedData) {
            if (storedData instanceof TickEffectData){
                return storedData;
            }
            return new TickEffectData();
        }
    },
    GT_FLUID {
        @Override
        public IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
            int tick = FMLCommonHandler.instance().getMinecraftServerInstance().getTickCounter();
            if (!housing.getWorldObj().isRemote && storedData.getInteger(0) != tick && tick % 20 == 0) {
                storedData.setInteger(0, FMLCommonHandler.instance().getMinecraftServerInstance().getTickCounter());
                Fluid fluid = GTBees.getFluidMaterial(genome.getPrimary().getUID());
                if (fluid == null) return storedData;
                int fluidFill = (int) (genome.getSpeed() * genome.getLifespan() / 2);
                if (housing instanceof AlvearyController){
                    for(IMultiblockComponent component : ((AlvearyController) housing).getComponents()) {
                        if (component instanceof TileGTAlveary){
                            fluidFill -= ((TileGTAlveary) component).getFluidTank().fillInternal(new FluidStack(fluid, fluidFill), true);
                            if (fluidFill <= 0)
                                return storedData;
                        }
                    }
                }
            }
            return storedData;
        }

        @Override
        public IEffectData validateStorage(IEffectData storedData) {
            if (storedData instanceof TickEffectData){
                return storedData;
            }
            return new TickEffectData();
        }
    }
    ;

    public static void initEffects() {
        for (final GTBeesEffects effect : values()) {
            AlleleManager.alleleRegistry.registerAllele(effect);
        }
    }

    @Override
    public IEffectData doFX(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
        IBeekeepingLogic beekeepingLogic = housing.getBeekeepingLogic();
        List<BlockPos> flowerPositions = beekeepingLogic.getFlowerPositions();
        ParticleRender.addBeeHiveFX(housing, genome, flowerPositions);
        return storedData;
    }

    @Override
    public boolean isCombinable() {
        return false;
    }

    @Override
    public IEffectData validateStorage(IEffectData storedData) {
        return storedData;
    }

    @Override
    public String getUID() {
        return toString().toLowerCase();
    }

    @Override
    public boolean isDominant() {
        return true;
    }

    @Override
    public String getName() {
        return I18n.format("for.bees.effects." + toString().toLowerCase());
    }

    @Override
    public String getUnlocalizedName() {
        return this.getUID();
    }
}
