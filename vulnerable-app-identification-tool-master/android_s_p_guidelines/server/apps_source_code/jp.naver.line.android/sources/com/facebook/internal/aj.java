package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.s;
import java.util.Locale;

public final class aj {
    private Context a;
    private Uri b;
    private al c;
    private boolean d;
    private Object e;

    /* synthetic */ aj(ak akVar, byte b) {
        this(akVar);
    }

    public static Uri a(String str, int i, int i2) {
        bn.a(str, "userId");
        i = Math.max(i, 0);
        i2 = Math.max(i2, 0);
        if (i == 0 && i2 == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        Builder path = Uri.parse(bg.b()).buildUpon().path(String.format(Locale.US, "%s/%s/picture", new Object[]{s.g(), str}));
        if (i2 != 0) {
            path.appendQueryParameter("height", String.valueOf(i2));
        }
        if (i != 0) {
            path.appendQueryParameter("width", String.valueOf(i));
        }
        path.appendQueryParameter("migration_overrides", "{october_2012:true}");
        return path.build();
    }

    private aj(ak akVar) {
        this.a = akVar.a;
        this.b = akVar.b;
        this.c = akVar.c;
        this.d = akVar.d;
        this.e = akVar.e == null ? new Object() : akVar.e;
    }

    public final Context a() {
        return this.a;
    }

    public final Uri b() {
        return this.b;
    }

    public final al c() {
        return this.c;
    }

    public final boolean d() {
        return this.d;
    }

    public final Object e() {
        return this.e;
    }
}
