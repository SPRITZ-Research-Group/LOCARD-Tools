package com.skypecam.obscura.e;

import android.os.Handler;
import android.os.Looper;
import com.skypecam.obscura.b.h;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class f implements Executor {
    private static final f a = new f();
    private final ExecutorService b = Executors.newCachedThreadPool();

    public static f a() {
        return a;
    }

    public static ExecutorService b() {
        return a.b;
    }

    public final void execute(Runnable runnable) {
        if (h.a) {
            new Handler(Looper.getMainLooper()).post(runnable);
        } else {
            this.b.execute(runnable);
        }
    }
}
