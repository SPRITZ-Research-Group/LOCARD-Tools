package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.q.a.u;

public class h extends LinearLayout {
    private static final float a = Resources.getSystem().getDisplayMetrics().density;
    private static final int b = ((int) (6.0f * a));
    private static final int c = ((int) (8.0f * a));
    private final TextView d;
    private final TextView e;

    public h(Context context, d dVar, boolean z, boolean z2, boolean z3) {
        super(context);
        setOrientation(1);
        this.d = new TextView(context);
        u.a(this.d, true, z2 ? 18 : 22);
        this.d.setTextColor(dVar.c(z));
        this.d.setEllipsize(TruncateAt.END);
        this.d.setLineSpacing((float) b, 1.0f);
        this.e = new TextView(context);
        u.a(this.e, false, z2 ? 14 : 16);
        this.e.setTextColor(dVar.b(z));
        this.e.setEllipsize(TruncateAt.END);
        this.e.setLineSpacing((float) b, 1.0f);
        addView(this.d, new LayoutParams(-1, -2));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.setMargins(0, z3 ? c / 2 : c, 0, 0);
        addView(this.e, layoutParams);
    }

    public final void a(String str, String str2, boolean z, boolean z2) {
        CharSequence str22;
        int i = 0;
        int i2 = 1;
        int i3 = !TextUtils.isEmpty(str) ? 1 : 0;
        if (!TextUtils.isEmpty(str22)) {
            i = 1;
        }
        TextView textView = this.d;
        if (i3 == 0) {
            Object str3 = str22;
        }
        textView.setText(str3);
        textView = this.e;
        if (i3 == 0) {
            str22 = "";
        }
        textView.setText(str22);
        if (i3 == 0 || i == 0) {
            TextView textView2 = this.d;
            i3 = z ? 2 : z2 ? 4 : 3;
            textView2.setMaxLines(i3);
            return;
        }
        this.d.setMaxLines(z ? 1 : 2);
        TextView textView3 = this.e;
        if (!z) {
            i2 = z2 ? 3 : 2;
        }
        textView3.setMaxLines(i2);
    }

    public void setAlignment(int i) {
        this.d.setGravity(i);
        this.e.setGravity(i);
    }
}
