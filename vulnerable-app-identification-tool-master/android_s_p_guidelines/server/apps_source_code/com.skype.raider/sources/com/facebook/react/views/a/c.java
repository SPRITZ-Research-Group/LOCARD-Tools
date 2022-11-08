package com.facebook.react.views.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class c {
    private static volatile c b;
    private Map<String, Integer> a = new HashMap();

    private c() {
    }

    public static c a() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    public final synchronized void b() {
        this.a.clear();
    }

    public final int a(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        name = name.toLowerCase().replace("-", "_");
        synchronized (this) {
            int intValue;
            if (this.a.containsKey(name)) {
                intValue = ((Integer) this.a.get(name)).intValue();
                return intValue;
            }
            intValue = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            this.a.put(name, Integer.valueOf(intValue));
            return intValue;
        }
    }
}
