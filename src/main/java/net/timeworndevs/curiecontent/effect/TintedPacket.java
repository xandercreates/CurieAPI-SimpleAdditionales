package net.timeworndevs.curiecontent.effect;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.curieapi.radiation.RadiationNBT;
import net.timeworndevs.curieapi.util.IEntityDataSaver;
import net.timeworndevs.curiecontent.CurieContent;

public class TintedPacket {
    public static final Identifier identifier = new Identifier(CurieContent.MOD_ID, "tinted_packet");

    public static void sync(ServerPlayerEntity player, boolean state) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(state);
        ServerPlayNetworking.send(player, identifier, buffer);
    }

    public void register() {
        ClientPlayNetworking.registerGlobalReceiver(identifier, this::receive);
    }

    // Updates the radiation value on the client.
    public void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                        PacketByteBuf buf, PacketSender responseSender) {
        boolean value = buf.readBoolean();
        if (client.player != null) {
            RadiationNBT.get((IEntityDataSaver) client.player).putBoolean("tinted", value);
        }
    }
}
