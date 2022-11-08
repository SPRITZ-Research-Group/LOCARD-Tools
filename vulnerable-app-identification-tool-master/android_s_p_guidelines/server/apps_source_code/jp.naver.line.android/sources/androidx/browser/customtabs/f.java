package androidx.browser.customtabs;

import android.content.ComponentName;
import defpackage.d;
import defpackage.g;

public final class f {
    private final Object a = new Object();
    private final g b;
    private final d c;
    private final ComponentName d;

    f(g gVar, d dVar, ComponentName componentName) {
        this.b = gVar;
        this.c = dVar;
        this.d = componentName;
    }

    public final boolean a(android.net.Uri r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.browser.customtabs.f.a(android.net.Uri):boolean. bs: []
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
        r0 = r3.b;	 Catch:{ RemoteException -> 0x000a }
        r1 = r3.c;	 Catch:{ RemoteException -> 0x000a }
        r2 = 0;	 Catch:{ RemoteException -> 0x000a }
        r4 = r0.a(r1, r4, r2, r2);	 Catch:{ RemoteException -> 0x000a }
        return r4;
    L_0x000a:
        r4 = 0;
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.browser.customtabs.f.a(android.net.Uri):boolean");
    }
}
