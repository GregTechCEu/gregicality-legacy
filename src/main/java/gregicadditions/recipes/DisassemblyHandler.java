package gregicadditions.recipes;

import gregicadditions.GAConfig;
import gregicadditions.GAValues;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GAMetaTool;
import gregicadditions.machines.overrides.GAMetaTileEntityHull;
import gregicadditions.machines.overrides.GATieredMetaTileEntity;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;
import gregtech.common.items.MetaTool;
import gregtech.common.metatileentities.electric.MetaTileEntityHull;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.*;

import static gregicadditions.recipes.GARecipeMaps.DISASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.Ash;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class DisassemblyHandler {

    /* Return Map is of type <Integer, Tuple<ItemStack, Integer>> where
     * Integer = metadata of the circuit to replace
     * ItemStack = the circuit to use
     * Integer = the tier of the circuit according to GAValues.V
     *
     * Passed parameter is the List of circuits to use for each tier,
     * always the worst of the tier.
     */
    private static final Map<Integer, Tuple<ItemStack, Integer>> circuitToUse = createCircuitMap(Arrays.asList(
            MetaItems.BASIC_CIRCUIT_LV.getStackForm(),
            GAMetaItems.PRIMITIVE_ASSEMBLY.getStackForm(),
            GAMetaItems.ELECTRONIC_COMPUTER.getStackForm(),
            GAMetaItems.REFINED_MAINFRAME.getStackForm(),
            GAMetaItems.MICRO_MAINFRAME.getStackForm(),
            GAMetaItems.NANO_MAINFRAME.getStackForm(),
            GAMetaItems.QUANTUM_MAINFRAME.getStackForm(),
            GAMetaItems.CRYSTAL_MAINFRAME.getStackForm(),
            MetaItems.WETWARE_MAINFRAME_MAX.getStackForm(),
            GAMetaItems.BIOWARE_MAINFRAME.getStackForm(),
            GAMetaItems.OPTICAL_MAINFRAME.getStackForm(),
            GAMetaItems.COSMIC_MAINFRAME.getStackForm(),
            GAMetaItems.SUPRACAUSAL_MAINFRAME.getStackForm()));

    /**
     * Handles the Disassembler Recipe Registration.
     * Registers recipes for all MTE's of types:
     * - instanceof IEnergyContainer
     * - instanceof MultiblockControllerBase
     * - instanceof SteamMetaTileEntity
     * - NOT instanceof MetaTileEntityHull (GA as well)
     *
     * This MUST be run after all other recipe registration, except for Material
     * decomposition handlers, as it is an unrelated category of recipes.
     */
    public static void buildDisassemblerRecipes() {
        Map<ResourceLocation, MetaTileEntity> mteMap = new HashMap<>();
        Map<MetaTileEntity, IRecipe> recipeMap = new HashMap<>();

        // Gather ResourceLocations, exclude duplicates (looking at you, Large Multi-Use Machine)
        GregTechAPI.META_TILE_ENTITY_REGISTRY.forEach(mte -> {
            if ((mte instanceof EnergyContainerHandler.IEnergyChangeListener
                    || mte instanceof MultiblockControllerBase
                    || mte instanceof SteamMetaTileEntity)
                    && (!(mte instanceof MetaTileEntityHull)
                    && !(mte instanceof GAMetaTileEntityHull))) {

                if (!mteMap.containsKey(mte.metaTileEntityId))
                    mteMap.put(mte.metaTileEntityId, mte);
            }
        });

        // Gather the recipe for each MTE
        // Unfortunately, there is not a better way to do this
        ForgeRegistries.RECIPES.forEach(iRecipe -> {

            MetaTileEntity mte;

            // Test for if output is a MTE
            if ((mte = testAndGetMTE(iRecipe.getRecipeOutput())) != null) {
                if (mteMap.containsKey(GregTechAPI.META_TILE_ENTITY_REGISTRY.getNameForObject(mte))) {

                    // Place a null for the MTE recipe if more than one exists to avoid exploits
                    // Throws out GTCE->Gregicality conversion recipes from consideration
                    if (iRecipe.getIngredients().size() != 1)
                        recipeMap.put(mte, recipeMap.containsKey(mte) ? null : iRecipe);
                }
            }
        });

        // Register the Disassembler recipes
        recipeMap.forEach(DisassemblyHandler::buildDisassemblerRecipe);
    }

    /**
     * NOT YET IMPLEMENTED
     *
     * Handles some Arc Furnace Recipe Registration.
     * Registers recipes for:
     * - instanceof BlockMetalCasing (GA as well)
     * - instanceof BlockMachineCasing (GA as well)
     * - instanceof SteamMetaTileEntity
     * - instanceof RecipeMapSteamMultiblockController
     *
     * This MUST be run after all other recipe registration, except for Material
     * decomposition handlers, as it is an unrelated category of recipes.
     */
    @SuppressWarnings("unused")
    public static void buildArcRecipes() {
        // WIP
    }

    /**
     * Generates a Disassembly Recipe given a MetaTileEntity and its crafting recipe.
     *
     * @param mte The MetaTileEntity to use as input in the disassembly recipe
     * @param recipe The crafting recipe for the MetaTileEntity
     */
    public static void buildDisassemblerRecipe(MetaTileEntity mte, IRecipe recipe) {
        if (recipe != null) {

            // Convert input Ingredients to ItemStack
            List<ItemStack> outputItems = new ArrayList<>();
            recipe.getIngredients().forEach(ingredient -> {
                ItemStack[] itemStacks = ingredient.getMatchingStacks();

                // Add ash in empty spaces and in place of tools to preserve item order
                if (itemStacks.length == 0 || itemStacks[0].getItem() instanceof MetaTool || itemStacks[0].getItem() instanceof GAMetaTool)
                    outputItems.add(OreDictUnifier.get(dustTiny, Ash));

                // Check for if a circuit needs to be replaced, otherwise insert the ItemStack
                else {
                    int key = itemStacks[0].getItemDamage();
                    if (circuitToUse.containsKey(key))
                        outputItems.add(circuitToUse.get(key).getFirst());
                    else
                        outputItems.add(itemStacks[0]);
                }
            });

            // Calculate EU/t
            int voltage = 0;

            // If MTE is tiered, use the tier value as the EU/t
            if (mte instanceof ITieredMetaTileEntity) {
                voltage = GAValues.V[((ITieredMetaTileEntity) mte).getTier()];
            }
            // If MTE is an energy container, use the voltage I/O as the EU/t
            else if (mte instanceof IEnergyContainer) {
                IEnergyContainer energy = ((IEnergyContainer) mte);
                voltage = (int) energy.getInputVoltage();
                if (voltage == 0)
                    voltage = (int) energy.getOutputVoltage();

            }
            // If the MTE is a Multi Controller, get the EU/t from the recipe directly
            if (mte instanceof MultiblockControllerBase) {

                // Quick reset for Multis that implement IEnergyContainer (Battery Tower, for example) since
                // they do not always have the proper tier as an ItemStack.
                if (voltage != 0)
                    voltage = 0;

                // One of the dumbest blocks of code I've ever written
                // At least we have proper EUt for multiblock disassembly now
                for (ItemStack itemStack : outputItems) {
                    Tuple<ItemStack, Integer> tempTuple;
                    MetaTileEntity tempMTE;

                    // Check if itemStack is a circuit, and set the voltage to its tier
                    if ((voltage = ((tempTuple = circuitToUse.get(itemStack.getItemDamage())) != null ? tempTuple.getSecond() : 0)) != 0) {
                        voltage = GAValues.V[voltage];
                        break;
                    }

                    // Check if itemStack is an MTE, and set the voltage to its tier
                    else if ((tempMTE = testAndGetMTE(itemStack)) != null
                          && (tempMTE instanceof TieredMetaTileEntity
                          ||  tempMTE instanceof GATieredMetaTileEntity)) {
                        voltage = GAValues.V[((ITieredMetaTileEntity) tempMTE).getTier()];
                        break;
                    }
                }
            }

            // Catches Steam machines, PBF, IPBF, and Coke Oven
            // Also bumps ULV machine disassembly to 32EU/t
            voltage = Math.max(voltage, 32);

            // Used for duration
            int itemCount = recipe.getIngredients().size();

            RecipeBuilder<?> builder = DISASSEMBLER_RECIPES.recipeBuilder()
                    .EUt(voltage)
                    .duration(itemCount * 100) // 5s per output item
                    .inputs(mte.getStackForm());

            // 50% chance at base, +7.5% up a tier
            if (GAConfig.Misc.disassemblyChancedOutputs)
                outputItems.forEach(outputStack -> builder.chancedOutput(outputStack, 5000, 750));
            else
                builder.outputs(outputItems);
            builder.buildAndRegister();
        }
    }

    /*
     * Test if an ItemStack contains a MetaTileEntity, and return it if it is.
     * @param itemStack The ItemStack to get an MetaTileEntity from
     * @return The MetaTileEntity contained in the ItemStack
     */
    private static MetaTileEntity testAndGetMTE(ItemStack itemStack) {
        if (testIsMTE(itemStack))
            return GregTechAPI.META_TILE_ENTITY_REGISTRY.getObjectById(itemStack.getItemDamage());
        return null;
    }

    /*
     * Test if an ItemStack contains a MetaTileEntity.
     * @param itemStack The ItemStack to test
     * @return The test result
     */
    private static boolean testIsMTE(ItemStack itemStack) {
        String unlocalizedName = itemStack.getItem().getUnlocalizedNameInefficiently(itemStack);
        return unlocalizedName.contains("gregtech.machine") || unlocalizedName.contains("gtadditions.machine");
    }

    /* Creates a Mapping of:
     * Key: Integer of the metadata of any circuit
     * Value: Tuple of <ItemStack circuitToUse, int circuitTier>
     *
     * Implemented using OreDict, so this will work for all current
     * and future circuits.
     */
    private static Map<Integer, Tuple<ItemStack, Integer>> createCircuitMap(List<ItemStack> circuitsMasterList) {
        Map<Integer, Tuple<ItemStack, Integer>> circuits = new HashMap<>();

        // Map each circuit in circuitsMasterList as the value
        // for every circuit in its OreDict
        circuitsMasterList.forEach(output ->
                OreDictUnifier.getOreDictionaryNames(output).forEach(name ->
                        OreDictUnifier.getAllWithOreDictionaryName(name).forEach(itemStack ->
                                circuits.put(itemStack.getItemDamage(), new Tuple<>(output, circuitsMasterList.indexOf(output) + 1))
                        )
                )
        );
        return circuits;
    }
}
