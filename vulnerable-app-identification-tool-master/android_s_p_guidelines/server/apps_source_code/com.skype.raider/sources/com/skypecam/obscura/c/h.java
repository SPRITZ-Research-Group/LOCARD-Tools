package com.skypecam.obscura.c;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.view.Surface;
import com.skypecam.obscura.e.g;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class h {
    private Surface a;
    private MediaCodec b;
    private final String c = "MediaCodecWrapper";

    public final void a(String type) throws IOException {
        this.b = MediaCodec.createEncoderByType(type);
    }

    public final void a(MediaFormat format) {
        this.b.configure(format, null, null, 1);
    }

    public final void a() {
        this.b.start();
        this.b.getOutputFormat();
    }

    public final Surface b() {
        return this.a;
    }

    public final void c() {
        this.a = this.b.createInputSurface();
    }

    public final MediaCodecInfo d() {
        return this.b.getCodecInfo();
    }

    public final MediaCodec e() {
        return this.b;
    }

    public final ByteBuffer[] f() {
        return this.b.getOutputBuffers();
    }

    public final int a(BufferInfo bufferInfo) {
        return this.b.dequeueOutputBuffer(bufferInfo, 0);
    }

    public final MediaFormat g() {
        return this.b.getOutputFormat();
    }

    public final void a(int encoderStatus) {
        this.b.releaseOutputBuffer(encoderStatus, false);
    }

    public final void h() {
        if (this.b != null) {
            g.a().b("MediaCodecWrapper", "stop codec");
            try {
                this.b.stop();
                g.a().b("MediaCodecWrapper", "releasing codec");
                this.b.release();
            } catch (IllegalStateException e) {
                g.a().c("MediaCodecWrapper", "unable to stop and release codec " + e.getLocalizedMessage());
                if (VERSION.SDK_INT >= 21) {
                    g.a().b("MediaCodecWrapper", "reseting codec");
                    this.b.reset();
                } else {
                    g.a().b("MediaCodecWrapper", "cannot reset codec; API level too low");
                }
                g.a().b("MediaCodecWrapper", "releasing codec");
                this.b.release();
            } catch (Throwable th) {
                g.a().b("MediaCodecWrapper", "releasing codec");
                this.b.release();
            }
            this.b = null;
            return;
        }
        g.a().b("MediaCodecWrapper", "no codec to stop");
    }
}
