package com.skype.urlutil;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlUtilModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "UrlUtil";
    private static final String TAG = "UrlUtil";

    public UrlUtilModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "UrlUtil";
    }

    @ReactMethod
    public void getUriComponents(String uri, ae promise) {
        try {
            promise.a(getUriComponents(uri));
        } catch (Throwable e) {
            FLog.e("UrlUtil", "Failed to parse uri. Error: ", e);
            promise.a(e);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public ar syncGetUriComponents(String uri) {
        ar map = new WritableNativeMap();
        try {
            return getUriComponents(uri);
        } catch (Throwable e) {
            FLog.e("UrlUtil", "Failed to parse uri. Error: ", e);
            return map;
        }
    }

    private ar getUriComponents(String uriString) throws URISyntaxException {
        ar map = new WritableNativeMap();
        if (uriString != null) {
            URI uri = new URI(uriString);
            map.putString("protocol", uri.getScheme());
            map.putString("userName", uri.getRawUserInfo());
            map.putString("host", uri.getHost());
            int portNumber = uri.getPort();
            map.putString("port", portNumber != -1 ? Integer.toString(portNumber) : "");
            if (uri.isOpaque()) {
                String schemeSpecificPart = uri.getRawSchemeSpecificPart();
                if (!TextUtils.isEmpty(schemeSpecificPart)) {
                    int queryIndex = schemeSpecificPart.indexOf("?");
                    if (queryIndex != -1) {
                        map.putString("path", schemeSpecificPart.substring(0, queryIndex));
                        map.putString("query", schemeSpecificPart.substring(queryIndex + 1));
                    } else {
                        map.putString("path", schemeSpecificPart);
                    }
                }
            } else {
                String path = uri.getRawPath();
                if (!TextUtils.isEmpty(path)) {
                    map.putString("path", path);
                }
                String query = uri.getRawQuery();
                if (!TextUtils.isEmpty(query)) {
                    map.putString("query", query);
                }
            }
            map.putString("fragment", uri.getRawFragment());
        }
        return map;
    }
}
