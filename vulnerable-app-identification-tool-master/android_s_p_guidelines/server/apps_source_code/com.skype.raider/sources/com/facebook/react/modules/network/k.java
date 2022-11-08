package com.facebook.react.modules.network;

import android.content.Context;
import android.net.Uri;
import c.d;
import c.f;
import c.l;
import com.facebook.common.logging.FLog;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;

final class k {
    @Nullable
    public static InputStream a(Context context, String fileContentUriStr) {
        try {
            return context.getContentResolver().openInputStream(Uri.parse(fileContentUriStr));
        } catch (Throwable e) {
            FLog.e("React", "Could not retrieve file for contentUri " + fileContentUriStr, e);
            return null;
        }
    }

    @Nullable
    public static RequestBody a(MediaType mediaType, String body) {
        ByteArrayOutputStream gzipByteArrayOutputStream = new ByteArrayOutputStream();
        try {
            OutputStream gzipOutputStream = new GZIPOutputStream(gzipByteArrayOutputStream);
            gzipOutputStream.write(body.getBytes());
            gzipOutputStream.close();
            return RequestBody.create(mediaType, gzipByteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    public static RequestBody a(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            public final MediaType contentType() {
                return mediaType;
            }

            public final long contentLength() {
                try {
                    return (long) inputStream.available();
                } catch (IOException e) {
                    return 0;
                }
            }

            public final void writeTo(d sink) throws IOException {
                Closeable source = null;
                try {
                    source = l.a(inputStream);
                    sink.a(source);
                } finally {
                    Util.closeQuietly(source);
                }
            }
        };
    }

    public static RequestBody a(String method) {
        if (method.equals("POST") || method.equals("PUT") || method.equals("PATCH")) {
            return RequestBody.create(null, f.b);
        }
        return null;
    }
}
