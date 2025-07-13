package net.timeworndevs.quantumadds.events;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.quantum.radiation.RadiationEffect;
import net.timeworndevs.quantum.radiation.RadiationNBT;
import net.timeworndevs.quantum.util.IEntityDataSaver;
import net.timeworndevs.quantumadds.effect.QuantumEffects;
import net.timeworndevs.quantumadds.effect.RadiationLimitEffect;
import net.timeworndevs.quantumadds.effect.SingleRadiationLimitEffect;
import net.timeworndevs.quantumadds.registries.QuantumRadiationTypes;

import java.util.*;

public class RadiationLimitEffectList {
    private static final Random random = new Random();

    public static final List<RadiationEffect> actions = List.of(
            new SingleRadiationLimitEffect(QuantumRadiationTypes.ALPHA, 0.9f, (player, threshold) -> {
                if (random.nextDouble() <0.15) {
                    addMutation(player);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 6));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.ALPHA, 0.8f, (player, threshold) -> {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 1));
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.ALPHA, 0.5f,(player,rad) ->  {
                double num = random.nextDouble();
                if (num >0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));   // 50% * 80%
                } else if (num < 0.25f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));     // 25% * 80%
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.ALPHA, 0.1f,(player,rad) ->  {
                if (random.nextDouble() > 0.75f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));        // 25%
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.BETA, 0.9f, (player, threshold) -> {
                double num = random.nextDouble();
                if (num > 0.8f) {
                    addMutation(player);
                } else if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 6));
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 6));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.BETA, 0.8f, (player, threshold) -> {
                double num = random.nextDouble();
                if (num > 0.95f) {
                    player.addStatusEffect(new StatusEffectInstance(QuantumEffects.SKIN_FALLOFF, 40, 1));
                } else if (num > 0.9f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.BETA, 0.5f, (player, threshold) -> {
                double num = random.nextDouble();
                if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (num < 0.15f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.BETA, 0.1f, (player, threshold) -> {
                if (random.nextDouble() > 0.8f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.GAMMA, 0.9f, (player, threshold) -> {
                if (random.nextDouble() <0.98f) {
                    addMutation(player);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 6));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.GAMMA, 0.8f, (player, threshold) -> {
                double num = random.nextDouble();
                if (num >0.9f) {
                    player.addStatusEffect(new StatusEffectInstance(QuantumEffects.SKIN_FALLOFF, 40, 1));
                } else if (num >0.8f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.GAMMA, 0.5f, (player, threshold) -> {
                double num = random.nextDouble();
                if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (num < 0.25f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            new SingleRadiationLimitEffect(QuantumRadiationTypes.GAMMA, 0.1f, (player, threshold) -> {
                if (random.nextDouble() > 0.8f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),
            new RadiationLimitEffect(Map.of(QuantumRadiationTypes.ALPHA, 0.1f,QuantumRadiationTypes.BETA,0.1f, QuantumRadiationTypes.GAMMA, 0.1f, QuantumRadiationTypes.NEUTRON, 0.1f),(player,rad) ->  {
                if (random.nextDouble() > 0.65) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),
            new RadiationLimitEffect(Map.of(QuantumRadiationTypes.ALPHA, 0.9f,QuantumRadiationTypes.BETA,0.9f, QuantumRadiationTypes.GAMMA, 0.8f, QuantumRadiationTypes.NEUTRON, 0.7f),(player,rad) ->  {
                addMutation(player);
            }),
            new RadiationLimitEffect(Map.of(QuantumRadiationTypes.ALPHA, 0.8f,QuantumRadiationTypes.BETA,0.5f, QuantumRadiationTypes.GAMMA, 0.3f, QuantumRadiationTypes.NEUTRON, 0.1f),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.6f) {
                    player.addStatusEffect(new StatusEffectInstance(QuantumEffects.SKIN_FALLOFF, 40, 1));
                } else if (num > 0.2f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            new RadiationLimitEffect(Map.of(QuantumRadiationTypes.ALPHA, 0.5f,QuantumRadiationTypes.BETA,0.5f, QuantumRadiationTypes.GAMMA, 0.5f, QuantumRadiationTypes.NEUTRON, 0.5f),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (num < 0.4f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            })
    );

    @FunctionalInterface
    public interface Mutation {
        void mutate(ServerPlayerEntity player);
    }
    private static final List<Mutation> mutations = new ArrayList<>(List.of(
            player -> player.setGlowing(true),
            player -> player.setAbsorptionAmount(3f),
            player -> player.setMainArm(player.getMainArm().getOpposite()),
            player -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 1)),
            player -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 2)),
            player -> player.setInPowderSnow(true),
            player -> RadiationNBT.get((IEntityDataSaver) player).putBoolean("tinted", true)
    ));
    private static void addMutation(ServerPlayerEntity player) {
        mutations.get(random.nextInt(mutations.size())).mutate(player);
    }
}
