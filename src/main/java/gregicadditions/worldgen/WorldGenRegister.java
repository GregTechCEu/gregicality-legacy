package gregicadditions.worldgen;


import gregtech.api.GTValues;
import gregtech.api.util.GTLog;
import net.minecraftforge.fml.common.Loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class WorldGenRegister {
    public static void init() {
        long time = System.currentTimeMillis();
        GTLog.logger.info("WorldGen init started");
        try {
            WorldGenRegister.removeGTConfigs();
        } catch (IOException e) {
            GTLog.logger.fatal("Failed to replace GT worldgen configs", e);
        }


        float t = (System.currentTimeMillis() * 1.0F) / (time * 1.0F);
        GTLog.logger.info(String.format("WorldGen init finished for %.3f seconds", t));
    }

    private static void removeGTConfigs() throws IOException {
        Path configPath = Loader.instance().getConfigDir().toPath().resolve(GTValues.MODID);
        Path worldgenRootPath = configPath.resolve("worldgen");
        Path gtUnpacked = configPath.resolve("worldgen_extracted");
        Path extractedLock = configPath.resolve("gt_replaced");
        String[] dims = new String[]{"end", "nether", "overworld"};
        if (Files.exists(gtUnpacked) && !Files.exists(extractedLock)) {
            for (String dim : dims) {
                Path currentDir = worldgenRootPath.resolve(dim);
                List<Path> configs = Files.walk(currentDir)
                        .filter(file -> Files.isRegularFile(file))
                        .filter(file -> file.toString().endsWith(".json"))
                        .filter(file ->
                                file.getFileName().toString().startsWith("platinum_vein.json")
                                || file.getFileName().toString().startsWith("olivine_vein.json")
                        )
                        .collect(Collectors.toList());
                for (Path config : configs) {
                    GTLog.logger.info(String.format("Removing GT worldgen config %s", config.getFileName().toString()));
                    Files.delete(config);
                }
            }
        }
    }
}