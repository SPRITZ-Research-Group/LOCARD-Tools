package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityEvent;

public final class a {
    private static final c a;

    static class c {
        c() {
        }

        public void a(AccessibilityEvent event, int types) {
        }

        public int a(AccessibilityEvent event) {
            return 0;
        }
    }

    @RequiresApi(16)
    static class a extends c {
        a() {
        }
    }

    @RequiresApi(19)
    static class b extends a {
        b() {
        }

        public final void a(AccessibilityEvent event, int types) {
            event.setContentChangeTypes(types);
        }

        public final int a(AccessibilityEvent event) {
            return event.getContentChangeTypes();
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new b();
        } else if (VERSION.SDK_INT >= 16) {
            a = new a();
        } else {
            a = new c();
        }
    }

    public static void a(AccessibilityEvent event, int changeTypes) {
        a.a(event, changeTypes);
    }

    public static int a(AccessibilityEvent event) {
        return a.a(event);
    }
}
