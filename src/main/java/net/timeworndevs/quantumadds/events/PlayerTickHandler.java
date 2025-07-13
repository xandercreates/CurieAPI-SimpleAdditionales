package net.timeworndevs.quantumadds.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.quantum.radiation.RadiationEffect;
import net.timeworndevs.quantum.radiation.RadiationNBT;
import net.timeworndevs.quantum.radiation.RadiationType;
import net.timeworndevs.quantum.util.IEntityDataSaver;

import net.timeworndevs.quantum.util.QuantumConfig;

import java.util.*;


public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private int tick = 0;


    @Override
    public void onStartTick(MinecraftServer server) {
        if (tick >= 20) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                NbtCompound persistentData = RadiationNBT.get((IEntityDataSaver) player);

                Map<RadiationType, Float> rad = new HashMap<>();
                for (RadiationType type : RadiationType.RADIATION_TYPES.values()) {
                    float currentValue = (float) persistentData.getInt(type.getName()) / QuantumConfig.cap;
                    rad.putIfAbsent(type, currentValue);
                }

                for (RadiationEffect effect : RadiationLimitEffectList.actions) {
                    effect.applyEffect(player, rad);
                }
            }
            tick = 0;
        }
        tick++;
    }

}

