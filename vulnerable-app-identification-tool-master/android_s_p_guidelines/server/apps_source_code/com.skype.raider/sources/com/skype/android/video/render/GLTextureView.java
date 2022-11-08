package com.skype.android.video.render;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.skype.android.util2.Log;

public class GLTextureView extends TextureView implements Callback, SurfaceTextureListener {
    private static final int MSG_RENDER = 1;
    private static final int MSG_RENDERING_WILL_START = 7;
    private static final int MSG_SET_RENDERER = 5;
    private static final int MSG_STOP_THREAD = 6;
    private static final int MSG_TEXTURE_AVAILABLE = 2;
    private static final int MSG_TEXTURE_DESTROYED = 4;
    private static final int MSG_TEXTURE_SIZE_CHANGED = 3;
    private final String TAG;
    private boolean _appliedVisibility;
    private boolean _canReceiveFrames;
    private int _height;
    private SurfaceTextureRenderer _renderer;
    private EGLRenderSurface _surface;
    private SurfaceTexture _surfaceTexture;
    private int _width;
    private boolean doReleaseSurfaceThread;
    private Handler handler;
    private boolean isHandlerThreadShared;
    private volatile boolean isRunning;
    private final SurfaceTextureAvailableListener listener;
    private final Object stoppedEvent;
    private HandlerThread thread;

    public interface SurfaceTextureAvailableListener {
        void onSurfaceTextureAvailable(TextureView textureView);
    }

    public GLTextureView(Context context, Looper looper, boolean isHandlerThreadShared, SurfaceTextureAvailableListener listener) {
        super(context);
        this.stoppedEvent = new Object();
        this.TAG = System.identityHashCode(this);
        if (Log.a(3)) {
            Log.a(this.TAG, "ctor using internal thread " + (looper == null) + " isHandlerThreadShared " + isHandlerThreadShared);
        }
        if (looper == null) {
            this.thread = new HandlerThread("GLTextureView");
            this.thread.start();
            looper = this.thread.getLooper();
        }
        this.handler = new Handler(looper, this);
        this.isHandlerThreadShared = isHandlerThreadShared;
        this.listener = listener;
        setSurfaceTextureListener(this);
        this.doReleaseSurfaceThread = true;
    }

    public GLTextureView(Context context) {
        this(context, null, false, null);
    }

    public void dispose() {
        if (Log.a(3)) {
            Log.a(this.TAG, "dispose");
        }
        setSurfaceTextureListener(null);
        if (!(this.handler == null || this.thread == null)) {
            this.handler.sendEmptyMessage(6);
        }
        this.handler = null;
    }

