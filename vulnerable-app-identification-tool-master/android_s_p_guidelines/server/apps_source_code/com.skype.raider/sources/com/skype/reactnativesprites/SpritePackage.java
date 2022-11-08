package com.skype.reactnativesprites;

import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.j;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpritePackage implements q {
    public final List<NativeModule> a(ag reactContext) {
        return Collections.emptyList();
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        j.a((Context) reactContext);
        return Arrays.asList(new ViewManager[]{new SpriteViewManager(Fresco.b())});
    }
}
