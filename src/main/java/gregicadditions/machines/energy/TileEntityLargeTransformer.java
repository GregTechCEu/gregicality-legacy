package gregicadditions.machines.energy;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregicadditions.capabilities.EnergyContainerListWithAmps;
import gregtech.api.capability.IEnergyContainer;
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
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class TileEntityLargeTransformer extends MultiblockWithDisplayBase {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {MultiblockAbility.OUTPUT_ENERGY, MultiblockAbility.INPUT_ENERGY};

    private IEnergyContainer input;
    private IEnergyContainer output;
    private boolean isActive = false;
    private long currentDrain = 0;
    private long drain = 0;
    DecimalFormat formatter = new DecimalFormat("#0.0");

    public TileEntityLargeTransformer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
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
        this.input = new EnergyContainerListWithAmps(getAbilities(MultiblockAbility.INPUT_ENERGY));
        this.output = new EnergyContainerListWithAmps(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.input = new EnergyContainerListWithAmps(Lists.newArrayList());
        this.output = new EnergyContainerListWithAmps(Lists.newArrayList());
    }


    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            if (!isActive)
                setActive(true);
            if (output.getEnergyStored() < output.getEnergyCapacity()) {
                if (input.getEnergyStored() < output.getEnergyCapacity() - output.getEnergyStored()) {
                    long drain = input.getEnergyStored();
                    output.addEnergy(drain);
                    input.removeEnergy(drain);
                    currentDrain += drain;
                } else {
                    long left = output.getEnergyCapacity() - output.getEnergyStored();
                    output.addEnergy(left);
                    input.removeEnergy(left);
                    currentDrain += left;
                }
            }
            if (getTimer() % 20 == 0) {
                drain = currentDrain / 20;
                currentDrain = 0;
            }
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("ASA")
                .where('S', selfPredicate())
                .where('A', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .build();
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        return abilities.containsKey(MultiblockAbility.INPUT_ENERGY) && abilities.containsKey(MultiblockAbility.OUTPUT_ENERGY);
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new TileEntityLargeTransformer(metaTileEntityId);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive);
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
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_transformer.input", input.getInputVoltage() / input.getInputAmperage(), input.getInputAmperage()));
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_transformer.output", output.getOutputVoltage() / output.getOutputAmperage(), output.getOutputAmperage()));
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_transformer.input_average", formatter.format(drain * 1.0 / (input.getInputVoltage() * 1.0 ))));
            textList.add(new TextComponentTranslation("gregtech.multiblock.large_transformer.output_average", formatter.format(drain * 1.0 / (output.getOutputVoltage() * 1.0))));
        }
    }
}
