package okhttp3.internal.http;

import c.e;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

public final class RealResponseBody extends ResponseBody {
    private final long contentLength;
    @Nullable
    private final String contentTypeString;
    private final e source;

    public RealResponseBody(@Nullable String contentTypeString, long contentLength, e source) {
        this.contentTypeString = contentTypeString;
        this.contentLength = contentLength;
        this.source = source;
    }

    public final MediaType contentType() {
        return this.contentTypeString != null ? MediaType.parse(this.contentTypeString) : null;
    }

    public final long contentLength() {
        return this.contentLength;
    }

    public final e source() {
        return this.source;
    }
}
