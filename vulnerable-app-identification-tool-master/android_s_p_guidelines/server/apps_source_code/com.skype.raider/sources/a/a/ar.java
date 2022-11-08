package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.VisibleForTesting;
import com.appboy.a.a;
import com.appboy.f.c;

public class ar implements aq {
    private static final String b = c.a(ar.class);
    @VisibleForTesting
    final SharedPreferences a;
    private final a c;

    public ar(Context context, a aVar) {
        this.c = aVar;
        this.a = context.getSharedPreferences("com.appboy.push_registration", 0);
    }

    public final synchronized String a() {
        String str = null;
        synchronized (this) {
            Object obj;
            if (this.c.c() || this.c.d() || this.c.A()) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null || !this.a.contains("version_code") || this.c.m() == this.a.getInt("version_code", Integer.MIN_VALUE)) {
                if (this.a.contains("device_identifier")) {
                    if (!ab.b().equals(this.a.getString("device_identifier", ""))) {
                        c.d(b, "Device identifier differs from saved device identifier. Returning null token.");
                    }
                }
                str = this.a.getString("registration_id", null);
            }
        }
        return str;
    }

    public final synchronized void a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Editor edit = this.a.edit();
        edit.putString("registration_id", str);
        edit.putInt("version_code", this.c.m());
        edit.putString("device_identifier", ab.b());
        edit.apply();
    }
}
