package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.h;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

public final class au {
    private boolean a = false;
    private final Deque<Runnable> b;
    private final Executor c;

    public au(Executor executor) {
        this.c = (Executor) h.a((Object) executor);
        this.b = new ArrayDeque();
    }

    public final synchronized void a(Runnable runnable) {
        if (this.a) {
            this.b.add(runnable);
        } else {
            this.c.execute(runnable);
        }
    }

    public final synchronized void b(Runnable runnable) {
        this.b.remove(runnable);
    }
}
