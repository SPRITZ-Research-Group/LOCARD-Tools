package android.support.v7.widget;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.view.menu.n;
import android.view.Menu;
import android.view.Window.Callback;

@RestrictTo({a.LIBRARY_GROUP})
public interface q {
    void a(int i);

    boolean c();

    boolean d();

    boolean e();

    boolean f();

    boolean g();

    void h();

    void setMenu(Menu menu, n.a aVar);

    void setMenuPrepared();

    void setWindowCallback(Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
