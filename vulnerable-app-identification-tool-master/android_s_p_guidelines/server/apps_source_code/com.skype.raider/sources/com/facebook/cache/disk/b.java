package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.a.f;
import com.facebook.cache.a.g;
import com.facebook.common.internal.h;
import com.facebook.common.internal.j;
import java.io.File;
import javax.annotation.Nullable;

public final class b {
    private final int a;
    private final String b;
    private final j<File> c;
    private final long d;
    private final long e;
    private final long f;
    private final g g;
    private final com.facebook.cache.a.a h;
    private final com.facebook.cache.a.b i;
    private final com.facebook.common.a.a j;
    private final Context k;
    private final boolean l;

    public static class a {
        private int a;
        private String b;
        private j<File> c;
        private long d;
        private long e;
        private long f;
        private g g;
        private com.facebook.cache.a.a h;
        private com.facebook.cache.a.b i;
        private com.facebook.common.a.a j;
        private boolean k;
        @Nullable
        private final Context l;

        /* synthetic */ a(Context x0, byte b) {
            this(x0);
        }

        private a(@Nullable Context context) {
            this.a = 1;
            this.b = "image_cache";
            this.d = 41943040;
            this.e = 10485760;
            this.f = 2097152;
            this.g = new a();
            this.l = context;
        }

        public final a a() {
            this.d = 104857600;
            return this;
        }

        public final b b() {
            boolean z = (this.c == null && this.l == null) ? false : true;
            h.b(z, "Either a non-null context or a base directory path or supplier must be provided.");
            if (this.c == null && this.l != null) {
                this.c = new j<File>(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = this$0;
                    }

                    public final /* synthetic */ Object a() {
                        return this.a.l.getApplicationContext().getCacheDir();
                    }
                };
            }
            return new b();
        }
    }

    /* synthetic */ b(a x0, byte b) {
        this(x0);
    }

    private b(a builder) {
        com.facebook.cache.a.a a;
        com.facebook.cache.a.b a2;
        com.facebook.common.a.a a3;
        this.a = builder.a;
        this.b = (String) h.a(builder.b);
        this.c = (j) h.a(builder.c);
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = (g) h.a(builder.g);
        if (builder.h == null) {
            a = f.a();
        } else {
            a = builder.h;
        }
        this.h = a;
        if (builder.i == null) {
            a2 = g.a();
        } else {
            a2 = builder.i;
        }
        this.i = a2;
        if (builder.j == null) {
            a3 = com.facebook.common.a.b.a();
        } else {
            a3 = builder.j;
        }
        this.j = a3;
        this.k = builder.l;
        this.l = builder.k;
    }

    public final int a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final j<File> c() {
        return this.c;
    }

    public final long d() {
        return this.d;
    }

    public final long e() {
        return this.e;
    }

    public final long f() {
        return this.f;
    }

    public final g g() {
        return this.g;
    }

    public final com.facebook.cache.a.a h() {
        return this.h;
    }

    public final com.facebook.cache.a.b i() {
        return this.i;
    }

    public final boolean j() {
        return this.l;
    }

    public static a a(@Nullable Context context) {
        return new a(context, (byte) 0);
    }
}
