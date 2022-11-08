package com.google.android.exoplayer2.b;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CodecException;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.d.i;
import com.google.android.exoplayer2.d.r;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.d;
import com.google.android.exoplayer2.drm.e;
import com.google.android.exoplayer2.h;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(16)
public abstract class b extends com.google.android.exoplayer2.a {
    private static final byte[] b = s.f("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private long A;
    private int B;
    private int C;
    private boolean D;
    private boolean E;
    private int F;
    private int G;
    private boolean H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    protected d a;
    private final c c;
    private final com.google.android.exoplayer2.drm.b<e> d;
    private final boolean e;
    private final DecoderInputBuffer f;
    private final DecoderInputBuffer g;
    private final h h;
    private final List<Long> i;
    private final BufferInfo j;
    private Format k;
    private MediaCodec l;
    private com.google.android.exoplayer2.drm.a<e> m;
    private com.google.android.exoplayer2.drm.a<e> n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private ByteBuffer[] y;
    private ByteBuffer[] z;

    public static class a extends Exception {
        public final String a;
        public final boolean b;
        public final String c;
        public final String d;

        public a(Format format, Throwable cause, boolean secureDecoderRequired, int errorCode) {
            super("Decoder init failed: [" + errorCode + "], " + format, cause);
            this.a = format.f;
            this.b = secureDecoderRequired;
            this.c = null;
            this.d = "com.google.android.exoplayer.MediaCodecTrackRenderer_" + (errorCode < 0 ? "neg_" : "") + Math.abs(errorCode);
        }

        public a(Format format, Throwable cause, boolean secureDecoderRequired, String decoderName) {
            String str = null;
            super("Decoder init failed: " + decoderName + ", " + format, cause);
            this.a = format.f;
            this.b = secureDecoderRequired;
            this.c = decoderName;
            if (s.a >= 21 && (cause instanceof CodecException)) {
                str = ((CodecException) cause).getDiagnosticInfo();
            }
            this.d = str;
        }
    }

    protected abstract int a(c cVar, Format format) throws com.google.android.exoplayer2.b.d.b;

    protected abstract void a(a aVar, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) throws com.google.android.exoplayer2.b.d.b;

    protected abstract boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws ExoPlaybackException;

    public b(int trackType, c mediaCodecSelector, com.google.android.exoplayer2.drm.b<e> drmSessionManager, boolean playClearSamplesWithoutKeys) {
        super(trackType);
        com.google.android.exoplayer2.d.a.b(s.a >= 16);
        this.c = (c) com.google.android.exoplayer2.d.a.a((Object) mediaCodecSelector);
        this.d = drmSessionManager;
        this.e = playClearSamplesWithoutKeys;
        this.f = new DecoderInputBuffer(0);
        this.g = new DecoderInputBuffer(0);
        this.h = new h();
        this.i = new ArrayList();
        this.j = new BufferInfo();
        this.F = 0;
        this.G = 0;
    }

    public final int m() throws ExoPlaybackException {
        return 4;
    }

    public final int a(Format format) throws ExoPlaybackException {
        try {
            return a(this.c, format);
        } catch (Exception e) {
            throw ExoPlaybackException.a(e, r());
        }
    }

    protected a a(c mediaCodecSelector, Format format, boolean requiresSecureDecoder) throws com.google.android.exoplayer2.b.d.b {
        return mediaCodecSelector.a(format.f, requiresSecureDecoder);
    }

    protected final void B() throws ExoPlaybackException {
        if (C()) {
            boolean z;
            this.m = this.n;
            String mimeType = this.k.f;
            MediaCrypto mediaCrypto = null;
            boolean drmSessionRequiresSecureDecoder = false;
            if (this.m != null) {
                int drmSessionState = this.m.b();
                if (drmSessionState == 0) {
                    throw ExoPlaybackException.a(this.m.d(), r());
                } else if (drmSessionState == 3 || drmSessionState == 4) {
                    mediaCrypto = ((e) this.m.c()).a();
                    drmSessionRequiresSecureDecoder = this.m.a(mimeType);
                } else {
                    return;
                }
            }
            a decoderInfo = null;
            try {
                decoderInfo = a(this.c, this.k, drmSessionRequiresSecureDecoder);
                if (decoderInfo == null && drmSessionRequiresSecureDecoder) {
                    decoderInfo = a(this.c, this.k, false);
                    if (decoderInfo != null) {
                        new StringBuilder("Drm session requires secure decoder for ").append(mimeType).append(", but no secure decoder available. Trying to proceed with ").append(decoderInfo.a).append(".");
                    }
                }
            } catch (Throwable e) {
                a(new a(this.k, e, drmSessionRequiresSecureDecoder, -49998));
            }
            if (decoderInfo == null) {
                a(new a(this.k, null, drmSessionRequiresSecureDecoder, -49999));
            }
            String codecName = decoderInfo.a;
            this.o = decoderInfo.b;
            Format format = this.k;
            if (s.a < 21 && format.h.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(codecName)) {
                z = true;
            } else {
                z = false;
            }
            this.p = z;
            if (s.a < 18 || ((s.a == 18 && ("OMX.SEC.avc.dec".equals(codecName) || "OMX.SEC.avc.dec.secure".equals(codecName))) || (s.a == 19 && s.d.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(codecName) || "OMX.Exynos.avc.dec.secure".equals(codecName))))) {
                z = true;
            } else {
                z = false;
            }
            this.q = z;
            if (s.a >= 24 || !(("OMX.Nvidia.h264.decode".equals(codecName) || "OMX.Nvidia.h264.decode.secure".equals(codecName)) && ("flounder".equals(s.b) || "flounder_lte".equals(s.b) || "grouper".equals(s.b) || "tilapia".equals(s.b)))) {
                z = false;
            } else {
                z = true;
            }
            this.r = z;
            if (s.a > 17 || !("OMX.rk.video_decoder.avc".equals(codecName) || "OMX.allwinner.video.decoder.avc".equals(codecName))) {
                z = false;
            } else {
                z = true;
            }
            this.s = z;
            if ((s.a > 23 || !"OMX.google.vorbis.decoder".equals(codecName)) && !(s.a <= 19 && "hb2000".equals(s.b) && ("OMX.amlogic.avc.decoder.awesome".equals(codecName) || "OMX.amlogic.avc.decoder.awesome.secure".equals(codecName)))) {
                z = false;
            } else {
                z = true;
            }
            this.t = z;
            if (s.a == 21 && "OMX.google.aac.decoder".equals(codecName)) {
                z = true;
            } else {
                z = false;
            }
            this.u = z;
            format = this.k;
            if (s.a <= 18 && format.r == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(codecName)) {
                z = true;
            } else {
                z = false;
            }
            this.v = z;
            try {
                long codecInitializingTimestamp = SystemClock.elapsedRealtime();
                r.a("createCodec:" + codecName);
                this.l = MediaCodec.createByCodecName(codecName);
                r.a();
                r.a("configureCodec");
                a(decoderInfo, this.l, this.k, mediaCrypto);
                r.a();
                r.a("startCodec");
                this.l.start();
                r.a();
                long codecInitializedTimestamp = SystemClock.elapsedRealtime();
                a(codecName, codecInitializedTimestamp, codecInitializedTimestamp - codecInitializingTimestamp);
                this.y = this.l.getInputBuffers();
                this.z = this.l.getOutputBuffers();
            } catch (Throwable e2) {
                a(new a(this.k, e2, drmSessionRequiresSecureDecoder, codecName));
            }
            this.A = d() == 2 ? SystemClock.elapsedRealtime() + 1000 : -9223372036854775807L;
            this.B = -1;
            this.C = -1;
            this.M = true;
            d dVar = this.a;
            dVar.a++;
        }
    }

