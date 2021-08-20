package gregicadditions.worldgen;


import gregicadditions.utils.GALog;
import gregtech.api.GTValues;
import gregtech.api.worldgen.config.WorldGenRegistry;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WorldGenRegister {

    public static void preInit() {
        GALog.logger.info("Adding Gregicality block filler to the ore generation registry");
        WorldGenRegistry.INSTANCE.registerBlockFiller("ga_simple", GABlockFiller::new);
    }

    public static void init() throws IOException {
        long time = System.currentTimeMillis();
        GALog.logger.info("WorldGen preInit2 started");

        try {
            WorldGenRegister.removeGTConfigs();
        } catch (IOException e) {
            GALog.logger.fatal("Failed to replace GT worldgen configs", e);
        }

        try {
            WorldGenRegister.copyCustomConfigs();
        } catch (IOException exception) {
            GALog.logger.fatal("Failed to add GA worldgen", exception);
        }

//        GregicalityLogger.logger.info("Reloading ore vein definitions to use our block filler");
//        WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();
        float t = (System.currentTimeMillis() * 1.0F) / (time * 1.0F);
        GALog.logger.info(String.format("WorldGen preInit2 finished for %.3f seconds", t));
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
                                        || file.getFileName().toString().startsWith("sulfur_vein.json")
                                        || file.getFileName().toString().startsWith("olivine_vien2.json")
                                        || file.getFileName().toString().startsWith("magnetite_vein.json")
                                        || file.getFileName().toString().startsWith("pitchblende_vein.json")
                                        || file.getFileName().toString().startsWith("pitchblende_vein2.json")
                                        || file.getFileName().toString().startsWith("naquadah_vein.json")
                                        || file.getFileName().toString().startsWith("tungstate_vein.json")
                        )
                        .collect(Collectors.toList());
                for (Path config : configs) {
                    GALog.logger.info(String.format("Removing GT worldgen config %s", config.getFileName().toString()));
                    Files.delete(config);
                }
            }
        }
    }

    private static void copyCustomConfigs() throws IOException {
        Path configPath = Loader.instance().getConfigDir().toPath().resolve(GTValues.MODID);
        Path worldgenRootPath = configPath.resolve("worldgen");
        Path jarFileExtractLock = configPath.resolve("ga_worldgen_10");
        if (!Files.exists(worldgenRootPath)) {
            Files.createDirectories(worldgenRootPath);
        }


        if (!Files.exists(jarFileExtractLock) || !Files.list(worldgenRootPath).peek(path -> GALog.logger.info(path)).findFirst().isPresent()) {
            if (!Files.exists(jarFileExtractLock)) {
                Files.createFile(jarFileExtractLock);
            }
            WorldGenRegister.extractJarVeinDefinitions(worldgenRootPath, "/assets/gregtech/worldgen");
        }

    }

    private static void extractJarVeinDefinitions(Path worldgenRootPath, String directory) throws IOException {
        FileSystem zipFileSystem = null;
        try {
            URI sampleUri = WorldGenRegister.class.getResource("/assets/gregtech/assetsroot").toURI();
            Path worldgenJarRootPath;
            if (sampleUri.getScheme().equals("jar") || sampleUri.getScheme().equals("zip")) {
                zipFileSystem = FileSystems.newFileSystem(sampleUri, Collections.emptyMap());
                worldgenJarRootPath = zipFileSystem.getPath(directory);
            } else if (sampleUri.getScheme().equals("file")) {
                worldgenJarRootPath = Paths.get(WorldGenRegister.class.getResource(directory).toURI());
            } else {
                throw new IllegalStateException("Unable to locate absolute path to worldgen root directory: " + sampleUri);
            }
            GALog.logger.info(String.format("Attempting extraction of worldgen definitions from %s to %s",
                    worldgenJarRootPath, worldgenRootPath));
            List<Path> jarFiles = Files.walk(worldgenJarRootPath)
                    .filter(jarFile -> Files.isRegularFile(jarFile))
                    .collect(Collectors.toList());
            for (Path jarFile : jarFiles) {
                Path worldgenPath = worldgenRootPath.resolve(worldgenJarRootPath.relativize(jarFile).toString());
                Files.createDirectories(worldgenPath.getParent());
                Files.copy(jarFile, worldgenPath, StandardCopyOption.REPLACE_EXISTING);
            }
            GALog.logger.info(String.format("Extracted %s builtin worldgen definitions into worldgen folder", jarFiles.size()));
        } catch (URISyntaxException impossible) {
            throw new RuntimeException(impossible);
        } finally {
            if (zipFileSystem != null) {
                IOUtils.closeQuietly(zipFileSystem);
            }
        }
    }
}