package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.s;
import android.support.v4.view.u;
import android.support.v7.appcompat.a.f;
import android.support.v7.appcompat.a.h;
import android.support.v7.appcompat.a.j;
import android.support.v7.content.res.b;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.n;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window.Callback;

@RestrictTo({a.LIBRARY_GROUP})
public final class ar implements r {
    Toolbar a;
    CharSequence b;
    Callback c;
    boolean d;
    private int e;
    private View f;
    private View g;
    private Drawable h;
    private Drawable i;
    private Drawable j;
    private boolean k;
    private CharSequence l;
    private CharSequence m;
    private ActionMenuPresenter n;
    private int o;
    private int p;
    private Drawable q;

    public ar(Toolbar toolbar) {
        this(toolbar, h.abc_action_bar_up_description);
    }

    private ar(Toolbar toolbar, int defaultNavigationContentDescription) {
        this.o = 0;
        this.p = 0;
        this.a = toolbar;
        this.b = toolbar.i();
        this.l = toolbar.j();
        this.k = this.b != null;
        this.j = toolbar.l();
        aq a = aq.a(toolbar.getContext(), null, j.ActionBar, android.support.v7.appcompat.a.a.actionBarStyle, 0);
        this.q = a.a(j.ActionBar_homeAsUpIndicator);
        CharSequence title = a.c(j.ActionBar_title);
        if (!TextUtils.isEmpty(title)) {
            this.k = true;
            b(title);
        }
        CharSequence subtitle = a.c(j.ActionBar_subtitle);
        if (!TextUtils.isEmpty(subtitle)) {
            this.l = subtitle;
            if ((this.e & 8) != 0) {
                this.a.setSubtitle(subtitle);
            }
        }
        Drawable logo = a.a(j.ActionBar_logo);
        if (logo != null) {
            b(logo);
        }
        Drawable icon = a.a(j.ActionBar_icon);
        if (icon != null) {
            a(icon);
        }
        if (this.j == null && this.q != null) {
            this.j = this.q;
            p();
        }
        c(a.a(j.ActionBar_displayOptions, 0));
        int customNavId = a.g(j.ActionBar_customNavigationLayout, 0);
        if (customNavId != 0) {
            View inflate = LayoutInflater.from(this.a.getContext()).inflate(customNavId, this.a, false);
            if (!(this.g == null || (this.e & 16) == 0)) {
                this.a.removeView(this.g);
            }
            this.g = inflate;
            if (!(inflate == null || (this.e & 16) == 0)) {
                this.a.addView(this.g);
            }
            c(this.e | 16);
        }
        int height = a.f(j.ActionBar_height, 0);
        if (height > 0) {
            LayoutParams lp = this.a.getLayoutParams();
            lp.height = height;
            this.a.setLayoutParams(lp);
        }
        int contentInsetStart = a.d(j.ActionBar_contentInsetStart, -1);
        int contentInsetEnd = a.d(j.ActionBar_contentInsetEnd, -1);
        if (contentInsetStart >= 0 || contentInsetEnd >= 0) {
            this.a.setContentInsetsRelative(Math.max(contentInsetStart, 0), Math.max(contentInsetEnd, 0));
        }
        int titleTextStyle = a.g(j.ActionBar_titleTextStyle, 0);
        if (titleTextStyle != 0) {
            this.a.setTitleTextAppearance(this.a.getContext(), titleTextStyle);
        }
        int subtitleTextStyle = a.g(j.ActionBar_subtitleTextStyle, 0);
        if (subtitleTextStyle != 0) {
            this.a.setSubtitleTextAppearance(this.a.getContext(), subtitleTextStyle);
        }
        int popupTheme = a.g(j.ActionBar_popupTheme, 0);
        if (popupTheme != 0) {
            this.a.setPopupTheme(popupTheme);
        }
        a.a();
        if (defaultNavigationContentDescription != this.p) {
            this.p = defaultNavigationContentDescription;
            if (TextUtils.isEmpty(this.a.k())) {
                CharSequence charSequence;
                int i = this.p;
                if (i == 0) {
                    charSequence = null;
                } else {
                    charSequence = this.a.getContext().getString(i);
                }
                this.m = charSequence;
                q();
            }
        }
        this.m = this.a.k();
        this.a.setNavigationOnClickListener(new OnClickListener(this) {
            final android.support.v7.view.menu.a a = new android.support.v7.view.menu.a(this.b.a.getContext(), this.b.b);
            final /* synthetic */ ar b;

            {
                this.b = this$0;
            }

            public final void onClick(View v) {
                if (this.b.c != null && this.b.d) {
                    this.b.c.onMenuItemSelected(0, this.a);
                }
            }
        });
    }

