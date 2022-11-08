package com.google.android.gms.internal.measurement;

import android.os.Process;
import com.google.android.gms.common.internal.ab;
import java.util.concurrent.BlockingQueue;

final class bw extends Thread {
    private final Object a = new Object();
    private final BlockingQueue<bv<?>> b;
    private final /* synthetic */ bs c;

    public bw(bs bsVar, String str, BlockingQueue<bv<?>> blockingQueue) {
        this.c = bsVar;
        ab.a((Object) str);
        ab.a((Object) blockingQueue);
        this.b = blockingQueue;
        setName(str);
    }

    private final void a(InterruptedException interruptedException) {
        this.c.q().y().a(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    public final void a() {
        synchronized (this.a) {
            this.a.notifyAll();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        Object obj = null;
        while (obj == null) {
            try {
                this.c.h.acquire();
                obj = 1;
            } catch (InterruptedException e) {
                a(e);
            }
        }
        int threadPriority = Process.getThreadPriority(Process.myTid());
        while (true) {
            bv bvVar = (bv) this.b.poll();
            if (bvVar != null) {
                Process.setThreadPriority(bvVar.a ? threadPriority : 10);
                bvVar.run();
            } else {
                synchronized (this.a) {
                    if (this.b.peek() == null && !this.c.i) {
                        try {
                            this.a.wait(30000);
                        } catch (InterruptedException e2) {
                            a(e2);
                        }
                    }
                }
                try {
                    synchronized (this.c.g) {
                        if (this.b.peek() == null) {
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this.c.g) {
                        this.c.h.release();
                        this.c.g.notifyAll();
                        if (this == this.c.a) {
                            this.c.a = null;
                        } else if (this == this.c.b) {
                            this.c.b = null;
                        } else {
                            this.c.q().v().a("Current scheduler thread is neither worker nor network");
                        }
                    }
                }
            }
        }
    }
}
