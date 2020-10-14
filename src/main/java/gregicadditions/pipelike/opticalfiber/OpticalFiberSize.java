package gregicadditions.pipelike.opticalfiber;

import gregtech.api.pipenet.block.material.IMaterialPipeType;
import gregtech.api.unification.ore.OrePrefix;

public enum OpticalFiberSize implements IMaterialPipeType<OpticalFiberProperties> {

    WIRE_SINGLE("optical_fiber_single", 0.075f, 1, OrePrefix.valueOf("opticalFiberSingle")),
    WIRE_DOUBLE("optical_fiber_double", 0.1f, 2, OrePrefix.valueOf("opticalFiberDouble")),
    WIRE_QUADRUPLE("optical_fiber_quadruple", 0.15f, 4, OrePrefix.valueOf("opticalFiberQuadruple")),
    WIRE_OCTAL("optical_fiber_octal", 0.2f, 8, OrePrefix.valueOf("opticalFiberOctal")),
    WIRE_HEX("optical_fiber_hex", 0.3f, 16, OrePrefix.valueOf("opticalFiberHex"));

    public final String name;
    public final float thickness;
    public final int amperage;
    public final OrePrefix orePrefix;

    OpticalFiberSize(String name, float thickness, int amperage, OrePrefix orePrefix) {
        this.name = name;
        this.thickness = thickness;
        this.amperage = amperage;
        this.orePrefix = orePrefix;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getThickness() {
        return thickness;
    }

    @Override
    public OrePrefix getOrePrefix() {
        return orePrefix;
    }


    @Override
    public OpticalFiberProperties modifyProperties(OpticalFiberProperties baseProperties) {
        return new OpticalFiberProperties(baseProperties.voltage,
                baseProperties.amperage * amperage);
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

}
