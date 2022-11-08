package com.microsoft.urlrequest;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public final class d extends RequestBody {
    private InputStream a;
    private final boolean b;
    private final int c;
    private final int d;
    private final int e;

    public d(InputStream input, int length, int start, int end) throws IllegalArgumentException {
        FLog.w("StreamingRequestBody", "Streaming upload body  length=" + length + " resumable=true start=" + start + " end=" + end);
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative: " + String.valueOf(length));
        } else if (start > end) {
            throw new IllegalArgumentException("Start offset cannot be greater than end: start=" + String.valueOf(start) + " end=" + String.valueOf(end));
        } else {
            this.a = input;
            this.b = true;
            this.d = start;
            this.e = end;
            this.c = end - start;
            FLog.w("StreamingRequestBody", "Streaming body length: " + String.valueOf(this.c));
        }
    }

    @Nullable
    public final MediaType contentType() {
        return MediaType.parse("application");
    }

    public final long contentLength() throws IOException {
        return (long) this.c;
    }

    public final void writeTo(c.d sink) throws IOException {
        OutputStream output = null;
        try {
            output = sink.c();
            byte[] buffer = new byte[65536];
            int bytesRemaining = this.e - this.d;
            int bytesToRead = 65536;
            if (65536 > bytesRemaining) {
                bytesToRead = bytesRemaining;
            }
            if (bytesToRead <= 0) {
                FLog.w("StreamingRequestBody", "File fully uploaded already");
                this.a.close();
                output.flush();
                if (this.a != null) {
                    this.a.close();
                    return;
                }
                return;
            }
            if (this.d > 0) {
                FLog.w("StreamingRequestBody", "Skipping bytes: " + String.valueOf(this.d));
                this.a.skip((long) this.d);
            }
            FLog.w("StreamingRequestBody", "Reading bytes: " + String.valueOf(bytesToRead));
            int totalRead = 0;
            int read = this.a.read(buffer, 0, bytesToRead);
            while (read != -1 && totalRead < this.c) {
                totalRead += read;
                FLog.w("StreamingRequestBody", "Sending bytes: " + String.valueOf(read));
                FLog.w("StreamingRequestBody", "Total bytes so far: " + String.valueOf(totalRead));
                output.write(buffer, 0, read);
                bytesRemaining = this.c - totalRead;
                FLog.w("StreamingRequestBody", "Remaining bytes: " + String.valueOf(bytesRemaining));
                if (bytesRemaining <= 0) {
                    break;
                }
                if (bytesRemaining > 65536) {
                    bytesToRead = 65536;
                } else {
                    bytesToRead = bytesRemaining;
                }
                FLog.w("StreamingRequestBody", "Reading bytes: " + String.valueOf(bytesToRead));
                read = this.a.read(buffer, 0, bytesToRead);
            }
            output.flush();
            if (this.a != null) {
                this.a.close();
            }
        } catch (Throwable th) {
            if (output != null) {
                output.flush();
            }
            if (this.a != null) {
                this.a.close();
            }
        }
    }
}
