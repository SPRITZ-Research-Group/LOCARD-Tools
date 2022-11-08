package com.microsoft.react.videofxp;

import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import com.skype.android.video.hw.utils.CodecUtils;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

final class m implements Runnable {
    private final p A;
    private final a<n> B;
    private final AtomicBoolean a = new AtomicBoolean(false);
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private long e = 0;
    private final MediaExtractor f;
    private MediaCodec g;
    private MediaCodec h;
    private f i;
    private h j;
    private final String k;
    private ByteBuffer[] l = null;
    private MediaFormat m = null;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private BufferInfo q = new BufferInfo();
    private ByteBuffer[] r = null;
    private final MediaFormat s;
    private final MediaFormat t;
    private ByteBuffer[] u = null;
    private BufferInfo v = null;
    private final g w;
    private final int x;
    private final float y;
    private final Bitmap z;

    m(MediaExtractor videoExtractor, String thumbnailPath, g muxerWrapper, MediaFormat outputVideoFormat, MediaFormat videoInputFormat, int lensMode, float lensIntensity, Bitmap overlay, p options, a<n> progress) {
        this.f = videoExtractor;
        this.k = thumbnailPath;
        this.w = muxerWrapper;
        this.s = outputVideoFormat;
        this.t = videoInputFormat;
        this.x = lensMode;
        this.y = lensIntensity;
        this.z = overlay;
        this.A = options;
        this.B = progress;
    }

    public final void a() {
        this.a.set(true);
    }

