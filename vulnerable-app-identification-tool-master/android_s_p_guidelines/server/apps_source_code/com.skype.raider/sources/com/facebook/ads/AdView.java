package com.facebook.ads;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.r.f;
import com.facebook.ads.internal.r.g;
import com.facebook.ads.internal.r.i;

public class AdView extends RelativeLayout {
    private static final f a = f.ADS;
    private final DisplayMetrics b;
    private final g c;
    private b d;
    private View e;

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.e != null) {
            i.a(this.b, this.e, this.c);
        }
    }

    public void setAdListener(b bVar) {
        this.d = bVar;
    }
}
