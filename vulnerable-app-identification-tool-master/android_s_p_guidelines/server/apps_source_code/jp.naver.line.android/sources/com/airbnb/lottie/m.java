package com.airbnb.lottie;

import defpackage.bv;
import defpackage.gm;
import defpackage.wx;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class m {
    private boolean a = false;
    private final Set<Object> b = new bv();
    private final Map<String, wx> c = new HashMap();
    private final Comparator<gm<String, Float>> d = new Comparator<gm<String, Float>>(this) {
        final /* synthetic */ m a;

        {
            this.a = r1;
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            gm gmVar = (gm) obj2;
            float floatValue = ((Float) ((gm) obj).b).floatValue();
            float floatValue2 = ((Float) gmVar.b).floatValue();
            if (floatValue2 > floatValue) {
                return 1;
            }
            return floatValue > floatValue2 ? -1 : 0;
        }
    };

    final void a(boolean z) {
        this.a = z;
    }

    public final void a(String str, float f) {
        if (this.a) {
            wx wxVar = (wx) this.c.get(str);
            if (wxVar == null) {
                wxVar = new wx();
                this.c.put(str, wxVar);
            }
            wxVar.a(f);
            if (str.equals("__container")) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }
    }
}
