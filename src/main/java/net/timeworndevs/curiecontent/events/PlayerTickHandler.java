package net.timeworndevs.curiecontent.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.curieapi.radiation.RadiationEntry;
import net.timeworndevs.curieapi.radiation.RadiationType;
import net.timeworndevs.curieapi.util.CurieAPIConfig;
import net.timeworndevs.curieapi.util.CurieNBT;
import net.timeworndevs.curieapi.util.IEntityDataSaver;
import net.timeworndevs.curiecontent.CurieContent;
import net.timeworndevs.curiecontent.registries.CurieRadiationEffects;

import static net.timeworndevs.curieapi.util.CurieAPIConfig.RADIATION_TYPES;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private int tick = 0;


    @Override
    public void onStartTick(MinecraftServer server) {
        if (tick >= 20) {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {


                RadiationEntry rad = RadiationEntry.createEmpty();
                for (RadiationType type : RADIATION_TYPES.values()) {
                    float currentValue = (float) CurieNBT.getRadiation((IEntityDataSaver) player, type.getName()) / CurieAPIConfig.CAP;
                    rad.getEntry().put(type, currentValue);
                }
                CurieContent.LOGGER.info(String.valueOf(CurieNBT.get((IEntityDataSaver) player, CurieNBT.CurieNBTType.EFFECT).getNbtType()));
                CurieRadiationEffects.JUMP_POWER.applyEffect(player, rad);
//                for (RadiationEffect effect : RadiationLimitEffectList.actions) {
//                    effect.applyEffect(player, rad);
//                }
            }
            tick = 0;
        }
        tick++;
    }

}

