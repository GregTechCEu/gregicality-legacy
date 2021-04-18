package gregicadditions.materials;

import com.google.common.collect.ImmutableList;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.SmallDigits;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.util.text.TextFormatting.*;

public abstract class SimpleMaterial {

    public String name;
    public int rgb;
    protected String chemicalFormula;
    public boolean fancy = false;

    protected String calculateChemicalFormula(ImmutableList<MaterialStack> materialComponents) {
        if (!materialComponents.isEmpty()) {
            StringBuilder components = new StringBuilder();
            for (MaterialStack component : materialComponents)
                components.append(component.toString());
            return components.toString();
        }
        return "";
    }

    protected String calculateChemicalFormula(String unformattedFormula) {
        StringBuilder sb = new StringBuilder();
        if (unformattedFormula != null && !unformattedFormula.isEmpty()) {
            for (char c : unformattedFormula.toCharArray()) {
                if (Character.isDigit(c))
                    sb.append(SmallDigits.toSmallDownNumbers(Character.toString(c)));
                else
                    sb.append(c);
            }
        }
        return sb.toString(); // returns "" if no formula, like other method
    }

    public String getFormula() {
        return fancy ? makeFancy(chemicalFormula) : chemicalFormula;
    }

    private static final TextFormatting[] fanciness = new TextFormatting[] { RED, GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE };

    private static String makeFancy(String input) {
        return fancyTest(input, fanciness, 80.0, 1);
    }

    private static String fancyTest(String input, TextFormatting[] colors, double delay, int posstep) {
        StringBuilder sb = new StringBuilder();

        int offset = (int) Math.floor(MinecraftServer.getCurrentTimeMillis() / delay) % colors.length;
        String format = null;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (!(c == '&' || c == '\u00a7')) {
                int col = ((i * posstep) + colors.length - offset) % colors.length;
                sb.append(colors[col].toString());
                if (format != null)
                    sb.append(format);
                sb.append(c);
            } else format = input.charAt(++i) == 'r' ? null : format == null ? "" + c + input.charAt(i) : format + c + input.charAt(i);
        }
        return sb.toString();
    }
}
