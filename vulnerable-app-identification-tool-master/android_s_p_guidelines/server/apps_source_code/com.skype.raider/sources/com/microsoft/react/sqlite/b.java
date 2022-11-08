package com.microsoft.react.sqlite;

import com.facebook.react.bridge.am;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;

public final class b {
    private final int a;
    private final String b;
    private final String c;
    private final boolean d;

    private enum a {
        OPEN,
        CLOSE
    }

    public static b a(am options) {
        return new b(options, a.CLOSE);
    }

    public static b b(am options) {
        return new b(options, a.OPEN);
    }

    private b(am options, a type) {
        String str = null;
        this.b = options.getString("name");
        if (type == a.OPEN) {
            boolean z;
            if (options.hasKey(PropertiesEntry.COLUMN_NAME_KEY)) {
                str = options.getString(PropertiesEntry.COLUMN_NAME_KEY);
            }
            this.c = str;
            this.a = options.hasKey("maxConcurrentTransactions") ? options.getInt("maxConcurrentTransactions") : 1;
            if (options.hasKey("verbose")) {
                z = options.getBoolean("verbose");
            } else {
                z = false;
            }
            this.d = z;
            return;
        }
        this.c = null;
        this.a = -1;
        this.d = false;
    }

    public final String a() {
        return this.b;
    }

    public final int b() {
        return this.a;
    }

    public final boolean c() {
        return this.d;
    }
}
