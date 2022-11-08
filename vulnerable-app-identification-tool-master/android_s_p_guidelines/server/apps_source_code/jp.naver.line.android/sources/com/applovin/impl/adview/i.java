package com.applovin.impl.adview;

class i implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private i(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    /* synthetic */ i(AdViewControllerImpl adViewControllerImpl, a aVar) {
        this(adViewControllerImpl);
    }

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.adview.i.run():void. bs: []
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
        r7 = this;
        r0 = r7.a;
        r0 = r0.l;
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r7.a;	 Catch:{ Exception -> 0x001a }
        r1 = r0.l;	 Catch:{ Exception -> 0x001a }
        r2 = "/";	 Catch:{ Exception -> 0x001a }
        r3 = "<html></html>";	 Catch:{ Exception -> 0x001a }
        r4 = "text/html";	 Catch:{ Exception -> 0x001a }
        r5 = 0;	 Catch:{ Exception -> 0x001a }
        r6 = "";	 Catch:{ Exception -> 0x001a }
        r1.loadDataWithBaseURL(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x001a }
    L_0x001a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.adview.i.run():void");
    }
}
