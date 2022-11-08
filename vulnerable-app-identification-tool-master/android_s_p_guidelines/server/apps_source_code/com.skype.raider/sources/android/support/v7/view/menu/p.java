package android.support.v7.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.a;
import android.support.v4.internal.view.b;
import android.view.Menu;
import android.view.MenuItem;

@RestrictTo({a.LIBRARY_GROUP})
public final class p {
    public static Menu a(Context context, android.support.v4.internal.view.a supportMenu) {
        return new q(context, supportMenu);
    }

    public static MenuItem a(Context context, b supportMenuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new k(context, supportMenuItem);
        }
        return new j(context, supportMenuItem);
    }
}
