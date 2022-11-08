package com.google.android.gms.common.util.a;

import com.google.android.gms.common.internal.ab;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class b implements ThreadFactory {
    private final String a;
    private final int b;
    private final AtomicInteger c;
    private final ThreadFactory d;

    public b(String str) {
        this(str, (byte) 0);
    }

    private b(String str, byte b) {
        this.c = new AtomicInteger();
        this.d = Executors.defaultThreadFactory();
        this.a = (String) ab.a((Object) str, (Object) "Name must not be null");
        this.b = 0;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.d.newThread(new c(runnable, this.b));
        String str = this.a;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.c.getAndIncrement()).append("]").toString());
        return newThread;
    }
}
