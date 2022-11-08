package com.microsoft.react.viewconfig;

import android.view.ViewConfiguration;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.uimanager.p;

public class NativeViewConfigurationModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNViewConfig";

    public NativeViewConfigurationModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void getPagingTouchSlopAsync(ae promise) {
        try {
            promise.a(Float.valueOf(p.b((float) ViewConfiguration.get(getReactApplicationContext()).getScaledPagingTouchSlop())));
        } catch (Throwable t) {
            promise.a(t);
        }
    }

    @ReactMethod
    public void getTouchSlopAsync(ae promise) {
        try {
            promise.a(Float.valueOf(p.b((float) ViewConfiguration.get(getReactApplicationContext()).getScaledTouchSlop())));
        } catch (Throwable t) {
            promise.a(t);
        }
    }
}
