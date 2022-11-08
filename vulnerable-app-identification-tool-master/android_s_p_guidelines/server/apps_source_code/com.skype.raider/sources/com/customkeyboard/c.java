package com.customkeyboard;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public final class c {
    private Rect a = new Rect();

    public final int a(View referenceView) {
        ViewGroup rootView = (ViewGroup) referenceView.getRootView();
        ViewGroup viewGroup = (ViewGroup) rootView.getRootView();
        View childAt = viewGroup.getChildAt(0);
        for (int i = 1; i < viewGroup.getChildCount(); i++) {
            View childAt2 = viewGroup.getChildAt(i);
            if (childAt2.getHeight() > childAt.getHeight()) {
                childAt = childAt2;
            }
        }
        int navBarHeight = viewGroup.getBottom() - childAt.getBottom();
        rootView.getWindowVisibleDisplayFrame(this.a);
        int keyboardHeight = (rootView.getBottom() - this.a.bottom) - navBarHeight;
        return keyboardHeight < 0 ? 0 : keyboardHeight;
    }

    public static int[] b(View referenceView) {
        ViewGroup rootView = (ViewGroup) referenceView.getRootView();
        return new int[]{rootView.getWidth(), rootView.getHeight()};
    }
}
