package com.facebook.drawee.c;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import javax.annotation.Nullable;

public final class e {
    public static void a(@Nullable Drawable to, @Nullable Drawable from) {
        if (from != null && to != null && to != from) {
            to.setBounds(from.getBounds());
            to.setChangingConfigurations(from.getChangingConfigurations());
            to.setLevel(from.getLevel());
            to.setVisible(from.isVisible(), false);
            to.setState(from.getState());
        }
    }

    public static void a(@Nullable Drawable drawable, @Nullable d properties) {
        if (drawable != null && properties != null) {
            properties.a(drawable);
        }
    }

    public static void a(@Nullable Drawable drawable, @Nullable Callback callback, @Nullable s transformCallback) {
        if (drawable != null) {
            drawable.setCallback(callback);
            if (drawable instanceof r) {
                ((r) drawable).a(transformCallback);
            }
        }
    }

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
