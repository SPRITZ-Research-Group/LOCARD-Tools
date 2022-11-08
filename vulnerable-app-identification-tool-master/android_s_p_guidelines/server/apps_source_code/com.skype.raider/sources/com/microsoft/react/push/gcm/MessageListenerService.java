package com.microsoft.react.push.gcm;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.common.logging.FLog;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.microsoft.react.push.PushReceiver;
import com.microsoft.react.push.f;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class MessageListenerService extends FirebaseMessagingService {
    private static final String b = MessageListenerService.class.getSimpleName();

    public final synchronized void a(RemoteMessage message) {
        FLog.i(b, "onMessageReceived");
        Map a = message.a();
        Bundle data = new Bundle();
        for (Entry entry : a.entrySet()) {
            data.putString((String) entry.getKey(), (String) entry.getValue());
        }
        data.putLong("receivedTime", System.currentTimeMillis());
        data.putString("correlationId", UUID.randomUUID().toString());
        Intent intent = new Intent("com.microsoft.react.push.PushConstants.ACTION_MESSAGE_RECEIVED");
        intent.addFlags(32);
        intent.setClass(this, PushReceiver.class);
        intent.putExtras(data);
        intent.putExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", f.a(getApplicationContext()));
        sendBroadcast(intent);
    }
}
