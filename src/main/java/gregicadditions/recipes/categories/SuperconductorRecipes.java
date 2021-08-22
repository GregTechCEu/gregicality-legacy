package gregicadditions.recipes.categories;

import gregicadditions.GAMaterials;
import gregtech.api.unification.material.Material;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.LARGE_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

// TODO Rework all of this
public class SuperconductorRecipes {

    private static final Material[] SUPERCONDUCTORS = {
            null, null, null,
            null, null, null,
            null, null, null,
            GAMaterials.StrontiumTaraniumTBCCO, ActiniumVibraniumBETSSuperhydride, ProtoFullereneBorocarbide,
            SuperheavyChargedBlackTitanium, NeutroniumLegendariumSuperhydride};

    private static final Material[] SUPERCONDUCTOR_BASES = {
            null, null, null,
            null, null, null,
            null, null, null,
            GAMaterials.StrontiumTaraniumTBCCO, GAMaterials.ActiniumVibraniumBETSSuperhydride, GAMaterials.ProtoFullereneBorocarbide,
            GAMaterials.SuperheavyChargedBlackTitanium, GAMaterials.NeutroniumLegendariumSuperhydride};

    public static void init() {

        // UHV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(2781).EUt(30)
                .inputs(TBCCODust.getItemStack(4))
                .inputs(StrontiumSuperconductorDust.getItemStack(4))
                .input(dust, Taranium)
                .output(dust, GAMaterials.StrontiumTaraniumTBCCO, 9)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3081).EUt(30)
                .input(dust, GAMaterials.StrontiumTaraniumTBCCO, 9)
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
                .output(dust, GAMaterials.ActiniumVibraniumBETSSuperhydride, 6)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(11892).EUt(30)
                .input(dust, GAMaterials.ActiniumVibraniumBETSSuperhydride, 6)
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
                .output(dust, GAMaterials.ProtoFullereneBorocarbide, 7)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3995).EUt(30)
                .input(dust, GAMaterials.ProtoFullereneBorocarbide, 7)
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
                .output(dust, GAMaterials.SuperheavyChargedBlackTitanium, 14)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(8500000)
                .input(dust, GAMaterials.SuperheavyChargedBlackTitanium, 14)
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
                .output(dust, GAMaterials.NeutroniumLegendariumSuperhydride, 30)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(33500000)
                .input(dust, GAMaterials.NeutroniumLegendariumSuperhydride, 30)
                .outputs(Legendarium.getItemStack(5))
                .output(dust, Neutronium, 4)
                .outputs(ActiniumSuperhydride.getItemStack(5))
                .outputs(LanthanumFullereneNanotubes.getItemStack(4))
                .outputs(RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate.getItemStack(12))
                .buildAndRegister();
    }
}
