package com.facebook.imagepipeline.core;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class k implements ThreadFactory {
    private final int a = 10;
    private final String b;
    private final boolean c;
    private final AtomicInteger d = new AtomicInteger(1);

    public k(String prefix) {
        this.b = prefix;
        this.c = true;
    }

    public final Thread newThread(final Runnable runnable) {
        String name;
        Runnable wrapperRunnable = new Runnable(this) {
            final /* synthetic */ k b;

            public final void run() {
                try {
                    Process.setThreadPriority(this.b.a);
                } catch (Throwable th) {
                }
                runnable.run();
            }
        };
        if (this.c) {
            name = this.b + "-" + this.d.getAndIncrement();
        } else {
            name = this.b;
        }
        return new Thread(wrapperRunnable, name);
    }
}
