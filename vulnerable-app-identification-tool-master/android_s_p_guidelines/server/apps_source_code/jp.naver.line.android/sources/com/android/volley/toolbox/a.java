package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Looper;
import android.widget.ImageView.ScaleType;
import defpackage.xi;
import defpackage.xk;
import defpackage.xm;
import defpackage.xn;
import java.util.HashMap;

public final class a {
    private final xk a;
    private final c b;
    private final HashMap<String, b> c;
    private final HashMap<String, b> d;

    public final d a(String str, e eVar, int i, int i2, ScaleType scaleType) {
        a aVar = this;
        e eVar2 = eVar;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            StringBuilder stringBuilder = new StringBuilder(str.length() + 12);
            stringBuilder.append("#W");
            stringBuilder.append(i);
            stringBuilder.append("#H");
            stringBuilder.append(i2);
            stringBuilder.append("#S");
            stringBuilder.append(scaleType.ordinal());
            stringBuilder.append(str);
            final String stringBuilder2 = stringBuilder.toString();
            Bitmap a = aVar.b.a();
            d dVar;
            if (a != null) {
                dVar = new d(this, a, str, null, null);
                eVar2.a(dVar, true);
                return dVar;
            }
            dVar = new d(this, null, str, stringBuilder2, eVar);
            eVar2.a(dVar, true);
            b bVar = (b) aVar.c.get(stringBuilder2);
            if (bVar != null) {
                bVar.a(dVar);
                return dVar;
            }
            d dVar2 = dVar;
            xi fVar = new f(str, new xn<Bitmap>(aVar) {
                final /* synthetic */ a b;
            }, i, i2, scaleType, Config.RGB_565, new xm(aVar) {
                final /* synthetic */ a b;
            });
            aVar.a.a(fVar);
            aVar.c.put(stringBuilder2, new b(aVar, fVar, dVar2));
            return dVar2;
        }
        throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
    }
}
