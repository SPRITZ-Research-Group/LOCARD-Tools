package android.support.design.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.design.a.i;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.j;
import android.support.v4.view.l;
import android.support.v4.view.m;
import android.support.v4.view.w;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.OnHierarchyChangeListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements j {
    static final String a;
    static final Class<?>[] b = new Class[]{Context.class, AttributeSet.class};
    static final ThreadLocal<Map<String, Constructor<b>>> c = new ThreadLocal();
    static final Comparator<View> e;
    static final e f;
    final Comparator<View> d;
    private final List<View> g;
    private final List<View> h;
    private final List<View> i;
    private final Rect j;
    private final Rect k;
    private final Rect l;
    private final int[] m;
    private boolean n;
    private int[] o;
    private View p;
    private View q;
    private View r;
    private e s;
    private boolean t;
    private w u;
    private boolean v;
    private Drawable w;
    private OnHierarchyChangeListener x;
    private final l y;

    public static abstract class b<V extends View> {
        public b(Context context, AttributeSet attrs) {
        }

        public boolean a(CoordinatorLayout parent, V v, MotionEvent ev) {
            return false;
        }

        public boolean b(CoordinatorLayout parent, V v, MotionEvent ev) {
            return false;
        }

        public boolean a_(View dependency) {
            return false;
        }

        public boolean b(CoordinatorLayout parent, V v, View dependency) {
            return false;
        }

        public boolean a(CoordinatorLayout parent, V v, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
            return false;
        }

        public boolean a(CoordinatorLayout parent, V v, int layoutDirection) {
            return false;
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, View directTargetChild, int nestedScrollAxes) {
            return false;
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, View target) {
        }

        public void b(CoordinatorLayout coordinatorLayout, V v, int dyUnconsumed) {
        }

        public void a(CoordinatorLayout coordinatorLayout, V v, int dy, int[] consumed) {
        }

        public boolean a(CoordinatorLayout coordinatorLayout, V v, float velocityY, boolean consumed) {
            return false;
        }

        public void a(CoordinatorLayout parent, V v, Parcelable state) {
        }

        public Parcelable a(CoordinatorLayout parent, V v) {
            return BaseSavedState.EMPTY_STATE;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface DefaultBehavior {
        Class<? extends b> value();
    }

    protected static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = android.support.v4.os.b.a(new android.support.v4.os.c<SavedState>() {
            public final /* bridge */ /* synthetic */ Object[] a(int i) {
                return new SavedState[i];
            }

            public final /* synthetic */ Object a(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        });
        SparseArray<Parcelable> a;

        public SavedState(Parcel source, ClassLoader loader) {
            super(source);
            int size = source.readInt();
            int[] ids = new int[size];
            source.readIntArray(ids);
            Parcelable[] states = source.readParcelableArray(loader);
            this.a = new SparseArray(size);
            for (int i = 0; i < size; i++) {
                this.a.append(ids[i], states[i]);
            }
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            int size = this.a != null ? this.a.size() : 0;
            dest.writeInt(size);
            int[] ids = new int[size];
            Parcelable[] states = new Parcelable[size];
            for (int i = 0; i < size; i++) {
                ids[i] = this.a.keyAt(i);
                states[i] = (Parcelable) this.a.valueAt(i);
            }
            dest.writeIntArray(ids);
            dest.writeParcelableArray(states, flags);
        }
    }

    final class a implements m {
        final /* synthetic */ CoordinatorLayout a;

        a(CoordinatorLayout coordinatorLayout) {
            this.a = coordinatorLayout;
        }

        public final w a(View v, w insets) {
            CoordinatorLayout.a(this.a, insets);
            return insets.f();
        }
    }

    final class c implements OnHierarchyChangeListener {
        final /* synthetic */ CoordinatorLayout a;

        c(CoordinatorLayout coordinatorLayout) {
            this.a = coordinatorLayout;
        }

        public final void onChildViewAdded(View parent, View child) {
            if (this.a.x != null) {
                this.a.x.onChildViewAdded(parent, child);
            }
        }

        public final void onChildViewRemoved(View parent, View child) {
            this.a.a(child);
            if (this.a.x != null) {
                this.a.x.onChildViewRemoved(parent, child);
            }
        }
    }

    public static class d extends MarginLayoutParams {
        b a;
        boolean b = false;
        public int c = 0;
        public int d = 0;
        public int e = -1;
        int f = -1;
        View g;
        View h;
        final Rect i = new Rect();
        Object j;
        private boolean k;
        private boolean l;
        private boolean m;

        public d() {
            super(-2, -2);
        }

        d(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, android.support.design.a.j.CoordinatorLayout_LayoutParams);
            this.c = a.getInteger(android.support.design.a.j.CoordinatorLayout_LayoutParams_android_layout_gravity, 0);
            this.f = a.getResourceId(android.support.design.a.j.CoordinatorLayout_LayoutParams_layout_anchor, -1);
            this.d = a.getInteger(android.support.design.a.j.CoordinatorLayout_LayoutParams_layout_anchorGravity, 0);
            this.e = a.getInteger(android.support.design.a.j.CoordinatorLayout_LayoutParams_layout_keyline, -1);
            this.b = a.hasValue(android.support.design.a.j.CoordinatorLayout_LayoutParams_layout_behavior);
            if (this.b) {
                this.a = CoordinatorLayout.a(context, attrs, a.getString(android.support.design.a.j.CoordinatorLayout_LayoutParams_layout_behavior));
            }
            a.recycle();
        }

        public d(d p) {
            super(p);
        }

        public d(MarginLayoutParams p) {
            super(p);
        }

        public d(LayoutParams p) {
            super(p);
        }

        public final void a(b behavior) {
            if (this.a != behavior) {
                this.a = behavior;
                this.j = null;
                this.b = true;
            }
        }

        final boolean a() {
            if (this.a == null) {
                this.k = false;
            }
            return this.k;
        }

        final boolean b() {
            if (this.k) {
                return true;
            }
            boolean z = this.k | 0;
            this.k = z;
            return z;
        }

        final void c() {
            this.k = false;
        }

        final void d() {
            this.l = false;
        }

        final void a(boolean accept) {
            this.l = accept;
        }

        final boolean e() {
            return this.l;
        }

        final boolean f() {
            return this.m;
        }

        final void g() {
            this.m = false;
        }

        final void h() {
            this.m = false;
        }

        final boolean a(View dependency) {
            return dependency == this.h || (this.a != null && this.a.a_(dependency));
        }
    }

    class e implements OnPreDrawListener {
        final /* synthetic */ CoordinatorLayout a;

        e(CoordinatorLayout coordinatorLayout) {
            this.a = coordinatorLayout;
        }

        public final boolean onPreDraw() {
            this.a.a(false);
            return true;
        }
    }

    static class f implements Comparator<View> {
        f() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            View view = (View) obj2;
            float C = ViewCompat.C((View) obj);
            float C2 = ViewCompat.C(view);
            if (C > C2) {
                return -1;
            }
            if (C < C2) {
                return 1;
            }
            return 0;
        }
    }

    static /* synthetic */ void a(CoordinatorLayout x0, w x1) {
        boolean z = true;
        int i = 0;
        if (x0.u != x1) {
            x0.u = x1;
            boolean z2 = x1 != null && x1.b() > 0;
            x0.v = z2;
            if (x0.v || x0.getBackground() != null) {
                z = false;
            }
            x0.setWillNotDraw(z);
            if (!x1.e()) {
                int childCount = x0.getChildCount();
                w wVar = x1;
                while (i < childCount) {
                    View childAt = x0.getChildAt(i);
                    if (ViewCompat.u(childAt)) {
                        if (((d) childAt.getLayoutParams()).a != null && wVar.e()) {
                            break;
                        }
                        wVar = ViewCompat.b(childAt, wVar);
                        if (wVar.e()) {
                            break;
                        }
                    }
                    i++;
                }
            }
            x0.requestLayout();
        }
    }

    static {
        Package pkg = CoordinatorLayout.class.getPackage();
        a = pkg != null ? pkg.getName() : null;
        if (VERSION.SDK_INT >= 21) {
            e = new f();
            f = new f();
        } else {
            e = null;
            f = null;
        }
    }

    public CoordinatorLayout(Context context) {
        this(context, null);
    }

    public CoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.d = new Comparator<View>(this) {
            final /* synthetic */ CoordinatorLayout a;

            {
                this.a = r1;
            }

            public final /* synthetic */ int compare(Object obj, Object obj2) {
                View view = (View) obj;
                View view2 = (View) obj2;
                if (view != view2) {
                    if (((d) view.getLayoutParams()).a(view2)) {
                        return 1;
                    }
                    if (((d) view2.getLayoutParams()).a(view)) {
                        return -1;
                    }
                }
                return 0;
            }
        };
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.j = new Rect();
        this.k = new Rect();
        this.l = new Rect();
        this.m = new int[2];
        this.y = new l(this);
        r.a(context);
        TypedArray a = context.obtainStyledAttributes(attrs, android.support.design.a.j.CoordinatorLayout, defStyleAttr, i.Widget_Design_CoordinatorLayout);
        int keylineArrayRes = a.getResourceId(android.support.design.a.j.CoordinatorLayout_keylines, 0);
        if (keylineArrayRes != 0) {
            Resources res = context.getResources();
            this.o = res.getIntArray(keylineArrayRes);
            float density = res.getDisplayMetrics().density;
            int count = this.o.length;
            for (int i = 0; i < count; i++) {
                int[] iArr = this.o;
                iArr[i] = (int) (((float) iArr[i]) * density);
            }
        }
        this.w = a.getDrawable(android.support.design.a.j.CoordinatorLayout_statusBarBackground);
        a.recycle();
        if (f != null) {
            f.a(this, new a(this));
        }
        super.setOnHierarchyChangeListener(new c(this));
    }

    public void setOnHierarchyChangeListener(OnHierarchyChangeListener onHierarchyChangeListener) {
        this.x = onHierarchyChangeListener;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        if (this.t) {
            if (this.s == null) {
                this.s = new e(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.s);
        }
        if (this.u == null && ViewCompat.u(this)) {
            ViewCompat.t(this);
        }
        this.n = true;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
        if (this.t && this.s != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.s);
        }
        if (this.r != null) {
            onStopNestedScroll(this.r);
        }
        this.n = false;
    }

    public void setStatusBarBackground(Drawable bg) {
        this.w = bg;
        invalidate();
    }

    public void setStatusBarBackgroundResource(int resId) {
        setStatusBarBackground(resId != 0 ? android.support.v4.content.a.a(getContext(), resId) : null);
    }

    public void setStatusBarBackgroundColor(int color) {
        setStatusBarBackground(new ColorDrawable(color));
    }

    private void a() {
        if (this.p != null) {
            b b = ((d) this.p.getLayoutParams()).a;
            if (b != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent cancelEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                b.b(this, this.p, cancelEvent);
                cancelEvent.recycle();
            }
            this.p = null;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ((d) getChildAt(i).getLayoutParams()).c();
        }
    }

    private boolean a(MotionEvent ev, int type) {
        boolean intercepted = false;
        boolean newBlock = false;
        MotionEvent cancelEvent = null;
        int action = ev.getActionMasked();
        List<View> topmostChildList = this.h;
        topmostChildList.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            int childDrawingOrder;
            if (isChildrenDrawingOrderEnabled) {
                childDrawingOrder = getChildDrawingOrder(childCount, i);
            } else {
                childDrawingOrder = i;
            }
            topmostChildList.add(getChildAt(childDrawingOrder));
        }
        if (e != null) {
            Collections.sort(topmostChildList, e);
        }
        int childCount2 = topmostChildList.size();
        for (int i2 = 0; i2 < childCount2; i2++) {
            View child = (View) topmostChildList.get(i2);
            d lp = (d) child.getLayoutParams();
            b b = lp.a;
            if ((intercepted || newBlock) && action != 0) {
                if (b != null) {
                    if (cancelEvent == null) {
                        long uptimeMillis = SystemClock.uptimeMillis();
                        cancelEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    switch (type) {
                        case 0:
                            b.a(this, child, cancelEvent);
                            break;
                        case 1:
                            b.b(this, child, cancelEvent);
                            break;
                        default:
                            break;
                    }
                }
            } else {
                if (!(intercepted || b == null)) {
                    switch (type) {
                        case 0:
                            intercepted = b.a(this, child, ev);
                            break;
                        case 1:
                            intercepted = b.b(this, child, ev);
                            break;
                    }
                    if (intercepted) {
                        this.p = child;
                    }
                }
                boolean wasBlocking = lp.a();
                boolean isBlocking = lp.b();
                newBlock = isBlocking && !wasBlocking;
                if (isBlocking && !newBlock) {
                    topmostChildList.clear();
                    return intercepted;
                }
            }
        }
        topmostChildList.clear();
        return intercepted;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(MotionEvent ev) {
        boolean handled = false;
        boolean cancelSuper = false;
        MotionEvent cancelEvent = null;
        int action = ev.getActionMasked();
        if (this.p == null) {
            cancelSuper = a(ev, 1);
        }
        b b = ((d) this.p.getLayoutParams()).a;
        if (b != null) {
            handled = b.b(this, this.p, ev);
        }
        if (this.p == null) {
            handled |= super.onTouchEvent(ev);
        } else if (cancelSuper) {
            long uptimeMillis = SystemClock.uptimeMillis();
            cancelEvent = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            super.onTouchEvent(cancelEvent);
        }
        if (cancelEvent != null) {
            cancelEvent.recycle();
        }
        if (action == 1 || action == 3) {
            a();
        }
        return handled;
    }

    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
        if (disallowIntercept) {
            a();
        }
    }

    private int a(int index) {
        if (this.o == null) {
            new StringBuilder("No keylines defined for ").append(this).append(" - attempted index lookup ").append(index);
            return 0;
        } else if (index >= 0 && index < this.o.length) {
            return this.o[index];
        } else {
            new StringBuilder("Keyline index ").append(index).append(" out of range for ").append(this);
            return 0;
        }
    }

    static b a(Context context, AttributeSet attrs, String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        fullName = name.startsWith(".") ? context.getPackageName() + name : name.indexOf(46) >= 0 ? name : !TextUtils.isEmpty(a) ? a + '.' + name : name;
        try {
            Map<String, Constructor<b>> constructors = (Map) c.get();
            if (constructors == null) {
                constructors = new HashMap();
                c.set(constructors);
            }
            Constructor<b> c = (Constructor) constructors.get(fullName);
            if (c == null) {
                c = Class.forName(fullName, true, context.getClassLoader()).getConstructor(b);
                c.setAccessible(true);
                constructors.put(fullName, c);
            }
            return (b) c.newInstance(new Object[]{context, attrs});
        } catch (Exception e) {
            throw new RuntimeException("Could not inflate Behavior subclass " + fullName, e);
        }
    }

    private static d d(View child) {
        d result = (d) child.getLayoutParams();
        if (!result.b) {
            DefaultBehavior defaultBehavior = null;
            for (Class<?> childClass = child.getClass(); childClass != null; childClass = childClass.getSuperclass()) {
                defaultBehavior = (DefaultBehavior) childClass.getAnnotation(DefaultBehavior.class);
                if (defaultBehavior != null) {
                    break;
                }
            }
            if (defaultBehavior != null) {
                try {
                    result.a((b) defaultBehavior.value().newInstance());
                } catch (Exception e) {
                    new StringBuilder("Default behavior class ").append(defaultBehavior.value().getName()).append(" could not be instantiated. Did you forget a default constructor?");
                }
            }
            result.b = true;
        }
        return result;
    }

    protected int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
    }

    protected int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
    }

    public final void a(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        measureChildWithMargins(child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    public final void a(View child, int layoutDirection) {
        Object obj;
        d lp = (d) child.getLayoutParams();
        if (lp.g != null || lp.f == -1) {
            obj = null;
        } else {
            obj = 1;
        }
        int i;
        d dVar;
        int max;
        int max2;
        if (obj != null) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (lp.g != null) {
            View view = lp.g;
            child.getLayoutParams();
            Rect rect = this.j;
            Rect rect2 = this.k;
            v.a(this, view, rect);
            a(child, layoutDirection, rect, rect2);
            child.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        } else if (lp.e >= 0) {
            int i2;
            i = lp.e;
            dVar = (d) child.getLayoutParams();
            int a = android.support.v4.view.d.a(c(dVar.c), layoutDirection);
            int i3 = a & 7;
            a &= 112;
            int width = getWidth();
            int height = getHeight();
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            if (layoutDirection == 1) {
                i = width - i;
            }
            i = a(i) - measuredWidth;
            switch (i3) {
                case 1:
                    i2 = i + (measuredWidth / 2);
                    break;
                case 5:
                    i2 = i + measuredWidth;
                    break;
                default:
                    i2 = i;
                    break;
            }
            switch (a) {
                case 16:
                    i = (measuredHeight / 2) + 0;
                    break;
                case 80:
                    i = measuredHeight + 0;
                    break;
                default:
                    i = 0;
                    break;
            }
            max = Math.max(getPaddingLeft() + dVar.leftMargin, Math.min(i2, ((width - getPaddingRight()) - measuredWidth) - dVar.rightMargin));
            max2 = Math.max(getPaddingTop() + dVar.topMargin, Math.min(i, ((height - getPaddingBottom()) - measuredHeight) - dVar.bottomMargin));
            child.layout(max, max2, max + measuredWidth, max2 + measuredHeight);
        } else {
            dVar = (d) child.getLayoutParams();
            Rect rect3 = this.j;
            rect3.set(getPaddingLeft() + dVar.leftMargin, getPaddingTop() + dVar.topMargin, (getWidth() - getPaddingRight()) - dVar.rightMargin, (getHeight() - getPaddingBottom()) - dVar.bottomMargin);
            if (!(this.u == null || !ViewCompat.u(this) || ViewCompat.u(child))) {
                rect3.left += this.u.a();
                rect3.top += this.u.b();
                rect3.right -= this.u.c();
                rect3.bottom -= this.u.d();
            }
            Rect rect4 = this.k;
            max2 = b(dVar.c);
            i = child.getMeasuredWidth();
            max = child.getMeasuredHeight();
            if (VERSION.SDK_INT >= 17) {
                Gravity.apply(max2, i, max, rect3, rect4, layoutDirection);
            } else {
                Gravity.apply(max2, i, max, rect3, rect4);
            }
            child.layout(rect4.left, rect4.top, rect4.right, rect4.bottom);
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layoutDirection = ViewCompat.f(this);
        int childCount = this.g.size();
        for (int i = 0; i < childCount; i++) {
            View child = (View) this.g.get(i);
            b behavior = ((d) child.getLayoutParams()).a;
            if (behavior == null || !behavior.a(this, child, layoutDirection)) {
                a(child, layoutDirection);
            }
        }
    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        if (this.v && this.w != null) {
            int inset = this.u != null ? this.u.b() : 0;
            if (inset > 0) {
                this.w.setBounds(0, 0, getWidth(), inset);
                this.w.draw(c);
            }
        }
    }

    private void a(View child, boolean transform, Rect out) {
        if (child.isLayoutRequested() || child.getVisibility() == 8) {
            out.set(0, 0, 0, 0);
        } else if (transform) {
            v.a(this, child, out);
        } else {
            out.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        }
    }

    private void a(View child, int layoutDirection, Rect anchorRect, Rect out) {
        int left;
        int top;
        d lp = (d) child.getLayoutParams();
        int i = lp.c;
        if (i == 0) {
            i = 17;
        }
        int absGravity = android.support.v4.view.d.a(i, layoutDirection);
        int absAnchorGravity = android.support.v4.view.d.a(b(lp.d), layoutDirection);
        int hgrav = absGravity & 7;
        int vgrav = absGravity & 112;
        int anchorHgrav = absAnchorGravity & 7;
        int anchorVgrav = absAnchorGravity & 112;
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        switch (anchorHgrav) {
            case 1:
                left = anchorRect.left + (anchorRect.width() / 2);
                break;
            case 5:
                left = anchorRect.right;
                break;
            default:
                left = anchorRect.left;
                break;
        }
        switch (anchorVgrav) {
            case 16:
                top = anchorRect.top + (anchorRect.height() / 2);
                break;
            case 80:
                top = anchorRect.bottom;
                break;
            default:
                top = anchorRect.top;
                break;
        }
        switch (hgrav) {
            case 1:
                left -= childWidth / 2;
                break;
            case 5:
                break;
            default:
                left -= childWidth;
                break;
        }
        switch (vgrav) {
            case 16:
                top -= childHeight / 2;
                break;
            case 80:
                break;
            default:
                top -= childHeight;
                break;
        }
        int width = getWidth();
        int height = getHeight();
        left = Math.max(getPaddingLeft() + lp.leftMargin, Math.min(left, ((width - getPaddingRight()) - childWidth) - lp.rightMargin));
        top = Math.max(getPaddingTop() + lp.topMargin, Math.min(top, ((height - getPaddingBottom()) - childHeight) - lp.bottomMargin));
        out.set(left, top, left + childWidth, top + childHeight);
    }

    private static int b(int gravity) {
        return gravity == 0 ? 8388659 : gravity;
    }

    private static int c(int gravity) {
        return gravity == 0 ? 8388661 : gravity;
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        child.getLayoutParams();
        return super.drawChild(canvas, child, drawingTime);
    }

    final void a(boolean fromNestedScroll) {
        int layoutDirection = ViewCompat.f(this);
        int childCount = this.g.size();
        for (int i = 0; i < childCount; i++) {
            int j;
            View child = (View) this.g.get(i);
            d lp = (d) child.getLayoutParams();
            for (j = 0; j < i; j++) {
                if (lp.h == ((View) this.g.get(j))) {
                    d dVar = (d) child.getLayoutParams();
                    if (dVar.g != null) {
                        Rect rect = this.j;
                        Rect rect2 = this.k;
                        Rect rect3 = this.l;
                        v.a(this, dVar.g, rect);
                        a(child, false, rect2);
                        a(child, layoutDirection, rect, rect3);
                        int i2 = rect3.left - rect2.left;
                        int i3 = rect3.top - rect2.top;
                        if (i2 != 0) {
                            child.offsetLeftAndRight(i2);
                        }
                        if (i3 != 0) {
                            child.offsetTopAndBottom(i3);
                        }
                        if (i2 != 0 || i3 != 0) {
                            b bVar = dVar.a;
                            if (bVar != null) {
                                bVar.b(this, child, dVar.g);
                            }
                        }
                    }
                }
            }
            Rect oldRect = this.j;
            Rect newRect = this.k;
            oldRect.set(((d) child.getLayoutParams()).i);
            a(child, true, newRect);
            if (!oldRect.equals(newRect)) {
                ((d) child.getLayoutParams()).i.set(newRect);
                for (j = i + 1; j < childCount; j++) {
                    View checkChild = (View) this.g.get(j);
                    d checkLp = (d) checkChild.getLayoutParams();
                    b b = checkLp.a;
                    if (b != null && b.a_(child)) {
                        if (fromNestedScroll || !checkLp.f()) {
                            b.b(this, checkChild, child);
                            if (fromNestedScroll) {
                                checkLp.g();
                            }
                        } else {
                            checkLp.h();
                        }
                    }
                }
            }
        }
    }

    final void a(View view) {
        int childCount = this.g.size();
        boolean viewSeen = false;
        for (int i = 0; i < childCount; i++) {
            View child = (View) this.g.get(i);
            if (child == view) {
                viewSeen = true;
            } else if (viewSeen) {
                child.getLayoutParams();
            }
        }
    }

    public final void b(View view) {
        int childCount = this.g.size();
        boolean viewSeen = false;
        for (int i = 0; i < childCount; i++) {
            View child = (View) this.g.get(i);
            if (child == view) {
                viewSeen = true;
            } else if (viewSeen) {
                d lp = (d) child.getLayoutParams();
                b b = lp.a;
                if (b != null && lp.a(view)) {
                    b.b(this, child, view);
                }
            }
        }
    }

    public final List<View> c(View child) {
        d lp = (d) child.getLayoutParams();
        List<View> list = this.i;
        list.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View other = getChildAt(i);
            if (other != child && lp.a(other)) {
                list.add(other);
            }
        }
        return list;
    }

    public final boolean a(View child, int x, int y) {
        Rect r = this.j;
        v.a(this, child, r);
        return r.contains(x, y);
    }

    public final boolean a(View first, View second) {
        if (first.getVisibility() != 0 || second.getVisibility() != 0) {
            return false;
        }
        boolean z;
        Rect firstRect = this.j;
        if (first.getParent() != this) {
            z = true;
        } else {
            z = false;
        }
        a(first, z, firstRect);
        Rect secondRect = this.k;
        if (second.getParent() != this) {
            z = true;
        } else {
            z = false;
        }
        a(second, z, secondRect);
        if (firstRect.left > secondRect.right || firstRect.top > secondRect.bottom || firstRect.right < secondRect.left || firstRect.bottom < secondRect.top) {
            return false;
        }
        return true;
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof d) && super.checkLayoutParams(p);
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        boolean handled = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            d lp = (d) view.getLayoutParams();
            b viewBehavior = lp.a;
            if (viewBehavior != null) {
                boolean accepted = viewBehavior.a(this, view, child, nestedScrollAxes);
                handled |= accepted;
                lp.a(accepted);
            } else {
                lp.a(false);
            }
        }
        return handled;
    }

    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        this.y.a(nestedScrollAxes);
        this.q = child;
        this.r = target;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).getLayoutParams();
        }
    }

    public void onStopNestedScroll(View target) {
        this.y.b();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            d lp = (d) view.getLayoutParams();
            if (lp.e()) {
                b viewBehavior = lp.a;
                if (viewBehavior != null) {
                    viewBehavior.a(this, view, target);
                }
                lp.d();
                lp.h();
            }
        }
        this.q = null;
        this.r = null;
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int childCount = getChildCount();
        boolean accepted = false;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            d lp = (d) view.getLayoutParams();
            if (lp.e()) {
                b viewBehavior = lp.a;
                if (viewBehavior != null) {
                    viewBehavior.b(this, view, dyUnconsumed);
                    accepted = true;
                }
            }
        }
        if (accepted) {
            a(true);
        }
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        int xConsumed = 0;
        int yConsumed = 0;
        boolean accepted = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            d lp = (d) view.getLayoutParams();
            if (lp.e()) {
                b viewBehavior = lp.a;
                if (viewBehavior != null) {
                    int[] iArr = this.m;
                    this.m[1] = 0;
                    iArr[0] = 0;
                    viewBehavior.a(this, view, dy, this.m);
                    xConsumed = dx > 0 ? Math.max(xConsumed, this.m[0]) : Math.min(xConsumed, this.m[0]);
                    yConsumed = dy > 0 ? Math.max(yConsumed, this.m[1]) : Math.min(yConsumed, this.m[1]);
                    accepted = true;
                }
            }
        }
        consumed[0] = xConsumed;
        consumed[1] = yConsumed;
        if (accepted) {
            a(true);
        }
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        boolean handled = false;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            d lp = (d) view.getLayoutParams();
            if (lp.e()) {
                b viewBehavior = lp.a;
                if (viewBehavior != null) {
                    handled |= viewBehavior.a(this, view, velocityY, consumed);
                }
            }
        }
        if (handled) {
            a(true);
        }
        return handled;
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).getLayoutParams();
        }
        return false;
    }

    public int getNestedScrollAxes() {
        return this.y.a();
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        SparseArray<Parcelable> behaviorStates = ss.a;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childId = child.getId();
            b b = d(child).a;
            if (!(childId == -1 || b == null)) {
                Parcelable savedState = (Parcelable) behaviorStates.get(childId);
                if (savedState != null) {
                    b.a(this, child, savedState);
                }
            }
        }
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> behaviorStates = new SparseArray();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int childId = child.getId();
            b b = ((d) child.getLayoutParams()).a;
            if (!(childId == -1 || b == null)) {
                Parcelable state = b.a(this, child);
                if (state != null) {
                    behaviorStates.append(childId, state);
                }
            }
        }
        ss.a = behaviorStates;
        return ss;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 0) {
            a();
        }
        boolean intercepted = a(ev, 0);
        if (action == 1 || action == 3) {
            a();
        }
        return intercepted;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.g.clear();
        int i = 0;
        int childCount = getChildCount();
        while (true) {
            int i2 = i;
            Object obj;
            if (i2 < childCount) {
                View childAt = getChildAt(i2);
                d d = d(childAt);
                if (d.f == -1) {
                    d.h = null;
                    d.g = null;
                } else {
                    View view;
                    ViewParent parent;
                    if (d.g != null) {
                        if (d.g.getId() != d.f) {
                            obj = null;
                        } else {
                            view = d.g;
                            parent = d.g.getParent();
                            while (parent != this) {
                                if (parent == null || parent == childAt) {
                                    d.h = null;
                                    d.g = null;
                                    obj = null;
                                    break;
                                }
                                if (parent instanceof View) {
                                    view = (View) parent;
                                }
                                parent = parent.getParent();
                            }
                            d.h = view;
                            obj = 1;
                        }
                        if (obj != null) {
                            continue;
                        }
                    }
                    d.g = findViewById(d.f);
                    if (d.g != null) {
                        view = d.g;
                        parent = d.g.getParent();
                        while (parent != this && parent != null) {
                            if (parent != childAt) {
                                if (parent instanceof View) {
                                    view = (View) parent;
                                }
                                parent = parent.getParent();
                            } else if (isInEditMode()) {
                                d.h = null;
                                d.g = null;
                            } else {
                                throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
                            }
                        }
                        d.h = view;
                    } else if (isInEditMode()) {
                        d.h = null;
                        d.g = null;
                    } else {
                        throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + getResources().getResourceName(d.f) + " to anchor view " + childAt);
                    }
                }
                this.g.add(childAt);
                i = i2 + 1;
            } else {
                int childCount2;
                boolean z;
                List list = this.g;
                Comparator comparator = this.d;
                if (list != null && list.size() >= 2) {
                    View[] viewArr = new View[list.size()];
                    list.toArray(viewArr);
                    for (i2 = 0; i2 < childCount2; i2++) {
                        i = i2;
                        for (int i3 = i2 + 1; i3 < childCount2; i3++) {
                            if (comparator.compare(viewArr[i3], viewArr[i]) < 0) {
                                i = i3;
                            }
                        }
                        if (i2 != i) {
                            View view2 = viewArr[i];
                            viewArr[i] = viewArr[i2];
                            viewArr[i2] = view2;
                        }
                    }
                    list.clear();
                    for (Object add : viewArr) {
                        list.add(add);
                    }
                }
                int childCount3 = getChildCount();
                i = 0;
                while (true) {
                    i2 = i;
                    if (i2 >= childCount3) {
                        z = false;
                        break;
                    }
                    View childAt2 = getChildAt(i2);
                    d dVar = (d) childAt2.getLayoutParams();
                    if (dVar.g != null) {
                        obj = 1;
                    } else {
                        childCount2 = getChildCount();
                        for (childCount = 0; childCount < childCount2; childCount++) {
                            View childAt3 = getChildAt(childCount);
                            if (childAt3 != childAt2 && dVar.a(childAt3)) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                    }
                    if (obj != null) {
                        z = true;
                        break;
                    }
                    i = i2 + 1;
                }
                if (z != this.t) {
                    if (z) {
                        if (this.n) {
                            if (this.s == null) {
                                this.s = new e(this);
                            }
                            getViewTreeObserver().addOnPreDrawListener(this.s);
                        }
                        this.t = true;
                    } else {
                        if (this.n && this.s != null) {
                            getViewTreeObserver().removeOnPreDrawListener(this.s);
                        }
                        this.t = false;
                    }
                }
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                int paddingRight = getPaddingRight();
                int paddingBottom = getPaddingBottom();
                int layoutDirection = ViewCompat.f(this);
                boolean isRtl = layoutDirection == 1;
                int widthMode = MeasureSpec.getMode(widthMeasureSpec);
                int widthSize = MeasureSpec.getSize(widthMeasureSpec);
                int heightMode = MeasureSpec.getMode(heightMeasureSpec);
                int heightSize = MeasureSpec.getSize(heightMeasureSpec);
                int widthPadding = paddingLeft + paddingRight;
                int heightPadding = paddingTop + paddingBottom;
                int widthUsed = getSuggestedMinimumWidth();
                int heightUsed = getSuggestedMinimumHeight();
                int childState = 0;
                boolean applyInsets = this.u != null && ViewCompat.u(this);
                int childCount4 = this.g.size();
                for (int i4 = 0; i4 < childCount4; i4++) {
                    View child = (View) this.g.get(i4);
                    d lp = (d) child.getLayoutParams();
                    int keylineWidthUsed = 0;
                    if (lp.e >= 0 && widthMode != 0) {
                        int keylinePos = a(lp.e);
                        int keylineGravity = android.support.v4.view.d.a(c(lp.c), layoutDirection) & 7;
                        if ((keylineGravity == 3 && !isRtl) || (keylineGravity == 5 && isRtl)) {
                            keylineWidthUsed = Math.max(0, (widthSize - paddingRight) - keylinePos);
                        } else if ((keylineGravity == 5 && !isRtl) || (keylineGravity == 3 && isRtl)) {
                            keylineWidthUsed = Math.max(0, keylinePos - paddingLeft);
                        }
                    }
                    int childWidthMeasureSpec = widthMeasureSpec;
                    int childHeightMeasureSpec = heightMeasureSpec;
                    if (applyInsets && !ViewCompat.u(child)) {
                        int vertInsets = this.u.b() + this.u.d();
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize - (this.u.a() + this.u.c()), widthMode);
                        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize - vertInsets, heightMode);
                    }
                    b b = lp.a;
                    if (b == null || !b.a(this, child, childWidthMeasureSpec, keylineWidthUsed, childHeightMeasureSpec, 0)) {
                        a(child, childWidthMeasureSpec, keylineWidthUsed, childHeightMeasureSpec, 0);
                    }
                    widthUsed = Math.max(widthUsed, ((child.getMeasuredWidth() + widthPadding) + lp.leftMargin) + lp.rightMargin);
                    heightUsed = Math.max(heightUsed, ((child.getMeasuredHeight() + heightPadding) + lp.topMargin) + lp.bottomMargin);
                    childState = ViewCompat.a(childState, ViewCompat.h(child));
                }
                setMeasuredDimension(ViewCompat.a(widthUsed, widthMeasureSpec, -16777216 & childState), ViewCompat.a(heightUsed, heightMeasureSpec, childState << 16));
                return;
            }
        }
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return new d();
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        if (layoutParams instanceof d) {
            return new d((d) layoutParams);
        }
        if (layoutParams instanceof MarginLayoutParams) {
            return new d((MarginLayoutParams) layoutParams);
        }
        return new d(layoutParams);
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new d(getContext(), attributeSet);
    }
}
