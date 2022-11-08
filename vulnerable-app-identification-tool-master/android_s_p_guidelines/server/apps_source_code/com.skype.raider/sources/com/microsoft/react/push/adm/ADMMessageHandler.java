package com.microsoft.react.push.adm;

import android.content.Intent;
import android.os.Bundle;
import com.amazon.device.messaging.ADMMessageHandlerBase;
import com.facebook.common.logging.FLog;
import com.microsoft.react.push.PushReceiver;
import com.microsoft.react.push.f;
import java.util.UUID;

public class ADMMessageHandler extends ADMMessageHandlerBase {
    private static String TAG = "ADMMessageHandler";

    public ADMMessageHandler() {
        super(ADMMessageHandler.class.getName());
    }

    protected void onMessage(Intent intent) {
        if (intent == null) {
            FLog.i(TAG, "onMessage() [WARN] null intent");
            return;
        }
        Bundle data = intent.getExtras();
        if (data == null) {
            FLog.i(TAG, "onMessage() [WARN] null bundle");
            return;
        }
        FLog.i(TAG, "onMessage()");
        for (String key : data.keySet()) {
            Object value = data.get(key);
            FLog.d(TAG, String.format("%s %s (%s)", new Object[]{key, value.toString(), value.getClass().getName()}));
        }
        data.putLong("receivedTime", System.currentTimeMillis());
        data.putString("correlationId", UUID.randomUUID().toString());
        Intent result = new Intent("com.microsoft.react.push.PushConstants.ACTION_MESSAGE_RECEIVED");
        result.addFlags(32);
        result.setClass(this, PushReceiver.class);
        result.putExtras(data);
        result.putExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", f.a(getApplicationContext()));
        sendBroadcast(result);
    }

    protected void onRegistered(String registrationId) {
        Intent result = new Intent("com.microsoft.react.push.PushConstants.ACTION_REGISTER");
        result.setClass(this, PushReceiver.class);
        result.putExtra("com.microsoft.react.push.PushConstants.extra.token", registrationId);
        sendBroadcast(result);
        FLog.i(TAG, "onRegistered() Registration successful. registrationId: " + registrationId);
    }

    protected void onRegistrationError(String error) {
        FLog.i(TAG, "onRegistrationError() error: " + error);
    }

    protected void onUnregistered(String registrationId) {
        Intent result = new Intent("com.microsoft.react.push.PushConstants.ACTION_UNREGISTER");
        result.setClass(this, PushReceiver.class);
        sendBroadcast(result);
        FLog.i(TAG, "onUnregistered() successful");
    }
}
