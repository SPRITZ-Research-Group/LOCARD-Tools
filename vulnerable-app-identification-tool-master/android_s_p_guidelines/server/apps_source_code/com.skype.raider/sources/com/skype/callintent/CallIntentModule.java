package com.skype.callintent;

import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.HashMap;
import java.util.Map;

public class CallIntentModule extends ReactContextBaseJavaModule implements CallIntentConstants {
    private static final String RN_CLASS = "CallIntent";
    private volatile boolean initialized = false;
    private Intent preInitializationIntent;
    private final ag reactContext;

    public CallIntentModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return RN_CLASS;
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = new HashMap();
        constants.put("CallOrDialIntentEventName", "callOrDialIntentEvent");
        return constants;
    }

    @ReactMethod
    public void startIntentProcessing() {
        this.initialized = true;
        if (this.preInitializationIntent != null) {
            processIntent(this.preInitializationIntent);
            this.preInitializationIntent = null;
        }
    }

    public void processIntent(Intent intent) {
        if (this.initialized) {
            Uri uri = intent.getData();
            String action = intent.getAction();
            ar data = new WritableNativeMap();
            if ("android.intent.action.CALL".equals(action)) {
                data.putString("type", "call");
                data.putString("number", uri.toString());
            } else if ("android.intent.action.DIAL".equals(action)) {
                data.putString("type", "dial");
                data.putString("number", uri.toString());
            } else if ("android.intent.action.VIEW".equals(action)) {
                data.putString("type", intent.getStringExtra("type"));
                data.putString("number", intent.getStringExtra("number"));
                data.putString("skypeId", intent.getStringExtra("skypeId"));
            }
            ((RCTDeviceEventEmitter) this.reactContext.a(RCTDeviceEventEmitter.class)).emit("callOrDialIntentEvent", data);
            return;
        }
        this.preInitializationIntent = intent;
    }
}
