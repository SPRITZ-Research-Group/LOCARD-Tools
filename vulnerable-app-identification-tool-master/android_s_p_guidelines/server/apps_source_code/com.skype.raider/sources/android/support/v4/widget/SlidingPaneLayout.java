package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    static final f h;
    View a;
    float b;
    int c;
    boolean d;
    final n e;
    boolean f;
    final ArrayList<b> g;
    private int i;
    private int j;
    private Drawable k;
    private Drawable l;
    private final int m;
    private boolean n;
    private float o;
    private int p;
    private float q;
    private float r;
    private e s;
    private boolean t;
    private final Rect u;

    static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }
        };
        boolean b;

        SavedState(Parcelable superState) {
            super(superState);
        }

        SavedState(Parcel in) {
            super(in, null);
            this.b = in.readInt() != 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.b ? 1 : 0);
        }
    }

    class a extends android.support.v4.view.a {
        final /* synthetic */ SlidingPaneLayout a;
        private final Rect c = new Rect();

        a(SlidingPaneLayout this$0) {
            this.a = this$0;
        }

        public final void a(View host, android.support.v4.view.accessibility.b info) {
            android.support.v4.view.accessibility.b superNode = android.support.v4.view.accessibility.b.a(info);
            super.a(host, superNode);
            Rect rect = this.c;
            superNode.a(rect);
            info.b(rect);
            superNode.c(rect);
            info.d(rect);
            info.c(superNode.f());
            info.a(superNode.l());
            info.b(superNode.m());
            info.d(superNode.n());
            info.h(superNode.k());
            info.f(superNode.i());
            info.a(superNode.d());
            info.b(superNode.e());
            info.d(superNode.g());
            info.e(superNode.h());
            info.g(superNode.j());
            info.a(superNode.b());
            info.b(superNode.c());
            superNode.o();
            info.b(SlidingPaneLayout.class.getName());
            info.a(host);
            ViewParent parent = ViewCompat.g(host);
            if (parent instanceof View) {
                info.c((View) parent);
            }
            int childCount = this.a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = this.a.getChildAt(i);
                if (!b(child) && child.getVisibility() == 0) {
                    ViewCompat.a(child, 1);
                    info.b(child);
                }
            }
        }

        public final void a(View host, AccessibilityEvent event) {
            super.a(host, event);
            event.setClassName(SlidingPaneLayout.class.getName());
        }

        public final boolean a(ViewGroup host, View child, AccessibilityEvent event) {
            if (b(child)) {
                return false;
            }
            return super.a(host, child, event);
        }

        private boolean b(View child) {
            return this.a.c(child);
        }
    }

    private class b implements Runnable {
        final View a;
        final /* synthetic */ SlidingPaneLayout b;

        b(SlidingPaneLayout slidingPaneLayout, View childView) {
            this.b = slidingPaneLayout;
            this.a = childView;
        }

        public final void run() {
            if (this.a.getParent() == this.b) {
                this.a.setLayerType(0, null);
                this.b.b(this.a);
            }
            this.b.g.remove(this);
        }
    }

    private class c extends android.support.v4.widget.n.a {
        final /* synthetic */ SlidingPaneLayout a;

        c(SlidingPaneLayout slidingPaneLayout) {
            this.a = slidingPaneLayout;
        }

        public final boolean a(View child) {
            if (this.a.d) {
                return false;
            }
            return ((d) child.getLayoutParams()).b;
        }

        public final void a(int state) {
            if (this.a.e.a() != 0) {
                return;
            }
            SlidingPaneLayout slidingPaneLayout;
            View view;
            if (this.a.b == 0.0f) {
                this.a.a(this.a.a);
                slidingPaneLayout = this.a;
                view = this.a.a;
                slidingPaneLayout.sendAccessibilityEvent(32);
                this.a.f = false;
                return;
            }
            slidingPaneLayout = this.a;
            view = this.a.a;
            slidingPaneLayout.sendAccessibilityEvent(32);
            this.a.f = true;
        }

        public final void b(View capturedChild) {
            this.a.a();
        }

        public final void b(View changedView, int left) {
            this.a.a(left);
            this.a.invalidate();
        }

        public final void a(View releasedChild, float xvel) {
            int left;
            d lp = (d) releasedChild.getLayoutParams();
            if (this.a.b()) {
                int startToRight = this.a.getPaddingRight() + lp.rightMargin;
                if (xvel < 0.0f || (xvel == 0.0f && this.a.b > 0.5f)) {
                    startToRight += this.a.c;
                }
                left = (this.a.getWidth() - startToRight) - this.a.a.getWidth();
            } else {
                left = this.a.getPaddingLeft() + lp.leftMargin;
                if (xvel > 0.0f || (xvel == 0.0f && this.a.b > 0.5f)) {
                    left += this.a.c;
                }
            }
            this.a.e.a(left, releasedChild.getTop());
            this.a.invalidate();
        }

        public final int c(View child) {
            return this.a.c;
        }

        public final int a(View child, int left) {
            d lp = (d) this.a.a.getLayoutParams();
            int startBound;
            if (this.a.b()) {
                startBound = this.a.getWidth() - ((this.a.getPaddingRight() + lp.rightMargin) + this.a.a.getWidth());
                return Math.max(Math.min(left, startBound), startBound - this.a.c);
            }
            startBound = this.a.getPaddingLeft() + lp.leftMargin;
            return Math.min(Math.max(left, startBound), startBound + this.a.c);
        }

        public final int d(View child) {
            return child.getTop();
        }

        public final void a(int edgeFlags, int pointerId) {
            this.a.e.a(this.a.a, pointerId);
        }
    }

    public static class d extends MarginLayoutParams {
        private static final int[] e = new int[]{16843137};
        public float a = 0.0f;
        boolean b;
        boolean c;
        Paint d;

        public d() {
            super(-1, -1);
        }

        public d(LayoutParams source) {
            super(source);
        }

        public d(MarginLayoutParams source) {
            super(source);
        }

        public d(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, e);
            this.a = a.getFloat(0, 0.0f);
            a.recycle();
        }
    }

    public interface e {
    }

    interface f {
        void a(SlidingPaneLayout slidingPaneLayout, View view);
    }

    static class g implements f {
        g() {
        }

        public void a(SlidingPaneLayout parent, View child) {
            ViewCompat.a(parent, child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        }
    }

    @RequiresApi(16)
    static class h extends g {
        private Method a;
        private Field b;

        h() {
            try {
                this.a = View.class.getDeclaredMethod("getDisplayList", null);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.b = View.class.getDeclaredField("mRecreateDisplayList");
                this.b.setAccessible(true);
            } catch (NoSuchFieldException e2) {
            }
        }

        public final void a(SlidingPaneLayout parent, View child) {
            if (this.a == null || this.b == null) {
                child.invalidate();
                return;
            }
            try {
                this.b.setBoolean(child, true);
                this.a.invoke(child, null);
            } catch (Exception e) {
            }
            super.a(parent, child);
        }
    }

    @RequiresApi(17)
    static class i extends g {
        i() {
        }

        public final void a(SlidingPaneLayout parent, View child) {
            ViewCompat.a(child, ((d) child.getLayoutParams()).d);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            h = new i();
        } else if (VERSION.SDK_INT >= 16) {
            h = new h();
        } else {
            h = new g();
        }
    }

    public SlidingPaneLayout(Context context) {
        this(context, null);
    }

    public SlidingPaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.i = -858993460;
        this.t = true;
        this.u = new Rect();
        this.g = new ArrayList();
        float density = context.getResources().getDisplayMetrics().density;
        this.m = (int) ((32.0f * density) + 0.5f);
        setWillNotDraw(false);
        ViewCompat.a((View) this, new a(this));
        ViewCompat.a((View) this, 1);
        this.e = n.a((ViewGroup) this, 0.5f, new c(this));
        this.e.a(400.0f * density);
    }

    public void setParallaxDistance(int parallaxBy) {
        this.p = parallaxBy;
        requestLayout();
    }

    public void setSliderFadeColor(@ColorInt int color) {
        this.i = color;
    }

    public void setCoveredFadeColor(@ColorInt int color) {
        this.j = color;
    }

    public void setPanelSlideListener(e listener) {
        this.s = listener;
    }

    final void a(View panel) {
        int endBound;
        int left;
        int right;
        int top;
        int bottom;
        int i;
        int childCount;
        View child;
        int i2;
        int clampedChildLeft;
        int clampedChildTop;
        int clampedChildRight;
        int clampedChildBottom;
        int vis;
        boolean isLayoutRtl = b();
        int startBound = isLayoutRtl ? getWidth() - getPaddingRight() : getPaddingLeft();
        if (isLayoutRtl) {
            endBound = getPaddingLeft();
        } else {
            endBound = getWidth() - getPaddingRight();
        }
        int topBound = getPaddingTop();
        int bottomBound = getHeight() - getPaddingBottom();
        if (panel != null) {
            Object obj;
            if (panel.isOpaque()) {
                obj = 1;
            } else {
                if (VERSION.SDK_INT < 18) {
                    Drawable background = panel.getBackground();
                    if (background != null) {
                        obj = background.getOpacity() == -1 ? 1 : null;
                    }
                }
                obj = null;
            }
            if (obj != null) {
                left = panel.getLeft();
                right = panel.getRight();
                top = panel.getTop();
                bottom = panel.getBottom();
                i = 0;
                childCount = getChildCount();
                while (i < childCount) {
                    child = getChildAt(i);
                    if (child != panel) {
                        if (child.getVisibility() == 8) {
                            if (isLayoutRtl) {
                                i2 = startBound;
                            } else {
                                i2 = endBound;
                            }
                            clampedChildLeft = Math.max(i2, child.getLeft());
                            clampedChildTop = Math.max(topBound, child.getTop());
                            if (isLayoutRtl) {
                                i2 = endBound;
                            } else {
                                i2 = startBound;
                            }
                            clampedChildRight = Math.min(i2, child.getRight());
                            clampedChildBottom = Math.min(bottomBound, child.getBottom());
                            if (clampedChildLeft >= left || clampedChildTop < top || clampedChildRight > right || clampedChildBottom > bottom) {
                                vis = 0;
                            } else {
                                vis = 4;
                            }
                            child.setVisibility(vis);
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
        bottom = 0;
        top = 0;
        right = 0;
        left = 0;
        i = 0;
        childCount = getChildCount();
        while (i < childCount) {
            child = getChildAt(i);
            if (child != panel) {
                if (child.getVisibility() == 8) {
                    if (isLayoutRtl) {
                        i2 = startBound;
                    } else {
                        i2 = endBound;
                    }
                    clampedChildLeft = Math.max(i2, child.getLeft());
                    clampedChildTop = Math.max(topBound, child.getTop());
                    if (isLayoutRtl) {
                        i2 = endBound;
                    } else {
                        i2 = startBound;
                    }
                    clampedChildRight = Math.min(i2, child.getRight());
                    clampedChildBottom = Math.min(bottomBound, child.getBottom());
                    if (clampedChildLeft >= left) {
                    }
                    vis = 0;
                    child.setVisibility(vis);
                }
                i++;
            } else {
                return;
            }
        }
    }

    final void a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.t = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.t = true;
        int count = this.g.size();
        for (int i = 0; i < count; i++) {
            ((b) this.g.get(i)).run();
        }
        this.g.clear();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        View child;
        d lp;
        int childWidthSpec;
        int childHeightSpec;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            } else if (widthMode != Integer.MIN_VALUE && widthMode == 0) {
                widthSize = 300;
            }
        } else if (heightMode == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            } else if (heightMode == 0) {
                heightMode = Integer.MIN_VALUE;
                heightSize = 300;
            }
        }
        int layoutHeight = 0;
        int maxLayoutHeight = 0;
        switch (heightMode) {
            case Integer.MIN_VALUE:
                maxLayoutHeight = (heightSize - getPaddingTop()) - getPaddingBottom();
                break;
            case ErrorDialogData.SUPPRESSED /*1073741824*/:
                maxLayoutHeight = (heightSize - getPaddingTop()) - getPaddingBottom();
                layoutHeight = maxLayoutHeight;
                break;
        }
        float weightSum = 0.0f;
        boolean canSlide = false;
        int widthAvailable = (widthSize - getPaddingLeft()) - getPaddingRight();
        int widthRemaining = widthAvailable;
        int childCount = getChildCount();
        this.a = null;
        for (i = 0; i < childCount; i++) {
            child = getChildAt(i);
            lp = (d) child.getLayoutParams();
            if (child.getVisibility() == 8) {
                lp.c = false;
            } else {
                if (lp.a > 0.0f) {
                    weightSum += lp.a;
                    if (lp.width == 0) {
                    }
                }
                int horizontalMargin = lp.leftMargin + lp.rightMargin;
                if (lp.width == -2) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, Integer.MIN_VALUE);
                } else if (lp.width == -1) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, ErrorDialogData.SUPPRESSED);
                } else {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, ErrorDialogData.SUPPRESSED);
                }
                if (lp.height == -2) {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                } else if (lp.height == -1) {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, ErrorDialogData.SUPPRESSED);
                } else {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, ErrorDialogData.SUPPRESSED);
                }
                child.measure(childWidthSpec, childHeightSpec);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (heightMode == Integer.MIN_VALUE && childHeight > layoutHeight) {
                    layoutHeight = Math.min(childHeight, maxLayoutHeight);
                }
                widthRemaining -= childWidth;
                boolean z = widthRemaining < 0;
                lp.b = z;
                canSlide |= z;
                if (lp.b) {
                    this.a = child;
                }
            }
        }
        if (canSlide || weightSum > 0.0f) {
            int fixedPanelWidthLimit = widthAvailable - this.m;
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                if (child.getVisibility() != 8) {
                    lp = (d) child.getLayoutParams();
                    if (child.getVisibility() != 8) {
                        boolean skippedFirstPass = lp.width == 0 && lp.a > 0.0f;
                        int measuredWidth = skippedFirstPass ? 0 : child.getMeasuredWidth();
                        if (!canSlide || child == this.a) {
                            if (lp.a > 0.0f) {
                                if (lp.width != 0) {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), ErrorDialogData.SUPPRESSED);
                                } else if (lp.height == -2) {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                } else if (lp.height == -1) {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, ErrorDialogData.SUPPRESSED);
                                } else {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, ErrorDialogData.SUPPRESSED);
                                }
                                if (canSlide) {
                                    int newWidth = widthAvailable - (lp.leftMargin + lp.rightMargin);
                                    childWidthSpec = MeasureSpec.makeMeasureSpec(newWidth, ErrorDialogData.SUPPRESSED);
                                    if (measuredWidth != newWidth) {
                                        child.measure(childWidthSpec, childHeightSpec);
                                    }
                                } else {
                                    child.measure(MeasureSpec.makeMeasureSpec(measuredWidth + ((int) ((lp.a * ((float) Math.max(0, widthRemaining))) / weightSum)), ErrorDialogData.SUPPRESSED), childHeightSpec);
                                }
                            }
                        } else if (lp.width < 0 && (measuredWidth > fixedPanelWidthLimit || lp.a > 0.0f)) {
                            if (!skippedFirstPass) {
                                childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), ErrorDialogData.SUPPRESSED);
                            } else if (lp.height == -2) {
                                childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                            } else if (lp.height == -1) {
                                childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, ErrorDialogData.SUPPRESSED);
                            } else {
                                childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, ErrorDialogData.SUPPRESSED);
                            }
                            child.measure(MeasureSpec.makeMeasureSpec(fixedPanelWidthLimit, ErrorDialogData.SUPPRESSED), childHeightSpec);
                        }
                    }
                }
            }
        }
        setMeasuredDimension(widthSize, (getPaddingTop() + layoutHeight) + getPaddingBottom());
        this.n = canSlide;
        if (this.e.a() != 0 && !canSlide) {
            this.e.f();
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        boolean isLayoutRtl = b();
        if (isLayoutRtl) {
            this.e.a(2);
        } else {
            this.e.a(1);
        }
        int width = r - l;
        int paddingStart = isLayoutRtl ? getPaddingRight() : getPaddingLeft();
        int paddingEnd = isLayoutRtl ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int xStart = paddingStart;
        int nextXStart = paddingStart;
        if (this.t) {
            float f = (this.n && this.f) ? 1.0f : 0.0f;
            this.b = f;
        }
        for (i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                int childRight;
                int childLeft;
                d lp = (d) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int offset = 0;
                if (lp.b) {
                    int range = (Math.min(nextXStart, (width - paddingEnd) - this.m) - xStart) - (lp.leftMargin + lp.rightMargin);
                    this.c = range;
                    int lpMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
                    lp.c = ((xStart + lpMargin) + range) + (childWidth / 2) > width - paddingEnd;
                    int pos = (int) (((float) range) * this.b);
                    xStart += pos + lpMargin;
                    this.b = ((float) pos) / ((float) this.c);
                } else {
                    if (this.n && this.p != 0) {
                        offset = (int) ((1.0f - this.b) * ((float) this.p));
                    }
                    xStart = nextXStart;
                }
                if (isLayoutRtl) {
                    childRight = (width - xStart) + offset;
                    childLeft = childRight - childWidth;
                } else {
                    childLeft = xStart - offset;
                    childRight = childLeft + childWidth;
                }
                child.layout(childLeft, paddingTop, childRight, paddingTop + child.getMeasuredHeight());
                nextXStart += child.getWidth();
            }
        }
        if (this.t) {
            if (this.n) {
                if (this.p != 0) {
                    b(this.b);
                }
                if (((d) this.a.getLayoutParams()).c) {
                    a(this.a, this.b, this.i);
                }
            } else {
                for (i = 0; i < childCount; i++) {
                    a(getChildAt(i), 0.0f, this.i);
                }
            }
            a(this.a);
        }
        this.t = false;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            this.t = true;
        }
    }

    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        if (!isInTouchMode() && !this.n) {
            this.f = child == this.a;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (!this.n && action == 0 && getChildCount() > 1) {
            View secondChild = getChildAt(1);
            if (secondChild != null) {
                boolean z;
                if (n.b(secondChild, (int) ev.getX(), (int) ev.getY())) {
                    z = false;
                } else {
                    z = true;
                }
                this.f = z;
            }
        }
        if (!this.n || (this.d && action != 0)) {
            this.e.e();
            return super.onInterceptTouchEvent(ev);
        } else if (action == 3 || action == 1) {
            this.e.e();
            return false;
        } else {
            boolean interceptTap = false;
            float x;
            float y;
            switch (action) {
                case 0:
                    this.d = false;
                    x = ev.getX();
                    y = ev.getY();
                    this.q = x;
                    this.r = y;
                    if (n.b(this.a, (int) x, (int) y) && c(this.a)) {
                        interceptTap = true;
                        break;
                    }
                case 2:
                    x = ev.getX();
                    y = ev.getY();
                    float adx = Math.abs(x - this.q);
                    float ady = Math.abs(y - this.r);
                    if (adx > ((float) this.e.d()) && ady > adx) {
                        this.e.e();
                        this.d = true;
                        return false;
                    }
            }
            if (this.e.a(ev) || interceptTap) {
                return true;
            }
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.n) {
            return super.onTouchEvent(ev);
        }
        this.e.b(ev);
        float x;
        float y;
        switch (ev.getActionMasked()) {
            case 0:
                x = ev.getX();
                y = ev.getY();
                this.q = x;
                this.r = y;
                break;
            case 1:
                if (c(this.a)) {
                    x = ev.getX();
                    y = ev.getY();
                    float dx = x - this.q;
                    float dy = y - this.r;
                    int slop = this.e.d();
                    if ((dx * dx) + (dy * dy) < ((float) (slop * slop)) && n.b(this.a, (int) x, (int) y)) {
                        c();
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private boolean c() {
        if (!this.t && !a(0.0f)) {
            return false;
        }
        this.f = false;
        return true;
    }

    final void a(int newLeft) {
        if (this.a == null) {
            this.b = 0.0f;
            return;
        }
        int newStart;
        boolean isLayoutRtl = b();
        d lp = (d) this.a.getLayoutParams();
        int childWidth = this.a.getWidth();
        if (isLayoutRtl) {
            newStart = (getWidth() - newLeft) - childWidth;
        } else {
            newStart = newLeft;
        }
        this.b = ((float) (newStart - ((isLayoutRtl ? getPaddingRight() : getPaddingLeft()) + (isLayoutRtl ? lp.rightMargin : lp.leftMargin)))) / ((float) this.c);
        if (this.p != 0) {
            b(this.b);
        }
        if (lp.c) {
            a(this.a, this.b, this.i);
        }
    }

    private void a(View v, float mag, int fadeColor) {
        d lp = (d) v.getLayoutParams();
        if (mag > 0.0f && fadeColor != 0) {
            int color = (((int) (((float) ((-16777216 & fadeColor) >>> 24)) * mag)) << 24) | (16777215 & fadeColor);
            if (lp.d == null) {
                lp.d = new Paint();
            }
            lp.d.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_OVER));
            if (v.getLayerType() != 2) {
                v.setLayerType(2, lp.d);
            }
            b(v);
        } else if (v.getLayerType() != 0) {
            if (lp.d != null) {
                lp.d.setColorFilter(null);
            }
            Runnable dlr = new b(this, v);
            this.g.add(dlr);
            ViewCompat.a((View) this, dlr);
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        d lp = (d) child.getLayoutParams();
        int save = canvas.save(2);
        if (!(!this.n || lp.b || this.a == null)) {
            canvas.getClipBounds(this.u);
            if (b()) {
                this.u.left = Math.max(this.u.left, this.a.getRight());
            } else {
                this.u.right = Math.min(this.u.right, this.a.getLeft());
            }
            canvas.clipRect(this.u);
        }
        boolean result = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(save);
        return result;
    }

    final void b(View v) {
        h.a(this, v);
    }

    private boolean a(float slideOffset) {
        if (!this.n) {
            return false;
        }
        int x;
        d lp = (d) this.a.getLayoutParams();
        if (b()) {
            x = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + lp.rightMargin)) + (((float) this.c) * slideOffset)) + ((float) this.a.getWidth())));
        } else {
            x = (int) (((float) (getPaddingLeft() + lp.leftMargin)) + (((float) this.c) * slideOffset));
        }
        if (!this.e.a(this.a, x, this.a.getTop())) {
            return false;
        }
        a();
        ViewCompat.d(this);
        return true;
    }

    public void computeScroll() {
        if (!this.e.g()) {
            return;
        }
        if (this.n) {
            ViewCompat.d(this);
        } else {
            this.e.f();
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable d) {
        setShadowDrawableLeft(d);
    }

    public void setShadowDrawableLeft(Drawable d) {
        this.k = d;
    }

    public void setShadowDrawableRight(Drawable d) {
        this.l = d;
    }

    @Deprecated
    public void setShadowResource(@DrawableRes int resId) {
        setShadowDrawable(getResources().getDrawable(resId));
    }

    public void setShadowResourceLeft(int resId) {
        setShadowDrawableLeft(android.support.v4.content.a.a(getContext(), resId));
    }

    public void setShadowResourceRight(int resId) {
        setShadowDrawableRight(android.support.v4.content.a.a(getContext(), resId));
    }

    public void draw(Canvas c) {
        Drawable shadowDrawable;
        super.draw(c);
        if (b()) {
            shadowDrawable = this.l;
        } else {
            shadowDrawable = this.k;
        }
        View shadowView = getChildCount() > 1 ? getChildAt(1) : null;
        if (shadowView != null && shadowDrawable != null) {
            int left;
            int right;
            int top = shadowView.getTop();
            int bottom = shadowView.getBottom();
            int shadowWidth = shadowDrawable.getIntrinsicWidth();
            if (b()) {
                left = shadowView.getRight();
                right = left + shadowWidth;
            } else {
                right = shadowView.getLeft();
                left = right - shadowWidth;
            }
            shadowDrawable.setBounds(left, top, right, bottom);
            shadowDrawable.draw(c);
        }
    }

    private void b(float slideOffset) {
        boolean dimViews;
        int childCount;
        int i;
        View v;
        int oldOffset;
        int dx;
        float f;
        boolean isLayoutRtl = b();
        d slideLp = (d) this.a.getLayoutParams();
        if (slideLp.c) {
            if ((isLayoutRtl ? slideLp.rightMargin : slideLp.leftMargin) <= 0) {
                dimViews = true;
                childCount = getChildCount();
                for (i = 0; i < childCount; i++) {
                    v = getChildAt(i);
                    if (v != this.a) {
                        oldOffset = (int) ((1.0f - this.o) * ((float) this.p));
                        this.o = slideOffset;
                        dx = oldOffset - ((int) ((1.0f - slideOffset) * ((float) this.p)));
                        if (isLayoutRtl) {
                            dx = -dx;
                        }
                        v.offsetLeftAndRight(dx);
                        if (!dimViews) {
                            if (isLayoutRtl) {
                                f = 1.0f - this.o;
                            } else {
                                f = this.o - 1.0f;
                            }
                            a(v, f, this.j);
                        }
                    }
                }
            }
        }
        dimViews = false;
        childCount = getChildCount();
        for (i = 0; i < childCount; i++) {
            v = getChildAt(i);
            if (v != this.a) {
                oldOffset = (int) ((1.0f - this.o) * ((float) this.p));
                this.o = slideOffset;
                dx = oldOffset - ((int) ((1.0f - slideOffset) * ((float) this.p)));
                if (isLayoutRtl) {
                    dx = -dx;
                }
                v.offsetLeftAndRight(dx);
                if (!dimViews) {
                    if (isLayoutRtl) {
                        f = 1.0f - this.o;
                    } else {
                        f = this.o - 1.0f;
                    }
                    a(v, f, this.j);
                }
            }
        }
    }

    final boolean c(View child) {
        if (child == null) {
            return false;
        }
        d lp = (d) child.getLayoutParams();
        if (this.n && lp.c && this.b > 0.0f) {
            return true;
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams ? new d((MarginLayoutParams) p) : new d(p);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof d) && super.checkLayoutParams(p);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new d(getContext(), attrs);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        boolean z = this.n ? !this.n || this.b == 1.0f : this.f;
        ss.b = z;
        return ss;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.a());
            if (!ss.b) {
                c();
            } else if (this.t || a(1.0f)) {
                this.f = true;
            }
            this.f = ss.b;
            return;
        }
        super.onRestoreInstanceState(state);
    }

    final boolean b() {
        return ViewCompat.f(this) == 1;
    }
}
