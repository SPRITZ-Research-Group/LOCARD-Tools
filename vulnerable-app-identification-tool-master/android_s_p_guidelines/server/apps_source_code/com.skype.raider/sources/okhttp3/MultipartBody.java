package okhttp3;

import c.c;
import c.d;
import c.f;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class MultipartBody extends RequestBody {
    public static final MediaType ALTERNATIVE = MediaType.get("multipart/alternative");
    private static final byte[] COLONSPACE = new byte[]{(byte) 58, (byte) 32};
    private static final byte[] CRLF = new byte[]{(byte) 13, (byte) 10};
    private static final byte[] DASHDASH = new byte[]{(byte) 45, (byte) 45};
    public static final MediaType DIGEST = MediaType.get("multipart/digest");
    public static final MediaType FORM = MediaType.get("multipart/form-data");
    public static final MediaType MIXED = MediaType.get("multipart/mixed");
    public static final MediaType PARALLEL = MediaType.get("multipart/parallel");
    private final f boundary;
    private long contentLength = -1;
    private final MediaType contentType;
    private final MediaType originalType;
    private final List<Part> parts;

    public static final class Builder {
        private final f boundary;
        private final List<Part> parts;
        private MediaType type;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String boundary) {
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
            this.boundary = f.a(boundary);
        }

        public final Builder setType(MediaType type) {
            if (type == null) {
                throw new NullPointerException("type == null");
            } else if (type.type().equals("multipart")) {
                this.type = type;
                return this;
            } else {
                throw new IllegalArgumentException("multipart != " + type);
            }
        }

        public final Builder addPart(RequestBody body) {
            return addPart(Part.create(body));
        }

        public final Builder addPart(@Nullable Headers headers, RequestBody body) {
            return addPart(Part.create(headers, body));
        }

        public final Builder addFormDataPart(String name, String value) {
            return addPart(Part.createFormData(name, value));
        }

        public final Builder addFormDataPart(String name, @Nullable String filename, RequestBody body) {
            return addPart(Part.createFormData(name, filename, body));
        }

        public final Builder addPart(Part part) {
            if (part == null) {
                throw new NullPointerException("part == null");
            }
            this.parts.add(part);
            return this;
        }

        public final MultipartBody build() {
            if (!this.parts.isEmpty()) {
                return new MultipartBody(this.boundary, this.type, this.parts);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    public static final class Part {
        final RequestBody body;
        @Nullable
        final Headers headers;

        public static Part create(RequestBody body) {
            return create(null, body);
        }

        public static Part create(@Nullable Headers headers, RequestBody body) {
            if (body == null) {
                throw new NullPointerException("body == null");
            } else if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            } else if (headers == null || headers.get("Content-Length") == null) {
                return new Part(headers, body);
            } else {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
        }

        public static Part createFormData(String name, String value) {
            return createFormData(name, null, RequestBody.create(null, value));
        }

        public static Part createFormData(String name, @Nullable String filename, RequestBody body) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder disposition = new StringBuilder("form-data; name=");
            MultipartBody.appendQuotedString(disposition, name);
            if (filename != null) {
                disposition.append("; filename=");
                MultipartBody.appendQuotedString(disposition, filename);
            }
            return create(Headers.of("Content-Disposition", disposition.toString()), body);
        }

        private Part(@Nullable Headers headers, RequestBody body) {
            this.headers = headers;
            this.body = body;
        }

        @Nullable
        public final Headers headers() {
            return this.headers;
        }

        public final RequestBody body() {
            return this.body;
        }
    }

    MultipartBody(f boundary, MediaType type, List<Part> parts) {
        this.boundary = boundary;
        this.originalType = type;
        this.contentType = MediaType.get(type + "; boundary=" + boundary.a());
        this.parts = Util.immutableList((List) parts);
    }

    public final MediaType type() {
        return this.originalType;
    }

    public final String boundary() {
        return this.boundary.a();
    }

    public final int size() {
        return this.parts.size();
    }

    public final List<Part> parts() {
        return this.parts;
    }

    public final Part part(int index) {
        return (Part) this.parts.get(index);
    }

    public final MediaType contentType() {
        return this.contentType;
    }

    public final long contentLength() throws IOException {
        long result = this.contentLength;
        if (result != -1) {
            return result;
        }
        result = writeOrCountBytes(null, true);
        this.contentLength = result;
        return result;
    }

    public final void writeTo(d sink) throws IOException {
        writeOrCountBytes(sink, false);
    }

    private long writeOrCountBytes(@Nullable d sink, boolean countBytes) throws IOException {
        long byteCount = 0;
        c byteCountBuffer = null;
        if (countBytes) {
            byteCountBuffer = new c();
            sink = byteCountBuffer;
        }
        int partCount = this.parts.size();
        for (int p = 0; p < partCount; p++) {
            Part part = (Part) this.parts.get(p);
            Headers headers = part.headers;
            RequestBody body = part.body;
            sink.c(DASHDASH);
            sink.c(this.boundary);
            sink.c(CRLF);
            if (headers != null) {
                int headerCount = headers.size();
                for (int h = 0; h < headerCount; h++) {
                    sink.b(headers.name(h)).c(COLONSPACE).b(headers.value(h)).c(CRLF);
                }
            }
            MediaType contentType = body.contentType();
            if (contentType != null) {
                sink.b("Content-Type: ").b(contentType.toString()).c(CRLF);
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                sink.b("Content-Length: ").m(contentLength).c(CRLF);
            } else if (countBytes) {
                byteCountBuffer.t();
                return -1;
            }
            sink.c(CRLF);
            if (countBytes) {
                byteCount += contentLength;
            } else {
                body.writeTo(sink);
            }
            sink.c(CRLF);
        }
        sink.c(DASHDASH);
        sink.c(this.boundary);
        sink.c(DASHDASH);
        sink.c(CRLF);
        if (countBytes) {
            byteCount += byteCountBuffer.a();
            byteCountBuffer.t();
        }
        return byteCount;
    }

    static StringBuilder appendQuotedString(StringBuilder target, String key) {
        target.append('\"');
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char ch = key.charAt(i);
            switch (ch) {
                case 10:
                    target.append("%0A");
                    break;
                case 13:
                    target.append("%0D");
                    break;
                case '\"':
                    target.append("%22");
                    break;
                default:
                    target.append(ch);
                    break;
            }
        }
        target.append('\"');
        return target;
    }
}
