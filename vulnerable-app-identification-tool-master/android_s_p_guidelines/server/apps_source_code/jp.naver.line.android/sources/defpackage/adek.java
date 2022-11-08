package defpackage;

import com.google.obf.ly;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedList;

/* renamed from: adek */
public final class adek {
    public static final BigInteger a;
    public static final BigInteger b;
    public static final BigInteger c = a.multiply(b);
    public static final BigInteger d = a.multiply(c);
    public static final BigInteger e = a.multiply(d);
    public static final BigInteger f = a.multiply(e);
    public static final BigInteger g = BigInteger.valueOf(1024).multiply(BigInteger.valueOf(1152921504606846976L));
    public static final BigInteger h = a.multiply(g);
    public static final File[] i = new File[0];

    static {
        BigInteger valueOf = BigInteger.valueOf(1024);
        a = valueOf;
        b = valueOf.multiply(valueOf);
    }

    private static FileOutputStream f(File file) throws IOException {
        StringBuilder stringBuilder;
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!(parentFile == null || parentFile.mkdirs() || parentFile.isDirectory())) {
                stringBuilder = new StringBuilder("Directory '");
                stringBuilder.append(parentFile);
                stringBuilder.append("' could not be created");
                throw new IOException(stringBuilder.toString());
            }
        } else if (file.isDirectory()) {
            stringBuilder = new StringBuilder("File '");
            stringBuilder.append(file);
            stringBuilder.append("' exists but is a directory");
            throw new IOException(stringBuilder.toString());
        } else if (!file.canWrite()) {
            stringBuilder = new StringBuilder("File '");
            stringBuilder.append(file);
            stringBuilder.append("' cannot be written to");
            throw new IOException(stringBuilder.toString());
        }
        return new FileOutputStream(file, false);
    }

    private static void a(Collection<File> collection, File file, adeu adeu, boolean z) {
        File[] listFiles = file.listFiles(adeu);
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    if (z) {
                        collection.add(file2);
                    }
                    adek.a(collection, file2, adeu, z);
                } else {
                    collection.add(file2);
                }
            }
        }
    }

    private static void b(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        Throwable th;
        FileChannel fileChannel;
        File file3 = file;
        File file4 = file2;
        if (file2.exists() && file2.isDirectory()) {
            StringBuilder stringBuilder = new StringBuilder("Destination '");
            stringBuilder.append(file4);
            stringBuilder.append("' exists but is a directory");
            throw new IOException(stringBuilder.toString());
        }
        Closeable closeable = null;
        try {
            FileChannel channel;
            fileInputStream = new FileInputStream(file3);
            try {
                fileOutputStream = new FileOutputStream(file4);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                fileChannel = fileOutputStream;
                aden.a(closeable, fileOutputStream, fileChannel, fileInputStream);
                throw th;
            }
            try {
                fileChannel = fileInputStream.getChannel();
                try {
                    channel = fileOutputStream.getChannel();
                } catch (Throwable th3) {
                    th = th3;
                    aden.a(closeable, fileOutputStream, fileChannel, fileInputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileChannel = null;
                aden.a(closeable, fileOutputStream, fileChannel, fileInputStream);
                throw th;
            }
            try {
                long size = fileChannel.size();
                long j = 0;
                while (j < size) {
                    long j2 = size - j;
                    j2 = channel.transferFrom(fileChannel, j, j2 > 31457280 ? 31457280 : j2);
                    if (j2 == 0) {
                        break;
                    }
                    j += j2;
                }
                aden.a(channel, fileOutputStream, fileChannel, fileInputStream);
                long length = file.length();
                long length2 = file2.length();
                if (length == length2) {
                    file4.setLastModified(file.lastModified());
                    return;
                }
                StringBuilder stringBuilder2 = new StringBuilder("Failed to copy full contents from '");
                stringBuilder2.append(file3);
                stringBuilder2.append("' to '");
                stringBuilder2.append(file4);
                stringBuilder2.append("' Expected length: ");
                stringBuilder2.append(length);
                stringBuilder2.append(" Actual: ");
                stringBuilder2.append(length2);
                throw new IOException(stringBuilder2.toString());
            } catch (Throwable th5) {
                th = th5;
                closeable = channel;
                aden.a(closeable, fileOutputStream, fileChannel, fileInputStream);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            fileInputStream = null;
            fileOutputStream = fileInputStream;
            fileChannel = fileOutputStream;
            aden.a(closeable, fileOutputStream, fileChannel, fileInputStream);
            throw th;
        }
    }

    public static void a(File file) throws IOException {
        if (file.exists()) {
            if (!adek.j(file)) {
                adek.c(file);
            }
            if (!file.delete()) {
                StringBuilder stringBuilder = new StringBuilder("Unable to delete directory ");
                stringBuilder.append(file);
                stringBuilder.append(ly.a);
                throw new IOException(stringBuilder.toString());
            }
        }
    }

    public static boolean b(java.io.File r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:adek.b(java.io.File):boolean. bs: []
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
        if (r2 != 0) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r2.isDirectory();	 Catch:{ Exception -> 0x000d }
        if (r1 == 0) goto L_0x000d;	 Catch:{ Exception -> 0x000d }
    L_0x000a:
        defpackage.adek.c(r2);	 Catch:{ Exception -> 0x000d }
    L_0x000d:
        r2 = r2.delete();	 Catch:{ Exception -> 0x0012 }
        return r2;
    L_0x0012:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: adek.b(java.io.File):boolean");
    }

    public static void c(File file) throws IOException {
        IOException e = null;
        for (File h : adek.g(file)) {
            try {
                adek.h(h);
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e != null) {
            throw e;
        }
    }

    private static File[] g(File file) throws IOException {
        StringBuilder stringBuilder;
        if (!file.exists()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(file);
            stringBuilder.append(" does not exist");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                return listFiles;
            }
            throw new IOException("Failed to list contents of ".concat(String.valueOf(file)));
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(file);
            stringBuilder.append(" is not a directory");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private static void h(File file) throws IOException {
        if (file.isDirectory()) {
            adek.a(file);
            return;
        }
        boolean exists = file.exists();
        if (!file.delete()) {
            if (exists) {
                throw new IOException("Unable to delete file: ".concat(String.valueOf(file)));
            } else {
                throw new FileNotFoundException("File does not exist: ".concat(String.valueOf(file)));
            }
        }
    }

    public static void d(File file) throws IOException {
        if (file.exists()) {
            if (!file.isDirectory()) {
                StringBuilder stringBuilder = new StringBuilder("File ");
                stringBuilder.append(file);
                stringBuilder.append(" exists and is not a directory. Unable to create directory.");
                throw new IOException(stringBuilder.toString());
            }
        } else if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("Unable to create directory ".concat(String.valueOf(file)));
        }
    }

    private static long i(java.io.File r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:adek.i(java.io.File):long. bs: []
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
        r9 = r9.listFiles();
        r0 = 0;
        if (r9 != 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r9.length;
        r3 = 0;
        r4 = r0;
    L_0x000c:
        if (r3 >= r2) goto L_0x002f;
    L_0x000e:
        r6 = r9[r3];
        r7 = defpackage.adek.j(r6);	 Catch:{ IOException -> 0x002c }
        if (r7 != 0) goto L_0x002c;	 Catch:{ IOException -> 0x002c }
    L_0x0016:
        r7 = r6.isDirectory();	 Catch:{ IOException -> 0x002c }
        if (r7 == 0) goto L_0x0021;	 Catch:{ IOException -> 0x002c }
    L_0x001c:
        r6 = defpackage.adek.i(r6);	 Catch:{ IOException -> 0x002c }
        goto L_0x0025;	 Catch:{ IOException -> 0x002c }
    L_0x0021:
        r6 = r6.length();	 Catch:{ IOException -> 0x002c }
    L_0x0025:
        r8 = 0;
        r4 = r4 + r6;
        r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r6 >= 0) goto L_0x002c;
    L_0x002b:
        goto L_0x002f;
    L_0x002c:
        r3 = r3 + 1;
        goto L_0x000c;
    L_0x002f:
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: adek.i(java.io.File):long");
    }

    private static boolean j(File file) throws IOException {
        if (adeo.a()) {
            return adeo.a(file);
        }
        if (file == null) {
            throw new NullPointerException("File must not be null");
        } else if (adel.a()) {
            return false;
        } else {
            File file2;
            if (file.getParent() == null) {
                file2 = file;
            } else {
                file2 = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file2.getCanonicalFile().equals(file2.getAbsoluteFile())) {
                return adek.k(file);
            }
            return true;
        }
    }

    private static boolean k(File file) throws IOException {
        if (file.exists()) {
            return false;
        }
        file = file.getCanonicalFile();
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            return false;
        }
        File[] listFiles = parentFile.listFiles(new FileFilter() {
            public final boolean accept(File file) {
                return file.equals(file);
            }
        });
        if (listFiles == null || listFiles.length <= 0) {
            return false;
        }
        return true;
    }

    public static Collection<File> a(File file, String[] strArr) {
        String[] strArr2 = new String[1];
        for (int i = 0; i <= 0; i++) {
            StringBuilder stringBuilder = new StringBuilder(ly.a);
            stringBuilder.append(strArr[0]);
            strArr2[0] = stringBuilder.toString();
        }
        adey adey = new adey(strArr2);
        adeu adeu = adez.b;
        if (file.isDirectory()) {
            adeu a = adet.a(adey, adet.a(ader.b));
            if (adeu == null) {
                adeu = ades.b;
            } else {
                adeu = adet.a(adeu, ader.b);
            }
            Collection<File> linkedList = new LinkedList();
            adek.a(linkedList, file, adet.b(a, adeu), false);
            return linkedList;
        }
        throw new IllegalArgumentException("Parameter 'directory' is not a directory: ".concat(String.valueOf(file)));
    }

    public static void a(File file, File file2) throws IOException {
        StringBuilder stringBuilder;
        if (file == null) {
            throw new NullPointerException("Source must not be null");
        } else if (file2 == null) {
            throw new NullPointerException("Destination must not be null");
        } else if (!file.exists()) {
            stringBuilder = new StringBuilder("Source '");
            stringBuilder.append(file);
            stringBuilder.append("' does not exist");
            throw new FileNotFoundException(stringBuilder.toString());
        } else if (file.isDirectory()) {
            stringBuilder = new StringBuilder("Source '");
            stringBuilder.append(file);
            stringBuilder.append("' exists but is a directory");
            throw new IOException(stringBuilder.toString());
        } else if (file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            StringBuilder stringBuilder2 = new StringBuilder("Source '");
            stringBuilder2.append(file);
            stringBuilder2.append("' and destination '");
            stringBuilder2.append(file2);
            stringBuilder2.append("' are the same");
            throw new IOException(stringBuilder2.toString());
        } else {
            File parentFile = file2.getParentFile();
            if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
                StringBuilder stringBuilder3 = new StringBuilder("Destination '");
                stringBuilder3.append(parentFile);
                stringBuilder3.append("' directory cannot be created");
                throw new IOException(stringBuilder3.toString());
            } else if (!file2.exists() || file2.canWrite()) {
                adek.b(file, file2);
            } else {
                stringBuilder = new StringBuilder("Destination '");
                stringBuilder.append(file2);
                stringBuilder.append("' exists but is read-only");
                throw new IOException(stringBuilder.toString());
            }
        }
    }

    public static void a(InputStream inputStream, File file) throws IOException {
        OutputStream f;
        try {
            f = adek.f(file);
            aden.a(inputStream, f);
            f.close();
            aden.a(f);
            aden.a(inputStream);
        } catch (Throwable th) {
            aden.a(inputStream);
        }
    }

    public static String a(File file, Charset charset) throws IOException {
        Throwable th;
        InputStream inputStream = null;
        try {
            StringBuilder stringBuilder;
            if (!file.exists()) {
                stringBuilder = new StringBuilder("File '");
                stringBuilder.append(file);
                stringBuilder.append("' does not exist");
                throw new FileNotFoundException(stringBuilder.toString());
            } else if (file.isDirectory()) {
                stringBuilder = new StringBuilder("File '");
                stringBuilder.append(file);
                stringBuilder.append("' exists but is a directory");
                throw new IOException(stringBuilder.toString());
            } else if (file.canRead()) {
                InputStream fileInputStream = new FileInputStream(file);
                try {
                    String a = aden.a(fileInputStream, adej.a(charset));
                    aden.a(fileInputStream);
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                    aden.a(inputStream);
                    throw th;
                }
            } else {
                stringBuilder = new StringBuilder("File '");
                stringBuilder.append(file);
                stringBuilder.append("' cannot be read");
                throw new IOException(stringBuilder.toString());
            }
        } catch (Throwable th3) {
            th = th3;
            aden.a(inputStream);
            throw th;
        }
    }

    public static void a(File file, byte[] bArr) throws IOException {
        Throwable th;
        int length = bArr.length;
        OutputStream f;
        try {
            f = adek.f(file);
            try {
                f.write(bArr, 0, length);
                f.close();
                aden.a(f);
            } catch (Throwable th2) {
                th = th2;
                aden.a(f);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            f = null;
            aden.a(f);
            throw th;
        }
    }

    public static long e(File file) {
        StringBuilder stringBuilder;
        if (!file.exists()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(file);
            stringBuilder.append(" does not exist");
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (file.isDirectory()) {
            return adek.i(file);
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(file);
            stringBuilder.append(" is not a directory");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
