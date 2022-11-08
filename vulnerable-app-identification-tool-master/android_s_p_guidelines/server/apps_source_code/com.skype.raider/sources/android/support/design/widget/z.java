package android.support.design.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewOutlineProvider;

final class z {
    static final d a = new d() {
        public final s a() {
            return new s(VERSION.SDK_INT >= 12 ? new u() : new t());
        }
    };
    private static final a b;

    private interface a {
        void a(View view);
    }

    private static class b implements a {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void a(View view) {
        }
    }

    private static class c implements a {
        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final void a(View view) {
            view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            b = new c();
        } else {
            b = new b();
        }
    }

    static void a(View view) {
        b.a(view);
    }

    static s a() {
        return a.a();
    }
}
