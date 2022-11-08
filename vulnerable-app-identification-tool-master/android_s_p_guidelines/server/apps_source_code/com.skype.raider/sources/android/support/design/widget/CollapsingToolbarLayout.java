package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.a.f;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.m;
import android.support.v4.view.w;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class CollapsingToolbarLayout extends FrameLayout {
    private boolean a;
    private int b;
    private Toolbar c;
    private View d;
    private int e;
    private int f;
    private int g;
    private int h;
    private final Rect i;
    private final d j;
    private boolean k;
    private boolean l;
    private Drawable m;
    private Drawable n;
    private int o;
    private boolean p;
    private s q;
    private android.support.design.widget.AppBarLayout.a r;
    private int s;
    private w t;

    public static class a extends LayoutParams {
        int a = 0;
        float b = 0.5f;

        public a(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, j.CollapsingAppBarLayout_LayoutParams);
            this.a = a.getInt(j.CollapsingAppBarLayout_LayoutParams_layout_collapseMode, 0);
            this.b = a.getFloat(j.CollapsingAppBarLayout_LayoutParams_layout_collapseParallaxMultiplier, 0.5f);
            a.recycle();
        }

        public a(ViewGroup.LayoutParams p) {
            super(p);
        }

        public a(LayoutParams source) {
            super(source);
        }
    }

    private class b implements android.support.design.widget.AppBarLayout.a {
        final /* synthetic */ CollapsingToolbarLayout a;

        private b(CollapsingToolbarLayout collapsingToolbarLayout) {
            this.a = collapsingToolbarLayout;
        }

        /* synthetic */ b(CollapsingToolbarLayout x0, byte b) {
            this(x0);
        }

        public final void a(AppBarLayout layout, int verticalOffset) {
            int insetTop;
            boolean z = false;
            this.a.s = verticalOffset;
            if (this.a.t != null) {
                insetTop = this.a.t.b();
            } else {
                insetTop = 0;
            }
            int scrollRange = layout.a();
            int z2 = this.a.getChildCount();
            for (int i = 0; i < z2; i++) {
                View child = this.a.getChildAt(i);
                a lp = (a) child.getLayoutParams();
                y offsetHelper = CollapsingToolbarLayout.b(child);
                switch (lp.a) {
                    case 1:
                        if ((this.a.getHeight() - insetTop) + verticalOffset < child.getHeight()) {
                            break;
                        }
                        offsetHelper.a(-verticalOffset);
                        break;
                    case 2:
                        offsetHelper.a(Math.round(((float) (-verticalOffset)) * lp.b));
                        break;
                    default:
                        break;
                }
            }
            if (!(this.a.m == null && this.a.n == null)) {
                CollapsingToolbarLayout collapsingToolbarLayout = this.a;
                if (this.a.getHeight() + verticalOffset < (ViewCompat.o(this.a) * 2) + insetTop) {
                    z = true;
                }
                collapsingToolbarLayout.setScrimsShown(z);
            }
            if (this.a.n != null && insetTop > 0) {
                ViewCompat.d(this.a);
            }
            this.a.j.b(((float) Math.abs(verticalOffset)) / ((float) ((this.a.getHeight() - ViewCompat.o(this.a)) - insetTop)));
            if (Math.abs(verticalOffset) == scrollRange) {
                ViewCompat.c((View) layout, layout.c());
            } else {
                ViewCompat.c((View) layout, 0.0f);
            }
        }
    }

    protected /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return c();
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return c();
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        boolean isRtl;
        super(context, attrs, defStyleAttr);
        this.a = true;
        this.i = new Rect();
        r.a(context);
        this.j = new d(this);
        this.j.a(a.c);
        TypedArray a = context.obtainStyledAttributes(attrs, j.CollapsingToolbarLayout, defStyleAttr, i.Widget_Design_CollapsingToolbar);
        this.j.c(a.getInt(j.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
        this.j.d(a.getInt(j.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
        int dimensionPixelSize = a.getDimensionPixelSize(j.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.h = dimensionPixelSize;
        this.g = dimensionPixelSize;
        this.f = dimensionPixelSize;
        this.e = dimensionPixelSize;
        if (ViewCompat.f(this) == 1) {
            isRtl = true;
        } else {
            isRtl = false;
        }
        if (a.hasValue(j.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            int marginStart = a.getDimensionPixelSize(j.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
            if (isRtl) {
                this.g = marginStart;
            } else {
                this.e = marginStart;
            }
        }
        if (a.hasValue(j.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            int marginEnd = a.getDimensionPixelSize(j.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
            if (isRtl) {
                this.e = marginEnd;
            } else {
                this.g = marginEnd;
            }
        }
        if (a.hasValue(j.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.f = a.getDimensionPixelSize(j.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if (a.hasValue(j.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.h = a.getDimensionPixelSize(j.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.k = a.getBoolean(j.CollapsingToolbarLayout_titleEnabled, true);
        setTitle(a.getText(j.CollapsingToolbarLayout_title));
        this.j.f(i.TextAppearance_Design_CollapsingToolbar_Expanded);
        this.j.e(i.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if (a.hasValue(j.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            this.j.f(a.getResourceId(j.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if (a.hasValue(j.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            this.j.e(a.getResourceId(j.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        setContentScrim(a.getDrawable(j.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(a.getDrawable(j.CollapsingToolbarLayout_statusBarScrim));
        this.b = a.getResourceId(j.CollapsingToolbarLayout_toolbarId, -1);
        a.recycle();
        setWillNotDraw(false);
        ViewCompat.a((View) this, new m(this) {
            final /* synthetic */ CollapsingToolbarLayout a;

            {
                this.a = r1;
            }

            public final w a(View v, w insets) {
                this.a.t = insets;
                this.a.requestLayout();
                return insets.f();
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            if (this.r == null) {
                this.r = new b();
            }
            ((AppBarLayout) parent).a(this.r);
        }
    }

    protected void onDetachedFromWindow() {
        ViewParent parent = getParent();
        if (this.r != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).b(this.r);
        }
        super.onDetachedFromWindow();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        a();
        if (this.c == null && this.m != null && this.o > 0) {
            this.m.mutate().setAlpha(this.o);
            this.m.draw(canvas);
        }
        if (this.k && this.l) {
            this.j.a(canvas);
        }
        if (this.n != null && this.o > 0) {
            int topInset = this.t != null ? this.t.b() : 0;
            if (topInset > 0) {
                this.n.setBounds(0, -this.s, getWidth(), topInset - this.s);
                this.n.mutate().setAlpha(this.o);
                this.n.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        a();
        if (child == this.c && this.m != null && this.o > 0) {
            this.m.mutate().setAlpha(this.o);
            this.m.draw(canvas);
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.m != null) {
            this.m.setBounds(0, 0, w, h);
        }
    }

    private void a() {
        if (this.a) {
            Toolbar fallback = null;
            Toolbar selected = null;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (child instanceof Toolbar) {
                    if (this.b == -1) {
                        selected = (Toolbar) child;
                        break;
                    } else if (this.b == child.getId()) {
                        selected = (Toolbar) child;
                        break;
                    } else if (fallback == null) {
                        fallback = (Toolbar) child;
                    }
                }
            }
            if (selected == null) {
                selected = fallback;
            }
            this.c = selected;
            b();
            this.a = false;
        }
    }

    private void b() {
        if (!(this.k || this.d == null)) {
            ViewParent parent = this.d.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.d);
            }
        }
        if (this.k && this.c != null) {
            if (this.d == null) {
                this.d = new View(getContext());
            }
            if (this.d.getParent() == null) {
                this.c.addView(this.d, -1, -1);
            }
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        a();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.k && this.d != null) {
            this.l = this.d.isShown();
            if (this.l) {
                v.a(this, this.d, this.i);
                this.j.b(this.i.left, bottom - this.i.height(), this.i.right, bottom);
                this.j.a(this.e, this.i.bottom + this.f, (right - left) - this.g, (bottom - top) - this.h);
                this.j.d();
            }
        }
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            View child = getChildAt(i);
            if (!(this.t == null || ViewCompat.u(child))) {
                int insetTop = this.t.b();
                if (child.getTop() < insetTop) {
                    child.offsetTopAndBottom(insetTop);
                }
            }
            b(child).a();
        }
        if (this.c != null) {
            if (this.k && TextUtils.isEmpty(this.j.e())) {
                this.j.a(this.c.i());
            }
            setMinimumHeight(this.c.getHeight());
        }
    }

    private static y b(View view) {
        y offsetHelper = (y) view.getTag(f.view_offset_helper);
        if (offsetHelper != null) {
            return offsetHelper;
        }
        offsetHelper = new y(view);
        view.setTag(f.view_offset_helper, offsetHelper);
        return offsetHelper;
    }

    public void setTitle(@Nullable CharSequence title) {
        this.j.a(title);
    }

    public void setTitleEnabled(boolean enabled) {
        if (enabled != this.k) {
            this.k = enabled;
            b();
            requestLayout();
        }
    }

    public void setScrimsShown(boolean shown) {
        boolean z = ViewCompat.B(this) && !isInEditMode();
        setScrimsShown(shown, z);
    }

    public void setScrimsShown(boolean shown, boolean animate) {
        int i = 255;
        if (this.p != shown) {
            if (animate) {
                if (!shown) {
                    i = 0;
                }
                a();
                if (this.q == null) {
                    this.q = z.a();
                    this.q.a(600);
                    this.q.a(a.b);
                    this.q.a(new c(this) {
                        final /* synthetic */ CollapsingToolbarLayout a;

                        {
                            this.a = r1;
                        }

                        public final void a(s animator) {
                            this.a.a(animator.c());
                        }
                    });
                } else if (this.q.b()) {
                    this.q.e();
                }
                this.q.a(this.o, i);
                this.q.a();
            } else {
                if (!shown) {
                    i = 0;
                }
                a(i);
            }
            this.p = shown;
        }
    }

    private void a(int alpha) {
        if (alpha != this.o) {
            if (!(this.m == null || this.c == null)) {
                ViewCompat.d(this.c);
            }
            this.o = alpha;
            ViewCompat.d(this);
        }
    }

    public void setContentScrim(@Nullable Drawable drawable) {
        if (this.m != drawable) {
            if (this.m != null) {
                this.m.setCallback(null);
            }
            if (drawable != null) {
                this.m = drawable.mutate();
                drawable.setBounds(0, 0, getWidth(), getHeight());
                drawable.setCallback(this);
                drawable.setAlpha(this.o);
            } else {
                this.m = null;
            }
            ViewCompat.d(this);
        }
    }

    public void setContentScrimColor(@ColorInt int color) {
        setContentScrim(new ColorDrawable(color));
    }

    public void setContentScrimResource(@DrawableRes int resId) {
        setContentScrim(android.support.v4.content.a.a(getContext(), resId));
    }

    public void setStatusBarScrim(@Nullable Drawable drawable) {
        if (this.n != drawable) {
            if (this.n != null) {
                this.n.setCallback(null);
            }
            this.n = drawable;
            drawable.setCallback(this);
            drawable.mutate().setAlpha(this.o);
            ViewCompat.d(this);
        }
    }

    public void setStatusBarScrimColor(@ColorInt int color) {
        setStatusBarScrim(new ColorDrawable(color));
    }

    public void setStatusBarScrimResource(@DrawableRes int resId) {
        setStatusBarScrim(android.support.v4.content.a.a(getContext(), resId));
    }

    public void setCollapsedTitleTextAppearance(@StyleRes int resId) {
        this.j.e(resId);
    }

    public void setCollapsedTitleTextColor(@ColorInt int color) {
        this.j.a(color);
    }

    public void setCollapsedTitleGravity(int gravity) {
        this.j.d(gravity);
    }

    public void setExpandedTitleTextAppearance(@StyleRes int resId) {
        this.j.f(resId);
    }

    public void setExpandedTitleColor(@ColorInt int color) {
        this.j.b(color);
    }

    public void setExpandedTitleGravity(int gravity) {
        this.j.c(gravity);
    }

    public void setCollapsedTitleTypeface(@Nullable Typeface typeface) {
        this.j.a(typeface);
    }

    public void setExpandedTitleTypeface(@Nullable Typeface typeface) {
        this.j.b(typeface);
    }

    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof a;
    }

    private a c() {
        return new a(super.generateDefaultLayoutParams());
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new a(getContext(), attrs);
    }

    protected /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }
}
