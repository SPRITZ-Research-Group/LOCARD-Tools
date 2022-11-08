package com.skype4life.syncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.common.logging.FLog;

public class AccountService extends Service {
    private static j a = null;

    public void onCreate() {
        synchronized (AccountService.class) {
            if (a == null) {
                a = new j(getApplicationContext());
            }
        }
    }

    public IBinder onBind(Intent intent) {
        FLog.d("AccountService", "onBind " + intent.getAction());
        if ("android.content.SyncAdapter".equals(intent.getAction())) {
            return a.getSyncAdapterBinder();
        }
        if ("android.accounts.AccountAuthenticator".equals(intent.getAction())) {
            return new a(getApplicationContext()).getIBinder();
        }
        FLog.w("AccountService", "onBind did not bind anything. Check your intent filter in the manifest!");
        return null;
    }

    public void onDestroy() {
        FLog.d("AccountService", "onDestroy");
        super.onDestroy();
    }
}
