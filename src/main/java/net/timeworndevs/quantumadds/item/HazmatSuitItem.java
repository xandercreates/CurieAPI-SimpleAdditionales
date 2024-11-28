package net.timeworndevs.quantumadds.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.QuantumClient;
import net.timeworndevs.quantumadds.item.Armors.FullArmorModel;
import org.jetbrains.annotations.NotNull;

public class HazmatSuitItem extends ArmorItem {
    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;

    private final String texture_feet;
    private final String texture_rest;
    public HazmatSuitItem(ArmorMaterial material, Type type, Settings settings, String[] textures) {
        super(material, type, settings);
        texture_feet = textures[0];
        texture_rest = textures[1];
    }


    @Override
    public ItemStack getDefaultStack() {
        return new ItemStack(this);
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canRepair(ItemStack itemStack_1, ItemStack itemStack_2) {
        return false;
    }

    @Environment(EnvType.CLIENT)
    protected BipedEntityModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot, Item item) {

        var models = MinecraftClient.getInstance().getEntityModelLoader();

        var feet = models.getModelPart(QuantumClient.HAZMAT_A_FEET_LAYER);
        var root = models.getModelPart(QuantumClient.HAZMAT_A_MAIN_LAYER);
        if (slot == EquipmentSlot.FEET) {
            return new FullArmorModel(feet, slot);
        } else {
            return new FullArmorModel(root, slot);
        }
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getArmorModel(Item item) {
        if (model == null) {
            model = provideArmorModelForSlot(getSlotType(), item);
        }
        return model;
    }

    @NotNull
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        if (slot == EquipmentSlot.FEET) {
            return new Identifier(Quantum.MOD_ID, this.texture_feet);
        } else {
            return new Identifier(Quantum.MOD_ID, this.texture_rest);
        }
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
}