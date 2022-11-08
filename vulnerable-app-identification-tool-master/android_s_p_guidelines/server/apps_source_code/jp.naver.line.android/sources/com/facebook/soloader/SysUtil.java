package com.facebook.soloader;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class SysUtil {
    private static final byte APK_SIGNATURE_VERSION = (byte) 1;

    final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            return Build.SUPPORTED_ABIS;
        }

        @DoNotOptimize
        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
            try {
                Os.posix_fallocate(fileDescriptor, 0, j);
            } catch (Throwable e) {
                if (e.errno != OsConstants.EOPNOTSUPP && e.errno != OsConstants.ENOSYS && e.errno != OsConstants.EINVAL) {
                    throw new IOException(e.toString(), e);
                }
            }
        }
    }

    public static int findAbiScore(String[] strArr, String str) {
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i] != null && str.equals(strArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        if (!file.delete()) {
            throw new IOException("could not delete file ".concat(String.valueOf(file)));
        }
    }

    public static String[] getSupportedAbis() {
        if (VERSION.SDK_INT >= 21) {
            return LollipopSysdeps.getSupportedAbis();
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
        if (VERSION.SDK_INT >= 21) {
            LollipopSysdeps.fallocateIfSupported(fileDescriptor, j);
        }
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File dumbDeleteRecursive : listFiles) {
                    dumbDeleteRecursive(dumbDeleteRecursive);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: ".concat(String.valueOf(file)));
        }
    }

    static void mkdirOrThrow(File file) throws IOException {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: ".concat(String.valueOf(file)));
        }
    }

    static int copyBytes(RandomAccessFile randomAccessFile, InputStream inputStream, int i, byte[] bArr) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, i - i2));
            if (read == -1) {
                break;
            }
            randomAccessFile.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void fsyncRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File fsyncRecursive : listFiles) {
                    fsyncRecursive(fsyncRecursive);
                }
                return;
            }
            throw new IOException("cannot list directory ".concat(String.valueOf(file)));
        } else if (!file.getPath().endsWith("_lock")) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
        }
    }

    public static byte[] makeApkDepBlock(File file, Context context) throws IOException {
        file = file.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte(APK_SIGNATURE_VERSION);
            obtain.writeString(file.getPath());
            obtain.writeLong(file.lastModified());
            obtain.writeInt(getAppVersionCode(context));
            byte[] marshall = obtain.marshall();
            return marshall;
        } finally {
            obtain.recycle();
        }
    }

    public static int getAppVersionCode(android.content.Context r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.soloader.SysUtil.getAppVersionCode(android.content.Context):int. bs: []
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
        r0 = r2.getPackageManager();
        r1 = 0;
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r2 = r2.getPackageName();	 Catch:{ NameNotFoundException -> 0x0012, NameNotFoundException -> 0x0012 }
        r2 = r0.getPackageInfo(r2, r1);	 Catch:{ NameNotFoundException -> 0x0012, NameNotFoundException -> 0x0012 }
        r2 = r2.versionCode;	 Catch:{ NameNotFoundException -> 0x0012, NameNotFoundException -> 0x0012 }
        return r2;
    L_0x0012:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SysUtil.getAppVersionCode(android.content.Context):int");
    }
}
