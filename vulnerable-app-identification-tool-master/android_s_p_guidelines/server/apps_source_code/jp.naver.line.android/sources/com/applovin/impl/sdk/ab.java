package com.applovin.impl.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public class ab {
    static void a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ab.a():void. bs: []
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
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x0016 }
        r1 = 9;	 Catch:{ Throwable -> 0x0016 }
        if (r0 < r1) goto L_0x0016;	 Catch:{ Throwable -> 0x0016 }
    L_0x0006:
        r0 = new android.os.StrictMode$ThreadPolicy$Builder;	 Catch:{ Throwable -> 0x0016 }
        r0.<init>();	 Catch:{ Throwable -> 0x0016 }
        r0 = r0.permitAll();	 Catch:{ Throwable -> 0x0016 }
        r0 = r0.build();	 Catch:{ Throwable -> 0x0016 }
        android.os.StrictMode.setThreadPolicy(r0);	 Catch:{ Throwable -> 0x0016 }
    L_0x0016:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ab.a():void");
    }

    public static boolean a(android.content.Context r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ab.a(android.content.Context):boolean. bs: []
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
        r0 = 0;
        r1 = new android.content.ComponentName;	 Catch:{ Throwable -> 0x001d }
        r2 = com.applovin.adview.AppLovinInterstitialActivity.class;	 Catch:{ Throwable -> 0x001d }
        r2 = r2.getCanonicalName();	 Catch:{ Throwable -> 0x001d }
        r1.<init>(r3, r2);	 Catch:{ Throwable -> 0x001d }
        r3 = r3.getPackageManager();	 Catch:{ Throwable -> 0x001d }
        r3 = r3.getActivityInfo(r1, r0);	 Catch:{ Throwable -> 0x001d }
        r3 = r3.configChanges;	 Catch:{ Throwable -> 0x001d }
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;	 Catch:{ Throwable -> 0x001d }
        r3 = com.applovin.impl.sdk.gd.a(r3, r1);	 Catch:{ Throwable -> 0x001d }
        return r3;
    L_0x001d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ab.a(android.content.Context):boolean");
    }

    public static boolean a(Class<?> cls, Context context) {
        return context.getPackageManager().resolveActivity(new Intent(context, cls), 0) != null;
    }

    public static boolean a(String str, Context context) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static boolean b() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean b(android.content.Context r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ab.b(android.content.Context):boolean. bs: []
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
        r0 = 0;
        r1 = new android.content.ComponentName;	 Catch:{ Throwable -> 0x001d }
        r2 = com.applovin.adview.AppLovinInterstitialActivity.class;	 Catch:{ Throwable -> 0x001d }
        r2 = r2.getCanonicalName();	 Catch:{ Throwable -> 0x001d }
        r1.<init>(r3, r2);	 Catch:{ Throwable -> 0x001d }
        r3 = r3.getPackageManager();	 Catch:{ Throwable -> 0x001d }
        r3 = r3.getActivityInfo(r1, r0);	 Catch:{ Throwable -> 0x001d }
        r3 = r3.configChanges;	 Catch:{ Throwable -> 0x001d }
        r1 = 128; // 0x80 float:1.794E-43 double:6.32E-322;	 Catch:{ Throwable -> 0x001d }
        r3 = com.applovin.impl.sdk.gd.a(r3, r1);	 Catch:{ Throwable -> 0x001d }
        return r3;
    L_0x001d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ab.b(android.content.Context):boolean");
    }

    public static android.graphics.Point c(android.content.Context r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.ab.c(android.content.Context):android.graphics.Point. bs: []
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
        r0 = new android.graphics.Point;
        r0.<init>();
        r1 = 480; // 0x1e0 float:6.73E-43 double:2.37E-321;
        r0.x = r1;
        r1 = 320; // 0x140 float:4.48E-43 double:1.58E-321;
        r0.y = r1;
        r1 = "window";	 Catch:{ Throwable -> 0x002f }
        r3 = r3.getSystemService(r1);	 Catch:{ Throwable -> 0x002f }
        r3 = (android.view.WindowManager) r3;	 Catch:{ Throwable -> 0x002f }
        r3 = r3.getDefaultDisplay();	 Catch:{ Throwable -> 0x002f }
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Throwable -> 0x002f }
        r2 = 13;	 Catch:{ Throwable -> 0x002f }
        if (r1 < r2) goto L_0x0023;	 Catch:{ Throwable -> 0x002f }
    L_0x001f:
        r3.getSize(r0);	 Catch:{ Throwable -> 0x002f }
        goto L_0x002f;	 Catch:{ Throwable -> 0x002f }
    L_0x0023:
        r1 = r3.getWidth();	 Catch:{ Throwable -> 0x002f }
        r0.x = r1;	 Catch:{ Throwable -> 0x002f }
        r3 = r3.getHeight();	 Catch:{ Throwable -> 0x002f }
        r0.y = r3;	 Catch:{ Throwable -> 0x002f }
    L_0x002f:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ab.c(android.content.Context):android.graphics.Point");
    }

    public static boolean c() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean d() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean e() {
        return VERSION.SDK_INT >= 17;
    }

    public static boolean f() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean g() {
        return VERSION.SDK_INT >= 21;
    }

    public static boolean h() {
        return VERSION.SDK_INT >= 23;
    }
}
