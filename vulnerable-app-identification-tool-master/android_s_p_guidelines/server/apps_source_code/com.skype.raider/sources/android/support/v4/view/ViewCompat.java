package android.support.v4.view;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.view.Display;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public final class ViewCompat {
    static final j a;

    @Retention(RetentionPolicy.SOURCE)
    private @interface AccessibilityLiveRegion {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusDirection {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRealDirection {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FocusRelativeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface ImportantForAccessibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface LayerType {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface LayoutDirectionMode {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NestedScrollType {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface OverScroll {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface ResolvedLayoutDirectionMode {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollAxis {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollIndicators {
    }

    static class j {
        static Field b;
        static boolean c = false;
        private static Field d;
        private static boolean e;
        private static Field f;
        private static boolean g;
        private static WeakHashMap<View, String> h;
        private static Method i;
        WeakHashMap<View, s> a = null;

        j() {
        }

        public static void a(View v, @Nullable a delegate) {
            AccessibilityDelegate accessibilityDelegate;
            if (delegate == null) {
                accessibilityDelegate = null;
            } else {
                accessibilityDelegate = delegate.b;
            }
            v.setAccessibilityDelegate(accessibilityDelegate);
        }

        public static boolean B(View v) {
            if (c) {
                return false;
            }
            if (b == null) {
                try {
                    Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                    b = declaredField;
                    declaredField.setAccessible(true);
                } catch (Throwable th) {
                    c = true;
                    return false;
                }
            }
            try {
                if (b.get(v) != null) {
                    return true;
                }
                return false;
            } catch (Throwable th2) {
                c = true;
                return false;
            }
        }

        public boolean b(View view) {
            return false;
        }

        public void c(View view) {
            view.postInvalidate();
        }

        public void a(View view, int left, int top, int right, int bottom) {
            view.postInvalidate(left, top, right, bottom);
        }

        public int d(View view) {
            return 0;
        }

        public void a(View view, int mode) {
        }

        public void a(View view, Paint paint) {
            view.setLayerType(view.getLayerType(), paint);
            view.invalidate();
        }

        public int k(View view) {
            return 0;
        }

        public ViewParent e(View view) {
            return view.getParent();
        }

        public void q(View view) {
        }

        public int l(View view) {
            return view.getPaddingLeft();
        }

        public int m(View view) {
            return view.getPaddingRight();
        }

        public void b(View view, int start, int top, int end, int bottom) {
            view.setPadding(start, top, end, bottom);
        }

        public boolean j(View view) {
            return true;
        }

        public int f(View view) {
            if (!e) {
                try {
                    Field declaredField = View.class.getDeclaredField("mMinWidth");
                    d = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                e = true;
            }
            if (d != null) {
                try {
                    return ((Integer) d.get(view)).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }

        public int g(View view) {
            if (!g) {
                try {
                    Field declaredField = View.class.getDeclaredField("mMinHeight");
                    f = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e) {
                }
                g = true;
            }
            if (f != null) {
                try {
                    return ((Integer) f.get(view)).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }

        public String t(View view) {
            if (h == null) {
                return null;
            }
            return (String) h.get(view);
        }

        public int n(View view) {
            return 0;
        }

        public void h(View view) {
        }

        public void a(View view, float elevation) {
        }

        public float u(View view) {
            return 0.0f;
        }

        public float v(View view) {
            return 0.0f;
        }

        public static void a(ViewGroup viewGroup) {
            if (i == null) {
                try {
                    i = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e) {
                }
                i.setAccessible(true);
            }
            try {
                i.invoke(viewGroup, new Object[]{Boolean.valueOf(true)});
            } catch (IllegalAccessException e2) {
            } catch (IllegalArgumentException e3) {
            } catch (InvocationTargetException e4) {
            }
        }

        public boolean i(View view) {
            return false;
        }

        public void a(View view, m listener) {
        }

        public w a(View v, w insets) {
            return insets;
        }

        public w b(View v, w insets) {
            return insets;
        }

        public boolean o(View view) {
            return false;
        }

        public boolean w(View view) {
            if (view instanceof g) {
                return ((g) view).isNestedScrollingEnabled();
            }
            return false;
        }

        public void a(View view, Drawable background) {
            view.setBackgroundDrawable(background);
        }

        public ColorStateList y(View view) {
            return view instanceof o ? ((o) view).a() : null;
        }

        public void a(View view, ColorStateList tintList) {
            if (view instanceof o) {
                ((o) view).setSupportBackgroundTintList(tintList);
            }
        }

        public void a(View view, Mode mode) {
            if (view instanceof o) {
                ((o) view).setSupportBackgroundTintMode(mode);
            }
        }

        public Mode z(View view) {
            return view instanceof o ? ((o) view).d() : null;
        }

        public void x(View view) {
            if (view instanceof g) {
                ((g) view).stopNestedScroll();
            }
        }

        public boolean r(View view) {
            return view.getWidth() > 0 && view.getHeight() > 0;
        }

        public float A(View view) {
            return v(view) + u(view);
        }

        public boolean s(View view) {
            return view.getWindowToken() != null;
        }

        public boolean a(View view) {
            return false;
        }

        public void d(View view, int indicators) {
        }

        public void b(View view, int offset) {
            view.offsetLeftAndRight(offset);
            if (view.getVisibility() == 0) {
                C(view);
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    C((View) parent);
                }
            }
        }

        public void c(View view, int offset) {
            view.offsetTopAndBottom(offset);
            if (view.getVisibility() == 0) {
                C(view);
                ViewParent parent = view.getParent();
                if (parent instanceof View) {
                    C((View) parent);
                }
            }
        }

        private static void C(View view) {
            float y = view.getTranslationY();
            view.setTranslationY(1.0f + y);
            view.setTranslationY(y);
        }

        public Display p(View view) {
            if (s(view)) {
                return ((WindowManager) view.getContext().getSystemService("window")).getDefaultDisplay();
            }
            return null;
        }

        public void a(View view, Runnable action) {
            view.postDelayed(action, ValueAnimator.getFrameDelay());
        }

        public void a(View view, Runnable action, long delayMillis) {
            view.postDelayed(action, ValueAnimator.getFrameDelay() + delayMillis);
        }
    }

    @RequiresApi(15)
    static class a extends j {
        a() {
        }

        public final boolean a(View view) {
            return view.hasOnClickListeners();
        }
    }

    @RequiresApi(16)
    static class b extends a {
        b() {
        }

        public final boolean b(View view) {
            return view.hasTransientState();
        }

        public final void c(View view) {
            view.postInvalidateOnAnimation();
        }

        public final void a(View view, int left, int top, int right, int bottom) {
            view.postInvalidateOnAnimation(left, top, right, bottom);
        }

        public final void a(View view, Runnable action) {
            view.postOnAnimation(action);
        }

        public final void a(View view, Runnable action, long delayMillis) {
            view.postOnAnimationDelayed(action, delayMillis);
        }

        public final int d(View view) {
            return view.getImportantForAccessibility();
        }

        public void a(View view, int mode) {
            if (mode == 4) {
                mode = 2;
            }
            view.setImportantForAccessibility(mode);
        }

        public final ViewParent e(View view) {
            return view.getParentForAccessibility();
        }

        public final int f(View view) {
            return view.getMinimumWidth();
        }

        public final int g(View view) {
            return view.getMinimumHeight();
        }

        public void h(View view) {
            view.requestFitSystemWindows();
        }

        public final boolean i(View view) {
            return view.getFitsSystemWindows();
        }

        public final boolean j(View view) {
            return view.hasOverlappingRendering();
        }

        public final void a(View view, Drawable background) {
            view.setBackground(background);
        }
    }

    @RequiresApi(17)
    static class c extends b {
        c() {
        }

        public final void a(View view, Paint paint) {
            view.setLayerPaint(paint);
        }

        public final int k(View view) {
            return view.getLayoutDirection();
        }

        public final int l(View view) {
            return view.getPaddingStart();
        }

        public final int m(View view) {
            return view.getPaddingEnd();
        }

        public final void b(View view, int start, int top, int end, int bottom) {
            view.setPaddingRelative(start, top, end, bottom);
        }

        public final int n(View view) {
            return view.getWindowSystemUiVisibility();
        }

        public final boolean o(View view) {
            return view.isPaddingRelative();
        }

        public final Display p(View view) {
            return view.getDisplay();
        }
    }

    @RequiresApi(18)
    static class d extends c {
        d() {
        }
    }

    @RequiresApi(19)
    static class e extends d {
        e() {
        }

        public final void q(View view) {
            view.setAccessibilityLiveRegion(1);
        }

        public final void a(View view, int mode) {
            view.setImportantForAccessibility(mode);
        }

        public final boolean r(View view) {
            return view.isLaidOut();
        }

        public final boolean s(View view) {
            return view.isAttachedToWindow();
        }
    }

    @RequiresApi(21)
    static class f extends e {
        private static ThreadLocal<Rect> d;

        f() {
        }

        public final String t(View view) {
            return view.getTransitionName();
        }

        public final void h(View view) {
            view.requestApplyInsets();
        }

        public final void a(View view, float elevation) {
            view.setElevation(elevation);
        }

        public final float u(View view) {
            return view.getElevation();
        }

        public final float v(View view) {
            return view.getTranslationZ();
        }

        public final void a(View view, final m listener) {
            if (listener == null) {
                view.setOnApplyWindowInsetsListener(null);
            } else {
                view.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener(this) {
                    final /* synthetic */ f b;

                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
                        return (WindowInsets) w.a(listener.a(view, w.a((Object) insets)));
                    }
                });
            }
        }

        public final boolean w(View view) {
            return view.isNestedScrollingEnabled();
        }

        public final void x(View view) {
            view.stopNestedScroll();
        }

        public final ColorStateList y(View view) {
            return view.getBackgroundTintList();
        }

        public final void a(View view, ColorStateList tintList) {
            view.setBackgroundTintList(tintList);
            if (VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean hasTint = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && hasTint) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        }

        public final void a(View view, Mode mode) {
            view.setBackgroundTintMode(mode);
            if (VERSION.SDK_INT == 21) {
                Drawable background = view.getBackground();
                boolean hasTint = (view.getBackgroundTintList() == null || view.getBackgroundTintMode() == null) ? false : true;
                if (background != null && hasTint) {
                    if (background.isStateful()) {
                        background.setState(view.getDrawableState());
                    }
                    view.setBackground(background);
                }
            }
        }

        public final Mode z(View view) {
            return view.getBackgroundTintMode();
        }

        public final w a(View v, w insets) {
            Object unwrapped = (WindowInsets) w.a(insets);
            WindowInsets result = v.onApplyWindowInsets(unwrapped);
            if (result != unwrapped) {
                unwrapped = new WindowInsets(result);
            }
            return w.a(unwrapped);
        }

        public final w b(View v, w insets) {
            Object unwrapped = (WindowInsets) w.a(insets);
            WindowInsets result = v.dispatchApplyWindowInsets(unwrapped);
            if (result != unwrapped) {
                unwrapped = new WindowInsets(result);
            }
            return w.a(unwrapped);
        }

        public final float A(View view) {
            return view.getZ();
        }

        public void b(View view, int offset) {
            Rect parentRect = a();
            boolean needInvalidateWorkaround = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View p = (View) parent;
                parentRect.set(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
                needInvalidateWorkaround = !parentRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            super.b(view, offset);
            if (needInvalidateWorkaround && parentRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(parentRect);
            }
        }

        public void c(View view, int offset) {
            Rect parentRect = a();
            boolean needInvalidateWorkaround = false;
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                View p = (View) parent;
                parentRect.set(p.getLeft(), p.getTop(), p.getRight(), p.getBottom());
                needInvalidateWorkaround = !parentRect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
            super.c(view, offset);
            if (needInvalidateWorkaround && parentRect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                ((View) parent).invalidate(parentRect);
            }
        }

        private static Rect a() {
            if (d == null) {
                d = new ThreadLocal();
            }
            Rect rect = (Rect) d.get();
            if (rect == null) {
                rect = new Rect();
                d.set(rect);
            }
            rect.setEmpty();
            return rect;
        }
    }

    @RequiresApi(23)
    static class g extends f {
        g() {
        }

        public final void d(View view, int indicators) {
            view.setScrollIndicators(indicators, 3);
        }

        public final void b(View view, int offset) {
            view.offsetLeftAndRight(offset);
        }

        public final void c(View view, int offset) {
            view.offsetTopAndBottom(offset);
        }
    }

    @RequiresApi(24)
    static class h extends g {
        h() {
        }
    }

    @RequiresApi(26)
    static class i extends h {
        i() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            a = new i();
        } else if (VERSION.SDK_INT >= 24) {
            a = new h();
        } else if (VERSION.SDK_INT >= 23) {
            a = new g();
        } else if (VERSION.SDK_INT >= 21) {
            a = new f();
        } else if (VERSION.SDK_INT >= 19) {
            a = new e();
        } else if (VERSION.SDK_INT >= 18) {
            a = new d();
        } else if (VERSION.SDK_INT >= 17) {
            a = new c();
        } else if (VERSION.SDK_INT >= 16) {
            a = new b();
        } else if (VERSION.SDK_INT >= 15) {
            a = new a();
        } else {
            a = new j();
        }
    }

    @Deprecated
    public static boolean a(View view) {
        return view.canScrollVertically(-1);
    }

    public static void a(View v, a delegate) {
        j.a(v, delegate);
    }

    public static boolean b(View v) {
        return j.B(v);
    }

    public static boolean c(View view) {
        return a.b(view);
    }

    public static void d(View view) {
        a.c(view);
    }

    public static void a(View view, int left, int top, int right, int bottom) {
        a.a(view, left, top, right, bottom);
    }

    public static void a(View view, Runnable action) {
        a.a(view, action);
    }

    public static void a(View view, Runnable action, long delayMillis) {
        a.a(view, action, delayMillis);
    }

    public static int e(View view) {
        return a.d(view);
    }

    public static void a(View view, int mode) {
        a.a(view, mode);
    }

    public static void a(View view, Paint paint) {
        a.a(view, paint);
    }

    public static int f(View view) {
        return a.k(view);
    }

    public static ViewParent g(View view) {
        return a.e(view);
    }

    @Deprecated
    public static int a(int size, int measureSpec, int childMeasuredState) {
        return View.resolveSizeAndState(size, measureSpec, childMeasuredState);
    }

    @Deprecated
    public static int h(View view) {
        return view.getMeasuredState();
    }

    @Deprecated
    public static int a(int curState, int newState) {
        return View.combineMeasuredStates(curState, newState);
    }

    public static void i(View view) {
        a.q(view);
    }

    public static int j(View view) {
        return a.l(view);
    }

    public static int k(View view) {
        return a.m(view);
    }

    public static void b(View view, int start, int top, int end, int bottom) {
        a.b(view, start, top, end, bottom);
    }

    @Deprecated
    public static float l(View view) {
        return view.getTranslationX();
    }

    @Deprecated
    public static float m(View view) {
        return view.getTranslationY();
    }

    public static int n(View view) {
        return a.f(view);
    }

    public static int o(View view) {
        return a.g(view);
    }

    public static s p(View view) {
        j jVar = a;
        if (jVar.a == null) {
            jVar.a = new WeakHashMap();
        }
        s sVar = (s) jVar.a.get(view);
        if (sVar != null) {
            return sVar;
        }
        sVar = new s(view);
        jVar.a.put(view, sVar);
        return sVar;
    }

    @Deprecated
    public static void a(View view, float value) {
        view.setTranslationY(value);
    }

    @Deprecated
    public static void b(View view, @FloatRange(from = 0.0d, to = 1.0d) float value) {
        view.setAlpha(value);
    }

    public static void c(View view, float elevation) {
        a.a(view, elevation);
    }

    public static float q(View view) {
        return a.u(view);
    }

    public static String r(View view) {
        return a.t(view);
    }

    public static int s(View view) {
        return a.n(view);
    }

    public static void t(View view) {
        a.h(view);
    }

    public static void a(ViewGroup viewGroup) {
        j.a(viewGroup);
    }

    public static boolean u(View v) {
        return a.i(v);
    }

    @Deprecated
    public static void a(View view, boolean fitSystemWindows) {
        view.setFitsSystemWindows(fitSystemWindows);
    }

    public static void a(View v, m listener) {
        a.a(v, listener);
    }

    public static w a(View view, w insets) {
        return a.a(view, insets);
    }

    public static w b(View view, w insets) {
        return a.b(view, insets);
    }

    public static boolean v(View view) {
        return a.j(view);
    }

    public static boolean w(View view) {
        return a.o(view);
    }

    public static void a(View view, Drawable background) {
        a.a(view, background);
    }

    public static ColorStateList x(View view) {
        return a.y(view);
    }

    public static void a(View view, ColorStateList tintList) {
        a.a(view, tintList);
    }

    public static Mode y(View view) {
        return a.z(view);
    }

    public static void a(View view, Mode mode) {
        a.a(view, mode);
    }

    public static boolean z(@NonNull View view) {
        return a.w(view);
    }

    public static void A(@NonNull View view) {
        a.x(view);
    }

    public static boolean B(View view) {
        return a.r(view);
    }

    public static float C(View view) {
        return a.A(view);
    }

    public static void b(View view, int offset) {
        a.c(view, offset);
    }

    public static void c(View view, int offset) {
        a.b(view, offset);
    }

    public static boolean D(View view) {
        return a.s(view);
    }

    public static boolean E(View view) {
        return a.a(view);
    }

    public static void d(@NonNull View view, int indicators) {
        a.d(view, indicators);
    }

    public static Display F(@NonNull View view) {
        return a.p(view);
    }
}
