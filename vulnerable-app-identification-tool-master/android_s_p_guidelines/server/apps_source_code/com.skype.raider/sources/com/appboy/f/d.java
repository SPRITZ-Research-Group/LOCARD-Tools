package com.appboy.f;

import android.os.Bundle;
import java.util.Map;
import java.util.Map.Entry;

public final class d {
    public static Bundle a(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return bundle;
    }
}
