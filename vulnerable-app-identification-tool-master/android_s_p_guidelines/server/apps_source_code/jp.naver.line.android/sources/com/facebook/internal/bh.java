package com.facebook.internal;

import java.util.EnumSet;
import java.util.Iterator;

public enum bh {
    None(0),
    Enabled(1),
    RequireConfirm(2);
    
    public static final EnumSet<bh> ALL = null;
    private final long mValue;

    static {
        ALL = EnumSet.allOf(bh.class);
    }

    public static EnumSet<bh> a(long j) {
        EnumSet<bh> noneOf = EnumSet.noneOf(bh.class);
        Iterator it = ALL.iterator();
        while (it.hasNext()) {
            bh bhVar = (bh) it.next();
            if ((bhVar.mValue & j) != 0) {
                noneOf.add(bhVar);
            }
        }
        return noneOf;
    }

    private bh(long j) {
        this.mValue = j;
    }
}
