package com.facebook.ads.internal.q.a;

import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class m implements ThreadFactory {
    protected final AtomicLong a = new AtomicLong();
    private int b = Thread.currentThread().getPriority();

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(null, runnable, String.format(Locale.ENGLISH, "com.facebook.ads thread-%d %tF %<tT", new Object[]{Long.valueOf(this.a.incrementAndGet()), Long.valueOf(System.currentTimeMillis())}), 0);
        thread.setPriority(this.b);
        return thread;
    }
}
