package a.a;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum fv {
    UNKNOWN("unknown"),
    NONE("none"),
    TWO_G("2g"),
    THREE_G("3g"),
    FOUR_G("4g"),
    WIFI("wifi");
    
    private static final Map<String, fv> g = null;
    private final String h;

    static {
        g = new HashMap();
        Iterator it = EnumSet.allOf(fv.class).iterator();
        while (it.hasNext()) {
            fv fvVar = (fv) it.next();
            g.put(fvVar.h, fvVar);
        }
    }

    private fv(String str) {
        this.h = str;
    }
}
