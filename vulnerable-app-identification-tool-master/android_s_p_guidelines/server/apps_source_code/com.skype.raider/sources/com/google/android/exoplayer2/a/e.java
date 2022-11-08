package com.google.android.exoplayer2.a;

import android.annotation.TargetApi;
import android.media.AudioAttributes.Builder;
import android.media.AudioFormat;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.l;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedList;

public final class e {
    public static boolean a = false;
    public static boolean b = false;
    private int A;
    private int B;
    private int C;
    private long D;
    private long E;
    private boolean F;
    private long G;
    private Method H;
    private int I;
    private long J;
    private long K;
    private int L;
    private long M;
    private long N;
    private int O;
    private int P;
    private long Q;
    private long R;
    private long S;
    private float T;
    private c[] U;
    private ByteBuffer[] V;
    private ByteBuffer W;
    private ByteBuffer X;
    private byte[] Y;
    private int Z;
    private int aa;
    private boolean ab;
    private boolean ac;
    private int ad;
    private boolean ae;
    private boolean af;
    private long ag;
    private final b c;
    private final f d;
    private final k e;
    private final c[] f;
    private final f g;
    private final ConditionVariable h = new ConditionVariable(true);
    private final long[] i;
    private final a j;
    private final LinkedList<g> k;
    private AudioTrack l;
    private AudioTrack m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private int t;
    private long u;
    private l v;
    private l w;
    private long x;
    private long y;
    private ByteBuffer z;

    private static class a {
        protected AudioTrack a;
        private boolean b;
        private int c;
        private long d;
        private long e;
        private long f;
        private long g;
        private long h;
        private long i;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        public void a(AudioTrack audioTrack, boolean needsPassthroughWorkaround) {
            this.a = audioTrack;
            this.b = needsPassthroughWorkaround;
            this.g = -9223372036854775807L;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            if (audioTrack != null) {
                this.c = audioTrack.getSampleRate();
            }
        }

        public final void a(long writtenFrames) {
            this.h = b();
            this.g = SystemClock.elapsedRealtime() * 1000;
            this.i = writtenFrames;
            this.a.stop();
        }

        public final void a() {
            if (this.g == -9223372036854775807L) {
                this.a.pause();
            }
        }

        public final long b() {
            if (this.g != -9223372036854775807L) {
                return Math.min(this.i, this.h + ((((SystemClock.elapsedRealtime() * 1000) - this.g) * ((long) this.c)) / 1000000));
            }
            int state = this.a.getPlayState();
            if (state == 1) {
                return 0;
            }
            long rawPlaybackHeadPosition = 4294967295L & ((long) this.a.getPlaybackHeadPosition());
            if (this.b) {
                if (state == 2 && rawPlaybackHeadPosition == 0) {
                    this.f = this.d;
                }
                rawPlaybackHeadPosition += this.f;
            }
            if (this.d > rawPlaybackHeadPosition) {
                this.e++;
            }
            this.d = rawPlaybackHeadPosition;
            return (this.e << 32) + rawPlaybackHeadPosition;
        }

        public final long c() {
            return (b() * 1000000) / ((long) this.c);
        }

        public boolean d() {
            return false;
        }

        public long e() {
            throw new UnsupportedOperationException();
        }

        public long f() {
            throw new UnsupportedOperationException();
        }
    }

    @TargetApi(19)
    private static class b extends a {
        private final AudioTimestamp b = new AudioTimestamp();
        private long c;
        private long d;
        private long e;

        public b() {
            super();
        }

        public final void a(AudioTrack audioTrack, boolean needsPassthroughWorkaround) {
            super.a(audioTrack, needsPassthroughWorkaround);
            this.c = 0;
            this.d = 0;
            this.e = 0;
        }

        public final boolean d() {
            boolean updated = this.a.getTimestamp(this.b);
            if (updated) {
                long rawFramePosition = this.b.framePosition;
                if (this.d > rawFramePosition) {
                    this.c++;
                }
                this.d = rawFramePosition;
                this.e = (this.c << 32) + rawFramePosition;
            }
            return updated;
        }

