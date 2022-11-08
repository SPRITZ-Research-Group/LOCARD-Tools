package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import java.util.UUID;

public final class ab implements ao {
    @VisibleForTesting
    final SharedPreferences a;

    public ab(Context context) {
        this.a = context.getSharedPreferences("com.appboy.device", 0);
    }

    public final String a() {
        String str = null;
        Object obj = null;
        String string = this.a.getString("device_id", null);
        if (this.a.contains("persistent_device_id")) {
            if (!b().equals(this.a.getString("persistent_device_id", ""))) {
                obj = 1;
            }
        }
        if (obj == null) {
            str = string;
        }
        if (str == null) {
            str = UUID.randomUUID().toString();
            a(str);
            return str;
        } else if (this.a.contains("persistent_device_id")) {
            return str;
        } else {
            a(str);
            return str;
        }
    }

    private void a(String str) {
        Editor edit = this.a.edit();
        edit.putString("device_id", str);
        edit.putString("persistent_device_id", b());
        edit.apply();
    }

    static String b() {
        return String.valueOf("android_id".hashCode());
    }
}
