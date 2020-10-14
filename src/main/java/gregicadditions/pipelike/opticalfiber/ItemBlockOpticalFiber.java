package gregicadditions.pipelike.opticalfiber;

import gregtech.api.GTValues;
import gregtech.api.pipenet.block.ItemBlockPipe;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockOpticalFiber extends ItemBlockPipe<OpticalFiberSize, OpticalFiberProperties> {

    public ItemBlockOpticalFiber(BlockOpticalFiber block) {
        super(block);
    }

    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        OpticalFiberSize pipeType = blockPipe.getItemPipeType(stack);
        OrePrefix orePrefix = pipeType.getOrePrefix();
        String specfiedUnlocalized = "item.oreprefix." + orePrefix.name();
        return I18n.format(specfiedUnlocalized);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        OpticalFiberProperties opticalFiberProperties = blockPipe.createItemProperties(stack);
        String voltageName = GTValues.VN[GTUtility.getTierByVoltage(opticalFiberProperties.qubit)];
        tooltip.add(I18n.format("gtaddition.optical_fiber.qubit", opticalFiberProperties.qubit, voltageName));
        tooltip.add(I18n.format("gtaddition.optical_fiber.parallel", opticalFiberProperties.parallel));
    }
}
