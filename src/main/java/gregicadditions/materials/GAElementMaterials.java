package gregicadditions.materials;

import gregtech.api.unification.material.Material;

import static gregicadditions.GAMaterials.*;
import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;

public class GAElementMaterials {

    /*
     * IDs 3000-3499
     */
    public static void register() {
        MetastableOganesson = new Material.Builder(3000, "metastable_oganesson")
                .ingot(7)
                .color(0xE61C24).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .components(Og)
                .blastTemp(10380)
                .build();

        MetastableFlerovium = new Material.Builder(3001, "metastable_flerovium")
                .ingot(7)
                .color(0x521973).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .element(Fl)
                .blastTemp(10990)
                .build();

        MetastableHassium = new Material.Builder(3002, "metastable_hassium")
                .ingot(6)
                .color(0x2D3A9D).iconSet(SHINY)
                .flags(GA_CORE_METAL)
                .element(Hs)
                .blastTemp(11240)
                .build();

        // todo
//        Protactinium = new RadioactiveMaterial(824, "protactinium", 0xA78B6D, METALLIC, 3, of(), GA_EXT2_METAL, Pa, 0, 0, 0, 0);
//        UraniumRadioactive = new RadioactiveMaterial(822, "uranium_radioactive", Uranium.materialRGB, METALLIC, 3, of(), GA_EXT2_METAL | GENERATE_ORE, U, 0, 0, 0, 0);
//        Neptunium = new RadioactiveMaterial(818, "neptunium", 0x284D7B, METALLIC, 3, of(), GA_EXT2_METAL, Np, 0, 0, 0, 0);
//        PlutoniumRadioactive = new RadioactiveMaterial(814, "plutonium_radioactive", Plutonium.materialRGB, METALLIC, 3, of(), GA_EXT2_METAL, Pu, 0, 0, 0, 0);
//        Curium = new RadioactiveMaterial(807, "curium", 0x7B544E, METALLIC, 3, of(), GA_EXT2_METAL, Cm, 0, 0, 0, 0);
//        Berkelium = new RadioactiveMaterial(801, "berkelium", 0x645A88, METALLIC, 3, of(), GA_EXT2_METAL, Bk, 0, 0, 0, 0);
//        Californium = new RadioactiveMaterial(797, "californium", 0xA85A12, METALLIC, 3, of(), GA_EXT2_METAL, Cf, 0, 0, 0, 0);
//        Einsteinium = new RadioactiveMaterial(791, "einsteinium", 0xCE9F00, METALLIC, 3, of(), GA_EXT2_METAL, Es, 0, 0, 0, 0);
//        Fermium = new RadioactiveMaterial(786, "fermium", 0x984ACF, METALLIC, 3, of(), GA_EXT2_METAL, Fm, 0, 0, 0, 0);
//        Mendelevium = new RadioactiveMaterial(780, "mendelevium", 0x1D4ACF, METALLIC, 3, of(), GA_EXT2_METAL, Md, 0, 0, 0, 0);

//        // Thorium
//        RadioactiveMaterial ThoriumRadioactive = new RadioactiveMaterial(Thorium);
//        IsotopeMaterial Thorium232Isotope = new IsotopeMaterial(Thorium, RadioactiveMaterial.REGISTRY.get(Thorium), 232);
//        IsotopeMaterial Thorium233 = new IsotopeMaterial(825, RadioactiveMaterial.REGISTRY.get(Thorium), 233, 0);
//
//        // Protactinium
//        IsotopeMaterial Protactinium233 = new IsotopeMaterial(823, RadioactiveMaterial.REGISTRY.get(Protactinium.getMaterial()), 233, 0);
//
//        // Uranium
//        IsotopeMaterial Uranium238Isotope = new IsotopeMaterial(Uranium, RadioactiveMaterial.REGISTRY.get(UraniumRadioactive.getMaterial()), 238);
//        IsotopeMaterial Uranium233 = new IsotopeMaterial(821, RadioactiveMaterial.REGISTRY.get(UraniumRadioactive.getMaterial()), 233, 0);
//        IsotopeMaterial Uranium234 = new IsotopeMaterial(820, RadioactiveMaterial.REGISTRY.get(UraniumRadioactive.getMaterial()), 234, 0);
//        IsotopeMaterial Uranium235Isotope = new IsotopeMaterial(Uranium235, RadioactiveMaterial.REGISTRY.get(UraniumRadioactive.getMaterial()), 235);
//        IsotopeMaterial Uranium239 = new IsotopeMaterial(819, RadioactiveMaterial.REGISTRY.get(UraniumRadioactive.getMaterial()), 239, 0);
//
//        // Neptunium
//        IsotopeMaterial Neptunium235 = new IsotopeMaterial(817, RadioactiveMaterial.REGISTRY.get(Neptunium.getMaterial()), 235, 0);
//        IsotopeMaterial Neptunium237 = new IsotopeMaterial(816, RadioactiveMaterial.REGISTRY.get(Neptunium.getMaterial()), 237, 0);
//        IsotopeMaterial Neptunium239 = new IsotopeMaterial(815, RadioactiveMaterial.REGISTRY.get(Neptunium.getMaterial()), 239, 0);
//
//        // Plutonium
//        IsotopeMaterial Plutonium239 = new IsotopeMaterial(813, RadioactiveMaterial.REGISTRY.get(PlutoniumRadioactive.getMaterial()), 239, 0);
//        IsotopeMaterial Plutonium240 = new IsotopeMaterial(812, RadioactiveMaterial.REGISTRY.get(PlutoniumRadioactive.getMaterial()), 240, 0);
//        IsotopeMaterial Plutonium241Isotope = new IsotopeMaterial(Plutonium241, RadioactiveMaterial.REGISTRY.get(PlutoniumRadioactive.getMaterial()), 241);
//        IsotopeMaterial Plutonium244Isotope = new IsotopeMaterial(Plutonium, RadioactiveMaterial.REGISTRY.get(PlutoniumRadioactive.getMaterial()), 244);
//        IsotopeMaterial Plutonium245 = new IsotopeMaterial(811, RadioactiveMaterial.REGISTRY.get(PlutoniumRadioactive.getMaterial()), 245, 0);
//
//        // Americium
//        RadioactiveMaterial AmericiumRadioactive = new RadioactiveMaterial(Americium);
//        IsotopeMaterial Americium241 = new IsotopeMaterial(810, RadioactiveMaterial.REGISTRY.get(Americium), 241, 0);
//        IsotopeMaterial Americium243 = new IsotopeMaterial(809, RadioactiveMaterial.REGISTRY.get(Americium), 243, 0);
//        IsotopeMaterial Americium245 = new IsotopeMaterial(808, RadioactiveMaterial.REGISTRY.get(Americium), 245, 0);
//
//        // Curium
//        IsotopeMaterial Curium245 = new IsotopeMaterial(806, RadioactiveMaterial.REGISTRY.get(Curium.getMaterial()), 245, 0);
//        IsotopeMaterial Curium246 = new IsotopeMaterial(805, RadioactiveMaterial.REGISTRY.get(Curium.getMaterial()), 246, 0);
//        IsotopeMaterial Curium247 = new IsotopeMaterial(804, RadioactiveMaterial.REGISTRY.get(Curium.getMaterial()), 247, 0);
//        IsotopeMaterial Curium250 = new IsotopeMaterial(803, RadioactiveMaterial.REGISTRY.get(Curium.getMaterial()), 250, 0);
//        IsotopeMaterial Curium251 = new IsotopeMaterial(802, RadioactiveMaterial.REGISTRY.get(Curium.getMaterial()), 251, 0);
//
//        // Berkelium
//        IsotopeMaterial Berkelium247 = new IsotopeMaterial(800, RadioactiveMaterial.REGISTRY.get(Berkelium.getMaterial()), 247, 0);
//        IsotopeMaterial Berkelium249 = new IsotopeMaterial(799, RadioactiveMaterial.REGISTRY.get(Berkelium.getMaterial()), 249, 0);
//        IsotopeMaterial Berkelium251 = new IsotopeMaterial(798, RadioactiveMaterial.REGISTRY.get(Berkelium.getMaterial()), 251, 0);
//
//        // Californium
//        IsotopeMaterial Californium251 = new IsotopeMaterial(796, RadioactiveMaterial.REGISTRY.get(Californium.getMaterial()), 251, 0);
//        IsotopeMaterial Californium252 = new IsotopeMaterial(795, RadioactiveMaterial.REGISTRY.get(Californium.getMaterial()), 252, 0);
//        IsotopeMaterial Californium253 = new IsotopeMaterial(794, RadioactiveMaterial.REGISTRY.get(Californium.getMaterial()), 253, 0);
//        IsotopeMaterial Californium256 = new IsotopeMaterial(793, RadioactiveMaterial.REGISTRY.get(Californium.getMaterial()), 256, 0);
//        IsotopeMaterial Californium257 = new IsotopeMaterial(792, RadioactiveMaterial.REGISTRY.get(Californium.getMaterial()), 257, 0);
//
//        // Einsteinium
//        IsotopeMaterial Einsteinium253 = new IsotopeMaterial(790, RadioactiveMaterial.REGISTRY.get(Einsteinium.getMaterial()), 253, 0);
//        IsotopeMaterial Einsteinium255 = new IsotopeMaterial(789, RadioactiveMaterial.REGISTRY.get(Einsteinium.getMaterial()), 255, 0);
//        IsotopeMaterial Einsteinium257 = new IsotopeMaterial(787, RadioactiveMaterial.REGISTRY.get(Einsteinium.getMaterial()), 257, 0);
//
//        // Fermium
//        IsotopeMaterial Fermium257 = new IsotopeMaterial(785, RadioactiveMaterial.REGISTRY.get(Fermium.getMaterial()), 257, 0);
//        IsotopeMaterial Fermium258 = new IsotopeMaterial(784, RadioactiveMaterial.REGISTRY.get(Fermium.getMaterial()), 258, 0);
//        IsotopeMaterial Fermium259 = new IsotopeMaterial(783, RadioactiveMaterial.REGISTRY.get(Fermium.getMaterial()), 259, 0);
//        IsotopeMaterial Fermium262 = new IsotopeMaterial(782, RadioactiveMaterial.REGISTRY.get(Fermium.getMaterial()), 262, 0);
//        IsotopeMaterial Fermium263 = new IsotopeMaterial(781, RadioactiveMaterial.REGISTRY.get(Fermium.getMaterial()), 263, 0);
//
//        // Mendelevium
//        IsotopeMaterial Mendelevium259 = new IsotopeMaterial(779, RadioactiveMaterial.REGISTRY.get(Mendelevium.getMaterial()), 259, 0);
//        IsotopeMaterial Mendelevium261 = new IsotopeMaterial(778, RadioactiveMaterial.REGISTRY.get(Mendelevium.getMaterial()), 261, 0);
//        IsotopeMaterial Mendelevium263 = new IsotopeMaterial(777, RadioactiveMaterial.REGISTRY.get(Mendelevium.getMaterial()), 263, 0);
    }
}
