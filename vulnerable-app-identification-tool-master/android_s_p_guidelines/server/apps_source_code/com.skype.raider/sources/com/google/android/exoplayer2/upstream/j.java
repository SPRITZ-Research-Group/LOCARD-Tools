package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.net.Uri;
import com.google.android.exoplayer2.d.a;
import com.google.android.exoplayer2.d.s;
import java.io.IOException;

public final class j implements f {
    private final f a;
    private final f b;
    private final f c;
    private final f d;
    private f e;

    public j(Context context, p<? super f> listener, f baseDataSource) {
        this.a = (f) a.a((Object) baseDataSource);
        this.b = new n(listener);
        this.c = new c(context, listener);
        this.d = new e(context, listener);
    }

    public final long a(DataSpec dataSpec) throws IOException {
        a.b(this.e == null);
        String scheme = dataSpec.a.getScheme();
        if (s.a(dataSpec.a)) {
            if (dataSpec.a.getPath().startsWith("/android_asset/")) {
                this.e = this.c;
            } else {
                this.e = this.b;
            }
        } else if ("asset".equals(scheme)) {
            this.e = this.c;
        } else if ("content".equals(scheme)) {
            this.e = this.d;
        } else {
            this.e = this.a;
        }
        return this.e.a(dataSpec);
    }

    public final int a(byte[] buffer, int offset, int readLength) throws IOException {
        return this.e.a(buffer, offset, readLength);
    }

    public final Uri a() {
        return this.e == null ? null : this.e.a();
    }

    public final void b() throws IOException {
        if (this.e != null) {
            try {
                this.e.b();
            } finally {
                this.e = null;
            }
        }
    }
}
