package com.skype.android.video.hw.codec.encoder.camera;

import android.view.Surface;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.AbstractVideoEncoder;
import com.skype.android.video.hw.format.ColorFormat;
import com.skype.android.video.hw.format.VideoFormat;
import com.skype.android.video.hw.frame.OutputFrame;
import com.skype.android.video.hw.utils.Log;
import java.lang.ref.WeakReference;

public class VideoCameraEncoder extends AbstractVideoEncoder {
    private Surface inputSurface;
    private WeakReference<SurfaceObserver> weakRefSurfaceObserver;

    public VideoCameraEncoder(String codecName) {
        super(codecName);
    }

    public void configure(Object userData, VideoFormat videoFormat, boolean checkHwApiVersioning) {
        if (!videoFormat.getColorFormat().equals(ColorFormat.SURFACE)) {
            if (Log.isLoggable(Commons.TAG, 5)) {
                Log.w(Commons.TAG, getClass().getSimpleName() + ": " + ColorFormat.SURFACE + " color format is expected");
            }
            videoFormat.setColorFormat(ColorFormat.SURFACE);
        }
        this.weakRefSurfaceObserver = new WeakReference((SurfaceObserver) userData);
        SurfaceObserver surfaceObserver = (SurfaceObserver) this.weakRefSurfaceObserver.get();
        super.configure(surfaceObserver, videoFormat, checkHwApiVersioning);
        this.inputSurface = this.mediaCodec.createInputSurface();
        if (surfaceObserver != null) {
            surfaceObserver.attachSurface(this.inputSurface, videoFormat.getResolution());
            return;
        }
        this.inputSurface.release();
        this.inputSurface = null;
    }

    public void close() {
        SurfaceObserver surfaceObserver = (SurfaceObserver) this.weakRefSurfaceObserver.get();
        if (surfaceObserver != null) {
            surfaceObserver.detachSurface();
        } else if (this.inputSurface != null) {
            this.inputSurface.release();
            this.inputSurface = null;
        }
        super.close();
    }

    public OutputFrame encode(long timeoutUs) {
        return super.drainCodec(timeoutUs);
    }
}
