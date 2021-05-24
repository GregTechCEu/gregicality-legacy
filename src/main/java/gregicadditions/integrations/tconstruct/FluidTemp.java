package gregicadditions.integrations.tconstruct;

import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;

@IMaterialHandler.RegisterMaterialHandler
public class FluidTemp implements IMaterialHandler {

	@Override
	public void onMaterialsInit() {
		for (Material mat : Material.MATERIAL_REGISTRY)
			if (mat instanceof DustMaterial && (mat instanceof IngotMaterial || mat.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID))) {
				if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0) ((IngotMaterial) mat).setFluidTemperature(((IngotMaterial) mat).blastFurnaceTemperature);
				else((DustMaterial) mat).setFluidTemperature(500);
			}
	}
}