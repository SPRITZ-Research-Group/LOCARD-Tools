package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.r.b;
import java.util.ArrayList;
import java.util.List;

public enum o {
    ANBANNER(q.class, n.AN, b.BANNER),
    ANINTERSTITIAL(s.class, n.AN, b.INTERSTITIAL),
    ADMOBNATIVE(l.class, n.ADMOB, b.NATIVE),
    ANNATIVE(v.class, n.AN, b.NATIVE),
    ANNATIVEBANNER(v.class, n.AN, b.NATIVE_BANNER),
    ANINSTREAMVIDEO(r.class, n.AN, b.INSTREAM),
    ANREWARDEDVIDEO(w.class, n.AN, b.REWARDED_VIDEO),
    INMOBINATIVE(aa.class, n.INMOBI, b.NATIVE),
    YAHOONATIVE(x.class, n.YAHOO, b.NATIVE);
    
    private static List<o> n;
    public Class<?> j;
    public String k;
    public n l;
    public b m;

    private o(Class<?> cls, n nVar, b bVar) {
        this.j = cls;
        this.l = nVar;
        this.m = bVar;
    }

    public static List<o> a() {
        if (n == null) {
            synchronized (o.class) {
                List arrayList = new ArrayList();
                n = arrayList;
                arrayList.add(ANBANNER);
                n.add(ANINTERSTITIAL);
                n.add(ANNATIVE);
                n.add(ANNATIVEBANNER);
                n.add(ANINSTREAMVIDEO);
                n.add(ANREWARDEDVIDEO);
                if (ag.a(n.YAHOO)) {
                    n.add(YAHOONATIVE);
                }
                if (ag.a(n.INMOBI)) {
                    n.add(INMOBINATIVE);
                }
                if (ag.a(n.ADMOB)) {
                    n.add(ADMOBNATIVE);
                }
            }
        }
        return n;
    }
}
