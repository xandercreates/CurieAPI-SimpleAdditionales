package net.timeworndevs.quantumadds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.timeworndevs.quantum.radiation.RadiationType;
import net.timeworndevs.quantumadds.entities.client.QuantumModelLayers;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.entities.client.MonstrocityRenderer;
import net.timeworndevs.quantumadds.item.GeigerCounter.GeigerCounterItemRenderer;
import net.timeworndevs.quantumadds.registries.QuantumBlocks;
import net.timeworndevs.quantumadds.registries.QuantumItems;
import net.timeworndevs.quantumadds.render.*;


@Environment(EnvType.CLIENT)
public class QuantumAddsClient implements ClientModInitializer {


    public static boolean isFiguraLoaded;
    @Override
    public void onInitializeClient() {

        QuantumModelLayers.registerModelLayers();
        EntityRendererRegistry.register(ModEntities.MONSTROCITY, MonstrocityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(QuantumBlocks.REACTOR_CORE, RenderLayer.getTranslucent());

        ArmorTestRenderer.register();

        BuiltinItemRendererRegistry.INSTANCE.register(QuantumItems.GEIGER_COUNTER, new GeigerCounterItemRenderer());
        RadiationType.RadiationPacket.registerPackets();

        isFiguraLoaded = (FabricLoader.getInstance().isModLoaded("figura"));
    }
}
