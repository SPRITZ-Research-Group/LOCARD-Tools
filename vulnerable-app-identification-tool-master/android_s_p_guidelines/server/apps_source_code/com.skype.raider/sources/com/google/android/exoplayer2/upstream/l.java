package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer2.d.m;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException;
import com.google.android.exoplayer2.upstream.HttpDataSource.c;
import com.google.android.exoplayer2.upstream.HttpDataSource.d;
import com.google.android.exoplayer2.upstream.HttpDataSource.e;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.http.StatusLine;

public final class l implements HttpDataSource {
    private static final Pattern b = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    private static final AtomicReference<byte[]> c = new AtomicReference();
    private final boolean d;
    private final int e;
    private final int f;
    private final String g;
    private final m<String> h;
    private final e i;
    private final e j;
    private final p<? super l> k;
    private DataSpec l;
    private HttpURLConnection m;
    private InputStream n;
    private boolean o;
    private long p;
    private long q;
    private long r;
    private long s;

    public l(String userAgent, p<? super l> listener, int connectTimeoutMillis, int readTimeoutMillis, boolean allowCrossProtocolRedirects, e defaultRequestProperties) {
        if (TextUtils.isEmpty(userAgent)) {
            throw new IllegalArgumentException();
        }
        this.g = userAgent;
        this.h = null;
        this.k = listener;
        this.j = new e();
        this.e = connectTimeoutMillis;
        this.f = readTimeoutMillis;
        this.d = allowCrossProtocolRedirects;
        this.i = defaultRequestProperties;
    }

    public final Uri a() {
        return this.m == null ? null : Uri.parse(this.m.getURL().toString());
    }

