package com.facebook.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

final class d implements ServiceConnection {
    private AtomicBoolean a;
    private final BlockingQueue<IBinder> b;

    public final void onServiceDisconnected(ComponentName componentName) {
    }

    private d() {
        this.a = new AtomicBoolean(false);
        this.b = new LinkedBlockingDeque();
    }

    /* synthetic */ d(byte b) {
        this();
    }

    public final void onServiceConnected(android.content.ComponentName r1, android.os.IBinder r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.d.onServiceConnected(android.content.ComponentName, android.os.IBinder):void. bs: []
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
        r0 = this;
        if (r2 == 0) goto L_0x0009;
    L_0x0002:
        r1 = r0.b;	 Catch:{ InterruptedException -> 0x0008 }
        r1.put(r2);	 Catch:{ InterruptedException -> 0x0008 }
        goto L_0x0009;
    L_0x0008:
        return;
    L_0x0009:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.d.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final IBinder a() throws InterruptedException {
        if (!this.a.compareAndSet(true, true)) {
            return (IBinder) this.b.take();
        }
        throw new IllegalStateException("Binder already consumed");
    }
}
