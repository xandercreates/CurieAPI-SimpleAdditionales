package net.timeworndevs.quantumadds;

import com.google.gson.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.timeworndevs.quantumadds.block.ModBlocks;
import net.timeworndevs.quantumadds.effect.ModEffects;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.entities.custom.MonstrocityEntity;
import net.timeworndevs.quantumadds.events.PlayerTickHandler;
import net.timeworndevs.quantumadds.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.networking.ModMessages;
import net.timeworndevs.quantumadds.util.ParseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static net.minecraft.server.command.CommandManager.*;

import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Quantum implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "quantumadds";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier locate(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static final ItemGroup RADIATION = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.GEIGER_COUNTER))
            .displayName(Text.translatable("itemGroup.quantumadds.radiation"))
            .entries((context, entries) -> {
            })
            .build();
    public static final ItemGroup BUILD_BLOCKS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.NUCLEAR_WASTE))
            .displayName(Text.translatable("itemGroup.quantumadds.building_blocks"))
            .entries((context, entries) -> {
            })
            .build();
    public static final ItemGroup QUANTUM = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.NUCLEAR_WASTE))
            .displayName(Text.translatable("itemGroup.quantumadds.quantum"))
            .entries((context, entries) -> {
            })
            .build();


    /*public Identifier getFabricId() {
        return new Identifier(Quantum.MOD_ID, "radiation_data");
    }
    public void reload(ResourceManager manager) {
        Map<Identifier, Resource> data = manager.findResources("radiation_data", path -> path.toString().endsWith(".json"));

        BiConsumer<Identifier, Resource> read = (i, resource) -> {
            Quantum.LOGGER.info(i + resource.toString());
        };
        data.forEach(read);
    }*/
    public static HashMap<String, JsonObject> radiation_data = new HashMap<>();
    public static HashMap<String, String> new_radiation_types = new HashMap<>();

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private static HashMap<String, String> all_types = new HashMap<>();

    @Override
    public void onInitialize() {
        Set<String> files;
        try {
            Files.createDirectories(Paths.get(FabricLoader.getInstance().getConfigDir() + "/curie"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Stream<Path> stream = Files.list(Paths.get(FabricLoader.getInstance().getConfigDir() + "/curie"))) {
            files = stream.filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.error(String.valueOf(files));

        for (int i=0; i<files.size(); i++) {
            File file = new File(FabricLoader.getInstance().getConfigDir() + "/curie/" + files.toArray()[i]);
            if (file.exists()) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                JsonObject json = JsonParser.parseReader(br).getAsJsonObject();

                if (json.has("radiation_types")) {
                    for (JsonElement ele: json.get("radiation_types").getAsJsonArray()) {
                        new_radiation_types.put(ele.getAsJsonObject().get("name").getAsString(), ele.getAsJsonObject().get("color").getAsString());
                    }
                }
            }
        }

        for (String i: new_radiation_types.keySet()) {
            ModMessages.NEW_RADIATIONS_SYNC_ID.put(new Identifier("radiation", "radiation_"+i+"_sync"), new ModMessages.NewSyncPackage(i));

        }
        
        new_radiation_types.put("alpha", "0f0.2f0.33f1f");
        new_radiation_types.put("beta", "0.24f0.33f0.2f1f");
        new_radiation_types.put("gamma", "0.35f0f0f1f");
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Computing wave-functions...");
        ModEffects.registerEffects();
        ModBlocks.registerBlocks();
        ModBlocks.registerBlockItems();
        ModEntities.registerModEntities();
        ModItems.registerItems();
        LOGGER.info("Analyzing external dimensions...");
        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
        //ModRecipes.registerRecipes();
        LOGGER.info("Testing radiation...");
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "radiation"), RADIATION);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "building_blocks"), BUILD_BLOCKS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "quantum"), QUANTUM);
        FabricDefaultAttributeRegistry.register(ModEntities.MONSTROCITY, MonstrocityEntity.createMonstrocityAttributes());

        LOGGER.info("Wormhole established!");
    }
}