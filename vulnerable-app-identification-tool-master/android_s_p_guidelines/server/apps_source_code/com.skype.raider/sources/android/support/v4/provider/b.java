package android.support.v4.provider;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({android.support.annotation.RestrictTo.a.LIBRARY_GROUP})
public final class b {
    private final Object a = new Object();
    @GuardedBy("mLock")
    private HandlerThread b;
    @GuardedBy("mLock")
    private Handler c;
    @GuardedBy("mLock")
    private int d;
    private Callback e = new Callback(this) {
        final /* synthetic */ b a;

        {
            this.a = this$0;
        }

        public final boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    b.a(this.a);
                    break;
                case 1:
                    b.a(this.a, (Runnable) msg.obj);
                    break;
            }
            return true;
        }
    };
    private final int f;
    private final int g;
    private final String h;

    public interface a<T> {
        void a(T t);
    }

    public b(String threadName) {
        this.h = threadName;
        this.g = 10;
        this.f = 10000;
        this.d = 0;
    }

    private void a(Runnable runnable) {
        synchronized (this.a) {
            if (this.b == null) {
                this.b = new HandlerThread(this.h, this.g);
                this.b.start();
                this.c = new Handler(this.b.getLooper(), this.e);
                this.d++;
            }
            this.c.removeMessages(0);
            this.c.sendMessage(this.c.obtainMessage(1, runnable));
        }
    }

    public final <T> void a(final Callable<T> callable, final a<T> reply) {
        final Handler callingHandler = new Handler();
        a(new Runnable(this) {
            final /* synthetic */ b d;

            public final void run() {
                T t;
                try {
                    t = callable.call();
                } catch (Exception e) {
                    t = null;
                }
                callingHandler.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public final void run() {
                        reply.a(t);
                    }
                });
            }
        });
    }

    public final <T> T a(Callable<T> callable, int timeoutMillis) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition cond = lock.newCondition();
        final AtomicReference<T> holder = new AtomicReference();
        final AtomicBoolean running = new AtomicBoolean(true);
        final Callable<T> callable2 = callable;
        a(new Runnable(this) {
            final /* synthetic */ b f;

            public final void run() {
                try {
                    holder.set(callable2.call());
                } catch (Exception e) {
                }
                lock.lock();
                try {
                    running.set(false);
                    cond.signal();
                } finally {
                    lock.unlock();
                }
            }
        });
        lock.lock();
        try {
            T t;
            if (running.get()) {
                long remaining = TimeUnit.MILLISECONDS.toNanos((long) timeoutMillis);
                while (true) {
                    try {
                        remaining = cond.awaitNanos(remaining);
                    } catch (InterruptedException e) {
                    }
                    if (!running.get()) {
                        t = holder.get();
                        lock.unlock();
                        break;
                    } else if (remaining <= 0) {
                        throw new InterruptedException("timeout");
                    }
                }
            }
            t = holder.get();
            return t;
        } finally {
            lock.unlock();
        }
    }

    static /* synthetic */ void a(b x0, Runnable x1) {
        x1.run();
        synchronized (x0.a) {
            x0.c.removeMessages(0);
            x0.c.sendMessageDelayed(x0.c.obtainMessage(0), (long) x0.f);
        }
    }

    static /* synthetic */ void a(b x0) {
        synchronized (x0.a) {
            if (x0.c.hasMessages(1)) {
                return;
            }
            x0.b.quit();
            x0.b = null;
            x0.c = null;
        }
    }
}
