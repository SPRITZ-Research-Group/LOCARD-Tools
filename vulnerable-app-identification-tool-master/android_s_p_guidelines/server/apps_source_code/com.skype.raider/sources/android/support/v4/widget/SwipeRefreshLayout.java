package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.g;
import android.support.v4.view.i;
import android.support.v4.view.j;
import android.support.v4.view.l;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class SwipeRefreshLayout extends ViewGroup implements g, j {
    private static final int[] D = new int[]{16842766};
    private static final String m = SwipeRefreshLayout.class.getSimpleName();
    private int A;
    private boolean B;
    private final DecelerateInterpolator C;
    private int E;
    private Animation F;
    private Animation G;
    private Animation H;
    private Animation I;
    private Animation J;
    private int K;
    private a L;
    private AnimationListener M;
    private final Animation N;
    private final Animation O;
    b a;
    boolean b;
    int c;
    boolean d;
    b e;
    protected int f;
    float g;
    protected int h;
    int i;
    CircularProgressDrawable j;
    boolean k;
    boolean l;
    private View n;
    private int o;
    private float p;
    private float q;
    private final l r;
    private final i s;
    private final int[] t;
    private final int[] u;
    private boolean v;
    private int w;
    private float x;
    private float y;
    private boolean z;

    public interface a {
        boolean a();
    }

    public interface b {
        void onRefresh();
    }

    final void a() {
        this.e.clearAnimation();
        this.j.stop();
        this.e.setVisibility(8);
        this.e.getBackground().setAlpha(255);
        this.j.setAlpha(255);
        if (this.d) {
            a(0.0f);
        } else {
            a(this.h - this.c);
        }
        this.c = this.e.getTop();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void setProgressViewOffset(boolean scale, int start, int end) {
        this.d = scale;
        this.h = start;
        this.i = end;
        this.l = true;
        a();
        this.b = false;
    }

    public void setProgressViewEndTarget(boolean scale, int end) {
        this.i = end;
        this.d = scale;
        this.e.invalidate();
    }

    public void setSize(int size) {
        if (size == 0 || size == 1) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            if (size == 0) {
                this.K = (int) (56.0f * metrics.density);
            } else {
                this.K = (int) (40.0f * metrics.density);
            }
            this.e.setImageDrawable(null);
            this.j.a(size);
            this.e.setImageDrawable(this.j);
        }
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.b = false;
        this.p = -1.0f;
        this.t = new int[2];
        this.u = new int[2];
        this.A = -1;
        this.E = -1;
        this.M = new AnimationListener(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = this$0;
            }

            public final void onAnimationStart(Animation animation) {
            }

            public final void onAnimationRepeat(Animation animation) {
            }

            public final void onAnimationEnd(Animation animation) {
                if (this.a.b) {
                    this.a.j.setAlpha(255);
                    this.a.j.start();
                    if (this.a.k && this.a.a != null) {
                        this.a.a.onRefresh();
                    }
                    this.a.c = this.a.e.getTop();
                    return;
                }
                this.a.a();
            }
        };
        this.N = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = this$0;
            }

            public final void applyTransformation(float interpolatedTime, Transformation t) {
                int endTarget;
                if (this.a.l) {
                    endTarget = this.a.i;
                } else {
                    endTarget = this.a.i - Math.abs(this.a.h);
                }
                this.a.a((this.a.f + ((int) (((float) (endTarget - this.a.f)) * interpolatedTime))) - this.a.e.getTop());
                this.a.j.a(1.0f - interpolatedTime);
            }
        };
        this.O = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = this$0;
            }

            public final void applyTransformation(float interpolatedTime, Transformation t) {
                this.a.b(interpolatedTime);
            }
        };
        this.o = ViewConfiguration.get(context).getScaledTouchSlop();
        this.w = getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.C = new DecelerateInterpolator(2.0f);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        this.K = (int) (40.0f * metrics.density);
        this.e = new b(getContext());
        this.j = new CircularProgressDrawable(getContext());
        this.j.a(1);
        this.e.setImageDrawable(this.j);
        this.e.setVisibility(8);
        addView(this.e);
        ViewCompat.a((ViewGroup) this);
        this.i = (int) (64.0f * metrics.density);
        this.p = (float) this.i;
        this.r = new l(this);
        this.s = new i(this);
        setNestedScrollingEnabled(true);
        int i = -this.K;
        this.c = i;
        this.h = i;
        b(1.0f);
        TypedArray a = context.obtainStyledAttributes(attrs, D);
        setEnabled(a.getBoolean(0, true));
        a.recycle();
    }

    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.E < 0) {
            return i;
        }
        if (i == childCount - 1) {
            return this.E;
        }
        if (i >= this.E) {
            return i + 1;
        }
        return i;
    }

    public void setOnRefreshListener(b listener) {
        this.a = listener;
    }

    public void setRefreshing(boolean refreshing) {
        if (!refreshing || this.b == refreshing) {
            a(refreshing, false);
            return;
        }
        int endTarget;
        this.b = refreshing;
        if (this.l) {
            endTarget = this.i;
        } else {
            endTarget = this.i + this.h;
        }
        a(endTarget - this.c);
        this.k = false;
        AnimationListener animationListener = this.M;
        this.e.setVisibility(0);
        if (VERSION.SDK_INT >= 11) {
            this.j.setAlpha(255);
        }
        this.F = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = this$0;
            }

            public final void applyTransformation(float interpolatedTime, Transformation t) {
                this.a.a(interpolatedTime);
            }
        };
        this.F.setDuration((long) this.w);
        if (animationListener != null) {
            this.e.a(animationListener);
        }
        this.e.clearAnimation();
        this.e.startAnimation(this.F);
    }

    final void a(float progress) {
        this.e.setScaleX(progress);
        this.e.setScaleY(progress);
    }

    private void a(boolean refreshing, boolean notify) {
        if (this.b != refreshing) {
            this.k = notify;
            c();
            this.b = refreshing;
            if (this.b) {
                int i = this.c;
                AnimationListener animationListener = this.M;
                this.f = i;
                this.N.reset();
                this.N.setDuration(200);
                this.N.setInterpolator(this.C);
                if (animationListener != null) {
                    this.e.a(animationListener);
                }
                this.e.clearAnimation();
                this.e.startAnimation(this.N);
                return;
            }
            a(this.M);
        }
    }

    final void a(AnimationListener listener) {
        this.G = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout a;

            {
                this.a = this$0;
            }

            public final void applyTransformation(float interpolatedTime, Transformation t) {
                this.a.a(1.0f - interpolatedTime);
            }
        };
        this.G.setDuration(150);
        this.e.a(listener);
        this.e.clearAnimation();
        this.e.startAnimation(this.G);
    }

    private Animation a(final int startingAlpha, final int endingAlpha) {
        Animation alpha = new Animation(this) {
            final /* synthetic */ SwipeRefreshLayout c;

            public final void applyTransformation(float interpolatedTime, Transformation t) {
                this.c.j.setAlpha((int) (((float) startingAlpha) + (((float) (endingAlpha - startingAlpha)) * interpolatedTime)));
            }
        };
        alpha.setDuration(300);
        this.e.a(null);
        this.e.clearAnimation();
        this.e.startAnimation(alpha);
        return alpha;
    }

    @Deprecated
    public void setProgressBackgroundColor(int colorRes) {
        setProgressBackgroundColorSchemeResource(colorRes);
    }

    public void setProgressBackgroundColorSchemeResource(@ColorRes int colorRes) {
        setProgressBackgroundColorSchemeColor(android.support.v4.content.a.c(getContext(), colorRes));
    }

    public void setProgressBackgroundColorSchemeColor(@ColorInt int color) {
        this.e.setBackgroundColor(color);
    }

    @Deprecated
    public void setColorScheme(@ColorRes int... colors) {
        setColorSchemeResources(colors);
    }

    public void setColorSchemeResources(@ColorRes int... colorResIds) {
        Context context = getContext();
        int[] colorRes = new int[colorResIds.length];
        for (int i = 0; i < colorResIds.length; i++) {
            colorRes[i] = android.support.v4.content.a.c(context, colorResIds[i]);
        }
        setColorSchemeColors(colorRes);
    }

    public void setColorSchemeColors(@ColorInt int... colors) {
        c();
        this.j.a(colors);
    }

    private void c() {
        if (this.n == null) {
            int i = 0;
            while (i < getChildCount()) {
                View child = getChildAt(i);
                if (child.equals(this.e)) {
                    i++;
                } else {
                    this.n = child;
                    return;
                }
            }
        }
    }

    public void setDistanceToTriggerSync(int distance) {
        this.p = (float) distance;
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (getChildCount() != 0) {
            if (this.n == null) {
                c();
            }
            if (this.n != null) {
                View child = this.n;
                int childLeft = getPaddingLeft();
                int childTop = getPaddingTop();
                child.layout(childLeft, childTop, childLeft + ((width - getPaddingLeft()) - getPaddingRight()), childTop + ((height - getPaddingTop()) - getPaddingBottom()));
                int circleWidth = this.e.getMeasuredWidth();
                this.e.layout((width / 2) - (circleWidth / 2), this.c, (width / 2) + (circleWidth / 2), this.c + this.e.getMeasuredHeight());
            }
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.n == null) {
            c();
        }
        if (this.n != null) {
            this.n.measure(MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), ErrorDialogData.SUPPRESSED));
            this.e.measure(MeasureSpec.makeMeasureSpec(this.K, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec(this.K, ErrorDialogData.SUPPRESSED));
            this.E = -1;
            for (int index = 0; index < getChildCount(); index++) {
                if (getChildAt(index) == this.e) {
                    this.E = index;
                    return;
                }
            }
        }
    }

    public final int b() {
        return this.K;
    }

    private boolean d() {
        if (this.L != null) {
            return this.L.a();
        }
        if (!(this.n instanceof ListView)) {
            return this.n.canScrollVertically(-1);
        }
        ListView listView = (ListView) this.n;
        if (VERSION.SDK_INT >= 19) {
            return listView.canScrollList(-1);
        }
        if (listView.getChildCount() != 0) {
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int top = listView.getChildAt(0).getTop();
            if (firstVisiblePosition > 0 || top < listView.getListPaddingTop()) {
                return true;
            }
        }
        return false;
    }

    public void setOnChildScrollUpCallback(@Nullable a callback) {
        this.L = callback;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        c();
        int action = ev.getActionMasked();
        if (this.B && action == 0) {
            this.B = false;
        }
        if (!isEnabled() || this.B || d() || this.b || this.v) {
            return false;
        }
        int pointerIndex;
        switch (action) {
            case 0:
                a(this.h - this.e.getTop());
                this.A = ev.getPointerId(0);
                this.z = false;
                pointerIndex = ev.findPointerIndex(this.A);
                if (pointerIndex >= 0) {
                    this.y = ev.getY(pointerIndex);
                    break;
                }
                return false;
            case 1:
            case 3:
                this.z = false;
                this.A = -1;
                break;
            case 2:
                if (this.A == -1) {
                    return false;
                }
                pointerIndex = ev.findPointerIndex(this.A);
                if (pointerIndex >= 0) {
                    e(ev.getY(pointerIndex));
                    break;
                }
                return false;
            case 6:
                a(ev);
                break;
        }
        return this.z;
    }

    public void requestDisallowInterceptTouchEvent(boolean b) {
        if (VERSION.SDK_INT < 21 && (this.n instanceof AbsListView)) {
            return;
        }
        if (this.n == null || ViewCompat.z(this.n)) {
            super.requestDisallowInterceptTouchEvent(b);
        }
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (!isEnabled() || this.B || this.b || (nestedScrollAxes & 2) == 0) ? false : true;
    }

    public void onNestedScrollAccepted(View child, View target, int axes) {
        this.r.a(axes);
        startNestedScroll(axes & 2);
        this.q = 0.0f;
        this.v = true;
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (dy > 0 && this.q > 0.0f) {
            if (((float) dy) > this.q) {
                consumed[1] = dy - ((int) this.q);
                this.q = 0.0f;
            } else {
                this.q -= (float) dy;
                consumed[1] = dy;
            }
            c(this.q);
        }
        if (this.l && dy > 0 && this.q == 0.0f && Math.abs(dy - consumed[1]) > 0) {
            this.e.setVisibility(8);
        }
        int[] parentConsumed = this.t;
        if (dispatchNestedPreScroll(dx - consumed[0], dy - consumed[1], parentConsumed, null)) {
            consumed[0] = consumed[0] + parentConsumed[0];
            consumed[1] = consumed[1] + parentConsumed[1];
        }
    }

    public int getNestedScrollAxes() {
        return this.r.a();
    }

    public void onStopNestedScroll(View target) {
        this.r.b();
        this.v = false;
        if (this.q > 0.0f) {
            d(this.q);
            this.q = 0.0f;
        }
        stopNestedScroll();
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, this.u);
        int dy = dyUnconsumed + this.u[1];
        if (dy < 0 && !d()) {
            this.q += (float) Math.abs(dy);
            c(this.q);
        }
    }

    public void setNestedScrollingEnabled(boolean enabled) {
        this.s.a(enabled);
    }

    public boolean isNestedScrollingEnabled() {
        return this.s.a();
    }

    public boolean startNestedScroll(int axes) {
        return this.s.b(axes);
    }

    public void stopNestedScroll() {
        this.s.c();
    }

    public boolean hasNestedScrollingParent() {
        return this.s.b();
    }

    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return this.s.a(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return this.s.a(dx, dy, consumed, offsetInWindow);
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return dispatchNestedPreFling(velocityX, velocityY);
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return dispatchNestedFling(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return this.s.a(velocityX, velocityY, consumed);
    }

    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return this.s.a(velocityX, velocityY);
    }

    private static boolean a(Animation animation) {
        return (animation == null || !animation.hasStarted() || animation.hasEnded()) ? false : true;
    }

    private void c(float overscrollTop) {
        float slingshotDist;
        this.j.a(true);
        float dragPercent = Math.min(1.0f, Math.abs(overscrollTop / this.p));
        float adjustedPercent = (((float) Math.max(((double) dragPercent) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
        float extraOS = Math.abs(overscrollTop) - this.p;
        if (this.l) {
            slingshotDist = (float) (this.i - this.h);
        } else {
            slingshotDist = (float) this.i;
        }
        float tensionSlingshotPercent = Math.max(0.0f, Math.min(extraOS, 2.0f * slingshotDist) / slingshotDist);
        float tensionPercent = ((float) (((double) (tensionSlingshotPercent / 4.0f)) - Math.pow((double) (tensionSlingshotPercent / 4.0f), 2.0d))) * 2.0f;
        int targetY = this.h + ((int) ((slingshotDist * dragPercent) + ((slingshotDist * tensionPercent) * 2.0f)));
        if (this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }
        if (!this.d) {
            this.e.setScaleX(1.0f);
            this.e.setScaleY(1.0f);
        }
        if (this.d) {
            a(Math.min(1.0f, overscrollTop / this.p));
        }
        if (overscrollTop < this.p) {
            if (this.j.getAlpha() > 76 && !a(this.H)) {
                this.H = a(this.j.getAlpha(), 76);
            }
        } else if (this.j.getAlpha() < 255 && !a(this.I)) {
            this.I = a(this.j.getAlpha(), 255);
        }
        this.j.b(Math.min(0.8f, adjustedPercent * 0.8f));
        this.j.a(Math.min(1.0f, adjustedPercent));
        this.j.c(((-0.25f + (0.4f * adjustedPercent)) + (2.0f * tensionPercent)) * 0.5f);
        a(targetY - this.c);
    }

    private void d(float overscrollTop) {
        if (overscrollTop > this.p) {
            a(true, true);
            return;
        }
        this.b = false;
        this.j.b(0.0f);
        AnimationListener listener = null;
        if (!this.d) {
            listener = new AnimationListener(this) {
                final /* synthetic */ SwipeRefreshLayout a;

                {
                    this.a = this$0;
                }

                public final void onAnimationStart(Animation animation) {
                }

                public final void onAnimationEnd(Animation animation) {
                    if (!this.a.d) {
                        this.a.a(null);
                    }
                }

                public final void onAnimationRepeat(Animation animation) {
                }
            };
        }
        int i = this.c;
        if (this.d) {
            this.f = i;
            this.g = this.e.getScaleX();
            this.J = new Animation(this) {
                final /* synthetic */ SwipeRefreshLayout a;

                {
                    this.a = this$0;
                }

                public final void applyTransformation(float interpolatedTime, Transformation t) {
                    this.a.a(this.a.g + ((-this.a.g) * interpolatedTime));
                    this.a.b(interpolatedTime);
                }
            };
            this.J.setDuration(150);
            if (listener != null) {
                this.e.a(listener);
            }
            this.e.clearAnimation();
            this.e.startAnimation(this.J);
        } else {
            this.f = i;
            this.O.reset();
            this.O.setDuration(200);
            this.O.setInterpolator(this.C);
            if (listener != null) {
                this.e.a(listener);
            }
            this.e.clearAnimation();
            this.e.startAnimation(this.O);
        }
        this.j.a(false);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (this.B && action == 0) {
            this.B = false;
        }
        if (!isEnabled() || this.B || d() || this.b || this.v) {
            return false;
        }
        int pointerIndex;
        float overscrollTop;
        switch (action) {
            case 0:
                this.A = ev.getPointerId(0);
                this.z = false;
                break;
            case 1:
                pointerIndex = ev.findPointerIndex(this.A);
                if (pointerIndex < 0) {
                    return false;
                }
                if (this.z) {
                    overscrollTop = (ev.getY(pointerIndex) - this.x) * 0.5f;
                    this.z = false;
                    d(overscrollTop);
                }
                this.A = -1;
                return false;
            case 2:
                pointerIndex = ev.findPointerIndex(this.A);
                if (pointerIndex < 0) {
                    return false;
                }
                float y = ev.getY(pointerIndex);
                e(y);
                if (this.z) {
                    overscrollTop = (y - this.x) * 0.5f;
                    if (overscrollTop > 0.0f) {
                        c(overscrollTop);
                        break;
                    }
                    return false;
                }
                break;
            case 3:
                return false;
            case 5:
                pointerIndex = ev.getActionIndex();
                if (pointerIndex >= 0) {
                    this.A = ev.getPointerId(pointerIndex);
                    break;
                }
                return false;
            case 6:
                a(ev);
                break;
        }
        return true;
    }

    private void e(float y) {
        if (y - this.y > ((float) this.o) && !this.z) {
            this.x = this.y + ((float) this.o);
            this.z = true;
            this.j.setAlpha(76);
        }
    }

    final void b(float interpolatedTime) {
        a((this.f + ((int) (((float) (this.h - this.f)) * interpolatedTime))) - this.e.getTop());
    }

    final void a(int offset) {
        this.e.bringToFront();
        ViewCompat.b(this.e, offset);
        this.c = this.e.getTop();
    }

    private void a(MotionEvent ev) {
        int pointerIndex = ev.getActionIndex();
        if (ev.getPointerId(pointerIndex) == this.A) {
            this.A = ev.getPointerId(pointerIndex == 0 ? 1 : 0);
        }
    }
}
