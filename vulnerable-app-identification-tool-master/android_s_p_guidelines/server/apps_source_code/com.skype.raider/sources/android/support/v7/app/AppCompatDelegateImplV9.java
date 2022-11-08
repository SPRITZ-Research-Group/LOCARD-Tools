package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.r;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.m;
import android.support.v4.view.s;
import android.support.v4.view.u;
import android.support.v4.view.w;
import android.support.v4.widget.i;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.menu.e;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.o;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.ViewStubCompat;
import android.support.v7.widget.av;
import android.support.v7.widget.ax;
import android.support.v7.widget.h;
import android.support.v7.widget.q;
import android.support.v7.widget.v;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;

@RequiresApi(14)
class AppCompatDelegateImplV9 extends b implements android.support.v7.view.menu.g.a, Factory2 {
    private static final boolean t = (VERSION.SDK_INT < 21);
    private View A;
    private boolean B;
    private boolean C;
    private boolean D;
    private PanelFeatureState[] E;
    private PanelFeatureState F;
    private boolean G;
    private final Runnable H = new Runnable(this) {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        {
            this.a = this$0;
        }

        public final void run() {
            if ((this.a.s & 1) != 0) {
                this.a.h(0);
            }
            if ((this.a.s & 4096) != 0) {
                this.a.h(108);
            }
            this.a.r = false;
            this.a.s = 0;
        }
    };
    private boolean I;
    private Rect J;
    private Rect K;
    private g L;
    android.support.v7.view.b m;
    ActionBarContextView n;
    PopupWindow o;
    Runnable p;
    s q = null;
    boolean r;
    int s;
    private q u;
    private a v;
    private d w;
    private boolean x;
    private ViewGroup y;
    private TextView z;

    protected static final class PanelFeatureState {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        ViewGroup g;
        View h;
        View i;
        g j;
        e k;
        Context l;
        boolean m;
        boolean n;
        boolean o;
        public boolean p;
        boolean q = false;
        boolean r;
        Bundle s;

        private static class SavedState implements Parcelable {
            public static final Creator<SavedState> CREATOR = new ClassLoaderCreator<SavedState>() {
                public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                    return SavedState.a(parcel, classLoader);
                }

                public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new SavedState[i];
                }

