package com.skype.android.video.hw.codec.encoder.camera.gl;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.EGL14;
import android.opengl.EGLDisplay;
import android.util.SparseArray;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.camera.gl.AbstractRenderingTarget.Events;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.CodecUtils;
import com.skype.android.video.hw.utils.FrameRateController;
import com.skype.android.video.hw.utils.Log;
import com.skype.android.video.hw.utils.Systrace;
import com.skype.android.video.hw.utils.Systrace.Section;
import java.io.Closeable;
import java.util.concurrent.TimeoutException;

public class SurfaceTextureChannel implements ChannelPushFrame, Closeable {
    private static final int[] EGL_SHARED_CONTEXT_ATTRIBUTES = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    private AbstractRenderingTarget defaultRenderingTarget;
    private boolean dropFrame = false;
    private EGLDisplay eglDisplay = EGL14.EGL_NO_DISPLAY;
    private FrameRateController frameRateController;
    private boolean isBroadComChip = false;
    private boolean isFirstFrame = true;
    private final boolean isPreEncodingRC;
    private final Object newFrameArrivedEvent = new Object();
    private SurfaceTextureRenderer renderer;
    private final SparseArray<AbstractRenderingTarget> renderingTargets = new SparseArray();
    private Context sharedContext;
    private Resolution sourceResolution;
    private volatile SourceState sourceState = SourceState.WAITING_FOR_FRAME;
    private SurfaceTexture sourceTexture;

    public enum OutputType {
        SCREEN,
        ENCODER
    }

