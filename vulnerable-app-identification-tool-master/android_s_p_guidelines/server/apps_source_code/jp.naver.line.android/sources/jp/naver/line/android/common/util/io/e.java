package jp.naver.line.android.common.util.io;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventTypes;
import defpackage.aden;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class e {
    public static void a(File file, boolean z) {
        a(file, null, z);
    }

    public static final void a(File file, File[] fileArr, boolean z) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            int i;
            if (fileArr != null) {
                int length = fileArr.length;
                i = 0;
                while (i < length) {
                    if (!file.equals(fileArr[i])) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    a(a, fileArr, z);
                }
            }
            if (z) {
                file.delete();
            }
        }
    }

    @Deprecated
    public static void a(File file, File file2) throws IOException {
        Closeable channel;
        InputStream inputStream;
        OutputStream outputStream;
        Throwable th;
        if (file != null && file2 != null && !file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            Closeable closeable = null;
            try {
                InputStream fileInputStream = new FileInputStream(file);
                try {
                    OutputStream fileOutputStream = new FileOutputStream(file2);
                    try {
                        channel = fileInputStream.getChannel();
                    } catch (Throwable th2) {
                        inputStream = fileInputStream;
                        outputStream = fileOutputStream;
                        th = th2;
                        channel = null;
                        aden.a(closeable);
                        aden.a(channel);
                        aden.a(outputStream);
                        aden.a(inputStream);
                        throw th;
                    }
                    try {
                        Closeable channel2 = fileOutputStream.getChannel();
                        try {
                            channel.transferTo(0, channel.size(), channel2);
                            aden.a(channel2);
                            aden.a(channel);
                            aden.a(fileOutputStream);
                            aden.a(fileInputStream);
                        } catch (Throwable th3) {
                            inputStream = fileInputStream;
                            outputStream = fileOutputStream;
                            th = th3;
                            closeable = channel2;
                            aden.a(closeable);
                            aden.a(channel);
                            aden.a(outputStream);
                            aden.a(inputStream);
                            throw th;
                        }
                    } catch (Throwable th4) {
                        InputStream inputStream2 = fileInputStream;
                        outputStream = fileOutputStream;
                        th = th4;
                        inputStream = inputStream2;
                        aden.a(closeable);
                        aden.a(channel);
                        aden.a(outputStream);
                        aden.a(inputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    channel = null;
                    inputStream = fileInputStream;
                    outputStream = channel;
                    aden.a(closeable);
                    aden.a(channel);
                    aden.a(outputStream);
                    aden.a(inputStream);
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
                channel = null;
                outputStream = channel;
                inputStream = outputStream;
                aden.a(closeable);
                aden.a(channel);
                aden.a(outputStream);
                aden.a(inputStream);
                throw th;
            }
        }
    }

    @Deprecated
    public static void a(InputStream inputStream, File file) throws IOException {
        Throwable th;
        if (inputStream != null && file != null) {
            OutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 8192);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            aden.a(fileOutputStream);
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    aden.a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                aden.a(fileOutputStream);
                throw th;
            }
        }
    }

    public static File a(Uri uri) throws FileNotFoundException {
        Object b = b(uri);
        if (!TextUtils.isEmpty(b)) {
            return new File(b);
        }
        throw new FileNotFoundException("File is not found represented as ".concat(String.valueOf(uri)));
    }

    public static String b(Uri uri) {
        Throwable th;
        String str = "_data";
        String str2 = null;
        if (uri == null) {
            return null;
        }
        Context c = jp.naver.line.android.common.e.c();
        if (!AppLovinEventTypes.USER_VIEWED_CONTENT.equals(uri.getScheme())) {
            return uri.getPath();
        }
        Cursor query;
        try {
            query = c.getContentResolver().query(uri, new String[]{str}, null, null, null);
            try {
                if (query.moveToFirst()) {
                    str2 = query.getString(query.getColumnIndexOrThrow(str));
                }
                if (query != null) {
                    query.close();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static final boolean a(File file) {
        return (file == null || !file.exists()) ? false : file.delete();
    }

    @android.annotation.SuppressLint({"NewApi"})
    public static long a(java.lang.String r5, boolean r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.e.a(java.lang.String, boolean):long. bs: []
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
        r0 = android.text.TextUtils.isEmpty(r5);
        if (r0 == 0) goto L_0x0009;
    L_0x0006:
        r5 = 0;
        return r5;
    L_0x0009:
        r0 = new android.os.StatFs;
        r0.<init>(r5);
        r5 = android.os.Build.VERSION.SDK_INT;
        r1 = 18;
        if (r5 < r1) goto L_0x0026;
    L_0x0014:
        r1 = r0.getBlockSizeLong();	 Catch:{ NoSuchMethodError -> 0x0026 }
        if (r6 == 0) goto L_0x001f;	 Catch:{ NoSuchMethodError -> 0x0026 }
    L_0x001a:
        r3 = r0.getBlockCountLong();	 Catch:{ NoSuchMethodError -> 0x0026 }
        goto L_0x0023;	 Catch:{ NoSuchMethodError -> 0x0026 }
    L_0x001f:
        r3 = r0.getAvailableBlocksLong();	 Catch:{ NoSuchMethodError -> 0x0026 }
    L_0x0023:
        r3 = r3 * r1;
        return r3;
    L_0x0026:
        r5 = r0.getBlockSize();
        r1 = (long) r5;
        if (r6 == 0) goto L_0x0033;
    L_0x002d:
        r5 = r0.getBlockCount();
    L_0x0031:
        r5 = (long) r5;
        goto L_0x0038;
    L_0x0033:
        r5 = r0.getAvailableBlocks();
        goto L_0x0031;
    L_0x0038:
        r5 = r5 * r1;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.e.a(java.lang.String, boolean):long");
    }

    public static java.lang.String a(java.lang.String r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.e.a(java.lang.String):java.lang.String. bs: []
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
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x001d }
        if (r0 != 0) goto L_0x001a;	 Catch:{ Exception -> 0x001d }
    L_0x0006:
        r0 = "\\.";	 Catch:{ Exception -> 0x001d }
        r1 = r1.split(r0);	 Catch:{ Exception -> 0x001d }
        r0 = r1.length;	 Catch:{ Exception -> 0x001d }
        r0 = r0 + -1;	 Catch:{ Exception -> 0x001d }
        r1 = r1[r0];	 Catch:{ Exception -> 0x001d }
        r0 = android.webkit.MimeTypeMap.getSingleton();	 Catch:{ Exception -> 0x001d }
        r1 = r0.getMimeTypeFromExtension(r1);	 Catch:{ Exception -> 0x001d }
        return r1;
    L_0x001a:
        r1 = "";
        return r1;
    L_0x001d:
        r1 = "";
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.e.a(java.lang.String):java.lang.String");
    }

    private static List<File> c(File file, boolean z) {
        if (file == null) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        List<File> arrayList = new ArrayList();
        for (File file2 : listFiles) {
            if (!file2.isHidden() || !z) {
                if (file2.isDirectory()) {
                    arrayList.addAll(c(file2, z));
                } else {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> b(File file) {
        List<File> c = c(file, true);
        if (c == null) {
            return null;
        }
        Collections.sort(c, new Comparator<File>() {
            public final /* synthetic */ int compare(Object obj, Object obj2) {
                return ((File) obj).getAbsolutePath().compareTo(((File) obj2).getAbsolutePath());
            }
        });
        return c;
    }

    public static long c(java.io.File r6) throws java.util.zip.ZipException, java.io.IOException {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.e.c(java.io.File):long. bs: []
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
        if (r6 == 0) goto L_0x002f;
    L_0x0002:
        r0 = 0;
        r2 = 0;
        r3 = new java.util.zip.ZipFile;	 Catch:{ all -> 0x0027 }
        r3.<init>(r6);	 Catch:{ all -> 0x0027 }
        r6 = r3.entries();	 Catch:{ all -> 0x0025 }
    L_0x000e:
        r2 = r6.hasMoreElements();	 Catch:{ all -> 0x0025 }
        if (r2 == 0) goto L_0x0021;	 Catch:{ all -> 0x0025 }
    L_0x0014:
        r2 = r6.nextElement();	 Catch:{ all -> 0x0025 }
        r2 = (java.util.zip.ZipEntry) r2;	 Catch:{ all -> 0x0025 }
        r4 = r2.getSize();	 Catch:{ all -> 0x0025 }
        r2 = 0;
        r0 = r0 + r4;
        goto L_0x000e;
    L_0x0021:
        r3.close();	 Catch:{ Exception -> 0x0024 }
    L_0x0024:
        return r0;
    L_0x0025:
        r6 = move-exception;
        goto L_0x0029;
    L_0x0027:
        r6 = move-exception;
        r3 = r2;
    L_0x0029:
        if (r3 == 0) goto L_0x002e;
    L_0x002b:
        r3.close();	 Catch:{ Exception -> 0x002e }
    L_0x002e:
        throw r6;
    L_0x002f:
        r6 = new java.util.zip.ZipException;
        r0 = "File is not found or null";
        r6.<init>(r0);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.e.c(java.io.File):long");
    }

    public static long d(java.io.File r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.e.d(java.io.File):long. bs: []
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
        r0 = defpackage.adek.e(r2);	 Catch:{ RuntimeException -> 0x0005 }
        return r0;
    L_0x0005:
        r0 = 0;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.e.d(java.io.File):long");
    }

    @Deprecated
    public static boolean b(File file, boolean z) {
        if (file == null) {
            return false;
        }
        if (z && file.isFile()) {
            a(file);
        }
        return file.mkdirs();
    }

    public static boolean a(java.io.File r5, java.io.Serializable r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:jp.naver.line.android.common.util.io.e.a(java.io.File, java.io.Serializable):boolean. bs: []
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
        r1 = 0;
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0079, all -> 0x0070 }
        r2.<init>();	 Catch:{ IOException -> 0x0079, all -> 0x0070 }
        r3 = new java.io.ObjectOutputStream;	 Catch:{ IOException -> 0x007a, all -> 0x006e }
        r3.<init>(r2);	 Catch:{ IOException -> 0x007a, all -> 0x006e }
        r3.writeObject(r6);	 Catch:{ IOException -> 0x006c, all -> 0x0069 }
        r6 = r2.toByteArray();	 Catch:{ IOException -> 0x006c, all -> 0x0069 }
        r6 = java.nio.ByteBuffer.wrap(r6);	 Catch:{ IOException -> 0x006c, all -> 0x0069 }
        defpackage.aden.a(r2);
        defpackage.aden.a(r3);
        r2 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r5.getAbsolutePath();
        r3.append(r4);
        r4 = "_temp";
        r3.append(r4);
        r3 = r3.toString();
        r2.<init>(r3);
        r3 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        r3.<init>(r2);	 Catch:{ IOException -> 0x0056, all -> 0x0053 }
        r4 = r3.getChannel();	 Catch:{ IOException -> 0x0057 }
        r4.write(r6);	 Catch:{ IOException -> 0x0051, all -> 0x004e }
        r2.renameTo(r5);	 Catch:{ IOException -> 0x0051, all -> 0x004e }
        defpackage.aden.a(r4);
        defpackage.aden.a(r3);
        r5 = 1;
        return r5;
    L_0x004e:
        r5 = move-exception;
        r1 = r4;
        goto L_0x0062;
    L_0x0051:
        r1 = r4;
        goto L_0x0057;
    L_0x0053:
        r5 = move-exception;
        r3 = r1;
        goto L_0x0062;
    L_0x0056:
        r3 = r1;
    L_0x0057:
        r2.delete();	 Catch:{ all -> 0x0061 }
        defpackage.aden.a(r1);
        defpackage.aden.a(r3);
        return r0;
    L_0x0061:
        r5 = move-exception;
    L_0x0062:
        defpackage.aden.a(r1);
        defpackage.aden.a(r3);
        throw r5;
    L_0x0069:
        r5 = move-exception;
        r1 = r3;
        goto L_0x0072;
    L_0x006c:
        r1 = r3;
        goto L_0x007a;
    L_0x006e:
        r5 = move-exception;
        goto L_0x0072;
    L_0x0070:
        r5 = move-exception;
        r2 = r1;
    L_0x0072:
        defpackage.aden.a(r2);
        defpackage.aden.a(r1);
        throw r5;
    L_0x0079:
        r2 = r1;
    L_0x007a:
        defpackage.aden.a(r2);
        defpackage.aden.a(r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.naver.line.android.common.util.io.e.a(java.io.File, java.io.Serializable):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Object e(File file) {
        Throwable th;
        InputStream inputStream = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            InputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            try {
                Object readObject = objectInputStream.readObject();
                aden.a(objectInputStream);
                return readObject;
            } catch (Throwable th2) {
                th = th2;
                inputStream = objectInputStream;
                aden.a(inputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            aden.a(inputStream);
            throw th;
        }
    }

    public static boolean f(File file) {
        return file != null && file.exists();
    }
}
