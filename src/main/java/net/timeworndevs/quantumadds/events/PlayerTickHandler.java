package net.timeworndevs.quantumadds.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.effect.ModEffects;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private int tick = 0;

    @Override
    public void onStartTick(MinecraftServer server) {
        if (tick >= 20) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                int cur_alpha = ((IEntityDataSaver) player).getPersistentData().getInt("radiation.alpha");
                int cur_beta = ((IEntityDataSaver) player).getPersistentData().getInt("beta");
                int cur_gamma = ((IEntityDataSaver) player).getPersistentData().getInt("gamma");
                int cur_neutron = ((IEntityDataSaver) player).getPersistentData().getInt("neutron");
                Quantum.LOGGER.info(String.valueOf(cur_alpha));

                if (cur_alpha>9000) {
                    if (Math.random()<0.98) {
                        addMutation(player);
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 6));
                    }
                } else if (cur_alpha>8000) {
                    if (Math.random()>0.5) {
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, cur_alpha, 4));
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 2));
                    }
                } else if (cur_alpha>5000) {
                    if (Math.random()>0.8) {
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, cur_alpha));
                    } else {
                        if (Math.random()>0.5) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 1));
                        } else {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                        }
                    }
                } else if (cur_beta>1000) {
                    if (Math.random()>0.8) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 1));
                    }
                }

                if (cur_beta>9000) {
                    if (Math.random()<0.98) {
                        addMutation(player);
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 6));
                    }
                } else if (cur_beta>8000) {
                    if (Math.random()>0.5) {
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, cur_beta, 4));
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 2));
                    }
                } else if (cur_beta>5000) {
                    if (Math.random()>0.8) {
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, cur_beta));
                    } else {
                        if (Math.random()>0.5) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, cur_beta/1000));
                        } else {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, cur_beta/2000));
                        }
                    }
                } else if (cur_beta>1000) {
                    if (Math.random()>0.8) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 1));
                    }
                }

                if (cur_gamma>9000) {
                    if (Math.random()<0.98) {
                        addMutation(player);
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 120, 6));
                    }
                } else if (cur_gamma>8000) {
                    if (Math.random()>0.5) {
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, cur_gamma, 4));
                    } else {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 2));
                    }
                } else if (cur_gamma>5000) {
                    if (Math.random()>0.8) {
                        player.addStatusEffect(new StatusEffectInstance(ModEffects.SKINFALLOFF, cur_gamma));
                    } else {
                        if (Math.random()>0.5) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 120, 1));
                        } else {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 120, 2));
                        }
                    }
                } else if (cur_gamma>1000) {
                    if (Math.random()>0.8) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 1));
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
            player -> player.setNoGravity(true)
            ));
    private void addMutation(ServerPlayerEntity player) {

        mutations.get((int) (Math.random() * mutations.size())).mutate(player);
    }
}

