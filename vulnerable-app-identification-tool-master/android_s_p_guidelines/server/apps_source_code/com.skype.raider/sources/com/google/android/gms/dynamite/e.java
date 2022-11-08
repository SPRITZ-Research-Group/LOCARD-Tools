package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule.b;
import com.google.android.gms.dynamite.DynamiteModule.b.a;

final class e implements b {
    e() {
    }

    public final b.b a(Context context, String str, a aVar) throws DynamiteModule.a {
        b.b bVar = new b.b();
        bVar.a = aVar.a(context, str);
        if (bVar.a != 0) {
            bVar.c = -1;
        } else {
            bVar.b = aVar.a(context, str, true);
            if (bVar.b != 0) {
                bVar.c = 1;
            }
        }
        return bVar;
    }
}
