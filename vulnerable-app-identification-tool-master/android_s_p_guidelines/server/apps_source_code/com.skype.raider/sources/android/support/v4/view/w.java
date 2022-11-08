package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.WindowInsets;

public final class w {
    private final Object a;

    private w(Object insets) {
        this.a = insets;
    }

    public final int a() {
        if (VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public final int b() {
        if (VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetTop();
        }
        return 0;
    }

    public final int c() {
        if (VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetRight();
        }
        return 0;
    }

    public final int d() {
        if (VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.a).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public final boolean e() {
        if (VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.a).isConsumed();
        }
        return false;
    }

    public final w f() {
        if (VERSION.SDK_INT >= 20) {
            return new w(((WindowInsets) this.a).consumeSystemWindowInsets());
        }
        return null;
    }

    public final w a(int left, int top, int right, int bottom) {
        if (VERSION.SDK_INT >= 20) {
            return new w(((WindowInsets) this.a).replaceSystemWindowInsets(left, top, right, bottom));
        }
        return null;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        w other = (w) o;
        if (this.a != null) {
            return this.a.equals(other.a);
        }
        if (other.a != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.a == null ? 0 : this.a.hashCode();
    }

    static w a(Object insets) {
        return insets == null ? null : new w(insets);
    }

    static Object a(w insets) {
        return insets == null ? null : insets.a;
    }
}
