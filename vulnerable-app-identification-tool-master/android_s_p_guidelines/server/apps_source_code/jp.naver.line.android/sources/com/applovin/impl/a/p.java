package com.applovin.impl.a;

import java.util.Comparator;

class p implements Comparator<r> {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public int a(r rVar, r rVar2) {
        return Integer.compare(rVar.e(), rVar2.e());
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((r) obj, (r) obj2);
    }
}
