package com.skypecam.obscura.b;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.TextureView.SurfaceTextureListener;
import android.view.WindowManager;
import com.adjust.sdk.Constants;
import com.skype4life.c.a.a.c;
import com.skypecam.obscura.b.a.b;
import com.skypecam.obscura.c.j;
import com.skypecam.obscura.c.k;
import com.skypecam.obscura.e.d;
import com.skypecam.obscura.e.e;
import com.skypecam.obscura.e.f;
import com.skypecam.obscura.e.g;
import com.skypecam.obscura.e.i;
import com.skypecam.obscura.view.CameraView;
import com.skypecam.obscura.view.CameraViewFinder;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class h extends a<d, g> implements OnFrameAvailableListener, ErrorCallback, PreviewCallback, SurfaceTextureListener {
    public static k l = new k() {
        public final ac a(OnFrameAvailableListener listener) {
            g.a().b("CameraStateMachine", "SurfaceApparatusFactory");
            return new ac(listener);
        }
    };
    private static final t<h> p;
    private static final b q = new b() {
        public final boolean a(Object candidate) {
            return candidate instanceof SurfaceTexture;
        }
    };
    private static final b r = new b() {
        public final boolean a(Object candidate) {
            return candidate instanceof RectF;
        }
    };
    private static final b s = new b() {
        public final boolean a(Object candidate) {
            return candidate instanceof i;
        }
    };
    private RectF A = i.a;
    private final com.skypecam.obscura.b.a.a<SurfaceTexture> B;
    private com.skype4life.c.a.a C;
    private u D;
    private final ac E;
    private Camera F;
    private r G;
    private final z H;
    private final q I = new q(this) {
        final /* synthetic */ h a;

        {
            this.a = this$0;
        }

        public final boolean a(Object feedback) {
            if (feedback instanceof y) {
                if (feedback == y.RUNNING) {
                    h.a(this.a, true);
                    this.a.E.a(this.a.H);
                } else {
                    this.a.E.b(this.a.H);
                    if (feedback == y.CONFIGURED || feedback == y.COMPLETED) {
                        h.a(this.a, false);
                    }
                }
            }
            return false;
        }

        public final boolean b(Object feedback) {
            return a(feedback);
        }
    };
    public s<d> m = new s<d>(this) {
        final /* synthetic */ h a;

        {
            this.a = this$0;
        }

        public final /* bridge */ /* synthetic */ void a(Object obj) {
            this.a.a((Object) (d) obj);
        }

        public final boolean a() {
            return false;
        }
    };
    private com.skypecam.obscura.d.a n;
    private int o = -1;
    private final Map<i, f> t = new EnumMap(i.class);
    private final AtomicReference<CameraView> u = new AtomicReference();
    private final AtomicReference<s<d>> v = new AtomicReference(null);
    private j w;
    private final com.skypecam.obscura.b.a.a<i> x;
    private final com.skypecam.obscura.b.a.a<RectF> y;
    private final e z;

    /* renamed from: com.skypecam.obscura.b.h$20 */
    static /* synthetic */ class AnonymousClass20 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.PORTRAIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.LANDSCAPE_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.PORTRAIT_UPSIDE_DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.LANDSCAPE_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private class a implements q {
        final /* synthetic */ h a;
        private final com.skypecam.obscura.e.a<e> b;
        private final com.skypecam.obscura.e.a<Throwable> c;
        private final com.skypecam.obscura.e.b<Boolean, CameraView> d;
        private boolean e = false;

        a(h hVar, com.skypecam.obscura.e.b<Boolean, CameraView> change, com.skypecam.obscura.e.a<e> success, com.skypecam.obscura.e.a<Throwable> failure) {
            this.a = hVar;
            this.d = change;
            this.b = success;
            this.c = failure;
        }

        public final boolean a(Object feedback) {
            g.a().b("CameraStateMachine", "RecordingProgress feedback " + feedback.getClass());
            if (feedback instanceof y) {
                g.a().b("CameraStateMachine", "RecordingProgress feedback:" + feedback + " recorded:" + this.e);
                if (feedback == y.RUNNING) {
                    if (!this.e) {
                        this.a.v.set(this.a.H.j());
                        this.d.a(Boolean.TRUE, this.a.u.get());
                    }
                    this.e = true;
                } else if (this.e || feedback == y.IDLE) {
                    if (this.e) {
                        this.d.a(Boolean.FALSE, this.a.u.get());
                    }
                    this.a.b(d.FIRE, false);
                    this.e = false;
                    if (feedback == y.IDLE) {
                        this.a.b(com.skypecam.obscura.d.b.RECORDING_WRONG_STATE);
                        this.c.a(new Throwable());
                        return true;
                    }
                }
            }
            if (!(feedback instanceof e)) {
                return false;
            }
            this.a.b(d.FIRE, false);
            g.a().b("CameraStateMachine", "RecordingProgress feedback:" + feedback);
            e myFeedback = (e) feedback;
            if (myFeedback.b()) {
                this.b.a(myFeedback);
                return true;
            }
            this.a.b(com.skypecam.obscura.d.b.RECORDING_INVALID_FORMAT);
            this.c.a(new Throwable());
            return true;
        }

        public final boolean b(Object feedback) {
            return false;
        }
    }

    static /* synthetic */ void b(h x0, Context x1) {
        boolean z = true;
        if (VERSION.SDK_INT >= 23 && x1.checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            z = false;
        }
        x0.H.b(x.MIC, z);
    }

    static /* synthetic */ void l(h x0) {
        g.a().b("CameraStateMachine", "setFocusMode (causeId 0)");
        Parameters a = x0.a(x0.F, "setFocusMode");
        if (a == null) {
            x0.a(d.PANIC, true);
            return;
        }
        List supportedFocusModes = a.getSupportedFocusModes();
        g.a().b("CameraStateMachine", "camera focus modes: " + supportedFocusModes.toString());
        if (!supportedFocusModes.contains("auto")) {
            g.a().c("CameraStateMachine", "camera not auto-focusable (causeId 0)");
            x0.b(com.skypecam.obscura.d.b.FOCUS);
            x0.b(d.FOCUS, true);
        }
        if (supportedFocusModes.contains("continuous-picture")) {
            a.setFocusMode("continuous-picture");
            x0.a(x0.F, a, "setFocusMode");
            g.a().b("CameraStateMachine", "setting focus mode to continuous picture (causeId 0)");
            return;
        }
        g.a().b("CameraStateMachine", "could not set focus to continuous picture (causeId 0)");
    }

    static /* synthetic */ void m(h x0) {
        g.a().b("CameraStateMachine", "adjustPreviewLayout (causeId 0)");
        Parameters a = x0.a(x0.F, "adjustPreviewLayout");
        if (a != null) {
            f fVar = (f) x0.t.get(x0.x.a());
            boolean z = fVar.b.facing == 1;
            int i = fVar.b.orientation;
            fVar.a(i.a(i, z));
            g.a().b("CameraStateMachine", "adjusting preview layout isFront:" + (z ? "T" : "F") + " orientation:" + i + " rotation:" + fVar.a() + " (causeId 0)");
            int a2 = fVar.a();
            if (fVar.b.facing == 1) {
                a2 = 360 - a2;
            }
            a2 %= 360;
            if (a2 == 0 || a2 == 90 || a2 == 180 || a2 == 270) {
                x0.F.setDisplayOrientation(a2);
                Size a3 = i.a(a.getSupportedPreviewSizes());
                if (a3 == null) {
                    g.a().c("CameraStateMachine", "adjustPreviewLayout optimalPreviewSize null (causeId 0)");
                    x0.b(com.skypecam.obscura.d.b.INVALID_PREVIEW_DIMENSIONS);
                    return;
                }
                int i2 = a3.width;
                a2 = a3.height;
                g.a().b("CameraStateMachine", "preview size " + i2 + " x " + a2 + " (causeId 0)");
                a.setPreviewSize(i2, a2);
                x0.a(x0.F, a, "adjustPreviewLayout");
                fVar.a(i2, a2);
                x0.E.a(fVar.b().a(), fVar.b().b());
                return;
            }
            x0.b(com.skypecam.obscura.d.b.INVALID_PREVIEW_DIMENSIONS);
            throw new RuntimeException("Display rotation was not a valid value : " + a2 + " (causeId 0)");
        }
    }

    static {
        t anonymousClass23 = new t<h>("CameraStateMachine") {
            protected final /* synthetic */ Handler a() throws b {
                return new h(new m(), new n(), new o());
            }
        };
        p = anonymousClass23;
        anonymousClass23.start();
    }

    public h(e cameraFactory, w recorderFactory, v playFactory) throws b {
        super("CameraStateMachine", g.class, d.class, new d[0]);
        g.a().b("CameraStateMachine", "CameraStateMachine " + cameraFactory.getClass().getName());
        this.z = cameraFactory;
        this.E = l.a(this);
        f();
        this.x = new com.skypecam.obscura.b.a.a(this, d.FACING, s);
        this.y = new com.skypecam.obscura.b.a.a(this, d.TOUCH_AREA, r);
        this.B = new com.skypecam.obscura.b.a.a<SurfaceTexture>(this, d.SURFACE_TEXTURE, q) {
            final /* synthetic */ h e;

            public final /* synthetic */ void a(Object obj) {
                SurfaceTexture surfaceTexture = (SurfaceTexture) obj;
                CameraViewFinder a = ((CameraView) this.e.u.get()).a();
                ac d = this.e.E;
                int width = a.getWidth();
                int height = a.getHeight();
                g.a().b("CameraSurfaceApparatus", "configure " + width + " " + height);
                if (surfaceTexture == null) {
                    g.a().c("CameraSurfaceApparatus", "createTexture: null");
                } else {
                    d.a(new a(d, surfaceTexture, width, height));
                }
            }

            public final /* synthetic */ void b(Object obj) {
                SurfaceTexture surfaceTexture = (SurfaceTexture) obj;
                g.a().b("CameraStateMachine", "Equipment surfaceTexture release");
                surfaceTexture.release();
                this.e.E.b();
            }
        };
        this.H = recorderFactory.a();
        this.w = new j(playFactory);
        a((Object) i.BACK, true);
    }

    private void f() {
        int count = Camera.getNumberOfCameras();
        int i = 0;
        while (i < count) {
            CameraInfo info = new CameraInfo();
            try {
                Camera.getCameraInfo(i, info);
                if (info.facing != 1 || this.t.containsKey(i.FRONT)) {
                    if (info.facing == 0 && !this.t.containsKey(i.BACK)) {
                        this.t.put(i.BACK, new f(info, i));
                    }
                    i++;
                } else {
                    this.t.put(i.FRONT, new f(info, i));
                    i++;
                }
            } catch (RuntimeException e) {
                b(com.skypecam.obscura.d.b.CAMERA_NO_HARDWARE);
                g.a().d("CameraStateMachine", "Could not getCameraInfo, exception: " + e.getLocalizedMessage());
            }
        }
    }

    public final void a(CameraView view) {
        CameraView oldView = (CameraView) this.u.getAndSet(view);
        g.a().b("CameraStateMachine", "setView " + System.identityHashCode(view) + " <- " + System.identityHashCode(oldView));
        if (view == null) {
            a(d.VIEW, false);
            return;
        }
        b(d.VIEW, true);
        view.a(oldView);
        view.a().setSurfaceTextureListener(this);
        if (view.a().isAvailable()) {
            a((Object) view.a().getSurfaceTexture(), true);
        }
    }

    public final void a(r mode) {
        this.G = mode;
    }

    public final void a(float x, float y) {
        CameraView currentView = (CameraView) this.u.get();
        if (currentView != null) {
            g.a().b("CameraStateMachine", "setTouchPoint " + x + "x" + y + " (view " + currentView.getWidth() + "x" + currentView.getHeight() + ")");
            this.A = i.a(x, y, currentView.getWidth(), currentView.getHeight());
            a((Object) this.A, true);
        }
    }

    public final void a(com.skypecam.obscura.e.a<d> success, final com.skypecam.obscura.e.a<Throwable> failureBlock) {
        b(this.G == r.ON ? d.FLAME : d.FIRE, true);
        a((Object) this.A, true);
        final j captureStill = new j(success, new com.skypecam.obscura.e.b<Throwable, com.skypecam.obscura.d.b>(this) {
            final /* synthetic */ h b;

            public final /* synthetic */ void a(Object obj, Object obj2) {
                Throwable th = (Throwable) obj;
                this.b.b((com.skypecam.obscura.d.b) obj2);
                failureBlock.a(th);
            }
        }, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.b(d.FIRE, false);
                this.a.b(d.FLAME, false);
                this.a.a((Object) this.a.A, false);
            }
        }, f.a());
        a((q) captureStill);
        postDelayed(new Runnable(this) {
            final /* synthetic */ h b;

            public final void run() {
                this.b.a((Object) captureStill.a);
            }
        }, 5000);
    }

    public final void a(com.skypecam.obscura.e.b<Boolean, CameraView> change, com.skypecam.obscura.e.a<e> success, com.skypecam.obscura.e.a<Throwable> failure) {
        b(this.G == r.ON ? d.FLAME : d.FIRE, true);
        z zVar = this.H;
        q aVar = new a(this, change, success, failure);
        zVar.b(x.RECORD, true);
        zVar.a(aVar);
    }

    public final void a() {
        this.H.e();
    }

    public final void d() {
        this.H.b(x.RECORD, false);
    }

    final void a(com.skypecam.obscura.d.b failure) {
        b(failure);
        b(d.PANIC, true);
    }

    public final void a(com.skypecam.obscura.d.a callback) {
        this.n = callback;
    }

    public final void a(float zoom) {
        this.E.a(zoom);
    }

    public final void a(com.skypecam.obscura.c.j.a qrListener) {
        this.w.a(qrListener);
    }

    public final void a(int maxRecordingMs) {
        this.H.b(maxRecordingMs);
    }

    public final void b(int rate) {
        this.H.a(rate);
    }

    public final void b(float dimension) {
        this.H.a(dimension);
    }

    public final void c(int profile) {
        this.H.e(profile);
    }

    public final void d(int dimensionShort) {
        this.H.c(dimensionShort);
    }

    public final void e(int dimensionLong) {
        this.H.d(dimensionLong);
    }

    public final void a(boolean enable) {
        this.H.b(enable);
    }

    public final void c(float bitrate) {
        this.H.b(bitrate);
    }

    public static h e() {
        return (h) p.b();
    }

    protected final void b() {
        a(EnumSet.of(d.PANIC), g.RELEASED);
        a(EnumSet.of(d.ACTIVITY, new d[]{d.FACING, d.VIEW, d.SURFACE_TEXTURE, d.FIRE, d.TOUCH_AREA, d.FOCUS}), g.CAPTURABLE);
        a(EnumSet.of(d.ACTIVITY, new d[]{d.FACING, d.VIEW, d.SURFACE_TEXTURE, d.FIRE, d.TOUCH_AREA}), g.FOCUSABLE);
        a(EnumSet.of(d.ACTIVITY, d.FACING, d.VIEW, d.SURFACE_TEXTURE, d.FIRE), g.LIT);
        a(EnumSet.of(d.ACTIVITY, d.FACING, d.VIEW, d.SURFACE_TEXTURE, d.FLAME), g.LIGHTABLE);
        a(EnumSet.of(d.ACTIVITY, d.FACING, d.VIEW, d.SURFACE_TEXTURE, d.TOUCH_AREA), g.PREVIEW_FOCUSABLE);
        a(EnumSet.of(d.ACTIVITY, d.FACING, d.VIEW, d.SURFACE_TEXTURE), g.PREVIEWING);
        a(EnumSet.of(d.ACTIVITY, d.FACING, d.VIEW), g.ACQUIRED);
        a(EnumSet.noneOf(d.class), g.RELEASED);
        a(g.class, new g[0]);
    }

    protected final void c() {
        a(g.RELEASED, EnumSet.of(g.PREVIEWING, g.LIGHTABLE, g.LIT, g.FOCUSABLE, g.CAPTURABLE), g.ACQUIRED);
        a(g.RELEASED, g.ACQUIRED, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                try {
                    this.a.F = this.a.z.a(((f) this.a.t.get(this.a.x.a())).a);
                    this.a.F.setErrorCallback(this.a);
                } catch (Exception e) {
                    this.a.b(com.skypecam.obscura.d.b.CAMERA_NO_ACQUIRE);
                    g.a().d("CameraStateMachine", "acquireCameraInstance failed: " + e.getLocalizedMessage());
                    this.a.a(this.a.x.a(), false);
                    this.a.a(d.PANIC, true);
                }
            }
        });
        a(g.ACQUIRED, EnumSet.of(g.LIGHTABLE, g.LIT, g.FOCUSABLE, g.CAPTURABLE), g.PREVIEWING);
        a(g.ACQUIRED, g.PREVIEWING, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                Context context = ((CameraView) this.a.u.get()).getContext();
                this.a.H.a((Object) context.getCacheDir(), true);
                this.a.H.a(this.a.I);
                this.a.E.a();
                h.a(this.a, context);
                h.b(this.a, context);
                this.a.w.a(context);
                try {
                    h.l(this.a);
                    h.m(this.a);
                    this.a.F.setPreviewTexture(this.a.E.c());
                    g.a().b("CameraStateMachine", "start startPreview");
                    this.a.F.startPreview();
                } catch (Exception e) {
                    this.a.b(com.skypecam.obscura.d.b.CAMERA_NO_PREVIEW);
                    g.a().d("CameraStateMachine", e.getLocalizedMessage());
                    this.a.a(this.a.B.a(), false);
                }
            }
        });
        a(g.PREVIEWING, g.CAPTURABLE, g.FOCUSABLE);
        a(g.PREVIEWING, g.FOCUSABLE, g.LIT);
        a(g.PREVIEWING, g.LIT, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.H.a(true);
            }
        });
        a(g.PREVIEWING, g.LIGHTABLE, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                g.a().b("CameraStateMachine", "set flash");
                Parameters parameters = this.a.a(this.a.F, "PREVIEWING->LIGHTABLE");
                if (parameters == null) {
                    this.a.a(d.PANIC, true);
                    return;
                }
                String mode = "torch";
                List<String> flashModes = parameters.getSupportedFlashModes();
                if (flashModes == null || !flashModes.contains(mode)) {
                    g.a().c("CameraStateMachine", "flashMode not available");
                    this.a.b(com.skypecam.obscura.d.b.FLASH);
                    this.a.b(d.FIRE, true);
                    return;
                }
                parameters.setFlashMode(mode);
                this.a.a(this.a.F, parameters, "PREVIEWING->LIGHTABLE");
                h.a(this.a, this.a.G);
            }
        });
        a(g.PREVIEW_FOCUSABLE, EnumSet.of(g.LIGHTABLE, g.LIT, g.FOCUSABLE, g.CAPTURABLE), g.PREVIEWING);
        a(g.PREVIEWING, g.PREVIEW_FOCUSABLE, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                h.p(this.a);
            }
        });
        a(EnumSet.of(g.RELEASED, g.ACQUIRED), g.PREVIEW_FOCUSABLE, g.PREVIEWING);
        a(g.LIGHTABLE, EnumSet.of(g.FOCUSABLE, g.CAPTURABLE), g.LIT);
        a(g.LIGHTABLE, g.LIT, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.H.a(true);
            }
        });
        a(g.LIT, g.CAPTURABLE, g.FOCUSABLE);
        a(g.LIT, g.FOCUSABLE, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.D = new u(this.a, this.a.F, new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        this.a.a.b(d.FOCUS, true);
                    }
                }, new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = this$1;
                    }

                    public final void run() {
                        this.a.a.b(com.skypecam.obscura.d.b.AUTOFOCUS_TIMEOUT);
                    }
                });
                this.a.D.a();
            }
        });
        a(g.FOCUSABLE, g.CAPTURABLE, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.v.set(this.a.m);
            }
        });
        a(g.CAPTURABLE, g.FOCUSABLE, c);
        a(g.CAPTURABLE, EnumSet.of(g.RELEASED, g.ACQUIRED, g.PREVIEWING, g.LIGHTABLE, g.LIT), g.FOCUSABLE);
        a(g.FOCUSABLE, EnumSet.of(g.RELEASED, g.ACQUIRED, g.PREVIEWING, g.LIGHTABLE), g.LIT);
        a(g.FOCUSABLE, g.LIT, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.D.b();
                this.a.D = null;
            }
        });
        a(EnumSet.of(g.LIGHTABLE, g.LIT, g.CAPTURABLE), g.ACQUIRED, g.PREVIEWING);
        a(EnumSet.of(g.PREVIEWING, g.LIGHTABLE, g.LIT, g.CAPTURABLE), g.RELEASED, g.ACQUIRED);
        a(g.LIT, g.LIGHTABLE, b);
        a(g.LIT, g.PREVIEWING, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.H.a(false);
                h.r(this.a);
            }
        });
        a(g.PREVIEW_FOCUSABLE, EnumSet.of(g.RELEASED, g.ACQUIRED), g.PREVIEWING);
        a(g.PREVIEW_FOCUSABLE, g.PREVIEWING, c);
        a(EnumSet.of(g.LIGHTABLE, g.LIT, g.FOCUSABLE, g.CAPTURABLE), g.PREVIEW_FOCUSABLE, g.PREVIEWING);
        a(g.LIGHTABLE, g.PREVIEWING, c);
        a(g.PREVIEWING, g.ACQUIRED, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                this.a.C.disable();
                this.a.C = null;
                this.a.H.a(null, false);
                this.a.H.b(this.a.I);
                this.a.F.stopPreview();
                this.a.A = i.a;
                this.a.o = -1;
            }
        });
        a(g.ACQUIRED, g.RELEASED, new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void run() {
                g.a().b("CameraStateMachine", "releasing camera");
                if (this.a.F != null) {
                    this.a.F.release();
                } else {
                    this.a.b(com.skypecam.obscura.d.b.CAMERA_NONE);
                }
                this.a.F = null;
                this.a.b(d.PANIC, false);
            }
        });
        a(g.class, new g[0]);
    }

    protected final void a(EnumSet<d> flags) {
        if (!flags.contains(d.TOUCH_AREA)) {
            flags.removeAll(EnumSet.of(d.FOCUS));
        }
        if (flags.contains(d.FIRE)) {
            flags.removeAll(EnumSet.of(d.FLAME));
        }
        if (!flags.contains(d.ACTIVITY) || !flags.contains(d.SURFACE_TEXTURE) || !flags.contains(d.FACING)) {
            flags.removeAll(EnumSet.of(d.FLAME, d.FIRE, d.TOUCH_AREA, d.FOCUS));
        }
    }

    public final synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.E.a((s) this.v.getAndSet(null)) != 0) {
            if (this.o == -1) {
                if (this.n != null) {
                    this.n.a("FirstFrameEvent", new HashMap());
                } else {
                    b(com.skypecam.obscura.d.b.NO_EVENT_REPORTER);
                }
            }
            if (this.w.a(this.o)) {
                this.F.setOneShotPreviewCallback(this);
            }
            this.o++;
            if (this.o >= 60) {
                this.o = 0;
            }
        }
    }

    public final void onPreviewFrame(byte[] data, Camera camera) {
        g.a().b("CameraStateMachine", "onPreviewFrame");
        Parameters parameters = a(camera, "onPreviewFrame");
        if (parameters != null) {
            int width = parameters.getPreviewSize().width;
            int height = parameters.getPreviewSize().height;
            this.w.a(new YuvImage(data, 17, width, height, null), width, height);
        }
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        g.a().b("CameraStateMachine", "onSurfaceTextureAvailable " + System.identityHashCode(surfaceTexture) + " (" + width + "x" + height + ")");
        this.E.a(surfaceTexture, width, height);
        this.H.a(new com.skypecam.obscura.e.j(width, height));
        a((Object) surfaceTexture, true);
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        g.a().b("CameraStateMachine", "onSurfaceTextureSizeChanged " + width + " " + height);
        this.E.a(surfaceTexture, width, height);
        this.H.a(new com.skypecam.obscura.e.j(width, height));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        g.a().b("CameraStateMachine", "onSurfaceTextureDestroyed " + System.identityHashCode(surfaceTexture));
        a((Object) surfaceTexture, false);
        return false;
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public final void onError(int error, Camera camera) {
        g.a().c("CameraStateMachine", "camera error " + error);
        b(com.skypecam.obscura.d.b.CAMERA_ERROR);
        if (error == 100) {
            b(d.PANIC, true);
        }
    }

    final void b(com.skypecam.obscura.d.b failure) {
        if (this.n != null) {
            this.n.a(failure);
        } else {
            g.a().c("CameraStateMachine", "attemptReportFailure failed failure:" + failure);
        }
    }

    private Parameters a(Camera camera, String label) {
        Parameters parameters = null;
        try {
            return camera.getParameters();
        } catch (Exception exception) {
            b(com.skypecam.obscura.d.b.CAMERA_GET_PARAMETERS);
            g.a().a("CameraStateMachine", "getParameters failed at " + label, exception);
            return parameters;
        }
    }

    private boolean a(Camera camera, Parameters parameters, String label) {
        try {
            camera.setParameters(parameters);
            return true;
        } catch (Exception exception) {
            b(com.skypecam.obscura.d.b.CAMERA_SET_PARAMETERS);
            g.a().a("CameraStateMachine", "setParameters failed at " + label, exception);
            return false;
        }
    }

    static /* synthetic */ void a(h x0, boolean x1) {
        Parameters a = x0.a(x0.F, "toggleRecordingHint " + x1);
        if (a != null) {
            a.setRecordingHint(x1);
            x0.a(x0.F, a, "toggleRecordingHint " + x1);
        }
    }

    static /* synthetic */ void a(h x0, final Context x1) {
        x0.C = new com.skype4life.c.a.a(x1);
        x0.C.a(new com.skype4life.c.a.a.a(x0) {
            final /* synthetic */ h b;

            public final void a(com.skype4life.c.a.a.b deviceOrientation, c deviceOrientationInSpace) {
                if (deviceOrientationInSpace != null) {
                    int deviceRotation;
                    switch (AnonymousClass20.a[deviceOrientationInSpace.ordinal()]) {
                        case 1:
                            deviceRotation = 0;
                            break;
                        case 2:
                            deviceRotation = 3;
                            break;
                        case 3:
                            deviceRotation = 2;
                            break;
                        case 4:
                            deviceRotation = 1;
                            break;
                        default:
                            deviceRotation = 0;
                            break;
                    }
                    this.b.E.b(deviceRotation, ((WindowManager) x1.getSystemService("window")).getDefaultDisplay().getRotation());
                }
            }
        });
        if (x0.C.canDetectOrientation()) {
            x0.C.enable();
            return;
        }
        x0.b(com.skypecam.obscura.d.b.NO_ORIENTATION_LISTENER);
        g.a().c("CameraStateMachine", "listenOrientation could not enable");
    }

    static /* synthetic */ void a(h x0, r x1) {
        if (x1 == r.OFF) {
            x0.b(d.FIRE, true);
        } else {
            f.a().execute(new Runnable(x0) {
                final /* synthetic */ h a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    try {
                        Thread.sleep(500);
                        this.a.b(d.FIRE, true);
                    } catch (InterruptedException e) {
                        this.a.b(com.skypecam.obscura.d.b.FLASH);
                        g.a().b("CameraStateMachine", e.getLocalizedMessage());
                        this.a.b(d.PANIC, true);
                    }
                }
            });
        }
    }

    static /* synthetic */ void p(h x0) {
        g.a().b("CameraStateMachine", "setFocusArea");
        Parameters a = x0.a(x0.F, "setFocusArea");
        if (a == null) {
            x0.a(d.PANIC, true);
            return;
        }
        List supportedFocusModes = a.getSupportedFocusModes();
        if (a.getMaxNumFocusAreas() == 0 || !(supportedFocusModes.contains("auto") || supportedFocusModes.contains("continuous-picture"))) {
            g.a().b("CameraStateMachine", "setFocusArea ignored because getMaxNumFocusAreas is 0 or FOCUS_MODE_AUTO not supported");
            x0.a((Object) x0.A, false);
            return;
        }
        List arrayList = new ArrayList();
        Rect rect = new Rect();
        ((RectF) x0.y.a()).roundOut(rect);
        g.a().b("CameraStateMachine", "setFocusArea rect " + rect);
        arrayList.add(new Area(rect, Constants.ONE_SECOND));
        a.setFocusAreas(arrayList);
        if (a.getMaxNumMeteringAreas() > 0) {
            g.a().b("CameraStateMachine", "setFocusArea setMeteringAreas");
            a.setMeteringAreas(arrayList);
        }
        x0.F.cancelAutoFocus();
        if (supportedFocusModes.contains("auto")) {
            a.setFocusMode("auto");
        } else {
            g.a().c("CameraStateMachine", "FOCUS_MODE_MACRO not supported, we might not able to tab to focus");
        }
        x0.a(x0.F, a, "setFocusArea");
        x0.F.autoFocus(new AutoFocusCallback(x0) {
            final /* synthetic */ h a;

            {
                this.a = this$0;
            }

            public final void onAutoFocus(boolean success, Camera camera) {
                Parameters focusedParameters = this.a.a(camera, "onAutoFocus");
                String focusMode = "";
                String focusRect = "";
                if (focusedParameters != null) {
                    focusMode = focusedParameters.getFocusMode();
                    if (!(focusedParameters.getFocusAreas() == null || focusedParameters.getFocusAreas().isEmpty())) {
                        Rect focusedRect = ((Area) focusedParameters.getFocusAreas().get(0)).rect;
                        focusRect = String.format("%s (%s x %s)", new Object[]{focusedRect, Integer.valueOf(focusedRect.width()), Integer.valueOf(focusedRect.height())});
                    }
                }
                g.a().b("CameraStateMachine", String.format("onAutoFocus %b focusMode %s - area %s", new Object[]{Boolean.valueOf(success), focusMode, focusRect}));
            }
        });
    }

    static /* synthetic */ void r(h x0) {
        Parameters a = x0.a(x0.F, "turnOffFlash");
        if (a == null) {
            x0.a(d.PANIC, true);
            return;
        }
        a.setFlashMode("off");
        x0.a(x0.F, a, "turnOffFlash");
    }
}
