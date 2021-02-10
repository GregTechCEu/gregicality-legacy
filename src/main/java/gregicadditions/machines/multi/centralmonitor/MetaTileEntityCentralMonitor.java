package gregicadditions.machines.multi.centralmonitor;

import gregicadditions.item.GAMetaBlocks;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.util.ResourceLocation;

import static gregtech.api.multiblock.BlockPattern.RelativeDirection.*;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityCentralMonitor extends MultiblockWithDisplayBase {
    public int height;

    public MetaTileEntityCentralMonitor(ResourceLocation metaTileEntityId, int height) {
        super(metaTileEntityId);
        this.height = height;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntityCentralMonitor(metaTileEntityId, height);
    }

    @Override
    protected void updateFormedValid() {

    }

    @Override
    protected BlockPattern createStructurePattern() {
        StringBuilder start = new StringBuilder("ES");
        StringBuilder slice = new StringBuilder("BB");
        StringBuilder end = new StringBuilder("AA");
        for (int i = 0; i < height - 2; i++) {
            start.append('A');
            slice.append('B');
            end.append('A');
        }

        return FactoryBlockPattern.start(UP, BACK, RIGHT)
                .aisle(start.toString())
                .aisle(slice.toString()).setRepeatable(3, 5)
                .aisle(end.toString())
                .where('S', selfPredicate())
                .where('A', statePredicate(GAMetaBlocks.getMetalCasingBlockState(Steel)))
                .where('E', abilityPartPredicate(MultiblockAbility.INPUT_ENERGY))
                .where('B', tilePredicate((state, tile) -> tile.metaTileEntityId.equals(MetaTileEntities.ITEM_IMPORT_BUS[0].metaTileEntityId)))
                .where('#', (tile) -> true).build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GAMetaBlocks.METAL_CASING.get(Steel);
    }
}
