package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.q.a.j;

public class l extends g {
    private s a;

    public l(Context context) {
        super(context);
        this.a = new s(context);
        this.a.setScaleType(ScaleType.CENTER_CROP);
        j.a(this.a, j.INTERNAL_AD_MEDIA);
        addView(this.a, new LayoutParams(-1, -1));
    }

    public final View a() {
        return this.a;
    }
}
