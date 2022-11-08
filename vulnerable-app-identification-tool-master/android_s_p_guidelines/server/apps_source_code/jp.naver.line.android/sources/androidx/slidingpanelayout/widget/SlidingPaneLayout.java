package androidx.slidingpanelayout.widget;

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
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import androidx.core.content.a;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import defpackage.hs;
import defpackage.jh;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.http.HttpStatus;

public class SlidingPaneLayout extends ViewGroup {
    View a;
    float b;
    int c;
    boolean d;
    final jh e;
    boolean f;
    final ArrayList<b> g;
    private int h;
    private int i;
    private Drawable j;
    private Drawable k;
    private final int l;
    private boolean m;
    private float n;
    private int o;
    private float p;
    private float q;
    private d r;
    private boolean s;
    private final Rect t;
    private Method u;
    private Field v;
    private boolean w;

    public class LayoutParams extends MarginLayoutParams {
        private static final int[] e = new int[]{16843137};
        public float a = BitmapDescriptorFactory.HUE_RED;
        boolean b;
        boolean c;
        Paint d;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e);
            this.a = obtainStyledAttributes.getFloat(0, BitmapDescriptorFactory.HUE_RED);
            obtainStyledAttributes.recycle();
        }
    }

    class SavedState extends AbsSavedState {
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
        boolean a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel) {
            super(parcel, null);
            this.a = parcel.readInt() != 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    public SlidingPaneLayout(Context context) {
        this(context, null);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingPaneLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = -858993460;
        this.s = true;
        this.t = new Rect();
        this.g = new ArrayList();
        float f = context.getResources().getDisplayMetrics().density;
        this.l = (int) ((32.0f * f) + 0.5f);
        setWillNotDraw(false);
        hs.a((View) this, new a(this));
        hs.a((View) this, 1);
        this.e = jh.a((ViewGroup) this, 0.5f, new c(this));
        this.e.a(f * 400.0f);
    }

    public void setParallaxDistance(int i) {
        this.o = i;
        requestLayout();
    }

    public void setSliderFadeColor(int i) {
        this.h = i;
    }

    public void setCoveredFadeColor(int i) {
        this.i = i;
    }

    public void setPanelSlideListener(d dVar) {
        this.r = dVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void a(View view) {
        int left;
        int right;
        int top;
        int bottom;
        int childCount;
        int i;
        View childAt;
        int max;
        int max2;
        boolean z;
        int i2;
        SlidingPaneLayout slidingPaneLayout;
        View view2 = view;
        boolean b = b();
        int width = b ? getWidth() - getPaddingRight() : getPaddingLeft();
        int paddingLeft = b ? getPaddingLeft() : getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        if (view2 != null) {
            Object obj = 1;
            if (!view.isOpaque()) {
                if (VERSION.SDK_INT < 18) {
                    Drawable background = view.getBackground();
                    if (background != null) {
                    }
                }
                obj = null;
            }
            if (obj != null) {
                left = view.getLeft();
                right = view.getRight();
                top = view.getTop();
                bottom = view.getBottom();
                childCount = getChildCount();
                i = 0;
                while (i < childCount) {
                    childAt = getChildAt(i);
                    if (childAt != view2) {
                        if (childAt.getVisibility() == 8) {
                            max = Math.max(b ? paddingLeft : width, childAt.getLeft());
                            max2 = Math.max(paddingTop, childAt.getTop());
                            if (b) {
                                z = b;
                                i2 = paddingLeft;
                            } else {
                                z = b;
                                i2 = width;
                            }
                            max = (max >= left || max2 < top || Math.min(i2, childAt.getRight()) > right || Math.min(height, childAt.getBottom()) > bottom) ? 0 : 4;
                            childAt.setVisibility(max);
                        } else {
                            z = b;
                        }
                        i++;
                        b = z;
                        view2 = view;
                    } else {
                        return;
                    }
                }
                slidingPaneLayout = this;
            }
        }
        left = 0;
        right = 0;
        top = 0;
        bottom = 0;
        childCount = getChildCount();
        i = 0;
        while (i < childCount) {
            childAt = getChildAt(i);
            if (childAt != view2) {
                if (childAt.getVisibility() == 8) {
                    z = b;
                } else {
                    if (b) {
                    }
                    max = Math.max(b ? paddingLeft : width, childAt.getLeft());
                    max2 = Math.max(paddingTop, childAt.getTop());
                    if (b) {
                        z = b;
                        i2 = paddingLeft;
                    } else {
                        z = b;
                        i2 = width;
                    }
                    if (max >= left) {
                    }
                    childAt.setVisibility(max);
                }
                i++;
                b = z;
                view2 = view;
            } else {
                return;
            }
        }
        slidingPaneLayout = this;
    }

    final void a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 4) {
                childAt.setVisibility(0);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.s = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.s = true;
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            ((b) this.g.get(i)).run();
        }
        this.g.clear();
    }

    protected void onMeasure(int i, int i2) {
        int paddingTop;
        int i3;
        int i4;
        SlidingPaneLayout slidingPaneLayout = this;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            } else if (mode != Integer.MIN_VALUE && mode == 0) {
                size = HttpStatus.SC_MULTIPLE_CHOICES;
            }
        } else if (mode2 == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            } else if (mode2 == 0) {
                mode2 = Integer.MIN_VALUE;
                size2 = HttpStatus.SC_MULTIPLE_CHOICES;
            }
        }
        boolean z = false;
        if (mode2 == Integer.MIN_VALUE) {
            paddingTop = (size2 - getPaddingTop()) - getPaddingBottom();
            size2 = 0;
        } else if (mode2 != 1073741824) {
            size2 = 0;
            paddingTop = 0;
        } else {
            size2 = (size2 - getPaddingTop()) - getPaddingBottom();
            paddingTop = size2;
        }
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        slidingPaneLayout.a = null;
        int i5 = size2;
        int i6 = paddingLeft;
        size2 = 0;
        boolean z2 = false;
        float f = BitmapDescriptorFactory.HUE_RED;
        while (true) {
            i3 = 8;
            if (size2 >= childCount) {
                break;
            }
            View childAt = getChildAt(size2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (childAt.getVisibility() == 8) {
                layoutParams.c = z;
            } else {
                if (layoutParams.a > BitmapDescriptorFactory.HUE_RED) {
                    f += layoutParams.a;
                    if (layoutParams.width == 0) {
                    }
                }
                i4 = layoutParams.leftMargin + layoutParams.rightMargin;
                if (layoutParams.width == -2) {
                    mode = MeasureSpec.makeMeasureSpec(paddingLeft - i4, Integer.MIN_VALUE);
                } else if (layoutParams.width == -1) {
                    mode = MeasureSpec.makeMeasureSpec(paddingLeft - i4, 1073741824);
                } else {
                    mode = MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                }
                if (layoutParams.height == -2) {
                    i3 = MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                } else if (layoutParams.height == -1) {
                    i3 = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                } else {
                    i3 = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                }
                childAt.measure(mode, i3);
                mode = childAt.getMeasuredWidth();
                i4 = childAt.getMeasuredHeight();
                if (mode2 == Integer.MIN_VALUE && i4 > i5) {
                    i5 = Math.min(i4, paddingTop);
                }
                i6 -= mode;
                z = i6 < 0;
                layoutParams.b = z;
                z |= z2;
                if (layoutParams.b) {
                    slidingPaneLayout.a = childAt;
                }
                z2 = z;
            }
            size2++;
            z = false;
        }
        if (z2 || f > BitmapDescriptorFactory.HUE_RED) {
            mode = paddingLeft - slidingPaneLayout.l;
            mode2 = 0;
            while (mode2 < childCount) {
                int i7;
                View childAt2 = getChildAt(mode2);
                if (childAt2.getVisibility() != i3) {
                    LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                    if (childAt2.getVisibility() != i3) {
                        Object obj = (layoutParams2.width != 0 || layoutParams2.a <= BitmapDescriptorFactory.HUE_RED) ? null : 1;
                        if (obj != null) {
                            i4 = 0;
                        } else {
                            i4 = childAt2.getMeasuredWidth();
                        }
                        int i8;
                        if (!z2 || childAt2 == slidingPaneLayout.a) {
                            if (layoutParams2.a > BitmapDescriptorFactory.HUE_RED) {
                                int makeMeasureSpec;
                                if (layoutParams2.width != 0) {
                                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                                } else if (layoutParams2.height == -2) {
                                    i3 = MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                                    if (z2) {
                                        i7 = mode;
                                        childAt2.measure(MeasureSpec.makeMeasureSpec(i4 + ((int) ((layoutParams2.a * ((float) Math.max(0, i6))) / f)), 1073741824), i3);
                                        mode2++;
                                        mode = i7;
                                        i3 = 8;
                                    } else {
                                        i8 = paddingLeft - (layoutParams2.leftMargin + layoutParams2.rightMargin);
                                        i7 = mode;
                                        mode = MeasureSpec.makeMeasureSpec(i8, 1073741824);
                                        if (i4 != i8) {
                                            childAt2.measure(mode, i3);
                                        }
                                        mode2++;
                                        mode = i7;
                                        i3 = 8;
                                    }
                                } else if (layoutParams2.height == -1) {
                                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                                } else {
                                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824);
                                }
                                i3 = makeMeasureSpec;
                                if (z2) {
                                    i7 = mode;
                                    childAt2.measure(MeasureSpec.makeMeasureSpec(i4 + ((int) ((layoutParams2.a * ((float) Math.max(0, i6))) / f)), 1073741824), i3);
                                    mode2++;
                                    mode = i7;
                                    i3 = 8;
                                } else {
                                    i8 = paddingLeft - (layoutParams2.leftMargin + layoutParams2.rightMargin);
                                    i7 = mode;
                                    mode = MeasureSpec.makeMeasureSpec(i8, 1073741824);
                                    if (i4 != i8) {
                                        childAt2.measure(mode, i3);
                                    }
                                    mode2++;
                                    mode = i7;
                                    i3 = 8;
                                }
                            }
                        } else if (layoutParams2.width < 0 && (i4 > mode || layoutParams2.a > BitmapDescriptorFactory.HUE_RED)) {
                            int i9;
                            if (obj == null) {
                                i9 = 1073741824;
                                i8 = MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), 1073741824);
                            } else if (layoutParams2.height == -2) {
                                i8 = MeasureSpec.makeMeasureSpec(paddingTop, Integer.MIN_VALUE);
                                i9 = 1073741824;
                            } else if (layoutParams2.height == -1) {
                                i9 = 1073741824;
                                i8 = MeasureSpec.makeMeasureSpec(paddingTop, 1073741824);
                            } else {
                                i9 = 1073741824;
                                i8 = MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824);
                            }
                            childAt2.measure(MeasureSpec.makeMeasureSpec(mode, i9), i8);
                        }
                    }
                }
                i7 = mode;
                mode2++;
                mode = i7;
                i3 = 8;
            }
        }
        setMeasuredDimension(size, (i5 + getPaddingTop()) + getPaddingBottom());
        slidingPaneLayout.m = z2;
        if (slidingPaneLayout.e.a() != 0 && !z2) {
            slidingPaneLayout.e.f();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        SlidingPaneLayout slidingPaneLayout = this;
        boolean b = b();
        if (b) {
            slidingPaneLayout.e.a(2);
        } else {
            slidingPaneLayout.e.a(1);
        }
        int i5 = i3 - i;
        int paddingRight = b ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = b ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        if (slidingPaneLayout.s) {
            float f = (slidingPaneLayout.m && slidingPaneLayout.f) ? 1.0f : BitmapDescriptorFactory.HUE_RED;
            slidingPaneLayout.b = f;
        }
        int i6 = paddingRight;
        int i7 = i6;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt = getChildAt(paddingRight);
            if (childAt.getVisibility() != 8) {
                int min;
                int i8;
                int i9;
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                if (layoutParams.b) {
                    int i10 = i5 - paddingLeft;
                    min = (Math.min(i6, i10 - slidingPaneLayout.l) - i7) - (layoutParams.leftMargin + layoutParams.rightMargin);
                    slidingPaneLayout.c = min;
                    i8 = b ? layoutParams.rightMargin : layoutParams.leftMargin;
                    layoutParams.c = ((i7 + i8) + min) + (measuredWidth / 2) > i10;
                    i10 = (int) (((float) min) * slidingPaneLayout.b);
                    i8 = (i8 + i10) + i7;
                    slidingPaneLayout.b = ((float) i10) / ((float) slidingPaneLayout.c);
                    i9 = 0;
                } else {
                    i8 = (!slidingPaneLayout.m || slidingPaneLayout.o == 0) ? 0 : (int) ((1.0f - slidingPaneLayout.b) * ((float) slidingPaneLayout.o));
                    i9 = i8;
                    i8 = i6;
                }
                if (b) {
                    min = (i5 - i8) + i9;
                    i9 = min - measuredWidth;
                } else {
                    i9 = i8 - i9;
                    min = i9 + measuredWidth;
                }
                childAt.layout(i9, paddingTop, min, childAt.getMeasuredHeight() + paddingTop);
                i6 += childAt.getWidth();
                i7 = i8;
            }
        }
        if (slidingPaneLayout.s) {
            if (slidingPaneLayout.m) {
                if (slidingPaneLayout.o != 0) {
                    b(slidingPaneLayout.b);
                }
                if (((LayoutParams) slidingPaneLayout.a.getLayoutParams()).c) {
                    a(slidingPaneLayout.a, slidingPaneLayout.b, slidingPaneLayout.h);
                }
            } else {
                for (int i11 = 0; i11 < childCount; i11++) {
                    a(getChildAt(i11), BitmapDescriptorFactory.HUE_RED, slidingPaneLayout.h);
                }
            }
            a(slidingPaneLayout.a);
        }
        slidingPaneLayout.s = false;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            this.s = true;
        }
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (!isInTouchMode() && !this.m) {
            this.f = view == this.a;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!this.m && actionMasked == 0 && getChildCount() > 1) {
            View childAt = getChildAt(1);
            if (childAt != null) {
                this.f = jh.b(childAt, (int) motionEvent.getX(), (int) motionEvent.getY()) ^ true;
            }
        }
        if (!this.m || (this.d && actionMasked != 0)) {
            this.e.e();
            return super.onInterceptTouchEvent(motionEvent);
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.e.e();
            return false;
        } else {
            Object obj;
            float x;
            float y;
            if (actionMasked == 0) {
                this.d = false;
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.p = x;
                this.q = y;
                if (jh.b(this.a, (int) x, (int) y) && c(this.a)) {
                    obj = 1;
                    if (this.e.a(motionEvent) && obj == null) {
                        return false;
                    }
                    return true;
                }
            } else if (actionMasked == 2) {
                x = motionEvent.getX();
                y = motionEvent.getY();
                x = Math.abs(x - this.p);
                y = Math.abs(y - this.q);
                if (x > ((float) this.e.d()) && y > x) {
                    this.e.e();
                    this.d = true;
                    return false;
                }
            }
            obj = null;
            if (this.e.a(motionEvent)) {
            }
            return true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.m) {
            return super.onTouchEvent(motionEvent);
        }
        this.e.b(motionEvent);
        float x;
        float y;
        switch (motionEvent.getActionMasked()) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                this.p = x;
                this.q = y;
                break;
            case 1:
                if (c(this.a)) {
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    float f = x - this.p;
                    float f2 = y - this.q;
                    int d = this.e.d();
                    if ((f * f) + (f2 * f2) < ((float) (d * d)) && jh.b(this.a, (int) x, (int) y)) {
                        c();
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private boolean c() {
        if (!this.s && !a((float) BitmapDescriptorFactory.HUE_RED)) {
            return false;
        }
        this.f = false;
        return true;
    }

    final void a(int i) {
        if (this.a == null) {
            this.b = BitmapDescriptorFactory.HUE_RED;
            return;
        }
        boolean b = b();
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        int width = this.a.getWidth();
        if (b) {
            i = (getWidth() - i) - width;
        }
        this.b = ((float) (i - ((b ? getPaddingRight() : getPaddingLeft()) + (b ? layoutParams.rightMargin : layoutParams.leftMargin)))) / ((float) this.c);
        if (this.o != 0) {
            b(this.b);
        }
        if (layoutParams.c) {
            a(this.a, this.b, this.h);
        }
    }

    private void a(View view, float f, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f <= BitmapDescriptorFactory.HUE_RED || i == 0) {
            if (view.getLayerType() != 0) {
                if (layoutParams.d != null) {
                    layoutParams.d.setColorFilter(null);
                }
                Runnable bVar = new b(this, view);
                this.g.add(bVar);
                hs.a((View) this, bVar);
            }
            return;
        }
        int i2 = (((int) (((float) ((-16777216 & i) >>> 24)) * f)) << 24) | (i & 16777215);
        if (layoutParams.d == null) {
            layoutParams.d = new Paint();
        }
        layoutParams.d.setColorFilter(new PorterDuffColorFilter(i2, Mode.SRC_OVER));
        if (view.getLayerType() != 2) {
            view.setLayerType(2, layoutParams.d);
        }
        b(view);
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int save = canvas.save();
        if (!(!this.m || layoutParams.b || this.a == null)) {
            canvas.getClipBounds(this.t);
            if (b()) {
                this.t.left = Math.max(this.t.left, this.a.getRight());
            } else {
                this.t.right = Math.min(this.t.right, this.a.getLeft());
            }
            canvas.clipRect(this.t);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        return drawChild;
    }

    final void b(View view) {
        if (VERSION.SDK_INT >= 17) {
            hs.a(view, ((LayoutParams) view.getLayoutParams()).d);
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            if (!this.w) {
                try {
                    this.u = View.class.getDeclaredMethod("getDisplayList", null);
                } catch (Throwable e) {
                    Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e);
                }
                try {
                    this.v = View.class.getDeclaredField("mRecreateDisplayList");
                    this.v.setAccessible(true);
                } catch (Throwable e2) {
                    Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
                }
                this.w = true;
            }
            if (this.u == null || this.v == null) {
                view.invalidate();
                return;
            }
            try {
                this.v.setBoolean(view, true);
                this.u.invoke(view, null);
            } catch (Throwable e22) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e22);
            }
        }
        hs.a(this, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    private boolean a(float f) {
        if (!this.m) {
            return false;
        }
        int width;
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        if (b()) {
            width = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + layoutParams.rightMargin)) + (f * ((float) this.c))) + ((float) this.a.getWidth())));
        } else {
            width = (int) (((float) (getPaddingLeft() + layoutParams.leftMargin)) + (f * ((float) this.c)));
        }
        if (!this.e.a(this.a, width, this.a.getTop())) {
            return false;
        }
        a();
        hs.e(this);
        return true;
    }

    public void computeScroll() {
        if (this.e.g()) {
            if (this.m) {
                hs.e(this);
            } else {
                this.e.f();
            }
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable drawable) {
        setShadowDrawableLeft(drawable);
    }

    public void setShadowDrawableLeft(Drawable drawable) {
        this.j = drawable;
    }

    public void setShadowDrawableRight(Drawable drawable) {
        this.k = drawable;
    }

    @Deprecated
    public void setShadowResource(int i) {
        setShadowDrawable(getResources().getDrawable(i));
    }

    public void setShadowResourceLeft(int i) {
        setShadowDrawableLeft(a.a(getContext(), i));
    }

    public void setShadowResourceRight(int i) {
        setShadowDrawableRight(a.a(getContext(), i));
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        if (b()) {
            drawable = this.k;
        } else {
            drawable = this.j;
        }
        View childAt = getChildCount() > 1 ? getChildAt(1) : null;
        if (childAt != null && drawable != null) {
            int right;
            int top = childAt.getTop();
            int bottom = childAt.getBottom();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (b()) {
                right = childAt.getRight();
                intrinsicWidth += right;
            } else {
                right = childAt.getLeft();
                int i = right - intrinsicWidth;
                intrinsicWidth = right;
                right = i;
            }
            drawable.setBounds(right, top, intrinsicWidth, bottom);
            drawable.draw(canvas);
        }
    }

    private void b(float f) {
        Object obj;
        int childCount;
        int i;
        View childAt;
        int i2;
        boolean b = b();
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        if (layoutParams.c) {
            if ((b ? layoutParams.rightMargin : layoutParams.leftMargin) <= 0) {
                obj = 1;
                childCount = getChildCount();
                for (i = 0; i < childCount; i++) {
                    childAt = getChildAt(i);
                    if (childAt != this.a) {
                        i2 = (int) ((1.0f - this.n) * ((float) this.o));
                        this.n = f;
                        i2 -= (int) ((1.0f - f) * ((float) this.o));
                        if (b) {
                            i2 = -i2;
                        }
                        childAt.offsetLeftAndRight(i2);
                        if (obj == null) {
                            a(childAt, b ? this.n - 1.0f : 1.0f - this.n, this.i);
                        }
                    }
                }
            }
        }
        obj = null;
        childCount = getChildCount();
        for (i = 0; i < childCount; i++) {
            childAt = getChildAt(i);
            if (childAt != this.a) {
                i2 = (int) ((1.0f - this.n) * ((float) this.o));
                this.n = f;
                i2 -= (int) ((1.0f - f) * ((float) this.o));
                if (b) {
                    i2 = -i2;
                }
                childAt.offsetLeftAndRight(i2);
                if (obj == null) {
                    if (b) {
                    }
                    a(childAt, b ? this.n - 1.0f : 1.0f - this.n, this.i);
                }
            }
        }
    }

    final boolean c(View view) {
        if (view == null) {
            return false;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.m && layoutParams.c && this.b > BitmapDescriptorFactory.HUE_RED) {
            return true;
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        boolean z = this.m ? !this.m || this.b == 1.0f : this.f;
        savedState.a = z;
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (!savedState.a) {
                c();
            } else if (this.s || a(1.0f)) {
                this.f = true;
            }
            this.f = savedState.a;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    final boolean b() {
        return hs.g(this) == 1;
    }
}
