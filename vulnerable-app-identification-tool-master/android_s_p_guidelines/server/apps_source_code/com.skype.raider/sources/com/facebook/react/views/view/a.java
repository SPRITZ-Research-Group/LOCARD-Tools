package com.facebook.react.views.view;

public final class a {
    public static int a(int color, int alpha) {
        if (alpha == 255) {
            return color;
        }
        if (alpha == 0) {
            return color & 16777215;
        }
        return ((((color >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24) | (16777215 & color);
    }
}
