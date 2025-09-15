//package net.timeworndevs.curiecontent.effect;
//
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.util.Identifier;
//import net.timeworndevs.curieapi.radiation.RadiationEntry;
//import net.timeworndevs.curieapi.radiation.RadiationType;
//import net.timeworndevs.curieapi.radiation.RadiationEffect;
//
//import java.util.function.BiConsumer;
//
//public class RadiationLimitEffect extends RadiationEffect {
//    public RadiationLimitEffect(Identifier id, RadiationEntry entry, BiConsumer<ServerPlayerEntity, RadiationEntry> consumer) {
//        super(id, entry, consumer);
//    }
//
//    @Override
//    public void applyEffect(ServerPlayerEntity serverPlayerEntity, RadiationEntry hashMap) {
//        for (RadiationType type : entry.getEntry().keySet()) {
//            if (!hashMap.getEntry().containsKey(type) || hashMap.getEntry().get(type) < entry.getEntry().get(type)) {
//                return;
//            }
//        }
//        this.updateEffects(serverPlayerEntity);
//        consumer.accept(serverPlayerEntity, hashMap);
//    }
//}
