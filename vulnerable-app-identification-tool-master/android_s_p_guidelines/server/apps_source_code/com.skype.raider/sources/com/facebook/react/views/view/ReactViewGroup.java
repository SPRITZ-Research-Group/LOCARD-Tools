package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import com.facebook.react.d.b;
import com.facebook.react.d.c;
import com.facebook.react.d.d;
import com.facebook.react.uimanager.an;
import com.facebook.react.uimanager.j;
import com.facebook.react.uimanager.q;
import com.facebook.react.uimanager.r;
import com.facebook.react.uimanager.s;
import com.facebook.react.uimanager.v;
import com.facebook.react.uimanager.z;
import com.facebook.yoga.YogaOverflow;
import java.util.Locale;
import javax.annotation.Nullable;

public class ReactViewGroup extends ViewGroup implements c, d, r, v, z {
    private static final LayoutParams a = new LayoutParams(0, 0);
    private static final Rect b = new Rect();
    private boolean c = false;
    @Nullable
    private View[] d = null;
    private int e;
    @Nullable
    private Rect f;
    @Nullable
    private Rect g;
    private q h = q.AUTO;
    @Nullable
    private a i;
    @Nullable
    private e j;
    @Nullable
    private b k;
    private boolean l = false;
    private final an m = new an(this);
    private b n = new b();
    private boolean o = false;

    private static final class a implements OnLayoutChangeListener {
        private final ReactViewGroup a;

        /* synthetic */ a(ReactViewGroup x0, byte b) {
            this(x0);
        }

        private a(ReactViewGroup parent) {
            this.a = parent;
        }

