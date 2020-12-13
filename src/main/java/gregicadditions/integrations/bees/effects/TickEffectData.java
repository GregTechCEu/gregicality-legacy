package gregicadditions.integrations.bees.effects;

import forestry.api.genetics.IEffectData;
import net.minecraft.nbt.NBTTagCompound;

public class TickEffectData implements IEffectData {
    private int lastTick = 0;

    @Override
    public void setInteger(int index, int val) {
        lastTick = val;
    }

    @Override
    public void setBoolean(int index, boolean val) {

    }

    @Override
    public int getInteger(int index) {
        return lastTick;
    }

    @Override
    public boolean getBoolean(int index) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        return nbt;
    }
}
