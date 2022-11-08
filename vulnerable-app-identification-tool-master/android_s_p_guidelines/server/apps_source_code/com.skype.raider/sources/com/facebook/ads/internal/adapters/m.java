package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.q.a.t;
import com.facebook.ads.internal.r.b;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class m {
    private static final Set<o> a = new HashSet();
    private static final Map<b, String> b = new ConcurrentHashMap();

    static {
        for (o oVar : o.a()) {
            Class cls;
            switch (oVar.m) {
                case BANNER:
                    cls = b.class;
                    break;
                case INTERSTITIAL:
                    cls = d.class;
                    break;
                case NATIVE:
                case NATIVE_BANNER:
                    cls = ai.class;
                    break;
                case INSTREAM:
                    cls = ab.class;
                    break;
                case REWARDED_VIDEO:
                    cls = h.class;
                    break;
                default:
                    cls = null;
                    break;
            }
            if (cls != null) {
                Class cls2 = oVar.j;
                if (cls2 == null) {
                    try {
                        cls2 = Class.forName(oVar.k);
                    } catch (ClassNotFoundException e) {
                    }
                }
                if (cls2 != null && cls.isAssignableFrom(cls2)) {
                    a.add(oVar);
                }
            }
        }
    }

    private static a a(n nVar, b bVar) {
        Exception e;
        a aVar;
        try {
            o oVar;
            for (o oVar2 : a) {
                if (oVar2.l == nVar && oVar2.m == bVar) {
                    oVar = oVar2;
                    break;
                }
            }
            oVar = null;
            if (oVar == null || !a.contains(oVar)) {
                return null;
            }
            Class cls = oVar.j;
            if (cls == null) {
                cls = Class.forName(oVar.k);
            }
            aVar = (a) cls.newInstance();
            try {
                if (!(aVar instanceof v)) {
                    return aVar;
                }
                ((v) aVar).a(bVar);
                return aVar;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return aVar;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            aVar = null;
            e = exception;
            e.printStackTrace();
            return aVar;
        }
    }

    public static a a(String str, b bVar) {
        return a(n.a(str), bVar);
    }

    public static String a(b bVar) {
        if (b.containsKey(bVar)) {
            return (String) b.get(bVar);
        }
        Set hashSet = new HashSet();
        for (o oVar : a) {
            if (oVar.m == bVar) {
                hashSet.add(oVar.l.toString());
            }
        }
        String a = t.a(hashSet, ",");
        b.put(bVar, a);
        return a;
    }
}
