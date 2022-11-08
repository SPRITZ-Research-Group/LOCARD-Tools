package com.facebook.ads.internal.view.f;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.d;
import com.facebook.ads.internal.view.f.b.h;
import com.facebook.ads.internal.view.f.b.j;
import com.facebook.ads.internal.view.f.b.l;
import com.facebook.ads.internal.view.f.b.n;
import com.facebook.ads.internal.view.f.b.p;
import com.facebook.ads.internal.view.f.b.r;
import com.facebook.ads.internal.view.f.b.s;
import com.facebook.ads.internal.view.f.b.t;
import com.facebook.ads.internal.view.f.b.v;
import com.facebook.ads.internal.view.f.b.x;
import com.facebook.ads.internal.view.f.b.y;
import com.facebook.ads.internal.view.f.c.g;
import com.facebook.ads.internal.view.f.d.c;
import com.facebook.ads.internal.view.f.d.e;
import java.util.ArrayList;
import java.util.List;

public class a extends RelativeLayout implements com.facebook.ads.internal.view.f.c.a, e {
    private static final l b = new l();
    private static final d c = new d();
    private static final r d = new r();
    private static final h e = new h();
    private static final s f = new s();
    private static final j g = new j();
    private static final v h = new v();
    private static final y i = new y();
    private static final x j = new x();
    protected final c a;
    private d k;
    private final List<b> l = new ArrayList();
    private final Handler m = new Handler();
    private final Handler n = new Handler();
    private final com.facebook.ads.internal.j.e<f, com.facebook.ads.internal.j.d> o = new com.facebook.ads.internal.j.e();
    private boolean p;
    private boolean q;
    private boolean r = false;
    private int s = 200;
    private final OnTouchListener t = new OnTouchListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.o.a(new t(view, motionEvent));
            return false;
        }
    };

    public a(Context context) {
        super(context);
        if (com.facebook.ads.internal.l.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.f.d.a(context);
        } else {
            this.a = new com.facebook.ads.internal.view.f.d.b(context);
        }
        B();
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (com.facebook.ads.internal.l.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.f.d.a(context, attributeSet);
        } else {
            this.a = new com.facebook.ads.internal.view.f.d.b(context, attributeSet);
        }
        B();
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (com.facebook.ads.internal.l.a.a(context)) {
            this.a = new com.facebook.ads.internal.view.f.d.a(context, attributeSet, i);
        } else {
            this.a = new com.facebook.ads.internal.view.f.d.b(context, attributeSet, i);
        }
        B();
    }

    private void B() {
        if (com.facebook.ads.internal.l.a.a(getContext()) && (this.a instanceof com.facebook.ads.internal.view.f.d.a)) {
            ((com.facebook.ads.internal.view.f.d.a) this.a).setTestMode(com.facebook.ads.internal.t.a.a(getContext()));
        }
        this.a.setRequestedVolume(1.0f);
        this.a.setVideoStateChangeListener(this);
        this.k = new d(getContext(), this.a);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.k, layoutParams);
        setOnTouchListener(this.t);
    }

    private void C() {
        this.m.postDelayed(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void run() {
                if (!this.a.p) {
                    this.a.o.a(new n(this.a.d()));
                    this.a.m.postDelayed(this, (long) this.a.s);
                }
            }
        }, (long) this.s);
    }

    @NonNull
    public final com.facebook.ads.internal.j.e<f, com.facebook.ads.internal.j.d> a() {
        return this.o;
    }

    public final void a(int i) {
        this.m.removeCallbacksAndMessages(null);
        this.a.a(i);
    }

    public final void a(final int i, final int i2) {
        this.n.post(new Runnable(this) {
            final /* synthetic */ a c;

            public final void run() {
                this.c.o.a(new p(i, i2));
            }
        });
        C();
    }

    public final void a(com.facebook.ads.internal.view.f.a.a aVar) {
        if (this.p && this.a.f() == com.facebook.ads.internal.view.f.d.d.PLAYBACK_COMPLETED) {
            this.p = false;
        }
        this.a.a(aVar);
    }

    public final void a(b bVar) {
        this.l.add(bVar);
    }

    public final void a(final com.facebook.ads.internal.view.f.d.d dVar) {
        final int d = d();
        final int f = f();
        this.n.post(new Runnable(this) {
            final /* synthetic */ a d;

            public final void run() {
                if (dVar == com.facebook.ads.internal.view.f.d.d.PREPARED) {
                    this.d.o.a(a.b);
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.ERROR) {
                    this.d.p = true;
                    this.d.o.a(a.c);
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.PLAYBACK_COMPLETED) {
                    this.d.p = true;
                    this.d.m.removeCallbacksAndMessages(null);
                    this.d.o.a(new com.facebook.ads.internal.view.f.b.b(d, f));
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.STARTED) {
                    this.d.o.a(a.g);
                    this.d.m.removeCallbacksAndMessages(null);
                    this.d.C();
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.PAUSED) {
                    this.d.o.a(a.e);
                    this.d.m.removeCallbacksAndMessages(null);
                } else if (dVar == com.facebook.ads.internal.view.f.d.d.IDLE) {
                    this.d.o.a(a.f);
                    this.d.m.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    public final void a(boolean z) {
        if (!t()) {
            this.a.a(z);
            this.r = z;
        }
    }

    public final void b() {
        for (b bVar : this.l) {
            if (bVar instanceof com.facebook.ads.internal.view.f.a.c) {
                com.facebook.ads.internal.view.f.a.c cVar = (com.facebook.ads.internal.view.f.a.c) bVar;
                if (cVar instanceof g) {
                    this.k.b(cVar);
                } else {
                    u.b(cVar);
                }
            }
            bVar.b(this);
        }
    }

    public final void c() {
        this.n.post(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.a().a(a.d);
            }
        });
        this.a.b();
    }

    public final int d() {
        return this.a.a();
    }

    public final long e() {
        return this.a.d();
    }

    public final int f() {
        return this.a.e();
    }

    public final com.facebook.ads.internal.view.f.d.d g() {
        return this.a.f();
    }

    public final com.facebook.ads.internal.view.f.a.a h() {
        return this.a.g();
    }

    public final int i() {
        return this.s;
    }

    public final void j() {
        this.a.c();
    }

    public final boolean k() {
        return com.facebook.ads.internal.l.a.a(getContext());
    }

    public final float l() {
        return this.a.l();
    }

    public final boolean m() {
        return this.q;
    }

    public final boolean n() {
        return this.a.h();
    }

    public final View o() {
        return this.k;
    }

    protected void onAttachedToWindow() {
        this.o.a(j);
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        this.o.a(i);
        super.onDetachedFromWindow();
    }

    public final int p() {
        return this.a.j();
    }

    public final int q() {
        return this.a.i();
    }

    protected final Handler r() {
        return this.n;
    }

    public final void s() {
        this.a.setVideoStateChangeListener(null);
        this.a.m();
    }

    public void setControlsAnchorView(View view) {
        if (this.a != null) {
            this.a.setControlsAnchorView(view);
        }
    }

    public void setIsFullScreen(boolean z) {
        this.q = z;
        this.a.setFullScreen(z);
    }

    public void setLayoutParams(LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public void setVideoMPD(@Nullable String str) {
        this.a.setVideoMPD(str);
    }

    public void setVideoProgressReportIntervalMs(int i) {
        this.s = i;
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            b();
        } else {
            for (b bVar : this.l) {
                if (bVar instanceof com.facebook.ads.internal.view.f.a.c) {
                    com.facebook.ads.internal.view.f.a.c cVar = (com.facebook.ads.internal.view.f.a.c) bVar;
                    if (cVar.getParent() == null) {
                        if (cVar instanceof g) {
                            this.k.a(cVar);
                        } else {
                            addView(cVar);
                        }
                    }
                }
                bVar.a(this);
            }
            this.a.setup(uri);
        }
        this.p = false;
    }

    public void setVideoURI(@Nullable String str) {
        setVideoURI(str != null ? Uri.parse(str) : null);
    }

    public void setVolume(float f) {
        this.a.setRequestedVolume(f);
        this.o.a(h);
    }

    public final boolean t() {
        return g() == com.facebook.ads.internal.view.f.d.d.PAUSED;
    }

    public final boolean u() {
        return t() && this.r;
    }
}
