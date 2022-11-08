package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.ab;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

@WorkerThread
final class bc implements Runnable {
    private final URL a;
    private final byte[] b;
    private final ba c;
    private final String d;
    private final Map<String, String> e;
    private final /* synthetic */ az f;

    public bc(az azVar, String str, URL url, byte[] bArr, Map<String, String> map, ba baVar) {
        this.f = azVar;
        ab.a(str);
        ab.a((Object) url);
        ab.a((Object) baVar);
        this.a = url;
        this.b = bArr;
        this.c = baVar;
        this.d = str;
        this.e = map;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        Throwable e;
        HttpURLConnection httpURLConnection;
        Throwable th;
        this.f.b();
        OutputStream outputStream = null;
        int i = 0;
        Map map = null;
        HttpURLConnection a;
        OutputStream outputStream2;
        try {
            a = this.f.a(this.a);
            try {
                if (this.e != null) {
                    for (Entry entry : this.e.entrySet()) {
                        a.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                if (this.b != null) {
                    byte[] a2 = this.f.n().a(this.b);
                    this.f.q().C().a("Uploading data. size", Integer.valueOf(a2.length));
                    a.setDoOutput(true);
                    a.addRequestProperty("Content-Encoding", "gzip");
                    a.setFixedLengthStreamingMode(a2.length);
                    a.connect();
                    outputStream2 = a.getOutputStream();
                    try {
                        outputStream2.write(a2);
                        outputStream2.close();
                        outputStream2 = null;
                    } catch (IOException e2) {
                        e = e2;
                        httpURLConnection = a;
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = outputStream2;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (IOException e3) {
                                this.f.q().v().a("Error closing HTTP compressed POST connection output stream. appId", av.a(this.d), e3);
                            }
                        }
                        if (a != null) {
                            a.disconnect();
                        }
                        this.f.p().a(new bb(this.d, this.c, i, null, null, map, (byte) 0));
                        throw th;
                    }
                }
                outputStream2 = null;
                i = a.getResponseCode();
                map = a.getHeaderFields();
                byte[] a3 = az.b(a);
                if (a != null) {
                    a.disconnect();
                }
                this.f.p().a(new bb(this.d, this.c, i, null, a3, map, (byte) 0));
            } catch (IOException e4) {
                e = e4;
                outputStream2 = null;
                httpURLConnection = a;
                if (outputStream2 != null) {
                    try {
                        outputStream2.close();
                    } catch (IOException e32) {
                        this.f.q().v().a("Error closing HTTP compressed POST connection output stream. appId", av.a(this.d), e32);
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                this.f.p().a(new bb(this.d, this.c, i, e, null, map, (byte) 0));
            } catch (Throwable th3) {
                th = th3;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (a != null) {
                    a.disconnect();
                }
                this.f.p().a(new bb(this.d, this.c, i, null, null, map, (byte) 0));
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            httpURLConnection = null;
            outputStream2 = null;
        } catch (Throwable th22) {
            th = th22;
            a = null;
            if (outputStream != null) {
                outputStream.close();
            }
            if (a != null) {
                a.disconnect();
            }
            this.f.p().a(new bb(this.d, this.c, i, null, null, map, (byte) 0));
            throw th;
        }
    }
}
