package com.facebook.react.modules.core;

import android.net.Uri;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ap;
import com.facebook.react.bridge.ar;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;
import net.hockeyapp.android.j;

@ReactModule(name = "DeviceEventManager")
public class DeviceEventManagerModule extends ReactContextBaseJavaModule {
    private final Runnable mInvokeDefaultBackPressRunnable;

    public interface RCTDeviceEventEmitter extends JavaScriptModule {
        void emit(String str, @Nullable Object obj);
    }

    public DeviceEventManagerModule(ag reactContext, final b backBtnHandler) {
        super(reactContext);
        this.mInvokeDefaultBackPressRunnable = new Runnable(this) {
            final /* synthetic */ DeviceEventManagerModule b;

            public final void run() {
                ap.b();
                backBtnHandler.c();
            }
        };
    }

    public void emitHardwareBackPressed() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("hardwareBackPress", null);
    }

    @ReactMethod
    public void invokeDefaultBackPressHandler() {
        getReactApplicationContext().a(this.mInvokeDefaultBackPressRunnable);
    }

    public String getName() {
        return "DeviceEventManager";
    }

    public void emitNewIntentReceived(Uri uri) {
        ar map = new WritableNativeMap();
        map.putString(j.FRAGMENT_URL, uri.toString());
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(j.FRAGMENT_URL, map);
    }
}
