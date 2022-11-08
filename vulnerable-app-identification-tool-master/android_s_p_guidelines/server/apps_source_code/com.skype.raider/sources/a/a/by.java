package a.a;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.appboy.f.c;

public abstract class by<T> {
    private static final String a = c.a(by.class);
    private final Object b = new Object();
    private boolean c = false;

    @VisibleForTesting
    @NonNull
    abstract T a();

    @VisibleForTesting
    abstract void a(T t, boolean z);

    public final T b() {
        T t;
        synchronized (this.b) {
            if (this.c) {
                c.c(a, "Received call to export dirty object, but the cache was already locked.");
                t = null;
            } else {
                this.c = true;
                t = a();
            }
        }
        return t;
    }

    public final boolean b(T t, boolean z) {
        boolean z2 = false;
        synchronized (this.b) {
            if (this.c) {
                a(t, z);
                this.c = false;
                synchronized (this) {
                    c.e(a, "Notifying confirmAndUnlock listeners");
                    notifyAll();
                }
                z2 = true;
            } else {
                c.f(a, "Tried to confirm outboundObject [" + t + "] with success [" + z + "], but the cache wasn't locked, so not doing anything.");
            }
        }
        return z2;
    }

    public final boolean c() {
        boolean z;
        synchronized (this.b) {
            z = this.c;
        }
        return z;
    }
}
