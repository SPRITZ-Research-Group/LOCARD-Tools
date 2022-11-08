package com.facebook.ads.internal.r;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private static final Map<g, h> a;

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put(g.RECTANGLE_HEIGHT_250, h.WEBVIEW_BANNER_250);
        a.put(g.BANNER_HEIGHT_90, h.WEBVIEW_BANNER_90);
        a.put(g.BANNER_HEIGHT_50, h.WEBVIEW_BANNER_50);
    }

    public static void a(DisplayMetrics displayMetrics, View view, g gVar) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) displayMetrics.widthPixels) / displayMetrics.density)) >= gVar.a() ? displayMetrics.widthPixels : (int) Math.ceil((double) (((float) gVar.a()) * displayMetrics.density)), (int) Math.ceil((double) (((float) gVar.b()) * displayMetrics.density)));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }
}
