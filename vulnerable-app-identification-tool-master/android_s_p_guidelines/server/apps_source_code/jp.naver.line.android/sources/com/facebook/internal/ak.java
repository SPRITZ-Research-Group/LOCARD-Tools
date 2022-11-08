package com.facebook.internal;

import android.content.Context;
import android.net.Uri;

public final class ak {
    private Context a;
    private Uri b;
    private al c;
    private boolean d;
    private Object e;

    public ak(Context context, Uri uri) {
        bn.a((Object) uri, "imageUri");
        this.a = context;
        this.b = uri;
    }

    public final ak a(al alVar) {
        this.c = alVar;
        return this;
    }

    public final ak a(Object obj) {
        this.e = obj;
        return this;
    }

    public final ak a(boolean z) {
        this.d = z;
        return this;
    }

    public final aj a() {
        return new aj();
    }
}
