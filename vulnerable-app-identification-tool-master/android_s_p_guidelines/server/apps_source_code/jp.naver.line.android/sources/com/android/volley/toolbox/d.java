package com.android.volley.toolbox;

import android.graphics.Bitmap;

public final class d {
    final /* synthetic */ a a;
    private Bitmap b;
    private final e c;
    private final String d;
    private final String e;

    public d(a aVar, Bitmap bitmap, String str, String str2, e eVar) {
        this.a = aVar;
        this.b = bitmap;
        this.e = str;
        this.d = str2;
        this.c = eVar;
    }

    public final void a() {
        if (this.c != null) {
            b bVar = (b) this.a.c.get(this.d);
            if (bVar != null) {
                if (bVar.b(this)) {
                    this.a.c.remove(this.d);
                }
                return;
            }
            bVar = (b) this.a.d.get(this.d);
            if (bVar != null) {
                bVar.b(this);
                if (bVar.c.size() == 0) {
                    this.a.d.remove(this.d);
                }
            }
        }
    }

    public final Bitmap b() {
        return this.b;
    }

    public final String c() {
        return this.e;
    }
}
