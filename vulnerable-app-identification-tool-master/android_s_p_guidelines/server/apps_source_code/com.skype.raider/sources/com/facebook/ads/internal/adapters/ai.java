package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.l;
import com.facebook.ads.internal.r.b;
import java.util.List;
import java.util.Map;

public abstract class ai implements a {
    private b a = b.NATIVE;

    public abstract List<e> A();

    public abstract int B();

    public abstract int C();

    public abstract void a(int i);

    public abstract void a(Context context, aj ajVar, c cVar, Map<String, Object> map, e.c cVar2);

    public abstract void a(View view, List<View> list);

    public abstract void a(aj ajVar);

    protected final void a(b bVar) {
        this.a = bVar;
    }

    public abstract void a(Map<String, String> map);

    public abstract void b(Map<String, String> map);

    public abstract String c();

    public final b d() {
        return this.a;
    }

    public abstract boolean f();

    public abstract void f_();

    public abstract boolean g();

    public abstract boolean g_();

    public abstract boolean h();

    public abstract int i();

    public abstract int j();

    public abstract int k();

    public abstract f l();

    public abstract f m();

    public abstract String n();

    public abstract String o();

    public abstract String p();

    public abstract String q();

    public abstract String r();

    public abstract f s();

    public abstract String t();

    public abstract String u();

    public abstract String v();

    public abstract String w();

    public abstract l x();

    public abstract int y();

    public abstract String z();
}
