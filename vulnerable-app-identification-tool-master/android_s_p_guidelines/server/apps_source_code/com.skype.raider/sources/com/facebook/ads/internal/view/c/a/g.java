package com.facebook.ads.internal.view.c.a;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.s;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.s.a;
import com.facebook.ads.internal.view.component.a.a.b;
import java.util.Map;

final class g extends s {
    private final b n;
    private final int o;
    private final int p;
    private final int q;
    private final int r;
    private boolean s = false;
    @Nullable
    private a t;
    private a.a u;
    private a v;

    g(b bVar, a aVar, int i, int i2, int i3, int i4) {
        super(bVar);
        this.n = bVar;
        this.v = aVar;
        this.o = i;
        this.p = i2;
        this.q = i3;
        this.r = i4;
    }

    final void a(final b bVar, c cVar, com.facebook.ads.internal.d.b bVar2, com.facebook.ads.internal.q.a.s sVar, String str) {
        int b = bVar.b();
        this.n.setTag(-1593835536, Integer.valueOf(b));
        LayoutParams marginLayoutParams = new MarginLayoutParams(this.o, -2);
        marginLayoutParams.setMargins(b == 0 ? this.p : this.q, 0, b >= this.r + -1 ? this.p : this.q, 0);
        String f = bVar.c().c().f();
        String a = bVar.c().c().a();
        this.n.setIsVideo(!TextUtils.isEmpty(a));
        if (this.n.f()) {
            this.n.setVideoPlaceholderUrl(f);
            b bVar3 = this.n;
            String str2 = "";
            if (!(bVar2 == null || a == null)) {
                str2 = bVar2.b(a);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = a;
            }
            bVar3.setVideoUrl(str2);
        } else {
            this.n.setImageUrl(f);
        }
        this.n.setLayoutParams(marginLayoutParams);
        this.n.a(bVar.c().a().a(), bVar.c().a().c());
        this.n.a(bVar.c().b().b(), bVar.c().b().a(), bVar.a());
        this.n.a(bVar.a());
        if (!this.s) {
            if (this.t != null) {
                this.t.c();
                this.t = null;
            }
            final Map a2 = bVar.a();
            final String str3 = str;
            final com.facebook.ads.internal.q.a.s sVar2 = sVar;
            final c cVar2 = cVar;
            this.u = new a.a(this) {
                final /* synthetic */ g e;

                public final void a() {
                    if (!(this.e.v.b() || TextUtils.isEmpty(str3))) {
                        if (this.e.t != null) {
                            this.e.t.a(a2);
                        }
                        a2.put("touch", k.a(sVar2.e()));
                        cVar2.a(str3, a2);
                    }
                    this.e.s = true;
                }
            };
            this.t = new a(this.n, 10, this.u);
            this.t.a(100);
            this.t.b(100);
            this.n.setOnAssetsLoadedListener(new b.a(this) {
                final /* synthetic */ g b;

                public final void a() {
                    if (bVar.b() == 0) {
                        this.b.v.a();
                    }
                    this.b.t.a();
                }
            });
        }
    }
}
