package com.facebook.react.views.scroll;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.ai;
import com.facebook.react.uimanager.j;
import com.facebook.react.uimanager.r;
import com.facebook.react.uimanager.s;
import com.facebook.react.views.view.b;
import com.facebook.react.views.view.e;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class ReactScrollView extends ScrollView implements OnLayoutChangeListener, OnHierarchyChangeListener, r {
    private static Field a;
    private static boolean b = false;
    private final b c = new b();
    private final OverScroller d;
    @Nullable
    private Rect e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j = true;
    private boolean k;
    @Nullable
    private a l = null;
    @Nullable
    private String m;
    @Nullable
    private Drawable n;
    private int o = 0;
    private View p;
    @Nullable
    private e q;
    private b r;

    public ReactScrollView(ai context, @Nullable a fpsListener) {
        super(context);
        this.l = fpsListener;
        if (!b) {
            b = true;
            try {
                Field declaredField = ScrollView.class.getDeclaredField("mScroller");
                a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
        }
        if (a != null) {
            try {
                Object scroller = a.get(this);
                if (scroller instanceof OverScroller) {
                    this.d = (OverScroller) scroller;
                } else {
                    this.d = null;
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Failed to get mScroller from ScrollView!", e2);
            }
        }
        this.d = null;
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
        setDescendantFocusability(131072);
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        super.addFocusables(views, direction, focusableMode);
        if (isFocusable() && getDescendantFocusability() == 262144) {
            views.add(this);
        }
    }

    public void setSendMomentumEvents(boolean sendMomentumEvents) {
        this.k = sendMomentumEvents;
    }

    public void setScrollPerfTag(String scrollPerfTag) {
        this.m = scrollPerfTag;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.j = scrollEnabled;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        j.a(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        scrollTo(getScrollX(), getScrollY());
    }

    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        if (this.r == null) {
            this.r = new b();
        }
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
        this.r.b(didTakeFocus);
        return didTakeFocus;
    }

    public void clearFocus() {
        super.clearFocus();
        this.r.b();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.i) {
            b_();
        }
        this.r.a((View) this);
    }

    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        this.r.a((ViewGroup) this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.i) {
            b_();
        }
    }

    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (this.c.a(x, y)) {
            if (this.i) {
                b_();
            }
            if (this.h) {
                this.f = false;
            }
            d.a((ViewGroup) this);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.j || !super.onInterceptTouchEvent(ev)) {
            return false;
        }
        com.facebook.react.uimanager.events.e.a(this, ev);
        d.b(this);
        this.g = true;
        a();
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.j) {
            return false;
        }
        if ((ev.getAction() & 255) == 1 && this.g) {
            d.c(this);
            this.g = false;
            d();
        }
        return super.onTouchEvent(ev);
    }

    public void setRemoveClippedSubviews(boolean removeClippedSubviews) {
        if (removeClippedSubviews && this.e == null) {
            this.e = new Rect();
        }
        this.i = removeClippedSubviews;
        b_();
    }

    public final boolean e_() {
        return this.i;
    }

    public final void b_() {
        if (this.i) {
            a.a(this.e);
            s.a(this, this.e);
            View contentView = getChildAt(0);
            if (contentView instanceof r) {
                ((r) contentView).b_();
            }
        }
    }

    public final void a(Rect outClippingRect) {
        outClippingRect.set((Rect) a.a(this.e));
    }

    public void fling(int velocityY) {
        if (this.d != null) {
            int i = velocityY;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            this.d.fling(getScrollX(), getScrollY(), 0, i, i2, i3, i4, Integer.MAX_VALUE, i5, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            postInvalidateOnAnimation();
        } else {
            super.fling(velocityY);
        }
        if (this.k || e()) {
            this.h = true;
            a();
            d.d(this);
            postOnAnimationDelayed(new Runnable(this) {
                final /* synthetic */ ReactScrollView a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    if (this.a.f) {
                        this.a.h = false;
                        this.a.d();
                        d.e(this.a);
                        return;
                    }
                    this.a.f = true;
                    this.a.postOnAnimationDelayed(this, 20);
                }
            }, 20);
        }
    }

    private void a() {
        if (e()) {
            a.a(this.l);
            a.a(this.m);
        }
    }

    private void d() {
        if (e()) {
            a.a(this.l);
            a.a(this.m);
        }
    }

    private boolean e() {
        return (this.l == null || this.m == null || this.m.isEmpty()) ? false : true;
    }

    private int f() {
        return Math.max(0, this.p.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    public void draw(Canvas canvas) {
        if (this.o != 0) {
            View content = getChildAt(0);
            if (!(this.n == null || content == null || content.getBottom() >= getHeight())) {
                this.n.setBounds(0, content.getBottom(), getWidth(), getHeight());
                this.n.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    public void setEndFillColor(int color) {
        if (color != this.o) {
            this.o = color;
            this.n = new ColorDrawable(this.o);
        }
    }

    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if (!(this.d == null || this.d.isFinished() || this.d.getCurrY() == this.d.getFinalY())) {
            int scrollRange = f();
            if (scrollY >= scrollRange) {
                this.d.abortAnimation();
                scrollY = scrollRange;
            }
        }
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    public void onChildViewAdded(View parent, View child) {
        this.p = child;
        this.p.addOnLayoutChangeListener(this);
    }

    public void onChildViewRemoved(View parent, View child) {
        this.p.removeOnLayoutChangeListener(this);
        this.p = null;
    }

    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (this.p != null) {
            int currentScrollY = getScrollY();
            int maxScrollY = f();
            if (currentScrollY > maxScrollY) {
                scrollTo(getScrollX(), maxScrollY);
            }
        }
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.q != null) {
            g().a(color);
        }
    }

    public void setBorderWidth(int position, float width) {
        g().a(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        g().a(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        g().a(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        g().a(borderRadius, position);
    }

    public void setBorderStyle(@Nullable String style) {
        g().a(style);
    }

    private e g() {
        if (this.q == null) {
            this.q = new e();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.q);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.q, backgroundDrawable}));
            }
        }
        return this.q;
    }
}
