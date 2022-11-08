package com.facebook.ads.internal.view.c.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.facebook.ads.internal.d.b;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.s;
import java.util.List;

public final class d extends android.support.v7.widget.RecyclerView.a<g> {
    private final c a;
    @Nullable
    private final b b;
    private final com.facebook.ads.internal.s.a c;
    private final s d;
    private final com.facebook.ads.internal.adapters.a.d e;
    @Nullable
    private com.facebook.ads.internal.view.a.a f;
    private int g;
    private int h;
    private String i;
    private int j;
    private int k;
    private List<b> l;
    private final a m;

    public interface a {
        void a(int i);
    }

    d(List<b> list, c cVar, b bVar, com.facebook.ads.internal.s.a aVar, s sVar, com.facebook.ads.internal.view.a.a aVar2, com.facebook.ads.internal.adapters.a.d dVar, String str, int i, int i2, int i3, int i4, a aVar3) {
        this.a = cVar;
        this.b = bVar;
        this.c = aVar;
        this.d = sVar;
        this.f = aVar2;
        this.l = list;
        this.h = i;
        this.e = dVar;
        this.j = i4;
        this.i = str;
        this.g = i3;
        this.k = i2;
        this.m = aVar3;
    }

    public final int a() {
        return this.l.size();
    }

    public final /* synthetic */ RecyclerView.s a(ViewGroup viewGroup, int i) {
        return new g(com.facebook.ads.internal.view.component.a.a.c.a(new com.facebook.ads.internal.view.component.a.d.a(viewGroup.getContext(), this.a, this.f, null, null, this.c, this.d).a(), this.j, this.e, this.i, this.m), this.c, this.h, this.g, this.k, this.l.size());
    }

    public final /* synthetic */ void a(RecyclerView.s sVar, int i) {
        ((g) sVar).a((b) this.l.get(i), this.a, this.b, this.d, this.i);
    }
}
