package net.timeworndevs.curiecontent.registries;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.curieapi.radiation.RadiationEffect;
import net.timeworndevs.curieapi.radiation.RadiationNBT;
import net.timeworndevs.curieapi.util.IEntityDataSaver;
import net.timeworndevs.curiecontent.CurieContent;

import java.util.*;
import scala.collection.immutable.Vector;
import scala.runtime.BoxedUnit;

import org.eu.net.pool.mutationkit.*;

@SuppressWarnings("unchecked")
public class CurieRadiationEffects {
    private record Radiation(double α, double β, double γ, double n) {
        boolean α(double min, double max) { return α >= min && α <= max; }
        boolean β(double min, double max) { return β >= min && β <= max; }
        boolean γ(double min, double max) { return γ >= min && γ <= max; }
        boolean n(double min, double max) { return n >= min && n <= max; }
    }

    public static final EffectManager<ServerPlayerEntity, Radiation>.System system;
    static {
        var manager = new EffectManager<ServerPlayerEntity, Radiation>(p -> {
            IEntityDataSaver data = (IEntityDataSaver) p;
            double α = RadiationNBT.get(data, "alpha");
            double β = RadiationNBT.get(data, "beta");
            double γ = RadiationNBT.get(data, "gamma");
            double n = RadiationNBT.get(data, "gamma");
            return new Radiation(α, β, γ, n);
        });
        system = manager.new System(
            scala.jdk.javaapi.CollectionConverters.asScala((Iterable) List.of(
                manager.new Row<>(MaxHealth$.MODULE$, r -> r.α(0.5, 0.6) && r.β(0.2, 0.3) && r.γ(0.3, 0.4), p -> 2.0),
                manager.new Row<>(Armor$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(AttackPower$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(AttackSpeed$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(KnockbackResistance$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(MovementSpeed$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(Reach$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(StepHeight$.MODULE$, r -> false, p -> 2.0),
                manager.new Row<>(JumpPower$.MODULE$, r -> false, p -> 2.0),
                // TODO: Gravity
                // TODO: Swim Speed
                manager.new Row<>(Spiderlike$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(Volatile$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(Frictionless$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(Breathless$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(Glowing$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(VisionAnomaly$.MODULE$, r -> false, p -> Identifier.of("minecraft", "spider")),
                manager.new Row<>(Conductive$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(LightSensitive$.MODULE$, r -> false, p -> BoxedUnit.UNIT),
                manager.new Row<>(Schizo$.MODULE$, r -> false, p -> BoxedUnit.UNIT)
            )).toSeq()
        );
    }

    public static void registerRadiationEffects() {

    }
}
