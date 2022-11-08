package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.k;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.a.s;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.component.e;
import com.facebook.ads.internal.view.component.h;
import com.facebook.ads.internal.view.f.b.z;
import java.util.HashMap;

public class a extends LinearLayout {
    private static final int a = ((int) (12.0f * u.b));
    private static final int b = ((int) (16.0f * u.b));
    private final h c;
    private final ImageView d;
    private final RelativeLayout e;
    private final com.facebook.ads.internal.view.component.a f;
    private final int g;

    public a(Context context, int i, d dVar, c cVar, com.facebook.ads.internal.view.a.a aVar, boolean z, boolean z2, com.facebook.ads.internal.s.a aVar2, s sVar) {
        super(context);
        this.g = i;
        this.d = new e(context);
        u.a(this.d, 0);
        u.a(this.d);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(15);
        layoutParams.addRule(9);
        layoutParams.setMargins(0, 0, a, 0);
        if (z2) {
            this.d.setVisibility(8);
        }
        this.c = new h(context, dVar, true, z, true);
        this.c.setAlignment(8388611);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, this.d.getId());
        layoutParams2.addRule(15);
        this.f = new com.facebook.ads.internal.view.component.a(context, true, false, z.REWARDED_VIDEO_AD_CLICK.a(), dVar, cVar, aVar, aVar2, sVar);
        this.f.setVisibility(8);
        this.e = new RelativeLayout(context);
        u.a(this.e);
        this.e.addView(this.d, layoutParams);
        this.e.addView(this.c, layoutParams2);
        addView(this.e, new LinearLayout.LayoutParams(-2, -2));
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
        gradientDrawable.setCornerRadius(0.0f);
        u.a((View) this, gradientDrawable);
    }

    public final void a() {
        this.f.setVisibility(0);
    }

    public final void a(int i) {
        int i2 = -1;
        int i3 = 1;
        u.b(this.f);
        int i4 = i == 1 ? 1 : 0;
        if (i4 == 0) {
            i3 = 0;
        }
        setOrientation(i3);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i4 != 0 ? -1 : 0, -2);
        layoutParams.weight = 1.0f;
        if (i4 == 0) {
            i2 = -2;
        }
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, -2);
        layoutParams2.setMargins(i4 != 0 ? 0 : b, i4 != 0 ? b : 0, 0, 0);
        layoutParams2.gravity = 80;
        this.e.setLayoutParams(layoutParams);
        addView(this.f, layoutParams2);
    }

    public void setInfo(k kVar) {
        this.c.a(kVar.b().a(), kVar.b().b(), false, false);
        this.f.a(kVar.c(), kVar.g(), new HashMap());
        new com.facebook.ads.internal.view.b.d(this.d).a(this.g, this.g).a(kVar.a().b());
    }
}
