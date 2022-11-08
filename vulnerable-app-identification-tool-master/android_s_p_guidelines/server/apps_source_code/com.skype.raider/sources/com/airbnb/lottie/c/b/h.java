package com.airbnb.lottie.c.b;

import android.support.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.a.a.b;
import com.airbnb.lottie.a.a.k;

public final class h implements b {
    private final String a;
    private final a b;

    public enum a {
        Merge,
        Add,
        Subtract,
        Intersect,
        ExcludeIntersections;

        public static a a(int id) {
            switch (id) {
                case 1:
                    return Merge;
                case 2:
                    return Add;
                case 3:
                    return Subtract;
                case 4:
                    return Intersect;
                case 5:
                    return ExcludeIntersections;
                default:
                    return Merge;
            }
        }
    }

    public h(String name, a mode) {
        this.a = name;
        this.b = mode;
    }

    public final String a() {
        return this.a;
    }

    public final a b() {
        return this.b;
    }

    @Nullable
    public final b a(LottieDrawable drawable, com.airbnb.lottie.c.c.a layer) {
        if (drawable.a()) {
            return new k(this);
        }
        return null;
    }

    public final String toString() {
        return "MergePaths{mode=" + this.b + '}';
    }
}
