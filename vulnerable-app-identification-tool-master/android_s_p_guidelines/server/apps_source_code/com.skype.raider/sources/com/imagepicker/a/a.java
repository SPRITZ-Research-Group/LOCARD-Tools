package com.imagepicker.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

public final class a {
    @Nullable
    public final File a;
    @Nullable
    public final File b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final boolean g;

    public a(@Nullable File original, @Nullable File resized, int maxWidth, int maxHeight, int quality, int rotation, boolean saveToCameraRoll) {
        this.a = original;
        this.b = resized;
        this.c = maxWidth;
        this.d = maxHeight;
        this.e = quality;
        this.f = rotation;
        this.g = saveToCameraRoll;
    }

    @NonNull
    public final a a(@Nullable File original) {
        return new a(original, this.b, this.c, this.d, this.e, this.f, this.g);
    }

    @NonNull
    public final a b(@Nullable File resized) {
        return new a(this.a, resized, this.c, this.d, this.e, this.f, this.g);
    }

    public final File a() {
        return this.b != null ? this.b : this.a;
    }
}
