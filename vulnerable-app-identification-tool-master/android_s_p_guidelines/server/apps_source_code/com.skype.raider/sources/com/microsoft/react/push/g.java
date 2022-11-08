package com.microsoft.react.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.common.logging.FLog;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class g {
    private static g a;
    private SharedPreferences b;

    static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private g() {
    }

    final void a(Context ctx) {
        this.b = ctx.getSharedPreferences("PushNotificationCache", 0);
    }

    final void a(String entryId, String entry) {
        if (this.b != null) {
            this.b.edit().putString(entryId, entry).apply();
        } else {
            FLog.e("PushNotificationCache", "PushNotificationCache was not properly initialized");
        }
    }

    final void a(String entryId) {
        if (this.b != null) {
            this.b.edit().remove(entryId).apply();
        } else {
            FLog.e("PushNotificationCache", "PushNotificationCache was not properly initialized");
        }
    }

    final Map<String, String> b() {
        if (this.b != null) {
            Set<String> keys = this.b.getAll().keySet();
            Map<String, String> hashMap = new HashMap();
            for (String key : keys) {
                hashMap.put(key, this.b.getString(key, ""));
            }
            return hashMap;
        }
        FLog.e("PushNotificationCache", "PushNotificationCache was not properly initialized");
        return new HashMap();
    }

    final void c() {
        if (this.b != null) {
            this.b.edit().clear().apply();
        } else {
            FLog.e("PushNotificationCache", "PushNotificationCache was not properly initialized");
        }
    }
}
