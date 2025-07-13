package net.timeworndevs.quantumadds;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.timeworndevs.quantumadds.registries.*;
import net.timeworndevs.quantumadds.effect.QuantumEffects;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.entities.custom.MonstrosityEntity;
import net.timeworndevs.quantumadds.events.PlayerTickHandler;
import net.timeworndevs.quantumadds.registries.QuantumItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.timeworndevs.quantumadds.registries.QuantumItems.RADIATION;
import static net.timeworndevs.quantumadds.registries.QuantumBlocks.BUILDING_BLOCKS;

public class QuantumAdds implements ModInitializer {

    public static final String MOD_ID = "quantumadds";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Computing wave-functions...");
        QuantumBlocks.registerBlocks();
        QuantumRadiationTypes.registerRadiationTypes();
        QuantumEffects.registerEffects();

        ModWorldGeneration.generateModWorldGen();



        ModEntities.registerModEntities();

        QuantumItems.registerItems();


        LOGGER.info("Analyzing external dimensions...");

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

        LOGGER.info("Testing radiation...");

        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "radiation"), RADIATION);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "building_blocks"), BUILDING_BLOCKS);
        FabricDefaultAttributeRegistry.register(ModEntities.MONSTROCITY, MonstrosityEntity.createMonstrosityAttributes());

        LOGGER.info("Wormhole established!");
    }
}