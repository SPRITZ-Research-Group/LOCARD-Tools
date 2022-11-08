package com.facebook.internal;

import java.util.HashSet;

public final class q {
    private static final HashSet<String> a;

    public static boolean a(android.content.Context r5, java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.q.a(android.content.Context, java.lang.String):boolean. bs: []
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
        r0 = android.os.Build.BRAND;
        r1 = r5.getApplicationInfo();
        r1 = r1.flags;
        r2 = "generic";
        r0 = r0.startsWith(r2);
        r2 = 1;
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r1 & 2;
        if (r0 == 0) goto L_0x0016;
    L_0x0015:
        return r2;
    L_0x0016:
        r0 = 0;
        r5 = r5.getPackageManager();	 Catch:{ NameNotFoundException -> 0x0049 }
        r1 = 64;	 Catch:{ NameNotFoundException -> 0x0049 }
        r5 = r5.getPackageInfo(r6, r1);	 Catch:{ NameNotFoundException -> 0x0049 }
        r6 = r5.signatures;
        if (r6 == 0) goto L_0x0048;
    L_0x0025:
        r6 = r5.signatures;
        r6 = r6.length;
        if (r6 > 0) goto L_0x002b;
    L_0x002a:
        goto L_0x0048;
    L_0x002b:
        r5 = r5.signatures;
        r6 = r5.length;
        r1 = 0;
    L_0x002f:
        if (r1 >= r6) goto L_0x0047;
    L_0x0031:
        r3 = r5[r1];
        r3 = r3.toByteArray();
        r3 = com.facebook.internal.bj.a(r3);
        r4 = a;
        r3 = r4.contains(r3);
        if (r3 != 0) goto L_0x0044;
    L_0x0043:
        return r0;
    L_0x0044:
        r1 = r1 + 1;
        goto L_0x002f;
    L_0x0047:
        return r2;
    L_0x0048:
        return r0;
    L_0x0049:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.q.a(android.content.Context, java.lang.String):boolean");
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
        hashSet.add("cc2751449a350f668590264ed76692694a80308a");
        hashSet.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
        hashSet.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
        hashSet.add("df6b721c8b4d3b6eb44c861d4415007e5a35fc95");
        hashSet.add("9b8f518b086098de3d77736f9458a3d2f6f95a37");
        hashSet.add("2438bce1ddb7bd026d5ff89f598b3b5e5bb824b3");
        a = hashSet;
    }
}
