package com.skype.utils;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;

public class FileUtil {

    public static class ShadowLineReader extends LineNumberReader {
        final PrintWriter a;

        public String readLine() throws IOException {
            String line = super.readLine();
            if (line != null) {
                this.a.println(line);
            }
            return line;
        }

        public void close() throws IOException {
            super.close();
            this.a.close();
        }
    }

    public static void a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                FLog.w("File", "can't close the file", e);
            }
        }
    }

    public static boolean a(File dir) {
        boolean result = true;
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    result &= a(file);
                }
            }
        }
        boolean dirDelete = false;
        if (result) {
            dirDelete = dir.delete();
            if (!dirDelete) {
                FLog.w("React", "Can't delete file: " + dir.getPath());
            }
        }
        if (result && dirDelete) {
            return true;
        }
        return false;
    }

    public static void a(File dir, String fileName, String contents) {
        Throwable e;
        File file = new File(dir, fileName);
        if (contents != null) {
            Closeable bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                try {
                    bufferedWriter.write(contents);
                    a(bufferedWriter);
                } catch (IOException e2) {
                    e = e2;
                    try {
                        FLog.d("File", "Can't save file :" + file.getName(), e);
                        a(bufferedWriter);
                    } catch (Throwable th) {
                        e = th;
                        a(bufferedWriter);
                        throw e;
                    }
                }
            } catch (IOException e3) {
                e = e3;
                bufferedWriter = null;
                FLog.d("File", "Can't save file :" + file.getName(), e);
                a(bufferedWriter);
            } catch (Throwable th2) {
                e = th2;
                bufferedWriter = null;
                a(bufferedWriter);
                throw e;
            }
        }
    }
}
