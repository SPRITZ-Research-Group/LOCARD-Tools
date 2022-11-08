package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class Dispatcher {
    @Nullable
    private ExecutorService executorService;
    @Nullable
    private Runnable idleCallback;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final Deque<AsyncCall> readyAsyncCalls = new ArrayDeque();
    private final Deque<AsyncCall> runningAsyncCalls = new ArrayDeque();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque();

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public final synchronized ExecutorService executorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    public final synchronized void setMaxRequests(int maxRequests) {
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("max < 1: " + maxRequests);
        }
        this.maxRequests = maxRequests;
        promoteCalls();
    }

    public final synchronized int getMaxRequests() {
        return this.maxRequests;
    }

    public final synchronized void setMaxRequestsPerHost(int maxRequestsPerHost) {
        if (maxRequestsPerHost <= 0) {
            throw new IllegalArgumentException("max < 1: " + maxRequestsPerHost);
        }
        this.maxRequestsPerHost = maxRequestsPerHost;
        promoteCalls();
    }

    public final synchronized int getMaxRequestsPerHost() {
        return this.maxRequestsPerHost;
    }

    public final synchronized void setIdleCallback(@Nullable Runnable idleCallback) {
        this.idleCallback = idleCallback;
    }

    final synchronized void enqueue(AsyncCall call) {
        if (this.runningAsyncCalls.size() >= this.maxRequests || runningCallsForHost(call) >= this.maxRequestsPerHost) {
            this.readyAsyncCalls.add(call);
        } else {
            this.runningAsyncCalls.add(call);
            executorService().execute(call);
        }
    }

    public final synchronized void cancelAll() {
        for (AsyncCall asyncCall : this.readyAsyncCalls) {
            asyncCall.get().cancel();
        }
        for (AsyncCall asyncCall2 : this.runningAsyncCalls) {
            asyncCall2.get().cancel();
        }
        for (RealCall cancel : this.runningSyncCalls) {
            cancel.cancel();
        }
    }

    private void promoteCalls() {
        if (this.runningAsyncCalls.size() < this.maxRequests && !this.readyAsyncCalls.isEmpty()) {
            Iterator<AsyncCall> i = this.readyAsyncCalls.iterator();
            while (i.hasNext()) {
                AsyncCall call = (AsyncCall) i.next();
                if (runningCallsForHost(call) < this.maxRequestsPerHost) {
                    i.remove();
                    this.runningAsyncCalls.add(call);
                    executorService().execute(call);
                }
                if (this.runningAsyncCalls.size() >= this.maxRequests) {
                    return;
                }
            }
        }
    }

    private int runningCallsForHost(AsyncCall call) {
        int result = 0;
        for (AsyncCall c : this.runningAsyncCalls) {
            if (!c.get().forWebSocket && c.host().equals(call.host())) {
                result++;
            }
        }
        return result;
    }

    final synchronized void executed(RealCall call) {
        this.runningSyncCalls.add(call);
    }

    final void finished(AsyncCall call) {
        finished(this.runningAsyncCalls, call, true);
    }

    final void finished(RealCall call) {
        finished(this.runningSyncCalls, call, false);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        int runningCallsCount;
        Runnable idleCallback;
        synchronized (this) {
            if (calls.remove(call)) {
                if (promoteCalls) {
                    promoteCalls();
                }
                runningCallsCount = runningCallsCount();
                idleCallback = this.idleCallback;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (runningCallsCount == 0 && idleCallback != null) {
            idleCallback.run();
        }
    }

    public final synchronized List<Call> queuedCalls() {
        List<Call> result;
        result = new ArrayList();
        for (AsyncCall asyncCall : this.readyAsyncCalls) {
            result.add(asyncCall.get());
        }
        return Collections.unmodifiableList(result);
    }

    public final synchronized List<Call> runningCalls() {
        List<Call> result;
        result = new ArrayList();
        result.addAll(this.runningSyncCalls);
        for (AsyncCall asyncCall : this.runningAsyncCalls) {
            result.add(asyncCall.get());
        }
        return Collections.unmodifiableList(result);
    }

    public final synchronized int queuedCallsCount() {
        return this.readyAsyncCalls.size();
    }

    public final synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }
}