    private void a(a e) throws ExoPlaybackException {
        throw ExoPlaybackException.a(e, r());
    }

    protected boolean C() {
        return this.l == null && this.k != null;
    }

    protected final MediaCodec D() {
        return this.l;
    }

    protected void a(boolean joining) throws ExoPlaybackException {
        this.a = new d();
    }

    protected void a(long positionUs, boolean joining) throws ExoPlaybackException {
        this.J = false;
        this.K = false;
        if (this.l != null) {
            this.A = -9223372036854775807L;
            this.B = -1;
            this.C = -1;
            this.M = true;
            this.L = false;
            this.D = false;
            this.i.clear();
            this.w = false;
            this.x = false;
            if (this.q || (this.t && this.I)) {
                E();
                B();
            } else if (this.G != 0) {
                E();
                B();
            } else {
                this.l.flush();
                this.H = false;
            }
            if (this.E && this.k != null) {
                this.F = 1;
            }
        }
    }

    protected void p() {
        this.k = null;
        try {
            E();
            try {
                if (this.m != null) {
                    this.d.a();
                }
                try {
                    if (!(this.n == null || this.n == this.m)) {
                        this.d.a();
                    }
                    this.m = null;
                    this.n = null;
                } catch (Throwable th) {
                    this.m = null;
                    this.n = null;
                }
            } catch (Throwable th2) {
                this.m = null;
                this.n = null;
            }
        } catch (Throwable th3) {
            this.m = null;
            this.n = null;
        }
    }

