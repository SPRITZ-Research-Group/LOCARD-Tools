package com.skype.qrcode;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QRCodePackage implements q {
    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Arrays.asList(new ViewManager[]{new QRCodeViewManager()});
    }

    public final List<NativeModule> a(ag reactContext) {
        return Collections.emptyList();
    }
}
