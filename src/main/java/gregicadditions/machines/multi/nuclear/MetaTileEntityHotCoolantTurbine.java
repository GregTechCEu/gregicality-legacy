package gregicadditions.machines.multi.nuclear;

import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.metal.MetalCasing1;
import gregicadditions.item.metal.MetalCasing2;
import gregicadditions.machines.multi.impl.HotCoolantMultiblockController;
import gregicadditions.machines.multi.impl.HotCoolantRecipeLogic;
import gregicadditions.machines.multi.impl.HotCoolantTurbineWorkableHandler;
import gregicadditions.machines.multi.impl.MetaTileEntityRotorHolderForNuclearCoolant;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.recipes.impl.nuclear.HotCoolantRecipeMap;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.render.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

import static gregicadditions.client.ClientHandler.MARAGING_STEEL_250_CASING;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_1;
import static gregicadditions.item.GAMetaBlocks.METAL_CASING_2;

public class MetaTileEntityHotCoolantTurbine extends HotCoolantMultiblockController { //todo maintenance

    public static final MultiblockAbility<MetaTileEntityRotorHolderForNuclearCoolant> ABILITY_ROTOR_HOLDER = new MultiblockAbility();

    private static final int MIN_DURABILITY_TO_WARN = 10;

    public enum TurbineType {

        HOT_COOLANT(GARecipeMaps.HOT_COOLANT_TURBINE_FUELS, METAL_CASING_1.getState(MetalCasing1.CasingType.MARAGING_STEEL_250), MARAGING_STEEL_250_CASING, true);

        public final HotCoolantRecipeMap recipeMap;
        public final IBlockState casingState;
        public final ICubeRenderer casingRenderer;
        public final boolean hasOutputHatch;

        TurbineType(HotCoolantRecipeMap recipeMap, IBlockState casingState, ICubeRenderer casingRenderer, boolean hasOutputHatch) {
            this.recipeMap = recipeMap;
            this.casingState = casingState;
            this.casingRenderer = casingRenderer;
            this.hasOutputHatch = hasOutputHatch;
        }
    }

    public final TurbineType turbineType;
    public IFluidHandler exportFluidHandler;

    public MetaTileEntityHotCoolantTurbine(ResourceLocation metaTileEntityId, TurbineType turbineType) {
        super(metaTileEntityId, turbineType.recipeMap, GAValues.V[GAValues.EV]);
        this.turbineType = turbineType;
        reinitializeStructurePattern();
    }

    @Override
    protected HotCoolantRecipeLogic createWorkable(long maxVoltage) {
        return new HotCoolantTurbineWorkableHandler(this, recipeMap, () -> energyContainer, () -> importFluidHandler);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityHotCoolantTurbine(metaTileEntityId, turbineType);
    }

    @Override
    protected void updateFormedValid() {
        if (isTurbineFaceFree()) {
            super.updateFormedValid();
        }
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.exportFluidHandler = null;
    }

    /**
     * @return true if structure formed, workable is active and front face is free
     */
    public boolean isActive() {
        return isTurbineFaceFree() && workableHandler.isActive() && workableHandler.isWorkingEnabled();
    }

    /**
     * @return true if turbine is formed and it's face is free and contains
     * only air blocks in front of rotor holder
     */
    public boolean isTurbineFaceFree() {
        return isStructureFormed() && getAbilities(ABILITY_ROTOR_HOLDER).get(0).isFrontFaceFree();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            MetaTileEntityRotorHolderForNuclearCoolant rotorHolder = getAbilities(ABILITY_ROTOR_HOLDER).get(0);
            FluidStack fuelStack = ((HotCoolantTurbineWorkableHandler) workableHandler).getFuelStack();
            int fuelAmount = fuelStack == null ? 0 : fuelStack.amount;

            ITextComponent fuelName = new TextComponentTranslation(fuelAmount == 0 ? "gregtech.fluid.empty" : fuelStack.getUnlocalizedName());
            textList.add(new TextComponentTranslation("gregtech.multiblock.turbine.fuel_amount", fuelAmount, fuelName));

            if (rotorHolder.getRotorEfficiency() > 0.0) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.turbine.rotor_speed", rotorHolder.getCurrentRotorSpeed(), rotorHolder.getMaxRotorSpeed()));
                textList.add(new TextComponentTranslation("gregtech.multiblock.turbine.rotor_efficiency", (int) (rotorHolder.getRotorEfficiency() * 100)));
                int rotorDurability = (int) (rotorHolder.getRotorDurability() * 100);
                if (rotorDurability > MIN_DURABILITY_TO_WARN) {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.turbine.rotor_durability", rotorDurability));
                } else {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.turbine.low_rotor_durability",
                            MIN_DURABILITY_TO_WARN, rotorDurability).setStyle(new Style().setColor(TextFormatting.RED)));
                }
            }
        }
        super.addDisplayText(textList);
    }

    @Override
    protected BlockPattern createStructurePattern() { //todo why does this not form in jei
        return turbineType == null ? null :
                FactoryBlockPattern.start()
                        .aisle("CCCC", "CHHC", "CCCC")
                        .aisle("CHHC", "R##D", "CHHC")
                        .aisle("CCCC", "CSHC", "CCCC")
                        .where('S', selfPredicate())
                        .where('#', isAirPredicate())
                        .where('C', statePredicate(getCasingState()))
                        .where('H', statePredicate(getCasingState()).or(abilityPartPredicate(getAllowedAbilities())))
                        .where('R', abilityPartPredicate(ABILITY_ROTOR_HOLDER))
                        .where('D', abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY))
                        .build();
    }

    public MultiblockAbility[] getAllowedAbilities() {
        return turbineType.hasOutputHatch ?
                new MultiblockAbility[]{MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.EXPORT_FLUIDS} :
                new MultiblockAbility[]{MultiblockAbility.IMPORT_FLUIDS, GregicAdditionsCapabilities.MAINTENANCE_CAPABILITY};
    }

    public IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.MARAGING_STEEL_250);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return MARAGING_STEEL_250_CASING;
    }

}
