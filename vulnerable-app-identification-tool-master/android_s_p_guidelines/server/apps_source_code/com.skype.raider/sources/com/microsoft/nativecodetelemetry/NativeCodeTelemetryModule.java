package com.microsoft.nativecodetelemetry;

import android.util.Pair;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ai;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.ArrayList;
import java.util.Iterator;

public class NativeCodeTelemetryModule extends ReactContextBaseJavaModule {
    private static boolean isReady = false;
    private static ai lastReactContext = null;
    private static ArrayList<Pair<String, ar>> mCache = new ArrayList();

    public static synchronized void sendEvent(String name, ar params) {
        synchronized (NativeCodeTelemetryModule.class) {
            if (!isReady || lastReactContext == null) {
                mCache.add(new Pair(name, params));
            } else {
                sendEventInternal(name, params);
            }
        }
    }

    public NativeCodeTelemetryModule(ag reactContext) {
        super(reactContext);
        lastReactContext = reactContext;
    }

    public String getName() {
        return "NativeCodeTelemetry";
    }

    @ReactMethod
    public synchronized void setReady() {
        isReady = true;
        replayCache();
    }

    private void replayCache() {
        Iterator it = mCache.iterator();
        while (it.hasNext()) {
            Pair<String, ar> item = (Pair) it.next();
            sendEventInternal(item.first, item.second);
        }
        mCache.clear();
    }

    public static void sendEventInternal(String name, ar actionsMap) {
        ar params = new WritableNativeMap();
        params.putString("name", name);
        params.putMap("actions", actionsMap);
        ((RCTDeviceEventEmitter) lastReactContext.a(RCTDeviceEventEmitter.class)).emit("s4l.report_telemetry_event", params);
    }
}
