package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import defpackage.bcz;
import defpackage.bdd;
import defpackage.bdk;

final class b extends HandlerThread implements Callback {
    private bdd a;
    private Handler b;
    private Error c;
    private RuntimeException d;
    private DummySurface e;

    public b() {
        super("dummySurface");
    }

    public final com.google.android.exoplayer2.video.DummySurface a(int r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.exoplayer2.video.b.a(int):com.google.android.exoplayer2.video.DummySurface. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:59)
	at java.lang.Iterable.forEach(Iterable.java:75)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r3 = this;
        r3.start();
        r0 = new android.os.Handler;
        r1 = r3.getLooper();
        r0.<init>(r1, r3);
        r3.b = r0;
        r0 = new bdd;
        r1 = r3.b;
        r0.<init>(r1);
        r3.a = r0;
        monitor-enter(r3);
        r0 = r3.b;	 Catch:{ all -> 0x0056 }
        r1 = 1;	 Catch:{ all -> 0x0056 }
        r2 = 0;	 Catch:{ all -> 0x0056 }
        r4 = r0.obtainMessage(r1, r4, r2);	 Catch:{ all -> 0x0056 }
        r4.sendToTarget();	 Catch:{ all -> 0x0056 }
    L_0x0023:
        r4 = r3.e;	 Catch:{ all -> 0x0056 }
        if (r4 != 0) goto L_0x0035;	 Catch:{ all -> 0x0056 }
    L_0x0027:
        r4 = r3.d;	 Catch:{ all -> 0x0056 }
        if (r4 != 0) goto L_0x0035;	 Catch:{ all -> 0x0056 }
    L_0x002b:
        r4 = r3.c;	 Catch:{ all -> 0x0056 }
        if (r4 != 0) goto L_0x0035;
    L_0x002f:
        r3.wait();	 Catch:{ InterruptedException -> 0x0033 }
        goto L_0x0023;
    L_0x0033:
        r2 = 1;
        goto L_0x0023;
    L_0x0035:
        monitor-exit(r3);	 Catch:{ all -> 0x0056 }
        if (r2 == 0) goto L_0x003f;
    L_0x0038:
        r4 = java.lang.Thread.currentThread();
        r4.interrupt();
    L_0x003f:
        r4 = r3.d;
        if (r4 != 0) goto L_0x0053;
    L_0x0043:
        r4 = r3.c;
        if (r4 != 0) goto L_0x0050;
    L_0x0047:
        r4 = r3.e;
        r4 = defpackage.bcz.a(r4);
        r4 = (com.google.android.exoplayer2.video.DummySurface) r4;
        return r4;
    L_0x0050:
        r4 = r3.c;
        throw r4;
    L_0x0053:
        r4 = r3.d;
        throw r4;
    L_0x0056:
        r4 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0056 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.b.a(int):com.google.android.exoplayer2.video.DummySurface");
    }

    public final void a() {
        bcz.a(this.b);
        this.b.sendEmptyMessage(2);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                try {
                    int i = message.arg1;
                    bcz.a(this.a);
                    this.a.a(i);
                    this.e = new DummySurface(this, this.a.b(), i != 0, (byte) 0);
                    synchronized (this) {
                        notify();
                    }
                } catch (Throwable e) {
                    bdk.b("DummySurface", "Failed to initialize dummy surface", e);
                    this.d = e;
                    synchronized (this) {
                        notify();
                    }
                } catch (Throwable e2) {
                    try {
                        bdk.b("DummySurface", "Failed to initialize dummy surface", e2);
                        this.c = e2;
                        synchronized (this) {
                            notify();
                        }
                    } catch (Throwable th) {
                        synchronized (this) {
                            notify();
                        }
                    }
                }
                return true;
            case 2:
                try {
                    bcz.a(this.a);
                    this.a.a();
                } catch (Throwable th2) {
                    quit();
                }
                quit();
                return true;
            default:
                return true;
        }
    }
}
