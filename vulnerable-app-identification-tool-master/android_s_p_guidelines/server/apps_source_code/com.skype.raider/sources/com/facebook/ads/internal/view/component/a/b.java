package com.facebook.ads.internal.view.component.a;

import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.h;
import java.util.HashMap;

public abstract class b extends RelativeLayout {
    static final int a = ((int) (16.0f * u.b));
    static final int b = ((int) (28.0f * u.b));
    private final h c;
    private final a d;
    private final c e;

    protected b(d dVar, d dVar2, boolean z) {
        super(dVar.a());
        this.e = dVar.b();
        this.d = new a(dVar.a(), b(), c(), "com.facebook.ads.interstitial.clicked", dVar2, dVar.b(), dVar.c(), dVar.e(), dVar.f());
        u.a(this.d);
        this.c = new h(getContext(), dVar2, z, k(), o());
        u.a(this.c);
    }

    public void a(com.facebook.ads.internal.adapters.a.h hVar, String str, double d) {
        h hVar2 = this.c;
        String b = hVar.a().b();
        String c = hVar.a().c();
        boolean z = !a() && d > 0.0d && d < 1.0d;
        hVar2.a(b, c, false, z);
        this.d.a(hVar.b(), str, new HashMap());
    }

    public abstract boolean a();

    protected boolean b() {
        return true;
    }

    protected boolean c() {
        return true;
    }

    protected boolean k() {
        return true;
    }

    public final c l() {
        return this.e;
    }

    protected final h m() {
        return this.c;
    }

    protected final a n() {
        return this.d;
    }

    protected boolean o() {
        return true;
    }
}