    protected final void E() {
        if (this.l != null) {
            this.A = -9223372036854775807L;
            this.B = -1;
            this.C = -1;
            this.L = false;
            this.D = false;
            this.i.clear();
            this.y = null;
            this.z = null;
            this.E = false;
            this.H = false;
            this.o = false;
            this.p = false;
            this.q = false;
            this.r = false;
            this.s = false;
            this.t = false;
            this.v = false;
            this.w = false;
            this.x = false;
            this.I = false;
            this.F = 0;
            this.G = 0;
            d dVar = this.a;
            dVar.b++;
            this.f.b = null;
            try {
                this.l.stop();
                try {
                    this.l.release();
                    this.l = null;
                    if (this.m != null && this.n != this.m) {
                        try {
                            this.d.a();
                        } finally {
                            this.m = null;
                        }
                    }
                } catch (Throwable th) {
                    this.l = null;
                    if (this.m != null && this.n != this.m) {
                        this.d.a();
                    }
                } finally {
                    this.m = null;
                }
            } catch (Throwable th2) {
                this.l = null;
                if (this.m != null && this.n != this.m) {
                    try {
                        this.d.a();
                    } catch (Throwable th3) {
                        this.m = null;
                    }
                }
            } finally {
                this.m = null;
            }
        }
    }

    protected void n() {
    }

    protected void o() {
    }

    public final void a(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        if (this.K) {
            A();
            return;
        }
        int result;
        if (this.k == null) {
            this.g.a();
            result = a(this.h, this.g, true);
            if (result == -5) {
                b(this.h.a);
            } else if (result == -4) {
                com.google.android.exoplayer2.d.a.b(this.g.c());
                this.J = true;
                w();
                return;
            } else {
                return;
            }
        }
        B();
        if (this.l != null) {
            r.a("drainAndFeed");
            do {
            } while (b(positionUs, elapsedRealtimeUs));
            do {
            } while (v());
            r.a();
        } else {
            b(positionUs);
            this.g.a();
            result = a(this.h, this.g, false);
            if (result == -5) {
                b(this.h.a);
            } else if (result == -4) {
                com.google.android.exoplayer2.d.a.b(this.g.c());
                this.J = true;
                w();
            }
        }
        this.a.a();
    }

