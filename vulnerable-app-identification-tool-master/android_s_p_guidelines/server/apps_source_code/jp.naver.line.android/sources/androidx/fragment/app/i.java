package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public final class i {
    private final j<?> a;

    public static i a(j<?> jVar) {
        return new i(jVar);
    }

    private i(j<?> jVar) {
        this.a = jVar;
    }

    public final k a() {
        return this.a.b;
    }

    public final Fragment a(String str) {
        return this.a.b.b(str);
    }

    public final void b() {
        this.a.b.a(this.a, this.a, null);
    }

    public final View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.a.b.onCreateView(view, str, context, attributeSet);
    }

    public final void c() {
        this.a.b.m();
    }

    public final Parcelable d() {
        return this.a.b.l();
    }

    public final void a(Parcelable parcelable, z zVar) {
        this.a.b.a(parcelable, zVar);
    }

    public final z e() {
        return this.a.b.k();
    }

    public final void f() {
        this.a.b.n();
    }

    public final void g() {
        this.a.b.o();
    }

    public final void h() {
        this.a.b.p();
    }

    public final void i() {
        this.a.b.q();
    }

    public final void j() {
        this.a.b.r();
    }

    public final void k() {
        this.a.b.s();
    }

    public final void l() {
        this.a.b.u();
    }

    public final void a(boolean z) {
        this.a.b.a(z);
    }

    public final void b(boolean z) {
        this.a.b.b(z);
    }

    public final void a(Configuration configuration) {
        this.a.b.a(configuration);
    }

    public final void m() {
        this.a.b.v();
    }

    public final boolean a(Menu menu, MenuInflater menuInflater) {
        return this.a.b.a(menu, menuInflater);
    }

    public final boolean a(Menu menu) {
        return this.a.b.a(menu);
    }

    public final boolean a(MenuItem menuItem) {
        return this.a.b.a(menuItem);
    }

    public final boolean b(MenuItem menuItem) {
        return this.a.b.b(menuItem);
    }

    public final void b(Menu menu) {
        this.a.b.b(menu);
    }

    public final boolean n() {
        return this.a.b.j();
    }
}
