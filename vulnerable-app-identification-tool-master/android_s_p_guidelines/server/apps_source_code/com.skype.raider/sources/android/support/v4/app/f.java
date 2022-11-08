package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class f {
    @Nullable
    public abstract View a(@IdRes int i);

    public abstract boolean a();

    public Fragment a(Context context, String className, Bundle arguments) {
        return Fragment.instantiate(context, className, arguments);
    }
}
