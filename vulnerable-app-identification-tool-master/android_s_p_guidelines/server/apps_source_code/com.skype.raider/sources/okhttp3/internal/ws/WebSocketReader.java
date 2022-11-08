package okhttp3.internal.ws;

import c.c;
import c.c.a;
import c.e;
import c.f;
import c.u;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

final class WebSocketReader {
    boolean closed;
    private final c controlFrameBuffer = new c();
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    private final a maskCursor;
    private final byte[] maskKey;
    private final c messageFrameBuffer = new c();
    int opcode;
    final e source;

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(f fVar) throws IOException;

        void onReadMessage(String str) throws IOException;

        void onReadPing(f fVar);

        void onReadPong(f fVar);
    }

    WebSocketReader(boolean isClient, e source, FrameCallback frameCallback) {
        a aVar = null;
        if (source == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        } else {
            this.isClient = isClient;
            this.source = source;
            this.frameCallback = frameCallback;
            this.maskKey = isClient ? null : new byte[4];
            if (!isClient) {
                aVar = new a();
            }
            this.maskCursor = aVar;
        }
    }

    final void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    private void readHeader() throws IOException {
        int b0;
        boolean z;
        boolean reservedFlag1;
        boolean reservedFlag2;
        boolean reservedFlag3;
        u uVar = true;
        TimeUnit timeUnit = null;
        if (this.closed) {
            throw new IOException("closed");
        }
        u h;
        long timeoutBefore = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            h = this.source.h();
            b0 = h & 255;
        } finally {
            uVar = h;
            uVar = this.source.timeout();
            timeUnit = TimeUnit.NANOSECONDS;
            uVar.timeout(timeoutBefore, timeUnit);
            if (h == null) {
                z = timeUnit;
            } else {
                z = uVar;
            }
            this.isFinalFrame = z;
            if ((b0 & 8) == 0) {
                z = timeUnit;
            } else {
                z = uVar;
            }
            this.isControlFrame = z;
            if (this.isControlFrame) {
            }
            if ((b0 & 64) == 0) {
                reservedFlag1 = timeUnit;
            } else {
                reservedFlag1 = uVar;
            }
            if ((b0 & 32) == 0) {
                reservedFlag2 = timeUnit;
            } else {
                reservedFlag2 = uVar;
            }
            if ((b0 & 16) == 0) {
                reservedFlag3 = timeUnit;
            } else {
                reservedFlag3 = uVar;
            }
            if (!reservedFlag1) {
            }
            throw new ProtocolException("Reserved flags are unsupported.");
        }
        if (h == null) {
            z = uVar;
        } else {
            z = timeUnit;
        }
        this.isFinalFrame = z;
        if ((b0 & 8) == 0) {
            z = uVar;
        } else {
            z = timeUnit;
        }
        this.isControlFrame = z;
        if (this.isControlFrame || this.isFinalFrame) {
            if ((b0 & 64) == 0) {
                reservedFlag1 = uVar;
            } else {
                reservedFlag1 = timeUnit;
            }
            if ((b0 & 32) == 0) {
                reservedFlag2 = uVar;
            } else {
                reservedFlag2 = timeUnit;
            }
            if ((b0 & 16) == 0) {
                reservedFlag3 = uVar;
            } else {
                reservedFlag3 = timeUnit;
            }
            if (reservedFlag1 || reservedFlag2 || reservedFlag3) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            boolean isMasked;
            int b1 = this.source.h() & 255;
            if ((b1 & 128) != 0) {
                isMasked = uVar;
            } else {
                isMasked = timeUnit;
            }
            if (isMasked == this.isClient) {
                String str;
                if (this.isClient) {
                    str = "Server-sent frames must not be masked.";
                } else {
                    str = "Client-sent frames must be masked.";
                }
                throw new ProtocolException(str);
            }
            this.frameLength = (long) (b1 & 127);
            if (this.frameLength == 126) {
                this.frameLength = ((long) this.source.i()) & 65535;
            } else if (this.frameLength == 127) {
                this.frameLength = this.source.k();
                if (this.frameLength < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            } else if (isMasked) {
                this.source.a(this.maskKey);
                return;
            } else {
                return;
            }
        }
        throw new ProtocolException("Control frames must be final.");
    }

    private void readControlFrame() throws IOException {
        if (this.frameLength > 0) {
            this.source.a(this.controlFrameBuffer, this.frameLength);
            if (!this.isClient) {
                this.controlFrameBuffer.a(this.maskCursor);
                this.maskCursor.a(0);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                int code = 1005;
                String reason = "";
                long bufferSize = this.controlFrameBuffer.a();
                if (bufferSize == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (bufferSize != 0) {
                    code = this.controlFrameBuffer.i();
                    reason = this.controlFrameBuffer.q();
                    String codeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(code);
                    if (codeExceptionMessage != null) {
                        throw new ProtocolException(codeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(code, reason);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.p());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.p());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() throws IOException {
        int opcode = this.opcode;
        if (opcode == 1 || opcode == 2) {
            readMessage();
            if (opcode == 1) {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.q());
                return;
            } else {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.p());
                return;
            }
        }
        throw new ProtocolException("Unknown opcode: " + Integer.toHexString(opcode));
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            if (this.frameLength > 0) {
                this.source.a(this.messageFrameBuffer, this.frameLength);
                if (!this.isClient) {
                    this.messageFrameBuffer.a(this.maskCursor);
                    this.maskCursor.a(this.messageFrameBuffer.a() - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (!this.isFinalFrame) {
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
                }
            }
            return;
        }
        throw new IOException("closed");
    }
}
