package com.customkeyboard;

import android.app.Activity;
import android.content.Context;
import com.customkeyboard.b.a;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.v;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.util.WeakHashMap;

public class CustomKeyboard extends ReactContextBaseJavaModule implements a, v {
    private boolean canHideKeyboardOnAndroidBackButton = true;
    private WeakHashMap<Context, b> keyboardContexts = new WeakHashMap();
    private final c metrics;

    public CustomKeyboard(ag context) {
        super(context);
        context.a((v) this);
        this.metrics = new c();
    }

    public String getName() {
        return "CustomKeyboard";
    }

    @ReactMethod
    public void show(String keyboardName) {
        b keyboardActivityContext = (b) this.keyboardContexts.get(getCurrentActivity());
        if (keyboardActivityContext != null) {
            keyboardActivityContext.b(keyboardName);
        }
    }

    @ReactMethod
    public void dismissCustomKeyboards() {
        b keyboardActivityContext = (b) this.keyboardContexts.get(getCurrentActivity());
        if (keyboardActivityContext != null) {
            keyboardActivityContext.b();
        }
    }

    @ReactMethod
    public void showNativeKeyboard() {
        b keyboardActivityContext = (b) this.keyboardContexts.get(getCurrentActivity());
        if (keyboardActivityContext != null) {
            keyboardActivityContext.a();
        }
    }

    @ReactMethod
    public void onCustomKeyboardSeen(double height) {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("customKeyboardSeen", Double.valueOf(height));
    }

    @ReactMethod
    public void onCustomKeyboardHidden() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit("customKeyboardHidden", null);
    }

    @ReactMethod
    public void removeCustomKeyboard(String keyboardName) {
        b keyboardActivityContext = (b) this.keyboardContexts.get(getCurrentActivity());
        if (keyboardActivityContext != null) {
            keyboardActivityContext.a(keyboardName);
        }
    }

    @ReactMethod
    public void hideKeyboardOnAndroidBackButton(boolean canHide) {
        this.canHideKeyboardOnAndroidBackButton = canHide;
    }

    public boolean canHideKeyboardOnAndroidBackButton() {
        return this.canHideKeyboardOnAndroidBackButton;
    }

    public boolean isDisplayingCustomKeyboard() {
        b keyboardActivityContext = (b) this.keyboardContexts.get(getCurrentActivity());
        if (keyboardActivityContext != null) {
            return keyboardActivityContext.c();
        }
        return false;
    }

    public void registerCustomKeyboard(String name, CustomKeyboardView view) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            b keyboardActivityContext = (b) this.keyboardContexts.get(currentActivity);
            if (keyboardActivityContext == null) {
                keyboardActivityContext = new b(currentActivity, this.metrics, this);
                this.keyboardContexts.put(currentActivity, keyboardActivityContext);
            }
            keyboardActivityContext.a(name, view);
        }
    }

    public void onConfigurationChanged() {
    }

    public void onHostResume() {
    }

    public void onHostPause() {
        dismissCustomKeyboards();
    }

    public void onHostDestroy() {
        dismissCustomKeyboards();
    }

    public void contextTerminated(Activity activity, b context) {
        this.keyboardContexts.remove(activity);
    }
}
