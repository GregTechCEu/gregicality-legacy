package gregicadditions.recipes.compat;

import forestry.core.ModuleCore;
import forestry.core.fluids.Fluids;
import forestry.core.items.EnumElectronTube;
import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import static gregicadditions.GAMaterials.FishOil;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class ForestryCompat {

    public static void init() {

        //Making BioDiesel
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Methanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Fluids.BIO_ETHANOL.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();
        } else {
            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Methanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30)
                    .notConsumable(dust, SodiumHydroxide)
                    .fluidInputs(FishOil.getFluid(6000))
                    .fluidInputs(Ethanol.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000))
                    .fluidOutputs(BioDiesel.getFluid(6000))
                    .buildAndRegister();
        }


        if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_APATITE.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.APATITE, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Apatite, 2)
                    .input(bolt, Apatite)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Apatite, 4)
                    .input(bolt, Apatite, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm(2))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BLAZE, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(dust, Blaze, 2)
                    .input(dustSmall, Blaze, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(2))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .input(dust, Blaze, 5)
                    .input(dust, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(4))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BRONZE, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Bronze, 2).input(bolt, Bronze)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Bronze, 4).input(bolt, Bronze, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm(2))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_COPPER.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.COPPER, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Copper, 2)
                    .input(bolt, Copper)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Copper, 4)
                    .input(bolt, Copper, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm(2)).
                    buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.DIAMOND, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Diamond, 2)
                    .input(bolt, Diamond)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Diamond, 4)
                    .input(bolt, Diamond, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm(2))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.EMERALD, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Emerald, 2)
                    .input(bolt, Emerald)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Emerald, 4)
                    .input(bolt, Emerald, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm(2))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_ENDER.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ENDER, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(dust, Endstone, 2)
                    .input(dustSmall, Endstone, 2)
                    .input(dust, EnderEye)
                    .outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(2))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .input(dust, Endstone, 5)
                    .input(dust, EnderEye, 2)
                    .outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(4))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_GOLD.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.GOLD, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Gold, 2)
                    .input(bolt, Gold)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Gold, 4)
                    .input(bolt, Gold, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm(2))
                    .buildAndRegister();

            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore")) {
                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                        .inputs(GAMetaItems.ELECTRODE_IRON.getStackForm())
                        .input(plate, Glass)
                        .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.IRON, 1))
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                        .input(stick, Iron, 2)
                        .input(bolt, Iron)
                        .input(dustSmall, Redstone, 2)
                        .outputs(GAMetaItems.ELECTRODE_IRON.getStackForm())
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                        .input(stick, Iron, 4).input(bolt, Iron, 2)
                        .input(dust, Redstone)
                        .outputs(GAMetaItems.ELECTRODE_IRON.getStackForm(2))
                        .buildAndRegister();

            }
            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.LAPIS, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Lapis, 2)
                    .input(bolt, Lapis)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Lapis, 4)
                    .input(bolt, Lapis, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm(2))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.OBSIDIAN, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(dust, Obsidian, 2)
                    .input(dustSmall, Obsidian, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(2))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .input(dust, Obsidian, 5)
                    .input(dust, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(4))
                    .buildAndRegister();

            if (Loader.isModLoaded("extrautils2")) {
                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                        .inputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm())
                        .input(plate, Glass)
                        .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ORCHID, 1))
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                        .inputs(new ItemStack(Blocks.REDSTONE_ORE, 5))
                        .input(dust, Redstone)
                        .outputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm(4))
                        .buildAndRegister();
            }
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn")) {
                ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                        .inputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm())
                        .input(plate, Glass)
                        .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.RUBBER, 1))
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                        .input(stick, Rubber, 2)
                        .input(bolt, Rubber)
                        .input(dustSmall, Redstone, 2)
                        .outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm())
                        .buildAndRegister();

                FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                        .input(stick, Rubber, 4)
                        .input(bolt, Rubber, 2)
                        .input(dust, Redstone)
                        .outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm(2))
                        .buildAndRegister();
            }
            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .inputs(GAMetaItems.ELECTRODE_TIN.getStackForm())
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.TIN, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Tin, 2)
                    .input(bolt, Tin)
                    .input(dustSmall, Redstone, 2)
                    .outputs(GAMetaItems.ELECTRODE_TIN.getStackForm())
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Tin, 4)
                    .input(bolt, Tin, 2)
                    .input(dust, Redstone)
                    .outputs(GAMetaItems.ELECTRODE_TIN.getStackForm(2))
                    .buildAndRegister();
        }
    }
}
