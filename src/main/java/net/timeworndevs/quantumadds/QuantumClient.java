package net.timeworndevs.quantumadds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.RenderLayer;
import net.timeworndevs.quantumadds.block.ModBlocks;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.entities.client.GooberModel;
import net.timeworndevs.quantumadds.entities.client.ModModelLayers;
import net.timeworndevs.quantumadds.entities.client.MonstrocityRenderer;

@Environment(EnvType.CLIENT)
public class QuantumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MONSTROCITY, GooberModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MONSTROCITY, MonstrocityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REACTOR_CORE, RenderLayer.getTranslucent());
        // Here we will put client-only registration code (thabks toast)
    }
}