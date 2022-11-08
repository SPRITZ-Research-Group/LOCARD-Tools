package com.google.android.exoplayer2;

import android.util.Pair;
import defpackage.bcz;

public abstract class ao {
    public static final ao a = new ao() {
        public final int a(Object obj) {
            return -1;
        }

        public final int b() {
            return 0;
        }

        public final int d() {
            return 0;
        }

        public final aq a(int i, aq aqVar, long j) {
            throw new IndexOutOfBoundsException();
        }

        public final ap a(int i, ap apVar, boolean z) {
            throw new IndexOutOfBoundsException();
        }

        public final Object a(int i) {
            throw new IndexOutOfBoundsException();
        }
    };

    public abstract int a(Object obj);

    public abstract ap a(int i, ap apVar, boolean z);

    public abstract aq a(int i, aq aqVar, long j);

    public abstract Object a(int i);

    public abstract int b();

    public abstract int d();

    public final boolean a() {
        return b() == 0;
    }

    private int e() {
        return a() ? -1 : b() - 1;
    }

    public final int c() {
        return a() ? -1 : 0;
    }

    public final boolean b(int i, ap apVar, aq aqVar, int i2) {
        return a(i, apVar, aqVar, i2) == -1;
    }

    public final Pair<Object, Long> a(aq aqVar, ap apVar, int i, long j) {
        return a(aqVar, apVar, i, j, 0);
    }

    public final Pair<Object, Long> a(aq aqVar, ap apVar, int i, long j, long j2) {
        bcz.a(i, b());
        a(i, aqVar, j2);
        if (j == -9223372036854775807L) {
            j = aqVar.h;
            if (j == -9223372036854775807L) {
                return null;
            }
        }
        i = aqVar.f;
        long j3 = aqVar.j + j;
        long j4 = a(i, apVar, true).d;
        while (j4 != -9223372036854775807L && j3 >= j4 && i < aqVar.g) {
            j3 -= j4;
            i++;
            j4 = a(i, apVar, true).d;
        }
        return Pair.create(apVar.b, Long.valueOf(j3));
    }

    public final ap a(Object obj, ap apVar) {
        return a(a(obj), apVar, true);
    }

    public final int a(int i, ap apVar, aq aqVar, int i2) {
        int i3 = a(i, apVar, false).c;
        if (a(i3, aqVar, 0).g != i) {
            return i + 1;
        }
        switch (i2) {
            case 0:
                if (i3 != e()) {
                    i3++;
                    break;
                }
                i3 = -1;
                break;
            case 1:
                break;
            case 2:
                if (i3 != e()) {
                    i3++;
                    break;
                }
                i3 = c();
                break;
            default:
                throw new IllegalStateException();
        }
        if (i3 == -1) {
            return -1;
        }
        return a(i3, aqVar, 0).f;
    }
}
