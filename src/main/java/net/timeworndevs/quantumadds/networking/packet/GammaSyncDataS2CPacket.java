package net.timeworndevs.quantumadds.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

import net.timeworndevs.quantumadds.util.IEntityDataSaver;

public class GammaSyncDataS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        //only on client thingies

        if (client.player != null) {
            ((IEntityDataSaver) client.player).getPersistentData().putInt("radiation.gamma", buf.readInt());
        }
    }
}