package com.google.android.exoplayer2;

import defpackage.beg;

final class o implements Comparable<o> {
    public final ad a;
    public int b;
    public long c;
    public Object d;

    public final /* synthetic */ int compareTo(Object obj) {
        o oVar = (o) obj;
        if ((this.d == null ? 1 : null) != (oVar.d == null ? 1 : null)) {
            if (this.d != null) {
                return -1;
            }
            return 1;
        } else if (this.d == null) {
            return 0;
        } else {
            int i = this.b - oVar.b;
            if (i != 0) {
                return i;
            }
            return beg.b(this.c, oVar.c);
        }
    }

    public o(ad adVar) {
        this.a = adVar;
    }

    public final void a(int i, long j, Object obj) {
        this.b = i;
        this.c = j;
        this.d = obj;
    }
}
