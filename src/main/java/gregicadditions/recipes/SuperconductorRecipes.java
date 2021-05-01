package gregicadditions.recipes;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.item.GAMetaItems.ELECTRIC_PUMP_UMV;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Superconductor;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Nitrogen;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.wireGtSingle;
import static gregtech.common.items.MetaItems.*;

public class SuperconductorRecipes {

    public static void init() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(wireGtSingle, MVSuperconductorBase, 3)
                .input(pipeTiny, StainlessSteel, 2)
                .inputs(ELECTRIC_PUMP_MV.getStackForm(2))
                .fluidInputs(Nitrogen.getFluid(2000))
                .output(wireGtSingle, MVSuperconductor, 3)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(256)
                .input(wireGtSingle, HVSuperconductorBase, 3)
                .input(pipeTiny, Titanium, 2)
                .inputs(ELECTRIC_PUMP_HV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(2000))
                .output(wireGtSingle, HVSuperconductor, 3)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480)
                .input(wireGtSingle, EVSuperconductorBase, 9)
                .input(pipeTiny, TungstenSteel, 6)
                .inputs(ELECTRIC_PUMP_EV.getStackForm(2))
                .fluidInputs(Nitrogen.getFluid(6000))
                .output(wireGtSingle, EVSuperconductor, 9)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .input(wireGtSingle, IVSuperconductorBase, 6)
                .input(pipeTiny, NiobiumTitanium, 4)
                .inputs(ELECTRIC_PUMP_IV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(4000))
                .output(wireGtSingle, IVSuperconductor, 6)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7680)
                .input(wireGtSingle, LuVSuperconductorBase, 8)
                .input(pipeTiny, Enderium, 5)
                .inputs(ELECTRIC_PUMP_LUV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(6000))
                .output(wireGtSingle, LuVSuperconductor, 8)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30720)
                .input(wireGtSingle, ZPMSuperconductorBase, 16)
                .input(pipeTiny, Naquadah, 6)
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .fluidInputs(Nitrogen.getFluid(8000))
                .output(wireGtSingle, ZPMSuperconductor, 16)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(122880)
                .input(wireGtSingle, UVSuperconductorBase, 32)
                .input(pipeTiny, Ultimet, 7)
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .fluidInputs(Nitrogen.getFluid(10000))
                .output(wireGtSingle, UVSuperconductor, 32)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(491520)
                .input(wireGtSingle, UHVSuperconductorBase, 32)
                .input(pipeTiny, Zeron100, 7)
                .inputs(ELECTRIC_PUMP_UV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(12000))
                .output(wireGtSingle, UHVSuperconductor, 32)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(1966080)
                .input(wireGtSingle, UEVSuperconductorBase, 32)
                .input(pipeTiny, Lafium, 7)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(14000))
                .output(wireGtSingle, UEVSuperconductor, 32)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(7864320)
                .input(wireGtSingle, UIVSuperconductorBase, 32)
                .input(pipeTiny, Neutronium, 7)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(16000))
                .output(wireGtSingle, UIVSuperconductor, 32)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(31457280)
                .input(wireGtSingle, UMVSuperconductorBase, 32)
                .input(pipeTiny, Neutronium, 7)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(18000))
                .output(wireGtSingle, UMVSuperconductor, 32)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(125829120)
                .input(wireGtSingle, UXVSuperconductorBase, 32)
                .input(pipeTiny, Neutronium, 7)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(20000))
                .output(wireGtSingle, UXVSuperconductor, 32)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(20).EUt(503316480)
                .input(wireGtSingle, UXVSuperconductorBase, 64)
                .input(pipeTiny, Neutronium, 7)
                .inputs(ELECTRIC_PUMP_UMV.getStackForm())
                .fluidInputs(Nitrogen.getFluid(22000))
                .output(wireGtSingle, Superconductor, 64)
                .buildAndRegister();

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
