package b;

import java.io.Closeable;

public final class d implements Closeable {
    private final Object a;
    private e b;
    private Runnable c;
    private boolean d;

    public final void close() {
        synchronized (this.a) {
            if (this.d) {
                return;
            }
            this.d = true;
            this.b.a(this);
            this.b = null;
            this.c = null;
        }
    }
}
