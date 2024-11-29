package net.timeworndevs.quantumadds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.block.ModBlocks;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.entities.client.GooberModel;
import net.timeworndevs.quantumadds.entities.client.ModModelLayers;
import net.timeworndevs.quantumadds.entities.client.MonstrocityRenderer;
import net.timeworndevs.quantumadds.render.*;

@Environment(EnvType.CLIENT)
public class QuantumClient implements ClientModInitializer {

    // -- Hazmat Suits -- //
    public static final EntityModelLayer HAZMAT_A_FEET_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID, "hazmat_suit_a"), "feet");
    public static final EntityModelLayer HAZMAT_A_MAIN_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID,"hazmat_suit_a"), "main");
    public static final EntityModelLayer HAZMAT_B_FEET_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID, "hazmat_suit_b"), "feet");
    public static final EntityModelLayer HAZMAT_B_MAIN_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID,"hazmat_suit_b"), "main");
    public static final EntityModelLayer HAZMAT_C_FEET_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID, "hazmat_suit_c"), "feet");
    public static final EntityModelLayer HAZMAT_C_MAIN_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID,"hazmat_suit_c"), "main");
    public static final EntityModelLayer HAZMAT_D_FEET_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID, "hazmat_suit_d"), "feet");
    public static final EntityModelLayer HAZMAT_D_MAIN_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID,"hazmat_suit_d"), "main");

    public static boolean isFiguraLoaded;
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MONSTROCITY, GooberModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MONSTROCITY, MonstrocityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REACTOR_CORE, RenderLayer.getTranslucent());
        // Here we will put client-only registration code (thabks toast)

        ArmorTestRenderer.register();
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_A_FEET_LAYER, () -> TexturedModelData.of(HazmatSuitLevelOneModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_A_MAIN_LAYER, () -> TexturedModelData.of(HazmatSuitLevelOneModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_B_FEET_LAYER, () -> TexturedModelData.of(HazmatSuitLevelTwoModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_B_MAIN_LAYER, () -> TexturedModelData.of(HazmatSuitLevelTwoModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_C_FEET_LAYER, () -> TexturedModelData.of(HazmatSuitLevelThreeModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_C_MAIN_LAYER, () -> TexturedModelData.of(HazmatSuitLevelThreeModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_D_FEET_LAYER, () -> TexturedModelData.of(HazmatSuitLevelFourModel.getModelData(), 128, 128));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_D_MAIN_LAYER, () -> TexturedModelData.of(HazmatSuitLevelFourModel.getModelData(), 128, 128));
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        isFiguraLoaded = (FabricLoader.getInstance().isModLoaded("figura"));
    }
}
