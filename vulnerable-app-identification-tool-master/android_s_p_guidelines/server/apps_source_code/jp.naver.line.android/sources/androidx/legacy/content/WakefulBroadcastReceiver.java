package androidx.legacy.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer.hls.HlsChunkSource;

@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "androidx.contentpager.content.wakelockid";
    private static int mNextId = 1;
    private static final SparseArray<WakeLock> sActiveWakeLocks = new SparseArray();

    public static ComponentName startWakefulService(Context context, Intent intent) {
        synchronized (sActiveWakeLocks) {
            int i = mNextId;
            int i2 = mNextId + 1;
            mNextId = i2;
            if (i2 <= 0) {
                mNextId = 1;
            }
            intent.putExtra(EXTRA_WAKE_LOCK_ID, i);
            ComponentName startService = context.startService(intent);
            if (startService == null) {
                return null;
            }
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            StringBuilder stringBuilder = new StringBuilder("androidx.core:wake:");
            stringBuilder.append(startService.flattenToShortString());
            WakeLock newWakeLock = powerManager.newWakeLock(1, stringBuilder.toString());
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire(HlsChunkSource.DEFAULT_PLAYLIST_BLACKLIST_MS);
            sActiveWakeLocks.put(i, newWakeLock);
            return startService;
        }
    }

    public static boolean completeWakefulIntent(Intent intent) {
        int intExtra = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
        if (intExtra == 0) {
            return false;
        }
        synchronized (sActiveWakeLocks) {
            WakeLock wakeLock = (WakeLock) sActiveWakeLocks.get(intExtra);
            if (wakeLock != null) {
                wakeLock.release();
                sActiveWakeLocks.remove(intExtra);
                return true;
            }
            Log.w("WakefulBroadcastReceiv.", "No active wake lock id #".concat(String.valueOf(intExtra)));
            return true;
        }
    }
}
