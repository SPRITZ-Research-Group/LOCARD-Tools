package com.microsoft.backgroundexecution;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import java.util.HashMap;
import java.util.Map;

public class BackgroundExecutionModule extends ReactContextBaseJavaModule {
    private static final String DEFAULT_MAX_TIMEOUT_SECONDS_KEY = "defaultMaxTimeoutSeconds";
    private ag context;

    public BackgroundExecutionModule(ag reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    public String getName() {
        return "RNBackgroundExecution";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        String str = DEFAULT_MAX_TIMEOUT_SECONDS_KEY;
        a.a.getClass();
        constants.put(str, Integer.valueOf(180));
        return constants;
    }

    @ReactMethod
    public void requestTime(double maxSeconds, String debugCause, ae promise) {
        Object id = a.a.a(this.context, maxSeconds, debugCause);
        if (id != null) {
            promise.a(id);
            return;
        }
        String error = "request_time_failed";
        promise.a(error, error);
    }

    @ReactMethod
    public void requestMaxTime(String debugCause, ae promise) {
        Object id = a.a.a(this.context, debugCause);
        if (id != null) {
            promise.a(id);
            return;
        }
        String error = "request_max_time_failed";
        promise.a(error, error);
    }

    @ReactMethod
    public void release(String id, String debugCause, ae promise) {
        a.a.a(id, debugCause);
        promise.a(null);
    }

    @ReactMethod
    public void releaseAll(String debugCause, ae promise) {
        a.a.a(debugCause);
        promise.a(null);
    }
}
