package net.timeworndevs.quantumadds.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.DynamicRegistryManager;

public class SkinFalloffEffect extends StatusEffect {

    public SkinFalloffEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {

            if (entity.isUsingItem() && Math.random()<0.02) {
                entity.stopUsingItem();
                entity.damage(new DamageSources(DynamicRegistryManager.EMPTY).cramming(), 1f);
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
