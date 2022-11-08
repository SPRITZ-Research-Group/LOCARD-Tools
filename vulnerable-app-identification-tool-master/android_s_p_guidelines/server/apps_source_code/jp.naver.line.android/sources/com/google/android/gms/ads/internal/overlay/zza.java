package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;

@zzadh
public final class zza {
    private static boolean zza(Context context, Intent intent, zzt zzt) {
        try {
            String str = "Launching an intent: ";
            String valueOf = String.valueOf(intent.toURI());
            zzakb.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            zzbv.zzek();
            zzakk.zza(context, intent);
            if (zzt != null) {
                zzt.zzbl();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzane.zzdk(e.getMessage());
            return false;
        }
    }

    public static boolean zza(android.content.Context r6, com.google.android.gms.ads.internal.overlay.zzc r7, com.google.android.gms.ads.internal.overlay.zzt r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.ads.internal.overlay.zza.zza(android.content.Context, com.google.android.gms.ads.internal.overlay.zzc, com.google.android.gms.ads.internal.overlay.zzt):boolean. bs: []
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
        r0 = 0;
        if (r7 != 0) goto L_0x0009;
    L_0x0003:
        r6 = "No intent data for launcher overlay.";
    L_0x0005:
        com.google.android.gms.internal.ads.zzane.zzdk(r6);
        return r0;
    L_0x0009:
        com.google.android.gms.internal.ads.zznk.initialize(r6);
        r1 = r7.intent;
        if (r1 == 0) goto L_0x0017;
    L_0x0010:
        r7 = r7.intent;
        r6 = zza(r6, r7, r8);
        return r6;
    L_0x0017:
        r1 = new android.content.Intent;
        r1.<init>();
        r2 = r7.url;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 == 0) goto L_0x0027;
    L_0x0024:
        r6 = "Open GMSG did not contain a URL.";
        goto L_0x0005;
    L_0x0027:
        r2 = r7.mimeType;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x003b;
    L_0x002f:
        r2 = r7.url;
        r2 = android.net.Uri.parse(r2);
        r3 = r7.mimeType;
        r1.setDataAndType(r2, r3);
        goto L_0x0044;
    L_0x003b:
        r2 = r7.url;
        r2 = android.net.Uri.parse(r2);
        r1.setData(r2);
    L_0x0044:
        r2 = "android.intent.action.VIEW";
        r1.setAction(r2);
        r2 = r7.packageName;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x0056;
    L_0x0051:
        r2 = r7.packageName;
        r1.setPackage(r2);
    L_0x0056:
        r2 = r7.zzbxj;
        r2 = android.text.TextUtils.isEmpty(r2);
        r3 = 1;
        if (r2 != 0) goto L_0x008f;
    L_0x005f:
        r2 = r7.zzbxj;
        r4 = "/";
        r5 = 2;
        r2 = r2.split(r4, r5);
        r4 = r2.length;
        if (r4 >= r5) goto L_0x0088;
    L_0x006b:
        r6 = "Could not parse component name from open GMSG: ";
        r7 = r7.zzbxj;
        r7 = java.lang.String.valueOf(r7);
        r8 = r7.length();
        if (r8 == 0) goto L_0x007e;
    L_0x0079:
        r6 = r6.concat(r7);
        goto L_0x0084;
    L_0x007e:
        r7 = new java.lang.String;
        r7.<init>(r6);
        r6 = r7;
    L_0x0084:
        com.google.android.gms.internal.ads.zzane.zzdk(r6);
        return r0;
    L_0x0088:
        r4 = r2[r0];
        r2 = r2[r3];
        r1.setClassName(r4, r2);
    L_0x008f:
        r7 = r7.zzbxk;
        r2 = android.text.TextUtils.isEmpty(r7);
        if (r2 != 0) goto L_0x00a5;
    L_0x0097:
        r7 = java.lang.Integer.parseInt(r7);	 Catch:{ NumberFormatException -> 0x009c }
        goto L_0x00a2;
    L_0x009c:
        r7 = "Could not parse intent flags.";
        com.google.android.gms.internal.ads.zzane.zzdk(r7);
        r7 = 0;
    L_0x00a2:
        r1.addFlags(r7);
    L_0x00a5:
        r7 = com.google.android.gms.internal.ads.zznk.zzbea;
        r0 = com.google.android.gms.internal.ads.zzkb.zzik();
        r7 = r0.zzd(r7);
        r7 = (java.lang.Boolean) r7;
        r7 = r7.booleanValue();
        if (r7 == 0) goto L_0x00c2;
    L_0x00b7:
        r7 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r1.addFlags(r7);
        r7 = "androidx.browser.customtabs.extra.user_opt_out";
        r1.putExtra(r7, r3);
        goto L_0x00da;
    L_0x00c2:
        r7 = com.google.android.gms.internal.ads.zznk.zzbdz;
        r0 = com.google.android.gms.internal.ads.zzkb.zzik();
        r7 = r0.zzd(r7);
        r7 = (java.lang.Boolean) r7;
        r7 = r7.booleanValue();
        if (r7 == 0) goto L_0x00da;
    L_0x00d4:
        com.google.android.gms.ads.internal.zzbv.zzek();
        com.google.android.gms.internal.ads.zzakk.zzb(r6, r1);
    L_0x00da:
        r6 = zza(r6, r1, r8);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.overlay.zza.zza(android.content.Context, com.google.android.gms.ads.internal.overlay.zzc, com.google.android.gms.ads.internal.overlay.zzt):boolean");
    }
}
