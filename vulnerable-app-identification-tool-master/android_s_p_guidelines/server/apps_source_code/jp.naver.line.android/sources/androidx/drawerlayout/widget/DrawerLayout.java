package androidx.drawerlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowInsets;
import androidx.core.content.a;
import androidx.customview.view.AbsSavedState;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.linecorp.yuki.sensetime.SenseTimeSlam;
import defpackage.he;
import defpackage.hs;
import defpackage.jh;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;

public class DrawerLayout extends ViewGroup {
    static final int[] a = new int[]{16842931};
    static final boolean b = (VERSION.SDK_INT >= 19);
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
    private Rect N;
    private Matrix O;
    private final b e;
    private float f;
    private int g;
    private int h;
    private float i;
    private Paint j;
    private final jh k;
    private final jh l;
    private final d m;
    private final d n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private c x;
    private List<c> y;
    private float z;

    public class LayoutParams extends MarginLayoutParams {
        public int a = 0;
        float b;
        boolean c;
        int d;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.a);
            this.a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.a = layoutParams.a;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    public class SavedState extends AbsSavedState {
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
        int a = 0;
        int b;
        int c;
        int d;
        int e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        d = z;
    }

    public DrawerLayout(Context context) {
        this(context, null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
        float f = getResources().getDisplayMetrics().density;
        this.g = (int) ((64.0f * f) + 0.5f);
        float f2 = 400.0f * f;
        this.m = new d(this, 3);
        this.n = new d(this, 5);
        this.k = jh.a((ViewGroup) this, 1.0f, this.m);
        this.k.a(1);
        this.k.a(f2);
        this.m.a(this.k);
        this.l = jh.a((ViewGroup) this, 1.0f, this.n);
        this.l.a(2);
        this.l.a(f2);
        this.n.a(this.l);
        setFocusableInTouchMode(true);
        hs.a((View) this, 1);
        hs.a((View) this, new a(this));
        setMotionEventSplittingEnabled(false);
        if (hs.u(this)) {
            if (VERSION.SDK_INT >= 21) {
                setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener(this) {
                    final /* synthetic */ DrawerLayout a;

                    {
                        this.a = r1;
                    }

                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        ((DrawerLayout) view).setChildInsets(windowInsets, windowInsets.getSystemWindowInsetTop() > 0);
                        return windowInsets.consumeSystemWindowInsets();
                    }
                });
                setSystemUiVisibility(SenseTimeSlam.MAX_PREVIEW_WIDTH_UPPER_S);
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(c);
                try {
                    this.B = obtainStyledAttributes.getDrawable(0);
                } finally {
                    obtainStyledAttributes.recycle();
                }
            } else {
                this.B = null;
            }
        }
        this.f = f * 10.0f;
        this.M = new ArrayList();
    }

    public void setDrawerElevation(float f) {
        this.f = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (d(childAt)) {
                hs.a(childAt, this.f);
            }
        }
    }

    public void setChildInsets(Object obj, boolean z) {
        this.G = obj;
        this.H = z;
        boolean z2 = !z && getBackground() == null;
        setWillNotDraw(z2);
        requestLayout();
    }

    public void setDrawerShadow(Drawable drawable, int i) {
        if (!d) {
            if ((i & 8388611) == 8388611) {
                this.I = drawable;
            } else if ((i & 8388613) == 8388613) {
                this.J = drawable;
            } else if ((i & 3) == 3) {
                this.K = drawable;
            } else if ((i & 5) == 5) {
                this.L = drawable;
            } else {
                return;
            }
            d();
            invalidate();
        }
    }

    public void setDrawerShadow(int i, int i2) {
        setDrawerShadow(a.a(getContext(), i), i2);
    }

    public void setScrimColor(int i) {
        this.h = i;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(c cVar) {
        if (this.x != null) {
            c cVar2 = this.x;
            if (!(cVar2 == null || this.y == null)) {
                this.y.remove(cVar2);
            }
        }
        if (!(cVar == null || cVar == null)) {
            if (this.y == null) {
                this.y = new ArrayList();
            }
            this.y.add(cVar);
        }
        this.x = cVar;
    }

    public void setDrawerLockMode(int i) {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }

    public void setDrawerLockMode(int i, int i2) {
        int a = he.a(i2, hs.g(this));
        if (i2 == 3) {
            this.r = i;
        } else if (i2 == 5) {
            this.s = i;
        } else if (i2 == 8388611) {
            this.t = i;
        } else if (i2 == 8388613) {
            this.u = i;
        }
        if (i != 0) {
            (a == 3 ? this.k : this.l).e();
        }
        View b;
        switch (i) {
            case 1:
                b = b(a);
                if (b != null) {
                    i(b);
                    break;
                }
                break;
            case 2:
                b = b(a);
                if (b != null) {
                    h(b);
                    return;
                }
                break;
        }
    }

    public void setDrawerLockMode(int i, View view) {
        if (d(view)) {
            setDrawerLockMode(i, ((LayoutParams) view.getLayoutParams()).a);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a drawer with appropriate layout_gravity");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final int a(View view) {
        if (d(view)) {
            int i = ((LayoutParams) view.getLayoutParams()).a;
            int g = hs.g(this);
            if (i != 3) {
                if (i != 5) {
                    if (i != 8388611) {
                        if (i == 8388613) {
                            if (this.u != 3) {
                                return this.u;
                            }
                            if (g == 0) {
                                i = this.s;
                            } else {
                                i = this.r;
                            }
                            if (i != 3) {
                                return i;
                            }
                        }
                    } else if (this.t != 3) {
                        return this.t;
                    } else {
                        if (g == 0) {
                            i = this.r;
                        } else {
                            i = this.s;
                        }
                        if (i != 3) {
                            return i;
                        }
                    }
                } else if (this.s != 3) {
                    return this.s;
                } else {
                    if (g == 0) {
                        i = this.u;
                    } else {
                        i = this.t;
                    }
                    if (i != 3) {
                        return i;
                    }
                }
            } else if (this.r != 3) {
                return this.r;
            } else {
                if (g == 0) {
                    i = this.t;
                } else {
                    i = this.u;
                }
                if (i != 3) {
                    return i;
                }
            }
            return 0;
        }
        StringBuilder stringBuilder = new StringBuilder("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a drawer");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void setDrawerTitle(int i, CharSequence charSequence) {
        i = he.a(i, hs.g(this));
        if (i == 3) {
            this.E = charSequence;
            return;
        }
        if (i == 5) {
            this.F = charSequence;
        }
    }

    public final CharSequence a(int i) {
        i = he.a(i, hs.g(this));
        if (i == 3) {
            return this.E;
        }
        return i == 5 ? this.F : null;
    }

    final void a(int i, View view) {
        int a = this.k.a();
        int a2 = this.l.a();
        int i2 = 2;
        if (a == 1 || a2 == 1) {
            i2 = 1;
        } else if (!(a == 2 || a2 == 2)) {
            i2 = 0;
        }
        if (view != null && i == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.b == BitmapDescriptorFactory.HUE_RED) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                if ((layoutParams.d & 1) == 1) {
                    layoutParams.d = 0;
                    if (this.y != null) {
                        for (i = this.y.size() - 1; i >= 0; i--) {
                            ((c) this.y.get(i)).b();
                        }
                    }
                    a(view, false);
                    if (hasWindowFocus()) {
                        View rootView = getRootView();
                        if (rootView != null) {
                            rootView.sendAccessibilityEvent(32);
                        }
                    }
                }
            } else if (layoutParams.b == 1.0f) {
                layoutParams = (LayoutParams) view.getLayoutParams();
                if ((layoutParams.d & 1) == 0) {
                    layoutParams.d = 1;
                    if (this.y != null) {
                        for (i = this.y.size() - 1; i >= 0; i--) {
                            ((c) this.y.get(i)).a();
                        }
                    }
                    a(view, true);
                    if (hasWindowFocus()) {
                        sendAccessibilityEvent(32);
                    }
                }
            }
        }
        if (i2 != this.o) {
            this.o = i2;
            if (this.y != null) {
                for (i = this.y.size() - 1; i >= 0; i--) {
                    this.y.get(i);
                }
            }
        }
    }

    private void a(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || d(childAt)) && !(z && childAt == view)) {
                hs.a(childAt, 4);
            } else {
                hs.a(childAt, 1);
            }
        }
    }

    final void a(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f != layoutParams.b) {
            layoutParams.b = f;
            if (this.y != null) {
                for (int size = this.y.size() - 1; size >= 0; size--) {
                    ((c) this.y.get(size)).a(f);
                }
            }
        }
    }

    static float b(View view) {
        return ((LayoutParams) view.getLayoutParams()).b;
    }

    final int c(View view) {
        return he.a(((LayoutParams) view.getLayoutParams()).a, hs.g(this));
    }

    final boolean a(View view, int i) {
        return (c(view) & i) == i;
    }

    private View c() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).d & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    final View b(int i) {
        i = he.a(i, hs.g(this)) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((c(childAt) & 7) == i) {
                return childAt;
            }
        }
        return null;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.q = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = true;
    }

    @SuppressLint({"WrongConstant"})
    protected void onMeasure(int i, int i2) {
        DrawerLayout drawerLayout = this;
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    size = HttpStatus.SC_MULTIPLE_CHOICES;
                }
                if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                    size2 = HttpStatus.SC_MULTIPLE_CHOICES;
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(size, size2);
        int i3 = 0;
        Object obj = (drawerLayout.G == null || !hs.u(this)) ? null : 1;
        int g = hs.g(this);
        int childCount = getChildCount();
        int i4 = 0;
        Object obj2 = null;
        Object obj3 = null;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (obj != null) {
                    mode2 = he.a(layoutParams.a, g);
                    WindowInsets windowInsets;
                    if (hs.u(childAt)) {
                        if (VERSION.SDK_INT >= 21) {
                            windowInsets = (WindowInsets) drawerLayout.G;
                            if (mode2 == 3) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i3, windowInsets.getSystemWindowInsetBottom());
                            } else if (mode2 == 5) {
                                windowInsets = windowInsets.replaceSystemWindowInsets(i3, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                            }
                            childAt.dispatchApplyWindowInsets(windowInsets);
                        }
                    } else if (VERSION.SDK_INT >= 21) {
                        windowInsets = (WindowInsets) drawerLayout.G;
                        if (mode2 == 3) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), i3, windowInsets.getSystemWindowInsetBottom());
                        } else if (mode2 == 5) {
                            windowInsets = windowInsets.replaceSystemWindowInsets(i3, windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
                        }
                        layoutParams.leftMargin = windowInsets.getSystemWindowInsetLeft();
                        layoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
                        layoutParams.rightMargin = windowInsets.getSystemWindowInsetRight();
                        layoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
                    }
                }
                if (g(childAt)) {
                    childAt.measure(MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                } else if (d(childAt)) {
                    if (d && hs.p(childAt) != drawerLayout.f) {
                        hs.a(childAt, drawerLayout.f);
                    }
                    mode2 = c(childAt) & 7;
                    Object obj4 = mode2 == 3 ? 1 : null;
                    if ((obj4 == null || obj2 == null) && (obj4 != null || obj3 == null)) {
                        if (obj4 != null) {
                            obj2 = 1;
                        } else {
                            obj3 = 1;
                        }
                        childAt.measure(getChildMeasureSpec(i, (drawerLayout.g + layoutParams.leftMargin) + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i2, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                        i4++;
                        i3 = 0;
                    } else {
                        StringBuilder stringBuilder = new StringBuilder("Child drawer has absolute gravity ");
                        String toHexString = (mode2 & 3) != 3 ? (mode2 & 5) == 5 ? "RIGHT" : Integer.toHexString(mode2) : "LEFT";
                        stringBuilder.append(toHexString);
                        stringBuilder.append(" but this DrawerLayout already has a drawer view along that edge");
                        throw new IllegalStateException(stringBuilder.toString());
                    }
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder("Child ");
                    stringBuilder2.append(childAt);
                    stringBuilder2.append(" at index ");
                    stringBuilder2.append(i4);
                    stringBuilder2.append(" does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                    throw new IllegalStateException(stringBuilder2.toString());
                }
            }
            int i5 = i;
            int i6 = i2;
            i4++;
            i3 = 0;
        }
    }

    private void d() {
        if (!d) {
            this.C = e();
            this.D = f();
        }
    }

    private Drawable e() {
        int g = hs.g(this);
        if (g == 0) {
            if (this.I != null) {
                a(this.I, g);
                return this.I;
            }
        } else if (this.J != null) {
            a(this.J, g);
            return this.J;
        }
        return this.K;
    }

    private Drawable f() {
        int g = hs.g(this);
        if (g == 0) {
            if (this.J != null) {
                a(this.J, g);
                return this.J;
            }
        } else if (this.I != null) {
            a(this.I, g);
            return this.I;
        }
        return this.L;
    }

    private static boolean a(Drawable drawable, int i) {
        if (drawable == null || !androidx.core.graphics.drawable.a.a(drawable)) {
            return false;
        }
        androidx.core.graphics.drawable.a.b(drawable, i);
        return true;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.p = true;
        int i5 = i3 - i;
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (g(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int i7;
                    float f;
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (a(childAt, 3)) {
                        float f2 = (float) measuredWidth;
                        i7 = (-measuredWidth) + ((int) (layoutParams.b * f2));
                        f = ((float) (measuredWidth + i7)) / f2;
                    } else {
                        float f3 = (float) measuredWidth;
                        int i8 = i5 - ((int) (layoutParams.b * f3));
                        f = ((float) (i5 - i8)) / f3;
                        i7 = i8;
                    }
                    Object obj = f != layoutParams.b ? 1 : null;
                    int i9 = layoutParams.a & 112;
                    int i10;
                    if (i9 == 16) {
                        i10 = i4 - i2;
                        i9 = (i10 - measuredHeight) / 2;
                        if (i9 < layoutParams.topMargin) {
                            i9 = layoutParams.topMargin;
                        } else if (i9 + measuredHeight > i10 - layoutParams.bottomMargin) {
                            i9 = (i10 - layoutParams.bottomMargin) - measuredHeight;
                        }
                        childAt.layout(i7, i9, measuredWidth + i7, measuredHeight + i9);
                    } else if (i9 != 80) {
                        childAt.layout(i7, layoutParams.topMargin, measuredWidth + i7, layoutParams.topMargin + measuredHeight);
                    } else {
                        i10 = i4 - i2;
                        childAt.layout(i7, (i10 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i7, i10 - layoutParams.bottomMargin);
                    }
                    if (obj != null) {
                        a(childAt, f);
                    }
                    int i11 = layoutParams.b > BitmapDescriptorFactory.HUE_RED ? 0 : 4;
                    if (childAt.getVisibility() != i11) {
                        childAt.setVisibility(i11);
                    }
                }
            }
        }
        r0.p = false;
        r0.q = false;
    }

    public void requestLayout() {
        if (!this.p) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f = BitmapDescriptorFactory.HUE_RED;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((LayoutParams) getChildAt(i).getLayoutParams()).b);
        }
        this.i = f;
        boolean g = this.k.g();
        boolean g2 = this.l.g();
        if (g || g2) {
            hs.e(this);
        }
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.B = drawable;
        invalidate();
    }

    public void setStatusBarBackground(int i) {
        this.B = i != 0 ? a.a(getContext(), i) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.B = new ColorDrawable(i);
        invalidate();
    }

    public void onRtlPropertiesChanged(int i) {
        d();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.H && this.B != null) {
            int systemWindowInsetTop = (VERSION.SDK_INT < 21 || this.G == null) ? 0 : ((WindowInsets) this.G).getSystemWindowInsetTop();
            if (systemWindowInsetTop > 0) {
                this.B.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.B.draw(canvas);
            }
        }
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        int i;
        DrawerLayout drawerLayout = this;
        Canvas canvas2 = canvas;
        View view2 = view;
        int height = getHeight();
        boolean g = g(view);
        int width = getWidth();
        int save = canvas.save();
        int i2 = 0;
        if (g) {
            int childCount = getChildCount();
            i = width;
            int i3 = 0;
            for (width = 0; width < childCount; width++) {
                View childAt = getChildAt(width);
                if (childAt != view2 && childAt.getVisibility() == 0) {
                    Drawable background = childAt.getBackground();
                    Object obj = (background == null || background.getOpacity() != -1) ? null : 1;
                    if (obj != null && d(childAt) && childAt.getHeight() >= height) {
                        int right;
                        if (a(childAt, 3)) {
                            right = childAt.getRight();
                            if (right > i3) {
                                i3 = right;
                            }
                        } else {
                            right = childAt.getLeft();
                            if (right < i) {
                                i = right;
                            }
                        }
                    }
                }
            }
            canvas2.clipRect(i3, 0, i, getHeight());
            i2 = i3;
        } else {
            i = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas2.restoreToCount(save);
        int right2;
        float max;
        if (drawerLayout.i > BitmapDescriptorFactory.HUE_RED && g) {
            drawerLayout.j.setColor((((int) (((float) ((drawerLayout.h & -16777216) >>> 24)) * drawerLayout.i)) << 24) | (drawerLayout.h & 16777215));
            canvas.drawRect((float) i2, BitmapDescriptorFactory.HUE_RED, (float) i, (float) getHeight(), drawerLayout.j);
        } else if (drawerLayout.C != null && a(view2, 3)) {
            height = drawerLayout.C.getIntrinsicWidth();
            right2 = view.getRight();
            max = Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(((float) right2) / ((float) drawerLayout.k.b()), 1.0f));
            drawerLayout.C.setBounds(right2, view.getTop(), height + right2, view.getBottom());
            drawerLayout.C.setAlpha((int) (max * 255.0f));
            drawerLayout.C.draw(canvas2);
        } else if (drawerLayout.D != null && a(view2, 5)) {
            height = drawerLayout.D.getIntrinsicWidth();
            right2 = view.getLeft();
            max = Math.max(BitmapDescriptorFactory.HUE_RED, Math.min(((float) (getWidth() - right2)) / ((float) drawerLayout.l.b()), 1.0f));
            drawerLayout.D.setBounds(right2 - height, view.getTop(), right2, view.getBottom());
            drawerLayout.D.setAlpha((int) (max * 255.0f));
            drawerLayout.D.draw(canvas2);
        }
        return drawChild;
    }

    private static boolean g(View view) {
        return ((LayoutParams) view.getLayoutParams()).a == 0;
    }

    static boolean d(View view) {
        int a = he.a(((LayoutParams) view.getLayoutParams()).a, hs.g(view));
        return ((a & 3) == 0 && (a & 5) == 0) ? false : true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Object obj;
        int a = this.k.a(motionEvent) | this.l.a(motionEvent);
        switch (motionEvent.getActionMasked()) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.z = x;
                this.A = y;
                if (this.i > BitmapDescriptorFactory.HUE_RED) {
                    View b = this.k.b((int) x, (int) y);
                    if (b != null && g(b)) {
                        obj = 1;
                        this.v = false;
                        this.w = false;
                        break;
                    }
                }
                obj = null;
                this.v = false;
                this.w = false;
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
        obj = null;
        if (a == 0 && r7 == null) {
            int childCount = getChildCount();
            int i = 0;
            while (i < childCount) {
                if (((LayoutParams) getChildAt(i).getLayoutParams()).c) {
                    obj = 1;
                    return obj == null || this.w;
                } else {
                    i++;
                }
            }
            obj = null;
            if (obj == null) {
            }
        }
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.i <= BitmapDescriptorFactory.HUE_RED) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount != 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            for (childCount--; childCount >= 0; childCount--) {
                View childAt = getChildAt(childCount);
                if (this.N == null) {
                    this.N = new Rect();
                }
                childAt.getHitRect(this.N);
                if (this.N.contains((int) x, (int) y) && !g(childAt)) {
                    boolean dispatchGenericMotionEvent;
                    float scrollX;
                    float scrollY;
                    if (childAt.getMatrix().isIdentity()) {
                        scrollX = (float) (getScrollX() - childAt.getLeft());
                        scrollY = (float) (getScrollY() - childAt.getTop());
                        motionEvent.offsetLocation(scrollX, scrollY);
                        dispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(motionEvent);
                        motionEvent.offsetLocation(-scrollX, -scrollY);
                    } else {
                        scrollX = (float) (getScrollX() - childAt.getLeft());
                        scrollY = (float) (getScrollY() - childAt.getTop());
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        obtain.offsetLocation(scrollX, scrollY);
                        Matrix matrix = childAt.getMatrix();
                        if (!matrix.isIdentity()) {
                            if (this.O == null) {
                                this.O = new Matrix();
                            }
                            matrix.invert(this.O);
                            obtain.transform(this.O);
                        }
                        dispatchGenericMotionEvent = childAt.dispatchGenericMotionEvent(obtain);
                        obtain.recycle();
                    }
                    if (dispatchGenericMotionEvent) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.k.b(motionEvent);
        this.l.b(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            float x;
            float y;
            switch (action) {
                case 0:
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    this.z = x;
                    this.A = y;
                    this.v = false;
                    this.w = false;
                    break;
                case 1:
                    boolean z;
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    View b = this.k.b((int) x, (int) y);
                    if (b != null && g(b)) {
                        x -= this.z;
                        y -= this.A;
                        int d = this.k.d();
                        if ((x * x) + (y * y) < ((float) (d * d))) {
                            View c = c();
                            if (!(c == null || a(c) == 2)) {
                                z = false;
                                a(z);
                                this.v = false;
                                break;
                            }
                        }
                    }
                    z = true;
                    a(z);
                    this.v = false;
            }
        } else {
            a(true);
            this.v = false;
            this.w = false;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        this.v = z;
        if (z) {
            a(true);
        }
    }

    private void a(boolean z) {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (d(childAt) && (!z || layoutParams.c)) {
                int width = childAt.getWidth();
                if (a(childAt, 3)) {
                    i |= this.k.a(childAt, -width, childAt.getTop());
                } else {
                    i |= this.l.a(childAt, getWidth(), childAt.getTop());
                }
                layoutParams.c = false;
            }
        }
        this.m.a();
        this.n.a();
        if (i != 0) {
            invalidate();
        }
    }

    private void h(View view) {
        if (d(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.q) {
                layoutParams.b = 1.0f;
                layoutParams.d = 1;
                a(view, true);
            } else {
                layoutParams.d |= 2;
                if (a(view, 3)) {
                    this.k.a(view, 0, view.getTop());
                } else {
                    this.l.a(view, getWidth() - view.getWidth(), view.getTop());
                }
            }
            invalidate();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a sliding drawer");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public final void e(View view) {
        i(view);
    }

    private void i(View view) {
        if (d(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.q) {
                layoutParams.b = BitmapDescriptorFactory.HUE_RED;
                layoutParams.d = 0;
            } else {
                layoutParams.d |= 4;
                if (a(view, 3)) {
                    this.k.a(view, -view.getWidth(), view.getTop());
                } else {
                    this.l.a(view, getWidth(), view.getTop());
                }
            }
            invalidate();
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("View ");
        stringBuilder.append(view);
        stringBuilder.append(" is not a sliding drawer");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            Object obj = null;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (!d(childAt)) {
                    this.M.add(childAt);
                } else if (d(childAt)) {
                    if (((((LayoutParams) childAt.getLayoutParams()).d & 1) == 1 ? 1 : null) != null) {
                        childAt.addFocusables(arrayList, i, i2);
                        obj = 1;
                    }
                } else {
                    StringBuilder stringBuilder = new StringBuilder("View ");
                    stringBuilder.append(childAt);
                    stringBuilder.append(" is not a drawer");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
            if (obj == null) {
                childCount = this.M.size();
                for (int i4 = 0; i4 < childCount; i4++) {
                    View view = (View) this.M.get(i4);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i, i2);
                    }
                }
            }
            this.M.clear();
        }
    }

    final View a() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (d(childAt)) {
                if (d(childAt)) {
                    if ((((LayoutParams) childAt.getLayoutParams()).b > BitmapDescriptorFactory.HUE_RED ? 1 : null) != null) {
                        return childAt;
                    }
                } else {
                    StringBuilder stringBuilder = new StringBuilder("View ");
                    stringBuilder.append(childAt);
                    stringBuilder.append(" is not a drawer");
                    throw new IllegalArgumentException(stringBuilder.toString());
                }
            }
        }
        return null;
    }

    final void b() {
        if (!this.w) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.w = true;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View a = a();
        if (a != null && a(a) == 0) {
            a(false);
        }
        if (a != null) {
            return true;
        }
        return false;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (savedState.a != 0) {
                View b = b(savedState.a);
                if (b != null) {
                    h(b);
                }
            }
            if (savedState.b != 3) {
                setDrawerLockMode(savedState.b, 3);
            }
            if (savedState.c != 3) {
                setDrawerLockMode(savedState.c, 5);
            }
            if (savedState.d != 3) {
                setDrawerLockMode(savedState.d, 8388611);
            }
            if (savedState.e != 3) {
                setDrawerLockMode(savedState.e, 8388613);
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            Object obj = 1;
            Object obj2 = layoutParams.d == 1 ? 1 : null;
            if (layoutParams.d != 2) {
                obj = null;
            }
            if (obj2 != null || obj != null) {
                savedState.a = layoutParams.a;
                break;
            }
        }
        savedState.b = this.r;
        savedState.c = this.s;
        savedState.d = this.t;
        savedState.e = this.u;
        return savedState;
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (c() != null || d(view)) {
            hs.a(view, 4);
        } else {
            hs.a(view, 1);
        }
        if (!b) {
            hs.a(view, this.e);
        }
    }

    static boolean f(View view) {
        return (hs.f(view) == 4 || hs.f(view) == 2) ? false : true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if ((a() != null ? 1 : null) != null) {
                keyEvent.startTracking();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
