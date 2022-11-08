package okhttp3.internal.http2;

import c.c;
import c.f;
import c.h;
import c.l;
import c.s;
import c.t;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;

public final class Http2Codec implements HttpCodec {
    private static final f CONNECTION = f.a("connection");
    private static final f ENCODING = f.a("encoding");
    private static final f HOST = f.a("host");
    private static final List<f> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY);
    private static final List<f> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE);
    private static final f KEEP_ALIVE = f.a("keep-alive");
    private static final f PROXY_CONNECTION = f.a("proxy-connection");
    private static final f TE = f.a("te");
    private static final f TRANSFER_ENCODING = f.a("transfer-encoding");
    private static final f UPGRADE = f.a("upgrade");
    private final Chain chain;
    private final Http2Connection connection;
    private final Protocol protocol;
    private Http2Stream stream;
    final StreamAllocation streamAllocation;

    class StreamFinishingSource extends h {
        long bytesRead = 0;
        boolean completed = false;

        StreamFinishingSource(t delegate) {
            super(delegate);
        }

        public long read(c sink, long byteCount) throws IOException {
            try {
                long read = delegate().read(sink, byteCount);
                if (read > 0) {
                    this.bytesRead += read;
                }
                return read;
            } catch (IOException e) {
                endOfInput(e);
                throw e;
            }
        }

        public void close() throws IOException {
            super.close();
            endOfInput(null);
        }

        private void endOfInput(IOException e) {
            if (!this.completed) {
                this.completed = true;
                Http2Codec.this.streamAllocation.streamFinished(false, Http2Codec.this, this.bytesRead, e);
            }
        }
    }

    public Http2Codec(OkHttpClient client, Chain chain, StreamAllocation streamAllocation, Http2Connection connection) {
        Protocol protocol;
        this.chain = chain;
        this.streamAllocation = streamAllocation;
        this.connection = connection;
        if (client.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        } else {
            protocol = Protocol.HTTP_2;
        }
        this.protocol = protocol;
    }

    public final s createRequestBody(Request request, long contentLength) {
        return this.stream.getSink();
    }

    public final void writeRequestHeaders(Request request) throws IOException {
        if (this.stream == null) {
            this.stream = this.connection.newStream(http2HeadersList(request), request.body() != null);
            this.stream.readTimeout().timeout((long) this.chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
            this.stream.writeTimeout().timeout((long) this.chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public final void flushRequest() throws IOException {
        this.connection.flush();
    }

    public final void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    public final Builder readResponseHeaders(boolean expectContinue) throws IOException {
        Builder responseBuilder = readHttp2HeadersList(this.stream.takeResponseHeaders(), this.protocol);
        if (expectContinue && Internal.instance.code(responseBuilder) == 100) {
            return null;
        }
        return responseBuilder;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        List<Header> result = new ArrayList(headers.size() + 4);
        result.add(new Header(Header.TARGET_METHOD, request.method()));
        result.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String host = request.header("Host");
        if (host != null) {
            result.add(new Header(Header.TARGET_AUTHORITY, host));
        }
        result.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            f name = f.a(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(name)) {
                result.add(new Header(name, headers.value(i)));
            }
        }
        return result;
    }

    public static Builder readHttp2HeadersList(List<Header> headerBlock, Protocol protocol) throws IOException {
        StatusLine statusLine = null;
        Headers.Builder headersBuilder = new Headers.Builder();
        int size = headerBlock.size();
        for (int i = 0; i < size; i++) {
            Header header = (Header) headerBlock.get(i);
            if (header != null) {
                f name = header.name;
                String value = header.value.a();
                if (name.equals(Header.RESPONSE_STATUS)) {
                    statusLine = StatusLine.parse("HTTP/1.1 " + value);
                } else if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(name)) {
                    Internal.instance.addLenient(headersBuilder, name.a(), value);
                }
            } else if (statusLine != null && statusLine.code == 100) {
                statusLine = null;
                headersBuilder = new Headers.Builder();
            }
        }
        if (statusLine != null) {
            return new Builder().protocol(protocol).code(statusLine.code).message(statusLine.message).headers(headersBuilder.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    public final ResponseBody openResponseBody(Response response) throws IOException {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        return new RealResponseBody(response.header("Content-Type"), HttpHeaders.contentLength(response), l.a(new StreamFinishingSource(this.stream.getSource())));
    }

    public final void cancel() {
        if (this.stream != null) {
            this.stream.closeLater(ErrorCode.CANCEL);
        }
    }
}
