package com.facebook.ads.internal.view.component.a.a;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.c.a.a;

public class d extends b {
    private static final int c = ((int) (20.0f * u.b));
    private static final int d = ((int) (16.0f * u.b));

    d(com.facebook.ads.internal.view.component.a.d dVar, com.facebook.ads.internal.adapters.a.d dVar2, String str, a aVar) {
        super(dVar, dVar2, false, str, aVar);
    }

    protected final void a(Context context) {
        View m = m();
        m.setAlignment(3);
        m.setLayoutParams(new LayoutParams(-1, -2));
        m.setPadding(0, 0, 0, c);
        n().setLayoutParams(new LayoutParams(-1, -2));
        View linearLayout = new LinearLayout(context);
        u.a(linearLayout, new ColorDrawable(-1));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(3, e().getId());
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(d, d, d, d);
        linearLayout.addView(m);
        linearLayout.addView(n());
        addView(e());
        addView(linearLayout);
    }

    protected final boolean k() {
        return false;
    }
}
