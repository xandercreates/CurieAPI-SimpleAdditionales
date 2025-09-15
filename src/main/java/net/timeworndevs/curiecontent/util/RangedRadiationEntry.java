package net.timeworndevs.curiecontent.util;

import net.timeworndevs.curieapi.radiation.AbstractRadiationEntry;
import net.timeworndevs.curieapi.radiation.RadiationEntry;
import net.timeworndevs.curieapi.radiation.RadiationType;

import java.util.HashMap;
import java.util.Map;

public class RangedRadiationEntry extends AbstractRadiationEntry<RangedRadiationEntry.Range> {
    public RangedRadiationEntry(Map<RadiationType, Range> entry) {
        super(entry);
    }

    @Override
    public boolean isWithin(AbstractRadiationEntry<?> abstractRadiationEntry) {
        return false;
    }

    public boolean isWithin(RadiationEntry other) {
        for (RadiationType type : this.entry.keySet()) {
            float value = other.getEntry().getOrDefault(type, 0.0f);
            if (!this.isWithin(type, value)) {
                return false;
            };
        }
        return true;
    }

    public boolean isWithin(RadiationType type, float value) {
        Range range = this.entry.get(type);
        return range.min <= value && value <= range.max;
    }

    public record Range(float min, float max) {}

    public static class Builder {
        private final Map<RadiationType, Range> map = new HashMap<>();

        public Builder add(RadiationType type, float min, float max) {
            map.put(type, new Range(min, max));
            return this;
        }

        public RangedRadiationEntry build() {
            return new RangedRadiationEntry(map);
        }
    }
}
