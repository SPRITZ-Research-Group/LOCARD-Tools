package com.skype.rt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;

public class PowerReceiver extends BroadcastReceiver {
    private static final boolean DBG = false;
    private static final String TAG = "rt::";
    private RootToolsHandler m_handler;

    PowerReceiver(RootToolsHandler handler) {
        this.m_handler = handler;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED") || intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            this.m_handler.notifyPowerEvent(true);
        } else if (VERSION.SDK_INT >= 21 && intent.getAction().equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
            this.m_handler.notifyPowerEvent(true);
        } else if (VERSION.SDK_INT >= 23 && intent.getAction().equals("android.os.action.DEVICE_IDLE_MODE_CHANGED")) {
            this.m_handler.notifyPowerEvent(false);
        }
    }
}