    public final long a(DataSpec dataSpec) throws HttpDataSourceException {
        HttpURLConnection a;
        this.l = dataSpec;
        this.s = 0;
        this.r = 0;
        URL url = new URL(dataSpec.a.toString());
        byte[] bArr = dataSpec.b;
        long j = dataSpec.d;
        long j2 = dataSpec.e;
        boolean a2 = dataSpec.a();
        if (this.d) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i <= 20) {
                    try {
                        a = a(url, bArr, j, j2, a2, false);
                        int responseCode = a.getResponseCode();
                        if (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303 && (bArr != null || (responseCode != StatusLine.HTTP_TEMP_REDIRECT && responseCode != StatusLine.HTTP_PERM_REDIRECT))) {
                            break;
                        }
                        bArr = null;
                        String headerField = a.getHeaderField("Location");
                        a.disconnect();
                        if (headerField == null) {
                            throw new ProtocolException("Null location redirect");
                        }
                        URL url2 = new URL(url, headerField);
                        String protocol = url2.getProtocol();
                        if (Constants.SCHEME.equals(protocol) || "http".equals(protocol)) {
                            url = url2;
                            i = i2;
                        } else {
                            throw new ProtocolException("Unsupported protocol redirect: " + protocol);
                        }
                    } catch (IOException e) {
                        throw new HttpDataSourceException("Unable to connect to " + dataSpec.a.toString(), e, dataSpec);
                    }
                }
                throw new NoRouteToHostException("Too many redirects: " + i2);
            }
        }
        a = a(url, bArr, j, j2, a2, true);
        this.m = a;
        try {
            int responseCode2 = this.m.getResponseCode();
            if (responseCode2 < 200 || responseCode2 > 299) {
                Map<String, List<String>> headers = this.m.getHeaderFields();
                c();
                d dVar = new d(responseCode2, headers, dataSpec);
                if (responseCode2 == 416) {
                    dVar.initCause(new g());
                }
                throw dVar;
            }
            String contentType = this.m.getContentType();
            if (this.h == null || this.h.a(contentType)) {
                long j3 = (responseCode2 != 200 || dataSpec.d == 0) ? 0 : dataSpec.d;
                this.p = j3;
                if (dataSpec.a()) {
                    this.q = dataSpec.e;
                } else if (dataSpec.e != -1) {
                    this.q = dataSpec.e;
                } else {
                    long contentLength = a(this.m);
                    this.q = contentLength != -1 ? contentLength - this.p : -1;
                }
                try {
                    this.n = this.m.getInputStream();
                    this.o = true;
                    if (this.k != null) {
                        this.k.b();
                    }
                    return this.q;
                } catch (IOException e2) {
                    c();
                    throw new HttpDataSourceException(e2, dataSpec, 1);
                }
            }
            c();
            throw new c(contentType, dataSpec);
        } catch (IOException e22) {
            c();
            throw new HttpDataSourceException("Unable to connect to " + dataSpec.a.toString(), e22, dataSpec);
        }
    }

    public final int a(byte[] buffer, int offset, int readLength) throws HttpDataSourceException {
        try {
            if (this.r != this.p) {
                Object obj = (byte[]) c.getAndSet(null);
                if (obj == null) {
                    obj = new byte[4096];
                }
                while (this.r != this.p) {
                    int read = this.n.read(obj, 0, (int) Math.min(this.p - this.r, (long) obj.length));
                    if (Thread.interrupted()) {
                        throw new InterruptedIOException();
                    } else if (read == -1) {
                        throw new EOFException();
                    } else {
                        this.r += (long) read;
                        if (this.k != null) {
                            this.k.a(read);
                        }
                    }
                }
                c.set(obj);
            }
            if (readLength == 0) {
                return 0;
            }
            if (this.q != -1) {
                long j = this.q - this.s;
                if (j == 0) {
                    return -1;
                }
                readLength = (int) Math.min((long) readLength, j);
            }
            int read2 = this.n.read(buffer, offset, readLength);
            if (read2 != -1) {
                this.s += (long) read2;
                if (this.k == null) {
                    return read2;
                }
                this.k.a(read2);
                return read2;
            } else if (this.q == -1) {
                return -1;
            } else {
                throw new EOFException();
            }
        } catch (IOException e) {
            throw new HttpDataSourceException(e, this.l, 2);
        }
    }

    public final void b() throws HttpDataSourceException {
        try {
            if (this.n != null) {
                long j;
                HttpURLConnection httpURLConnection = this.m;
                if (this.q == -1) {
                    j = this.q;
                } else {
                    j = this.q - this.s;
                }
                if (s.a == 19 || s.a == 20) {
                    try {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        if (j != -1 ? j <= 2048 : inputStream.read() == -1) {
                            String name = inputStream.getClass().getName();
                            if (name.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream") || name.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream")) {
                                Method declaredMethod = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(inputStream, new Object[0]);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                this.n.close();
            }
            this.n = null;
            c();
            if (this.o) {
                this.o = false;
                if (this.k != null) {
                    this.k.c();
                }
            }
        } catch (IOException e2) {
            throw new HttpDataSourceException(e2, this.l, 3);
        } catch (Throwable th) {
            this.n = null;
            c();
            if (this.o) {
                this.o = false;
                if (this.k != null) {
                    this.k.c();
                }
            }
        }
    }

    private HttpURLConnection a(URL url, byte[] postBody, long position, long length, boolean allowGzip, boolean followRedirects) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(this.e);
        connection.setReadTimeout(this.f);
        if (this.i != null) {
            for (Entry<String, String> property : this.i.a().entrySet()) {
                connection.setRequestProperty((String) property.getKey(), (String) property.getValue());
            }
        }
        for (Entry<String, String> property2 : this.j.a().entrySet()) {
            connection.setRequestProperty((String) property2.getKey(), (String) property2.getValue());
        }
        if (!(position == 0 && length == -1)) {
            String rangeRequest = "bytes=" + position + "-";
            if (length != -1) {
                rangeRequest = rangeRequest + ((position + length) - 1);
            }
            connection.setRequestProperty("Range", rangeRequest);
        }
        connection.setRequestProperty("User-Agent", this.g);
        if (!allowGzip) {
            connection.setRequestProperty("Accept-Encoding", "identity");
        }
        connection.setInstanceFollowRedirects(followRedirects);
        connection.setDoOutput(postBody != null);
        if (postBody != null) {
            connection.setRequestMethod("POST");
            if (postBody.length != 0) {
                connection.setFixedLengthStreamingMode(postBody.length);
                connection.connect();
                OutputStream os = connection.getOutputStream();
                os.write(postBody);
                os.close();
                return connection;
            }
        }
        connection.connect();
        return connection;
    }

    private static long a(HttpURLConnection connection) {
        long contentLength = -1;
        String contentLengthHeader = connection.getHeaderField("Content-Length");
        if (!TextUtils.isEmpty(contentLengthHeader)) {
            try {
                contentLength = Long.parseLong(contentLengthHeader);
            } catch (NumberFormatException e) {
                new StringBuilder("Unexpected Content-Length [").append(contentLengthHeader).append("]");
            }
        }
        String contentRangeHeader = connection.getHeaderField("Content-Range");
        if (TextUtils.isEmpty(contentRangeHeader)) {
            return contentLength;
        }
        Matcher matcher = b.matcher(contentRangeHeader);
        if (!matcher.find()) {
            return contentLength;
        }
        try {
            long contentLengthFromRange = (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
            if (contentLength < 0) {
                return contentLengthFromRange;
            }
            if (contentLength == contentLengthFromRange) {
                return contentLength;
            }
            new StringBuilder("Inconsistent headers [").append(contentLengthHeader).append("] [").append(contentRangeHeader).append("]");
            return Math.max(contentLength, contentLengthFromRange);
        } catch (NumberFormatException e2) {
            new StringBuilder("Unexpected Content-Range [").append(contentRangeHeader).append("]");
            return contentLength;
        }
    }

    private void c() {
        if (this.m != null) {
            try {
                this.m.disconnect();
            } catch (Exception e) {
            }
            this.m = null;
        }
    }
}
