package com.devfd.RNGeocoder;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class a implements q {
    public final List<NativeModule> a(ag reactContext) {
        return Arrays.asList(new NativeModule[]{new RNGeocoderModule(reactContext)});
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
