package com.facebook.internal;

import com.facebook.s;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

public final class aq<T> {
    private T a;
    private CountDownLatch b = new CountDownLatch(1);

    public aq(final Callable<T> callable) {
        s.d().execute(new FutureTask(new Callable<Void>(this) {
            final /* synthetic */ aq b;

            public final /* synthetic */ Object call() throws Exception {
                return a();
            }

            private Void a() throws Exception {
                try {
                    this.b.a = callable.call();
                    return null;
                } finally {
                    this.b.b.countDown();
                }
            }
        }));
    }

    public final T a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.aq.a():T. bs: []
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:89)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r1 = this;
        r0 = r1.b;
        if (r0 == 0) goto L_0x0009;
    L_0x0004:
        r0 = r1.b;	 Catch:{ InterruptedException -> 0x0009 }
        r0.await();	 Catch:{ InterruptedException -> 0x0009 }
    L_0x0009:
        r0 = r1.a;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.aq.a():T");
    }
}
