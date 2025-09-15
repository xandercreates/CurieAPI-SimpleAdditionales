package net.timeworndevs.curiecontent.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.timeworndevs.curieapi.util.CurieNBT;
import net.timeworndevs.curieapi.util.IEntityDataSaver;
import net.timeworndevs.curiecontent.effect.QuantumEffects;
import net.timeworndevs.curiecontent.registries.CurieRadiationEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 2000)
public class LivingEntityMixin {
    @Inject(method="heal", at=@At(value="HEAD"), cancellable = true)
    public void curieContent$heal(float amount, CallbackInfo ci) {
        if ((LivingEntity) (Object) this instanceof ServerPlayerEntity player) {
            if (player.hasStatusEffect(QuantumEffects.DEGENERATION)) {
                ci.cancel();
            }
        }
    }
//    @Inject(method="getJumpVelocity", at=@At(value="TAIL"), cancellable = true)
//    protected void getJumpVelocity(CallbackInfoReturnable<Float> cir) {
//        if ((LivingEntity) (Object) this instanceof PlayerEntity player) {
//            if (CurieNBT.getEffectList((IEntityDataSaver) player).contains(NbtString.of(""))) {
//                cir.setReturnValue(cir.getReturnValue() + 2.0f);
//            }
//        }
//
//    }

}
