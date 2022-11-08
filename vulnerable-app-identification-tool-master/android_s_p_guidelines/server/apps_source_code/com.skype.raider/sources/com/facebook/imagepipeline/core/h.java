package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.common.e.d;
import com.facebook.common.internal.j;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.i;
import com.facebook.imagepipeline.d.k;
import com.facebook.imagepipeline.d.n;
import com.facebook.imagepipeline.d.q;
import com.facebook.imagepipeline.d.t;
import com.facebook.imagepipeline.f.c;
import com.facebook.imagepipeline.f.e;
import com.facebook.imagepipeline.f.g;
import com.facebook.imagepipeline.memory.ab;
import com.facebook.imagepipeline.memory.ac;
import com.facebook.imagepipeline.producers.af;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public final class h {
    private static b C = new b();
    private final i A;
    private final boolean B;
    private final Config a;
    private final j<q> b;
    private final com.facebook.imagepipeline.d.h.a c;
    private final f d;
    private final Context e;
    private final boolean f;
    private final f g;
    private final j<q> h;
    private final e i;
    private final n j;
    @Nullable
    private final c k;
    @Nullable
    private final com.facebook.imagepipeline.transcoder.c l;
    @Nullable
    private final Integer m;
    private final j<Boolean> n;
    private final com.facebook.cache.disk.b o;
    private final d p;
    private final int q;
    private final af r;
    private final int s;
    @Nullable
    private final com.facebook.imagepipeline.c.f t;
    private final ac u;
    private final e v;
    private final Set<com.facebook.imagepipeline.h.c> w;
    private final boolean x;
    private final com.facebook.cache.disk.b y;
    @Nullable
    private final com.facebook.imagepipeline.f.d z;

    public static class a {
        private final com.facebook.imagepipeline.core.i.a A;
        private boolean B;
        private Config a;
        private j<q> b;
        private com.facebook.imagepipeline.d.h.a c;
        private f d;
        private final Context e;
        private boolean f;
        private j<q> g;
        private e h;
        private n i;
        private c j;
        private com.facebook.imagepipeline.transcoder.c k;
        @Nullable
        private Integer l;
        private j<Boolean> m;
        private com.facebook.cache.disk.b n;
        private d o;
        @Nullable
        private Integer p;
        private af q;
        private com.facebook.imagepipeline.c.f r;
        private ac s;
        private e t;
        private Set<com.facebook.imagepipeline.h.c> u;
        private boolean v;
        private com.facebook.cache.disk.b w;
        private f x;
        private com.facebook.imagepipeline.f.d y;
        private int z;

        /* synthetic */ a(Context x0, byte b) {
            this(x0);
        }

        private a(Context context) {
            this.f = false;
            this.l = null;
            this.p = null;
            this.v = true;
            this.z = -1;
            this.A = new com.facebook.imagepipeline.core.i.a(this);
            this.B = true;
            this.e = (Context) com.facebook.common.internal.h.a((Object) context);
        }

        public final a a(Config config) {
            this.a = config;
            return this;
        }

        public final a a(boolean downsampleEnabled) {
            this.f = downsampleEnabled;
            return this;
        }

        public final a a(c imageDecoder) {
            this.j = imageDecoder;
            return this;
        }

        public final a a(com.facebook.cache.disk.b mainDiskCacheConfig) {
            this.n = mainDiskCacheConfig;
            return this;
        }

        public final a a(d memoryTrimmableRegistry) {
            this.o = memoryTrimmableRegistry;
            return this;
        }

        public final a a(af networkFetcher) {
            this.q = networkFetcher;
            return this;
        }

        public final a a(Set<com.facebook.imagepipeline.h.c> requestListeners) {
            this.u = requestListeners;
            return this;
        }

        public final h a() {
            return new h();
        }
    }

    public static class b {
        private boolean a;

        /* synthetic */ b(byte b) {
            this();
        }

        private b() {
            this.a = false;
        }

        public final boolean a() {
            return this.a;
        }
    }

    /* synthetic */ h(a x0, byte b) {
        this(x0);
    }

    private h(a builder) {
        j iVar;
        com.facebook.imagepipeline.d.h.a dVar;
        f a;
        f bVar;
        n a2;
        com.facebook.imagepipeline.l.b.a();
        this.A = new i(builder.A, (byte) 0);
        if (builder.b == null) {
            iVar = new i((ActivityManager) builder.e.getSystemService("activity"));
        } else {
            iVar = builder.b;
        }
        this.b = iVar;
        if (builder.c == null) {
            dVar = new com.facebook.imagepipeline.d.d();
        } else {
            dVar = builder.c;
        }
        this.c = dVar;
        this.a = builder.a == null ? Config.ARGB_8888 : builder.a;
        if (builder.d == null) {
            a = com.facebook.imagepipeline.d.j.a();
        } else {
            a = builder.d;
        }
        this.d = a;
        this.e = (Context) com.facebook.common.internal.h.a(builder.e);
        if (builder.x == null) {
            bVar = new b(new d());
        } else {
            bVar = builder.x;
        }
        this.g = bVar;
        this.f = builder.f;
        if (builder.g == null) {
            iVar = new k();
        } else {
            iVar = builder.g;
        }
        this.h = iVar;
        if (builder.i == null) {
            a2 = t.a();
        } else {
            a2 = builder.i;
        }
        this.j = a2;
        this.k = builder.j;
        if (builder.k == null || builder.l == null) {
            com.facebook.imagepipeline.transcoder.c A;
            com.facebook.cache.disk.b b;
            d a3;
            af tVar;
            ac acVar;
            e gVar;
            Set hashSet;
            e aVar;
            if (builder.k != null) {
                A = builder.k;
            } else {
                A = null;
            }
            this.l = A;
            this.m = builder.l;
            if (builder.m == null) {
                iVar = new j<Boolean>(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = this$0;
                    }

                    public final /* synthetic */ Object a() {
                        return Boolean.valueOf(true);
                    }
                };
            } else {
                iVar = builder.m;
            }
            this.n = iVar;
            if (builder.n == null) {
                b = b(builder.e);
            } else {
                b = builder.n;
            }
            this.o = b;
            if (builder.o == null) {
                a3 = com.facebook.common.e.e.a();
            } else {
                a3 = builder.o;
            }
            this.p = a3;
            int intValue = builder.p != null ? builder.p.intValue() : this.A.h() ? 1 : 0;
            this.q = intValue;
            if (builder.z < 0) {
                intValue = 30000;
            } else {
                intValue = builder.z;
            }
            this.s = intValue;
            com.facebook.imagepipeline.l.b.a();
            if (builder.q == null) {
                tVar = new com.facebook.imagepipeline.producers.t(this.s);
            } else {
                tVar = builder.q;
            }
            this.r = tVar;
            com.facebook.imagepipeline.l.b.a();
            this.t = builder.r;
            if (builder.s == null) {
                acVar = new ac(ab.l().a());
            } else {
                acVar = builder.s;
            }
            this.u = acVar;
            if (builder.t == null) {
                gVar = new g();
            } else {
                gVar = builder.t;
            }
            this.v = gVar;
            if (builder.u == null) {
                hashSet = new HashSet();
            } else {
                hashSet = builder.u;
            }
            this.w = hashSet;
            this.x = builder.v;
            if (builder.w == null) {
                b = this.o;
            } else {
                b = builder.w;
            }
            this.y = b;
            this.z = builder.y;
            int numCpuBoundThreads = this.u.c();
            if (builder.h == null) {
                aVar = new a(numCpuBoundThreads);
            } else {
                aVar = builder.h;
            }
            this.i = aVar;
            this.B = builder.B;
            com.facebook.common.j.a webpBitmapFactory = this.A.d();
            com.facebook.imagepipeline.c.d dVar2;
            if (webpBitmapFactory != null) {
                dVar2 = new com.facebook.imagepipeline.c.d(this.u);
                com.facebook.common.j.b.d = webpBitmapFactory;
            } else if (this.A.b() && com.facebook.common.j.b.a) {
                webpBitmapFactory = com.facebook.common.j.b.a();
                if (webpBitmapFactory != null) {
                    dVar2 = new com.facebook.imagepipeline.c.d(this.u);
                    com.facebook.common.j.b.d = webpBitmapFactory;
                }
            }
            com.facebook.imagepipeline.l.b.a();
            return;
        }
        throw new IllegalStateException("You can't define a custom ImageTranscoderFactory and provide an ImageTranscoderType");
    }

    private static com.facebook.cache.disk.b b(Context context) {
        try {
            com.facebook.imagepipeline.l.b.a();
            com.facebook.cache.disk.b b = com.facebook.cache.disk.b.a(context).b();
            return b;
        } finally {
            com.facebook.imagepipeline.l.b.a();
        }
    }

    public final Config a() {
        return this.a;
    }

    public final j<q> b() {
        return this.b;
    }

    public final com.facebook.imagepipeline.d.h.a c() {
        return this.c;
    }

    public final f d() {
        return this.d;
    }

    public final Context e() {
        return this.e;
    }

    public static b f() {
        return C;
    }

    public final f g() {
        return this.g;
    }

    public final boolean h() {
        return this.f;
    }

    public final boolean i() {
        return this.B;
    }

    public final j<q> j() {
        return this.h;
    }

    public final e k() {
        return this.i;
    }

    public final n l() {
        return this.j;
    }

    @Nullable
    public final c m() {
        return this.k;
    }

    @Nullable
    public final com.facebook.imagepipeline.transcoder.c n() {
        return this.l;
    }

    @Nullable
    public final Integer o() {
        return this.m;
    }

    public final j<Boolean> p() {
        return this.n;
    }

    public final com.facebook.cache.disk.b q() {
        return this.o;
    }

    public final d r() {
        return this.p;
    }

    public final int s() {
        return this.q;
    }

    public final af t() {
        return this.r;
    }

    public final ac u() {
        return this.u;
    }

    public final e v() {
        return this.v;
    }

    public final Set<com.facebook.imagepipeline.h.c> w() {
        return Collections.unmodifiableSet(this.w);
    }

    public final boolean x() {
        return this.x;
    }

    public final com.facebook.cache.disk.b y() {
        return this.y;
    }

    @Nullable
    public final com.facebook.imagepipeline.f.d z() {
        return this.z;
    }

    public final i A() {
        return this.A;
    }

    public static a a(Context context) {
        return new a(context, (byte) 0);
    }
}
