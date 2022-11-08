package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaPositionType {
    RELATIVE(0),
    ABSOLUTE(1);
    
    private final int mIntValue;

    private YogaPositionType(int i) {
        this.mIntValue = i;
    }

    public final int intValue() {
        return this.mIntValue;
    }

    public static YogaPositionType fromInt(int i) {
        switch (i) {
            case 0:
                return RELATIVE;
            case 1:
                return ABSOLUTE;
            default:
                throw new IllegalArgumentException("Unknown enum value: ".concat(String.valueOf(i)));
        }
    }
}
