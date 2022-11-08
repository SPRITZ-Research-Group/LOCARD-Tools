package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.ai;
import defpackage.beg;

public final class o {
    public final int a;
    public final ai[] b;
    public final l c;
    public final Object d;

    public o(ai[] aiVarArr, j[] jVarArr, Object obj) {
        this.b = aiVarArr;
        this.c = new l(jVarArr);
        this.d = obj;
        this.a = aiVarArr.length;
    }

    public final boolean a(int i) {
        return this.b[i] != null;
    }

    public final boolean a(o oVar) {
        if (oVar == null || oVar.c.a != this.c.a) {
            return false;
        }
        for (int i = 0; i < this.c.a; i++) {
            if (!a(oVar, i)) {
                return false;
            }
        }
        return true;
    }

    public final boolean a(o oVar, int i) {
        if (oVar != null && beg.a(this.b[i], oVar.b[i]) && beg.a(this.c.a(i), oVar.c.a(i))) {
            return true;
        }
        return false;
    }
}
