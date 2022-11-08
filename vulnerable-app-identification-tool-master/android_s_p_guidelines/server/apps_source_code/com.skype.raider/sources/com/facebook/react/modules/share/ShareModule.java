package com.facebook.react.modules.share;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.am;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "ShareModule")
public class ShareModule extends ReactContextBaseJavaModule {
    static final String ACTION_SHARED = "sharedAction";
    static final String ERROR_INVALID_CONTENT = "E_INVALID_CONTENT";
    static final String ERROR_UNABLE_TO_OPEN_DIALOG = "E_UNABLE_TO_OPEN_DIALOG";

    public ShareModule(ag reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ShareModule";
    }

    @ReactMethod
    public void share(am content, String dialogTitle, ae promise) {
        if (content == null) {
            promise.a(ERROR_INVALID_CONTENT, "Content cannot be null");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setTypeAndNormalize("text/plain");
            if (content.hasKey("title")) {
                intent.putExtra("android.intent.extra.SUBJECT", content.getString("title"));
            }
            if (content.hasKey("message")) {
                intent.putExtra("android.intent.extra.TEXT", content.getString("message"));
            }
            Intent chooser = Intent.createChooser(intent, dialogTitle);
            chooser.addCategory("android.intent.category.DEFAULT");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivity(chooser);
            } else {
                getReactApplicationContext().startActivity(chooser);
            }
            Object result = new WritableNativeMap();
            result.putString("action", ACTION_SHARED);
            promise.a(result);
        } catch (Exception e) {
            promise.a(ERROR_UNABLE_TO_OPEN_DIALOG, "Failed to open share dialog");
        }
    }
}
