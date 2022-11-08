package okhttp3.internal.http2;

import c.c;
import c.e;
import c.f;
import c.t;
import c.u;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;

final class Http2Reader implements Closeable {
    static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private final ContinuationSource continuation = new ContinuationSource(this.source);
    final Reader hpackReader = new Reader(4096, this.continuation);
    private final e source;

    interface Handler {
        void ackSettings();

        void alternateService(int i, String str, f fVar, String str2, int i2, long j);

        void data(boolean z, int i, e eVar, int i2) throws IOException;

        void goAway(int i, ErrorCode errorCode, f fVar);

        void headers(boolean z, int i, int i2, List<Header> list);

        void ping(boolean z, int i, int i2);

        void priority(int i, int i2, int i3, boolean z);

        void pushPromise(int i, int i2, List<Header> list) throws IOException;

        void rstStream(int i, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i, long j);
    }

    static final class ContinuationSource implements t {
        byte flags;
        int left;
        int length;
        short padding;
        private final e source;
        int streamId;

        ContinuationSource(e source) {
            this.source = source;
        }

        public final long read(c sink, long byteCount) throws IOException {
            while (this.left == 0) {
                this.source.h((long) this.padding);
                this.padding = (short) 0;
                if ((this.flags & 4) != 0) {
                    return -1;
                }
                readContinuationHeader();
            }
            long read = this.source.read(sink, Math.min(byteCount, (long) this.left));
            if (read == -1) {
                return -1;
            }
            this.left = (int) (((long) this.left) - read);
            return read;
        }

        public final u timeout() {
            return this.source.timeout();
        }

        public final void close() throws IOException {
        }

        private void readContinuationHeader() throws IOException {
            int previousStreamId = this.streamId;
            int readMedium = Http2Reader.readMedium(this.source);
            this.left = readMedium;
            this.length = readMedium;
            byte type = (byte) (this.source.h() & 255);
            this.flags = (byte) (this.source.h() & 255);
            if (Http2Reader.logger.isLoggable(Level.FINE)) {
                Http2Reader.logger.fine(Http2.frameLog(true, this.streamId, this.length, type, this.flags));
            }
            this.streamId = this.source.j() & Integer.MAX_VALUE;
            if (type != (byte) 9) {
                throw Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(type));
            } else if (this.streamId != previousStreamId) {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    Http2Reader(e source, boolean client) {
        this.source = source;
        this.client = client;
    }

    public final void readConnectionPreface(Handler handler) throws IOException {
        if (!this.client) {
            f connectionPreface = this.source.d((long) Http2.CONNECTION_PREFACE.h());
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format("<< CONNECTION %s", connectionPreface.f()));
            }
            if (!Http2.CONNECTION_PREFACE.equals(connectionPreface)) {
                throw Http2.ioException("Expected a connection header but was %s", connectionPreface.a());
            }
        } else if (!nextFrame(true, handler)) {
            throw Http2.ioException("Required SETTINGS preface not received", new Object[0]);
        }
    }

