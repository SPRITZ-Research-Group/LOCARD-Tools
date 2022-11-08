package com.facebook.react.modules.accessibilityinfo;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.d;
import com.facebook.react.bridge.v;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import javax.annotation.Nullable;

@ReactModule(name = "AccessibilityInfo")
public class AccessibilityInfoModule extends ReactContextBaseJavaModule implements v {
    private static final String EVENT_NAME = "touchExplorationDidChange";
    @Nullable
    private AccessibilityManager mAccessibilityManager;
    private boolean mEnabled = false;
    @Nullable
    private a mTouchExplorationStateChangeListener;

    @TargetApi(19)
    private class a implements TouchExplorationStateChangeListener {
        final /* synthetic */ AccessibilityInfoModule a;

        private a(AccessibilityInfoModule accessibilityInfoModule) {
            this.a = accessibilityInfoModule;
        }

        /* synthetic */ a(AccessibilityInfoModule x0, byte b) {
            this(x0);
        }

        public final void onTouchExplorationStateChanged(boolean enabled) {
            this.a.updateAndSendChangeEvent(enabled);
        }
    }

    public AccessibilityInfoModule(ag context) {
        super(context);
        this.mAccessibilityManager = (AccessibilityManager) context.getApplicationContext().getSystemService("accessibility");
        this.mEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
        if (VERSION.SDK_INT >= 19) {
            this.mTouchExplorationStateChangeListener = new a();
        }
    }

    public String getName() {
        return "AccessibilityInfo";
    }

    @ReactMethod
    public void isTouchExplorationEnabled(d successCallback) {
        successCallback.invoke(Boolean.valueOf(this.mEnabled));
    }

    private void updateAndSendChangeEvent(boolean enabled) {
        if (this.mEnabled != enabled) {
            this.mEnabled = enabled;
            ((RCTDeviceEventEmitter) getReactApplicationContext().a(RCTDeviceEventEmitter.class)).emit(EVENT_NAME, Boolean.valueOf(this.mEnabled));
        }
    }

    public void onHostResume() {
        if (VERSION.SDK_INT >= 19) {
            this.mAccessibilityManager.addTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        }
        updateAndSendChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
    }

    public void onHostPause() {
        if (VERSION.SDK_INT >= 19) {
            this.mAccessibilityManager.removeTouchExplorationStateChangeListener(this.mTouchExplorationStateChangeListener);
        }
    }

    public void initialize() {
        getReactApplicationContext().a((v) this);
        updateAndSendChangeEvent(this.mAccessibilityManager.isTouchExplorationEnabled());
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        getReactApplicationContext().b((v) this);
    }

    public void onHostDestroy() {
    }
}
