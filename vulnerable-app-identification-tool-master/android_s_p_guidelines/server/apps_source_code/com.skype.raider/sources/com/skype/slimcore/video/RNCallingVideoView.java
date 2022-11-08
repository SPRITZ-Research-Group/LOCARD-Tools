package com.skype.slimcore.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.ar;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.skype.SkyLib;
import com.skype.VideoImpl;
import com.skype.android.video.render.GLTextureView;
import com.skype.slimcore.skylib.SkyLibManager;
import com.skype.slimcore.skylib.SkyLibManager.SkyLibExecution;
import com.skype.slimcore.video.RNGLTextureView.RNSurfaceTextureAvailableListener;
import com.skype.slimcore.video.VideoRenderer.Callback;
import java.util.Locale;

public class RNCallingVideoView extends FrameLayout implements RNSurfaceTextureAvailableListener {
    private int a;
    private VideoRenderer b;
    private boolean c;
    private VideoImpl d;
    private Runnable e;
    private boolean f;
    private int g;
    private int h;
    private ag i;
    private RNBackgroundDrawable j;
    private Path k = new Path();
    private float l = 0.0f;
    private Point m = new Point(0, 0);
    private float n;
    private GLTextureView o;

    class a implements Callback {
        final /* synthetic */ RNCallingVideoView a;
        private ag b;

        a(RNCallingVideoView this$0, ag reactApplicationContext) {
            this.a = this$0;
            this.b = reactApplicationContext;
        }

        public final void a() {
            FLog.i(this.a.c(), "onBindingAttached");
            com.microsoft.skype.a.a.a.c(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    ar map = new WritableNativeMap();
                    map.putInt("id", this.a.a.a);
                    map.putString("action", "attached");
                    map.putBoolean("success", true);
                    ((RCTNativeAppEventEmitter) this.a.b.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
                }
            });
        }

