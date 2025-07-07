package net.timeworndevs.quantumadds.radiation;

import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.networking.ModMessages;

import java.util.List;

public class RadiationType {
    private final String name;
    private final List<Float> color;
    private final Identifier syncID;
    private final ModMessages.NewSyncPackage syncPacket;
    public RadiationType(String name, List<Float> color) {
        this.name = name;
        this.color = color;
        this.syncID = new Identifier(Quantum.MOD_ID, "radiation_" + name + "_sync");
        this.syncPacket = new ModMessages.NewSyncPackage(this.name);
        ModMessages.NEW_RADIATIONS_SYNC_ID.put(this.syncID, this.syncPacket);
    }

    public List<Float> getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }
    public Identifier getSyncID() {
        return this.syncID;
    }
    public ModMessages.NewSyncPackage getSyncPacket() {
        return this.syncPacket;
    }

}
