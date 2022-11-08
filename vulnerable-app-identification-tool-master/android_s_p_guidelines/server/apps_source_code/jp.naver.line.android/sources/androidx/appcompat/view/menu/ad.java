package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.Menu;
import android.view.MenuItem;
import defpackage.fb;
import defpackage.fc;

public final class ad {
    public static Menu a(Context context, fb fbVar) {
        return new ae(context, fbVar);
    }

    public static MenuItem a(Context context, fc fcVar) {
        if (VERSION.SDK_INT >= 16) {
            return new v(context, fcVar);
        }
        return new q(context, fcVar);
    }
}