    private boolean v() throws ExoPlaybackException {
        if (this.l == null || this.G == 2 || this.J) {
            return false;
        }
        if (this.B < 0) {
            this.B = this.l.dequeueInputBuffer(0);
            if (this.B < 0) {
                return false;
            }
            this.f.b = this.y[this.B];
            this.f.a();
        }
        if (this.G == 1) {
            if (!this.s) {
                this.I = true;
                this.l.queueInputBuffer(this.B, 0, 0, 0, 4);
                this.B = -1;
            }
            this.G = 2;
            return false;
        } else if (this.w) {
            this.w = false;
            this.f.b.put(b);
            this.l.queueInputBuffer(this.B, 0, b.length, 0, 0);
            this.B = -1;
            this.H = true;
            return true;
        } else {
            int result;
            int adaptiveReconfigurationBytes = 0;
            if (this.L) {
                result = -4;
            } else {
                if (this.F == 1) {
                    for (int i = 0; i < this.k.h.size(); i++) {
                        this.f.b.put((byte[]) this.k.h.get(i));
                    }
                    this.F = 2;
                }
                adaptiveReconfigurationBytes = this.f.b.position();
                result = a(this.h, this.f, false);
            }
            if (result == -3) {
                return false;
            }
            if (result == -5) {
                if (this.F == 2) {
                    this.f.a();
                    this.F = 1;
                }
                b(this.h.a);
                return true;
            } else if (this.f.c()) {
                if (this.F == 2) {
                    this.f.a();
                    this.F = 1;
                }
                this.J = true;
                if (this.H) {
                    try {
                        if (!this.s) {
                            this.I = true;
                            this.l.queueInputBuffer(this.B, 0, 0, 0, 4);
                            this.B = -1;
                        }
                        return false;
                    } catch (Exception e) {
                        throw ExoPlaybackException.a(e, r());
                    }
                }
                w();
                return false;
            } else if (!this.M || this.f.d()) {
                boolean z;
                long presentationTimeUs;
                CryptoInfo a;
                int[] iArr;
                d dVar;
                this.M = false;
                boolean bufferEncrypted = this.f.g();
                if (this.m != null) {
                    int b = this.m.b();
                    if (b == 0) {
                        throw ExoPlaybackException.a(this.m.d(), r());
                    } else if (b != 4 && (bufferEncrypted || !this.e)) {
                        z = true;
                        this.L = z;
                        if (this.L) {
                            return false;
                        }
                        if (this.p && !bufferEncrypted) {
                            i.a(this.f.b);
                            if (this.f.b.position() == 0) {
                                return true;
                            }
                            this.p = false;
                        }
                        try {
                            presentationTimeUs = this.f.c;
                            if (this.f.k_()) {
                                this.i.add(Long.valueOf(presentationTimeUs));
                            }
                            this.f.h();
                            F();
                            if (bufferEncrypted) {
                                this.l.queueInputBuffer(this.B, 0, this.f.b.limit(), presentationTimeUs, 0);
                            } else {
                                a = this.f.a.a();
                                if (adaptiveReconfigurationBytes != 0) {
                                    if (a.numBytesOfClearData == null) {
                                        a.numBytesOfClearData = new int[1];
                                    }
                                    iArr = a.numBytesOfClearData;
                                    iArr[0] = iArr[0] + adaptiveReconfigurationBytes;
                                }
                                this.l.queueSecureInputBuffer(this.B, 0, a, presentationTimeUs, 0);
                            }
                            this.B = -1;
                            this.H = true;
                            this.F = 0;
                            dVar = this.a;
                            dVar.c++;
                            return true;
                        } catch (Exception e2) {
                            throw ExoPlaybackException.a(e2, r());
                        }
                    }
                }
                z = false;
                this.L = z;
                if (this.L) {
                    return false;
                }
                i.a(this.f.b);
                if (this.f.b.position() == 0) {
                    return true;
                }
                this.p = false;
                presentationTimeUs = this.f.c;
                if (this.f.k_()) {
                    this.i.add(Long.valueOf(presentationTimeUs));
                }
                this.f.h();
                F();
                if (bufferEncrypted) {
                    this.l.queueInputBuffer(this.B, 0, this.f.b.limit(), presentationTimeUs, 0);
                } else {
                    a = this.f.a.a();
                    if (adaptiveReconfigurationBytes != 0) {
                        if (a.numBytesOfClearData == null) {
                            a.numBytesOfClearData = new int[1];
                        }
                        iArr = a.numBytesOfClearData;
                        iArr[0] = iArr[0] + adaptiveReconfigurationBytes;
                    }
                    this.l.queueSecureInputBuffer(this.B, 0, a, presentationTimeUs, 0);
                }
                this.B = -1;
                this.H = true;
                this.F = 0;
                dVar = this.a;
                dVar.c++;
                return true;
            } else {
                this.f.a();
                if (this.F == 2) {
                    this.F = 1;
                }
                return true;
            }
        }
    }

    protected void a(String name, long initializedTimestampMs, long initializationDurationMs) {
    }

