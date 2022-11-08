package com.skype.android.video.capture;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import java.util.LinkedList;
import java.util.Queue;

public class UiPreviewBinding implements com.skype.android.video.capture.IPreviewBinding.Callback {
    private boolean canUsePreviewBinding = false;
    private Callback cb;
    private Handler handler;
    private boolean isBindingCallbackActive = false;
    private boolean isStarted = false;
    private boolean isStopped = false;
    private SurfaceTexture mostRecentSurface;
    private PreviewBinding previewBinding;
    private Queue<SurfaceTexture> surfacesToUnsetNext = new LinkedList();
    private Queue<SurfaceTexture> unsetSurfacesWaitingToBeNotified = new LinkedList();

    public class BindingParams {
        public long event;
        public int type;
    }

    public interface Callback {
        void onFrameSizeChanged(SurfaceTexture surfaceTexture, int i, int i2);

        void onPreviewSurfaceUnset(SurfaceTexture surfaceTexture);
    }

    public UiPreviewBinding(Handler handler, Callback cb) {
        this.handler = handler;
        this.cb = cb;
    }

    public BindingParams setup() {
        if (this.isStarted) {
            throw new IllegalStateException("can not setup twice");
        }
        this.isStarted = true;
        this.previewBinding = new PreviewBinding(this);
        return getBindingParams(this.previewBinding);
    }

    public BindingParams destroy() {
        if (!this.isStarted || this.isStopped) {
            throw new IllegalStateException("can not destroy twice or before setup");
        }
        this.isStopped = true;
        this.canUsePreviewBinding = false;
        return getBindingParams(this.previewBinding);
    }

    public void setSurface(SurfaceTexture surface) {
        notifyOnPreviewSurfaceUnset(this.unsetSurfacesWaitingToBeNotified);
        if (this.mostRecentSurface != null) {
            this.surfacesToUnsetNext.add(this.mostRecentSurface);
        }
        this.mostRecentSurface = surface;
        if (this.canUsePreviewBinding) {
            this.previewBinding.setPreviewSurface(surface);
        } else if (!this.isBindingCallbackActive) {
            notifyOnPreviewSurfaceUnset(this.surfacesToUnsetNext);
        }
    }

    private void notifyOnPreviewSurfaceUnset(Queue<SurfaceTexture> surfaces) {
        while (!surfaces.isEmpty()) {
            this.cb.onPreviewSurfaceUnset((SurfaceTexture) surfaces.remove());
        }
    }

    private BindingParams getBindingParams(PreviewBinding pb) {
        BindingParams params = new BindingParams();
        params.type = pb.getNativeBindingType();
        params.event = pb.getNativeBindingEvent();
        return params;
    }

    public void onBindingCreated(long binding) {
        this.handler.post(new Runnable() {
            public void run() {
                UiPreviewBinding uiPreviewBinding = UiPreviewBinding.this;
                boolean z = UiPreviewBinding.this.isStarted && !UiPreviewBinding.this.isStopped;
                uiPreviewBinding.canUsePreviewBinding = z;
                if (UiPreviewBinding.this.canUsePreviewBinding) {
                    UiPreviewBinding.this.isBindingCallbackActive = true;
                    if (UiPreviewBinding.this.mostRecentSurface != null) {
                        UiPreviewBinding.this.previewBinding.setPreviewSurface(UiPreviewBinding.this.mostRecentSurface);
                    }
                }
            }
        });
    }

    public void onBindingReleased() {
        this.previewBinding.dispose();
        this.handler.post(new Runnable() {
            public void run() {
                UiPreviewBinding.this.isBindingCallbackActive = false;
            }
        });
    }

    public void onBindingFailed() {
        this.previewBinding.dispose();
    }

    public void onFrameSizeChanged(Object holder, final int width, final int height) {
        final SurfaceTexture surface = (SurfaceTexture) holder;
        this.handler.post(new Runnable() {
            public void run() {
                if (UiPreviewBinding.this.mostRecentSurface == surface) {
                    UiPreviewBinding.this.cb.onFrameSizeChanged(surface, width, height);
                }
            }
        });
    }

    public void onPreviewSurfaceUnset(Object holder) {
        final SurfaceTexture surface = (SurfaceTexture) holder;
        this.handler.post(new Runnable() {
            public void run() {
                if (UiPreviewBinding.this.surfacesToUnsetNext.remove(surface)) {
                    UiPreviewBinding.this.cb.onPreviewSurfaceUnset(surface);
                } else {
                    UiPreviewBinding.this.unsetSurfacesWaitingToBeNotified.add(surface);
                }
            }
        });
    }
}
