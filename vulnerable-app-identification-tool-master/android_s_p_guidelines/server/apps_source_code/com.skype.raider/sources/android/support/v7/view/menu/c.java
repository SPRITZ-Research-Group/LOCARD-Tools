package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.b;
import android.support.v4.util.a;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

abstract class c<T> extends d<T> {
    final Context a;
    private Map<b, MenuItem> c;
    private Map<android.support.v4.internal.view.c, SubMenu> d;

    c(Context context, T object) {
        super(object);
        this.a = context;
    }

    final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof b)) {
            return menuItem;
        }
        b supportMenuItem = (b) menuItem;
        if (this.c == null) {
            this.c = new a();
        }
        MenuItem wrappedItem = (MenuItem) this.c.get(menuItem);
        if (wrappedItem != null) {
            return wrappedItem;
        }
        wrappedItem = p.a(this.a, supportMenuItem);
        this.c.put(supportMenuItem, wrappedItem);
        return wrappedItem;
    }

    final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof android.support.v4.internal.view.c)) {
            return subMenu;
        }
        android.support.v4.internal.view.c supportSubMenu = (android.support.v4.internal.view.c) subMenu;
        if (this.d == null) {
            this.d = new a();
        }
        SubMenu wrappedMenu = (SubMenu) this.d.get(supportSubMenu);
        if (wrappedMenu != null) {
            return wrappedMenu;
        }
        wrappedMenu = new u(this.a, supportSubMenu);
        this.d.put(supportSubMenu, wrappedMenu);
        return wrappedMenu;
    }

    final void a() {
        if (this.c != null) {
            this.c.clear();
        }
        if (this.d != null) {
            this.d.clear();
        }
    }

    final void a(int groupId) {
        if (this.c != null) {
            Iterator<b> iterator = this.c.keySet().iterator();
            while (iterator.hasNext()) {
                if (groupId == ((MenuItem) iterator.next()).getGroupId()) {
                    iterator.remove();
                }
            }
        }
    }

    final void b(int id) {
        if (this.c != null) {
            Iterator<b> iterator = this.c.keySet().iterator();
            while (iterator.hasNext()) {
                if (id == ((MenuItem) iterator.next()).getItemId()) {
                    iterator.remove();
                    return;
                }
            }
        }
    }
}
