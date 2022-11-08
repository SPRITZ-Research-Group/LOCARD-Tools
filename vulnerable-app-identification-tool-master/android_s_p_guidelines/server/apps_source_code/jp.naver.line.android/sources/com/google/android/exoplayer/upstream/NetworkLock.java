package com.google.android.exoplayer.upstream;

import com.google.android.gms.common.api.Api.BaseClientBuilder;
import java.io.IOException;
import java.util.PriorityQueue;

public final class NetworkLock {
    public static final int DOWNLOAD_PRIORITY = 10;
    public static final int STREAMING_PRIORITY = 0;
    public static final NetworkLock instance = new NetworkLock();
    private int highestPriority = BaseClientBuilder.API_PRIORITY_OTHER;
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue();

    public class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder("Priority too low [priority=");
            stringBuilder.append(i);
            stringBuilder.append(", highest=");
            stringBuilder.append(i2);
            stringBuilder.append("]");
            super(stringBuilder.toString());
        }
    }

    private NetworkLock() {
    }

    public final void proceed(int i) throws InterruptedException {
        synchronized (this.lock) {
            while (this.highestPriority < i) {
                this.lock.wait();
            }
        }
    }

    public final boolean proceedNonBlocking(int i) {
        boolean z;
        synchronized (this.lock) {
            z = this.highestPriority >= i;
        }
        return z;
    }

    public final void proceedOrThrow(int i) throws PriorityTooLowException {
        synchronized (this.lock) {
            if (this.highestPriority >= i) {
            } else {
                throw new PriorityTooLowException(i, this.highestPriority);
            }
        }
    }

    public final void add(int i) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i));
            this.highestPriority = Math.min(this.highestPriority, i);
        }
    }

    public final void remove(int i) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i));
            this.highestPriority = this.queue.isEmpty() ? BaseClientBuilder.API_PRIORITY_OTHER : ((Integer) this.queue.peek()).intValue();
            this.lock.notifyAll();
        }
    }
}
