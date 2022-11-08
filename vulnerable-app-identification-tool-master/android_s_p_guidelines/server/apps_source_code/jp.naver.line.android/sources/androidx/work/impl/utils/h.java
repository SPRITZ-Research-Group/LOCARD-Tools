package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import androidx.work.p;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class h {
    private static final String a = p.a("WakeLocks");
    private static final WeakHashMap<WakeLock, String> b = new WeakHashMap();

    public static WakeLock a(Context context, String str) {
        PowerManager powerManager = (PowerManager) context.getApplicationContext().getSystemService("power");
        str = "WorkManager: ".concat(String.valueOf(str));
        WakeLock newWakeLock = powerManager.newWakeLock(1, str);
        synchronized (b) {
            b.put(newWakeLock, str);
        }
        return newWakeLock;
    }

    public static void a() {
        Map hashMap = new HashMap();
        synchronized (b) {
            hashMap.putAll(b);
        }
        for (WakeLock isHeld : hashMap.keySet()) {
            if (isHeld.isHeld()) {
                Throwable[] thArr = new Throwable[0];
                p.a().a(a, String.format("WakeLock held for %s", new Object[]{hashMap.get((WakeLock) r1.next())}));
            }
        }
    }
}
