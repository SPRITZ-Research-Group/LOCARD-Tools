package android.support.v7.view;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v7.view.menu.g;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

@RestrictTo({a.LIBRARY_GROUP})
public final class e extends b implements g.a {
    private Context a;
    private ActionBarContextView b;
    private b.a c;
    private WeakReference<View> d;
    private boolean e;
    private boolean f;
    private g g;

    public e(Context context, ActionBarContextView view, b.a callback, boolean isFocusable) {
        this.a = context;
        this.b = view;
        this.c = callback;
        this.g = new g(view.getContext()).a();
        this.g.a((g.a) this);
        this.f = isFocusable;
    }

    public final void b(CharSequence title) {
        this.b.setTitle(title);
    }

    public final void a(CharSequence subtitle) {
        this.b.setSubtitle(subtitle);
    }

    public final void a(int resId) {
        b(this.a.getString(resId));
    }

    public final void b(int resId) {
        a(this.a.getString(resId));
    }

    public final void a(boolean titleOptional) {
        super.a(titleOptional);
        this.b.setTitleOptional(titleOptional);
    }

    public final boolean h() {
        return this.b.f();
    }

    public final void a(View view) {
        this.b.setCustomView(view);
        this.d = view != null ? new WeakReference(view) : null;
    }

    public final void d() {
        this.c.b(this, this.g);
    }

    public final void c() {
        if (!this.e) {
            this.e = true;
            this.b.sendAccessibilityEvent(32);
            this.c.a(this);
        }
    }

    public final Menu b() {
        return this.g;
    }

    public final CharSequence f() {
        return this.b.b();
    }

    public final CharSequence g() {
        return this.b.c();
    }

    public final View i() {
        return this.d != null ? (View) this.d.get() : null;
    }

    public final MenuInflater a() {
        return new g(this.b.getContext());
    }

    public final boolean a(g menu, MenuItem item) {
        return this.c.a((b) this, item);
    }

    public final void a(g menu) {
        d();
        this.b.a();
    }
}
