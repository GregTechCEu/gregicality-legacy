package gregicadditions.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.GAMaterials;
import gregicadditions.capabilities.GAEnergyContainerList;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.capabilities.impl.QubitContainerList;
import gregicadditions.covers.CoverWirelessReceiver;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.utils.BlockPosDim;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.cover.ICoverable;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.Nullable;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Predicate;

public class MetaTileEntityTeslaTower extends MultiblockWithDisplayBase {

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.INPUT_ENERGY,
            MultiblockAbility.IMPORT_ITEMS,
            GregicAdditionsCapabilities.INPUT_QBIT
    };

    private static final SolidMaterial[] ALLOWED_TORUS_MATERIALS = {
            Materials.BlackBronze,
            Materials.Electrum,
            GAMaterials.Pikyonium
    };


    protected final double rangeFactor = 4.0D;

    /**
     * The amount of coil layers
     */
    protected int coilHeight = 3;

    /**
     * The coil tier used. All coils must be the same tier
     */
    protected int coilTier = 0;

    /**
     * The range where machines gets powered
     * center is {@link #center}
     * = pow(coilHeight, 0.6) * rangeFactor
     */
    protected double range = 0;

    protected int slots = 0;

    /**
     * This is the voltage of the energy input with the highest voltage
     */
    protected long inputVoltage = 0L;

    /**
     * The amount of amps per pulse
     */
    protected long amps = 16L;

    /**
     * Gets reset before each pulse
     */
    private long ampsUsed = 0L;

    /**
     * The maximum amount of energy send per pulse
     */
    protected long voltagePerPulse = 0L;

    private List<IItemHandlerModifiable> inputInventorys = new ArrayList<>();
    private QubitContainerList inputQubit;
    private IItemHandlerModifiable dataInventory;
    private GAEnergyContainerList inputEnergy;
    private BlockPos center = getPos();

    private Map<String, Integer> failedPositions = new HashMap<>();

    private List<BlockPos> allBlocks = new ArrayList<>();
    private List<BlockPosDim> receivers = new ArrayList<>();
    private List<BlockPosDim> removeLater = new ArrayList<>();

    public MetaTileEntityTeslaTower(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        coilHeight = context.getOrDefault("height", 0);
        // there is no way CoilType doesn't exist
        coilTier = ((GAHeatingCoil.CoilType) context.get("CoilType")).ordinal()+1;
        range = Math.pow(coilHeight, 0.8) * rangeFactor;
        amps = 8 + coilTier * 8;

        inputQubit = new QubitContainerList(getAbilities(GregicAdditionsCapabilities.INPUT_QBIT));
        inputInventorys = getAbilities(MultiblockAbility.IMPORT_ITEMS);
        if (inputInventorys.size() > 0) {
            dataInventory = inputInventorys.get(0);
        }
        if (dataInventory == null)
            dataInventory = getImportItems();
        inputEnergy = new GAEnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
        inputVoltage = inputEnergy.getMaxInputVoltage();
        center = getPos().offset(getFrontFacing().getOpposite(), 4).offset(EnumFacing.UP, 2 + coilHeight / 2);
        allBlocks = createCircularBox();
        receivers = scanRange();
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            // send energy every 0.5 seconds (10 ticks)
            if (getTimer() % 10 == 0) {
                scanDataInventory();
                ampsUsed = 0L;
                for(BlockPosDim pos : receivers) {
                    feedEnergy(pos, getFeedAmount());
                    if(ampsUsed >= amps) {
                        break;
                    }
                }
            }
        }
    }

    protected void scanDataInventory() {
        for(IItemHandlerModifiable itemHandler : inputInventorys) {
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                if (!stack.isEmpty() && stack.hasTagCompound()) {
                    NBTTagCompound tag = stack.getSubCompound("BlockPos");
                    if (tag != null) {
                        int dim = tag.getShort("dim");
                        BlockPosDim pos = new BlockPosDim(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"), dim);
                        if(!receivers.contains(pos)) {
                            receivers.add(pos);
                        }
                    }
                }
            }
            if(removeLater.size() > 0) {
                for(BlockPosDim pos : removeLater) {
                    receivers.remove(pos);
                }
                removeLater.clear();
            }
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(BlockPattern.RelativeDirection.RIGHT, BlockPattern.RelativeDirection.BACK, BlockPattern.RelativeDirection.UP)
                .aisle("#############",
                        "#############",
                        "#####BBB#####",
                        "####BBBBB####",
                        "###BBBBBBB###",
                        "##BBBBBBBBB##",
                        "##BBBBBBBBB##",
                        "##BBBBBBBBB##",
                        "###BBBBBBB###",
                        "####BBBBB####",
                        "#####BSB#####",
                        "#############",
                        "#############")
                .aisle("#############",
                        "#############",
                        "#############",
                        "#####CCC#####",
                        "####C###C####",
                        "###C##P##C###",
                        "###C#P#P#C###",
                        "###C##P##C###",
                        "####C###C####",
                        "#####CCC#####",
                        "#############",
                        "#############",
                        "#############")
                .aisle("#############",
                        "#############",
                        "#############",
                        "#############",
                        "#############",
                        "######P######",
                        "#####P#P#####",
                        "######P######",
                        "#############",
                        "#############",
                        "#############",
                        "#############",
                        "#############")
                .aisle("#############",
                        "#############",
                        "#############",
                        "#############",
                        "#####CCC#####",
                        "####C#P#C####",
                        "####CP#PC####",
                        "####C#P#C####",
                        "#####CCC#####",
                        "#############",
                        "#############",
                        "#############",
                        "#############")
                .setRepeatable(3, 128)
                .aisle("#############",
                        "#############",
                        "#############",
                        "#############",
                        "#############",
                        "######P######",
                        "#####P#P#####",
                        "######P######",
                        "#############",
                        "#############",
                        "#############",
                        "#############",
                        "#############")
                .aisle("#############",
                        "####GGGGG####",
                        "###G#####G###",
                        "##G#######G##",
                        "#G#########G#",
                        "#G####P####G#",
                        "#G###P#P###G#",
                        "#G####P####G#",
                        "#G#########G#",
                        "##G#######G##",
                        "###G#####G###",
                        "####GGGGG####",
                        "#############")
                .aisle("####GGGGG####",
                        "###G#####G###",
                        "##G#GGGGG#G##",
                        "#G#G##G##G#G#",
                        "G#G###G###G#G",
                        "G#G##PGP##G#G",
                        "G#GGGG#GGGG#G",
                        "G#G##PGP##G#G",
                        "G#G###G###G#G",
                        "#G#G##G##G#G#",
                        "##G#GGGGG#G##",
                        "###G#####G###",
                        "####GGGGG####")
                .aisle("#############",
                        "####GGGGG####",
                        "###G#####G###",
                        "##G#######G##",
                        "#G#########G#",
                        "#G####P####G#",
                        "#G###P#P###G#",
                        "#G####P####G#",
                        "#G#########G#",
                        "##G#######G##",
                        "###G#####G###",
                        "####GGGGG####",
                        "#############")
                .where('B', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('P', blockPredicate(MetaBlocks.COMPRESSED.get(Materials.Plastic)))
                .where('C', coilPredicate())
                .where('G', torusPredicate())
                .where('S', selfPredicate())
                .where('#', (tile) -> true)
                //.where('T', itemInputPredicate())
                //.setAmountLimit('T', 1, 1)
                .build();
    }

    public Predicate<BlockWorldState> torusPredicate() {
        Block[] blocks = new Block[ALLOWED_TORUS_MATERIALS.length];
        for(int i = 0; i < ALLOWED_TORUS_MATERIALS.length; i++) {
            blocks[i] = MetaBlocks.COMPRESSED.get(ALLOWED_TORUS_MATERIALS[i]);
        }
        return blockPredicate(blocks);
    }

    public Predicate<BlockWorldState> itemInputPredicate() {
        return (blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            TileEntity tile = blockWorldState.getTileEntity();
            if (tile instanceof MetaTileEntityHolder) {
                if (((MetaTileEntityHolder) tile).getMetaTileEntity() instanceof IMultiblockAbilityPart) {
                    IMultiblockAbilityPart<?> part = (IMultiblockAbilityPart<?>) ((MetaTileEntityHolder) tile).getMetaTileEntity();
                    if (part.getAbility() == MultiblockAbility.IMPORT_ITEMS)
                        return true;
                }
            }
            return false;
        });
    }

    public Predicate<BlockWorldState> coilPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof GAHeatingCoil)) {
                return false;
            } else {
                GAHeatingCoil coil = (GAHeatingCoil) blockState.getBlock();
                GAHeatingCoil.CoilType coilType = coil.getState(blockState);
                GAHeatingCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", coilType);

                //BlockWireCoil blockWireCoil = (BlockWireCoil) blockState.getBlock();
                //BlockWireCoil.CoilType cellType = blockWireCoil.getState(blockState);
                //BlockWireCoil.CoilType currentCoilType = blockWorldState.getMatchContext().getOrPut("CoilType", cellType);
                if (currentCoilType.getName().equals(coilType.getName())) {
                    if (!blockWorldState.getLayerContext().getOrDefault("counted", false)) {
                        blockWorldState.getLayerContext().set("counted", true);
                        blockWorldState.getMatchContext().increment("height", 1);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(GAMaterials.TungstenTitaniumCarbide);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.MULTIBLOCK_WORKABLE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(),
                isStructureFormed() && receivers.size() > 0 && inputEnergy.getEnergyStored() > 0);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityTeslaTower(metaTileEntityId);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        Style red = new Style().setColor(TextFormatting.RED);

        if (isStructureFormed()) {
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.HALF_UP);
            textList.add(new TextComponentTranslation("gtadditions.multiblock.tesla_tower.range", df.format(range)));
            textList.add(new TextComponentTranslation("gtadditions.multiblock.tesla_tower.voltage", inputVoltage));
            textList.add(new TextComponentTranslation("gtadditions.multiblock.tesla_tower.receivers", receivers.size()));
            textList.add(new TextComponentTranslation("gtadditions.multiblock.tesla_tower.amps", amps, ampsUsed));
            if (failedPositions.size() > 0) {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.tesla_tower.fail"));
                for (Map.Entry<String, Integer> pos : failedPositions.entrySet()) {
                    textList.add(new TextComponentString(" - " + pos.getKey() + " E:" + pos.getValue()));
                }
            }
        }
    }

    protected IBlockState getCasingState() {
        return GAMetaBlocks.getMetalCasingBlockState(GAMaterials.TungstenTitaniumCarbide);
    }

    public long getMaxFeedAmount() {
        return inputVoltage * 4 * coilTier;
    }

    public long getFeedAmount() {
        long stored = inputEnergy.getEnergyStored();
        return Math.min(stored, inputVoltage);
    }

    /**
     * feeds the machine at pos
     *
     * @param pos    the position to fill
     * @param amount the amount of energy
     * @return the amount of energy fed
     */
    public long feedEnergy(BlockPosDim pos, long amount) {
        boolean consumeQubit = false;
        World world = getWorld();
        if(!isInRange(pos)) {
            consumeQubit = true;
        }
        if(!isInDim(pos.getDim())) {
            consumeQubit = true;
            world = DimensionManager.getWorld(pos.getDim());
        }

        long charged = 0L;
        TileEntity tile = world.getTileEntity(pos);
        IEnergyContainer container = null;
        String sPos = "X:" + pos.getX() + ", Y:" + pos.getY() + ", Z:" + pos.getZ() + ", Dim:" + pos.getDim();
        if(world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
            // block is broken
            removeLater.add(pos);
            failedPositions.remove(sPos);
            return 0;
        }
        if (tile instanceof MetaTileEntityHolder) {
            MetaTileEntity tileEntity = ((MetaTileEntityHolder) tile).getMetaTileEntity();
            if (hasCover(tileEntity)) {
                container = ((MetaTileEntityHolder) tile).getMetaTileEntity().getCapability(GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER, null);
            } else {
                removeLater.add(pos);
                failedPositions.remove(sPos);
                return 0;
            }
        }

        if (container != null && container.inputsEnergy(EnumFacing.NORTH)) {
            if(consumeQubit) {
                int qubitCost = 10;
                if(inputQubit == null) {
                    failed(sPos, 1);
                    return 0;
                }
                if(inputQubit.getQubitStored() >= qubitCost && inputQubit.changeQubit(-qubitCost) < qubitCost) {
                    failed(sPos, 2);
                    return 0;
                }
            }
            charged = container.addEnergy(amount);
            ampsUsed += container.acceptEnergyFromNetwork(EnumFacing.NORTH, amount, amps - ampsUsed);
            inputEnergy.removeEnergy(charged);
            success(sPos);
        } else {
            failed(sPos, 3);
        }
        return charged;
    }

    /**
     * error
     * 1: no qubt input
     * 2: not enough qubit
     * 3: container is null or cannot input energy
     * @param pos
     * @param error
     */
    protected void failed(String pos, int error) {
        if (!failedPositions.containsKey(pos)) {
            failedPositions.put(pos, error);
        }
    }

    protected void success(String pos) {
        failedPositions.remove(pos);
    }

    /**
     * checks if the pos is in range of the multiblock
     *
     * @param pos of the machine
     * @return if pos is in range
     */
    public boolean isInRange(BlockPos pos) {
        return center.getDistance(pos.getX(), pos.getY(), pos.getZ()) <= range;
    }

    public boolean isInDim(int dim) {
        return dim == getWorld().provider.getDimension();
    }

    /**
     * @return if the tile has a receiver cover
     */
    public boolean hasCover(ICoverable coverable) {
        for (EnumFacing face : EnumFacing.values()) {
            if (coverable.getCoverAtSide(face) instanceof CoverWirelessReceiver) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a list of positions that are in range
     */
    public List<BlockPos> createCircularBox() {
        int intRange = (int) range;
        List<BlockPos> posList = new ArrayList<>();
        Iterable<BlockPos> poss = BlockPos.getAllInBox(center.getX() - intRange, center.getY() - intRange, center.getZ() - intRange, center.getX() + intRange, center.getY() + intRange, center.getZ() + intRange);

        poss.forEach((blockPos -> {
            if (isInRange(blockPos))
                posList.add(blockPos);
        }));
        return posList;
    }

    /**
     * scans the list created in {@link #createCircularBox()}
     * @return all MetaTileEntities that have wireless cover
     */
    public List<BlockPosDim> scanRange() {
        failedPositions.clear();
        List<BlockPosDim> blocks = new ArrayList<>();
        for (BlockPos pos : allBlocks) {
            TileEntity tile = getWorld().getTileEntity(pos);
            if (tile instanceof MetaTileEntityHolder) {
                if (((MetaTileEntityHolder) tile).getMetaTileEntity() != null) {
                    if (hasCover(((MetaTileEntityHolder) tile).getMetaTileEntity())) {
                        blocks.add(new BlockPosDim(pos, getWorld().provider.getDimension()));
                    }
                }
            }
        }
        return blocks;
    }

    protected void handleButtonClick(Widget.ClickData clickData) {
        receivers = scanRange();
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        return super.createUITemplate(entityPlayer);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = this.createUITemplate(entityPlayer);
        builder.widget(new ClickButtonWidget(125, 103, 40, 18, I18n.format("gtadditions.multiblock.tesla_tower.scan"), this::handleButtonClick));
        return builder.build(this.getHolder(), entityPlayer);
    }

    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
    }

    public IItemHandlerModifiable getDataInventory() {
        return dataInventory;
    }

    public GAEnergyContainerList getEnergyInput() {
        return inputEnergy;
    }

    public BlockPos getCenter() {
        return center;
    }

    public Map<String, Integer> getFailedPositions() {
        return failedPositions;
    }

    public List<BlockPosDim> getReceivers() {
        return receivers;
    }
}
