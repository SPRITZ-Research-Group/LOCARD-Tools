package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.d.m;
import com.google.android.exoplayer2.d.s;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface HttpDataSource extends f {
    public static final m<String> a = new m<String>() {
        public final /* synthetic */ boolean a(Object obj) {
            String d = s.d((String) obj);
            return (TextUtils.isEmpty(d) || ((d.contains("text") && !d.contains("text/vtt")) || d.contains("html") || d.contains("xml"))) ? false : true;
        }
    };

    public static class HttpDataSourceException extends IOException {
        public final int a;
        public final DataSpec b;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        public HttpDataSourceException(String message, DataSpec dataSpec) {
            super(message);
            this.b = dataSpec;
            this.a = 1;
        }

        public HttpDataSourceException(IOException cause, DataSpec dataSpec, int type) {
            super(cause);
            this.b = dataSpec;
            this.a = type;
        }

        public HttpDataSourceException(String message, IOException cause, DataSpec dataSpec) {
            super(message, cause);
            this.b = dataSpec;
            this.a = 1;
        }
    }

    public interface b extends com.google.android.exoplayer2.upstream.f.a {
    }

    public static abstract class a implements b {
        private final e a = new e();

        protected abstract HttpDataSource a(e eVar);

        public final /* bridge */ /* synthetic */ f a() {
            return a(this.a);
        }
    }

    public static final class c extends HttpDataSourceException {
        public final String c;

        public c(String contentType, DataSpec dataSpec) {
            super("Invalid content type: " + contentType, dataSpec);
            this.c = contentType;
        }
    }

    public static final class d extends HttpDataSourceException {
        public final int c;
        public final Map<String, List<String>> d;

        public d(int responseCode, Map<String, List<String>> headerFields, DataSpec dataSpec) {
            super("Response code: " + responseCode, dataSpec);
            this.c = responseCode;
            this.d = headerFields;
        }
    }

    public static final class e {
        private final Map<String, String> a = new HashMap();
        private Map<String, String> b;

        public final synchronized Map<String, String> a() {
            if (this.b == null) {
                this.b = Collections.unmodifiableMap(new HashMap(this.a));
            }
            return this.b;
        }
    }
}
