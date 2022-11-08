package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.wrappers.Wrappers;

@ShowFirstParty
@KeepForSdk
public class GoogleSignatureVerifier {
    private static GoogleSignatureVerifier zzam;
    private final Context mContext;
    private volatile String zzan;

    private GoogleSignatureVerifier(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (zzam == null) {
                zzc.zza(context);
                zzam = new GoogleSignatureVerifier(context);
            }
        }
        return zzam;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        zzm zzm;
        String[] packagesForUid = Wrappers.packageManager(this.mContext).getPackagesForUid(i);
        if (packagesForUid != null && packagesForUid.length != 0) {
            zzm = null;
            for (String zza : packagesForUid) {
                zzm = zza(zza, i);
                if (zzm.zzad) {
                    break;
                }
            }
        } else {
            zzm = zzm.zzb("no pkgs");
        }
        zzm.zzf();
        return zzm.zzad;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(String str) {
        zzm zzc = zzc(str);
        zzc.zzf();
        return zzc.zzad;
    }

    public static boolean zza(PackageInfo packageInfo, boolean z) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            zze zza;
            if (z) {
                zza = zza(packageInfo, zzh.zzx);
            } else {
                zza = zza(packageInfo, zzh.zzx[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (zza(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.mContext)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    private final com.google.android.gms.common.zzm zza(java.lang.String r7, int r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.GoogleSignatureVerifier.zza(java.lang.String, int):com.google.android.gms.common.zzm. bs: []
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
        r6 = this;
        r0 = r6.mContext;	 Catch:{ NameNotFoundException -> 0x005e }
        r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0);	 Catch:{ NameNotFoundException -> 0x005e }
        r1 = 64;	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = r0.zza(r7, r1, r8);	 Catch:{ NameNotFoundException -> 0x005e }
        r0 = r6.mContext;	 Catch:{ NameNotFoundException -> 0x005e }
        r0 = com.google.android.gms.common.GooglePlayServicesUtilLight.honorsDebugCertificates(r0);	 Catch:{ NameNotFoundException -> 0x005e }
        if (r8 != 0) goto L_0x001b;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x0014:
        r8 = "null pkg";	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = com.google.android.gms.common.zzm.zzb(r8);	 Catch:{ NameNotFoundException -> 0x005e }
        return r8;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x001b:
        r1 = r8.signatures;	 Catch:{ NameNotFoundException -> 0x005e }
        r1 = r1.length;	 Catch:{ NameNotFoundException -> 0x005e }
        r2 = 1;	 Catch:{ NameNotFoundException -> 0x005e }
        if (r1 == r2) goto L_0x0028;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x0021:
        r8 = "single cert required";	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = com.google.android.gms.common.zzm.zzb(r8);	 Catch:{ NameNotFoundException -> 0x005e }
        return r8;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x0028:
        r1 = new com.google.android.gms.common.zzf;	 Catch:{ NameNotFoundException -> 0x005e }
        r2 = r8.signatures;	 Catch:{ NameNotFoundException -> 0x005e }
        r3 = 0;	 Catch:{ NameNotFoundException -> 0x005e }
        r2 = r2[r3];	 Catch:{ NameNotFoundException -> 0x005e }
        r2 = r2.toByteArray();	 Catch:{ NameNotFoundException -> 0x005e }
        r1.<init>(r2);	 Catch:{ NameNotFoundException -> 0x005e }
        r2 = r8.packageName;	 Catch:{ NameNotFoundException -> 0x005e }
        r4 = com.google.android.gms.common.zzc.zza(r2, r1, r0, r3);	 Catch:{ NameNotFoundException -> 0x005e }
        r5 = r4.zzad;	 Catch:{ NameNotFoundException -> 0x005e }
        if (r5 == 0) goto L_0x005d;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x0040:
        r5 = r8.applicationInfo;	 Catch:{ NameNotFoundException -> 0x005e }
        if (r5 == 0) goto L_0x005d;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x0044:
        r8 = r8.applicationInfo;	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = r8.flags;	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = r8 & 2;	 Catch:{ NameNotFoundException -> 0x005e }
        if (r8 == 0) goto L_0x005d;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x004c:
        if (r0 == 0) goto L_0x0056;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x004e:
        r8 = com.google.android.gms.common.zzc.zza(r2, r1, r3, r3);	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = r8.zzad;	 Catch:{ NameNotFoundException -> 0x005e }
        if (r8 == 0) goto L_0x005d;	 Catch:{ NameNotFoundException -> 0x005e }
    L_0x0056:
        r8 = "debuggable release cert app rejected";	 Catch:{ NameNotFoundException -> 0x005e }
        r8 = com.google.android.gms.common.zzm.zzb(r8);	 Catch:{ NameNotFoundException -> 0x005e }
        return r8;
    L_0x005d:
        return r4;
        r8 = "no pkg ";
        r7 = java.lang.String.valueOf(r7);
        r0 = r7.length();
        if (r0 == 0) goto L_0x0070;
    L_0x006b:
        r7 = r8.concat(r7);
        goto L_0x0075;
    L_0x0070:
        r7 = new java.lang.String;
        r7.<init>(r8);
    L_0x0075:
        r7 = com.google.android.gms.common.zzm.zzb(r7);
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zza(java.lang.String, int):com.google.android.gms.common.zzm");
    }

    private final com.google.android.gms.common.zzm zzc(java.lang.String r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.google.android.gms.common.GoogleSignatureVerifier.zzc(java.lang.String):com.google.android.gms.common.zzm. bs: []
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
        r7 = this;
        if (r8 != 0) goto L_0x0009;
    L_0x0002:
        r8 = "null pkg";
        r8 = com.google.android.gms.common.zzm.zzb(r8);
        return r8;
    L_0x0009:
        r0 = r7.zzan;
        r0 = r8.equals(r0);
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r8 = com.google.android.gms.common.zzm.zze();
        return r8;
    L_0x0016:
        r0 = r7.mContext;	 Catch:{ NameNotFoundException -> 0x007b }
        r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0);	 Catch:{ NameNotFoundException -> 0x007b }
        r1 = 64;	 Catch:{ NameNotFoundException -> 0x007b }
        r0 = r0.getPackageInfo(r8, r1);	 Catch:{ NameNotFoundException -> 0x007b }
        r1 = r7.mContext;
        r1 = com.google.android.gms.common.GooglePlayServicesUtilLight.honorsDebugCertificates(r1);
        if (r0 != 0) goto L_0x0031;
    L_0x002a:
        r0 = "null pkg";
        r0 = com.google.android.gms.common.zzm.zzb(r0);
        goto L_0x0074;
    L_0x0031:
        r2 = r0.signatures;
        r2 = r2.length;
        r3 = 1;
        if (r2 == r3) goto L_0x003e;
    L_0x0037:
        r0 = "single cert required";
        r0 = com.google.android.gms.common.zzm.zzb(r0);
        goto L_0x0074;
    L_0x003e:
        r2 = new com.google.android.gms.common.zzf;
        r3 = r0.signatures;
        r4 = 0;
        r3 = r3[r4];
        r3 = r3.toByteArray();
        r2.<init>(r3);
        r3 = r0.packageName;
        r5 = com.google.android.gms.common.zzc.zza(r3, r2, r1, r4);
        r6 = r5.zzad;
        if (r6 == 0) goto L_0x0073;
    L_0x0056:
        r6 = r0.applicationInfo;
        if (r6 == 0) goto L_0x0073;
    L_0x005a:
        r0 = r0.applicationInfo;
        r0 = r0.flags;
        r0 = r0 & 2;
        if (r0 == 0) goto L_0x0073;
    L_0x0062:
        if (r1 == 0) goto L_0x006c;
    L_0x0064:
        r0 = com.google.android.gms.common.zzc.zza(r3, r2, r4, r4);
        r0 = r0.zzad;
        if (r0 == 0) goto L_0x0073;
    L_0x006c:
        r0 = "debuggable release cert app rejected";
        r0 = com.google.android.gms.common.zzm.zzb(r0);
        goto L_0x0074;
    L_0x0073:
        r0 = r5;
    L_0x0074:
        r1 = r0.zzad;
        if (r1 == 0) goto L_0x007a;
    L_0x0078:
        r7.zzan = r8;
    L_0x007a:
        return r0;
        r0 = "no pkg ";
        r8 = java.lang.String.valueOf(r8);
        r1 = r8.length();
        if (r1 == 0) goto L_0x008d;
    L_0x0088:
        r8 = r0.concat(r8);
        goto L_0x0092;
    L_0x008d:
        r8 = new java.lang.String;
        r8.<init>(r0);
    L_0x0092:
        r8 = com.google.android.gms.common.zzm.zzb(r8);
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleSignatureVerifier.zzc(java.lang.String):com.google.android.gms.common.zzm");
    }

    private static zze zza(PackageInfo packageInfo, zze... zzeArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        int i = 0;
        zzf zzf = new zzf(packageInfo.signatures[0].toByteArray());
        while (i < zzeArr.length) {
            if (zzeArr[i].equals(zzf)) {
                return zzeArr[i];
            }
            i++;
        }
        return null;
    }
}
