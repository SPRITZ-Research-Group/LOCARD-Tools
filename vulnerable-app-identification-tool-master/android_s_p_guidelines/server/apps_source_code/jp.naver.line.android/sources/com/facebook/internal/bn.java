package com.facebook.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.facebook.s;
import com.facebook.u;
import java.util.Collection;
import java.util.List;

public final class bn {
    private static final String a = "com.facebook.internal.bn";

    public static void a(Object obj, String str) {
        if (obj == null) {
            StringBuilder stringBuilder = new StringBuilder("Argument '");
            stringBuilder.append(str);
            stringBuilder.append("' cannot be null");
            throw new NullPointerException(stringBuilder.toString());
        }
    }

    public static void a(String str, String str2) {
        if (bj.a(str)) {
            StringBuilder stringBuilder = new StringBuilder("Argument '");
            stringBuilder.append(str2);
            stringBuilder.append("' cannot be null or empty");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public static void a() {
        if (!s.a()) {
            throw new u("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }

    public static String b() {
        String j = s.j();
        if (j != null) {
            return j;
        }
        throw new IllegalStateException("No App ID found, please set the App ID.");
    }

    public static String c() {
        String l = s.l();
        if (l != null) {
            return l;
        }
        throw new IllegalStateException("No Client Token found, please set the Client Token.");
    }

    public static void a(Context context) {
        a(context, true);
    }

    public static void a(Context context, boolean z) {
        a((Object) context, "context");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != -1) {
            return;
        }
        if (z) {
            throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
        }
        Log.w(a, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
    }

    public static void b(Context context) {
        b(context, true);
    }

    public static void b(android.content.Context r3, boolean r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.bn.b(android.content.Context, boolean):void. bs: []
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
        r0 = "context";
        a(r3, r0);
        r0 = r3.getPackageManager();
        if (r0 == 0) goto L_0x0018;
    L_0x000b:
        r1 = new android.content.ComponentName;
        r2 = "com.facebook.FacebookActivity";
        r1.<init>(r3, r2);
        r3 = 1;
        r3 = r0.getActivityInfo(r1, r3);	 Catch:{ NameNotFoundException -> 0x0018 }
        goto L_0x0019;
    L_0x0018:
        r3 = 0;
    L_0x0019:
        if (r3 != 0) goto L_0x002d;
    L_0x001b:
        if (r4 != 0) goto L_0x0025;
    L_0x001d:
        r3 = a;
        r4 = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
        android.util.Log.w(r3, r4);
        goto L_0x002d;
    L_0x0025:
        r3 = new java.lang.IllegalStateException;
        r4 = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
        r3.<init>(r4);
        throw r3;
    L_0x002d:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.bn.b(android.content.Context, boolean):void");
    }

    public static boolean c(Context context) {
        a((Object) context, "context");
        PackageManager packageManager = context.getPackageManager();
        List queryIntentActivities;
        if (packageManager != null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            StringBuilder stringBuilder = new StringBuilder("fb");
            stringBuilder.append(s.j());
            stringBuilder.append("://authorize");
            intent.setData(Uri.parse(stringBuilder.toString()));
            queryIntentActivities = packageManager.queryIntentActivities(intent, 64);
        } else {
            queryIntentActivities = null;
        }
        boolean z = false;
        if (queryIntentActivities != null) {
            boolean z2 = false;
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (!activityInfo.name.equals("com.facebook.CustomTabActivity") || !activityInfo.packageName.equals(context.getPackageName())) {
                    return false;
                }
                z2 = true;
            }
            z = z2;
        }
        return z;
    }

    public static void d(Context context) {
        a((Object) context, "context");
        String b = b();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            if (packageManager.resolveContentProvider("com.facebook.app.FacebookContentProvider".concat(String.valueOf(b)), 0) == null) {
                throw new IllegalStateException(String.format("A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.", new Object[]{"com.facebook.app.FacebookContentProvider".concat(String.valueOf(b))}));
            }
        }
    }

    public static <T> void a(Collection<T> collection, String str) {
        StringBuilder stringBuilder;
        a((Object) collection, str);
        for (T t : collection) {
            if (t == null) {
                stringBuilder = new StringBuilder("Container '");
                stringBuilder.append(str);
                stringBuilder.append("' cannot contain null values");
                throw new NullPointerException(stringBuilder.toString());
            }
        }
        if (collection.isEmpty()) {
            stringBuilder = new StringBuilder("Container '");
            stringBuilder.append(str);
            stringBuilder.append("' cannot be empty");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
