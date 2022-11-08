package b;

import java.util.Locale;

public final class c {
    private final e a;

    public final boolean a() {
        return this.a.a();
    }

    public final String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(this.a.a())});
    }
}
