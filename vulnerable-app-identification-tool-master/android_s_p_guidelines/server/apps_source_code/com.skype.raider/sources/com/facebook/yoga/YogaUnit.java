package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaUnit {
    UNDEFINED(0),
    POINT(1),
    PERCENT(2),
    AUTO(3);
    
    private int mIntValue;

    private YogaUnit(int intValue) {
        this.mIntValue = intValue;
    }

    public final int intValue() {
        return this.mIntValue;
    }

    public static YogaUnit fromInt(int value) {
        switch (value) {
            case 0:
                return UNDEFINED;
            case 1:
                return POINT;
            case 2:
                return PERCENT;
            case 3:
                return AUTO;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
