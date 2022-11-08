package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.ViewGroup;

public final class q {
    static final c a;

    static class c {
        c() {
        }
    }

    @RequiresApi(18)
    static class a extends c {
        a() {
        }
    }

    @RequiresApi(21)
    static class b extends a {
        b() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new b();
        } else if (VERSION.SDK_INT >= 18) {
            a = new a();
        } else {
            a = new c();
        }
    }

    @Deprecated
    public static void a(ViewGroup group) {
        group.setMotionEventSplittingEnabled(false);
    }
}
