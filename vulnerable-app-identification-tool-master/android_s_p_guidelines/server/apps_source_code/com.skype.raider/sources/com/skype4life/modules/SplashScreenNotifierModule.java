package com.skype4life.modules;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.facebook.react.module.annotations.ReactModule;
import com.skype4life.MainActivity;

@ReactModule(name = "SplashScreenNotifier")
public class SplashScreenNotifierModule extends ReactContextBaseJavaModule {
    public SplashScreenNotifierModule(ag reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void onSplashScreenClosed() {
        MainActivity activity = (MainActivity) getCurrentActivity();
        if (activity != null) {
            activity.d();
        }
    }

    public String getName() {
        return "SplashScreenNotifier";
    }
}
