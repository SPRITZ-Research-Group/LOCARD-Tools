package okhttp3.internal.huc;

import c.c;
import c.d;
import java.io.IOException;
import okhttp3.Request;

final class BufferedRequestBody extends OutputStreamRequestBody {
    final c buffer = new c();
    long contentLength = -1;

    BufferedRequestBody(long expectedContentLength) {
        initOutputStream(this.buffer, expectedContentLength);
    }

    public final long contentLength() throws IOException {
        return this.contentLength;
    }

    public final Request prepareToSendRequest(Request request) throws IOException {
        if (request.header("Content-Length") != null) {
            return request;
        }
        outputStream().close();
        this.contentLength = this.buffer.a();
        return request.newBuilder().removeHeader("Transfer-Encoding").header("Content-Length", Long.toString(this.buffer.a())).build();
    }

    public final void writeTo(d sink) throws IOException {
        this.buffer.a(sink.b(), 0, this.buffer.a());
    }
}
