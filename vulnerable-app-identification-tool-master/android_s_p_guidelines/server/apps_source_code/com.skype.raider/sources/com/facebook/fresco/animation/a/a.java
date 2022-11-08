package com.facebook.fresco.animation.a;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import javax.annotation.Nullable;

public interface a extends d {
    int a();

    void a(@IntRange(from = 0, to = 255) int i);

    void a(@Nullable ColorFilter colorFilter);

    void a(Rect rect);

    boolean a(Drawable drawable, Canvas canvas, int i);

    int b();

    void c();
}
