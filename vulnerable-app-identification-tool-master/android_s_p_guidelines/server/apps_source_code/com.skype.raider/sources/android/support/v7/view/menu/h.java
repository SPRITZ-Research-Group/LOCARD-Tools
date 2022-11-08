package android.support.v7.view.menu;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.appcompat.a.g;
import android.support.v7.view.menu.n.a;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

final class h implements OnClickListener, OnDismissListener, OnKeyListener, a {
    e a;
    private g b;
    private AlertDialog c;
    private a d;

    public h(g menu) {
        this.b = menu;
    }

    public final void a() {
        g menu = this.b;
        AlertDialog.a builder = new AlertDialog.a(menu.e());
        this.a = new e(builder.a(), g.abc_list_menu_item_layout);
        this.a.a((a) this);
        this.b.a(this.a);
        builder.a(this.a.c(), (OnClickListener) this);
        View headerView = menu.c;
        if (headerView != null) {
            builder.a(headerView);
        } else {
            builder.a(menu.b).a(menu.a);
        }
        builder.a((OnKeyListener) this);
        this.c = builder.c();
        this.c.setOnDismissListener(this);
        LayoutParams lp = this.c.getWindow().getAttributes();
        lp.type = 1003;
        lp.flags |= 131072;
        this.c.show();
    }

    public final boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == 82 || keyCode == 4) {
            Window win;
            View decor;
            DispatcherState ds;
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                win = this.c.getWindow();
                if (win != null) {
                    decor = win.getDecorView();
                    if (decor != null) {
                        ds = decor.getKeyDispatcherState();
                        if (ds != null) {
                            ds.startTracking(event, this);
                            return true;
                        }
                    }
                }
            } else if (event.getAction() == 1 && !event.isCanceled()) {
                win = this.c.getWindow();
                if (win != null) {
                    decor = win.getDecorView();
                    if (decor != null) {
                        ds = decor.getKeyDispatcherState();
                        if (ds != null && ds.isTracking(event)) {
                            this.b.b(true);
                            dialog.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.b.performShortcut(keyCode, event, 0);
    }

    public final void onDismiss(DialogInterface dialog) {
        this.a.a(this.b, true);
    }

    public final void a(g menu, boolean allMenusAreClosing) {
        if ((allMenusAreClosing || menu == this.b) && this.c != null) {
            this.c.dismiss();
        }
        if (this.d != null) {
            this.d.a(menu, allMenusAreClosing);
        }
    }

    public final boolean a(g subMenu) {
        if (this.d != null) {
            return this.d.a(subMenu);
        }
        return false;
    }

    public final void onClick(DialogInterface dialog, int which) {
        this.b.a((i) this.a.c().getItem(which), 0);
    }
}
