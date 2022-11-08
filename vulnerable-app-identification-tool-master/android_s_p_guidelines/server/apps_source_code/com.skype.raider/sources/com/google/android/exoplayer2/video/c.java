package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.d;
import com.google.android.exoplayer2.d.h;
import com.google.android.exoplayer2.d.r;
import com.google.android.exoplayer2.d.s;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.e;
import com.skype.android.video.hw.utils.CodecUtils;
import java.nio.ByteBuffer;

@TargetApi(16)
public final class c extends com.google.android.exoplayer2.b.b {
    private static final int[] c = new int[]{1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private float A;
    private boolean B;
    private int C;
    b b;
    private final d d;
    private final com.google.android.exoplayer2.video.e.a e;
    private final long f;
    private final int g = 50;
    private final boolean h;
    private Format[] i;
    private a j;
    private Surface k;
    private int l;
    private boolean m;
    private long n;
    private long o;
    private int p;
    private int q;
    private int r;
    private float s;
    private int t;
    private int u;
    private int v;
    private float w;
    private int x;
    private int y;
    private int z;

    protected static final class a {
        public final int a;
        public final int b;
        public final int c;

        public a(int width, int height, int inputSize) {
            this.a = width;
            this.b = height;
            this.c = inputSize;
        }
    }

    @TargetApi(23)
    private final class b implements OnFrameRenderedListener {
        final /* synthetic */ c a;

        /* synthetic */ b(c x0, MediaCodec x1, byte b) {
            this(x0, x1);
        }

        private b(c cVar, MediaCodec codec) {
            this.a = cVar;
            codec.setOnFrameRenderedListener(this, new Handler());
        }

        public final void onFrameRendered(@NonNull MediaCodec codec, long presentationTimeUs, long nanoTime) {
            if (this == this.a.b) {
                this.a.v();
            }
        }
    }

    public c(Context context, com.google.android.exoplayer2.b.c mediaCodecSelector, long allowedJoiningTimeMs, com.google.android.exoplayer2.drm.b<e> drmSessionManager, Handler eventHandler, e eventListener) {
        boolean z = false;
        super(2, mediaCodecSelector, drmSessionManager, false);
        this.f = allowedJoiningTimeMs;
        this.d = new d(context);
        this.e = new com.google.android.exoplayer2.video.e.a(eventHandler, eventListener);
        if (s.a <= 22 && "foster".equals(s.b) && "NVIDIA".equals(s.c)) {
            z = true;
        }
        this.h = z;
        this.n = -9223372036854775807L;
        this.t = -1;
        this.u = -1;
        this.w = -1.0f;
        this.s = -1.0f;
        this.l = 1;
        y();
    }

    protected final int a(com.google.android.exoplayer2.b.c mediaCodecSelector, Format format) throws com.google.android.exoplayer2.b.d.b {
        String mimeType = format.f;
        if (!h.b(mimeType)) {
            return 0;
        }
        boolean requiresSecureDecryption = false;
        DrmInitData drmInitData = format.i;
        if (drmInitData != null) {
            for (int i = 0; i < drmInitData.a; i++) {
                requiresSecureDecryption |= drmInitData.a(i).c;
            }
        }
        com.google.android.exoplayer2.b.a decoderInfo = mediaCodecSelector.a(mimeType, requiresSecureDecryption);
        if (decoderInfo == null) {
            return 1;
        }
        boolean decoderCapable = decoderInfo.b(format.c);
        if (decoderCapable && format.j > 0 && format.k > 0) {
            if (s.a >= 21) {
                decoderCapable = decoderInfo.a(format.j, format.k, (double) format.l);
            } else {
                decoderCapable = format.j * format.k <= d.b();
                if (!decoderCapable) {
                    new StringBuilder("FalseCheck [legacyFrameSize, ").append(format.j).append("x").append(format.k).append("] [").append(s.e).append("]");
                }
            }
        }
        return ((decoderInfo.b ? 8 : 4) | (decoderInfo.c ? 16 : 0)) | (decoderCapable ? 3 : 2);
    }

    protected final void a(boolean joining) throws ExoPlaybackException {
        super.a(joining);
        this.C = q().b;
        this.B = this.C != 0;
        this.e.a(this.a);
        this.d.a();
    }

    protected final void a(Format[] formats) throws ExoPlaybackException {
        this.i = formats;
        super.a(formats);
    }

    protected final void a(long positionUs, boolean joining) throws ExoPlaybackException {
        super.a(positionUs, joining);
        x();
        this.q = 0;
        if (joining) {
            w();
        } else {
            this.n = -9223372036854775807L;
        }
    }

    public final boolean t() {
        if ((this.m || super.C()) && super.t()) {
            this.n = -9223372036854775807L;
            return true;
        } else if (this.n == -9223372036854775807L) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.n) {
                return true;
            }
            this.n = -9223372036854775807L;
            return false;
        }
    }

    protected final void n() {
        super.n();
        this.p = 0;
        this.o = SystemClock.elapsedRealtime();
        this.n = -9223372036854775807L;
    }

    protected final void o() {
        H();
        super.o();
    }

    protected final void p() {
        this.t = -1;
        this.u = -1;
        this.w = -1.0f;
        this.s = -1.0f;
        y();
        x();
        this.d.b();
        this.b = null;
        try {
            super.p();
        } finally {
            this.a.a();
            this.e.b(this.a);
        }
    }

    public final void a(int messageType, Object message) throws ExoPlaybackException {
        if (messageType == 1) {
            Surface surface = (Surface) message;
            if (this.k != surface) {
                this.k = surface;
                int d = d();
                if (d == 1 || d == 2) {
                    MediaCodec D = D();
                    if (s.a < 23 || D == null || surface == null) {
                        E();
                        B();
                    } else {
                        D.setOutputSurface(surface);
                    }
                }
                if (surface != null) {
                    G();
                    x();
                    if (d == 2) {
                        w();
                        return;
                    }
                    return;
                }
                y();
                x();
            } else if (surface != null) {
                G();
                if (this.m) {
                    this.e.a(this.k);
                }
            }
        } else if (messageType == 4) {
            this.l = ((Integer) message).intValue();
            MediaCodec codec = D();
            if (codec != null) {
                codec.setVideoScalingMode(this.l);
            }
        } else {
            super.a(messageType, message);
        }
    }

    protected final boolean C() {
        return super.C() && this.k != null && this.k.isValid();
    }

    protected final void a(com.google.android.exoplayer2.b.a codecInfo, MediaCodec codec, Format format, MediaCrypto crypto) throws com.google.android.exoplayer2.b.d.b {
        a aVar;
        Format[] formatArr = this.i;
        int i = format.j;
        int i2 = format.k;
        int c = c(format);
        if (formatArr.length == 1) {
            aVar = new a(i, i2, c);
        } else {
            int i3 = 0;
            int length = formatArr.length;
            int i4 = 0;
            while (i4 < length) {
                int i5;
                Format format2 = formatArr[i4];
                if (b(codecInfo.b, format, format2)) {
                    i5 = (format2.j == -1 || format2.k == -1) ? 1 : 0;
                    i5 |= i3;
                    i = Math.max(i, format2.j);
                    i2 = Math.max(i2, format2.k);
                    i3 = Math.max(c, c(format2));
                } else {
                    i5 = i3;
                    i3 = c;
                }
                i4++;
                i = i;
                i2 = i2;
                c = i3;
                i3 = i5;
            }
            if (i3 != 0) {
                new StringBuilder("Resolutions unknown. Codec max resolution: ").append(i).append("x").append(i2);
                Point a = a(codecInfo, format);
                if (a != null) {
                    i = Math.max(i, a.x);
                    i2 = Math.max(i2, a.y);
                    c = Math.max(c, a(format.f, i, i2));
                    new StringBuilder("Codec max resolution adjusted to: ").append(i).append("x").append(i2);
                }
            }
            aVar = new a(i, i2, c);
        }
        this.j = aVar;
        aVar = this.j;
        boolean z = this.h;
        c = this.C;
        MediaFormat mediaFormat = format.b();
        mediaFormat.setInteger("max-width", aVar.a);
        mediaFormat.setInteger("max-height", aVar.b);
        if (aVar.c != -1) {
            mediaFormat.setInteger("max-input-size", aVar.c);
        }
        if (z) {
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (c != 0) {
            mediaFormat.setFeatureEnabled("tunneled-playback", true);
            mediaFormat.setInteger("audio-session-id", c);
        }
        codec.configure(mediaFormat, this.k, crypto, 0);
        if (s.a >= 23 && this.B) {
            this.b = new b(this, codec, (byte) 0);
        }
    }

    protected final void a(String name, long initializedTimestampMs, long initializationDurationMs) {
        this.e.a(name, initializedTimestampMs, initializationDurationMs);
    }

    protected final void b(Format newFormat) throws ExoPlaybackException {
        float f;
        super.b(newFormat);
        this.e.a(newFormat);
        if (newFormat.n == -1.0f) {
            f = 1.0f;
        } else {
            f = newFormat.n;
        }
        this.s = f;
        this.r = d(newFormat);
    }

    protected final void F() {
        if (s.a < 23 && this.B) {
            v();
        }
    }

    protected final void a(MediaCodec codec, MediaFormat outputFormat) {
        int integer;
        boolean hasCrop = outputFormat.containsKey("crop-right") && outputFormat.containsKey("crop-left") && outputFormat.containsKey("crop-bottom") && outputFormat.containsKey("crop-top");
        if (hasCrop) {
            integer = (outputFormat.getInteger("crop-right") - outputFormat.getInteger("crop-left")) + 1;
        } else {
            integer = outputFormat.getInteger("width");
        }
        this.t = integer;
        if (hasCrop) {
            integer = (outputFormat.getInteger("crop-bottom") - outputFormat.getInteger("crop-top")) + 1;
        } else {
            integer = outputFormat.getInteger("height");
        }
        this.u = integer;
        this.w = this.s;
        if (s.a < 21) {
            this.v = this.r;
        } else if (this.r == 90 || this.r == 270) {
            int rotatedHeight = this.t;
            this.t = this.u;
            this.u = rotatedHeight;
            this.w = 1.0f / this.w;
        }
        codec.setVideoScalingMode(this.l);
    }

    protected final boolean a(boolean codecIsAdaptive, Format oldFormat, Format newFormat) {
        return b(codecIsAdaptive, oldFormat, newFormat) && newFormat.j <= this.j.a && newFormat.k <= this.j.b && newFormat.g <= this.j.c;
    }

    protected final boolean a(long positionUs, long elapsedRealtimeUs, MediaCodec codec, ByteBuffer buffer, int bufferIndex, int bufferFlags, long bufferPresentationTimeUs, boolean shouldSkip) {
        com.google.android.exoplayer2.decoder.d dVar;
        if (shouldSkip) {
            r.a("skipVideoBuffer");
            codec.releaseOutputBuffer(bufferIndex, false);
            r.a();
            dVar = this.a;
            dVar.e++;
            return true;
        } else if (!this.m) {
            if (s.a >= 21) {
                a(codec, bufferIndex, System.nanoTime());
            } else {
                a(codec, bufferIndex);
            }
            return true;
        } else if (d() != 2) {
            return false;
        } else {
            Object obj;
            long earlyUs = (bufferPresentationTimeUs - positionUs) - ((SystemClock.elapsedRealtime() * 1000) - elapsedRealtimeUs);
            long systemTimeNs = System.nanoTime();
            long adjustedReleaseTimeNs = this.d.a(bufferPresentationTimeUs, systemTimeNs + (1000 * earlyUs));
            earlyUs = (adjustedReleaseTimeNs - systemTimeNs) / 1000;
            if (earlyUs < -30000) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                r.a("dropVideoBuffer");
                codec.releaseOutputBuffer(bufferIndex, false);
                r.a();
                dVar = this.a;
                dVar.f++;
                this.p++;
                this.q++;
                this.a.g = Math.max(this.q, this.a.g);
                if (this.p == this.g) {
                    H();
                }
                return true;
            }
            if (s.a >= 21) {
                if (earlyUs < 50000) {
                    a(codec, bufferIndex, adjustedReleaseTimeNs);
                    return true;
                }
            } else if (earlyUs < 30000) {
                if (earlyUs > 11000) {
                    try {
                        Thread.sleep((earlyUs - 10000) / 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                a(codec, bufferIndex);
                return true;
            }
            return false;
        }
    }

    private void a(MediaCodec codec, int bufferIndex) {
        z();
        r.a("releaseOutputBuffer");
        codec.releaseOutputBuffer(bufferIndex, true);
        r.a();
        com.google.android.exoplayer2.decoder.d dVar = this.a;
        dVar.d++;
        this.q = 0;
        v();
    }

    @TargetApi(21)
    private void a(MediaCodec codec, int bufferIndex, long releaseTimeNs) {
        z();
        r.a("releaseOutputBuffer");
        codec.releaseOutputBuffer(bufferIndex, releaseTimeNs);
        r.a();
        com.google.android.exoplayer2.decoder.d dVar = this.a;
        dVar.d++;
        this.q = 0;
        v();
    }

    private void w() {
        this.n = this.f > 0 ? SystemClock.elapsedRealtime() + this.f : -9223372036854775807L;
    }

    private void x() {
        this.m = false;
        if (s.a >= 23 && this.B) {
            MediaCodec codec = D();
            if (codec != null) {
                this.b = new b(this, codec, (byte) 0);
            }
        }
    }

    final void v() {
        if (!this.m) {
            this.m = true;
            this.e.a(this.k);
        }
    }

    private void y() {
        this.x = -1;
        this.y = -1;
        this.A = -1.0f;
        this.z = -1;
    }

    private void z() {
        if (this.x != this.t || this.y != this.u || this.z != this.v || this.A != this.w) {
            this.e.a(this.t, this.u, this.v, this.w);
            this.x = this.t;
            this.y = this.u;
            this.z = this.v;
            this.A = this.w;
        }
    }

    private void G() {
        if (this.x != -1 || this.y != -1) {
            this.e.a(this.t, this.u, this.v, this.w);
        }
    }

    private void H() {
        if (this.p > 0) {
            long now = SystemClock.elapsedRealtime();
            this.e.a(this.p, now - this.o);
            this.p = 0;
            this.o = now;
        }
    }

    private static Point a(com.google.android.exoplayer2.b.a codecInfo, Format format) throws com.google.android.exoplayer2.b.d.b {
        boolean isVerticalVideo = format.k > format.j;
        int formatLongEdgePx = isVerticalVideo ? format.k : format.j;
        int formatShortEdgePx = isVerticalVideo ? format.j : format.k;
        float aspectRatio = ((float) formatShortEdgePx) / ((float) formatLongEdgePx);
        int[] iArr = c;
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return null;
            }
            int longEdgePx = iArr[i2];
            int shortEdgePx = (int) (((float) longEdgePx) * aspectRatio);
            if (longEdgePx > formatLongEdgePx && shortEdgePx > formatShortEdgePx) {
                if (s.a >= 21) {
                    int i3;
                    if (isVerticalVideo) {
                        i3 = shortEdgePx;
                    } else {
                        i3 = longEdgePx;
                    }
                    Point alignedSize = codecInfo.a(i3, isVerticalVideo ? longEdgePx : shortEdgePx);
                    if (codecInfo.a(alignedSize.x, alignedSize.y, (double) format.l)) {
                        return alignedSize;
                    }
                } else {
                    longEdgePx = s.a(longEdgePx, 16) * 16;
                    shortEdgePx = s.a(shortEdgePx, 16) * 16;
                    if (longEdgePx * shortEdgePx <= d.b()) {
                        i = isVerticalVideo ? shortEdgePx : longEdgePx;
                        if (!isVerticalVideo) {
                            longEdgePx = shortEdgePx;
                        }
                        return new Point(i, longEdgePx);
                    }
                }
                i = i2 + 1;
            }
        }
        return null;
    }

    private static int c(Format format) {
        if (format.g != -1) {
            return format.g;
        }
        return a(format.f, format.j, format.k);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(String sampleMimeType, int width, int height) {
        if (width == -1 || height == -1) {
            return -1;
        }
        int i;
        int maxPixels;
        int minCompressionRatio;
        switch (sampleMimeType.hashCode()) {
            case -1664118616:
                if (sampleMimeType.equals("video/3gpp")) {
                    i = 0;
                    break;
                }
            case -1662541442:
                if (sampleMimeType.equals("video/hevc")) {
                    i = 4;
                    break;
                }
            case 1187890754:
                if (sampleMimeType.equals("video/mp4v-es")) {
                    i = 1;
                    break;
                }
            case 1331836730:
                if (sampleMimeType.equals(CodecUtils.MEDIA_TYPE)) {
                    i = 2;
                    break;
                }
            case 1599127256:
                if (sampleMimeType.equals("video/x-vnd.on2.vp8")) {
                    i = 3;
                    break;
                }
            case 1599127257:
                if (sampleMimeType.equals("video/x-vnd.on2.vp9")) {
                    i = 5;
                    break;
                }
            default:
                i = -1;
                break;
        }
        switch (i) {
            case 0:
            case 1:
                maxPixels = width * height;
                minCompressionRatio = 2;
                break;
            case 2:
                if (!"BRAVIA 4K 2015".equals(s.d)) {
                    maxPixels = ((s.a(width, 16) * s.a(height, 16)) * 16) * 16;
                    minCompressionRatio = 2;
                    break;
                }
                return -1;
            case 3:
                maxPixels = width * height;
                minCompressionRatio = 2;
                break;
            case 4:
            case 5:
                maxPixels = width * height;
                minCompressionRatio = 4;
                break;
            default:
                return -1;
        }
        return (maxPixels * 3) / (minCompressionRatio * 2);
    }

    private static boolean b(boolean codecIsAdaptive, Format first, Format second) {
        return first.f.equals(second.f) && d(first) == d(second) && (codecIsAdaptive || (first.j == second.j && first.k == second.k));
    }

    private static int d(Format format) {
        return format.m == -1 ? 0 : format.m;
    }
}
