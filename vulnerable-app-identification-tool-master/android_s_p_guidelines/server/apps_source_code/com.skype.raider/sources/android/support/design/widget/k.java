package android.support.design.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.adjust.sdk.Constants;

abstract class k<V extends View> extends x<V> {
    private Runnable a;
    private android.support.v4.widget.k b;
    private boolean c;
    private int d = -1;
    private int e;
    private int f = -1;
    private VelocityTracker g;

    private class a implements Runnable {
        final /* synthetic */ k a;
        private final CoordinatorLayout b;
        private final V c;

        a(k kVar, CoordinatorLayout parent, V layout) {
            this.a = kVar;
            this.b = parent;
            this.c = layout;
        }

        public final void run() {
            if (this.c != null && this.a.b != null && this.a.b.b()) {
                this.a.c(this.b, this.c, this.a.b.a());
                ViewCompat.a(this.c, (Runnable) this);
            }
        }
    }

    public k(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public final boolean a(CoordinatorLayout parent, V child, MotionEvent ev) {
        if (this.f < 0) {
            this.f = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        if (ev.getAction() == 2 && this.c) {
            return true;
        }
        int y;
        switch (ev.getActionMasked()) {
            case 0:
                this.c = false;
                int x = (int) ev.getX();
                y = (int) ev.getY();
                if (b() && parent.a((View) child, x, y)) {
                    this.e = y;
                    this.d = ev.getPointerId(0);
                    d();
                    break;
                }
            case 1:
            case 3:
                this.c = false;
                this.d = -1;
                if (this.g != null) {
                    this.g.recycle();
                    this.g = null;
                    break;
                }
                break;
            case 2:
                int activePointerId = this.d;
                if (activePointerId != -1) {
                    int pointerIndex = ev.findPointerIndex(activePointerId);
                    if (pointerIndex != -1) {
                        y = (int) ev.getY(pointerIndex);
                        if (Math.abs(y - this.e) > this.f) {
                            this.c = true;
                            this.e = y;
                            break;
                        }
                    }
                }
                break;
        }
        if (this.g != null) {
            this.g.addMovement(ev);
        }
        return this.c;
    }

    public final boolean b(CoordinatorLayout parent, V child, MotionEvent ev) {
        if (this.f < 0) {
            this.f = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        }
        int y;
        switch (ev.getActionMasked()) {
            case 0:
                y = (int) ev.getY();
                if (parent.a((View) child, (int) ev.getX(), y) && b()) {
                    this.e = y;
                    this.d = ev.getPointerId(0);
                    d();
                    break;
                }
                return false;
                break;
            case 1:
                if (this.g != null) {
                    this.g.addMovement(ev);
                    this.g.computeCurrentVelocity(Constants.ONE_SECOND);
                    a(parent, child, -a((View) child), this.g.getYVelocity(this.d));
                    break;
                }
                break;
            case 2:
                int activePointerIndex = ev.findPointerIndex(this.d);
                if (activePointerIndex != -1) {
                    y = (int) ev.getY(activePointerIndex);
                    int dy = this.e - y;
                    if (!this.c && Math.abs(dy) > this.f) {
                        this.c = true;
                        dy = dy > 0 ? dy - this.f : dy + this.f;
                    }
                    if (this.c) {
                        this.e = y;
                        b(parent, child, dy, b(child), 0);
                        break;
                    }
                }
                return false;
                break;
            case 3:
                break;
        }
        this.c = false;
        this.d = -1;
        if (this.g != null) {
            this.g.recycle();
            this.g = null;
        }
        if (this.g != null) {
            this.g.addMovement(ev);
        }
        return true;
    }

    final int c(CoordinatorLayout parent, V header, int newOffset) {
        return a(parent, header, newOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int a(CoordinatorLayout parent, V v, int newOffset, int minOffset, int maxOffset) {
        int curOffset = c();
        if (minOffset == 0 || curOffset < minOffset || curOffset > maxOffset) {
            return 0;
        }
        newOffset = m.a(newOffset, minOffset, maxOffset);
        if (curOffset == newOffset) {
            return 0;
        }
        a(newOffset);
        return curOffset - newOffset;
    }

    int a() {
        return c();
    }

    final int b(CoordinatorLayout coordinatorLayout, V header, int dy, int minOffset, int maxOffset) {
        return a(coordinatorLayout, header, a() - dy, minOffset, maxOffset);
    }

    final boolean a(CoordinatorLayout coordinatorLayout, V layout, int minOffset, float velocityY) {
        if (this.a != null) {
            layout.removeCallbacks(this.a);
            this.a = null;
        }
        if (this.b == null) {
            this.b = android.support.v4.widget.k.a(layout.getContext());
        }
        this.b.a(c(), Math.round(velocityY), minOffset);
        if (!this.b.b()) {
            return false;
        }
        this.a = new a(this, coordinatorLayout, layout);
        ViewCompat.a((View) layout, this.a);
        return true;
    }

    boolean b() {
        return false;
    }

    int b(V view) {
        return -view.getHeight();
    }

    int a(V view) {
        return view.getHeight();
    }

    private void d() {
        if (this.g == null) {
            this.g = VelocityTracker.obtain();
        }
    }
}
