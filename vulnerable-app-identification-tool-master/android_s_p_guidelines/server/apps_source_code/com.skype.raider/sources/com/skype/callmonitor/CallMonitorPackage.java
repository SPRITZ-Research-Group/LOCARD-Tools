package com.skype.callmonitor;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.skype.slimcore.video.VideoViewManagerProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallMonitorPackage implements q {
    private WeakReference<VideoViewManagerProvider> a;

    public CallMonitorPackage(WeakReference<VideoViewManagerProvider> videoViewManagerProvider) {
        this.a = videoViewManagerProvider;
    }

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new CallMonitorModule(reactContext, this.a));
        return modules;
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }
}
