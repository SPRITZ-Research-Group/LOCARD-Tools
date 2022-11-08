package com.microsoft.skype.devicesettings;

import android.content.Intent;
import android.os.Build.VERSION;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ag;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class DeviceSettingsModule extends ReactContextBaseJavaModule {
    private ag reactContext;

    public DeviceSettingsModule(ag reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return "DeviceSettings";
    }

    @ReactMethod
    public void openAppNotificationsSettings() {
        Intent intent = new Intent("android.settings.APP_NOTIFICATION_SETTINGS");
        if (VERSION.SDK_INT >= 26) {
            intent.putExtra("android.provider.extra.APP_PACKAGE", this.reactContext.getPackageName());
        } else {
            intent.putExtra("app_package", this.reactContext.getPackageName());
            intent.putExtra("app_uid", this.reactContext.getApplicationInfo().uid);
        }
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        this.reactContext.startActivity(intent);
    }
}
