package jp.naver.line.android.common.util.io;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import jp.naver.line.android.common.e;
import jp.naver.line.android.util.cl;

public final class i {
    @Deprecated
    public static File a() {
        return new File(e.c().getApplicationInfo().dataDir);
    }

    @java.lang.Deprecated
    public static java.io.File b() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.i.b():java.io.File. bs: []
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
        r0 = jp.naver.line.android.common.e.c();
        r0 = r0.getExternalCacheDir();
        if (r0 != 0) goto L_0x0057;
    L_0x000a:
        r1 = h();	 Catch:{ d -> 0x0021 }
        r2 = new java.io.File;	 Catch:{ d -> 0x0021 }
        r3 = "storage";	 Catch:{ d -> 0x0021 }
        r2.<init>(r1, r3);	 Catch:{ d -> 0x0021 }
        r0 = r2.exists();
        if (r0 != 0) goto L_0x0058;
    L_0x001b:
        r2.mkdirs();
        goto L_0x0058;
    L_0x001f:
        r1 = move-exception;
        goto L_0x004b;
    L_0x0021:
        r1 = 3;
        r1 = new java.lang.CharSequence[r1];	 Catch:{ all -> 0x001f }
        r2 = 0;	 Catch:{ all -> 0x001f }
        r3 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ all -> 0x001f }
        r3 = r3.getAbsolutePath();	 Catch:{ all -> 0x001f }
        r1[r2] = r3;	 Catch:{ all -> 0x001f }
        r2 = 1;	 Catch:{ all -> 0x001f }
        r3 = l();	 Catch:{ all -> 0x001f }
        r1[r2] = r3;	 Catch:{ all -> 0x001f }
        r2 = 2;	 Catch:{ all -> 0x001f }
        r3 = "storage";	 Catch:{ all -> 0x001f }
        r1[r2] = r3;	 Catch:{ all -> 0x001f }
        r1 = jp.naver.line.android.util.cl.a(r1);	 Catch:{ all -> 0x001f }
        r2 = new java.io.File;	 Catch:{ all -> 0x001f }
        r2.<init>(r1);	 Catch:{ all -> 0x001f }
        r0 = r2.exists();
        if (r0 != 0) goto L_0x0058;
    L_0x004a:
        goto L_0x001b;
    L_0x004b:
        if (r0 == 0) goto L_0x0056;
    L_0x004d:
        r2 = r0.exists();
        if (r2 != 0) goto L_0x0056;
    L_0x0053:
        r0.mkdirs();
    L_0x0056:
        throw r1;
    L_0x0057:
        r2 = r0;
    L_0x0058:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.i.b():java.io.File");
    }

    public static long c() {
        return e.a(Environment.getExternalStorageDirectory().getAbsolutePath(), false);
    }

    public static void a(long j) throws d, g {
        if (j()) {
            long c = c();
            if (c < j) {
                StringBuilder stringBuilder = new StringBuilder("Not enough external disk space. Available=");
                stringBuilder.append(c);
                stringBuilder.append(" bytes, required=");
                stringBuilder.append(j);
                stringBuilder.append(" bytes");
                throw new g(stringBuilder.toString());
            }
            return;
        }
        throw new d("External storage is not available");
    }

    public static long d() {
        return e.a(Environment.getDataDirectory().getAbsolutePath(), false);
    }

    public static void b(long j) throws h {
        long d = d();
        if (d < j) {
            StringBuilder stringBuilder = new StringBuilder("Not enough internal disk space. Available=");
            stringBuilder.append(d);
            stringBuilder.append(" bytes, required=");
            stringBuilder.append(j);
            stringBuilder.append(" bytes");
            throw new h(stringBuilder.toString());
        }
    }

    public static File e() throws d {
        return a("Pictures", "LINE_MOVIE");
    }

    public static File f() throws d {
        return a("Pictures", "LINE");
    }

    public static File g() throws d {
        return a(Environment.DIRECTORY_DOWNLOADS, "LINE");
    }

    private static File a(String... strArr) throws d {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        arrayList.add(0, Environment.getExternalStorageDirectory().getAbsolutePath());
        File file = new File(cl.a((CharSequence[]) arrayList.toArray(strArr)));
        StringBuilder stringBuilder;
        if (file.exists()) {
            if (!file.isDirectory()) {
                stringBuilder = new StringBuilder("createMediaDirectoryInternal has been failed. path=");
                stringBuilder.append(file.getAbsolutePath());
                stringBuilder.append(" is not a directory.");
                throw new d(stringBuilder.toString());
            }
        } else if (!file.mkdirs()) {
            stringBuilder = new StringBuilder("createMediaDirectoryInternal has been failed. path=");
            stringBuilder.append(file.getAbsolutePath());
            throw new d(stringBuilder.toString());
        }
        return file;
    }

    public static void a(File file) throws d {
        StringBuilder stringBuilder;
        if (!file.exists() && !file.mkdir()) {
            stringBuilder = new StringBuilder("mkdirToExternalStorage failure.(path = ");
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(")");
            throw new d(stringBuilder.toString());
        } else if (!file.isDirectory() && !file.delete() && !file.mkdir()) {
            stringBuilder = new StringBuilder("mkdirToExternalStorage failure. File already exists.(path = ");
            stringBuilder.append(file.getAbsolutePath());
            stringBuilder.append(")");
            throw new d(stringBuilder.toString());
        }
    }

    public static File a(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null || !k()) {
            return context.getCacheDir();
        }
        return externalCacheDir;
    }

    @java.lang.Deprecated
    public static java.io.File h() throws jp.naver.line.android.common.util.io.d {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.i.h():java.io.File. bs: []
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
        r0 = k();
        if (r0 == 0) goto L_0x0067;
    L_0x0006:
        r0 = jp.naver.line.android.common.e.c();
        r0 = r0.getPackageName();
        r1 = new java.io.File;
        r2 = android.os.Environment.getExternalStorageDirectory();
        r3 = l();
        r1.<init>(r2, r3);
        r2 = r1.exists();
        if (r2 != 0) goto L_0x0066;
    L_0x0021:
        r1 = new java.io.File;
        r2 = android.os.Environment.getExternalStorageDirectory();
        r3 = "Android";
        r1.<init>(r2, r3);
        r2 = new java.io.File;
        r3 = "data";
        r2.<init>(r1, r3);
        r1 = new java.io.File;
        r1.<init>(r2, r0);
        r0 = r1.mkdirs();
        if (r0 == 0) goto L_0x0049;
    L_0x003e:
        r0 = new java.io.File;
        r2 = ".nomedia";
        r0.<init>(r1, r2);
        r0.createNewFile();	 Catch:{ IOException -> 0x0048 }
    L_0x0048:
        return r1;
    L_0x0049:
        r0 = new jp.naver.line.android.common.util.io.d;
        r2 = new java.lang.StringBuilder;
        r3 = "Failed to create baseDir (path=";
        r2.<init>(r3);
        r1 = r1.getAbsolutePath();
        r2.append(r1);
        r1 = ")";
        r2.append(r1);
        r1 = r2.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0066:
        return r1;
    L_0x0067:
        r0 = new jp.naver.line.android.common.util.io.d;
        r1 = "Failed to create baseDir. External storage is not writable";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.i.h():java.io.File");
    }

    @Deprecated
    public static String i() {
        String str = null;
        if (TextUtils.isEmpty(str)) {
            return l();
        }
        CharSequence[] charSequenceArr = new CharSequence[2];
        charSequenceArr[0] = l();
        if (str.startsWith("u")) {
            str = str.substring(1);
        }
        StringBuilder stringBuilder = new StringBuilder(String.format("%08X", new Object[]{Integer.valueOf(str.hashCode())}));
        int length = str.length() >> 1;
        stringBuilder.append(String.format("%08X", new Object[]{Integer.valueOf(str.substring(length).concat(str.substring(0, length)).hashCode())}));
        charSequenceArr[1] = stringBuilder.toString();
        return cl.a(charSequenceArr);
    }

    private static String l() {
        Context c = e.c();
        return cl.a("Android", "data", c.getPackageName());
    }

    public static boolean j() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState) || "checking".equals(externalStorageState);
    }

    public static boolean k() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
