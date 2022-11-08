package com.facebook.ads.internal.view.f.c;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.j.d;
import com.facebook.ads.internal.j.f;
import com.facebook.ads.internal.view.f.a.c;
import com.facebook.ads.internal.view.f.b.l;

public class k extends c {
    private final f<l> a;

    public k(Context context) {
        this(context, null);
    }

    public k(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new f<l>(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public final Class<l> a() {
                return l.class;
            }

            public final /* synthetic */ void a(d dVar) {
                this.a.setVisibility(8);
            }
        };
        int applyDimension = (int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        View progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.SRC_IN);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(applyDimension, applyDimension);
        layoutParams.addRule(13);
        addView(progressBar, layoutParams);
    }

    protected final void b() {
        super.b();
        setVisibility(0);
        if (a() != null) {
            a().a().a(this.a);
        }
    }

    protected final void c() {
        if (a() != null) {
            a().a().b(this.a);
        }
        setVisibility(8);
        super.c();
    }
}
