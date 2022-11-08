package com.microsoft.react.push;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.ac;
import com.facebook.common.logging.FLog;

public class BackgroundActionService extends IntentService implements d {
    private static final String b = BackgroundActionService.class.getSimpleName();

    public BackgroundActionService() {
        super(b);
    }

    protected void onHandleIntent(@Nullable Intent intent) {
        FLog.i(b, "onHandleIntent");
        Intent broadcastIntent = new Intent("LocalNotificationActionReceived");
        broadcastIntent.setClass(this, PushReceiver.class);
        Bundle remoteInput = ac.a(intent);
        if (remoteInput != null) {
            intent.putExtra("com.microsoft.react.push.PushConstants.extra.inline.reply.value", remoteInput.getCharSequence("key_text_reply").toString());
        }
        broadcastIntent.putExtras(intent);
        sendBroadcast(broadcastIntent);
        if (intent.getExtras().containsKey("notificationId")) {
            NotificationManagerCompat.from(this).cancel(intent.getIntExtra("notificationId", 0));
        }
    }
}
