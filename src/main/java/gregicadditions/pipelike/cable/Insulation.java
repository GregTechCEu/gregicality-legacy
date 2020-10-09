package gregicadditions.pipelike.cable;

import gregtech.api.pipenet.block.material.IMaterialPipeType;
import gregtech.api.unification.ore.OrePrefix;

public enum Insulation implements IMaterialPipeType<WireProperties> {

    WIRE_SINGLE("optical_fiber_single", 0.1f, 1, OrePrefix.valueOf("opticalFiberSingle")),
    WIRE_DOUBLE("optical_fiber_double", 0.15f, 2, OrePrefix.valueOf("opticalFiberDouble")),
    WIRE_QUADRUPLE("optical_fiber_quadruple", 0.2f, 4, OrePrefix.valueOf("opticalFiberQuadruple")),
    WIRE_OCTAL("optical_fiber_octal", 0.3f, 8, OrePrefix.valueOf("opticalFiberOctal")),
    WIRE_HEX("optical_fiber_hex", 0.4f, 16, OrePrefix.valueOf("opticalFiberHex"));

    public final String name;
    public final float thickness;
    public final int amperage;
    public final OrePrefix orePrefix;

    Insulation(String name, float thickness, int amperage, OrePrefix orePrefix) {
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
    public WireProperties modifyProperties(WireProperties baseProperties) {
        return new WireProperties(baseProperties.voltage,
                baseProperties.amperage * amperage);
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

}
