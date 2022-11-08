package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.e.d;
import com.facebook.common.e.e;
import com.facebook.imagepipeline.l.b;
import com.microsoft.applications.telemetry.LogConfiguration;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class ab {
    private final ad a;
    private final ae b;
    private final ad c;
    private final d d;
    private final ad e;
    private final ae f;
    private final ad g;
    private final ae h;
    private final String i;
    private final int j;
    private final int k;

    public static class a {
        private ad a;
        private ae b;
        private ad c;
        private d d;
        private ad e;
        private ae f;
        private ad g;
        private ae h;
        private String i;
        private int j;
        private int k;

        /* synthetic */ a(byte b) {
            this();
        }

        private a() {
        }

        public final ab a() {
            return new ab();
        }
    }

    /* synthetic */ ab(a x0, byte b) {
        this(x0);
    }

    private ab(a builder) {
        ad a;
        ae a2;
        d a3;
        b.a();
        if (builder.a == null) {
            a = k.a();
        } else {
            a = builder.a;
        }
        this.a = a;
        if (builder.b == null) {
            a2 = y.a();
        } else {
            a2 = builder.b;
        }
        this.b = a2;
        if (builder.c == null) {
            a = l.a();
        } else {
            a = builder.c;
        }
        this.c = a;
        if (builder.d == null) {
            a3 = e.a();
        } else {
            a3 = builder.d;
        }
        this.d = a3;
        if (builder.e == null) {
            SparseIntArray sparseIntArray = new SparseIntArray();
            sparseIntArray.put(1024, 5);
            sparseIntArray.put(2048, 5);
            sparseIntArray.put(4096, 5);
            sparseIntArray.put(8192, 5);
            sparseIntArray.put(16384, 5);
            sparseIntArray.put(32768, 5);
            sparseIntArray.put(65536, 5);
            sparseIntArray.put(131072, 5);
            sparseIntArray.put(262144, 2);
            sparseIntArray.put(524288, 2);
            sparseIntArray.put(1048576, 2);
            int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
            if (min < 16777216) {
                min = LogConfiguration.DATA_PACKAGE_SIZE_LIMITS;
            } else if (min < 33554432) {
                min = 6291456;
            } else {
                min = 12582912;
            }
            int min2 = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
            if (min2 < 16777216) {
                min2 /= 2;
            } else {
                min2 = (min2 / 4) * 3;
            }
            a = new ad(min, min2, sparseIntArray);
        } else {
            a = builder.e;
        }
        this.e = a;
        if (builder.f == null) {
            a2 = y.a();
        } else {
            a2 = builder.f;
        }
        this.f = a2;
        if (builder.g == null) {
            SparseIntArray sparseIntArray2 = new SparseIntArray();
            sparseIntArray2.put(16384, 5);
            a = new ad(81920, 1048576, sparseIntArray2);
        } else {
            a = builder.g;
        }
        this.g = a;
        if (builder.h == null) {
            a2 = y.a();
        } else {
            a2 = builder.h;
        }
        this.h = a2;
        this.i = builder.i == null ? "legacy" : builder.i;
        this.j = builder.j;
        this.k = builder.k > 0 ? builder.k : 4194304;
        b.a();
    }

    public final ad a() {
        return this.a;
    }

    public final ae b() {
        return this.b;
    }

    public final d c() {
        return this.d;
    }

    public final ad d() {
        return this.e;
    }

    public final ae e() {
        return this.f;
    }

    public final ad f() {
        return this.c;
    }

    public final ad g() {
        return this.g;
    }

    public final ae h() {
        return this.h;
    }

    public final String i() {
        return this.i;
    }

    public final int j() {
        return this.j;
    }

    public final int k() {
        return this.k;
    }

    public static a l() {
        return new a();
    }
}
