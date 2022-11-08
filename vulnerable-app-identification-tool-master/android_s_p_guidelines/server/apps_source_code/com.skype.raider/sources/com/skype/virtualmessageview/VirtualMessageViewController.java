package com.skype.virtualmessageview;

import android.content.Context;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.am;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.p;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.view.ReactViewGroup;

public class VirtualMessageViewController extends ReactViewGroup {
    private ReactScrollView a;
    private ReactViewGroup b;
    private a c;
    private boolean d = false;
    private final OnLayoutChangeListener e = new OnLayoutChangeListener(this) {
        final /* synthetic */ VirtualMessageViewController a;

        {
            this.a = this$0;
        }

        public final void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            VirtualMessageViewController.a(this.a, "contentView");
        }
    };
    private final OnLayoutChangeListener f = new OnLayoutChangeListener(this) {
        final /* synthetic */ VirtualMessageViewController a;

        {
            this.a = this$0;
        }

        public final void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            VirtualMessageViewController.a(this.a, "scrollView");
        }
    };

    private class a {
        final int a;
        final am b;
        final /* synthetic */ VirtualMessageViewController c;

        a(VirtualMessageViewController virtualMessageViewController, int deltaScroll, am repositionData) {
            this.c = virtualMessageViewController;
            this.a = deltaScroll;
            this.b = repositionData;
        }
    }

    public VirtualMessageViewController(Context context) {
        super(context);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = (ReactViewGroup) getParent();
        this.b.addOnLayoutChangeListener(this.e);
        this.a = (ReactScrollView) this.b.getParent();
        this.a.addOnLayoutChangeListener(this.f);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.removeOnLayoutChangeListener(this.f);
        this.b.removeOnLayoutChangeListener(this.e);
        this.a = null;
        this.b = null;
    }

    public final void a(int deltaScroll, am repositionData, boolean hasTopProperty) {
        if (this.b == null || this.a == null) {
            FLog.w("VirtualMessageViewController", "scrollBy is called on detached VirtualMessageViewController");
            e();
            return;
        }
        this.d = hasTopProperty;
        int maxScrollPosition = Math.max(0, this.b.getMeasuredHeight() - this.a.getMeasuredHeight());
        int newScrollPosition = this.a.getScrollY() + deltaScroll;
        if (newScrollPosition < 0) {
            FLog.i("VirtualMessageViewController", "Cannot scroll to less than 0, adjusting to 0");
            newScrollPosition = 0;
        }
        if (newScrollPosition > maxScrollPosition + 1) {
            FLog.i("VirtualMessageViewController", "Postponing scroll by " + p.b((float) deltaScroll));
            this.c = new a(this, deltaScroll, repositionData);
        } else if (newScrollPosition >= maxScrollPosition) {
            a(maxScrollPosition, repositionData);
        } else {
            a(newScrollPosition, repositionData);
        }
    }

    private void a(int newScrollPosition, am repositionData) {
        this.c = null;
        FLog.i("VirtualMessageViewController", "Scrolling to " + p.b((float) newScrollPosition));
        if (newScrollPosition != this.a.getScrollY()) {
            this.a.scrollTo(this.a.getScrollX(), newScrollPosition);
            this.a.smoothScrollBy(0, 0);
        }
        if (repositionData != null) {
            a(repositionData);
        }
        e();
    }

    private void a(am repositionData) {
        ReactViewGroup innerWrapper = (ReactViewGroup) this.b.getChildAt(1);
        StringBuilder logline = new StringBuilder("adjustCells " + innerWrapper.getChildCount() + "; ");
        for (int i = 0; i < innerWrapper.getChildCount(); i++) {
            View child = innerWrapper.getChildAt(i);
            float position = this.d ? (float) child.getTop() : child.getTranslationY();
            String keyId = String.valueOf(child.getId());
            if (repositionData.hasKey(keyId)) {
                float newPosition = p.a((float) repositionData.getInt(keyId));
                if (this.d) {
                    child.setTop(Math.round(newPosition));
                } else {
                    child.setTranslationY(newPosition);
                }
                logline.append(child.getId()).append(' ').append(p.b(position)).append(" -> ").append(p.b(newPosition)).append("; ");
            }
        }
        FLog.i("VirtualMessageViewController", logline.toString());
    }

    private void e() {
        ((UIManagerModule) ((ai) getContext()).b(UIManagerModule.class)).getEventDispatcher().a(ScrollPositionSetEvent.b(getId()));
    }

    static /* synthetic */ void a(VirtualMessageViewController x0, String x1) {
        if (x0.c != null) {
            int max = Math.max(0, x0.b.getMeasuredHeight() - x0.a.getMeasuredHeight());
            int scrollY = x0.a.getScrollY() + x0.c.a;
            if (scrollY <= max) {
                FLog.i("VirtualMessageViewController", x1 + " - Executing postponed scroll by " + p.b((float) x0.c.a));
                x0.a(scrollY, x0.c.b);
            } else if (scrollY <= max + 1) {
                FLog.i("VirtualMessageViewController", x1 + " - Executing postponed scroll with margin by " + p.b((float) x0.c.a));
                x0.a(max, x0.c.b);
            } else {
                FLog.i("VirtualMessageViewController", x1 + " - Ignoring event newScrollY:" + scrollY + " maxScrollPosition:" + max);
            }
        }
    }
}
