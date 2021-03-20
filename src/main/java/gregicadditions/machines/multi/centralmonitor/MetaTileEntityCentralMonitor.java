package gregicadditions.machines.multi.centralmonitor;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregicadditions.covers.CoverDigitalInterface;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.renderer.RenderHelper;
import gregicadditions.utils.Tuple;
import gregicadditions.widgets.monitor.WidgetScreenGrid;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.AdvancedTextWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.pipenet.tile.AttachmentType;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.pipelike.cable.net.EnergyNet;
import gregtech.common.pipelike.cable.net.WorldENet;
import gregtech.common.pipelike.cable.tile.TileEntityCable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityCentralMonitor extends MultiblockWithDisplayBase implements IFastRenderMetaTileEntity {
    private final static long ENERGY_COST = -50;
    public final static int MAX_HEIGHT = 9;
    public final static int MAX_WIDTH = 14;
    // run-time data
    public int width;
    private long lastUpdate;
    private WeakReference<EnergyNet> currentEnergyNet;
    private List<BlockPos> activeNodes;
    public List<Tuple<BlockPos, EnumFacing>> covers;
    public List<BlockPos> parts;
    public List<MetaTileEntityMonitorScreen> screens;
    private boolean isActive;
    private EnergyContainerList inputEnergy;
    // persistent data
    public int height = 3;

    public MetaTileEntityCentralMonitor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        reinitializeStructurePattern();
    }

    private EnergyNet getEnergyNet() {
        if (!this.getWorld().isRemote) {
            TileEntity te = this.getWorld().getTileEntity(this.getPos().offset(frontFacing.getOpposite()));
            if (te instanceof TileEntityCable) {
                TileEntityCable tileEntityCable = (TileEntityCable) te;
                EnergyNet currentEnergyNet = this.currentEnergyNet.get();
                if (currentEnergyNet != null && currentEnergyNet.isValid() && currentEnergyNet.containsNode(tileEntityCable.getPipePos())) {
                    return currentEnergyNet; //return current net if it is still valid
                }
                WorldENet worldENet = (WorldENet) tileEntityCable.getPipeBlock().getWorldPipeNet(tileEntityCable.getPipeWorld());
                currentEnergyNet = worldENet.getNetFromPos(tileEntityCable.getPipePos());
                if (currentEnergyNet != null) {
                    this.currentEnergyNet = new WeakReference<>(currentEnergyNet);
                }
                return currentEnergyNet;
            }
        }
        return null;
    }

    private void updateNodes() {
        EnergyNet energyNet = getEnergyNet();
        if (energyNet == null) {
            activeNodes.clear();
            return;
        }
        if (energyNet.getLastUpdate() == lastUpdate) {
            return;
        }
        lastUpdate = energyNet.getLastUpdate();
        activeNodes.clear();
        energyNet.getAllNodes().forEach((pos, node) -> {
            if (node.isActive) {
                activeNodes.add(pos);
            }
        });
    }

    private boolean checkCovers() {
        updateNodes();
        List<Tuple<BlockPos, EnumFacing>> checkCovers = new ArrayList<>();
        World world = this.getWorld();
        for (BlockPos pos : activeNodes) {
            TileEntity tileEntityCable = world.getTileEntity(pos);
            if (!(tileEntityCable instanceof TileEntityCable)) {
                continue;
            }
            for (EnumFacing facing : EnumFacing.VALUES) {
                TileEntity tileEntity = world.getTileEntity(pos.offset(facing));
                if (tileEntity instanceof MetaTileEntityHolder && !((TileEntityCable) tileEntityCable).isConnectionBlocked(AttachmentType.PIPE, facing)) {
                    MetaTileEntity metaTileEntity = ((MetaTileEntityHolder) tileEntity).getMetaTileEntity();
                    if (metaTileEntity != null) {
                        CoverBehavior cover = metaTileEntity.getCoverAtSide(facing.getOpposite());
                        if (cover instanceof CoverDigitalInterface && ((CoverDigitalInterface) cover).isProxy()) {
                            checkCovers.add(new Tuple<>(metaTileEntity.getPos(), cover.attachedSide));
                        }
                    }
                }
            }
        }
        if (checkCovers.size() != covers.size() || !covers.containsAll(checkCovers)) {
            covers = checkCovers;
            return true;
        }
        return false;
    }

    private void writeCovers(PacketBuffer buf) {
        if(covers == null) {
            buf.writeInt(0);
            return;
        }
        buf.writeInt(covers.size());
        for (Tuple<BlockPos, EnumFacing> cover : covers){
            buf.writeBlockPos(cover.getKey());
            buf.writeByte(cover.getValue().getIndex());
        }
    }

    private void readCovers(PacketBuffer buf) {
        covers = new ArrayList<>();
        int size = buf.readInt();
        for (int i = 0; i < size; i++) {
            covers.add(new Tuple<>(buf.readBlockPos(), EnumFacing.byIndex(buf.readByte())));
        }
    }

    private void writeParts(PacketBuffer buf) {
        buf.writeInt(this.getMultiblockParts().size() - 1);
        this.getMultiblockParts().forEach(part->{
            if (part instanceof MetaTileEntityMonitorScreen) {
                buf.writeBlockPos(((MetaTileEntityMonitorScreen) part).getPos());
            }
        });
    }

    private void readParts(PacketBuffer buf) {
        parts = new ArrayList<>();
        screens = new ArrayList<>();
        int size = buf.readInt();
        for (int i = 0; i < size; i++) {
            parts.add(buf.readBlockPos());
        }
    }

    public void setHeight(int height) {
        if(this.height == height || height < 2 || height > MAX_HEIGHT) return;
        this.height = height;
        reinitializeStructurePattern();
        checkStructurePattern();
        writeCustomData(3, buf->{
            buf.writeInt(height);
        });
    }

    private void setActive(boolean isActive) {
        if(isActive == this.isActive) return;
        this.isActive = isActive;
        writeCustomData(4, buf -> buf.writeBoolean(this.isActive));
    }

    public boolean isActive() {
        return isStructureFormed() && isActive;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.SCREEN.renderSided(getFrontFacing(), renderState, translation, pipeline);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gtadditions.multiblock.central_monitor.height", this.height));
        if (!isStructureFormed()) {
            ITextComponent buttonText = new TextComponentTranslation("gtadditions.multiblock.central_monitor.height_modify", new Object[0]);
            buttonText.appendText(" ");
            buttonText.appendSibling(AdvancedTextWidget.withButton(new TextComponentString("[-]"), "sub"));
            buttonText.appendText(" ");
            buttonText.appendSibling(AdvancedTextWidget.withButton(new TextComponentString("[+]"), "add"));
            textList.add(buttonText);
        } else {
            textList.add(new TextComponentTranslation("gtadditions.multiblock.central_monitor.width", this.width));
            textList.add(new TextComponentTranslation("metaitem.tool.prospect.low_power"));
        }
    }

    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        int modifier = componentData.equals("add") ? 1 : -1;
        setHeight(this.height + modifier);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(width);
        buf.writeInt(height);
        buf.writeBoolean(isActive);
        writeCovers(buf);
        writeParts(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.width = buf.readInt();
        this.height = buf.readInt();
        this.isActive = buf.readBoolean();
        readCovers(buf);
        readParts(buf);
    }

    @Override
    public void receiveCustomData(int id, PacketBuffer buf) {
        super.receiveCustomData(id, buf);
        if (id == 1) {
            this.width = buf.readInt();
            this.height = buf.readInt();
            readCovers(buf);
            readParts(buf);
        } else if (id == 2) {
            readCovers(buf);
        } else if (id == 3) {
            this.height = buf.readInt();
        } else if (id == 4) {
            this.isActive = buf.readBoolean();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("screenH", this.height);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.height = data.hasKey("screenH") ? data.getInteger("screenH") : this.height;
        reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityCentralMonitor(metaTileEntityId);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void updateFormedValid() {
        if (this.getTimer() % 20 ==0) {
            setActive(inputEnergy.changeEnergy(ENERGY_COST * this.getMultiblockParts().size()) == ENERGY_COST * this.getMultiblockParts().size());
            if (checkCovers()) {
                this.getMultiblockParts().forEach(part -> {
                    if (part instanceof MetaTileEntityMonitorScreen) {
                        ((MetaTileEntityMonitorScreen) part).updateCoverValid(covers);
                    }
                });
                writeCustomData(2, this::writeCovers);
            }
        }
    }

    @Override
    protected BlockPattern createStructurePattern() {
        StringBuilder start = new StringBuilder("AS");
        StringBuilder slice = new StringBuilder("BB");
        StringBuilder end = new StringBuilder("AA");
        for (int i = 0; i < height - 2; i++) {
            start.append('A');
            slice.append('B');
            end.append('A');
        }
        return FactoryBlockPattern.start(UP, BACK, RIGHT)
                .aisle(start.toString())
                .aisle(slice.toString()).setRepeatable(3, MAX_WIDTH)
                .aisle(end.toString())
                .where('S', selfPredicate())
                .where('A', statePredicate(GAMetaBlocks.getMetalCasingBlockState(Steel)).or(abilityPartPredicate(MultiblockAbility.INPUT_ENERGY)))
                .where('B', tilePredicate((state, tile) -> tile instanceof MetaTileEntityMonitorScreen))
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        lastUpdate = 0;
        currentEnergyNet = new WeakReference<>(null);
        activeNodes = new ArrayList<>();
        covers = new ArrayList<>();
        inputEnergy = new EnergyContainerList(this.getAbilities(MultiblockAbility.INPUT_ENERGY));
        width = (this.getMultiblockParts().size() - 1) / height;
        checkCovers();
        writeCustomData(1, packetBuffer -> {
            packetBuffer.writeInt(width);
            packetBuffer.writeInt(height);
            writeCovers(packetBuffer);
            writeParts(packetBuffer);
        });
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if(side == this.frontFacing.getOpposite() && capability == GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER) {
            return GregtechCapabilities.CAPABILITY_ENERGY_CONTAINER.cast(CoverDigitalInterface.proxyCapability);
        }
        return null;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        if (this.isStructureFormed()) {
            return pass == 0;
        }
        return false;
    }

    @Override
    public void renderMetaTileEntityFast(CCRenderState ccRenderState, Matrix4 matrix4, float partialTicks) {
        if (!this.isStructureFormed()) return;
        GlStateManager.pushMatrix();
        RenderHelper.moveToFace(matrix4.m03 - this.frontFacing.rotateY().getXOffset() * 0.5, matrix4.m13 + height - 1.5, matrix4.m23 - this.frontFacing.rotateY().getZOffset() * 0.5, this.frontFacing);
        RenderHelper.rotateToFace(this.frontFacing, EnumFacing.EAST);
        RenderHelper.renderRect(0, 0, width, height, 0.001f, 0xFF000000);
        GlStateManager.popMatrix();
        if (isActive) {
            GlStateManager.pushMatrix();
            /* hack the lightmap */
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);

            if(screens.size() != parts.size()) {
                screens.clear();
                for (BlockPos pos : parts) {
                    TileEntity tileEntity = getWorld().getTileEntity(pos);
                    if(tileEntity instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityMonitorScreen) {
                        screens.add((MetaTileEntityMonitorScreen) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity());
                    }
                }
            }

            screens.forEach(screen -> {
                if (screen != null && screen.isActive()) {
                    BlockPos pos = screen.getPos();
                    BlockPos pos2 = this.getPos();
                    GlStateManager.pushMatrix();
                    RenderHelper.moveToFace(matrix4.m03 + pos.getX() - pos2.getX(), matrix4.m13 + pos.getY() - pos2.getY(), matrix4.m23 + pos.getZ() - pos2.getZ(), this.frontFacing);
                    RenderHelper.rotateToFace(this.frontFacing, EnumFacing.EAST);
                    screen.renderScreen(partialTicks);
                    GlStateManager.popMatrix();
                }
            });

            /* restore the lightmap  */
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
            net.minecraft.client.renderer.RenderHelper.enableStandardItemLighting();
            GL11.glPopAttrib();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        BlockPos sp = this.getPos().offset(EnumFacing.DOWN);
        BlockPos ep = sp.offset(this.frontFacing.rotateY(), -width - 1).offset(EnumFacing.UP, height);
        return new AxisAlignedBB(sp, ep);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        if (!isActive()) {
            return super.createUI(entityPlayer);
        } else {
            WidgetScreenGrid[][] screenGrids = new WidgetScreenGrid[width][height];
            WidgetGroup group = new WidgetGroup();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    screenGrids[i][j] = new WidgetScreenGrid(4 * width, 4 * height, i, j);
                    group.addWidget(screenGrids[i][j]);
                }
            }
            if (!this.getWorld().isRemote) {
                this.getMultiblockParts().forEach(part->{
                    if (part instanceof MetaTileEntityMonitorScreen) {
                        int x = ((MetaTileEntityMonitorScreen) part).getX() - 1;
                        int y = ((MetaTileEntityMonitorScreen) part).getY() - 1;
                        screenGrids[x][y].setScreen((MetaTileEntityMonitorScreen) part);
                    }
                });
            } else {
                parts.forEach(partPos->{
                    TileEntity tileEntity = this.getWorld().getTileEntity(partPos);
                    if (tileEntity instanceof MetaTileEntityHolder && ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityMonitorScreen) {
                        MetaTileEntityMonitorScreen part = (MetaTileEntityMonitorScreen) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity();
                        int x = part.getX() - 1;
                        int y = part.getY() - 1;
                        screenGrids[x][y].setScreen(part);
                    }
                });
            }
            return ModularUI.builder(GuiTextures.BOXED_BACKGROUND, 28 * width, 28 * height)
                    .widget(group)
                    .build(this.getHolder(), entityPlayer);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.multiblock.central_monitor.tooltip.1"));
        tooltip.add(I18n.format("gtadditions.multiblock.central_monitor.tooltip.2", MAX_WIDTH, MAX_HEIGHT));
        tooltip.add(I18n.format("gtadditions.multiblock.central_monitor.tooltip.3"));
        tooltip.add(I18n.format("gtadditions.multiblock.central_monitor.tooltip.4", -ENERGY_COST));
    }
}
