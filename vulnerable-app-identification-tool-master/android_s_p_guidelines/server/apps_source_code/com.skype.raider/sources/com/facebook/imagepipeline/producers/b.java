package com.facebook.imagepipeline.producers;

import com.facebook.common.logging.FLog;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class b<T> implements Consumer<T> {
    private boolean a = false;

    protected abstract void a();

    protected abstract void a(T t, int i);

    protected abstract void a(Throwable th);

    public static boolean a(int status) {
        return (status & 1) == 1;
    }

    public static boolean b(int status) {
        return !a(status);
    }

    public static boolean a(int status, int flag) {
        return (status & flag) == flag;
    }

    public static boolean c(int status) {
        return (status & 10) != 0;
    }

    public final synchronized void b(@Nullable T newResult, int status) {
        if (!this.a) {
            this.a = a(status);
            try {
                a((Object) newResult, status);
            } catch (Exception e) {
                a(e);
            }
        }
        return;
    }

    public final synchronized void b(Throwable t) {
        if (!this.a) {
            this.a = true;
            try {
                a(t);
            } catch (Exception e) {
                a(e);
            }
        }
        return;
    }

    public final synchronized void b() {
        if (!this.a) {
            this.a = true;
            try {
                a();
            } catch (Exception e) {
                a(e);
            }
        }
        return;
    }

    public final synchronized void b(float progress) {
        if (!this.a) {
            try {
                a(progress);
            } catch (Exception e) {
                a(e);
            }
        }
        return;
    }

    protected void a(float progress) {
    }

    private void a(Exception e) {
        FLog.wtf(getClass(), "unhandled exception", (Throwable) e);
    }
}
