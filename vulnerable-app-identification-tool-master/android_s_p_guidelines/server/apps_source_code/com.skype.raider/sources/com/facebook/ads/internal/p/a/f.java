package com.facebook.ads.internal.p.a;

import com.adjust.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class f implements q {
    private final r a;

    public f() {
        this(new g());
    }

    private f(r rVar) {
        this.a = rVar;
    }

    public final OutputStream a(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getOutputStream();
    }

    public final HttpURLConnection a(String str) {
        return (HttpURLConnection) new URL(str).openConnection();
    }

    public final void a(OutputStream outputStream, byte[] bArr) {
        outputStream.write(bArr);
    }

    public final boolean a(m mVar) {
        n a = mVar.a();
        if (this.a.a()) {
            this.a.a("BasicRequestHandler.onError got");
            mVar.printStackTrace();
        }
        return a != null && a.a() > 0;
    }

    public final byte[] a(InputStream inputStream) {
        byte[] bArr = new byte[16384];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public final InputStream b(HttpURLConnection httpURLConnection) {
        return httpURLConnection.getInputStream();
    }

    public final void a(HttpURLConnection httpURLConnection, j jVar, String str) {
        httpURLConnection.setRequestMethod(jVar.toString());
        httpURLConnection.setDoOutput(jVar.b());
        httpURLConnection.setDoInput(jVar.a());
        if (str != null) {
            httpURLConnection.setRequestProperty("Content-Type", str);
        }
        httpURLConnection.setRequestProperty("Accept-Charset", Constants.ENCODING);
    }
}
