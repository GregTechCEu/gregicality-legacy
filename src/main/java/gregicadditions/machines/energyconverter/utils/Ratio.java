package gregicadditions.machines.energyconverter.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;


public final class Ratio {
    public final BigInteger input;
    public final BigInteger output;
    private final double inputDouble;
    private final double outputDouble;
    private BigDecimal inputDecimal;
    private BigDecimal outputDecimal;
    private Ratio reverse;


    public static Ratio ratioOf(final long input, final long output) {
        return ratioOf(BigInteger.valueOf(input), BigInteger.valueOf(output));
    }

    public static Ratio ratioOf(final BigInteger input, final BigInteger output) {
        final BigInteger cd = input.gcd(output);
        if (cd.compareTo(BigInteger.ONE) != 0) {
            return new Ratio(input.divide(cd), output.divide(cd));
        }
        return new Ratio(input, output);
    }

    private Ratio(final BigInteger input, final BigInteger output) {
        this.input = input;
        this.inputDouble = input.doubleValue();
        this.output = output;
        this.outputDouble = output.doubleValue();
    }


    public int convertToInt(final long input) {
        return this.convertToInt(BigInteger.valueOf(input));
    }

    public long convertToLong(final long input) {
        return this.convertToLong(BigInteger.valueOf(input));
    }


    public Number convert(final long input, final NumberType type) {
        return this.convert(BigInteger.valueOf(input), type);
    }

    public BigInteger convert(final double input) {
        return this.convertToDecimal(input).toBigInteger();
    }

    public int convertToInt(final double input) {
        return Numbers.toInt(this.convertToDouble(input));
    }

    public long convertToLong(final double input) {
        return Numbers.toLong(this.convertToDouble(input));
    }

    public double convertToDouble(final double input) {
        return convertDouble(input, this.inputDouble, this.outputDouble);
    }

    public BigDecimal convertToDecimal(final double input) {
        return this.convertToDecimal(new BigDecimal(input));
    }

    public Number convert(final double input, final NumberType type) {
        switch (type) {
            case INTEGER: {
                return this.convertToInt(input);
            }
            case LONG: {
                return this.convertToLong(input);
            }
            case BIG_INTEGER: {
                return this.convert(input);
            }
            case DOUBLE: {
                return this.convertToDouble(input);
            }
            case BIG_DECIMAL: {
                return this.convertToDecimal(input);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public BigInteger convert(final BigInteger input) {
        return convert(input, this.input, this.output);
    }

    public int convertToInt(final BigInteger input) {
        return Numbers.toInt(this.convert(input));
    }

    public long convertToLong(final BigInteger input) {
        return Numbers.toLong(this.convert(input));
    }

    public double convertToDouble(final BigInteger input) {
        return this.convert(input).doubleValue();
    }

    public BigDecimal convertToDecimal(final BigInteger input) {
        return this.convertToDecimal(new BigDecimal(input));
    }

    public Number convert(final BigInteger input, final NumberType type) {
        switch (type) {
            case INTEGER: {
                return this.convertToInt(input);
            }
            case LONG: {
                return this.convertToLong(input);
            }
            case BIG_INTEGER: {
                return this.convert(input);
            }
            case DOUBLE: {
                return this.convertToDouble(input);
            }
            case BIG_DECIMAL: {
                return this.convertToDecimal(input);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public BigInteger convert(final BigDecimal input) {
        return this.convertToDecimal(input).toBigInteger();
    }

    public int convertToInt(final BigDecimal input) {
        return Numbers.toInt(this.convertToDouble(input));
    }

    public long convertToLong(final BigDecimal input) {
        return Numbers.toLong(this.convertToDouble(input));
    }

    public double convertToDouble(final BigDecimal input) {
        return this.convertToDecimal(input).doubleValue();
    }

    public BigDecimal convertToDecimal(final BigDecimal input) {
        if (this.inputDecimal == null) {
            this.inputDecimal = new BigDecimal(this.input);
            this.outputDecimal = new BigDecimal(this.output);
        }
        return convertDecimal(input, this.inputDecimal, this.outputDecimal);
    }

    public Number convert(final BigDecimal input, final NumberType type) {
        switch (type) {
            case INTEGER: {
                return this.convertToInt(input);
            }
            case LONG: {
                return this.convertToLong(input);
            }
            case BIG_INTEGER: {
                return this.convert(input);
            }
            case DOUBLE: {
                return this.convertToDouble(input);
            }
            case BIG_DECIMAL: {
                return this.convertToDecimal(input);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }


    public long convertToLong(final Number input) {
        switch (NumberType.of(input)) {
            case INTEGER: {
                return this.convertToLong(input.intValue());
            }
            case LONG: {
                return this.convertToLong(input.longValue());
            }
            case BIG_INTEGER: {
                return this.convertToLong((BigInteger) input);
            }
            case DOUBLE: {
                return this.convertToLong(input.doubleValue());
            }
            case BIG_DECIMAL: {
                return this.convertToLong((BigDecimal) input);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }


    public Number convert(final Number input, final NumberType type) {
        switch (NumberType.of(input)) {
            case INTEGER: {
                return this.convert(input.intValue(), type);
            }
            case LONG: {
                return this.convert(input.longValue(), type);
            }
            case BIG_INTEGER: {
                return this.convert((BigInteger) input, type);
            }
            case DOUBLE: {
                return this.convert(input.doubleValue(), type);
            }
            case BIG_DECIMAL: {
                return this.convert((BigDecimal) input, type);
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    public Ratio reverse() {
        if (this.reverse == null) {
            this.reverse = new Ratio(this.output, this.input);
            this.reverse.reverse = this;
        }
        return this.reverse;
    }


    @Override
    public String toString() {
        return this.input + ":" + this.output;
    }

    private static BigInteger convert(final BigInteger value, final BigInteger ratioA, final BigInteger ratioB) {
        return value.multiply(ratioB).divide(ratioA);
    }

    private static double convertDouble(final double value, final double ratioA, final double ratioB) {
        return value * ratioB / ratioA;
    }

    private static BigDecimal convertDecimal(final BigDecimal value, final BigDecimal ratioA, final BigDecimal ratioB) {
        return value.multiply(ratioB).divide(ratioA, MathContext.DECIMAL64);
    }
}


