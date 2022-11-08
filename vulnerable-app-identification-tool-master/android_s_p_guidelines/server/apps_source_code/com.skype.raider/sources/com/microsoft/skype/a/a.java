package com.microsoft.skype.a;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class a implements Executor {
    public static final a a = new a("Main", b.LOW, true);
    public static final a b = new a("Low", b.LOW, false);
    public static final a c = new a("Default", b.DEFAULT, false);
    public static final a d = new a("High", b.HIGH, false);
    private static final Logger g = Logger.getLogger("DispatchQueue");
    private static final Executor h;
    private static final ThreadLocal<List<a>> i = new ThreadLocal();
    private static final Handler j = new Handler(Looper.getMainLooper());
    private static final AtomicInteger k = new AtomicInteger(0);
    private static final AtomicInteger l = new AtomicInteger(0);
    private static final Random m = new Random();
    public final String e;
    public final boolean f;
    private final Object n = new Object();
    private final Queue<com.microsoft.skype.b.b<Runnable, Boolean, Integer>> o = new ArrayDeque();
    private final b p;
    private boolean q;

    private final class a implements Comparable<a>, Runnable {
        final /* synthetic */ a a;
        private final Runnable b;
        private final int c;

        public final /* bridge */ /* synthetic */ int compareTo(@NonNull Object obj) {
            return this.c - ((a) obj).c;
        }

        public a(a aVar, int priority, Runnable runnable) {
            this.a = aVar;
            this.b = runnable;
            this.c = priority;
        }

        public final void run() {
            this.b.run();
        }
    }

    public enum b {
        LOW(100),
        DEFAULT(10),
        HIGH(1);
        
        private final int d;

        private b(int value) {
            this.d = value;
        }

        public final int a() {
            return this.d;
        }
    }

    static {
        long convert = TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES);
        Object dVar = new d();
        Executor bVar = new b(convert, TimeUnit.MILLISECONDS, dVar, new c("GCD"));
        bVar.setRejectedExecutionHandler(new a());
        dVar.a(bVar);
        h = bVar;
    }

    private a(String name, b priority, boolean serial) {
        this.e = name;
        this.f = serial;
        this.p = priority;
        this.q = false;
    }

    public static a a(String name, b priority) {
        return new a(name, priority, true);
    }

    public static boolean a(a queue) {
        return c().contains(queue);
    }

    public final String toString() {
        String str = "%s GCD queue - \"%s\"";
        Object[] objArr = new Object[2];
        objArr[0] = this.f ? "serial" : "concurrent";
        objArr[1] = this.e;
        return String.format(str, objArr);
    }

    public final void a(Runnable runnable) {
        boolean runItNow;
        int causeId = m.nextInt();
        synchronized (this.n) {
            if (this.q) {
                runItNow = false;
            } else {
                runItNow = true;
                this.q = true;
            }
        }
        if (runItNow) {
            try {
                b(runnable, this, causeId);
            } finally {
                b();
            }
        } else {
            final Object syncObject = new Object();
            synchronized (syncObject) {
                a(true, causeId, new Runnable(this) {
                    final /* synthetic */ a b;

                    public final void run() {
                        synchronized (syncObject) {
                            syncObject.notify();
                        }
                    }
                });
                try {
                    int soonToBeBlockedCount = k.incrementAndGet();
                    while (true) {
                        int currentMaxBlocked = l.get();
                        if (currentMaxBlocked < soonToBeBlockedCount) {
                            if (l.compareAndSet(currentMaxBlocked, soonToBeBlockedCount)) {
                                break;
                            }
                        }
                        break;
                    }
                    syncObject.wait();
                    k.decrementAndGet();
                    b(runnable, this, causeId);
                    b();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    b();
                }
            }
        }
    }

    public final void b(Runnable runnable) {
        a(false, m.nextInt(), runnable);
    }

    public final void c(Runnable runnable) {
        if ((this == a && Looper.myLooper() == Looper.getMainLooper()) || a(this)) {
            m.nextInt();
            runnable.run();
            return;
        }
        b(runnable);
    }

    public final void d(Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            m.nextInt();
            runnable.run();
            return;
        }
        b(runnable);
    }

    public final void execute(@NonNull Runnable command) {
        b(command);
    }

    private void a(boolean skipSerialResart, final int causeId, final Runnable runnable) {
        if (this == a) {
            j.post(new Runnable(this) {
                final /* synthetic */ a c;

                public final void run() {
                    a.b(runnable, this.c, causeId);
                }
            });
            return;
        }
        synchronized (this.n) {
            this.o.add(new com.microsoft.skype.b.b(runnable, Boolean.valueOf(skipSerialResart), Integer.valueOf(causeId)));
            if (this.f && !this.q) {
                this.q = true;
                a();
            } else if (!this.f) {
                a();
            }
        }
    }

    private void a() {
        h.execute(new a(this, this.p.a(), new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = this$0;
            }

            public final void run() {
                com.microsoft.skype.b.b<Runnable, Boolean, Integer> runnableInfo;
                synchronized (this.a.n) {
                    runnableInfo = (com.microsoft.skype.b.b) this.a.o.poll();
                }
                a.b((Runnable) runnableInfo.a, this.a, ((Integer) runnableInfo.c).intValue());
                if (this.a.f && !((Boolean) runnableInfo.b).booleanValue()) {
                    a aVar = this.a;
                    ((Integer) runnableInfo.c).intValue();
                    aVar.b();
                }
            }
        }));
    }

    private void b() {
        synchronized (this.n) {
            if (this.o.isEmpty()) {
                this.q = false;
            } else {
                a();
            }
        }
    }

    private static void b(Runnable runnable, a queue, int causeId) {
        List<a> queues = c();
        queues.add(0, queue);
        if (queue.f) {
            g.log(Level.FINE, "about to execute on " + queue.e + " (" + causeId + ")");
        }
        try {
            runnable.run();
            g.log(Level.FINE, "ran as " + queue.e + " (" + causeId + ")");
        } finally {
            if (queue.f) {
                g.log(Level.FINE, "finished executing on " + queue.e + " (" + causeId + ")");
            }
            queues.remove(0);
        }
    }

    private static List<a> c() {
        List<a> result = (List) i.get();
        if (result != null) {
            return result;
        }
        result = new ArrayList();
        i.set(result);
        return result;
    }
}
