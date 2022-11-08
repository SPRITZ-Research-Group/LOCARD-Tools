package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class av {
    private final String a = "FileManager";
    private final AppLovinSdkImpl b;
    private final AppLovinLogger c;
    private final Object d;

    av(AppLovinSdk appLovinSdk) {
        this.b = (AppLovinSdkImpl) appLovinSdk;
        this.c = appLovinSdk.getLogger();
        this.d = new Object();
    }

    private long a(long j) {
        return j / 1048576;
    }

    private void a(long j, Context context) {
        long c = (long) c();
        if (c == -1) {
            this.c.d("FileManager", "Cache has no maximum size set; skipping drop...");
        } else if (a(j) > c) {
            this.c.d("FileManager", "Cache has exceeded maximum size; dropping...");
            g(context);
            this.b.a().a("cache_drop_count");
        } else {
            this.c.d("FileManager", "Cache is present but under size limit; not dropping...");
        }
    }

    private boolean a() {
        return ((Boolean) this.b.get(ea.aY)).booleanValue();
    }

    private long b() {
        long longValue = ((Long) this.b.get(ea.aZ)).longValue();
        return (longValue < 0 || !a()) ? -1 : longValue;
    }

    private boolean b(java.io.ByteArrayOutputStream r5, java.io.File r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.av.b(java.io.ByteArrayOutputStream, java.io.File):boolean. bs: []
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
        r0 = r4.c;
        r1 = "FileManager";
        r2 = new java.lang.StringBuilder;
        r3 = "Writing resource to filesystem: ";
        r2.<init>(r3);
        r3 = r6.getName();
        r2.append(r3);
        r2 = r2.toString();
        r0.d(r1, r2);
        r0 = r4.d;
        monitor-enter(r0);
        r1 = 0;
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0045, Throwable -> 0x0035 }
        r2.<init>(r6);	 Catch:{ IOException -> 0x0045, Throwable -> 0x0035 }
        r5.writeTo(r2);	 Catch:{ IOException -> 0x0030, Throwable -> 0x002d, all -> 0x002a }
        r5 = 1;
        r2.close();	 Catch:{ Exception -> 0x0053 }
        goto L_0x0053;
    L_0x002a:
        r5 = move-exception;
        r1 = r2;
        goto L_0x0055;
    L_0x002d:
        r5 = move-exception;
        r1 = r2;
        goto L_0x0036;
    L_0x0030:
        r5 = move-exception;
        r1 = r2;
        goto L_0x0046;
    L_0x0033:
        r5 = move-exception;
        goto L_0x0055;
    L_0x0035:
        r5 = move-exception;
    L_0x0036:
        r6 = r4.c;	 Catch:{ all -> 0x0033 }
        r2 = "FileManager";	 Catch:{ all -> 0x0033 }
        r3 = "Unknown failure to write file.";	 Catch:{ all -> 0x0033 }
        r6.e(r2, r3, r5);	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x0052;
    L_0x0041:
        r1.close();	 Catch:{ Exception -> 0x0052 }
        goto L_0x0052;
    L_0x0045:
        r5 = move-exception;
    L_0x0046:
        r6 = r4.c;	 Catch:{ all -> 0x0033 }
        r2 = "FileManager";	 Catch:{ all -> 0x0033 }
        r3 = "Unable to write data to file.";	 Catch:{ all -> 0x0033 }
        r6.e(r2, r3, r5);	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x0052;
    L_0x0051:
        goto L_0x0041;
    L_0x0052:
        r5 = 0;
    L_0x0053:
        monitor-exit(r0);	 Catch:{ all -> 0x005b }
        return r5;
    L_0x0055:
        if (r1 == 0) goto L_0x005d;
    L_0x0057:
        r1.close();	 Catch:{ Exception -> 0x005d }
        goto L_0x005d;
    L_0x005b:
        r5 = move-exception;
        goto L_0x005e;
    L_0x005d:
        throw r5;	 Catch:{ all -> 0x005b }
    L_0x005e:
        monitor-exit(r0);	 Catch:{ all -> 0x005b }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.av.b(java.io.ByteArrayOutputStream, java.io.File):boolean");
    }

    private boolean b(File file) {
        boolean delete;
        StringBuilder stringBuilder = new StringBuilder("Removing file ");
        stringBuilder.append(file.getName());
        stringBuilder.append(" from filesystem...");
        this.c.d("FileManager", stringBuilder.toString());
        synchronized (this.d) {
            try {
                delete = file.delete();
            } catch (Throwable e) {
                StringBuilder stringBuilder2 = new StringBuilder("Failed to remove file ");
                stringBuilder2.append(file.getName());
                stringBuilder2.append(" from filesystem!");
                this.c.e("FileManager", stringBuilder2.toString(), e);
                return false;
            }
        }
        return delete;
    }

    private int c() {
        int intValue = ((Integer) this.b.get(ea.ba)).intValue();
        return (intValue < 0 || !a()) ? -1 : intValue;
    }

    private File e(Context context) {
        return a(context) ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
    }

    private long f(Context context) {
        long j;
        av avVar = this;
        long b = b();
        Object obj = b != -1 ? 1 : null;
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (avVar.d) {
            j = 0;
            for (File file : b(context)) {
                Object obj2;
                if (obj == null || toSeconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) <= b) {
                    obj2 = null;
                } else {
                    StringBuilder stringBuilder = new StringBuilder("File ");
                    stringBuilder.append(file.getName());
                    stringBuilder.append(" has expired, removing...");
                    avVar.c.d("FileManager", stringBuilder.toString());
                    b(file);
                    obj2 = 1;
                }
                if (obj2 != null) {
                    avVar.b.a().a("cached_files_expired");
                } else {
                    j += file.length();
                }
            }
        }
        return j;
    }

    private void g(Context context) {
        synchronized (this.d) {
            for (File b : b(context)) {
                b(b);
            }
        }
    }

    java.io.ByteArrayOutputStream a(java.io.File r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.av.a(java.io.File):java.io.ByteArrayOutputStream. bs: []
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
        r8 = this;
        r0 = 0;
        if (r9 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r8.c;
        r2 = "FileManager";
        r3 = new java.lang.StringBuilder;
        r4 = "Reading resource from filesystem: ";
        r3.<init>(r4);
        r4 = r9.getName();
        r3.append(r4);
        r3 = r3.toString();
        r1.d(r2, r3);
        r1 = r8.d;
        monitor-enter(r1);
        r2 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0086, IOException -> 0x0061, Throwable -> 0x004f, all -> 0x004c }
        r2.<init>(r9);	 Catch:{ FileNotFoundException -> 0x0086, IOException -> 0x0061, Throwable -> 0x004f, all -> 0x004c }
        r3 = new java.io.ByteArrayOutputStream;	 Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0048, Throwable -> 0x0046 }
        r3.<init>();	 Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0048, Throwable -> 0x0046 }
        r4 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;	 Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0048, Throwable -> 0x0046 }
        r5 = new byte[r4];	 Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0048, Throwable -> 0x0046 }
    L_0x002e:
        r6 = 0;	 Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0048, Throwable -> 0x0046 }
        r7 = r2.read(r5, r6, r4);	 Catch:{ FileNotFoundException -> 0x004a, IOException -> 0x0048, Throwable -> 0x0046 }
        if (r7 < 0) goto L_0x0041;
    L_0x0035:
        r3.write(r5, r6, r7);	 Catch:{ Exception -> 0x0039 }
        goto L_0x002e;
    L_0x0039:
        r3.close();	 Catch:{ Exception -> 0x003c }
    L_0x003c:
        r2.close();	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        monitor-exit(r1);	 Catch:{ all -> 0x00a7 }
        return r0;
    L_0x0041:
        r2.close();	 Catch:{ Exception -> 0x0044 }
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x00a7 }
        return r3;
    L_0x0046:
        r9 = move-exception;
        goto L_0x0051;
    L_0x0048:
        r3 = move-exception;
        goto L_0x0063;
    L_0x004a:
        r9 = move-exception;
        goto L_0x0088;
    L_0x004c:
        r9 = move-exception;
        r2 = r0;
        goto L_0x00a1;
    L_0x004f:
        r9 = move-exception;
        r2 = r0;
    L_0x0051:
        r3 = r8.c;	 Catch:{ all -> 0x00a0 }
        r4 = "FileManager";	 Catch:{ all -> 0x00a0 }
        r5 = "Unknown failure to read file.";	 Catch:{ all -> 0x00a0 }
        r3.e(r4, r5, r9);	 Catch:{ all -> 0x00a0 }
        if (r2 == 0) goto L_0x005f;
    L_0x005c:
        r2.close();	 Catch:{ Exception -> 0x005f }
    L_0x005f:
        monitor-exit(r1);	 Catch:{ all -> 0x00a7 }
        return r0;
    L_0x0061:
        r3 = move-exception;
        r2 = r0;
    L_0x0063:
        r4 = r8.c;	 Catch:{ all -> 0x00a0 }
        r5 = "FileManager";	 Catch:{ all -> 0x00a0 }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a0 }
        r7 = "Failed to read file: ";	 Catch:{ all -> 0x00a0 }
        r6.<init>(r7);	 Catch:{ all -> 0x00a0 }
        r9 = r9.getName();	 Catch:{ all -> 0x00a0 }
        r6.append(r9);	 Catch:{ all -> 0x00a0 }
        r6.append(r3);	 Catch:{ all -> 0x00a0 }
        r9 = r6.toString();	 Catch:{ all -> 0x00a0 }
        r4.d(r5, r9);	 Catch:{ all -> 0x00a0 }
        if (r2 == 0) goto L_0x0084;
    L_0x0081:
        r2.close();	 Catch:{ Exception -> 0x0084 }
    L_0x0084:
        monitor-exit(r1);	 Catch:{ all -> 0x00a7 }
        return r0;
    L_0x0086:
        r9 = move-exception;
        r2 = r0;
    L_0x0088:
        r3 = r8.c;	 Catch:{ all -> 0x00a0 }
        r4 = "FileManager";	 Catch:{ all -> 0x00a0 }
        r5 = "File not found. ";	 Catch:{ all -> 0x00a0 }
        r9 = java.lang.String.valueOf(r9);	 Catch:{ all -> 0x00a0 }
        r9 = r5.concat(r9);	 Catch:{ all -> 0x00a0 }
        r3.i(r4, r9);	 Catch:{ all -> 0x00a0 }
        if (r2 == 0) goto L_0x009e;
    L_0x009b:
        r2.close();	 Catch:{ Exception -> 0x009e }
    L_0x009e:
        monitor-exit(r1);	 Catch:{ all -> 0x00a7 }
        return r0;
    L_0x00a0:
        r9 = move-exception;
    L_0x00a1:
        if (r2 == 0) goto L_0x00a9;
    L_0x00a3:
        r2.close();	 Catch:{ Exception -> 0x00a9 }
        goto L_0x00a9;
    L_0x00a7:
        r9 = move-exception;
        goto L_0x00aa;
    L_0x00a9:
        throw r9;	 Catch:{ all -> 0x00a7 }
    L_0x00aa:
        monitor-exit(r1);	 Catch:{ all -> 0x00a7 }
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.av.a(java.io.File):java.io.ByteArrayOutputStream");
    }

    java.io.ByteArrayOutputStream a(java.lang.String r7, java.util.List<java.lang.String> r8, boolean r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.av.a(java.lang.String, java.util.List, boolean):java.io.ByteArrayOutputStream. bs: []
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
        r6 = this;
        r0 = 0;
        if (r9 == 0) goto L_0x001b;
    L_0x0003:
        r8 = com.applovin.impl.sdk.gd.a(r7, r8);
        if (r8 != 0) goto L_0x001b;
    L_0x0009:
        r8 = r6.c;
        r9 = "FileManager";
        r1 = "Domain is not whitelisted, skipping precache for url: ";
        r7 = java.lang.String.valueOf(r7);
        r7 = r1.concat(r7);
        r8.d(r9, r7);
        return r0;
    L_0x001b:
        r8 = r6.b;
        r9 = com.applovin.impl.sdk.ea.bS;
        r8 = r8.get(r9);
        r8 = (java.lang.Boolean) r8;
        r8 = r8.booleanValue();
        if (r8 == 0) goto L_0x0048;
    L_0x002b:
        r8 = "https://";
        r8 = r7.contains(r8);
        if (r8 != 0) goto L_0x0048;
    L_0x0033:
        r8 = r6.b;
        r8 = r8.getLogger();
        r9 = "FileManager";
        r1 = "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...";
        r8.w(r9, r1);
        r8 = "http://";
        r9 = "https://";
        r7 = r7.replace(r8, r9);
    L_0x0048:
        r8 = r6.c;
        r9 = "FileManager";
        r1 = new java.lang.StringBuilder;
        r2 = "Loading ";
        r1.<init>(r2);
        r1.append(r7);
        r2 = "...";
        r1.append(r2);
        r1 = r1.toString();
        r8.d(r9, r1);
        r8 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x010b, all -> 0x0107 }
        r8.<init>();	 Catch:{ IOException -> 0x010b, all -> 0x0107 }
        r9 = new java.net.URL;	 Catch:{ IOException -> 0x0104, all -> 0x0101 }
        r9.<init>(r7);	 Catch:{ IOException -> 0x0104, all -> 0x0101 }
        r9 = r9.openConnection();	 Catch:{ IOException -> 0x0104, all -> 0x0101 }
        r9 = (java.net.HttpURLConnection) r9;	 Catch:{ IOException -> 0x0104, all -> 0x0101 }
        r1 = r6.b;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r2 = com.applovin.impl.sdk.ea.w;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = r1.get(r2);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = (java.lang.Integer) r1;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = r1.intValue();	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r9.setConnectTimeout(r1);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = r6.b;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r2 = com.applovin.impl.sdk.ea.y;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = r1.get(r2);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = (java.lang.Integer) r1;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = r1.intValue();	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r9.setReadTimeout(r1);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = 1;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r9.setDefaultUseCaches(r1);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r9.setUseCaches(r1);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r2 = 0;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r9.setAllowUserInteraction(r2);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r9.setInstanceFollowRedirects(r1);	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r1 = r9.getResponseCode();	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        if (r1 < r3) goto L_0x00f3;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
    L_0x00aa:
        r3 = 300; // 0x12c float:4.2E-43 double:1.48E-321;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        if (r1 < r3) goto L_0x00af;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
    L_0x00ae:
        goto L_0x00f3;	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
    L_0x00af:
        r1 = r9.getInputStream();	 Catch:{ IOException -> 0x00fe, all -> 0x00fc }
        r3 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = new byte[r3];	 Catch:{ IOException -> 0x00f1 }
    L_0x00b7:
        r5 = r1.read(r4, r2, r3);	 Catch:{ IOException -> 0x00f1 }
        if (r5 < 0) goto L_0x00d2;
    L_0x00bd:
        r8.write(r4, r2, r5);	 Catch:{ Exception -> 0x00c1 }
        goto L_0x00b7;
    L_0x00c1:
        r8.close();	 Catch:{ Exception -> 0x00c4 }
    L_0x00c4:
        if (r1 == 0) goto L_0x00c9;
    L_0x00c6:
        r1.close();	 Catch:{ Exception -> 0x00c9 }
    L_0x00c9:
        r8.close();	 Catch:{ Exception -> 0x00cc }
    L_0x00cc:
        if (r9 == 0) goto L_0x00d1;
    L_0x00ce:
        r9.disconnect();	 Catch:{ Exception -> 0x00d1 }
    L_0x00d1:
        return r0;
    L_0x00d2:
        r2 = r6.c;	 Catch:{ IOException -> 0x00f1 }
        r3 = "FileManager";	 Catch:{ IOException -> 0x00f1 }
        r4 = "Loaded resource at ";	 Catch:{ IOException -> 0x00f1 }
        r5 = java.lang.String.valueOf(r7);	 Catch:{ IOException -> 0x00f1 }
        r4 = r4.concat(r5);	 Catch:{ IOException -> 0x00f1 }
        r2.d(r3, r4);	 Catch:{ IOException -> 0x00f1 }
        if (r1 == 0) goto L_0x00e8;
    L_0x00e5:
        r1.close();	 Catch:{ Exception -> 0x00e8 }
    L_0x00e8:
        r8.close();	 Catch:{ Exception -> 0x00eb }
    L_0x00eb:
        if (r9 == 0) goto L_0x00f0;
    L_0x00ed:
        r9.disconnect();	 Catch:{ Exception -> 0x00f0 }
    L_0x00f0:
        return r8;
    L_0x00f1:
        r2 = move-exception;
        goto L_0x010f;
    L_0x00f3:
        r8.close();	 Catch:{ Exception -> 0x00f6 }
    L_0x00f6:
        if (r9 == 0) goto L_0x00fb;
    L_0x00f8:
        r9.disconnect();	 Catch:{ Exception -> 0x00fb }
    L_0x00fb:
        return r0;
    L_0x00fc:
        r7 = move-exception;
        goto L_0x0132;
    L_0x00fe:
        r2 = move-exception;
        r1 = r0;
        goto L_0x010f;
    L_0x0101:
        r7 = move-exception;
        r9 = r0;
        goto L_0x0132;
    L_0x0104:
        r2 = move-exception;
        r9 = r0;
        goto L_0x010e;
    L_0x0107:
        r7 = move-exception;
        r8 = r0;
        r9 = r8;
        goto L_0x0132;
    L_0x010b:
        r2 = move-exception;
        r8 = r0;
        r9 = r8;
    L_0x010e:
        r1 = r9;
    L_0x010f:
        r3 = r6.c;	 Catch:{ all -> 0x0130 }
        r4 = "FileManager";	 Catch:{ all -> 0x0130 }
        r5 = "Error loading ";	 Catch:{ all -> 0x0130 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ all -> 0x0130 }
        r7 = r5.concat(r7);	 Catch:{ all -> 0x0130 }
        r3.e(r4, r7, r2);	 Catch:{ all -> 0x0130 }
        if (r1 == 0) goto L_0x0125;
    L_0x0122:
        r1.close();	 Catch:{ Exception -> 0x0125 }
    L_0x0125:
        if (r8 == 0) goto L_0x012a;
    L_0x0127:
        r8.close();	 Catch:{ Exception -> 0x012a }
    L_0x012a:
        if (r9 == 0) goto L_0x012f;
    L_0x012c:
        r9.disconnect();	 Catch:{ Exception -> 0x012f }
    L_0x012f:
        return r0;
    L_0x0130:
        r7 = move-exception;
        r0 = r1;
    L_0x0132:
        if (r0 == 0) goto L_0x0137;
    L_0x0134:
        r0.close();	 Catch:{ Exception -> 0x0137 }
    L_0x0137:
        if (r8 == 0) goto L_0x013c;
    L_0x0139:
        r8.close();	 Catch:{ Exception -> 0x013c }
    L_0x013c:
        if (r9 == 0) goto L_0x0141;
    L_0x013e:
        r9.disconnect();	 Catch:{ Exception -> 0x0141 }
    L_0x0141:
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.av.a(java.lang.String, java.util.List, boolean):java.io.ByteArrayOutputStream");
    }

    java.io.File a(java.lang.String r6, android.content.Context r7, boolean r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.applovin.impl.sdk.av.a(java.lang.String, android.content.Context, boolean):java.io.File. bs: []
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
        r5 = this;
        r0 = com.applovin.sdk.AppLovinSdkUtils.isValidString(r6);
        r1 = 0;
        if (r0 != 0) goto L_0x0015;
    L_0x0007:
        r6 = r5.b;
        r6 = r6.getLogger();
        r7 = "FileManager";
        r8 = "Nothing to look up, skipping...";
        r6.d(r7, r8);
        return r1;
    L_0x0015:
        r0 = r5.c;
        r2 = "FileManager";
        r3 = "Looking up cached resource: ";
        r4 = java.lang.String.valueOf(r6);
        r3 = r3.concat(r4);
        r0.d(r2, r3);
        r0 = r5.a(r7);
        if (r0 != 0) goto L_0x002f;
    L_0x002c:
        if (r8 != 0) goto L_0x002f;
    L_0x002e:
        return r1;
    L_0x002f:
        r8 = "icon";
        r8 = r6.contains(r8);
        if (r8 == 0) goto L_0x0047;
    L_0x0037:
        r8 = "/";
        r0 = "_";
        r6 = r6.replace(r8, r0);
        r8 = ".";
        r0 = "_";
        r6 = r6.replace(r8, r0);
    L_0x0047:
        r8 = r5.d;
        monitor-enter(r8);
        r7 = r5.e(r7);	 Catch:{ all -> 0x005a }
        r0 = new java.io.File;	 Catch:{ all -> 0x005a }
        r0.<init>(r7, r6);	 Catch:{ all -> 0x005a }
        r7.mkdirs();	 Catch:{ Exception -> 0x0058 }
        monitor-exit(r8);	 Catch:{ all -> 0x005a }
        return r0;	 Catch:{ all -> 0x005a }
    L_0x0058:
        monitor-exit(r8);	 Catch:{ all -> 0x005a }
        return r1;	 Catch:{ all -> 0x005a }
    L_0x005a:
        r6 = move-exception;	 Catch:{ all -> 0x005a }
        monitor-exit(r8);	 Catch:{ all -> 0x005a }
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.av.a(java.lang.String, android.content.Context, boolean):java.io.File");
    }

    String a(Context context, String str, String str2, List<String> list, boolean z, z zVar) throws MalformedURLException {
        return a(context, str, str2, list, z, false, zVar);
    }

    String a(Context context, String str, String str2, List<String> list, boolean z, boolean z2, z zVar) throws MalformedURLException {
        if (AppLovinSdkUtils.isValidString(str)) {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            if (AppLovinSdkUtils.isValidString(lastPathSegment) && AppLovinSdkUtils.isValidString(str2)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str2);
                stringBuilder.append(lastPathSegment);
                lastPathSegment = stringBuilder.toString();
            }
            File a = a(lastPathSegment, context, false);
            if (!a(a, str, list, z, zVar)) {
                return null;
            }
            this.c.d("FileManager", "Caching succeeded for file ".concat(String.valueOf(lastPathSegment)));
            return z2 ? Uri.fromFile(a).toString() : lastPathSegment;
        } else {
            this.b.getLogger().d("FileManager", "Nothing to cache, skipping...");
            return null;
        }
    }

    protected boolean a(Context context) {
        if (ab.a("android.permission.WRITE_EXTERNAL_STORAGE", context)) {
            return true;
        }
        if (ab.f() && !((Boolean) this.b.get(ea.cc)).booleanValue()) {
            return true;
        }
        this.b.getLogger().w("FileManager", "Application lacks required WRITE_EXTERNAL_STORAGE manifest permission.");
        return false;
    }

    boolean a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        if (file == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder("Caching ");
        stringBuilder.append(file.getAbsolutePath());
        stringBuilder.append("...");
        this.c.d("FileManager", stringBuilder.toString());
        StringBuilder stringBuilder2;
        if (byteArrayOutputStream == null || byteArrayOutputStream.size() <= 0) {
            stringBuilder2 = new StringBuilder("No data for ");
            stringBuilder2.append(file.getAbsolutePath());
            this.c.w("FileManager", stringBuilder2.toString());
            return false;
        } else if (b(byteArrayOutputStream, file)) {
            this.c.d("FileManager", "Caching completed for ".concat(String.valueOf(file)));
            return true;
        } else {
            stringBuilder2 = new StringBuilder("Unable to cache ");
            stringBuilder2.append(file.getAbsolutePath());
            this.c.e("FileManager", stringBuilder2.toString());
            return false;
        }
    }

    boolean a(File file, String str, List<String> list, z zVar) {
        return a(file, str, list, true, zVar);
    }

    boolean a(File file, String str, List<String> list, boolean z, z zVar) {
        if (file == null || !file.exists() || file.isDirectory()) {
            ByteArrayOutputStream a = a(str, (List) list, z);
            if (zVar != null) {
                zVar.a((long) a.size());
            }
            return a(a, file);
        }
        this.b.getLogger().d("FileManager", "File exists for ".concat(String.valueOf(str)));
        if (zVar != null) {
            zVar.b(file.length());
        }
        return true;
    }

    public boolean a(String str, Context context) {
        boolean b;
        synchronized (this.d) {
            b = b(str, context, false);
        }
        return b;
    }

    public List<File> b(Context context) {
        File e = e(context);
        if (!e.isDirectory()) {
            return new ArrayList(0);
        }
        List<File> asList;
        synchronized (this.d) {
            asList = Arrays.asList(e.listFiles());
        }
        return asList;
    }

    boolean b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.d) {
            File a = a(str, context, z);
            z2 = (a == null || !a.exists() || a.isDirectory()) ? false : true;
        }
        return z2;
    }

    boolean c(Context context) {
        if (((Boolean) this.b.get(ea.cd)).booleanValue()) {
            try {
                a(".nomedia", context, false);
                File file = new File(e(context), ".nomedia");
                if (file.exists()) {
                    return true;
                }
                StringBuilder stringBuilder = new StringBuilder("Dropping .nomedia file at ");
                stringBuilder.append(file.getAbsolutePath());
                this.b.getLogger().d("FileManager", stringBuilder.toString());
                return file.createNewFile();
            } catch (Throwable e) {
                this.b.getLogger().w("FileManager", "Failed to create nomedia file", e);
            }
        }
        return false;
    }

    void d(Context context) {
        try {
            if (!a()) {
                return;
            }
            if (this.b.e()) {
                this.c.d("FileManager", "Compacting cache...");
                synchronized (this.d) {
                    a(f(context), context);
                }
                return;
            }
            this.c.e("FileManager", "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
        } catch (Throwable e) {
            this.c.e("FileManager", "Caught exception while compacting cache!", e);
            this.b.getSettingsManager().a(ea.aY, Boolean.FALSE);
            this.b.getSettingsManager().a();
        }
    }
}
