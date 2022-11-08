package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.ab;

final class ah {
    final String a;
    final String b;
    final long c;
    final long d;
    final long e;
    final long f;
    final Long g;
    final Long h;
    final Boolean i;

    ah(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Boolean bool) {
        ab.a(str);
        ab.a(str2);
        ab.b(j >= 0);
        ab.b(j2 >= 0);
        ab.b(j4 >= 0);
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = j2;
        this.e = j3;
        this.f = j4;
        this.g = l;
        this.h = l2;
        this.i = bool;
    }

    final ah a() {
        return new ah(this.a, this.b, this.c + 1, this.d + 1, this.e, this.f, this.g, this.h, this.i);
    }

    final ah a(long j) {
        return new ah(this.a, this.b, this.c, this.d, j, this.f, this.g, this.h, this.i);
    }

    final ah a(Long l, Long l2, Boolean bool) {
        Boolean bool2 = (bool == null || bool.booleanValue()) ? bool : null;
        return new ah(this.a, this.b, this.c, this.d, this.e, this.f, l, l2, bool2);
    }

    final ah b(long j) {
        return new ah(this.a, this.b, this.c, this.d, this.e, j, this.g, this.h, this.i);
    }
}
