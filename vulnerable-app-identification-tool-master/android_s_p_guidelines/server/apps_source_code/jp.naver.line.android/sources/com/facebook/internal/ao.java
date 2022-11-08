package com.facebook.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

final class ao extends BufferedInputStream {
    HttpURLConnection a;

    ao(InputStream inputStream, HttpURLConnection httpURLConnection) {
        super(inputStream, 8192);
        this.a = httpURLConnection;
    }

    public final void close() throws IOException {
        super.close();
        bj.a(this.a);
    }
}
