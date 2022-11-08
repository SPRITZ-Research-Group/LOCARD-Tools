package okhttp3.internal.huc;

import c.c;
import c.d;
import c.l;
import c.m;
import java.io.IOException;
import okhttp3.internal.http.UnrepeatableRequestBody;

final class StreamedRequestBody extends OutputStreamRequestBody implements UnrepeatableRequestBody {
    private final m pipe = new m();

    StreamedRequestBody(long expectedContentLength) {
        initOutputStream(l.a(this.pipe.b()), expectedContentLength);
    }

    public final void writeTo(d sink) throws IOException {
        c buffer = new c();
        while (this.pipe.a().read(buffer, 8192) != -1) {
            sink.write(buffer, buffer.a());
        }
    }
}
