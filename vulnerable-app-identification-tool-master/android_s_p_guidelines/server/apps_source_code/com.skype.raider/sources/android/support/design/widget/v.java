package android.support.design.widget;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;

final class v {
    private static final a a;

    private interface a {
        void a(ViewGroup viewGroup, View view, Rect rect);
    }

    private static class b implements a {
        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        public final void a(ViewGroup parent, View child, Rect rect) {
            parent.offsetDescendantRectToMyCoords(child, rect);
        }
    }

    private static class c implements a {
        private c() {
        }

        /* synthetic */ c(byte b) {
            this();
        }

        public final void a(ViewGroup parent, View child, Rect rect) {
            w.a(parent, child, rect);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            a = new c();
        } else {
            a = new b();
        }
    }

    static void a(ViewGroup parent, View descendant, Rect out) {
        out.set(0, 0, descendant.getWidth(), descendant.getHeight());
        a.a(parent, descendant, out);
    }
}
