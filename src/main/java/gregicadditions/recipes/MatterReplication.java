package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.utils.GALog;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraftforge.fluids.FluidStack;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class MatterReplication {
    public static void init() {
        GARecipeMaps.LARGE_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BosionicUUMatter.getFluid(1000))
                .fluidInputs(FermionicUUMatter.getFluid(1000))
                .fluidInputs(FreeElectrons.getFluid(2000))
                .fluidOutputs(Materials.UUMatter.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material.element == null)
                continue;
            int mass = (int) material.getMass();
            FluidStack uuFluid = mass % 2 == 0 ? BosionicUUMatter.getFluid(mass) : FermionicUUMatter.getFluid(mass);
            if (((FluidMaterial) material).getMaterialFluid() != null) {
                int amount = material instanceof IngotMaterial ? 144 : 1000;
                GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder()
                        .fluidInputs(((FluidMaterial) material).getFluid(amount))
                        .fluidOutputs(uuFluid)
                        .fluidOutputs(FreeElectrons.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
                if (material.hasFlag(DISABLE_REPLICATION))
                    continue;
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder()
                        .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                        .notConsumable(((FluidMaterial) material).getFluid(amount))
                        .fluidInputs(uuFluid)
                        .fluidInputs(FreeElectrons.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder()
                        .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                        .notConsumable(((FluidMaterial) material).getFluid(amount))
                        .fluidInputs(Materials.UUMatter.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
            } else {
                GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder()
                        .input(dust, material)
                        .fluidOutputs(uuFluid)
                        .fluidOutputs(FreeElectrons.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
                if (material.hasFlag(DISABLE_REPLICATION))
                    continue;
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder()
                        .output(dust, material)
                        .notConsumable(dust, material)
                        .fluidInputs(uuFluid)
                        .fluidInputs(FreeElectrons.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder()
                        .output(dust, material)
                        .notConsumable(dust, material)
                        .fluidInputs(Materials.UUMatter.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
            }

        }
    }
}
