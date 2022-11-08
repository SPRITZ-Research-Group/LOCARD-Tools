package com.facebook.react.views.scroll;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import com.facebook.infer.annotation.a;
import com.facebook.react.uimanager.j;
import com.facebook.react.uimanager.r;
import com.facebook.react.uimanager.s;
import com.facebook.react.views.view.b;
import com.facebook.react.views.view.e;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class ReactHorizontalScrollView extends HorizontalScrollView implements r {
    private final b a;
    private boolean b;
    @Nullable
    private Rect c;
    private boolean d;
    private boolean e;
    @Nullable
    private Runnable f;
    private boolean g;
    private boolean h;
    private boolean i;
    @Nullable
    private a j;
    @Nullable
    private String k;
    @Nullable
    private Drawable l;
    private int m;
    @Nullable
    private e n;
    private b o;

    public ReactHorizontalScrollView(Context context) {
        this(context, null);
    }

    public ReactHorizontalScrollView(Context context, @Nullable a fpsListener) {
        super(context);
        this.a = new b();
        this.e = false;
        this.h = true;
        this.j = null;
        this.m = 0;
        this.j = fpsListener;
        setDescendantFocusability(131072);
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        super.addFocusables(views, direction, focusableMode);
        if (isFocusable() && getDescendantFocusability() == 262144) {
            views.add(this);
        }
    }

    public void setScrollPerfTag(@Nullable String scrollPerfTag) {
        this.k = scrollPerfTag;
    }

    public void setRemoveClippedSubviews(boolean removeClippedSubviews) {
        if (removeClippedSubviews && this.c == null) {
            this.c = new Rect();
        }
        this.g = removeClippedSubviews;
        b_();
    }

    public final boolean e_() {
        return this.g;
    }

    public void setSendMomentumEvents(boolean sendMomentumEvents) {
        this.i = sendMomentumEvents;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        this.h = scrollEnabled;
    }

    public void setPagingEnabled(boolean pagingEnabled) {
        this.e = pagingEnabled;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        j.a(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        scrollTo(getScrollX(), getScrollY());
    }

    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (this.a.a(x, y)) {
            if (this.g) {
                b_();
            }
            this.b = true;
            d.a((ViewGroup) this);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.h || !super.onInterceptTouchEvent(ev)) {
            return false;
        }
        com.facebook.react.uimanager.events.e.a(this, ev);
        d.b(this);
        this.d = true;
        if (a()) {
            a.a(this.j);
            a.a(this.k);
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.h) {
            return false;
        }
        if ((ev.getAction() & 255) == 1 && this.d) {
            d.c(this);
            this.d = false;
            d();
        }
        return super.onTouchEvent(ev);
    }

    public void fling(int velocityX) {
        if (this.e) {
            a(velocityX);
        } else {
            super.fling(velocityX);
        }
        d();
    }

    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
        if (this.o == null) {
            this.o = new b();
        }
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
        this.o.b(didTakeFocus);
        return didTakeFocus;
    }

    public void clearFocus() {
        super.clearFocus();
        this.o.b();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.g) {
            b_();
        }
        this.o.a((View) this);
    }

    public void addView(View child, int index, LayoutParams params) {
        super.addView(child, index, params);
        this.o.a((ViewGroup) this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.g) {
            b_();
        }
    }

    public final void b_() {
        if (this.g) {
            a.a(this.c);
            s.a(this, this.c);
            View contentView = getChildAt(0);
            if (contentView instanceof r) {
                ((r) contentView).b_();
            }
        }
    }

    public final void a(Rect outClippingRect) {
        outClippingRect.set((Rect) a.a(this.c));
    }

    public void setEndFillColor(int color) {
        if (color != this.m) {
            this.m = color;
            this.l = new ColorDrawable(this.m);
        }
    }

    private boolean a() {
        return (this.j == null || this.k == null || this.k.isEmpty()) ? false : true;
    }

    public void draw(Canvas canvas) {
        if (this.m != 0) {
            View content = getChildAt(0);
            if (!(this.l == null || content == null || content.getRight() >= getWidth())) {
                this.l.setBounds(content.getRight(), 0, getWidth(), getHeight());
                this.l.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    @TargetApi(16)
    private void d() {
        if ((this.i || this.e || a()) && this.f == null) {
            if (this.i) {
                d.d(this);
            }
            this.b = false;
            this.f = new Runnable(this) {
                final /* synthetic */ ReactHorizontalScrollView a;
                private boolean b = false;

                {
                    this.a = this$0;
                }

                public final void run() {
                    if (this.a.b) {
                        this.a.b = false;
                    } else {
                        boolean doneWithAllScrolling = true;
                        if (this.a.e && !this.b) {
                            this.b = true;
                            this.a.a(0);
                            doneWithAllScrolling = false;
                        }
                        if (doneWithAllScrolling) {
                            if (this.a.i) {
                                d.e(this.a);
                            }
                            this.a.f = null;
                            ReactHorizontalScrollView.g(this.a);
                            return;
                        }
                    }
                    this.a.postOnAnimationDelayed(this, 20);
                }
            };
            postOnAnimationDelayed(this.f, 20);
        }
    }

    private void a(int velocity) {
        int width = getWidth();
        int currentX = getScrollX();
        int page = currentX / width;
        if (currentX + velocity > (page * width) + (width / 2)) {
            page++;
        }
        smoothScrollTo(page * width, getScrollY());
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.n != null) {
            e().a(color);
        }
    }

    public void setBorderWidth(int position, float width) {
        e().a(position, width);
    }

    public void setBorderColor(int position, float color, float alpha) {
        e().a(position, color, alpha);
    }

    public void setBorderRadius(float borderRadius) {
        e().a(borderRadius);
    }

    public void setBorderRadius(float borderRadius, int position) {
        e().a(borderRadius, position);
    }

    public void setBorderStyle(@Nullable String style) {
        e().a(style);
    }

    private e e() {
        if (this.n == null) {
            this.n = new e();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.n);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.n, backgroundDrawable}));
            }
        }
        return this.n;
    }

    static /* synthetic */ void g(ReactHorizontalScrollView x0) {
        if (x0.a()) {
            a.a(x0.j);
            a.a(x0.k);
        }
    }
}
