package com.airbnb.lottie;

import com.airbnb.lottie.e.d;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class i {
    private boolean a = false;
    private final Set<Object> b = new HashSet();
    private final Map<String, d> c = new HashMap();
    private final Comparator<android.support.v4.util.i<String, Float>> d = new Comparator<android.support.v4.util.i<String, Float>>(this) {
        final /* synthetic */ i a;

        {
            this.a = this$0;
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            android.support.v4.util.i iVar = (android.support.v4.util.i) obj2;
            float floatValue = ((Float) ((android.support.v4.util.i) obj).b).floatValue();
            float floatValue2 = ((Float) iVar.b).floatValue();
            if (floatValue2 > floatValue) {
                return 1;
            }
            if (floatValue > floatValue2) {
                return -1;
            }
            return 0;
        }
    };

    final void a(boolean enabled) {
        this.a = enabled;
    }

    public final void a(String layerName, float millis) {
        if (this.a) {
            d meanCalculator = (d) this.c.get(layerName);
            if (meanCalculator == null) {
                meanCalculator = new d();
                this.c.put(layerName, meanCalculator);
            }
            meanCalculator.a(millis);
            if (layerName.equals("__container")) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }
    }
}
