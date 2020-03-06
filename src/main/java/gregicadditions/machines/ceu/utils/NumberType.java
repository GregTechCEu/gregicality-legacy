package gregicadditions.machines.ceu.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public enum NumberType
{
	INTEGER,
	LONG,
	BIG_INTEGER,
	DOUBLE,
	BIG_DECIMAL;

	public NumberType bigger(final NumberType type) {
		return (type.ordinal() > this.ordinal()) ? type : this;
	}

	public boolean isInteger() {
		return this != NumberType.DOUBLE && this != NumberType.BIG_DECIMAL;
	}

	public static NumberType of(final Number n) {
		if (n instanceof BigInteger) {
			return NumberType.BIG_INTEGER;
		}
		if (n instanceof BigDecimal) {
			return NumberType.BIG_DECIMAL;
		}
		if (n instanceof Double) {
			return NumberType.DOUBLE;
		}
		if (n instanceof Long) {
			return NumberType.LONG;
		}
		return NumberType.INTEGER;
	}
}
