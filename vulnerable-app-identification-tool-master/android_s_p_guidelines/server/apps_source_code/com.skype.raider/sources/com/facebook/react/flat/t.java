package com.facebook.react.flat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.an;
import com.facebook.react.d.b;
import com.facebook.react.d.c;
import com.facebook.react.d.d;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.q;
import com.facebook.react.uimanager.r;
import com.facebook.react.uimanager.u;
import com.facebook.react.uimanager.v;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.annotation.Nullable;

final class t extends ViewGroup implements c, d, r, u, v {
    private static Paint b;
    private static Paint c;
    private static Paint d;
    private static Paint e;
    private static Rect f;
    private static final ArrayList<t> g = new ArrayList();
    private static final Rect h = new Rect();
    private static final SparseArray<View> u = new SparseArray(0);
    private boolean a;
    @Nullable
    private a i;
    private h[] j = h.b;
    private c[] k = c.a;
    private x[] l = x.a;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private Drawable q;
    private q r = q.AUTO;
    private long s;
    @Nullable
    private b t;
    @Nullable
    private i v;
    @Nullable
    private Rect w;

    static final class a extends WeakReference<t> {
        /* synthetic */ a(t x0, byte b) {
            this(x0);
        }

        private a(t view) {
            super(view);
        }

        public final void a() {
            t view = (t) get();
            if (view != null) {
                view.invalidate();
            }
        }

        public final void a(int reactTag, int imageLoadEvent) {
            t view = (t) get();
            if (view != null) {
                ((UIManagerModule) ((ai) view.getContext()).b(UIManagerModule.class)).getEventDispatcher().a(new com.facebook.react.views.image.a(reactTag, imageLoadEvent));
            }
        }
    }

    t(Context context) {
        super(context);
        setClipChildren(false);
    }

    protected final void detachAllViewsFromParent() {
        super.detachAllViewsFromParent();
    }

    @SuppressLint({"MissingSuperCall"})
    public final void requestLayout() {
        if (!this.o) {
            this.o = true;
            g.add(this);
        }
    }

    public final int a(float touchX, float touchY) {
        an.a(this.r != q.NONE, "TouchTargetHelper should not allow calling this method when pointer events are NONE");
        if (this.r != q.BOX_ONLY) {
            x nodeRegion = c(touchX, touchY);
            if (nodeRegion != null) {
                return nodeRegion.c;
            }
        }
        return getId();
    }

    public final void dispatchDraw(Canvas canvas) {
        this.a = false;
        super.dispatchDraw(canvas);
        if (this.v != null) {
            this.v.a(canvas);
        } else {
            for (h a : this.j) {
                a.a(this, canvas);
            }
        }
        if (this.m != getChildCount()) {
            throw new RuntimeException("Did not draw all children: " + this.m + " / " + getChildCount());
        }
        this.m = 0;
        if (this.a) {
            Paint paint;
            if (b == null) {
                paint = new Paint();
                b = paint;
                paint.setTextAlign(Align.RIGHT);
                b.setTextSize((float) a(9));
                b.setTypeface(Typeface.MONOSPACE);
                b.setAntiAlias(true);
                b.setColor(-65536);
            }
            if (c == null) {
                paint = new Paint();
                c = paint;
                paint.setColor(-1);
                c.setAlpha(200);
                c.setStyle(Style.FILL);
            }
            if (d == null) {
                paint = new Paint();
                d = paint;
                paint.setAlpha(100);
                d.setStyle(Style.STROKE);
            }
            if (e == null) {
                paint = new Paint();
                e = paint;
                paint.setAlpha(200);
                e.setColor(Color.rgb(63, 127, 255));
                e.setStyle(Style.FILL);
            }
            if (f == null) {
                f = new Rect();
            }
            c(canvas);
        }
        if (this.q != null) {
            this.q.draw(canvas);
        }
    }

    private void c(Canvas canvas) {
        if (this.v != null) {
            this.v.b(canvas);
        } else {
            for (h b : this.j) {
                b.b(this, canvas);
            }
        }
        this.m = 0;
    }

