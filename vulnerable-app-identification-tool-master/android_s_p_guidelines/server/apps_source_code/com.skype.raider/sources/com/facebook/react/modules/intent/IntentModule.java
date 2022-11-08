package com.facebook.react.modules.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.n;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

@ReactModule(name = "IntentAndroid")
public class IntentModule extends ReactContextBaseJavaModule {
    public IntentModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "IntentAndroid";
    }

    @ReactMethod
    public void getInitialURL(ae promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            Object initialURL = null;
            if (currentActivity != null) {
                Intent intent = currentActivity.getIntent();
                String action = intent.getAction();
                Uri uri = intent.getData();
                if ("android.intent.action.VIEW".equals(action) && uri != null) {
                    initialURL = uri.toString();
                }
            }
            promise.a(initialURL);
        } catch (Exception e) {
            promise.a(new n("Could not get the initial URL : " + e.getMessage()));
        }
    }

    @ReactMethod
    public void openURL(String url, ae promise) {
        if (url == null || url.isEmpty()) {
            promise.a(new n("Invalid URL: " + url));
            return;
        }
        try {
            Activity currentActivity = getCurrentActivity();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url).normalizeScheme());
            String selfPackageName = getReactApplicationContext().getPackageName();
            ComponentName componentName = intent.resolveActivity(getReactApplicationContext().getPackageManager());
            String otherPackageName = componentName != null ? componentName.getPackageName() : "";
            if (currentActivity == null || !selfPackageName.equals(otherPackageName)) {
                intent.addFlags(ErrorDialogData.BINDER_CRASH);
            }
            if (currentActivity != null) {
                currentActivity.startActivity(intent);
            } else {
                getReactApplicationContext().startActivity(intent);
            }
            promise.a(Boolean.valueOf(true));
        } catch (Exception e) {
            promise.a(new n("Could not open URL '" + url + "': " + e.getMessage()));
        }
    }

    @ReactMethod
    public void canOpenURL(String url, ae promise) {
        if (url == null || url.isEmpty()) {
            promise.a(new n("Invalid URL: " + url));
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url).normalizeScheme());
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            promise.a(Boolean.valueOf(intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null));
        } catch (Exception e) {
            promise.a(new n("Could not check if URL '" + url + "' can be opened: " + e.getMessage()));
        }
    }
}
