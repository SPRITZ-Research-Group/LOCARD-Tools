package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaWrap {
    NO_WRAP(0),
    WRAP(1),
    WRAP_REVERSE(2);
    
    private int mIntValue;

    private YogaWrap(int intValue) {
        this.mIntValue = intValue;
    }

    public final int intValue() {
        return this.mIntValue;
    }

    public static YogaWrap fromInt(int value) {
        switch (value) {
            case 0:
                return NO_WRAP;
            case 1:
                return WRAP;
            case 2:
                return WRAP_REVERSE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
