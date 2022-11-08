package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.adapters.ai;
import com.facebook.ads.internal.n.d;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.n.e.c;
import com.facebook.ads.internal.n.f;

public abstract class h {
    private final e a;

    public static class a {
        private final f a;

        a(f fVar) {
            this.a = fVar;
        }
    }

    public enum b {
        NONE(d.NONE),
        ALL(d.ALL);
        
        private final d c;

        private b(d dVar) {
            this.c = dVar;
        }

        final d a() {
            return this.c;
        }
    }

    public h(Context context, String str) {
        this.a = new e(context, str, new c() {
            public final boolean a(View view) {
                return (view instanceof MediaViewVideoRenderer) || (view instanceof AdChoicesView) || (view instanceof com.facebook.ads.internal.view.hscroll.b);
            }
        });
    }

    h(e eVar) {
        this.a = eVar;
    }

    public static c b() {
        return /* anonymous class already generated */;
    }

    final void a(AdIconView adIconView) {
        if (adIconView != null) {
            this.a.w();
        }
    }

    final void a(MediaView mediaView) {
        if (mediaView != null) {
            this.a.v();
        }
    }

    public final void a(b bVar) {
        this.a.a(bVar.a());
    }

    public final void a(final i iVar) {
        this.a.a(new com.facebook.ads.internal.n.h(this) {
            final /* synthetic */ h b;

            public final void a() {
                iVar.a();
            }

            public final void a(com.facebook.ads.internal.r.c cVar) {
                iVar.a(a.a(cVar));
            }

            public final void b() {
                iVar.b();
            }
        });
    }

    final void a(com.facebook.ads.internal.r.h hVar) {
        this.a.a(hVar);
    }

    final e c() {
        return this.a;
    }

    final ai d() {
        return this.a.a();
    }

    public final boolean e() {
        return this.a.b();
    }

    public final boolean f() {
        return this.a.c();
    }

    public final a g() {
        return this.a.e() == null ? null : new a(this.a.e());
    }

    public final String h() {
        return this.a.f();
    }

    public final String i() {
        return this.a.g();
    }

    public final String j() {
        return this.a.h();
    }

    public final String k() {
        return this.a.i();
    }

    public final String l() {
        return this.a.j();
    }

    @Nullable
    public final String m() {
        return this.a.k() == null ? null : this.a.k().a();
    }

    public final String n() {
        return this.a.l();
    }

    public final String o() {
        return this.a.m();
    }

    @Nullable
    final String p() {
        return this.a.s();
    }

    public final void q() {
        this.a.t();
    }

    public final void r() {
        this.a.u();
    }
}
