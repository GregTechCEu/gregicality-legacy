package gregicadditions.capabilities.impl;

import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.machines.multi.multiblockpart.MetaTileEntityMufflerHatch;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.XSTR;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;

import java.util.*;
import java.util.stream.Collectors;

public abstract class GARecipeMapMultiblockController extends RecipeMapMultiblockController {

    private final List<ItemStack> recoveryItems = new ArrayList<ItemStack>() {{
        add(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash));
    }};
    private final boolean hasMuffler;
    private final boolean hasMaintenance;

    public static final XSTR XSTR_RAND = new XSTR();

    private int timeActive;
    private static final int minimumMaintenanceTime = 5184000; // 72 real-life hours = 5184000 ticks

    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        this(metaTileEntityId, recipeMap, false, true);
    }

    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, boolean hasMuffler, boolean hasMaintenance) {
        super(metaTileEntityId, recipeMap);
        this.hasMuffler = hasMuffler;
        this.hasMaintenance = hasMaintenance;
        this.maintenance_problems = 0b000000;
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
        timeActive += duration;
        if (minimumMaintenanceTime - timeActive <= 0)
            if(XSTR_RAND.nextFloat() - 0.75f >= 0)
                causeProblems();
    }

    @Override
    protected boolean checkStructureComponents(List<IMultiblockPart> parts, Map<MultiblockAbility<Object>, List<Object>> abilities) {
        boolean canForm = super.checkStructureComponents(parts, abilities);
        if (!canForm)
            return false;

        int mufflerCount = abilities.getOrDefault(GregicAdditionsCapabilities.MUFFLER_HATCH, Collections.emptyList()).size();
        int maintenanceCount = abilities.getOrDefault(GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY, Collections.emptyList()).size();

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
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        maintenance_problems = data.getByte("Maintenance");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeByte(maintenance_problems);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.maintenance_problems = buf.readByte();
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
            }
        } else {
            ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.invalid_structure.tooltip");
            tooltip.setStyle(new Style().setColor(TextFormatting.GRAY));
            textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure")
                    .setStyle(new Style().setColor(TextFormatting.RED)
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip))));
        }
    }

    protected void outputRecoveryItems() {
        MetaTileEntityMufflerHatch muffler = getAbilities(GregicAdditionsCapabilities.MUFFLER_HATCH).get(0);
        muffler.recoverItems(recoveryItems.stream().map(ItemStack::copy).collect(Collectors.toList()));
    }

    @Override
    protected void updateFormedValid() {
        if (isMufflerFaceFree())
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

    public boolean hasMaintenanceHatch() { return hasMaintenance; }
}
