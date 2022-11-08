package com.appboy.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.appboy.f.c;

public class AppboyNotificationRoutingActivity extends Activity {
    private static final String a = c.a(AppboyNotificationRoutingActivity.class);

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent receivedIntent = getIntent();
        if (receivedIntent == null) {
            c.b(a, "Notification routing activity received null intent. Doing nothing.");
            finish();
            return;
        }
        String action = receivedIntent.getAction();
        if (action == null) {
            c.b(a, "Notification routing activity received intent with null action. Doing nothing.");
            finish();
            return;
        }
        c.d(a, "Notification routing activity received intent: " + receivedIntent.toString());
        Context context = getApplicationContext();
        Intent sendIntent = new Intent(action).setClass(context, e.b());
        sendIntent.putExtras(receivedIntent.getExtras());
        context.sendBroadcast(sendIntent);
        finish();
    }
}
