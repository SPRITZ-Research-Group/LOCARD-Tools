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
import com.skype.SkyLib;
import com.skype.VideoImpl;
import com.skype.android.video.DeviceProfile;
import com.skype.slimcore.skylib.SkyLibManager;
import com.skype.slimcore.skylib.SkyLibManager.SkyLibExecution;
import com.skype.slimcore.video.IPreviewRenderer.Callback;
import com.skype.slimcore.video.VideoOrientationManager.OrientationChangedCallback;
import java.util.Random;

public class RNCallingVideoViewPreview extends FrameLayout implements SurfaceTextureListener, v, Callback, OrientationChangedCallback {
    private static final Random g = new Random();
    private static Point h;
    private static boolean i = false;
    protected TextureView a;
    protected boolean b = false;
    protected boolean c = false;
    protected int d;
    protected SurfaceTexture e;
    protected final Object f = new Object();
    private boolean j = false;
    private CameraFacing k = CameraFacing.NONE;
    private ag l;
    private RNBackgroundDrawable m;
    private Path n = new Path();
    private float o = 0.0f;
    private Point p = new Point(0, 0);
    private PreviewRenderer q;
    private VideoImpl r;
    private VideoOrientationManager s;

    public RNCallingVideoViewPreview(Context context, VideoOrientationManager videoOrientationManager) {
        super(context);
        setClipChildren(true);
        if (VERSION.SDK_INT >= 21) {
            setClipToOutline(true);
        }
        if (h == null) {
            DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
            h = new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "Initialized videoDimensions are %d x %d", Integer.valueOf(h.x), Integer.valueOf(h.y));
        }
        this.q = new PreviewRenderer(this);
        this.s = videoOrientationManager;
    }

    public final void a(ag reactContext, SkyLibManager skyLibManager, final int videoObjectId, int videoCameraPosition, boolean fit, int causeId) {
        this.k = d(videoCameraPosition);
        this.c = fit;
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "attachVideo, videoObjectId: %d camera: %s fit: %b causeId: %x", Integer.valueOf(videoObjectId), this.k, Boolean.valueOf(fit), Integer.valueOf(causeId));
        this.l = reactContext;
        this.d = videoObjectId;
        this.l.a((v) this);
        this.s.a(videoObjectId, (OrientationChangedCallback) this);
        this.s.a();
        TextureView textureView = new TextureView(getContext());
        textureView.setSurfaceTextureListener(this);
        this.a = textureView;
        textureView.setVisibility(0);
        textureView.layout(0, 0, getRight(), getBottom());
        addView(textureView);
        a(this.a, causeId);
        skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNCallingVideoViewPreview b;

            public final void a(SkyLib skyLib) {
                this.b.r = new VideoImpl();
                if (!skyLib.getVideo(videoObjectId, this.b.r)) {
                    FLog.e(RNCallingVideoViewPreviewManager.REACT_CLASS, "Failed to acquire preview videoObjectId: %d", Integer.valueOf(videoObjectId));
                } else if (!this.b.q.a(this.b.r)) {
                    FLog.e(RNCallingVideoViewPreviewManager.REACT_CLASS, "Failed to start preview videoObjectId: %d", Integer.valueOf(videoObjectId));
                }
            }
        });
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "attachVideo default videoDimensions %d x %d causeId: %x", Integer.valueOf(h.x), Integer.valueOf(h.y), Integer.valueOf(causeId));
    }

    public final void a(boolean fit, int causeId) {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "updateAspectRatio, fit: %b w: %d h: %d causeId: %x", Boolean.valueOf(fit), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(causeId));
        this.c = fit;
        a(this.a, causeId);
    }

    public final void a(RNCallingVideoViewPreview otherView, int videoObjectId, int videoCameraPosition, boolean fit, int causeId) {
        a.a(videoObjectId == otherView.d, "reparentVideo videoObjectIds must match (" + videoObjectId + "," + otherView.d + ") causeId: " + causeId);
        this.k = d(videoCameraPosition);
        this.c = fit;
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "reparentVideo, videoObjectId: %d camera: %s fit: %b w: %d h: %d causeId: %x", Integer.valueOf(videoObjectId), this.k, Boolean.valueOf(fit), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(causeId));
        otherView.s.a(this.d);
        this.l = otherView.l;
        this.d = videoObjectId;
        this.r = otherView.r;
        this.q = otherView.q;
        this.s = otherView.s;
        this.a = otherView.a;
        otherView.removeView(this.a);
        this.a.setSurfaceTextureListener(this);
        this.a.layout(0, 0, getRight(), getBottom());
        addView(this.a);
        this.l.b((v) otherView);
        this.l.a((v) this);
        this.s.a(videoObjectId, (OrientationChangedCallback) this);
        a(this.a, causeId);
        if (this.a.isAvailable()) {
            ar map = new WritableNativeMap();
            map.putInt("id", videoObjectId);
            map.putString("action", "attached");
            map.putBoolean("success", true);
        }
    }

    public final void a(int causeId) {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "detachVideo causeId: %x", Integer.valueOf(causeId));
        this.j = true;
        this.l.b((v) this);
        this.s.a(this.d);
        if (!this.q.b(this.r)) {
            FLog.e(RNCallingVideoViewPreviewManager.REACT_CLASS, "Failed to stop preview videoObjectId: %d", Integer.valueOf(this.d));
        }
        if (this.a != null) {
            removeView(this.a);
        }
        synchronized (this.f) {
            if (this.b) {
                FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "detachVideo called when surface was already destroyed; cleaning up causeId: %x", Integer.valueOf(causeId));
                c(causeId);
            }
        }
    }

    protected final void a(TextureView view, int causeId) {
        Point adjustedVideoDimensions;
        if (this.a == null) {
            adjustedVideoDimensions = h;
        } else {
            int c = this.s.c();
            if (i) {
                if (DeviceProfile.changePreviewDimensions(this.k == CameraFacing.FRONT ? 1 : 0, c)) {
                    FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay rotating for camera -> %d x %d causeId: %x", Integer.valueOf(h.y), Integer.valueOf(h.x), Integer.valueOf(causeId));
                    adjustedVideoDimensions = new Point(h.y, h.x);
                } else {
                    FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay no change -> %d x %d causeId: %x", Integer.valueOf(h.x), Integer.valueOf(h.y), Integer.valueOf(causeId));
                    adjustedVideoDimensions = h;
                }
            } else if (c == 90 || c == 270) {
                FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay landscape -> %d x %d causeId: %x", Integer.valueOf(h.x), Integer.valueOf(h.y), Integer.valueOf(causeId));
                adjustedVideoDimensions = new Point(h.y, h.x);
            } else {
                FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "adjustedVideoDimensionsForDisplay default -> %d x %d causeId: %x", Integer.valueOf(h.x), Integer.valueOf(h.y), Integer.valueOf(causeId));
                adjustedVideoDimensions = h;
            }
        }
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "scaleView width: %d height: %d displayWidth: %d displayHeight: %d scaleToFit: %b panOffset: %s causeId: %x ", Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(adjustedVideoDimensions.x), Integer.valueOf(adjustedVideoDimensions.y), Boolean.valueOf(this.c), this.p, Integer.valueOf(causeId));
        ViewScaleHelper.a(view, getWidth(), getHeight(), adjustedVideoDimensions.x, adjustedVideoDimensions.y, this.c, this.p);
    }

    private void a(int width, int height, int causeId) {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "handleVideoSizeChanged, w: %d h: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
        if (this.a != null) {
            h.set(width, height);
            i = true;
            a(this.c, causeId);
            ar writableNativeMap = new WritableNativeMap();
            writableNativeMap.putInt("id", this.d);
            writableNativeMap.putInt("width", width);
            writableNativeMap.putInt("height", height);
            ((RCTNativeAppEventEmitter) this.l.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoSizeChangedEvent", writableNativeMap);
        }
    }

    protected void onAttachedToWindow() {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onAttachedToWindow");
        if (!(this.a == null || this.e == null || this.a.getSurfaceTexture() == this.e)) {
            this.a.setSurfaceTexture(this.e);
        }
        super.onAttachedToWindow();
    }

    public final void b(int causeId) {
        if (this.a != null) {
            a(this.a, causeId);
        } else {
            FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onOrientationChanged view is null causeId: %x", Integer.valueOf(causeId));
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        synchronized (this.f) {
            int causeId = g.nextInt();
            FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onSurfaceTextureAvailable, w: %d h: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
            if (this.e == null) {
                this.e = surface;
                this.q.a(surface);
                a(this.a, causeId);
                ar map = new WritableNativeMap();
                map.putInt("id", this.d);
                map.putString("action", "attached");
                map.putBoolean("success", true);
                ((RCTNativeAppEventEmitter) this.l.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
            }
            this.b = false;
            if (h != null) {
                a(h.x, h.y, causeId);
            }
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        int causeId = g.nextInt();
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onSurfaceTextureSizeChanged w: %d h: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
        a(this.a, causeId);
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        synchronized (this.f) {
            int causeId = g.nextInt();
            FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onSurfaceTextureDestroyed causeId: %x", Integer.valueOf(causeId));
            if (this.j) {
                this.q.b(surface);
                c(causeId);
            } else {
                FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "Received onSurfaceDestroyed while isDetachingVideo was false; setting isSurfaceDestroyedButNotCleanedUp to true causeId: %x", Integer.valueOf(causeId));
                this.b = true;
            }
        }
        return false;
    }

    private void c(int causeId) {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "cleanupSurfaceTexture causeId: %x", Integer.valueOf(causeId));
        ar map = new WritableNativeMap();
        map.putInt("id", this.d);
        map.putString("action", "detached");
        map.putBoolean("success", true);
        ((RCTNativeAppEventEmitter) this.l.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
        this.a.setSurfaceTextureListener(null);
        this.j = false;
        this.b = false;
        if (this.e != null) {
            FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "cleanupSurfaceTexture release causeId: %x", Integer.valueOf(causeId));
            this.e.release();
            this.e = null;
            return;
        }
        FLog.w(RNCallingVideoViewPreviewManager.REACT_CLASS, "cleanupSurfaceTexture could not release - was already null causeId: %x", Integer.valueOf(causeId));
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
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
        int causeId = g.nextInt();
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onHostResume causeId: %x", Integer.valueOf(causeId));
        if (this.a != null) {
            if (!(this.e == null || this.a.getSurfaceTexture() == this.e)) {
                this.a.setSurfaceTexture(this.e);
            }
            a(this.a, causeId);
            return;
        }
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onHostResume - no scale");
    }

    public void onHostPause() {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onHostPause");
    }

    public void onHostDestroy() {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onHostDestroy");
    }

    public void setBorderRadius(float borderRadius, int causeId) {
        FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "setBorderRadius %f causeId: %x", Float.valueOf(borderRadius), Integer.valueOf(causeId));
        b().a((ViewGroup) this, borderRadius);
        this.o = borderRadius;
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.m != null) {
            b().a((ViewGroup) this, color);
        }
    }

    private RNBackgroundDrawable b() {
        if (this.m == null) {
            this.m = new RNBackgroundDrawable();
        }
        return this.m;
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        if (this.o > 0.0f) {
            this.n.reset();
            this.n.addRoundRect(new RectF(canvas.getClipBounds()), this.o, this.o, Direction.CW);
            canvas.clipPath(this.n);
        }
        super.onDraw(canvas);
    }

    public final void a(final int width, final int height) {
        post(new Runnable(this) {
            final /* synthetic */ RNCallingVideoViewPreview c;

            public final void run() {
                int causeId = RNCallingVideoViewPreview.g.nextInt();
                FLog.i(RNCallingVideoViewPreviewManager.REACT_CLASS, "onPreviewSizeChanged width: %d, height: %d causeId: %x", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(causeId));
                this.c.a(width, height, causeId);
                if (this.c.a != null) {
                    this.c.a(this.c.a, causeId);
                }
            }
        });
    }
}
