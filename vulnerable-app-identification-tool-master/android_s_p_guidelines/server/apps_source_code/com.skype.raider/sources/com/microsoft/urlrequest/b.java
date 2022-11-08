package com.microsoft.urlrequest;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.List;

public final class b implements q {
    private RNUrlRequestNativeModule a;

    public final List<NativeModule> a(ag reactApplicationContext) {
        List<NativeModule> nativeModules = new ArrayList();
        this.a = new RNUrlRequestNativeModule(reactApplicationContext);
        nativeModules.add(this.a);
        return nativeModules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return new ArrayList();
    }

    public final List<ViewManager> b(ag reactApplicationContext) {
        return new ArrayList();
    }
}
