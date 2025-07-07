package net.timeworndevs.quantumadds;

import com.google.gson.*;
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
import net.timeworndevs.quantumadds.networking.ModMessages;
import net.timeworndevs.quantumadds.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static net.timeworndevs.quantumadds.registries.QuantumItems.RADIATION;
import static net.timeworndevs.quantumadds.registries.QuantumBlocks.BUILDING_BLOCKS;

public class Quantum implements ModInitializer {

    public static final String MOD_ID = "quantumadds";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static HashMap<String, JsonObject> radiation_data = new HashMap<>();
    public static int cap = 100000;
    @Override
    public void onInitialize() {
        QuantumConfig.readConfig();
        LOGGER.info("Computing wave-functions...");
        QuantumEffects.registerEffects();

        ModWorldGeneration.generateModWorldGen();

        QuantumBlocks.registerBlocks();

        ModEntities.registerModEntities();
        ModMessages.registerS2CPackets();

        QuantumItems.registerItems();

        QuantumRadiationTypes.registerRadiationTypes();
        LOGGER.info("Analyzing external dimensions...");

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

        LOGGER.info("Testing radiation...");

        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "radiation"), RADIATION);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "building_blocks"), BUILDING_BLOCKS);
        FabricDefaultAttributeRegistry.register(ModEntities.MONSTROCITY, MonstrosityEntity.createMonstrosityAttributes());

        LOGGER.info("Wormhole established!");
    }
}