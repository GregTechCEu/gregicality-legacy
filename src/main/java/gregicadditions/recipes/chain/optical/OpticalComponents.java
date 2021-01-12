package gregicadditions.recipes.chain.optical;

import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class OpticalComponents {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(670000)
                .input(dust, Niobium)
                .fluidInputs(Chlorine.getFluid(5000))
                .outputs(NiobiumChloride.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(750000).blastFurnaceTemp(1600)
                .inputs(LithiumAluminiumHydride.getItemStack())
                .outputs(LithiumHydride.getItemStack())
                .outputs(AluminiumHydride.getItemStack())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(320).EUt(1200000).blastFurnaceTemp(4500)
                .inputs(NiobiumChloride.getItemStack(2))
                .inputs(LithiumHydride.getItemStack(2))
                .input(dustTiny, Hafnium)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(OreDictUnifier.get(ingotHot, LithiumNiobate, 2))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(4000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(560000)
                .input(dust, SodiumHydroxide, 5)
                .inputs(NiobiumChloride.getItemStack())
                .outputs(OreDictUnifier.get(dust, Salt, 5))
                .outputs(NiobiumHydroxide.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(450000)
                .inputs(NiobiumHydroxide.getItemStack())
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(OxalicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(AmmoniumNiobiumOxalateSolution.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(260).EUt(950000)
                .input(dust, LithiumNiobate, 2)
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

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(150000)
                .input(dust, Magnesia)
                .inputs(AmmoniumBifluoride.getItemStack())
                .outputs(MagnesiumFluoride.getItemStack())
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(8400000).blastFurnaceTemp(4600)
                .input(dust, Phosphorus)
                .input(dust, Indium)
                .outputs(OreDictUnifier.get(dust, IndiumPhospide, 2))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(8400000).blastFurnaceTemp(4600)
                .input(dust, Zinc)
                .input(dust, Sulfur)
                .outputs(ZincSulfide.getItemStack(2))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(270).EUt(970000)
                .inputs(MagnesiumFluoride.getItemStack())
                .inputs(ZincSulfide.getItemStack())
                .inputs(TantalumOxide.getItemStack())
                .input(dust, Rutile)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(DielectricMirrorFormationMix.getFluid(5000))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .inputs(ZBLANDust.getItemStack())
                .fluidOutputs(LiquidZBLAN.getFluid(144))
                .buildAndRegister();
    }
}

