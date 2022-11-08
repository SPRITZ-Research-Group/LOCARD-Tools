package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerOnce {
    private Runnable command;
    private CustomScheduledExecutor executor;
    private ILogger logger = AdjustFactory.getLogger();
    private String name;
    private ScheduledFuture waitingTask;

    public TimerOnce(Runnable command, String name) {
        this.name = name;
        this.executor = new CustomScheduledExecutor(name, true);
        this.command = command;
    }

    public void startIn(long fireIn) {
        cancel(false);
        String fireInSeconds = Util.SecondsDisplayFormat.format(((double) fireIn) / 1000.0d);
        this.logger.verbose("%s starting. Launching in %s seconds", this.name, fireInSeconds);
        this.waitingTask = this.executor.schedule(new Runnable(this) {
            final /* synthetic */ TimerOnce a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.logger.verbose("%s fired", this.a.name);
                this.a.command.run();
                this.a.waitingTask = null;
            }
        }, fireIn, TimeUnit.MILLISECONDS);
    }

    public long getFireIn() {
        if (this.waitingTask == null) {
            return 0;
        }
        return this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
    }

    private void cancel(boolean mayInterruptIfRunning) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(mayInterruptIfRunning);
        }
        this.waitingTask = null;
        this.logger.verbose("%s canceled", this.name);
    }

    public void cancel() {
        cancel(false);
    }

    public void teardown() {
        cancel(true);
        this.executor = null;
    }
}