        public final long e() {
            return this.b.nanoTime;
        }

        public final long f() {
            return this.e;
        }
    }

    public static final class c extends Exception {
        public c(Throwable cause) {
            super(cause);
        }

        public c(String message) {
            super(message);
        }
    }

    public static final class d extends Exception {
        public final int a;

        public d(int audioTrackState, int sampleRate, int channelConfig, int bufferSize) {
            super("AudioTrack init failed: " + audioTrackState + ", Config(" + sampleRate + ", " + channelConfig + ", " + bufferSize + ")");
            this.a = audioTrackState;
        }
    }

    public static final class e extends RuntimeException {
        public e(String detailMessage) {
            super(detailMessage);
        }
    }

    public interface f {
        void a();

        void a(int i);

        void a(int i, long j, long j2);
    }

    private static final class g {
        private final l a;
        private final long b;
        private final long c;

        /* synthetic */ g(l x0, long x1, long x2, byte b) {
            this(x0, x1, x2);
        }

        private g(l playbackParameters, long mediaTimeUs, long positionUs) {
            this.a = playbackParameters;
            this.b = mediaTimeUs;
            this.c = positionUs;
        }
    }

    public static final class h extends Exception {
        public final int a;

        public h(int errorCode) {
            super("AudioTrack write failed: " + errorCode);
            this.a = errorCode;
        }
    }

    public e(b audioCapabilities, c[] audioProcessors, f listener) {
        this.c = audioCapabilities;
        this.g = listener;
        if (s.a >= 18) {
            try {
                this.H = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException e) {
            }
        }
        if (s.a >= 19) {
            this.j = new b();
        } else {
            this.j = new a();
        }
        this.d = new f();
        this.e = new k();
        this.f = new c[(audioProcessors.length + 3)];
        this.f[0] = new i();
        this.f[1] = this.d;
        System.arraycopy(audioProcessors, 0, this.f, 2, audioProcessors.length);
        this.f[audioProcessors.length + 2] = this.e;
        this.i = new long[10];
        this.T = 1.0f;
        this.P = 0;
        this.r = 3;
        this.ad = 0;
        this.w = l.a;
        this.aa = -1;
        this.U = new c[0];
        this.V = new ByteBuffer[0];
        this.k = new LinkedList();
    }

    public final boolean a(String mimeType) {
        return this.c != null && this.c.a(b(mimeType));
    }

