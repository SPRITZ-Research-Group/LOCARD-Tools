package com.facebook.react.d;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import javax.annotation.Nullable;

public final class a implements b {
    private volatile int a = -1;
    @Nullable
    private ViewParent b;

    public final void a(int tag, @Nullable ViewParent viewParentBlockingNativeResponder) {
        this.a = tag;
        b();
        if (viewParentBlockingNativeResponder != null) {
            viewParentBlockingNativeResponder.requestDisallowInterceptTouchEvent(true);
            this.b = viewParentBlockingNativeResponder;
        }
    }

    public final void a() {
        this.a = -1;
        b();
    }

    private void b() {
        if (this.b != null) {
            this.b.requestDisallowInterceptTouchEvent(false);
            this.b = null;
        }
    }

    public final boolean a(ViewGroup v, MotionEvent event) {
        int currentJSResponder = this.a;
        if (currentJSResponder == -1 || event.getAction() == 1) {
            return false;
        }
        if (v.getId() == currentJSResponder) {
            return true;
        }
        return false;
    }
}
