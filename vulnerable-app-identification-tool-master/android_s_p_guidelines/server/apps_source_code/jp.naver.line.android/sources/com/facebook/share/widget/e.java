package com.facebook.share.widget;

import com.linecorp.linepay.legacy.activity.a;
import org.apache.cordova.networkinformation.NetworkManager;

@Deprecated
public enum e {
    UNKNOWN(NetworkManager.TYPE_UNKNOWN, 0),
    OPEN_GRAPH("open_graph", 1),
    PAGE(a.QUERY_KEY_PAGE, 2);
    
    public static e DEFAULT;
    private int intValue;
    private String stringValue;

    static {
        DEFAULT = UNKNOWN;
    }

    public static e a(int i) {
        for (e eVar : values()) {
            if (eVar.intValue == i) {
                return eVar;
            }
        }
        return null;
    }

    private e(String str, int i) {
        this.stringValue = str;
        this.intValue = i;
    }

    public final String toString() {
        return this.stringValue;
    }

    public final int a() {
        return this.intValue;
    }
}
