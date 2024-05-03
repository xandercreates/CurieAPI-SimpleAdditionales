package net.timeworndevs.quantumadds.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;

public class ModEffects {
    public static StatusEffect SKINFALLOFF;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Quantum.MOD_ID, name),
                new SkinFalloffEffect(StatusEffectCategory.HARMFUL, 3245231));

    }

    public static void registerEffects() {
        SKINFALLOFF = registerStatusEffect("skin_falloff");
    }
}
