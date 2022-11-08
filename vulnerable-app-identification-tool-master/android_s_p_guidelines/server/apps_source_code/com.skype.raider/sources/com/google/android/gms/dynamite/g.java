package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.b;
import com.google.android.gms.dynamite.DynamiteModule.b.a;

final class g implements b {
    g() {
    }

    public final b.b a(Context context, String str, a aVar) throws DynamiteModule.a {
        b.b bVar = new b.b();
        bVar.a = aVar.a(context, str);
        if (bVar.a != 0) {
            bVar.b = aVar.a(context, str, false);
        } else {
            bVar.b = aVar.a(context, str, true);
        }
        if (bVar.a == 0 && bVar.b == 0) {
            bVar.c = 0;
        } else if (bVar.a >= bVar.b) {
            bVar.c = -1;
        } else {
            bVar.c = 1;
        }
        return bVar;
    }
}
