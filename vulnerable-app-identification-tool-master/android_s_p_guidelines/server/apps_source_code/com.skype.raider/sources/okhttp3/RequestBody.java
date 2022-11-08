package okhttp3;

import c.d;
import c.f;
import c.l;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public abstract class RequestBody {
    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(d dVar) throws IOException;

    public long contentLength() throws IOException {
        return -1;
    }

    public static RequestBody create(@Nullable MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null) {
            charset = contentType.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                contentType = MediaType.parse(contentType + "; charset=utf-8");
            }
        }
        return create(contentType, content.getBytes(charset));
    }

    public static RequestBody create(@Nullable final MediaType contentType, final f content) {
        return new RequestBody() {
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            public long contentLength() throws IOException {
                return (long) content.h();
            }

            public void writeTo(d sink) throws IOException {
                sink.c(content);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType contentType, byte[] content) {
        return create(contentType, content, 0, content.length);
    }

    public static RequestBody create(@Nullable final MediaType contentType, final byte[] content, final int offset, final int byteCount) {
        if (content == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount((long) content.length, (long) offset, (long) byteCount);
        return new RequestBody() {
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            public long contentLength() {
                return (long) byteCount;
            }

            public void writeTo(d sink) throws IOException {
                sink.c(content, offset, byteCount);
            }
        };
    }

    public static RequestBody create(@Nullable final MediaType contentType, final File file) {
        if (file != null) {
            return new RequestBody() {
                @Nullable
                public MediaType contentType() {
                    return contentType;
                }

                public long contentLength() {
                    return file.length();
                }

                public void writeTo(d sink) throws IOException {
                    Closeable source = null;
                    try {
                        source = l.a(file);
                        sink.a(source);
                    } finally {
                        Util.closeQuietly(source);
                    }
                }
            };
        }
        throw new NullPointerException("content == null");
    }
}
