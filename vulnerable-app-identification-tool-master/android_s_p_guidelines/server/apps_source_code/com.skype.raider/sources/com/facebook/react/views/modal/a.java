package com.facebook.react.views.modal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

final class a {
    private static final Point a = new Point();
    private static final Point b = new Point();
    private static final Point c = new Point();

    @TargetApi(16)
    public static Point a(Context context) {
        Display display = ((WindowManager) com.facebook.infer.annotation.a.a((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
        display.getCurrentSizeRange(a, b);
        display.getSize(c);
        boolean windowFullscreen = context.getTheme().obtainStyledAttributes(new int[]{16843277}).getBoolean(0, false);
        Resources resources = context.getResources();
        int statusBarId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = 0;
        if (windowFullscreen && statusBarId > 0) {
            statusBarHeight = (int) resources.getDimension(statusBarId);
        }
        if (c.x < c.y) {
            return new Point(a.x, b.y + statusBarHeight);
        }
        return new Point(b.x, a.y + statusBarHeight);
    }
}
