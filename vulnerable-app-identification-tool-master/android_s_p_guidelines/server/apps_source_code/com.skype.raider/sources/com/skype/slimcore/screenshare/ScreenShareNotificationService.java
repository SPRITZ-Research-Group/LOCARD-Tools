package com.skype.slimcore.screenshare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.skype.views.ScreenShareBorderView;

public class ScreenShareNotificationService extends Service {
    ScreenShareBorderView a;
    private volatile boolean b;

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        FLog.d("ScreenShareNotificationService", "onStartCommand isRunning: " + this.b + " start ID: " + startId + " intent: " + intent);
        if (!this.b) {
            this.a = new ScreenShareBorderView(this);
            this.b = true;
        }
        return 2;
    }

    public void onDestroy() {
        FLog.d("ScreenShareNotificationService", "onDestroy");
        if (this.a != null) {
            FLog.d("ScreenShareNotificationService", "destroying border view");
            this.a.a();
            this.a = null;
        }
        this.b = false;
        super.onDestroy();
    }
}
