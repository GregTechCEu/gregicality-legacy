package gregicadditions.integrations.exnihilocreatio.machines;

import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.api.render.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class SteamSieve extends SteamMetaTileEntity {

    public SteamSieve(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new SteamSieve(metaTileEntityId, isHighPressure);
    }

    @Override
    public IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(2);
    }

    @Override
    public IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(24);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BRONZE_BACKGROUND, 176, 216)
                .widget(new LabelWidget(6, 6, getMetaFullName()))
                .widget(new ImageWidget(79, 42, 18, 18, getFullGuiTexture("not_enough_steam_%s"))
                        .setPredicate(() -> workableHandler.isHasNotEnoughEnergy()))
                .bindPlayerInventory(player.inventory, BRONZE_SLOT_BACKGROUND_TEXTURE, 8, 134)
                .widget(new SlotWidget(this.importItems, 0, 35, 25)
                        .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE))
                .widget(new SlotWidget(this.importItems, 1, 53, 25)
                        .setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE))
                .widget(new ProgressWidget(workableHandler::getProgressPercent, 78, 24, 20, 18)
                        .setProgressBar(getFullGuiTexture("progress_bar_%s_macerator"),
                                getFullGuiTexture("progress_bar_%s_macerator_filled"),
                                ProgressWidget.MoveType.HORIZONTAL));

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                builder.widget(new SlotWidget(this.exportItems, y * 4 + x, 98 + x * 18, 7 + y * 18, true, false).setBackgroundTexture(BRONZE_SLOT_BACKGROUND_TEXTURE));
            }
        }

        return builder.build(getHolder(), player);
    }
}