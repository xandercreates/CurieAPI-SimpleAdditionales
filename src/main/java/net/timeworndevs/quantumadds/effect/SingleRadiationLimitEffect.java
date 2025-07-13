package net.timeworndevs.quantumadds.effect;

import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.quantum.radiation.RadiationEffect;
import net.timeworndevs.quantum.radiation.RadiationType;

import java.util.Map;
import java.util.function.BiConsumer;

public class SingleRadiationLimitEffect implements RadiationEffect {
    private final RadiationType type;
    private final float threshold;
    private final BiConsumer<ServerPlayerEntity, Map<RadiationType, Float>> consumer;
    public SingleRadiationLimitEffect(RadiationType type, float threshold , BiConsumer<ServerPlayerEntity, Map<RadiationType, Float>> consumer) {
        this.type = type;
        this.threshold = threshold;
        this.consumer = consumer;
    }

    @Override
    public void applyEffect(ServerPlayerEntity serverPlayerEntity, Map<RadiationType, Float> hashMap) {
        if (hashMap.containsKey(this.type) && hashMap.get(this.type) > this.threshold) {
            consumer.accept(serverPlayerEntity, hashMap);
        }
    }
}
