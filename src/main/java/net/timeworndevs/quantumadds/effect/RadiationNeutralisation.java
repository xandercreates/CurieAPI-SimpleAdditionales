package net.timeworndevs.quantumadds.effect;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.radiation.RadiationType;
import net.timeworndevs.quantumadds.registries.QuantumRadiationTypes;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;
import net.timeworndevs.quantumadds.radiation.RadiationData;

public class RadiationNeutralisation extends StatusEffect {

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
                for (RadiationType type : QuantumRadiationTypes.RADIATION_TYPES.values()) {
                    RadiationData.delRad((IEntityDataSaver) entity, type, (int) (Math.random() * 10* amplifier));
                }
            }
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
