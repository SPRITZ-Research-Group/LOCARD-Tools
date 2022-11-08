package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

public final class n {
    private static String a = null;
    private static int b = 0;

    private static BufferedReader a(String str) throws IOException {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            return bufferedReader;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    @Nullable
    public static String a() {
        if (a == null) {
            if (b == 0) {
                b = Process.myPid();
            }
            a = a(b);
        }
        return a;
    }

    @Nullable
    private static String a(int i) {
        Throwable th;
        String str = null;
        if (i > 0) {
            Closeable a;
            try {
                a = a("/proc/" + i + "/cmdline");
                try {
                    str = a.readLine().trim();
                    j.a(a);
                } catch (IOException e) {
                    j.a(a);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    j.a(a);
                    throw th;
                }
            } catch (IOException e2) {
                a = str;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                a = str;
                th = th4;
                j.a(a);
                throw th;
            }
        }
        return str;
    }
}
