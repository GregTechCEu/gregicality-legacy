package gregicadditions.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.worldgen.PumpjackHandler;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockConcrete;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.StoneBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static gregtech.api.unification.material.Materials.StainlessSteel;

public class MetaTileEntityDrillingRig extends MultiblockWithDisplayBase {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = new MultiblockAbility[]{MultiblockAbility.IMPORT_FLUIDS,
            MultiblockAbility.EXPORT_FLUIDS, MultiblockAbility.INPUT_ENERGY};

    private IEnergyContainer energyContainer;
    private IMultipleTankHandler importFluidHandler;
    protected IMultipleTankHandler exportFluidHandler;

    private boolean isActive = false;
    private boolean done = false;
    private static PumpjackHandler.OilWorldInfo oilWorldInfo;


    public MetaTileEntityDrillingRig(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        reinitializeStructurePattern();
    }


    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        if (isActive)
            setActive(false);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    private void initializeAbilities() {
        this.importFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.importFluidHandler = new FluidTankList(true);
        this.exportFluidHandler = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    public boolean drainEnergy() {
        FluidStack drillingMud = GAMaterials.DrillingMud.getFluid(GAConfig.extraction.drillingMud);
        FluidStack canDrain = importFluidHandler.drain(drillingMud, false);
        if (energyContainer.getEnergyStored() >= getMaxVoltage() && canDrain != null && canDrain.amount == GAConfig.extraction.drillingMud) {
            energyContainer.removeEnergy(getMaxVoltage());
            importFluidHandler.drain(drillingMud, true);
            exportFluidHandler.fill(GAMaterials.UsedDrillingMud.getFluid(GAConfig.extraction.drillingMud), true);
            return true;
        }
        return false;
    }

    public long getMaxVoltage() {
        int tier = getTier();
        return GAValues.V[tier];
    }

    public int getTier() {
        return Math.max(1, GAUtility.getTierByVoltage(energyContainer.getInputVoltage()));
    }



    @Override
    protected void updateFormedValid() {

        if (getWorld().isRemote) {
            return;
        }

        if (done) {
            if (isActive)
                setActive(false);
            return;
        }

        if(canFillFluidExport() && drainEnergy()) {
            if(!isActive) setActive(true);
        } else {
            if(isActive) setActive(false);
            return;
        }

        if (getTimer() % 20 == 0) {
            int residual = getResidualOil();
            if (availableOil() > 0 || residual > 0) {
                int oilAmnt = availableOil() <= 0 ? residual * getTier() : availableOil();
                FluidStack out = new FluidStack(availableFluid(), Math.min(fluidDrain(), oilAmnt));
                int drained = exportFluidHandler.fill(out, true);
                extractOil(drained);
            } else {
                done = true;
            }
        }
    }

    public boolean canFillFluidExport() {
        Fluid available = availableFluid();
        if(available == null) return false;
        return exportFluidHandler.fill(new FluidStack(available, 1), false) > 0;
    }

    public int fluidDrain() {
        int tier = getTier();
        return Math.min(100000, GAValues.V[tier] / tier);
    }

    public int availableOil() {
        return PumpjackHandler.getFluidAmount(getWorld(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z);
    }

    public Fluid availableFluid() {
        return PumpjackHandler.getFluid(getWorld(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z);
    }

    public int getResidualOil() {
        return PumpjackHandler.getResidualFluid(getWorld(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z);
    }

    public void extractOil(int amount) {
        PumpjackHandler.depleteFluid(getWorld(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z, amount);
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        //basically check minimal requirements for inputs count
        int fluidOutputsCount = abilities.getOrDefault(MultiblockAbility.EXPORT_FLUIDS, Collections.emptyList()).size();
        int fluidInputsCount = abilities.getOrDefault(MultiblockAbility.IMPORT_FLUIDS, Collections.emptyList()).size();
        return fluidOutputsCount >= 2 &&
                fluidInputsCount >= 1 &&
                abilities.containsKey(MultiblockAbility.INPUT_ENERGY);
    }


    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        oilWorldInfo = PumpjackHandler.getOilWorldInfo(getWorld(), getWorld().getChunk(getPos()).x, getWorld().getChunk(getPos()).z);


        if (isStructureFormed()) {
            if (this.isStructureFormed()) {
                if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                    long maxVoltage = energyContainer.getInputVoltage();
                    String voltageName = GAValues.VN[GAUtility.getTierByVoltage(maxVoltage)];
                    textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
                }
            }
            if (oilWorldInfo.getType() == null) {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.no_fluid").setStyle(new Style().setColor(TextFormatting.RED)));
            } else {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.fluid_drain", fluidDrain()));
                ITextComponent fluidName = new TextComponentTranslation(oilWorldInfo.getType().getFluid().getUnlocalizedName());
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.fluid", fluidName));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.chunk_capacity", oilWorldInfo.capacity / 1000));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.chunk_remaining", oilWorldInfo.current / 1000));
                textList.add(new TextComponentTranslation("gtadditions.multiblock.drilling_rig.replenish", oilWorldInfo.type.replenishRate * getTier()));
            }
        }

    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("F#####F", "F#####F", "CCCCCCC", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                .aisle("#######", "#######", "CCCCCCC", "#BBBBB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##", "#######", "#######", "#######", "#######", "#######")
                .aisle("#######", "#######", "CCCCCCC", "#BBBBB#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#BB#BB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##")
                .aisle("###P###", "###P###", "CCCPCCC", "#BBPBB#", "###P###", "###P###", "###P###", "###P###", "###P###", "#B#P#B#", "###P###", "###P###", "###P###", "###P###", "##BPB##")
                .aisle("#######", "#######", "CCCCCCC", "#BBBBB#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#F###F#", "#BB#BB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##")
                .aisle("#######", "#######", "CCCCCCC", "#BBSBB#", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##F#F##", "##BBB##", "#######", "#######", "#######", "#######", "#######")
                .aisle("F#####F", "F#####F", "CCCCCCC", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######", "#######")
                .where('S', selfPredicate())
                .where('B', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('F', statePredicate(getFrameState()))
                .where('#', blockWorldState -> true)
                .where('C', statePredicate(getConcrete()))
                .where('P', statePredicate(getPipe()))
                .build();
    }

    public IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(StainlessSteel);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GAMetaBlocks.METAL_CASING.get(StainlessSteel);
    }

    public IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(StainlessSteel).getDefaultState();
    }

    public IBlockState getConcrete() {
        return MetaBlocks.CONCRETE.withVariant(BlockConcrete.ConcreteVariant.LIGHT_CONCRETE, StoneBlock.ChiselingVariant.NORMAL);
    }

    public IBlockState getPipe() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityDrillingRig(metaTileEntityId);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gtadditions.machine.miner.fluid_usage", GAConfig.Extraction.drillingMud, I18n.format(GAMaterials.DrillingMud.getFluid(0).getUnlocalizedName())));
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    protected void setActive(boolean active) {
        this.isActive = active;
        markDirty();
        if (!getWorld().isRemote) {
            writeCustomData(1, buf -> buf.writeBoolean(active));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 1) {
            this.isActive = buf.readBoolean();
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
    }
}
