package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.util.l;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public final class g {
    private final h<?> a;

    public static final g a(h<?> callbacks) {
        return new g(callbacks);
    }

    private g(h<?> callbacks) {
        this.a = callbacks;
    }

    public final i a() {
        return this.a.d;
    }

    @Nullable
    public final Fragment a(String who) {
        return this.a.d.b(who);
    }

    public final void b() {
        this.a.d.a(this.a, this.a, null);
    }

    public final View a(View parent, String name, Context context, AttributeSet attrs) {
        return this.a.d.onCreateView(parent, name, context, attrs);
    }

    public final void c() {
        this.a.d.i();
    }

    public final Parcelable d() {
        return this.a.d.h();
    }

    public final void a(Parcelable state, k nonConfig) {
        this.a.d.a(state, nonConfig);
    }

    public final k e() {
        return this.a.d.g();
    }

    public final void f() {
        this.a.d.j();
    }

    public final void g() {
        this.a.d.k();
    }

    public final void h() {
        this.a.d.l();
    }

    public final void i() {
        this.a.d.m();
    }

    public final void j() {
        this.a.d.n();
    }

    public final void k() {
        this.a.d.o();
    }

    public final void l() {
        this.a.d.p();
    }

    public final void m() {
        this.a.d.r();
    }

    public final void a(boolean isInMultiWindowMode) {
        this.a.d.a(isInMultiWindowMode);
    }

    public final void b(boolean isInPictureInPictureMode) {
        this.a.d.b(isInPictureInPictureMode);
    }

    public final void a(Configuration newConfig) {
        this.a.d.a(newConfig);
    }

    public final void n() {
        this.a.d.s();
    }

    public final boolean a(Menu menu, MenuInflater inflater) {
        return this.a.d.a(menu, inflater);
    }

    public final boolean a(Menu menu) {
        return this.a.d.a(menu);
    }

    public final boolean a(MenuItem item) {
        return this.a.d.a(item);
    }

    public final boolean b(MenuItem item) {
        return this.a.d.b(item);
    }

    public final void b(Menu menu) {
        this.a.d.b(menu);
    }

    public final boolean o() {
        return this.a.d.f();
    }

    public final void p() {
        this.a.j();
    }

    public final void c(boolean retain) {
        this.a.a(retain);
    }

    public final void q() {
        this.a.k();
    }

    public final void r() {
        this.a.l();
    }

    public final l<String, p> s() {
        return this.a.m();
    }

    public final void a(l<String, p> loaderManagers) {
        this.a.a((l) loaderManagers);
    }

    public final void a(String prefix, FileDescriptor fd, PrintWriter writer) {
        this.a.a(prefix, writer);
    }
}
