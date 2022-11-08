package androidx.core.app;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;

public class f {
    public Bundle a() {
        return null;
    }

    public static f a(Context context, int i, int i2) {
        if (VERSION.SDK_INT >= 16) {
            return new g(ActivityOptions.makeCustomAnimation(context, i, i2));
        }
        return new f();
    }

    protected f() {
    }
}
