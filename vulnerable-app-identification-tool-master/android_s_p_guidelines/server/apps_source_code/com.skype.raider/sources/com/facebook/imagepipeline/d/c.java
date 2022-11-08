package com.facebook.imagepipeline.d;

import android.net.Uri;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.b;
import com.facebook.imagepipeline.common.e;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class c implements com.facebook.cache.a.c {
    private final String a;
    @Nullable
    private final e b;
    private final RotationOptions c;
    private final b d;
    @Nullable
    private final com.facebook.cache.a.c e;
    @Nullable
    private final String f;
    private final int g;
    private final Object h;
    private final long i;

    public c(String sourceString, @Nullable e resizeOptions, RotationOptions rotationOptions, b imageDecodeOptions, @Nullable com.facebook.cache.a.c postprocessorCacheKey, @Nullable String postprocessorName, Object callerContext) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        this.a = (String) h.a((Object) sourceString);
        this.b = resizeOptions;
        this.c = rotationOptions;
        this.d = imageDecodeOptions;
        this.e = postprocessorCacheKey;
        this.f = postprocessorName;
        Integer valueOf = Integer.valueOf(sourceString.hashCode());
        Integer valueOf2 = Integer.valueOf(resizeOptions != null ? resizeOptions.hashCode() : 0);
        Integer valueOf3 = Integer.valueOf(rotationOptions.hashCode());
        b bVar = this.d;
        com.facebook.cache.a.c cVar = this.e;
        if (valueOf == null) {
            i = 0;
        } else {
            i = valueOf.hashCode();
        }
        if (valueOf2 == null) {
            i2 = 0;
        } else {
            i2 = valueOf2.hashCode();
        }
        if (valueOf3 == null) {
            i3 = 0;
        } else {
            i3 = valueOf3.hashCode();
        }
        if (bVar == null) {
            i4 = 0;
        } else {
            i4 = bVar.hashCode();
        }
        if (cVar == null) {
            i5 = 0;
        } else {
            i5 = cVar.hashCode();
        }
        if (postprocessorName == null) {
            i6 = 0;
        } else {
            i6 = postprocessorName.hashCode();
        }
        this.g = i6 + ((i5 + ((i4 + ((i3 + ((i2 + ((i + 31) * 31)) * 31)) * 31)) * 31)) * 31);
        this.h = callerContext;
        this.i = RealtimeSinceBootClock.get().now();
    }

    public final boolean equals(Object o) {
        if (!(o instanceof c)) {
            return false;
        }
        c otherKey = (c) o;
        if (this.g == otherKey.g && this.a.equals(otherKey.a) && g.a(this.b, otherKey.b) && g.a(this.c, otherKey.c) && g.a(this.d, otherKey.d) && g.a(this.e, otherKey.e) && g.a(this.f, otherKey.f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.g;
    }

    public final String a() {
        return this.a;
    }

    public final String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", new Object[]{this.a, this.b, this.c, this.d, this.e, this.f, Integer.valueOf(this.g)});
    }

    public final boolean a(Uri uri) {
        return this.a.contains(uri.toString());
    }
}
