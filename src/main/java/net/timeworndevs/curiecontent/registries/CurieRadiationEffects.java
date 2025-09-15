package net.timeworndevs.curiecontent.registries;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.curieapi.radiation.RadiationEffect;
import net.timeworndevs.curiecontent.CurieContent;
import net.timeworndevs.curiecontent.effect.ExclusiveRadiationEffect;
import net.timeworndevs.curiecontent.util.RangedRadiationEntry;

import java.util.*;

import static net.timeworndevs.curiecontent.registries.CurieContentRadiationTypes.*;

public class CurieRadiationEffects {
    public static final Map<Identifier, RadiationEffect<?>> effects = new HashMap<>();

    private static final Random rand = new Random();
    public static <T extends RadiationEffect<?>> T register(T effect) {
        effects.put(effect.getIdentifier(), effect);
        return effect;
    }

    private static void tempModifier(String name, UUID uuid, PlayerEntity player, EntityAttribute attribute, float min, float max) {
        tempModifier(name, uuid, player, attribute, rand.nextFloat(min, max));
    }
    private static void tempModifier(String name, UUID uuid, PlayerEntity player, EntityAttribute attribute, float value) {
        EntityAttributeInstance instance = player.getAttributeInstance(attribute);
        EntityAttributeModifier modifier = new EntityAttributeModifier(uuid, name, value, EntityAttributeModifier.Operation.ADDITION);
        if (instance != null && !instance.hasModifier(modifier)) {
            instance.addTemporaryModifier(modifier);
        }
    }

    public static final ExclusiveRadiationEffect<RangedRadiationEntry> HEALTH_MODIFIER = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "health_modifier"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.5f, 0.6f).add(BETA, 0.2f, 0.3f).add(GAMMA, 0.3f, 0.4f).build(),
            (player, list) -> tempModifier("health_modifier", UUID.fromString("3999f1d6-6b7e-4c6d-8bc0-46025f73dd84"), player, EntityAttributes.GENERIC_MAX_HEALTH, -5.0f, 5.0f)));

    public static final ExclusiveRadiationEffect<RangedRadiationEntry> ARMOR_INCREASE = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "armor_increase"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.5f, 0.6f).add(BETA, 0.4f, 0.5f).add(GAMMA, 0.1f, 0.2f).build(),
            (player, list) -> tempModifier("armor_increase", UUID.fromString("e8cb576b-477a-42d9-8391-8b10cbdff72a"), player, EntityAttributes.GENERIC_ARMOR, 0.0f,5.0f)));

    public static final ExclusiveRadiationEffect<RangedRadiationEntry> ATTACK_POWER = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "attack_power"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.0f, 0.1f).add(BETA, 0.3f, 0.4f).add(NEUTRON, 0.3f, 0.4f).build(),
            (player, list) -> {
                tempModifier("attack_power", UUID.fromString("e7c35d93-bec3-40a7-97b8-19560b3776b5"), player, EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.0f, 5.0f);
                tempModifier("attack_power", UUID.fromString("b0721eb0-2d62-4977-8e46-69d0c2a530ec"), player, EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0f, 5.0f);
            }));

    public static final ExclusiveRadiationEffect<RangedRadiationEntry> ATTACK_SPEED = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "attack_speed"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.0f, 0.1f).add(BETA, 0.3f, 0.4f).add(GAMMA, 0.3f, 0.4f).build(),
            (player, list) -> tempModifier("attack_speed", UUID.fromString("d164291f-9615-4109-8c89-b98a55c702d3"), player, EntityAttributes.GENERIC_ATTACK_SPEED, 0.0f, 5.0f)));

    public static final RadiationEffect<RangedRadiationEntry> KNOCKBACK_RESISTANCE = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "knockback_resistance"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.3f, 0.4f).add(BETA, 0.0f, 0.1f).add(NEUTRON, 0.5f, 0.6f).build(),
            (player, list) -> tempModifier("knockback_resistance", UUID.fromString("ef619ff4-bc1b-4bd3-99d0-f59ec14291db"), player, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.0f, 5.0f)));

    public static final RadiationEffect<RangedRadiationEntry> MOVEMENT_SPEED = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "movement_speed"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.2f, 0.3f).add(BETA, 0.5f, 0.7f).add(GAMMA, 0.2f, 0.3f).add(NEUTRON, 0.0f, 0.1f).build(),
            (player, list) -> tempModifier("movement_speed", UUID.fromString("992e3f6e-5b73-44a5-beb2-d7c9c8e8ec37"), player, EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0f, 5.0f)));

    public static final RadiationEffect<RangedRadiationEntry> REACH = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "reach"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.2f, 0.3f).add(BETA, 0.5f, 0.7f).add(GAMMA, 0.2f, 0.3f).add(NEUTRON, 0.0f, 0.1f).build(),
            (player, list) -> tempModifier("reach", UUID.fromString("992e3f6e-5b73-44a5-beb2-d7c9c8e8ec37"), player, ReachEntityAttributes.REACH, 0.0f, 5.0f)));

    public static final RadiationEffect<RangedRadiationEntry> STEP_HEIGHT = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "step_height"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.2f, 0.3f).add(BETA, 0.5f, 0.7f).add(GAMMA, 0.2f, 0.3f).add(NEUTRON, 0.0f, 0.1f).build(),
            (player, list) -> player.setStepHeight(0.2f)));

    public static final RadiationEffect<RangedRadiationEntry> JUMP_POWER = register(new ExclusiveRadiationEffect<>(
            Identifier.of(CurieContent.MOD_ID, "jump_power"),
            new RangedRadiationEntry.Builder().add(ALPHA, 0.2f, 0.3f).add(BETA, 0.5f, 0.7f).add(GAMMA, 0.2f, 0.3f).add(NEUTRON, 0.0f, 0.1f).build(),
            (player, list) -> player.jump())); // Oh this is so temp I swear

    public static void registerRadiationEffects() {

    }
}
