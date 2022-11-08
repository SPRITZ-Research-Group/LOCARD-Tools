package b;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class b {
    private static final b a = new b();
    private final ExecutorService b;
    private final ScheduledExecutorService c;
    private final Executor d;

    private static class a implements Executor {
        private ThreadLocal<Integer> a;

        private a() {
            this.a = new ThreadLocal();
        }

        /* synthetic */ a(byte b) {
            this();
        }

        private int a() {
            Integer oldDepth = (Integer) this.a.get();
            if (oldDepth == null) {
                oldDepth = Integer.valueOf(0);
            }
            int newDepth = oldDepth.intValue() - 1;
            if (newDepth == 0) {
                this.a.remove();
            } else {
                this.a.set(Integer.valueOf(newDepth));
            }
            return newDepth;
        }

        public final void execute(Runnable command) {
            Integer num = (Integer) this.a.get();
            if (num == null) {
                num = Integer.valueOf(0);
            }
            int depth = num.intValue() + 1;
            this.a.set(Integer.valueOf(depth));
            if (depth <= 15) {
                try {
                    command.run();
                } catch (Throwable th) {
                    a();
                }
            } else {
                b.a().execute(command);
            }
            a();
        }
    }

    private b() {
        String property = System.getProperty("java.runtime.name");
        this.b = !(property == null ? false : property.toLowerCase(Locale.US).contains("android")) ? Executors.newCachedThreadPool() : a.a();
        this.c = Executors.newSingleThreadScheduledExecutor();
        this.d = new a();
    }

    public static ExecutorService a() {
        return a.b;
    }

    static Executor b() {
        return a.d;
    }
}
