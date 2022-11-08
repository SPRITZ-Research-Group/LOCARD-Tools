package com.skypecam.obscura.c;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.skypecam.obscura.e.g;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

public final class i {
    private AtomicBoolean a = new AtomicBoolean(false);
    private final MediaMuxer b;
    private int c = -1;
    private int d = -1;
    private long e = -1;
    private int f = 0;
    private int g = 0;
    private final Object h = new Object();

    public i(String destinationPath) throws IOException {
        g.a().b("MuxerWrapper", "MuxerWrapper: " + destinationPath);
        this.b = new MediaMuxer(destinationPath, 0);
    }

    public final void a(MediaFormat videoFormat) {
        synchronized (this.h) {
            if (this.c == -1) {
                g.a().b("MuxerWrapper", "muxer: adding video track.");
                this.c = this.b.addTrack(videoFormat);
            } else {
                g.a().c("MuxerWrapper", "muxer: not adding video track.");
            }
            c();
        }
    }

    public final void b(MediaFormat audioFormat) {
        synchronized (this.h) {
            if (this.d == -1) {
                g.a().b("MuxerWrapper", "muxer: adding audio track.");
                this.d = this.b.addTrack(audioFormat);
            } else {
                g.a().c("MuxerWrapper", "muxer: not adding audio track.");
            }
            c();
        }
    }

    private void c() {
        if (!this.a.get() && this.c >= 0 && this.d >= 0) {
            this.b.start();
            this.a.set(true);
            this.f = 0;
            this.g = 0;
            g.a().b("MuxerWrapper", "muxer: started");
        }
    }

    public final void a() {
        g.a().b("MuxerWrapper", "muxer: endAudio");
        synchronized (this.h) {
            this.d = -1;
            if (this.a.get() && (this.c == -1 || this.d == -1)) {
                try {
                    g.a().b("MuxerWrapper", "muxer.release() v:" + this.f + " a:" + this.g);
                    this.b.release();
                    this.e = -1;
                } catch (IllegalStateException e) {
                    g.a().d("MuxerWrapper", "muxer.release() exception : " + e.getLocalizedMessage());
                }
                this.a.set(false);
            }
        }
    }

    public final void a(ByteBuffer encoderOutputBuffer, BufferInfo videoEncoderOutputBufferInfo, long presentationTime) {
        if (this.e == -1) {
            this.e = presentationTime;
        }
        if (a(encoderOutputBuffer, videoEncoderOutputBufferInfo, this.c, presentationTime - this.e)) {
            this.f++;
        }
    }

    public final void b(ByteBuffer encoderOutputBuffer, BufferInfo videoEncoderOutputBufferInfo, long presentationTime) {
        if (this.e == -1) {
            this.e = presentationTime;
        }
        if (a(encoderOutputBuffer, videoEncoderOutputBufferInfo, this.d, presentationTime - this.e)) {
            this.g++;
        }
    }

    private boolean a(ByteBuffer encoderOutputBuffer, BufferInfo encoderOutputBufferInfo, int track, long presentationTime) {
        RuntimeException e;
        if (!this.a.get()) {
            return false;
        }
        try {
            encoderOutputBufferInfo.presentationTimeUs = presentationTime;
            this.b.writeSampleData(track, encoderOutputBuffer, encoderOutputBufferInfo);
            return true;
        } catch (IllegalStateException e2) {
            e = e2;
            g.a().d("MuxerWrapper", "writeSample() exception (" + e.getClass().getName() + ") " + e.getLocalizedMessage());
            return false;
        } catch (IllegalArgumentException e3) {
            e = e3;
            g.a().d("MuxerWrapper", "writeSample() exception (" + e.getClass().getName() + ") " + e.getLocalizedMessage());
            return false;
        }
    }

    public final int b() {
        return this.f;
    }
}
