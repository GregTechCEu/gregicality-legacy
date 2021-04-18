package gregicadditions.fluid;

import gregicadditions.materials.IsotopeMaterial;
import gregicadditions.materials.RadioactiveMaterial;
import gregicadditions.materials.SimpleFluidMaterial;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.common.MetaFluids;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.material.Materials.*;

public class GAMetaFluids {


    public static final Map<FluidMaterial, Fluid> HOT_FLUIDS = new HashMap<>();
    private static final Map<String, SimpleFluidMaterial> fluidToMaterialMappings = new HashMap<>();


    public static void init() {
        HOT_FLUIDS.put(Steam, MetaFluids.registerFluid(Steam, MetaFluids.FluidType.valueOf("HOT"), 423));
        HOT_FLUIDS.put(Deuterium, MetaFluids.registerFluid(Deuterium, MetaFluids.FluidType.valueOf("HOT"), 618));
        HOT_FLUIDS.put(SodiumPotassiumAlloy, MetaFluids.registerFluid(SodiumPotassiumAlloy, MetaFluids.FluidType.valueOf("HOT"), 1058));
        HOT_FLUIDS.put(Sodium, MetaFluids.registerFluid(Sodium, MetaFluids.FluidType.valueOf("HOT"), 1156));
        HOT_FLUIDS.put(FLiNaK, MetaFluids.registerFluid(FLiNaK, MetaFluids.FluidType.valueOf("HOT"), 1843));
        HOT_FLUIDS.put(FLiBe, MetaFluids.registerFluid(FLiBe, MetaFluids.FluidType.valueOf("HOT"), 1703));
        HOT_FLUIDS.put(LeadBismuthEutectic, MetaFluids.registerFluid(LeadBismuthEutectic, MetaFluids.FluidType.valueOf("HOT"), 1943));


        RadioactiveMaterial.REGISTRY.forEach((ingotMaterial, radioactiveMaterial) -> {
            radioactiveMaterial.fluidHexachloride = MetaFluids.registerFluid(ingotMaterial, MetaFluids.FluidType.valueOf("HEXACHLORIDE"), 300);
            radioactiveMaterial.fluidHexafluoride = MetaFluids.registerFluid(ingotMaterial, MetaFluids.FluidType.valueOf("HEXAFLUORIDE"), 300);
        });

        IsotopeMaterial.REGISTRY.forEach((ingotMaterial, isotopeMaterial) -> {
            isotopeMaterial.fluidHexafluoride = MetaFluids.registerFluid(ingotMaterial, MetaFluids.FluidType.valueOf("HEXAFLUORIDE"), 300);
            isotopeMaterial.depletedFuelNitrateSolution = MetaFluids.registerFluid(ingotMaterial, MetaFluids.FluidType.valueOf("DEPLETED_FUEL_NITRATE_SOLUTION"), 300);
            isotopeMaterial.hexafluorideSteamCracked = MetaFluids.registerFluid(ingotMaterial, MetaFluids.FluidType.valueOf("HEXAFLUORIDE_STEAM_CRACKED"), 300);
        });

        for (SimpleFluidMaterial fluidMat : SimpleFluidMaterial.GA_FLUIDS.values()) {
            Fluid fluid = new Fluid(fluidMat.name, MetaFluids.AUTO_GENERATED_FLUID_TEXTURE, MetaFluids.AUTO_GENERATED_FLUID_TEXTURE, fluidMat.rgb);
            fluid.setTemperature(fluidMat.temperature);
            if (!FluidRegistry.isFluidRegistered(fluid.getName())) {
                FluidRegistry.registerFluid(fluid);
                FluidTooltipUtil.registerTooltip(fluid, fluidMat.getFormula());
                FluidRegistry.addBucketForFluid(fluid);
                fluidMat.fluid = fluid;
            } else {
                if (!FluidRegistry.hasBucket(FluidRegistry.getFluid(fluid.getName()))) {
                    FluidRegistry.addBucketForFluid(fluid);
                }
                fluidMat.fluid = FluidRegistry.getFluid(fluid.getName());
            }
            if (fluidMat.hasPlasma) {
                Fluid plasma = new Fluid(fluidMat.name + "_plasma", MetaFluids.AUTO_GENERATED_FLUID_TEXTURE, MetaFluids.AUTO_GENERATED_FLUID_TEXTURE, fluidMat.rgb);
                plasma.setTemperature(fluidMat.temperature + 10000);
                if (!FluidRegistry.isFluidRegistered(plasma.getName())) {
                    FluidRegistry.registerFluid(plasma);
                    FluidTooltipUtil.registerTooltip(plasma, fluidMat.getFormula());
                    FluidRegistry.addBucketForFluid(plasma);
                    fluidMat.plasma = plasma;
                } else {
                    fluidMat.plasma = FluidRegistry.getFluid(plasma.getName());
                }
                fluidToMaterialMappings.put(fluidMat.plasma.getName(), fluidMat);
            }
            fluidToMaterialMappings.put(fluidMat.fluid.getName(), fluidMat);
        }
    }

    @Nullable
    public static FluidStack getHotFluid(Material material, int amount) {
        return new FluidStack(HOT_FLUIDS.get(material), amount);
    }
    
    public static SimpleFluidMaterial getMaterialFromFluid(Fluid fluid) {
        return fluidToMaterialMappings.get(fluid.getName());
    }
}
