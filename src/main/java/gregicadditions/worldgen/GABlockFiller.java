package gregicadditions.worldgen;

import com.google.gson.JsonObject;
import gregtech.api.worldgen.filler.BlockFiller;
import gregtech.api.worldgen.filler.FillerEntry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Collections;
import java.util.List;

public class GABlockFiller extends BlockFiller {

    private FillerEntry fillerEntry;

    public GABlockFiller() {
    }

    @Override
    public void loadFromConfig(JsonObject jsonObject) {
        this.fillerEntry = GAFillerUtils.createBlockStateFiller(jsonObject.get("value"));
    }

    @Override
    public IBlockState apply(IBlockState currentState, IBlockAccess blockAccess, BlockPos blockPos, int relativeX, int relativeY, int relativeZ) {
        return fillerEntry.apply(currentState, blockAccess, blockPos);
    }

    @Override
    public List<FillerEntry> getAllPossibleStates() {
        return Collections.singletonList(fillerEntry);
    }
}
