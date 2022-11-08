package com.microsoft.skype.a;

import android.support.annotation.NonNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class b {

    private static final class a implements RejectedExecutionHandler {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public final void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                throw new RejectedExecutionException(e);
            }
        }
    }

    private static final class b extends ThreadPoolExecutor {
        private final AtomicInteger a = new AtomicInteger();

        public b(long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(1, 20, keepAliveTime, unit, workQueue, threadFactory);
        }

        public final int getActiveCount() {
            return this.a.get();
        }

        protected final void beforeExecute(Thread t, Runnable r) {
            this.a.incrementAndGet();
        }

        protected final void afterExecute(Runnable r, Throwable t) {
            this.a.decrementAndGet();
        }
    }

    private static final class c implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);
        private final String b;

        public c(String threadNamePrefix) {
            this.b = threadNamePrefix;
        }

        public final Thread newThread(@NonNull Runnable r) {
            return new Thread(Thread.currentThread().getThreadGroup(), r, this.b + "-" + this.a.getAndIncrement(), 0);
        }
    }

    private static final class d extends PriorityBlockingQueue<Runnable> {
        private ThreadPoolExecutor a;

        private d() {
        }

        /* synthetic */ d(byte b) {
            this();
        }

        public final /* synthetic */ boolean offer(Object obj) {
            return this.a.getActiveCount() + super.size() < this.a.getPoolSize() && super.offer((Runnable) obj);
        }

        public final /* synthetic */ void put(Object obj) {
            super.offer((Runnable) obj);
        }

        public final void a(ThreadPoolExecutor executor) {
            this.a = executor;
        }
    }
}
