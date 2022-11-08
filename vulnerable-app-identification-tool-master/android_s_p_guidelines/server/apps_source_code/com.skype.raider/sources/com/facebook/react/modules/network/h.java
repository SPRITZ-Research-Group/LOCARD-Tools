package com.facebook.react.modules.network;

import c.c;
import c.e;
import c.l;
import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

public final class h extends ResponseBody {
    private final ResponseBody a;
    private final f b;
    @Nullable
    private e c;
    private long d = 0;

    public h(ResponseBody responseBody, f progressListener) {
        this.a = responseBody;
        this.b = progressListener;
    }

    public final MediaType contentType() {
        return this.a.contentType();
    }

    public final long contentLength() {
        return this.a.contentLength();
    }

    public final long a() {
        return this.d;
    }

    public final e source() {
        if (this.c == null) {
            this.c = l.a(new c.h(this, this.a.source()) {
                final /* synthetic */ h a;

                public final long read(c sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    this.a.d = (bytesRead != -1 ? bytesRead : 0) + this.a.d;
                    this.a.b.a(this.a.d, this.a.a.contentLength(), bytesRead == -1);
                    return bytesRead;
                }
            });
        }
        return this.c;
    }
}
