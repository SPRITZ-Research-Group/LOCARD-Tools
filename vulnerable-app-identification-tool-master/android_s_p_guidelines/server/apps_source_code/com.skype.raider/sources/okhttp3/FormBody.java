package okhttp3;

import c.c;
import c.d;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class FormBody extends RequestBody {
    private static final MediaType CONTENT_TYPE = MediaType.get("application/x-www-form-urlencoded");
    private final List<String> encodedNames;
    private final List<String> encodedValues;

    public static final class Builder {
        private final Charset charset;
        private final List<String> names;
        private final List<String> values;

        public Builder() {
            this(null);
        }

        public Builder(Charset charset) {
            this.names = new ArrayList();
            this.values = new ArrayList();
            this.charset = charset;
        }

        public final Builder add(String name, String value) {
            if (name == null) {
                throw new NullPointerException("name == null");
            } else if (value == null) {
                throw new NullPointerException("value == null");
            } else {
                this.names.add(HttpUrl.canonicalize(name, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.charset));
                this.values.add(HttpUrl.canonicalize(value, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.charset));
                return this;
            }
        }

        public final Builder addEncoded(String name, String value) {
            if (name == null) {
                throw new NullPointerException("name == null");
            } else if (value == null) {
                throw new NullPointerException("value == null");
            } else {
                this.names.add(HttpUrl.canonicalize(name, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.charset));
                this.values.add(HttpUrl.canonicalize(value, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.charset));
                return this;
            }
        }

        public final FormBody build() {
            return new FormBody(this.names, this.values);
        }
    }

    FormBody(List<String> encodedNames, List<String> encodedValues) {
        this.encodedNames = Util.immutableList((List) encodedNames);
        this.encodedValues = Util.immutableList((List) encodedValues);
    }

    public final int size() {
        return this.encodedNames.size();
    }

    public final String encodedName(int index) {
        return (String) this.encodedNames.get(index);
    }

    public final String name(int index) {
        return HttpUrl.percentDecode(encodedName(index), true);
    }

    public final String encodedValue(int index) {
        return (String) this.encodedValues.get(index);
    }

    public final String value(int index) {
        return HttpUrl.percentDecode(encodedValue(index), true);
    }

    public final MediaType contentType() {
        return CONTENT_TYPE;
    }

    public final long contentLength() {
        return writeOrCountBytes(null, true);
    }

    public final void writeTo(d sink) throws IOException {
        writeOrCountBytes(sink, false);
    }

    private long writeOrCountBytes(@Nullable d sink, boolean countBytes) {
        c buffer;
        if (countBytes) {
            buffer = new c();
        } else {
            buffer = sink.b();
        }
        int size = this.encodedNames.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.b(38);
            }
            buffer.a((String) this.encodedNames.get(i));
            buffer.b(61);
            buffer.a((String) this.encodedValues.get(i));
        }
        if (!countBytes) {
            return 0;
        }
        long byteCount = buffer.a();
        buffer.t();
        return byteCount;
    }
}
