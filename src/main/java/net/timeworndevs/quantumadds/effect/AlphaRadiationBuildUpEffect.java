package net.timeworndevs.quantumadds.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.DynamicRegistryManager;

public class AlphaRadiationBuildUpEffect extends StatusEffect {

    protected AlphaRadiationBuildUpEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {

            entity.kill();
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
