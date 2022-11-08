package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.view.f.a;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.i;
import com.facebook.ads.internal.view.f.b.k;
import com.facebook.ads.internal.view.f.b.m;

public class h extends c implements OnTouchListener {
    private final m a;
    private final i b;
    private final k c;
    private final com.facebook.ads.internal.view.f.b.c d;
    private final m e;

    public h(Context context) {
        this(context, null);
    }

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new m(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.setVisibility(0);
            }
        };
        this.b = new i(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.e.setChecked(true);
            }
        };
        this.c = new k(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.e.setChecked(false);
            }
        };
        this.d = new com.facebook.ads.internal.view.f.b.c(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.e.setChecked(true);
            }
        };
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.e = new m(context);
        this.e.setChecked(true);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (displayMetrics.density * 25.0f), (int) (displayMetrics.density * 25.0f));
        setVisibility(8);
        addView(this.e, layoutParams);
        setClickable(true);
        setFocusable(true);
    }

    protected final void b() {
        super.b();
        this.e.setOnTouchListener(this);
        setOnTouchListener(this);
        if (a() != null) {
            a().a().a(this.a, this.d, this.b, this.c);
        }
    }

    protected final void c() {
        if (a() != null) {
            a().a().b(this.c, this.b, this.d, this.a);
        }
        setOnTouchListener(null);
        this.e.setOnTouchListener(null);
        super.c();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        a a = a();
        if (a == null) {
            return false;
        }
        if (a.g() == com.facebook.ads.internal.view.f.d.d.PREPARED || a.g() == com.facebook.ads.internal.view.f.d.d.PAUSED || a.g() == com.facebook.ads.internal.view.f.d.d.PLAYBACK_COMPLETED) {
            a.a(com.facebook.ads.internal.view.f.a.a.USER_STARTED);
            return true;
        } else if (a.g() != com.facebook.ads.internal.view.f.d.d.STARTED) {
            return false;
        } else {
            a.a(true);
            return false;
        }
    }
}
