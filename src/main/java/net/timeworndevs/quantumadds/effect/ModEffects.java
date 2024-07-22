package net.timeworndevs.quantumadds.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;

public class ModEffects {
    public static StatusEffect SKINFALLOFF;
    public static StatusEffect EXPOSEDORGANS;
    public static StatusEffect RADIATIONNEUTRALISATION;

    public static StatusEffect registerSkinFalloff(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Quantum.MOD_ID, name),
                new SkinFalloffEffect(StatusEffectCategory.HARMFUL, 5245231));
    }
    public static StatusEffect registerExposedOrgans(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Quantum.MOD_ID, name),
                new ExposedOrgans(StatusEffectCategory.HARMFUL, 7633476));
    }
    public static StatusEffect registerRadiationNeutralisation(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Quantum.MOD_ID, name),
                new RadiationNeutralisation(StatusEffectCategory.BENEFICIAL, 3245231));
    }

    public static void registerEffects() {
        SKINFALLOFF = registerSkinFalloff("skin_falloff");
        EXPOSEDORGANS = registerExposedOrgans("exposed_organs");
        RADIATIONNEUTRALISATION = registerRadiationNeutralisation("radiation_neutralisation");
    }
}