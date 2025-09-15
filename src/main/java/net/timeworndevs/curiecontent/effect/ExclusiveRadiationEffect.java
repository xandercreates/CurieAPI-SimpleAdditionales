package net.timeworndevs.curiecontent.effect;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.curieapi.radiation.AbstractRadiationEntry;
import net.timeworndevs.curieapi.radiation.RadiationEffect;
import net.timeworndevs.curieapi.radiation.RadiationEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class ExclusiveRadiationEffect<T extends AbstractRadiationEntry<?>> extends RadiationEffect<T> {
    protected static final Random rand = new Random();
    private final List<RadiationEffect<?>> exclusions;

    public ExclusiveRadiationEffect(Identifier id, T entry, BiConsumer<ServerPlayerEntity, RadiationEntry> consumer) {
        this(id, entry, consumer, new ArrayList<>());
    }
    public ExclusiveRadiationEffect(Identifier id, T entry, BiConsumer<ServerPlayerEntity, RadiationEntry> consumer, List<RadiationEffect<?>> exclusions) {
        super(id, entry, consumer);
        this.exclusions = exclusions;
    }

    public void setExclusions(List<RadiationEffect<?>> exclusions) {
        this.exclusions.addAll(exclusions);
    }

    public void applyEffect(ServerPlayerEntity serverPlayerEntity, RadiationEntry types) {
        Set<Identifier> currentEffects = RadiationEffect.getCurrentEffects(serverPlayerEntity);

        for (RadiationEffect<?> exclusion : exclusions) {
            if (currentEffects.contains(exclusion.getIdentifier())) {
                return; // Don't apply anything if one of the mutually exclusive effects is found.
            }
        }
        this.consumer.accept(serverPlayerEntity, types);
    }

}
