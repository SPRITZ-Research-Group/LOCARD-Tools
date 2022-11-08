package com.google.android.gms.internal.clearcut;

final class bc implements bj {
    private bj[] a;

    bc(bj... bjVarArr) {
        this.a = bjVarArr;
    }

    public final boolean a(Class<?> cls) {
        for (bj a : this.a) {
            if (a.a(cls)) {
                return true;
            }
        }
        return false;
    }

    public final bi b(Class<?> cls) {
        for (bj bjVar : this.a) {
            if (bjVar.a(cls)) {
                return bjVar.b(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
