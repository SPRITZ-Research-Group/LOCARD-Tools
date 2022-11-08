package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.internal.view.c;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

@RequiresApi(14)
@RestrictTo({a.LIBRARY_GROUP})
final class u extends q implements SubMenu {
    u(Context context, c subMenu) {
        super(context, subMenu);
    }

    public final SubMenu setHeaderTitle(int titleRes) {
        ((c) this.b).setHeaderTitle(titleRes);
        return this;
    }

    public final SubMenu setHeaderTitle(CharSequence title) {
        ((c) this.b).setHeaderTitle(title);
        return this;
    }

    public final SubMenu setHeaderIcon(int iconRes) {
        ((c) this.b).setHeaderIcon(iconRes);
        return this;
    }

    public final SubMenu setHeaderIcon(Drawable icon) {
        ((c) this.b).setHeaderIcon(icon);
        return this;
    }

    public final SubMenu setHeaderView(View view) {
        ((c) this.b).setHeaderView(view);
        return this;
    }

    public final void clearHeader() {
        ((c) this.b).clearHeader();
    }

    public final SubMenu setIcon(int iconRes) {
        ((c) this.b).setIcon(iconRes);
        return this;
    }

    public final SubMenu setIcon(Drawable icon) {
        ((c) this.b).setIcon(icon);
        return this;
    }

    public final MenuItem getItem() {
        return a(((c) this.b).getItem());
    }
}
