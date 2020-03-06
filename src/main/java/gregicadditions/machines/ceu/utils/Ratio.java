package gregicadditions.machines.ceu.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Property;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Ratio {
	public static final Pattern PATTERN;
	public static final Ratio RATIO_1_1;
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
		if (input.compareTo(output) == 0) {
			return Ratio.RATIO_1_1;
		}
		final BigInteger cd = input.gcd(output);
		if (cd.compareTo(BigInteger.ONE) != 0) {
			return new Ratio(input.divide(cd), output.divide(cd));
		}
		return new Ratio(input, output);
	}

	private Ratio() {
		this(BigInteger.ONE, BigInteger.ONE);
		this.reverse = this;
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
		return (this == Ratio.RATIO_1_1) ? input : convertDouble(input, this.inputDouble, this.outputDouble);
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
		return (this == Ratio.RATIO_1_1) ? input : convert(input, this.input, this.output);
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
		if (this == Ratio.RATIO_1_1) {
			return input;
		}
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

	public void serialize(final NBTTagCompound nbt) {
		nbt.setByteArray("inputRatio", this.input.toByteArray());
		nbt.setByteArray("outputRatio", this.output.toByteArray());
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

	static {
		PATTERN = Pattern.compile("\\s*(\\d+)\\s*:\\s*(\\d+)\\s*");
		RATIO_1_1 = new Ratio();
	}
}