    public final void a(String mimeType, int channelCount, int sampleRate, int pcmEncoding, int[] outputChannels) throws c {
        int encoding;
        int channelConfig;
        boolean passthrough = !"audio/raw".equals(mimeType);
        if (passthrough) {
            encoding = b(mimeType);
        } else {
            encoding = pcmEncoding;
        }
        boolean flush = false;
        if (!passthrough) {
            this.I = s.b(pcmEncoding, channelCount);
            this.d.a(outputChannels);
            c[] cVarArr = this.f;
            int length = cVarArr.length;
            int i = 0;
            while (i < length) {
                c audioProcessor = cVarArr[i];
                try {
                    flush |= audioProcessor.a(sampleRate, channelCount, encoding);
                    if (audioProcessor.a()) {
                        channelCount = audioProcessor.b();
                        encoding = audioProcessor.c();
                    }
                    i++;
                } catch (Throwable e) {
                    throw new c(e);
                }
            }
            if (flush) {
                k();
            }
        }
        switch (channelCount) {
            case 1:
                channelConfig = 4;
                break;
            case 2:
                channelConfig = 12;
                break;
            case 3:
                channelConfig = 28;
                break;
            case 4:
                channelConfig = 204;
                break;
            case 5:
                channelConfig = 220;
                break;
            case 6:
                channelConfig = 252;
                break;
            case 7:
                channelConfig = 1276;
                break;
            case 8:
                channelConfig = C.a;
                break;
            default:
                throw new c("Unsupported channel count: " + channelCount);
        }
        if (s.a <= 23 && "foster".equals(s.b) && "NVIDIA".equals(s.c)) {
            switch (channelCount) {
                case 3:
                case 5:
                    channelConfig = 252;
                    break;
                case 7:
                    channelConfig = C.a;
                    break;
            }
        }
        if (s.a <= 25 && "fugu".equals(s.b) && passthrough && channelCount == 1) {
            channelConfig = 12;
        }
        if (flush || !o() || this.p != encoding || this.n != sampleRate || this.o != channelConfig) {
            int minAppBufferSize;
            e this;
            long j;
            i();
            this.p = encoding;
            this.s = passthrough;
            this.n = sampleRate;
            this.o = channelConfig;
            if (!passthrough) {
                encoding = 2;
            }
            this.q = encoding;
            this.L = s.b(2, channelCount);
            if (!passthrough) {
                int minBufferSize = AudioTrack.getMinBufferSize(sampleRate, channelConfig, this.q);
                com.google.android.exoplayer2.d.a.b(minBufferSize != -2);
                int multipliedBufferSize = minBufferSize * 4;
                minAppBufferSize = ((int) d(250000)) * this.L;
                int maxAppBufferSize = (int) Math.max((long) minBufferSize, d(750000) * ((long) this.L));
                if (multipliedBufferSize < minAppBufferSize) {
                    this = this;
                } else if (multipliedBufferSize > maxAppBufferSize) {
                    minAppBufferSize = maxAppBufferSize;
                    this = this;
                } else {
                    minAppBufferSize = multipliedBufferSize;
                    this = this;
                }
            } else if (this.q == 5 || this.q == 6) {
                minAppBufferSize = 20480;
                this = this;
            } else {
                minAppBufferSize = 49152;
                this = this;
            }
            this.t = minAppBufferSize;
            if (passthrough) {
                j = -9223372036854775807L;
            } else {
                j = c((long) (this.t / this.L));
            }
            this.u = j;
            a(this.w);
        }
    }

    private void k() {
        c audioProcessor;
        ArrayList<c> newAudioProcessors = new ArrayList();
        for (c audioProcessor2 : this.f) {
            if (audioProcessor2.a()) {
                newAudioProcessors.add(audioProcessor2);
            } else {
                audioProcessor2.g();
            }
        }
        int count = newAudioProcessors.size();
        this.U = (c[]) newAudioProcessors.toArray(new c[count]);
        this.V = new ByteBuffer[count];
        for (int i = 0; i < count; i++) {
            audioProcessor2 = this.U[i];
            audioProcessor2.g();
            this.V[i] = audioProcessor2.e();
        }
    }

    public final void a() {
        this.ac = true;
        if (o()) {
            this.R = System.nanoTime() / 1000;
            this.m.play();
        }
    }

    public final void b() {
        if (this.P == 1) {
            this.P = 2;
        }
    }

