package net.timeworndevs.quantumadds.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.QuantumAdds;
import net.timeworndevs.quantumadds.entities.custom.MonstrosityEntity;

public class ModEntities {
    public static EntityType<MonstrosityEntity> MONSTROCITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(QuantumAdds.MOD_ID, "monstrocity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MonstrosityEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build());
    
    public static void registerModEntities() {

        QuantumAdds.LOGGER.info("Registering piles of flesh for " + QuantumAdds.MOD_ID);

    }

}
