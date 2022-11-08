package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* renamed from: pxz */
final class pxz implements Runnable {
    pxz() {
    }

    public final void run() {
        Iterator it = new ArrayList(pxx.d.keySet()).iterator();
        while (it.hasNext()) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it.next();
            if (scheduledThreadPoolExecutor.isShutdown()) {
                pxx.d.remove(scheduledThreadPoolExecutor);
            } else {
                scheduledThreadPoolExecutor.purge();
            }
        }
    }
}