    public final boolean a(ByteBuffer buffer, long presentationTimeUs) throws d, h {
        int i;
        boolean z = this.W == null || buffer == this.W;
        com.google.android.exoplayer2.d.a.a(z);
        if (!o()) {
            this.h.block();
            if (this.ae) {
                i = this.n;
                int i2 = this.o;
                this.m = new AudioTrack(new Builder().setUsage(1).setContentType(3).setFlags(16).build(), new AudioFormat.Builder().setChannelMask(i2).setEncoding(this.q).setSampleRate(i).build(), this.t, 1, this.ad);
            } else if (this.ad == 0) {
                this.m = new AudioTrack(this.r, this.n, this.o, this.q, this.t, 1);
            } else {
                this.m = new AudioTrack(this.r, this.n, this.o, this.q, this.t, 1, this.ad);
            }
            i = this.m.getState();
            if (i != 1) {
                try {
                    this.m.release();
                } catch (Exception e) {
                } finally {
                    this.m = null;
                }
                throw new d(i, this.n, this.o, this.t);
            }
            int audioSessionId = this.m.getAudioSessionId();
            if (a && s.a < 21) {
                if (!(this.l == null || audioSessionId == this.l.getAudioSessionId())) {
                    n();
                }
                if (this.l == null) {
                    this.l = new AudioTrack(this.r, 4000, 4, 2, 2, 0, audioSessionId);
                }
            }
            if (this.ad != audioSessionId) {
                this.ad = audioSessionId;
                this.g.a(audioSessionId);
            }
            this.j.a(this.m, r());
            m();
            this.af = false;
            if (this.ac) {
                a();
            }
        }
        if (r()) {
            if (this.m.getPlayState() == 2) {
                this.af = false;
                return false;
            } else if (this.m.getPlayState() == 1 && this.j.b() != 0) {
                return false;
            }
        }
        boolean hadData = this.af;
        this.af = e();
        if (!(!hadData || this.af || this.m.getPlayState() == 1)) {
            this.g.a(this.t, C.a(this.u), SystemClock.elapsedRealtime() - this.ag);
        }
        if (this.W == null) {
            if (!buffer.hasRemaining()) {
                return true;
            }
            if (this.s && this.O == 0) {
                i = this.q;
                if (i == 7 || i == 8) {
                    i = g.a(buffer);
                } else if (i == 5) {
                    i = a.a();
                } else if (i == 6) {
                    i = a.a(buffer);
                } else {
                    throw new IllegalStateException("Unexpected audio encoding: " + i);
                }
                this.O = i;
            }
            if (this.v != null) {
                if (!l()) {
                    return false;
                }
                this.k.add(new g(this.v, Math.max(0, presentationTimeUs), c(p()), (byte) 0));
                this.v = null;
                k();
            }
            if (this.P == 0) {
                this.Q = Math.max(0, presentationTimeUs);
                this.P = 1;
            } else {
                long j;
                long j2 = this.Q;
                if (this.s) {
                    j = this.K;
                } else {
                    j = this.J / ((long) this.I);
                }
                long expectedPresentationTimeUs = j2 + c(j);
                if (this.P == 1 && Math.abs(expectedPresentationTimeUs - presentationTimeUs) > 200000) {
                    new StringBuilder("Discontinuity detected [expected ").append(expectedPresentationTimeUs).append(", got ").append(presentationTimeUs).append("]");
                    this.P = 2;
                }
                if (this.P == 2) {
                    this.Q += presentationTimeUs - expectedPresentationTimeUs;
                    this.P = 1;
                    this.g.a();
                }
            }
            if (this.s) {
                this.K += (long) this.O;
            } else {
                this.J += (long) buffer.remaining();
            }
            this.W = buffer;
        }
        if (this.s) {
            b(this.W, presentationTimeUs);
        } else {
            a(presentationTimeUs);
        }
        if (this.W.hasRemaining()) {
            return false;
        }
        this.W = null;
        return true;
    }

    private void a(long avSyncPresentationTimeUs) throws h {
        int count = this.U.length;
        int index = count;
        while (index >= 0) {
            ByteBuffer input = index > 0 ? this.V[index - 1] : this.W != null ? this.W : c.a;
            if (index == count) {
                b(input, avSyncPresentationTimeUs);
            } else {
                c audioProcessor = this.U[index];
                audioProcessor.a(input);
                ByteBuffer output = audioProcessor.e();
                this.V[index] = output;
                if (output.hasRemaining()) {
                    index++;
                }
            }
            if (!input.hasRemaining()) {
                index--;
            } else {
                return;
            }
        }
    }

