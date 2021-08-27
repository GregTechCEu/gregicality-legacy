package gregicadditions.pipelike.opticalfiber;

import gregicadditions.materials.GAOrePrefix;
import gregtech.api.pipenet.block.material.IMaterialPipeType;
import gregtech.api.unification.ore.OrePrefix;

public enum OpticalFiberSize implements IMaterialPipeType<OpticalFiberProperties> {

    WIRE_SINGLE("optical_fiber_single", 0.075f, 1, GAOrePrefix.opticalFiberSingle),
    WIRE_DOUBLE("optical_fiber_double", 0.1f, 2, GAOrePrefix.opticalFiberDouble),
    WIRE_QUADRUPLE("optical_fiber_quadruple", 0.15f, 4, GAOrePrefix.opticalFiberQuadruple),
    WIRE_OCTAL("optical_fiber_octal", 0.2f, 8, GAOrePrefix.opticalFiberOctal),
    WIRE_HEX("optical_fiber_hex", 0.3f, 16, GAOrePrefix.opticalFiberHex);

    public final String name;
    public final float thickness;
    public final int parallel;
    public final OrePrefix orePrefix;

    OpticalFiberSize(String name, float thickness, int parallel, OrePrefix orePrefix) {
        this.name = name;
        this.thickness = thickness;
        this.parallel = parallel;
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
        return new OpticalFiberProperties(baseProperties.qubit,
                baseProperties.parallel * parallel);
    }

    @Override
    public boolean isPaintable() {
        return false;
    }

}
