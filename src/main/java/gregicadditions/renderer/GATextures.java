package gregicadditions.renderer;

import gregtech.api.gui.resources.TextureArea;
import gregtech.api.render.SimpleOverlayRenderer;

public class GATextures {
    public static SimpleOverlayRenderer COVER_INTERFACE_FLUID;
    public static SimpleOverlayRenderer COVER_INTERFACE_FLUID_GLASS;
    public static SimpleOverlayRenderer COVER_INTERFACE_ITEM;
    public static SimpleOverlayRenderer COVER_INTERFACE_ENERGY;
    public static SimpleOverlayRenderer COVER_INTERFACE_MACHINE_ON;
    public static SimpleOverlayRenderer COVER_INTERFACE_MACHINE_OFF;
    public static TextureArea  BUTTON_FLUID;
    public static TextureArea  BUTTON_ITEM;
    public static TextureArea  BUTTON_ENERGY;
    public static TextureArea  BUTTON_MACHINE;
    public static TextureArea  BUTTON_INTERFACE;

    public static void initTextures() {
        COVER_INTERFACE_FLUID = new SimpleOverlayRenderer("cover/cover_interface_fluid");
        COVER_INTERFACE_FLUID_GLASS = new SimpleOverlayRenderer("cover/cover_interface_fluid_glass");
        COVER_INTERFACE_ITEM = new SimpleOverlayRenderer("cover/cover_interface_item");
        COVER_INTERFACE_ENERGY = new SimpleOverlayRenderer("cover/cover_interface_energy");
        COVER_INTERFACE_MACHINE_ON = new SimpleOverlayRenderer("cover/cover_interface_machine_on");
        COVER_INTERFACE_MACHINE_OFF = new SimpleOverlayRenderer("cover/cover_interface_machine_off");
        BUTTON_FLUID = TextureArea.fullImage("textures/blocks/cover/cover_interface_fluid_button.png");
        BUTTON_ITEM = TextureArea.fullImage("textures/blocks/cover/cover_interface_item_button.png");
        BUTTON_ENERGY = TextureArea.fullImage("textures/blocks/cover/cover_interface_energy_button.png");
        BUTTON_MACHINE = TextureArea.fullImage("textures/blocks/cover/cover_interface_machine_button.png");
        BUTTON_INTERFACE = TextureArea.fullImage("textures/blocks/cover/cover_interface_computer_button.png");
    }

}
