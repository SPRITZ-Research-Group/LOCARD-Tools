package com.facebook.fbui.textlayoutbuilder.b;

import android.os.Build.VERSION;
import android.text.Layout;
import android.text.StaticLayout;

public final class a {
    public static int a(Layout layout) {
        if (layout == null) {
            return 0;
        }
        int extra = 0;
        if (VERSION.SDK_INT < 20 && (layout instanceof StaticLayout)) {
            int above = layout.getLineAscent(layout.getLineCount() - 1);
            int below = layout.getLineDescent(layout.getLineCount() - 1);
            float ex = ((float) (below - above)) - ((((float) (below - above)) - layout.getSpacingAdd()) / layout.getSpacingMultiplier());
            extra = ex >= 0.0f ? (int) (((double) ex) + 0.5d) : -((int) (((double) (-ex)) + 0.5d));
        }
        return layout.getHeight() - extra;
    }
}
