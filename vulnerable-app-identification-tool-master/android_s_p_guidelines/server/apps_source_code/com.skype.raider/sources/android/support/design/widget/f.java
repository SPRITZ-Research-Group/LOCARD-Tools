package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.m;
import android.view.View;

final class f implements e {
    f() {
    }

    public final void a(View view, m insetsListener) {
        if (ViewCompat.u(view)) {
            ViewCompat.a(view, insetsListener);
            view.setSystemUiVisibility(1280);
        }
    }
}
