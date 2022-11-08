package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.a.j;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ActionBar {

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayOptions {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NavigationMode {
    }

    public static class a extends MarginLayoutParams {
        public int a;

        public a(@NonNull Context c, AttributeSet attrs) {
            super(c, attrs);
            this.a = 0;
            TypedArray a = c.obtainStyledAttributes(attrs, j.ActionBarLayout);
            this.a = a.getInt(j.ActionBarLayout_android_layout_gravity, 0);
            a.recycle();
        }

        public a() {
            super(-2, -2);
            this.a = 0;
            this.a = 8388627;
        }

        public a(a source) {
            super(source);
            this.a = 0;
            this.a = source.a;
        }

        public a(LayoutParams source) {
            super(source);
            this.a = 0;
        }
    }

    @Deprecated
    public static abstract class b {
        public abstract Drawable a();

        public abstract CharSequence b();

        public abstract View c();

        public abstract CharSequence d();
    }

    public abstract int a();

    public Context b() {
        return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void a(boolean enabled) {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void b(boolean enabled) {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void a(Configuration config) {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void c(boolean visible) {
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public android.support.v7.view.b a(android.support.v7.view.b.a callback) {
        return null;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public boolean c() {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public boolean d() {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public boolean e() {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public boolean a(KeyEvent event) {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public boolean a(int keyCode, KeyEvent ev) {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public boolean f() {
        return false;
    }

    @RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
    public void a(CharSequence title) {
    }

    void g() {
    }
}