    public final boolean nextFrame(boolean requireSettings, Handler handler) throws IOException {
        try {
            this.source.a(9);
            int length = readMedium(this.source);
            if (length < 0 || length > 16384) {
                throw Http2.ioException("FRAME_SIZE_ERROR: %s", Integer.valueOf(length));
            }
            byte type = (byte) (this.source.h() & 255);
            if (!requireSettings || type == (byte) 4) {
                byte flags = (byte) (this.source.h() & 255);
                int streamId = this.source.j() & Integer.MAX_VALUE;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(Http2.frameLog(true, streamId, length, type, flags));
                }
                switch (type) {
                    case (byte) 0:
                        readData(handler, length, flags, streamId);
                        return true;
                    case (byte) 1:
                        readHeaders(handler, length, flags, streamId);
                        return true;
                    case (byte) 2:
                        readPriority(handler, length, flags, streamId);
                        return true;
                    case (byte) 3:
                        readRstStream(handler, length, flags, streamId);
                        return true;
                    case (byte) 4:
                        readSettings(handler, length, flags, streamId);
                        return true;
                    case (byte) 5:
                        readPushPromise(handler, length, flags, streamId);
                        return true;
                    case (byte) 6:
                        readPing(handler, length, flags, streamId);
                        return true;
                    case (byte) 7:
                        readGoAway(handler, length, flags, streamId);
                        return true;
                    case (byte) 8:
                        readWindowUpdate(handler, length, flags, streamId);
                        return true;
                    default:
                        this.source.h((long) length);
                        return true;
                }
            }
            throw Http2.ioException("Expected a SETTINGS frame but was %s", Byte.valueOf(type));
        } catch (IOException e) {
            return false;
        }
    }

    private void readHeaders(Handler handler, int length, byte flags, int streamId) throws IOException {
        short padding = (short) 0;
        if (streamId == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean endStream;
        if ((flags & 1) != 0) {
            endStream = true;
        } else {
            endStream = false;
        }
        if ((flags & 8) != 0) {
            padding = (short) (this.source.h() & 255);
        }
        if ((flags & 32) != 0) {
            readPriority(handler, streamId);
            length -= 5;
        }
        handler.headers(endStream, streamId, -1, readHeaderBlock(lengthWithoutPadding(length, flags, padding), padding, flags, streamId));
    }

    private List<Header> readHeaderBlock(int length, short padding, byte flags, int streamId) throws IOException {
        ContinuationSource continuationSource = this.continuation;
        this.continuation.left = length;
        continuationSource.length = length;
        this.continuation.padding = padding;
        this.continuation.flags = flags;
        this.continuation.streamId = streamId;
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private void readData(Handler handler, int length, byte flags, int streamId) throws IOException {
        short s = (short) 1;
        short padding = (short) 0;
        if (streamId == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean inFinished;
        if ((flags & 1) != 0) {
            inFinished = true;
        } else {
            inFinished = false;
        }
        if ((flags & 32) == 0) {
            s = (short) 0;
        }
        if (s != (short) 0) {
            throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        if ((flags & 8) != 0) {
            padding = (short) (this.source.h() & 255);
        }
        handler.data(inFinished, streamId, this.source, lengthWithoutPadding(length, flags, padding));
        this.source.h((long) padding);
    }

    private void readPriority(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 5) {
            throw Http2.ioException("TYPE_PRIORITY length: %d != 5", Integer.valueOf(length));
        } else if (streamId == 0) {
            throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
        } else {
            readPriority(handler, streamId);
        }
    }

    private void readPriority(Handler handler, int streamId) throws IOException {
        int w1 = this.source.j();
        handler.priority(streamId, w1 & Integer.MAX_VALUE, (this.source.h() & 255) + 1, (Integer.MIN_VALUE & w1) != 0);
    }

    private void readRstStream(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 4) {
            throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(length));
        } else if (streamId == 0) {
            throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
        } else {
            ErrorCode errorCode = ErrorCode.fromHttp2(this.source.j());
            if (errorCode == null) {
                throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(errorCodeInt));
            } else {
                handler.rstStream(streamId, errorCode);
            }
        }
    }

    private void readSettings(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (streamId != 0) {
            throw Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
        } else if ((flags & 1) != 0) {
            if (length != 0) {
                throw Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            handler.ackSettings();
        } else if (length % 6 != 0) {
            throw Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(length));
        } else {
            Settings settings = new Settings();
            for (int i = 0; i < length; i += 6) {
                int id = this.source.i() & 65535;
                int value = this.source.j();
                switch (id) {
                    case 2:
                        if (!(value == 0 || value == 1)) {
                            throw Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        }
                    case 3:
                        id = 4;
                        break;
                    case 4:
                        id = 7;
                        if (value >= 0) {
                            break;
                        }
                        throw Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                    case 5:
                        if (value >= 16384 && value <= 16777215) {
                            break;
                        }
                        throw Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(value));
                        break;
                    default:
                        break;
                }
                settings.set(id, value);
            }
            handler.settings(false, settings);
        }
    }

    private void readPushPromise(Handler handler, int length, byte flags, int streamId) throws IOException {
        short padding = (short) 0;
        if (streamId == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        if ((flags & 8) != 0) {
            padding = (short) (this.source.h() & 255);
        }
        handler.pushPromise(streamId, this.source.j() & Integer.MAX_VALUE, readHeaderBlock(lengthWithoutPadding(length - 4, flags, padding), padding, flags, streamId));
    }

    private void readPing(Handler handler, int length, byte flags, int streamId) throws IOException {
        boolean ack = true;
        if (length != 8) {
            throw Http2.ioException("TYPE_PING length != 8: %s", Integer.valueOf(length));
        } else if (streamId != 0) {
            throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
        } else {
            int payload1 = this.source.j();
            int payload2 = this.source.j();
            if ((flags & 1) == 0) {
                ack = false;
            }
            handler.ping(ack, payload1, payload2);
        }
    }

    private void readGoAway(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length < 8) {
            throw Http2.ioException("TYPE_GOAWAY length < 8: %s", Integer.valueOf(length));
        } else if (streamId != 0) {
            throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
        } else {
            int lastStreamId = this.source.j();
            int opaqueDataLength = length - 8;
            ErrorCode errorCode = ErrorCode.fromHttp2(this.source.j());
            if (errorCode == null) {
                throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(errorCodeInt));
            }
            f debugData = f.b;
            if (opaqueDataLength > 0) {
                debugData = this.source.d((long) opaqueDataLength);
            }
            handler.goAway(lastStreamId, errorCode, debugData);
        }
    }

    private void readWindowUpdate(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 4) {
            throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(length));
        }
        long increment = ((long) this.source.j()) & 2147483647L;
        if (increment == 0) {
            throw Http2.ioException("windowSizeIncrement was 0", Long.valueOf(increment));
        } else {
            handler.windowUpdate(streamId, increment);
        }
    }

    public final void close() throws IOException {
        this.source.close();
    }

    static int readMedium(e source) throws IOException {
        return (((source.h() & 255) << 16) | ((source.h() & 255) << 8)) | (source.h() & 255);
    }

    static int lengthWithoutPadding(int length, byte flags, short padding) throws IOException {
        short length2;
        if ((flags & 8) != 0) {
            length2 = length2 - 1;
        }
        if (padding <= length2) {
            return (short) (length2 - padding);
        }
        throw Http2.ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(padding), Integer.valueOf(length2));
    }
}
