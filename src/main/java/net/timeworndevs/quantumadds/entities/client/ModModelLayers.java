package net.timeworndevs.quantumadds.entities.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.Quantum;

public class ModModelLayers {
    public static final EntityModelLayer MONSTROCITY =
            new EntityModelLayer(new Identifier(Quantum.MOD_ID, "monstrocity"), "main");
}
