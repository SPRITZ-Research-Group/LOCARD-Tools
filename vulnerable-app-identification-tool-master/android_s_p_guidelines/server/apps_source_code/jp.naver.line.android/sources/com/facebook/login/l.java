package com.facebook.login;

import android.content.Context;
import com.facebook.s;

final class l {
    private static h a;

    private static synchronized h b(Context context) {
        synchronized (l.class) {
            if (context == null) {
                context = s.f();
            }
            if (context == null) {
                return null;
            }
            if (a == null) {
                a = new h(context, s.j());
            }
            h hVar = a;
            return hVar;
        }
    }
}
