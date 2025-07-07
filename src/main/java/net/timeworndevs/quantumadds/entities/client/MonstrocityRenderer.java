package net.timeworndevs.quantumadds.entities.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.entities.custom.MonstrosityEntity;

public class MonstrocityRenderer extends MobEntityRenderer<MonstrosityEntity, GooberModel<MonstrosityEntity>> {
    private static final Identifier TEXTURE = new Identifier(Quantum.MOD_ID, "textures/entity/monstrocity_by_toast.png");

    public MonstrocityRenderer(EntityRendererFactory.Context context) {

        super(context, new GooberModel<>(context.getPart(QuantumModelLayers.MONSTROCITY)), 0.6f);
    }

    @Override
    public Identifier getTexture(MonstrosityEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(MonstrosityEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(0.6f, 0.6f, 0.6f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
