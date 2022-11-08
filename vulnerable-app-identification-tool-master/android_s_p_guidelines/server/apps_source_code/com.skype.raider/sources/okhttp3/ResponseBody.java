package okhttp3;

import c.c;
import c.e;
import c.f;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public abstract class ResponseBody implements Closeable {
    private Reader reader;

    static final class BomAwareReader extends Reader {
        private final Charset charset;
        private boolean closed;
        private Reader delegate;
        private final e source;

        BomAwareReader(e source, Charset charset) {
            this.source = source;
            this.charset = charset;
        }

        public final int read(char[] cbuf, int off, int len) throws IOException {
            if (this.closed) {
                throw new IOException("Stream closed");
            }
            Reader delegate = this.delegate;
            if (delegate == null) {
                delegate = new InputStreamReader(this.source.f(), Util.bomAwareCharset(this.source, this.charset));
                this.delegate = delegate;
            }
            return delegate.read(cbuf, off, len);
        }

        public final void close() throws IOException {
            this.closed = true;
            if (this.delegate != null) {
                this.delegate.close();
            } else {
                this.source.close();
            }
        }
    }

    public abstract long contentLength();

    @Nullable
    public abstract MediaType contentType();

    public abstract e source();

    public final InputStream byteStream() {
        return source().f();
    }

    public final byte[] bytes() throws IOException {
        long contentLength = contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        Closeable source = source();
        try {
            byte[] bytes = source.s();
            if (contentLength == -1 || contentLength == ((long) bytes.length)) {
                return bytes;
            }
            throw new IOException("Content-Length (" + contentLength + ") and stream length (" + bytes.length + ") disagree");
        } finally {
            Util.closeQuietly(source);
        }
    }

    public final Reader charStream() {
        Reader r = this.reader;
        if (r != null) {
            return r;
        }
        r = new BomAwareReader(source(), charset());
        this.reader = r;
        return r;
    }

    public final String string() throws IOException {
        Closeable source = source();
        try {
            String a = source.a(Util.bomAwareCharset(source, charset()));
            return a;
        } finally {
            Util.closeQuietly(source);
        }
    }

    private Charset charset() {
        MediaType contentType = contentType();
        return contentType != null ? contentType.charset(Util.UTF_8) : Util.UTF_8;
    }

    public void close() {
        Util.closeQuietly(source());
    }

    public static ResponseBody create(@Nullable MediaType contentType, String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null) {
            charset = contentType.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                contentType = MediaType.parse(contentType + "; charset=utf-8");
            }
        }
        c buffer = new c().a(content, 0, content.length(), charset);
        return create(contentType, buffer.a(), buffer);
    }

    public static ResponseBody create(@Nullable MediaType contentType, byte[] content) {
        return create(contentType, (long) content.length, new c().b(content));
    }

    public static ResponseBody create(@Nullable MediaType contentType, f content) {
        return create(contentType, (long) content.h(), new c().a(content));
    }

    public static ResponseBody create(@Nullable final MediaType contentType, final long contentLength, final e content) {
        if (content != null) {
            return new ResponseBody() {
                @Nullable
                public MediaType contentType() {
                    return contentType;
                }

                public long contentLength() {
                    return contentLength;
                }

                public e source() {
                    return content;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
