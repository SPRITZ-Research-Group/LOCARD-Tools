package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewParent;
import com.facebook.infer.annotation.a;

public final class ab {
    public static aa a(View reactView) {
        View current = reactView;
        while (!(current instanceof aa)) {
            ViewParent next = current.getParent();
            if (next == null) {
                return null;
            }
            a.a(next instanceof View);
            current = (View) next;
        }
        return (aa) current;
    }
}
