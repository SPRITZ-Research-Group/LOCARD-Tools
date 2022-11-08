package com.skype.brazemanager;

import android.app.Application;
import com.appboy.a;
import com.appboy.c;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.common.e;
import java.util.Map;
import org.json.JSONObject;

public class BrazeManagerModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "BrazeManager";
    private c appboyLifecycleCallbackListener;
    private final Application application;
    private boolean isInitialized = false;

    public BrazeManagerModule(Application application, ag reactContext) {
        super(reactContext);
        this.application = application;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public Map<String, Object> getConstants() {
        return e.a().a();
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.application.unregisterActivityLifecycleCallbacks(this.appboyLifecycleCallbackListener);
        this.appboyLifecycleCallbackListener = null;
    }

    @ReactMethod
    public void initialize(ae promise) {
        FLog.i("NativeBrazeManager", "SDK initialize.");
        if (this.isInitialized) {
            FLog.i("NativeBrazeManager", "SDK initialize.");
            promise.a(null);
            return;
        }
        FLog.i("NativeBrazeManager", "Registering activity lifecycle callback.");
        this.appboyLifecycleCallbackListener = new c();
        this.application.registerActivityLifecycleCallbacks(this.appboyLifecycleCallbackListener);
        this.isInitialized = true;
        promise.a(null);
    }

    @ReactMethod
    public void wipeDataAndDisable(ae promise) {
        FLog.i("NativeBrazeManager", "Wipe data and disable SDK.");
        a.c(getReactApplicationContext());
        a.b(getReactApplicationContext());
        promise.a(null);
    }

    @ReactMethod
    public void changeUser(String userId, ae promise) {
        FLog.i("NativeBrazeManager", "ChangeUser with value %s", (Object) userId);
        a.a(getReactApplicationContext()).c(userId);
        promise.a(null);
    }

    @ReactMethod
    public void registerPushToken(String token, ae promise) {
        FLog.i("NativeBrazeManager", "RegisterPushToken with value %s", (Object) token);
        a.a(getReactApplicationContext()).d(token);
    }

    @ReactMethod
    public void requestImmediateDataFlush(ae promise) {
        FLog.i("NativeBrazeManager", "RequestImmediateDataFlush.");
        a.a(getReactApplicationContext()).e();
        promise.a(null);
    }

    @ReactMethod
    public void setFirstName(String firstName) {
        FLog.i("NativeBrazeManager", "SetFirstName.");
        a.a(getReactApplicationContext()).f().a(firstName);
    }

    @ReactMethod
    public void logCustomEvent(String eventName, am eventProperties) {
        FLog.i("NativeBrazeManager", "LogCustomEvent with eventName %s", (Object) eventName);
        a.a(getReactApplicationContext()).a(eventName, populateEventPropertiesFromReadableMap(eventProperties));
    }

    @ReactMethod
    public void getInitialUrl(ae promise) {
        FLog.i("NativeBrazeManager", "GetInitialUrl");
        promise.a(null);
    }

    @ReactMethod
    public void setBoolCustomUserAttribute(String key, Boolean value) {
        FLog.i("NativeBrazeManager", "SetCustomAttributeWithKey %s", (Object) key);
        a.a(getReactApplicationContext()).f().a(key, value.booleanValue());
    }

    private com.appboy.e.b.a populateEventPropertiesFromReadableMap(am eventProperties) {
        com.appboy.e.b.a properties = new com.appboy.e.b.a();
        ReadableMapKeySetIterator keySetIterator = eventProperties.keySetIterator();
        if (eventProperties != JSONObject.NULL) {
            while (keySetIterator.hasNextKey()) {
                String key = keySetIterator.nextKey();
                ReadableType readableType = eventProperties.getType(key);
                if (readableType == ReadableType.String) {
                    properties.a(key, eventProperties.getString(key));
                } else if (readableType == ReadableType.Boolean) {
                    properties.a(key, eventProperties.getBoolean(key));
                } else if (readableType == ReadableType.Number) {
                    try {
                        properties.a(key, eventProperties.getDouble(key));
                    } catch (Exception e) {
                        try {
                            properties.a(key, eventProperties.getInt(key));
                        } catch (Exception e2) {
                            FLog.e("NativeBrazeManager", "Could not parse ReadableType.Number from ReadableMap");
                        }
                    }
                } else {
                    FLog.e("NativeBrazeManager", "Could not map ReadableType to an AppboyProperty value");
                }
            }
        }
        return properties;
    }
}
