package com.microsoft.react.push.adm;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.device.messaging.ADM;
import com.amazon.device.messaging.development.ADMManifest;
import com.facebook.common.logging.FLog;
import com.microsoft.react.push.PushReceiver;

public class ADMPushRegistration {
    private static final String ADM_CLASS = "com.amazon.device.messaging.ADM";
    private static final String TAG = "ADMPushRegistration";
    private static volatile ADMPushRegistration instance;
    private ADM adm;

    private ADMPushRegistration() {
    }

    public static ADMPushRegistration getInstance() {
        if (instance == null) {
            instance = new ADMPushRegistration();
        }
        return instance;
    }

    public boolean isSupported(Context context) {
        try {
            Class.forName(ADM_CLASS);
            try {
                if (this.adm == null) {
                    this.adm = new ADM(context);
                }
                FLog.i(TAG, "isSupported adm.isSupported():" + this.adm.isSupported());
                return this.adm.isSupported();
            } catch (Exception ignore) {
                FLog.i(TAG, "isSupported adm.isSupported(): err:" + ignore.toString());
                return false;
            }
        } catch (Exception ignore2) {
            FLog.i(TAG, "isSupported Class.forName(ADM_CLASS): err:" + ignore2.toString());
            return false;
        }
    }

    public void register(Context context) {
        if (isSupported(context)) {
            ADMManifest.checkManifestAuthoredProperly(context);
            String registrationId = this.adm.getRegistrationId();
            if (registrationId == null) {
                this.adm.startRegister();
                return;
            }
            FLog.i(TAG, "register() already registered");
            Intent result = new Intent("com.microsoft.react.push.PushConstants.ACTION_REGISTER");
            result.setClass(context, PushReceiver.class);
            result.putExtra("com.microsoft.react.push.PushConstants.extra.token", registrationId);
            context.sendBroadcast(result);
        }
    }

    public void unregister(Context context) {
        if (isRegistered(context)) {
            this.adm.startUnregister();
            return;
        }
        FLog.i(TAG, "unregister() already unregistered");
        Intent result = new Intent("com.microsoft.react.push.PushConstants.ACTION_UNREGISTER");
        result.setClass(context, PushReceiver.class);
        context.sendBroadcast(result);
    }

    private boolean isRegistered(Context context) {
        return (!isSupported(context) || this.adm == null || TextUtils.isEmpty(this.adm.getRegistrationId())) ? false : true;
    }

    public String getRegistrationToken(Context context) {
        if (isSupported(context)) {
            return this.adm.getRegistrationId();
        }
        return null;
    }
}
