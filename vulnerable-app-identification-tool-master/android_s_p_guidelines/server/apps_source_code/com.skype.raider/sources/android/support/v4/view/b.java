package android.support.v4.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public abstract class b {
    private final Context a;
    private a b;
    private b c;

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public interface a {
        void b(boolean z);
    }

    public interface b {
        void a();
    }

    public abstract View a();

    public b(Context context) {
        this.a = context;
    }

    public View a(MenuItem forItem) {
        return a();
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return true;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public void a(SubMenu subMenu) {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void a(boolean isVisible) {
        if (this.b != null) {
            this.b.b(isVisible);
        }
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void a(a listener) {
        this.b = listener;
    }

    public void a(b listener) {
        if (this.c != null) {
            new StringBuilder("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ").append(getClass().getSimpleName()).append(" instance while it is still in use somewhere else?");
        }
        this.c = listener;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public final void f() {
        this.c = null;
        this.b = null;
    }
}
