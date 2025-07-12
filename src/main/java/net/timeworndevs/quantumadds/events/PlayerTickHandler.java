package net.timeworndevs.quantumadds.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.math.random.Random;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.effect.ModEffects;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Map.entry;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private int tick = 0;


    @FunctionalInterface
    public interface Action {
        void activate(ServerPlayerEntity player, HashMap<String, Double> rad);
    }
    private final Map<List<Double>, Action> actions = Map.ofEntries(
            //([min_alpha, min_beta, min_gamma, min_neutron] -> effect

            // ALPHA RADIATION
            entry(List.of(.9,.0,.0,.0),(player,rad) ->  {
                if (Math.random()<0.15) {
                    addMutation(player);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 6));
                }
            }),
            entry(List.of(.8,.0,.0,.0),(player,rad) ->  {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 1));
            }),
            entry(List.of(.5,.0,.0,.0),(player,rad) ->  {
                double r = Math.random();
                if (r>0.5) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));   // 50% * 80%
                } else if (r<0.25) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));     // 25% * 80%
                }
            }),
            entry(List.of(.1,.0,.0,.0),(player,rad) ->  {
                if (Math.random()>0.9) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));        // 25%
                }
            }),

            // BETA RADIATION
            entry(List.of(.0,.9,.0,.0),(player,rad) ->  {
                double r = Math.random();
                if (r>0.8) {
                    addMutation(player);
                } else if (r>0.5) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20, 6));
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 6));
                }
            }),
            entry(List.of(.0,.8,.0,.0),(player,rad) ->  {
                double r = Math.random();
                if (r>0.95) {
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, 40, 1));
                } else if (r>0.9) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            entry(List.of(.0,.5,.0,.0),(player,rad) ->  {
                double r = Math.random();
                if (r>0.5) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (r<0.15) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            entry(List.of(.0,.1,.0,.0),(player,rad) ->  {
                if (Math.random()>0.8) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),

            // GAMMA RADIATION
            entry(List.of(.0,.0,.0,.9),(player,rad) ->  {
                if (Math.random()<0.98) {
                    addMutation(player);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 6));
                }
            }),
            entry(List.of(.0,.0,.0,.8),(player,rad) ->  {
                double r = Math.random();
                if (r>0.9) {
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, 40, 1));
                } else if (r>0.8) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            entry(List.of(.0,.0,.0,.5),(player,rad) ->  {
                double r = Math.random();
                if (r>0.5) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (r<0.25) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            entry(List.of(.0,.0,.0,.1),(player,rad) ->  {
                if (Math.random()>0.8) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            }),

            // MIXED RADIATION
            entry(List.of(.9,.9,.8,.7),(player,rad) ->  {
                addMutation(player);
            }),
            entry(List.of(.8,.5,.3,.1),(player,rad) ->  {
                double r = Math.random();
                if (r>0.6) {
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, 40, 1));
                } else if (r>0.2) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1200, 2));
                }
            }),
            entry(List.of(.5,.5,.5,.5),(player,rad) ->  {
                double r = Math.random();
                if (r>0.5) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 120, 2));
                } else if (r<0.4) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                }
            }),
            entry(List.of(.1,.1,.1,.1),(player,rad) ->  {
                if (Math.random()>0.65) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 1));
                }
            })
    );

    @Override
    public void onStartTick(MinecraftServer server) {
        if (tick >= 20) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                double cur_alpha = ((double) ((IEntityDataSaver) player).getPersistentData().getInt("radiation.alpha")) / (double) Quantum.cap;
                double cur_beta = (double) ((IEntityDataSaver) player).getPersistentData().getInt("radiation.beta") /(double) Quantum.cap;
                double cur_gamma = (double) ((IEntityDataSaver) player).getPersistentData().getInt("radiation.gamma") /(double) Quantum.cap;
                double cur_neutron = (double) ((IEntityDataSaver) player).getPersistentData().getInt("radiation.neutron") /(double) Quantum.cap;

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

        mutations.get((int) (Math.random() * mutations.size())).mutate(player);
    }
}

