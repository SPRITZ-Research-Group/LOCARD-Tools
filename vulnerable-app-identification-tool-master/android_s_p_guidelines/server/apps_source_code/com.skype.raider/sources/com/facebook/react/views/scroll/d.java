package com.facebook.react.views.scroll;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.n;
import com.facebook.react.uimanager.UIManagerModule;

public final class d {
    public static void a(ViewGroup scrollView) {
        a(scrollView, f.SCROLL);
    }

    public static void b(ViewGroup scrollView) {
        a(scrollView, f.BEGIN_DRAG);
    }

    public static void c(ViewGroup scrollView) {
        a(scrollView, f.END_DRAG);
    }

    public static void d(ViewGroup scrollView) {
        a(scrollView, f.MOMENTUM_BEGIN);
    }

    public static void e(ViewGroup scrollView) {
        a(scrollView, f.MOMENTUM_END);
    }

    public static void a(ViewGroup scrollView, int id) {
        ((UIManagerModule) ((ai) scrollView.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(g.a(scrollView.getId(), id));
    }

    private static void a(ViewGroup scrollView, f scrollEventType) {
        View contentView = scrollView.getChildAt(0);
        if (contentView != null) {
            ((UIManagerModule) ((ai) scrollView.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(e.a(scrollView.getId(), scrollEventType, scrollView.getScrollX(), scrollView.getScrollY(), contentView.getWidth(), contentView.getHeight(), scrollView.getWidth(), scrollView.getHeight()));
        }
    }

    public static int a(String jsOverScrollMode) {
        if (jsOverScrollMode == null || jsOverScrollMode.equals("auto")) {
            return 1;
        }
        if (jsOverScrollMode.equals("always")) {
            return 0;
        }
        if (jsOverScrollMode.equals("never")) {
            return 2;
        }
        throw new n("wrong overScrollMode: " + jsOverScrollMode);
    }
}
