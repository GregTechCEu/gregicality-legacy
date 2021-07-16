package gregicadditions.capabilities.impl;

import gregicadditions.GAConfig;
import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAHeatingCoil;
import gregicadditions.machines.multi.IMaintenance;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityMaintenanceHatch;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityMufflerHatch;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.XSTR;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;
import java.util.stream.Collectors;

import static gregicadditions.capabilities.MultiblockDataCodes.STORE_TAPED;
import static gregtech.api.gui.widgets.AdvancedTextWidget.withButton;
import static gregtech.api.gui.widgets.AdvancedTextWidget.withHoverTextTranslate;

public abstract class GARecipeMapMultiblockController extends RecipeMapMultiblockController implements IMaintenance {

    private final List<ItemStack> recoveryItems = new ArrayList<ItemStack>() {{
        add(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash));
    }};

    private final boolean hasMuffler;
    private final boolean hasMaintenance;

    /**
     * When false, this multiblock will behave like any other.
     * When true, this multiblock will treat each of its input buses as distinct,
     * checking recipes for them independently. This is useful for many machines, for example the
     * Large Extruder, where the player may want to put one extruder shape per bus, rather than
     * one machine per extruder shape.
     */
    protected boolean isDistinct = false;

    protected final boolean canDistinct;

    public static final XSTR XSTR_RAND = new XSTR();

    private int timeActive;
    private static int minimumMaintenanceTime = GAConfig.GT5U.minimumMaintenanceTime;

    // Used for data preservation with Maintenance Hatch
    private boolean storedTaped = false;

    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        this(metaTileEntityId, recipeMap, false, true, false);
    }

    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, boolean hasMuffler, boolean hasMaintenance, boolean canDistinct) {
        super(metaTileEntityId, recipeMap);
        this.hasMuffler = hasMuffler;
        this.hasMaintenance = hasMaintenance;
        this.maintenance_problems = 0b000000;
        this.canDistinct = canDistinct;
    }

    /**
     * This value stores whether each of the 5 maintenance problems have been fixed.
     * A value of 0 means the problem is not fixed, else it is fixed
     * Value positions correspond to the following from left to right: 0=Wrench, 1=Screwdriver, 2=Soft Hammer, 3=Hard Hammer, 4=Wire Cutter, 5=Crowbar
     */
    protected byte maintenance_problems;

    /**
     * Sets the maintenance problem corresponding to index to fixed
     *
     * @param index the index of the maintenance problem
     */
    public void setMaintenanceFixed(int index) {
        this.maintenance_problems |= 1 << index;
    }

    /**
     * Used to cause a single random maintenance problem
     */
    protected void causeProblems() {
        this.maintenance_problems &= ~(1 << ((int) (XSTR_RAND.nextFloat()*5)));
    }

    /**
     *
     * @return the byte value representing the maintenance problems
     */
    public byte getProblems() {
        return maintenance_problems;
    }

    /**
     *
     * @return the amount of maintenance problems the multiblock has
     */
    public int getNumProblems() {
        return 6 - Integer.bitCount(maintenance_problems);
    }

    /**
     *
     * @return whether the multiblock has any maintenance problems
     */
    public boolean hasProblems() {
        return this.maintenance_problems < 63;
    }

    /**
     * Used to calculate whether a maintenance problem should happen based on machine time active
     * @param duration duration in ticks to add to the counter of active time
     */
    public void calculateMaintenance(int duration) {
        if (getAbilities(GregicAdditionsCapabilities.MAINTENANCE_HATCH).isEmpty())
            return;
        MetaTileEntityMaintenanceHatch maintenanceHatch = getAbilities(GregicAdditionsCapabilities.MAINTENANCE_HATCH).get(0);
        if (maintenanceHatch.getType() == 2 || !GAConfig.GT5U.enableMaintenance) {
            return;
        }

        timeActive += duration;
        if (minimumMaintenanceTime - timeActive <= 0)
            if(XSTR_RAND.nextFloat() - 0.75f >= 0) {
                causeProblems();
                maintenanceHatch.setTaped(false);
            }
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        if (hasMaintenance) {
            MetaTileEntityMaintenanceHatch maintenanceHatch = getAbilities(GregicAdditionsCapabilities.MAINTENANCE_HATCH).get(0);
            if (maintenanceHatch.getType() == 2 || !GAConfig.GT5U.enableMaintenance) {
                this.maintenance_problems = 0b111111;
            } else {
                readMaintenanceData(maintenanceHatch);
                if (maintenanceHatch.getType() == 0 && storedTaped) {
                    maintenanceHatch.setTaped(true);
                    storeTaped(false);
                }
            }
        }
    }

    private void readMaintenanceData(MetaTileEntityMaintenanceHatch hatch) {
        if (hatch.hasMaintenanceData()) {
            Tuple<Byte, Integer> data = hatch.readMaintenanceData();
            this.maintenance_problems = data.getFirst();
            this.timeActive = data.getSecond();
        }
    }

    @Override
    public void invalidateStructure() {
        if (hasMaintenance) {
            if (getAbilities(GregicAdditionsCapabilities.MAINTENANCE_HATCH).isEmpty())
                return;
            MetaTileEntityMaintenanceHatch maintenance = getAbilities(GregicAdditionsCapabilities.MAINTENANCE_HATCH).get(0);
            if (maintenance.getType() != 2)
                maintenance.storeMaintenanceData(maintenance_problems, timeActive);
        }
        super.invalidateStructure();
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        boolean canForm = super.checkStructureComponents(parts, abilities);
        if (!canForm)
            return false;

        int mufflerCount = abilities.getOrDefault(GregicAdditionsCapabilities.MUFFLER_HATCH, Collections.emptyList()).size();
        int maintenanceCount = abilities.getOrDefault(GregicAdditionsCapabilities.MAINTENANCE_HATCH, Collections.emptyList()).size();

        if (hasMuffler) {
            if (mufflerCount != 1)
                return false;
        } else {
            if (mufflerCount != 0)
                return false;
        }
        return hasMaintenance ? maintenanceCount == 1 : maintenanceCount == 0;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setByte("Maintenance", maintenance_problems);
        data.setInteger("ActiveTimer", timeActive);
        data.setBoolean("IsDistinct", isDistinct);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        maintenance_problems = data.getByte("Maintenance");
        timeActive = data.getInteger("ActiveTimer");
        isDistinct = data.getBoolean("IsDistinct");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeByte(maintenance_problems);
        buf.writeInt(timeActive);
        buf.writeBoolean(isDistinct);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        maintenance_problems = buf.readByte();
        timeActive = buf.readInt();
        isDistinct = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == STORE_TAPED) {
            storedTaped = buf.readBoolean();
        }
    }


    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            IEnergyContainer energyContainer = recipeMapWorkable.getEnergyContainer();
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GAValues.VN[GAUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }

            if (!recipeMapWorkable.isWorkingEnabled()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.work_paused"));

            } else if (recipeMapWorkable.isActive()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.running"));
                int currentProgress = (int) (recipeMapWorkable.getProgressPercent() * 100);
                textList.add(new TextComponentTranslation("gregtech.multiblock.progress", currentProgress));
            } else {
                textList.add(new TextComponentTranslation("gregtech.multiblock.idling"));
            }

            if (recipeMapWorkable.isHasNotEnoughEnergy()) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy").setStyle(new Style().setColor(TextFormatting.RED)));
            }

            // Maintenance Text
            if (hasMuffler && !isMufflerFaceFree()) {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.muffler_obstructed")
                        .setStyle(new Style().setColor(TextFormatting.RED)
                                .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                        new TextComponentTranslation("gtadditions.multiblock.universal.muffler_obstructed.tooltip")))));

            } else if (this.hasProblems()) {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.has_problems")
                        .setStyle(new Style().setColor(TextFormatting.DARK_RED)));

                // Wrench
                if (((this.maintenance_problems >> 0) & 1) == 0) {
                    textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problem.wrench")
                            .setStyle(new Style().setColor(TextFormatting.RED)
                                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            new TextComponentTranslation("gtadditions.multiblock.universal.problem.wrench.tooltip")))));
                }

                // Screwdriver
                if (((this.maintenance_problems >> 1) & 1) == 0) {
                    textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problem.screwdriver")
                            .setStyle(new Style().setColor(TextFormatting.RED)
                                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            new TextComponentTranslation("gtadditions.multiblock.universal.problem.screwdriver.tooltip")))));
                }

                // Soft Hammer
                if (((this.maintenance_problems >> 2) & 1) == 0) {
                    textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problem.softhammer")
                            .setStyle(new Style().setColor(TextFormatting.RED)
                                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            new TextComponentTranslation("gtadditions.multiblock.universal.problem.softhammer.tooltip")))));
                }

                // Hard Hammer
                if (((this.maintenance_problems >> 3) & 1) == 0) {
                    textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problem.hardhammer")
                            .setStyle(new Style().setColor(TextFormatting.RED)
                                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            new TextComponentTranslation("gtadditions.multiblock.universal.problem.hardhammer.tooltip")))));
                }

                // Wirecutter
                if (((this.maintenance_problems >> 4) & 1) == 0) {
                    textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problem.wirecutter")
                            .setStyle(new Style().setColor(TextFormatting.RED)
                                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            new TextComponentTranslation("gtadditions.multiblock.universal.problem.wirecutter.tooltip")))));
                }

                // Crowbar
                if (((this.maintenance_problems >> 5) & 1) == 0) {
                    textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problem.crowbar")
                            .setStyle(new Style().setColor(TextFormatting.RED)
                                    .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                            new TextComponentTranslation("gtadditions.multiblock.universal.problem.crowbar.tooltip")))));
                }
            } else {
                textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.no_problems")
                        .setStyle(new Style().setColor(TextFormatting.GREEN)));

                if (canDistinct) {
                    ITextComponent buttonText = new TextComponentTranslation("gtadditions.multiblock.universal.distinct");
                    buttonText.appendText(" ");
                    ITextComponent button = withButton((isDistinct ?
                            new TextComponentTranslation("gtadditions.multiblock.universal.distinct.yes") :
                            new TextComponentTranslation("gtadditions.multiblock.universal.distinct.no")), "distinct");
                    withHoverTextTranslate(button, "gtadditions.multiblock.universal.distinct.info");
                    buttonText.appendSibling(button);
                    textList.add(buttonText);
                }
            }
        } else {
            ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.invalid_structure.tooltip");
            tooltip.setStyle(new Style().setColor(TextFormatting.GRAY));
            textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure")
                    .setStyle(new Style().setColor(TextFormatting.RED)
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip))));
        }
    }

    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        isDistinct = !isDistinct;
    }

    protected void outputRecoveryItems() {
        MetaTileEntityMufflerHatch muffler = getAbilities(GregicAdditionsCapabilities.MUFFLER_HATCH).get(0);
        muffler.recoverItemsTable(recoveryItems.stream().map(ItemStack::copy).collect(Collectors.toList()));
    }

    @Override
    protected void updateFormedValid() {
        if (!hasMuffler || isMufflerFaceFree())
            super.updateFormedValid();
    }

    public boolean isMufflerFaceFree() {
        return isStructureFormed() && hasMuffler && getAbilities(GregicAdditionsCapabilities.MUFFLER_HATCH).get(0).isFrontFaceFree();
    }

    protected void setRecoveryItems(ItemStack... recoveryItems) {
        this.recoveryItems.clear();
        this.recoveryItems.addAll(Arrays.asList(recoveryItems));
    }

    public boolean isActive() {
        return isStructureFormed() && recipeMapWorkable.isActive();
    }

    public boolean hasMufflerHatch() {
        return hasMuffler;
    }

    public boolean hasMaintenanceHatch() {
        return hasMaintenance;
    }

    public void storeTaped(boolean isTaped) {
        this.storedTaped = isTaped;
        writeCustomData(STORE_TAPED, buf -> {
            buf.writeBoolean(isTaped);
        });
    }

    @SideOnly(Side.CLIENT)
    public void runMufflerEffect(float xPos, float yPos, float zPos, float xSpd, float ySpd, float zSpd) {
        getWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, xPos, yPos, zPos, xSpd, ySpd, zSpd);
    }

    protected static final BlockWireCoil.CoilType[] HEATING_COILS = BlockWireCoil.CoilType.values();
    protected static final GAHeatingCoil.CoilType[] GA_HEATING_COILS = GAHeatingCoil.CoilType.values();
}
