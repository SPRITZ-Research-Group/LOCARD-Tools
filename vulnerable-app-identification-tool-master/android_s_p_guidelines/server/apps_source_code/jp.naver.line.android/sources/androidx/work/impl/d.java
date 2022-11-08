package androidx.work.impl;

import defpackage.bon;

final class d implements Runnable {
    private a a;
    private String b;
    private bon<Boolean> c;

    d(a aVar, String str, bon<Boolean> bon) {
        this.a = aVar;
        this.b = str;
        this.c = bon;
    }

    public final void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.work.impl.d.run():void. bs: []
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
        r3 = this;
        r0 = r3.c;	 Catch:{ InterruptedException -> 0x000d, InterruptedException -> 0x000d }
        r0 = r0.get();	 Catch:{ InterruptedException -> 0x000d, InterruptedException -> 0x000d }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InterruptedException -> 0x000d, InterruptedException -> 0x000d }
        r0 = r0.booleanValue();	 Catch:{ InterruptedException -> 0x000d, InterruptedException -> 0x000d }
        goto L_0x000e;
    L_0x000d:
        r0 = 1;
    L_0x000e:
        r1 = r3.a;
        r2 = r3.b;
        r1.a(r2, r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.d.run():void");
    }
}
