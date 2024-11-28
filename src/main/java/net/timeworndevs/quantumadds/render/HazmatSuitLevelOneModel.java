package net.timeworndevs.quantumadds.render;

import net.minecraft.client.model.*;

public class HazmatSuitLevelOneModel {
    public final ModelPart head;
    public final ModelPart body;
    public final ModelPart right_arm;
    public final ModelPart left_arm;
    public final ModelPart right_leg;
    public final ModelPart left_leg;

    public HazmatSuitLevelOneModel(ModelPart root) {
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
        ModelPartData Helmet = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.15F))
                .uv(44, 60).cuboid(-1.5F, -3.2F, -6.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 16).cuboid(1.5F, -2.7F, -5.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-3.5F, -2.7F, -5.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData resp_left = Helmet.addChild("resp_left", ModelPartBuilder.create().uv(24, 4).cuboid(-1.0389F, -0.5231F, -1.1452F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 66).cuboid(-1.5389F, -1.0231F, -2.7452F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -1.2F, -5.0F, 0.3439F, -0.5537F, -0.0916F));

        ModelPartData resp_right = Helmet.addChild("resp_right", ModelPartBuilder.create().uv(24, 0).cuboid(-0.9611F, -0.5231F, -1.1452F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(58, 65).cuboid(-1.4611F, -1.0231F, -2.7452F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.2F, -5.0F, 0.3439F, 0.5537F, 0.0916F));

        ModelPartData Chestplate = modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 28).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
                .uv(32, 16).cuboid(-1.5F, 2.0F, 2.2F, 3.0F, 9.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 5).cuboid(-1.0F, 1.0F, 2.7F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-2.0F, 7.0F, 1.7F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(58, 60).cuboid(-2.0F, 3.0F, 1.7F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData LeftLeg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(36, 44).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F))
                .uv(28, 60).cuboid(-2.0F, 7.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

        ModelPartData RightLeg = modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(20, 44).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F))
                .uv(60, 22).cuboid(-2.0F, 7.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        ModelPartData RightArm = modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(44, 12).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F))
                .uv(12, 60).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-6.0F, 2.0F, 0.0F));

        ModelPartData LeftArm = modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 0).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.1F))
                .uv(60, 16).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(6.0F, 2.0F, 0.0F));
        return modelData;
    }
}