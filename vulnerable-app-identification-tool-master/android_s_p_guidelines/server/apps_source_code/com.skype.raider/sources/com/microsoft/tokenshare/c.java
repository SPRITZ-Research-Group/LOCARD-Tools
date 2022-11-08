package com.microsoft.tokenshare;

import android.os.Handler;
import android.os.Looper;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

abstract class c<T> {
    private final AtomicReference<b<T>> a;
    private final Handler b;
    private final Timer c;

    protected abstract void a();

    private c(b<T> callback, Handler handler) {
        this.a = new AtomicReference(null);
        this.a.set(callback);
        this.b = handler;
        this.c = new Timer();
        this.c.schedule(new TimerTask(this) {
            final /* synthetic */ c a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.a();
            }
        }, 5000);
    }

    public c(b<T> callback) {
        this(callback, Looper.myLooper() == Looper.getMainLooper() ? new Handler() : null);
    }

    public final void a(final T result) {
        final b<T> callback = (b) this.a.getAndSet(null);
        if (callback == null) {
            h.a("CallbackExecutor");
            return;
        }
        this.c.cancel();
        if (this.b != null) {
            this.b.post(new Runnable(this) {
                final /* synthetic */ c c;

                public final void run() {
                    callback.a(result);
                }
            });
        } else {
            callback.a((Object) result);
        }
    }

    public final void a(final Throwable ex) {
        final b<T> callback = (b) this.a.getAndSet(null);
        if (callback == null) {
            h.a("CallbackExecutor");
            return;
        }
        this.c.cancel();
        h.a("CallbackExecutor", "Connection query failed", ex);
        if (this.b != null) {
            this.b.post(new Runnable(this) {
                final /* synthetic */ c c;

                public final void run() {
                    callback.a(ex);
                }
            });
        } else {
            callback.a(ex);
        }
    }
}
