package com.facebook.react.views.art;

import com.facebook.react.bridge.al;
import javax.annotation.Nullable;

final class f {
    @Nullable
    static float[] a(@Nullable al value) {
        if (value == null) {
            return null;
        }
        float[] result = new float[value.size()];
        a(value, result);
        return result;
    }

    static int a(al value, float[] into) {
        int length = value.size() > into.length ? into.length : value.size();
        for (int i = 0; i < length; i++) {
            into[i] = (float) value.getDouble(i);
        }
        return value.size();
    }
}
