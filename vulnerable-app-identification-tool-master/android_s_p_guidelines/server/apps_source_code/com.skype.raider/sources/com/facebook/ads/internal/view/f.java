package com.facebook.ads.internal.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.c.g;
import com.facebook.ads.internal.view.component.CircularProgressView;
import com.facebook.ads.internal.view.f.a.b;
import com.facebook.ads.internal.view.f.b.c;
import com.facebook.ads.internal.view.f.b.o;

public class f extends LinearLayout implements b {
    private static final float c = Resources.getSystem().getDisplayMetrics().density;
    private static final int d = ((int) (40.0f * c));
    private static final int e = ((int) (44.0f * c));
    private static final int f = ((int) (10.0f * c));
    private static final int g;
    private static final int h;
    private static final int i = ((g * 2) - f);
    private final o a = new o(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.q != null && this.a.r != 0 && this.a.m.isShown()) {
                float d = ((float) this.a.q.d()) / Math.min(((float) this.a.r) * 1000.0f, (float) this.a.q.f());
                this.a.setProgress(100.0f * d);
                if (d >= 1.0f) {
                    this.a.a(true);
                    this.a.q.a().b(this.a.a, this.a.b);
                }
            }
        }
    };
    private final c b = new c(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(d dVar) {
            if (this.a.q != null && this.a.r != 0 && this.a.m.isShown() && !this.a.t) {
                this.a.a(true);
                this.a.q.a().b(this.a.a, this.a.b);
            }
        }
    };
    private final ImageView j;
    private final FrameLayout k;
    private final ImageView l;
    private final CircularProgressView m;
    private final com.facebook.ads.internal.view.c.c n;
    private final PopupMenu o;
    @Nullable
    private a p;
    @Nullable
    private com.facebook.ads.internal.view.f.a q;
    private int r = 0;
    private boolean s = false;
    private boolean t = false;
    private OnDismissListener u;

    public interface a {
        void a();
    }

    static {
        int i = (int) (16.0f * c);
        g = i;
        h = i - f;
    }

    public f(Context context) {
        super(context);
        setGravity(16);
        if (VERSION.SDK_INT >= 14) {
            this.u = new OnDismissListener(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public final void onDismiss(PopupMenu popupMenu) {
                    this.a.s = false;
                }
            };
        }
        this.l = new ImageView(context);
        this.l.setPadding(f, f, f, f);
        this.l.setScaleType(ScaleType.FIT_CENTER);
        this.l.setImageBitmap(com.facebook.ads.internal.q.b.c.a(com.facebook.ads.internal.q.b.b.INTERSTITIAL_CLOSE));
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (this.a.p != null && this.a.t) {
                    this.a.p.a();
                }
            }
        });
        this.m = new CircularProgressView(context);
        this.m.setPadding(f, f, f, f);
        this.m.setProgress(0.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(h, h, i, h);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(e, e);
        this.k = new FrameLayout(context);
        this.k.setLayoutTransition(new LayoutTransition());
        this.k.addView(this.l, layoutParams2);
        this.k.addView(this.m, layoutParams2);
        addView(this.k, layoutParams);
        this.n = new com.facebook.ads.internal.view.c.c(context);
        layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        addView(this.n, layoutParams);
        this.j = new ImageView(context);
        this.j.setPadding(f, f, f, f);
        this.j.setScaleType(ScaleType.FIT_CENTER);
        this.j.setImageBitmap(com.facebook.ads.internal.q.b.c.a(com.facebook.ads.internal.q.b.b.INTERSTITIAL_AD_CHOICES));
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.o.show();
                this.a.s = true;
            }
        });
        this.o = new PopupMenu(context, this.j);
        this.o.getMenu().add("Ad Choices");
        layoutParams = new LinearLayout.LayoutParams(d, d);
        layoutParams.setMargins(0, g / 2, g / 2, g / 2);
        addView(this.j, layoutParams);
    }

    public final void a(com.facebook.ads.internal.adapters.a.d dVar, boolean z) {
        int a = dVar.a(z);
        this.n.a(dVar.g(z), a);
        this.j.setColorFilter(a);
        this.l.setColorFilter(a);
        this.m.a(android.support.v4.graphics.a.b(a, 77), a);
        if (z) {
            Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{-1778384896, 0});
            gradientDrawable.setCornerRadius(0.0f);
            u.a((View) this, gradientDrawable);
            return;
        }
        u.a((View) this, 0);
    }

    public final void a(final i iVar, final String str, int i) {
        this.r = i;
        this.n.setPageDetails(iVar);
        this.o.setOnMenuItemClickListener(new OnMenuItemClickListener(this) {
            final /* synthetic */ f c;

            public final boolean onMenuItemClick(MenuItem menuItem) {
                this.c.s = false;
                if (!TextUtils.isEmpty(iVar.c())) {
                    g gVar = new g();
                    g.a(this.c.getContext(), Uri.parse(iVar.c()), str);
                }
                return true;
            }
        });
        if (VERSION.SDK_INT >= 14) {
            this.o.setOnDismissListener(this.u);
        }
        a(i <= 0);
    }

    public final void a(com.facebook.ads.internal.view.f.a aVar) {
        this.q = aVar;
        this.q.a().a(this.a, this.b);
    }

    public final void a(boolean z) {
        int i = 0;
        this.t = z;
        this.k.setVisibility(0);
        this.m.setVisibility(z ? 4 : 0);
        ImageView imageView = this.l;
        if (!z) {
            i = 4;
        }
        imageView.setVisibility(i);
    }

    public final boolean a() {
        return this.t;
    }

    public final void b() {
        this.t = false;
        this.k.setVisibility(4);
        this.m.setVisibility(4);
        this.l.setVisibility(4);
    }

    public final void b(com.facebook.ads.internal.view.f.a aVar) {
        if (this.q != null) {
            this.q.a().b(this.a, this.b);
            this.q = null;
        }
    }

    public final void c() {
        this.n.setVisibility(4);
    }

    public final void d() {
        if (VERSION.SDK_INT >= 14) {
            this.o.setOnDismissListener(null);
        }
        this.o.dismiss();
        if (VERSION.SDK_INT >= 14) {
            this.o.setOnDismissListener(this.u);
        }
    }

    public final void e() {
        if (this.s && VERSION.SDK_INT >= 14) {
            this.o.show();
        }
    }

    public void setProgress(float f) {
        this.m.setProgressWithAnimation(f);
    }

    public void setShowPageDetails(boolean z) {
        this.n.setVisibility(z ? 0 : 4);
    }

    public void setToolbarListener(a aVar) {
        this.p = aVar;
    }
}
