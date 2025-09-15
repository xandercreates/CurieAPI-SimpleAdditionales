package net.timeworndevs.curiecontent.effect.radiation;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.curieapi.CurieAPI;
import net.timeworndevs.curieapi.radiation.RadiationEffect;
import net.timeworndevs.curieapi.radiation.RadiationEntry;
import net.timeworndevs.curiecontent.CurieContent;
import net.timeworndevs.curiecontent.effect.ExclusiveRadiationEffect;
import net.timeworndevs.curiecontent.util.RangedRadiationEntry;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class HealthModifier extends ExclusiveRadiationEffect<RangedRadiationEntry> {
    public static final UUID uuid = UUID.fromString("3999f1d6-6b7e-4c6d-8bc0-46025f73dd84");
    private static final Identifier id = new Identifier(CurieContent.MOD_ID, "health_modifier");
    private static final BiConsumer<ServerPlayerEntity, RadiationEntry> consumer = (player, entry) -> {
        EntityAttributeInstance instance = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);
        EntityAttributeModifier modifier = new EntityAttributeModifier(uuid, "curie-content:armor_reduction", rand.nextFloat(-5, 5), EntityAttributeModifier.Operation.ADDITION);
        if (instance != null && !instance.hasModifier(modifier)) {
            instance.addTemporaryModifier(modifier);
        }
    };

    public HealthModifier(RangedRadiationEntry entry, List<RadiationEffect<?>> exclusions) {
        super(id, entry, consumer, exclusions);
    }
}
