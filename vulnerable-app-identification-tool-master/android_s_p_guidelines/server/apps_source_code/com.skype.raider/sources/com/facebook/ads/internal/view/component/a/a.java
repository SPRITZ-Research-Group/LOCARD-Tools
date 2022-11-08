package com.facebook.ads.internal.view.component.a;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.q.a.u;

public class a extends b {
    public a(d dVar, d dVar2, boolean z) {
        LayoutParams layoutParams;
        int i = 1;
        super(dVar, dVar2, true);
        View relativeLayout = new RelativeLayout(dVar.a());
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(12);
        Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
        gradientDrawable.setCornerRadius(0.0f);
        gradientDrawable.setGradientType(0);
        u.a(relativeLayout, gradientDrawable);
        View linearLayout = new LinearLayout(dVar.a());
        if (z) {
            i = 0;
        }
        linearLayout.setOrientation(i);
        linearLayout.setGravity(80);
        u.a(linearLayout);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(a, 0, a, dVar.h() == null ? a : a / 2);
        LayoutParams layoutParams4 = new LinearLayout.LayoutParams(z ? -2 : -1, -2);
        layoutParams4.setMargins(z ? a : 0, z ? 0 : a, 0, 0);
        LayoutParams layoutParams5 = new LinearLayout.LayoutParams(z ? 0 : -1, -2);
        layoutParams5.setMargins(0, 0, 0, 0);
        layoutParams5.weight = 1.0f;
        linearLayout.addView(m(), layoutParams5);
        linearLayout.addView(n(), layoutParams4);
        relativeLayout.addView(linearLayout, layoutParams3);
        if (dVar.h() != null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, 0);
            layoutParams.addRule(3, linearLayout.getId());
            relativeLayout.addView(dVar.h(), layoutParams);
        }
        addView(dVar.d(), new RelativeLayout.LayoutParams(-1, -1));
        addView(relativeLayout, layoutParams2);
        if (dVar.i() != null) {
            layoutParams = new RelativeLayout.LayoutParams(b, b);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            layoutParams.setMargins(a, a + dVar.j(), a, a);
            addView(dVar.i(), layoutParams);
        }
    }

    public final boolean a() {
        return true;
    }
}
