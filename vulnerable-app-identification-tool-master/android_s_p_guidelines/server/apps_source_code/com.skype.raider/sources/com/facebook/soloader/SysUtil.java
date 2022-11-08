package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class SysUtil {

    private static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            return Build.SUPPORTED_ABIS;
        }

        @DoNotOptimize
        public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
            try {
                Os.posix_fallocate(fd, 0, length);
            } catch (ErrnoException ex) {
                if (ex.errno != OsConstants.EOPNOTSUPP && ex.errno != OsConstants.ENOSYS && ex.errno != OsConstants.EINVAL) {
                    throw new IOException(ex.toString(), ex);
                }
            }
        }
    }

    public static int a(String[] supportedAbis, String abi) {
        int i = 0;
        while (i < supportedAbis.length) {
            if (supportedAbis[i] != null && abi.equals(supportedAbis[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void a(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList != null) {
                for (File a : fileList) {
                    a(a);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }

    static int a(RandomAccessFile os, InputStream is, byte[] buffer) throws IOException {
        int bytesCopied = 0;
        while (bytesCopied < Integer.MAX_VALUE) {
            int nrRead = is.read(buffer, 0, Math.min(32768, Integer.MAX_VALUE - bytesCopied));
            if (nrRead == -1) {
                break;
            }
            os.write(buffer, 0, nrRead);
            bytesCopied += nrRead;
        }
        return bytesCopied;
    }

    static void b(File fileName) throws IOException {
        RandomAccessFile file;
        Throwable th;
        Throwable th2;
        if (fileName.isDirectory()) {
            File[] files = fileName.listFiles();
            if (files == null) {
                throw new IOException("cannot list directory " + fileName);
            }
            for (File b : files) {
                b(b);
            }
            return;
        } else if (!fileName.getPath().endsWith("_lock")) {
            file = new RandomAccessFile(fileName, "r");
            th = null;
            try {
                file.getFD().sync();
                file.close();
                return;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                th3 = th2;
                th2 = th4;
            }
        } else {
            return;
        }
        if (th3 != null) {
            try {
                file.close();
            } catch (Throwable th5) {
                th3.addSuppressed(th5);
            }
        } else {
            file.close();
        }
        throw th2;
        throw th2;
    }

    public static int a(Context context) {
        int i = 0;
        PackageManager pm = context.getPackageManager();
        if (pm == null) {
            return i;
        }
        try {
            return pm.getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        } catch (RuntimeException e2) {
            return i;
        }
    }
}