    private class SourceFrameAvailableListener implements OnFrameAvailableListener {
        private SourceFrameAvailableListener() {
        }

        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            synchronized (SurfaceTextureChannel.this.newFrameArrivedEvent) {
                SurfaceTextureChannel.this.sourceState = SourceState.FRAME_AVAILABLE;
                SurfaceTextureChannel.this.newFrameArrivedEvent.notifyAll();
            }
        }
    }

    private enum SourceState {
        WAITING_FOR_FRAME,
        FRAME_AVAILABLE,
        INTERRUPTED
    }

    public SurfaceTextureChannel(boolean isPreEncodingRC) throws GLException {
        Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Constructing");
        this.isPreEncodingRC = isPreEncodingRC;
        try {
            this.frameRateController = new FrameRateController(30.0f);
            this.eglDisplay = createEGLDisplay();
            this.sharedContext = new Context(this.eglDisplay, EGL_SHARED_CONTEXT_ATTRIBUTES);
            this.defaultRenderingTarget = new BufferRenderingTarget(this.sharedContext, null);
            this.defaultRenderingTarget.setSurface(null, new Resolution(320, 240));
            this.renderer = new SurfaceTextureRenderer();
            this.sourceTexture = new SurfaceTexture(this.renderer.getSourceTextureId());
            this.sourceTexture.setOnFrameAvailableListener(new SourceFrameAvailableListener());
            String[] encoderNames = CodecUtils.enumEncoders();
            for (String name : encoderNames) {
                if (name.toLowerCase().contains("brcm") || name.toLowerCase().contains("broadcom")) {
                    this.isBroadComChip = true;
                    return;
                }
            }
        } catch (GLException e) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Initialization failed. Closing");
            }
            close();
            throw e;
        } catch (RuntimeException e2) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Initialization failed. Closing");
            }
            close();
            throw e2;
        }
    }

    public Object getSharedMutex() {
        return this.renderingTargets;
    }

    public boolean hasTarget(int id) {
        return this.renderingTargets.indexOfKey(id) >= 0;
    }

    public void allocateOutput(OutputType type, int id, Events eventsListener) throws GLException {
        if (this.renderingTargets.indexOfKey(id) >= 0) {
            throw new IllegalArgumentException("another target " + id + "/" + type + " exists");
        }
        this.renderingTargets.append(id, createRenderingTarget(type, eventsListener));
    }

    public void deallocateOutput(int id) {
        int index = this.renderingTargets.indexOfKey(id);
        if (index >= 0) {
            AbstractRenderingTarget target = (AbstractRenderingTarget) this.renderingTargets.valueAt(index);
            if (target != null) {
                target.close();
            }
            this.renderingTargets.removeAt(index);
        }
    }

    public boolean isOutputAttached(int id) {
        if (this.renderingTargets != null) {
            return ((AbstractRenderingTarget) this.renderingTargets.get(id)).hasSurface();
        }
        throw new IllegalStateException("closed");
    }

    public boolean isOutputAttached(int id, Object surface, Resolution resolution) {
        if (this.renderingTargets == null) {
            throw new IllegalStateException("closed");
        }
        AbstractRenderingTarget target = (AbstractRenderingTarget) this.renderingTargets.get(id);
        return target.hasSurface() && target.getSurface() == surface && ((resolution == null && target.getResolution() == null) || (resolution != null && resolution.equals(target.getResolution())));
    }

    public void attachOutput(int id, Object surface, Resolution resolution) throws GLException {
        if (this.renderingTargets == null) {
            throw new IllegalStateException("closed");
        }
        ((AbstractRenderingTarget) this.renderingTargets.get(id)).setSurface(surface, resolution);
    }

    public void detachOutput(int id) {
        if (this.renderingTargets == null) {
            throw new IllegalStateException("closed");
        }
        ((AbstractRenderingTarget) this.renderingTargets.get(id)).unsetSurface();
    }

    public void setParameters(int id, boolean isEnabled, int rotationAngle, float fitFactor, boolean isHorizFlipped, boolean isVertFlipped, float targetFrameRate) {
        if (this.renderingTargets == null) {
            throw new IllegalStateException("closed");
        }
        AbstractRenderingTarget target = (AbstractRenderingTarget) this.renderingTargets.get(id);
        target.setEnabled(isEnabled);
        target.setRotationAngle(rotationAngle);
        target.setFlipped(isHorizFlipped, isVertFlipped);
        target.setFitFactor(fitFactor);
        target.setTargetFrameRate(targetFrameRate);
    }

    public void interrupt() {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Interrupting");
        }
        synchronized (this.newFrameArrivedEvent) {
            this.sourceState = SourceState.INTERRUPTED;
            this.newFrameArrivedEvent.notify();
        }
    }

    public long acquireFrame(long timeoutMs) throws GLException, InterruptedException, TimeoutException {
        if (this.sourceTexture == null || this.renderingTargets == null || this.renderer == null) {
            throw new IllegalStateException("closed");
        }
        switch (awaitNewFrame(timeoutMs)) {
            case WAITING_FOR_FRAME:
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Could not get a frame withing " + timeoutMs + " ms. Timeouyt elapsed");
                }
                throw new TimeoutException();
            case INTERRUPTED:
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Wait for a frame interrupted");
                }
                throw new InterruptedException();
            default:
                this.defaultRenderingTarget.bind();
                try {
                    Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Updating source texture image");
                    this.sourceTexture.updateTexImage();
                    return getTimestamp(this.sourceTexture);
                } catch (Throwable e) {
                    throw new GLException("Failed to update texture image", e);
                }
        }
    }

    public void pushAndRenderFrame(long timestampNs) throws GLException {
        updateFrameRateContoller(timestampNs);
        if (this.isPreEncodingRC) {
            renderPreviewAndSignalFrameReady(timestampNs);
        } else {
            pushFrameToAllTargets(timestampNs);
        }
    }

    private void renderPreviewAndSignalFrameReady(long timestampNs) throws GLException {
        int num = this.renderingTargets.size();
        for (int i = 0; i < num; i++) {
            AbstractRenderingTarget target = (AbstractRenderingTarget) this.renderingTargets.valueAt(i);
            if (target.isEnabled() && target.hasSurface() && target.getResolution() != null) {
                if (target instanceof ScreenRenderingTarget) {
                    pushFrameToSingleTarget(timestampNs, target);
                } else {
                    target.setDelayedTimestmap(timestampNs);
                }
                target.onFrameReady(timestampNs);
            }
        }
    }

    private void pushFrameToAllTargets(long timestampNs) throws GLException {
        int num = this.renderingTargets.size();
        for (int i = 0; i < num; i++) {
            AbstractRenderingTarget target = (AbstractRenderingTarget) this.renderingTargets.valueAt(i);
            if (target instanceof ScreenRenderingTarget) {
                pushFrameToSingleTarget(System.nanoTime(), target);
            } else {
                pushFrameToSingleTarget(timestampNs, target);
            }
            target.onFrameReady(timestampNs);
        }
    }

    public boolean executePushFrame(int Id) throws GLException {
        if (!this.isPreEncodingRC) {
            return false;
        }
        pushFrameToSingleTarget(((AbstractRenderingTarget) this.renderingTargets.get(Id)).getDelayedTimestamp(), (AbstractRenderingTarget) this.renderingTargets.get(Id));
        return this.dropFrame;
    }

    private void updateFrameRateContoller(long timestampNs) {
        boolean z = true;
        long timestampMs = timestampNs / 1000000;
        Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + " timestampMs " + timestampMs + " ms timestampNs " + timestampNs + " ns");
        AbstractRenderingTarget target = null;
        int num = this.renderingTargets.size();
        for (int i = 0; i < num; i++) {
            target = (AbstractRenderingTarget) this.renderingTargets.valueAt(i);
            if (target.isEnabled() && target.hasSurface() && target.getResolution() != null && (target instanceof EncoderRenderingTarget)) {
                break;
            }
        }
        if (target != null) {
            float targetFps = target.getTargetFrameRate();
            if (targetFps > 0.0f && this.frameRateController.GetTargetFps() != targetFps) {
                boolean z2;
                FrameRateController frameRateController = this.frameRateController;
                if (targetFps > this.frameRateController.GetTargetFps() + 1.0f) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                frameRateController.Reset(targetFps, timestampMs, z2);
            }
            if (targetFps <= 0.0f || !this.frameRateController.GetDropFlag(timestampMs)) {
                z = false;
            }
            this.dropFrame = z;
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + (this.dropFrame ? ": drop " : ": encode ") + "current Frame");
            }
            if (!this.dropFrame && targetFps > 0.0f) {
                this.frameRateController.Update(timestampMs);
            }
        }
    }

    private void pushFrameToSingleTarget(long timestampNs, AbstractRenderingTarget target) throws GLException {
        if (!target.isEnabled() || !target.hasSurface() || target.getResolution() == null) {
            return;
        }
        if (target instanceof ScreenRenderingTarget) {
            Systrace.begin(Section.RenderVideoPreview);
            try {
                render(System.nanoTime(), target);
            } finally {
                Systrace.end();
            }
        } else if (!this.dropFrame) {
            Systrace.begin(Section.RenderVideoTarget);
            try {
                render(timestampNs, target);
                if (this.isFirstFrame && this.isBroadComChip) {
                    this.isFirstFrame = false;
                    render(1000 + timestampNs, target);
                }
                Systrace.end();
            } catch (Throwable th) {
                Systrace.end();
            }
        }
    }

    private void render(long timestampNs, AbstractRenderingTarget target) throws GLException {
        target.bind();
        this.renderer.draw(this.sourceTexture, this.sourceResolution, target.getResolution(), target.getFitFactor(), target.getRotationAngle(), target.isHorizFlipped(), target.isVertFlipped());
        target.setTimestamp(timestampNs);
        target.swapBuffers();
    }

    public void freeContext() throws GLException {
        this.defaultRenderingTarget.unbind();
    }

    public SurfaceTexture getInput() {
        return this.sourceTexture;
    }

    public void setInputResolution(Resolution resolution) {
        this.sourceResolution = resolution;
    }

    public void close() {
        int num = this.renderingTargets.size();
        for (int i = 0; i < num; i++) {
            ((AbstractRenderingTarget) this.renderingTargets.valueAt(i)).close();
        }
        this.renderingTargets.clear();
        if (this.sourceTexture != null) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Releasing surface texture 0x" + Integer.toHexString(System.identityHashCode(this.sourceTexture)));
            }
            this.sourceTexture.release();
            this.sourceTexture = null;
        }
        if (this.renderer != null) {
            this.renderer.close();
            this.renderer = null;
        }
        if (this.defaultRenderingTarget != null) {
            this.defaultRenderingTarget.close();
            this.defaultRenderingTarget = null;
        }
        if (this.sharedContext != null) {
            this.sharedContext.close();
            this.sharedContext = null;
        }
        if (this.eglDisplay != null) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Terminating EGL");
            }
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.eglDisplay);
            this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        }
    }

    protected long getTimestamp(SurfaceTexture st) {
        return st.getTimestamp();
    }

    private SourceState awaitNewFrame(long timeoutMs) throws InterruptedException, GLException {
        SourceState initialState;
        synchronized (this.newFrameArrivedEvent) {
            if (this.sourceState == SourceState.WAITING_FOR_FRAME) {
                this.newFrameArrivedEvent.wait(timeoutMs);
            }
            Systrace.begin(Section.CaptureVideo);
            initialState = this.sourceState;
            this.sourceState = SourceState.WAITING_FOR_FRAME;
        }
        return initialState;
    }

    private AbstractRenderingTarget createRenderingTarget(OutputType type, Events eventsListener) throws GLException {
        switch (type) {
            case ENCODER:
                return new EncoderRenderingTarget(this.sharedContext, eventsListener);
            case SCREEN:
                return new ScreenRenderingTarget(this.sharedContext, eventsListener);
            default:
                throw new IllegalArgumentException("unknown rendering target type " + type);
        }
    }

    private static EGLDisplay createEGLDisplay() throws GLException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Creating EGL display");
        }
        EGLDisplay eglDisplay = EGL14.eglGetDisplay(0);
        if (eglDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new GLException("Failed to get EGL14 display.", EGL14.eglGetError());
        }
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": Initializing EGL");
        }
        int[] eglVersion = new int[2];
        if (EGL14.eglInitialize(eglDisplay, eglVersion, 0, eglVersion, 1)) {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, SurfaceTextureChannel.class.getSimpleName() + ": EGL initialized: version " + eglVersion[0] + "." + eglVersion[1]);
            }
            return eglDisplay;
        }
        throw new GLException("Failed to initialize EGL.", EGL14.eglGetError());
    }
}
