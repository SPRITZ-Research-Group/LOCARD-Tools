package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.view.component.f;

final class d extends f {
    private final ImageView a;

    public d(Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a.setAdjustViewBounds(true);
        addView(this.a, new LayoutParams(-2, -1));
    }

    public final void a(String str) {
        com.facebook.ads.internal.view.b.d dVar = new com.facebook.ads.internal.view.b.d(this.a);
        dVar.a();
        dVar.a(str);
    }
}
