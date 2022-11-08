package okhttp3;

import c.c;
import c.d;
import c.e;
import c.f;
import c.g;
import c.h;
import c.l;
import c.s;
import c.t;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;
import okhttp3.Headers.Builder;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.DiskLruCache;
import okhttp3.internal.cache.DiskLruCache.Editor;
import okhttp3.internal.cache.DiskLruCache.Snapshot;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;

public final class Cache implements Closeable, Flushable {
    private static final int ENTRY_BODY = 1;
    private static final int ENTRY_COUNT = 2;
    private static final int ENTRY_METADATA = 0;
    private static final int VERSION = 201105;
    final DiskLruCache cache;
    private int hitCount;
    final InternalCache internalCache;
    private int networkCount;
    private int requestCount;
    int writeAbortCount;
    int writeSuccessCount;

    private final class CacheRequestImpl implements CacheRequest {
        private s body;
        private s cacheOut;
        boolean done;
        private final Editor editor;

        CacheRequestImpl(final Editor editor) {
            this.editor = editor;
            this.cacheOut = editor.newSink(1);
            this.body = new g(this.cacheOut, Cache.this) {
                public void close() throws IOException {
                    synchronized (Cache.this) {
                        if (CacheRequestImpl.this.done) {
                            return;
                        }
                        CacheRequestImpl.this.done = true;
                        Cache cache = Cache.this;
                        cache.writeSuccessCount++;
                        super.close();
                        editor.commit();
                    }
                }
            };
        }

        public final void abort() {
            synchronized (Cache.this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                Cache cache = Cache.this;
                cache.writeAbortCount++;
                Util.closeQuietly(this.cacheOut);
                try {
                    this.editor.abort();
                } catch (IOException e) {
                }
            }
        }

        public final s body() {
            return this.body;
        }
    }

    private static class CacheResponseBody extends ResponseBody {
        private final e bodySource;
        @Nullable
        private final String contentLength;
        @Nullable
        private final String contentType;
        final Snapshot snapshot;

        CacheResponseBody(final Snapshot snapshot, String contentType, String contentLength) {
            this.snapshot = snapshot;
            this.contentType = contentType;
            this.contentLength = contentLength;
            this.bodySource = l.a(new h(snapshot.getSource(1)) {
                public void close() throws IOException {
                    snapshot.close();
                    super.close();
                }
            });
        }

        public MediaType contentType() {
            return this.contentType != null ? MediaType.parse(this.contentType) : null;
        }