    private static void a(MediaCodec codec, MediaFormat format) {
        if (codec != null && VERSION.SDK_INT >= 25) {
            CodecCapabilities capabilities = codec.getCodecInfo().getCapabilitiesForType(CodecUtils.MEDIA_TYPE);
            if (capabilities.profileLevels != null) {
                for (CodecProfileLevel codecProfileLevel : capabilities.profileLevels) {
                    if (codecProfileLevel.profile == 2) {
                        FLog.i("VideoFXPVideoProcessor", "Device supports requested codec profile: 2");
                        format.setInteger("profile", 2);
                        format.setInteger("level", 512);
                        return;
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        try {
            AtomicReference atomicReference = new AtomicReference();
            MediaFormat mediaFormat = this.s;
            p pVar = this.A;
            MediaCodec createEncoderByType = MediaCodec.createEncoderByType(CodecUtils.MEDIA_TYPE);
            if (pVar.a == q.HD) {
                a(createEncoderByType, mediaFormat);
            }
            createEncoderByType.configure(mediaFormat, null, null, 1);
            FLog.i("VideoFXPVideoProcessor", "video format used: " + mediaFormat);
            atomicReference.set(createEncoderByType.createInputSurface());
            createEncoderByType.start();
            this.h = createEncoderByType;
            this.i = new f((Surface) atomicReference.get());
            this.i.b();
            this.j = new h(this.x, this.y, this.z);
            MediaFormat mediaFormat2 = this.t;
            Surface b = this.j.b();
            MediaCodec createDecoderByType = MediaCodec.createDecoderByType(CodecUtils.MEDIA_TYPE);
            createDecoderByType.configure(mediaFormat2, b, null, 0);
            createDecoderByType.start();
            this.g = createDecoderByType;
            this.l = this.g.getInputBuffers();
            this.r = this.g.getOutputBuffers();
            this.u = this.h.getOutputBuffers();
            this.v = new BufferInfo();
            while (!this.a.get()) {
                int dequeueInputBuffer;
                long j = this.t.getLong("durationUs");
                if (!this.b && (this.m == null || this.w.a)) {
                    dequeueInputBuffer = this.g.dequeueInputBuffer(10000);
                    if (dequeueInputBuffer == -1) {
                        FLog.i("VideoFXPVideoProcessor", "no video decoder input buffer");
                    } else {
                        FLog.i("VideoFXPVideoProcessor", "video decoder: returned input buffer: " + dequeueInputBuffer);
                        int readSampleData = this.f.readSampleData(this.l[dequeueInputBuffer], 0);
                        long sampleTime = this.f.getSampleTime();
                        FLog.i("VideoFXPVideoProcessor", "video extractor: returned buffer of size " + readSampleData);
                        FLog.i("VideoFXPVideoProcessor", "video extractor: returned buffer for time " + sampleTime);
                        long time = new Date().getTime();
                        if (time > this.e + 300) {
                            this.e = time;
                            if (sampleTime > 0 && j > 0 && this.B != null) {
                                this.B.a(new n(((double) sampleTime) / ((double) j), j / 1000000));
                            }
                        }
                        if (readSampleData >= 0) {
                            this.g.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, sampleTime, this.f.getSampleFlags());
                        }
                        this.b = !this.f.advance();
                        if (this.b) {
                            FLog.i("VideoFXPVideoProcessor", "video extractor: EOS");
                            this.g.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                        }
                        this.n++;
                    }
                }
                if (!this.c && (this.m == null || this.w.a)) {
                    dequeueInputBuffer = this.g.dequeueOutputBuffer(this.q, 10000);
                    if (dequeueInputBuffer == -1) {
                        FLog.i("VideoFXPVideoProcessor", "no video decoder output buffer");
                    } else if (dequeueInputBuffer == -3) {
                        FLog.i("VideoFXPVideoProcessor", "video decoder: output buffers changed");
                        this.r = this.g.getOutputBuffers();
                    } else if (dequeueInputBuffer == -2) {
                        FLog.i("VideoFXPVideoProcessor", "video decoder: output format changed: " + this.g.getOutputFormat());
                    } else {
                        FLog.i("VideoFXPVideoProcessor", "video decoder: returned output buffer: " + dequeueInputBuffer);
                        FLog.i("VideoFXPVideoProcessor", "video decoder: returned buffer of size " + this.q.size);
                        if ((this.q.flags & 2) != 0) {
                            FLog.i("VideoFXPVideoProcessor", "video decoder: codec config buffer");
                            this.g.releaseOutputBuffer(dequeueInputBuffer, false);
                        } else {
                            FLog.i("VideoFXPVideoProcessor", "video decoder: returned buffer for time " + this.q.presentationTimeUs);
                            boolean z = this.q.size != 0;
                            this.g.releaseOutputBuffer(dequeueInputBuffer, z);
                            if (z) {
                                FLog.i("VideoFXPVideoProcessor", "output surface: await new image");
                                this.j.c();
                                FLog.i("VideoFXPVideoProcessor", "output surface: draw image");
                                this.j.d();
                                if (!this.d) {
                                    float max = 360.0f / ((float) Math.max(this.i.d(), this.i.e()));
                                    FLog.i("VideoFXPVideoProcessor", "thumbnail before save frame rescale " + max);
                                    k.a(this.k, this.i.d(), this.i.e(), max);
                                    FLog.i("VideoFXPVideoProcessor", "thumbnail after save frame");
                                    this.d = true;
                                }
                                this.i.a(this.q.presentationTimeUs * 1000);
                                FLog.i("VideoFXPVideoProcessor", "input surface: swap buffers");
                                this.i.c();
                                FLog.i("VideoFXPVideoProcessor", "video encoder: notified of new frame");
                            }
                            if ((this.q.flags & 4) != 0) {
                                FLog.i("VideoFXPVideoProcessor", "video decoder: EOS");
                                this.c = true;
                                this.h.signalEndOfInputStream();
                            }
                            this.o++;
                        }
                    }
                }
                if (!this.a.get() && (this.m == null || this.w.a)) {
                    int dequeueOutputBuffer = this.h.dequeueOutputBuffer(this.v, 10000);
                    if (dequeueOutputBuffer == -1) {
                        FLog.i("VideoFXPVideoProcessor", "no video encoder output buffer");
                    } else if (dequeueOutputBuffer == -3) {
                        FLog.i("VideoFXPVideoProcessor", "video encoder: output buffers changed");
                        this.u = this.h.getOutputBuffers();
                    } else if (dequeueOutputBuffer == -2) {
                        this.m = this.h.getOutputFormat();
                        FLog.i("VideoFXPVideoProcessor", "video encoder: output format changed: " + this.m);
                        this.w.a(this.m);
                    } else {
                        an.a(this.w.a, "VideoFXPVideoProcessorshould have added track before processing output");
                        FLog.i("VideoFXPVideoProcessor", "video encoder: returned output buffer: " + dequeueOutputBuffer);
                        FLog.i("VideoFXPVideoProcessor", "video encoder: returned buffer of size " + this.v.size);
                        ByteBuffer byteBuffer = this.u[dequeueOutputBuffer];
                        if ((this.v.flags & 2) != 0) {
                            FLog.i("VideoFXPVideoProcessor", "video encoder: codec config buffer");
                            this.h.releaseOutputBuffer(dequeueOutputBuffer, false);
                        } else {
                            FLog.i("VideoFXPVideoProcessor", "video encoder: returned buffer for time " + this.v.presentationTimeUs);
                            if (this.v.size != 0) {
                                g gVar = this.w;
                                gVar.b.writeSampleData(gVar.c, byteBuffer, this.v);
                            }
                            if ((this.v.flags & 4) != 0) {
                                FLog.i("VideoFXPVideoProcessor", "video encoder: EOS");
                                this.a.set(true);
                            }
                            this.h.releaseOutputBuffer(dequeueOutputBuffer, false);
                            this.p++;
                        }
                    }
                }
            }
            this.w.b();
            if (this.g != null) {
                this.g.stop();
                this.g.release();
            }
            if (this.j != null) {
                this.j.a();
            }
            if (this.h != null) {
                this.h.stop();
                this.h.release();
            }
            if (this.i != null) {
                this.i.a();
            }
        } catch (IOException e) {
            FLog.i("VideoFXPVideoProcessor", "IOException: " + e.getLocalizedMessage());
        } catch (Throwable th) {
            if (this.g != null) {
                this.g.stop();
                this.g.release();
            }
            if (this.j != null) {
                this.j.a();
            }
            if (this.h != null) {
                this.h.stop();
                this.h.release();
            }
            if (this.i != null) {
                this.i.a();
            }
        }
    }
}
