package com.facebook.imageutils;

import android.graphics.ColorSpace;
import android.util.Pair;
import javax.annotation.Nullable;

public final class b {
    @Nullable
    private final Pair<Integer, Integer> a;
    @Nullable
    private final ColorSpace b;

    public b(int width, int height, @Nullable ColorSpace colorSpace) {
        Pair pair = (width == -1 || height == -1) ? null : new Pair(Integer.valueOf(width), Integer.valueOf(height));
        this.a = pair;
        this.b = colorSpace;
    }

    @Nullable
    public final Pair<Integer, Integer> a() {
        return this.a;
    }

    @Nullable
    public final ColorSpace b() {
        return this.b;
    }
}
