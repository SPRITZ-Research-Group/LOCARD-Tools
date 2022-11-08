package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.view.s;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.n;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window.Callback;

@RestrictTo({a.LIBRARY_GROUP})
public interface r {
    s a(int i, long j);

    ViewGroup a();

    void a(int i);

    void a(Drawable drawable);

    void a(n.a aVar, g.a aVar2);

    void a(ScrollingTabContainerView scrollingTabContainerView);

    void a(Menu menu, n.a aVar);

    void a(Callback callback);

    void a(CharSequence charSequence);

    void a(boolean z);

    Context b();

    void b(int i);

    void c(int i);

    boolean c();

    void d();

    void d(int i);

    boolean e();

    boolean f();

    boolean g();

    boolean h();

    boolean i();

    void j();

    void k();

    int l();

    int m();

    Menu n();
}