    protected final boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return false;
    }

    final void a(Canvas canvas) {
        View child = getChildAt(this.m);
        a(canvas, child instanceof t ? -12303292 : -65536, (float) child.getLeft(), (float) child.getTop(), (float) child.getRight(), (float) child.getBottom());
        this.m++;
    }

    private int a(int dips) {
        return (int) ((((float) dips) * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static void a(Canvas canvas, Paint paint, float x1, float y1, float x2, float y2) {
        if (x1 != x2 && y1 != y2) {
            float tmp;
            if (x1 > x2) {
                tmp = x1;
                x1 = x2;
                x2 = tmp;
            }
            if (y1 > y2) {
                tmp = y1;
                y1 = y2;
                y2 = tmp;
            }
            canvas.drawRect(x1, y1, x2, y2, paint);
        }
    }

    private static int a(float x) {
        return x >= 0.0f ? 1 : -1;
    }

    private static void a(Canvas c, Paint paint, float x1, float y1, float dx, float dy, float lw) {
        a(c, paint, x1, y1, x1 + dx, y1 + (((float) a(dy)) * lw));
        a(c, paint, x1, y1, x1 + (((float) a(dx)) * lw), y1 + dy);
    }

    final void a(Canvas canvas, int color, float left, float top, float right, float bottom) {
        d.setColor((d.getColor() & -16777216) | (16777215 & color));
        d.setAlpha(100);
        canvas.drawRect(left, top, right - 1.0f, bottom - 1.0f, d);
        Paint paint = e;
        int a = a(8);
        int a2 = a(1);
        a(canvas, paint, left, top, (float) a, (float) a, (float) a2);
        a(canvas, paint, left, bottom, (float) a, (float) (-a), (float) a2);
        a(canvas, paint, right, top, (float) (-a), (float) a, (float) a2);
        a(canvas, paint, right, bottom, (float) (-a), (float) (-a), (float) a2);
    }

    protected final void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    @SuppressLint({"MissingSuperCall"})
    protected final boolean verifyDrawable(Drawable who) {
        return true;
    }

    protected final void onAttachedToWindow() {
        int i = 0;
        if (!this.n) {
            this.n = true;
            super.onAttachedToWindow();
            c[] cVarArr = this.k;
            if (cVarArr.length != 0) {
                if (this.i == null) {
                    this.i = new a();
                }
                a aVar = this.i;
                int length = cVarArr.length;
                while (i < length) {
                    cVarArr[i].a(aVar);
                    i++;
                }
            }
            b_();
        }
    }

    protected final void onDetachedFromWindow() {
        if (this.n) {
            this.n = false;
            super.onDetachedFromWindow();
            a(this.k);
            return;
        }
        throw new RuntimeException("Double detach");
    }

    protected final void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (this.q != null) {
            this.q.setBounds(0, 0, w, h);
            invalidate();
        }
        b_();
    }

    public final void dispatchDrawableHotspotChanged(float x, float y) {
        if (this.q != null) {
            this.q.setHotspot(x, y);
            invalidate();
        }
    }

    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.q != null && this.q.isStateful()) {
            this.q.setState(getDrawableState());
        }
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.q != null) {
            this.q.jumpToCurrentState();
        }
    }

    public final void invalidate() {
        invalidate(0, 0, getWidth() + 1, getHeight() + 1);
    }

    public final boolean hasOverlappingRendering() {
        return this.p;
    }

    public final void setOnInterceptTouchEventListener(b listener) {
        this.t = listener;
    }

    public final boolean onInterceptTouchEvent(MotionEvent ev) {
        long downTime = ev.getDownTime();
        if (downTime != this.s) {
            this.s = downTime;
            if (b(ev.getX(), ev.getY())) {
                return true;
            }
        }
        if ((this.t != null && this.t.a(this, ev)) || this.r == q.NONE || this.r == q.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public final boolean onTouchEvent(MotionEvent ev) {
        if (this.r == q.NONE) {
            return false;
        }
        if (this.r == q.BOX_NONE && c(ev.getX(), ev.getY()) == null) {
            return false;
        }
        return true;
    }

    public final q c_() {
        return this.r;
    }

    final void a(q pointerEvents) {
        this.r = pointerEvents;
    }

    final void a(boolean needsOffscreenAlphaCompositing) {
        this.p = needsOffscreenAlphaCompositing;
    }

    final void a(Drawable hotspot) {
        if (this.q != null) {
            this.q.setCallback(null);
            unscheduleDrawable(this.q);
        }
        if (hotspot != null) {
            hotspot.setCallback(this);
            if (hotspot.isStateful()) {
                hotspot.setState(getDrawableState());
            }
        }
        this.q = hotspot;
        invalidate();
    }

    final void b(Canvas canvas) {
        View child = getChildAt(this.m);
        if (child instanceof t) {
            super.drawChild(canvas, child, getDrawingTime());
        } else {
            canvas.save(2);
            child.getHitRect(h);
            canvas.clipRect(h);
            super.drawChild(canvas, child, getDrawingTime());
            canvas.restore();
        }
        this.m++;
    }

    final void a(View view) {
        removeDetachedView(view, false);
    }

    public final void removeAllViewsInLayout() {
        this.j = h.b;
        super.removeAllViewsInLayout();
    }

    final void a(View view, int index) {
        addViewInLayout(view, index, a(view.getLayoutParams()), true);
    }

    final void b(View view, int index) {
        attachViewToParent(view, index, a(view.getLayoutParams()));
    }

    @Nullable
    private x c(float touchX, float touchY) {
        if (this.v != null) {
            return this.v.a(touchX, touchY);
        }
        for (int i = this.l.length - 1; i >= 0; i--) {
            x nodeRegion = this.l[i];
            if (nodeRegion.d && nodeRegion.a(touchX, touchY)) {
                return nodeRegion;
            }
        }
        return null;
    }

    private static void a(c[] listeners) {
        for (c a : listeners) {
            a.a();
        }
    }

    private LayoutParams a(LayoutParams lp) {
        return checkLayoutParams(lp) ? lp : generateDefaultLayoutParams();
    }

    public final void b_() {
        if (this.v != null && this.v.b()) {
            invalidate();
        }
    }

    public final void a(Rect outClippingRect) {
        if (this.v == null) {
            throw new RuntimeException("Trying to get the clipping rect for a non-clipping FlatViewGroup");
        }
        this.v.a(outClippingRect);
    }

    public final void b(boolean removeClippedSubviews) {
        boolean currentlyClipping = e_();
        if (removeClippedSubviews != currentlyClipping) {
            if (currentlyClipping) {
                throw new RuntimeException("Trying to transition FlatViewGroup from clipping to non-clipping state");
            }
            this.v = new aj(this, this.j);
            this.j = h.b;
        }
    }

    public final boolean e_() {
        return this.v != null;
    }

    @Nullable
    public final Rect d() {
        return this.w;
    }

    final void b(@Nullable Rect rect) {
        this.w = rect;
    }

    public final boolean b(float touchX, float touchY) {
        x nodeRegion;
        if (this.v != null) {
            nodeRegion = this.v.b(touchX, touchY);
        } else {
            for (int length = this.l.length - 1; length >= 0; length--) {
                nodeRegion = this.l[length];
                if (nodeRegion.a(touchX, touchY)) {
                    break;
                }
            }
            nodeRegion = null;
        }
        if (nodeRegion == null || !nodeRegion.d) {
            return false;
        }
        return true;
    }
}
