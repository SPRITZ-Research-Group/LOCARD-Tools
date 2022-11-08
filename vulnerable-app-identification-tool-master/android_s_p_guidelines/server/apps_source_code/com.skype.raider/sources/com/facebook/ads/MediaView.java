package com.facebook.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.a;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.j;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.b;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.b.e;
import com.facebook.ads.internal.view.k;

public class MediaView extends g {
    private static final String a = MediaView.class.getSimpleName();
    private static final int b = Color.argb(51, 145, 150, 165);
    private b c;
    private com.facebook.ads.internal.view.hscroll.b d;
    private MediaViewVideoRenderer e;
    private View f;
    @Nullable
    private f g;
    private boolean h;
    private boolean i;
    private boolean j;

    public MediaView(Context context) {
        super(context);
        a(new b(context));
        a(new com.facebook.ads.internal.view.hscroll.b(context));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context));
        b();
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(new b(context, attributeSet));
        a(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet));
        b();
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(new b(context, attributeSet, i));
        a(new com.facebook.ads.internal.view.hscroll.b(context, attributeSet, i));
        setVideoRenderer(new DefaultMediaViewVideoRenderer(context, attributeSet, i));
        b();
    }

    private void a(b bVar) {
        if (this.h) {
            throw new IllegalStateException("Image renderer must be set before nativeAd.");
        }
        if (this.c != null) {
            removeView(this.c);
        }
        bVar.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) bVar, layoutParams);
        this.c = bVar;
    }

    private void a(com.facebook.ads.internal.view.hscroll.b bVar) {
        if (this.h) {
            throw new IllegalStateException("Carousel renderer must be set before nativeAd.");
        }
        if (this.d != null) {
            removeView(this.d);
        }
        float f = u.b;
        int round = Math.round(4.0f * f);
        int round2 = Math.round(f * 12.0f);
        bVar.setChildSpacing(round);
        bVar.setPadding(0, round2, 0, round2);
        bVar.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView((View) bVar, layoutParams);
        this.d = bVar;
    }

    private void b() {
        u.a((View) this, b);
        j.a(this, j.INTERNAL_AD_MEDIA);
        j.a(this.c, j.INTERNAL_AD_MEDIA);
        j.a(this.e, j.INTERNAL_AD_MEDIA);
        j.a(this.d, j.INTERNAL_AD_MEDIA);
        this.i = true;
    }

    protected final View a() {
        return this.f;
    }

    final void a(final g gVar) {
        int i;
        boolean i2;
        this.h = true;
        gVar.a(this);
        if (gVar.a() == null) {
            i2 = 0;
        } else {
            for (g g : gVar.a()) {
                if (g.g() == null) {
                    i2 = 0;
                    break;
                }
            }
            i2 = true;
        }
        if (i2 != 0) {
            this.f = this.d;
            this.c.setVisibility(8);
            this.c.a(null, null);
            this.e.setVisibility(8);
            this.e.a();
            bringChildToFront(this.d);
            this.d.setCurrentPosition(0);
            a pVar = new p(this.d, gVar.c().r());
            pVar.a(new p.a(this) {
                final /* synthetic */ MediaView b;

                public final void a() {
                    gVar.c().a(true, true);
                }
            });
            this.d.setAdapter(pVar);
            this.d.setVisibility(0);
            return;
        }
        i2 = VERSION.SDK_INT >= 14 && !TextUtils.isEmpty(gVar.c().n());
        if (i2) {
            gVar.c().a(this.j);
            this.f = this.e.c.o();
            this.c.setVisibility(8);
            this.c.a(null, null);
            this.d.setVisibility(8);
            this.d.setAdapter(null);
            bringChildToFront(this.e);
            this.e.a(gVar);
            this.e.setVisibility(0);
        } else if (gVar.g() != null) {
            this.f = this.c.a();
            this.e.setVisibility(8);
            this.e.a();
            this.d.setVisibility(8);
            this.d.setAdapter(null);
            bringChildToFront(this.c);
            this.c.setVisibility(0);
            d a = new d(this.c).a(getHeight(), getWidth());
            a.a = com.facebook.ads.internal.l.a.e(getContext());
            a.a(new e(this) {
                final /* synthetic */ MediaView b;

                public final void a(boolean z) {
                    gVar.c().a(z, true);
                }
            }).a(gVar.c().e().a());
        }
    }

    public void addView(View view) {
        if (!this.i) {
            super.addView(view);
        }
    }

    public void addView(View view, int i) {
        if (!this.i) {
            super.addView(view, i);
        }
    }

    public void addView(View view, int i, int i2) {
        if (!this.i) {
            super.addView(view, i, i2);
        }
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (!this.i) {
            super.addView(view, i, layoutParams);
        }
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (!this.i) {
            super.addView(view, layoutParams);
        }
    }

    public void bringChildToFront(View view) {
        if (view == this.d || view == this.e || view == this.c) {
            super.bringChildToFront(view);
        }
    }

    public void setListener(final f fVar) {
        this.g = fVar;
        if (fVar == null) {
            this.e.a(null);
        } else {
            this.e.a(new k(this) {
                final /* synthetic */ MediaView b;

                public final void a() {
                    this.b.e.c.l();
                }
            });
        }
    }

    public void setVideoRenderer(MediaViewVideoRenderer mediaViewVideoRenderer) {
        boolean z = true;
        if (this.h) {
            throw new IllegalStateException("Video renderer must be set before nativeAd.");
        }
        if (this.e != null) {
            removeView(this.e);
            this.e.c.s();
        }
        mediaViewVideoRenderer.c.setAdEventManager(com.facebook.ads.internal.m.d.a(getContext()));
        mediaViewVideoRenderer.setVisibility(8);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.i = false;
        addView((View) mediaViewVideoRenderer, layoutParams);
        this.i = true;
        this.e = mediaViewVideoRenderer;
        if (this.e instanceof DefaultMediaViewVideoRenderer) {
            z = false;
        }
        this.j = z;
    }
}
