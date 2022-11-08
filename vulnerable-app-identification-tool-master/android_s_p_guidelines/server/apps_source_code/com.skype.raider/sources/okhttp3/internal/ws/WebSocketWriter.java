package okhttp3.internal.ws;

import c.c;
import c.c.a;
import c.d;
import c.f;
import c.s;
import c.u;
import java.io.IOException;
import java.util.Random;

final class WebSocketWriter {
    boolean activeWriter;
    final c buffer = new c();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final a maskCursor;
    private final byte[] maskKey;
    final Random random;
    final d sink;
    final c sinkBuffer;
    boolean writerClosed;

    final class FrameSink implements s {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        public final void write(c source, long byteCount) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(source, byteCount);
            boolean deferWrite;
            if (!this.isFirstFrame || this.contentLength == -1 || WebSocketWriter.this.buffer.a() <= this.contentLength - 8192) {
                deferWrite = false;
            } else {
                deferWrite = true;
            }
            long emitCount = WebSocketWriter.this.buffer.g();
            if (emitCount > 0 && !deferWrite) {
                WebSocketWriter.this.writeMessageFrame(this.formatOpcode, emitCount, this.isFirstFrame, false);
                this.isFirstFrame = false;
            }
        }

        public final void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.a(), this.isFirstFrame, false);
            this.isFirstFrame = false;
        }

        public final u timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        public final void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.a(), this.isFirstFrame, true);
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }

    WebSocketWriter(boolean isClient, d sink, Random random) {
        a aVar = null;
        if (sink == null) {
            throw new NullPointerException("sink == null");
        } else if (random == null) {
            throw new NullPointerException("random == null");
        } else {
            byte[] bArr;
            this.isClient = isClient;
            this.sink = sink;
            this.sinkBuffer = sink.b();
            this.random = random;
            if (isClient) {
                bArr = new byte[4];
            } else {
                bArr = null;
            }
            this.maskKey = bArr;
            if (isClient) {
                aVar = new a();
            }
            this.maskCursor = aVar;
        }
    }

    final void writePing(f payload) throws IOException {
        writeControlFrame(9, payload);
    }

    final void writePong(f payload) throws IOException {
        writeControlFrame(10, payload);
    }

    final void writeClose(int code, f reason) throws IOException {
        f payload = f.b;
        if (!(code == 0 && reason == null)) {
            if (code != 0) {
                WebSocketProtocol.validateCloseCode(code);
            }
            c buffer = new c();
            buffer.c(code);
            if (reason != null) {
                buffer.a(reason);
            }
            payload = buffer.p();
        }
        try {
            writeControlFrame(8, payload);
        } finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int opcode, f payload) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int length = payload.h();
        if (((long) length) > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sinkBuffer.b(opcode | 128);
        int b1 = length;
        if (this.isClient) {
            this.sinkBuffer.b(b1 | 128);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.b(this.maskKey);
            if (length > 0) {
                long payloadStart = this.sinkBuffer.a();
                this.sinkBuffer.a(payload);
                this.sinkBuffer.a(this.maskCursor);
                this.maskCursor.a(payloadStart);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.b(b1);
            this.sinkBuffer.a(payload);
        }
        this.sink.flush();
    }

    final s newMessageSink(int formatOpcode, long contentLength) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = formatOpcode;
        this.frameSink.contentLength = contentLength;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    final void writeMessageFrame(int formatOpcode, long byteCount, boolean isFirstFrame, boolean isFinal) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int b0 = isFirstFrame ? formatOpcode : 0;
        if (isFinal) {
            b0 |= 128;
        }
        this.sinkBuffer.b(b0);
        int b1 = 0;
        if (this.isClient) {
            b1 = 128;
        }
        if (byteCount <= 125) {
            this.sinkBuffer.b(b1 | ((int) byteCount));
        } else if (byteCount <= 65535) {
            this.sinkBuffer.b(b1 | 126);
            this.sinkBuffer.c((int) byteCount);
        } else {
            this.sinkBuffer.b(b1 | 127);
            this.sinkBuffer.i(byteCount);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.b(this.maskKey);
            if (byteCount > 0) {
                long bufferStart = this.sinkBuffer.a();
                this.sinkBuffer.write(this.buffer, byteCount);
                this.sinkBuffer.a(this.maskCursor);
                this.maskCursor.a(bufferStart);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.write(this.buffer, byteCount);
        }
        this.sink.d();
    }
}
