package a.a;

import com.appboy.f.c;
import java.lang.Thread.UncaughtExceptionHandler;

public class t implements UncaughtExceptionHandler {
    private static final String a = c.a(t.class);
    private b b;

    public t(b bVar) {
        this.b = bVar;
    }

    public final void a(b bVar) {
        this.b = bVar;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        try {
            if (this.b != null) {
                c.c(a, "Uncaught exception from thread. Publishing as Throwable event.", throwable);
                this.b.a(throwable, Throwable.class);
            }
        } catch (Throwable e) {
            c.c(a, "Failed to log throwable.", e);
        }
    }
}
