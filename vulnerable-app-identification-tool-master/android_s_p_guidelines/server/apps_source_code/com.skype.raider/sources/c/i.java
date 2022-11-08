package c;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class i extends u {
    private u a;

    public i(u delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = delegate;
    }

    public final u a() {
        return this.a;
    }

    public final i a(u delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = delegate;
        return this;
    }

    public final u timeout(long timeout, TimeUnit unit) {
        return this.a.timeout(timeout, unit);
    }

    public final long timeoutNanos() {
        return this.a.timeoutNanos();
    }

    public final boolean hasDeadline() {
        return this.a.hasDeadline();
    }

    public final long deadlineNanoTime() {
        return this.a.deadlineNanoTime();
    }

    public final u deadlineNanoTime(long deadlineNanoTime) {
        return this.a.deadlineNanoTime(deadlineNanoTime);
    }

    public final u clearTimeout() {
        return this.a.clearTimeout();
    }

    public final u clearDeadline() {
        return this.a.clearDeadline();
    }

    public final void throwIfReached() throws IOException {
        this.a.throwIfReached();
    }
}
