package com.facebook.ads.internal.f;

import com.facebook.ads.internal.q.a.n;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import java.util.HashMap;
import java.util.Map;

public final class a extends d {
    public a(String str, String str2) {
        double b = n.b();
        String c = n.c();
        Map hashMap = new HashMap();
        hashMap.put(PropertiesEntry.COLUMN_NAME_KEY, str);
        hashMap.put(PropertiesEntry.COLUMN_NAME_VALUE, str2);
        super(b, c, hashMap);
    }

    public final String a() {
        return "client_response";
    }
}
