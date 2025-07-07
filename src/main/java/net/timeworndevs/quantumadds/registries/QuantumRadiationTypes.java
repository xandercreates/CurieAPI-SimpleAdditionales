package net.timeworndevs.quantumadds.registries;

import net.timeworndevs.quantumadds.radiation.RadiationType;

import java.util.HashMap;
import java.util.List;

public class QuantumRadiationTypes {

    public static final HashMap<String, RadiationType> RADIATION_TYPES = new HashMap<>();

    public static final RadiationType GAMMA = new RadiationType("gamma", List.of(0.0f, 0.2f, 0.33f, 1.0f));
    public static final RadiationType BETA = new RadiationType("beta", List.of(0.24f, 0.33f, 0.2f, 1.0f));
    public static final RadiationType ALPHA = new RadiationType("alpha", List.of(0.35f, 0.0f, 0.0f, 1.0f));

    public static void registerRadiationTypes() {
        RADIATION_TYPES.put("alpha", ALPHA);
        RADIATION_TYPES.put("beta", BETA);
        RADIATION_TYPES.put("gamma", GAMMA);
    }

}
