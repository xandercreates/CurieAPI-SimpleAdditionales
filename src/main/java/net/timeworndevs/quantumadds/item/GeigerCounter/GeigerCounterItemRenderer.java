package net.timeworndevs.quantumadds.item.GeigerCounter;


import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.timeworndevs.quantumadds.QuantumAdds;

public class GeigerCounterItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    private static final Identifier texture = new Identifier(QuantumAdds.MOD_ID, "textures/item/geiger_counter.png");

    public GeigerCounterItemRenderer() {}
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("group", ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -8.0F, 7.0F, 12.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 12.0f, -8.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }
    private static final ModelPart model = getTexturedModelData().createModel();
    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        VertexConsumer baseConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));
        model.render(matrices, baseConsumer, light, overlay);
        float radiation = stack.getOrCreateNbt().getFloat("quantum:radiation_detected");

            matrices.push();
            matrices.translate(-0.125f, 1.125f, -0.2f);

            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));

            TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

            String text = "  " + radiation + " CPM";
            int width = textRenderer.getWidth(text);
            textRenderer.drawWithOutline(Text.literal(text).asOrderedText(), -width / 2f, 0, 0xFFFFFF, 0x000000, matrices.peek().getPositionMatrix(),
                    vertexConsumers, light);

            matrices.pop();

    }
}
