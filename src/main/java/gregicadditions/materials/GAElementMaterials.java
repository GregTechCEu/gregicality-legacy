package gregicadditions.materials;

import gregtech.api.unification.material.Material;

import static gregicadditions.GAMaterials.*;

public class GAElementMaterials {

    /*
     * IDs 3000-3499
     */
    public static void register() {


        Helium4 = new Material.Builder(3007, "helium4")
                .fluid(Material.FluidType.GAS)
                .color(0xDDDD22)
                .element(GAElements.He4)
                .build();

        Carbon12 = new Material.Builder(3008, "carbon_12")
                .fluid()
                .color(0x242424)
                .element(GAElements.C12)
                .build();

        Carbon13 = new Material.Builder(3009, "carbon_13")
                .fluid()
                .color(0x151515)
                .element(GAElements.C13)
                .build();

        Nitrogen14 = new Material.Builder(3010, "nitrogen_14")
                .fluid()
                .color(0x00BFC1)
                .element(GAElements.N14)
                .build();

        Nitrogen15 = new Material.Builder(3011, "nitrogen_15")
                .fluid()
                .color(0x00CFD1)
                .element(GAElements.N15)
                .build();

        Calcium44 = new Material.Builder(3012, "calcium_44")
                .fluid()
                .color(0xFFF6F6)
                .element(GAElements.Ca44)
                .build();

        Ytterbium178 = new Material.Builder(3013, "ytterbium_178")
                .fluid()
                .color(0xA8A8A8)
                .element(GAElements.Yb178)
                .build();

        Chromium48 = new Material.Builder(3014, "chromium_48")
                .fluid().plasma()
                .color(0xFFE7E7)
                .element(GAElements.Cr48)
                .build();

        Iron52 = new Material.Builder(3015, "iron_52")
                .fluid().plasma()
                .color(0xC9C9C9)
                .element(GAElements.Fe52)
                .build();

        Nickel56 = new Material.Builder(3016, "nickel_56")
                .fluid().plasma()
                .color(0xC9C9FB)
                .element(GAElements.Ni56)
                .build();

        Titanium44 = new Material.Builder(3017, "titanium_44")
                .fluid().plasma()
                .color(0xC9C9FB)
                .element(GAElements.Ti44)
                .build();

        Titanium50 = new Material.Builder(3018, "titanium_50")
                .fluid()
                .color(0xDDA1F1)
                .element(GAElements.Ti50)
                .build();

        //todo nuclear rework
//        RadioactiveMaterial ThoriumRadioactive = new RadioactiveMaterial(Thorium);
//        Protactinium = new RadioactiveMaterial(824, "protactinium", 0xA78B6D, METALLIC, 3, of(), GA_EXT2_METAL, Pa, 0, 0, 0, 0);
//        UraniumRadioactive = new RadioactiveMaterial(822, "uranium_radioactive", Uranium.materialRGB, METALLIC, 3, of(), GA_EXT2_METAL | GENERATE_ORE, U, 0, 0, 0, 0);
//        Neptunium = new RadioactiveMaterial(818, "neptunium", 0x284D7B, METALLIC, 3, of(), GA_EXT2_METAL, Np, 0, 0, 0, 0);
//        PlutoniumRadioactive = new RadioactiveMaterial(814, "plutonium_radioactive", Plutonium.materialRGB, METALLIC, 3, of(), GA_EXT2_METAL, Pu, 0, 0, 0, 0);
//        RadioactiveMaterial AmericiumRadioactive = new RadioactiveMaterial(Americium);
//        Curium = new RadioactiveMaterial(807, "curium", 0x7B544E, METALLIC, 3, of(), GA_EXT2_METAL, Cm, 0, 0, 0, 0);
//        Berkelium = new RadioactiveMaterial(801, "berkelium", 0x645A88, METALLIC, 3, of(), GA_EXT2_METAL, Bk, 0, 0, 0, 0);
//        Californium = new RadioactiveMaterial(797, "californium", 0xA85A12, METALLIC, 3, of(), GA_EXT2_METAL, Cf, 0, 0, 0, 0);
//        Einsteinium = new RadioactiveMaterial(791, "einsteinium", 0xCE9F00, METALLIC, 3, of(), GA_EXT2_METAL, Es, 0, 0, 0, 0);
//        Fermium = new RadioactiveMaterial(786, "fermium", 0x984ACF, METALLIC, 3, of(), GA_EXT2_METAL, Fm, 0, 0, 0, 0);
//        Mendelevium = new RadioactiveMaterial(780, "mendelevium", 0x1D4ACF, METALLIC, 3, of(), GA_EXT2_METAL, Md, 0, 0, 0, 0);

        // Thorium

        Thorium232 = new Material.Builder(3019, "thorium_232")
                .ingot().fluid()
                .color(0x111E11)
                .element(GAElements.Th232)
                .build();

        Thorium233 = new Material.Builder(3020, "thorium_233")
                .ingot().fluid()
                .color(0x121F12)
                .element(GAElements.Th233)
                .build();

        // Protactinium
        Protactinium233 = new Material.Builder(3021, "protactinium_233")
                .ingot().fluid()
                .color(0xA88C6E)
                .element(GAElements.Pa233)
                .build();

        // Uranium
        Uranium233 = new Material.Builder(3022, "uranium_233")
                .ingot().fluid()
                .color(0x44F844)
                .element(GAElements.U233)
                .build();

        Uranium234 = new Material.Builder(3023, "uranium_234")
                .ingot().fluid()
                .color(0x45F945)
                .element(GAElements.U234)
                .build();

        Uranium239 = new Material.Builder(3024, "uranium_239")
                .ingot().fluid()
                .color(0x33F133)
                .element(GAElements.U239)
                .build();

        // Neptunium
        Neptunium235 = new Material.Builder(3025, "neptunium_235")
                .ingot().fluid()
                .color(0x284D7B)
                .element(GAElements.Np235)
                .build();

        Neptunium237 = new Material.Builder(3026, "neptunium_237")
                .ingot().fluid()
                .color(0x294E7C)
                .element(GAElements.Np237)
                .build();

        Neptunium239 = new Material.Builder(3027, "neptunium_239")
                .ingot().fluid()
                .color(0x304F7D)
                .element(GAElements.Np239)
                .build();

        // Plutonium
        Plutonium240 = new Material.Builder(3028, "plutonium_240")
                .ingot().fluid()
                .color(0xF13333)
                .element(GAElements.Pu240)
                .build();

        Plutonium244 = new Material.Builder(3029, "plutonium_244")
                .ingot().fluid()
                .color(0xFB4747)
                .element(GAElements.Pu244)
                .build();

        Plutonium245 = new Material.Builder(3030, "plutonium_245")
                .ingot().fluid()
                .color(0xFC4848)
                .element(GAElements.Pu245)
                .build();

        // Americium
        Americium241 = new Material.Builder(3031, "americium_241")
                .ingot().fluid()
                .color(0xC9C9C9)
                .element(GAElements.Am241)
                .build();

        Americium243 = new Material.Builder(3032, "americium_243")
                .ingot().fluid()
                .color(0xD0D0D0)
                .element(GAElements.Am243)
                .build();

        Americium245 = new Material.Builder(3033, "americium_245")
                .ingot().fluid()
                .color(0xD1D1D1)
                .element(GAElements.Am245)
                .build();

        // Curium
        Curium245 = new Material.Builder(3034, "curium_245")
                .ingot().fluid()
                .color(0x7B544E)
                .element(GAElements.Cm245)
                .build();

        Curium246 = new Material.Builder(3035, "curium_246")
                .ingot().fluid()
                .color(0x7C554F)
                .element(GAElements.Cm246)
                .build();

        Curium247 = new Material.Builder(3036, "curium_247")
                .ingot().fluid()
                .color(0x7D5650)
                .element(GAElements.Cm247)
                .build();

        Curium250 = new Material.Builder(3037, "curium_250")
                .ingot().fluid()
                .color(0x7E5751)
                .element(GAElements.Cm250)
                .build();

        Curium251 = new Material.Builder(3038, "curium_251")
                .ingot().fluid()
                .color(0x7F5852)
                .element(GAElements.Cm251)
                .build();

        // Berkelium
        Berkelium247 = new Material.Builder(3039, "berkelium_247")
                .ingot().fluid()
                .color(0x655B89)
                .element(GAElements.Bk247)
                .build();

        Berkelium249 = new Material.Builder(3040, "berkelium_249")
                .ingot().fluid()
                .color(0x665C8A)
                .element(GAElements.Bk249)
                .build();

        Berkelium251 = new Material.Builder(3041, "berkelium_251")
                .ingot().fluid()
                .color(0x675D8B)
                .element(GAElements.Bk251)
                .build();

        // Californium
        Californium251 = new Material.Builder(3042, "californium_251")
                .ingot().fluid()
                .color(0xA95B13)
                .element(GAElements.Cf251)
                .build();

        Californium252 = new Material.Builder(3043, "californium_252")
                .ingot().fluid()
                .color(0xAA5C14)
                .element(GAElements.Cf252)
                .build();

        Californium253 = new Material.Builder(3044, "californium_253")
                .ingot().fluid()
                .color(0xAB5D15)
                .element(GAElements.Cf253)
                .build();

        Californium256 = new Material.Builder(3045, "californium_256")
                .ingot().fluid()
                .color(0xAC5E16)
                .element(GAElements.Cf256)
                .build();

        Californium257 = new Material.Builder(3046, "californium_257")
                .ingot().fluid()
                .color(0xAD5F17)
                .element(GAElements.Cf257)
                .build();

        // Einsteinium
        Einsteinium253 = new Material.Builder(3047, "einsteinium_253")
                .ingot().fluid()
                .color(0xCFA001)
                .element(GAElements.Es253)
                .build();

        Einsteinium255 = new Material.Builder(3048, "einsteinium_255")
                .ingot().fluid()
                .color(0xD0A102)
                .element(GAElements.Es255)
                .build();

        Einsteinium257 = new Material.Builder(3049, "einsteinium_257")
                .ingot().fluid()
                .color(0xD1A203)
                .element(GAElements.Es257)
                .build();

        // Fermium
        Fermium257 = new Material.Builder(3050, "fermium_257")
                .ingot().fluid()
                .color(0x994BD0)
                .element(GAElements.Fm257)
                .build();

        Fermium258 = new Material.Builder(3051, "fermium_258")
                .ingot().fluid()
                .color(0xA04CD1)
                .element(GAElements.Fm258)
                .build();

        Fermium259 = new Material.Builder(3052, "fermium_259")
                .ingot().fluid()
                .color(0xA14DD2)
                .element(GAElements.Fm259)
                .build();

        Fermium262 = new Material.Builder(3053, "fermium_262")
                .ingot().fluid()
                .color(0xA24ED3)
                .element(GAElements.Fm262)
                .build();

        Fermium263 = new Material.Builder(3054, "fermium_263")
                .ingot().fluid()
                .color(0xA34FD4)
                .element(GAElements.Fm263)
                .build();

        // Mendelevium
        Mendelevium259 = new Material.Builder(3055, "mendelevium_259")
                .ingot().fluid()
                .color(0x1E4BD0)
                .element(GAElements.Md259)
                .build();

        Mendelevium261 = new Material.Builder(3056, "mendelevium_261")
                .ingot().fluid()
                .color(0x1F4CD1)
                .element(GAElements.Md261)
                .build();

        Mendelevium263 = new Material.Builder(3057, "mendelevium_263")
                .ingot().fluid()
                .color(0x204DD2)
                .element(GAElements.Md263)
                .build();
    }
}
