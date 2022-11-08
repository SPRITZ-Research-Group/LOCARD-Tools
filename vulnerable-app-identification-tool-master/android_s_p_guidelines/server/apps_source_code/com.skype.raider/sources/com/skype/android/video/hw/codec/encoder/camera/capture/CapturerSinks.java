package com.skype.android.video.hw.codec.encoder.camera.capture;

import android.graphics.SurfaceTexture;
import android.util.SparseArray;
import android.view.Surface;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.camera.capture.Capturer.Sink;
import com.skype.android.video.hw.codec.encoder.camera.capture.Capturer.SinkEvents;
import com.skype.android.video.hw.codec.encoder.camera.gl.AbstractRenderingTarget.Events;
import com.skype.android.video.hw.codec.encoder.camera.gl.ChannelPushFrame;
import com.skype.android.video.hw.codec.encoder.camera.gl.GLException;
import com.skype.android.video.hw.codec.encoder.camera.gl.SurfaceTextureChannel;
import com.skype.android.video.hw.codec.encoder.camera.gl.SurfaceTextureChannel.OutputType;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;

public class CapturerSinks implements Closeable {
    private final AtomicBoolean isDirty = new AtomicBoolean();
    private final SparseArray<AbstractSink> removedSinks = new SparseArray();
    private int sinkCounter;
    private final SparseArray<AbstractSink> sinks = new SparseArray();

    private abstract class AbstractSink implements Sink, Closeable {
        protected final SinkEvents cb;
        private TargetSurfaceHolder currentTarget;
        private volatile float fitFactor;
        protected final int id;
        private volatile boolean isEnabled;
        private volatile boolean isHorizFlipped;
        private volatile boolean isVertFlipped;
        private TargetSurfaceHolder newTarget;
        private volatile int rotationAngle;
        protected final Object sharedMutex;
        private final Object targetSurfaceMutex = new Object();

        public abstract boolean canReleaseSurface();

        public abstract OutputType getType();

        public abstract void onFrameCaptured(long j);

        protected AbstractSink(Object mutex, SinkEvents cb, int id) {
            this.sharedMutex = mutex;
            this.cb = cb;
            this.id = id;
        }

        public void attachSurface(Object surface, Resolution resolution) {
            if ((surface instanceof Surface) || (surface instanceof SurfaceTexture)) {
                synchronized (this.sharedMutex) {
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Surface provided: " + surface + " at " + resolution);
                    }
                    setTarget(surface, resolution);
                }
                return;
            }
            throw new IllegalArgumentException(surface.getClass().getCanonicalName() + " is not supported");
        }

