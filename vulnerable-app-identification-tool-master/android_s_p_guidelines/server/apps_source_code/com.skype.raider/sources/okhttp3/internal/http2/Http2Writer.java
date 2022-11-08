package okhttp3.internal.http2;

import c.c;
import c.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;

final class Http2Writer implements Closeable {
    private static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private boolean closed;
    private final c hpackBuffer = new c();
    final Writer hpackWriter = new Writer(this.hpackBuffer);
    private int maxFrameSize = 16384;
    private final d sink;

    Http2Writer(d sink, boolean client) {
        this.sink = sink;
        this.client = client;
    }

    public final synchronized void connectionPreface() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (this.client) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.f()));
            }
            this.sink.c(Http2.CONNECTION_PREFACE.i());
            this.sink.flush();
        }
    }

    public final synchronized void applyAndAckSettings(Settings peerSettings) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.maxFrameSize = peerSettings.getMaxFrameSize(this.maxFrameSize);
        if (peerSettings.getHeaderTableSize() != -1) {
            this.hpackWriter.setHeaderTableSizeSetting(peerSettings.getHeaderTableSize());
        }
        frameHeader(0, 0, (byte) 4, (byte) 1);
        this.sink.flush();
    }

    public final synchronized void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(requestHeaders);
        long byteCount = this.hpackBuffer.a();
        int length = (int) Math.min((long) (this.maxFrameSize - 4), byteCount);
        frameHeader(streamId, length + 4, (byte) 5, byteCount == ((long) length) ? (byte) 4 : (byte) 0);
        this.sink.f(Integer.MAX_VALUE & promisedStreamId);
        this.sink.write(this.hpackBuffer, (long) length);
        if (byteCount > ((long) length)) {
            writeContinuationFrames(streamId, byteCount - ((long) length));
        }
    }

    public final synchronized void flush() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.sink.flush();
    }

    public final synchronized void synStream(boolean outFinished, int streamId, int associatedStreamId, List<Header> headerBlock) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(outFinished, streamId, headerBlock);
    }

    public final synchronized void synReply(boolean outFinished, int streamId, List<Header> headerBlock) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(outFinished, streamId, headerBlock);
    }

    public final synchronized void headers(int streamId, List<Header> headerBlock) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        headers(false, streamId, headerBlock);
    }

    public final synchronized void rstStream(int streamId, ErrorCode errorCode) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        } else {
            frameHeader(streamId, 4, (byte) 3, (byte) 0);
            this.sink.f(errorCode.httpCode);
            this.sink.flush();
        }
    }

    public final int maxDataLength() {
        return this.maxFrameSize;
    }

    public final synchronized void data(boolean outFinished, int streamId, c source, int byteCount) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        byte flags = (byte) 0;
        if (outFinished) {
            flags = (byte) 1;
        }
        dataFrame(streamId, flags, source, byteCount);
    }

    final void dataFrame(int streamId, byte flags, c buffer, int byteCount) throws IOException {
        frameHeader(streamId, byteCount, (byte) 0, flags);
        if (byteCount > 0) {
            this.sink.write(buffer, (long) byteCount);
        }
    }

    public final synchronized void settings(Settings settings) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        frameHeader(0, settings.size() * 6, (byte) 4, (byte) 0);
        for (int i = 0; i < 10; i++) {
            if (settings.isSet(i)) {
                int id = i;
                if (i == 4) {
                    id = 3;
                } else if (id == 7) {
                    id = 4;
                }
                this.sink.g(id);
                this.sink.f(settings.get(i));
            }
        }
        this.sink.flush();
    }

    public final synchronized void ping(boolean ack, int payload1, int payload2) throws IOException {
        byte flags = (byte) 0;
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (ack) {
                flags = (byte) 1;
            }
            frameHeader(0, 8, (byte) 6, flags);
            this.sink.f(payload1);
            this.sink.f(payload2);
            this.sink.flush();
        }
    }

    public final synchronized void goAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (errorCode.httpCode == -1) {
            throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
        } else {
            frameHeader(0, debugData.length + 8, (byte) 7, (byte) 0);
            this.sink.f(lastGoodStreamId);
            this.sink.f(errorCode.httpCode);
            if (debugData.length > 0) {
                this.sink.c(debugData);
            }
            this.sink.flush();
        }
    }

    public final synchronized void windowUpdate(int streamId, long windowSizeIncrement) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        } else if (windowSizeIncrement == 0 || windowSizeIncrement > 2147483647L) {
            throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(windowSizeIncrement));
        } else {
            frameHeader(streamId, 4, (byte) 8, (byte) 0);
            this.sink.f((int) windowSizeIncrement);
            this.sink.flush();
        }
    }

    public final void frameHeader(int streamId, int length, byte type, byte flags) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(false, streamId, length, type, flags));
        }
        if (length > this.maxFrameSize) {
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.maxFrameSize), Integer.valueOf(length));
        } else if ((Integer.MIN_VALUE & streamId) != 0) {
            throw Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(streamId));
        } else {
            writeMedium(this.sink, length);
            this.sink.h(type & 255);
            this.sink.h(flags & 255);
            this.sink.f(Integer.MAX_VALUE & streamId);
        }
    }

    public final synchronized void close() throws IOException {
        this.closed = true;
        this.sink.close();
    }

    private static void writeMedium(d sink, int i) throws IOException {
        sink.h((i >>> 16) & 255);
        sink.h((i >>> 8) & 255);
        sink.h(i & 255);
    }

    private void writeContinuationFrames(int streamId, long byteCount) throws IOException {
        while (byteCount > 0) {
            int length = (int) Math.min((long) this.maxFrameSize, byteCount);
            byteCount -= (long) length;
            frameHeader(streamId, length, (byte) 9, byteCount == 0 ? (byte) 4 : (byte) 0);
            this.sink.write(this.hpackBuffer, (long) length);
        }
    }

    final void headers(boolean outFinished, int streamId, List<Header> headerBlock) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(headerBlock);
        long byteCount = this.hpackBuffer.a();
        int length = (int) Math.min((long) this.maxFrameSize, byteCount);
        byte flags = byteCount == ((long) length) ? (byte) 4 : (byte) 0;
        if (outFinished) {
            flags = (byte) (flags | 1);
        }
        frameHeader(streamId, length, (byte) 1, flags);
        this.sink.write(this.hpackBuffer, (long) length);
        if (byteCount > ((long) length)) {
            writeContinuationFrames(streamId, byteCount - ((long) length));
        }
    }
}
