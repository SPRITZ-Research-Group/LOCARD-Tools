package com.skypecam.obscura.b;

import android.os.Handler;
import android.os.Looper;
import com.skypecam.obscura.e.g;
import java.util.concurrent.CountDownLatch;

public abstract class t<H extends Handler> extends Thread {
    public static final Runnable a = new Runnable() {
        public final void run() {
            if (Thread.currentThread() instanceof t) {
                Looper.myLooper().quit();
            }
        }
    };
    private final CountDownLatch b = new CountDownLatch(1);
    private H c;
    private final String d;

    protected abstract H a() throws b;

    public t(String name) {
        super(name);
        this.d = name;
    }

    public final H b() {
        try {
            this.b.await();
            return this.c;
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted on thread start. Handler thread is in undefined state");
        }
    }

    public void run() {
        Looper.prepare();
        try {
            this.c = a();
        } catch (b e) {
        }
        this.b.countDown();
        try {
            Looper.loop();
        } catch (Throwable t) {
            g.a().a(this.d, "Event loop exited with throwable", t);
        }
    }
}
