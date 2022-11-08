package com.facebook.ads.internal.o;

import com.facebook.ads.internal.r.e;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class a {
    private static Map<String, Long> a = new ConcurrentHashMap();
    private static Map<String, Long> b = new ConcurrentHashMap();
    private static Map<String, String> c = new ConcurrentHashMap();

    public static void a(long j, b bVar) {
        a.put(d(bVar), Long.valueOf(j));
    }

    public static void a(String str, b bVar) {
        c.put(d(bVar), str);
    }

    public static boolean a(b bVar) {
        String d = d(bVar);
        if (!b.containsKey(d)) {
            return false;
        }
        long j;
        long longValue = ((Long) b.get(d)).longValue();
        e b = bVar.b();
        if (!a.containsKey(d)) {
            switch (b) {
                case BANNER:
                    j = 15000;
                    break;
                case INTERSTITIAL:
                case NATIVE:
                    j = -1000;
                    break;
                default:
                    j = -1000;
                    break;
            }
        }
        j = ((Long) a.get(d)).longValue();
        return System.currentTimeMillis() - longValue < j;
    }

    public static void b(b bVar) {
        b.put(d(bVar), Long.valueOf(System.currentTimeMillis()));
    }

    public static String c(b bVar) {
        return (String) c.get(d(bVar));
    }

    private static String d(b bVar) {
        int i = 0;
        String str = "%s:%s:%s:%d:%d:%d";
        Object[] objArr = new Object[6];
        objArr[0] = bVar.a();
        objArr[1] = bVar.b();
        objArr[2] = bVar.c;
        objArr[3] = Integer.valueOf(bVar.c() == null ? 0 : bVar.c().a());
        if (bVar.c() != null) {
            i = bVar.c().b();
        }
        objArr[4] = Integer.valueOf(i);
        objArr[5] = Integer.valueOf(bVar.d());
        return String.format(str, objArr);
    }
}
