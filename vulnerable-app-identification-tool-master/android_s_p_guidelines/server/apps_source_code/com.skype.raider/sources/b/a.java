package b;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class a {
    static final int a;
    static final int b = ((e * 2) + 1);
    private static final a c = new a();
    private static final int e;
    private final Executor d = new a();

    private static class a implements Executor {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void execute(Runnable command) {
            new Handler(Looper.getMainLooper()).post(command);
        }
    }

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        e = availableProcessors;
        a = availableProcessors + 1;
    }

    private a() {
    }

    public static ExecutorService a() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(a, b, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
        if (VERSION.SDK_INT >= 9) {
            executor.allowCoreThreadTimeOut(true);
        }
        return executor;
    }

    public static Executor b() {
        return c.d;
    }
}
