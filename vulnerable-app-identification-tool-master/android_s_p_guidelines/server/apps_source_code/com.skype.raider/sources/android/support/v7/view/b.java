package android.support.v7.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class b {
    private Object a;
    private boolean b;

    public interface a {
        void a(b bVar);

        boolean a(b bVar, Menu menu);

        boolean a(b bVar, MenuItem menuItem);

        boolean b(b bVar, Menu menu);
    }

    public abstract MenuInflater a();

    public abstract void a(int i);

    public abstract void a(View view);

    public abstract void a(CharSequence charSequence);

    public abstract Menu b();

    public abstract void b(int i);

    public abstract void b(CharSequence charSequence);

    public abstract void c();

    public abstract void d();

    public abstract CharSequence f();

    public abstract CharSequence g();

    public abstract View i();

    public final void a(Object tag) {
        this.a = tag;
    }

    public final Object j() {
        return this.a;
    }

    public void a(boolean titleOptional) {
        this.b = titleOptional;
    }

    public final boolean k() {
        return this.b;
    }

    public boolean h() {
        return false;
    }
}
