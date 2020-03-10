package gregicadditions.integrations.exnihilocreatio;

import gregicadditions.GAConfig;
import gregicadditions.GregicAdditions;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.render.Textures;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;

import static gregicadditions.machines.GATileEntities.location;

@Mod.EventBusSubscriber(modid = GregicAdditions.MODID)
public class ExNihiloCreatioProxy {

    public static SimpleMachineMetaTileEntity[] SIEVES = new SimpleMachineMetaTileEntity[8];

    public void preInit() {
        if (!GAConfig.exNihilo.Disable && Loader.isModLoaded("exnihilocreatio")) {
            SIEVES[0] = GregTechAPI.registerMetaTileEntity(2224, new SimpleMachineMetaTileEntity(location("sieve.lv"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 1));
            SIEVES[1] = GregTechAPI.registerMetaTileEntity(2225, new SimpleMachineMetaTileEntity(location("sieve.mv"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 2));
            SIEVES[2] = GregTechAPI.registerMetaTileEntity(2226, new SimpleMachineMetaTileEntity(location("sieve.hv"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 3));
            SIEVES[3] = GregTechAPI.registerMetaTileEntity(2227, new SimpleMachineMetaTileEntity(location("sieve.ev"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 4));
            if (GAConfig.exNihilo.highTierSieve) {
                SIEVES[4] = GregTechAPI.registerMetaTileEntity(2228, new SimpleMachineMetaTileEntity(location("sieve.iv"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 5));
                SIEVES[5] = GregTechAPI.registerMetaTileEntity(2229, new SimpleMachineMetaTileEntity(location("sieve.luv"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 6));
                SIEVES[6] = GregTechAPI.registerMetaTileEntity(2230, new SimpleMachineMetaTileEntity(location("sieve.zpm"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 7));
                SIEVES[7] = GregTechAPI.registerMetaTileEntity(2231, new SimpleMachineMetaTileEntity(location("sieve.uv"), GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, 8));
            }
        }

    }
}
