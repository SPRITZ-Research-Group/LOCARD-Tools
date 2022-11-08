package com.psykar.cookiemanager;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.d;
import com.facebook.react.modules.network.b;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieManagerModule extends ReactContextBaseJavaModule {
    private b cookieHandler;

    public CookieManagerModule(ag context) {
        super(context);
        this.cookieHandler = new b(context);
    }

    public String getName() {
        return "RNCookieManagerAndroid";
    }

    @ReactMethod
    public void set(am cookie, d callback) throws Exception {
        throw new Exception("Cannot call on android, try setFromResponse");
    }

    @ReactMethod
    public void setFromResponse(String url, String value, d callback) throws URISyntaxException, IOException {
        Map headers = new HashMap();
        headers.put("Set-cookie", Collections.singletonList(value));
        this.cookieHandler.put(new URI(url), headers);
        callback.invoke(null, null);
    }

    @ReactMethod
    public void getAll(d callback) throws Exception {
        throw new Exception("Cannot get all cookies on android, try getCookieHeader(url)");
    }

    @ReactMethod
    public void get(String url, d callback) throws URISyntaxException, IOException {
        List<String> cookieList = (List) this.cookieHandler.get(new URI(url), new HashMap()).get("Cookie");
        ar map = new WritableNativeMap();
        if (cookieList != null) {
            String[] cookies = ((String) cookieList.get(0)).split(";");
            for (String split : cookies) {
                String[] cookie = split.split("=", 2);
                map.putString(cookie[0], cookie[1]);
            }
        }
        callback.invoke(null, map);
    }

    @ReactMethod
    public void clearAll(final d callback) {
        this.cookieHandler.a(new d(this) {
            final /* synthetic */ CookieManagerModule b;

            public final void invoke(Object... args) {
                callback.invoke(null, null);
            }
        });
    }
}
