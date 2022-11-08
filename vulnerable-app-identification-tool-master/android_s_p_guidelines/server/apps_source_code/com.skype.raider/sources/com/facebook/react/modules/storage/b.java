package com.facebook.react.modules.storage;

import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ar;

public final class b {
    static ar a(String errorMessage) {
        ar errorMap = new WritableNativeMap();
        errorMap.putString("message", errorMessage);
        return errorMap;
    }
}
