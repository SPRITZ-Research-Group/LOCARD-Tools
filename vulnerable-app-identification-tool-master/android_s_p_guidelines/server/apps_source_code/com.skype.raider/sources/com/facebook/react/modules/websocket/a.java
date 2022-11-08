package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.am;
import java.util.HashMap;
import java.util.Map;

public final class a {
    private long a = 0;
    private final Map<String, String> b = new HashMap();

    public a(am options) {
        long j = 0;
        if (options != null) {
            if (options.hasKey("pingInterval")) {
                j = (long) options.getDouble("pingInterval");
            }
            this.a = j;
            if (options.hasKey("headers")) {
                ReadableMapKeySetIterator iterator = options.getMap("headers").keySetIterator();
                while (iterator.hasNextKey()) {
                    String key = iterator.nextKey();
                    if (ReadableType.String.equals(options.getType(key))) {
                        this.b.put(key, options.getString(key));
                    } else {
                        FLog.w("React", "Ignoring: requested " + key + ", value not a string");
                    }
                }
            }
        }
    }

    public final Map<String, String> a() {
        return this.b;
    }

    public final long b() {
        return this.a;
    }
}
