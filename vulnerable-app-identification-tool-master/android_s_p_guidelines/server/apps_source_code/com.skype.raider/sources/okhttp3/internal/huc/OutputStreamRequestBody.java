package okhttp3.internal.huc;

import c.d;
import c.u;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

abstract class OutputStreamRequestBody extends RequestBody {
    boolean closed;
    private long expectedContentLength;
    private OutputStream outputStream;
    private u timeout;

    OutputStreamRequestBody() {
    }

    protected void initOutputStream(final d sink, final long expectedContentLength) {
        this.timeout = sink.timeout();
        this.expectedContentLength = expectedContentLength;
        this.outputStream = new OutputStream() {
            private long bytesReceived;

            public void write(int b) throws IOException {
                write(new byte[]{(byte) b}, 0, 1);
            }

            public void write(byte[] source, int offset, int byteCount) throws IOException {
                if (OutputStreamRequestBody.this.closed) {
                    throw new IOException("closed");
                } else if (expectedContentLength == -1 || this.bytesReceived + ((long) byteCount) <= expectedContentLength) {
                    this.bytesReceived += (long) byteCount;
                    try {
                        sink.c(source, offset, byteCount);
                    } catch (InterruptedIOException e) {
                        throw new SocketTimeoutException(e.getMessage());
                    }
                } else {
                    throw new ProtocolException("expected " + expectedContentLength + " bytes but received " + this.bytesReceived + byteCount);
                }
            }

            public void flush() throws IOException {
                if (!OutputStreamRequestBody.this.closed) {
                    sink.flush();
                }
            }

            public void close() throws IOException {
                OutputStreamRequestBody.this.closed = true;
                if (expectedContentLength == -1 || this.bytesReceived >= expectedContentLength) {
                    sink.close();
                    return;
                }
                throw new ProtocolException("expected " + expectedContentLength + " bytes but received " + this.bytesReceived);
            }
        };
    }

    public final OutputStream outputStream() {
        return this.outputStream;
    }

    public final u timeout() {
        return this.timeout;
    }

    public final boolean isClosed() {
        return this.closed;
    }

    public long contentLength() throws IOException {
        return this.expectedContentLength;
    }

    public final MediaType contentType() {
        return null;
    }

    public Request prepareToSendRequest(Request request) throws IOException {
        return request;
    }
}
