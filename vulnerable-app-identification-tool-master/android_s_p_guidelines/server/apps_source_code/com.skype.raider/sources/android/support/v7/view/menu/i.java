package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.internal.view.b;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug.CapturedViewProperty;
import android.widget.LinearLayout;

@RestrictTo({a.LIBRARY_GROUP})
public final class i implements b {
    private static String F;
    private static String G;
    private static String H;
    private static String I;
    private View A;
    private android.support.v4.view.b B;
    private OnActionExpandListener C;
    private boolean D = false;
    private ContextMenuInfo E;
    g a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private CharSequence f;
    private CharSequence g;
    private Intent h;
    private char i;
    private int j = 4096;
    private char k;
    private int l = 4096;
    private Drawable m;
    private int n = 0;
    private t o;
    private Runnable p;
    private OnMenuItemClickListener q;
    private CharSequence r;
    private CharSequence s;
    private ColorStateList t = null;
    private Mode u = null;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private int y = 16;
    private int z = 0;

    public final /* synthetic */ MenuItem setActionView(View view) {
        return a(view);
    }

    public final /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        return a(charSequence);
    }

    public final /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        return b(charSequence);
    }

    i(g menu, int group, int id, int categoryOrder, int ordering, CharSequence title, int showAsAction) {
        this.a = menu;
        this.b = id;
        this.c = group;
        this.d = categoryOrder;
        this.e = ordering;
        this.f = title;
        this.z = showAsAction;
    }

    public final boolean b() {
        if ((this.q != null && this.q.onMenuItemClick(this)) || this.a.a(this.a, (MenuItem) this)) {
            return true;
        }
        if (this.p != null) {
            this.p.run();
            return true;
        }
        if (this.h != null) {
            try {
                this.a.e().startActivity(this.h);
                return true;
            } catch (ActivityNotFoundException e) {
            }
        }
        if (this.B == null || !this.B.d()) {
            return false;
        }
        return true;
    }

    public final boolean isEnabled() {
        return (this.y & 16) != 0;
    }

    public final MenuItem setEnabled(boolean enabled) {
        if (enabled) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.a.a(false);
        return this;
    }

    public final int getGroupId() {
        return this.c;
    }

    @CapturedViewProperty
    public final int getItemId() {
        return this.b;
    }

    public final int getOrder() {
        return this.d;
    }

    public final int c() {
        return this.e;
    }

    public final Intent getIntent() {
        return this.h;
    }

    public final MenuItem setIntent(Intent intent) {
        this.h = intent;
        return this;
    }

    public final char getAlphabeticShortcut() {
        return this.k;
    }

    public final MenuItem setAlphabeticShortcut(char alphaChar) {
        if (this.k != alphaChar) {
            this.k = Character.toLowerCase(alphaChar);
            this.a.a(false);
        }
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char alphaChar, int alphaModifiers) {
        if (!(this.k == alphaChar && this.l == alphaModifiers)) {
            this.k = Character.toLowerCase(alphaChar);
            this.l = KeyEvent.normalizeMetaState(alphaModifiers);
            this.a.a(false);
        }
        return this;
    }

    public final int getAlphabeticModifiers() {
        return this.l;
    }

    public final char getNumericShortcut() {
        return this.i;
    }

    public final int getNumericModifiers() {
        return this.j;
    }

    public final MenuItem setNumericShortcut(char numericChar) {
        if (this.i != numericChar) {
            this.i = numericChar;
            this.a.a(false);
        }
        return this;
    }

    public final MenuItem setNumericShortcut(char numericChar, int numericModifiers) {
        if (!(this.i == numericChar && this.j == numericModifiers)) {
            this.i = numericChar;
            this.j = KeyEvent.normalizeMetaState(numericModifiers);
            this.a.a(false);
        }
        return this;
    }

    public final MenuItem setShortcut(char numericChar, char alphaChar) {
        this.i = numericChar;
        this.k = Character.toLowerCase(alphaChar);
        this.a.a(false);
        return this;
    }

    public final MenuItem setShortcut(char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
        this.i = numericChar;
        this.j = KeyEvent.normalizeMetaState(numericModifiers);
        this.k = Character.toLowerCase(alphaChar);
        this.l = KeyEvent.normalizeMetaState(alphaModifiers);
        this.a.a(false);
        return this;
    }

    final char d() {
        return this.a.c() ? this.k : this.i;
    }

    final String e() {
        char shortcut = d();
        if (shortcut == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(F);
        switch (shortcut) {
            case 8:
                sb.append(H);
                break;
            case 10:
                sb.append(G);
                break;
            case ' ':
                sb.append(I);
                break;
            default:
                sb.append(shortcut);
                break;
        }
        return sb.toString();
    }

    final boolean f() {
        return this.a.d() && d() != 0;
    }

    public final SubMenu getSubMenu() {
        return this.o;
    }

    public final boolean hasSubMenu() {
        return this.o != null;
    }

    public final void a(t subMenu) {
        this.o = subMenu;
        subMenu.setHeaderTitle(getTitle());
    }

    @CapturedViewProperty
    public final CharSequence getTitle() {
        return this.f;
    }

    final CharSequence a(o.a itemView) {
        if (itemView == null || !itemView.c()) {
            return getTitle();
        }
        return getTitleCondensed();
    }

    public final MenuItem setTitle(CharSequence title) {
        this.f = title;
        this.a.a(false);
        if (this.o != null) {
            this.o.setHeaderTitle(title);
        }
        return this;
    }

    public final MenuItem setTitle(int title) {
        return setTitle(this.a.e().getString(title));
    }

    public final CharSequence getTitleCondensed() {
        CharSequence ctitle = this.g != null ? this.g : this.f;
        if (VERSION.SDK_INT >= 18 || ctitle == null || (ctitle instanceof String)) {
            return ctitle;
        }
        return ctitle.toString();
    }

    public final MenuItem setTitleCondensed(CharSequence title) {
        this.g = title;
        this.a.a(false);
        return this;
    }

    public final Drawable getIcon() {
        if (this.m != null) {
            return a(this.m);
        }
        if (this.n == 0) {
            return null;
        }
        Drawable icon = android.support.v7.content.res.b.b(this.a.e(), this.n);
        this.n = 0;
        this.m = icon;
        return a(icon);
    }

    public final MenuItem setIcon(Drawable icon) {
        this.n = 0;
        this.m = icon;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public final MenuItem setIcon(int iconResId) {
        this.m = null;
        this.n = iconResId;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public final MenuItem setIconTintList(@Nullable ColorStateList iconTintList) {
        this.t = iconTintList;
        this.v = true;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public final ColorStateList getIconTintList() {
        return this.t;
    }

    public final MenuItem setIconTintMode(Mode iconTintMode) {
        this.u = iconTintMode;
        this.w = true;
        this.x = true;
        this.a.a(false);
        return this;
    }

    public final Mode getIconTintMode() {
        return this.u;
    }

    private Drawable a(Drawable icon) {
        if (icon != null && this.x && (this.v || this.w)) {
            icon = android.support.v4.graphics.drawable.a.f(icon).mutate();
            if (this.v) {
                android.support.v4.graphics.drawable.a.a(icon, this.t);
            }
            if (this.w) {
                android.support.v4.graphics.drawable.a.a(icon, this.u);
            }
            this.x = false;
        }
        return icon;
    }

    public final boolean isCheckable() {
        return (this.y & 1) == 1;
    }

    public final MenuItem setCheckable(boolean checkable) {
        int oldFlags = this.y;
        this.y = (checkable ? 1 : 0) | (this.y & -2);
        if (oldFlags != this.y) {
            this.a.a(false);
        }
        return this;
    }

    public final void a(boolean exclusive) {
        this.y = (exclusive ? 4 : 0) | (this.y & -5);
    }

    public final boolean g() {
        return (this.y & 4) != 0;
    }

    public final boolean isChecked() {
        return (this.y & 2) == 2;
    }

    public final MenuItem setChecked(boolean checked) {
        if ((this.y & 4) != 0) {
            this.a.a((MenuItem) this);
        } else {
            b(checked);
        }
        return this;
    }

    final void b(boolean checked) {
        int i;
        int oldFlags = this.y;
        int i2 = this.y & -3;
        if (checked) {
            i = 2;
        } else {
            i = 0;
        }
        this.y = i | i2;
        if (oldFlags != this.y) {
            this.a.a(false);
        }
    }

    public final boolean isVisible() {
        if (this.B == null || !this.B.b()) {
            if ((this.y & 8) != 0) {
                return false;
            }
            return true;
        } else if ((this.y & 8) == 0 && this.B.c()) {
            return true;
        } else {
            return false;
        }
    }

    final boolean c(boolean shown) {
        int oldFlags = this.y;
        this.y = (shown ? 0 : 8) | (this.y & -9);
        if (oldFlags != this.y) {
            return true;
        }
        return false;
    }

    public final MenuItem setVisible(boolean shown) {
        if (c(shown)) {
            this.a.i();
        }
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(OnMenuItemClickListener clickListener) {
        this.q = clickListener;
        return this;
    }

    public final String toString() {
        return this.f != null ? this.f.toString() : null;
    }

    final void a(ContextMenuInfo menuInfo) {
        this.E = menuInfo;
    }

    public final ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    public final boolean h() {
        return (this.y & 32) == 32;
    }

    public final boolean i() {
        return (this.z & 1) == 1;
    }

    public final boolean j() {
        return (this.z & 2) == 2;
    }

    public final void d(boolean isActionButton) {
        if (isActionButton) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    public final boolean k() {
        return (this.z & 4) == 4;
    }

    public final void setShowAsAction(int actionEnum) {
        switch (actionEnum & 3) {
            case 0:
            case 1:
            case 2:
                this.z = actionEnum;
                this.a.j();
                return;
            default:
                throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
    }

    private b a(View view) {
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && this.b > 0) {
            view.setId(this.b);
        }
        this.a.j();
        return this;
    }

    public final View getActionView() {
        if (this.A != null) {
            return this.A;
        }
        if (this.B == null) {
            return null;
        }
        this.A = this.B.a((MenuItem) this);
        return this.A;
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public final android.support.v4.view.b a() {
        return this.B;
    }

    public final b a(android.support.v4.view.b actionProvider) {
        if (this.B != null) {
            this.B.f();
        }
        this.A = null;
        this.B = actionProvider;
        this.a.a(true);
        if (this.B != null) {
            this.B.a(new android.support.v4.view.b.b(this) {
                final /* synthetic */ i a;

                {
                    this.a = this$0;
                }

                public final void a() {
                    this.a.a.i();
                }
            });
        }
        return this;
    }

    public final boolean expandActionView() {
        if (!l()) {
            return false;
        }
        if (this.C == null || this.C.onMenuItemActionExpand(this)) {
            return this.a.a(this);
        }
        return false;
    }

    public final boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        if (this.C == null || this.C.onMenuItemActionCollapse(this)) {
            return this.a.b(this);
        }
        return false;
    }

    public final boolean l() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && this.B != null) {
            this.A = this.B.a((MenuItem) this);
        }
        if (this.A != null) {
            return true;
        }
        return false;
    }

    public final void e(boolean isExpanded) {
        this.D = isExpanded;
        this.a.a(false);
    }

    public final boolean isActionViewExpanded() {
        return this.D;
    }

    public final MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
        this.C = listener;
        return this;
    }

    public final b a(CharSequence contentDescription) {
        this.r = contentDescription;
        this.a.a(false);
        return this;
    }

    public final CharSequence getContentDescription() {
        return this.r;
    }

    public final b b(CharSequence tooltipText) {
        this.s = tooltipText;
        this.a.a(false);
        return this;
    }

    public final CharSequence getTooltipText() {
        return this.s;
    }

    public final /* synthetic */ MenuItem setActionView(int i) {
        Context e = this.a.e();
        a(LayoutInflater.from(e).inflate(i, new LinearLayout(e), false));
        return this;
    }

    public final /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }
}
