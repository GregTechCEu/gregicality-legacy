package gregicadditions.armor;

import gregicadditions.GAConfig;
import gregicadditions.item.GAMetaItems;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

import javax.annotation.Nonnull;

public class NanoMuscleSuite extends NightvisionGoggles {

    public NanoMuscleSuite(EntityEquipmentSlot slot, int energyPerUse, int capacity) {
        super(energyPerUse, capacity, GAConfig.equipment.nanoSuit.voltageTier, slot);
    }

    public NanoMuscleSuite(EntityEquipmentSlot slot, int energyPerUse, int capacity, int tier) {
        super(energyPerUse, capacity, tier, slot);
    }

    public boolean handleUnblockableDamage(EntityLivingBase entity, @Nonnull ItemStack armor, DamageSource source, double damage, EntityEquipmentSlot equipmentSlot) {
        if (source == DamageSource.FALL) return true;
        return false;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, EntityEquipmentSlot equipmentSlot) {
        IElectricItem container = armor.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        int damageLimit = Integer.MAX_VALUE;
        if (source == DamageSource.FALL && this.getEquipmentSlot(armor) == EntityEquipmentSlot.FEET) {
            if (energyPerUse > 0) {
                damageLimit = (int) Math.min(damageLimit, 25.0 * container.getCharge() / energyPerUse);
            }
            return new ArmorProperties(10, (damage < 8.0) ? 1.0 : 0.875, damageLimit);
        }
        return super.getProperties(player, armor, source, damage, equipmentSlot);
    }

    @Override
    public EntityEquipmentSlot getEquipmentSlot(ItemStack itemStack) {
        return SLOT;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack itemStack, DamageSource source, int damage, EntityEquipmentSlot equipmentSlot) {
        IElectricItem item = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        item.discharge(energyPerUse * damage, item.getTier(), true, false, false);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        ItemStack currentChest = Minecraft.getMinecraft().player.inventory.armorItemInSlot(EntityEquipmentSlot.CHEST.getIndex());
        ItemStack advancedChest = GAMetaItems.ADVANCED_NANO_MUSCLE_CHESTPLATE.getStackForm();
        String armorTexture = "nano_muscule_suite";
        if (advancedChest.isItemEqual(currentChest)) armorTexture = "advanced_nano_muscle_suite";
        return SLOT != EntityEquipmentSlot.LEGS ?
                String.format("gtadditions:textures/armor/%s_1.png", armorTexture) :
                String.format("gtadditions:textures/armor/%s_2.png", armorTexture);
    }

    @Override
    public double getDamageAbsorption() {
        return 0.9D;
    }
}
