package com.skype.slimcore.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.a;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.bridge.v;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skype.android.video.ControlUnit;
import com.skype.android.video.ControlUnit.StateListener;
import com.skype.android.video.DeviceProfile;
import com.skype.slimcore.video.VideoOrientationManager.OrientationChangedCallback;
import java.util.Random;

public class RNCallingVideoViewLocal extends FrameLayout implements SurfaceTextureListener, v, StateListener, OrientationChangedCallback {
    private static final Random a = new Random();
    private static Point b;
    private static boolean c = false;
    private TextureView d;
    private boolean e = false;
    private boolean f = false;
    private int g;
    private CameraFacing h = CameraFacing.NONE;
    private boolean i = false;
    private ag j;
    private int k;
    private SurfaceTexture l;
    private final Object m = new Object();
    private RNBackgroundDrawable n;
    private Path o = new Path();
    private float p = 0.0f;
    private Point q = new Point(0, 0);
    private VideoOrientationManager r;

    public RNCallingVideoViewLocal(Context context, VideoOrientationManager videoOrientationManager) {
        super(context);
        setClipChildren(true);
        if (VERSION.SDK_INT >= 21) {
            setClipToOutline(true);
        }
        if (b == null) {
            DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            b = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "Initialized videoDimensions are %d x %d", Integer.valueOf(b.x), Integer.valueOf(b.y));
        }
        this.r = videoOrientationManager;
    }

    public final void a(ag reactContext, int videoObjectId, int videoCameraPosition, boolean fit, int causeId) {
        this.h = d(videoCameraPosition);
        this.i = fit;
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "attachVideo, videoObjectId: %d camera: %s fit: %b causeId: %x", Integer.valueOf(videoObjectId), this.h, Boolean.valueOf(fit), Integer.valueOf(causeId));
        this.j = reactContext;
        this.k = videoObjectId;
        ControlUnit.registerStateListener(this);
        this.j.a((v) this);
        this.j.a((v) this);
        TextureView textureView = new TextureView(getContext());
        textureView.setSurfaceTextureListener(this);
        this.d = textureView;
        textureView.setVisibility(0);
        textureView.layout(0, 0, getRight(), getBottom());
        addView(textureView);
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "attachVideo default videoDimensions %d x %d causeId: %x", Integer.valueOf(b.x), Integer.valueOf(b.y), Integer.valueOf(causeId));
        this.r.a();
        this.r.a(videoObjectId, (OrientationChangedCallback) this);
        a(this.d, causeId);
    }

    public final void a(boolean fit, int causeId) {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "updateAspectRatio, fit: %b w: %d h: %d causeId: %x", Boolean.valueOf(fit), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(causeId));
        this.i = fit;
        a(this.d, causeId);
    }

    public final void a(RNCallingVideoViewLocal otherView, int videoObjectId, int videoCameraPosition, boolean fit, int causeId) {
        a.a(videoObjectId == otherView.k, "reparentVideo videoObjectIds must match (" + videoObjectId + "," + otherView.k + ") causeId: " + causeId);
        this.h = d(videoCameraPosition);
        this.i = fit;
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "reparentVideo, videoObjectId: %d camera: %s fit: %b w: %d h: %d causeId: %x", Integer.valueOf(videoObjectId), this.h, Boolean.valueOf(fit), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(causeId));
        otherView.r.a(this.k);
        this.j = otherView.j;
        this.j = otherView.j;
        this.r = otherView.r;
        this.k = videoObjectId;
        this.d = otherView.d;
        otherView.removeView(this.d);
        this.d.setSurfaceTextureListener(this);
        this.d.layout(0, 0, getRight(), getBottom());
        addView(this.d);
        ControlUnit.unregisterStateListener(otherView);
        this.j.b((v) otherView);
        ControlUnit.registerStateListener(this);
        this.j.a((v) this);
        this.r.a(videoObjectId, (OrientationChangedCallback) this);
        a(this.d, causeId);
        if (this.d.isAvailable()) {
            ar map = new WritableNativeMap();
            map.putInt("id", videoObjectId);
            map.putString("action", "attached");
            map.putBoolean("success", true);
        }
    }

    public final void a(int causeId) {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "detachVideo causeId: %x", Integer.valueOf(causeId));
        this.e = true;
        ControlUnit.unregisterStateListener(this);
        this.j.b((v) this);
        this.r.a(this.k);
        this.r.b();
        if (this.d != null) {
            removeView(this.d);
        }
        synchronized (this.m) {
            if (this.f) {
                FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "detachVideo called when surface was already destroyed; cleaning up causeId: %x", Integer.valueOf(causeId));
                c(causeId);
            }
        }
    }

    private void a(int width, int height, int causeId) {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "handleVideoSizeChanged, w: %d h: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
        if (this.d != null) {
            b.set(width, height);
            c = true;
            a(this.i, causeId);
            ar writableNativeMap = new WritableNativeMap();
            writableNativeMap.putInt("id", this.k);
            writableNativeMap.putInt("width", width);
            writableNativeMap.putInt("height", height);
            ((RCTNativeAppEventEmitter) this.j.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoSizeChangedEvent", writableNativeMap);
        }
    }

    private void a(TextureView view, int causeId) {
        Point adjustedVideoDimensions;
        if (this.d == null) {
            adjustedVideoDimensions = b;
        } else {
            int c = this.r.c();
            if (c) {
                if (DeviceProfile.changePreviewDimensions(this.h == CameraFacing.FRONT ? 1 : 0, c)) {
                    FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay rotating for camera -> %d x %d causeId: %x", Integer.valueOf(b.y), Integer.valueOf(b.x), Integer.valueOf(causeId));
                    adjustedVideoDimensions = new Point(b.y, b.x);
                } else {
                    FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay no change -> %d x %d causeId: %x", Integer.valueOf(b.x), Integer.valueOf(b.y), Integer.valueOf(causeId));
                    adjustedVideoDimensions = b;
                }
            } else if (c == 90 || c == 270) {
                FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay landscape -> %d x %d causeId: %x", Integer.valueOf(b.x), Integer.valueOf(b.y), Integer.valueOf(causeId));
                adjustedVideoDimensions = new Point(b.y, b.x);
            } else {
                FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay default -> %d x %d causeId: %x", Integer.valueOf(b.x), Integer.valueOf(b.y), Integer.valueOf(causeId));
                adjustedVideoDimensions = b;
            }
        }
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "scaleView width: %d height: %d displayWidth: %d displayHeight: %d scaleToFit: %b panOffset: %s causeId: %x ", Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(adjustedVideoDimensions.x), Integer.valueOf(adjustedVideoDimensions.y), Boolean.valueOf(this.i), this.q, Integer.valueOf(causeId));
        ViewScaleHelper.a(view, getWidth(), getHeight(), adjustedVideoDimensions.x, adjustedVideoDimensions.y, this.i, this.q);
    }

    protected void onAttachedToWindow() {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onAttachedToWindow");
        if (!(this.d == null || this.l == null || this.d.getSurfaceTexture() == this.l)) {
            this.d.setSurfaceTexture(this.l);
        }
        super.onAttachedToWindow();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        synchronized (this.m) {
            int causeId = a.nextInt();
            FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onSurfaceTextureAvailable, w: %d h: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
            if (this.l == null) {
                this.l = surface;
                this.g = ControlUnit.registerView(surface, 0, 3, 0);
                a(this.d, causeId);
                ar map = new WritableNativeMap();
                map.putInt("id", this.k);
                map.putString("action", "attached");
                map.putBoolean("success", true);
                ((RCTNativeAppEventEmitter) this.j.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
            }
            this.f = false;
            if (b != null) {
                a(b.x, b.y, causeId);
            }
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        int causeId = a.nextInt();
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onSurfaceTextureSizeChanged w: %d h: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
        a(this.d, causeId);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        synchronized (this.m) {
            int causeId = a.nextInt();
            FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onSurfaceTextureDestroyed causeId: %x", Integer.valueOf(causeId));
            if (this.e) {
                c(causeId);
            } else {
                FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "Received onSurfaceDestroyed while isDetachingVideo was false; setting isSurfaceDestroyedButNotCleanedUp to true causeId: %x", Integer.valueOf(causeId));
                this.f = true;
            }
        }
        return false;
    }

    private void c(int causeId) {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "cleanupSurfaceTexture causeId: %x", Integer.valueOf(causeId));
        ControlUnit.unregisterView(this.g, 0, 3, 0);
        ar map = new WritableNativeMap();
        map.putInt("id", this.k);
        map.putString("action", "detached");
        map.putBoolean("success", true);
        ((RCTNativeAppEventEmitter) this.j.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
        this.d.setSurfaceTextureListener(null);
        this.e = false;
        this.f = false;
        if (this.l != null) {
            FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "cleanupSurfaceTexture release causeId: %x", Integer.valueOf(causeId));
            this.l.release();
            this.l = null;
            return;
        }
        FLog.w(RNCallingVideoViewLocalManager.REACT_CLASS, "cleanupSurfaceTexture could not release - was already null causeId: %x", Integer.valueOf(causeId));
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    public void onStateChanged(final int stateWhat, final int width, final int height) {
        post(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewLocal d;

            public final void run() {
                int causeId = RNCallingVideoViewLocal.a.nextInt();
                FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onStateChanged what: %d, width: %d, height: %d causeId: %x", Integer.valueOf(stateWhat), Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
                if (stateWhat == ControlUnit.STATE_PREVIEW_RESOLUTION_CHANGED) {
                    this.d.a(width, height, causeId);
                }
                if (this.d.d != null) {
                    this.d.a(this.d.d, causeId);
                }
            }
        });
    }

    private static CameraFacing d(int index) {
        if (index == 1) {
            return CameraFacing.FRONT;
        }
        if (index == 2) {
            return CameraFacing.BACK;
        }
        return CameraFacing.NONE;
    }

    public void onHostResume() {
        int causeId = a.nextInt();
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onHostResume causeId: %x", Integer.valueOf(causeId));
        if (this.d != null) {
            if (!(this.l == null || this.d.getSurfaceTexture() == this.l)) {
                this.d.setSurfaceTexture(this.l);
            }
            a(this.d, causeId);
            return;
        }
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onHostResume - no scale");
    }

    public void onHostPause() {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onHostPause");
    }

    public void onHostDestroy() {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onHostDestroy");
    }

    public void setBorderRadius(float borderRadius, int causeId) {
        FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "setBorderRadius %f causeId: %x", Float.valueOf(borderRadius), Integer.valueOf(causeId));
        b().a((ViewGroup) this, borderRadius);
        this.p = borderRadius;
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.n != null) {
            b().a((ViewGroup) this, color);
        }
    }

    private RNBackgroundDrawable b() {
        if (this.n == null) {
            this.n = new RNBackgroundDrawable();
        }
        return this.n;
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        if (this.p > 0.0f) {
            this.o.reset();
            this.o.addRoundRect(new RectF(canvas.getClipBounds()), this.p, this.p, Direction.CW);
            canvas.clipPath(this.o);
        }
        super.onDraw(canvas);
    }

    public final void b(int causeId) {
        if (this.d != null) {
            a(this.d, causeId);
        } else {
            FLog.i(RNCallingVideoViewLocalManager.REACT_CLASS, "onOrientationChanged view is null causeId: %x", Integer.valueOf(causeId));
        }
    }
}
