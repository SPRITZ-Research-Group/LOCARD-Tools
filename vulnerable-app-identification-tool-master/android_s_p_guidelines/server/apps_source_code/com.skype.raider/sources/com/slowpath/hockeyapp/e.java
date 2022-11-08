package com.slowpath.hockeyapp;

import android.app.Application;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.skype.hockeyapp.SkypeCrashManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class e implements q {
    public e(c logProvider) {
        SkypeCrashManager.c().a(logProvider);
    }

    public final List<NativeModule> a(ag reactContext) {
        f.a((Application) reactContext.getApplicationContext());
        List<NativeModule> modules = new ArrayList();
        modules.add(new RNHockeyAppModule(reactContext));
        return modules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
