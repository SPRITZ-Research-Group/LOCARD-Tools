package com.skype4life.modules;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class b implements q {
    private final com.skype4life.b a;

    public b(com.skype4life.b appLogsProvider) {
        this.a = appLogsProvider;
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new ClientLoggingModule(reactContext, this.a));
        modules.add(new ExtendedAppStateModule(reactContext));
        modules.add(new SplashScreenNotifierModule(reactContext));
        modules.add(new ZipUtil(reactContext));
        modules.add(new SyncModule(reactContext));
        return modules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
