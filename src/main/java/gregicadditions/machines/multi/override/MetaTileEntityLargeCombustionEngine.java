package gregicadditions.machines.multi.override;

import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityDieselEngine;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityLargeCombustionEngine extends MetaTileEntityDieselEngine {

	public MetaTileEntityLargeCombustionEngine(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
	}

	public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
		return new MetaTileEntityLargeCombustionEngine(this.metaTileEntityId);
	}

	@Override
	protected BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
				.aisle("XXX", "XDX", "XXX")
				.aisle("XHX", "HGH", "XHX")
				.aisle("XHX", "HGH", "XHX")
				.aisle("AAA", "AYA", "AAA")
				.where('X', statePredicate(this.getCasingState()))
				.where('G', statePredicate(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TITANIUM_GEARBOX)))
				.where('H', statePredicate(this.getCasingState()).or(abilityPartPredicate(MultiblockAbility.IMPORT_FLUIDS, GregicAdditionsCapabilities.MAINTENANCE_HATCH)))
				.where('D', abilityPartPredicate(MultiblockAbility.OUTPUT_ENERGY))
				.where('A', this.intakeCasingPredicate())
				.where('Y', this.selfPredicate())
				.build();
	}
}
