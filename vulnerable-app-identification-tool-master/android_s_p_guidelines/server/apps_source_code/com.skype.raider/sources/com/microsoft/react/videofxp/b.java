package com.microsoft.react.videofxp;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

final class b implements Runnable {
    private final AtomicBoolean a = new AtomicBoolean(false);
    private boolean b = false;
    private int c = 0;
    private boolean d = false;
    private MediaFormat e = null;
    private ByteBuffer[] f = null;
    private final MediaExtractor g;
    private final MediaCodec h;
    private final MediaCodec i;
    private int j = -1;
    private BufferInfo k = null;
    private ByteBuffer[] l = null;
    private int m = 0;
    private ByteBuffer[] n = null;
    private ByteBuffer[] o = null;
    private BufferInfo p = null;
    private int q = 0;
    private final g r;

    b(MediaExtractor audioExtractor, MediaCodec audioDecoder, MediaCodec audioEncoder, g muxerWrapper) {
        this.g = audioExtractor;
        this.h = audioDecoder;
        this.i = audioEncoder;
        this.r = muxerWrapper;
        if (audioDecoder == null || audioEncoder == null) {
            muxerWrapper.a();
            return;
        }
        this.f = audioDecoder.getInputBuffers();
        this.k = new BufferInfo();
        this.l = audioDecoder.getOutputBuffers();
        this.n = audioEncoder.getInputBuffers();
        this.o = audioEncoder.getOutputBuffers();
        this.p = new BufferInfo();
    }

    public final void run() {
        if (this.h == null || this.i == null) {
            this.a.set(true);
        }
        while (!this.a.get()) {
            int dequeueInputBuffer;
            int readSampleData;
            long sampleTime;
            int dequeueOutputBuffer;
            if (!this.b && (this.e == null || this.r.a)) {
                dequeueInputBuffer = this.h.dequeueInputBuffer(10000);
                if (dequeueInputBuffer == -1) {
                    FLog.i("VideoFXPAudioProcessor", "no audio decoder input buffer");
                } else {
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: returned input buffer: " + dequeueInputBuffer);
                    readSampleData = this.g.readSampleData(this.f[dequeueInputBuffer], 0);
                    sampleTime = this.g.getSampleTime();
                    FLog.i("VideoFXPAudioProcessor", "audio extractor: returned buffer of size " + readSampleData);
                    FLog.i("VideoFXPAudioProcessor", "audio extractor: returned buffer for time " + sampleTime);
                    if (readSampleData >= 0) {
                        this.h.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, sampleTime, this.g.getSampleFlags());
                    }
                    this.b = !this.g.advance();
                    if (this.b) {
                        FLog.i("VideoFXPAudioProcessor", "audio extractor: EOS");
                        this.h.queueInputBuffer(dequeueInputBuffer, 0, 0, 0, 4);
                    }
                    this.c++;
                }
            }
            if (!this.d && this.j == -1 && (this.e == null || this.r.a)) {
                dequeueOutputBuffer = this.h.dequeueOutputBuffer(this.k, 10000);
                if (dequeueOutputBuffer == -1) {
                    FLog.i("VideoFXPAudioProcessor", "no audio decoder output buffer");
                } else if (dequeueOutputBuffer == -3) {
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: output buffers changed");
                    this.l = this.h.getOutputBuffers();
                } else if (dequeueOutputBuffer == -2) {
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: output format changed: " + this.h.getOutputFormat());
                } else {
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: returned output buffer: " + dequeueOutputBuffer);
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: returned buffer of size " + this.k.size);
                    if ((this.k.flags & 2) != 0) {
                        FLog.i("VideoFXPAudioProcessor", "audio decoder: codec config buffer");
                        this.h.releaseOutputBuffer(dequeueOutputBuffer, false);
                    } else {
                        FLog.i("VideoFXPAudioProcessor", "audio decoder: returned buffer for time " + this.k.presentationTimeUs);
                        FLog.i("VideoFXPAudioProcessor", "audio decoder: output buffer is now pending: " + this.j);
                        this.j = dequeueOutputBuffer;
                        this.m++;
                    }
                }
            }
            if (this.j != -1) {
                FLog.i("VideoFXPAudioProcessor", "audio decoder: attempting to process pending buffer: " + this.j);
                dequeueInputBuffer = this.i.dequeueInputBuffer(10000);
                if (dequeueInputBuffer == -1) {
                    FLog.i("VideoFXPAudioProcessor", "no audio encoder input buffer");
                } else {
                    FLog.i("VideoFXPAudioProcessor", "audio encoder: returned input buffer: " + dequeueInputBuffer);
                    ByteBuffer byteBuffer = this.n[dequeueInputBuffer];
                    readSampleData = this.k.size;
                    sampleTime = this.k.presentationTimeUs;
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: processing pending buffer: " + this.j);
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: pending buffer of size " + readSampleData);
                    FLog.i("VideoFXPAudioProcessor", "audio decoder: pending buffer for time " + sampleTime);
                    if (readSampleData >= 0) {
                        ByteBuffer duplicate = this.l[this.j].duplicate();
                        duplicate.position(this.k.offset);
                        duplicate.limit(this.k.offset + readSampleData);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        this.i.queueInputBuffer(dequeueInputBuffer, 0, readSampleData, sampleTime, this.k.flags);
                    }
                    this.h.releaseOutputBuffer(this.j, false);
                    this.j = -1;
                    if ((this.k.flags & 4) != 0) {
                        FLog.i("VideoFXPAudioProcessor", "audio decoder: EOS");
                        this.d = true;
                    }
                }
            }
            if (!this.a.get() && (this.e == null || this.r.a)) {
                dequeueOutputBuffer = this.i.dequeueOutputBuffer(this.p, 10000);
                if (dequeueOutputBuffer == -1) {
                    FLog.i("VideoFXPAudioProcessor", "no audio encoder output buffer");
                } else if (dequeueOutputBuffer == -3) {
                    FLog.i("VideoFXPAudioProcessor", "audio encoder: output buffers changed");
                    this.o = this.i.getOutputBuffers();
                } else if (dequeueOutputBuffer == -2) {
                    FLog.i("VideoFXPAudioProcessor", "audio encoder: output format changed");
                    this.e = this.i.getOutputFormat();
                    this.r.b(this.e);
                } else {
                    an.a(this.r.a, "VideoFXPAudioProcessorshould have added track before processing output");
                    FLog.i("VideoFXPAudioProcessor", "audio encoder: returned output buffer: " + dequeueOutputBuffer);
                    FLog.i("VideoFXPAudioProcessor", "audio encoder: returned buffer of size " + this.p.size);
                    ByteBuffer byteBuffer2 = this.o[dequeueOutputBuffer];
                    if ((this.p.flags & 2) != 0) {
                        FLog.i("VideoFXPAudioProcessor", "audio encoder: codec config buffer");
                        this.i.releaseOutputBuffer(dequeueOutputBuffer, false);
                    } else {
                        FLog.i("VideoFXPAudioProcessor", "audio encoder: returned buffer for time " + this.p.presentationTimeUs);
                        if (this.p.size != 0) {
                            g gVar = this.r;
                            gVar.b.writeSampleData(gVar.d, byteBuffer2, this.p);
                        }
                        if ((this.p.flags & 4) != 0) {
                            FLog.i("VideoFXPAudioProcessor", "audio encoder: EOS");
                            this.a.set(true);
                        }
                        this.i.releaseOutputBuffer(dequeueOutputBuffer, false);
                        this.q++;
                    }
                }
            }
        }
        this.r.c();
    }

    public final void a() {
        this.a.set(true);
    }
}