        public final void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            if (this.a.e_()) {
                ReactViewGroup.a(this.a, v);
            }
        }
    }

    static /* synthetic */ void a(ReactViewGroup x0, View x1) {
        int i = 0;
        if (x0.c && x0.getParent() != null) {
            com.facebook.infer.annotation.a.a(x0.f);
            com.facebook.infer.annotation.a.a(x0.d);
            b.set(x1.getLeft(), x1.getTop(), x1.getRight(), x1.getBottom());
            if (x0.f.intersects(b.left, b.top, b.right, b.bottom) != (x1.getParent() != null)) {
                int i2 = 0;
                while (i < x0.e) {
                    if (x0.d[i] == x1) {
                        x0.a(x0.f, i, i2);
                        return;
                    }
                    if (x0.d[i].getParent() == null) {
                        i2++;
                    }
                    i++;
                }
            }
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        setClipChildren(true);
        a(true);
    }

    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        boolean didTakeFocus = super.requestFocus(direction, previouslyFocusedRect);
        this.n.b(didTakeFocus);
        return didTakeFocus;
    }

    public void clearFocus() {
        super.clearFocus();
        this.n.b();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (this.n.a((View) this, keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public void setRestrictFocusWithin(boolean restrictFocusWithin) {
        this.o = restrictFocusWithin;
    }

    public final boolean h() {
        return this.o;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        j.a(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    public void requestLayout() {
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.j != null) {
            e().a(color);
        }
    }

    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    public void setTranslucentBackgroundDrawable(@Nullable Drawable background) {
        super.setBackground(null);
        if (this.j != null && background != null) {
            super.setBackground(new LayerDrawable(new Drawable[]{this.j, background}));
        } else if (background != null) {
            super.setBackground(background);
        }
    }

    public void setOverflow(String value) {
        String upperValue = value == null ? null : value.toUpperCase(Locale.US);
        if (value == null || YogaOverflow.HIDDEN.toString().equals(upperValue) || YogaOverflow.SCROLL.toString().equals(upperValue)) {
            setClipChildren(true);
            a(true);
        } else if (YogaOverflow.VISIBLE.toString().equals(upperValue)) {
            setClipChildren(false);
            a(false);
        }
    }

    public void setOnInterceptTouchEventListener(b listener) {
        this.k = listener;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if ((this.k != null && this.k.a(this, ev)) || this.h == q.NONE || this.h == q.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.h == q.NONE || this.h == q.BOX_NONE) {
            return false;
        }
        return true;
    }

    public boolean hasOverlappingRendering() {
        return this.l;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean needsOffscreenAlphaCompositing) {
        this.l = needsOffscreenAlphaCompositing;
    }

    public void setBorderWidth(int position, float width) {
        e().a(position, width);
    }

    public void setBorderColor(int position, float rgb, float alpha) {
        e().a(position, rgb, alpha);
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

    public void setRemoveClippedSubviews(boolean removeClippedSubviews) {
        if (removeClippedSubviews != this.c) {
            this.c = removeClippedSubviews;
            int i;
            if (removeClippedSubviews) {
                this.f = new Rect();
                s.a(this, this.f);
                this.e = getChildCount();
                this.d = new View[Math.max(12, this.e)];
                this.i = new a();
                for (i = 0; i < this.e; i++) {
                    View child = getChildAt(i);
                    this.d[i] = child;
                    child.addOnLayoutChangeListener(this.i);
                }
                b_();
                return;
            }
            com.facebook.infer.annotation.a.a(this.f);
            com.facebook.infer.annotation.a.a(this.d);
            com.facebook.infer.annotation.a.a(this.i);
            for (i = 0; i < this.e; i++) {
                this.d[i].removeOnLayoutChangeListener(this.i);
            }
            getDrawingRect(this.f);
            b(this.f);
            this.d = null;
            this.f = null;
            this.e = 0;
            this.i = null;
        }
    }

    public final boolean e_() {
        return this.c;
    }

    public final void a(Rect outClippingRect) {
        outClippingRect.set(this.f);
    }

    public final void b_() {
        if (this.c) {
            com.facebook.infer.annotation.a.a(this.f);
            com.facebook.infer.annotation.a.a(this.d);
            s.a(this, this.f);
            b(this.f);
        }
    }

    private void b(Rect clippingRect) {
        com.facebook.infer.annotation.a.a(this.d);
        int clippedSoFar = 0;
        for (int i = 0; i < this.e; i++) {
            a(clippingRect, i, clippedSoFar);
            if (this.d[i].getParent() == null) {
                clippedSoFar++;
            }
        }
    }

    private void a(Rect clippingRect, int idx, int clippedSoFar) {
        View child = ((View[]) com.facebook.infer.annotation.a.a(this.d))[idx];
        b.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        boolean intersects = clippingRect.intersects(b.left, b.top, b.right, b.bottom);
        boolean needUpdateClippingRecursive = false;
        Animation animation = child.getAnimation();
        boolean isAnimating = (animation == null || animation.hasEnded()) ? false : true;
        if (!intersects && child.getParent() != null && !isAnimating) {
            super.removeViewsInLayout(idx - clippedSoFar, 1);
            needUpdateClippingRecursive = true;
        } else if (intersects && child.getParent() == null) {
            super.addViewInLayout(child, idx - clippedSoFar, a, true);
            invalidate();
            needUpdateClippingRecursive = true;
        } else if (intersects) {
            needUpdateClippingRecursive = true;
        }
        if (needUpdateClippingRecursive && (child instanceof r)) {
            r clippingChild = (r) child;
            if (clippingChild.e_()) {
                clippingChild.b_();
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.c) {
            b_();
        }
        this.n.a((View) this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.c) {
            b_();
        }
    }

    public void addView(View child, int index, LayoutParams params) {
        this.m.a(child);
        setChildrenDrawingOrderEnabled(this.m.a());
        super.addView(child, index, params);
        this.n.a((ViewGroup) this);
    }

    public void removeView(View view) {
        this.m.b(view);
        setChildrenDrawingOrderEnabled(this.m.a());
        super.removeView(view);
    }

    public void removeViewAt(int index) {
        this.m.b(getChildAt(index));
        setChildrenDrawingOrderEnabled(this.m.a());
        super.removeViewAt(index);
    }

    protected int getChildDrawingOrder(int childCount, int index) {
        return this.m.a(childCount, index);
    }

    public final int a(int index) {
        if (this.m.a()) {
            return this.m.a(getChildCount(), index);
        }
        return index;
    }

    public final q c_() {
        return this.h;
    }

    protected void dispatchSetPressed(boolean pressed) {
    }

    final void a(q pointerEvents) {
        this.h = pointerEvents;
    }

    final int i() {
        return this.e;
    }

    final View b(int index) {
        return ((View[]) com.facebook.infer.annotation.a.a(this.d))[index];
    }

    final void a(View child, int index) {
        int i = 0;
        com.facebook.infer.annotation.a.a(this.c);
        com.facebook.infer.annotation.a.a(this.f);
        com.facebook.infer.annotation.a.a(this.d);
        View[] viewArr = (View[]) com.facebook.infer.annotation.a.a(this.d);
        int i2 = this.e;
        int length = viewArr.length;
        if (index == i2) {
            if (length == i2) {
                this.d = new View[(length + 12)];
                System.arraycopy(viewArr, 0, this.d, 0, length);
                viewArr = this.d;
            }
            i2 = this.e;
            this.e = i2 + 1;
            viewArr[i2] = child;
        } else if (index < i2) {
            if (length == i2) {
                this.d = new View[(length + 12)];
                System.arraycopy(viewArr, 0, this.d, 0, index);
                System.arraycopy(viewArr, index, this.d, index + 1, i2 - index);
                viewArr = this.d;
            } else {
                System.arraycopy(viewArr, index, viewArr, index + 1, i2 - index);
            }
            viewArr[index] = child;
            this.e++;
        } else {
            throw new IndexOutOfBoundsException("index=" + index + " count=" + i2);
        }
        int i3 = 0;
        while (i < index) {
            if (this.d[i].getParent() == null) {
                i3++;
            }
            i++;
        }
        a(this.f, index, i3);
        child.addOnLayoutChangeListener(this.i);
    }

    final void a(View view) {
        com.facebook.infer.annotation.a.a(this.c);
        com.facebook.infer.annotation.a.a(this.f);
        com.facebook.infer.annotation.a.a(this.d);
        view.removeOnLayoutChangeListener(this.i);
        int i = this.e;
        View[] viewArr = (View[]) com.facebook.infer.annotation.a.a(this.d);
        int index = 0;
        while (index < i) {
            if (viewArr[index] == view) {
                break;
            }
            index++;
        }
        index = -1;
        if (this.d[index].getParent() != null) {
            int clippedSoFar = 0;
            for (int i2 = 0; i2 < index; i2++) {
                if (this.d[i2].getParent() == null) {
                    clippedSoFar++;
                }
            }
            super.removeViewsInLayout(index - clippedSoFar, 1);
        }
        viewArr = (View[]) com.facebook.infer.annotation.a.a(this.d);
        i = this.e;
        if (index == i - 1) {
            i = this.e - 1;
            this.e = i;
            viewArr[i] = null;
        } else if (index < 0 || index >= i) {
            throw new IndexOutOfBoundsException();
        } else {
            System.arraycopy(viewArr, index + 1, viewArr, index, (i - index) - 1);
            i = this.e - 1;
            this.e = i;
            viewArr[i] = null;
        }
    }

    final void j() {
        com.facebook.infer.annotation.a.a(this.c);
        com.facebook.infer.annotation.a.a(this.d);
        for (int i = 0; i < this.e; i++) {
            this.d[i].removeOnLayoutChangeListener(this.i);
        }
        removeAllViewsInLayout();
        this.e = 0;
    }

    private e e() {
        if (this.j == null) {
            this.j = new e();
            Drawable backgroundDrawable = getBackground();
            super.setBackground(null);
            if (backgroundDrawable == null) {
                super.setBackground(this.j);
            } else {
                super.setBackground(new LayerDrawable(new Drawable[]{this.j, backgroundDrawable}));
            }
        }
        return this.j;
    }

    @Nullable
    public final Rect d() {
        return this.g;
    }

    public void setHitSlopRect(@Nullable Rect rect) {
        this.g = rect;
    }

    private void a(boolean clip) {
        if (VERSION.SDK_INT >= 21) {
            setClipToOutline(clip);
        }
    }
}
