package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.s;
import android.support.v4.view.t;
import android.support.v4.view.u;
import android.support.v4.view.v;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.b;
import android.support.v7.view.h;
import android.support.v7.view.menu.g;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.r;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public class l extends ActionBar implements android.support.v7.widget.ActionBarOverlayLayout.a {
    static final /* synthetic */ boolean s = (!l.class.desiredAssertionStatus());
    private static final Interpolator t = new AccelerateInterpolator();
    private static final Interpolator u = new DecelerateInterpolator();
    private boolean A;
    private boolean B;
    private ArrayList<Object> C = new ArrayList();
    private boolean D;
    private int E = 0;
    private boolean F;
    private boolean G = true;
    private boolean H;
    Context a;
    ActionBarOverlayLayout b;
    ActionBarContainer c;
    r d;
    ActionBarContextView e;
    View f;
    ScrollingTabContainerView g;
    a h;
    b i;
    android.support.v7.view.b.a j;
    boolean k = true;
    boolean l;
    boolean m;
    h n;
    boolean o;
    final t p = new u(this) {
        final /* synthetic */ l a;

        {
            this.a = this$0;
        }

        public final void b(View view) {
            if (this.a.k && this.a.f != null) {
                this.a.f.setTranslationY(0.0f);
                this.a.c.setTranslationY(0.0f);
            }
            this.a.c.setVisibility(8);
            this.a.c.setTransitioning(false);
            this.a.n = null;
            l lVar = this.a;
            if (lVar.j != null) {
                lVar.j.a(lVar.i);
                lVar.i = null;
                lVar.j = null;
            }
            if (this.a.b != null) {
                ViewCompat.t(this.a.b);
            }
        }
    };
    final t q = new u(this) {
        final /* synthetic */ l a;

        {
            this.a = this$0;
        }

        public final void b(View view) {
            this.a.n = null;
            this.a.c.requestLayout();
        }
    };
    final v r = new v(this) {
        final /* synthetic */ l a;

        {
            this.a = this$0;
        }

        public final void a() {
            ((View) this.a.c.getParent()).invalidate();
        }
    };
    private Context v;
    private Activity w;
    private Dialog x;
    private ArrayList<Object> y = new ArrayList();
    private int z = -1;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public class a extends b implements android.support.v7.view.menu.g.a {
        final /* synthetic */ l a;
        private final Context b;
        private final g c;
        private android.support.v7.view.b.a d;
        private WeakReference<View> e;

        public a(l this$0, Context context, android.support.v7.view.b.a callback) {
            this.a = this$0;
            this.b = context;
            this.d = callback;
            this.c = new g(context).a();
            this.c.a((android.support.v7.view.menu.g.a) this);
        }

        public final MenuInflater a() {
            return new android.support.v7.view.g(this.b);
        }

        public final Menu b() {
            return this.c;
        }

        public final void c() {
            if (this.a.h == this) {
                if (l.a(this.a.l, this.a.m, false)) {
                    this.d.a(this);
                } else {
                    this.a.i = this;
                    this.a.j = this.d;
                }
                this.d = null;
                this.a.e(false);
                this.a.e.d();
                this.a.d.a().sendAccessibilityEvent(32);
                this.a.b.setHideOnContentScrollEnabled(this.a.o);
                this.a.h = null;
            }
        }

        public final void d() {
            if (this.a.h == this) {
                this.c.g();
                try {
                    this.d.b(this, this.c);
                } finally {
                    this.c.h();
                }
            }
        }

        public final boolean e() {
            this.c.g();
            try {
                boolean a = this.d.a((b) this, this.c);
                return a;
            } finally {
                this.c.h();
            }
        }

        public final void a(View view) {
            this.a.e.setCustomView(view);
            this.e = new WeakReference(view);
        }

        public final void a(CharSequence subtitle) {
            this.a.e.setSubtitle(subtitle);
        }

        public final void b(CharSequence title) {
            this.a.e.setTitle(title);
        }

        public final void a(int resId) {
            b(this.a.a.getResources().getString(resId));
        }

        public final void b(int resId) {
            a(this.a.a.getResources().getString(resId));
        }

        public final CharSequence f() {
            return this.a.e.b();
        }

        public final CharSequence g() {
            return this.a.e.c();
        }

        public final void a(boolean titleOptional) {
            super.a(titleOptional);
            this.a.e.setTitleOptional(titleOptional);
        }

        public final boolean h() {
            return this.a.e.f();
        }

        public final View i() {
            return this.e != null ? (View) this.e.get() : null;
        }

        public final boolean a(g menu, MenuItem item) {
            if (this.d != null) {
                return this.d.a((b) this, item);
            }
            return false;
        }

        public final void a(g menu) {
            if (this.d != null) {
                d();
                this.a.e.a();
            }
        }
    }

    public l(Activity activity, boolean overlayMode) {
        this.w = activity;
        View decor = activity.getWindow().getDecorView();
        a(decor);
        if (!overlayMode) {
            this.f = decor.findViewById(16908290);
        }
    }

    public l(Dialog dialog) {
        this.x = dialog;
        a(dialog.getWindow().getDecorView());
    }

    private void a(View decor) {
        r rVar;
        this.b = (ActionBarOverlayLayout) decor.findViewById(f.decor_content_parent);
        if (this.b != null) {
            this.b.setActionBarVisibilityCallback(this);
        }
        View findViewById = decor.findViewById(f.action_bar);
        if (findViewById instanceof r) {
            rVar = (r) findViewById;
        } else if (findViewById instanceof Toolbar) {
            rVar = ((Toolbar) findViewById).r();
        } else {
            throw new IllegalStateException(new StringBuilder("Can't make a decor toolbar out of ").append(findViewById).toString() != null ? findViewById.getClass().getSimpleName() : "null");
        }
        this.d = rVar;
        this.e = (ActionBarContextView) decor.findViewById(f.action_context_bar);
        this.c = (ActionBarContainer) decor.findViewById(f.action_bar_container);
        if (this.d == null || this.e == null || this.c == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        boolean z;
        this.a = this.d.b();
        if ((this.d.l() & 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.A = true;
        }
        android.support.v7.view.a abp = android.support.v7.view.a.a(this.a);
        abp.f();
        f(abp.d());
        TypedArray a = this.a.obtainStyledAttributes(null, j.ActionBar, android.support.v7.appcompat.a.a.actionBarStyle, 0);
        if (a.getBoolean(j.ActionBar_hideOnContentScroll, false)) {
            if (this.b.a()) {
                this.o = true;
                this.b.setHideOnContentScrollEnabled(true);
            } else {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
        }
        int elevation = a.getDimensionPixelSize(j.ActionBar_elevation, 0);
        if (elevation != 0) {
            ViewCompat.c(this.c, (float) elevation);
        }
        a.recycle();
    }

    public final void a(Configuration newConfig) {
        f(android.support.v7.view.a.a(this.a).d());
    }

    private void f(boolean hasEmbeddedTabs) {
        boolean isInTabMode;
        boolean z;
        boolean z2 = true;
        this.D = hasEmbeddedTabs;
        if (this.D) {
            this.c.setTabContainer(null);
            this.d.a(this.g);
        } else {
            this.d.a(null);
            this.c.setTabContainer(this.g);
        }
        if (this.d.m() == 2) {
            isInTabMode = true;
        } else {
            isInTabMode = false;
        }
        if (this.g != null) {
            if (isInTabMode) {
                this.g.setVisibility(0);
                if (this.b != null) {
                    ViewCompat.t(this.b);
                }
            } else {
                this.g.setVisibility(8);
            }
        }
        r rVar = this.d;
        if (this.D || !isInTabMode) {
            z = false;
        } else {
            z = true;
        }
        rVar.a(z);
        ActionBarOverlayLayout actionBarOverlayLayout = this.b;
        if (this.D || !isInTabMode) {
            z2 = false;
        }
        actionBarOverlayLayout.setHasNonEmbeddedTabs(z2);
    }

    public final void a(int visibility) {
        this.E = visibility;
    }

    public final void b(boolean enabled) {
        this.H = enabled;
        if (!enabled && this.n != null) {
            this.n.c();
        }
    }

    public final void c(boolean isVisible) {
        if (isVisible != this.B) {
            this.B = isVisible;
            int count = this.C.size();
            for (int i = 0; i < count; i++) {
                this.C.get(i);
            }
        }
    }

    public final void a(CharSequence title) {
        this.d.a(title);
    }

    public final int a() {
        return this.d.l();
    }

    public final b a(android.support.v7.view.b.a callback) {
        if (this.h != null) {
            this.h.c();
        }
        this.b.setHideOnContentScrollEnabled(false);
        this.e.e();
        a mode = new a(this, this.e.getContext(), callback);
        if (!mode.e()) {
            return null;
        }
        this.h = mode;
        mode.d();
        this.e.a(mode);
        e(true);
        this.e.sendAccessibilityEvent(32);
        return mode;
    }

    public final void d(boolean enabled) {
        this.k = enabled;
    }

    public final void h() {
        if (this.m) {
            this.m = false;
            g(true);
        }
    }

    public final void i() {
        if (!this.m) {
            this.m = true;
            g(true);
        }
    }

    static boolean a(boolean hiddenByApp, boolean hiddenBySystem, boolean showingForMode) {
        if (showingForMode) {
            return true;
        }
        if (hiddenByApp || hiddenBySystem) {
            return false;
        }
        return true;
    }

    private void g(boolean fromSystem) {
        float f;
        h hVar;
        s b;
        if (a(this.l, this.m, this.F)) {
            if (!this.G) {
                this.G = true;
                if (this.n != null) {
                    this.n.c();
                }
                this.c.setVisibility(0);
                if (this.E == 0 && (this.H || fromSystem)) {
                    this.c.setTranslationY(0.0f);
                    f = (float) (-this.c.getHeight());
                    if (fromSystem) {
                        int[] iArr = new int[]{0, 0};
                        this.c.getLocationInWindow(iArr);
                        f -= (float) iArr[1];
                    }
                    this.c.setTranslationY(f);
                    hVar = new h();
                    b = ViewCompat.p(this.c).b(0.0f);
                    b.a(this.r);
                    hVar.a(b);
                    if (this.k && this.f != null) {
                        this.f.setTranslationY(f);
                        hVar.a(ViewCompat.p(this.f).b(0.0f));
                    }
                    hVar.a(u);
                    hVar.d();
                    hVar.a(this.q);
                    this.n = hVar;
                    hVar.a();
                } else {
                    this.c.setAlpha(1.0f);
                    this.c.setTranslationY(0.0f);
                    if (this.k && this.f != null) {
                        this.f.setTranslationY(0.0f);
                    }
                    this.q.b(null);
                }
                if (this.b != null) {
                    ViewCompat.t(this.b);
                }
            }
        } else if (this.G) {
            this.G = false;
            if (this.n != null) {
                this.n.c();
            }
            if (this.E == 0 && (this.H || fromSystem)) {
                this.c.setAlpha(1.0f);
                this.c.setTransitioning(true);
                hVar = new h();
                f = (float) (-this.c.getHeight());
                if (fromSystem) {
                    int[] iArr2 = new int[]{0, 0};
                    this.c.getLocationInWindow(iArr2);
                    f -= (float) iArr2[1];
                }
                b = ViewCompat.p(this.c).b(f);
                b.a(this.r);
                hVar.a(b);
                if (this.k && this.f != null) {
                    hVar.a(ViewCompat.p(this.f).b(f));
                }
                hVar.a(t);
                hVar.d();
                hVar.a(this.p);
                this.n = hVar;
                hVar.a();
                return;
            }
            this.p.b(null);
        }
    }

    public final void e(boolean toActionMode) {
        if (toActionMode) {
            if (!this.F) {
                this.F = true;
                if (this.b != null) {
                    this.b.setShowingForActionMode(true);
                }
                g(false);
            }
        } else if (this.F) {
            this.F = false;
            if (this.b != null) {
                this.b.setShowingForActionMode(false);
            }
            g(false);
        }
        if (ViewCompat.B(this.c)) {
            s fadeOut;
            s fadeIn;
            if (toActionMode) {
                fadeOut = this.d.a(4, 100);
                fadeIn = this.e.a(0, 200);
            } else {
                fadeIn = this.d.a(0, 200);
                fadeOut = this.e.a(8, 100);
            }
            h set = new h();
            set.a(fadeOut, fadeIn);
            set.a();
        } else if (toActionMode) {
            this.d.d(4);
            this.e.setVisibility(0);
        } else {
            this.d.d(0);
            this.e.setVisibility(8);
        }
    }

    public final Context b() {
        if (this.v == null) {
            TypedValue outValue = new TypedValue();
            this.a.getTheme().resolveAttribute(android.support.v7.appcompat.a.a.actionBarWidgetTheme, outValue, true);
            int targetThemeRes = outValue.resourceId;
            if (targetThemeRes != 0) {
                this.v = new ContextThemeWrapper(this.a, targetThemeRes);
            } else {
                this.v = this.a;
            }
        }
        return this.v;
    }

    public final void j() {
        if (this.n != null) {
            this.n.c();
            this.n = null;
        }
    }

    public final boolean f() {
        if (this.d == null || !this.d.c()) {
            return false;
        }
        this.d.d();
        return true;
    }

    public final void a(boolean enable) {
        if (!this.A) {
            int i;
            if (enable) {
                i = 4;
            } else {
                i = 0;
            }
            int l = this.d.l();
            this.A = true;
            this.d.c((i & 4) | (l & -5));
        }
    }

    public final boolean a(int keyCode, KeyEvent event) {
        if (this.h == null) {
            return false;
        }
        Menu menu = this.h.b();
        if (menu == null) {
            return false;
        }
        boolean z;
        if (KeyCharacterMap.load(event != null ? event.getDeviceId() : -1).getKeyboardType() != 1) {
            z = true;
        } else {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(keyCode, event, 0);
    }
}
