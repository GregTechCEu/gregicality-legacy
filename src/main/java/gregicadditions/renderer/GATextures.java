package gregicadditions.renderer;

import gregtech.api.gui.resources.TextureArea;
import gregtech.api.render.SimpleOverlayRenderer;

public class GATextures {
    public static SimpleOverlayRenderer COVER_INTERFACE_FLUID;
    public static SimpleOverlayRenderer COVER_INTERFACE_FLUID_GLASS;
    public static SimpleOverlayRenderer COVER_INTERFACE_ITEM;
    public static SimpleOverlayRenderer COVER_INTERFACE_ENERGY;
    public static TextureArea  BUTTON_FLUID;
    public static TextureArea  BUTTON_ITEM;
    public static TextureArea  BUTTON_ENERGY;
    public static TextureArea  BUTTON_MACHINE;
    public static TextureArea  BUTTON_INTERFACE;
    public static TextureArea  FLUID_IMPORT;
    public static TextureArea  FLUID_EXPORT;
    public static TextureArea  ITEM_IMPORT;
    public static TextureArea  ITEM_EXPORT;

    public static void initTextures() {
        COVER_INTERFACE_FLUID = new SimpleOverlayRenderer("cover/cover_interface_fluid");
        COVER_INTERFACE_FLUID_GLASS = new SimpleOverlayRenderer("cover/cover_interface_fluid_glass");
        COVER_INTERFACE_ITEM = new SimpleOverlayRenderer("cover/cover_interface_item");
        COVER_INTERFACE_ENERGY = new SimpleOverlayRenderer("cover/cover_interface_energy");
        BUTTON_FLUID = TextureArea.fullImage("textures/blocks/cover/cover_interface_fluid_button.png");
        BUTTON_ITEM = TextureArea.fullImage("textures/blocks/cover/cover_interface_item_button.png");
        BUTTON_ENERGY = TextureArea.fullImage("textures/blocks/cover/cover_interface_energy_button.png");
        BUTTON_MACHINE = TextureArea.fullImage("textures/blocks/cover/cover_interface_machine_button.png");
        BUTTON_INTERFACE = TextureArea.fullImage("textures/blocks/cover/cover_interface_computer_button.png");
        FLUID_IMPORT = TextureArea.fullImage("textures/blocks/cover/cover_interface_fluid_input.png");
        FLUID_EXPORT = TextureArea.fullImage("textures/blocks/cover/cover_interface_fluid_output.png");
        ITEM_IMPORT = TextureArea.fullImage("textures/blocks/cover/cover_interface_item_input.png");
        ITEM_EXPORT = TextureArea.fullImage("textures/blocks/cover/cover_interface_item_output.png");
    }

}
