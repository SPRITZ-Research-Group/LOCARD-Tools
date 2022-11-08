package com.facebook.react.modules.network;

import android.util.Base64;
import c.f;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.aq;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.j;
import com.facebook.react.common.h;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@ReactModule(name = "Networking")
public final class NetworkingModule extends ReactContextBaseJavaModule {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    protected static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private final OkHttpClient mClient;
    private final b mCookieHandler;
    private final a mCookieJarContainer;
    @Nullable
    private final String mDefaultUserAgent;
    private final Set<Integer> mRequestIds;
    private boolean mShuttingDown;

    NetworkingModule(ag reactContext, @Nullable String defaultUserAgent, OkHttpClient client, @Nullable List<d> networkInterceptorCreators) {
        super(reactContext);
        if (networkInterceptorCreators != null) {
            Builder clientBuilder = client.newBuilder();
            for (d networkInterceptorCreator : networkInterceptorCreators) {
                clientBuilder.addNetworkInterceptor(networkInterceptorCreator.a());
            }
            client = clientBuilder.build();
        }
        this.mClient = client;
        this.mCookieHandler = new b(reactContext);
        this.mCookieJarContainer = (a) this.mClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = defaultUserAgent;
        this.mRequestIds = new HashSet();
    }

    NetworkingModule(ag context, @Nullable String defaultUserAgent, OkHttpClient client) {
        this(context, defaultUserAgent, client, null);
    }

    public NetworkingModule(ag context) {
        this(context, null, e.b(), null);
    }

    public NetworkingModule(ag context, List<d> networkInterceptorCreators) {
        this(context, null, e.b(), networkInterceptorCreators);
    }

    public NetworkingModule(ag context, String defaultUserAgent) {
        this(context, defaultUserAgent, e.b(), null);
    }

    public final void initialize() {
        this.mCookieJarContainer.a(new JavaNetCookieJar(this.mCookieHandler));
    }

    public final String getName() {
        return NAME;
    }

