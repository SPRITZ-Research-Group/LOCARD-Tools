package com.skype.utils;

import com.facebook.common.logging.FLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static int a = 16384;

    public static void a(List<File> sourceFiles, File destination) throws IOException {
        BufferedInputStream origin;
        Throwable e;
        BufferedInputStream origin2 = null;
        Closeable dest = null;
        Closeable out = null;
        byte[] data = new byte[a];
        Throwable th;
        try {
            Closeable dest2 = new FileOutputStream(destination);
            try {
                Closeable origin3;
                Closeable out2 = new ZipOutputStream(new BufferedOutputStream(dest2));
                int i = 0;
                while (true) {
                    try {
                        origin = origin2;
                        if (i < sourceFiles.size()) {
                            File file = (File) sourceFiles.get(i);
                            FLog.i("ZipUtil", "Adding: " + file);
                            try {
                                origin3 = new BufferedInputStream(new FileInputStream(file), a);
                                try {
                                    String absolutePath = file.getAbsolutePath();
                                    out2.putNextEntry(new ZipEntry(absolutePath.substring(absolutePath.lastIndexOf("/") + 1)));
                                    while (true) {
                                        int count = origin3.read(data, 0, a);
                                        if (count == -1) {
                                            break;
                                        }
                                        out2.write(data, 0, count);
                                    }
                                    FileUtil.a(origin3);
                                } catch (IOException e2) {
                                    e = e2;
                                    try {
                                        FLog.w("ZipUtil", "can't read data from file " + file, e);
                                        FileUtil.a(origin3);
                                        origin2 = null;
                                        i++;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                            } catch (IOException e3) {
                                e = e3;
                                Object origin32 = origin;
                                FLog.w("ZipUtil", "can't read data from file " + file, e);
                                FileUtil.a(origin32);
                                origin2 = null;
                                i++;
                            } catch (Throwable th3) {
                                th = th3;
                                out = out2;
                                dest = dest2;
                            }
                            origin2 = null;
                            i++;
                        } else {
                            FileUtil.a(out2);
                            FileUtil.a(dest2);
                            return;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        out = out2;
                        dest = dest2;
                        origin2 = origin;
                    }
                }
                FileUtil.a(origin32);
                throw th;
            } catch (Throwable th5) {
                th = th5;
                dest = dest2;
                FileUtil.a(out);
                FileUtil.a(dest);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            FileUtil.a(out);
            FileUtil.a(dest);
            throw th;
        }
    }
}
