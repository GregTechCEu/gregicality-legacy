package gregicadditions.machines.energyconverter.utils;


import java.math.BigDecimal;
import java.math.BigInteger;

public final class Numbers {
	private static final BigInteger INT_LIMIT;
	private static final BigInteger LONG_LIMIT;

	private Numbers() {
	}

	public static int addWithOverflowCheck(final int x, final int y) {
		final int r = x + y;
		return (((x ^ r) & (y ^ r)) < 0) ? ((x < 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE) : (x + y);
	}

	public static long addWithOverflowCheck(final long x, final long y) {
		final long r = x + y;
		return (((x ^ r) & (y ^ r)) < 0L) ? ((x < 0L) ? Long.MIN_VALUE : Long.MAX_VALUE) : (x + y);
	}

	public static Number addWithOverflowCheck(final Number x, final Number y) {
		switch (NumberType.of(x).bigger(NumberType.of(y))) {
			case INTEGER: {
				return addWithOverflowCheck(x.intValue(), y.intValue());
			}
			case LONG: {
				return addWithOverflowCheck(x.longValue(), y.longValue());
			}
			case BIG_INTEGER: {
				return ((BigInteger) x).add((BigInteger) y);
			}
			case DOUBLE: {
				return x.doubleValue() + y.doubleValue();
			}
			case BIG_DECIMAL: {
				return ((BigDecimal) x).add((BigDecimal) y);
			}
			default: {
				throw new IllegalStateException();
			}
		}
	}

	public static Number sub(final Number a, final Number b) {
		switch (NumberType.of(a).bigger(NumberType.of(b))) {
			case INTEGER: {
				return a.intValue() - b.intValue();
			}
			case LONG: {
				return a.longValue() - b.longValue();
			}
			case BIG_INTEGER: {
				return toBigInteger(a).subtract(toBigInteger(b));
			}
			case DOUBLE: {
				return a.doubleValue() - b.doubleValue();
			}
			case BIG_DECIMAL: {
				return toBigDecimal(a).subtract(toBigDecimal(b));
			}
			default: {
				throw new IllegalStateException();
			}
		}
	}

	public static long clamp(final long value, final long min, final long max) {
		return (value >= max) ? max : ((value <= min) ? min : value);
	}

	public static BigInteger toBigInteger(final Number n) {
		if (n instanceof BigDecimal || n instanceof Double) {
			throw new IllegalArgumentException("Couldn't cast decimal number format to BigInteger");
		}
		if (n instanceof BigInteger) {
			return (BigInteger) n;
		}
		return BigInteger.valueOf(n.longValue());
	}

	public static BigDecimal toBigDecimal(final Number n) {
		if (n instanceof BigDecimal) {
			return (BigDecimal) n;
		}
		if (n instanceof BigInteger) {
			return new BigDecimal((BigInteger) n);
		}
		return new BigDecimal(n.toString());
	}

	public static int toInt(final BigInteger i) {
		return (i.compareTo(Numbers.INT_LIMIT) > 0) ? Integer.MAX_VALUE : i.intValue();
	}

	public static long toLong(final BigInteger i) {
		return (i.compareTo(Numbers.LONG_LIMIT) > 0) ? Long.MAX_VALUE : i.longValue();
	}

	public static int toInt(final double d) {
		return (d > 2.147483647E9) ? Integer.MAX_VALUE : ((int) d);
	}

	public static long toLong(final double d) {
		return (d > 9.223372036854776E18) ? Long.MAX_VALUE : ((long) d);
	}

	static {
		INT_LIMIT = BigInteger.valueOf(2147483647L);
		LONG_LIMIT = BigInteger.valueOf(Long.MAX_VALUE);
	}
}

