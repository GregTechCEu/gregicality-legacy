package gregicadditions.recipes.chain.optical;

import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class OpticalComponents {
    public static void init() {

        // Nb + 5Cl -> NbCl5
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(670000)
                .input(dust, Niobium)
                .fluidInputs(Chlorine.getFluid(5000))
                .outputs(NiobiumChloride.getItemStack(6))
                .buildAndRegister();

        // LiAlH4 -> LiH + AlH3
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(750000).blastFurnaceTemp(1600)
                .inputs(LithiumAluminiumHydride.getItemStack(6))
                .outputs(LithiumHydride.getItemStack(2))
                .outputs(AluminiumHydride.getItemStack(4))
                .buildAndRegister();

        // NbCl5 + LiH + 2H2O2 -> LiNbO4 + 5HCl
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1200000).blastFurnaceTemp(4500)
                .inputs(NiobiumChloride.getItemStack(6))
                .inputs(LithiumHydride.getItemStack(2))
                .input(dustTiny, Hafnium)
                .fluidInputs(HydrogenPeroxide.getFluid(2000))
                .output(ingotHot, LithiumNiobate, 6)
                .fluidOutputs(HydrochloricAcid.getFluid(5000))
                .buildAndRegister();

        // 5NaOH + NbCl5 -> 5NaCl + H5NbO5
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(560000)
                .input(dust, SodiumHydroxide, 15)
                .inputs(NiobiumChloride.getItemStack(6))
                .output(dust, Salt, 10)
                .outputs(NiobiumHydroxide.getItemStack(11))
                .buildAndRegister();

        // 2H5NbO5 + 5C2H2O4 + NH3 + Na -> 9H2O + [C10Nb2O20 + NH4] + NaOH
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(450000)
                .inputs(NiobiumHydroxide.getItemStack(22))
                .input(dust, Sodium)
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(OxalicAcid.getFluid(5000))
                .output(dust ,SodiumHydroxide, 3)
                .fluidOutputs(Water.getFluid(9000))
                .fluidOutputs(AmmoniumNiobiumOxalateSolution.getFluid(1000))
                .buildAndRegister();

        // [C10Nb2O20 + NH4] + 2LiNbO4 -> Nanparticles
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(950000)
                .input(dust, LithiumNiobate, 12)
                .fluidInputs(AmmoniumNiobiumOxalateSolution.getFluid(1000))
                .outputs(LithiumNiobateNanoparticles.getItemStack(3))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(750000)
                .input(dustSmall, Radium)
                .input(plate, Polybenzimidazole, 4)
                .input(plate, Steel, 4)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(ELECTRON_SOURCE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(800000)
                .input(plate, Graphene, 2)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(ROTATING_TRANSPARENT_SURFACE.getStackForm())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(3200000)
                .inputs(LithiumNiobateNanoparticles.getItemStack(2))
                .notConsumable(ROTATING_TRANSPARENT_SURFACE)
                .notConsumable(ELECTRON_SOURCE)
                .fluidInputs(Xenon.getFluid(1000))
                .outputs(PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(260).EUt(1600000)
                .inputs(PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE.getStackForm())
                .outputs(NON_LINEAR_OPTICAL_LENS.getStackForm())
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(240).EUt(710000)
                .input(plate, Germanium)
                .fluidInputs(Zinc.getFluid(144))
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .outputs(HIGHLY_REFLECTIVE_MIRROR.getStackForm())
                .buildAndRegister();

        ItemStack[][] laser_components = {{NDYAG_ROD.getStackForm(), RED_HALIDE_LAMP.getStackForm()},
                {LUTMYVO_ROD.getStackForm(), GREEN_HALIDE_LAMP.getStackForm()},
                {PRHOYLF_ROD.getStackForm(), BLUE_HALIDE_LAMP.getStackForm()}};
        ItemStack[] lasers = {LOW_FREQUENCY_LASER.getStackForm(), MEDIUM_FREQUENCY_LASER.getStackForm(), HIGH_FREQUENCY_LASER.getStackForm()};

        for (int i = 0; i < laser_components.length; i++) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(910000)
                    .inputs(laser_components[i])
                    .inputs(HIGHLY_REFLECTIVE_MIRROR.getStackForm())
                    .inputs(NON_LINEAR_OPTICAL_LENS.getStackForm())
                    .inputs(SMD_DIODE_OPTICAL.getStackForm())
                    .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                    .fluidInputs(SolderingAlloy.getFluid(576))
                    .outputs(lasers[i])
                    .buildAndRegister();
        }

        // MgO + NH4HF2 -> MgF2 + NH3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(150000)
                .input(dust, Magnesia, 2)
                .inputs(AmmoniumBifluoride.getItemStack(8))
                .outputs(MagnesiumFluoride.getItemStack(3))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // P + I -> InP
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(1600000).blastFurnaceTemp(4600)
                .input(dust, Phosphorus)
                .input(dust, Indium)
                .output(dust, IndiumPhospide, 2)
                .buildAndRegister();

        // Zn + S -> ZnS
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(491520).blastFurnaceTemp(4600)
                .input(dust, Zinc)
                .input(dust, Sulfur)
                .outputs(ZincSulfide.getItemStack(2))
                .buildAndRegister();

        // MgF2 + ZnS + Ta2O5 + TiO2 + C2H5OH -> Dielectric Mirror Formation Mix
        MIXER_RECIPES.recipeBuilder().duration(270).EUt(970000)
                .inputs(MagnesiumFluoride.getItemStack(3))
                .inputs(ZincSulfide.getItemStack(2))
                .inputs(TantalumOxide.getItemStack(7))
                .input(dust, Rutile, 3)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(DielectricMirrorFormationMix.getFluid(1000))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(260).EUt(260000)
                .inputs(ZBLANDust.getItemStack())
                .fluidOutputs(LiquidZBLAN.getFluid(144))
                .buildAndRegister();
    }
}
