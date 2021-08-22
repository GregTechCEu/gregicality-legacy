package gregicadditions.recipes.categories;

import gregicadditions.GAConfig;
import gregicadditions.recipes.helper.GACraftingComponents;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Superconductor;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

// TODO Rework all of this
public class SuperconductorRecipes {

    private static final Material[] SUPERCONDUCTORS = {
            null, null, null,
            null, null, null,
            null, null, null,
            UHVSuperconductor, UEVSuperconductor, UIVSuperconductor,
            UMVSuperconductor, UXVSuperconductor};

    private static final Material[] SUPERCONDUCTOR_BASES = {
            null, null, null,
            null, null, null,
            null, null, null,
            UHVSuperconductorBase, UEVSuperconductorBase, UIVSuperconductorBase,
            UMVSuperconductorBase, UXVSuperconductorBase};

    public static void init() {

        if (!GAConfig.Misc.harderSuperconductors) {

            for (int i = GTValues.MV; i <= GTValues.IV; i++) {

                CHEMICAL_BATH_RECIPES.recipeBuilder().duration(200).EUt((int) GTValues.V[i - 1] / 32 * 30)
                        .input(wireGtSingle, SUPERCONDUCTOR_BASES[i], 16)
                        .output(wireGtSingle, SUPERCONDUCTORS[i], 16)
                        .fluidInputs(Nitrogen.getFluid(2000))
                        .buildAndRegister();
            }

            for (int i = GTValues.LuV; i <= GTValues.MAX; i++) {
                for (int j = i - 4; j <= i; j++) {
                    UnificationEntry pipe = (UnificationEntry) GACraftingComponents.PIPE.getIngredient(j);
                    ItemStack pump = ((MetaItem<?>.MetaValueItem) GACraftingComponents.PUMP.getIngredient(j)).getStackForm();
                    int amount = (int) (8 * Math.pow(2, j - i + 4));

                    ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt((int) GTValues.V[i - 1] / 32 * 30)
                            .input(wireGtSingle, SUPERCONDUCTOR_BASES[i], amount)
                            .output(wireGtSingle, SUPERCONDUCTORS[i], amount)
                            .inputs(pump)
                            .input(pipeNormalFluid, pipe.material, 2)
                            .fluidInputs(Nitrogen.getFluid(2000))
                            .buildAndRegister();
                }
            }
        } else {

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(491520)
                    .input(wireGtSingle, UHVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Zeron100, 7)
                    .inputs(ELECTRIC_PUMP_UV.getStackForm())
                    .fluidInputs(Nitrogen.getFluid(12000))
                    .output(wireGtSingle, UHVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(1966080)
                    .input(wireGtSingle, UEVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Lafium, 7)
                    .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                    .fluidInputs(Nitrogen.getFluid(14000))
                    .output(wireGtSingle, UEVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(7864320)
                    .input(wireGtSingle, UIVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Neutronium, 7)
                    .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                    .fluidInputs(Nitrogen.getFluid(16000))
                    .output(wireGtSingle, UIVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(31457280)
                    .input(wireGtSingle, UMVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Neutronium, 7)
                    .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                    .fluidInputs(Nitrogen.getFluid(18000))
                    .output(wireGtSingle, UMVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(125829120)
                    .input(wireGtSingle, UXVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Neutronium, 7)
                    .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                    .fluidInputs(Nitrogen.getFluid(20000))
                    .output(wireGtSingle, UXVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(20).EUt(503316480)
                    .input(wireGtSingle, UXVSuperconductorBase, 64)
                    .input(pipeTinyFluid, Neutronium, 7)
                    .inputs(ELECTRIC_PUMP_UMV.getStackForm())
                    .fluidInputs(Nitrogen.getFluid(22000))
                    .output(wireGtSingle, Superconductor, 64)
                    .buildAndRegister();
        }

        // UHV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(2781).EUt(30)
                .inputs(TBCCODust.getItemStack(4))
                .inputs(StrontiumSuperconductorDust.getItemStack(4))
                .input(dust, Taranium)
                .output(dust, UHVSuperconductorBase, 9)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3081).EUt(30)
                .input(dust, UHVSuperconductorBase, 9)
                .outputs(TBCCODust.getItemStack(4))
                .outputs(StrontiumSuperconductorDust.getItemStack(4))
                .output(dust, Taranium)
                .buildAndRegister();

        // UEV Superconductor Base Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(11292).EUt(30)
                .inputs(ActiniumSuperhydride.getItemStack())
                .inputs(BETSPerrhenate.getItemStack())
                .input(dust, Vibranium, 2)
                .input(dust, Quantum)
                .input(dust, TriniumTitanium)
                .output(dust, UEVSuperconductorBase, 6)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(11892).EUt(30)
                .input(dust, UEVSuperconductorBase, 6)
                .outputs(ActiniumSuperhydride.getItemStack())
                .outputs(StrontiumSuperconductorDust.getItemStack())
                .output(dust, Vibranium, 2)
                .output(dust, Quantum)
                .output(dust, TriniumTitanium)
                .buildAndRegister();

        // UIV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(3395).EUt(30)
                .inputs(BorocarbideDust.getItemStack(2))
                .inputs(FullereneSuperconductiveDust.getItemStack())
                .input(dust, MetastableOganesson, 2)
                .input(dust, ProtoAdamantium, 2)
                .output(dust, UIVSuperconductorBase, 7)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3995).EUt(30)
                .input(dust, UIVSuperconductorBase, 7)
                .outputs(BorocarbideDust.getItemStack(2))
                .outputs(FullereneSuperconductiveDust.getItemStack())
                .output(dust, MetastableOganesson, 2)
                .output(dust, ProtoAdamantium, 2)
                .buildAndRegister();

        // UMV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(720).EUt(8500000)
                .input(dust, BlackTitanium, 3)
                .input(dust, SuperheavyHAlloy, 2)
                .inputs(ChargedCesiumCeriumCobaltIndium.getItemStack(3))
                .inputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(6))
                .output(dust, UMVSuperconductorBase, 14)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(8500000)
                .input(dust, UMVSuperconductorBase, 14)
                .output(dust, BlackTitanium, 3)
                .output(dust, SuperheavyHAlloy, 2)
                .outputs(ChargedCesiumCeriumCobaltIndium.getItemStack(3))
                .outputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(6))
                .buildAndRegister();

        // UXV Superconductor Base Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(720).EUt(33500000)
                .inputs(Legendarium.getItemStack(5))
                .input(dust, Neutronium, 4)
                .inputs(ActiniumSuperhydride.getItemStack(5))
                .inputs(LanthanumFullereneNanotubes.getItemStack(4))
                .inputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(12))
                .output(dust, UXVSuperconductorBase, 30)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(33500000)
                .input(dust, UXVSuperconductorBase, 30)
                .outputs(Legendarium.getItemStack(5))
                .output(dust, Neutronium, 4)
                .outputs(ActiniumSuperhydride.getItemStack(5))
                .outputs(LanthanumFullereneNanotubes.getItemStack(4))
                .outputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(12))
                .buildAndRegister();
    }
}