    public final ViewGroup a() {
        return this.a;
    }

    public final Context b() {
        return this.a.getContext();
    }

    public final boolean c() {
        return this.a.g();
    }

    public final void d() {
        this.a.h();
    }

    public final void a(Callback cb) {
        this.c = cb;
    }

    public final void a(CharSequence title) {
        if (!this.k) {
            b(title);
        }
    }

    private void b(CharSequence title) {
        this.b = title;
        if ((this.e & 8) != 0) {
            this.a.setTitle(title);
        }
    }

    public final void a(int resId) {
        a(resId != 0 ? b.b(this.a.getContext(), resId) : null);
    }

    public final void a(Drawable d) {
        this.h = d;
        o();
    }

    public final void b(int resId) {
        b(resId != 0 ? b.b(this.a.getContext(), resId) : null);
    }

    private void b(Drawable d) {
        this.i = d;
        o();
    }

    private void o() {
        Drawable logo = null;
        if ((this.e & 2) != 0) {
            logo = (this.e & 1) != 0 ? this.i != null ? this.i : this.h : this.h;
        }
        this.a.setLogo(logo);
    }

    public final boolean e() {
        return this.a.a();
    }

    public final boolean f() {
        return this.a.b();
    }

    public final boolean g() {
        return this.a.c();
    }

    public final boolean h() {
        return this.a.d();
    }

    public final boolean i() {
        return this.a.e();
    }

    public final void j() {
        this.d = true;
    }

    public final void a(Menu menu, n.a cb) {
        if (this.n == null) {
            this.n = new ActionMenuPresenter(this.a.getContext());
            this.n.a(f.action_menu_presenter);
        }
        this.n.a(cb);
        this.a.setMenu((g) menu, this.n);
    }

    public final void k() {
        this.a.f();
    }

    public final int l() {
        return this.e;
    }

    public final void c(int newOpts) {
        int changed = this.e ^ newOpts;
        this.e = newOpts;
        if (changed != 0) {
            if ((changed & 4) != 0) {
                if ((newOpts & 4) != 0) {
                    q();
                }
                p();
            }
            if ((changed & 3) != 0) {
                o();
            }
            if ((changed & 8) != 0) {
                if ((newOpts & 8) != 0) {
                    this.a.setTitle(this.b);
                    this.a.setSubtitle(this.l);
                } else {
                    this.a.setTitle(null);
                    this.a.setSubtitle(null);
                }
            }
            if ((changed & 16) != 0 && this.g != null) {
                if ((newOpts & 16) != 0) {
                    this.a.addView(this.g);
                } else {
                    this.a.removeView(this.g);
                }
            }
        }
    }

    public final void a(ScrollingTabContainerView tabView) {
        if (this.f != null && this.f.getParent() == this.a) {
            this.a.removeView(this.f);
        }
        this.f = tabView;
        if (tabView != null && this.o == 2) {
            this.a.addView(this.f, 0);
            Toolbar.b lp = (Toolbar.b) this.f.getLayoutParams();
            lp.width = -2;
            lp.height = -2;
            lp.a = 8388691;
            tabView.setAllowCollapse(true);
        }
    }

    public final void a(boolean collapsible) {
        this.a.setCollapsible(collapsible);
    }

    public final int m() {
        return this.o;
    }

    public final s a(final int visibility, long duration) {
        return ViewCompat.p(this.a).a(visibility == 0 ? 1.0f : 0.0f).a(duration).a(new u(this) {
            final /* synthetic */ ar b;
            private boolean c = false;

            public final void a(View view) {
                this.b.a.setVisibility(0);
            }

            public final void b(View view) {
                if (!this.c) {
                    this.b.a.setVisibility(visibility);
                }
            }

            public final void c(View view) {
                this.c = true;
            }
        });
    }

    private void p() {
        if ((this.e & 4) != 0) {
            this.a.setNavigationIcon(this.j != null ? this.j : this.q);
        } else {
            this.a.setNavigationIcon(null);
        }
    }

    private void q() {
        if ((this.e & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.m)) {
            this.a.setNavigationContentDescription(this.p);
        } else {
            this.a.setNavigationContentDescription(this.m);
        }
    }

    public final void d(int visible) {
        this.a.setVisibility(visible);
    }

    public final void a(n.a actionMenuPresenterCallback, g.a menuBuilderCallback) {
        this.a.setMenuCallbacks(actionMenuPresenterCallback, menuBuilderCallback);
    }

    public final Menu n() {
        return this.a.m();
    }
}
