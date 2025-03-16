package net.timeworndevs.quantumadds.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;
import net.timeworndevs.quantumadds.util.RadiationData;

public class ExposedOrgans extends StatusEffect {

    protected ExposedOrgans(StatusEffectCategory category, int color) {
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
                RadiationData.addRad((IEntityDataSaver) entity, (String) Quantum.new_radiation_types.keySet().toArray()[(int) (Math.random()* Quantum.new_radiation_types.keySet().size()-0.01)], (int) (Math.random()*10*amplifier));
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
