package com.facebook.react;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;

public interface q {
    List<Class<? extends JavaScriptModule>> a();

    List<NativeModule> a(ag agVar);

    List<ViewManager> b(ag agVar);
}
