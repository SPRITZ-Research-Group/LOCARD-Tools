package com.google.android.gms.phenotype;

import java.util.Comparator;

final class g implements Comparator<zzi> {
    g() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzi zzi = (zzi) obj;
        zzi zzi2 = (zzi) obj2;
        return zzi.b == zzi2.b ? zzi.a.compareTo(zzi2.a) : zzi.b - zzi2.b;
    }
}
