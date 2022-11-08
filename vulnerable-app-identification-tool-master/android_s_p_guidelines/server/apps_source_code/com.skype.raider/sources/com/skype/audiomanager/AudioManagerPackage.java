package com.skype.audiomanager;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.skype.slimcore.skylib.SkyLibProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AudioManagerPackage implements q {
    private WeakReference<SkyLibProvider> a;

    public AudioManagerPackage(WeakReference<SkyLibProvider> skyLibProvider) {
        this.a = skyLibProvider;
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new AudioManagerModule(reactContext, this.a));
        return modules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
