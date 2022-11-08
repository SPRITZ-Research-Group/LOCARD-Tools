package com.appboy.b;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public enum c {
    ADVERTISING,
    ANNOUNCEMENTS,
    NEWS,
    SOCIAL,
    NO_CATEGORY;
    
    private static final Map<String, c> f = null;

    static {
        f = new HashMap();
        Iterator it = EnumSet.allOf(c.class).iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            f.put(cVar.toString(), cVar);
        }
    }

    public static c a(String value) {
        return (c) f.get(value.toUpperCase(Locale.US));
    }

    public static EnumSet<c> a() {
        return EnumSet.allOf(c.class);
    }
}
