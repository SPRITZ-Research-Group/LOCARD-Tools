package com.facebook.imagepipeline.k;

import android.net.Uri;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.i.f;
import com.facebook.common.internal.g;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.common.e;
import com.facebook.imagepipeline.h.c;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.EventsEntry;
import java.io.File;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class b {
    private final a a;
    private final Uri b;
    private final int c;
    private File d;
    private final boolean e;
    private final boolean f;
    private final com.facebook.imagepipeline.common.b g;
    @Nullable
    private final e h;
    private final RotationOptions i;
    @Nullable
    private final com.facebook.imagepipeline.common.a j;
    private final d k;
    private final b l;
    private final boolean m;
    private final boolean n;
    @Nullable
    private final d o;
    @Nullable
    private final c p;

    public enum a {
        SMALL,
        DEFAULT
    }

    public enum b {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        private int e;

        private b(int value) {
            this.e = value;
        }

        public final int a() {
            return this.e;
        }

        public static b a(b requestLevel1, b requestLevel2) {
            return requestLevel1.e > requestLevel2.e ? requestLevel1 : requestLevel2;
        }
    }

    public static b a(@Nullable Uri uri) {
        return uri == null ? null : c.a(uri).q();
    }

    protected b(c builder) {
        int i;
        this.a = builder.h();
        this.b = builder.a();
        Uri uri = this.b;
        if (uri != null) {
            if (f.b(uri)) {
                i = 0;
            } else if (f.c(uri)) {
                if (com.facebook.common.d.a.a(com.facebook.common.d.a.b(uri.getPath()))) {
                    i = 2;
                } else {
                    i = 3;
                }
            } else if (f.d(uri)) {
                i = 4;
            } else if (f.g(uri)) {
                i = 5;
            } else if (f.h(uri)) {
                i = 6;
            } else if (f.j(uri)) {
                i = 7;
            } else if (f.i(uri)) {
                i = 8;
            }
            this.c = i;
            this.e = builder.i();
            this.f = builder.k();
            this.g = builder.g();
            this.h = builder.d();
            this.i = builder.e() != null ? RotationOptions.a() : builder.e();
            this.j = builder.f();
            this.k = builder.n();
            this.l = builder.b();
            this.m = builder.l();
            this.n = builder.m();
            this.o = builder.o();
            this.p = builder.p();
        }
        i = -1;
        this.c = i;
        this.e = builder.i();
        this.f = builder.k();
        this.g = builder.g();
        this.h = builder.d();
        if (builder.e() != null) {
        }
        this.i = builder.e() != null ? RotationOptions.a() : builder.e();
        this.j = builder.f();
        this.k = builder.n();
        this.l = builder.b();
        this.m = builder.l();
        this.n = builder.m();
        this.o = builder.o();
        this.p = builder.p();
    }

    public final a a() {
        return this.a;
    }

    public final Uri b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final int d() {
        return this.h != null ? this.h.a : 2048;
    }

    public final int e() {
        return this.h != null ? this.h.b : 2048;
    }

    @Nullable
    public final e f() {
        return this.h;
    }

    public final RotationOptions g() {
        return this.i;
    }

    @Nullable
    public final com.facebook.imagepipeline.common.a h() {
        return this.j;
    }

    public final com.facebook.imagepipeline.common.b i() {
        return this.g;
    }

    public final boolean j() {
        return this.e;
    }

    public final boolean k() {
        return this.f;
    }

    public final d l() {
        return this.k;
    }

    public final b m() {
        return this.l;
    }

    public final boolean n() {
        return this.m;
    }

    public final boolean o() {
        return this.n;
    }

    public final synchronized File p() {
        if (this.d == null) {
            this.d = new File(this.b.getPath());
        }
        return this.d;
    }

    @Nullable
    public final d q() {
        return this.o;
    }

    @Nullable
    public final c r() {
        return this.p;
    }

    public boolean equals(Object o) {
        if (!(o instanceof b)) {
            return false;
        }
        b request = (b) o;
        if (!g.a(this.b, request.b) || !g.a(this.a, request.a) || !g.a(this.d, request.d) || !g.a(this.j, request.j) || !g.a(this.g, request.g) || !g.a(this.h, request.h) || !g.a(this.i, request.i)) {
            return false;
        }
        com.facebook.cache.a.c thisPostprocessorKey;
        com.facebook.cache.a.c thatPostprocessorKey;
        if (this.o != null) {
            thisPostprocessorKey = this.o.a();
        } else {
            thisPostprocessorKey = null;
        }
        if (request.o != null) {
            thatPostprocessorKey = request.o.a();
        } else {
            thatPostprocessorKey = null;
        }
        return g.a(thisPostprocessorKey, thatPostprocessorKey);
    }

    public int hashCode() {
        com.facebook.cache.a.c postprocessorCacheKey = this.o != null ? this.o.a() : null;
        return Arrays.hashCode(new Object[]{this.a, this.b, this.d, this.j, this.g, this.h, this.i, postprocessorCacheKey});
    }

    public String toString() {
        return g.a(this).a(ReactVideoViewManager.PROP_SRC_URI, this.b).a("cacheChoice", this.a).a("decodeOptions", this.g).a("postprocessor", this.o).a(EventsEntry.COLUMN_NAME_PRIORITY, this.k).a("resizeOptions", this.h).a("rotationOptions", this.i).a("bytesRange", this.j).toString();
    }
}
