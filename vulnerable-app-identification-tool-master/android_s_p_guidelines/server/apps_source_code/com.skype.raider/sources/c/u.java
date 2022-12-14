package c;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class u {
    public static final u NONE = new u() {
        public final u timeout(long timeout, TimeUnit unit) {
            return this;
        }

        public final u deadlineNanoTime(long deadlineNanoTime) {
            return this;
        }

        public final void throwIfReached() throws IOException {
        }
    };
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    public u timeout(long timeout, TimeUnit unit) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout < 0: " + timeout);
        } else if (unit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.timeoutNanos = unit.toNanos(timeout);
            return this;
        }
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public u deadlineNanoTime(long deadlineNanoTime) {
        this.hasDeadline = true;
        this.deadlineNanoTime = deadlineNanoTime;
        return this;
    }

    public final u deadline(long duration, TimeUnit unit) {
        if (duration <= 0) {
            throw new IllegalArgumentException("duration <= 0: " + duration);
        } else if (unit != null) {
            return deadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
        } else {
            throw new IllegalArgumentException("unit == null");
        }
    }

    public u clearTimeout() {
        this.timeoutNanos = 0;
        return this;
    }

    public u clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public void throwIfReached() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public final void waitUntilNotified(Object monitor) throws InterruptedIOException {
        try {
            boolean hasDeadline = hasDeadline();
            long timeoutNanos = timeoutNanos();
            if (hasDeadline || timeoutNanos != 0) {
                long waitNanos;
                long start = System.nanoTime();
                if (hasDeadline && timeoutNanos != 0) {
                    waitNanos = Math.min(timeoutNanos, deadlineNanoTime() - start);
                } else if (hasDeadline) {
                    waitNanos = deadlineNanoTime() - start;
                } else {
                    waitNanos = timeoutNanos;
                }
                long elapsedNanos = 0;
                if (waitNanos > 0) {
                    long waitMillis = waitNanos / 1000000;
                    monitor.wait(waitMillis, (int) (waitNanos - (1000000 * waitMillis)));
                    elapsedNanos = System.nanoTime() - start;
                }
                if (elapsedNanos >= waitNanos) {
                    throw new InterruptedIOException("timeout");
                }
                return;
            }
            monitor.wait();
        } catch (InterruptedException e) {
            throw new InterruptedIOException("interrupted");
        }
    }
}
