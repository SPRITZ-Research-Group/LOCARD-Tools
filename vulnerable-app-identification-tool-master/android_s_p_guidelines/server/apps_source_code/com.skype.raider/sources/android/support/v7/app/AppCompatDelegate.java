package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class AppCompatDelegate {
    private static int a = -1;
    private static boolean b = false;

    @Retention(RetentionPolicy.SOURCE)
    @interface ApplyableNightMode {
    }

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NightMode {
    }

    @Nullable
    public abstract ActionBar a();

    @Nullable
    public abstract <T extends View> T a(@IdRes int i);

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, LayoutParams layoutParams);

    public abstract void a(@Nullable CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(@LayoutRes int i);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, LayoutParams layoutParams);

    public abstract void c();

    public abstract boolean c(int i);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract boolean j();

    public static AppCompatDelegate a(Activity activity, a callback) {
        return a(activity, activity.getWindow(), callback);
    }

    public static AppCompatDelegate a(Dialog dialog, a callback) {
        return a(dialog.getContext(), dialog.getWindow(), callback);
    }

    private static AppCompatDelegate a(Context context, Window window, a callback) {
        if (VERSION.SDK_INT >= 24) {
            return new c(context, window, callback);
        }
        if (VERSION.SDK_INT >= 23) {
            return new f(context, window, callback);
        }
        if (VERSION.SDK_INT >= 14) {
            return new e(context, window, callback);
        }
        if (VERSION.SDK_INT >= 11) {
            return new d(context, window, callback);
        }
        return new AppCompatDelegateImplV9(context, window, callback);
    }

    AppCompatDelegate() {
    }

    public static int k() {
        return a;
    }

    public static boolean l() {
        return b;
    }
}
