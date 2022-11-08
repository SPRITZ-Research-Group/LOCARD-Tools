package com.microsoft.react.videofxp;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.microsoft.react.videofxp.VideoFXPModule.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class l implements q, a {
    private VideoFXPViewManager a;

    public final List<NativeModule> a(ag reactContext) {
        List<NativeModule> modules = new ArrayList();
        modules.add(new VideoFXPModule(reactContext, this));
        return modules;
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        this.a = new VideoFXPViewManager();
        return Arrays.asList(new ViewManager[]{this.a});
    }

    public final VideoFXPView getView() {
        if (this.a != null) {
            return this.a.getView();
        }
        return null;
    }
}
