package com.facebook.internal;

import android.util.Log;
import com.facebook.ai;
import com.facebook.s;
import java.util.HashMap;
import java.util.Map.Entry;

public final class ar {
    private static final HashMap<String, String> a = new HashMap();
    private final ai b;
    private final String c;
    private StringBuilder d;
    private int e = 3;

    private static synchronized void a(String str, String str2) {
        synchronized (ar.class) {
            a.put(str, str2);
        }
    }

    public static synchronized void a(String str) {
        synchronized (ar.class) {
            if (!s.a(ai.INCLUDE_ACCESS_TOKENS)) {
                a(str, "ACCESS_TOKEN_REMOVED");
            }
        }
    }

    public static void a(ai aiVar, String str, String str2) {
        a(aiVar, 3, str, str2);
    }

    public static void a(ai aiVar, String str, String str2, Object... objArr) {
        if (s.a(aiVar)) {
            a(aiVar, 3, str, String.format(str2, objArr));
        }
    }

    public static void b(ai aiVar, String str, String str2, Object... objArr) {
        if (s.a(aiVar)) {
            a(aiVar, 5, str, String.format(str2, objArr));
        }
    }

    public static void a(ai aiVar, int i, String str, String str2) {
        if (s.a(aiVar)) {
            str2 = c(str2);
            if (!str.startsWith("FacebookSDK.")) {
                str = "FacebookSDK.".concat(String.valueOf(str));
            }
            Log.println(i, str, str2);
            if (aiVar == ai.DEVELOPER_ERRORS) {
                new Exception().printStackTrace();
            }
        }
    }

    private static synchronized String c(String str) {
        synchronized (ar.class) {
            for (Entry entry : a.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public ar(ai aiVar, String str) {
        bn.a(str, "tag");
        this.b = aiVar;
        this.c = "FacebookSDK.".concat(String.valueOf(str));
        this.d = new StringBuilder();
    }

    public final void a() {
        a(this.b, this.e, this.c, this.d.toString());
        this.d = new StringBuilder();
    }

    public final void a(String str, Object obj) {
        String str2 = "  %s:\t%s\n";
        Object[] objArr = new Object[]{str, obj};
        if (s.a(this.b)) {
            this.d.append(String.format(str2, objArr));
        }
    }

    public final void b(String str) {
        if (s.a(this.b)) {
            this.d.append(str);
        }
    }
}
