package okhttp3.internal.ws;

import c.d;
import c.e;
import c.f;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.ws.WebSocketReader.FrameCallback;

public final class RealWebSocket implements WebSocket, FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = (!RealWebSocket.class.desiredAssertionStatus());
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque();
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<f> pongQueue = new ArrayDeque();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    public static abstract class Streams implements Closeable {
        public final boolean client;
        public final d sink;
        public final e source;

        public Streams(boolean client, e source, d sink) {
            this.client = client;
            this.source = source;
            this.sink = sink;
        }
    }

    final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        public final void run() {
            RealWebSocket.this.cancel();
        }
    }

    static final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final f reason;

        Close(int code, f reason, long cancelAfterCloseMillis) {
            this.code = code;
            this.reason = reason;
            this.cancelAfterCloseMillis = cancelAfterCloseMillis;
        }
    }

    static final class Message {
        final f data;
        final int formatOpcode;

        Message(int formatOpcode, f data) {
            this.formatOpcode = formatOpcode;
            this.data = data;
        }
    }

    private final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        public final void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    public RealWebSocket(Request request, WebSocketListener listener, Random random, long pingIntervalMillis) {
        if ("GET".equals(request.method())) {
            this.originalRequest = request;
            this.listener = listener;
            this.random = random;
            this.pingIntervalMillis = pingIntervalMillis;
            byte[] nonce = new byte[16];
            random.nextBytes(nonce);
            this.key = f.a(nonce).b();
            this.writerRunnable = new Runnable() {
                public void run() {
                    do {
                        try {
                        } catch (IOException e) {
                            RealWebSocket.this.failWebSocket(e, null);
                            return;
                        }
                    } while (RealWebSocket.this.writeOneFrame());
                }
            };
            return;
        }
        throw new IllegalArgumentException("Request must be GET: " + request.method());
    }

    public final Request request() {
        return this.originalRequest;
    }

    public final synchronized long queueSize() {
        return this.queueSize;
    }

    public final void cancel() {
        this.call.cancel();
    }

    public final void connect(OkHttpClient client) {
        client = client.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request request = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = Internal.instance.newWebSocketCall(client, request);
        this.call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams streams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + request.url().redact(), streams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, null);
                    }
                } catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly((Closeable) response);
                }
            }

            public void onFailure(Call call, IOException e) {
                RealWebSocket.this.failWebSocket(e, null);
            }
        });
    }

    final void checkResponse(Response response) throws ProtocolException {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
        }
        String headerConnection = response.header("Connection");
        if ("Upgrade".equalsIgnoreCase(headerConnection)) {
            String headerUpgrade = response.header("Upgrade");
            if ("websocket".equalsIgnoreCase(headerUpgrade)) {
                String headerAccept = response.header("Sec-WebSocket-Accept");
                String acceptExpected = f.a(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").d().b();
                if (!acceptExpected.equals(headerAccept)) {
                    throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + acceptExpected + "' but was '" + headerAccept + "'");
                }
                return;
            }
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + headerUpgrade + "'");
        }
        throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + headerConnection + "'");
    }

    public final void initReaderAndWriter(String name, Streams streams) throws IOException {
        synchronized (this) {
            this.streams = streams;
            this.writer = new WebSocketWriter(streams.client, streams.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(name, false));
            if (this.pingIntervalMillis != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), this.pingIntervalMillis, this.pingIntervalMillis, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams.client, streams.source, this);
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    final boolean processNextFrame() throws IOException {
        try {
            this.reader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, null);
            return false;
        }
    }

    final void awaitTermination(int timeout, TimeUnit timeUnit) throws InterruptedException {
        this.executor.awaitTermination((long) timeout, timeUnit);
    }

    final void tearDown() throws InterruptedException {
        if (this.cancelFuture != null) {
            this.cancelFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    final synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    final synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    final synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    public final void onReadMessage(String text) throws IOException {
        this.listener.onMessage((WebSocket) this, text);
    }

    public final void onReadMessage(f bytes) throws IOException {
        this.listener.onMessage((WebSocket) this, bytes);
    }

    public final synchronized void onReadPing(f payload) {
        if (!(this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()))) {
            this.pongQueue.add(payload);
            runWriter();
            this.receivedPingCount++;
        }
    }

    public final synchronized void onReadPong(f buffer) {
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public final void onReadClose(int code, String reason) {
        if (code == -1) {
            throw new IllegalArgumentException();
        }
        Closeable toClose = null;
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = code;
            this.receivedCloseReason = reason;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                toClose = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                this.executor.shutdown();
            }
        }
        try {
            this.listener.onClosing(this, code, reason);
            if (toClose != null) {
                this.listener.onClosed(this, code, reason);
            }
            Util.closeQuietly(toClose);
        } catch (Throwable th) {
            Util.closeQuietly(toClose);
        }
    }

    public final boolean send(String text) {
        if (text != null) {
            return send(f.a(text), 1);
        }
        throw new NullPointerException("text == null");
    }

    public final boolean send(f bytes) {
        if (bytes != null) {
            return send(bytes, 2);
        }
        throw new NullPointerException("bytes == null");
    }

    private synchronized boolean send(f data, int formatOpcode) {
        boolean z = false;
        synchronized (this) {
            if (!(this.failed || this.enqueuedClose)) {
                if (this.queueSize + ((long) data.h()) > MAX_QUEUE_SIZE) {
                    close(1001, null);
                } else {
                    this.queueSize += (long) data.h();
                    this.messageAndCloseQueue.add(new Message(formatOpcode, data));
                    runWriter();
                    z = true;
                }
            }
        }
        return z;
    }

    final synchronized boolean pong(f payload) {
        boolean z;
        if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty())) {
            z = false;
        } else {
            this.pongQueue.add(payload);
            runWriter();
            z = true;
        }
        return z;
    }

    public final boolean close(int code, String reason) {
        return close(code, reason, CANCEL_AFTER_CLOSE_MILLIS);
    }

    final synchronized boolean close(int code, String reason, long cancelAfterCloseMillis) {
        boolean z = true;
        synchronized (this) {
            WebSocketProtocol.validateCloseCode(code);
            f reasonBytes = null;
            if (reason != null) {
                reasonBytes = f.a(reason);
                if (((long) reasonBytes.h()) > 123) {
                    throw new IllegalArgumentException("reason.size() > 123: " + reason);
                }
            }
            if (this.failed || this.enqueuedClose) {
                z = false;
            } else {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(code, reasonBytes, cancelAfterCloseMillis));
                runWriter();
            }
        }
        return z;
    }

    private void runWriter() {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.executor != null) {
            this.executor.execute(this.writerRunnable);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final boolean writeOneFrame() throws IOException {
        Object messageOrClose = null;
        int receivedCloseCode = 0;
        String receivedCloseReason = null;
        Closeable streamsToClose = null;
        synchronized (this) {
            if (this.failed) {
                return false;
            }
            WebSocketWriter writer = this.writer;
            f pong = (f) this.pongQueue.poll();
            if (pong == null) {
                messageOrClose = this.messageAndCloseQueue.poll();
                if (messageOrClose instanceof Close) {
                    receivedCloseCode = this.receivedCloseCode;
                    receivedCloseReason = this.receivedCloseReason;
                    if (receivedCloseCode != -1) {
                        streamsToClose = this.streams;
                        this.streams = null;
                        this.executor.shutdown();
                    } else {
                        this.cancelFuture = this.executor.schedule(new CancelRunnable(), ((Close) messageOrClose).cancelAfterCloseMillis, TimeUnit.MILLISECONDS);
                    }
                } else if (messageOrClose == null) {
                    return false;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final void writePingFrame() {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            WebSocketWriter writer = this.writer;
            int failedPing;
            if (this.awaitingPong) {
                failedPing = this.sentPingCount;
            } else {
                failedPing = -1;
            }
            this.sentPingCount++;
            this.awaitingPong = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void failWebSocket(Exception e, @Nullable Response response) {
        synchronized (this) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            Closeable streamsToClose = this.streams;
            this.streams = null;
            if (this.cancelFuture != null) {
                this.cancelFuture.cancel(false);
            }
            if (this.executor != null) {
                this.executor.shutdown();
            }
        }
    }
}
