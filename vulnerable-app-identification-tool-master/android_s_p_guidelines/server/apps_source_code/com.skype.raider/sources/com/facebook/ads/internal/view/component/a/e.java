package com.facebook.ads.internal.view.component.a;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;

public class e extends b {
    private static final int c = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final f d;

    public e(d dVar, boolean z, d dVar2) {
        super(dVar, dVar2, z);
        this.d = new f(dVar.a(), dVar.d());
        this.d.a(dVar.h(), dVar.i(), m(), z);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.setMargins(a, a, a, a);
        n().setLayoutParams(layoutParams);
        View frameLayout = new FrameLayout(dVar.a());
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(2, n().getId());
        frameLayout.setLayoutParams(layoutParams2);
        layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        layoutParams2.setMargins(a, 0, a, 0);
        frameLayout.addView(this.d, layoutParams2);
        addView(frameLayout);
        addView(n());
    }

    public final void a(h hVar, String str, double d) {
        super.a(hVar, str, d);
        if (d > 0.0d) {
            this.d.a((int) (((double) (c - (a * 2))) / d));
        }
    }

    public final boolean a() {
        return false;
    }

    protected final boolean c() {
        return false;
    }

    protected final boolean o() {
        return false;
    }
}
