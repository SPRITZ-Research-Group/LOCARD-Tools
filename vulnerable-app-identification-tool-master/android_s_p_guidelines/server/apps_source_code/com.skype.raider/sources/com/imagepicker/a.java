package com.imagepicker;

import android.support.annotation.StyleRes;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ag;
import com.facebook.react.q;
import com.facebook.react.uimanager.ViewManager;
import com.imagepicker.b.b;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class a implements q {
    public static final int a = b.DefaultExplainingPermissionsTheme;
    @StyleRes
    private final int b = a;

    public final List<NativeModule> a(ag reactContext) {
        return Arrays.asList(new NativeModule[]{new ImagePickerModule(reactContext, this.b)});
    }

    public final List<Class<? extends JavaScriptModule>> a() {
        return Collections.emptyList();
    }

    public final List<ViewManager> b(ag reactContext) {
        return Collections.emptyList();
    }
}
