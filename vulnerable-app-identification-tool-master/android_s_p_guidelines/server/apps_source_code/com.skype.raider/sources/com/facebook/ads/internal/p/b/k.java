package com.facebook.ads.internal.p.b;

import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

class k {
    private final n a;
    private final a b;
    private final Object c = new Object();
    private final Object d = new Object();
    private final AtomicInteger e;
    private volatile Thread f;
    private volatile boolean g;
    private volatile int h = -1;

    private class a implements Runnable {
        final /* synthetic */ k a;

        private a(k kVar) {
            this.a = kVar;
        }

        /* synthetic */ a(k kVar, byte b) {
            this(kVar);
        }

        public final void run() {
            k.a(this.a);
        }
    }

    public k(n nVar, a aVar) {
        this.a = (n) j.a(nVar);
        this.b = (a) j.a(aVar);
        this.e = new AtomicInteger();
    }

    private void a(long j, long j2) {
        Object obj = 1;
        int i = ((j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 1 : null) != null ? 100 : (int) ((100 * j) / j2);
        Object obj2 = i != this.h ? 1 : null;
        if (j2 < 0) {
            obj = null;
        }
        if (!(obj == null || obj2 == null)) {
            a(i);
        }
        this.h = i;
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(k kVar) {
        Throwable th;
        Throwable th2;
        int i = 0;
        int a;
        int i2;
        try {
            i = kVar.b.a();
            kVar.a.a(i);
            a = kVar.a.a();
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        i2 = i;
                        i = kVar.a.a(bArr);
                        if (i == -1) {
                            break;
                        }
                        synchronized (kVar.d) {
                            if (kVar.c()) {
                                kVar.d();
                                kVar.a((long) i2, (long) a);
                                return;
                            }
                            kVar.b.a(bArr, i);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
            } catch (Throwable th4) {
                th2 = th4;
                i2 = i;
                th = th2;
            }
        } catch (Throwable th42) {
            a = -1;
            int i3 = i;
            th = th42;
            i2 = i3;
            kVar.d();
            kVar.a((long) i2, (long) a);
            throw th;
        }
    }

    private synchronized void b() {
        Object obj = null;
        synchronized (this) {
            if (!(this.f == null || this.f.getState() == State.TERMINATED)) {
                obj = 1;
            }
            if (!(this.g || this.b.d() || obj != null)) {
                this.f = new Thread(new a(), "Source reader for " + this.a);
                this.f.start();
            }
        }
    }

    private boolean c() {
        return Thread.currentThread().isInterrupted() || this.g;
    }

    private void d() {
        try {
            this.a.b();
        } catch (Throwable e) {
            l lVar = new l("Error closing source " + this.a, e);
        }
    }

    public final int a(byte[] bArr, long j) {
        int i;
        j.a(j >= 0, "Data offset must be positive!");
        j.a(true, "Length must be in range [0..buffer.length]");
        while (!this.b.d() && ((long) this.b.a()) < 8192 + j && !this.g) {
            b();
            synchronized (this.c) {
                try {
                    this.c.wait(1000);
                } catch (Throwable e) {
                    throw new l("Waiting source data is interrupted!", e);
                }
            }
            i = this.e.get();
            if (i > 0) {
                this.e.set(0);
                throw new l("Error reading source " + i + " times");
            }
        }
        i = this.b.a(bArr, j);
        if (this.b.d() && this.h != 100) {
            this.h = 100;
            a(100);
        }
        return i;
    }

    public final void a() {
        synchronized (this.d) {
            new StringBuilder("Shutdown proxy for ").append(this.a);
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            } catch (l e) {
            }
        }
    }

    protected void a(int i) {
    }
}
