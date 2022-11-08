package com.github.alinz.reactnativewebviewbridge;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class d implements q {
    public final List<NativeModule> a(ag reactApplicationContext) {
        return new ArrayList();
    }

    public final List<ViewManager> b(ag reactApplicationContext) {
        return Arrays.asList(new ViewManager[]{new WebViewBridgeManager()});
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Arrays.asList(new Class[0]);
    }
}
