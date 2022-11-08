package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaDisplay {
    FLEX(0),
    NONE(1);
    
    private int mIntValue;

    private YogaDisplay(int intValue) {
        this.mIntValue = intValue;
    }

    public final int intValue() {
        return this.mIntValue;
    }

    public static YogaDisplay fromInt(int value) {
        switch (value) {
            case 0:
                return FLEX;
            case 1:
                return NONE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
