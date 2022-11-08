package com.facebook.ads.internal.view.component.a;

import com.facebook.ads.internal.adapters.a.d;
import com.facebook.ads.internal.adapters.a.h;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.view.component.a;

public final class c {
    private static final int a = u.a.heightPixels;
    private static final int b = u.a.widthPixels;

    public static b a(d dVar) {
        b aVar;
        boolean z = true;
        d a = dVar.k() == 1 ? dVar.g().b().a() : dVar.g().b().b();
        h hVar = (h) dVar.g().d().get(0);
        int g = hVar.c().g();
        int h = hVar.c().h();
        double d = (double) (h > 0 ? ((float) g) / ((float) h) : -1.0f);
        g = dVar.k();
        h = dVar.j();
        if (g != 2) {
            boolean z2;
            if ((a - h) - ((u.b() + (a.a * 2)) + (e.a * 2)) < ((int) (((double) (b - (e.a * 2))) / d))) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                g = 0;
                if (g == 0) {
                    aVar = new a(dVar, a, dVar.k() != 2);
                } else {
                    if (d >= 0.9d) {
                        z = false;
                    }
                    aVar = new e(dVar, z, a);
                }
                aVar.a(hVar, dVar.g().c(), d);
                return aVar;
            }
        }
        g = true;
        if (g == 0) {
            if (d >= 0.9d) {
                z = false;
            }
            aVar = new e(dVar, z, a);
        } else {
            if (dVar.k() != 2) {
            }
            aVar = new a(dVar, a, dVar.k() != 2);
        }
        aVar.a(hVar, dVar.g().c(), d);
        return aVar;
    }
}
