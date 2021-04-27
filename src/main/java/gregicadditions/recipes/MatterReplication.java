package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.utils.GALog;
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
/*        //Mass Fab
        GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (Materials.Hydrogen.getMass() * GAConfig.Misc.replicationTimeFactor)).EUt(32).fluidInputs(Materials.Hydrogen.getFluid(1000)).fluidOutputs(GAMaterials.PositiveMatter.getFluid(1)).buildAndRegister();
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!m.hasFlag(DISABLE_REPLICATION) && m.getProtons() > 0 && m.getNeutrons() > 0 && m.getMass() != 98 && m instanceof FluidMaterial && OreDictUnifier.get(dust, m).isEmpty()) {
                GARecipeMaps.MASS_FAB_RECIPES.recipeBuilder().duration((int) (m.getAverageMass() * GAConfig.Misc.replicationTimeFactor)).EUt(32).fluidInputs(((FluidMaterial) m).getFluid(1000)).fluidOutputs(GAMaterials.PositiveMatter.getFluid((int) (m.getAverageProtons())), GAMaterials.NeutralMatter.getFluid((int) (m.getAverageNeutrons()))).buildAndRegister();
            }
        }

        //Replicator
        GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (Materials.Hydrogen.getMass() * GAConfig.Misc.replicationTimeFactor * 2)).EUt(32).inputs(FluidCellIngredient.getIngredient(Materials.Hydrogen, 0)).fluidOutputs(Materials.Hydrogen.getFluid(1000)).fluidInputs(GAMaterials.PositiveMatter.getFluid(1)).buildAndRegister();
        for (Material m : Material.MATERIAL_REGISTRY) {
            if (!m.hasFlag(DISABLE_REPLICATION) && m.getProtons() > 0 && m.getNeutrons() > 0 && m.getMass() != 98 && m instanceof FluidMaterial && OreDictUnifier.get(dust, m).isEmpty() && m != Materials.Air && m != Materials.LiquidAir) {
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder().duration((int) (m.getAverageMass() * GAConfig.Misc.replicationTimeFactor * 2)).EUt(32)
                        .inputs(FluidCellIngredient.getIngredient((FluidMaterial) m, 0))
                        .fluidOutputs(((FluidMaterial) m).getFluid(1000)).fluidInputs(GAMaterials.PositiveMatter.getFluid((int) (m.getAverageProtons())), GAMaterials.NeutralMatter.getFluid((int) (m.getAverageNeutrons())))
                        .buildAndRegister();
            }
        }*/
        for (Material material : Material.MATERIAL_REGISTRY) {
            if (material.element == null)
                continue;
            int mass = (int) material.getMass();
            FluidStack uuFluid = mass % 2 == 0 ? BosionicUUMatter.getFluid(mass) : FermionicUUMatter.getFluid(mass);
            if (((FluidMaterial) material).getMaterialFluid() != null) {
                GALog.logger.info(material.getUnlocalizedName());
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
                        .fluidInputs(uuFluid)
                        .fluidInputs(FreeElectrons.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder()
                        .fluidOutputs(((FluidMaterial) material).getFluid(amount))
                        .fluidInputs(Materials.UUMatter.getFluid((int) (mass * 1.5)))
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
                        .fluidInputs(uuFluid)
                        .fluidInputs(FreeElectrons.getFluid(mass))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
                GARecipeMaps.REPLICATOR_RECIPES.recipeBuilder()
                        .output(dust, material)
                        .fluidInputs(Materials.UUMatter.getFluid((int) (mass * 1.5)))
                        .duration(mass * GAConfig.Misc.replicationTimeFactor)
                        .EUt(32)
                        .buildAndRegister();
            }

        }
    }
}
