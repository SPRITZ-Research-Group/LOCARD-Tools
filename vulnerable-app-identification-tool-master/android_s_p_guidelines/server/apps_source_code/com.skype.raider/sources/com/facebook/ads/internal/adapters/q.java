package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.a.e;
import com.facebook.ads.internal.h.d;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.view.b.a;
import com.facebook.ads.internal.view.b.a.b;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

public class q extends b {
    private static final String a = q.class.getSimpleName();
    @Nullable
    private b b;
    @Nullable
    private a c;
    private z d;
    private c e;
    private Map<String, Object> f;
    @Nullable
    private c g;
    private Context h;
    private long i;
    private com.facebook.ads.internal.j.a.a j;

    public final void a(Context context, c cVar, c cVar2, Map<String, Object> map) {
        this.h = context;
        this.g = cVar;
        this.e = cVar2;
        this.f = map;
        d dVar = (d) this.f.get("definition");
        this.i = 0;
        this.j = null;
        final y a = y.a((JSONObject) this.f.get("data"));
        if (e.a(this.h, a, this.g)) {
            c cVar3 = this.e;
            com.facebook.ads.a aVar = com.facebook.ads.a.b;
            cVar3.a(this);
            return;
        }
        this.b = new a.c(this) {
            final /* synthetic */ q b;

            public final void a() {
                this.b.d.b();
            }

            public final void a(int i) {
                if (i == 0 && this.b.i > 0 && this.b.j != null) {
                    com.facebook.ads.internal.j.b.a(com.facebook.ads.internal.j.a.a(this.b.i, this.b.j, a.g()));
                    this.b.i = 0;
                    this.b.j = null;
                }
            }

            public final void a(String str, Map<String, String> map) {
                Uri parse = Uri.parse(str);
                if ("fbad".equals(parse.getScheme()) && com.facebook.ads.internal.a.c.a(parse.getAuthority()) && this.b.e != null) {
                    this.b.e.b();
                }
                com.facebook.ads.internal.a.b a = com.facebook.ads.internal.a.c.a(this.b.h, this.b.g, a.c(), parse, map);
                if (a != null) {
                    try {
                        this.b.j = a.a();
                        this.b.i = System.currentTimeMillis();
                        a.b();
                    } catch (Exception e) {
                        q.a;
                    }
                }
            }

            public final void b() {
                if (this.b.d != null) {
                    this.b.d.a();
                }
            }
        };
        this.c = new a(this.h, new WeakReference(this.b), dVar.f());
        this.c.a(dVar.h(), dVar.i());
        this.d = new z(this.h, this.g, this.c, this.c.d(), new k(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public final void a() {
                if (this.a.e != null) {
                    this.a.e.a();
                }
            }
        });
        this.d.a(a);
        this.c.loadDataWithBaseURL(com.facebook.ads.internal.q.c.b.a(), a.d(), "text/html", "utf-8", null);
        if (this.e != null) {
            this.e.a(this, this.c);
        }
    }

    public final void e() {
        if (this.c != null) {
            this.c.destroy();
            this.c = null;
            this.b = null;
        }
    }
}
