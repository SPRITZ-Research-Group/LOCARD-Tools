package android.support.v4.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.q;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
    static final int[] a = new int[]{16842931};
    static final boolean b;
    private static final int[] c = new int[]{16843828};
    private static final boolean d;
    private float A;
    private Drawable B;
    private Drawable C;
    private Drawable D;
    private CharSequence E;
    private CharSequence F;
    private Object G;
    private boolean H;
    private Drawable I;
    private Drawable J;
    private Drawable K;
    private Drawable L;
    private final ArrayList<View> M;
    private final b e;
    private float f;
    private int g;
    private int h;
    private float i;
    private Paint j;
    private final n k;
    private final n l;
    private final e m;
    private final e n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    @Nullable
    private c x;
    private List<c> y;
    private float z;

    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface LockMode {
    }

    protected static class SavedState extends AbsSavedState {
        public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        int b = 0;
        int c;
        int d;
        int e;
        int f;

        public SavedState(Parcel in, ClassLoader loader) {
            super(in, loader);
            this.b = in.readInt();
            this.c = in.readInt();
            this.d = in.readInt();
            this.e = in.readInt();
            this.f = in.readInt();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.b);
            dest.writeInt(this.c);
            dest.writeInt(this.d);
            dest.writeInt(this.e);
            dest.writeInt(this.f);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface State {
    }

    class a extends android.support.v4.view.a {
        final /* synthetic */ DrawerLayout a;
        private final Rect c = new Rect();

        a(DrawerLayout this$0) {
            this.a = this$0;
        }

        public final void a(View host, android.support.v4.view.accessibility.b info) {
            if (DrawerLayout.b) {
                super.a(host, info);
            } else {
                android.support.v4.view.accessibility.b superNode = android.support.v4.view.accessibility.b.a(info);
                super.a(host, superNode);
                info.a(host);
                ViewParent parent = ViewCompat.g(host);
                if (parent instanceof View) {
                    info.c((View) parent);
                }
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
                superNode.o();
                ViewGroup viewGroup = (ViewGroup) host;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (DrawerLayout.f(childAt)) {
                        info.b(childAt);
                    }
                }
            }
            info.b(DrawerLayout.class.getName());
            info.a(false);
            info.b(false);
            info.a(android.support.v4.view.accessibility.b.a.a);
            info.a(android.support.v4.view.accessibility.b.a.b);
        }

        public final void a(View host, AccessibilityEvent event) {
            super.a(host, event);
            event.setClassName(DrawerLayout.class.getName());
        }

        public final boolean d(View host, AccessibilityEvent event) {
            if (event.getEventType() != 32) {
                return super.d(host, event);
            }
            List<CharSequence> eventText = event.getText();
            View visibleDrawer = this.a.a();
            if (visibleDrawer != null) {
                CharSequence title = this.a.a(this.a.c(visibleDrawer));
                if (title != null) {
                    eventText.add(title);
                }
            }
            return true;
        }

        public final boolean a(ViewGroup host, View child, AccessibilityEvent event) {
            if (DrawerLayout.b || DrawerLayout.f(child)) {
                return super.a(host, child, event);
            }
            return false;
        }
    }

    static final class b extends android.support.v4.view.a {
        b() {
        }

        public final void a(View child, android.support.v4.view.accessibility.b info) {
            super.a(child, info);
            if (!DrawerLayout.f(child)) {
                info.c(null);
            }
        }
    }

    public interface c {
        void a();

        void a(float f);

        void a(int i);

        void b();
    }

    public static class d extends MarginLayoutParams {
        public int a = 0;
        float b;
        boolean c;
        int d;

        public d(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, DrawerLayout.a);
            this.a = a.getInt(0, 0);
            a.recycle();
        }

        public d() {
            super(-1, -1);
        }

        public d(d source) {
            super(source);
            this.a = source.a;
        }

        public d(LayoutParams source) {
            super(source);
        }

        public d(MarginLayoutParams source) {
            super(source);
        }
    }

    private class e extends android.support.v4.widget.n.a {
        final /* synthetic */ DrawerLayout a;
        private final int b;
        private n c;
        private final Runnable d = new Runnable(this) {
            final /* synthetic */ e a;

            {
                this.a = this$1;
            }

            public final void run() {
                this.a.c();
            }
        };

        e(DrawerLayout drawerLayout, int gravity) {
            this.a = drawerLayout;
            this.b = gravity;
        }

        public final void a(n dragger) {
            this.c = dragger;
        }

        public final void a() {
            this.a.removeCallbacks(this.d);
        }

        public final boolean a(View child) {
            return DrawerLayout.d(child) && this.a.a(child, this.b) && this.a.a(child) == 0;
        }

        public final void a(int state) {
            this.a.a(state, this.c.c());
        }

        public final void b(View changedView, int left) {
            float offset;
            int childWidth = changedView.getWidth();
            if (this.a.a(changedView, 3)) {
                offset = ((float) (childWidth + left)) / ((float) childWidth);
            } else {
                offset = ((float) (this.a.getWidth() - left)) / ((float) childWidth);
            }
            this.a.a(changedView, offset);
            changedView.setVisibility(offset == 0.0f ? 4 : 0);
            this.a.invalidate();
        }

        public final void b(View capturedChild) {
            ((d) capturedChild.getLayoutParams()).c = false;
            d();
        }

        private void d() {
            int otherGrav = 3;
            if (this.b == 3) {
                otherGrav = 5;
            }
            View toClose = this.a.b(otherGrav);
            if (toClose != null) {
                this.a.e(toClose);
            }
        }

        public final void a(View releasedChild, float xvel) {
            int left;
            float offset = DrawerLayout.b(releasedChild);
            int childWidth = releasedChild.getWidth();
            if (this.a.a(releasedChild, 3)) {
                left = (xvel > 0.0f || (xvel == 0.0f && offset > 0.5f)) ? 0 : -childWidth;
            } else {
                int width = this.a.getWidth();
                left = (xvel < 0.0f || (xvel == 0.0f && offset > 0.5f)) ? width - childWidth : width;
            }
            this.c.a(left, releasedChild.getTop());
            this.a.invalidate();
        }

        public final void b() {
            this.a.postDelayed(this.d, 160);
        }

        final void c() {
            boolean leftEdge;
            View toCapture;
            int childLeft;
            int i = 0;
            int peekDistance = this.c.b();
            if (this.b == 3) {
                leftEdge = true;
            } else {
                leftEdge = false;
            }
            if (leftEdge) {
                toCapture = this.a.b(3);
                if (toCapture != null) {
                    i = -toCapture.getWidth();
                }
                childLeft = i + peekDistance;
            } else {
                toCapture = this.a.b(5);
                childLeft = this.a.getWidth() - peekDistance;
            }
            if (toCapture == null) {
                return;
            }
            if (((leftEdge && toCapture.getLeft() < childLeft) || (!leftEdge && toCapture.getLeft() > childLeft)) && this.a.a(toCapture) == 0) {
                d lp = (d) toCapture.getLayoutParams();
                this.c.a(toCapture, childLeft, toCapture.getTop());
                lp.c = true;
                this.a.invalidate();
                d();
                this.a.b();
            }
        }

        public final void a(int edgeFlags, int pointerId) {
            View toCapture;
            if ((edgeFlags & 1) == 1) {
                toCapture = this.a.b(3);
            } else {
                toCapture = this.a.b(5);
            }
            if (toCapture != null && this.a.a(toCapture) == 0) {
                this.c.a(toCapture, pointerId);
            }
        }

        public final int c(View child) {
            return DrawerLayout.d(child) ? child.getWidth() : 0;
        }

        public final int a(View child, int left) {
            if (this.a.a(child, 3)) {
                return Math.max(-child.getWidth(), Math.min(left, 0));
            }
            int width = this.a.getWidth();
            return Math.max(width - child.getWidth(), Math.min(left, width));
        }

        public final int d(View child) {
            return child.getTop();
        }
    }

    static {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 19) {
            z = true;
        } else {
            z = false;
        }
        b = z;
        if (VERSION.SDK_INT < 21) {
            z2 = false;
        }
        d = z2;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.e = new b();
        this.h = -1728053248;
        this.j = new Paint();
        this.q = true;
        this.r = 3;
        this.s = 3;
        this.t = 3;
        this.u = 3;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        setDescendantFocusability(262144);
        float density = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * density) + 0.5f);
        float minVel = 400.0f * density;
        this.m = new e(this, 3);
        this.n = new e(this, 5);
        this.k = n.a((ViewGroup) this, 1.0f, this.m);
        this.k.a(1);
        this.k.a(minVel);
        this.m.a(this.k);
        this.l = n.a((ViewGroup) this, 1.0f, this.n);
        this.l.a(2);
        this.l.a(minVel);
        this.n.a(this.l);
        setFocusableInTouchMode(true);
        ViewCompat.a((View) this, 1);
        ViewCompat.a((View) this, new a(this));
        q.a(this);
        if (ViewCompat.u(this)) {
            if (VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener(this) {
                    final /* synthetic */ DrawerLayout a;

                    {
                        this.a = this$0;
                    }

                    @TargetApi(21)
                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                        ((DrawerLayout) view).setChildInsets(insets, insets.getSystemWindowInsetTop() > 0);
                        return insets.consumeSystemWindowInsets();
                    }
                });
                setSystemUiVisibility(1280);
                TypedArray a = context.obtainStyledAttributes(c);
                try {
                    this.B = a.getDrawable(0);
                } finally {
                    a.recycle();
                }
            } else {
                this.B = null;
            }
        }
        this.f = 10.0f * density;
        this.M = new ArrayList();
    }

    public void setDrawerElevation(float elevation) {
        this.f = elevation;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (d(child)) {
                ViewCompat.c(child, this.f);
            }
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setChildInsets(Object insets, boolean draw) {
        this.G = insets;
        this.H = draw;
        boolean z = !draw && getBackground() == null;
        setWillNotDraw(z);
        requestLayout();
    }

    public void setDrawerShadow(Drawable shadowDrawable, int gravity) {
        if (!d) {
            if ((gravity & 8388611) == 8388611) {
                this.I = shadowDrawable;
            } else if ((gravity & 8388613) == 8388613) {
                this.J = shadowDrawable;
            } else if ((gravity & 3) == 3) {
                this.K = shadowDrawable;
            } else if ((gravity & 5) == 5) {
                this.L = shadowDrawable;
            } else {
                return;
            }
            d();
            invalidate();
        }
    }

    public void setDrawerShadow(@DrawableRes int resId, int gravity) {
        setDrawerShadow(android.support.v4.content.a.a(getContext(), resId), gravity);
    }

    public void setScrimColor(@ColorInt int color) {
        this.h = color;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(c listener) {
        if (this.x != null) {
            c cVar = this.x;
            if (!(cVar == null || this.y == null)) {
                this.y.remove(cVar);
            }
        }
        if (!(listener == null || listener == null)) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(listener);
        }
        this.x = listener;
    }

    public void setDrawerLockMode(int lockMode) {
        setDrawerLockMode(lockMode, 3);
        setDrawerLockMode(lockMode, 5);
    }

    public void setDrawerLockMode(int lockMode, int edgeGravity) {
        int absGravity = android.support.v4.view.d.a(edgeGravity, ViewCompat.f(this));
        switch (edgeGravity) {
            case 3:
                this.r = lockMode;
                break;
            case 5:
                this.s = lockMode;
                break;
            case 8388611:
                this.t = lockMode;
                break;
            case 8388613:
                this.u = lockMode;
                break;
        }
        if (lockMode != 0) {
            (absGravity == 3 ? this.k : this.l).e();
        }
        switch (lockMode) {
            case 1:
                View toClose = b(absGravity);
                if (toClose != null) {
                    i(toClose);
                    return;
                }
                return;
            case 2:
                View toOpen = b(absGravity);
                if (toOpen != null) {
                    h(toOpen);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int lockMode, View drawerView) {
        if (d(drawerView)) {
            setDrawerLockMode(lockMode, ((d) drawerView.getLayoutParams()).a);
            return;
        }
        throw new IllegalArgumentException("View " + drawerView + " is not a drawer with appropriate layout_gravity");
    }

    public final int a(View drawerView) {
        if (d(drawerView)) {
            int drawerGravity = ((d) drawerView.getLayoutParams()).a;
            int f = ViewCompat.f(this);
            switch (drawerGravity) {
                case 3:
                    if (this.r != 3) {
                        return this.r;
                    }
                    f = f == 0 ? this.t : this.u;
                    if (f != 3) {
                        return f;
                    }
                    break;
                case 5:
                    if (this.s != 3) {
                        return this.s;
                    }
                    f = f == 0 ? this.u : this.t;
                    if (f != 3) {
                        return f;
                    }
                    break;
                case 8388611:
                    if (this.t != 3) {
                        return this.t;
                    }
                    f = f == 0 ? this.r : this.s;
                    if (f != 3) {
                        return f;
                    }
                    break;
                case 8388613:
                    if (this.u != 3) {
                        return this.u;
                    }
                    f = f == 0 ? this.s : this.r;
                    if (f != 3) {
                        return f;
                    }
                    break;
            }
            return 0;
        }
        throw new IllegalArgumentException("View " + drawerView + " is not a drawer");
    }

    public void setDrawerTitle(int edgeGravity, CharSequence title) {
        int absGravity = android.support.v4.view.d.a(edgeGravity, ViewCompat.f(this));
        if (absGravity == 3) {
            this.E = title;
        } else if (absGravity == 5) {
            this.F = title;
        }
    }

    @Nullable
    public final CharSequence a(int edgeGravity) {
        int absGravity = android.support.v4.view.d.a(edgeGravity, ViewCompat.f(this));
        if (absGravity == 3) {
            return this.E;
        }
        if (absGravity == 5) {
            return this.F;
        }
        return null;
    }

    final void a(int activeState, View activeDrawer) {
        int state;
        int leftState = this.k.a();
        int rightState = this.l.a();
        if (leftState == 1 || rightState == 1) {
            state = 1;
        } else if (leftState == 2 || rightState == 2) {
            state = 2;
        } else {
            state = 0;
        }
        if (activeDrawer != null && activeState == 0) {
            d lp = (d) activeDrawer.getLayoutParams();
            d dVar;
            int size;
            if (lp.b == 0.0f) {
                dVar = (d) activeDrawer.getLayoutParams();
                if ((dVar.d & 1) == 1) {
                    dVar.d = 0;
                    if (this.y != null) {
                        for (size = this.y.size() - 1; size >= 0; size--) {
                            ((c) this.y.get(size)).b();
                        }
                    }
                    a(activeDrawer, false);
                    if (hasWindowFocus()) {
                        View rootView = getRootView();
                        if (rootView != null) {
                            rootView.sendAccessibilityEvent(32);
                        }
                    }
                }
            } else if (lp.b == 1.0f) {
                dVar = (d) activeDrawer.getLayoutParams();
                if ((dVar.d & 1) == 0) {
                    dVar.d = 1;
                    if (this.y != null) {
                        for (size = this.y.size() - 1; size >= 0; size--) {
                            ((c) this.y.get(size)).a();
                        }
                    }
                    a(activeDrawer, true);
                    if (hasWindowFocus()) {
                        sendAccessibilityEvent(32);
                    }
                }
            }
        }
        if (state != this.o) {
            this.o = state;
            if (this.y != null) {
                for (int i = this.y.size() - 1; i >= 0; i--) {
                    ((c) this.y.get(i)).a(state);
                }
            }
        }
    }

    private void a(View drawerView, boolean isDrawerOpen) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if ((isDrawerOpen || d(child)) && !(isDrawerOpen && child == drawerView)) {
                ViewCompat.a(child, 4);
            } else {
                ViewCompat.a(child, 1);
            }
        }
    }

    final void a(View drawerView, float slideOffset) {
        d lp = (d) drawerView.getLayoutParams();
        if (slideOffset != lp.b) {
            lp.b = slideOffset;
            if (this.y != null) {
                for (int size = this.y.size() - 1; size >= 0; size--) {
                    ((c) this.y.get(size)).a(slideOffset);
                }
            }
        }
    }

    static float b(View drawerView) {
        return ((d) drawerView.getLayoutParams()).b;
    }

    final int c(View drawerView) {
        return android.support.v4.view.d.a(((d) drawerView.getLayoutParams()).a, ViewCompat.f(this));
    }

    final boolean a(View drawerView, int checkFor) {
        return (c(drawerView) & checkFor) == checkFor;
    }

    private View c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if ((((d) child.getLayoutParams()).d & 1) == 1) {
                return child;
            }
        }
        return null;
    }

    final View b(int gravity) {
        int absHorizGravity = android.support.v4.view.d.a(gravity, ViewCompat.f(this)) & 7;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if ((c(child) & 7) == absHorizGravity) {
                return child;
            }
        }
        return null;
    }

    private static String e(int gravity) {
        if ((gravity & 3) == 3) {
            return "LEFT";
        }
        if ((gravity & 5) == 5) {
            return "RIGHT";
        }
        return Integer.toHexString(gravity);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (!(widthMode == 1073741824 && heightMode == 1073741824)) {
            if (isInEditMode()) {
                if (widthMode != Integer.MIN_VALUE && widthMode == 0) {
                    widthSize = 300;
                }
                if (heightMode != Integer.MIN_VALUE && heightMode == 0) {
                    heightSize = 300;
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(widthSize, heightSize);
        boolean applyInsets = this.G != null && ViewCompat.u(this);
        int layoutDirection = ViewCompat.f(this);
        boolean hasDrawerOnLeftEdge = false;
        boolean hasDrawerOnRightEdge = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                d lp = (d) child.getLayoutParams();
                if (applyInsets) {
                    int cgrav = android.support.v4.view.d.a(lp.a, layoutDirection);
                    WindowInsets wi;
                    if (ViewCompat.u(child)) {
                        if (VERSION.SDK_INT >= 21) {
                            wi = this.G;
                            if (cgrav == 3) {
                                wi = wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(), wi.getSystemWindowInsetTop(), 0, wi.getSystemWindowInsetBottom());
                            } else if (cgrav == 5) {
                                wi = wi.replaceSystemWindowInsets(0, wi.getSystemWindowInsetTop(), wi.getSystemWindowInsetRight(), wi.getSystemWindowInsetBottom());
                            }
                            child.dispatchApplyWindowInsets(wi);
                        }
                    } else if (VERSION.SDK_INT >= 21) {
                        wi = (WindowInsets) this.G;
                        if (cgrav == 3) {
                            wi = wi.replaceSystemWindowInsets(wi.getSystemWindowInsetLeft(), wi.getSystemWindowInsetTop(), 0, wi.getSystemWindowInsetBottom());
                        } else if (cgrav == 5) {
                            wi = wi.replaceSystemWindowInsets(0, wi.getSystemWindowInsetTop(), wi.getSystemWindowInsetRight(), wi.getSystemWindowInsetBottom());
                        }
                        lp.leftMargin = wi.getSystemWindowInsetLeft();
                        lp.topMargin = wi.getSystemWindowInsetTop();
                        lp.rightMargin = wi.getSystemWindowInsetRight();
                        lp.bottomMargin = wi.getSystemWindowInsetBottom();
                    }
                }
                if (g(child)) {
                    child.measure(MeasureSpec.makeMeasureSpec((widthSize - lp.leftMargin) - lp.rightMargin, ErrorDialogData.SUPPRESSED), MeasureSpec.makeMeasureSpec((heightSize - lp.topMargin) - lp.bottomMargin, ErrorDialogData.SUPPRESSED));
                } else if (d(child)) {
                    if (d && ViewCompat.q(child) != this.f) {
                        ViewCompat.c(child, this.f);
                    }
                    int childGravity = c(child) & 7;
                    boolean isLeftEdgeDrawer = childGravity == 3;
                    if (!(isLeftEdgeDrawer && hasDrawerOnLeftEdge) && (isLeftEdgeDrawer || !hasDrawerOnRightEdge)) {
                        if (isLeftEdgeDrawer) {
                            hasDrawerOnLeftEdge = true;
                        } else {
                            hasDrawerOnRightEdge = true;
                        }
                        child.measure(getChildMeasureSpec(widthMeasureSpec, (this.g + lp.leftMargin) + lp.rightMargin, lp.width), getChildMeasureSpec(heightMeasureSpec, lp.topMargin + lp.bottomMargin, lp.height));
                    } else {
                        throw new IllegalStateException("Child drawer has absolute gravity " + e(childGravity) + " but this DrawerLayout already has a drawer view along that edge");
                    }
                } else {
                    throw new IllegalStateException("Child " + child + " at index " + i + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
        }
    }

    private void d() {
        if (!d) {
            Drawable drawable;
            int f = ViewCompat.f(this);
            if (f == 0) {
                if (this.I != null) {
                    a(this.I, f);
                    drawable = this.I;
                }
                drawable = this.K;
            } else {
                if (this.J != null) {
                    a(this.J, f);
                    drawable = this.J;
                }
                drawable = this.K;
            }
            this.C = drawable;
            f = ViewCompat.f(this);
            if (f == 0) {
                if (this.J != null) {
                    a(this.J, f);
                    drawable = this.J;
                }
                drawable = this.L;
            } else {
                if (this.I != null) {
                    a(this.I, f);
                    drawable = this.I;
                }
                drawable = this.L;
            }
            this.D = drawable;
        }
    }

    private static boolean a(Drawable drawable, int layoutDirection) {
        if (drawable == null || !android.support.v4.graphics.drawable.a.b(drawable)) {
            return false;
        }
        android.support.v4.graphics.drawable.a.b(drawable, layoutDirection);
        return true;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.p = true;
        int width = r - l;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                d lp = (d) child.getLayoutParams();
                if (g(child)) {
                    child.layout(lp.leftMargin, lp.topMargin, lp.leftMargin + child.getMeasuredWidth(), lp.topMargin + child.getMeasuredHeight());
                } else {
                    int childLeft;
                    float newOffset;
                    int childWidth = child.getMeasuredWidth();
                    int childHeight = child.getMeasuredHeight();
                    if (a(child, 3)) {
                        childLeft = (-childWidth) + ((int) (((float) childWidth) * lp.b));
                        newOffset = ((float) (childWidth + childLeft)) / ((float) childWidth);
                    } else {
                        childLeft = width - ((int) (((float) childWidth) * lp.b));
                        newOffset = ((float) (width - childLeft)) / ((float) childWidth);
                    }
                    boolean changeOffset = newOffset != lp.b;
                    int height;
                    switch (lp.a & 112) {
                        case 16:
                            height = b - t;
                            int childTop = (height - childHeight) / 2;
                            if (childTop < lp.topMargin) {
                                childTop = lp.topMargin;
                            } else {
                                if (childTop + childHeight > height - lp.bottomMargin) {
                                    childTop = (height - lp.bottomMargin) - childHeight;
                                }
                            }
                            child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
                            break;
                        case 80:
                            height = b - t;
                            child.layout(childLeft, (height - lp.bottomMargin) - child.getMeasuredHeight(), childLeft + childWidth, height - lp.bottomMargin);
                            break;
                        default:
                            child.layout(childLeft, lp.topMargin, childLeft + childWidth, lp.topMargin + childHeight);
                            break;
                    }
                    if (changeOffset) {
                        a(child, newOffset);
                    }
                    int newVisibility = lp.b > 0.0f ? 0 : 4;
                    if (child.getVisibility() != newVisibility) {
                        child.setVisibility(newVisibility);
                    }
                }
            }
        }
        this.p = false;
        this.q = false;
    }

    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float scrimOpacity = 0.0f;
        for (int i = 0; i < childCount; i++) {
            scrimOpacity = Math.max(scrimOpacity, ((d) getChildAt(i).getLayoutParams()).b);
        }
        this.i = scrimOpacity;
        boolean leftDraggerSettling = this.k.g();
        boolean rightDraggerSettling = this.l.g();
        if (leftDraggerSettling || rightDraggerSettling) {
            ViewCompat.d(this);
        }
    }

    public void setStatusBarBackground(Drawable bg) {
        this.B = bg;
        invalidate();
    }

    public void setStatusBarBackground(int resId) {
        this.B = resId != 0 ? android.support.v4.content.a.a(getContext(), resId) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int color) {
        this.B = new ColorDrawable(color);
        invalidate();
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        d();
    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        if (this.H && this.B != null) {
            int inset = VERSION.SDK_INT >= 21 ? this.G != null ? ((WindowInsets) this.G).getSystemWindowInsetTop() : 0 : 0;
            if (inset > 0) {
                this.B.setBounds(0, 0, getWidth(), inset);
                this.B.draw(c);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        int height = getHeight();
        boolean drawingContent = g(child);
        int clipLeft = 0;
        int clipRight = getWidth();
        int restoreCount = canvas.save();
        if (drawingContent) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View v = getChildAt(i);
                if (v != child && v.getVisibility() == 0) {
                    Object obj;
                    Drawable background = v.getBackground();
                    if (background == null) {
                        obj = null;
                    } else if (background.getOpacity() == -1) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null && d(v) && v.getHeight() >= height) {
                        if (a(v, 3)) {
                            int vright = v.getRight();
                            if (vright > clipLeft) {
                                clipLeft = vright;
                            }
                        } else {
                            int vleft = v.getLeft();
                            if (vleft < clipRight) {
                                clipRight = vleft;
                            }
                        }
                    }
                }
            }
            canvas.clipRect(clipLeft, 0, clipRight, getHeight());
        }
        boolean result = super.drawChild(canvas, child, drawingTime);
        canvas.restoreToCount(restoreCount);
        int shadowWidth;
        float alpha;
        if (this.i > 0.0f && drawingContent) {
            this.j.setColor((((int) (((float) ((this.h & -16777216) >>> 24)) * this.i)) << 24) | (this.h & 16777215));
            canvas.drawRect((float) clipLeft, 0.0f, (float) clipRight, (float) getHeight(), this.j);
        } else if (this.C != null && a(child, 3)) {
            shadowWidth = this.C.getIntrinsicWidth();
            int childRight = child.getRight();
            alpha = Math.max(0.0f, Math.min(((float) childRight) / ((float) this.k.b()), 1.0f));
            this.C.setBounds(childRight, child.getTop(), childRight + shadowWidth, child.getBottom());
            this.C.setAlpha((int) (255.0f * alpha));
            this.C.draw(canvas);
        } else if (this.D != null && a(child, 5)) {
            shadowWidth = this.D.getIntrinsicWidth();
            int childLeft = child.getLeft();
            alpha = Math.max(0.0f, Math.min(((float) (getWidth() - childLeft)) / ((float) this.l.b()), 1.0f));
            this.D.setBounds(childLeft - shadowWidth, child.getTop(), childLeft, child.getBottom());
            this.D.setAlpha((int) (255.0f * alpha));
            this.D.draw(canvas);
        }
        return result;
    }

    private static boolean g(View child) {
        return ((d) child.getLayoutParams()).a == 0;
    }

    static boolean d(View child) {
        int absGravity = android.support.v4.view.d.a(((d) child.getLayoutParams()).a, ViewCompat.f(child));
        if ((absGravity & 3) != 0) {
            return true;
        }
        if ((absGravity & 5) != 0) {
            return true;
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean interceptForDrag = this.k.a(ev) | this.l.a(ev);
        boolean interceptForTap = false;
        switch (ev.getActionMasked()) {
            case 0:
                float x = ev.getX();
                float y = ev.getY();
                this.z = x;
                this.A = y;
                if (this.i > 0.0f) {
                    View child = this.k.b((int) x, (int) y);
                    if (child != null && g(child)) {
                        interceptForTap = true;
                    }
                }
                this.v = false;
                this.w = false;
                break;
            case 1:
            case 3:
                a(true);
                this.v = false;
                this.w = false;
                break;
            case 2:
                if (this.k.h()) {
                    this.m.a();
                    this.n.a();
                    break;
                }
                break;
        }
        if (!(interceptForDrag || interceptForTap)) {
            boolean z;
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (((d) getChildAt(i).getLayoutParams()).c) {
                    z = true;
                    if (!(z || this.w)) {
                        return false;
                    }
                }
            }
            z = false;
            return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        this.k.b(ev);
        this.l.b(ev);
        float x;
        float y;
        switch (ev.getAction() & 255) {
            case 0:
                x = ev.getX();
                y = ev.getY();
                this.z = x;
                this.A = y;
                this.v = false;
                this.w = false;
                break;
            case 1:
                x = ev.getX();
                y = ev.getY();
                boolean peekingOnly = true;
                View touchedView = this.k.b((int) x, (int) y);
                if (touchedView != null && g(touchedView)) {
                    float dx = x - this.z;
                    float dy = y - this.A;
                    int slop = this.k.d();
                    if ((dx * dx) + (dy * dy) < ((float) (slop * slop))) {
                        View openDrawer = c();
                        if (openDrawer != null) {
                            peekingOnly = a(openDrawer) == 2;
                        }
                    }
                }
                a(peekingOnly);
                this.v = false;
                break;
            case 3:
                a(true);
                this.v = false;
                this.w = false;
                break;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        this.v = disallowIntercept;
        if (disallowIntercept) {
            a(true);
        }
    }

    private void a(boolean peekingOnly) {
        boolean needsInvalidate = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            d lp = (d) child.getLayoutParams();
            if (d(child) && (!peekingOnly || lp.c)) {
                int childWidth = child.getWidth();
                if (a(child, 3)) {
                    needsInvalidate |= this.k.a(child, -childWidth, child.getTop());
                } else {
                    needsInvalidate |= this.l.a(child, getWidth(), child.getTop());
                }
                lp.c = false;
            }
        }
        this.m.a();
        this.n.a();
        if (needsInvalidate) {
            invalidate();
        }
    }

    private void h(View drawerView) {
        if (d(drawerView)) {
            d lp = (d) drawerView.getLayoutParams();
            if (this.q) {
                lp.b = 1.0f;
                lp.d = 1;
                a(drawerView, true);
            } else {
                lp.d |= 2;
                if (a(drawerView, 3)) {
                    this.k.a(drawerView, 0, drawerView.getTop());
                } else {
                    this.l.a(drawerView, getWidth() - drawerView.getWidth(), drawerView.getTop());
                }
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + drawerView + " is not a sliding drawer");
    }

    public final void e(View drawerView) {
        i(drawerView);
    }

    private void i(View drawerView) {
        if (d(drawerView)) {
            d lp = (d) drawerView.getLayoutParams();
            if (this.q) {
                lp.b = 0.0f;
                lp.d = 0;
            } else {
                lp.d |= 4;
                if (a(drawerView, 3)) {
                    this.k.a(drawerView, -drawerView.getWidth(), drawerView.getTop());
                } else {
                    this.l.a(drawerView, getWidth(), drawerView.getTop());
                }
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + drawerView + " is not a sliding drawer");
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        if (p instanceof d) {
            return new d((d) p);
        }
        return p instanceof MarginLayoutParams ? new d((MarginLayoutParams) p) : new d(p);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof d) && super.checkLayoutParams(p);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new d(getContext(), attrs);
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        if (getDescendantFocusability() != 393216) {
            int i;
            View child;
            int childCount = getChildCount();
            boolean isDrawerOpen = false;
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                if (!d(child)) {
                    this.M.add(child);
                } else if (d(child)) {
                    Object obj;
                    if ((((d) child.getLayoutParams()).d & 1) == 1) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        isDrawerOpen = true;
                        child.addFocusables(views, direction, focusableMode);
                    }
                } else {
                    throw new IllegalArgumentException("View " + child + " is not a drawer");
                }
            }
            if (!isDrawerOpen) {
                int nonDrawerViewsCount = this.M.size();
                for (i = 0; i < nonDrawerViewsCount; i++) {
                    child = (View) this.M.get(i);
                    if (child.getVisibility() == 0) {
                        child.addFocusables(views, direction, focusableMode);
                    }
                }
            }
            this.M.clear();
        }
    }

    final View a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (d(child)) {
                if (d(child)) {
                    Object obj;
                    if (((d) child.getLayoutParams()).b > 0.0f) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        return child;
                    }
                } else {
                    throw new IllegalArgumentException("View " + child + " is not a drawer");
                }
            }
        }
        return null;
    }

    final void b() {
        if (!this.w) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent cancelEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(cancelEvent);
            }
            cancelEvent.recycle();
            this.w = true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            boolean z;
            if (a() != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                event.startTracking();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyUp(keyCode, event);
        }
        View visibleDrawer = a();
        if (visibleDrawer != null && a(visibleDrawer) == 0) {
            a(false);
        }
        if (visibleDrawer != null) {
            return true;
        }
        return false;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.a());
            if (ss.b != 0) {
                View toOpen = b(ss.b);
                if (toOpen != null) {
                    h(toOpen);
                }
            }
            if (ss.c != 3) {
                setDrawerLockMode(ss.c, 3);
            }
            if (ss.d != 3) {
                setDrawerLockMode(ss.d, 5);
            }
            if (ss.e != 3) {
                setDrawerLockMode(ss.e, 8388611);
            }
            if (ss.f != 3) {
                setDrawerLockMode(ss.f, 8388613);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            boolean isOpenedAndNotClosing;
            d lp = (d) getChildAt(i).getLayoutParams();
            if (lp.d == 1) {
                isOpenedAndNotClosing = true;
            } else {
                isOpenedAndNotClosing = false;
            }
            boolean isClosedAndOpening;
            if (lp.d == 2) {
                isClosedAndOpening = true;
            } else {
                isClosedAndOpening = false;
            }
            if (isOpenedAndNotClosing || isClosedAndOpening) {
                ss.b = lp.a;
                break;
            }
        }
        ss.c = this.r;
        ss.d = this.s;
        ss.e = this.t;
        ss.f = this.u;
        return ss;
    }

    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        if (c() != null || d(child)) {
            ViewCompat.a(child, 4);
        } else {
            ViewCompat.a(child, 1);
        }
        if (!b) {
            ViewCompat.a(child, this.e);
        }
    }

    static boolean f(View child) {
        return (ViewCompat.e(child) == 4 || ViewCompat.e(child) == 2) ? false : true;
    }

    public final void c(int gravity) {
        View b = b(gravity);
        if (b == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + e(gravity));
        }
        h(b);
    }

    public final void d(int gravity) {
        View b = b(gravity);
        if (b == null) {
            throw new IllegalArgumentException("No drawer view found with gravity " + e(gravity));
        }
        i(b);
    }
}
