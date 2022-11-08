package com.facebook.ads.internal.view.component.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.component.h;

final class f extends RelativeLayout {
    private final View a;
    private final com.facebook.ads.internal.view.component.f b;

    public f(Context context, View view) {
        super(context);
        this.a = view;
        this.b = new com.facebook.ads.internal.view.component.f(context);
        u.a(this.b);
    }

    public final void a(int i) {
        this.a.setLayoutParams(new LayoutParams(-1, i));
    }

    public final void a(@Nullable View view, @Nullable View view2, @Nullable h hVar, boolean z) {
        this.b.addView(this.a, new LayoutParams(-1, -2));
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(b.b, b.b);
            layoutParams.addRule(6, this.a.getId());
            layoutParams.addRule(7, this.a.getId());
            layoutParams.setMargins(b.a, b.a, b.a, b.a);
            this.b.addView(view2, layoutParams);
        }
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(8, this.a.getId());
        if (hVar != null) {
            ViewGroup.LayoutParams layoutParams3;
            if (z) {
                layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                hVar.setAlignment(3);
                layoutParams3.setMargins(b.a / 2, b.a / 2, b.a / 2, b.a / 2);
                linearLayout.addView(hVar, layoutParams3);
                Drawable gradientDrawable = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                gradientDrawable.setCornerRadius(0.0f);
                gradientDrawable.setGradientType(0);
                u.a(linearLayout, gradientDrawable);
            } else {
                layoutParams3 = new LayoutParams(-1, -2);
                layoutParams3.addRule(3, this.b.getId());
                layoutParams3.setMargins(0, b.a, 0, 0);
                hVar.setAlignment(17);
                addView(hVar, layoutParams3);
            }
        }
        if (view != null) {
            linearLayout.addView(view, new LayoutParams(-1, -2));
        }
        this.b.addView(linearLayout, layoutParams2);
        addView(this.b, new LayoutParams(-1, -2));
    }
}
