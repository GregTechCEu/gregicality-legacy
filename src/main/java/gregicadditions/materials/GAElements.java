package gregicadditions.materials;

import gregtech.api.unification.Element;

import static gregtech.api.unification.Elements.add;

public class GAElements {

    private GAElements() {
    }

    //todo halfLifeSeconds
    public static final Element He4 = add(2, 2, -1, null, "Helium-4", "He-4", true);
    public static final Element C12 = add(6, 6, -1, null, "Carbon-12", "C-12", true);
    public static final Element C13 = add(6, 7, -1, null, "Carbon-13", "C-13", true);
    public static final Element N14 = add(7, 7, -1, null, "Nitrogen-14", "N-14", true);
    public static final Element N15 = add(7, 8, -1, null, "Nitrogen-15", "N-15", true);
    public static final Element Ca44 = add(20, 24, -1, null, "Calcium-44", "Ca-44", true);
    public static final Element Yb178 = add(70, 108, -1, null, "Ytterbium-178", "Yb-178", true);
    public static final Element Cr48 = add(24, 24, -1, "Ti", "Chromium-48", "Cr-48", true);
    public static final Element Fe52 = add(26, 26, -1, "Cr", "Iron-52", "Fe-52", true);
    public static final Element Ni56 = add(28, 28, -1, "Fe", "Nickel-56", "Ni-56", true);
    public static final Element Ti44 = add(22, 22, -1, null, "Titanium-44", "Ti-44", true);
    public static final Element Ti50 = add(22, 28, -1, null, "Titanium-50", "Ti-50", true);
    public static final Element Th232 = add(90, 142, -1, "Ra", "Thorium-232", "Th-232", true);
    public static final Element Th233 = add(90, 143, -1, "Pa-233", "Thorium-233", "Th-233", true);
    public static final Element Pa233 = add(91, 142, -1, "U-233", "Protactinium-233", "Pa-233", true);
    public static final Element U233 = add(92, 141, -1, "Th-239", "Uranium-233", "U-233", true);
    public static final Element U234 = add(92, 142, -1, "Th-230", "Uranium-234", "U-234", true);
    public static final Element U239 = add(92, 147, -1, "Pu-239", "Uranium-239", "U-239", true);
    public static final Element Np235 = add(93, 142, -1, "U-235", "Neptunium-235", "Np-235", true);
    public static final Element Np237 = add(93, 145, -1, "Pa-233", "Neptunium-237", "Np-237", true);
    public static final Element Np239 = add(93, 147, -1, "Pu-239", "Neptunium-239", "Np-239", true);
    public static final Element Pu240 = add(94, 146, -1, "U-236", "Plutonium-240", "Pu-240", true);
    public static final Element Pu244 = add(94, 150, -1, "U-240", "Plutonium-244", "Pu-244", true);
    public static final Element Pu245 = add(94, 151, -1, "Am-245", "Plutonium-245", "Pu-245", true);
    public static final Element Am241 = add(95, 146, -1, "Np-237", "Americium-241", "Am-241", true);
    public static final Element Am243 = add(95, 148, -1, "Np-239", "Americium-243", "Am-243", true);
    public static final Element Am245 = add(95, 148, -1, "Cm-245", "Americium-245", "Am-245", true);
    public static final Element Cm245 = add(96, 149, -1, "Pu-241", "Curium-245", "Cm-245", true);
    public static final Element Cm246 = add(96, 150, -1, "Pu-242", "Curium-246", "Cm-246", true);
    public static final Element Cm247 = add(96, 151, -1, "Pu-243", "Curium-247", "Cm-247", true);
    public static final Element Cm250 = add(96, 154, -1, "Pu-246", "Curium-250", "Cm-250", true);
    public static final Element Cm251 = add(96, 155, -1, "Bk-251", "Curium-251", "Cm-251", true);
    public static final Element Bk247 = add(97, 150, -1, "Am-243", "Berkelium-247", "Bk-247", true);
    public static final Element Bk249 = add(97, 152, -1, "Cf-249", "Berkelium-249", "Bk-249", true);
    public static final Element Bk251 = add(97, 154, -1, "Cf-251", "Berkelium-251", "Bk-251", true);
    public static final Element Cf251 = add(98, 153, -1, "Cm-247", "Californium-251", "Cf-251", true);
    public static final Element Cf252 = add(98, 154, -1, "Cm-248", "Californium-252", "Cf-252", true);
    public static final Element Cf253 = add(98, 155, -1, "Es-253", "Californium-253", "Cf-253", true);
    public static final Element Cf256 = add(98, 158, -1, "Cm-252", "Californium-256", "Cf-256", true);
    public static final Element Cf257 = add(98, 159, -1, "???", "Californium-257", "Cf-257", true); //todo is this real???
    public static final Element Es253 = add(99, 158, -1, "Bk-249", "Einsteinium-253", "Es-253", true);
    public static final Element Es255 = add(99, 160, -1, "Fm-255", "Einsteinium-255", "Es-255", true);
    public static final Element Es257 = add(99, 160, -1, "Fm-257", "Einsteinium-257", "Es-257", true);
    public static final Element Fm257 = add(100, 157, -1, "Cf-253", "Fermium-257", "Fm-257", true);
    public static final Element Fm258 = add(100, 158, -1, null, "Fermium-258", "Fm-258", true); //todo fm decay
    public static final Element Fm259 = add(100, 159, -1, null, "Fermium-259", "Fm-259", true);
    public static final Element Fm262 = add(100, 162, -1, null, "Fermium-262", "Fm-262", true); //todo is this and 263 real?
    public static final Element Fm263 = add(100, 163, -1, null, "Fermium-263", "Fm-263", true);
    public static final Element Md259 = add(101, 158, -1, null, "Fermium-263", "Fm-263", true); //todo md decay
    public static final Element Md261 = add(101, 160, -1, null, "Fermium-263", "Fm-263", true); //todo is this and 263 real?
    public static final Element Md263 = add(101, 162, -1, null, "Fermium-263", "Fm-263", true);

}
