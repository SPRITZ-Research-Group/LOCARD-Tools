package com.skype.brazemanager;

import android.app.Application;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BrazeManagerPackage implements q {
    private final Application a;

    public BrazeManagerPackage(Application application) {
        this.a = application;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }

    public final List<NativeModule> a(ag reactContext) {
        return Arrays.asList(new NativeModule[]{new BrazeManagerModule(this.a, reactContext)});
    }
}