    private String stateStr() {
        return " _canReceiveFrames " + this._canReceiveFrames + " _appliedVisibility " + this._appliedVisibility + " _renderer " + System.identityHashCode(this._renderer) + " _surface " + System.identityHashCode(this._surface) + " _surfaceTexture " + System.identityHashCode(this._surfaceTexture);
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                if (!(!this.isRunning || this._renderer == null || this._surface == null)) {
                    _render();
                    break;
                }
            case 2:
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_TEXTURE_AVAILABLE enter surfaceTexture " + System.identityHashCode(msg.obj) + " width " + msg.arg1 + " height " + msg.arg2 + stateStr());
                }
                this._surfaceTexture = (SurfaceTexture) msg.obj;
                this._width = msg.arg1;
                this._height = msg.arg2;
                if (this._renderer != null) {
                    _createSurface();
                    _attach();
                    _tryUpdateVisibility(true);
                }
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_TEXTURE_AVAILABLE exit" + stateStr());
                    break;
                }
                break;
            case 3:
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_TEXTURE_SIZE_CHANGED surfaceTexture " + System.identityHashCode(msg.obj) + " width " + msg.arg1 + " height " + msg.arg2);
                }
                this._width = msg.arg1;
                this._height = msg.arg2;
                if (this._renderer != null) {
                    _detach();
                    _destroySurface();
                    _createSurface();
                    _attach();
                }
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_TEXTURE_SIZE_CHANGED exit" + stateStr());
                    break;
                }
                break;
            case 4:
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_TEXTURE_DESTROYED enter surfaceTexture " + System.identityHashCode(msg.obj) + stateStr());
                }
                this._surfaceTexture.release();
                this._surfaceTexture = null;
                if (this._surface != null) {
                    if (this._renderer != null) {
                        _tryUpdateVisibility(false);
                        _detach();
                    }
                    _destroySurface();
                }
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_TEXTURE_DESTROYED exit" + stateStr());
                    break;
                }
                break;
            case 5:
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_SET_RENDERER enter renderer " + System.identityHashCode(msg.obj) + stateStr());
                }
                SurfaceTextureRenderer renderer = msg.obj;
                if (renderer != null) {
                    this.isRunning = true;
                    this._renderer = renderer;
                    if (this._surfaceTexture != null && this._surface == null) {
                        _createSurface();
                    }
                    if (this._surface != null) {
                        _attach();
                        _tryUpdateVisibility(true);
                    }
                } else {
                    if (this._surface != null) {
                        if (this._renderer != null) {
                            _detach();
                        }
                        _destroySurface();
                    }
                    this._renderer = null;
                    this._canReceiveFrames = false;
                    this._appliedVisibility = false;
                    synchronized (this.stoppedEvent) {
                        this.isRunning = false;
                        this.stoppedEvent.notify();
                    }
                }
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_SET_RENDERER exit renderer " + System.identityHashCode(msg.obj) + stateStr());
                    break;
                }
                break;
            case 6:
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_STOP_THREAD" + stateStr());
                }
                this.thread.quit();
                this.thread = null;
                break;
            case 7:
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_RENDERING_WILL_START enter" + stateStr());
                }
                this._canReceiveFrames = true;
                if (!(this._renderer == null || this._surface == null)) {
                    _tryUpdateVisibility(true);
                }
                if (Log.a(3)) {
                    Log.a(this.TAG, "handleMessage: MSG_RENDERING_WILL_START exit" + stateStr());
                    break;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    private void _tryUpdateVisibility(boolean value) {
        if (this._appliedVisibility != value && this._canReceiveFrames) {
            this._appliedVisibility = value;
            if (Log.a(3)) {
                Log.a(this.TAG, "_tryUpdateVisibility: visible " + value + stateStr());
            }
            this._renderer.onVisibilityChanged(value);
        }
    }

    private void _createSurface() {
        this._surface = new EGL10RenderSurface();
        this._surface.create(this._surfaceTexture);
        if (!this.isHandlerThreadShared) {
            this._surface.makeCurrent(true);
        }
    }

    private void _destroySurface() {
        this._surface.destroy(this.doReleaseSurfaceThread);
        this._surface = null;
    }

    private void _render() {
        try {
            if (this.isHandlerThreadShared) {
                this._surface.makeCurrent(true);
            }
            if (this._renderer.render(this._width, this._height)) {
                this._surface.swapBuffers();
            }
        } catch (EGLException e) {
            throw e;
        }
    }

    private void _attach() {
        if (this.isHandlerThreadShared) {
            this._surface.makeCurrent(true);
        }
        this._renderer.attach();
    }

    private void _detach() {
        if (this.isHandlerThreadShared) {
            this._surface.makeCurrent(true);
        }
        this._renderer.detach();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (Log.a(3)) {
            Log.a(this.TAG, "onSurfaceTextureAvailable: surfaceTexture " + System.identityHashCode(surface) + " width " + width + " height " + height);
        }
        if (this.listener != null) {
            this.listener.onSurfaceTextureAvailable(this);
        }
        this.handler.sendMessage(this.handler.obtainMessage(2, width, height, surface));
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        if (Log.a(3)) {
            Log.a(this.TAG, "onSurfaceTextureSizeChanged: surfaceTexture " + System.identityHashCode(surface) + " width " + width + " height " + height);
        }
        this.handler.sendMessage(this.handler.obtainMessage(3, width, height, surface));
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (Log.a(3)) {
            Log.a(this.TAG, "onSurfaceTextureDestroyed: surfaceTexture " + System.identityHashCode(surface));
        }
        this.handler.sendMessage(this.handler.obtainMessage(4, surface));
        return false;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    public void requestRender() {
        this.handler.removeMessages(1);
        this.handler.sendEmptyMessage(1);
    }

    public void setRenderer(SurfaceTextureRenderer renderer) {
        if (Log.a(3)) {
            Log.a(this.TAG, "setRenderer " + System.identityHashCode(renderer));
        }
        this.handler.sendMessage(this.handler.obtainMessage(5, renderer));
    }

    public void start() {
        this.handler.sendEmptyMessage(7);
    }

    public void stop() {
        synchronized (this.stoppedEvent) {
            if (this.isRunning) {
                try {
                    this.stoppedEvent.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void queueEvent(Runnable runnable) {
        if (this.handler != null) {
            this.handler.post(runnable);
        }
    }

    public boolean getReleaseSurfaceThread() {
        return this.doReleaseSurfaceThread;
    }

    public void setReleaseSurfaceThread(boolean doReleaseSurfaceThread) {
        this.doReleaseSurfaceThread = doReleaseSurfaceThread;
    }
}
