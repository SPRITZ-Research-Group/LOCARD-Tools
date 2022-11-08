package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.internal.view.b;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class a implements b {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private int i = 4096;
    private char j;
    private int k = 4096;
    private Drawable l;
    private int m = 0;
    private Context n;
    private OnMenuItemClickListener o;
    private CharSequence p;
    private CharSequence q;
    private ColorStateList r = null;
    private Mode s = null;
    private boolean t = false;
    private boolean u = false;
    private int v = 16;

    public a(Context context, CharSequence title) {
        this.n = context;
        this.a = 16908332;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = title;
    }

    public final char getAlphabeticShortcut() {
        return this.j;
    }

    public final int getAlphabeticModifiers() {
        return this.k;
    }

    public final int getGroupId() {
        return this.b;
    }

    public final Drawable getIcon() {
        return this.l;
    }

    public final Intent getIntent() {
        return this.g;
    }

    public final int getItemId() {
        return this.a;
    }

    public final ContextMenuInfo getMenuInfo() {
        return null;
    }

    public final char getNumericShortcut() {
        return this.h;
    }

    public final int getNumericModifiers() {
        return this.i;
    }

    public final int getOrder() {
        return this.d;
    }

    public final SubMenu getSubMenu() {
        return null;
    }

    public final CharSequence getTitle() {
        return this.e;
    }

    public final CharSequence getTitleCondensed() {
        return this.f != null ? this.f : this.e;
    }

    public final boolean hasSubMenu() {
        return false;
    }

    public final boolean isCheckable() {
        return (this.v & 1) != 0;
    }

    public final boolean isChecked() {
        return (this.v & 2) != 0;
    }

    public final boolean isEnabled() {
        return (this.v & 16) != 0;
    }

    public final boolean isVisible() {
        return (this.v & 8) == 0;
    }

    public final MenuItem setAlphabeticShortcut(char alphaChar) {
        this.j = Character.toLowerCase(alphaChar);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char alphaChar, int alphaModifiers) {
        this.j = Character.toLowerCase(alphaChar);
        this.k = KeyEvent.normalizeMetaState(alphaModifiers);
        return this;
    }

    public final MenuItem setCheckable(boolean checkable) {
        this.v = (checkable ? 1 : 0) | (this.v & -2);
        return this;
    }

    public final MenuItem setChecked(boolean checked) {
        this.v = (checked ? 2 : 0) | (this.v & -3);
        return this;
    }

    public final MenuItem setEnabled(boolean enabled) {
        this.v = (enabled ? 16 : 0) | (this.v & -17);
        return this;
    }

    public final MenuItem setIcon(Drawable icon) {
        this.l = icon;
        this.m = 0;
        b();
        return this;
    }

    public final MenuItem setIcon(int iconRes) {
        this.m = iconRes;
        this.l = android.support.v4.content.a.a(this.n, iconRes);
        b();
        return this;
    }

    public final MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    public final MenuItem setNumericShortcut(char numericChar) {
        this.h = numericChar;
        return this;
    }

    public final MenuItem setNumericShortcut(char numericChar, int numericModifiers) {
        this.h = numericChar;
        this.i = KeyEvent.normalizeMetaState(numericModifiers);
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener) {
        this.o = menuItemClickListener;
        return this;
    }

    public final MenuItem setShortcut(char numericChar, char alphaChar) {
        this.h = numericChar;
        this.j = Character.toLowerCase(alphaChar);
        return this;
    }

    public final MenuItem setShortcut(char numericChar, char alphaChar, int numericModifiers, int alphaModifiers) {
        this.h = numericChar;
        this.i = KeyEvent.normalizeMetaState(numericModifiers);
        this.j = Character.toLowerCase(alphaChar);
        this.k = KeyEvent.normalizeMetaState(alphaModifiers);
        return this;
    }

    public final MenuItem setTitle(CharSequence title) {
        this.e = title;
        return this;
    }

    public final MenuItem setTitle(int title) {
        this.e = this.n.getResources().getString(title);
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence title) {
        this.f = title;
        return this;
    }

    public final MenuItem setVisible(boolean visible) {
        this.v = (visible ? 0 : 8) | (this.v & 8);
        return this;
    }

    public final void setShowAsAction(int show) {
    }

    public final View getActionView() {
        return null;
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public final android.support.v4.view.b a() {
        return null;
    }

    public final b a(android.support.v4.view.b actionProvider) {
        throw new UnsupportedOperationException();
    }

    public final boolean expandActionView() {
        return false;
    }

    public final boolean collapseActionView() {
        return false;
    }

    public final boolean isActionViewExpanded() {
        return false;
    }

    public final MenuItem setOnActionExpandListener(OnActionExpandListener listener) {
        throw new UnsupportedOperationException();
    }

    public final b a(CharSequence contentDescription) {
        this.p = contentDescription;
        return this;
    }

    public final CharSequence getContentDescription() {
        return this.p;
    }

    public final b b(CharSequence tooltipText) {
        this.q = tooltipText;
        return this;
    }

    public final CharSequence getTooltipText() {
        return this.q;
    }

    public final MenuItem setIconTintList(@Nullable ColorStateList iconTintList) {
        this.r = iconTintList;
        this.t = true;
        b();
        return this;
    }

    public final ColorStateList getIconTintList() {
        return this.r;
    }

    public final MenuItem setIconTintMode(Mode iconTintMode) {
        this.s = iconTintMode;
        this.u = true;
        b();
        return this;
    }

    public final Mode getIconTintMode() {
        return this.s;
    }

    private void b() {
        if (this.l == null) {
            return;
        }
        if (this.t || this.u) {
            this.l = android.support.v4.graphics.drawable.a.f(this.l);
            this.l = this.l.mutate();
            if (this.t) {
                android.support.v4.graphics.drawable.a.a(this.l, this.r);
            }
            if (this.u) {
                android.support.v4.graphics.drawable.a.a(this.l, this.s);
            }
        }
    }

    public final /* synthetic */ MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        this.q = charSequence;
        return this;
    }

    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        this.p = charSequence;
        return this;
    }
}
