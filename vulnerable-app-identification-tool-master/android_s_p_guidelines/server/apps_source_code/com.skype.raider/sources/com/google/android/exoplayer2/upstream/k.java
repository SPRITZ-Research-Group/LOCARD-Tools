package com.google.android.exoplayer2.upstream;

import android.content.Context;
import com.google.android.exoplayer2.upstream.f.a;

public final class k implements a {
    private final Context a;
    private final p<? super f> b;
    private final a c;

    public k(Context context, String userAgent, p<? super f> listener) {
        this(context, (p) listener, new m(userAgent, listener));
    }

    private k(Context context, p<? super f> listener, a baseDataSourceFactory) {
        this.a = context.getApplicationContext();
        this.b = listener;
        this.c = baseDataSourceFactory;
    }

    public final /* synthetic */ f a() {
        return new j(this.a, this.b, this.c.a());
    }
}
