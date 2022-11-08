package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import defpackage.g;
import java.util.ArrayList;
import java.util.List;

public class b {
    private final g a;
    private final ComponentName b;

    b(g gVar, ComponentName componentName) {
        this.a = gVar;
        this.b = componentName;
    }

    public static String a(Context context, List<String> list, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        List arrayList = list == null ? new ArrayList() : list;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        if (!z) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            if (resolveActivity != null) {
                String str = resolveActivity.activityInfo.packageName;
                List arrayList2 = new ArrayList(arrayList.size() + 1);
                arrayList2.add(str);
                if (list != null) {
                    arrayList2.addAll(list);
                }
                arrayList = arrayList2;
            }
        }
        Intent intent2 = new Intent("android.support.customtabs.action.CustomTabsService");
        for (String str2 : arrayList) {
            intent2.setPackage(str2);
            if (packageManager.resolveService(intent2, 0) != null) {
                return str2;
            }
        }
        return null;
    }

    public final boolean a() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.browser.customtabs.b.a():boolean. bs: []
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
        r0 = r3.a;	 Catch:{ RemoteException -> 0x0009 }
        r1 = 0;	 Catch:{ RemoteException -> 0x0009 }
        r0 = r0.a(r1);	 Catch:{ RemoteException -> 0x0009 }
        return r0;
    L_0x0009:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.browser.customtabs.b.a():boolean");
    }

    public final androidx.browser.customtabs.f b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:androidx.browser.customtabs.b.b():androidx.browser.customtabs.f. bs: []
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
        r4 = this;
        r0 = new androidx.browser.customtabs.b$1;
        r0.<init>(r4);
        r1 = 0;
        r2 = r4.a;	 Catch:{ RemoteException -> 0x0019 }
        r2 = r2.a(r0);	 Catch:{ RemoteException -> 0x0019 }
        if (r2 != 0) goto L_0x000f;
    L_0x000e:
        return r1;
    L_0x000f:
        r1 = new androidx.browser.customtabs.f;
        r2 = r4.a;
        r3 = r4.b;
        r1.<init>(r2, r0, r3);
        return r1;
    L_0x0019:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.browser.customtabs.b.b():androidx.browser.customtabs.f");
    }
}
