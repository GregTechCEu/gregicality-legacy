package gregicadditions.pipelike.opticalfiber;

import gregtech.api.GTValues;
import gregtech.api.pipenet.block.material.ItemBlockMaterialPipe;
import gregtech.api.util.GTUtility;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockOpticalFiber extends ItemBlockMaterialPipe<OpticalFiberSize, OpticalFiberProperties> {

    public ItemBlockOpticalFiber(BlockOpticalFiber block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        OpticalFiberProperties opticalFiberProperties = blockPipe.createItemProperties(stack);
        String voltageName = GTValues.VN[GTUtility.getTierByVoltage(opticalFiberProperties.voltage)];
        tooltip.add(I18n.format("gregtech.cable.voltage", opticalFiberProperties.voltage, voltageName));
        tooltip.add(I18n.format("gregtech.cable.amperage", opticalFiberProperties.amperage));
    }
}
