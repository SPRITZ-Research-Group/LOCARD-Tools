package com.facebook.react.modules.network;

import c.c;
import c.d;
import c.l;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class g extends RequestBody {
    private final RequestBody a;
    private final f b;

    public g(RequestBody requestBody, f progressListener) {
        this.a = requestBody;
        this.b = progressListener;
    }

    public final MediaType contentType() {
        return this.a.contentType();
    }

    public final long contentLength() throws IOException {
        return this.a.contentLength();
    }

    public final void writeTo(d sink) throws IOException {
        d sinkWrapper = l.a(new c.g(this, sink) {
            long a = 0;
            long b = 0;
            final /* synthetic */ g c;

            public final void write(c source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (this.b == 0) {
                    this.b = this.c.contentLength();
                }
                this.a += byteCount;
                this.c.b.a(this.a, this.b, this.a == this.b);
            }
        });
        this.a.writeTo(sinkWrapper);
        sinkWrapper.flush();
    }
}
