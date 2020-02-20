package gregicadditions.bees;

import java.awt.Color;
import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum GTCombs implements IStringSerializable {
	LIGNITE(new Color(0x58300b), new Color(0x906237)),
	COAL(new Color(0x525252), new Color(0x666666)),
	RUBBERY(new Color(0xdcc289), new Color(0x277b4e)),
	OIL(new Color(0x333333), new Color(0x414141)),
	STONE(new Color(0x999999), new Color(0x6e6e6e)),
	CERTUS(new Color(0xbbeeff), new Color(0x4bb2d8)),
	REDSTONE(new Color(0xd11919), new Color(0x7d0f0f)),
	LAPIS(new Color(0x476cda), new Color(0x153db3)),
	RUBY(new Color(0xcc0052), new Color(0xc5004f)),
	SAPPHIRE(new Color(0x00248f), new Color(0x002caf)),
	DIAMOND(new Color(0xa3cccc), new Color(0xafdbdb)),
	OLIVINE(new Color(0xccffcc), new Color(0x248f24)),
	EMERALD(new Color(0x2eb82e), new Color(0x1f7b1f)),
	SLAG(new Color(0x58300b), new Color(0xb6b6b6)),
	COPPON(new Color(0xe65c00), new Color(0xdb5800)),
	TINE(new Color(0xdddddd), new Color(0xb6b6b6)),
	PLUMBIA(new Color(0xa3a3cc), new Color(0x585883)),
	FERRU(new Color(0xde9c59), new Color(0xbb7d3d)),
	STEELDUST(new Color(0x999999), new Color(0x6e6e6e)),
	NICKELDUST(new Color(0x9d9dbd), new Color(0x727295)),
	GALVANIA(new Color(0xf2e1f2), new Color(0xcebfce)),
	ARGENTIA(new Color(0xcecede), new Color(0xa7a7b8)),
	AURELIA(new Color(0xcfa600), new Color(0xc59e00)),
	BAUXIA(new Color(0xd6d6ff), new Color(0x00769e)),
	PYROLUSIUM(new Color(0xaaaaaa), new Color(0xb7b7b7)),
	TITANIUM(new Color(0xdbb8ff), new Color(0xaf83db)),
	CHROMIUM(new Color(0xf2c3f2), new Color(0xca8aca)),
	SCHEELINIUM(new Color(0x050508), new Color(0x54545e)),
	PLATINA(new Color(0xffffcc), new Color(0xc5c5c5)),
	QUANTARIA(new Color(0xd1d1e0), new Color(0xbbbbbb)),
	URANIA(new Color(0x169e16), new Color(0x159615)),
	PLUTONIUM(new Color(0x6b8f00), new Color(0x2c4f2c)),
	STARGATIUM(new Color(0x002400), new Color(0x002c00));

	public static final GTCombs[] VALUES = values();

	public final String name;
	public final int primaryColor;
	public final int secondaryColor;

	public static GTCombItem combItem = new GTCombItem();

	GTCombs(Color primary, Color secondary) {
		this.name = toString().toLowerCase(Locale.ENGLISH);
		this.primaryColor = primary.getRGB();
		this.secondaryColor = secondary.getRGB();
	}

	@Override
	public String getName() {
		return name;
	}

	public static GTCombs get(int meta) {
		if (meta >= VALUES.length) {
			meta = 0;
		}
		return VALUES[meta];
	}
}