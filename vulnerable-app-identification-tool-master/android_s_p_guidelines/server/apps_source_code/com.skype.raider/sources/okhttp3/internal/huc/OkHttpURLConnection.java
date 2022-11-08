package okhttp3.internal.huc;

import c.c;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.JavaNetHeaders;
import okhttp3.internal.URLFilter;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.platform.Platform;

public final class OkHttpURLConnection extends HttpURLConnection implements Callback {
    private static final Set<String> METHODS = new LinkedHashSet(Arrays.asList(new String[]{"OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH"}));
    public static final String RESPONSE_SOURCE = (Platform.get().getPrefix() + "-Response-Source");
    public static final String SELECTED_PROTOCOL = (Platform.get().getPrefix() + "-Selected-Protocol");
    Call call;
    private Throwable callFailure;
    OkHttpClient client;
    boolean connectPending;
    private boolean executed;
    private long fixedContentLength;
    Handshake handshake;
    private final Object lock;
    private final NetworkInterceptor networkInterceptor;
    Response networkResponse;
    Proxy proxy;
    private Builder requestHeaders;
    private Response response;
    private Headers responseHeaders;
    URLFilter urlFilter;

    final class NetworkInterceptor implements Interceptor {
        private boolean proceed;

        NetworkInterceptor() {
        }

        public final void proceed() {
            synchronized (OkHttpURLConnection.this.lock) {
                this.proceed = true;
                OkHttpURLConnection.this.lock.notifyAll();
            }
        }

        public final Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (OkHttpURLConnection.this.urlFilter != null) {
                OkHttpURLConnection.this.urlFilter.checkURLPermitted(request.url().url());
            }
            synchronized (OkHttpURLConnection.this.lock) {
                OkHttpURLConnection.this.connectPending = false;
                OkHttpURLConnection.this.proxy = chain.connection().route().proxy();
                OkHttpURLConnection.this.handshake = chain.connection().handshake();
                OkHttpURLConnection.this.lock.notifyAll();
                while (!this.proceed) {
                    try {
                        OkHttpURLConnection.this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
            }
            if (request.body() instanceof OutputStreamRequestBody) {
                request = ((OutputStreamRequestBody) request.body()).prepareToSendRequest(request);
            }
            Response response = chain.proceed(request);
            synchronized (OkHttpURLConnection.this.lock) {
                OkHttpURLConnection.this.networkResponse = response;
                OkHttpURLConnection.this.url = response.request().url().url();
            }
            return response;
        }
    }

    static final class UnexpectedException extends IOException {
        static final Interceptor INTERCEPTOR = new Interceptor() {
            public final Response intercept(Chain chain) throws IOException {
                Throwable e;
                try {
                    return chain.proceed(chain.request());
                } catch (Error e2) {
                    e = e2;
                } catch (RuntimeException e3) {
                    e = e3;
                }
                throw new UnexpectedException(e);
            }
        };

        UnexpectedException(Throwable cause) {
            super(cause);
        }
    }

    public OkHttpURLConnection(URL url, OkHttpClient client) {
        super(url);
        this.networkInterceptor = new NetworkInterceptor();
        this.requestHeaders = new Builder();
        this.fixedContentLength = -1;
        this.lock = new Object();
        this.connectPending = true;
        this.client = client;
    }

    public OkHttpURLConnection(URL url, OkHttpClient client, URLFilter urlFilter) {
        this(url, client);
        this.urlFilter = urlFilter;
    }

    public final void connect() throws IOException {
        if (!this.executed) {
            Call call = buildCall();
            this.executed = true;
            call.enqueue(this);
            synchronized (this.lock) {
                while (this.connectPending && this.response == null && this.callFailure == null) {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException e) {
                        throw new InterruptedIOException();
                    }
                }
                if (this.callFailure != null) {
                    throw propagate(this.callFailure);
                }
            }
        }
    }

    public final void disconnect() {
        if (this.call != null) {
            this.networkInterceptor.proceed();
            this.call.cancel();
        }
    }

