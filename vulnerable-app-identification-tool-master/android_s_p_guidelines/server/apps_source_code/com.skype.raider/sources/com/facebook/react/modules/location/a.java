package com.facebook.react.modules.location;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;

public final class a {
    public static int a = 1;
    public static int b = 2;
    public static int c = 3;

    public static ar a(int code, String message) {
        ar error = new WritableNativeMap();
        error.putInt("code", code);
        if (message != null) {
            error.putString("message", message);
        }
        return error;
    }
}
