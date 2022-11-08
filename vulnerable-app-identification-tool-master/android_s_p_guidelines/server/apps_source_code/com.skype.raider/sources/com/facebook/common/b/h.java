package com.facebook.common.b;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class h<T> implements Runnable {
    protected final AtomicInteger a = new AtomicInteger(0);

    protected abstract T c() throws Exception;

    public final void run() {
        if (this.a.compareAndSet(0, 1)) {
            try {
                Object result = c();
                this.a.set(3);
                try {
                    a(result);
                } finally {
                    b(result);
                }
            } catch (Exception e) {
                this.a.set(4);
                a(e);
            }
        }
    }

    public final void a() {
        if (this.a.compareAndSet(0, 2)) {
            b();
        }
    }

    protected void a(T t) {
    }

    protected void a(Exception e) {
    }

    protected void b() {
    }

    protected void b(T t) {
    }
}
