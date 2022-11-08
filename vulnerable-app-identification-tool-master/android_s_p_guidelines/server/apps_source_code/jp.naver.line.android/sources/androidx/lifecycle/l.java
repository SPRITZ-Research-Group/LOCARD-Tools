package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

final class l {
    private static AtomicBoolean a = new AtomicBoolean(false);

    static void a(Context context) {
        if (!a.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new m());
        }
    }
}
