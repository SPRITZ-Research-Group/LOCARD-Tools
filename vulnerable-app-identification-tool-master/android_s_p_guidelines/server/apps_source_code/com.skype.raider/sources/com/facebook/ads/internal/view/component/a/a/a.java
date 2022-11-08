package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.component.a.d;

public class a extends b {
    private static final int c = ((int) (12.0f * u.b));

    a(d dVar, com.facebook.ads.internal.adapters.a.d dVar2, String str, com.facebook.ads.internal.view.c.a.a aVar) {
        super(dVar, dVar2, true, str, aVar);
    }

    protected final void a(Context context) {
        View m = m();
        m.setAlignment(3);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(8, e().getId());
        m.setLayoutParams(layoutParams);
        m.setPadding(c, c, c, c);
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        u.a(m, gradientDrawable);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, e().getId());
        n().setLayoutParams(layoutParams);
        addView(e());
        addView(m);
        addView(n());
    }

    protected final boolean b() {
        return false;
    }

    protected final boolean c() {
        return false;
    }
}
