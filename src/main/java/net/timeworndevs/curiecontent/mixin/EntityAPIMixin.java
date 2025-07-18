package net.timeworndevs.quantumadds.mixin;

import net.minecraft.entity.Entity;
import net.timeworndevs.quantumadds.Quantum;
import net.timeworndevs.quantumadds.util.IEntityDataSaver;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.api.entity.EntityAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.LuaTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Pseudo
@Mixin(EntityAPI.class)
public class EntityAPIMixin {
    @Shadow @NotNull protected Entity entity;

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("curie.player.getRadiation")
    public LuaTable getRadiation() {
        LuaTable table = new LuaTable();
        // why?? why?? why...
        for (String key: Quantum.new_radiation_types.keySet()) {
            table.set(key, ((IEntityDataSaver) entity).getPersistentData().getInt("radiation."+key));
        }
        return table;
    }
}
