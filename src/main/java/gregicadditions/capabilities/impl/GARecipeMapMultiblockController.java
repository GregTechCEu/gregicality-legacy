package gregicadditions.capabilities.impl;

import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;

import java.util.List;

public abstract class GARecipeMapMultiblockController extends RecipeMapMultiblockController {


    public GARecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
        super(metaTileEntityId, recipeMap);
    }

    /**
     * This value stores whether each of the 5 maintenance problems have been fixed.
     * A value of 0 means the problem is not fixed, else it is fixed
     * Value positions correspond to the following from left to right: 0=Wrench, 1=Screwdriver, 2=Soft Hammer, 3=Hard Hammer, 4=Wire Cutter, 5=Crowbar
     */
    protected byte maintenance_problems = 0b000000;

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
        this.maintenance_problems &= ~(1 << ((int) (Math.random()*5)));
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
        return Integer.bitCount(maintenance_problems);
    }

    /**
     *
     * @return whether the multiblock has any maintenance problems
     */
    public boolean hasProblems() {
        return this.maintenance_problems < 63;
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
        } else {
            ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.invalid_structure.tooltip");
            tooltip.setStyle(new Style().setColor(TextFormatting.GRAY));
            textList.add(new TextComponentTranslation("gregtech.multiblock.invalid_structure")
                    .setStyle(new Style().setColor(TextFormatting.RED)
                            .setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip))));
        }

        textList.add(new TextComponentTranslation("gtadditions.multiblock.universal.problems", this.maintenance_problems));
    }
}
