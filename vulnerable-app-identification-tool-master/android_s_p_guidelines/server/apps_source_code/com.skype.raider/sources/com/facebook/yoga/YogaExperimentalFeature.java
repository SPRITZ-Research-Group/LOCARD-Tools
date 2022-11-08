package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaExperimentalFeature {
    WEB_FLEX_BASIS(0);
    
    private int mIntValue;

    private YogaExperimentalFeature(int intValue) {
        this.mIntValue = intValue;
    }

    public final int intValue() {
        return this.mIntValue;
    }

    public static YogaExperimentalFeature fromInt(int value) {
        switch (value) {
            case 0:
                return WEB_FLEX_BASIS;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
