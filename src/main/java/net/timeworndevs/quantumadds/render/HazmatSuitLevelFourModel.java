package net.timeworndevs.quantumadds.render;

import net.minecraft.client.model.*;

public class HazmatSuitLevelFourModel {
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart right_arm;
    public final ModelPart left_arm;
    public final ModelPart right_leg;
    public final ModelPart left_leg;

    public HazmatSuitLevelFourModel(ModelPart root) {
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
    }
    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.625F))
                .uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F))
                .uv(53, 31).cuboid(-2.0F, -3.0F, -6.0F, 4.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData mask_right_r1 = head.addChild("mask_right_r1", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(-2.75F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 0.0F, -5.5F, 0.0F, 0.7854F, 0.0F));

        ModelPartData mask_left_r1 = head.addChild("mask_left_r1", ModelPartBuilder.create().uv(24, 0).cuboid(-0.25F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 0.0F, -5.5F, 0.0F, -0.7854F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.3125F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_arm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(39, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3125F))
                .uv(39, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.625F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData left_arm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.625F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData left_sleeve_r1 = left_arm.addChild("left_sleeve_r1", ModelPartBuilder.create().uv(32, 48).cuboid(-1.5F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.3125F)), ModelTransform.of(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0004F));

        ModelPartData right_leg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.3125F))
                .uv(0, 32).cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.625F))
                .uv(16, 39).cuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.5F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.3125F))
                .uv(16, 32).cuboid(-2.0F, 9.0F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.5F))
                .uv(0, 48).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.625F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));
        return modelData;
    }
}