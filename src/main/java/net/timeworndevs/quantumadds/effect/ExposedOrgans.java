package net.timeworndevs.quantumadds.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.timeworndevs.quantum.radiation.RadiationData;
import net.timeworndevs.quantum.radiation.RadiationType;
import net.timeworndevs.quantum.util.IEntityDataSaver;

import java.util.ArrayList;
import java.util.Random;

import static net.timeworndevs.quantum.radiation.RadiationType.RADIATION_TYPES;

public class ExposedOrgans extends StatusEffect {

    protected ExposedOrgans(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {
            if (entity.isPlayer()) {
                Random rand = new Random();
                ArrayList<RadiationType> radiations = new ArrayList<>(RADIATION_TYPES.values());
                RadiationData.addRad((IEntityDataSaver) entity, radiations.get(rand.nextInt(radiations.size())), (int) (Math.random()*10*amplifier));
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
