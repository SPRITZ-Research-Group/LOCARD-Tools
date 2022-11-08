package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.work.impl.j;
import androidx.work.p;

public class RescheduleReceiver extends BroadcastReceiver {
    private static final String a = p.a("RescheduleReceiver");

    public void onReceive(Context context, Intent intent) {
        if (VERSION.SDK_INT >= 23) {
            j b = j.b();
            if (b == null) {
                p.a().b(a, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", new Throwable[0]);
                return;
            } else {
                b.a(goAsync());
                return;
            }
        }
        context.startService(b.b(context));
    }
}
