package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaNodeType {
    DEFAULT(0),
    TEXT(1);
    
    private int mIntValue;

    private YogaNodeType(int intValue) {
        this.mIntValue = intValue;
    }

    public final int intValue() {
        return this.mIntValue;
    }

    public static YogaNodeType fromInt(int value) {
        switch (value) {
            case 0:
                return DEFAULT;
            case 1:
                return TEXT;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
