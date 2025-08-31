package net.timeworndevs.curiecontent.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class Degeneration extends StatusEffect {

    protected Degeneration(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    private static float health = -1;
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (health == -1) {
                    health = player.getHealth();
                }
                health = Math.min(health, player.getHealth());
                player.setHealth(health);
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