                public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return SavedState.a(parcel, null);
                }
            };
            int a;
            boolean b;
            Bundle c;

            SavedState() {
            }

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.a);
                dest.writeInt(this.b ? 1 : 0);
                if (this.b) {
                    dest.writeBundle(this.c);
                }
            }

            static SavedState a(Parcel source, ClassLoader loader) {
                boolean z = true;
                SavedState savedState = new SavedState();
                savedState.a = source.readInt();
                if (source.readInt() != 1) {
                    z = false;
                }
                savedState.b = z;
                if (savedState.b) {
                    savedState.c = source.readBundle(loader);
                }
                return savedState;
            }
        }

        PanelFeatureState(int featureId) {
            this.a = featureId;
        }

        final void a(g menu) {
            if (menu != this.j) {
                if (this.j != null) {
                    this.j.b(this.k);
                }
                this.j = menu;
                if (menu != null && this.k != null) {
                    menu.a(this.k);
                }
            }
        }
    }

    private final class a implements android.support.v7.view.menu.n.a {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        a(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
            this.a = appCompatDelegateImplV9;
        }

        public final boolean a(g subMenu) {
            Callback cb = this.a.b.getCallback();
            if (cb != null) {
                cb.onMenuOpened(108, subMenu);
            }
            return true;
        }

        public final void a(g menu, boolean allMenusAreClosing) {
            this.a.b(menu);
        }
    }

    class b implements android.support.v7.view.b.a {
        final /* synthetic */ AppCompatDelegateImplV9 a;
        private android.support.v7.view.b.a b;

        public b(AppCompatDelegateImplV9 this$0, android.support.v7.view.b.a wrapped) {
            this.a = this$0;
            this.b = wrapped;
        }

        public final boolean a(android.support.v7.view.b mode, Menu menu) {
            return this.b.a(mode, menu);
        }

        public final boolean b(android.support.v7.view.b mode, Menu menu) {
            return this.b.b(mode, menu);
        }

        public final boolean a(android.support.v7.view.b mode, MenuItem item) {
            return this.b.a(mode, item);
        }

        public final void a(android.support.v7.view.b mode) {
            this.b.a(mode);
            if (this.a.o != null) {
                this.a.b.getDecorView().removeCallbacks(this.a.p);
            }
            if (this.a.n != null) {
                this.a.s();
                this.a.q = ViewCompat.p(this.a.n).a(0.0f);
                this.a.q.a(new u(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = this$1;
                    }

                    public final void b(View view) {
                        this.a.a.n.setVisibility(8);
                        if (this.a.a.o != null) {
                            this.a.a.o.dismiss();
                        } else if (this.a.a.n.getParent() instanceof View) {
                            ViewCompat.t((View) this.a.a.n.getParent());
                        }
                        this.a.a.n.removeAllViews();
                        this.a.a.q.a(null);
                        this.a.a.q = null;
                    }
                });
            }
            if (this.a.e != null) {
                a aVar = this.a.e;
                android.support.v7.view.b bVar = this.a.m;
            }
            this.a.m = null;
        }
    }

    private class c extends ContentFrameLayout {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        public c(AppCompatDelegateImplV9 appCompatDelegateImplV9, Context context) {
            this.a = appCompatDelegateImplV9;
            super(context);
        }

        public final boolean dispatchKeyEvent(KeyEvent event) {
            return this.a.a(event) || super.dispatchKeyEvent(event);
        }

        public final boolean onInterceptTouchEvent(MotionEvent event) {
            if (event.getAction() == 0) {
                boolean z;
                int x = (int) event.getX();
                int y = (int) event.getY();
                if (x < -5 || y < -5 || x > getWidth() + 5 || y > getHeight() + 5) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    this.a.t();
                    return true;
                }
            }
            return super.onInterceptTouchEvent(event);
        }

        public final void setBackgroundResource(int resid) {
            setBackgroundDrawable(android.support.v7.content.res.b.b(getContext(), resid));
        }
    }

    private final class d implements android.support.v7.view.menu.n.a {
        final /* synthetic */ AppCompatDelegateImplV9 a;

        d(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
            this.a = appCompatDelegateImplV9;
        }

        public final void a(g menu, boolean allMenusAreClosing) {
            Menu menu2;
            Menu parentMenu = menu2.o();
            boolean isSubMenu = parentMenu != menu2;
            AppCompatDelegateImplV9 appCompatDelegateImplV9 = this.a;
            if (isSubMenu) {
                menu2 = parentMenu;
            }
            PanelFeatureState panel = appCompatDelegateImplV9.a(menu2);
            if (panel == null) {
                return;
            }
            if (isSubMenu) {
                this.a.a(panel.a, panel, parentMenu);
                this.a.a(panel, true);
                return;
            }
            this.a.a(panel, allMenusAreClosing);
        }

        public final boolean a(g subMenu) {
            if (subMenu == null && this.a.h) {
                Callback cb = this.a.b.getCallback();
                if (!(cb == null || this.a.p())) {
                    cb.onMenuOpened(108, subMenu);
                }
            }
            return true;
        }
    }

    AppCompatDelegateImplV9(Context context, Window window, a callback) {
        super(context, window, callback);
    }

    public void a(Bundle savedInstanceState) {
        if ((this.c instanceof Activity) && r.b((Activity) this.c) != null) {
            ActionBar ab = this.f;
            if (ab == null) {
                this.I = true;
            } else {
                ab.a(true);
            }
        }
    }

    public final void c() {
        v();
    }

    public final void m() {
        v();
        if (this.h && this.f == null) {
            if (this.c instanceof Activity) {
                this.f = new l((Activity) this.c, this.i);
            } else if (this.c instanceof Dialog) {
                this.f = new l((Dialog) this.c);
            }
            if (this.f != null) {
                this.f.a(this.I);
            }
        }
    }

    @Nullable
    public final <T extends View> T a(@IdRes int id) {
        v();
        return this.b.findViewById(id);
    }

    public final void a(Configuration newConfig) {
        if (this.h && this.x) {
            ActionBar ab = a();
            if (ab != null) {
                ab.a(newConfig);
            }
        }
        h.a().a(this.a);
        j();
    }

    public void e() {
        ActionBar ab = a();
        if (ab != null) {
            ab.b(false);
        }
    }

    public final void f() {
        ActionBar ab = a();
        if (ab != null) {
            ab.b(true);
        }
    }

    public final void a(View v) {
        v();
        ViewGroup contentParent = (ViewGroup) this.y.findViewById(16908290);
        contentParent.removeAllViews();
        contentParent.addView(v);
        this.c.onContentChanged();
    }

    public final void b(int resId) {
        v();
        ViewGroup contentParent = (ViewGroup) this.y.findViewById(16908290);
        contentParent.removeAllViews();
        LayoutInflater.from(this.a).inflate(resId, contentParent);
        this.c.onContentChanged();
    }

    public final void a(View v, LayoutParams lp) {
        v();
        ViewGroup contentParent = (ViewGroup) this.y.findViewById(16908290);
        contentParent.removeAllViews();
        contentParent.addView(v, lp);
        this.c.onContentChanged();
    }

    public final void b(View v, LayoutParams lp) {
        v();
        ((ViewGroup) this.y.findViewById(16908290)).addView(v, lp);
        this.c.onContentChanged();
    }

    public void h() {
        if (this.r) {
            this.b.getDecorView().removeCallbacks(this.H);
        }
        super.h();
        if (this.f != null) {
            this.f.g();
        }
    }

    private void v() {
        if (!this.x) {
            TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(j.AppCompatTheme);
            if (obtainStyledAttributes.hasValue(j.AppCompatTheme_windowActionBar)) {
                View view;
                View view2;
                if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowNoTitle, false)) {
                    c(1);
                } else if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowActionBar, false)) {
                    c(108);
                }
                if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowActionBarOverlay, false)) {
                    c(109);
                }
                if (obtainStyledAttributes.getBoolean(j.AppCompatTheme_windowActionModeOverlay, false)) {
                    c(10);
                }
                this.k = obtainStyledAttributes.getBoolean(j.AppCompatTheme_android_windowIsFloating, false);
                obtainStyledAttributes.recycle();
                this.b.getDecorView();
                LayoutInflater from = LayoutInflater.from(this.a);
                View view3;
                if (this.l) {
                    if (this.j) {
                        view = (ViewGroup) from.inflate(android.support.v7.appcompat.a.g.abc_screen_simple_overlay_action_mode, null);
                    } else {
                        view = (ViewGroup) from.inflate(android.support.v7.appcompat.a.g.abc_screen_simple, null);
                    }
                    if (VERSION.SDK_INT >= 21) {
                        ViewCompat.a(view, new m(this) {
                            final /* synthetic */ AppCompatDelegateImplV9 a;

                            {
                                this.a = this$0;
                            }

                            public final w a(View v, w insets) {
                                int top = insets.b();
                                int newTop = this.a.i(top);
                                if (top != newTop) {
                                    insets = insets.a(insets.a(), newTop, insets.c(), insets.d());
                                }
                                return ViewCompat.a(v, insets);
                            }
                        });
                        view2 = view;
                    } else {
                        ((v) view).setOnFitSystemWindowsListener(new android.support.v7.widget.v.a(this) {
                            final /* synthetic */ AppCompatDelegateImplV9 a;

                            {
                                this.a = this$0;
                            }

                            public final void a(Rect insets) {
                                insets.top = this.a.i(insets.top);
                            }
                        });
                        view2 = view;
                    }
                } else if (this.k) {
                    view3 = (ViewGroup) from.inflate(android.support.v7.appcompat.a.g.abc_dialog_title_material, null);
                    this.i = false;
                    this.h = false;
                    view2 = view3;
                } else if (this.h) {
                    Context dVar;
                    TypedValue typedValue = new TypedValue();
                    this.a.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        dVar = new android.support.v7.view.d(this.a, typedValue.resourceId);
                    } else {
                        dVar = this.a;
                    }
                    view3 = (ViewGroup) LayoutInflater.from(dVar).inflate(android.support.v7.appcompat.a.g.abc_screen_toolbar, null);
                    this.u = (q) view3.findViewById(f.decor_content_parent);
                    this.u.setWindowCallback(this.b.getCallback());
                    if (this.i) {
                        this.u.a(109);
                    }
                    if (this.B) {
                        this.u.a(2);
                    }
                    if (this.C) {
                        this.u.a(5);
                    }
                    view2 = view3;
                } else {
                    view2 = null;
                }
                if (view2 == null) {
                    throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.h + ", windowActionBarOverlay: " + this.i + ", android:windowIsFloating: " + this.k + ", windowActionModeOverlay: " + this.j + ", windowNoTitle: " + this.l + " }");
                }
                if (this.u == null) {
                    this.z = (TextView) view2.findViewById(f.title);
                }
                ax.b(view2);
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) view2.findViewById(f.action_bar_activity_content);
                ViewGroup viewGroup = (ViewGroup) this.b.findViewById(16908290);
                if (viewGroup != null) {
                    while (viewGroup.getChildCount() > 0) {
                        View childAt = viewGroup.getChildAt(0);
                        viewGroup.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup instanceof FrameLayout) {
                        ((FrameLayout) viewGroup).setForeground(null);
                    }
                }
                this.b.setContentView(view2);
                contentFrameLayout.setAttachListener(new android.support.v7.widget.ContentFrameLayout.a(this) {
                    final /* synthetic */ AppCompatDelegateImplV9 a;

                    {
                        this.a = this$0;
                    }

                    public final void a() {
                        this.a.u();
                    }
                });
                this.y = view2;
                CharSequence title = q();
                if (!TextUtils.isEmpty(title)) {
                    b(title);
                }
                contentFrameLayout = (ContentFrameLayout) this.y.findViewById(16908290);
                view = this.b.getDecorView();
                contentFrameLayout.setDecorPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                TypedArray obtainStyledAttributes2 = this.a.obtainStyledAttributes(j.AppCompatTheme);
                obtainStyledAttributes2.getValue(j.AppCompatTheme_windowMinWidthMajor, contentFrameLayout.a());
                obtainStyledAttributes2.getValue(j.AppCompatTheme_windowMinWidthMinor, contentFrameLayout.b());
                if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedWidthMajor)) {
                    obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout.c());
                }
                if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedWidthMinor)) {
                    obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout.d());
                }
                if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedHeightMajor)) {
                    obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout.e());
                }
                if (obtainStyledAttributes2.hasValue(j.AppCompatTheme_windowFixedHeightMinor)) {
                    obtainStyledAttributes2.getValue(j.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout.f());
                }
                obtainStyledAttributes2.recycle();
                contentFrameLayout.requestLayout();
                this.x = true;
                PanelFeatureState st = g(0);
                if (!p()) {
                    if (st == null || st.j == null) {
                        f(108);
                        return;
                    }
                    return;
                }
                return;
            }
            obtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
    }

    public final boolean c(int featureId) {
        if (featureId == 8) {
            featureId = 108;
        } else if (featureId == 9) {
            featureId = 109;
        }
        if (this.l && featureId == 108) {
            return false;
        }
        if (this.h && featureId == 1) {
            this.h = false;
        }
        switch (featureId) {
            case 1:
                w();
                this.l = true;
                return true;
            case 2:
                w();
                this.B = true;
                return true;
            case 5:
                w();
                this.C = true;
                return true;
            case 10:
                w();
                this.j = true;
                return true;
            case 108:
                w();
                this.h = true;
                return true;
            case 109:
                w();
                this.i = true;
                return true;
            default:
                return this.b.requestFeature(featureId);
        }
    }

    final void b(CharSequence title) {
        if (this.u != null) {
            this.u.setWindowTitle(title);
        } else if (this.f != null) {
            this.f.a(title);
        } else if (this.z != null) {
            this.z.setText(title);
        }
    }

    final void d(int featureId) {
        if (featureId == 108) {
            ActionBar ab = a();
            if (ab != null) {
                ab.c(false);
            }
        } else if (featureId == 0) {
            PanelFeatureState st = g(featureId);
            if (st.o) {
                a(st, false);
            }
        }
    }

    final boolean e(int featureId) {
        if (featureId != 108) {
            return false;
        }
        ActionBar ab = a();
        if (ab == null) {
            return true;
        }
        ab.c(true);
        return true;
    }

    public final void a(g menu) {
        if (this.u == null || !this.u.c() || (ViewConfiguration.get(this.a).hasPermanentMenuKey() && !this.u.e())) {
            PanelFeatureState g = g(0);
            g.q = true;
            a(g, false);
            a(g, null);
            return;
        }
        Callback callback = this.b.getCallback();
        if (this.u.d()) {
            this.u.g();
            if (!p()) {
                callback.onPanelClosed(108, g(0).j);
            }
        } else if (callback != null && !p()) {
            if (this.r && (this.s & 1) != 0) {
                this.b.getDecorView().removeCallbacks(this.H);
                this.H.run();
            }
            PanelFeatureState g2 = g(0);
            if (g2.j != null && !g2.r && callback.onPreparePanel(0, g2.i, g2.j)) {
                callback.onMenuOpened(108, g2.j);
                this.u.f();
            }
        }
    }

    public final void g() {
        ActionBar ab = a();
        if (ab == null || !ab.e()) {
            f(0);
        }
    }

    final android.support.v7.view.b a(@NonNull android.support.v7.view.b.a callback) {
        s();
        if (this.m != null) {
            this.m.c();
        }
        if (this.n == null) {
            if (this.k) {
                Context actionBarContext;
                TypedValue outValue = new TypedValue();
                Theme baseTheme = this.a.getTheme();
                baseTheme.resolveAttribute(android.support.v7.appcompat.a.a.actionBarTheme, outValue, true);
                if (outValue.resourceId != 0) {
                    Theme actionBarTheme = this.a.getResources().newTheme();
                    actionBarTheme.setTo(baseTheme);
                    actionBarTheme.applyStyle(outValue.resourceId, true);
                    actionBarContext = new android.support.v7.view.d(this.a, 0);
                    actionBarContext.getTheme().setTo(actionBarTheme);
                } else {
                    actionBarContext = this.a;
                }
                this.n = new ActionBarContextView(actionBarContext);
                this.o = new PopupWindow(actionBarContext, null, android.support.v7.appcompat.a.a.actionModePopupWindowStyle);
                i.a(this.o, 2);
                this.o.setContentView(this.n);
                this.o.setWidth(-1);
                actionBarContext.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.actionBarSize, outValue, true);
                this.n.setContentHeight(TypedValue.complexToDimensionPixelSize(outValue.data, actionBarContext.getResources().getDisplayMetrics()));
                this.o.setHeight(-2);
                this.p = new Runnable(this) {
                    final /* synthetic */ AppCompatDelegateImplV9 a;

                    {
                        this.a = this$0;
                    }

                    public final void run() {
                        this.a.o.showAtLocation(this.a.n, 55, 0, 0);
                        this.a.s();
                        if (this.a.r()) {
                            this.a.n.setAlpha(0.0f);
                            this.a.q = ViewCompat.p(this.a.n).a(1.0f);
                            this.a.q.a(new u(this) {
                                final /* synthetic */ AnonymousClass5 a;

                                {
                                    this.a = this$1;
                                }

                                public final void a(View view) {
                                    this.a.a.n.setVisibility(0);
                                }

                                public final void b(View view) {
                                    this.a.a.n.setAlpha(1.0f);
                                    this.a.a.q.a(null);
                                    this.a.a.q = null;
                                }
                            });
                            return;
                        }
                        this.a.n.setAlpha(1.0f);
                        this.a.n.setVisibility(0);
                    }
                };
            } else {
                ViewStubCompat stub = (ViewStubCompat) this.y.findViewById(f.action_mode_bar_stub);
                if (stub != null) {
                    stub.setLayoutInflater(LayoutInflater.from(n()));
                    this.n = (ActionBarContextView) stub.a();
                }
            }
        }
        if (this.n != null) {
            boolean z;
            s();
            this.n.e();
            Context context = this.n.getContext();
            ActionBarContextView actionBarContextView = this.n;
            if (this.o == null) {
                z = true;
            } else {
                z = false;
            }
            android.support.v7.view.b mode = new android.support.v7.view.e(context, actionBarContextView, callback, z);
            if (callback.a(mode, mode.b())) {
                mode.d();
                this.n.a(mode);
                this.m = mode;
                if (r()) {
                    this.n.setAlpha(0.0f);
                    this.q = ViewCompat.p(this.n).a(1.0f);
                    this.q.a(new u(this) {
                        final /* synthetic */ AppCompatDelegateImplV9 a;

                        {
                            this.a = this$0;
                        }

                        public final void a(View view) {
                            this.a.n.setVisibility(0);
                            this.a.n.sendAccessibilityEvent(32);
                            if (this.a.n.getParent() instanceof View) {
                                ViewCompat.t((View) this.a.n.getParent());
                            }
                        }

                        public final void b(View view) {
                            this.a.n.setAlpha(1.0f);
                            this.a.q.a(null);
                            this.a.q = null;
                        }
                    });
                } else {
                    this.n.setAlpha(1.0f);
                    this.n.setVisibility(0);
                    this.n.sendAccessibilityEvent(32);
                    if (this.n.getParent() instanceof View) {
                        ViewCompat.t((View) this.n.getParent());
                    }
                }
                if (this.o != null) {
                    this.b.getDecorView().post(this.p);
                }
            } else {
                this.m = null;
            }
        }
        return this.m;
    }

    final boolean r() {
        return this.x && this.y != null && ViewCompat.B(this.y);
    }

    final void s() {
        if (this.q != null) {
            this.q.b();
        }
    }

    final boolean a(int keyCode, KeyEvent ev) {
        ActionBar ab = a();
        if (ab != null && ab.a(keyCode, ev)) {
            return true;
        }
        if (this.F == null || !a(this.F, ev.getKeyCode(), ev)) {
            if (this.F == null) {
                PanelFeatureState st = g(0);
                b(st, ev);
                boolean handled = a(st, ev.getKeyCode(), ev);
                st.m = false;
                if (handled) {
                    return true;
                }
            }
            return false;
        } else if (this.F == null) {
            return true;
        } else {
            this.F.n = true;
            return true;
        }
    }

    final boolean a(KeyEvent event) {
        if (event.getKeyCode() == 82 && this.c.dispatchKeyEvent(event)) {
            return true;
        }
        boolean z;
        int keyCode = event.getKeyCode();
        if (event.getAction() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            switch (keyCode) {
                case 4:
                    this.G = (event.getFlags() & 128) != 0;
                    break;
                case 82:
                    if (event.getRepeatCount() != 0) {
                        return true;
                    }
                    PanelFeatureState g = g(0);
                    if (g.o) {
                        return true;
                    }
                    b(g, event);
                    return true;
            }
            if (VERSION.SDK_INT < 11) {
                a(keyCode, event);
            }
            return false;
        }
        PanelFeatureState g2;
        switch (keyCode) {
            case 4:
                z = this.G;
                this.G = false;
                g2 = g(0);
                if (g2 == null || !g2.o) {
                    if (this.m != null) {
                        this.m.c();
                        z = true;
                    } else {
                        ActionBar a = a();
                        if (a == null || !a.f()) {
                            z = false;
                        } else {
                            z = true;
                        }
                    }
                    if (z) {
                        return true;
                    }
                } else if (z) {
                    return true;
                } else {
                    a(g2, true);
                    return true;
                }
                break;
            case 82:
                if (this.m != null) {
                    return true;
                }
                g2 = g(0);
                if (this.u == null || !this.u.c() || ViewConfiguration.get(this.a).hasPermanentMenuKey()) {
                    if (g2.o || g2.n) {
                        z = g2.o;
                        a(g2, true);
                    } else {
                        if (g2.m) {
                            if (g2.r) {
                                g2.m = false;
                                z = b(g2, event);
                            } else {
                                z = true;
                            }
                            if (z) {
                                a(g2, event);
                                z = true;
                            }
                        }
                        z = false;
                    }
                } else if (this.u.d()) {
                    z = this.u.g();
                } else {
                    if (!p() && b(g2, event)) {
                        z = this.u.f();
                    }
                    z = false;
                }
                if (!z) {
                    return true;
                }
                AudioManager audioManager = (AudioManager) this.a.getSystemService("audio");
                if (audioManager == null) {
                    return true;
                }
                audioManager.playSoundEffect(0);
                return true;
        }
        return false;
    }

    public final void i() {
        LayoutInflater layoutInflater = LayoutInflater.from(this.a);
        if (layoutInflater.getFactory() == null) {
            android.support.v4.view.e.b(layoutInflater, this);
        } else {
            layoutInflater.getFactory2();
        }
    }

    public final View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = a(name, context, attrs);
        if (view != null) {
            return view;
        }
        boolean z;
        if (this.L == null) {
            this.L = new g();
        }
        if (t) {
            boolean z2;
            if (attrs instanceof XmlPullParser) {
                z2 = ((XmlPullParser) attrs).getDepth() > 1;
            } else {
                ViewParent viewParent = (ViewParent) parent;
                if (viewParent == null) {
                    z2 = false;
                } else {
                    ViewParent decorView = this.b.getDecorView();
                    ViewParent viewParent2 = viewParent;
                    while (viewParent2 != null) {
                        if (viewParent2 == decorView || !(viewParent2 instanceof View) || ViewCompat.D((View) viewParent2)) {
                            z2 = false;
                            break;
                        }
                        viewParent2 = viewParent2.getParent();
                    }
                    z2 = true;
                }
            }
            z = z2;
        } else {
            z = false;
        }
        return this.L.a(parent, name, context, attrs, z, t, av.a());
    }

    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    View a(String name, Context context, AttributeSet attrs) {
        if (this.c instanceof Factory) {
            View result = ((Factory) this.c).onCreateView(name, context, attrs);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private void a(PanelFeatureState st, KeyEvent event) {
        if (!st.o && !p()) {
            if (st.a == 0) {
                Context context = this.a;
                boolean isXLarge = (context.getResources().getConfiguration().screenLayout & 15) == 4;
                boolean isHoneycombApp = context.getApplicationInfo().targetSdkVersion >= 11;
                if (isXLarge && isHoneycombApp) {
                    return;
                }
            }
            Callback cb = this.b.getCallback();
            if (cb == null || cb.onMenuOpened(st.a, st.j)) {
                WindowManager wm = (WindowManager) this.a.getSystemService("window");
                if (wm != null && b(st, event)) {
                    int width = -2;
                    LayoutParams lp;
                    if (st.g == null || st.q) {
                        Object obj;
                        if (st.g == null) {
                            Context n = n();
                            TypedValue typedValue = new TypedValue();
                            Theme newTheme = n.getResources().newTheme();
                            newTheme.setTo(n.getTheme());
                            newTheme.resolveAttribute(android.support.v7.appcompat.a.a.actionBarPopupTheme, typedValue, true);
                            if (typedValue.resourceId != 0) {
                                newTheme.applyStyle(typedValue.resourceId, true);
                            }
                            newTheme.resolveAttribute(android.support.v7.appcompat.a.a.panelMenuListTheme, typedValue, true);
                            if (typedValue.resourceId != 0) {
                                newTheme.applyStyle(typedValue.resourceId, true);
                            } else {
                                newTheme.applyStyle(android.support.v7.appcompat.a.i.Theme_AppCompat_CompactMenu, true);
                            }
                            Context dVar = new android.support.v7.view.d(n, 0);
                            dVar.getTheme().setTo(newTheme);
                            st.l = dVar;
                            TypedArray obtainStyledAttributes = dVar.obtainStyledAttributes(j.AppCompatTheme);
                            st.b = obtainStyledAttributes.getResourceId(j.AppCompatTheme_panelBackground, 0);
                            st.f = obtainStyledAttributes.getResourceId(j.AppCompatTheme_android_windowAnimationStyle, 0);
                            obtainStyledAttributes.recycle();
                            st.g = new c(this, st.l);
                            st.c = 81;
                            if (st.g == null) {
                                return;
                            }
                        } else if (st.q && st.g.getChildCount() > 0) {
                            st.g.removeAllViews();
                        }
                        if (st.i != null) {
                            st.h = st.i;
                            obj = 1;
                        } else {
                            if (st.j != null) {
                                o oVar;
                                if (this.w == null) {
                                    this.w = new d(this);
                                }
                                android.support.v7.view.menu.n.a aVar = this.w;
                                if (st.j == null) {
                                    oVar = null;
                                } else {
                                    if (st.k == null) {
                                        st.k = new e(st.l, android.support.v7.appcompat.a.g.abc_list_menu_item_layout);
                                        st.k.a(aVar);
                                        st.j.a(st.k);
                                    }
                                    oVar = st.k.a(st.g);
                                }
                                st.h = (View) oVar;
                                if (st.h != null) {
                                    obj = 1;
                                }
                            }
                            obj = null;
                        }
                        if (obj != null) {
                            ViewParent shownPanelParent;
                            if (st.h != null) {
                                if (st.i != null) {
                                    obj = 1;
                                } else if (st.k.c().getCount() > 0) {
                                    obj = 1;
                                }
                                if (obj != null) {
                                    lp = st.h.getLayoutParams();
                                    if (lp == null) {
                                        lp = new LayoutParams(-2, -2);
                                    }
                                    st.g.setBackgroundResource(st.b);
                                    shownPanelParent = st.h.getParent();
                                    if (shownPanelParent != null && (shownPanelParent instanceof ViewGroup)) {
                                        ((ViewGroup) shownPanelParent).removeView(st.h);
                                    }
                                    st.g.addView(st.h, lp);
                                    if (!st.h.hasFocus()) {
                                        st.h.requestFocus();
                                    }
                                } else {
                                    return;
                                }
                            }
                            obj = null;
                            if (obj != null) {
                                lp = st.h.getLayoutParams();
                                if (lp == null) {
                                    lp = new LayoutParams(-2, -2);
                                }
                                st.g.setBackgroundResource(st.b);
                                shownPanelParent = st.h.getParent();
                                ((ViewGroup) shownPanelParent).removeView(st.h);
                                st.g.addView(st.h, lp);
                                if (st.h.hasFocus()) {
                                    st.h.requestFocus();
                                }
                            } else {
                                return;
                            }
                        }
                        return;
                    } else if (st.i != null) {
                        lp = st.i.getLayoutParams();
                        if (lp != null && lp.width == -1) {
                            width = -1;
                        }
                    }
                    st.n = false;
                    WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams(width, -2, st.d, st.e, 1002, 8519680, -3);
                    lp2.gravity = st.c;
                    lp2.windowAnimations = st.f;
                    wm.addView(st.g, lp2);
                    st.o = true;
                    return;
                }
                return;
            }
            a(st, true);
        }
    }

    private boolean b(PanelFeatureState st, KeyEvent event) {
        if (p()) {
            return false;
        }
        if (st.m) {
            return true;
        }
        boolean isActionBarMenu;
        if (!(this.F == null || this.F == st)) {
            a(this.F, false);
        }
        Callback cb = this.b.getCallback();
        if (cb != null) {
            st.i = cb.onCreatePanelView(st.a);
        }
        if (st.a == 0 || st.a == 108) {
            isActionBarMenu = true;
        } else {
            isActionBarMenu = false;
        }
        if (isActionBarMenu && this.u != null) {
            this.u.setMenuPrepared();
        }
        if (st.i == null && !(isActionBarMenu && (this.f instanceof i))) {
            if (st.j == null || st.r) {
                if (st.j == null) {
                    Context dVar;
                    g gVar;
                    Context context = this.a;
                    if ((st.a == 0 || st.a == 108) && this.u != null) {
                        Theme newTheme;
                        TypedValue typedValue = new TypedValue();
                        Theme theme = context.getTheme();
                        theme.resolveAttribute(android.support.v7.appcompat.a.a.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            newTheme = context.getResources().newTheme();
                            newTheme.setTo(theme);
                            newTheme.applyStyle(typedValue.resourceId, true);
                            newTheme.resolveAttribute(android.support.v7.appcompat.a.a.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme.resolveAttribute(android.support.v7.appcompat.a.a.actionBarWidgetTheme, typedValue, true);
                            newTheme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (newTheme == null) {
                                newTheme = context.getResources().newTheme();
                                newTheme.setTo(theme);
                            }
                            newTheme.applyStyle(typedValue.resourceId, true);
                        }
                        Theme theme2 = newTheme;
                        if (theme2 != null) {
                            dVar = new android.support.v7.view.d(context, 0);
                            dVar.getTheme().setTo(theme2);
                            gVar = new g(dVar);
                            gVar.a((android.support.v7.view.menu.g.a) this);
                            st.a(gVar);
                            if (st.j == null) {
                                return false;
                            }
                        }
                    }
                    dVar = context;
                    gVar = new g(dVar);
                    gVar.a((android.support.v7.view.menu.g.a) this);
                    st.a(gVar);
                    if (st.j == null) {
                        return false;
                    }
                }
                if (isActionBarMenu && this.u != null) {
                    if (this.v == null) {
                        this.v = new a(this);
                    }
                    this.u.setMenu(st.j, this.v);
                }
                st.j.g();
                if (cb.onCreatePanelMenu(st.a, st.j)) {
                    st.r = false;
                } else {
                    st.a(null);
                    if (!isActionBarMenu || this.u == null) {
                        return false;
                    }
                    this.u.setMenu(null, this.v);
                    return false;
                }
            }
            st.j.g();
            if (st.s != null) {
                st.j.d(st.s);
                st.s = null;
            }
            if (cb.onPreparePanel(0, st.i, st.j)) {
                boolean z;
                if (KeyCharacterMap.load(event != null ? event.getDeviceId() : -1).getKeyboardType() != 1) {
                    z = true;
                } else {
                    z = false;
                }
                st.p = z;
                st.j.setQwertyMode(st.p);
                st.j.h();
            } else {
                if (isActionBarMenu && this.u != null) {
                    this.u.setMenu(null, this.v);
                }
                st.j.h();
                return false;
            }
        }
        st.m = true;
        st.n = false;
        this.F = st;
        return true;
    }

    final void b(g menu) {
        if (!this.D) {
            this.D = true;
            this.u.h();
            Callback cb = this.b.getCallback();
            if (!(cb == null || p())) {
                cb.onPanelClosed(108, menu);
            }
            this.D = false;
        }
    }

    final void t() {
        a(g(0), true);
    }

    final void a(PanelFeatureState st, boolean doCallback) {
        if (doCallback && st.a == 0 && this.u != null && this.u.d()) {
            b(st.j);
            return;
        }
        WindowManager wm = (WindowManager) this.a.getSystemService("window");
        if (!(wm == null || !st.o || st.g == null)) {
            wm.removeView(st.g);
            if (doCallback) {
                a(st.a, st, null);
            }
        }
        st.m = false;
        st.n = false;
        st.o = false;
        st.h = null;
        st.q = true;
        if (this.F == st) {
            this.F = null;
        }
    }

    final void a(int featureId, PanelFeatureState panel, Menu menu) {
        if (menu == null) {
            if (panel == null && featureId >= 0 && featureId < this.E.length) {
                panel = this.E[featureId];
            }
            if (panel != null) {
                menu = panel.j;
            }
        }
        if ((panel == null || panel.o) && !p()) {
            this.c.onPanelClosed(featureId, menu);
        }
    }

    final PanelFeatureState a(Menu menu) {
        PanelFeatureState[] panels = this.E;
        int N = panels != null ? panels.length : 0;
        for (int i = 0; i < N; i++) {
            PanelFeatureState panel = panels[i];
            if (panel != null && panel.j == menu) {
                return panel;
            }
        }
        return null;
    }

    protected final PanelFeatureState g(int featureId) {
        PanelFeatureState[] ar = this.E;
        if (ar == null || ar.length <= featureId) {
            PanelFeatureState[] nar = new PanelFeatureState[(featureId + 1)];
            if (ar != null) {
                System.arraycopy(ar, 0, nar, 0, ar.length);
            }
            ar = nar;
            this.E = nar;
        }
        PanelFeatureState st = ar[featureId];
        if (st != null) {
            return st;
        }
        st = new PanelFeatureState(featureId);
        ar[featureId] = st;
        return st;
    }

    private boolean a(PanelFeatureState st, int keyCode, KeyEvent event) {
        if (event.isSystem()) {
            return false;
        }
        if ((st.m || b(st, event)) && st.j != null) {
            return st.j.performShortcut(keyCode, event, 1);
        }
        return false;
    }

    private void f(int featureId) {
        this.s |= 1 << featureId;
        if (!this.r) {
            ViewCompat.a(this.b.getDecorView(), this.H);
            this.r = true;
        }
    }

    final void h(int featureId) {
        PanelFeatureState st = g(featureId);
        if (st.j != null) {
            Bundle savedActionViewStates = new Bundle();
            st.j.c(savedActionViewStates);
            if (savedActionViewStates.size() > 0) {
                st.s = savedActionViewStates;
            }
            st.j.g();
            st.j.clear();
        }
        st.r = true;
        st.q = true;
        if ((featureId == 108 || featureId == 0) && this.u != null) {
            st = g(0);
            if (st != null) {
                st.m = false;
                b(st, null);
            }
        }
    }

    final int i(int insetTop) {
        int i = 0;
        boolean showStatusGuard = false;
        if (this.n != null && (this.n.getLayoutParams() instanceof MarginLayoutParams)) {
            MarginLayoutParams mlp = (MarginLayoutParams) this.n.getLayoutParams();
            boolean mlpChanged = false;
            if (this.n.isShown()) {
                int newMargin;
                if (this.J == null) {
                    this.J = new Rect();
                    this.K = new Rect();
                }
                Rect insets = this.J;
                Rect localInsets = this.K;
                insets.set(0, insetTop, 0, 0);
                ax.a(this.y, insets, localInsets);
                if (localInsets.top == 0) {
                    newMargin = insetTop;
                } else {
                    newMargin = 0;
                }
                if (mlp.topMargin != newMargin) {
                    mlpChanged = true;
                    mlp.topMargin = insetTop;
                    if (this.A == null) {
                        this.A = new View(this.a);
                        this.A.setBackgroundColor(this.a.getResources().getColor(android.support.v7.appcompat.a.c.abc_input_method_navigation_guard));
                        this.y.addView(this.A, -1, new LayoutParams(-1, insetTop));
                    } else {
                        LayoutParams lp = this.A.getLayoutParams();
                        if (lp.height != insetTop) {
                            lp.height = insetTop;
                            this.A.setLayoutParams(lp);
                        }
                    }
                }
                if (this.A != null) {
                    showStatusGuard = true;
                } else {
                    showStatusGuard = false;
                }
                if (!this.j && showStatusGuard) {
                    insetTop = 0;
                }
            } else if (mlp.topMargin != 0) {
                mlpChanged = true;
                mlp.topMargin = 0;
            }
            if (mlpChanged) {
                this.n.setLayoutParams(mlp);
            }
        }
        if (this.A != null) {
            View view = this.A;
            if (!showStatusGuard) {
                i = 8;
            }
            view.setVisibility(i);
        }
        return insetTop;
    }

    private void w() {
        if (this.x) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    final void u() {
        if (this.u != null) {
            this.u.h();
        }
        if (this.o != null) {
            this.b.getDecorView().removeCallbacks(this.p);
            if (this.o.isShowing()) {
                try {
                    this.o.dismiss();
                } catch (IllegalArgumentException e) {
                }
            }
            this.o = null;
        }
        s();
        PanelFeatureState st = g(0);
        if (st != null && st.j != null) {
            st.j.close();
        }
    }

    public final boolean a(g menu, MenuItem item) {
        Callback cb = this.b.getCallback();
        if (!(cb == null || p())) {
            PanelFeatureState panel = a(menu.o());
            if (panel != null) {
                return cb.onMenuItemSelected(panel.a, item);
            }
        }
        return false;
    }
}
