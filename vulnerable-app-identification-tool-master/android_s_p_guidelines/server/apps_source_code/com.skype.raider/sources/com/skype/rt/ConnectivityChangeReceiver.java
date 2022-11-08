package com.skype.rt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    private static final boolean DBG = false;
    private static final String TAG = "rt::";
    private RootToolsHandler m_handler;
    private int m_writeFd;

    ConnectivityChangeReceiver(int writeFd, RootToolsHandler handler) {
        this.m_writeFd = writeFd;
        this.m_handler = handler;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.m_handler.listInterfaces(this.m_writeFd);
        }
    }
}
