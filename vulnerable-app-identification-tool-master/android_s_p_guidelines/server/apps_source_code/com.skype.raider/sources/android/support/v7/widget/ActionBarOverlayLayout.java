package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.j;
import android.support.v4.view.l;
import android.support.v7.appcompat.a.f;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.Window.Callback;
import android.widget.OverScroller;
import com.skype.Defines;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class ActionBarOverlayLayout extends ViewGroup implements j, q {
    static final int[] e = new int[]{android.support.v7.appcompat.a.a.actionBarSize, 16842841};
    private final Runnable A;
    private final Runnable B;
    private final l C;
    ActionBarContainer a;
    boolean b;
    ViewPropertyAnimator c;
    final AnimatorListenerAdapter d;
    private int f;
    private int g;
    private ContentFrameLayout h;
    private r i;
    private Drawable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;
    private final Rect w;
    private a x;
    private final int y;
    private OverScroller z;

    public interface a {
        void a(int i);

        void d(boolean z);

        void h();

        void i();

        void j();
    }

    public static class b extends MarginLayoutParams {
        public b(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public b() {
            super(-1, -1);
        }

        public b(LayoutParams source) {
            super(source);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.g = 0;
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        this.w = new Rect();
        this.y = 600;
        this.d = new AnimatorListenerAdapter(this) {
            final /* synthetic */ ActionBarOverlayLayout a;

            {
                this.a = this$0;
            }

            public final void onAnimationEnd(Animator animator) {
                this.a.c = null;
                this.a.b = false;
            }

            public final void onAnimationCancel(Animator animator) {
                this.a.c = null;
                this.a.b = false;
            }
        };
        this.A = new Runnable(this) {
            final /* synthetic */ ActionBarOverlayLayout a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b();
                this.a.c = this.a.a.animate().translationY(0.0f).setListener(this.a.d);
            }
        };
        this.B = new Runnable(this) {
            final /* synthetic */ ActionBarOverlayLayout a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b();
                this.a.c = this.a.a.animate().translationY((float) (-this.a.a.getHeight())).setListener(this.a.d);
            }
        };
        a(context);
        this.C = new l(this);
    }

    private void a(Context context) {
        boolean z = true;
        TypedArray ta = getContext().getTheme().obtainStyledAttributes(e);
        this.f = ta.getDimensionPixelSize(0, 0);
        this.j = ta.getDrawable(1);
        setWillNotDraw(this.j == null);
        ta.recycle();
        if (context.getApplicationInfo().targetSdkVersion >= 19) {
            z = false;
        }
        this.k = z;
        this.z = new OverScroller(context);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    public void setActionBarVisibilityCallback(a cb) {
        this.x = cb;
        if (getWindowToken() != null) {
            this.x.a(this.g);
            if (this.p != 0) {
                onWindowSystemUiVisibilityChanged(this.p);
                ViewCompat.t(this);
            }
        }
    }

    public void setOverlayMode(boolean overlayMode) {
        this.l = overlayMode;
        boolean z = overlayMode && getContext().getApplicationInfo().targetSdkVersion < 19;
        this.k = z;
    }

    public final boolean a() {
        return this.l;
    }

    public void setHasNonEmbeddedTabs(boolean hasNonEmbeddedTabs) {
        this.m = hasNonEmbeddedTabs;
    }

    public void setShowingForActionMode(boolean showing) {
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        a(getContext());
        ViewCompat.t(this);
    }

    public void onWindowSystemUiVisibilityChanged(int visible) {
        boolean barVisible;
        boolean stable;
        boolean z = true;
        if (VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(visible);
        }
        i();
        int diff = this.p ^ visible;
        this.p = visible;
        if ((visible & 4) == 0) {
            barVisible = true;
        } else {
            barVisible = false;
        }
        if ((visible & Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) != 0) {
            stable = true;
        } else {
            stable = false;
        }
        if (this.x != null) {
            a aVar = this.x;
            if (stable) {
                z = false;
            }
            aVar.d(z);
            if (barVisible || !stable) {
                this.x.h();
            } else {
                this.x.i();
            }
        }
        if ((diff & Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) != 0 && this.x != null) {
            ViewCompat.t(this);
        }
    }

    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.g = visibility;
        if (this.x != null) {
            this.x.a(visibility);
        }
    }

    private static boolean a(View view, Rect insets, boolean bottom) {
        boolean changed = false;
        b lp = (b) view.getLayoutParams();
        if (lp.leftMargin != insets.left) {
            changed = true;
            lp.leftMargin = insets.left;
        }
        if (lp.topMargin != insets.top) {
            changed = true;
            lp.topMargin = insets.top;
        }
        if (lp.rightMargin != insets.right) {
            changed = true;
            lp.rightMargin = insets.right;
        }
        if (!bottom || lp.bottomMargin == insets.bottom) {
            return changed;
        }
        lp.bottomMargin = insets.bottom;
        return true;
    }

    protected boolean fitSystemWindows(Rect insets) {
        i();
        ViewCompat.s(this);
        Rect systemInsets = insets;
        boolean changed = a(this.a, systemInsets, false);
        this.t.set(systemInsets);
        ax.a(this, this.t, this.q);
        if (!this.u.equals(this.t)) {
            changed = true;
            this.u.set(this.t);
        }
        if (!this.r.equals(this.q)) {
            changed = true;
            this.r.set(this.q);
        }
        if (changed) {
            requestLayout();
        }
        return true;
    }

    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new b(p);
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof b;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean stable;
        i();
        int topInset = 0;
        measureChildWithMargins(this.a, widthMeasureSpec, 0, heightMeasureSpec, 0);
        b lp = (b) this.a.getLayoutParams();
        int maxWidth = Math.max(0, (this.a.getMeasuredWidth() + lp.leftMargin) + lp.rightMargin);
        int maxHeight = Math.max(0, (this.a.getMeasuredHeight() + lp.topMargin) + lp.bottomMargin);
        int childState = View.combineMeasuredStates(0, this.a.getMeasuredState());
        if ((ViewCompat.s(this) & Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE) != 0) {
            stable = true;
        } else {
            stable = false;
        }
        if (stable) {
            topInset = this.f;
            if (this.m && this.a.a() != null) {
                topInset += this.f;
            }
        } else if (this.a.getVisibility() != 8) {
            topInset = this.a.getMeasuredHeight();
        }
        this.s.set(this.q);
        this.v.set(this.t);
        Rect rect;
        if (this.l || stable) {
            rect = this.v;
            rect.top += topInset;
            rect = this.v;
            rect.bottom += 0;
        } else {
            rect = this.s;
            rect.top += topInset;
            rect = this.s;
            rect.bottom += 0;
        }
        a(this.h, this.s, true);
        if (!this.w.equals(this.v)) {
            this.w.set(this.v);
            this.h.a(this.v);
        }
        measureChildWithMargins(this.h, widthMeasureSpec, 0, heightMeasureSpec, 0);
        lp = (b) this.h.getLayoutParams();
        maxWidth = Math.max(maxWidth, (this.h.getMeasuredWidth() + lp.leftMargin) + lp.rightMargin);
        maxHeight = Math.max(maxHeight, (this.h.getMeasuredHeight() + lp.topMargin) + lp.bottomMargin);
        childState = View.combineMeasuredStates(childState, this.h.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(maxWidth + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), widthMeasureSpec, childState), View.resolveSizeAndState(Math.max((getPaddingTop() + getPaddingBottom()) + maxHeight, getSuggestedMinimumHeight()), heightMeasureSpec, childState << 16));
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int parentLeft = getPaddingLeft();
        getPaddingRight();
        int parentTop = getPaddingTop();
        getPaddingBottom();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                b lp = (b) child.getLayoutParams();
                int childLeft = parentLeft + lp.leftMargin;
                int childTop = parentTop + lp.topMargin;
                child.layout(childLeft, childTop, childLeft + child.getMeasuredWidth(), childTop + child.getMeasuredHeight());
            }
        }
    }

    public void draw(Canvas c) {
        super.draw(c);
        if (this.j != null && !this.k) {
            int top = this.a.getVisibility() == 0 ? (int) ((((float) this.a.getBottom()) + this.a.getTranslationY()) + 0.5f) : 0;
            this.j.setBounds(0, top, getWidth(), this.j.getIntrinsicHeight() + top);
            this.j.draw(c);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean onStartNestedScroll(View child, View target, int axes) {
        if ((axes & 2) == 0 || this.a.getVisibility() != 0) {
            return false;
        }
        return this.n;
    }

    public void onNestedScrollAccepted(View child, View target, int axes) {
        int i;
        this.C.a(axes);
        if (this.a != null) {
            i = -((int) this.a.getTranslationY());
        } else {
            i = 0;
        }
        this.o = i;
        b();
        if (this.x != null) {
            this.x.j();
        }
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        this.o += dyConsumed;
        setActionBarHideOffset(this.o);
    }

    public void onStopNestedScroll(View target) {
        if (this.n && !this.b) {
            if (this.o <= this.a.getHeight()) {
                b();
                postDelayed(this.A, 600);
                return;
            }
            b();
            postDelayed(this.B, 600);
        }
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        boolean z = false;
        if (!this.n || !consumed) {
            return false;
        }
        this.z.fling(0, 0, 0, (int) velocityY, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.z.getFinalY() > this.a.getHeight()) {
            z = true;
        }
        if (z) {
            b();
            this.B.run();
        } else {
            b();
            this.A.run();
        }
        this.b = true;
        return true;
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    public int getNestedScrollAxes() {
        return this.C.a();
    }

    private void i() {
        if (this.h == null) {
            r rVar;
            this.h = (ContentFrameLayout) findViewById(f.action_bar_activity_content);
            this.a = (ActionBarContainer) findViewById(f.action_bar_container);
            View findViewById = findViewById(f.action_bar);
            if (findViewById instanceof r) {
                rVar = (r) findViewById;
            } else if (findViewById instanceof Toolbar) {
                rVar = ((Toolbar) findViewById).r();
            } else {
                throw new IllegalStateException("Can't make a decor toolbar out of " + findViewById.getClass().getSimpleName());
            }
            this.i = rVar;
        }
    }

    public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
        if (hideOnContentScroll != this.n) {
            this.n = hideOnContentScroll;
            if (!hideOnContentScroll) {
                b();
                setActionBarHideOffset(0);
            }
        }
    }

    public void setActionBarHideOffset(int offset) {
        b();
        this.a.setTranslationY((float) (-Math.max(0, Math.min(offset, this.a.getHeight()))));
    }

    final void b() {
        removeCallbacks(this.A);
        removeCallbacks(this.B);
        if (this.c != null) {
            this.c.cancel();
        }
    }

    public void setWindowCallback(Callback cb) {
        i();
        this.i.a(cb);
    }

    public void setWindowTitle(CharSequence title) {
        i();
        this.i.a(title);
    }

    public final void a(int windowFeature) {
        i();
        switch (windowFeature) {
            case 109:
                setOverlayMode(true);
                return;
            default:
                return;
        }
    }

    public void setUiOptions(int uiOptions) {
    }

    public void setIcon(int resId) {
        i();
        this.i.a(resId);
    }

    public void setIcon(Drawable d) {
        i();
        this.i.a(d);
    }

    public void setLogo(int resId) {
        i();
        this.i.b(resId);
    }

    public final boolean c() {
        i();
        return this.i.e();
    }

    public final boolean d() {
        i();
        return this.i.f();
    }

    public final boolean e() {
        i();
        return this.i.g();
    }

    public final boolean f() {
        i();
        return this.i.h();
    }

    public final boolean g() {
        i();
        return this.i.i();
    }

    public void setMenuPrepared() {
        i();
        this.i.j();
    }

    public void setMenu(Menu menu, android.support.v7.view.menu.n.a cb) {
        i();
        this.i.a(menu, cb);
    }

    public final void h() {
        i();
        this.i.k();
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return new b();
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }
}