    public final InputStream getErrorStream() {
        try {
            Response response = getResponse(true);
            if (!HttpHeaders.hasBody(response) || response.code() < 400) {
                return null;
            }
            return response.body().byteStream();
        } catch (IOException e) {
            return null;
        }
    }

    private Headers getHeaders() throws IOException {
        if (this.responseHeaders == null) {
            Response response = getResponse(true);
            this.responseHeaders = response.headers().newBuilder().add(SELECTED_PROTOCOL, response.protocol().toString()).add(RESPONSE_SOURCE, responseSourceHeader(response)).build();
        }
        return this.responseHeaders;
    }

    private static String responseSourceHeader(Response response) {
        if (response.networkResponse() == null) {
            if (response.cacheResponse() == null) {
                return "NONE";
            }
            return "CACHE " + response.code();
        } else if (response.cacheResponse() == null) {
            return "NETWORK " + response.code();
        } else {
            return "CONDITIONAL_CACHE " + response.networkResponse().code();
        }
    }

    public final String getHeaderField(int position) {
        try {
            Headers headers = getHeaders();
            if (position < 0 || position >= headers.size()) {
                return null;
            }
            return headers.value(position);
        } catch (IOException e) {
            return null;
        }
    }

    public final String getHeaderField(String fieldName) {
        if (fieldName != null) {
            return getHeaders().get(fieldName);
        }
        try {
            return StatusLine.get(getResponse(true)).toString();
        } catch (IOException e) {
            return null;
        }
    }

    public final String getHeaderFieldKey(int position) {
        try {
            Headers headers = getHeaders();
            if (position < 0 || position >= headers.size()) {
                return null;
            }
            return headers.name(position);
        } catch (IOException e) {
            return null;
        }
    }

