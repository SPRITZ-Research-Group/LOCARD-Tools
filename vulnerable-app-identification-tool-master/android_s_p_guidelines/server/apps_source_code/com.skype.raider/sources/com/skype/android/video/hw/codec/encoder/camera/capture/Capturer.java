package com.skype.android.video.hw.codec.encoder.camera.capture;

import android.graphics.SurfaceTexture;
import com.skype.android.video.hw.Commons;
import com.skype.android.video.hw.codec.encoder.camera.SurfaceObserver;
import com.skype.android.video.hw.codec.encoder.camera.gl.GLException;
import com.skype.android.video.hw.codec.encoder.camera.gl.SurfaceTextureChannel;
import com.skype.android.video.hw.format.Resolution;
import com.skype.android.video.hw.utils.Log;
import com.skype.android.video.hw.utils.Systrace;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Capturer {
    private Resolution cameraResolution;
    protected CapturerSource captureSource;
    private SurfaceTextureChannel channel;
    private volatile boolean isStarted;
    private Runner runner;
    private Thread runnerThread;
    private final Object sinkMutex = new Object();
    private final CapturerSinks sinks = new CapturerSinks();

    private class Runner implements Closeable, Runnable {
        private static final long CAPTURE_TIMEOUT_MS = Long.MAX_VALUE;
        private final CapturerParameters capturerParameters;
        private final RunnerEvents cb;
        private volatile boolean shallRun = true;

        public Runner(CapturerParameters capturerParameters, RunnerEvents cb) {
            this.capturerParameters = capturerParameters;
            this.cb = cb;
        }

        public void close() {
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Requesting exit");
            }
            this.shallRun = false;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            if (this.capturerParameters == null) {
                throw new IllegalStateException("no capture parameters set");
            }
            try {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Entering");
                }
                if (Capturer.this.channel == null) {
                    Capturer.this.setup(this.capturerParameters);
                }
                if (this.cb != null) {
                    this.cb.onStarted();
                }
                while (this.shallRun) {
                    Capturer.this.prepareNextFrame(CAPTURE_TIMEOUT_MS);
                }
                Capturer.this.shutdown();
                Capturer.this.isStarted = false;
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            } catch (RuntimeException e) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, Runner.class.getSimpleName() + ": UnexpectedException caught", e);
                }
                Capturer.this.shutdown();
                Capturer.this.isStarted = false;
                if (this.cb != null) {
                    this.cb.onFailed(e);
                }
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            } catch (CapturerException e2) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, Runner.class.getSimpleName() + ": Capturing has failed", e2);
                }
                Capturer.this.shutdown();
                Capturer.this.isStarted = false;
                if (this.cb != null) {
                    this.cb.onFailed(e2);
                }
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            } catch (InterruptedException e3) {
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Interrupted");
                }
                Capturer.this.shutdown();
                Capturer.this.isStarted = false;
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            } catch (TimeoutException e4) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, Runner.class.getSimpleName() + ": Timeout expired");
                }
                Capturer.this.shutdown();
                Capturer.this.isStarted = false;
                if (this.cb != null) {
                    this.cb.onFailed(e4);
                }
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            } catch (GLException e5) {
                if (Log.isLoggable(Commons.TAG, 6)) {
                    Log.e(Commons.TAG, Runner.class.getSimpleName() + ": GL Error");
                }
                Capturer.this.shutdown();
                Capturer.this.isStarted = false;
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            } catch (Throwable th) {
                if (this.cb != null) {
                    this.cb.onStopped();
                }
                if (Log.isLoggable(Commons.TAG, 4)) {
                    Log.i(Commons.TAG, Runner.class.getSimpleName() + ": Leaving");
                }
            }
        }
    }

    public interface RunnerEvents {
        void onFailed(Exception exception);

        void onStarted();

        void onStopped();
    }

    public interface Sink extends SurfaceObserver {
        void setEnabled(boolean z);

        void setFitFactor(float f);

        void setFlipped(boolean z, boolean z2);

        void setResolution(Resolution resolution);

        void setRotationAngle(int i);
    }

    public interface SinkEvents {
        void onFrameCaptured(long j);
    }

    public synchronized Sink addPreviewSink(SinkEvents cb) {
        return this.sinks.addPreviewSink(this.sinkMutex, cb);
    }

    public synchronized Sink addEncoderSink(SinkEvents cb) {
        return this.sinks.addEncoderSink(this.sinkMutex, cb, this.channel);
    }

    public synchronized void removeSink(Sink sink) {
        this.sinks.removeSink(sink);
    }

    public synchronized void closeSinks() {
        this.sinks.close();
    }

    public synchronized void setCameraResolution(Resolution resolution) throws CapturerException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Switching camera to " + resolution);
        }
        boolean wasStarted = this.isStarted;
        if (this.isStarted) {
            stop();
        }
        this.captureSource.setResolution(resolution);
        this.channel.setInputResolution(resolution);
        if (wasStarted) {
            start();
        }
    }

    public synchronized void setCameraFramerate(float framerate) throws CapturerException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Switching camera to " + framerate + " fps");
        }
        boolean wasStarted = this.isStarted;
        if (this.isStarted) {
            stop();
        }
        this.captureSource.setFramerate((int) (1000.0f * framerate));
        if (wasStarted) {
            start();
        }
    }

    public synchronized void setCameraParameters(Resolution resolution, float framerate) throws CapturerException {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Switching camera to " + (resolution != null ? resolution : "") + (framerate > 0.0f ? "@" + framerate + " fps" : ""));
        }
        boolean wasStarted = this.isStarted;
        if (this.isStarted) {
            stop();
        }
        this.captureSource.configure(resolution, framerate > 0.0f ? (int) (1000.0f * framerate) : 0);
        if (resolution != null) {
            this.channel.setInputResolution(resolution);
        }
        if (wasStarted) {
            start();
        }
    }

    public synchronized void initialize(CapturerParameters capturerParameters, RunnerEvents cb) {
        if (isAlive()) {
            throw new IllegalStateException("already running");
        }
        this.runner = new Runner(capturerParameters, cb);
        this.runnerThread = new Thread(this.runner, Capturer.class.getCanonicalName());
        this.runnerThread.start();
    }

    public synchronized void terminate(long timeoutMs) throws InterruptedException {
        if (this.isStarted) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, Capturer.class.getSimpleName() + ": Stopping capture source");
            }
            stop();
        }
        if (isAlive()) {
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, Capturer.class.getSimpleName() + ": Shutting down the runner");
            }
            this.runner.close();
            this.channel.interrupt();
            this.runnerThread.join(timeoutMs);
        }
        if (isAlive() && Log.isLoggable(Commons.TAG, 5)) {
            Log.w(Commons.TAG, Capturer.class.getSimpleName() + ": Runner thead did not exit in " + timeoutMs + " ms");
        }
    }

    public synchronized boolean isAlive() {
        boolean z;
        z = this.runnerThread != null && this.runnerThread.isAlive();
        return z;
    }

    public synchronized void start() throws CapturerException {
        if (this.captureSource == null) {
            throw new IllegalStateException("closed");
        } else if (!this.isStarted) {
            this.captureSource.start();
            this.isStarted = true;
        }
    }

    public synchronized void stop() {
        if (this.captureSource == null) {
            throw new IllegalStateException("closed");
        } else if (this.isStarted) {
            this.captureSource.stop();
            this.isStarted = false;
        }
    }

    public boolean isStarted() {
        return isAlive() && this.isStarted;
    }

    protected long doOverrideFrameTimestamp(long timestamp) {
        return timestamp;
    }

    protected CapturerSource doCreateCapturerSource(CapturerParameters capturerParameters) throws CapturerException {
        return new CameraCapturerSource(capturerParameters.cameraId);
    }

    protected void setup(CapturerParameters capturerParameters) throws CapturerException, GLException {
        try {
            this.cameraResolution = capturerParameters.cameraResolution;
            this.channel = new SurfaceTextureChannel(capturerParameters.isPreEncodingRC) {
                protected long getTimestamp(SurfaceTexture st) {
                    return Capturer.this.doOverrideFrameTimestamp(st.getTimestamp());
                }
            };
            this.sinks.syncWith(this.channel);
            this.sinks.setChannel(this.channel);
            this.channel.setInputResolution(this.cameraResolution);
            this.captureSource = doCreateCapturerSource(capturerParameters);
            this.captureSource.configure(capturerParameters.cameraResolution, (int) (capturerParameters.framerate * 1000.0f));
            this.captureSource.setSurfaceTexture(this.channel.getInput());
            this.channel.freeContext();
            if (Log.isLoggable(Commons.TAG, 4)) {
                Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Initialized");
            }
        } catch (GLException e) {
            throw new CapturerException("Failed to create SurfaceTextureChannel", e);
        } catch (CapturerException e2) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, Capturer.class.getSimpleName() + ": Initialization failed. Closing");
            }
            shutdown();
            throw e2;
        } catch (RuntimeException e3) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, Capturer.class.getSimpleName() + ": Initialization failed. Closing");
            }
            shutdown();
            throw e3;
        } catch (GLException e4) {
            if (Log.isLoggable(Commons.TAG, 6)) {
                Log.e(Commons.TAG, Capturer.class.getSimpleName() + ": Initialization failed. Closing");
            }
            shutdown();
            throw e4;
        }
    }

    private void shutdown() {
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Closing");
        }
        if (this.captureSource != null) {
            try {
                this.captureSource.close();
            } catch (IOException e) {
            }
            this.captureSource = null;
        }
        if (this.channel != null) {
            this.channel.close();
            this.channel = null;
        }
        if (Log.isLoggable(Commons.TAG, 4)) {
            Log.i(Commons.TAG, Capturer.class.getSimpleName() + ": Closed");
        }
    }

    private void prepareNextFrame(long timeoutMs) throws CapturerException, InterruptedException, TimeoutException {
        if (this.channel == null) {
            throw new IllegalStateException("closed");
        }
        try {
            long timestampNS = this.channel.acquireFrame(timeoutMs);
            long timestampMS = timestampNS / 1000000;
            if (Log.isLoggable(Commons.TAG, 3)) {
                Log.d(Commons.TAG, Capturer.class.getSimpleName() + " Capturer timestampNS " + timestampNS + " timestampMS " + timestampMS);
            }
            synchronized (this.sinkMutex) {
                this.sinks.syncWith(this.channel);
                this.channel.pushAndRenderFrame(1000000 * timestampMS);
            }
            Systrace.end();
        } catch (GLException e) {
            try {
                throw new CapturerException("Failed to push a frame through SurfaceTextureChannel", e);
            } catch (Throwable th) {
                Systrace.end();
            }
        }
    }
}
