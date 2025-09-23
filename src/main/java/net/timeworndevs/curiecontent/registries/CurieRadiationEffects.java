package net.timeworndevs.curiecontent.registries;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.timeworndevs.curieapi.radiation.RadiationEntry;
import net.timeworndevs.curieapi.radiation.RadiationNBT;
import net.timeworndevs.curieapi.radiation.RadiationType;
import net.timeworndevs.curieapi.util.CurieAPIConfig;
import net.timeworndevs.curieapi.util.IEntityDataSaver;

import java.util.*;
import scala.runtime.BoxedUnit;
import static scala.jdk.javaapi.CollectionConverters.asScala;

import org.eu.net.pool.mutationkit.*;

import static net.timeworndevs.curieapi.util.CurieAPIConfig.RADIATION_TYPES;
import static net.timeworndevs.curiecontent.registries.CurieContentRadiationTypes.*;

@SuppressWarnings("unchecked")
public class CurieRadiationEffects {
    public static final EffectManager<ServerPlayerEntity, RadiationEntry>.System system;
    static class RadiationRange implements scala.Function1 {
        static record Range(double min, double max) {}
        private HashMap<RadiationType, Range> queries = new HashMap();

        public RadiationRange put(RadiationType name, double min, double max) {
            queries.put(name, new Range(min, max));
            return this;
        }
        public RadiationRange a(double min, double max) { return put(ALPHA, min, max); }
        public RadiationRange b(double min, double max) { return put(BETA, min, max); }
        public RadiationRange g(double min, double max) { return put(GAMMA, min, max); }
        public RadiationRange n(double min, double max) { return put(NEUTRON, min, max); }

        @Override
        public Boolean apply(Object v1) {
            var ent = (RadiationEntry) v1;
            for (var pair: queries.entrySet()) {
                float x = ent.get(pair.getKey()) / CurieAPIConfig.CAP;
                Range r = pair.getValue();
                if (x < r.min || x > r.max) return false;
            }
            return true;
        }

    }

    private static RadiationRange rg() {
        return new RadiationRange();
    }

    static {
        var manager = new EffectManager<ServerPlayerEntity, RadiationEntry>(p -> {
            RadiationEntry rad = RadiationEntry.createEmpty();
            for (RadiationType type : RADIATION_TYPES.values()) {
                float currentValue = (float) RadiationNBT.get((IEntityDataSaver) player, type.getName()) / CurieAPIConfig.CAP;
                rad.put(type, currentValue);
            }
            return rad;
        });
        system = manager.new System(
            asScala((Iterable) List.of(
                manager.new Row(MaxHealth$.MODULE$, rg().a(0.5, 0.6).b(0.2, 0.3).g(0.3, 0.4), p -> 2.0),
                manager.new Row(Armor$.MODULE$, r -> rg().a(0.5, 0.6).b(0.4, 0.5).g(0.1, 0.2), p -> 2.0),
                manager.new Row(AttackPower$.MODULE$, rg().a(0.0, 0.1).b(0.3, 0.4).n(0.3, 0.4), p -> 2.0),
                manager.new Row(AttackSpeed$.MODULE$, rg().a(0.0, 0.1).b(0.3, 0.4).g(0.3, 0.4), p -> 2.0),
                manager.new Row(KnockbackResistance$.MODULE$, rg().a(0.3, 0.4).b(0.0, 0.1).n(0.5, 0.6), p -> 2.0),
                manager.new Row(MovementSpeed$.MODULE$, rg().a(0.2, 0.3).b(0.5, 0.6).g(0.2, 0.3).n(0.0, 0.1), p -> 2.0),
                manager.new Row(Reach$.MODULE$, rg().a(0.0, 0.1).b(0.8, 0.9), p -> 2.0),
                manager.new Row(StepHeight$.MODULE$, rg().a(0.0, 0.1).g(0.8, 0.9), p -> 2.0),
                manager.new Row(JumpPower$.MODULE$, rg().a(0.1, 0.2).b(.5,.6).g(.1,.2).n(.1,.2), p -> 2.0),
                // TODO: Gravity
                // TODO: Swim Speed
                manager.new Row(Spiderlike$.MODULE$, rg().g(.5,.6).n(.2,.3), p -> BoxedUnit.UNIT),
                manager.new Row(Volatile$.MODULE$, rg().a(.8,.9).b(.0,.1).g(.0,.1).n(.6,.7), p -> BoxedUnit.UNIT),
                manager.new Row(Frictionless$.MODULE$, rg().a(.3,.4).b(.5,.7).g(.2,.3).n(.0,.1), p -> BoxedUnit.UNIT),
                manager.new Row(Breathless$.MODULE$, rg().a(.0,.1).b(.9,1).g(.4,.5), p -> BoxedUnit.UNIT),
                manager.new Row(Glowing$.MODULE$, rg().a(.5,1).g(.5,1), p -> BoxedUnit.UNIT),
                manager.new Row(VisionAnomaly$.MODULE$, rg().b(.2,1).g(.5,.6).n(.2,.3), p -> Identifier.of("minecraft", "spider")),
                manager.new Row(Conductive$.MODULE$, rg().a(.9,1).b(.1,.2).g(0,.1).n(.6,.7), p -> BoxedUnit.UNIT),
                manager.new Row(LightSensitive$.MODULE$, rg().a(.4,1).b(.2,1).g(.5,.6).n(.3,.4), p -> BoxedUnit.UNIT),
                manager.new Row(Schizo$.MODULE$, rg().a(.4,1).b(.2,1).g(.5,.6).n(.2,.3), p -> BoxedUnit.UNIT)
            )).toSeq()
        );
    }

    public static void registerRadiationEffects() {

    }
}
