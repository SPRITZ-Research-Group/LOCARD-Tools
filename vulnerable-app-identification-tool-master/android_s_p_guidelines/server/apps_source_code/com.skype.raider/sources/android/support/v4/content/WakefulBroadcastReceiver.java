package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final SparseArray<WakeLock> a = new SparseArray();
    private static int b = 1;

    public static ComponentName a_(Context context, Intent intent) {
        ComponentName comp;
        synchronized (a) {
            int id = b;
            int i = b + 1;
            b = i;
            if (i <= 0) {
                b = 1;
            }
            intent.putExtra("android.support.content.wakelockid", id);
            comp = context.startService(intent);
            if (comp == null) {
                comp = null;
            } else {
                WakeLock wl = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "wake:" + comp.flattenToShortString());
                wl.setReferenceCounted(false);
                wl.acquire(60000);
                a.put(id, wl);
            }
        }
        return comp;
    }

    public static boolean a(Intent intent) {
        int id = intent.getIntExtra("android.support.content.wakelockid", 0);
        if (id == 0) {
            return false;
        }
        synchronized (a) {
            WakeLock wl = (WakeLock) a.get(id);
            if (wl != null) {
                wl.release();
                a.remove(id);
                return true;
            }
            return true;
        }
    }
}
