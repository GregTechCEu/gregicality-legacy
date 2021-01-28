package gregicadditions.machines.multi.steam;

import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.common.metatileentities.electric.multiblockpart.MetaTileEntityItemBus;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

public class MetaTileEntitySteamItemBus extends MetaTileEntityItemBus implements IMultiblockAbilityPart<IItemHandlerModifiable> {

    private final boolean isExportHatch;

    public MetaTileEntitySteamItemBus(ResourceLocation metaTileEntityId, boolean isExportHatch) {
        super(metaTileEntityId, 1, isExportHatch);
        this.isExportHatch = isExportHatch;
    }

    @Override
    public MultiblockAbility<IItemHandlerModifiable> getAbility() {
        return isExportHatch ? GAMultiblockAbility.STEAM_EXPORT_ITEMS : GAMultiblockAbility.STEAM_IMPORT_ITEMS;
    }

    @Override
    public void registerAbilities(List<IItemHandlerModifiable> abilityList) {
        abilityList.add(isExportHatch ? this.exportItems : this.importItems);
    }

    // Override base texture to have a bus with 4 slots, but ULV textures
    @Override
    public ICubeRenderer getBaseTexture() {
        MultiblockControllerBase controller = getController();
        return controller == null ? Textures.VOLTAGE_CASINGS[0] : controller.getBaseTexture(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gtadditions.machine.steam_bus.tooltip"));
    }
}
