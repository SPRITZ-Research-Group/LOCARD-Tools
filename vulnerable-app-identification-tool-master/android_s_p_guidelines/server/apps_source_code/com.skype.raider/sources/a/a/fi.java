package a.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.f.c;

public class fi {
    private static final String a = c.a(fi.class);
    private final SharedPreferences b;

    public fi(Context context) {
        this.b = context.getSharedPreferences("com.appboy.override.configuration.cache", 0);
    }

    public final String a(String str, String str2) {
        return this.b.getString(str, str2);
    }

    public final int a(String str, int i) {
        return this.b.getInt(str, i);
    }

    public final boolean a(String str, boolean z) {
        return this.b.getBoolean(str, z);
    }

    public final boolean a(String str) {
        return this.b.contains(str);
    }
}
