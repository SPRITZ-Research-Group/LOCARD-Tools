package com.facebook.internal;

import android.net.Uri;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class bi {
    static final String a = "bi";
    private static final String b;
    private static v c;

    bi() {
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a);
        stringBuilder.append("_Redirect");
        b = stringBuilder.toString();
    }

    private static synchronized v a() throws IOException {
        v vVar;
        synchronized (bi.class) {
            if (c == null) {
                c = new v(a, new z());
            }
            vVar = c;
        }
        return vVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static Uri a(Uri uri) {
        Throwable th;
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        Closeable inputStreamReader;
        try {
            v a = a();
            Closeable closeable = null;
            Object obj = null;
            while (true) {
                try {
                    InputStream a2 = a.a(uri2, b);
                    if (a2 == null) {
                        break;
                    }
                    obj = 1;
                    inputStreamReader = new InputStreamReader(a2);
                    try {
                        char[] cArr = new char[128];
                        StringBuilder stringBuilder = new StringBuilder();
                        while (true) {
                            int read = inputStreamReader.read(cArr, 0, 128);
                            if (read <= 0) {
                                break;
                            }
                            stringBuilder.append(cArr, 0, read);
                        }
                        bj.a(inputStreamReader);
                        closeable = inputStreamReader;
                        uri2 = stringBuilder.toString();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = closeable;
                }
            }
            bj.a(inputStreamReader);
            return null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            bj.a(inputStreamReader);
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(Uri uri, Uri uri2) {
        Throwable th;
        if (uri != null && uri2 != null) {
            Closeable closeable = null;
            try {
                Closeable b = a().b(uri.toString(), b);
                try {
                    b.write(uri2.toString().getBytes());
                    bj.a(b);
                } catch (Throwable th2) {
                    th = th2;
                    closeable = b;
                    bj.a(closeable);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bj.a(closeable);
                throw th;
            }
        }
    }
}
