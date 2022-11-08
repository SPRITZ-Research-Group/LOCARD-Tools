package jp.naver.line.android.db.main.model;

import android.util.SparseArray;
import java.util.EnumSet;
import java.util.Set;

public enum ad {
    NORMAL(0),
    RECOMMENDED(1),
    BLOCKED(2),
    BLOCKED_RECOMMENDED(3),
    UNREGISTERED(4),
    DELETED(5),
    DELETED_BLOCKED(6);
    
    private static final Set<ad> BLOCKED_STATUSES = null;
    private static final SparseArray<ad> VALUE_MAP = null;
    public final int dbValue;

    static {
        BLOCKED_STATUSES = EnumSet.of(BLOCKED, BLOCKED_RECOMMENDED, DELETED_BLOCKED);
        VALUE_MAP = new SparseArray(values().length);
        ad[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            ad adVar = values[i];
            VALUE_MAP.put(adVar.dbValue, adVar);
            i++;
        }
    }

    private ad(int i) {
        this.dbValue = i;
    }

    public static ad a(int i) {
        return (ad) VALUE_MAP.get(i);
    }

    public static boolean a(ad adVar) {
        return BLOCKED_STATUSES.contains(adVar);
    }
}
