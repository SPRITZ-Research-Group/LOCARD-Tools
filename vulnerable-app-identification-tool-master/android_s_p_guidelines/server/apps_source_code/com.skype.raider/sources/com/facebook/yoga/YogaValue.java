package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class YogaValue {
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);
    public static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    public static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    public final YogaUnit unit;
    public final float value;

    public YogaValue(float value, YogaUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    @DoNotStrip
    YogaValue(float value, int unit) {
        this(value, YogaUnit.fromInt(unit));
    }

    public boolean equals(Object other) {
        if (!(other instanceof YogaValue)) {
            return false;
        }
        YogaValue otherValue = (YogaValue) other;
        if (this.unit != otherValue.unit) {
            return false;
        }
        if (this.unit == YogaUnit.UNDEFINED || Float.compare(this.value, otherValue.value) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.value) + this.unit.intValue();
    }

    public String toString() {
        switch (this.unit) {
            case UNDEFINED:
                return "undefined";
            case POINT:
                return Float.toString(this.value);
            case PERCENT:
                return this.value + "%";
            case AUTO:
                return "auto";
            default:
                throw new IllegalStateException();
        }
    }

    public static YogaValue parse(String s) {
        if (s == null) {
            return null;
        }
        if ("undefined".equals(s)) {
            return UNDEFINED;
        }
        if ("auto".equals(s)) {
            return AUTO;
        }
        if (s.endsWith("%")) {
            return new YogaValue(Float.parseFloat(s.substring(0, s.length() - 1)), YogaUnit.PERCENT);
        }
        return new YogaValue(Float.parseFloat(s), YogaUnit.POINT);
    }
}
