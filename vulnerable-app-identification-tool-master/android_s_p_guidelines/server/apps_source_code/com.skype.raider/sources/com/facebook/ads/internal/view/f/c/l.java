package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.f.a.a;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.d.d;

public class l extends c {
    private final i a;
    private final k b;
    private final com.facebook.ads.internal.view.f.b.c c;
    private final m d;
    private final Paint e;

    /* renamed from: com.facebook.ads.internal.view.f.c.l$5 */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[d.values().length];

        static {
            try {
                a[d.PREPARED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[d.IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[d.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[d.PLAYBACK_COMPLETED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[d.STARTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public l(Context context) {
        this(context, false);
    }

    public l(Context context, boolean z) {
        super(context);
        this.a = new i(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                this.a.d.setChecked(true);
            }
        };
        this.b = new k(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                this.a.d.setChecked(false);
            }
        };
        this.c = new com.facebook.ads.internal.view.f.b.c(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(com.facebook.ads.internal.j.d dVar) {
                this.a.d.setChecked(true);
            }
        };
        this.d = new m(context, z);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 23.76d), (int) (((double) displayMetrics.density) * 23.76d));
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
        this.d.setChecked(true);
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        if (z) {
            this.e.setColor(-1728053248);
        } else {
            this.e.setColor(-1);
            this.e.setAlpha(204);
        }
        u.a((View) this, 0);
        addView(this.d);
        setGravity(17);
        layoutParams = new RelativeLayout.LayoutParams((int) (((double) displayMetrics.density) * 72.0d), (int) (((double) displayMetrics.density) * 72.0d));
        layoutParams.addRule(13);
        setLayoutParams(layoutParams);
    }

    protected final void b() {
        super.b();
        if (a() != null) {
            a().a().a(this.a, this.b, this.c);
        }
        OnClickListener anonymousClass4 = new OnClickListener(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                if (this.a.a() != null) {
                    switch (AnonymousClass5.a[this.a.a().g().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            this.a.a().a(a.USER_STARTED);
                            return;
                        case 5:
                            this.a.a().a(true);
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.d.setClickable(false);
        setOnClickListener(anonymousClass4);
    }

    protected final void c() {
        setOnClickListener(null);
        if (a() != null) {
            a().a().b(this.c, this.b, this.a);
        }
        super.c();
    }

    protected void onDraw(Canvas canvas) {
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom()) / 2;
        canvas.drawCircle((float) (getPaddingLeft() + min), (float) (getPaddingTop() + min), (float) min, this.e);
        super.onDraw(canvas);
    }
}
