package net.timeworndevs.quantumadds.registries;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.timeworndevs.quantumadds.radiation.RadiationType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.timeworndevs.quantumadds.Quantum.radiation_data;
import static net.timeworndevs.quantumadds.registries.QuantumRadiationTypes.RADIATION_TYPES;

public class QuantumConfig {
    public static void readConfig() {
        Set<String> files;
        try {
            if (!Files.exists(Paths.get(FabricLoader.getInstance().getConfigDir() + "/curie"))) {
                Files.createDirectory(Paths.get(FabricLoader.getInstance().getConfigDir() + "/curie"));
            }
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

        for (int i=0; i<files.size(); i++) {
            File file = new File(FabricLoader.getInstance().getConfigDir() + "/curie/" + files.toArray()[i]);
            if (file.exists()) {
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                JsonObject json = JsonParser.parseReader(br).getAsJsonObject();
                radiation_data.put(file.getName(), json);
                if (json.has("radiation_types")) {
                    for (JsonElement ele: json.get("radiation_types").getAsJsonArray()) {
                        List<Float> color = new ArrayList<>();
                        for (JsonElement element: ele.getAsJsonObject().get("color").getAsJsonArray()) {
                            color.add(element.getAsFloat());
                        }
                        String name = ele.getAsJsonObject().get("name").getAsString();
                        RadiationType type = new RadiationType(name, color);
                        RADIATION_TYPES.put(name, type);
                    }
                }
            }
        }
    }
}
