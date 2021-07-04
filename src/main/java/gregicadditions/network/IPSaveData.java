package gregicadditions.network;

import gregicadditions.worldgen.DimensionChunkCoords;
import gregicadditions.worldgen.PumpjackHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import javax.annotation.Nonnull;

import java.util.Map;

public class IPSaveData extends WorldSavedData {
    //	private static HashMap<Integer, IESaveData> INSTANCE = new HashMap<Integer, IESaveData>();
    private static IPSaveData INSTANCE;
    public static final String dataName = "Gregicality-SaveData";

    public IPSaveData(String s) {
        super(s);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList oilList = nbt.getTagList("oilInfo", 10);
        PumpjackHandler.oilCache.clear();
        for (int i = 0; i < oilList.tagCount(); i++) {
            NBTTagCompound tag = oilList.getCompoundTagAt(i);
            DimensionChunkCoords coords = DimensionChunkCoords.readFromNBT(tag);
            if (coords != null) {
                PumpjackHandler.OilWorldInfo info = PumpjackHandler.OilWorldInfo.readFromNBT(tag.getCompoundTag("info"));
                PumpjackHandler.oilCache.put(coords, info);
            }
        }


    }

    @Override
    public @Nonnull NBTTagCompound writeToNBT(@Nonnull NBTTagCompound nbt) {
        NBTTagList oilList = new NBTTagList();
        for (Map.Entry<DimensionChunkCoords, PumpjackHandler.OilWorldInfo> e : PumpjackHandler.oilCache.entrySet()) {
            if (e.getKey() != null && e.getValue() != null) {
                NBTTagCompound tag = e.getKey().writeToNBT();
                tag.setTag("info", e.getValue().writeToNBT());
                oilList.appendTag(tag);
            }
        }
        nbt.setTag("oilInfo", oilList);

        return nbt;
    }


    public static void setDirty(int dimension) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && INSTANCE != null)
            INSTANCE.markDirty();
    }

    public static void setInstance(int dimension, IPSaveData in) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
            INSTANCE = in;
    }

}