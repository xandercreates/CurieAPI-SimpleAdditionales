package net.timeworndevs.quantumadds.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.timeworndevs.quantum.radiation.RadiationData;
import net.timeworndevs.quantum.radiation.RadiationType;
import net.timeworndevs.quantum.util.IEntityDataSaver;

import java.util.Random;

import static net.timeworndevs.quantum.radiation.RadiationType.RADIATION_TYPES;

public class RadiationNeutralisation extends StatusEffect {
    private static final Random random = new Random();
    protected RadiationNeutralisation(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // In our case, we just make it return true so that it applies the status effect every tick.
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {
            if (entity.isPlayer()) {
                for (RadiationType type : RADIATION_TYPES.values()) {
                    RadiationData.delRad((IEntityDataSaver) entity, type, random.nextInt(25) * amplifier);
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
