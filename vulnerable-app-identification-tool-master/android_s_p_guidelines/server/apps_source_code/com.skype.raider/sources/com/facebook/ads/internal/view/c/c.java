package com.facebook.ads.internal.view.c;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.i;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.b.d;
import com.facebook.ads.internal.view.component.e;

public class c extends LinearLayout {
    private e a;
    private TextView b;
    private TextView c;

    public c(Context context) {
        super(context);
        int i = (int) (32.0f * u.b);
        setGravity(16);
        this.a = new e(context);
        this.a.setFullCircleCorners(true);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.setMargins(0, 0, (int) (8.0f * u.b), 0);
        addView(this.a, layoutParams);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        this.b = new TextView(context);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        u.a(this.b, true, 16);
        this.b.setEllipsize(TruncateAt.END);
        this.b.setSingleLine(true);
        this.c = new TextView(context);
        u.a(this.c, false, 14);
        linearLayout.addView(this.b);
        linearLayout.addView(this.c);
        addView(linearLayout, layoutParams);
    }

    public final void a(int i, int i2) {
        this.b.setTextColor(i);
        this.c.setTextColor(i2);
    }

    public void setPageDetails(i iVar) {
        d dVar = new d(this.a);
        dVar.a((int) (u.b * 32.0f), (int) (u.b * 32.0f));
        dVar.a(iVar.b());
        this.b.setText(iVar.a());
        this.c.setText(iVar.d());
    }
}
