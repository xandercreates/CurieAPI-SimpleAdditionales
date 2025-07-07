package net.timeworndevs.quantumadds.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.effect.QuantumEffects;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;

import java.util.*;

import static java.util.Map.entry;


public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private int tick = 0;

    private final Random random = new Random();
    @FunctionalInterface
    public interface Action {
        void activate(ServerPlayerEntity player, HashMap<String, Double> rad);
    }
    private final Map<List<Double>, Action> actions = Map.ofEntries(
            //([min_alpha, min_beta, min_gamma, min_neutron] -> effect
            // ALPHA RADIATION
            entry(List.of(.9,.0,.0,.0),(player,rad) ->  {
                if (random.nextDouble() <0.15) {
                    addMutation(player);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 6));
                }
            }),
            entry(List.of(.8,.0,.0,.0),(player,rad) ->  {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 1));
            }),
            entry(List.of(.5,.0,.0,.0),(player,rad) ->  {
                double num = random.nextDouble();
                if (num >0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));   // 50% * 80%
                } else if (num < 0.25f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));     // 25% * 80%
                }
            }),
            entry(List.of(.1,.0,.0,.0),(player,rad) ->  {
                if (random.nextDouble() > 0.9f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));        // 25%
                }
            }),

            // BETA RADIATION
            entry(List.of(.0,.9,.0,.0),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.8f) {
                    addMutation(player);
                } else if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 6));
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 6));
                }
            }),
            entry(List.of(.0,.8,.0,.0),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.95f) {
                    player.addStatusEffect(new StatusEffectInstance(QuantumEffects.SKIN_FALLOFF, 40, 1));
                } else if (num > 0.9f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            entry(List.of(.0,.5,.0,.0),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (num < 0.15f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            entry(List.of(.0,.1,.0,.0),(player,rad) ->  {
                if (random.nextDouble() > 0.8f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),

            // GAMMA RADIATION
            entry(List.of(.0,.0,.0,.9),(player,rad) ->  {
                if (random.nextDouble() <0.98f) {
                    addMutation(player);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 6));
                }
            }),
            entry(List.of(.0,.0,.0,.8),(player,rad) ->  {
                double num = random.nextDouble();
                if (num >0.9f) {
                    player.addStatusEffect(new StatusEffectInstance(QuantumEffects.SKIN_FALLOFF, 40, 1));
                } else if (num >0.8f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            entry(List.of(.0,.0,.0,.5),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (num < 0.25f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            entry(List.of(.0,.0,.0,.1),(player,rad) ->  {
                if (random.nextDouble() > 0.8f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),

            // MIXED RADIATION
            entry(List.of(.9,.9,.8,.7),(player,rad) ->  {
                addMutation(player);
            }),
            entry(List.of(.8,.5,.3,.1),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.6f) {
                    player.addStatusEffect(new StatusEffectInstance(QuantumEffects.SKIN_FALLOFF, 40, 1));
                } else if (num > 0.2f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            entry(List.of(.5,.5,.5,.5),(player,rad) ->  {
                double num = random.nextDouble();
                if (num > 0.5f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (num < 0.4f) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            entry(List.of(.1,.1,.1,.1),(player,rad) ->  {
                if (random.nextDouble() > 0.65) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            })
    );

    @Override
    public void onStartTick(MinecraftServer server) {
        if (tick >= 20) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                NbtCompound persistentData = ((IEntityDataSaver) player).getPersistentData();
                double cur_alpha = (double) persistentData.getInt("radiation.alpha") / Quantum.cap;
                double cur_beta = (double) persistentData.getInt("radiation.beta") / Quantum.cap;
                double cur_gamma = (double) persistentData.getInt("radiation.gamma") / Quantum.cap;
                double cur_neutron = (double) persistentData.getInt("radiation.neutron") / Quantum.cap;

                HashMap<String, Double> rad = new HashMap<>();
                rad.put("alpha", cur_alpha);
                rad.put("beta", cur_beta);
                rad.put("gamma", cur_gamma);
                rad.put("neutron", cur_neutron);
                for (List<Double> k : actions.keySet()) {
                    if (cur_alpha > k.get(0) && cur_beta > k.get(1) && cur_gamma > k.get(2) && cur_neutron > k.get(3)) {
                        actions.get(k).activate(player, rad);
                    }
                }
            }
            tick = 0;
        }
        tick++;

    }
    @FunctionalInterface
    public interface Mutation {
        void mutate(ServerPlayerEntity player);
    }
    List<Mutation> mutations = new ArrayList<>(List.of(
            player -> player.setGlowing(true),
            player -> player.setAbsorptionAmount(3f),
            player -> {
                player.setMainArm(player.getMainArm().getOpposite());
            },
            player -> {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 1));
            },
            player -> {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 2));
            },
            player -> {
                player.setInPowderSnow(true);
            },
            player -> {
                 ((IEntityDataSaver) player).getPersistentData().putBoolean("radiation.tinted", true);
            }
            ));
    private void addMutation(ServerPlayerEntity player) {

        mutations.get(random.nextInt(mutations.size())).mutate(player);
    }
}

