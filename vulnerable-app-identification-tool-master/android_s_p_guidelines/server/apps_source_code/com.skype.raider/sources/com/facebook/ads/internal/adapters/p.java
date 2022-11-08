package com.facebook.ads.internal.adapters;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.s;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.f;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.c;
import com.facebook.ads.internal.view.hscroll.b;
import com.facebook.ads.internal.view.l;
import java.util.List;

public final class p extends android.support.v7.widget.RecyclerView.a<c> {
    private static final int a = Color.argb(51, 0, 0, 0);
    private final com.facebook.ads.internal.s.a.a b = new com.facebook.ads.internal.s.a.a(this) {
        final /* synthetic */ p a;

        {
            this.a = r1;
        }

        public final void a() {
            if (this.a.f != null) {
                this.a.f.a();
            }
        }
    };
    private final List<e> c;
    private final int d;
    private final int e;
    @Nullable
    private a f;

    public interface a {
        void a();
    }

    public p(b bVar, List<e> list) {
        float f = bVar.getContext().getResources().getDisplayMetrics().density;
        this.c = list;
        this.d = Math.round(f * 1.0f);
        this.e = bVar.t();
    }

    public final int a() {
        return this.c.size();
    }

    public final /* synthetic */ void a(s sVar, final int i) {
        c cVar = (c) sVar;
        LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -1);
        marginLayoutParams.setMargins(i == 0 ? this.e * 2 : this.e, 0, i >= this.c.size() + -1 ? this.e * 2 : this.e, 0);
        View view = (l) cVar.n;
        view.setLayoutParams(marginLayoutParams);
        view.setPadding(this.d, this.d, this.d, this.d);
        final ImageView imageView = (com.facebook.ads.internal.view.s) view.a();
        u.a((View) imageView, 0);
        imageView.setImageDrawable(null);
        final e eVar = (e) this.c.get(i);
        eVar.a(view, (g) view);
        f e = eVar.e();
        if (e != null) {
            d a = new d(imageView).a();
            a.a(new com.facebook.ads.internal.view.b.e(this) {
                final /* synthetic */ p d;

                public final void a(boolean z) {
                    if (i == 0) {
                        eVar.a(this.d.b);
                    }
                    eVar.a(z, true);
                    u.a(imageView, p.a);
                }
            });
            a.a(e.a());
        }
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    public final /* synthetic */ s a(ViewGroup viewGroup, int i) {
        return new c(new l(viewGroup.getContext()));
    }
}
