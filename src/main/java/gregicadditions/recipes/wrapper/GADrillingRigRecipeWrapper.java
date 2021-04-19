package gregicadditions.recipes.wrapper;

import com.google.common.collect.Lists;
import gregicadditions.worldgen.PumpjackHandler;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class GADrillingRigRecipeWrapper implements IRecipeWrapper {

    private final PumpjackHandler.ReservoirType reservoirType;
    private final int weight;
    private final Map<Area, List<String>> tooltips = new HashMap<>();
    private final int WIDTH = 176;
    private boolean tooltipSet = false;

    public GADrillingRigRecipeWrapper(PumpjackHandler.ReservoirType reservoirType, int weight) {
        this.reservoirType = reservoirType;
        this.weight = weight;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setOutput(VanillaTypes.FLUID, new FluidStack(reservoirType.getFluid(), 1000));
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.size", formatFluidAmount(reservoirType.minSize)+ " - " + formatFluidAmount(reservoirType.maxSize)), 0, 42, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.replenish_rate", reservoirType.replenishRate), 0, 51, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gtadditions.recipe.drilling_rig.weight", weight), 0, 60, 0x111111);

        int x = 6, y = 21;

        if(reservoirType.dimensionWhitelist.size() > 0) {
            drawText(minecraft, "gtadditions.recipe.drilling_rig.dimension", x, y);
            drawText(minecraft, "gtadditions.recipe.drilling_rig.whitelist", x, y+9);
            addDimensionTooltip(reservoirType.dimensionWhitelist);
        } else if(reservoirType.dimensionBlacklist.size() > 0) {
            drawText(minecraft, "gtadditions.recipe.drilling_rig.dimension", x, y);
            drawText(minecraft, "gtadditions.recipe.drilling_rig.blacklist", x, y+9);
            addDimensionTooltip(reservoirType.dimensionBlacklist);
        } else {
            drawText(minecraft, "gtadditions.recipe.drilling_rig.no_dimension", x, y+4);
        }

        if(reservoirType.biomeWhitelist.size() > 0) {
            drawText(minecraft, "gtadditions.recipe.drilling_rig.biome", WIDTH / 2 + x, y);
            drawText(minecraft, "gtadditions.recipe.drilling_rig.whitelist", WIDTH / 2 + x, y+9);
            addBiomeTooltip(reservoirType.biomeWhitelist);
        } else if(reservoirType.biomeBlacklist.size() > 0) {
            drawText(minecraft, "gtadditions.recipe.drilling_rig.biome", WIDTH / 2 + x, y);
            drawText(minecraft, "gtadditions.recipe.drilling_rig.blacklist", WIDTH / 2 + x, y+9);
            addBiomeTooltip(reservoirType.biomeBlacklist);
        } else {
            drawText(minecraft, "gtadditions.recipe.drilling_rig.no_biome", WIDTH / 2 + x, y+4);
        }
        tooltipSet = true;
    }

    @Override
    @Nonnull
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        for(Map.Entry<Area, List<String>> entry : tooltips.entrySet()) {
            if(entry.getKey().isInArea(mouseX, mouseY)) {
                return entry.getValue();
            }
        }
        return Collections.emptyList();
    }

    public void drawText(Minecraft minecraft, String langKey, int x, int y) {
        drawText(minecraft, langKey, x, y, 0x111111);
    }

    public void drawText(Minecraft minecraft, String langKey, int x, int y, int color) {
        minecraft.fontRenderer.drawString(I18n.format(langKey), x, y, color);
    }

    public void addBiomeTooltip(List<Integer> biomes) {
        if(tooltipSet) return;
        tooltips.put(new Area(WIDTH / 2 + 2, 18, WIDTH - 2, 34), getNamesFor(biomes, true));
    }

    public void addDimensionTooltip(List<Integer> biomes) {
        if(tooltipSet) return;
        tooltips.put(new Area( 2, 18, WIDTH / 2 - 2, 34), getNamesFor(biomes, false));
    }

    public String getBiomeName(int id) {
        Biome b = Biome.getBiome(id);
        if(b == null) return "" + id;
        return b.getBiomeName();
    }

    public String getDimName(int id) {
        if(!DimensionManager.isDimensionRegistered(id)) return "" + id;
        DimensionType dim = DimensionManager.getProviderType(id);
        String[] parts = dim.getName().split("_");
        return Arrays.stream(parts).map(part -> {
            String s = Character.toString(part.charAt(0));
            return part.replaceFirst(s, s.toUpperCase());
        }).collect(Collectors.joining(" "));
    }

    public List<String> getNamesFor(List<Integer> ids, boolean isBiome) {
        List<String> names = Lists.newArrayList();
        ids.forEach(id -> {
            String name;
            if(isBiome) {
                name = getBiomeName(id);
            } else {
                name = getDimName(id);
            }
            if(!name.trim().equals("")) {
                names.add(name);
            }
        });
        return names;
    }

    public String formatFluidAmount(int amount) {
        DecimalFormat df = new DecimalFormat("#,#");
        df.setGroupingSize(3);
        df.setParseIntegerOnly(true);
        return amount >= 10000 ? df.format(amount / 1000) + "B" : df.format(amount) + "mB";
    }

    public static class Area {
        int x1, y1, x2, y2;
        public Area(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean isInArea(int x, int y) {
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }
    }
}
