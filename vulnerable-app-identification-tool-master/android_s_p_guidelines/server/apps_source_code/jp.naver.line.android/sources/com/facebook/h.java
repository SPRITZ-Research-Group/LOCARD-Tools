package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class h extends BroadcastReceiver {
    final /* synthetic */ g a;

    private h(g gVar) {
        this.a = gVar;
    }

    /* synthetic */ h(g gVar, byte b) {
        this(gVar);
    }

    public final void onReceive(Context context, Intent intent) {
        if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(intent.getAction())) {
            g.a;
            intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN");
            this.a.a((AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN"));
        }
    }
}
