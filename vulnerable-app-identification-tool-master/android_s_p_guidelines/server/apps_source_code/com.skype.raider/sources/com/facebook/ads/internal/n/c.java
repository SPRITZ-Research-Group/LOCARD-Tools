package com.facebook.ads.internal.n;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.c.h;
import com.facebook.ads.internal.view.f.d.d;
import com.facebook.ads.internal.view.j;
import java.util.concurrent.atomic.AtomicBoolean;

public class c {
    private static final String a = c.class.getSimpleName();
    private final g b;
    private final com.facebook.ads.internal.s.a c;
    private final com.facebook.ads.internal.s.a.a d;
    private final View e;
    private final com.facebook.ads.internal.view.f.d.a f = new com.facebook.ads.internal.view.f.d.a(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public final void a() {
            this.a.n.set(true);
            if (this.a.h != null) {
                this.a.h.a(this.a.m.get());
            }
        }
    };
    @Nullable
    private j g;
    @Nullable
    private a h;
    private Context i;
    private boolean j;
    private boolean k;
    private boolean l;
    private final AtomicBoolean m = new AtomicBoolean(false);
    private final AtomicBoolean n = new AtomicBoolean(false);
    private l o = l.DEFAULT;

    public interface a {
        void a(boolean z);
    }

    public c(Context context, View view) {
        this.i = context;
        this.e = view;
        this.b = new g(context);
        this.d = new com.facebook.ads.internal.s.a.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public final void a() {
                if (this.a.g != null) {
                    if (!this.a.l && (this.a.k || c.g(this.a))) {
                        c.a(this.a, com.facebook.ads.internal.view.f.a.a.AUTO_STARTED);
                    }
                    this.a.k = false;
                    this.a.l = false;
                }
            }

            public final void b() {
                if (this.a.g != null) {
                    if (this.a.g.g() == d.PAUSED) {
                        this.a.l = true;
                    } else if (this.a.g.g() == d.STARTED) {
                        this.a.k = true;
                    }
                    c.c(this.a, this.a.l);
                }
            }
        };
        this.c = new com.facebook.ads.internal.s.a(this.e, this.d);
        g();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() {
        float f = u.b;
        int i = (int) (2.0f * f);
        int i2 = (int) (f * 25.0f);
        b hVar = new h(this.i);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        hVar.setPadding(i, i2, i2, i);
        hVar.setLayoutParams(layoutParams);
        for (i = 0; i < ((ViewGroup) this.e).getChildCount(); i++) {
            View childAt = ((ViewGroup) this.e).getChildAt(0);
            if (childAt instanceof j) {
                this.g = (j) childAt;
                break;
            }
        }
        if (this.g != null) {
            this.g.a(this.b);
            this.g.a(hVar);
        }
        this.c.a(0);
        this.c.b(250);
    }

    static /* synthetic */ boolean g(c cVar) {
        return (cVar.g == null || cVar.g.g() == d.PLAYBACK_COMPLETED || cVar.o != l.ON) ? false : true;
    }

    private void h() {
        if (this.e.getVisibility() == 0 && this.j && this.e.hasWindowFocus()) {
            this.c.a();
            return;
        }
        if (this.g != null && this.g.g() == d.PAUSED) {
            this.l = true;
        }
        this.c.c();
    }

    public final void a() {
        this.o = l.DEFAULT;
        if (this.g != null) {
            ((com.facebook.ads.internal.view.f.d) this.g.o()).setViewImplInflationListener(null);
        }
    }

    public final void a(e eVar, @Nullable a aVar) {
        this.k = false;
        this.l = false;
        this.h = aVar;
        if (this.g != null) {
            ((com.facebook.ads.internal.view.f.d) this.g.o()).setViewImplInflationListener(this.f);
        }
        g gVar = this.b;
        String a = (eVar == null || eVar.e() == null) ? null : eVar.e().a();
        gVar.a(a, new e(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public final void a(boolean z) {
                this.a.m.set(z);
                if (this.a.n.get() && this.a.h != null) {
                    this.a.h.a(z);
                }
            }
        });
        this.o = eVar.q();
        this.c.a();
    }

    public final void b() {
        if (this.g != null) {
            this.g.o().setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (this.a.g != null && motionEvent.getAction() == 1) {
                        this.a.g.B();
                    }
                    return true;
                }
            });
        }
    }

    public final void c() {
        this.j = true;
        h();
    }

    public final void d() {
        this.j = false;
        h();
    }

    public final void e() {
        h();
    }

    public final void f() {
        h();
    }

    static /* synthetic */ void a(c cVar, com.facebook.ads.internal.view.f.a.a aVar) {
        if (cVar.g != null) {
            cVar.g.a(aVar);
        } else {
            com.facebook.ads.internal.t.a.f();
        }
    }

    static /* synthetic */ void c(c cVar, boolean z) {
        if (cVar.g != null) {
            cVar.g.a(z);
        } else {
            com.facebook.ads.internal.t.a.f();
        }
    }
}
