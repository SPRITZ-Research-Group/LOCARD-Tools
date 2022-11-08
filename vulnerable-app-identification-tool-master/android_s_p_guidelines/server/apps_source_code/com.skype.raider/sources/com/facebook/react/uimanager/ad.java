package com.facebook.react.uimanager;

import com.facebook.yoga.a;
import com.skype.Defines;

public final class ad {
    private static final int[] a = new int[]{1, 2, 4, 8, 16, 32, 64, 128, Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE};
    private final float[] b;
    private int c;
    private float d;
    private boolean e;

    public ad() {
        this(0.0f);
    }

    public ad(float defaultValue) {
        this.b = new float[]{Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN};
        this.c = 0;
        this.d = defaultValue;
    }

    public final boolean a(int spacingType, float value) {
        boolean z = false;
        if (c.a(this.b[spacingType], value)) {
            return false;
        }
        this.b[spacingType] = value;
        if (a.a(value)) {
            this.c &= a[spacingType] ^ -1;
        } else {
            this.c |= a[spacingType];
        }
        if (!((this.c & a[8]) == 0 && (this.c & a[7]) == 0 && (this.c & a[6]) == 0)) {
            z = true;
        }
        this.e = z;
        return true;
    }

    public final float a(int spacingType) {
        float defaultValue = (spacingType == 4 || spacingType == 5) ? Float.NaN : this.d;
        if (this.c == 0) {
            return defaultValue;
        }
        if ((this.c & a[spacingType]) != 0) {
            return this.b[spacingType];
        }
        if (!this.e) {
            return defaultValue;
        }
        int secondType = (spacingType == 1 || spacingType == 3) ? 7 : 6;
        if ((this.c & a[secondType]) != 0) {
            return this.b[secondType];
        }
        if ((this.c & a[8]) != 0) {
            return this.b[8];
        }
        return defaultValue;
    }

    public final float b(int spacingType) {
        return this.b[spacingType];
    }
}
