package gregicadditions.recipes.chain;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GASimpleBlock;
import gregicadditions.item.fusion.GAFusionCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import gregicadditions.recipes.GACraftingComponents;

import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class DigitalInterfaces {
    public static void init() {
        int tier = 3;
        for (String fluid : GAConfig.Misc.solderingFluidList) {
            String[] fluidSplit = fluid.split(":");
            int amount = Integer.parseInt(fluidSplit[1]);
            if (amount > 64000) {
                amount = 64000;
            }
            if (amount < 1) {
                amount = 1;
            }
            FluidStack fluidStack = FluidRegistry.getFluidStack(fluidSplit[0], amount);
            if (fluidStack == null) continue;

            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(300)
                    .inputs(SMD_DIODE_REFINED.getStackForm(32))
                    .input(dust, Glass, 1)
                    .input(dye, MarkerMaterials.Color.Red, 1)
                    .input(dye, MarkerMaterials.Color.Green, 1)
                    .input(dye, MarkerMaterials.Color.Blue, 1)
                    .input(wireFine, Aluminium, 8)
                    .fluidInputs(fluidStack)
                    .outputs(COLOURED_LEDS.getStackForm(32))
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(300)
                    .inputs(SMD_DIODE.getStackForm(16))
                    .input(dust, Glass, 1)
                    .input(dye, MarkerMaterials.Color.Red, 1)
                    .input(dye, MarkerMaterials.Color.Green, 1)
                    .input(dye, MarkerMaterials.Color.Blue, 1)
                    .input(wireFine, Aluminium, 8)
                    .fluidInputs(fluidStack)
                    .outputs(COLOURED_LEDS.getStackForm(32))
                    .buildAndRegister();
            CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(120).EUt(300)
                    .inputs(COLOURED_LEDS.getStackForm(4))
                    .inputs(PLASTIC_BOARD.getStackForm())
                    .input(wireFine, Aluminium, 4)
                    .fluidInputs(fluidStack)
                    .outputs(DISPLAY.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(480)
                    .inputs(DISPLAY.getStackForm())
                    .inputs((ItemStack) GACraftingComponents.HULL.getIngredient(tier))
                    .input(wireFine, AnnealedCopper, 8)
                    .fluidInputs(fluidStack)
                    .outputs(GATileEntities.MONITOR_SCREEN.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(500)
                    .inputs(DISPLAY.getStackForm())
                    .inputs((ItemStack) GACraftingComponents.HULL.getIngredient(tier))
                    .input(circuit, MarkerMaterials.Tier.Advanced, 2)
                    .fluidInputs(fluidStack)
                    .outputs(GATileEntities.CENTRAL_MONITOR.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(500)
                    .inputs(DISPLAY.getStackForm())
                    .input(plate, StainlessSteel)
                    .input(circuit, MarkerMaterials.Tier.Advanced)
                    .input(screw, StainlessSteel, 4)
                    .fluidInputs(fluidStack)
                    .outputs(COVER_DIGITAL_INTERFACE.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(400)
                    .inputs(DISPLAY.getStackForm())
                    .input(circuit, MarkerMaterials.Tier.Basic)
                    .input(wireFine, Copper, 2)
                    .fluidInputs(fluidStack)
                    .outputs(PLUGIN_TEXT.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(400)
                    .inputs(DISPLAY.getStackForm())
                    .input(circuit, MarkerMaterials.Tier.Basic)
                    .input(wireFine, Iron, 2)
                    .fluidInputs(fluidStack)
                    .outputs(PLUGIN_ONLINE_PIC.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(400)
                    .inputs(DISPLAY.getStackForm())
                    .input(circuit, MarkerMaterials.Tier.Basic)
                    .input(wireFine, Gold, 2)
                    .fluidInputs(fluidStack)
                    .outputs(PLUGIN_FAKE_GUI.getStackForm())
                    .buildAndRegister();
            ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(400)
                    .inputs(DISPLAY.getStackForm())
                    .input(circuit, MarkerMaterials.Tier.Advanced)
                    .input(wireFine, Aluminium, 2)
                    .fluidInputs(fluidStack)
                    .outputs(PLUGIN_ADVANCED_MONITOR.getStackForm())
                    .buildAndRegister();
        }
    }
}

