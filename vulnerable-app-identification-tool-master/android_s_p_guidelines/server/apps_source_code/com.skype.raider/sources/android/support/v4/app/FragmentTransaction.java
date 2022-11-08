package android.support.v4.app;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class FragmentTransaction {

    @RestrictTo({a.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Transit {
    }

    public abstract FragmentTransaction a(@IdRes int i, Fragment fragment, @Nullable String str);

    public abstract FragmentTransaction a(Fragment fragment);

    public abstract FragmentTransaction a(Fragment fragment, String str);

    public abstract int b();

    public abstract FragmentTransaction b(Fragment fragment);

    public abstract int c();

    public abstract FragmentTransaction c(Fragment fragment);
}