        public void setResolution(Resolution resolution) {
            synchronized (this.sharedMutex) {
                synchronized (this.targetSurfaceMutex) {
                    if (this.newTarget == null) {
                        throw new IllegalStateException("no surface attached");
                    }
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Surface resolution provided: " + resolution);
                    }
                    this.newTarget.setResolution(resolution);
                    CapturerSinks.this.isDirty.set(true);
                }
            }
        }

        public void detachSurface() {
            synchronized (this.sharedMutex) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Surface abandoned");
                }
                setTarget(null, null);
            }
        }

        public void setEnabled(boolean isEnabled) {
            CapturerSinks.this.isDirty.set(true);
            this.isEnabled = isEnabled;
        }

        public void setRotationAngle(int rotationAngle) {
            CapturerSinks.this.isDirty.set(true);
            this.rotationAngle = rotationAngle;
        }

        public void setFlipped(boolean isHorizFlipped, boolean isVertFlipped) {
            CapturerSinks.this.isDirty.set(true);
            this.isHorizFlipped = isHorizFlipped;
            this.isVertFlipped = isVertFlipped;
        }

        public void setFitFactor(float fitFactor) {
            CapturerSinks.this.isDirty.set(true);
            this.fitFactor = fitFactor;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }

        public int getRotationAngle() {
            return this.rotationAngle;
        }

        public float getFitFactor() {
            return this.fitFactor;
        }

        public boolean isHorizFlipped() {
            return this.isHorizFlipped;
        }

        public boolean isVertFlipped() {
            return this.isVertFlipped;
        }

        public TargetSurfaceHolder getTargetSurface() {
            TargetSurfaceHolder targetSurfaceHolder;
            synchronized (this.targetSurfaceMutex) {
                if (this.newTarget != null) {
                    if (!(this.currentTarget == null || this.currentTarget == this.newTarget)) {
                        if (Log.isLoggable(Commons.TAG, 3)) {
                            Log.d(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Closing old " + this.currentTarget);
                        }
                        this.currentTarget.close();
                    }
                } else if (this.currentTarget != null) {
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Closing old " + this.currentTarget);
                    }
                    this.currentTarget.close();
                }
                this.currentTarget = this.newTarget;
                targetSurfaceHolder = this.currentTarget;
            }
            return targetSurfaceHolder;
        }

        private void setTarget(Object surface, Resolution resolution) {
            int i = 1;
            synchronized (this.targetSurfaceMutex) {
                if (!(this.newTarget == null && surface == null) && (this.newTarget == null || this.newTarget.getSurface() != surface)) {
                    if (!(this.newTarget == null || this.currentTarget == this.newTarget)) {
                        if (Log.isLoggable(Commons.TAG, 3)) {
                            Log.d(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Closing unused " + this.newTarget);
                        }
                        this.newTarget.close();
                    }
                    this.newTarget = surface != null ? new TargetSurfaceHolder(surface, resolution, canReleaseSurface()) : null;
                    CapturerSinks.this.isDirty.set(true);
                } else if (this.newTarget != null) {
                    int i2 = resolution != null ? 1 : 0;
                    if (resolution.equals(this.newTarget.getResolution())) {
                        i = 0;
                    }
                    if ((i & i2) != 0) {
                        this.newTarget.setResolution(resolution);
                        CapturerSinks.this.isDirty.set(true);
                    }
                }
            }
        }

        public void close() {
            synchronized (this.sharedMutex) {
                if (this.newTarget == null && this.currentTarget != null) {
                    if (Log.isLoggable(Commons.TAG, 3)) {
                        Log.d(Commons.TAG, getClass().getSimpleName() + " #" + this.id + " Closing old " + this.currentTarget);
                    }
                    this.currentTarget.close();
                    this.currentTarget = null;
                }
            }
        }

        public int hashCode() {
            return this.id;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            if (this.id != ((AbstractSink) obj).id) {
                return false;
            }
            return true;
        }

        public int getId() {
            return this.id;
        }

        public String toString() {
            return getClass().getSimpleName() + " #" + this.id + " [ ( " + this.currentTarget + " -> " + this.newTarget + " ), @" + this.rotationAngle + " deg" + (!this.isEnabled ? ", disabled" : "") + (this.isHorizFlipped ? ", H-flipped" : "") + (this.isVertFlipped ? ", V-flipped" : "") + "]";
        }
    }

    private class EncoderSink extends AbstractSink {
        private volatile ChannelPushFrame channel;
        private float targetFps = 30.0f;

        public EncoderSink(Object mutex, SinkEvents cb, int id, ChannelPushFrame channel) {
            super(mutex, cb, id);
            this.channel = channel;
        }

        public OutputType getType() {
            return OutputType.ENCODER;
        }

        public void onFrameCaptured(long timestamp) {
            this.cb.onFrameCaptured(timestamp);
        }

        public boolean canReleaseSurface() {
            return true;
        }

        public void setTargetFrameRate(float fps) {
            CapturerSinks.this.isDirty.set(true);
            this.targetFps = fps;
        }

        public float getTargetFrameRate() {
            return this.targetFps;
        }

        public boolean pushFrame() throws CapturerException {
            if (this.channel != null) {
                try {
                    return this.channel.executePushFrame(this.id);
                } catch (GLException e) {
                    throw new CapturerException("Push encoder frame failed", e);
                }
            }
            if (Log.isLoggable(Commons.TAG, 5)) {
                Log.w(Commons.TAG, "Attempt to push frame when channel does not exist");
            }
            return false;
        }

        public void setChannel(ChannelPushFrame channel) {
            this.channel = channel;
        }
    }

    private class PreviewSink extends AbstractSink {
        public PreviewSink(Object mutex, SinkEvents cb, int id) {
            super(mutex, cb, id);
        }

        public OutputType getType() {
            return OutputType.SCREEN;
        }

        public void onFrameCaptured(long timestamp) {
        }

        public boolean canReleaseSurface() {
            return false;
        }

        public void setTargetFrameRate(float fps) {
        }

        public float getTargetFrameRate() {
            return 0.0f;
        }

        public boolean pushFrame() throws CapturerException {
            return false;
        }
    }

    public static class TargetSurfaceHolder implements Closeable {
        private final boolean canReleaseSurface;
        private Resolution resolution;
        private Object surface;

        public TargetSurfaceHolder(Object surface, Resolution resolution, boolean canReleaseSurface) {
            this.canReleaseSurface = canReleaseSurface;
            this.surface = surface;
            this.resolution = resolution;
        }

        public Object getSurface() {
            return this.surface;
        }

        public Resolution getResolution() {
            return this.resolution;
        }

        public void setResolution(Resolution resolution) {
            this.resolution = resolution;
        }

        public void close() {
            if (this.surface != null) {
                if (this.canReleaseSurface) {
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Releasing surface: " + this);
                    }
                    if (this.surface instanceof Surface) {
                        ((Surface) this.surface).release();
                    } else if (this.surface instanceof SurfaceTexture) {
                        ((SurfaceTexture) this.surface).release();
                    } else {
                        throw new IllegalArgumentException(this.surface.getClass().getCanonicalName() + " is not supported");
                    }
                }
                this.surface = null;
            }
            this.resolution = null;
        }

        public String toString() {
            return this.surface + " at " + this.resolution;
        }
    }

    public Sink addEncoderSink(Object mutex, SinkEvents cb, ChannelPushFrame channel) {
        this.isDirty.set(true);
        int i = this.sinkCounter;
        this.sinkCounter = i + 1;
        EncoderSink sink = new EncoderSink(mutex, cb, i, channel);
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Adding " + sink + " to the capturer");
        }
        this.sinks.append(sink.getId(), sink);
        return sink;
    }

    public Sink addPreviewSink(Object mutex, SinkEvents cb) {
        this.isDirty.set(true);
        int i = this.sinkCounter;
        this.sinkCounter = i + 1;
        PreviewSink sink = new PreviewSink(mutex, cb, i);
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Adding " + sink + " to the capturer");
        }
        this.sinks.append(sink.getId(), sink);
        return sink;
    }

    public void removeSink(Sink sink) {
        this.isDirty.set(true);
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Removing " + sink + " from the capturer");
        }
        AbstractSink abstractSink = (AbstractSink) sink;
        this.removedSinks.append(abstractSink.getId(), abstractSink);
        this.sinks.delete(abstractSink.getId());
    }

    public void syncWith(SurfaceTextureChannel channel) throws GLException {
        if (this.isDirty.getAndSet(false)) {
            int num;
            int i;
            AbstractSink sink;
            if (this.removedSinks.size() > 0) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, getClass().getSimpleName() + ": Got " + this.removedSinks.size() + " removed sinks");
                }
                num = this.removedSinks.size();
                for (i = 0; i < num; i++) {
                    sink = (AbstractSink) this.removedSinks.valueAt(i);
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, getClass().getSimpleName() + ": Deallocating " + sink + " at the " + channel.getClass().getSimpleName());
                    }
                    channel.deallocateOutput(sink.getId());
                    sink.close();
                }
                this.removedSinks.clear();
            }
            num = this.sinks.size();
            for (i = 0; i < num; i++) {
                sink = (AbstractSink) this.sinks.valueAt(i);
                if (!channel.hasTarget(sink.getId())) {
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, getClass().getSimpleName() + ": Allocating " + sink + " at the " + channel.getClass().getSimpleName());
                    }
                    channel.allocateOutput(sink.getType(), sink.getId(), new Events() {
                        public void onFrameRendered(long timestamp) {
                            sink.onFrameCaptured(timestamp);
                        }
                    });
                }
                TargetSurfaceHolder target = sink.getTargetSurface();
                if (target != null) {
                    if (!channel.isOutputAttached(sink.getId(), target.getSurface(), target.getResolution())) {
                        if (Log.isLoggable(Commons.TAG, 4)) {
                            Log.i(Commons.TAG, getClass().getSimpleName() + ": Attaching " + sink + " to the " + channel.getClass().getSimpleName());
                        }
                        channel.attachOutput(sink.getId(), target.getSurface(), target.getResolution());
                    }
                } else if (channel.isOutputAttached(sink.getId())) {
                    if (Log.isLoggable(Commons.TAG, 4)) {
                        Log.i(Commons.TAG, getClass().getSimpleName() + ": Detaching " + sink + " from the " + channel.getClass().getSimpleName());
                    }
                    channel.detachOutput(sink.getId());
                }
                channel.setParameters(sink.getId(), sink.isEnabled(), sink.getRotationAngle(), sink.getFitFactor(), sink.isHorizFlipped(), sink.isVertFlipped(), sink.getTargetFrameRate());
            }
        }
    }

    public void setChannel(ChannelPushFrame channel) {
        int num = this.sinks.size();
        for (int i = 0; i < num; i++) {
            AbstractSink sink = (AbstractSink) this.sinks.valueAt(i);
            if (sink instanceof EncoderSink) {
                ((EncoderSink) sink).setChannel(channel);
            }
        }
    }

    public void close() {
        int i;
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, getClass().getSimpleName() + ": Closing");
        }
        int num = this.removedSinks.size();
        for (i = 0; i < num; i++) {
            ((AbstractSink) this.removedSinks.valueAt(i)).close();
        }
        this.removedSinks.clear();
        num = this.sinks.size();
        for (i = 0; i < num; i++) {
            ((AbstractSink) this.sinks.valueAt(i)).close();
        }
        this.sinks.clear();
    }

    public String toString() {
        return getClass().getSimpleName() + " [sinkCounter=" + this.sinkCounter + ", sinks=" + this.sinks + ", removedSinks=" + this.removedSinks + "]";
    }
}
