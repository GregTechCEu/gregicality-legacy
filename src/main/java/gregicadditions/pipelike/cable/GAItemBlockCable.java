package gregicadditions.pipelike.cable;

import gregicadditions.GAUtility;
import gregicadditions.GAValues;
import gregtech.api.unification.material.properties.WireProperties;
import gregtech.common.pipelike.cable.BlockCable;
import gregtech.common.pipelike.cable.ItemBlockCable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class GAItemBlockCable extends ItemBlockCable {

    public GAItemBlockCable(BlockCable block) {
        super(block);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        WireProperties wireProperties = blockPipe.createItemProperties(stack);
        String voltageName = GAValues.VN[GAUtility.getTierByVoltage(wireProperties.voltage)];
        tooltip.add(I18n.format("gregtech.cable.voltage", wireProperties.voltage, voltageName));
        tooltip.add(I18n.format("gregtech.cable.amperage", wireProperties.amperage));
        tooltip.add(I18n.format("gregtech.cable.loss_per_block", wireProperties.lossPerBlock));
    }
}
