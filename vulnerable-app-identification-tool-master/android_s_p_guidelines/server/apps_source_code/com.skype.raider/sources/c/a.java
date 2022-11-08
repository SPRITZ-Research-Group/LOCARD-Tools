package c;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class a extends u {
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    static a head;
    private boolean inQueue;
    @Nullable
    private a next;
    private long timeoutAt;

    private static final class a extends Thread {
        a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            while (true) {
                try {
                    synchronized (a.class) {
                        a timedOut = a.awaitTimeout();
                        if (timedOut == null) {
                        } else if (timedOut == a.head) {
                            a.head = null;
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    private static synchronized void scheduleTimeout(a node, long timeoutNanos, boolean hasDeadline) {
        synchronized (a.class) {
            if (head == null) {
                head = new a();
                new a().start();
            }
            long now = System.nanoTime();
            if (timeoutNanos != 0 && hasDeadline) {
                node.timeoutAt = Math.min(timeoutNanos, node.deadlineNanoTime() - now) + now;
            } else if (timeoutNanos != 0) {
                node.timeoutAt = now + timeoutNanos;
            } else if (hasDeadline) {
                node.timeoutAt = node.deadlineNanoTime();
            } else {
                throw new AssertionError();
            }
            long remainingNanos = node.remainingNanos(now);
            a prev = head;
            while (prev.next != null && remainingNanos >= prev.next.remainingNanos(now)) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
            if (prev == head) {
                a.class.notify();
            }
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    private static synchronized boolean cancelScheduledTimeout(a node) {
        boolean z;
        synchronized (a.class) {
            for (a prev = head; prev != null; prev = prev.next) {
                if (prev.next == node) {
                    prev.next = node.next;
                    node.next = null;
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    private long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    protected void timedOut() {
    }

    public final s sink(final s sink) {
        return new s(this) {
            final /* synthetic */ a b;

            public final void write(c source, long byteCount) throws IOException {
                v.a(source.b, 0, byteCount);
                while (byteCount > 0) {
                    long toWrite = 0;
                    p s = source.a;
                    while (toWrite < 65536) {
                        toWrite += (long) (s.c - s.b);
                        if (toWrite >= byteCount) {
                            toWrite = byteCount;
                            break;
                        }
                        s = s.f;
                    }
                    this.b.enter();
                    try {
                        sink.write(source, toWrite);
                        byteCount -= toWrite;
                        this.b.exit(true);
                    } catch (IOException e) {
                        throw this.b.exit(e);
                    } catch (Throwable th) {
                        this.b.exit(false);
                    }
                }
            }

            public final void flush() throws IOException {
                this.b.enter();
                try {
                    sink.flush();
                    this.b.exit(true);
                } catch (IOException e) {
                    throw this.b.exit(e);
                } catch (Throwable th) {
                    this.b.exit(false);
                }
            }

            public final void close() throws IOException {
                this.b.enter();
                try {
                    sink.close();
                    this.b.exit(true);
                } catch (IOException e) {
                    throw this.b.exit(e);
                } catch (Throwable th) {
                    this.b.exit(false);
                }
            }

            public final u timeout() {
                return this.b;
            }

            public final String toString() {
                return "AsyncTimeout.sink(" + sink + ")";
            }
        };
    }

    public final t source(final t source) {
        return new t(this) {
            final /* synthetic */ a b;

            public final long read(c sink, long byteCount) throws IOException {
                this.b.enter();
                try {
                    long result = source.read(sink, byteCount);
                    this.b.exit(true);
                    return result;
                } catch (IOException e) {
                    throw this.b.exit(e);
                } catch (Throwable th) {
                    this.b.exit(false);
                }
            }

            public final void close() throws IOException {
                try {
                    source.close();
                    this.b.exit(true);
                } catch (IOException e) {
                    throw this.b.exit(e);
                } catch (Throwable th) {
                    this.b.exit(false);
                }
            }

            public final u timeout() {
                return this.b;
            }

            public final String toString() {
                return "AsyncTimeout.source(" + source + ")";
            }
        };
    }

    final void exit(boolean throwOnTimeout) throws IOException {
        if (exit() && throwOnTimeout) {
            throw newTimeoutException(null);
        }
    }

    final IOException exit(IOException cause) throws IOException {
        return !exit() ? cause : newTimeoutException(cause);
    }

    protected IOException newTimeoutException(@Nullable IOException cause) {
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }

    @Nullable
    static a awaitTimeout() throws InterruptedException {
        a node = head.next;
        if (node == null) {
            long startNanos = System.nanoTime();
            a.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next != null || System.nanoTime() - startNanos < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return head;
        }
        long waitNanos = node.remainingNanos(System.nanoTime());
        if (waitNanos > 0) {
            long waitMillis = waitNanos / 1000000;
            a.class.wait(waitMillis, (int) (waitNanos - (waitMillis * 1000000)));
            return null;
        }
        head.next = node.next;
        node.next = null;
        return node;
    }
}
