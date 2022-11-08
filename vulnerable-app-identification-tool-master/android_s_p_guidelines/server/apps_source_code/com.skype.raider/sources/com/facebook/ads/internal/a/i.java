package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import com.facebook.ads.internal.j.a.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.g;
import java.util.Map;

class i extends h {
    private static final String e = i.class.getSimpleName();
    private final Uri f;
    private final Map<String, String> g;

    i(Context context, c cVar, String str, Uri uri, Map<String, String> map, l lVar) {
        super(context, cVar, str, lVar);
        this.f = uri;
        this.g = map;
    }

    public final a a() {
        return a.OPEN_LINK;
    }

    final void c() {
        a aVar = null;
        try {
            g gVar = new g();
            g.a(this.a, Uri.parse(this.f.getQueryParameter("link")), this.c);
        } catch (Exception e) {
            new StringBuilder("Failed to open link url: ").append(this.f.toString());
            aVar = a.CANNOT_OPEN;
        }
        a(this.g, aVar);
    }
}
