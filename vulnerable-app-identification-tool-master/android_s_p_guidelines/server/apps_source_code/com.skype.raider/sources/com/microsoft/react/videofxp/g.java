package com.microsoft.react.videofxp;

import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.an;
import java.io.IOException;

final class g {
    private static final Object e = new Object();
    public boolean a = false;
    public final MediaMuxer b;
    public int c = -1;
    public int d = -1;
    private boolean f = true;

    g(String destinationPath) throws IOException {
        this.b = new MediaMuxer(destinationPath, 0);
    }

    public final void a(MediaFormat videoFormat) {
        synchronized (e) {
            an.a(this.c < 0, "VideoFXPMuxerWrappervideo encoder changed its output format again?");
            FLog.i("VideoFXPMuxerWrapper", "muxer: adding video track.");
            this.c = this.b.addTrack(videoFormat);
            d();
        }
    }

    public final void b(MediaFormat audioFormat) {
        synchronized (e) {
            an.a(this.d < 0, "VideoFXPMuxerWrapperaudio encoder changed its output format again?");
            FLog.i("VideoFXPReencoder", "muxer: adding audio track.");
            this.d = this.b.addTrack(audioFormat);
            d();
        }
    }

    public final void a() {
        synchronized (e) {
            this.f = false;
            d();
        }
    }

    private void d() {
        boolean hasVideoTrack;
        if (this.c >= 0) {
            hasVideoTrack = true;
        } else {
            hasVideoTrack = false;
        }
        boolean audioRequirementsSatisfied;
        if (this.d >= 0 || !this.f) {
            audioRequirementsSatisfied = true;
        } else {
            audioRequirementsSatisfied = false;
        }
        if (!this.a && hasVideoTrack && audioRequirementsSatisfied) {
            this.b.start();
            this.a = true;
            FLog.i("VideoFXPReencoder", "muxer: started");
        }
    }

    public final void b() {
        FLog.i("VideoFXPReencoder", "muxer: endVideo");
        this.c = -1;
        e();
    }

    public final void c() {
        FLog.i("VideoFXPReencoder", "muxer: endAudio");
        this.d = -1;
        e();
    }

    private void e() {
        synchronized (e) {
            if (this.b != null && this.a && this.c == -1 && this.d == -1) {
                try {
                    FLog.i("VideoFXPMuxerWrapper", "muxer.release()");
                    this.b.release();
                } catch (IllegalStateException e) {
                    FLog.e("VideoFXPMuxerWrapper", "muxer.release() exception : " + e.getLocalizedMessage());
                }
                this.a = false;
            }
        }
    }
}