    private boolean b(ByteBuffer buffer, long avSyncPresentationTimeUs) throws h {
        if (!buffer.hasRemaining()) {
            return true;
        }
        int bytesRemaining;
        if (this.X != null) {
            boolean z;
            if (this.X == buffer) {
                z = true;
            } else {
                z = false;
            }
            com.google.android.exoplayer2.d.a.a(z);
        } else {
            this.X = buffer;
            if (s.a < 21) {
                bytesRemaining = buffer.remaining();
                if (this.Y == null || this.Y.length < bytesRemaining) {
                    this.Y = new byte[bytesRemaining];
                }
                int originalPosition = buffer.position();
                buffer.get(this.Y, 0, bytesRemaining);
                buffer.position(originalPosition);
                this.Z = 0;
            }
        }
        bytesRemaining = buffer.remaining();
        int bytesWritten = 0;
        if (s.a < 21) {
            int bytesToWrite = this.t - ((int) (this.M - (this.j.b() * ((long) this.L))));
            if (bytesToWrite > 0) {
                bytesWritten = this.m.write(this.Y, this.Z, Math.min(bytesRemaining, bytesToWrite));
                if (bytesWritten > 0) {
                    this.Z += bytesWritten;
                    buffer.position(buffer.position() + bytesWritten);
                }
            }
        } else if (this.ae) {
            com.google.android.exoplayer2.d.a.b(avSyncPresentationTimeUs != -9223372036854775807L);
            AudioTrack audioTrack = this.m;
            if (this.z == null) {
                this.z = ByteBuffer.allocate(16);
                this.z.order(ByteOrder.BIG_ENDIAN);
                this.z.putInt(1431633921);
            }
            if (this.A == 0) {
                this.z.putInt(4, bytesRemaining);
                this.z.putLong(8, 1000 * avSyncPresentationTimeUs);
                this.z.position(0);
                this.A = bytesRemaining;
            }
            int remaining = this.z.remaining();
            if (remaining > 0) {
                bytesWritten = audioTrack.write(this.z, remaining, 1);
                if (bytesWritten < 0) {
                    this.A = 0;
                } else if (bytesWritten < remaining) {
                    bytesWritten = 0;
                }
            }
            bytesWritten = audioTrack.write(buffer, bytesRemaining, 1);
            if (bytesWritten < 0) {
                this.A = 0;
            } else {
                this.A -= bytesWritten;
            }
        } else {
            bytesWritten = this.m.write(buffer, bytesRemaining, 1);
        }
        this.ag = SystemClock.elapsedRealtime();
        if (bytesWritten < 0) {
            throw new h(bytesWritten);
        }
        if (!this.s) {
            this.M += (long) bytesWritten;
        }
        if (bytesWritten != bytesRemaining) {
            return false;
        }
        if (this.s) {
            this.N += (long) this.O;
        }
        this.X = null;
        return true;
    }

    public final void c() throws h {
        if (!this.ab && o() && l()) {
            this.j.a(p());
            this.A = 0;
            this.ab = true;
        }
    }

    private boolean l() throws h {
        boolean audioProcessorNeedsEndOfStream = false;
        if (this.aa == -1) {
            this.aa = this.s ? this.U.length : 0;
            audioProcessorNeedsEndOfStream = true;
        }
        while (this.aa < this.U.length) {
            c audioProcessor = this.U[this.aa];
            if (audioProcessorNeedsEndOfStream) {
                audioProcessor.d();
            }
            a(-9223372036854775807L);
            if (!audioProcessor.f()) {
                return false;
            }
            audioProcessorNeedsEndOfStream = true;
            this.aa++;
        }
        if (this.X != null) {
            b(this.X, -9223372036854775807L);
            if (this.X != null) {
                return false;
            }
        }
        this.aa = -1;
        return true;
    }

    public final boolean d() {
        return !o() || (this.ab && !e());
    }

