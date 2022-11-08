package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.m;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.p;
import com.facebook.ads.internal.view.f.b.r;
import com.facebook.ads.internal.view.f.b.s;
import com.facebook.ads.internal.view.f.b.w;
import com.facebook.ads.internal.view.f.b.x;
import com.facebook.ads.internal.view.f.b.y;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class b extends c {
    public int a;
    private final w b;
    private final f<r> c;
    private final f<h> d;
    private final f<j> e;
    private final f<n> f;
    private final f<com.facebook.ads.internal.view.f.b.b> g;
    private final f<p> h;
    private final f<x> i;
    private final f<y> j;
    private final f<s> k;
    private final m l;
    private final a m;
    private boolean n;

    public b(Context context, c cVar, a aVar, String str) {
        this(context, cVar, aVar, new ArrayList(), str);
    }

    public b(Context context, c cVar, a aVar, String str, @Nullable Bundle bundle) {
        this(context, cVar, aVar, new ArrayList(), str, bundle, null);
    }

    public b(Context context, c cVar, a aVar, String str, @Nullable Map<String, String> map) {
        this(context, cVar, aVar, new ArrayList(), str, null, map);
    }

    public b(Context context, c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str) {
        super(context, cVar, aVar, list, str);
        this.b = new w(this) {
            static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
            final /* synthetic */ b b;

            {
                this.b = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                if (!a && this.b == null) {
                    throw new AssertionError();
                } else if (this.b != null) {
                    this.b.d();
                }
            }
        };
        this.c = new f<r>(this) {
            static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
            final /* synthetic */ b b;

            {
                this.b = r1;
            }

            public final Class<r> a() {
                return r.class;
            }

            public final /* synthetic */ void a(d dVar) {
                if (!a && this.b == null) {
                    throw new AssertionError();
                } else if (this.b != null) {
                    this.b.e();
                }
            }
        };
        this.d = new f<h>(this) {
            static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
            final /* synthetic */ b b;

            {
                this.b = r1;
            }

            public final Class<h> a() {
                return h.class;
            }

            public final /* synthetic */ void a(d dVar) {
                if (!a && this.b == null) {
                    throw new AssertionError();
                } else if (this.b != null) {
                    this.b.f();
                }
            }
        };
        this.e = new f<j>(this) {
            static final /* synthetic */ boolean a = (!b.class.desiredAssertionStatus());
            final /* synthetic */ b b;

            {
                this.b = r1;
            }

            public final Class<j> a() {
                return j.class;
            }

            public final /* synthetic */ void a(d dVar) {
                if (!a && this.b == null) {
                    throw new AssertionError();
                } else if (this.b == null) {
                } else {
                    if (this.b.n) {
                        this.b.g();
                    } else {
                        this.b.n = true;
                    }
                }
            }
        };
        this.f = new f<n>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final Class<n> a() {
                return n.class;
            }

            public final /* synthetic */ void a(d dVar) {
                int a = ((n) dVar).a();
                if (this.a.a <= 0 || a != this.a.m.f() || this.a.m.f() <= this.a.a) {
                    this.a.a(a);
                }
            }
        };
        this.g = new f<com.facebook.ads.internal.view.f.b.b>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final Class<com.facebook.ads.internal.view.f.b.b> a() {
                return com.facebook.ads.internal.view.f.b.b.class;
            }

            public final /* synthetic */ void a(d dVar) {
                com.facebook.ads.internal.view.f.b.b bVar = (com.facebook.ads.internal.view.f.b.b) dVar;
                int a = bVar.a();
                int b = bVar.b();
                if (this.a.a > 0 && a == b && b > this.a.a) {
                    return;
                }
                if (b >= a + 500) {
                    this.a.b(a);
                } else if (b == 0) {
                    this.a.b(this.a.a);
                } else {
                    this.a.b(b);
                }
            }
        };
        this.h = new f<p>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final Class<p> a() {
                return p.class;
            }

            public final /* synthetic */ void a(d dVar) {
                p pVar = (p) dVar;
                this.a.a(pVar.a(), pVar.b());
            }
        };
        this.i = new f<x>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final Class<x> a() {
                return x.class;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.b();
            }
        };
        this.j = new f<y>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final Class<y> a() {
                return y.class;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.c();
            }
        };
        this.k = new f<s>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final Class<s> a() {
                return s.class;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.a(this.a.i(), this.a.i());
            }
        };
        this.l = new m(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.a = this.a.m.f();
            }
        };
        this.n = false;
        this.m = aVar;
        this.m.a().a(this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.l, this.k);
    }

    private b(Context context, c cVar, a aVar, List<com.facebook.ads.internal.b.b> list, String str, @Nullable Bundle bundle, @Nullable Map<String, String> map) {
        super(context, cVar, aVar, list, str, bundle, map);
        this.b = /* anonymous class already generated */;
        this.c = /* anonymous class already generated */;
        this.d = /* anonymous class already generated */;
        this.e = /* anonymous class already generated */;
        this.f = /* anonymous class already generated */;
        this.g = /* anonymous class already generated */;
        this.h = /* anonymous class already generated */;
        this.i = /* anonymous class already generated */;
        this.j = /* anonymous class already generated */;
        this.k = /* anonymous class already generated */;
        this.l = /* anonymous class already generated */;
        this.n = false;
        this.m = aVar;
        this.m.a().a(this.b, this.f, this.c, this.e, this.d, this.g, this.h, this.i, this.j, this.k);
    }

    public final void a() {
        this.m.r().post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.m.a().b(this.a.b, this.a.f, this.a.c, this.a.e, this.a.d, this.a.g, this.a.h, this.a.i, this.a.j, this.a.l, this.a.k);
            }
        });
    }
}
