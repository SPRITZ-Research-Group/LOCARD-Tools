package com.adjust.sdk;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class CustomScheduledExecutor extends ScheduledThreadPoolExecutor {

    private static class a implements Runnable {
        private Runnable a;

        a(Runnable runnable) {
            this.a = runnable;
        }

        public final void run() {
            try {
                this.a.run();
            } catch (Throwable t) {
                AdjustFactory.getLogger().error("Runnable error [%s] of type [%s]", t.getMessage(), t.getClass().getCanonicalName());
            }
        }
    }

    public CustomScheduledExecutor(final String source, boolean doKeepAlive) {
        super(1, new ThreadFactory() {
            public final Thread newThread(Runnable runnable) {
                Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                thread.setPriority(1);
                thread.setName(new StringBuilder(Constants.THREAD_PREFIX).append(thread.getName()).append("-").append(source).toString());
                thread.setDaemon(true);
                thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = this$1;
                    }

                    public final void uncaughtException(Thread th, Throwable tr) {
                        AdjustFactory.getLogger().error("Thread [%s] with error [%s]", th.getName(), tr.getMessage());
                    }
                });
                return thread;
            }
        }, new RejectedExecutionHandler() {
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                AdjustFactory.getLogger().warn("Runnable [%s] rejected from [%s] ", runnable.toString(), source);
            }
        });
        if (!doKeepAlive) {
            setKeepAliveTime(10, TimeUnit.MILLISECONDS);
            allowCoreThreadTimeOut(true);
        }
    }

    public final Future<?> submit(Runnable task) {
        return super.submit(new a(task));
    }

    public final ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return super.scheduleWithFixedDelay(new a(command), initialDelay, delay, unit);
    }

    public final ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return super.schedule(new a(command), delay, unit);
    }
}
