package gtadditions;

import gregicadditions.GAEnums;
import gregicadditions.GAMaterials;
import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.GTLog;
import gregtech.common.MetaFluids;
import net.minecraft.init.Bootstrap;
import org.apache.logging.log4j.LogManager;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaterialIdTest {

    /**
     * Initialize registries
     */
    @BeforeClass
    public static void bootStrap() {
        Bootstrap.register();

        // Bootstrap the GTCE Material System
        GTLog.init(LogManager.getLogger(GTValues.MODID)); // yes this was necessary
        Materials.register();
        GAEnums.preInit();
        GAMaterials gaMaterials = new GAMaterials();
        gaMaterials.onMaterialsInit();
        Material.freezeRegistry();
        MetaFluids.init();
    }

    /**
     * Basic Nonnull test to try.
     *
     * The real test is in the bootStrap, where if there are conflicting material IDs registered,
     * it will throw an {@link IllegalArgumentException} and fail the test.
      */
    @Test
    public void areMaterialsGenerated() {
        assertNotNull(
                "OreDictUnifier failed to gather a GTCE Material ItemStack",
                Materials.Carbon
        );
        assertNotNull(
                "OreDictUnifier failed to gather a Gregicality Material ItemStack",
                GAMaterials.Tumbaga
        );
    }
}
