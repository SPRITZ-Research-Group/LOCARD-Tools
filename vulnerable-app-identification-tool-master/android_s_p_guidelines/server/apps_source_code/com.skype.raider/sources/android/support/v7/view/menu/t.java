package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RestrictTo({a.LIBRARY_GROUP})
public class t extends g implements SubMenu {
    private g d;
    private i e;

    public t(Context context, g parentMenu, i item) {
        super(context);
        this.d = parentMenu;
        this.e = item;
    }

    public void setQwertyMode(boolean isQwerty) {
        this.d.setQwertyMode(isQwerty);
    }

    public final boolean c() {
        return this.d.c();
    }

    public final boolean d() {
        return this.d.d();
    }

    public final Menu r() {
        return this.d;
    }

    public MenuItem getItem() {
        return this.e;
    }

    public final void a(g.a callback) {
        this.d.a(callback);
    }

    public final g o() {
        return this.d.o();
    }

    final boolean a(g menu, MenuItem item) {
        return super.a(menu, item) || this.d.a(menu, item);
    }

    public SubMenu setIcon(Drawable icon) {
        this.e.setIcon(icon);
        return this;
    }

    public SubMenu setIcon(int iconRes) {
        this.e.setIcon(iconRes);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable icon) {
        return (SubMenu) super.a(icon);
    }

    public SubMenu setHeaderIcon(int iconRes) {
        return (SubMenu) super.b(iconRes);
    }

    public SubMenu setHeaderTitle(CharSequence title) {
        return (SubMenu) super.a(title);
    }

    public SubMenu setHeaderTitle(int titleRes) {
        return (SubMenu) super.a(titleRes);
    }

    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.a(view);
    }

    public final boolean a(i item) {
        return this.d.a(item);
    }

    public final boolean b(i item) {
        return this.d.b(item);
    }

    public final String b() {
        int itemId = this.e != null ? this.e.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.b() + ":" + itemId;
    }
}
