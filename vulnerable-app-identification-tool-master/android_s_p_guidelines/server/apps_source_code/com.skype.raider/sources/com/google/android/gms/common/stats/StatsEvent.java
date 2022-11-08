package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {
    public abstract long a();

    public abstract int b();

    public abstract String c();

    public abstract long d();

    public abstract long e();

    public abstract long f();

    public abstract String g();

    public String toString() {
        long a = a();
        int b = b();
        long d = d();
        String g = g();
        return new StringBuilder(String.valueOf(g).length() + 53).append(a).append("\t").append(b).append("\t").append(d).append(g).toString();
    }
}
