package gregicadditions.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static gregicadditions.GAEnums.GAOrePrefix.oxide;

public class REEChain {
    public static void init() {

        /*
         * When I use [REE], it could be any of the below, in any combination,
         * [La, Pr, Nd, Ce, Sc, Eu, Gd, Sm, Y, Tb, Dy, Ho, Er, Tm, Yt, Lu],
         * being all Rare Earth Elements, excluding Promethium (Pm).
         *
         * Unknown compounds key:
         * Acidic Salt Water: 3 == H2SO4(NaCl)3(H2O)3
         * SulfuricBromineSolution: H2SO4Br(H2O)
         * DebrominatedWater: H2O, basically
         * HotVapourMixture: H2SO4Br(H2O)2
         * DampBromine: Br(H2O)
         * RareEarth: [REE]
         * RareEarthHydroxidesSolution: Na[REE]-OH(H2O)
         * RareEarthChloridesSolution: HNaOH[REE]-Cl(H2O)
         * ThUSludge: ThU
         * LaNdOxidesSolution: LaPrNdCeOx
         * SmGdOxidesSolution: ScEuGdSmOx
         * TbHoOxidesSolution: YTbDyHoOx
         * ErLuOxidesSolution: ErTmYtLuOx
         */

        // 3NaCl(H2O) + 2Cl + H2SO4 -> H2SO4(NaCl)3(H2O)3
        // Formula above multiplied up for simplicity
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SaltWater.getFluid(1500))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(500))
                .fluidOutputs(AcidicSaltWater.getFluid(3000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // 2H2SO4(NaCl)3(H2O)3 -> 6NaCl + 2H2SO4Br(H2O) + 4H2O
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(AcidicSaltWater.getFluid(6000))
                .output(dust, Salt, 12)
                .fluidOutputs(SulfuricBromineSolution.getFluid(2000))
                .fluidOutputs(DebrominatedWater.getFluid(4000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // H2SO4Br(H2O) + H2O -> H2SO4Br(H2O)2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricBromineSolution.getFluid(1000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(HotVapourMixture.getFluid(2000))
                .EUt(480)
                .duration(150)
                .buildAndRegister();

        // H2SO4Br(H2O)2 -> H2SO4(H2O) + Br(H2O)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(HotVapourMixture.getFluid(2000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(2000))
                .fluidOutputs(DampBromine.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // Br(H2O) -> Br
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(DampBromine.getFluid(2000))
                .fluidOutputs(Bromine.getFluid(1000))
                .EUt(480)
                .duration(500)
                .buildAndRegister();

        // CO + C3H6 + 2H -> C4H8O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxde.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Butyraldehyde.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // 2C4H8O + 4H -> C8H18O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butyraldehyde.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(4000))
                .fluidOutputs(Ethylhexanol.getFluid(3000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // 5C8H18O + 0.5P4O10 -> 2C16H35O4P + 2C4H8 + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethylhexanol.getFluid(5000))
                .input(dust, PhosphorousPentoxide, 7)
                .fluidOutputs(DiethylhexylPhosphoricAcid.getFluid(2000))
                .fluidOutputs(Butene.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // [REE] + NaOH + H2O -> Na[REE]-OH(H2O)
        MIXER_RECIPES.recipeBuilder()
                .input(dust, RareEarth)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(RareEarthHydroxidesSolution.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // HCl + Na[REE]-OH(H2O) -> ThU + H[REE]-Cl+ [NaOH + H2O]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(RareEarthHydroxidesSolution.getFluid(1000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(1000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(ThUSludge.getItemStack(2))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().duration(100).EUt(32)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .buildAndRegister();

        // H[REE]-Cl + C16H35O4P(cat.) -> [REE](sep.) + HCl
        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(RareEarthChloridesSolution.getFluid(1000))
                .notConsumable(DiethylhexylPhosphoricAcid.getFluid(0))
                .fluidOutputs(LaNdOxidesSolution.getFluid(250))
                .fluidOutputs(SmGdOxidesSolution.getFluid(250))
                .fluidOutputs(TbHoOxidesSolution.getFluid(250))
                .fluidOutputs(ErLuOxidesSolution.getFluid(250))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();

        // 4LaPrNdCeOx -> La2O3 + Pr2O3 + Nd2O3 + Ce2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(LaNdOxidesSolution.getFluid(4000))
                .chancedOutput(LanthanumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(PraseodymiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(NeodymiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(CeriumOxide.getItemStack(5), 4300, 275)
                .EUt(480)
                .duration(250)
                .buildAndRegister();

        // 4ScEuGdSmOx -> Sc2O3 + Eu2O3 + Gd2O3 + Sm2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(SmGdOxidesSolution.getFluid(4000))
                .chancedOutput(ScandiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(EuropiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(GadoliniumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(SamariumOxide.getItemStack(5), 4300, 275)
                .EUt(480)
                .duration(250)
                .buildAndRegister();

        // 4YTbDyHoOx -> Y2O3 + Tb2O3 + Dy2O3 + Ho2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TbHoOxidesSolution.getFluid(4000))
                .chancedOutput(OreDictUnifier.get(dust, YttriumOxide, 5), 4300, 275)
                .chancedOutput(TerbiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(DysprosiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(HolmiumOxide.getItemStack(5), 4300, 275)
                .EUt(480)
                .duration(250)
                .buildAndRegister();

        // 4ErTmYtLuOx -> Er2O3 + Tm2O3 + Yt2O3 + Lu2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ErLuOxidesSolution.getFluid(4000))
                .chancedOutput(ErbiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(ThuliumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(YtterbiumOxide.getItemStack(5), 4300, 275)
                .chancedOutput(LutetiumOxide.getItemStack(5), 4300, 275)
                .EUt(480)
                .duration(250)
                .buildAndRegister();

        // 3C + 2La2O3 -> 4La + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(LanthanumOxide.getItemStack(10))
                .output(dust, Lanthanum,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Pr2O3 -> 4Pr + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(PraseodymiumOxide.getItemStack(10))
                .output(dust, Praseodymium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Nd2O3 -> 4Nd + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(NeodymiumOxide.getItemStack(10))
                .output(dust, Neodymium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Ce2O3 -> 4Ce + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(CeriumOxide.getItemStack(10))
                .output(dust, Cerium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Sc2O3 -> 4Sc + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(ScandiumOxide.getItemStack(10))
                .output(dust, Scandium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Eu2O3 -> 4Eu + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(EuropiumOxide.getItemStack(10))
                .output(dust, Europium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Gd2O3 -> 4Gd + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(GadoliniumOxide.getItemStack(10))
                .output(dust, Gadolinium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Sm2O3 -> 4Sm + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .inputs(SamariumOxide.getItemStack(10))
                .output(dust, Samarium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Y2O3 -> 4Y + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .input(dust, YttriumOxide,10)
                .output(dust, Yttrium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Tb2O3 -> 4Tb + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(TerbiumOxide.getItemStack(10))
                .output(dust, Terbium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Dy2O3 -> 4Dy + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(DysprosiumOxide.getItemStack(10))
                .output(dust, Dysprosium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Ho2O3 -> 4Ho + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(HolmiumOxide.getItemStack(10))
                .output(dust, Holmium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Er2O3 -> 4Er + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(ErbiumOxide.getItemStack(10))
                .output(dust, Erbium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Tm2O3 -> 4Tm + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(ThuliumOxide.getItemStack(10))
                .output(dust, Thulium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + Yt2O3 -> 4Yt + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(YtterbiumOxide.getItemStack(10))
                .output(dust, Ytterbium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Lu2O3 -> 4Lu + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon,3)
                .inputs(LutetiumOxide.getItemStack(10))
                .output(dust, Lutetium,4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 2ThU + O -> 0.5ThO + Th(20%) + U(20%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(ThUSludge.getItemStack(4))
                .fluidInputs(Oxygen.getFluid(500))
                .chancedOutput(OreDictUnifier.get(dustTiny, Thorium), 2000, 150)
                .chancedOutput(OreDictUnifier.get(dustTiny, UraniumRadioactive.getMaterial()), 2000, 150)
                .output(oxide, Thorium)
                .EUt(480)
                .duration(500)
                .buildAndRegister();
    }
}
