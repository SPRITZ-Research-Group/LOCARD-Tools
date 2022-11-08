package androidx.work.impl.background.systemalarm;

import androidx.work.p;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

final class j {
    private static final String d = p.a("WorkTimer");
    final Map<String, l> a = new HashMap();
    final Map<String, k> b = new HashMap();
    final Object c = new Object();
    private final ThreadFactory e = new ThreadFactory(this) {
        final /* synthetic */ j a;
        private int b = 0;

        {
            this.a = r1;
        }

        public final Thread newThread(Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
            StringBuilder stringBuilder = new StringBuilder("WorkManager-WorkTimer-thread-");
            stringBuilder.append(this.b);
            newThread.setName(stringBuilder.toString());
            this.b++;
            return newThread;
        }
    };
    private final ScheduledExecutorService f = Executors.newSingleThreadScheduledExecutor(this.e);

    j() {
    }

    final void a(String str, k kVar) {
        synchronized (this.c) {
            p.a();
            String.format("Starting timer for %s", new Object[]{str});
            Throwable[] thArr = new Throwable[0];
            a(str);
            Runnable lVar = new l(this, str);
            this.a.put(str, lVar);
            this.b.put(str, kVar);
            this.f.schedule(lVar, 600000, TimeUnit.MILLISECONDS);
        }
    }

    final void a(String str) {
        synchronized (this.c) {
            if (((l) this.a.remove(str)) != null) {
                p.a();
                String.format("Stopping timer for %s", new Object[]{str});
                Throwable[] thArr = new Throwable[0];
                this.b.remove(str);
            }
        }
    }
}