        public final void b() {
            FLog.i(this.a.c(), "onBindingDetached");
            com.microsoft.skype.a.a.a.c(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    ar map = new WritableNativeMap();
                    map.putInt("id", this.a.a.a);
                    map.putString("action", "detached");
                    map.putBoolean("success", true);
                    ((RCTNativeAppEventEmitter) this.a.b.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
                    RNCallingVideoView.d(this.a.a);
                }
            });
        }

        public final void c() {
            FLog.i(this.a.c(), "onBindingFailed");
            com.microsoft.skype.a.a.a.c(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    ar map = new WritableNativeMap();
                    map.putInt("id", this.a.a.a);
                    map.putString("action", this.a.a.c ? "attached" : "detached");
                    map.putBoolean("success", false);
                    ((RCTNativeAppEventEmitter) this.a.b.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoBindingChangedEvent", map);
                    FLog.w(this.a.a.c(), "Binding failed for videoObjectId: %d", Integer.valueOf(this.a.a.a));
                }
            });
        }

        public final void a(final int w, final int h) {
            com.microsoft.skype.a.a.a.c(new Runnable(this) {
                final /* synthetic */ a c;

                public final void run() {
                    this.c.a.g = w;
                    this.c.a.h = h;
                    FLog.i(this.c.a.c(), "onSizeChanged, videoSize: %d x %d viewSize: %d x %d videoObjectId: %d", Integer.valueOf(w), Integer.valueOf(h), Integer.valueOf(this.c.a.getWidth()), Integer.valueOf(this.c.a.getHeight()), Integer.valueOf(this.c.a.a));
                    ar map = new WritableNativeMap();
                    map.putInt("id", this.c.a.a);
                    map.putInt("width", w);
                    map.putInt("height", h);
                    ((RCTNativeAppEventEmitter) this.c.b.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoSizeChangedEvent", map);
                    this.c.a.a(this.c.a.f);
                }
            });
        }

        public final void d() {
            com.microsoft.skype.a.a.a.c(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = this$1;
                }

                public final void run() {
                    FLog.i(this.a.a.c(), "onFirstFrameRendered, videoObjectId: %d", Integer.valueOf(this.a.a.a));
                    ar map = new WritableNativeMap();
                    map.putInt("id", this.a.a.a);
                    ((RCTNativeAppEventEmitter) this.a.b.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoFirstFrameRenderedEvent", map);
                }
            });
        }

        public final void e() {
            FLog.i(this.a.c(), "onRendererDisposed, videoObjectId: %d", Integer.valueOf(this.a.a));
        }
    }

    static /* synthetic */ void a(RNCallingVideoView x0, VideoImpl x1) {
        x0.d = x1;
        if (x0.b == null || !x0.b.a(x1)) {
            FLog.e(x0.c(), "Failed to start video videoObjectId: %d", Integer.valueOf(x0.a));
            return;
        }
        x0.c = true;
    }

    static /* synthetic */ void d(RNCallingVideoView x0) {
        FLog.i(x0.c(), "cleanupVideo");
        if (x0.e != null) {
            x0.e.run();
        }
        if (x0.b != null) {
            x0.b.b();
        }
        x0.b = null;
        x0.d = null;
        x0.a = 0;
        x0.c = false;
    }

    public RNCallingVideoView(Context context) {
        super(context);
        a(context);
    }

    public RNCallingVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        a(context);
    }

    public RNCallingVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        a(context);
    }

    private void a(Context context) {
        this.n = context.getResources().getDisplayMetrics().density;
    }

    private String c() {
        return String.format(Locale.US, "%s[%d:%b:%b]", new Object[]{RNCallingVideoViewManager.REACT_CLASS, Integer.valueOf(this.a), Boolean.valueOf(this.c), Boolean.valueOf(this.f)});
    }

    public final void a(ag reactContext, SkyLibManager skyLibManager, final int videoObjectId, boolean fit, Runnable onCleanupVideo) {
        FLog.i(c(), "attachVideo, videoObjectId: %d fit: %b", Integer.valueOf(videoObjectId), Boolean.valueOf(fit));
        this.a = videoObjectId;
        this.f = fit;
        this.e = onCleanupVideo;
        this.i = reactContext;
        this.b = new VideoRenderer(new a(this, reactContext), videoObjectId);
        a(this.f);
        GLTextureView videoView = this.b.a(getContext(), this);
        videoView.setVisibility(0);
        addView(videoView);
        videoView.layout(0, 0, getWidth(), getHeight());
        this.o = videoView;
        skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNCallingVideoView b;

            public final void a(SkyLib skyLib) {
                final VideoImpl myVideo = new VideoImpl();
                if (skyLib.getVideo(videoObjectId, myVideo)) {
                    com.microsoft.skype.a.a.a.b(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public final void run() {
                            RNCallingVideoView.a(this.b.b, myVideo);
                        }
                    });
                    return;
                }
                FLog.e(this.b.c(), "Failed to acquire video videoObjectId: %d", Integer.valueOf(videoObjectId));
            }
        });
    }

    public final void a(boolean fit) {
        FLog.i(c(), "updateAspectRatio, fit: %b w: %d h: %d", Boolean.valueOf(fit), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
        if (this.f != fit) {
            this.m = new Point(0, 0);
        }
        this.f = fit;
        e();
    }

    public final void a(double x, double y) {
        Point point = this.m;
        point.x = (int) (((double) point.x) + (((double) this.n) * x));
        point = this.m;
        point.y = (int) (((double) point.y) + (((double) this.n) * y));
        e();
    }

    public final void a() {
        this.m.x = 0;
        this.m.y = 0;
        this.f = false;
        e();
    }

    public final boolean a(RNCallingVideoView otherView, ag reactApplicationContext, boolean fit) {
        FLog.i(c(), "reparentVideo, videoObjectId: %d w: %d h: %d", Integer.valueOf(otherView.a), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
        this.a = otherView.a;
        this.d = otherView.d;
        this.f = fit;
        this.i = reactApplicationContext;
        this.c = otherView.c;
        this.g = otherView.g;
        this.h = otherView.h;
        this.e = otherView.e;
        otherView.e = null;
        this.b = otherView.b;
        otherView.b = null;
        if (this.b == null) {
            FLog.w(c(), "reparentVideo, videoRenderer is null");
            return false;
        }
        RNGLTextureView innerView = this.b.a();
        if (innerView == null) {
            FLog.w(c(), "reparentVideo got null RNGLTextureView");
        } else {
            innerView.setRNSurfaceTextureAvailableListener(this);
            otherView.removeView(innerView);
            addView(innerView);
            this.o = innerView;
            innerView.layout(0, 0, getWidth(), getHeight());
            this.b.a(new a(this, reactApplicationContext));
        }
        e();
        return true;
    }

    public final void a(SkyLibManager skyLibManager) {
        FLog.i(c(), "detachVideo");
        skyLibManager.a(new SkyLibExecution(this) {
            final /* synthetic */ RNCallingVideoView a;

            {
                this.a = this$0;
            }

            public final void a(SkyLib skyLib) {
                FLog.i(this.a.c(), "detachVideo - executeWithSkyLib");
                com.microsoft.skype.a.a.a.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        RNCallingVideoView.b(this.a.a);
                    }
                });
            }
        });
    }

    public final Bitmap a(int causeId) {
        if (this.b != null) {
            return this.b.a(causeId);
        }
        FLog.e(RNCallingVideoViewManager.REACT_CLASS, "captureFrame: no video renderer, causeId %x", Integer.valueOf(causeId));
        return null;
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        FLog.i(c(), "onLayout scaleView, w: %d h: %d", Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
        e();
    }

    public void setBorderRadius(float borderRadius) {
        d().a((ViewGroup) this, borderRadius);
        this.l = borderRadius;
    }

    public void setBackgroundColor(int color) {
        if (color != 0 || this.j != null) {
            d().a((ViewGroup) this, color);
        }
    }

    private RNBackgroundDrawable d() {
        if (this.j == null) {
            this.j = new RNBackgroundDrawable();
        }
        return this.j;
    }

    private void e() {
        if (this.b != null) {
            ViewScaleHelper.a(this.b.a(), getWidth(), getHeight(), this.g, this.h, this.f, this.m);
        }
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        if (this.l > 0.0f) {
            this.k.reset();
            this.k.addRoundRect(new RectF(canvas.getClipBounds()), this.l, this.l, Direction.CW);
            canvas.clipPath(this.k);
        }
        super.onDraw(canvas);
    }

    public final void b() {
        FLog.i(c(), "onSurfaceTextureAvailable, w: %d h: %d", Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
        e();
        getWidth();
        getHeight();
        if (this.i != null) {
            ar writableNativeMap = new WritableNativeMap();
            writableNativeMap.putInt("id", this.a);
            writableNativeMap.putInt("width", this.g);
            writableNativeMap.putInt("height", this.h);
            FLog.i(c(), "sendSizeChangedEvent, videoSize: %d x %d videoObjectId: %d", Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.a));
            ((RCTNativeAppEventEmitter) this.i.a(RCTNativeAppEventEmitter.class)).emit("RNSlimcore-VideoSizeChangedEvent", writableNativeMap);
        }
    }

    static /* synthetic */ void b(RNCallingVideoView x0) {
        FLog.i(x0.c(), "intDetachVideo");
        if (x0.c && x0.b != null && !x0.b.b(x0.d)) {
            FLog.e(x0.c(), "Failed to stop video videoObjectId: %d", Integer.valueOf(x0.a));
        }
    }
}
