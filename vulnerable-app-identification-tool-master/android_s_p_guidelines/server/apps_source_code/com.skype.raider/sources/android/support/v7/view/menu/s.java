package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.appcompat.a.d;
import android.support.v7.appcompat.a.g;
import android.support.v7.view.menu.n.a;
import android.support.v7.widget.MenuPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

final class s extends l implements n, OnKeyListener, OnItemClickListener, OnDismissListener {
    final MenuPopupWindow a;
    View b;
    private final Context c;
    private final g d;
    private final f e;
    private final boolean f;
    private final int g;
    private final int h;
    private final int i;
    private final OnGlobalLayoutListener j = new OnGlobalLayoutListener(this) {
        final /* synthetic */ s a;

        {
            this.a = this$0;
        }

        public final void onGlobalLayout() {
            if (this.a.f() && !this.a.a.i()) {
                View anchor = this.a.b;
                if (anchor == null || !anchor.isShown()) {
                    this.a.e();
                } else {
                    this.a.a.c();
                }
            }
        }
    };
    private final OnAttachStateChangeListener k = new OnAttachStateChangeListener(this) {
        final /* synthetic */ s a;

        {
            this.a = this$0;
        }

        public final void onViewAttachedToWindow(View v) {
        }

        public final void onViewDetachedFromWindow(View v) {
            if (this.a.o != null) {
                if (!this.a.o.isAlive()) {
                    this.a.o = v.getViewTreeObserver();
                }
                this.a.o.removeGlobalOnLayoutListener(this.a.j);
            }
            v.removeOnAttachStateChangeListener(this);
        }
    };
    private OnDismissListener l;
    private View m;
    private a n;
    private ViewTreeObserver o;
    private boolean p;
    private boolean q;
    private int r;
    private int s = 0;
    private boolean t;

    public s(Context context, g menu, View anchorView, int popupStyleAttr, int popupStyleRes, boolean overflowOnly) {
        this.c = context;
        this.d = menu;
        this.f = overflowOnly;
        this.e = new f(menu, LayoutInflater.from(context), this.f);
        this.h = popupStyleAttr;
        this.i = popupStyleRes;
        Resources res = context.getResources();
        this.g = Math.max(res.getDisplayMetrics().widthPixels / 2, res.getDimensionPixelSize(d.abc_config_prefDialogWidth));
        this.m = anchorView;
        this.a = new MenuPopupWindow(this.c, this.h, this.i);
        menu.a((n) this, context);
    }

    public final void b(boolean forceShow) {
        this.e.a(forceShow);
    }

    public final void a(int gravity) {
        this.s = gravity;
    }

    public final void c() {
        boolean z = true;
        if (!f()) {
            if (this.p || this.m == null) {
                z = false;
            } else {
                this.b = this.m;
                this.a.a((OnDismissListener) this);
                this.a.a((OnItemClickListener) this);
                this.a.h();
                View view = this.b;
                boolean z2 = this.o == null;
                this.o = view.getViewTreeObserver();
                if (z2) {
                    this.o.addOnGlobalLayoutListener(this.j);
                }
                view.addOnAttachStateChangeListener(this.k);
                this.a.b(view);
                this.a.c(this.s);
                if (!this.q) {
                    this.r = l.a(this.e, null, this.c, this.g);
                    this.q = true;
                }
                this.a.d(this.r);
                this.a.o();
                this.a.a(i());
                this.a.c();
                ViewGroup g = this.a.g();
                g.setOnKeyListener(this);
                if (this.t && this.d.a != null) {
                    FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.c).inflate(g.abc_popup_menu_header_item_layout, g, false);
                    TextView textView = (TextView) frameLayout.findViewById(16908310);
                    if (textView != null) {
                        textView.setText(this.d.a);
                    }
                    frameLayout.setEnabled(false);
                    g.addHeaderView(frameLayout, null, false);
                }
                this.a.a(this.e);
                this.a.c();
            }
        }
        if (!z) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    public final void e() {
        if (f()) {
            this.a.e();
        }
    }

    public final void a(g menu) {
    }

    public final boolean f() {
        return !this.p && this.a.f();
    }

    public final void onDismiss() {
        this.p = true;
        this.d.close();
        if (this.o != null) {
            if (!this.o.isAlive()) {
                this.o = this.b.getViewTreeObserver();
            }
            this.o.removeGlobalOnLayoutListener(this.j);
            this.o = null;
        }
        this.b.removeOnAttachStateChangeListener(this.k);
        if (this.l != null) {
            this.l.onDismiss();
        }
    }

    public final void a(boolean cleared) {
        this.q = false;
        if (this.e != null) {
            this.e.notifyDataSetChanged();
        }
    }

    public final void a(a cb) {
        this.n = cb;
    }

    public final boolean a(t subMenu) {
        if (subMenu.hasVisibleItems()) {
            m subPopup = new m(this.c, subMenu, this.b, this.f, this.h, this.i);
            subPopup.a(this.n);
            subPopup.a(l.b((g) subMenu));
            subPopup.a(this.s);
            subPopup.a(this.l);
            this.l = null;
            this.d.b(false);
            if (subPopup.a(this.a.l(), this.a.m())) {
                if (this.n != null) {
                    this.n.a(subMenu);
                }
                return true;
            }
        }
        return false;
    }

    public final void a(g menu, boolean allMenusAreClosing) {
        if (menu == this.d) {
            e();
            if (this.n != null) {
                this.n.a(menu, allMenusAreClosing);
            }
        }
    }

    public final boolean a() {
        return false;
    }

    public final Parcelable d() {
        return null;
    }

    public final void a(Parcelable state) {
    }

    public final void a(View anchor) {
        this.m = anchor;
    }

    public final boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() != 1 || keyCode != 82) {
            return false;
        }
        e();
        return true;
    }

    public final void a(OnDismissListener listener) {
        this.l = listener;
    }

    public final ListView g() {
        return this.a.g();
    }

    public final void b(int x) {
        this.a.a(x);
    }

    public final void c(int y) {
        this.a.b(y);
    }

    public final void c(boolean showTitle) {
        this.t = showTitle;
    }
}
