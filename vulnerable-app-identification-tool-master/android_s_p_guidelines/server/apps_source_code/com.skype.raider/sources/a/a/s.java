package a.a;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class s implements ThreadFactory {
    private final AtomicInteger a;
    private final String b;
    private UncaughtExceptionHandler c;

    public s() {
        this.a = new AtomicInteger(1);
        this.b = s.class.getSimpleName();
    }

    public s(String str) {
        this.a = new AtomicInteger(1);
        this.b = str;
    }

    public final void a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.c = uncaughtExceptionHandler;
    }

    public Thread newThread(Runnable runnable) {
        if (this.c == null) {
            throw new IllegalStateException("No UncaughtExceptionHandler. You must call setUncaughtExceptionHandler before creating a new thread");
        }
        Thread thread = new Thread(runnable, this.b + " #" + this.a.getAndIncrement());
        thread.setUncaughtExceptionHandler(this.c);
        return thread;
    }
}
