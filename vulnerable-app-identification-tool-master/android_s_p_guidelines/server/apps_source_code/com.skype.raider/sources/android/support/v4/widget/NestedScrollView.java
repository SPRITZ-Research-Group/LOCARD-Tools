package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.f;
import android.support.v4.view.h;
import android.support.v4.view.i;
import android.support.v4.view.j;
import android.support.v4.view.l;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.List;

public class NestedScrollView extends FrameLayout implements h, j {
    private static final a w = new a();
    private static final int[] x = new int[]{16843130};
    private float A;
    private b B;
    private long a;
    private final Rect b;
    private OverScroller c;
    private EdgeEffect d;
    private EdgeEffect e;
    private int f;
    private boolean g;
    private boolean h;
    private View i;
    private boolean j;
    private VelocityTracker k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private final int[] r;
    private final int[] s;
    private int t;
    private int u;
    private SavedState v;
    private final l y;
    private final i z;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        public int a;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel source) {
            super(source);
            this.a = source.readInt();
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.a);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.a + "}";
        }
    }

    static class a extends android.support.v4.view.a {
        a() {
        }

        public final boolean a(View host, int action, Bundle arguments) {
            if (super.a(host, action, arguments)) {
                return true;
            }
            NestedScrollView nsvHost = (NestedScrollView) host;
            if (!nsvHost.isEnabled()) {
                return false;
            }
            int targetScrollY;
            switch (action) {
                case 4096:
                    targetScrollY = Math.min(nsvHost.getScrollY() + ((nsvHost.getHeight() - nsvHost.getPaddingBottom()) - nsvHost.getPaddingTop()), nsvHost.a());
                    if (targetScrollY == nsvHost.getScrollY()) {
                        return false;
                    }
                    nsvHost.a(targetScrollY);
                    return true;
                case 8192:
                    targetScrollY = Math.max(nsvHost.getScrollY() - ((nsvHost.getHeight() - nsvHost.getPaddingBottom()) - nsvHost.getPaddingTop()), 0);
                    if (targetScrollY == nsvHost.getScrollY()) {
                        return false;
                    }
                    nsvHost.a(targetScrollY);
                    return true;
                default:
                    return false;
            }
        }

        public final void a(View host, android.support.v4.view.accessibility.b info) {
            super.a(host, info);
            NestedScrollView nsvHost = (NestedScrollView) host;
            info.b(ScrollView.class.getName());
            if (nsvHost.isEnabled()) {
                int scrollRange = nsvHost.a();
                if (scrollRange > 0) {
                    info.i(true);
                    if (nsvHost.getScrollY() > 0) {
                        info.a(8192);
                    }
                    if (nsvHost.getScrollY() < scrollRange) {
                        info.a(4096);
                    }
                }
            }
        }

        public final void a(View host, AccessibilityEvent event) {
            super.a(host, event);
            NestedScrollView nsvHost = (NestedScrollView) host;
            event.setClassName(ScrollView.class.getName());
            event.setScrollable(nsvHost.a() > 0);
            event.setScrollX(nsvHost.getScrollX());
            event.setScrollY(nsvHost.getScrollY());
            f.a(event, nsvHost.getScrollX());
            f.b(event, nsvHost.a());
        }
    }

    public interface b {
        void a(NestedScrollView nestedScrollView);
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    public NestedScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.b = new Rect();
        this.g = true;
        this.h = false;
        this.i = null;
        this.j = false;
        this.m = true;
        this.q = -1;
        this.r = new int[2];
        this.s = new int[2];
        this.c = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.n = viewConfiguration.getScaledTouchSlop();
        this.o = viewConfiguration.getScaledMinimumFlingVelocity();
        this.p = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray a = context.obtainStyledAttributes(attrs, x, defStyleAttr, 0);
        setFillViewport(a.getBoolean(0, false));
        a.recycle();
        this.y = new l(this);
        this.z = new i(this);
        setNestedScrollingEnabled(true);
        ViewCompat.a((View) this, w);
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.z.a(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.z.a();
    }

    public boolean startNestedScroll(int axes) {
        return this.z.b(axes);
    }

    private boolean b(int type) {
        return this.z.a(2, type);
    }

    public void stopNestedScroll() {
        this.z.c();
    }

    private void c(int type) {
        this.z.c(type);
    }

    public boolean hasNestedScrollingParent() {
        return this.z.b();
    }

    private boolean d(int type) {
        return this.z.a(type);
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return this.z.a(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    private boolean a(int dyConsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
        return this.z.a(0, dyConsumed, 0, dyUnconsumed, offsetInWindow, type);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return this.z.a(dx, dy, consumed, offsetInWindow);
    }

    private boolean a(int dy, int[] consumed, int[] offsetInWindow, int type) {
        return this.z.a(0, dy, consumed, offsetInWindow, type);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.z.a(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.z.a(velocityX, velocityY);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & 2) != 0;
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        this.y.a(nestedScrollAxes);
        startNestedScroll(2);
    }

    public void onStopNestedScroll(View target) {
        this.y.b();
        stopNestedScroll();
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int oldScrollY = getScrollY();
        scrollBy(0, dyUnconsumed);
        int myConsumed = getScrollY() - oldScrollY;
        dispatchNestedScroll(0, myConsumed, 0, dyUnconsumed - myConsumed, null);
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        dispatchNestedPreScroll(dx, dy, consumed, null);
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        if (consumed) {
            return false;
        }
        h((int) velocityY);
        return true;
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public int getNestedScrollAxes() {
        return this.y.a();
    }

    public boolean shouldDelayChildPressedState() {
        return true;
    }

    protected float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int length = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < length) {
            return ((float) scrollY) / ((float) length);
        }
        return 1.0f;
    }

    protected float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int length = getVerticalFadingEdgeLength();
        int span = (getChildAt(0).getBottom() - getScrollY()) - (getHeight() - getPaddingBottom());
        if (span < length) {
            return ((float) span) / ((float) length);
        }
        return 1.0f;
    }

    public void addView(View child) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(child);
    }

    public void addView(View child, int index) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(child, index);
    }

    public void addView(View child, LayoutParams params) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(child, params);
    }

    public void addView(View child, int index, LayoutParams params) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(child, index, params);
    }

    public void setOnScrollChangeListener(b l) {
        this.B = l;
    }

    public void setFillViewport(boolean fillViewport) {
        if (fillViewport != this.l) {
            this.l = fillViewport;
            requestLayout();
        }
    }

    public void setSmoothScrollingEnabled(boolean smoothScrollingEnabled) {
        this.m = smoothScrollingEnabled;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.B != null) {
            this.B.a(this);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.l && MeasureSpec.getMode(heightMeasureSpec) != 0 && getChildCount() > 0) {
            View child = getChildAt(0);
            int height = getMeasuredHeight();
            if (child.getMeasuredHeight() < height) {
                child.measure(getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight(), ((FrameLayout.LayoutParams) child.getLayoutParams()).width), MeasureSpec.makeMeasureSpec((height - getPaddingTop()) - getPaddingBottom(), ErrorDialogData.SUPPRESSED));
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || a(event);
    }

    public final boolean a(KeyEvent event) {
        boolean z;
        int i = 33;
        boolean z2 = true;
        this.b.setEmpty();
        View childAt = getChildAt(0);
        if (childAt != null) {
            z = getHeight() < (childAt.getHeight() + getPaddingTop()) + getPaddingBottom();
        } else {
            z = false;
        }
        if (z) {
            boolean handled = false;
            if (event.getAction() == 0) {
                switch (event.getKeyCode()) {
                    case 19:
                        if (!event.isAltPressed()) {
                            handled = f(33);
                            break;
                        }
                        handled = e(33);
                        break;
                    case 20:
                        if (!event.isAltPressed()) {
                            handled = f(130);
                            break;
                        }
                        handled = e(130);
                        break;
                    case 62:
                        if (!event.isShiftPressed()) {
                            i = 130;
                        }
                        if (i != 130) {
                            z2 = false;
                        }
                        int height = getHeight();
                        if (z2) {
                            this.b.top = getScrollY() + height;
                            int childCount = getChildCount();
                            if (childCount > 0) {
                                View childAt2 = getChildAt(childCount - 1);
                                if (this.b.top + height > childAt2.getBottom()) {
                                    this.b.top = childAt2.getBottom() - height;
                                }
                            }
                        } else {
                            this.b.top = getScrollY() - height;
                            if (this.b.top < 0) {
                                this.b.top = 0;
                            }
                        }
                        this.b.bottom = this.b.top + height;
                        a(i, this.b.top, this.b.bottom);
                        break;
                }
            }
            return handled;
        } else if (!isFocused() || event.getKeyCode() == 4) {
            return false;
        } else {
            View currentFocused = findFocus();
            if (currentFocused == this) {
                currentFocused = null;
            }
            View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, 130);
            if (nextFocused == null || nextFocused == this || !nextFocused.requestFocus(130)) {
                return false;
            }
            return true;
        }
    }

    private void b() {
        if (this.k == null) {
            this.k = VelocityTracker.obtain();
        }
    }

    private void c() {
        if (this.k != null) {
            this.k.recycle();
            this.k = null;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (disallowIntercept) {
            c();
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean z = true;
        int action = ev.getAction();
        if (action == 2 && this.j) {
            return true;
        }
        int y;
        switch (action & 255) {
            case 0:
                boolean z2;
                y = (int) ev.getY();
                int x = (int) ev.getX();
                if (getChildCount() > 0) {
                    int scrollY = getScrollY();
                    View childAt = getChildAt(0);
                    z2 = y >= childAt.getTop() - scrollY && y < childAt.getBottom() - scrollY && x >= childAt.getLeft() && x < childAt.getRight();
                } else {
                    z2 = false;
                }
                if (!z2) {
                    this.j = false;
                    c();
                    break;
                }
                this.f = y;
                this.q = ev.getPointerId(0);
                if (this.k == null) {
                    this.k = VelocityTracker.obtain();
                } else {
                    this.k.clear();
                }
                this.k.addMovement(ev);
                this.c.computeScrollOffset();
                if (this.c.isFinished()) {
                    z = false;
                }
                this.j = z;
                b(0);
                break;
                break;
            case 1:
            case 3:
                this.j = false;
                this.q = -1;
                c();
                if (this.c.springBack(getScrollX(), getScrollY(), 0, 0, 0, a())) {
                    ViewCompat.d(this);
                }
                c(0);
                break;
            case 2:
                int activePointerId = this.q;
                if (activePointerId != -1) {
                    int pointerIndex = ev.findPointerIndex(activePointerId);
                    if (pointerIndex != -1) {
                        y = (int) ev.getY(pointerIndex);
                        if (Math.abs(y - this.f) > this.n && (getNestedScrollAxes() & 2) == 0) {
                            this.j = true;
                            this.f = y;
                            b();
                            this.k.addMovement(ev);
                            this.t = 0;
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                                break;
                            }
                        }
                    }
                    new StringBuilder("Invalid pointerId=").append(activePointerId).append(" in onInterceptTouchEvent");
                    break;
                }
                break;
            case 6:
                a(ev);
                break;
        }
        return this.j;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        b();
        MotionEvent vtev = MotionEvent.obtain(ev);
        int actionMasked = ev.getActionMasked();
        if (actionMasked == 0) {
            this.t = 0;
        }
        vtev.offsetLocation(0.0f, (float) this.t);
        ViewParent parent;
        switch (actionMasked) {
            case 0:
                if (getChildCount() != 0) {
                    boolean z = !this.c.isFinished();
                    this.j = z;
                    if (z) {
                        parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                    if (!this.c.isFinished()) {
                        this.c.abortAnimation();
                    }
                    this.f = (int) ev.getY();
                    this.q = ev.getPointerId(0);
                    b(0);
                    break;
                }
                return false;
            case 1:
                VelocityTracker velocityTracker = this.k;
                velocityTracker.computeCurrentVelocity(Constants.ONE_SECOND, (float) this.p);
                int initialVelocity = (int) velocityTracker.getYVelocity(this.q);
                if (Math.abs(initialVelocity) > this.o) {
                    h(-initialVelocity);
                } else if (this.c.springBack(getScrollX(), getScrollY(), 0, 0, 0, a())) {
                    ViewCompat.d(this);
                }
                this.q = -1;
                d();
                break;
            case 2:
                int activePointerIndex = ev.findPointerIndex(this.q);
                if (activePointerIndex != -1) {
                    int y = (int) ev.getY(activePointerIndex);
                    int deltaY = this.f - y;
                    if (a(deltaY, this.s, this.r, 0)) {
                        deltaY -= this.s[1];
                        vtev.offsetLocation(0.0f, (float) this.r[1]);
                        this.t += this.r[1];
                    }
                    if (!this.j && Math.abs(deltaY) > this.n) {
                        parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        this.j = true;
                        if (deltaY > 0) {
                            deltaY -= this.n;
                        } else {
                            deltaY += this.n;
                        }
                    }
                    if (this.j) {
                        this.f = y - this.r[1];
                        int oldY = getScrollY();
                        int range = a();
                        int overscrollMode = getOverScrollMode();
                        boolean canOverscroll = overscrollMode == 0 || (overscrollMode == 1 && range > 0);
                        if (a(deltaY, 0, getScrollY(), range) && !d(0)) {
                            this.k.clear();
                        }
                        int scrolledDeltaY = getScrollY() - oldY;
                        if (!a(scrolledDeltaY, deltaY - scrolledDeltaY, this.r, 0)) {
                            if (canOverscroll) {
                                e();
                                int pulledToY = oldY + deltaY;
                                if (pulledToY < 0) {
                                    f.a(this.d, ((float) deltaY) / ((float) getHeight()), ev.getX(activePointerIndex) / ((float) getWidth()));
                                    if (!this.e.isFinished()) {
                                        this.e.onRelease();
                                    }
                                } else if (pulledToY > range) {
                                    f.a(this.e, ((float) deltaY) / ((float) getHeight()), 1.0f - (ev.getX(activePointerIndex) / ((float) getWidth())));
                                    if (!this.d.isFinished()) {
                                        this.d.onRelease();
                                    }
                                }
                                if (!(this.d == null || (this.d.isFinished() && this.e.isFinished()))) {
                                    ViewCompat.d(this);
                                    break;
                                }
                            }
                        }
                        this.f -= this.r[1];
                        vtev.offsetLocation(0.0f, (float) this.r[1]);
                        this.t += this.r[1];
                        break;
                    }
                }
                new StringBuilder("Invalid pointerId=").append(this.q).append(" in onTouchEvent");
                break;
                break;
            case 3:
                if (this.j && getChildCount() > 0 && this.c.springBack(getScrollX(), getScrollY(), 0, 0, 0, a())) {
                    ViewCompat.d(this);
                }
                this.q = -1;
                d();
                break;
            case 5:
                int index = ev.getActionIndex();
                this.f = (int) ev.getY(index);
                this.q = ev.getPointerId(index);
                break;
            case 6:
                a(ev);
                this.f = (int) ev.getY(ev.findPointerIndex(this.q));
                break;
        }
        if (this.k != null) {
            this.k.addMovement(vtev);
        }
        vtev.recycle();
        return true;
    }

    private void a(MotionEvent ev) {
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.q) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.f = (int) ev.getY(newPointerIndex);
            this.q = ev.getPointerId(newPointerIndex);
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        if ((event.getSource() & 2) != 0) {
            switch (event.getAction()) {
                case 8:
                    if (!this.j) {
                        float vscroll = event.getAxisValue(9);
                        if (vscroll != 0.0f) {
                            if (this.A == 0.0f) {
                                TypedValue typedValue = new TypedValue();
                                Context context = getContext();
                                if (context.getTheme().resolveAttribute(16842829, typedValue, true)) {
                                    this.A = typedValue.getDimension(context.getResources().getDisplayMetrics());
                                } else {
                                    throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
                                }
                            }
                            int delta = (int) (this.A * vscroll);
                            int range = a();
                            int oldScrollY = getScrollY();
                            int newScrollY = oldScrollY - delta;
                            if (newScrollY < 0) {
                                newScrollY = 0;
                            } else if (newScrollY > range) {
                                newScrollY = range;
                            }
                            if (newScrollY != oldScrollY) {
                                super.scrollTo(getScrollX(), newScrollY);
                                return true;
                            }
                        }
                    }
                    break;
            }
        }
        return false;
    }

    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.scrollTo(scrollX, scrollY);
    }

    private boolean a(int deltaY, int scrollX, int scrollY, int scrollRangeY) {
        getOverScrollMode();
        computeHorizontalScrollRange();
        computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        computeVerticalScrollExtent();
        int newScrollX = scrollX + 0;
        int newScrollY = scrollY + deltaY;
        int bottom = scrollRangeY + 0;
        boolean clampedX = false;
        if (newScrollX > 0) {
            newScrollX = 0;
            clampedX = true;
        } else if (newScrollX < 0) {
            newScrollX = 0;
            clampedX = true;
        }
        boolean clampedY = false;
        if (newScrollY > bottom) {
            newScrollY = bottom;
            clampedY = true;
        } else if (newScrollY < 0) {
            newScrollY = 0;
            clampedY = true;
        }
        if (clampedY && !d(1)) {
            this.c.springBack(newScrollX, newScrollY, 0, 0, 0, a());
        }
        onOverScrolled(newScrollX, newScrollY, clampedX, clampedY);
        if (clampedX || clampedY) {
            return true;
        }
        return false;
    }

    final int a() {
        if (getChildCount() > 0) {
            return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
        }
        return 0;
    }

    private boolean e(int direction) {
        boolean down;
        if (direction == 130) {
            down = true;
        } else {
            down = false;
        }
        int height = getHeight();
        this.b.top = 0;
        this.b.bottom = height;
        if (down) {
            int count = getChildCount();
            if (count > 0) {
                View view = getChildAt(count - 1);
                this.b.bottom = view.getBottom() + getPaddingBottom();
                this.b.top = this.b.bottom - height;
            }
        }
        return a(direction, this.b.top, this.b.bottom);
    }

    private boolean a(int direction, int top, int bottom) {
        boolean up;
        boolean handled = true;
        int height = getHeight();
        int containerTop = getScrollY();
        int containerBottom = containerTop + height;
        if (direction == 33) {
            up = true;
        } else {
            up = false;
        }
        List focusables = getFocusables(2);
        View newFocused = null;
        Object obj = null;
        int size = focusables.size();
        int i = 0;
        while (i < size) {
            Object obj2;
            View view = (View) focusables.get(i);
            int top2 = view.getTop();
            int bottom2 = view.getBottom();
            if (top < bottom2 && top2 < bottom) {
                Object obj3 = (top >= top2 || bottom2 >= bottom) ? null : 1;
                if (newFocused == null) {
                    newFocused = view;
                    obj2 = obj3;
                } else {
                    Object obj4 = ((!up || top2 >= newFocused.getTop()) && (up || bottom2 <= newFocused.getBottom())) ? null : 1;
                    if (obj != null) {
                        if (!(obj3 == null || obj4 == null)) {
                            newFocused = view;
                            obj2 = obj;
                        }
                    } else if (obj3 != null) {
                        newFocused = view;
                        int obj22 = 1;
                    } else if (obj4 != null) {
                        newFocused = view;
                        obj22 = obj;
                    }
                }
                i++;
                obj = obj22;
            }
            obj22 = obj;
            i++;
            obj = obj22;
        }
        if (newFocused == null) {
            newFocused = this;
        }
        if (top < containerTop || bottom > containerBottom) {
            g(up ? top - containerTop : bottom - containerBottom);
        } else {
            handled = false;
        }
        if (newFocused != findFocus()) {
            newFocused.requestFocus(direction);
        }
        return handled;
    }

    private boolean f(int direction) {
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        }
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        int maxJump = (int) (0.5f * ((float) getHeight()));
        if (nextFocused == null || !a(nextFocused, maxJump, getHeight())) {
            int scrollDelta = maxJump;
            if (direction == 33 && getScrollY() < scrollDelta) {
                scrollDelta = getScrollY();
            } else if (direction == 130 && getChildCount() > 0) {
                int daBottom = getChildAt(0).getBottom();
                int screenBottom = (getScrollY() + getHeight()) - getPaddingBottom();
                if (daBottom - screenBottom < maxJump) {
                    scrollDelta = daBottom - screenBottom;
                }
            }
            if (scrollDelta == 0) {
                return false;
            }
            int i;
            if (direction == 130) {
                i = scrollDelta;
            } else {
                i = -scrollDelta;
            }
            g(i);
        } else {
            nextFocused.getDrawingRect(this.b);
            offsetDescendantRectToMyCoords(nextFocused, this.b);
            g(a(this.b));
            nextFocused.requestFocus(direction);
        }
        if (currentFocused != null && currentFocused.isFocused() && a(currentFocused)) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    private boolean a(View descendant) {
        return !a(descendant, 0, getHeight());
    }

    private boolean a(View descendant, int delta, int height) {
        descendant.getDrawingRect(this.b);
        offsetDescendantRectToMyCoords(descendant, this.b);
        return this.b.bottom + delta >= getScrollY() && this.b.top - delta <= getScrollY() + height;
    }

    private void g(int delta) {
        if (delta == 0) {
            return;
        }
        if (this.m) {
            a(0, delta);
        } else {
            scrollBy(0, delta);
        }
    }

    private void a(int dx, int dy) {
        if (getChildCount() != 0) {
            if (AnimationUtils.currentAnimationTimeMillis() - this.a > 250) {
                int maxY = Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
                int scrollY = getScrollY();
                this.c.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(scrollY + dy, maxY)) - scrollY);
                ViewCompat.d(this);
            } else {
                if (!this.c.isFinished()) {
                    this.c.abortAnimation();
                }
                scrollBy(dx, dy);
            }
            this.a = AnimationUtils.currentAnimationTimeMillis();
        }
    }

    public final void a(int y) {
        a(0 - getScrollX(), y - getScrollY());
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int computeVerticalScrollRange() {
        int contentHeight = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (getChildCount() == 0) {
            return contentHeight;
        }
        int scrollRange = getChildAt(0).getBottom();
        int scrollY = getScrollY();
        int overscrollBottom = Math.max(0, scrollRange - contentHeight);
        if (scrollY < 0) {
            scrollRange -= scrollY;
        } else if (scrollY > overscrollBottom) {
            scrollRange += scrollY - overscrollBottom;
        }
        return scrollRange;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight(), child.getLayoutParams().width), MeasureSpec.makeMeasureSpec(0, 0));
    }

    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, (((getPaddingLeft() + getPaddingRight()) + lp.leftMargin) + lp.rightMargin) + widthUsed, lp.width), MeasureSpec.makeMeasureSpec(lp.topMargin + lp.bottomMargin, 0));
    }

    public void computeScroll() {
        int i = 0;
        if (this.c.computeScrollOffset()) {
            this.c.getCurrX();
            int y = this.c.getCurrY();
            int dy = y - this.u;
            if (a(dy, this.s, null, 1)) {
                dy -= this.s[1];
            }
            if (dy != 0) {
                int range = a();
                int oldScrollY = getScrollY();
                a(dy, getScrollX(), oldScrollY, range);
                int scrolledDeltaY = getScrollY() - oldScrollY;
                if (!a(scrolledDeltaY, dy - scrolledDeltaY, null, 1)) {
                    int mode = getOverScrollMode();
                    if (mode == 0 || (mode == 1 && range > 0)) {
                        i = 1;
                    }
                    if (i != 0) {
                        e();
                        if (y <= 0 && oldScrollY > 0) {
                            this.d.onAbsorb((int) this.c.getCurrVelocity());
                        } else if (y >= range && oldScrollY < range) {
                            this.e.onAbsorb((int) this.c.getCurrVelocity());
                        }
                    }
                }
            }
            this.u = y;
            ViewCompat.d(this);
            return;
        }
        if (d(1)) {
            c(1);
        }
        this.u = 0;
    }

    private void b(View child) {
        child.getDrawingRect(this.b);
        offsetDescendantRectToMyCoords(child, this.b);
        int scrollDelta = a(this.b);
        if (scrollDelta != 0) {
            scrollBy(0, scrollDelta);
        }
    }

    private int a(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int screenTop = getScrollY();
        int screenBottom = screenTop + height;
        int fadingEdge = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            screenTop += fadingEdge;
        }
        if (rect.bottom < getChildAt(0).getHeight()) {
            screenBottom -= fadingEdge;
        }
        int scrollYDelta;
        if (rect.bottom > screenBottom && rect.top > screenTop) {
            if (rect.height() > height) {
                scrollYDelta = (rect.top - screenTop) + 0;
            } else {
                scrollYDelta = (rect.bottom - screenBottom) + 0;
            }
            return Math.min(scrollYDelta, getChildAt(0).getBottom() - screenBottom);
        } else if (rect.top >= screenTop || rect.bottom >= screenBottom) {
            return 0;
        } else {
            if (rect.height() > height) {
                scrollYDelta = 0 - (screenBottom - rect.bottom);
            } else {
                scrollYDelta = 0 - (screenTop - rect.top);
            }
            return Math.max(scrollYDelta, -getScrollY());
        }
    }

    public void requestChildFocus(View child, View focused) {
        if (this.g) {
            this.i = focused;
        } else {
            b(focused);
        }
        super.requestChildFocus(child, focused);
    }

    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        View nextFocus;
        if (direction == 2) {
            direction = 130;
        } else if (direction == 1) {
            direction = 33;
        }
        if (previouslyFocusedRect == null) {
            nextFocus = FocusFinder.getInstance().findNextFocus(this, null, direction);
        } else {
            nextFocus = FocusFinder.getInstance().findNextFocusFromRect(this, previouslyFocusedRect, direction);
        }
        if (nextFocus == null || a(nextFocus)) {
            return false;
        }
        return nextFocus.requestFocus(direction, previouslyFocusedRect);
    }

    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        rectangle.offset(child.getLeft() - child.getScrollX(), child.getTop() - child.getScrollY());
        int a = a(rectangle);
        boolean z = a != 0;
        if (z) {
            if (immediate) {
                scrollBy(0, a);
            } else {
                a(0, a);
            }
        }
        return z;
    }

    public void requestLayout() {
        this.g = true;
        super.requestLayout();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.g = false;
        if (this.i != null && a(this.i, (View) this)) {
            b(this.i);
        }
        this.i = null;
        if (!this.h) {
            if (this.v != null) {
                scrollTo(getScrollX(), this.v.a);
                this.v = null;
            }
            int scrollRange = Math.max(0, (getChildCount() > 0 ? getChildAt(0).getMeasuredHeight() : 0) - (((b - t) - getPaddingBottom()) - getPaddingTop()));
            if (getScrollY() > scrollRange) {
                scrollTo(getScrollX(), scrollRange);
            } else if (getScrollY() < 0) {
                scrollTo(getScrollX(), 0);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.h = true;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = false;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        View currentFocused = findFocus();
        if (currentFocused != null && this != currentFocused && a(currentFocused, 0, oldh)) {
            currentFocused.getDrawingRect(this.b);
            offsetDescendantRectToMyCoords(currentFocused, this.b);
            g(a(this.b));
        }
    }

    private static boolean a(View child, View parent) {
        if (child == parent) {
            return true;
        }
        ViewParent theParent = child.getParent();
        if ((theParent instanceof ViewGroup) && a((View) theParent, parent)) {
            return true;
        }
        return false;
    }

    private void h(int velocityY) {
        boolean canFling;
        int scrollY = getScrollY();
        if ((scrollY > 0 || velocityY > 0) && (scrollY < a() || velocityY < 0)) {
            canFling = true;
        } else {
            canFling = false;
        }
        if (!dispatchNestedPreFling(0.0f, (float) velocityY)) {
            dispatchNestedFling(0.0f, (float) velocityY, canFling);
            if (getChildCount() > 0) {
                b(1);
                this.c.fling(getScrollX(), getScrollY(), 0, velocityY, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
                this.u = getScrollY();
                ViewCompat.d(this);
            }
        }
    }

    private void d() {
        this.j = false;
        c();
        c(0);
        if (this.d != null) {
            this.d.onRelease();
            this.e.onRelease();
        }
    }

    public void scrollTo(int x, int y) {
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            x = b(x, (getWidth() - getPaddingRight()) - getPaddingLeft(), child.getWidth());
            y = b(y, (getHeight() - getPaddingBottom()) - getPaddingTop(), child.getHeight());
            if (x != getScrollX() || y != getScrollY()) {
                super.scrollTo(x, y);
            }
        }
    }

    private void e() {
        if (getOverScrollMode() == 2) {
            this.d = null;
            this.e = null;
        } else if (this.d == null) {
            Context context = getContext();
            this.d = new EdgeEffect(context);
            this.e = new EdgeEffect(context);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.d != null) {
            int restoreCount;
            int width;
            int scrollY = getScrollY();
            if (!this.d.isFinished()) {
                restoreCount = canvas.save();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate((float) getPaddingLeft(), (float) Math.min(0, scrollY));
                this.d.setSize(width, getHeight());
                if (this.d.draw(canvas)) {
                    ViewCompat.d(this);
                }
                canvas.restoreToCount(restoreCount);
            }
            if (!this.e.isFinished()) {
                restoreCount = canvas.save();
                width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                int height = getHeight();
                canvas.translate((float) ((-width) + getPaddingLeft()), (float) (Math.max(a(), scrollY) + height));
                canvas.rotate(180.0f, (float) width, 0.0f);
                this.e.setSize(width, height);
                if (this.e.draw(canvas)) {
                    ViewCompat.d(this);
                }
                canvas.restoreToCount(restoreCount);
            }
        }
    }

    private static int b(int n, int my, int child) {
        if (my >= child || n < 0) {
            return 0;
        }
        if (my + n > child) {
            return child - my;
        }
        return n;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.v = ss;
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.a = getScrollY();
        return ss;
    }
}
