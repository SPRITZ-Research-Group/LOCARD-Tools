package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.widget.EdgeEffect;

public final class f {
    private static final b a;

    static class b {
        b() {
        }

        public void a(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
            edgeEffect.onPull(deltaDistance);
        }
    }

    @RequiresApi(21)
    static class a extends b {
        a() {
        }

        public final void a(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
            edgeEffect.onPull(deltaDistance, displacement);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new a();
        } else {
            a = new b();
        }
    }

    public static void a(EdgeEffect edgeEffect, float deltaDistance, float displacement) {
        a.a(edgeEffect, deltaDistance, displacement);
    }
}
