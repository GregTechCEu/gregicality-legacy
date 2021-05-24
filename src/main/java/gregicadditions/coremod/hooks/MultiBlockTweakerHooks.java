package gregicadditions.coremod.hooks;

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MultiBlockTweakerHooks {

    //origin: eutros.multiblocktweaker.client.onUse().getMetaController()
    public static MultiblockControllerBase getMetaController(World world, BlockPos targetPos) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("metaitem.freedom_wrench.mbt_installed"));
        return null;
    }
}
