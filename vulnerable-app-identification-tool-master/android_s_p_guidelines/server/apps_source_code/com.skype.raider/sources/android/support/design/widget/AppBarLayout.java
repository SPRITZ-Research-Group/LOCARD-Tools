package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.design.a.i;
import android.support.design.a.j;
import android.support.design.widget.CoordinatorLayout.DefaultBehavior;
import android.support.design.widget.CoordinatorLayout.d;
import android.support.v4.os.b;
import android.support.v4.os.c;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.m;
import android.support.v4.view.w;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@DefaultBehavior(Behavior.class)
public class AppBarLayout extends LinearLayout {
    boolean a;
    private int b;
    private int c;
    private int d;
    private float e;
    private int f;
    private w g;
    private final List<a> h;

    public static class Behavior extends k<AppBarLayout> {
        private int a;
        private boolean b;
        private boolean c;
        private s d;
        private int e = -1;
        private boolean f;
        private float g;
        private WeakReference<View> h;
        private a i;

        protected static class SavedState extends BaseSavedState {
            public static final Creator<SavedState> CREATOR = b.a(new c<SavedState>() {
                public final /* bridge */ /* synthetic */ Object[] a(int i) {
                    return new SavedState[i];
                }

                public final /* synthetic */ Object a(Parcel parcel, ClassLoader classLoader) {
                    return new SavedState(parcel);
                }
            });
            int a;
            float b;
            boolean c;

            public SavedState(Parcel source) {
                super(source);
                this.a = source.readInt();
                this.b = source.readFloat();
                this.c = source.readByte() != (byte) 0;
            }

            public SavedState(Parcelable superState) {
                super(superState);
            }

            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeInt(this.a);
                dest.writeFloat(this.b);
                dest.writeByte((byte) (this.c ? 1 : 0));
            }
        }

        public static abstract class a {
            public abstract boolean a();
        }

