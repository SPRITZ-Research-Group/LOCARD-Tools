package com.microsoft.react.push;

import android.content.Context;
import android.content.Intent;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ag;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class e {
    private static final Queue<Intent> a = new ConcurrentLinkedQueue();
    private static PushModule b;
    private static boolean c;

    public static synchronized PushModule a(ag reactContext) {
        PushModule pushModule;
        synchronized (e.class) {
            pushModule = new PushModule(reactContext, a);
            b = pushModule;
        }
        return pushModule;
    }

    public static synchronized void a() {
        synchronized (e.class) {
            c = true;
            if (b == null) {
                throw new IllegalStateException("PushModule is not created while JS engine is initialized");
            }
        }
    }

    public static synchronized void b() {
        synchronized (e.class) {
            c = false;
            b = null;
        }
    }

    public static synchronized void a(Context context, Intent intent) {
        synchronized (e.class) {
            FLog.i("PushHandlingContext", "onReceive");
            if (intent.getIntExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", 0) == 0) {
                FLog.i("PushHandlingContext", "onReceive - no prior WakeLock; starting push handling");
                intent.putExtra("com.microsoft.react.push.PushConstants.notificationProcessingId", f.a(context));
            }
            if (c) {
                b.handleIntent(intent);
            } else {
                FLog.i("PushHandlingContext", "onReceive - delaying message until PushModule is initialized");
                a.add(intent);
            }
        }
    }
}
