package com.facebook.ads.internal.o;

import android.content.Context;
import com.facebook.ads.internal.i.c;
import com.facebook.ads.internal.q.a.l;
import com.facebook.ads.internal.q.a.r;
import com.facebook.ads.internal.q.a.w;
import com.facebook.ads.internal.r.e;
import com.facebook.ads.internal.r.f;
import com.facebook.ads.internal.r.h;
import com.facebook.ads.internal.r.j;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class b {
    protected String a;
    public Context b;
    public h c;
    private e d;
    private final com.facebook.ads.internal.r.b e = this.d.a();
    private final String f;
    private final String g;
    private f h;
    private boolean i;
    private boolean j;
    private int k;
    private l l;
    private final Map<String, String> m;
    private final j n;
    private String o;

    public b(Context context, c cVar, String str, l lVar, h hVar, f fVar, String str2, String str3, int i, boolean z, boolean z2, j jVar, String str4) {
        this.a = str;
        this.l = lVar;
        this.c = hVar;
        this.d = e.a(hVar);
        this.h = fVar;
        this.f = str2;
        this.g = str3;
        this.k = i;
        this.i = z;
        this.j = z2;
        this.m = cVar.b();
        this.n = jVar;
        this.b = context;
        this.o = str4;
    }

    private static void a(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    public final String a() {
        return this.a;
    }

    public final e b() {
        return this.d;
    }

    public final l c() {
        return this.l;
    }

    public final int d() {
        return this.k;
    }

    public final j e() {
        return this.n;
    }

    public final Map<String, String> f() {
        Map<String, String> hashMap = new HashMap(this.m);
        a(hashMap, "IDFA", com.facebook.ads.internal.c.b.b);
        a(hashMap, "IDFA_FLAG", com.facebook.ads.internal.c.b.c ? "0" : "1");
        a(hashMap, "COPPA", String.valueOf(this.j));
        a(hashMap, "PLACEMENT_ID", this.a);
        if (this.e != com.facebook.ads.internal.r.b.UNKNOWN) {
            a(hashMap, "PLACEMENT_TYPE", this.e.toString().toLowerCase());
        }
        if (this.l != null) {
            a(hashMap, "WIDTH", String.valueOf(this.l.b()));
            a(hashMap, "HEIGHT", String.valueOf(this.l.a()));
        }
        a(hashMap, "ADAPTERS", this.g);
        if (this.c != null) {
            a(hashMap, "TEMPLATE_ID", String.valueOf(this.c.a()));
        }
        if (this.h != null) {
            a(hashMap, "REQUEST_TYPE", String.valueOf(this.h.a()));
        }
        if (this.i) {
            a(hashMap, "TEST_MODE", "1");
        }
        if (this.f != null) {
            a(hashMap, "DEMO_AD_ID", this.f);
        }
        if (this.k != 0) {
            a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(this.k));
        }
        a(hashMap, "CLIENT_EVENTS", com.facebook.ads.internal.j.b.a());
        a(hashMap, "KG_RESTRICTED", String.valueOf(w.a(this.b)));
        a(hashMap, "REQUEST_TIME", r.a(System.currentTimeMillis()));
        if (this.n.c()) {
            a(hashMap, "BID_ID", this.n.d());
        }
        if (this.o != null) {
            a(hashMap, "STACK_TRACE", this.o);
        }
        a(hashMap, "CLIENT_REQUEST_ID", UUID.randomUUID().toString());
        return hashMap;
    }
}
