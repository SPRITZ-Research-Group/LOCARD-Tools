package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class e {
    private Context a;
    private SharedPreferences b;

    public e(Context context) {
        this.a = context;
    }

    public final boolean a() {
        return b().getBoolean("reschedule_needed", false);
    }

    public final void a(boolean z) {
        b().edit().putBoolean("reschedule_needed", z).apply();
    }

    private SharedPreferences b() {
        SharedPreferences sharedPreferences;
        synchronized (e.class) {
            if (this.b == null) {
                this.b = this.a.getSharedPreferences("androidx.work.util.preferences", 0);
            }
            sharedPreferences = this.b;
        }
        return sharedPreferences;
    }
}