    public final Map<String, List<String>> getHeaderFields() {
        try {
            return JavaNetHeaders.toMultimap(getHeaders(), StatusLine.get(getResponse(true)).toString());
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }

    public final Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return JavaNetHeaders.toMultimap(this.requestHeaders.build(), null);
        }
        throw new IllegalStateException("Cannot access request header fields after connection is set");
    }

    public final InputStream getInputStream() throws IOException {
        if (this.doInput) {
            Response response = getResponse(false);
            if (response.code() < 400) {
                return response.body().byteStream();
            }
            throw new FileNotFoundException(this.url.toString());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    public final OutputStream getOutputStream() throws IOException {
        OutputStreamRequestBody requestBody = (OutputStreamRequestBody) buildCall().request().body();
        if (requestBody == null) {
            throw new ProtocolException("method does not support a request body: " + this.method);
        }
        if (requestBody instanceof StreamedRequestBody) {
            connect();
            this.networkInterceptor.proceed();
        }
        if (!requestBody.isClosed()) {
            return requestBody.outputStream();
        }
        throw new ProtocolException("cannot write request body after response has been read");
    }

    public final Permission getPermission() throws IOException {
        int hostPort;
        URL url = getURL();
        String hostname = url.getHost();
        if (url.getPort() != -1) {
            hostPort = url.getPort();
        } else {
            hostPort = HttpUrl.defaultPort(url.getProtocol());
        }
        if (usingProxy()) {
            InetSocketAddress proxyAddress = (InetSocketAddress) this.client.proxy().address();
            hostname = proxyAddress.getHostName();
            hostPort = proxyAddress.getPort();
        }
        return new SocketPermission(hostname + ":" + hostPort, "connect, resolve");
    }

    public final String getRequestProperty(String field) {
        if (field == null) {
            return null;
        }
        return this.requestHeaders.get(field);
    }

    public final void setConnectTimeout(int timeoutMillis) {
        this.client = this.client.newBuilder().connectTimeout((long) timeoutMillis, TimeUnit.MILLISECONDS).build();
    }

    public final void setInstanceFollowRedirects(boolean followRedirects) {
        this.client = this.client.newBuilder().followRedirects(followRedirects).build();
    }

    public final boolean getInstanceFollowRedirects() {
        return this.client.followRedirects();
    }

    public final int getConnectTimeout() {
        return this.client.connectTimeoutMillis();
    }

    public final void setReadTimeout(int timeoutMillis) {
        this.client = this.client.newBuilder().readTimeout((long) timeoutMillis, TimeUnit.MILLISECONDS).build();
    }

    public final int getReadTimeout() {
        return this.client.readTimeoutMillis();
    }

    private Call buildCall() throws IOException {
        boolean stream = true;
        if (this.call != null) {
            return this.call;
        }
        this.connected = true;
        if (this.doOutput) {
            if (this.method.equals("GET")) {
                this.method = "POST";
            } else if (!HttpMethod.permitsRequestBody(this.method)) {
                throw new ProtocolException(this.method + " does not support writing");
            }
        }
        if (this.requestHeaders.get("User-Agent") == null) {
            this.requestHeaders.add("User-Agent", defaultUserAgent());
        }
        OutputStreamRequestBody requestBody = null;
        if (HttpMethod.permitsRequestBody(this.method)) {
            if (this.requestHeaders.get("Content-Type") == null) {
                this.requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
            }
            if (this.fixedContentLength == -1 && this.chunkLength <= 0) {
                stream = false;
            }
            long contentLength = -1;
            String contentLengthString = this.requestHeaders.get("Content-Length");
            if (this.fixedContentLength != -1) {
                contentLength = this.fixedContentLength;
            } else if (contentLengthString != null) {
                contentLength = Long.parseLong(contentLengthString);
            }
            if (stream) {
                requestBody = new StreamedRequestBody(contentLength);
            } else {
                requestBody = new BufferedRequestBody(contentLength);
            }
            requestBody.timeout().timeout((long) this.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        }
        Request request = new Request.Builder().url(Internal.instance.getHttpUrlChecked(getURL().toString())).headers(this.requestHeaders.build()).method(this.method, requestBody).build();
        if (this.urlFilter != null) {
            this.urlFilter.checkURLPermitted(request.url().url());
        }
        OkHttpClient.Builder clientBuilder = this.client.newBuilder();
        clientBuilder.interceptors().clear();
        clientBuilder.interceptors().add(UnexpectedException.INTERCEPTOR);
        clientBuilder.networkInterceptors().clear();
        clientBuilder.networkInterceptors().add(this.networkInterceptor);
        clientBuilder.dispatcher(new Dispatcher(this.client.dispatcher().executorService()));
        if (!getUseCaches()) {
            clientBuilder.cache(null);
        }
        Call newCall = clientBuilder.build().newCall(request);
        this.call = newCall;
        return newCall;
    }

    private String defaultUserAgent() {
        String agent = System.getProperty("http.agent");
        return agent != null ? toHumanReadableAscii(agent) : Version.userAgent();
    }

    private static String toHumanReadableAscii(String s) {
        int i = 0;
        int length = s.length();
        while (i < length) {
            int c = s.codePointAt(i);
            if (c <= 31 || c >= 127) {
                c buffer = new c();
                buffer.a(s, 0, i);
                buffer.a(63);
                int j = i + Character.charCount(c);
                while (j < length) {
                    int i2;
                    c = s.codePointAt(j);
                    if (c <= 31 || c >= 127) {
                        i2 = 63;
                    } else {
                        i2 = c;
                    }
                    buffer.a(i2);
                    j += Character.charCount(c);
                }
                return buffer.q();
            }
            i += Character.charCount(c);
        }
        return s;
    }

    private Response getResponse(boolean networkResponseOnError) throws IOException {
        Response response;
        synchronized (this.lock) {
            if (this.response != null) {
                response = this.response;
            } else if (this.callFailure == null) {
                Call call = buildCall();
                this.networkInterceptor.proceed();
                OutputStreamRequestBody requestBody = (OutputStreamRequestBody) call.request().body();
                if (requestBody != null) {
                    requestBody.outputStream().close();
                }
                if (this.executed) {
                    synchronized (this.lock) {
                        while (this.response == null && this.callFailure == null) {
                            try {
                                this.lock.wait();
                            } catch (InterruptedException e) {
                                throw new InterruptedIOException();
                            }
                        }
                    }
                } else {
                    this.executed = true;
                    try {
                        onResponse(call, call.execute());
                    } catch (IOException e2) {
                        onFailure(call, e2);
                    }
                }
                synchronized (this.lock) {
                    if (this.callFailure != null) {
                        throw propagate(this.callFailure);
                    } else if (this.response != null) {
                        response = this.response;
                    } else {
                        throw new AssertionError();
                    }
                }
            } else if (!networkResponseOnError || this.networkResponse == null) {
                throw propagate(this.callFailure);
            } else {
                response = this.networkResponse;
            }
        }
        return response;
    }

    public final boolean usingProxy() {
        if (this.proxy != null) {
            return true;
        }
        Proxy clientProxy = this.client.proxy();
        if (clientProxy == null || clientProxy.type() == Type.DIRECT) {
            return false;
        }
        return true;
    }

    public final String getResponseMessage() throws IOException {
        return getResponse(true).message();
    }

    public final int getResponseCode() throws IOException {
        return getResponse(true).code();
    }

    public final void setRequestProperty(String field, String newValue) {
        if (this.connected) {
            throw new IllegalStateException("Cannot set request property after connection is made");
        } else if (field == null) {
            throw new NullPointerException("field == null");
        } else if (newValue == null) {
            Platform.get().log(5, "Ignoring header " + field + " because its value was null.", null);
        } else {
            this.requestHeaders.set(field, newValue);
        }
    }

    public final void setIfModifiedSince(long newValue) {
        super.setIfModifiedSince(newValue);
        if (this.ifModifiedSince != 0) {
            this.requestHeaders.set("If-Modified-Since", HttpDate.format(new Date(this.ifModifiedSince)));
        } else {
            this.requestHeaders.removeAll("If-Modified-Since");
        }
    }

    public final void addRequestProperty(String field, String value) {
        if (this.connected) {
            throw new IllegalStateException("Cannot add request property after connection is made");
        } else if (field == null) {
            throw new NullPointerException("field == null");
        } else if (value == null) {
            Platform.get().log(5, "Ignoring header " + field + " because its value was null.", null);
        } else {
            this.requestHeaders.add(field, value);
        }
    }

    public final void setRequestMethod(String method) throws ProtocolException {
        if (METHODS.contains(method)) {
            this.method = method;
            return;
        }
        throw new ProtocolException("Expected one of " + METHODS + " but was " + method);
    }

    public final void setFixedLengthStreamingMode(int contentLength) {
        setFixedLengthStreamingMode((long) contentLength);
    }

    public final void setFixedLengthStreamingMode(long contentLength) {
        if (this.connected) {
            throw new IllegalStateException("Already connected");
        } else if (this.chunkLength > 0) {
            throw new IllegalStateException("Already in chunked mode");
        } else if (contentLength < 0) {
            throw new IllegalArgumentException("contentLength < 0");
        } else {
            this.fixedContentLength = contentLength;
            this.fixedContentLength = (int) Math.min(contentLength, 2147483647L);
        }
    }

    public final void onFailure(Call call, IOException e) {
        synchronized (this.lock) {
            if (e instanceof UnexpectedException) {
                e = e.getCause();
            }
            this.callFailure = e;
            this.lock.notifyAll();
        }
    }

    public final void onResponse(Call call, Response response) {
        synchronized (this.lock) {
            this.response = response;
            this.handshake = response.handshake();
            this.url = response.request().url().url();
            this.lock.notifyAll();
        }
    }

    private static IOException propagate(Throwable throwable) throws IOException {
        if (throwable instanceof IOException) {
            throw ((IOException) throwable);
        } else if (throwable instanceof Error) {
            throw ((Error) throwable);
        } else if (throwable instanceof RuntimeException) {
            throw ((RuntimeException) throwable);
        } else {
            throw new AssertionError();
        }
    }
}
