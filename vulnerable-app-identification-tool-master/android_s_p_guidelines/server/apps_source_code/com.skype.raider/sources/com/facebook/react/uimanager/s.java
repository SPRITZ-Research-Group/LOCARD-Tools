package com.facebook.react.uimanager;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class s {
    private static final Rect a = new Rect();

    public static void a(View view, Rect outputRect) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            outputRect.setEmpty();
            return;
        }
        if (parent instanceof r) {
            r clippingViewGroup = (r) parent;
            if (clippingViewGroup.e_()) {
                clippingViewGroup.a(a);
                if (a.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                    a.offset(-view.getLeft(), -view.getTop());
                    a.offset(view.getScrollX(), view.getScrollY());
                    outputRect.set(a);
                    return;
                }
                outputRect.setEmpty();
                return;
            }
        }
        view.getDrawingRect(outputRect);
    }
}
