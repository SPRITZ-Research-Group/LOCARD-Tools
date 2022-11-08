package androidx.core.app;

import android.app.Notification;
import android.os.Build.VERSION;
import android.os.Bundle;

public final class w {
    public static Bundle a(Notification notification) {
        if (VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        return VERSION.SDK_INT >= 16 ? af.a(notification) : null;
    }
}
