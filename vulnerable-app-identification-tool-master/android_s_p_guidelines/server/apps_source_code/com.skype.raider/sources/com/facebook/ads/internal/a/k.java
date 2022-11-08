package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import com.facebook.ads.internal.j.a.a;
import com.facebook.ads.internal.m.c;
import com.facebook.ads.internal.q.c.g;

public class k extends b {
    private static final String d = k.class.getSimpleName();
    private final Uri e;

    public k(Context context, c cVar, String str, Uri uri) {
        super(context, cVar, str);
        this.e = uri;
    }

    public final a a() {
        return a.OPEN_LINK;
    }

    public final void b() {
        try {
            this.e.toString();
            g gVar = new g();
            g.a(this.a, this.e, this.c);
        } catch (Exception e) {
            new StringBuilder("Failed to open link url: ").append(this.e.toString());
        }
    }
}