    public final boolean e() {
        if (o()) {
            if (p() > this.j.b()) {
                return true;
            }
            boolean z;
            if (r() && this.m.getPlayState() == 2 && this.m.getPlaybackHeadPosition() == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final l a(l playbackParameters) {
        if (this.s) {
            this.w = l.a;
            return this.w;
        }
        l playbackParameters2 = new l(this.e.a(playbackParameters.b), this.e.b(playbackParameters.c));
        l lastSetPlaybackParameters = this.v != null ? this.v : !this.k.isEmpty() ? ((g) this.k.getLast()).a : this.w;
        if (!playbackParameters2.equals(lastSetPlaybackParameters)) {
            if (o()) {
                this.v = playbackParameters2;
            } else {
                this.w = playbackParameters2;
            }
        }
        playbackParameters = playbackParameters2;
        return this.w;
    }

    public final l f() {
        return this.w;
    }

    public final void a(int streamType) {
        if (this.r != streamType) {
            this.r = streamType;
            if (!this.ae) {
                i();
                this.ad = 0;
            }
        }
    }

    public final void b(int tunnelingAudioSessionId) {
        com.google.android.exoplayer2.d.a.b(s.a >= 21);
        if (!this.ae || this.ad != tunnelingAudioSessionId) {
            this.ae = true;
            this.ad = tunnelingAudioSessionId;
            i();
        }
    }

    public final void g() {
        if (this.ae) {
            this.ae = false;
            this.ad = 0;
            i();
        }
    }

    public final void a(float volume) {
        if (this.T != volume) {
            this.T = volume;
            m();
        }
    }

    private void m() {
        if (!o()) {
            return;
        }
        if (s.a >= 21) {
            this.m.setVolume(this.T);
            return;
        }
        AudioTrack audioTrack = this.m;
        float f = this.T;
        audioTrack.setStereoVolume(f, f);
    }

    public final void h() {
        this.ac = false;
        if (o()) {
            q();
            this.j.a();
        }
    }

    public final void i() {
        if (o()) {
            this.J = 0;
            this.K = 0;
            this.M = 0;
            this.N = 0;
            this.O = 0;
            if (this.v != null) {
                this.w = this.v;
                this.v = null;
            } else if (!this.k.isEmpty()) {
                this.w = ((g) this.k.getLast()).a;
            }
            this.k.clear();
            this.x = 0;
            this.y = 0;
            this.W = null;
            this.X = null;
            for (int i = 0; i < this.U.length; i++) {
                c audioProcessor = this.U[i];
                audioProcessor.g();
                this.V[i] = audioProcessor.e();
            }
            this.ab = false;
            this.aa = -1;
            this.z = null;
            this.A = 0;
            this.P = 0;
            this.S = 0;
            q();
            if (this.m.getPlayState() == 3) {
                this.m.pause();
            }
            final AudioTrack toRelease = this.m;
            this.m = null;
            this.j.a(null, false);
            this.h.close();
            new Thread(this) {
                final /* synthetic */ e b;

                public final void run() {
                    try {
                        toRelease.flush();
                        toRelease.release();
                    } finally {
                        this.b.h.open();
                    }
                }
            }.start();
        }
    }

    public final void j() {
        i();
        n();
        for (c h : this.f) {
            h.h();
        }
        this.ad = 0;
        this.ac = false;
    }

    private void n() {
        if (this.l != null) {
            final AudioTrack toRelease = this.l;
            this.l = null;
            new Thread(this) {
                final /* synthetic */ e b;

                public final void run() {
                    toRelease.release();
                }
            }.start();
        }
    }

    private long b(long positionUs) {
        while (!this.k.isEmpty() && positionUs >= ((g) this.k.getFirst()).c) {
            g checkpoint = (g) this.k.remove();
            this.w = checkpoint.a;
            this.y = checkpoint.c;
            this.x = checkpoint.b - this.Q;
        }
        if (this.w.b == 1.0f) {
            return (this.x + positionUs) - this.y;
        }
        if (!this.k.isEmpty() || this.e.j() < 1024) {
            return this.x + ((long) (((double) this.w.b) * ((double) (positionUs - this.y))));
        }
        return s.a(positionUs - this.y, this.e.i(), this.e.j()) + this.x;
    }

    private boolean o() {
        return this.m != null;
    }

    private long c(long frameCount) {
        return (1000000 * frameCount) / ((long) this.n);
    }

    private long d(long durationUs) {
        return (((long) this.n) * durationUs) / 1000000;
    }

    private long p() {
        return this.s ? this.N : this.M / ((long) this.L);
    }

    private void q() {
        this.D = 0;
        this.C = 0;
        this.B = 0;
        this.E = 0;
        this.F = false;
        this.G = 0;
    }

    private boolean r() {
        return s.a < 23 && (this.q == 5 || this.q == 6);
    }

    private static int b(String mimeType) {
        int i = -1;
        switch (mimeType.hashCode()) {
            case -1095064472:
                if (mimeType.equals("audio/vnd.dts")) {
                    i = 2;
                    break;
                }
                break;
            case 187078296:
                if (mimeType.equals("audio/ac3")) {
                    i = 0;
                    break;
                }
                break;
            case 1504578661:
                if (mimeType.equals("audio/eac3")) {
                    i = 1;
                    break;
                }
                break;
            case 1505942594:
                if (mimeType.equals("audio/vnd.dts.hd")) {
                    i = 3;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 5;
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 8;
            default:
                return 0;
        }
    }

    public final long a(boolean sourceEnded) {
        Object obj;
        if (!o() || this.P == 0) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj == null) {
            return Long.MIN_VALUE;
        }
        long positionUs;
        if (this.m.getPlayState() == 3) {
            long c = this.j.c();
            if (c != 0) {
                long nanoTime = System.nanoTime() / 1000;
                if (nanoTime - this.E >= 30000) {
                    this.i[this.B] = c - nanoTime;
                    this.B = (this.B + 1) % 10;
                    if (this.C < 10) {
                        this.C++;
                    }
                    this.E = nanoTime;
                    this.D = 0;
                    for (int i = 0; i < this.C; i++) {
                        this.D += this.i[i] / ((long) this.C);
                    }
                }
                if (!r() && nanoTime - this.G >= 500000) {
                    this.F = this.j.d();
                    if (this.F) {
                        long e = this.j.e() / 1000;
                        long f = this.j.f();
                        if (e >= this.R) {
                            String str;
                            if (Math.abs(e - nanoTime) > 5000000) {
                                str = "Spurious audio timestamp (system clock mismatch): " + f + ", " + e + ", " + nanoTime + ", " + c;
                                if (b) {
                                    throw new e(str);
                                }
                                this.F = false;
                            } else if (Math.abs(c(f) - c) > 5000000) {
                                str = "Spurious audio timestamp (frame position mismatch): " + f + ", " + e + ", " + nanoTime + ", " + c;
                                if (b) {
                                    throw new e(str);
                                }
                            }
                        }
                        this.F = false;
                    }
                    if (!(this.H == null || this.s)) {
                        try {
                            this.S = (((long) ((Integer) this.H.invoke(this.m, null)).intValue()) * 1000) - this.u;
                            this.S = Math.max(this.S, 0);
                            if (this.S > 5000000) {
                                new StringBuilder("Ignoring impossibly large audio latency: ").append(this.S);
                                this.S = 0;
                            }
                        } catch (Exception e2) {
                            this.H = null;
                        }
                    }
                    this.G = nanoTime;
                }
            }
        }
        long systemClockUs = System.nanoTime() / 1000;
        if (this.F) {
            positionUs = c(this.j.f() + d(systemClockUs - (this.j.e() / 1000)));
        } else {
            if (this.C == 0) {
                positionUs = this.j.c();
            } else {
                positionUs = systemClockUs + this.D;
            }
            if (!sourceEnded) {
                positionUs -= this.S;
            }
        }
        return this.Q + b(positionUs);
    }
}
