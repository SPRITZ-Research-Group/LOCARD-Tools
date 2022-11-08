package android.support.design.internal;

import android.content.Context;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.i;
import android.support.v7.view.menu.t;
import android.view.SubMenu;

public final class a extends g {
    public a(Context context) {
        super(context);
    }

    public final SubMenu addSubMenu(int group, int id, int categoryOrder, CharSequence title) {
        i item = (i) a(group, id, categoryOrder, title);
        t subMenu = new c(e(), this, item);
        item.a(subMenu);
        return subMenu;
    }
}
