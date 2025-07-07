package net.timeworndevs.quantumadds.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.radiation.RadiationType;
import net.timeworndevs.quantumadds.registries.QuantumRadiationTypes;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;

import java.util.HashMap;

public class ModMessages {
    public static class NewSyncPackage {
        private final String name;
        public NewSyncPackage(String name) {
            this.name = name;
        }
        @Environment(EnvType.CLIENT)
        public void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                            PacketByteBuf buf, PacketSender responseSender) {
            if (client.player != null) {
                ((IEntityDataSaver) client.player).getPersistentData().putInt("radiation." + this.name, buf.readInt());
            }
        }
    }

    public static HashMap<Identifier, NewSyncPackage> NEW_RADIATIONS_SYNC_ID = new HashMap<>();

    public static void registerS2CPackets() {
        for (RadiationType type : QuantumRadiationTypes.RADIATION_TYPES.values()) {
            ClientPlayNetworking.registerGlobalReceiver(type.getSyncID(), type.getSyncPacket()::receive);
        }
    }
}