package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.upstream.HttpDataSource.a;
import com.google.android.exoplayer2.upstream.HttpDataSource.e;
import com.skype.Defines;

public final class m extends a {
    private final String a;
    private final p<? super f> b;
    private final int c;
    private final int d;
    private final boolean e;

    public m(String userAgent, p<? super f> listener) {
        this(userAgent, listener, (byte) 0);
    }

    private m(String userAgent, p<? super f> listener, byte b) {
        this.a = userAgent;
        this.b = listener;
        this.c = Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE;
        this.d = Defines.SKYLIB_MESSAGE_MAX_BODY_SIZE;
        this.e = false;
    }

    protected final /* synthetic */ HttpDataSource a(e eVar) {
        return new l(this.a, this.b, this.c, this.d, this.e, eVar);
    }
}
