package net.timeworndevs.curiecontent.mixin;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

//    // This will modify the arguments before passing them to the original method
//    @ModifyArgs(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"))
//    public void modifyRenderArgs(Args args, @Local(ordinal = 0, argsOnly = true) LivingEntity livingEntity) {
//        if (livingEntity instanceof ClientPlayerEntity player) {
//            if(CurieNBT.get((IEntityDataSaver) player, "EFFECT").e("tinted")) {
//                args.set(4, 0.2f);
//                args.set(5, 0.4f);
//                args.set(6, 0.2f);
//            }
//        }
//    }
}