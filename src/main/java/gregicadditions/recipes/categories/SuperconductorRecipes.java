package gregicadditions.recipes.categories;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

// TODO Rework all of this
public class SuperconductorRecipes {
    public static void init() {

        // UHV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(2781).EUt(30)
                .input(dust, TBCCO)
                .input(dust, StrontiumSuperconductor, 4)
                .input(dust, Taranium)
                .output(dust, StrontiumTaraniumTBCCO, 9)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3081).EUt(30)
                .input(dust, StrontiumTaraniumTBCCO, 9)
                .output(dust, TBCCO, 4)
                .output(dust, StrontiumSuperconductor, 4)
                .output(dust, Taranium)
                .buildAndRegister();

        // UEV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(11292).EUt(30)
                .input(dust, ActiniumSuperhydride)
                .input(dust, BETSPerrhenate)
                .input(dust, Vibranium, 2)
                .input(dust, Quantum)
                .input(dust, TriniumTitanium)
                .output(dust, ActiniumVibraniumBETSSuperhydride, 6)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(11892).EUt(30)
                .input(dust, ActiniumVibraniumBETSSuperhydride, 6)
                .output(dust, ActiniumSuperhydride)
                .output(dust, StrontiumSuperconductor)
                .output(dust, Vibranium, 2)
                .output(dust, Quantum)
                .output(dust, TriniumTitanium)
                .buildAndRegister();

        // UIV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(3395).EUt(30)
                .input(dust, Borocarbide, 2)
                .input(dust, FullereneSuperconductor)
                .input(dust, MetastableOganesson, 2)
                .input(dust, ProtoAdamantium, 2)
                .output(dust, ProtoFullereneBorocarbide, 7)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3995).EUt(30)
                .input(dust, ProtoFullereneBorocarbide, 7)
                .output(dust, Borocarbide, 2)
                .output(dust, FullereneSuperconductor)
                .output(dust, MetastableOganesson, 2)
                .output(dust, ProtoAdamantium, 2)
                .buildAndRegister();

        // UMV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(720).EUt(8500000)
                .input(dust, BlackTitanium, 3)
                .input(dust, SuperheavyHAlloy, 2)
                .input(dust, ChargedCesiumCeriumCobaltIndium, 3)
                .input(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 6)
                .output(dust, SuperheavyChargedBlackTitanium, 14)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(8500000)
                .input(dust, SuperheavyChargedBlackTitanium, 14)
                .output(dust, BlackTitanium, 3)
                .output(dust, SuperheavyHAlloy, 2)
                .output(dust, ChargedCesiumCeriumCobaltIndium, 3)
                .output(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 6)
                .buildAndRegister();

        // UXV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(720).EUt(33500000)
                .input(dust, Legendarium, 5)
                .input(dust, Neutronium, 4)
                .input(dust, ActiniumSuperhydride, 5)
                .input(dust, LanthanumFullereneNanotubes, 4)
                .input(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 12)
                .output(dust, NeutroniumLegendariumSuperhydride, 30)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(33500000)
                .input(dust, NeutroniumLegendariumSuperhydride, 30)
                .output(dust, Legendarium, 5)
                .output(dust, Neutronium, 4)
                .output(dust, ActiniumSuperhydride, 5)
                .output(dust, LanthanumFullereneNanotubes, 4)
                .output(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 12)
                .buildAndRegister();
    }
}
