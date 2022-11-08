package com.google.android.exoplayer2.a;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.a.e.f;
import com.google.android.exoplayer2.b.b;
import com.google.android.exoplayer2.b.c;
import com.google.android.exoplayer2.b.d;
import com.google.android.exoplayer2.d.g;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.e;
import com.google.android.exoplayer2.l;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class h extends b implements g {
    private final com.google.android.exoplayer2.a.d.a b;
    private final e c;
    private boolean d;
    private boolean e;
    private MediaFormat f;
    private int g;
    private int h;
    private long i;
    private boolean j;

    private final class a implements f {
        final /* synthetic */ h a;

        private a(h hVar) {
            this.a = hVar;
        }

        /* synthetic */ a(h x0, byte b) {
            this(x0);
        }

        public final void a(int audioSessionId) {
            this.a.b.a(audioSessionId);
            h.v();
        }

        public final void a() {
            h.w();
            this.a.j = true;
        }

        public final void a(int bufferSize, long bufferSizeMs, long elapsedSinceLastFeedMs) {
            this.a.b.a(bufferSize, bufferSizeMs, elapsedSinceLastFeedMs);
            h.x();
        }
    }

    public h(c mediaCodecSelector, com.google.android.exoplayer2.drm.b<e> drmSessionManager, Handler eventHandler, d eventListener, b audioCapabilities, c... audioProcessors) {
        super(1, mediaCodecSelector, drmSessionManager, true);
        this.c = new e(audioCapabilities, audioProcessors, new a());
        this.b = new com.google.android.exoplayer2.a.d.a(eventHandler, eventListener);
    }

    protected final int a(c mediaCodecSelector, Format format) throws d.b {
        boolean z = false;
        String mimeType = format.f;
        if (!com.google.android.exoplayer2.d.h.a(mimeType)) {
            return 0;
        }
        int tunnelingSupport;
        if (s.a >= 21) {
            tunnelingSupport = 16;
        } else {
            tunnelingSupport = 0;
        }
        if (a(mimeType) && mediaCodecSelector.a() != null) {
            return (tunnelingSupport | 4) | 3;
        }
        com.google.android.exoplayer2.b.a decoderInfo = mediaCodecSelector.a(mimeType, false);
        if (decoderInfo == null) {
            return 1;
        }
        if (s.a < 21 || ((format.s == -1 || decoderInfo.a(format.s)) && (format.r == -1 || decoderInfo.b(format.r)))) {
            z = true;
        }
        return (tunnelingSupport | 4) | (z ? 3 : 2);
    }

    protected final com.google.android.exoplayer2.b.a a(c mediaCodecSelector, Format format, boolean requiresSecureDecoder) throws d.b {
        if (a(format.f)) {
            com.google.android.exoplayer2.b.a passthroughDecoderInfo = mediaCodecSelector.a();
            if (passthroughDecoderInfo != null) {
                this.d = true;
                return passthroughDecoderInfo;
            }
        }
        this.d = false;
        return super.a(mediaCodecSelector, format, requiresSecureDecoder);
    }

    private boolean a(String mimeType) {
        return this.c.a(mimeType);
    }

    protected final void a(com.google.android.exoplayer2.b.a codecInfo, MediaCodec codec, Format format, MediaCrypto crypto) {
        boolean z;
        String str = codecInfo.a;
        if (s.a < 24 && "OMX.SEC.aac.dec".equals(str) && "samsung".equals(s.c) && (s.b.startsWith("zeroflte") || s.b.startsWith("herolte") || s.b.startsWith("heroqlte"))) {
            z = true;
        } else {
            z = false;
        }
        this.e = z;
        if (this.d) {
            this.f = format.b();
            this.f.setString("mime", "audio/raw");
            codec.configure(this.f, null, crypto, 0);
            this.f.setString("mime", format.f);
            return;
        }
        codec.configure(format.b(), null, crypto, 0);
        this.f = null;
    }

    public final g c() {
        return this;
    }

    protected final void a(String name, long initializedTimestampMs, long initializationDurationMs) {
        this.b.a(name, initializedTimestampMs, initializationDurationMs);
    }

    protected final void b(Format newFormat) throws ExoPlaybackException {
        super.b(newFormat);
        this.b.a(newFormat);
        this.g = "audio/raw".equals(newFormat.f) ? newFormat.t : 2;
        this.h = newFormat.r;
    }

    protected final void a(MediaCodec codec, MediaFormat outputFormat) throws ExoPlaybackException {
        MediaFormat format;
        int[] channelMap;
        boolean passthrough = this.f != null;
        String mimeType = passthrough ? this.f.getString("mime") : "audio/raw";
        if (passthrough) {
            format = this.f;
        } else {
            format = outputFormat;
        }
        int channelCount = format.getInteger("channel-count");
        int sampleRate = format.getInteger("sample-rate");
        if (this.e && channelCount == 6 && this.h < 6) {
            channelMap = new int[this.h];
            for (int i = 0; i < this.h; i++) {
                channelMap[i] = i;
            }
        } else {
            channelMap = null;
        }
        try {
            this.c.a(mimeType, channelCount, sampleRate, this.g, channelMap);
        } catch (Exception e) {
            throw ExoPlaybackException.a(e, r());
        }
    }

    protected static void v() {
    }

    protected static void w() {
    }

    protected static void x() {
    }

    protected final void a(boolean joining) throws ExoPlaybackException {
        super.a(joining);
        this.b.a(this.a);
        int tunnelingAudioSessionId = q().b;
        if (tunnelingAudioSessionId != 0) {
            this.c.b(tunnelingAudioSessionId);
        } else {
            this.c.g();
        }
    }

    protected final void a(long positionUs, boolean joining) throws ExoPlaybackException {
        super.a(positionUs, joining);
        this.c.i();
        this.i = positionUs;
        this.j = true;
    }

    protected final void n() {
        super.n();
        this.c.a();
    }

    protected final void o() {
        this.c.h();
        super.o();
    }

    protected final void p() {
        try {
            this.c.j();
            try {
                super.p();
            } finally {
                this.a.a();
                this.b.b(this.a);
            }
        } catch (Throwable th) {
            super.p();
        } finally {
            this.a.a();
            this.b.b(this.a);
        }
    }

    public final boolean u() {
        return super.u() && this.c.d();
    }

    public final boolean t() {
        return this.c.e() || super.t();
    }

    public final long y() {
        long newCurrentPositionUs = this.c.a(u());
        if (newCurrentPositionUs != Long.MIN_VALUE) {
            if (!this.j) {
                newCurrentPositionUs = Math.max(this.i, newCurrentPositionUs);
            }
            this.i = newCurrentPositionUs;
            this.j = false;
        }
        return this.i;
    }

    public final l a(l playbackParameters) {
        return this.c.a(playbackParameters);
    }

    public final l z() {
        return this.c.f();
    }

    protected final boolean a(long positionUs, long elapsedRealtimeUs, MediaCodec codec, ByteBuffer buffer, int bufferIndex, int bufferFlags, long bufferPresentationTimeUs, boolean shouldSkip) throws ExoPlaybackException {
        Exception e;
        com.google.android.exoplayer2.decoder.d dVar;
        if (this.d && (bufferFlags & 2) != 0) {
            codec.releaseOutputBuffer(bufferIndex, false);
            return true;
        } else if (shouldSkip) {
            codec.releaseOutputBuffer(bufferIndex, false);
            dVar = this.a;
            dVar.e++;
            this.c.b();
            return true;
        } else {
            try {
                if (!this.c.a(buffer, bufferPresentationTimeUs)) {
                    return false;
                }
                codec.releaseOutputBuffer(bufferIndex, false);
                dVar = this.a;
                dVar.d++;
                return true;
            } catch (e.d e2) {
                e = e2;
                throw ExoPlaybackException.a(e, r());
            } catch (com.google.android.exoplayer2.a.e.h e3) {
                e = e3;
                throw ExoPlaybackException.a(e, r());
            }
        }
    }

    protected final void A() throws ExoPlaybackException {
        try {
            this.c.c();
        } catch (Exception e) {
            throw ExoPlaybackException.a(e, r());
        }
    }

    public final void a(int messageType, Object message) throws ExoPlaybackException {
        switch (messageType) {
            case 2:
                this.c.a(((Float) message).floatValue());
                return;
            case 3:
                this.c.a(((Integer) message).intValue());
                return;
            default:
                super.a(messageType, message);
                return;
        }
    }
}