        public long contentLength() {
            try {
                return this.contentLength != null ? Long.parseLong(this.contentLength) : -1;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        public e source() {
            return this.bodySource;
        }
    }

    private static final class Entry {
        private static final String RECEIVED_MILLIS = (Platform.get().getPrefix() + "-Received-Millis");
        private static final String SENT_MILLIS = (Platform.get().getPrefix() + "-Sent-Millis");
        private final int code;
        @Nullable
        private final Handshake handshake;
        private final String message;
        private final Protocol protocol;
        private final long receivedResponseMillis;
        private final String requestMethod;
        private final Headers responseHeaders;
        private final long sentRequestMillis;
        private final String url;
        private final Headers varyHeaders;

        Entry(t in) throws IOException {
            try {
                int i;
                long parseLong;
                e source = l.a(in);
                this.url = source.r();
                this.requestMethod = source.r();
                Builder varyHeadersBuilder = new Builder();
                int varyRequestHeaderLineCount = Cache.readInt(source);
                for (i = 0; i < varyRequestHeaderLineCount; i++) {
                    varyHeadersBuilder.addLenient(source.r());
                }
                this.varyHeaders = varyHeadersBuilder.build();
                StatusLine statusLine = StatusLine.parse(source.r());
                this.protocol = statusLine.protocol;
                this.code = statusLine.code;
                this.message = statusLine.message;
                Builder responseHeadersBuilder = new Builder();
                int responseHeaderLineCount = Cache.readInt(source);
                for (i = 0; i < responseHeaderLineCount; i++) {
                    responseHeadersBuilder.addLenient(source.r());
                }
                String sendRequestMillisString = responseHeadersBuilder.get(SENT_MILLIS);
                String receivedResponseMillisString = responseHeadersBuilder.get(RECEIVED_MILLIS);
                responseHeadersBuilder.removeAll(SENT_MILLIS);
                responseHeadersBuilder.removeAll(RECEIVED_MILLIS);
                if (sendRequestMillisString != null) {
                    parseLong = Long.parseLong(sendRequestMillisString);
                } else {
                    parseLong = 0;
                }
                this.sentRequestMillis = parseLong;
                if (receivedResponseMillisString != null) {
                    parseLong = Long.parseLong(receivedResponseMillisString);
                } else {
                    parseLong = 0;
                }
                this.receivedResponseMillis = parseLong;
                this.responseHeaders = responseHeadersBuilder.build();
                if (isHttps()) {
                    String blank = source.r();
                    if (blank.length() > 0) {
                        throw new IOException("expected \"\" but was \"" + blank + "\"");
                    }
                    TlsVersion tlsVersion;
                    CipherSuite cipherSuite = CipherSuite.forJavaName(source.r());
                    List<Certificate> peerCertificates = readCertificateList(source);
                    List<Certificate> localCertificates = readCertificateList(source);
                    if (source.e()) {
                        tlsVersion = TlsVersion.SSL_3_0;
                    } else {
                        tlsVersion = TlsVersion.forJavaName(source.r());
                    }
                    this.handshake = Handshake.get(tlsVersion, cipherSuite, peerCertificates, localCertificates);
                } else {
                    this.handshake = null;
                }
                in.close();
            } catch (Throwable th) {
                in.close();
            }
        }

        Entry(Response response) {
            this.url = response.request().url().toString();
            this.varyHeaders = HttpHeaders.varyHeaders(response);
            this.requestMethod = response.request().method();
            this.protocol = response.protocol();
            this.code = response.code();
            this.message = response.message();
            this.responseHeaders = response.headers();
            this.handshake = response.handshake();
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
        }

        public final void writeTo(Editor editor) throws IOException {
            int i;
            d sink = l.a(editor.newSink(0));
            sink.b(this.url).h(10);
            sink.b(this.requestMethod).h(10);
            sink.m((long) this.varyHeaders.size()).h(10);
            int size = this.varyHeaders.size();
            for (i = 0; i < size; i++) {
                sink.b(this.varyHeaders.name(i)).b(": ").b(this.varyHeaders.value(i)).h(10);
            }
            sink.b(new StatusLine(this.protocol, this.code, this.message).toString()).h(10);
            sink.m((long) (this.responseHeaders.size() + 2)).h(10);
            size = this.responseHeaders.size();
            for (i = 0; i < size; i++) {
                sink.b(this.responseHeaders.name(i)).b(": ").b(this.responseHeaders.value(i)).h(10);
            }
            sink.b(SENT_MILLIS).b(": ").m(this.sentRequestMillis).h(10);
            sink.b(RECEIVED_MILLIS).b(": ").m(this.receivedResponseMillis).h(10);
            if (isHttps()) {
                sink.h(10);
                sink.b(this.handshake.cipherSuite().javaName()).h(10);
                writeCertList(sink, this.handshake.peerCertificates());
                writeCertList(sink, this.handshake.localCertificates());
                sink.b(this.handshake.tlsVersion().javaName()).h(10);
            }
            sink.close();
        }

        private boolean isHttps() {
            return this.url.startsWith("https://");
        }

        private List<Certificate> readCertificateList(e source) throws IOException {
            int length = Cache.readInt(source);
            if (length == -1) {
                return Collections.emptyList();
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                List<Certificate> result = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    String line = source.r();
                    c bytes = new c();
                    bytes.a(f.b(line));
                    result.add(certificateFactory.generateCertificate(bytes.f()));
                }
                return result;
            } catch (CertificateException e) {
                throw new IOException(e.getMessage());
            }
        }

        private void writeCertList(d sink, List<Certificate> certificates) throws IOException {
            try {
                sink.m((long) certificates.size()).h(10);
                int size = certificates.size();
                for (int i = 0; i < size; i++) {
                    sink.b(f.a(((Certificate) certificates.get(i)).getEncoded()).b()).h(10);
                }
            } catch (CertificateEncodingException e) {
                throw new IOException(e.getMessage());
            }
        }

        public final boolean matches(Request request, Response response) {
            return this.url.equals(request.url().toString()) && this.requestMethod.equals(request.method()) && HttpHeaders.varyMatches(response, this.varyHeaders, request);
        }

        public final Response response(Snapshot snapshot) {
            String contentType = this.responseHeaders.get("Content-Type");
            String contentLength = this.responseHeaders.get("Content-Length");
            return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, null).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).body(new CacheResponseBody(snapshot, contentType, contentLength)).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
        }
    }

    public Cache(File directory, long maxSize) {
        this(directory, maxSize, FileSystem.SYSTEM);
    }

    Cache(File directory, long maxSize, FileSystem fileSystem) {
        this.internalCache = new InternalCache() {
            public Response get(Request request) throws IOException {
                return Cache.this.get(request);
            }

            public CacheRequest put(Response response) throws IOException {
                return Cache.this.put(response);
            }

            public void remove(Request request) throws IOException {
                Cache.this.remove(request);
            }

            public void update(Response cached, Response network) {
                Cache.this.update(cached, network);
            }

            public void trackConditionalCacheHit() {
                Cache.this.trackConditionalCacheHit();
            }

            public void trackResponse(CacheStrategy cacheStrategy) {
                Cache.this.trackResponse(cacheStrategy);
            }
        };
        this.cache = DiskLruCache.create(fileSystem, directory, VERSION, 2, maxSize);
    }

    public static String key(HttpUrl url) {
        return f.a(url.toString()).c().f();
    }

    @Nullable
    final Response get(Request request) {
        try {
            Closeable snapshot = this.cache.get(key(request.url()));
            if (snapshot == null) {
                return null;
            }
            try {
                Entry entry = new Entry(snapshot.getSource(0));
                Response response = entry.response(snapshot);
                if (entry.matches(request, response)) {
                    return response;
                }
                Util.closeQuietly(response.body());
                return null;
            } catch (IOException e) {
                Util.closeQuietly(snapshot);
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
    }

    @Nullable
    final CacheRequest put(Response response) {
        String requestMethod = response.request().method();
        if (HttpMethod.invalidatesCache(response.request().method())) {
            try {
                remove(response.request());
                return null;
            } catch (IOException e) {
                return null;
            }
        } else if (!requestMethod.equals("GET") || HttpHeaders.hasVaryAll(response)) {
            return null;
        } else {
            Entry entry = new Entry(response);
            try {
                Editor editor = this.cache.edit(key(response.request().url()));
                if (editor == null) {
                    return null;
                }
                entry.writeTo(editor);
                return new CacheRequestImpl(editor);
            } catch (IOException e2) {
                abortQuietly(null);
                return null;
            }
        }
    }

    final void remove(Request request) throws IOException {
        this.cache.remove(key(request.url()));
    }

    final void update(Response cached, Response network) {
        Entry entry = new Entry(network);
        try {
            Editor editor = ((CacheResponseBody) cached.body()).snapshot.edit();
            if (editor != null) {
                entry.writeTo(editor);
                editor.commit();
            }
        } catch (IOException e) {
            abortQuietly(null);
        }
    }

    private void abortQuietly(@Nullable Editor editor) {
        if (editor != null) {
            try {
                editor.abort();
            } catch (IOException e) {
            }
        }
    }

    public final void initialize() throws IOException {
        this.cache.initialize();
    }

    public final void delete() throws IOException {
        this.cache.delete();
    }

    public final void evictAll() throws IOException {
        this.cache.evictAll();
    }

    public final Iterator<String> urls() throws IOException {
        return new Iterator<String>() {
            boolean canRemove;
            final Iterator<Snapshot> delegate = Cache.this.cache.snapshots();
            @Nullable
            String nextUrl;

            public boolean hasNext() {
                if (this.nextUrl != null) {
                    return true;
                }
                this.canRemove = false;
                while (this.delegate.hasNext()) {
                    Snapshot snapshot = (Snapshot) this.delegate.next();
                    try {
                        this.nextUrl = l.a(snapshot.getSource(0)).r();
                        snapshot.close();
                        return true;
                    } catch (IOException e) {
                        snapshot.close();
                    } catch (Throwable th) {
                        snapshot.close();
                        throw th;
                    }
                }
                return false;
            }

            public String next() {
                if (hasNext()) {
                    String result = this.nextUrl;
                    this.nextUrl = null;
                    this.canRemove = true;
                    return result;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (this.canRemove) {
                    this.delegate.remove();
                    return;
                }
                throw new IllegalStateException("remove() before next()");
            }
        };
    }

    public final synchronized int writeAbortCount() {
        return this.writeAbortCount;
    }

    public final synchronized int writeSuccessCount() {
        return this.writeSuccessCount;
    }

    public final long size() throws IOException {
        return this.cache.size();
    }

    public final long maxSize() {
        return this.cache.getMaxSize();
    }

    public final void flush() throws IOException {
        this.cache.flush();
    }

    public final void close() throws IOException {
        this.cache.close();
    }

    public final File directory() {
        return this.cache.getDirectory();
    }

    public final boolean isClosed() {
        return this.cache.isClosed();
    }

    final synchronized void trackResponse(CacheStrategy cacheStrategy) {
        this.requestCount++;
        if (cacheStrategy.networkRequest != null) {
            this.networkCount++;
        } else if (cacheStrategy.cacheResponse != null) {
            this.hitCount++;
        }
    }

    final synchronized void trackConditionalCacheHit() {
        this.hitCount++;
    }

    public final synchronized int networkCount() {
        return this.networkCount;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int requestCount() {
        return this.requestCount;
    }

    static int readInt(e source) throws IOException {
        try {
            long result = source.n();
            String line = source.r();
            if (result >= 0 && result <= 2147483647L && line.isEmpty()) {
                return (int) result;
            }
            throw new IOException("expected an int but was \"" + result + line + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }
}
