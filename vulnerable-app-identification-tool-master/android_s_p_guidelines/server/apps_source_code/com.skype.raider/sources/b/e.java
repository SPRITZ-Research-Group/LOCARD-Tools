package b;

import java.io.Closeable;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;

public final class e implements Closeable {
    private final Object a;
    private final List<d> b;
    private ScheduledFuture<?> c;
    private boolean d;
    private boolean e;

    public final boolean a() {
        boolean z;
        synchronized (this.a) {
            b();
            z = this.d;
        }
        return z;
    }

    public final void close() {
        synchronized (this.a) {
            if (this.e) {
                return;
            }
            if (this.c != null) {
                this.c.cancel(true);
                this.c = null;
            }
            for (d close : this.b) {
                close.close();
            }
            this.b.clear();
            this.e = true;
        }
    }

    final void a(d registration) {
        synchronized (this.a) {
            b();
            this.b.remove(registration);
        }
    }

    public final String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", new Object[]{getClass().getName(), Integer.toHexString(hashCode()), Boolean.toString(a())});
    }

    private void b() {
        if (this.e) {
            throw new IllegalStateException("Object already closed");
        }
    }
}
