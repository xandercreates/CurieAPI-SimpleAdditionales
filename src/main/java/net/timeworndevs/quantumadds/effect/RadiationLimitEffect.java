package net.timeworndevs.quantumadds.effect;

import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.quantum.radiation.RadiationEffect;
import net.timeworndevs.quantum.radiation.RadiationType;

import java.util.Map;
import java.util.function.BiConsumer;

public class RadiationLimitEffect implements RadiationEffect {
    private final Map<RadiationType, Float> radiations;
    private final BiConsumer<ServerPlayerEntity, Map<RadiationType, Float>> consumer;
    public RadiationLimitEffect(Map<RadiationType, Float> radiations, BiConsumer<ServerPlayerEntity, Map<RadiationType, Float>> consumer) {
        this.radiations = radiations;
        this.consumer = consumer;
    }
    @Override
    public void applyEffect(ServerPlayerEntity serverPlayerEntity, Map<RadiationType, Float> hashMap) {
        boolean state = true;
        for (RadiationType type : radiations.keySet()) {
            if (!hashMap.containsKey(type) || hashMap.get(type) < radiations.get(type)) {
                state = false;
                break;
            }
        }
        if (state) {
            consumer.accept(serverPlayerEntity, hashMap);
        }
    }
}
