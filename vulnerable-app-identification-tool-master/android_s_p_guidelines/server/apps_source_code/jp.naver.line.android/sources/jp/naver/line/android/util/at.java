package jp.naver.line.android.util;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class at {
    private static bt a = new bt(10, 64, false, new bu());
    private static final ExecutorService[] b;
    private static final ScheduledExecutorService[] c;

    static {
        int length = au.values().length;
        b = new ExecutorService[length];
        c = new ScheduledExecutorService[length];
    }

    public static void a(Runnable runnable, long j) {
        au auVar = au.BASEACTIVITY;
        b(auVar).schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    public static void c(Runnable runnable) {
        a(au.BASEACTIVITY, runnable);
    }

    public static void a(au auVar, Runnable runnable) {
        a(auVar).execute(runnable);
    }

    public static ExecutorService a() {
        return a(au.BASEACTIVITY);
    }

    public static ExecutorService a(au auVar) {
        int ordinal = auVar.ordinal();
        ExecutorService executorService = b[ordinal];
        if (executorService != null && !executorService.isShutdown()) {
            return executorService;
        }
        ExecutorService executorService2;
        synchronized (b) {
            if (b[ordinal] == null || b[ordinal].isShutdown()) {
                b[ordinal] = h();
            }
            executorService2 = b[ordinal];
        }
        return executorService2;
    }

    public static ExecutorService b() {
        return a;
    }

    @Deprecated
    public static ExecutorService c() {
        return a;
    }

    public static ScheduledExecutorService d() {
        return b(au.BASEACTIVITY);
    }

    public static ScheduledExecutorService b(au auVar) {
        int ordinal = auVar.ordinal();
        ScheduledExecutorService scheduledExecutorService = c[ordinal];
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            return scheduledExecutorService;
        }
        ScheduledExecutorService scheduledExecutorService2;
        synchronized (c) {
            if (c[ordinal] == null || c[ordinal].isShutdown()) {
                c[ordinal] = f();
            }
            scheduledExecutorService2 = c[ordinal];
        }
        return scheduledExecutorService2;
    }

    public static ExecutorService a(int i) {
        return new az(a, i);
    }

    @Deprecated
    public static ExecutorService b(int i) {
        return a(i);
    }

    public static ExecutorService a(int i, Queue<bq<?>> queue) {
        return new az(a, i, queue);
    }

    @Deprecated
    public static ExecutorService e() {
        return a;
    }

    public static ScheduledExecutorService f() {
        return new cy(a);
    }

    @Deprecated
    public static ScheduledExecutorService g() {
        return f();
    }

    public static ExecutorService h() {
        return new az(a, 1);
    }

    @Deprecated
    public static ExecutorService i() {
        return h();
    }

    public static void a(Runnable runnable) {
        a.execute(runnable);
    }

    @Deprecated
    public static void b(Runnable runnable) {
        a.execute(runnable);
    }
}
