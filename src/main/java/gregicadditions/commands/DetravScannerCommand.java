package gregicadditions.commands;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_LanguageManager;
import gregtech.common.blocks.GT_TileEntity_Ores;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.chunk.Chunk;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetravScannerCommand implements ICommand {

    private List aliases;

    public DetravScannerCommand() {
        this.aliases = new ArrayList<String>();
        this.aliases.add("DetravScanner");
        this.aliases.add("dscan");
    }

    @Override
    public String getName() {
        return "DetravScanner";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "DetravScanner [\"Part of Greg ore name\"]";
    }

    @Override
    public List getAliases() {
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        int aX = 0;
        int aZ = 0;
        int aY = 0;
        String name = null;

        ArrayList<String> strs = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {
            strs.add(args[i]);
            if (args[i].startsWith("\"")) {
                for (i++; i < args.length; i++) {
                    String temp = (String) strs.get(strs.size() - 1);
                    temp = temp + " " + args[i];
                    temp = temp.replace("\"", "");
                    strs.set(strs.size() - 1, temp);
                    if (args[i].endsWith("\""))
                        break;
                }
            }
        }
        args = new String[strs.size()];
        args = strs.toArray(args);

        switch (args.length) {
            case 0:
                break;
            case 1:
                if (args[0].toLowerCase() == "help") {
                    sendHelpMessage(sender);
                    return;
                }
                name = args[0];
                break;
            default:
                sendHelpMessage(sender);
                return;
        }
        BlockPos c = sender.getPosition();
        if (name != null) name = name.toLowerCase();
        process(sender, (int) Math.floor(c.getX() / 16.0), (int) Math.floor(c.getZ() / 16.0), name);
    }

    private void process(ICommandSender sender, int aX, int aZ, String fName) {
        Chunk c = sender.getEntityWorld().getChunk(aX, aZ);
        HashMap<String, Integer> ores = new HashMap<String, Integer>();
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++) {
                int ySize = c.getHeightValue(x, z);
                for (int y = 1; y < ySize; y++) {
                    Block b = c.getBlockState(x, y, z).getBlock();
                    if (b == GregTech_API.sBlockOres1) {
                        TileEntity entity = c.getTileEntity(new BlockPos(x, y, z), Chunk.EnumCreateEntityType.CHECK);
                        if (entity != null) {
                            GT_TileEntity_Ores gt_entity = (GT_TileEntity_Ores) entity;
                            short meta = gt_entity.getMetaData();
                            String name = Materials.getLocalizedNameForItem(
                                    GT_LanguageManager.getTranslation(b.getUnlocalizedName() + "." + meta + ".name"), meta % 1000);
                            if (name.startsWith("Small")) continue;
                            if (fName == null || name.toLowerCase().contains(fName)) {
                                if (!ores.containsKey(name))
                                    ores.put(name, 1);
                                else {
                                    int val = ores.get(name);
                                    ores.put(name, val + 1);
                                }
                            }
                        }
                    }
                }

            }
        sender.sendMessage(new TextComponentString("*** Detrav Scanner Begin"));
        for (String key : ores.keySet()) {
            sender.sendMessage(new TextComponentString(String.format("%s : %d", key, ores.get(key))));
        }
        sender.sendMessage(new TextComponentString("*** Detrav Scanner End"));
    }

    private void sendHelpMessage(ICommandSender sender) {
        sender.sendMessage(new TextComponentString(getUsage(sender)));
    }


    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length != 1) return null;
        if ("help".startsWith(args[0].toLowerCase())) {
            List result = new ArrayList();
            result.add("help");
            sendHelpMessage(sender);
            return result;
        }
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(@NotNull ICommand o) {
        return 0;
    }
}
