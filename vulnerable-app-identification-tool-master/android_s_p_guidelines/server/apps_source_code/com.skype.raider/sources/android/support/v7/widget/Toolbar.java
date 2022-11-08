package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.d;
import android.support.v7.appcompat.a.j;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.n;
import android.support.v7.view.menu.t;
import android.support.v7.widget.ActionMenuView.e;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import java.util.List;

public class Toolbar extends ViewGroup {
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private final ArrayList<View> E;
    private final ArrayList<View> F;
    private final int[] G;
    private final e H;
    private ar I;
    private ActionMenuPresenter J;
    private a K;
    private android.support.v7.view.menu.n.a L;
    private android.support.v7.view.menu.g.a M;
    private boolean N;
    private final Runnable O;
    ImageButton a;
    View b;
    int c;
    c d;
    private ActionMenuView e;
    private TextView f;
    private TextView g;
    private ImageButton h;
    private ImageView i;
    private Drawable j;
    private CharSequence k;
    private Context l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private ah u;
    private int v;
    private int w;
    private int x;
    private CharSequence y;
    private CharSequence z;

    public static class SavedState extends AbsSavedState {
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
        int b;
        boolean c;

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            this.b = source.readInt();
            this.c = source.readInt() != 0;
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.b);
            out.writeInt(this.c ? 1 : 0);
        }
    }

    private class a implements n {
        g a;
        i b;
        final /* synthetic */ Toolbar c;

        a(Toolbar toolbar) {
            this.c = toolbar;
        }

        public final void a(Context context, g menu) {
            if (!(this.a == null || this.b == null)) {
                this.a.b(this.b);
            }
            this.a = menu;
        }

        public final void a(boolean cleared) {
            if (this.b != null) {
                boolean found = false;
                if (this.a != null) {
                    int count = this.a.size();
                    for (int i = 0; i < count; i++) {
                        if (this.a.getItem(i) == this.b) {
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    b(this.b);
                }
            }
        }

        public final void a(android.support.v7.view.menu.n.a cb) {
        }

        public final boolean a(t subMenu) {
            return false;
        }

        public final void a(g menu, boolean allMenusAreClosing) {
        }

        public final boolean a() {
            return false;
        }

        public final boolean a(i item) {
            this.c.p();
            if (this.c.a.getParent() != this.c) {
                this.c.addView(this.c.a);
            }
            this.c.b = item.getActionView();
            this.b = item;
            if (this.c.b.getParent() != this.c) {
                b lp = Toolbar.q();
                lp.a = 8388611 | (this.c.c & 112);
                lp.b = 2;
                this.c.b.setLayoutParams(lp);
                this.c.addView(this.c.b);
            }
            this.c.s();
            this.c.requestLayout();
            item.e(true);
            if (this.c.b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c) this.c.b).a();
            }
            return true;
        }

        public final boolean b(i item) {
            if (this.c.b instanceof android.support.v7.view.c) {
                ((android.support.v7.view.c) this.c.b).b();
            }
            this.c.removeView(this.c.b);
            this.c.removeView(this.c.a);
            this.c.b = null;
            this.c.t();
            this.b = null;
            this.c.requestLayout();
            item.e(false);
            return true;
        }

        public final int b() {
            return 0;
        }

        public final Parcelable d() {
            return null;
        }

        public final void a(Parcelable state) {
        }
    }

    public static class b extends android.support.v7.app.ActionBar.a {
        int b;

        public b(@NonNull Context c, AttributeSet attrs) {
            super(c, attrs);
            this.b = 0;
        }

        public b() {
            this.b = 0;
            this.a = 8388627;
        }

        public b(b source) {
            super((android.support.v7.app.ActionBar.a) source);
            this.b = 0;
            this.b = source.b;
        }

        public b(android.support.v7.app.ActionBar.a source) {
            super(source);
            this.b = 0;
        }

        public b(MarginLayoutParams source) {
            super((LayoutParams) source);
            this.b = 0;
            this.leftMargin = source.leftMargin;
            this.topMargin = source.topMargin;
            this.rightMargin = source.rightMargin;
            this.bottomMargin = source.bottomMargin;
        }

        public b(LayoutParams source) {
            super(source);
            this.b = 0;
        }
    }

    public interface c {
        boolean a(MenuItem menuItem);
    }

    protected /* synthetic */ LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.a.a.toolbarStyle);
    }

    public Toolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.x = 8388627;
        this.E = new ArrayList();
        this.F = new ArrayList();
        this.G = new int[2];
        this.H = new e(this) {
            final /* synthetic */ Toolbar a;

            {
                this.a = this$0;
            }

            public final boolean a(MenuItem item) {
                if (this.a.d != null) {
                    return this.a.d.a(item);
                }
                return false;
            }
        };
        this.O = new Runnable(this) {
            final /* synthetic */ Toolbar a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.d();
            }
        };
        aq a = aq.a(getContext(), attrs, j.Toolbar, defStyleAttr, 0);
        this.n = a.g(j.Toolbar_titleTextAppearance, 0);
        this.o = a.g(j.Toolbar_subtitleTextAppearance, 0);
        this.x = a.c(j.Toolbar_android_gravity, this.x);
        this.c = a.c(j.Toolbar_buttonGravity, 48);
        int titleMargin = a.d(j.Toolbar_titleMargin, 0);
        if (a.h(j.Toolbar_titleMargins)) {
            titleMargin = a.d(j.Toolbar_titleMargins, titleMargin);
        }
        this.t = titleMargin;
        this.s = titleMargin;
        this.r = titleMargin;
        this.q = titleMargin;
        int marginStart = a.d(j.Toolbar_titleMarginStart, -1);
        if (marginStart >= 0) {
            this.q = marginStart;
        }
        int marginEnd = a.d(j.Toolbar_titleMarginEnd, -1);
        if (marginEnd >= 0) {
            this.r = marginEnd;
        }
        int marginTop = a.d(j.Toolbar_titleMarginTop, -1);
        if (marginTop >= 0) {
            this.s = marginTop;
        }
        int marginBottom = a.d(j.Toolbar_titleMarginBottom, -1);
        if (marginBottom >= 0) {
            this.t = marginBottom;
        }
        this.p = a.e(j.Toolbar_maxButtonHeight, -1);
        int contentInsetStart = a.d(j.Toolbar_contentInsetStart, Integer.MIN_VALUE);
        int contentInsetEnd = a.d(j.Toolbar_contentInsetEnd, Integer.MIN_VALUE);
        int contentInsetLeft = a.e(j.Toolbar_contentInsetLeft, 0);
        int contentInsetRight = a.e(j.Toolbar_contentInsetRight, 0);
        A();
        this.u.b(contentInsetLeft, contentInsetRight);
        if (!(contentInsetStart == Integer.MIN_VALUE && contentInsetEnd == Integer.MIN_VALUE)) {
            this.u.a(contentInsetStart, contentInsetEnd);
        }
        this.v = a.d(j.Toolbar_contentInsetStartWithNavigation, Integer.MIN_VALUE);
        this.w = a.d(j.Toolbar_contentInsetEndWithActions, Integer.MIN_VALUE);
        this.j = a.a(j.Toolbar_collapseIcon);
        this.k = a.c(j.Toolbar_collapseContentDescription);
        CharSequence title = a.c(j.Toolbar_title);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        CharSequence subtitle = a.c(j.Toolbar_subtitle);
        if (!TextUtils.isEmpty(subtitle)) {
            setSubtitle(subtitle);
        }
        this.l = getContext();
        setPopupTheme(a.g(j.Toolbar_popupTheme, 0));
        Drawable navIcon = a.a(j.Toolbar_navigationIcon);
        if (navIcon != null) {
            setNavigationIcon(navIcon);
        }
        CharSequence navDesc = a.c(j.Toolbar_navigationContentDescription);
        if (!TextUtils.isEmpty(navDesc)) {
            setNavigationContentDescription(navDesc);
        }
        Drawable logo = a.a(j.Toolbar_logo);
        if (logo != null) {
            setLogo(logo);
        }
        CharSequence logoDesc = a.c(j.Toolbar_logoDescription);
        if (!TextUtils.isEmpty(logoDesc)) {
            setLogoDescription(logoDesc);
        }
        if (a.h(j.Toolbar_titleTextColor)) {
            setTitleTextColor(a.b(j.Toolbar_titleTextColor, -1));
        }
        if (a.h(j.Toolbar_subtitleTextColor)) {
            setSubtitleTextColor(a.b(j.Toolbar_subtitleTextColor, -1));
        }
        a.a();
    }

    public void setPopupTheme(@StyleRes int resId) {
        if (this.m != resId) {
            this.m = resId;
            if (resId == 0) {
                this.l = getContext();
            } else {
                this.l = new ContextThemeWrapper(getContext(), resId);
            }
        }
    }

    public void setTitleMargin(int start, int top, int end, int bottom) {
        this.q = start;
        this.s = top;
        this.r = end;
        this.t = bottom;
        requestLayout();
    }

    public void setTitleMarginStart(int margin) {
        this.q = margin;
        requestLayout();
    }

    public void setTitleMarginTop(int margin) {
        this.s = margin;
        requestLayout();
    }

    public void setTitleMarginEnd(int margin) {
        this.r = margin;
        requestLayout();
    }

    public void setTitleMarginBottom(int margin) {
        this.t = margin;
        requestLayout();
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        boolean z = true;
        if (VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(layoutDirection);
        }
        A();
        ah ahVar = this.u;
        if (layoutDirection != 1) {
            z = false;
        }
        ahVar.a(z);
    }

    public void setLogo(@DrawableRes int resId) {
        setLogo(android.support.v7.content.res.b.b(getContext(), resId));
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final boolean a() {
        return getVisibility() == 0 && this.e != null && this.e.a();
    }

    public final boolean b() {
        return this.e != null && this.e.g();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final boolean c() {
        return this.e != null && this.e.h();
    }

    public final boolean d() {
        return this.e != null && this.e.e();
    }

    public final boolean e() {
        return this.e != null && this.e.f();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setMenu(g menu, ActionMenuPresenter outerPresenter) {
        if (menu != null || this.e != null) {
            w();
            g oldMenu = this.e.d();
            if (oldMenu != menu) {
                if (oldMenu != null) {
                    oldMenu.b(this.J);
                    oldMenu.b(this.K);
                }
                if (this.K == null) {
                    this.K = new a(this);
                }
                outerPresenter.c(true);
                if (menu != null) {
                    menu.a((n) outerPresenter, this.l);
                    menu.a(this.K, this.l);
                } else {
                    outerPresenter.a(this.l, null);
                    this.K.a(this.l, null);
                    outerPresenter.a(true);
                    this.K.a(true);
                }
                this.e.setPopupTheme(this.m);
                this.e.setPresenter(outerPresenter);
                this.J = outerPresenter;
            }
        }
    }

    public final void f() {
        if (this.e != null) {
            this.e.i();
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            u();
            if (!d(this.i)) {
                a(this.i, true);
            }
        } else if (this.i != null && d(this.i)) {
            removeView(this.i);
            this.F.remove(this.i);
        }
        if (this.i != null) {
            this.i.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(@StringRes int resId) {
        setLogoDescription(getContext().getText(resId));
    }

    public void setLogoDescription(CharSequence description) {
        if (!TextUtils.isEmpty(description)) {
            u();
        }
        if (this.i != null) {
            this.i.setContentDescription(description);
        }
    }

    private void u() {
        if (this.i == null) {
            this.i = new AppCompatImageView(getContext());
        }
    }

    public final boolean g() {
        return (this.K == null || this.K.b == null) ? false : true;
    }

    public final void h() {
        i item = this.K == null ? null : this.K.b;
        if (item != null) {
            item.collapseActionView();
        }
    }

    public final CharSequence i() {
        return this.y;
    }

    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (this.f == null) {
                Context context = getContext();
                this.f = new AppCompatTextView(context);
                this.f.setSingleLine();
                this.f.setEllipsize(TruncateAt.END);
                if (this.n != 0) {
                    this.f.setTextAppearance(context, this.n);
                }
                if (this.A != 0) {
                    this.f.setTextColor(this.A);
                }
            }
            if (!d(this.f)) {
                a(this.f, true);
            }
        } else if (this.f != null && d(this.f)) {
            removeView(this.f);
            this.F.remove(this.f);
        }
        if (this.f != null) {
            this.f.setText(title);
        }
        this.y = title;
    }

    public final CharSequence j() {
        return this.z;
    }

    public void setSubtitle(@StringRes int resId) {
        setSubtitle(getContext().getText(resId));
    }

    public void setSubtitle(CharSequence subtitle) {
        if (!TextUtils.isEmpty(subtitle)) {
            if (this.g == null) {
                Context context = getContext();
                this.g = new AppCompatTextView(context);
                this.g.setSingleLine();
                this.g.setEllipsize(TruncateAt.END);
                if (this.o != 0) {
                    this.g.setTextAppearance(context, this.o);
                }
                if (this.B != 0) {
                    this.g.setTextColor(this.B);
                }
            }
            if (!d(this.g)) {
                a(this.g, true);
            }
        } else if (this.g != null && d(this.g)) {
            removeView(this.g);
            this.F.remove(this.g);
        }
        if (this.g != null) {
            this.g.setText(subtitle);
        }
        this.z = subtitle;
    }

    public void setTitleTextAppearance(Context context, @StyleRes int resId) {
        this.n = resId;
        if (this.f != null) {
            this.f.setTextAppearance(context, resId);
        }
    }

    public void setSubtitleTextAppearance(Context context, @StyleRes int resId) {
        this.o = resId;
        if (this.g != null) {
            this.g.setTextAppearance(context, resId);
        }
    }

    public void setTitleTextColor(@ColorInt int color) {
        this.A = color;
        if (this.f != null) {
            this.f.setTextColor(color);
        }
    }

    public void setSubtitleTextColor(@ColorInt int color) {
        this.B = color;
        if (this.g != null) {
            this.g.setTextColor(color);
        }
    }

    @Nullable
    public final CharSequence k() {
        return this.h != null ? this.h.getContentDescription() : null;
    }

    public void setNavigationContentDescription(@StringRes int resId) {
        setNavigationContentDescription(resId != 0 ? getContext().getText(resId) : null);
    }

    public void setNavigationContentDescription(@Nullable CharSequence description) {
        if (!TextUtils.isEmpty(description)) {
            z();
        }
        if (this.h != null) {
            this.h.setContentDescription(description);
        }
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        setNavigationIcon(android.support.v7.content.res.b.b(getContext(), resId));
    }

    public void setNavigationIcon(@Nullable Drawable icon) {
        if (icon != null) {
            z();
            if (!d(this.h)) {
                a(this.h, true);
            }
        } else if (this.h != null && d(this.h)) {
            removeView(this.h);
            this.F.remove(this.h);
        }
        if (this.h != null) {
            this.h.setImageDrawable(icon);
        }
    }

    @Nullable
    public final Drawable l() {
        return this.h != null ? this.h.getDrawable() : null;
    }

    public void setNavigationOnClickListener(OnClickListener listener) {
        z();
        this.h.setOnClickListener(listener);
    }

    public final Menu m() {
        v();
        return this.e.c();
    }

    public void setOverflowIcon(@Nullable Drawable icon) {
        v();
        this.e.setOverflowIcon(icon);
    }

    private void v() {
        w();
        if (this.e.d() == null) {
            g menu = (g) this.e.c();
            if (this.K == null) {
                this.K = new a(this);
            }
            this.e.setExpandedActionViewsExclusive(true);
            menu.a(this.K, this.l);
        }
    }

    private void w() {
        if (this.e == null) {
            this.e = new ActionMenuView(getContext());
            this.e.setPopupTheme(this.m);
            this.e.setOnMenuItemClickListener(this.H);
            this.e.setMenuCallbacks(this.L, this.M);
            b lp = new b();
            lp.a = 8388613 | (this.c & 112);
            this.e.setLayoutParams(lp);
            a(this.e, false);
        }
    }

    public void setOnMenuItemClickListener(c listener) {
        this.d = listener;
    }

    public void setContentInsetsRelative(int contentInsetStart, int contentInsetEnd) {
        A();
        this.u.a(contentInsetStart, contentInsetEnd);
    }

    public final int n() {
        return this.u != null ? this.u.a() : 0;
    }

    public final int o() {
        return this.u != null ? this.u.b() : 0;
    }

    public void setContentInsetsAbsolute(int contentInsetLeft, int contentInsetRight) {
        A();
        this.u.b(contentInsetLeft, contentInsetRight);
    }

    public void setContentInsetStartWithNavigation(int insetStartWithNavigation) {
        if (insetStartWithNavigation < 0) {
            insetStartWithNavigation = Integer.MIN_VALUE;
        }
        if (insetStartWithNavigation != this.v) {
            this.v = insetStartWithNavigation;
            if (l() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetEndWithActions(int insetEndWithActions) {
        if (insetEndWithActions < 0) {
            insetEndWithActions = Integer.MIN_VALUE;
        }
        if (insetEndWithActions != this.w) {
            this.w = insetEndWithActions;
            if (l() != null) {
                requestLayout();
            }
        }
    }

    private int x() {
        if (l() != null) {
            return Math.max(n(), Math.max(this.v, 0));
        }
        return n();
    }

    private int y() {
        boolean hasActions = false;
        if (this.e != null) {
            g mb = this.e.d();
            if (mb == null || !mb.hasVisibleItems()) {
                hasActions = false;
            } else {
                hasActions = true;
            }
        }
        if (hasActions) {
            return Math.max(o(), Math.max(this.w, 0));
        }
        return o();
    }

    private void z() {
        if (this.h == null) {
            this.h = new AppCompatImageButton(getContext(), null, android.support.v7.appcompat.a.a.toolbarNavigationButtonStyle);
            b lp = new b();
            lp.a = 8388611 | (this.c & 112);
            this.h.setLayoutParams(lp);
        }
    }

    final void p() {
        if (this.a == null) {
            this.a = new AppCompatImageButton(getContext(), null, android.support.v7.appcompat.a.a.toolbarNavigationButtonStyle);
            this.a.setImageDrawable(this.j);
            this.a.setContentDescription(this.k);
            b lp = new b();
            lp.a = 8388611 | (this.c & 112);
            lp.b = 2;
            this.a.setLayoutParams(lp);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ Toolbar a;

                {
                    this.a = this$0;
                }

                public final void onClick(View v) {
                    this.a.h();
                }
            });
        }
    }

    private void a(View v, boolean allowHide) {
        b lp;
        LayoutParams vlp = v.getLayoutParams();
        if (vlp == null) {
            lp = new b();
        } else if (checkLayoutParams(vlp)) {
            lp = (b) vlp;
        } else {
            lp = a(vlp);
        }
        lp.b = 1;
        if (!allowHide || this.b == null) {
            addView(v, lp);
            return;
        }
        v.setLayoutParams(lp);
        this.F.add(v);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState state = new SavedState(super.onSaveInstanceState());
        if (!(this.K == null || this.K.b == null)) {
            state.b = this.K.b.getItemId();
        }
        state.c = b();
        return state;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.a());
            Menu menu = this.e != null ? this.e.d() : null;
            if (!(ss.b == 0 || this.K == null || menu == null)) {
                MenuItem item = menu.findItem(ss.b);
                if (item != null) {
                    item.expandActionView();
                }
            }
            if (ss.c) {
                removeCallbacks(this.O);
                post(this.O);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(state);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.O);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean handled = super.onTouchEvent(ev);
            if (action == 0 && !handled) {
                this.C = true;
            }
        }
        if (action == 1 || action == 3) {
            this.C = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean handled = super.onHoverEvent(ev);
            if (action == 9 && !handled) {
                this.D = true;
            }
        }
        if (action == 10 || action == 3) {
            this.D = false;
        }
        return true;
    }

    private void a(View child, int parentWidthSpec, int widthUsed, int parentHeightSpec, int heightConstraint) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int childWidthSpec = getChildMeasureSpec(parentWidthSpec, (((getPaddingLeft() + getPaddingRight()) + lp.leftMargin) + lp.rightMargin) + widthUsed, lp.width);
        int childHeightSpec = getChildMeasureSpec(parentHeightSpec, (((getPaddingTop() + getPaddingBottom()) + lp.topMargin) + lp.bottomMargin) + 0, lp.height);
        int childHeightMode = MeasureSpec.getMode(childHeightSpec);
        if (childHeightMode != ErrorDialogData.SUPPRESSED && heightConstraint >= 0) {
            if (childHeightMode != 0) {
                heightConstraint = Math.min(MeasureSpec.getSize(childHeightSpec), heightConstraint);
            }
            childHeightSpec = MeasureSpec.makeMeasureSpec(heightConstraint, ErrorDialogData.SUPPRESSED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    private int a(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed, int[] collapsingMargins) {
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int leftDiff = lp.leftMargin - collapsingMargins[0];
        int rightDiff = lp.rightMargin - collapsingMargins[1];
        int hMargins = Math.max(0, leftDiff) + Math.max(0, rightDiff);
        collapsingMargins[0] = Math.max(0, -leftDiff);
        collapsingMargins[1] = Math.max(0, -rightDiff);
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, ((getPaddingLeft() + getPaddingRight()) + hMargins) + widthUsed, lp.width), getChildMeasureSpec(parentHeightMeasureSpec, (((getPaddingTop() + getPaddingBottom()) + lp.topMargin) + lp.bottomMargin) + heightUsed, lp.height));
        return child.getMeasuredWidth() + hMargins;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int marginStartIndex;
        int marginEndIndex;
        int i;
        Object obj;
        int height = 0;
        int childState = 0;
        int[] collapsingMargins = this.G;
        if (ax.a(this)) {
            marginStartIndex = 1;
            marginEndIndex = 0;
        } else {
            marginStartIndex = 0;
            marginEndIndex = 1;
        }
        int navWidth = 0;
        if (a(this.h)) {
            a(this.h, widthMeasureSpec, 0, heightMeasureSpec, this.p);
            navWidth = this.h.getMeasuredWidth() + b(this.h);
            height = Math.max(0, this.h.getMeasuredHeight() + c(this.h));
            childState = View.combineMeasuredStates(0, this.h.getMeasuredState());
        }
        if (a(this.a)) {
            a(this.a, widthMeasureSpec, 0, heightMeasureSpec, this.p);
            navWidth = this.a.getMeasuredWidth() + b(this.a);
            height = Math.max(height, this.a.getMeasuredHeight() + c(this.a));
            childState = View.combineMeasuredStates(childState, this.a.getMeasuredState());
        }
        int contentInsetStart = x();
        int width = Math.max(contentInsetStart, navWidth) + 0;
        collapsingMargins[marginStartIndex] = Math.max(0, contentInsetStart - navWidth);
        int menuWidth = 0;
        if (a(this.e)) {
            a(this.e, widthMeasureSpec, width, heightMeasureSpec, this.p);
            menuWidth = this.e.getMeasuredWidth() + b(this.e);
            height = Math.max(height, this.e.getMeasuredHeight() + c(this.e));
            childState = View.combineMeasuredStates(childState, this.e.getMeasuredState());
        }
        int contentInsetEnd = y();
        width += Math.max(contentInsetEnd, menuWidth);
        collapsingMargins[marginEndIndex] = Math.max(0, contentInsetEnd - menuWidth);
        if (a(this.b)) {
            width += a(this.b, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
            height = Math.max(height, this.b.getMeasuredHeight() + c(this.b));
            childState = View.combineMeasuredStates(childState, this.b.getMeasuredState());
        }
        if (a(this.i)) {
            width += a(this.i, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
            height = Math.max(height, this.i.getMeasuredHeight() + c(this.i));
            childState = View.combineMeasuredStates(childState, this.i.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = getChildAt(i2);
            if (((b) child.getLayoutParams()).b == 0 && a(child)) {
                width += a(child, widthMeasureSpec, width, heightMeasureSpec, 0, collapsingMargins);
                height = Math.max(height, child.getMeasuredHeight() + c(child));
                childState = View.combineMeasuredStates(childState, child.getMeasuredState());
            }
        }
        int titleWidth = 0;
        int titleHeight = 0;
        int titleVertMargins = this.s + this.t;
        int titleHorizMargins = this.q + this.r;
        if (a(this.f)) {
            a(this.f, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleVertMargins, collapsingMargins);
            titleWidth = this.f.getMeasuredWidth() + b(this.f);
            titleHeight = this.f.getMeasuredHeight() + c(this.f);
            childState = View.combineMeasuredStates(childState, this.f.getMeasuredState());
        }
        if (a(this.g)) {
            i = titleWidth;
            titleWidth = Math.max(i, a(this.g, widthMeasureSpec, width + titleHorizMargins, heightMeasureSpec, titleHeight + titleVertMargins, collapsingMargins));
            titleHeight += this.g.getMeasuredHeight() + c(this.g);
            childState = View.combineMeasuredStates(childState, this.g.getMeasuredState());
        }
        width += titleWidth;
        height = Math.max(height, titleHeight) + (getPaddingTop() + getPaddingBottom());
        i = widthMeasureSpec;
        int measuredWidth = View.resolveSizeAndState(Math.max(width + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, -16777216 & childState);
        i = heightMeasureSpec;
        int measuredHeight = View.resolveSizeAndState(Math.max(height, getSuggestedMinimumHeight()), i, childState << 16);
        if (this.N) {
            int childCount2 = getChildCount();
            for (int i3 = 0; i3 < childCount2; i3++) {
                View childAt = getChildAt(i3);
                if (a(childAt) && childAt.getMeasuredWidth() > 0 && childAt.getMeasuredHeight() > 0) {
                    obj = null;
                    break;
                }
            }
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            measuredHeight = 0;
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int contentInsetLeft;
        int contentInsetRight;
        b lp;
        int i;
        boolean isRtl = ViewCompat.f(this) == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int left = paddingLeft;
        int right = width - paddingRight;
        int[] collapsingMargins = this.G;
        collapsingMargins[1] = 0;
        collapsingMargins[0] = 0;
        int minHeight = ViewCompat.o(this);
        int alignmentHeight = minHeight >= 0 ? Math.min(minHeight, b - t) : 0;
        if (a(this.h)) {
            if (isRtl) {
                right = b(this.h, right, collapsingMargins, alignmentHeight);
            } else {
                left = a(this.h, paddingLeft, collapsingMargins, alignmentHeight);
            }
        }
        if (a(this.a)) {
            if (isRtl) {
                right = b(this.a, right, collapsingMargins, alignmentHeight);
            } else {
                left = a(this.a, left, collapsingMargins, alignmentHeight);
            }
        }
        if (a(this.e)) {
            if (isRtl) {
                left = a(this.e, left, collapsingMargins, alignmentHeight);
            } else {
                right = b(this.e, right, collapsingMargins, alignmentHeight);
            }
        }
        if (ViewCompat.f(this) == 1) {
            contentInsetLeft = y();
        } else {
            contentInsetLeft = x();
        }
        if (ViewCompat.f(this) == 1) {
            contentInsetRight = x();
        } else {
            contentInsetRight = y();
        }
        collapsingMargins[0] = Math.max(0, contentInsetLeft - left);
        collapsingMargins[1] = Math.max(0, contentInsetRight - ((width - paddingRight) - right));
        left = Math.max(left, contentInsetLeft);
        right = Math.min(right, (width - paddingRight) - contentInsetRight);
        if (a(this.b)) {
            if (isRtl) {
                right = b(this.b, right, collapsingMargins, alignmentHeight);
            } else {
                left = a(this.b, left, collapsingMargins, alignmentHeight);
            }
        }
        if (a(this.i)) {
            if (isRtl) {
                right = b(this.i, right, collapsingMargins, alignmentHeight);
            } else {
                left = a(this.i, left, collapsingMargins, alignmentHeight);
            }
        }
        boolean layoutTitle = a(this.f);
        boolean layoutSubtitle = a(this.g);
        int titleHeight = 0;
        if (layoutTitle) {
            lp = (b) this.f.getLayoutParams();
            titleHeight = ((lp.topMargin + this.f.getMeasuredHeight()) + lp.bottomMargin) + 0;
        }
        if (layoutSubtitle) {
            lp = (b) this.g.getLayoutParams();
            titleHeight += (lp.topMargin + this.g.getMeasuredHeight()) + lp.bottomMargin;
        }
        if (layoutTitle || layoutSubtitle) {
            int titleTop;
            b toplp = (b) (layoutTitle ? this.f : this.g).getLayoutParams();
            b bottomlp = (b) (layoutSubtitle ? this.g : this.f).getLayoutParams();
            boolean titleHasWidth = (layoutTitle && this.f.getMeasuredWidth() > 0) || (layoutSubtitle && this.g.getMeasuredWidth() > 0);
            switch (this.x & 112) {
                case 48:
                    titleTop = (getPaddingTop() + toplp.topMargin) + this.s;
                    break;
                case 80:
                    titleTop = (((height - paddingBottom) - bottomlp.bottomMargin) - this.t) - titleHeight;
                    break;
                default:
                    int spaceAbove = (((height - paddingTop) - paddingBottom) - titleHeight) / 2;
                    if (spaceAbove < toplp.topMargin + this.s) {
                        spaceAbove = toplp.topMargin + this.s;
                    } else {
                        int spaceBelow = (((height - paddingBottom) - titleHeight) - spaceAbove) - paddingTop;
                        if (spaceBelow < toplp.bottomMargin + this.t) {
                            spaceAbove = Math.max(0, spaceAbove - ((bottomlp.bottomMargin + this.t) - spaceBelow));
                        }
                    }
                    titleTop = paddingTop + spaceAbove;
                    break;
            }
            int titleRight;
            int subtitleRight;
            int titleLeft;
            int titleBottom;
            int i2;
            if (isRtl) {
                int rd = (titleHasWidth ? this.q : 0) - collapsingMargins[1];
                right -= Math.max(0, rd);
                collapsingMargins[1] = Math.max(0, -rd);
                titleRight = right;
                subtitleRight = right;
                if (layoutTitle) {
                    lp = (b) this.f.getLayoutParams();
                    titleLeft = titleRight - this.f.getMeasuredWidth();
                    titleBottom = titleTop + this.f.getMeasuredHeight();
                    this.f.layout(titleLeft, titleTop, titleRight, titleBottom);
                    titleRight = titleLeft - this.r;
                    titleTop = titleBottom + lp.bottomMargin;
                }
                if (layoutSubtitle) {
                    lp = (b) this.g.getLayoutParams();
                    titleTop += lp.topMargin;
                    this.g.layout(subtitleRight - this.g.getMeasuredWidth(), titleTop, subtitleRight, titleTop + this.g.getMeasuredHeight());
                    subtitleRight -= this.r;
                    i2 = lp.bottomMargin;
                }
                if (titleHasWidth) {
                    right = Math.min(titleRight, subtitleRight);
                }
            } else {
                int ld = (titleHasWidth ? this.q : 0) - collapsingMargins[0];
                left += Math.max(0, ld);
                collapsingMargins[0] = Math.max(0, -ld);
                titleLeft = left;
                int subtitleLeft = left;
                if (layoutTitle) {
                    lp = (b) this.f.getLayoutParams();
                    titleRight = titleLeft + this.f.getMeasuredWidth();
                    titleBottom = titleTop + this.f.getMeasuredHeight();
                    this.f.layout(titleLeft, titleTop, titleRight, titleBottom);
                    titleLeft = titleRight + this.r;
                    titleTop = titleBottom + lp.bottomMargin;
                }
                if (layoutSubtitle) {
                    lp = (b) this.g.getLayoutParams();
                    titleTop += lp.topMargin;
                    subtitleRight = subtitleLeft + this.g.getMeasuredWidth();
                    this.g.layout(subtitleLeft, titleTop, subtitleRight, titleTop + this.g.getMeasuredHeight());
                    subtitleLeft = subtitleRight + this.r;
                    i2 = lp.bottomMargin;
                }
                if (titleHasWidth) {
                    left = Math.max(titleLeft, subtitleLeft);
                }
            }
        }
        a((List) this.E, 3);
        int leftViewsCount = this.E.size();
        for (i = 0; i < leftViewsCount; i++) {
            left = a((View) this.E.get(i), left, collapsingMargins, alignmentHeight);
        }
        a((List) this.E, 5);
        int rightViewsCount = this.E.size();
        for (i = 0; i < rightViewsCount; i++) {
            right = b((View) this.E.get(i), right, collapsingMargins, alignmentHeight);
        }
        a((List) this.E, 1);
        List list = this.E;
        int i3 = collapsingMargins[0];
        int i4 = collapsingMargins[1];
        int centerViewsWidth = 0;
        int size = list.size();
        int i5 = i4;
        int i6 = i3;
        i3 = 0;
        while (i3 < size) {
            View view = (View) list.get(i3);
            b bVar = (b) view.getLayoutParams();
            i6 = bVar.leftMargin - i6;
            i4 = bVar.rightMargin - i5;
            centerViewsWidth += (view.getMeasuredWidth() + Math.max(0, i6)) + Math.max(0, i4);
            i3++;
            i6 = Math.max(0, -i6);
            i5 = Math.max(0, -i4);
        }
        int centerLeft = (paddingLeft + (((width - paddingLeft) - paddingRight) / 2)) - (centerViewsWidth / 2);
        int centerRight = centerLeft + centerViewsWidth;
        if (centerLeft < left) {
            centerLeft = left;
        } else if (centerRight > right) {
            centerLeft -= centerRight - right;
        }
        int centerViewsCount = this.E.size();
        for (i = 0; i < centerViewsCount; i++) {
            centerLeft = a((View) this.E.get(i), centerLeft, collapsingMargins, alignmentHeight);
        }
        this.E.clear();
    }

    private int a(View child, int left, int[] collapsingMargins, int alignmentHeight) {
        b lp = (b) child.getLayoutParams();
        int l = lp.leftMargin - collapsingMargins[0];
        left += Math.max(0, l);
        collapsingMargins[0] = Math.max(0, -l);
        int top = a(child, alignmentHeight);
        int childWidth = child.getMeasuredWidth();
        child.layout(left, top, left + childWidth, child.getMeasuredHeight() + top);
        return (lp.rightMargin + childWidth) + left;
    }

    private int b(View child, int right, int[] collapsingMargins, int alignmentHeight) {
        b lp = (b) child.getLayoutParams();
        int r = lp.rightMargin - collapsingMargins[1];
        right -= Math.max(0, r);
        collapsingMargins[1] = Math.max(0, -r);
        int top = a(child, alignmentHeight);
        int childWidth = child.getMeasuredWidth();
        child.layout(right - childWidth, top, right, child.getMeasuredHeight() + top);
        return right - (lp.leftMargin + childWidth);
    }

    private int a(View child, int alignmentHeight) {
        int alignmentOffset;
        b lp = (b) child.getLayoutParams();
        int childHeight = child.getMeasuredHeight();
        if (alignmentHeight > 0) {
            alignmentOffset = (childHeight - alignmentHeight) / 2;
        } else {
            alignmentOffset = 0;
        }
        int i = lp.a & 112;
        switch (i) {
            case 16:
            case 48:
            case 80:
                break;
            default:
                i = this.x & 112;
                break;
        }
        switch (i) {
            case 48:
                return getPaddingTop() - alignmentOffset;
            case 80:
                return (((getHeight() - getPaddingBottom()) - childHeight) - lp.bottomMargin) - alignmentOffset;
            default:
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int spaceAbove = (((height - paddingTop) - paddingBottom) - childHeight) / 2;
                if (spaceAbove < lp.topMargin) {
                    spaceAbove = lp.topMargin;
                } else {
                    int spaceBelow = (((height - paddingBottom) - childHeight) - spaceAbove) - paddingTop;
                    if (spaceBelow < lp.bottomMargin) {
                        spaceAbove = Math.max(0, spaceAbove - (lp.bottomMargin - spaceBelow));
                    }
                }
                return paddingTop + spaceAbove;
        }
    }

    private void a(List<View> views, int gravity) {
        boolean isRtl = true;
        if (ViewCompat.f(this) != 1) {
            isRtl = false;
        }
        int childCount = getChildCount();
        int absGrav = d.a(gravity, ViewCompat.f(this));
        views.clear();
        int i;
        View child;
        b lp;
        if (isRtl) {
            for (i = childCount - 1; i >= 0; i--) {
                child = getChildAt(i);
                lp = (b) child.getLayoutParams();
                if (lp.b == 0 && a(child) && a(lp.a) == absGrav) {
                    views.add(child);
                }
            }
            return;
        }
        for (i = 0; i < childCount; i++) {
            child = getChildAt(i);
            lp = (b) child.getLayoutParams();
            if (lp.b == 0 && a(child) && a(lp.a) == absGrav) {
                views.add(child);
            }
        }
    }

    private int a(int gravity) {
        int ld = ViewCompat.f(this);
        int hGrav = d.a(gravity, ld) & 7;
        switch (hGrav) {
            case 1:
            case 3:
            case 5:
                return hGrav;
            default:
                return ld == 1 ? 5 : 3;
        }
    }

    private boolean a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    private static int b(View v) {
        int marginStart;
        int marginEnd;
        MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams();
        if (VERSION.SDK_INT >= 17) {
            marginStart = mlp.getMarginStart();
        } else {
            marginStart = mlp.leftMargin;
        }
        if (VERSION.SDK_INT >= 17) {
            marginEnd = mlp.getMarginEnd();
        } else {
            marginEnd = mlp.rightMargin;
        }
        return marginStart + marginEnd;
    }

    private static int c(View v) {
        MarginLayoutParams mlp = (MarginLayoutParams) v.getLayoutParams();
        return mlp.topMargin + mlp.bottomMargin;
    }

    private static b a(LayoutParams p) {
        if (p instanceof b) {
            return new b((b) p);
        }
        if (p instanceof android.support.v7.app.ActionBar.a) {
            return new b((android.support.v7.app.ActionBar.a) p);
        }
        if (p instanceof MarginLayoutParams) {
            return new b((MarginLayoutParams) p);
        }
        return new b(p);
    }

    protected static b q() {
        return new b();
    }

    protected boolean checkLayoutParams(LayoutParams p) {
        return super.checkLayoutParams(p) && (p instanceof b);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final r r() {
        if (this.I == null) {
            this.I = new ar(this);
        }
        return this.I;
    }

    final void s() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (!(((b) child.getLayoutParams()).b == 2 || child == this.e)) {
                removeViewAt(i);
                this.F.add(child);
            }
        }
    }

    final void t() {
        for (int i = this.F.size() - 1; i >= 0; i--) {
            addView((View) this.F.get(i));
        }
        this.F.clear();
    }

    private boolean d(View child) {
        return child.getParent() == this || this.F.contains(child);
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setCollapsible(boolean collapsible) {
        this.N = collapsible;
        requestLayout();
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void setMenuCallbacks(android.support.v7.view.menu.n.a pcb, android.support.v7.view.menu.g.a mcb) {
        this.L = pcb;
        this.M = mcb;
        if (this.e != null) {
            this.e.setMenuCallbacks(pcb, mcb);
        }
    }

    private void A() {
        if (this.u == null) {
            this.u = new ah();
        }
    }

    protected /* synthetic */ LayoutParams generateDefaultLayoutParams() {
        return new b();
    }

    public /* synthetic */ LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }
}