        final /* synthetic */ int a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            int i4 = 0;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int a = a();
            if (i2 != 0 && a >= i2 && a <= i3) {
                int a2 = m.a(i, i2, i3);
                if (a != a2) {
                    int height;
                    if (appBarLayout.a) {
                        int abs = Math.abs(a2);
                        int childCount = appBarLayout.getChildCount();
                        int i5 = 0;
                        while (i5 < childCount) {
                            View childAt = appBarLayout.getChildAt(i5);
                            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                            Interpolator interpolator = layoutParams.b;
                            if (abs < childAt.getTop() || abs > childAt.getBottom()) {
                                i5++;
                            } else {
                                if (interpolator != null) {
                                    i5 = layoutParams.a;
                                    if ((i5 & 1) != 0) {
                                        height = (layoutParams.bottomMargin + (childAt.getHeight() + layoutParams.topMargin)) + 0;
                                        if ((i5 & 2) != 0) {
                                            height -= ViewCompat.o(childAt);
                                        }
                                    } else {
                                        height = 0;
                                    }
                                    if (ViewCompat.u(childAt)) {
                                        height -= appBarLayout.e();
                                    }
                                    if (height > 0) {
                                        height = Math.round(interpolator.getInterpolation(((float) (abs - childAt.getTop())) / ((float) height)) * ((float) height));
                                        height = (height + childAt.getTop()) * Integer.signum(a2);
                                    }
                                }
                                height = a2;
                            }
                        }
                        height = a2;
                    } else {
                        height = a2;
                    }
                    boolean a3 = super.a(height);
                    i4 = a - a2;
                    this.a = a2 - height;
                    if (!a3 && appBarLayout.a) {
                        coordinatorLayout.b((View) appBarLayout);
                    }
                    a(appBarLayout);
                }
            }
            return i4;
        }

        public final /* synthetic */ Parcelable a(CoordinatorLayout coordinatorLayout, View view) {
            boolean z = false;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            Parcelable a = super.a(coordinatorLayout, appBarLayout);
            int c = super.c();
            int childCount = appBarLayout.getChildCount();
            int i = 0;
            while (i < childCount) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + c;
                if (childAt.getTop() + c > 0 || bottom < 0) {
                    i++;
                } else {
                    SavedState savedState = new SavedState(a);
                    savedState.a = i;
                    if (bottom == ViewCompat.o(childAt)) {
                        z = true;
                    }
                    savedState.c = z;
                    savedState.b = ((float) bottom) / ((float) childAt.getHeight());
                    return savedState;
                }
            }
            return a;
        }

        public final /* synthetic */ void a(CoordinatorLayout coordinatorLayout, View view, int i, int[] iArr) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i != 0 && !this.b) {
                int i2;
                int b;
                if (i < 0) {
                    i2 = -appBarLayout.a();
                    b = i2 + AppBarLayout.b(appBarLayout);
                } else {
                    i2 = -appBarLayout.a();
                    b = 0;
                }
                iArr[1] = b(coordinatorLayout, appBarLayout, i, i2, b);
            }
        }

        public final /* synthetic */ void a(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof SavedState) {
                SavedState savedState = (SavedState) parcelable;
                super.a(coordinatorLayout, (View) appBarLayout, savedState.getSuperState());
                this.e = savedState.a;
                this.g = savedState.b;
                this.f = savedState.c;
                return;
            }
            super.a(coordinatorLayout, (View) appBarLayout, parcelable);
            this.e = -1;
        }

        public final /* synthetic */ void a(CoordinatorLayout coordinatorLayout, View view, View view2) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (!this.c) {
                int i;
                View view3;
                int a = a();
                int childCount = appBarLayout.getChildCount();
                for (i = 0; i < childCount; i++) {
                    View childAt = appBarLayout.getChildAt(i);
                    if (childAt.getTop() <= (-a) && childAt.getBottom() >= (-a)) {
                        view3 = childAt;
                        break;
                    }
                }
                view3 = null;
                if (view3 != null) {
                    LayoutParams layoutParams = (LayoutParams) view3.getLayoutParams();
                    if ((layoutParams.a & 17) == 17) {
                        int o;
                        childCount = -view3.getTop();
                        i = -view3.getBottom();
                        if ((layoutParams.a & 2) == 2) {
                            o = ViewCompat.o(view3) + i;
                        } else {
                            o = i;
                        }
                        if (a >= (o + childCount) / 2) {
                            o = childCount;
                        }
                        a(coordinatorLayout, appBarLayout, m.a(o, -appBarLayout.a(), 0));
                    }
                }
            }
            this.b = false;
            this.c = false;
            this.h = new WeakReference(view2);
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, float f, boolean z) {
            boolean z2 = true;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (z) {
                int b;
                if (f < 0.0f) {
                    b = (-appBarLayout.a()) + AppBarLayout.b(appBarLayout);
                    if (a() < b) {
                        a(coordinatorLayout, appBarLayout, b);
                    }
                } else {
                    b = -appBarLayout.a();
                    if (a() > b) {
                        a(coordinatorLayout, appBarLayout, b);
                    }
                }
                z2 = false;
            } else {
                z2 = a(coordinatorLayout, appBarLayout, -appBarLayout.a(), -f);
            }
            this.c = z2;
            return z2;
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean a = super.a(coordinatorLayout, appBarLayout, i);
            int e = appBarLayout.f;
            int i2;
            int i3;
            if (e != 0) {
                i2 = (e & 4) != 0 ? 1 : 0;
                if ((e & 2) != 0) {
                    i3 = -appBarLayout.a();
                    if (i2 != 0) {
                        a(coordinatorLayout, appBarLayout, i3);
                    } else {
                        c(coordinatorLayout, appBarLayout, i3);
                    }
                } else if ((e & 1) != 0) {
                    if (i2 != 0) {
                        a(coordinatorLayout, appBarLayout, 0);
                    } else {
                        c(coordinatorLayout, appBarLayout, 0);
                    }
                }
            } else if (this.e >= 0) {
                View childAt = appBarLayout.getChildAt(this.e);
                i3 = -childAt.getBottom();
                if (this.f) {
                    i2 = ViewCompat.o(childAt) + i3;
                } else {
                    i2 = Math.round(((float) childAt.getHeight()) * this.g) + i3;
                }
                super.a(i2);
            }
            appBarLayout.f = 0;
            this.e = -1;
            a(appBarLayout);
            return a;
        }

        public final /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z = (i & 2) != 0 && AppBarLayout.a(appBarLayout) && coordinatorLayout.getHeight() - view2.getHeight() <= appBarLayout.getHeight();
            if (z && this.d != null) {
                this.d.e();
            }
            this.h = null;
            return z;
        }

        final /* synthetic */ int b(View view) {
            return -AppBarLayout.d((AppBarLayout) view);
        }

        public final /* synthetic */ void b(CoordinatorLayout coordinatorLayout, View view, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i < 0) {
                b(coordinatorLayout, appBarLayout, i, -AppBarLayout.d(appBarLayout), 0);
                this.b = true;
                return;
            }
            this.b = false;
        }

        public final /* bridge */ /* synthetic */ int c() {
            return super.c();
        }

        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        private void a(final CoordinatorLayout coordinatorLayout, final AppBarLayout child, int offset) {
            int currentOffset = a();
            if (currentOffset != offset) {
                if (this.d == null) {
                    this.d = z.a();
                    this.d.a(a.c);
                    this.d.a(new c(this) {
                        final /* synthetic */ Behavior c;

                        public final void a(s animator) {
                            this.c.c(coordinatorLayout, child, animator.c());
                        }
                    });
                } else {
                    this.d.e();
                }
                this.d.a(Math.round((1000.0f * (((float) Math.abs(currentOffset - offset)) / coordinatorLayout.getResources().getDisplayMetrics().density)) / 300.0f));
                this.d.a(currentOffset, offset);
                this.d.a();
            } else if (this.d != null && this.d.b()) {
                this.d.e();
            }
        }

        private void a(AppBarLayout layout) {
            List<a> listeners = layout.h;
            int z = listeners.size();
            for (int i = 0; i < z; i++) {
                a listener = (a) listeners.get(i);
                if (listener != null) {
                    listener.a(layout, super.c());
                }
            }
        }

        final int a() {
            return super.c() + this.a;
        }

        final /* synthetic */ boolean b() {
            if (this.i != null) {
                return this.i.a();
            }
            if (this.h != null) {
                View view = (View) this.h.get();
                if (view == null || !view.isShown() || ViewCompat.a(view)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
        int a = 1;
        Interpolator b;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, j.AppBarLayout_LayoutParams);
            this.a = a.getInt(j.AppBarLayout_LayoutParams_layout_scrollFlags, 0);
            if (a.hasValue(j.AppBarLayout_LayoutParams_layout_scrollInterpolator)) {
                this.b = AnimationUtils.loadInterpolator(c, a.getResourceId(j.AppBarLayout_LayoutParams_layout_scrollInterpolator, 0));
            }
            a.recycle();
        }

        public LayoutParams() {
            super(-1, -2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(android.widget.LinearLayout.LayoutParams source) {
            super(source);
        }
    }

    public static class ScrollingViewBehavior extends l {
        private int a;

        public final /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return super.a(coordinatorLayout, view, i, i2, i3, i4);
        }

        public final /* bridge */ /* synthetic */ int c() {
            return super.c();
        }

        public ScrollingViewBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, j.ScrollingViewBehavior_Params);
            this.a = a.getDimensionPixelSize(j.ScrollingViewBehavior_Params_behavior_overlapTop, 0);
            a.recycle();
        }

        public final boolean a_(View dependency) {
            return dependency instanceof AppBarLayout;
        }

        public final boolean a(CoordinatorLayout parent, View child, int layoutDirection) {
            super.a(parent, child, layoutDirection);
            List<View> dependencies = parent.c(child);
            int i = 0;
            int z = dependencies.size();
            while (i < z && !c((View) dependencies.get(i))) {
                i++;
            }
            return true;
        }

        public final boolean b(CoordinatorLayout parent, View child, View dependency) {
            c(dependency);
            return false;
        }

        private boolean c(View dependency) {
            CoordinatorLayout.b behavior = ((d) dependency.getLayoutParams()).a;
            if (!(behavior instanceof Behavior)) {
                return false;
            }
            int a;
            int offset = ((Behavior) behavior).a();
            int height = dependency.getHeight() + offset;
            if (this.a != 0 && (dependency instanceof AppBarLayout)) {
                AppBarLayout appBarLayout = (AppBarLayout) dependency;
                a = appBarLayout.a();
                int b = AppBarLayout.b(appBarLayout);
                if (b == 0 || a + offset > b) {
                    a -= b;
                    if (a != 0) {
                        a = m.a(Math.round(((((float) offset) / ((float) a)) + 1.0f) * ((float) this.a)), 0, this.a);
                        super.a(height - a);
                        return true;
                    }
                }
                a = 0;
                super.a(height - a);
                return true;
            }
            a = this.a;
            super.a(height - a);
            return true;
        }

        final View a(List<View> views) {
            int z = views.size();
            for (int i = 0; i < z; i++) {
                View view = (View) views.get(i);
                if (view instanceof AppBarLayout) {
                    return view;
                }
            }
            return null;
        }

        final int b(View v) {
            if (v instanceof AppBarLayout) {
                return ((AppBarLayout) v).a();
            }
            return super.b(v);
        }
    }

    public interface a {
        void a(AppBarLayout appBarLayout, int i);
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public /* synthetic */ android.widget.LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return a(attributeSet);
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    static /* synthetic */ int d(AppBarLayout x0) {
        if (x0.d != -1) {
            return x0.d;
        }
        int i;
        int childCount = x0.getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = x0.getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + (layoutParams.topMargin + layoutParams.bottomMargin);
            i = layoutParams.a;
            if ((i & 1) == 0) {
                break;
            }
            i2 += measuredHeight;
            if ((i & 2) != 0) {
                i = i2 - (ViewCompat.o(childAt) + x0.e());
                break;
            }
        }
        i = i2;
        i = Math.max(0, i);
        x0.d = i;
        return i;
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public AppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.f = 0;
        setOrientation(1);
        r.a(context);
        TypedArray a = context.obtainStyledAttributes(attrs, j.AppBarLayout, 0, i.Widget_Design_AppBarLayout);
        this.e = (float) a.getDimensionPixelSize(j.AppBarLayout_elevation, 0);
        setBackgroundDrawable(a.getDrawable(j.AppBarLayout_android_background));
        if (a.hasValue(j.AppBarLayout_expanded)) {
            setExpanded(a.getBoolean(j.AppBarLayout_expanded, false));
        }
        a.recycle();
        z.a(this);
        this.h = new ArrayList();
        ViewCompat.c((View) this, this.e);
        ViewCompat.a((View) this, new m(this) {
            final /* synthetic */ AppBarLayout a;

            {
                this.a = r1;
            }

            public final w a(View v, w insets) {
                AppBarLayout.a(this.a, insets);
                return insets.f();
            }
        });
    }

    public final void a(a listener) {
        if (listener != null && !this.h.contains(listener)) {
            this.h.add(listener);
        }
    }

    public final void b(a listener) {
        if (listener != null) {
            this.h.remove(listener);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        d();
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        d();
        this.a = false;
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).b != null) {
                this.a = true;
                return;
            }
        }
    }

    private void d() {
        this.b = -1;
        this.c = -1;
        this.d = -1;
    }

    public void setOrientation(int orientation) {
        if (orientation != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(orientation);
    }

    public void setExpanded(boolean expanded) {
        setExpanded(expanded, ViewCompat.B(this));
    }

    public void setExpanded(boolean expanded, boolean animate) {
        this.f = (animate ? 4 : 0) | (expanded ? 1 : 2);
        requestLayout();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    private LayoutParams a(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    private static LayoutParams a(android.view.ViewGroup.LayoutParams p) {
        if (p instanceof android.widget.LinearLayout.LayoutParams) {
            return new LayoutParams((android.widget.LinearLayout.LayoutParams) p);
        }
        if (p instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    public final int a() {
        if (this.b != -1) {
            return this.b;
        }
        int range = 0;
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.a;
            if ((flags & 1) == 0) {
                break;
            }
            range += (lp.topMargin + childHeight) + lp.bottomMargin;
            if ((flags & 2) != 0) {
                range -= ViewCompat.o(child);
                break;
            }
        }
        int max = Math.max(0, range - e());
        this.b = max;
        return max;
    }

    final int b() {
        int topInset;
        if (this.g != null) {
            topInset = this.g.b();
        } else {
            topInset = 0;
        }
        int minHeight = ViewCompat.o(this);
        if (minHeight != 0) {
            return (minHeight * 2) + topInset;
        }
        int childCount = getChildCount();
        if (childCount > 0) {
            return (ViewCompat.o(getChildAt(childCount - 1)) * 2) + topInset;
        }
        return 0;
    }

    public void setTargetElevation(float elevation) {
        this.e = elevation;
    }

    public final float c() {
        return this.e;
    }

    private int e() {
        return this.g != null ? this.g.b() : 0;
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    static /* synthetic */ void a(AppBarLayout x0, w x1) {
        x0.b = -1;
        x0.g = x1;
        int i = 0;
        int childCount = x0.getChildCount();
        while (i < childCount) {
            x1 = ViewCompat.b(x0.getChildAt(i), x1);
            if (!x1.e()) {
                i++;
            } else {
                return;
            }
        }
    }

    static /* synthetic */ boolean a(AppBarLayout x0) {
        return x0.a() != 0;
    }

    static /* synthetic */ int b(AppBarLayout x0) {
        if (x0.c != -1) {
            return x0.c;
        }
        int i = 0;
        int childCount = x0.getChildCount() - 1;
        while (childCount >= 0) {
            int i2;
            View childAt = x0.getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i3 = layoutParams.a;
            if ((i3 & 5) != 5) {
                if (i > 0) {
                    break;
                }
                i2 = i;
            } else {
                i2 = (layoutParams.bottomMargin + layoutParams.topMargin) + i;
                if ((i3 & 8) != 0) {
                    i2 += ViewCompat.o(childAt);
                } else {
                    i2 += measuredHeight;
                }
            }
            childCount--;
            i = i2;
        }
        x0.c = i;
        return i;
    }
}
