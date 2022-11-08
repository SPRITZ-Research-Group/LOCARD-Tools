package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.an;
import com.facebook.react.bridge.ap;
import com.facebook.react.common.a.a;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@DoNotStrip
public class MessageQueueThreadImpl implements MessageQueueThread {
    private final String mAssertionErrorMessage;
    private final a mHandler;
    private volatile boolean mIsFinished = false;
    private final Looper mLooper;
    private final String mName;

    private MessageQueueThreadImpl(String name, Looper looper, c exceptionHandler) {
        this.mName = name;
        this.mLooper = looper;
        this.mHandler = new a(looper, exceptionHandler);
        this.mAssertionErrorMessage = "Expected to be called from the '" + getName() + "' thread!";
    }

    @DoNotStrip
    public void runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            FLog.w("React", "Tried to enqueue runnable on already finished thread: '" + getName() + "... dropping Runnable.");
        }
        this.mHandler.post(runnable);
    }

    @DoNotStrip
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final a<T> future = new a();
        runOnQueue(new Runnable(this) {
            final /* synthetic */ MessageQueueThreadImpl c;

            public final void run() {
                try {
                    future.a(callable.call());
                } catch (Exception e) {
                    future.a(e);
                }
            }
        });
        return future;
    }

    @DoNotStrip
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @DoNotStrip
    public void assertIsOnThread() {
        an.a(isOnThread(), this.mAssertionErrorMessage);
    }

    @DoNotStrip
    public void assertIsOnThread(String message) {
        an.a(isOnThread(), this.mAssertionErrorMessage + " " + message);
    }

    @DoNotStrip
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Got interrupted waiting to join thread " + this.mName);
            }
        }
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    public static MessageQueueThreadImpl create(b spec, c exceptionHandler) {
        switch (spec.b()) {
            case MAIN_UI:
                return createForMainThread(spec.c(), exceptionHandler);
            case NEW_BACKGROUND:
                return startNewBackgroundThread(spec.c(), spec.d(), exceptionHandler);
            default:
                throw new RuntimeException("Unknown thread type: " + spec.b());
        }
    }

    private static MessageQueueThreadImpl createForMainThread(String name, c exceptionHandler) {
        MessageQueueThreadImpl mqt = new MessageQueueThreadImpl(name, Looper.getMainLooper(), exceptionHandler);
        if (ap.a()) {
            Process.setThreadPriority(-4);
        } else {
            ap.a(new Runnable() {
                public final void run() {
                    Process.setThreadPriority(-4);
                }
            });
        }
        return mqt;
    }

    private static MessageQueueThreadImpl startNewBackgroundThread(String name, long stackSize, c exceptionHandler) {
        final a<Looper> looperFuture = new a();
        new Thread(null, new Runnable() {
            public final void run() {
                Process.setThreadPriority(-4);
                Looper.prepare();
                looperFuture.a(Looper.myLooper());
                Looper.loop();
            }
        }, "mqt_" + name, stackSize).start();
        return new MessageQueueThreadImpl(name, (Looper) looperFuture.a(), exceptionHandler);
    }
}
