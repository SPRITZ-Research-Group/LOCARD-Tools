package com.facebook.ads.internal.p.b;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class h implements n {
    public final String a;
    private HttpURLConnection b;
    private InputStream c;
    private volatile int d;
    private volatile String e;

    public h(h hVar) {
        this.d = Integer.MIN_VALUE;
        this.a = hVar.a;
        this.e = hVar.e;
        this.d = hVar.d;
    }

    private h(String str, String str2) {
        this.d = Integer.MIN_VALUE;
        this.a = (String) j.a(str);
        this.e = str2;
    }

    private HttpURLConnection a(int i, int i2) {
        HttpURLConnection httpURLConnection;
        String str = this.a;
        int i3 = 0;
        Object obj;
        do {
            new StringBuilder("Open connection ").append(i > 0 ? " with offset " + i : "").append(" to ").append(str);
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (i > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + i + "-");
            }
            if (i2 > 0) {
                httpURLConnection.setConnectTimeout(i2);
                httpURLConnection.setReadTimeout(i2);
            }
            int responseCode = httpURLConnection.getResponseCode();
            obj = (responseCode == 301 || responseCode == 302 || responseCode == 303) ? 1 : null;
            if (obj != null) {
                str = httpURLConnection.getHeaderField("Location");
                i3++;
                httpURLConnection.disconnect();
            }
            if (i3 > 5) {
                throw new l("Too many redirects: " + i3);
            }
        } while (obj != null);
        return httpURLConnection;
    }

    private void d() {
        Throwable th;
        HttpURLConnection httpURLConnection;
        Closeable closeable;
        Throwable th2;
        Closeable closeable2 = null;
        new StringBuilder("Read content info from ").append(this.a);
        HttpURLConnection a;
        try {
            a = a(0, 10000);
            try {
                this.d = a.getContentLength();
                this.e = a.getContentType();
                closeable2 = a.getInputStream();
                try {
                    new StringBuilder("Content info for `").append(this.a).append("`: mime: ").append(this.e).append(", content-length: ").append(this.d);
                    m.a(closeable2);
                    if (a != null) {
                        a.disconnect();
                    }
                } catch (IOException e) {
                    try {
                        new StringBuilder("Error fetching info from ").append(this.a);
                        m.a(closeable2);
                        if (a != null) {
                            a.disconnect();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection = a;
                        closeable = closeable2;
                        th2 = th;
                        m.a(closeable);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th32) {
                th = th32;
                httpURLConnection = a;
                closeable = closeable2;
                th2 = th;
            }
        } catch (IOException e2) {
            a = closeable2;
        } catch (Throwable th4) {
            httpURLConnection = closeable2;
            Closeable closeable3 = closeable2;
            th2 = th4;
            closeable = closeable3;
            m.a(closeable);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }

    public final synchronized int a() {
        if (this.d == Integer.MIN_VALUE) {
            d();
        }
        return this.d;
    }

    public final int a(byte[] bArr) {
        if (this.c == null) {
            throw new l("Error reading data from " + this.a + ": connection is absent!");
        }
        try {
            return this.c.read(bArr, 0, bArr.length);
        } catch (Throwable e) {
            throw new i("Reading source " + this.a + " is interrupted", e);
        } catch (Throwable e2) {
            throw new l("Error reading data from " + this.a, e2);
        }
    }

    public final void a(int i) {
        try {
            this.b = a(i, -1);
            this.e = this.b.getContentType();
            this.c = new BufferedInputStream(this.b.getInputStream(), 8192);
            HttpURLConnection httpURLConnection = this.b;
            int responseCode = this.b.getResponseCode();
            int contentLength = httpURLConnection.getContentLength();
            if (responseCode != 200) {
                contentLength = responseCode == 206 ? contentLength + i : this.d;
            }
            this.d = contentLength;
        } catch (Throwable e) {
            throw new l("Error opening connection for " + this.a + " with offset " + i, e);
        }
    }

    public final void b() {
        if (this.b != null) {
            try {
                this.b.disconnect();
            } catch (Throwable e) {
                throw new l("Error disconnecting HttpUrlConnection", e);
            }
        }
    }

    public final synchronized String c() {
        if (TextUtils.isEmpty(this.e)) {
            d();
        }
        return this.e;
    }

    public final String toString() {
        return "HttpUrlSource{url='" + this.a + "}";
    }

    public h(String str) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        Object fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        this(str, TextUtils.isEmpty(fileExtensionFromUrl) ? null : singleton.getMimeTypeFromExtension(fileExtensionFromUrl));
    }
}
