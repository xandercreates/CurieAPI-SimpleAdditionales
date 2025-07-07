package net.timeworndevs.quantumadds.radiation;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.networking.ModMessages;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;

public class RadiationData {
    public static void addRad(IEntityDataSaver player, RadiationType type, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int rad = nbt.getInt("radiation."+type.getName());

        rad = Math.min(rad + amount, 10000);
        nbt.putInt("radiation."+type.getName(), rad);
        syncRad(rad, type, (ServerPlayerEntity) player);
    }

    public static void delRad(IEntityDataSaver player, RadiationType type, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int rad = nbt.getInt("radiation." + type.getName());
        rad = Math.max(rad - amount, 0);

        nbt.putInt("radiation."+ type.getName(), rad);
        syncRad(rad, type, (ServerPlayerEntity) player);
    }
    public static void setRad(IEntityDataSaver player, RadiationType type, int rad) {
        NbtCompound nbt = player.getPersistentData();

        nbt.putInt("radiation." + type.getName(), rad);
        syncRad(rad, type, (ServerPlayerEntity) player);
    }

    public static void syncRad(int rad, RadiationType type, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(rad);
        ServerPlayNetworking.send(player, type.getSyncID(), buffer);
    }
}