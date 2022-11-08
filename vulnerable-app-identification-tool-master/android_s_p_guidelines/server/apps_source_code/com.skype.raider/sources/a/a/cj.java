package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.f.c;

public class cj {
    private static final String a = c.a(cj.class);
    private final SharedPreferences b;

    public cj(Context context) {
        this.b = context.getSharedPreferences("persistent.com.appboy.storage.sdk_enabled_cache", 0);
    }

    public final boolean a() {
        return this.b.getBoolean("appboy_sdk_disabled", false);
    }

    public final void b() {
        c.d(a, "Setting Appboy SDK disabled to: true");
        this.b.edit().putBoolean("appboy_sdk_disabled", true).apply();
    }
}