    protected void b(Format newFormat) throws ExoPlaybackException {
        Format oldFormat = this.k;
        this.k = newFormat;
        if (!s.a(this.k.i, oldFormat == null ? null : oldFormat.i)) {
            if (this.k.i == null) {
                this.n = null;
            } else if (this.d == null) {
                throw ExoPlaybackException.a(new IllegalStateException("Media requires a DrmSessionManager"), r());
            } else {
                this.n = this.d.a(Looper.myLooper(), this.k.i);
                if (this.n == this.m) {
                    this.d.a();
                }
            }
        }
        if (this.n == this.m && this.l != null && a(this.o, oldFormat, this.k)) {
            boolean z;
            this.E = true;
            this.F = 1;
            if (this.r && this.k.j == oldFormat.j && this.k.k == oldFormat.k) {
                z = true;
            } else {
                z = false;
            }
            this.w = z;
        } else if (this.H) {
            this.G = 1;
        } else {
            E();
            B();
        }
    }

    protected void a(MediaCodec codec, MediaFormat outputFormat) throws ExoPlaybackException {
    }

    protected void F() {
    }

    protected boolean a(boolean codecIsAdaptive, Format oldFormat, Format newFormat) {
        return false;
    }

    public boolean u() {
        return this.K;
    }

    public boolean t() {
        return (this.k == null || this.L || (!s() && this.C < 0 && (this.A == -9223372036854775807L || SystemClock.elapsedRealtime() >= this.A))) ? false : true;
    }

    private boolean b(long positionUs, long elapsedRealtimeUs) throws ExoPlaybackException {
        boolean processedOutputBuffer;
        if (this.C < 0) {
            if (this.u && this.I) {
                try {
                    this.C = this.l.dequeueOutputBuffer(this.j, 0);
                } catch (IllegalStateException e) {
                    w();
                    if (this.K) {
                        E();
                    }
                    return false;
                }
            }
            this.C = this.l.dequeueOutputBuffer(this.j, 0);
            if (this.C >= 0) {
                if (this.x) {
                    this.x = false;
                    this.l.releaseOutputBuffer(this.C, false);
                    this.C = -1;
                    return true;
                } else if ((this.j.flags & 4) != 0) {
                    w();
                    this.C = -1;
                    return false;
                } else {
                    boolean z;
                    ByteBuffer outputBuffer = this.z[this.C];
                    if (outputBuffer != null) {
                        outputBuffer.position(this.j.offset);
                        outputBuffer.limit(this.j.offset + this.j.size);
                    }
                    long j = this.j.presentationTimeUs;
                    int size = this.i.size();
                    for (int i = 0; i < size; i++) {
                        if (((Long) this.i.get(i)).longValue() == j) {
                            this.i.remove(i);
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    this.D = z;
                }
            } else if (this.C == -2) {
                MediaFormat outputFormat = this.l.getOutputFormat();
                if (this.r && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
                    this.x = true;
                } else {
                    if (this.v) {
                        outputFormat.setInteger("channel-count", 1);
                    }
                    a(this.l, outputFormat);
                }
                return true;
            } else if (this.C == -3) {
                this.z = this.l.getOutputBuffers();
                return true;
            } else {
                if (this.s && (this.J || this.G == 2)) {
                    w();
                }
                return false;
            }
        }
        if (this.u && this.I) {
            try {
                processedOutputBuffer = a(positionUs, elapsedRealtimeUs, this.l, this.z[this.C], this.C, this.j.flags, this.j.presentationTimeUs, this.D);
            } catch (IllegalStateException e2) {
                w();
                if (this.K) {
                    E();
                }
                return false;
            }
        }
        processedOutputBuffer = a(positionUs, elapsedRealtimeUs, this.l, this.z[this.C], this.C, this.j.flags, this.j.presentationTimeUs, this.D);
        if (!processedOutputBuffer) {
            return false;
        }
        long j2 = this.j.presentationTimeUs;
        this.C = -1;
        return true;
    }

    protected void A() throws ExoPlaybackException {
    }

    private void w() throws ExoPlaybackException {
        if (this.G == 2) {
            E();
            B();
            return;
        }
        this.K = true;
        A();
    }
}
