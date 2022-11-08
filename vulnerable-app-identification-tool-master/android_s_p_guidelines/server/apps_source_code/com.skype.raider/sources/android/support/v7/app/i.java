package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.menu.g;
import android.support.v7.widget.r;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window.Callback;
import java.util.ArrayList;

final class i extends ActionBar {
    r a;
    Callback b;
    private boolean c;
    private boolean d;
    private ArrayList<Object> e;
    private final Runnable f;

    private final class a implements android.support.v7.view.menu.n.a {
        final /* synthetic */ i a;
        private boolean b;

        a(i iVar) {
            this.a = iVar;
        }

        public final boolean a(g subMenu) {
            if (this.a.b == null) {
                return false;
            }
            this.a.b.onMenuOpened(108, subMenu);
            return true;
        }

        public final void a(g menu, boolean allMenusAreClosing) {
            if (!this.b) {
                this.b = true;
                this.a.a.k();
                if (this.a.b != null) {
                    this.a.b.onPanelClosed(108, menu);
                }
                this.b = false;
            }
        }
    }

    private final class b implements android.support.v7.view.menu.g.a {
        final /* synthetic */ i a;

        b(i iVar) {
            this.a = iVar;
        }

        public final boolean a(g menu, MenuItem item) {
            return false;
        }

        public final void a(g menu) {
            if (this.a.b == null) {
                return;
            }
            if (this.a.a.f()) {
                this.a.b.onPanelClosed(108, menu);
            } else if (this.a.b.onPreparePanel(0, null, menu)) {
                this.a.b.onMenuOpened(108, menu);
            }
        }
    }

    public final Context b() {
        return this.a.b();
    }

    public final void a(boolean enabled) {
    }

    public final void b(boolean enabled) {
    }

    public final void a(Configuration config) {
        super.a(config);
    }

    public final void a(CharSequence title) {
        this.a.a(title);
    }

    public final int a() {
        return this.a.l();
    }

    public final boolean c() {
        return this.a.h();
    }

    public final boolean d() {
        return this.a.i();
    }

    public final boolean e() {
        this.a.a().removeCallbacks(this.f);
        ViewCompat.a(this.a.a(), this.f);
        return true;
    }

    public final boolean f() {
        if (!this.a.c()) {
            return false;
        }
        this.a.d();
        return true;
    }

    public final boolean a(KeyEvent event) {
        if (event.getAction() == 1) {
            c();
        }
        return true;
    }

    public final boolean a(int keyCode, KeyEvent ev) {
        if (!this.c) {
            this.a.a(new a(this), new b(this));
            this.c = true;
        }
        Menu menu = this.a.n();
        if (menu == null) {
            return false;
        }
        boolean z;
        if (KeyCharacterMap.load(ev != null ? ev.getDeviceId() : -1).getKeyboardType() != 1) {
            z = true;
        } else {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(keyCode, ev, 0);
    }

    final void g() {
        this.a.a().removeCallbacks(this.f);
    }

    public final void c(boolean isVisible) {
        if (isVisible != this.d) {
            this.d = isVisible;
            int count = this.e.size();
            for (int i = 0; i < count; i++) {
                this.e.get(i);
            }
        }
    }
}
