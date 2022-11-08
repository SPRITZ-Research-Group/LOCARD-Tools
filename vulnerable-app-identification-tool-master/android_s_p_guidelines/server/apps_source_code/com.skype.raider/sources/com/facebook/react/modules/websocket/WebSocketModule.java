package com.facebook.react.modules.websocket;

import c.f;
import com.adjust.sdk.Constants;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.al;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.network.b;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

@ReactModule(hasConstants = false, name = "WebSocketModule")
public class WebSocketModule extends ReactContextBaseJavaModule {
    private b mCookieHandler;
    private ai mReactContext;
    private final Map<Integer, WebSocket> mWebSocketConnections = new ConcurrentHashMap();

    public WebSocketModule(ag context) {
        super(context);
        this.mReactContext = context;
        this.mCookieHandler = new b(context);
    }

    private void sendEvent(String eventName, ar params) {
        ((RCTDeviceEventEmitter) this.mReactContext.a(RCTDeviceEventEmitter.class)).emit(eventName, params);
    }

    public String getName() {
        return "WebSocketModule";
    }

    @ReactMethod
    public void connect(String url, @Nullable al protocols, @Nullable am options, int id) {
        a socketOptions = new a(options);
        OkHttpClient client = new Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).pingInterval(socketOptions.b(), TimeUnit.MILLISECONDS).build();
        Request.Builder builder = new Request.Builder().tag(Integer.valueOf(id)).url(url);
        String cookie = getCookie(url);
        if (cookie != null) {
            builder.addHeader("Cookie", cookie);
        }
        for (Entry<String, String> headerEntry : socketOptions.a().entrySet()) {
            builder.addHeader((String) headerEntry.getKey(), (String) headerEntry.getValue());
        }
        if (!socketOptions.a().containsKey("origin")) {
            builder.addHeader("origin", getDefaultOrigin(url));
        }
        if (protocols != null && protocols.size() > 0) {
            StringBuilder protocolsValue = new StringBuilder("");
            for (int i = 0; i < protocols.size(); i++) {
                String v = protocols.getString(i).trim();
                if (!(v.isEmpty() || v.contains(","))) {
                    protocolsValue.append(v);
                    protocolsValue.append(",");
                }
            }
            if (protocolsValue.length() > 0) {
                protocolsValue.replace(protocolsValue.length() - 1, protocolsValue.length(), "");
                builder.addHeader("Sec-WebSocket-Protocol", protocolsValue.toString());
            }
        }
        final int i2 = id;
        client.newWebSocket(builder.build(), new WebSocketListener(this) {
            final /* synthetic */ WebSocketModule b;

            public final void onOpen(WebSocket webSocket, Response response) {
                this.b.mWebSocketConnections.put(Integer.valueOf(i2), webSocket);
                ar params = new WritableNativeMap();
                params.putInt("id", i2);
                this.b.sendEvent("websocketOpen", params);
            }

            public final void onFailure(WebSocket webSocket, Throwable t, Response response) {
                this.b.notifyWebSocketFailed(i2, t.getMessage());
            }

            public final void onMessage(WebSocket webSocket, f bytes) {
                String text = bytes.a();
                ar params = new WritableNativeMap();
                params.putInt("id", i2);
                params.putString("data", text);
                params.putString("type", "binary");
                this.b.sendEvent("websocketMessage", params);
            }

            public final void onClosed(WebSocket webSocket, int code, String reason) {
                ar params = new WritableNativeMap();
                params.putInt("id", i2);
                params.putInt("code", code);
                params.putString("reason", reason);
                this.b.sendEvent("websocketClosed", params);
            }

            public final void onMessage(WebSocket webSocket, String text) {
                ar params = new WritableNativeMap();
                params.putInt("id", i2);
                params.putString("data", text);
                params.putString("type", "text");
                this.b.sendEvent("websocketMessage", params);
            }
        });
        client.dispatcher().executorService().shutdown();
    }

    @ReactMethod
    public void close(int code, String reason, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client != null) {
            try {
                client.close(code, reason);
                this.mWebSocketConnections.remove(Integer.valueOf(id));
            } catch (Throwable e) {
                FLog.e("React", "Could not close WebSocket connection for id " + id, e);
            }
        }
    }

    @ReactMethod
    public void send(String message, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            ar params = new WritableNativeMap();
            params.putInt("id", id);
            params.putString("message", "client is null");
            sendEvent("websocketFailed", params);
            params = new WritableNativeMap();
            params.putInt("id", id);
            params.putInt("code", 0);
            params.putString("reason", "client is null");
            sendEvent("websocketClosed", params);
            this.mWebSocketConnections.remove(Integer.valueOf(id));
            return;
        }
        try {
            client.send(message);
        } catch (Exception e) {
            notifyWebSocketFailed(id, e.getMessage());
        }
    }

    @ReactMethod
    public void sendBinary(String base64String, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            ar params = new WritableNativeMap();
            params.putInt("id", id);
            params.putString("message", "client is null");
            sendEvent("websocketFailed", params);
            params = new WritableNativeMap();
            params.putInt("id", id);
            params.putInt("code", 0);
            params.putString("reason", "client is null");
            sendEvent("websocketClosed", params);
            this.mWebSocketConnections.remove(Integer.valueOf(id));
            return;
        }
        try {
            client.send(f.b(base64String));
        } catch (Exception e) {
            notifyWebSocketFailed(id, e.getMessage());
        }
    }

    @ReactMethod
    public void ping(int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            ar params = new WritableNativeMap();
            params.putInt("id", id);
            params.putString("message", "client is null");
            sendEvent("websocketFailed", params);
            params = new WritableNativeMap();
            params.putInt("id", id);
            params.putInt("code", 0);
            params.putString("reason", "client is null");
            sendEvent("websocketClosed", params);
            this.mWebSocketConnections.remove(Integer.valueOf(id));
            return;
        }
        try {
            client.send(f.b);
        } catch (Exception e) {
            notifyWebSocketFailed(id, e.getMessage());
        }
    }

    private static String getDefaultOrigin(String uri) {
        try {
            String scheme = "";
            URI requestURI = new URI(uri);
            if (requestURI.getScheme().equals("wss")) {
                scheme = Constants.SCHEME;
            } else if (requestURI.getScheme().equals("ws")) {
                scheme = "http";
            } else if (requestURI.getScheme().equals("http") || requestURI.getScheme().equals(Constants.SCHEME)) {
                scheme = scheme + requestURI.getScheme();
            }
            StringBuilder builder = new StringBuilder(scheme);
            builder.append("://");
            builder.append(requestURI.getHost());
            int port = requestURI.getPort();
            if (port != -1) {
                builder.append(':');
                builder.append(port);
            } else {
                builder.append('/');
            }
            return builder.toString();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to set " + uri + " as default origin header");
        }
    }

    private String getCookie(String uri) {
        try {
            List<String> cookieList = (List) this.mCookieHandler.get(new URI(getDefaultOrigin(uri)), new HashMap()).get("Cookie");
            if (cookieList == null || cookieList.isEmpty()) {
                return null;
            }
            return (String) cookieList.get(0);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to get cookie from " + uri);
        } catch (IOException e2) {
            throw new IllegalArgumentException("Unable to get cookie from " + uri);
        }
    }

    private void notifyWebSocketFailed(int id, String message) {
        ar params = new WritableNativeMap();
        params.putInt("id", id);
        params.putString("message", message);
        sendEvent("websocketFailed", params);
    }
}
