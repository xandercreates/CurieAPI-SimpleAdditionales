package net.timeworndevs.curiecontent.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.timeworndevs.curieapi.util.CurieNBT;
import net.timeworndevs.curieapi.util.IEntityDataSaver;
import org.figuramc.figura.lua.LuaWhitelist;
import org.figuramc.figura.lua.NbtToLua;
import org.figuramc.figura.lua.ReadOnlyLuaTable;
import org.figuramc.figura.lua.api.entity.LivingEntityAPI;
import org.figuramc.figura.lua.api.entity.PlayerAPI;
import org.figuramc.figura.lua.docs.LuaMethodDoc;
import org.luaj.vm2.LuaTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;

@Pseudo
@Mixin(PlayerAPI.class)
public class PlayerAPIMixin extends LivingEntityAPI<PlayerEntity> {
    public PlayerAPIMixin(PlayerEntity entity) {
        super(entity);
    }

    @Unique
    @LuaWhitelist
    @LuaMethodDoc("curie.player.getRadiation")
    public LuaTable getRadiation() {
        return (ReadOnlyLuaTable) NbtToLua.convert(CurieNBT.get((IEntityDataSaver) entity, CurieNBT.CurieNBTType.RADIATION));
    }
}