    public final void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.a();
        this.mCookieJarContainer.a();
    }

    @ReactMethod
    public final void sendRequest(String method, String url, int requestId, al headers, am data, String responseType, boolean useIncrementalUpdates, int timeout, boolean withCredentials) {
        try {
            sendRequestInternal(method, url, requestId, headers, data, responseType, useIncrementalUpdates, timeout, withCredentials);
        } catch (Throwable th) {
            FLog.e(TAG, "Failed to send url request: " + url, th);
            l.a(getEventEmitter(), requestId, th.getMessage(), th);
        }
    }

    public final void sendRequestInternal(String method, String url, int requestId, al headers, am data, String responseType, boolean useIncrementalUpdates, int timeout, boolean withCredentials) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (requestId != 0) {
            requestBuilder.tag(Integer.valueOf(requestId));
        }
        final RCTDeviceEventEmitter eventEmitter = getEventEmitter();
        Builder clientBuilder = this.mClient.newBuilder();
        if (!withCredentials) {
            clientBuilder.cookieJar(CookieJar.NO_COOKIES);
        }
        if (useIncrementalUpdates) {
            final String str = responseType;
            final int i = requestId;
            clientBuilder.addNetworkInterceptor(new Interceptor(this) {
                final /* synthetic */ NetworkingModule d;

                public final Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder().body(new h(originalResponse.body(), new f(this) {
                        long a = System.nanoTime();
                        final /* synthetic */ AnonymousClass1 b;

                        {
                            this.b = this$1;
                        }

                        public final void a(long bytesWritten, long contentLength, boolean done) {
                            long now = System.nanoTime();
                            if ((done || NetworkingModule.shouldDispatch(now, this.a)) && !str.equals("text")) {
                                RCTDeviceEventEmitter rCTDeviceEventEmitter = eventEmitter;
                                int i = i;
                                aq writableNativeArray = new WritableNativeArray();
                                writableNativeArray.pushInt(i);
                                writableNativeArray.pushInt((int) bytesWritten);
                                writableNativeArray.pushInt((int) contentLength);
                                rCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", writableNativeArray);
                                this.a = now;
                            }
                        }
                    })).build();
                }
            });
        }
        if (timeout != this.mClient.connectTimeoutMillis()) {
            clientBuilder.readTimeout((long) timeout, TimeUnit.MILLISECONDS);
        }
        OkHttpClient client = clientBuilder.build();
        Headers requestHeaders = extractHeaders(headers, data);
        if (requestHeaders == null) {
            l.a(eventEmitter, requestId, "Unrecognized headers format", null);
            return;
        }
        String contentType = requestHeaders.get(CONTENT_TYPE_HEADER_NAME);
        String contentEncoding = requestHeaders.get(CONTENT_ENCODING_HEADER_NAME);
        requestBuilder.headers(requestHeaders);
        if (data == null) {
            requestBuilder.method(method, k.a(method));
        } else {
            if (!data.hasKey(REQUEST_BODY_KEY_STRING)) {
                if (!data.hasKey(REQUEST_BODY_KEY_BASE64)) {
                    if (!data.hasKey("uri")) {
                        if (data.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                            if (contentType == null) {
                                contentType = "multipart/form-data";
                            }
                            MultipartBody.Builder multipartBuilder = constructMultipartBody(data.getArray(REQUEST_BODY_KEY_FORMDATA), contentType, requestId);
                            if (multipartBuilder != null) {
                                final int i2 = requestId;
                                requestBuilder.method(method, new g(multipartBuilder.build(), new f(this) {
                                    long a = System.nanoTime();
                                    final /* synthetic */ NetworkingModule d;

                                    public final void a(long bytesWritten, long contentLength, boolean done) {
                                        long now = System.nanoTime();
                                        if (done || NetworkingModule.shouldDispatch(now, this.a)) {
                                            RCTDeviceEventEmitter rCTDeviceEventEmitter = eventEmitter;
                                            int i = i2;
                                            aq writableNativeArray = new WritableNativeArray();
                                            writableNativeArray.pushInt(i);
                                            writableNativeArray.pushInt((int) bytesWritten);
                                            writableNativeArray.pushInt((int) contentLength);
                                            rCTDeviceEventEmitter.emit("didSendNetworkData", writableNativeArray);
                                            this.a = now;
                                        }
                                    }
                                }));
                            } else {
                                return;
                            }
                        }
                        requestBuilder.method(method, k.a(method));
                    } else if (contentType == null) {
                        l.a(eventEmitter, requestId, "Payload is set but no content-type header specified", null);
                        return;
                    } else {
                        String uri = data.getString("uri");
                        InputStream fileInputStream = k.a(getReactApplicationContext(), uri);
                        if (fileInputStream == null) {
                            l.a(eventEmitter, requestId, "Could not retrieve file for uri " + uri, null);
                            return;
                        }
                        requestBuilder.method(method, k.a(MediaType.parse(contentType), fileInputStream));
                    }
                } else if (contentType == null) {
                    l.a(eventEmitter, requestId, "Payload is set but no content-type header specified", null);
                    return;
                } else {
                    String base64String = data.getString(REQUEST_BODY_KEY_BASE64);
                    requestBuilder.method(method, RequestBody.create(MediaType.parse(contentType), f.b(base64String)));
                }
            } else if (contentType == null) {
                l.a(eventEmitter, requestId, "Payload is set but no content-type header specified", null);
                return;
            } else {
                String body = data.getString(REQUEST_BODY_KEY_STRING);
                MediaType contentMediaType = MediaType.parse(contentType);
                if ("gzip".equalsIgnoreCase(contentEncoding)) {
                    RequestBody requestBody = k.a(contentMediaType, body);
                    if (requestBody == null) {
                        l.a(eventEmitter, requestId, "Failed to gzip request body", null);
                        return;
                    }
                    requestBuilder.method(method, requestBody);
                } else {
                    requestBuilder.method(method, RequestBody.create(contentMediaType, body));
                }
            }
        }
        addRequest(requestId);
        final int i3 = requestId;
        final boolean z = useIncrementalUpdates;
        final String str2 = responseType;
        client.newCall(requestBuilder.build()).enqueue(new Callback(this) {
            final /* synthetic */ NetworkingModule e;

            public final void onFailure(Call call, IOException e) {
                if (!this.e.mShuttingDown) {
                    String errorMessage;
                    this.e.removeRequest(i3);
                    if (e.getMessage() != null) {
                        errorMessage = e.getMessage();
                    } else {
                        errorMessage = "Error while executing request: " + e.getClass().getSimpleName();
                    }
                    l.a(eventEmitter, i3, errorMessage, e);
                }
            }

            public final void onResponse(Call call, Response response) throws IOException {
                if (!this.e.mShuttingDown) {
                    this.e.removeRequest(i3);
                    RCTDeviceEventEmitter rCTDeviceEventEmitter = eventEmitter;
                    int i = i3;
                    int code = response.code();
                    ar access$300 = NetworkingModule.translateHeaders(response.headers());
                    String httpUrl = response.request().url().toString();
                    aq writableNativeArray = new WritableNativeArray();
                    writableNativeArray.pushInt(i);
                    writableNativeArray.pushInt(code);
                    writableNativeArray.pushMap(access$300);
                    writableNativeArray.pushString(httpUrl);
                    rCTDeviceEventEmitter.emit("didReceiveNetworkResponse", writableNativeArray);
                    ResponseBody responseBody = response.body();
                    try {
                        if (z && str2.equals("text")) {
                            this.e.readWithProgress(eventEmitter, i3, responseBody);
                            l.a(eventEmitter, i3);
                            return;
                        }
                        String responseString = "";
                        if (str2.equals("text")) {
                            responseString = responseBody.string();
                        } else if (str2.equals(NetworkingModule.REQUEST_BODY_KEY_BASE64)) {
                            responseString = Base64.encodeToString(responseBody.bytes(), 2);
                        }
                        rCTDeviceEventEmitter = eventEmitter;
                        i = i3;
                        aq writableNativeArray2 = new WritableNativeArray();
                        writableNativeArray2.pushInt(i);
                        writableNativeArray2.pushString(responseString);
                        rCTDeviceEventEmitter.emit("didReceiveNetworkData", writableNativeArray2);
                        l.a(eventEmitter, i3);
                    } catch (IOException e) {
                        l.a(eventEmitter, i3, e.getMessage(), e);
                    }
                }
            }
        });
    }

    private void readWithProgress(RCTDeviceEventEmitter eventEmitter, int requestId, ResponseBody responseBody) throws IOException {
        Charset charset;
        long totalBytesRead = -1;
        long contentLength = -1;
        try {
            h progressResponseBody = (h) responseBody;
            totalBytesRead = progressResponseBody.a();
            contentLength = progressResponseBody.contentLength();
        } catch (ClassCastException e) {
        }
        if (responseBody.contentType() == null) {
            charset = h.a;
        } else {
            charset = responseBody.contentType().charset(h.a);
        }
        int read;
        if (h.a.equals(charset)) {
            i streamDecoder = new i();
            InputStream inputStream = responseBody.byteStream();
            try {
                byte[] buffer = new byte[MAX_CHUNK_SIZE_BETWEEN_FLUSHES];
                while (true) {
                    read = inputStream.read(buffer);
                    if (read == -1) {
                        break;
                    }
                    l.a(eventEmitter, requestId, streamDecoder.a(buffer, read), totalBytesRead, contentLength);
                }
            } finally {
                inputStream.close();
            }
        } else {
            Reader reader = responseBody.charStream();
            try {
                char[] buffer2 = new char[MAX_CHUNK_SIZE_BETWEEN_FLUSHES];
                while (true) {
                    read = reader.read(buffer2);
                    if (read == -1) {
                        break;
                    }
                    l.a(eventEmitter, requestId, new String(buffer2, 0, read), totalBytesRead, contentLength);
                }
            } finally {
                reader.close();
            }
        }
    }

    private static boolean shouldDispatch(long now, long last) {
        return 100000000 + last < now;
    }

    private synchronized void addRequest(int requestId) {
        this.mRequestIds.add(Integer.valueOf(requestId));
    }

    private synchronized void removeRequest(int requestId) {
        this.mRequestIds.remove(Integer.valueOf(requestId));
    }

    private synchronized void cancelAllRequests() {
        for (Integer requestId : this.mRequestIds) {
            cancelRequest(requestId.intValue());
        }
        this.mRequestIds.clear();
    }

    @ReactMethod
    public final void abortRequest(int requestId) {
        cancelRequest(requestId);
        removeRequest(requestId);
    }

    private void cancelRequest(final int requestId) {
        new j<Void, Void>(this, getReactApplicationContext()) {
            final /* synthetic */ NetworkingModule b;

            protected final /* synthetic */ void a() {
                OkHttpClient access$500 = this.b.mClient;
                Integer valueOf = Integer.valueOf(requestId);
                for (Call call : access$500.dispatcher().queuedCalls()) {
                    if (valueOf.equals(call.request().tag())) {
                        call.cancel();
                        return;
                    }
                }
                for (Call call2 : access$500.dispatcher().runningCalls()) {
                    if (valueOf.equals(call2.request().tag())) {
                        call2.cancel();
                        return;
                    }
                }
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public final void clearCookies(d callback) {
        this.mCookieHandler.a(callback);
    }

    @Nullable
    private MultipartBody.Builder constructMultipartBody(al body, String contentType, int requestId) {
        RCTDeviceEventEmitter eventEmitter = getEventEmitter();
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        multipartBuilder.setType(MediaType.parse(contentType));
        int size = body.size();
        for (int i = 0; i < size; i++) {
            am bodyPart = body.getMap(i);
            Headers headers = extractHeaders(bodyPart.getArray("headers"), null);
            if (headers == null) {
                l.a(eventEmitter, requestId, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            MediaType partContentType = null;
            String partContentTypeStr = headers.get(CONTENT_TYPE_HEADER_NAME);
            if (partContentTypeStr != null) {
                partContentType = MediaType.parse(partContentTypeStr);
                headers = headers.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            }
            if (bodyPart.hasKey(REQUEST_BODY_KEY_STRING)) {
                multipartBuilder.addPart(headers, RequestBody.create(partContentType, bodyPart.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!bodyPart.hasKey("uri")) {
                l.a(eventEmitter, requestId, "Unrecognized FormData part.", null);
            } else if (partContentType == null) {
                l.a(eventEmitter, requestId, "Binary FormData part needs a content-type header.", null);
                return null;
            } else {
                String fileContentUriStr = bodyPart.getString("uri");
                InputStream fileInputStream = k.a(getReactApplicationContext(), fileContentUriStr);
                if (fileInputStream == null) {
                    l.a(eventEmitter, requestId, "Could not retrieve file for uri " + fileContentUriStr, null);
                    return null;
                }
                multipartBuilder.addPart(headers, k.a(partContentType, fileInputStream));
            }
        }
        return multipartBuilder;
    }

    @Nullable
    private Headers extractHeaders(@Nullable al headersArray, @Nullable am requestData) {
        if (headersArray == null) {
            return null;
        }
        Object obj;
        Headers.Builder headersBuilder = new Headers.Builder();
        int size = headersArray.size();
        for (int headersIdx = 0; headersIdx < size; headersIdx++) {
            al header = headersArray.getArray(headersIdx);
            if (header == null || header.size() != 2) {
                return null;
            }
            String headerName = header.getString(0);
            StringBuilder stringBuilder = new StringBuilder(headerName.length());
            int length = headerName.length();
            obj = null;
            for (int i = 0; i < length; i++) {
                char charAt = headerName.charAt(i);
                if (charAt <= ' ' || charAt >= 127) {
                    obj = 1;
                } else {
                    stringBuilder.append(charAt);
                }
            }
            if (obj != null) {
                headerName = stringBuilder.toString();
            }
            String headerValue = c.a(header.getString(1));
            if (headerName == null || headerValue == null) {
                return null;
            }
            headersBuilder.add(headerName, headerValue);
        }
        if (headersBuilder.get(USER_AGENT_HEADER_NAME) == null && this.mDefaultUserAgent != null) {
            headersBuilder.add(USER_AGENT_HEADER_NAME, this.mDefaultUserAgent);
        }
        obj = (requestData == null || !requestData.hasKey(REQUEST_BODY_KEY_STRING)) ? null : 1;
        if (obj == null) {
            headersBuilder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return headersBuilder.build();
    }

    private RCTDeviceEventEmitter getEventEmitter() {
        return (RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class);
    }

    private static ar translateHeaders(Headers headers) {
        ar responseHeaders = new WritableNativeMap();
        for (int i = 0; i < headers.size(); i++) {
            String headerName = headers.name(i);
            if (responseHeaders.hasKey(headerName)) {
                responseHeaders.putString(headerName, responseHeaders.getString(headerName) + ", " + headers.value(i));
            } else {
                responseHeaders.putString(headerName, headers.value(i));
            }
        }
        return responseHeaders;
    }
}
