package com.facebook.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.s;
import java.io.File;
import java.util.UUID;

public final class at {
    private static final String a = "com.facebook.internal.at";
    private static File b;

    private at() {
    }

    public static au a(UUID uuid, Bitmap bitmap) {
        bn.a((Object) uuid, "callId");
        bn.a((Object) bitmap, "attachmentBitmap");
        return new au(uuid, bitmap, null, (byte) 0);
    }

    public static au a(UUID uuid, Uri uri) {
        bn.a((Object) uuid, "callId");
        bn.a((Object) uri, "attachmentUri");
        return new au(uuid, null, uri, (byte) 0);
    }

    public static void a(java.util.Collection<com.facebook.internal.au> r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.at.a(java.util.Collection):void. bs: []
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
        if (r5 == 0) goto L_0x00cb;
    L_0x0002:
        r0 = r5.size();
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        goto L_0x00cb;
    L_0x000a:
        r0 = b;
        if (r0 != 0) goto L_0x0015;
    L_0x000e:
        r0 = a();
        com.facebook.internal.bj.a(r0);
    L_0x0015:
        r0 = a();
        r0.mkdirs();
        r0 = new java.util.ArrayList;
        r0.<init>();
        r5 = r5.iterator();	 Catch:{ IOException -> 0x009f }
    L_0x0025:
        r1 = r5.hasNext();	 Catch:{ IOException -> 0x009f }
        if (r1 == 0) goto L_0x009e;	 Catch:{ IOException -> 0x009f }
    L_0x002b:
        r1 = r5.next();	 Catch:{ IOException -> 0x009f }
        r1 = (com.facebook.internal.au) r1;	 Catch:{ IOException -> 0x009f }
        r2 = r1.g;	 Catch:{ IOException -> 0x009f }
        if (r2 == 0) goto L_0x0025;	 Catch:{ IOException -> 0x009f }
    L_0x0037:
        r2 = r1.a;	 Catch:{ IOException -> 0x009f }
        r3 = r1.c;	 Catch:{ IOException -> 0x009f }
        r4 = 1;	 Catch:{ IOException -> 0x009f }
        r2 = a(r2, r3, r4);	 Catch:{ IOException -> 0x009f }
        r0.add(r2);	 Catch:{ IOException -> 0x009f }
        r3 = r1.d;	 Catch:{ IOException -> 0x009f }
        if (r3 == 0) goto L_0x0066;	 Catch:{ IOException -> 0x009f }
    L_0x004d:
        r1 = r1.d;	 Catch:{ IOException -> 0x009f }
        r3 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x009f }
        r3.<init>(r2);	 Catch:{ IOException -> 0x009f }
        r2 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ all -> 0x0061 }
        r4 = 100;	 Catch:{ all -> 0x0061 }
        r1.compress(r2, r4, r3);	 Catch:{ all -> 0x0061 }
        com.facebook.internal.bj.a(r3);	 Catch:{ IOException -> 0x009f }
        goto L_0x0025;	 Catch:{ IOException -> 0x009f }
    L_0x0061:
        r5 = move-exception;	 Catch:{ IOException -> 0x009f }
        com.facebook.internal.bj.a(r3);	 Catch:{ IOException -> 0x009f }
        throw r5;	 Catch:{ IOException -> 0x009f }
    L_0x0066:
        r3 = r1.e;	 Catch:{ IOException -> 0x009f }
        if (r3 == 0) goto L_0x0025;	 Catch:{ IOException -> 0x009f }
    L_0x006c:
        r3 = r1.e;	 Catch:{ IOException -> 0x009f }
        r1 = r1.f;	 Catch:{ IOException -> 0x009f }
        r4 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x009f }
        r4.<init>(r2);	 Catch:{ IOException -> 0x009f }
        if (r1 != 0) goto L_0x0087;
    L_0x007b:
        r1 = new java.io.FileInputStream;	 Catch:{ all -> 0x0085 }
        r2 = r3.getPath();	 Catch:{ all -> 0x0085 }
        r1.<init>(r2);	 Catch:{ all -> 0x0085 }
        goto L_0x0093;	 Catch:{ all -> 0x0085 }
    L_0x0085:
        r5 = move-exception;	 Catch:{ all -> 0x0085 }
        goto L_0x009a;	 Catch:{ all -> 0x0085 }
    L_0x0087:
        r1 = com.facebook.s.f();	 Catch:{ all -> 0x0085 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0085 }
        r1 = r1.openInputStream(r3);	 Catch:{ all -> 0x0085 }
    L_0x0093:
        com.facebook.internal.bj.a(r1, r4);	 Catch:{ all -> 0x0085 }
        com.facebook.internal.bj.a(r4);	 Catch:{ IOException -> 0x009f }
        goto L_0x0025;	 Catch:{ IOException -> 0x009f }
    L_0x009a:
        com.facebook.internal.bj.a(r4);	 Catch:{ IOException -> 0x009f }
        throw r5;	 Catch:{ IOException -> 0x009f }
    L_0x009e:
        return;
    L_0x009f:
        r5 = move-exception;
        r1 = a;
        r2 = "Got unexpected exception:";
        r3 = java.lang.String.valueOf(r5);
        r2 = r2.concat(r3);
        android.util.Log.e(r1, r2);
        r0 = r0.iterator();
    L_0x00b3:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x00c5;
    L_0x00b9:
        r1 = r0.next();
        r1 = (java.io.File) r1;
        r1.delete();	 Catch:{ Exception -> 0x00c3 }
        goto L_0x00b3;
        goto L_0x00b3;
    L_0x00c5:
        r0 = new com.facebook.n;
        r0.<init>(r5);
        throw r0;
    L_0x00cb:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.at.a(java.util.Collection):void");
    }

    public static void a(UUID uuid) {
        File a = a(uuid, false);
        if (a != null) {
            bj.a(a);
        }
    }

    public static java.io.File a(java.util.UUID r1, java.lang.String r2) throws java.io.FileNotFoundException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.at.a(java.util.UUID, java.lang.String):java.io.File. bs: []
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
        r0 = com.facebook.internal.bj.a(r2);
        if (r0 != 0) goto L_0x0014;
    L_0x0006:
        if (r1 == 0) goto L_0x0014;
    L_0x0008:
        r0 = 0;
        r1 = a(r1, r2, r0);	 Catch:{ IOException -> 0x000e }
        return r1;
    L_0x000e:
        r1 = new java.io.FileNotFoundException;
        r1.<init>();
        throw r1;
    L_0x0014:
        r1 = new java.io.FileNotFoundException;
        r1.<init>();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.at.a(java.util.UUID, java.lang.String):java.io.File");
    }

    private static synchronized File a() {
        File file;
        synchronized (at.class) {
            if (b == null) {
                b = new File(s.f().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
            }
            file = b;
        }
        return file;
    }

    private static File a(UUID uuid, boolean z) {
        if (b == null) {
            return null;
        }
        File file = new File(b, uuid.toString());
        if (z && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static java.io.File a(java.util.UUID r2, java.lang.String r3, boolean r4) throws java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.internal.at.a(java.util.UUID, java.lang.String, boolean):java.io.File. bs: []
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
        r2 = a(r2, r4);
        r4 = 0;
        if (r2 != 0) goto L_0x0008;
    L_0x0007:
        return r4;
    L_0x0008:
        r0 = new java.io.File;	 Catch:{ UnsupportedEncodingException -> 0x0014 }
        r1 = "UTF-8";	 Catch:{ UnsupportedEncodingException -> 0x0014 }
        r3 = java.net.URLEncoder.encode(r3, r1);	 Catch:{ UnsupportedEncodingException -> 0x0014 }
        r0.<init>(r2, r3);	 Catch:{ UnsupportedEncodingException -> 0x0014 }
        return r0;
    L_0x0014:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.at.a(java.util.UUID, java.lang.String, boolean):java.io.File");
    }
}
