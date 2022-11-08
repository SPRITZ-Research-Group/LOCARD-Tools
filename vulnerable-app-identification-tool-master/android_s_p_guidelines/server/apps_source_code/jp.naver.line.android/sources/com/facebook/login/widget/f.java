package com.facebook.login.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.login.p;
import com.facebook.login.q;
import java.lang.ref.WeakReference;

public final class f {
    private final String a;
    private final WeakReference<View> b;
    private final Context c;
    private g d;
    private PopupWindow e;
    private h f = h.BLUE;
    private long g = 6000;
    private final OnScrollChangedListener h = new OnScrollChangedListener(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public final void onScrollChanged() {
            if (!(this.a.b.get() == null || this.a.e == null || !this.a.e.isShowing())) {
                if (this.a.e.isAboveAnchor()) {
                    this.a.d.b();
                    return;
                }
                this.a.d.a();
            }
        }
    };

    public f(String str, View view) {
        this.a = str;
        this.b = new WeakReference(view);
        this.c = view.getContext();
    }

    public final void a(h hVar) {
        this.f = hVar;
    }

    public final void a() {
        if (this.b.get() != null) {
            this.d = new g(this, this.c);
            ((TextView) this.d.findViewById(q.com_facebook_tooltip_bubble_view_text_body)).setText(this.a);
            if (this.f == h.BLUE) {
                this.d.d.setBackgroundResource(p.com_facebook_tooltip_blue_background);
                this.d.c.setImageResource(p.com_facebook_tooltip_blue_bottomnub);
                this.d.b.setImageResource(p.com_facebook_tooltip_blue_topnub);
                this.d.e.setImageResource(p.com_facebook_tooltip_blue_xout);
            } else {
                this.d.d.setBackgroundResource(p.com_facebook_tooltip_black_background);
                this.d.c.setImageResource(p.com_facebook_tooltip_black_bottomnub);
                this.d.b.setImageResource(p.com_facebook_tooltip_black_topnub);
                this.d.e.setImageResource(p.com_facebook_tooltip_black_xout);
            }
            View decorView = ((Activity) this.c).getWindow().getDecorView();
            int width = decorView.getWidth();
            int height = decorView.getHeight();
            c();
            if (this.b.get() != null) {
                ((View) this.b.get()).getViewTreeObserver().addOnScrollChangedListener(this.h);
            }
            this.d.measure(MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE));
            this.e = new PopupWindow(this.d, this.d.getMeasuredWidth(), this.d.getMeasuredHeight());
            this.e.showAsDropDown((View) this.b.get());
            if (this.e != null && this.e.isShowing()) {
                if (this.e.isAboveAnchor()) {
                    this.d.b();
                } else {
                    this.d.a();
                }
            }
            if (this.g > 0) {
                this.d.postDelayed(new Runnable(this) {
                    final /* synthetic */ f a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        this.a.b();
                    }
                }, this.g);
            }
            this.e.setTouchable(true);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public final void onClick(View view) {
                    this.a.b();
                }
            });
        }
    }

    public final void a(long j) {
        this.g = j;
    }

    public final void b() {
        c();
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    private void c() {
        if (this.b.get() != null) {
            ((View) this.b.get()).getViewTreeObserver().removeOnScrollChangedListener(this.h);
        }
    }
}
