package com.facebook.react.animated;

import com.facebook.react.bridge.am;
import com.microsoft.applications.telemetry.core.SQLiteStorageContract.PropertiesEntry;
import javax.annotation.Nullable;

class q extends b {
    double e = Double.NaN;
    double f = 0.0d;
    @Nullable
    private c g;

    public q(am config) {
        this.e = config.getDouble(PropertiesEntry.COLUMN_NAME_VALUE);
        this.f = config.getDouble("offset");
    }

    public final double b() {
        return this.f + this.e;
    }

    public final void c() {
        if (this.g != null) {
            this.g.a(this.e);
        }
    }

    public final void a(@Nullable c listener) {
        this.g = listener;
    }
}
