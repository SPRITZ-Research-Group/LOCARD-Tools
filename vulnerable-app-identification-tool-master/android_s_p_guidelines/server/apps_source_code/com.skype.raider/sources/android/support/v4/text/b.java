package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.util.Locale;

public final class b {
    private static final b a;

    static class b {
        b() {
        }

        public String a(Locale locale) {
            return d.a(locale);
        }
    }

    @RequiresApi(21)
    static class a extends b {
        a() {
        }

        public final String a(Locale locale) {
            return c.a(locale);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new a();
        } else {
            a = new b();
        }
    }

    @Nullable
    public static String a(Locale locale) {
        return a.a(locale);
    }
}
