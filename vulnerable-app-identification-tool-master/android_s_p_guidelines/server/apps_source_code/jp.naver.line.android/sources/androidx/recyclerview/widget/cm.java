package androidx.recyclerview.widget;

import defpackage.bu;
import defpackage.by;

final class cm {
    final bu<cb, cn> a = new bu();
    final by<cb> b = new by();

    cm() {
    }

    final void a() {
        this.a.clear();
        this.b.c();
    }

    final void a(cb cbVar, bg bgVar) {
        cn cnVar = (cn) this.a.get(cbVar);
        if (cnVar == null) {
            cnVar = cn.a();
            this.a.put(cbVar, cnVar);
        }
        cnVar.b = bgVar;
        cnVar.a |= 4;
    }

    final boolean a(cb cbVar) {
        cn cnVar = (cn) this.a.get(cbVar);
        return (cnVar == null || (cnVar.a & 1) == 0) ? false : true;
    }

    final bg b(cb cbVar) {
        return a(cbVar, 4);
    }

    final bg c(cb cbVar) {
        return a(cbVar, 8);
    }

    private bg a(cb cbVar, int i) {
        int a = this.a.a((Object) cbVar);
        if (a < 0) {
            return null;
        }
        cn cnVar = (cn) this.a.c(a);
        if (cnVar == null || (cnVar.a & i) == 0) {
            return null;
        }
        bg bgVar;
        cnVar.a &= i ^ -1;
        if (i == 4) {
            bgVar = cnVar.b;
        } else if (i == 8) {
            bgVar = cnVar.c;
        } else {
            throw new IllegalArgumentException("Must provide flag PRE or POST");
        }
        if ((cnVar.a & 12) == 0) {
            this.a.d(a);
            cn.a(cnVar);
        }
        return bgVar;
    }

    final void a(long j, cb cbVar) {
        this.b.b(j, cbVar);
    }

    final void b(cb cbVar, bg bgVar) {
        cn cnVar = (cn) this.a.get(cbVar);
        if (cnVar == null) {
            cnVar = cn.a();
            this.a.put(cbVar, cnVar);
        }
        cnVar.c = bgVar;
        cnVar.a |= 8;
    }

    final void d(cb cbVar) {
        cn cnVar = (cn) this.a.get(cbVar);
        if (cnVar == null) {
            cnVar = cn.a();
            this.a.put(cbVar, cnVar);
        }
        cnVar.a |= 1;
    }

    final void e(cb cbVar) {
        cn cnVar = (cn) this.a.get(cbVar);
        if (cnVar != null) {
            cnVar.a &= -2;
        }
    }

    final void f(cb cbVar) {
        for (int b = this.b.b() - 1; b >= 0; b--) {
            if (cbVar == this.b.c(b)) {
                this.b.a(b);
                break;
            }
        }
        cn cnVar = (cn) this.a.remove(cbVar);
        if (cnVar != null) {
            cn.a(cnVar);
        }
    }
}
