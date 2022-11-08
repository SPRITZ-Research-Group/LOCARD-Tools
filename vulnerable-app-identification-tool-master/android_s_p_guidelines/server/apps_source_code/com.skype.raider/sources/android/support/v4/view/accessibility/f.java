package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityRecord;

public final class f {
    private static final c a;
    private final AccessibilityRecord b;

    static class c {
        c() {
        }

        public void a(AccessibilityRecord record, int maxScrollX) {
        }

        public void b(AccessibilityRecord record, int maxScrollY) {
        }
    }

    @RequiresApi(15)
    static class a extends c {
        a() {
        }

        public final void a(AccessibilityRecord record, int maxScrollX) {
            record.setMaxScrollX(maxScrollX);
        }

        public final void b(AccessibilityRecord record, int maxScrollY) {
            record.setMaxScrollY(maxScrollY);
        }
    }

    @RequiresApi(16)
    static class b extends a {
        b() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            a = new b();
        } else if (VERSION.SDK_INT >= 15) {
            a = new a();
        } else {
            a = new c();
        }
    }

    public static void a(AccessibilityRecord record, int maxScrollX) {
        a.a(record, maxScrollX);
    }

    public static void b(AccessibilityRecord record, int maxScrollY) {
        a.b(record, maxScrollY);
    }

    @Deprecated
    public final int hashCode() {
        return this.b == null ? 0 : this.b.hashCode();
    }

    @Deprecated
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        f other = (f) obj;
        if (this.b == null) {
            if (other.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(other.b)) {
            return true;
        } else {
            return false;
        }
    }
}
