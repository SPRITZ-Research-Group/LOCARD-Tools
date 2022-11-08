package com.facebook.react.views.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.events.e;
import com.facebook.react.uimanager.p;

public class ReactSwipeRefreshLayout extends SwipeRefreshLayout {
    private boolean m = false;
    private boolean n = false;
    private float o = 0.0f;
    private int p;
    private float q;
    private boolean r;

    public ReactSwipeRefreshLayout(ai reactContext) {
        super(reactContext);
        this.p = ViewConfiguration.get(reactContext).getScaledTouchSlop();
    }

    public void setRefreshing(boolean refreshing) {
        this.n = refreshing;
        if (this.m) {
            super.setRefreshing(refreshing);
        }
    }

    public void setProgressViewOffset(float offset) {
        this.o = offset;
        if (this.m) {
            int diameter = b();
            setProgressViewOffset(false, Math.round(p.a(offset)) - diameter, Math.round(p.a(64.0f + offset) - ((float) diameter)));
        }
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!this.m) {
            this.m = true;
            setProgressViewOffset(this.o);
            setRefreshing(this.n);
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean z;
        switch (ev.getAction()) {
            case 0:
                this.q = ev.getX();
                this.r = false;
            case 2:
                float abs = Math.abs(ev.getX() - this.q);
                if (this.r || abs > ((float) this.p)) {
                    this.r = true;
                    z = false;
                    break;
                }
        }
        z = true;
        if (!z || !super.onInterceptTouchEvent(ev)) {
            return false;
        }
        e.a(this, ev);
        return true;
    }
}
