package gregicadditions.integrations.exnihilocreatio.machines;

import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.render.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntitySieve extends SimpleMachineMetaTileEntity {
    public MetaTileEntitySieve(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, GARecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, tier);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 216)
                .widget(new LabelWidget(6, 6, getMetaFullName()))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT, 8, 134)
                .widget(new SlotWidget(this.importItems, 0, 35, 25).setBackgroundTexture(GuiTextures.SLOT))
                .widget(new SlotWidget(this.importItems, 1, 53, 25).setBackgroundTexture(GuiTextures.SLOT))
                .widget(new ProgressWidget(workable::getProgressPercent, 78, 24, 20, 18, GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED));

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                builder.widget(new SlotWidget(this.exportItems, y * 4 + x, 98 + x * 18, 14 + y * 18, true, false).setBackgroundTexture(GuiTextures.SLOT));
            }
        }

        return builder.build(getHolder(), player);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntitySieve(metaTileEntityId, getTier());
    }
}
