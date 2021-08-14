package gregicadditions.item;

import gregicadditions.GAConfig;
import gregtech.api.GTValues;
import gregtech.common.blocks.VariantBlock;
import gregtech.common.blocks.VariantItemBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class CellCasing extends VariantBlock<CellCasing.CellType> {
    public CellCasing() {
        super(Material.IRON);
        setTranslationKey("cell_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CellType.CELL_LUV));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> lines, ITooltipFlag tooltipFlag) {
        super.addInformation(itemStack, worldIn, lines, tooltipFlag);

        VariantItemBlock itemBlock = (VariantItemBlock<CellCasing.CellType, CellCasing>) itemStack.getItem();
        IBlockState stackState = itemBlock.getBlock().getStateFromMeta(itemBlock.getMetadata(itemStack.getItemDamage()));
        CellCasing.CellType coilType = getState(stackState);

        lines.add(I18n.format("tile.cell_casing.tooltip.1"));
        lines.add(I18n.format("tile.cell_casing.tooltip.2", coilType.getStorage()));
        lines.add(I18n.format("tile.cell_casing.tooltip.3", GTValues.VN[coilType.getTier()]));

    }


    public enum CellType implements IStringSerializable {

        CELL_HV("cell_hv", GAConfig.multis.batteryTower.baseCellCapacity, GTValues.HV),
        CELL_EV("cell_ev", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 1)), GTValues.EV),
        CELL_IV("cell_iv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 2)), GTValues.IV),
        CELL_LUV("cell_luv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 3)), GTValues.LuV),
        CELL_ZPM("cell_zpm", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 4)), GTValues.ZPM),
        CELL_UV("cell_uv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 5)), GTValues.UV),
        CELL_UHV("cell_uhv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 6)), GTValues.UHV),
        CELL_UEV("cell_uev", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 7)), GTValues.UEV),
        CELL_UIV("cell_uiv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 8)), GTValues.UIV),
        CELL_UMV("cell_umv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 9)), GTValues.UMV),
        CELL_UXV("cell_uxv", (long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 10)), GTValues.UXV),
        CELL_MAX("cell_max", Math.min((long) (GAConfig.multis.batteryTower.baseCellCapacity * Math.pow(4, 11)), Long.MAX_VALUE), GTValues.MAX);

        private final String name;
        private final long storage;
        private final int tier;

        CellType(String name, long storage, int tier) {
            this.name = name;
            this.storage = storage;
            this.tier = tier;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public long getStorage() {
            return storage;
        }

        public int getTier() {
            return tier;
        }

    }
}
