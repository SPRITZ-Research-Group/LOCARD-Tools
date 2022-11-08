package com.skpcamera.antediluvian;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.net.Uri;
import android.opengl.GLES20;
import android.os.AsyncTask;
import android.support.v4.app.NotificationManagerCompat;
import android.view.OrientationEventListener;
import android.view.TextureView.SurfaceTextureListener;
import android.view.WindowManager;
import com.adjust.sdk.Constants;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.ae;
import com.facebook.react.bridge.ag;
import com.facebook.react.bridge.v;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public final class t implements OnFrameAvailableListener, ErrorCallback, v, com.skpcamera.antediluvian.i.a {
    private static final t a = new t();
    private static final com.microsoft.skype.a.a n = com.microsoft.skype.a.a.a("CameraQueue", com.microsoft.skype.a.a.b.DEFAULT);
    private static final Random o = new Random();
    private boolean A;
    private TimerTask B = null;
    private s C;
    private x D;
    private o E;
    private int F;
    private SurfaceTexture G;
    private m H;
    private final a I = new a();
    private final float[] J = new float[16];
    private AtomicBoolean K = new AtomicBoolean();
    private ag L;
    private final HashMap<Integer, h> b = new HashMap();
    private final HashMap<Integer, Integer> c = new HashMap();
    private final Map<Number, Camera> d = new HashMap();
    private int e = -1;
    private int f = -1;
    private boolean g = false;
    private boolean h;
    private volatile boolean i = false;
    private boolean j = true;
    private boolean k = false;
    private boolean l;
    private AtomicBoolean m = new AtomicBoolean(false);
    private int p = 0;
    private ArrayList<WeakReference<u>> q = new ArrayList();
    private WeakReference<SkypeCameraView> r = new WeakReference(null);
    private OrientationEventListener s;
    private int t;
    private WeakReference<com.skpcamera.antediluvian.u.a> u = new WeakReference(null);
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;

    private class a implements SurfaceTextureListener {
        final /* synthetic */ t a;
        private volatile boolean b;
        private int c;
        private File d;
        private ae e;
        private volatile boolean f;

        private a(t tVar) {
            this.a = tVar;
            this.b = false;
            this.c = -1;
        }

        /* synthetic */ a(t x0, byte b) {
            this(x0);
        }

        public final void a(File cacheDir, boolean freeze, ae promise, int causeId) {
            FLog.i("SkypeCameraInstance", "GLCapturer capture (causeId " + causeId + ")");
            this.d = cacheDir;
            this.e = promise;
            this.f = freeze;
            this.c = causeId;
            this.b = true;
        }

        public final void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            int causeId = t.c();
            FLog.i("SkypeCameraInstance", "onSurfaceTextureAvailable with causeId " + causeId);
            t.b(this.a, surface, causeId);
        }

        public final void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            FLog.i("SkypeCameraInstance", "onSurfaceTextureSizeChanged to " + width + " " + height);
        }

        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            u viewFinder;
            int causeId = t.c();
            FLog.i("SkypeCameraInstance", "onSurfaceTextureDestroyed with causeId " + causeId);
            Collection<u> removes = new ArrayList();
            Iterator it = this.a.q.iterator();
            while (it.hasNext()) {
                viewFinder = (u) ((WeakReference) it.next()).get();
                if (viewFinder != null && surface == viewFinder.getSurfaceTexture()) {
                    removes.add(viewFinder);
                    FLog.i("SkypeCameraInstance", "removes add (causeId " + causeId + ")");
                }
            }
            for (u viewFinder2 : removes) {
                t.a(this.a, viewFinder2, causeId);
            }
            FLog.i("SkypeCameraInstance", "removed " + removes.size() + ", left with " + this.a.q.size() + " (causeId " + causeId + ")");
            if (this.a.E != null) {
                this.a.E.a();
                this.a.E = null;
            }
            if (this.a.H != null) {
                this.a.H.a();
                this.a.H = null;
            }
            FLog.i("SkypeCameraInstance", "onSurfaceTextureDestroyed - done (causeId " + causeId + ")");
            return true;
        }

        public final void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }

        public final void a() {
            this.b = false;
            FLog.i("SkypeCameraInstance", "onSurfaceTextureUpdated with capture (causeId " + this.c + ")");
            t.a(this.a, this.d, this.f, this.e, this.c);
            this.d = null;
            this.e = null;
            this.c = -1;
        }
    }

    private class b implements Runnable {
        final /* synthetic */ t a;
        private TimerTask b;

        b(t tVar, TimerTask task) {
            this.a = tVar;
            this.b = task;
        }

        public final void run() {
            int causeId = t.c();
            FLog.i("SkypeCameraInstance", "stop recording timeout with causeId " + causeId);
            this.a.d(causeId);
            if (this.a.B == this.b) {
                this.a.B = null;
            }
        }
    }

    static class c extends AsyncTask<byte[], String, String> {
        private File a;

        c() {
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((byte[][]) objArr);
        }

        private String a(byte[]... bytes) {
            try {
                File f = File.createTempFile("SKP_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + new Random().nextInt(Constants.ONE_SECOND), ".jpg", this.a);
                FileOutputStream fos = new FileOutputStream(f.getPath());
                fos.write(bytes[0]);
                fos.close();
                return Uri.fromFile(f).toString();
            } catch (IOException e) {
                return null;
            }
        }
    }

    static /* synthetic */ Rect a(t x0, float x1, float x2) {
        SkypeCameraView skypeCameraView = (SkypeCameraView) x0.r.get();
        if (skypeCameraView == null) {
            return new Rect(0, 0, 0, 0);
        }
        int intValue = Float.valueOf(75.0f).intValue();
        float width = ((x1 / ((float) skypeCameraView.getWidth())) * 2000.0f) - 1000.0f;
        float height = ((x2 / ((float) skypeCameraView.getHeight())) * 2000.0f) - 1000.0f;
        int b = b(((int) width) - (intValue / 2), ((int) (2000.0f - ((float) intValue))) / 2);
        int b2 = b(((int) height) - (intValue / 2), ((int) (2000.0f - ((float) intValue))) / 2);
        return new Rect(b, b2, Math.round((float) ((intValue / 2) + b)), Math.round((float) ((intValue / 2) + b2)));
    }

    static /* synthetic */ void a(t x0, SurfaceTexture x1, int x2) throws IOException {
        FLog.i("SkypeCameraInstance", "startPreview experimental " + x0.k + " (causeId " + x2 + ")");
        f();
        Camera g = x0.g(x2);
        if (g != null) {
            FLog.i("SkypeCameraInstance", "setFocusMode (causeId " + x2 + ")");
            Camera g2 = x0.g(x2);
            if (g2 != null) {
                try {
                    Parameters parameters = g2.getParameters();
                    List supportedFocusModes = parameters.getSupportedFocusModes();
                    FLog.i("SkypeCameraInstance", "camera focus modes: " + supportedFocusModes.toString());
                    x0.g = supportedFocusModes.contains("auto");
                    FLog.i("SkypeCameraInstance", "camera auto focus " + x0.g + " (causeId " + x2 + ")");
                    if (supportedFocusModes.contains("continuous-picture")) {
                        parameters.setFocusMode("continuous-picture");
                        g2.setParameters(parameters);
                        FLog.i("SkypeCameraInstance", "setting focus mode to continuous picture (causeId " + x2 + ")");
                    } else {
                        FLog.i("SkypeCameraInstance", "could not set focus to continuous picture (causeId " + x2 + ")");
                    }
                } catch (RuntimeException e) {
                    FLog.e("SkypeCameraInstance", "setFocusMode exception %s (causeId %x)", e.getLocalizedMessage(), Integer.valueOf(x2));
                }
            }
            x0.h(x2);
            x0.I.f = false;
            if (!x0.m.getAndSet(true)) {
                f();
                x0.G = x1;
                ((Camera) x0.d.get(Integer.valueOf(x0.e))).setPreviewTexture(x0.G);
                x0.G.setOnFrameAvailableListener(x0);
                try {
                    g.startPreview();
                } catch (RuntimeException e2) {
                    FLog.e("SkypeCameraInstance", "startPreview exception " + e2.getLocalizedMessage() + " (causeId " + x2 + ")");
                }
            }
        }
    }

    static /* synthetic */ void a(t x0, File x1, boolean x2, ae x3, final int x4) {
        FLog.i("SkypeCameraInstance", "saveFrame (causeId " + x4 + ")");
        String str = "SKP_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + new Random().nextInt(Constants.ONE_SECOND);
        try {
            if (!x0.i) {
                int max;
                File createTempFile = File.createTempFile(str, ".jpg", x1);
                u a = a(x0.q);
                int max2 = a != null ? Math.max(a.a(), 0) : 0;
                if (a != null) {
                    max = Math.max(a.b(), 0);
                } else {
                    max = 0;
                }
                FLog.i("SkypeCameraInstance", "make current (causeId " + x4 + ")");
                x0.D.d();
                FLog.i("SkypeCameraInstance", "save frame crop " + max2 + "," + max + " (causeId " + x4 + ")");
                x0.D.a(createTempFile, max2, max);
                FLog.i("SkypeCameraInstance", "resolving (causeId " + x4 + ")");
                String uri = Uri.fromFile(createTempFile).toString();
                Object writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(ReactVideoViewManager.PROP_SRC_URI, uri);
                writableNativeMap.putInt("fileSize", (int) createTempFile.length());
                writableNativeMap.putInt("width", x0.D.a() - (max2 * 2));
                writableNativeMap.putInt("height", x0.D.b() - (max * 2));
                FLog.i("SkypeCameraInstance", "display surface is " + writableNativeMap.getInt("width") + " " + writableNativeMap.getInt("height") + " (causeId " + x4 + ")");
                if (!x0.i) {
                    FLog.i("SkypeCameraInstance", "resolving capture promise (causeId " + x4 + ")");
                    x3.a(writableNativeMap);
                }
            }
            if (x0.h) {
                n.b(new Runnable(x0) {
                    final /* synthetic */ t b;

                    public final void run() {
                        t.a(this.b, false, x4);
                    }
                });
            }
            if (x0.i) {
                FLog.i("SkypeCameraInstance", "saveFrame: capture was canceled (causeId " + x4 + ")");
                x0.i = false;
                x0.I.f = false;
            } else if (x2) {
                FLog.i("SkypeCameraInstance", "stopping camera for freeze (causeId " + x4 + ")");
                x0.a(false, x4);
            }
        } catch (IOException e) {
            FLog.e("SkypeCameraInstance", "could not save frame " + e.getLocalizedMessage() + " (causeId " + x4 + ")");
            if (x0.h) {
                n.b(/* anonymous class already generated */);
            }
            if (x0.i) {
                FLog.i("SkypeCameraInstance", "saveFrame: capture was canceled (causeId " + x4 + ")");
                x0.i = false;
                x0.I.f = false;
            } else if (x2) {
                FLog.i("SkypeCameraInstance", "stopping camera for freeze (causeId " + x4 + ")");
                x0.a(false, x4);
            }
        } catch (Throwable th) {
            if (x0.h) {
                n.b(/* anonymous class already generated */);
            }
            if (x0.i) {
                FLog.i("SkypeCameraInstance", "saveFrame: capture was canceled (causeId " + x4 + ")");
                x0.i = false;
                x0.I.f = false;
            } else if (x2) {
                FLog.i("SkypeCameraInstance", "stopping camera for freeze (causeId " + x4 + ")");
                x0.a(false, x4);
            }
        }
    }

    public static t b() {
        return a;
    }

    public static int c() {
        return o.nextInt();
    }

    private static void f() {
        if (!com.microsoft.skype.a.a.a(n)) {
            FLog.e("SkypeCameraInstance", "not on camera queue");
            throw new com.facebook.react.bridge.c("Not on camera queue!");
        }
    }

    private t() {
        int count = Camera.getNumberOfCameras();
        int i = 0;
        while (i < count) {
            CameraInfo info = new CameraInfo();
            try {
                Camera.getCameraInfo(i, info);
                if (info.facing == 1 && this.b.get(Integer.valueOf(1)) == null) {
                    this.b.put(Integer.valueOf(1), new h(info));
                    this.c.put(Integer.valueOf(1), Integer.valueOf(i));
                    i++;
                } else {
                    if (info.facing == 0 && this.b.get(Integer.valueOf(2)) == null) {
                        this.b.put(Integer.valueOf(2), new h(info));
                        this.c.put(Integer.valueOf(2), Integer.valueOf(i));
                    }
                    i++;
                }
            } catch (RuntimeException e) {
                FLog.e("SkypeCameraInstance", "Could not getCameraInfo, exception: " + e.getLocalizedMessage());
            }
        }
        this.w = 640.0f;
        this.x = 360.0f;
        this.y = 1300.0f;
        this.z = 2.0f;
        this.A = false;
    }

    public final void a(SkypeCameraView view) {
        this.r = new WeakReference(view);
        if (this.C != null) {
            this.C.a(view);
        }
    }

    public final void a(com.skpcamera.antediluvian.u.a callback) {
        this.u = new WeakReference(callback);
    }

    public final void a(ag reactAppContext) {
        this.L = reactAppContext;
    }

    @Nullable
    private Camera g(int causeId) {
        if (this.e != -1) {
            return a(this.e, causeId);
        }
        return null;
    }

    public final void a(final int type, final u viewFinder, final int causeId) {
        FLog.i("SkypeCameraInstance", "setActiveCamera to " + type + " (causeId " + causeId + ")");
        h(causeId);
        viewFinder.setSurfaceTextureListener(this.I);
        n.c(new Runnable(this) {
            final /* synthetic */ t d;

            public final void run() {
                this.d.e = type;
                Iterator<WeakReference<u>> iter = this.d.q.iterator();
                while (iter.hasNext()) {
                    WeakReference<u> ref = (WeakReference) iter.next();
                    if (ref.get() == null || ((u) ref.get()).equals(viewFinder)) {
                        iter.remove();
                    }
                }
                this.d.q.add(0, new WeakReference(viewFinder));
                if (this.d.K.getAndSet(true)) {
                    FLog.i("SkypeCameraInstance", "not adding lifecycle listener (causeId " + causeId + ")");
                } else {
                    FLog.i("SkypeCameraInstance", "adding lifecycle listener (causeId " + causeId + ")");
                    this.d.L.a(this.d);
                }
                this.d.c(type, causeId);
            }
        });
    }

    private void h(int causeId) {
        FLog.i("SkypeCameraInstance", "listenOrientation (causeId " + causeId + ")");
        if (this.s == null) {
            this.s = new OrientationEventListener(this, this.L) {
                final /* synthetic */ t a;

                public final void onOrientationChanged(int orientation) {
                    t.b(this.a, t.c());
                }
            };
        }
        if (this.s.canDetectOrientation()) {
            this.s.enable();
            FLog.i("SkypeCameraInstance", "enabled orientation listener (causeId " + causeId + ")");
            return;
        }
        FLog.w("SkypeCameraInstance", "could not enable orientation listener (causeId " + causeId + ")");
    }

    public final void a(int type, u viewFinder, Context context, int causeId) {
        FLog.i("SkypeCameraInstance", "setCameraType (causeId " + causeId + ")");
        final SurfaceTexture texture = this.G;
        final int i = causeId;
        final int i2 = type;
        final u uVar = viewFinder;
        final Context context2 = context;
        n.b(new Runnable(this) {
            final /* synthetic */ t f;

            public final void run() {
                this.f.a(false, i);
                this.f.a(i2, uVar, i);
                if (texture != null) {
                    this.f.a(texture, i);
                }
                if (this.f.u != null) {
                    com.microsoft.skype.a.a.a.b(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 a;

                        {
                            this.a = this$1;
                        }

                        public final void run() {
                            com.skpcamera.antediluvian.u.a callback = (com.skpcamera.antediluvian.u.a) this.a.f.u.get();
                            if (callback != null) {
                                callback.a(i2, i);
                            }
                        }
                    });
                } else {
                    FLog.i("SkypeCameraInstance", "no viewfinder callback; camera type change not propagated (causeId " + i + ")");
                }
            }
        });
    }

    public final void a(int causeId) {
        FLog.i("SkypeCameraInstance", "enterPreview (causeId " + causeId + ")");
        this.i = false;
        this.I.f = false;
    }

    public final void b(int causeId) {
        this.i = true;
        e(causeId);
    }

    public final void a(final SurfaceTexture texture, final int causeId) {
        FLog.i("SkypeCameraInstance", "startCamera (causeId " + causeId + ")");
        n.c(new Runnable(this) {
            final /* synthetic */ t c;

            public final void run() {
                try {
                    t.a(this.c, texture, causeId);
                } catch (IOException e) {
                    FLog.e("SkypeCameraInstance", "exception starting camera " + e.getLocalizedMessage() + " (causeId " + causeId + ")");
                }
            }
        });
    }

    public final void a(boolean sync, final int causeId) {
        FLog.i("SkypeCameraInstance", "stopCamera (" + (sync ? "" : "a") + "sync) (causeId " + causeId + ")");
        Runnable stop = new Runnable(this) {
            final /* synthetic */ t b;

            public final void run() {
                t.b(this.b, this.b.e, causeId);
            }
        };
        if (sync) {
            n.a(stop);
        } else {
            n.c(stop);
        }
    }

    public final void c(final int causeId) {
        FLog.i("SkypeCameraInstance", "release (causeId " + causeId + ")");
        if (a(this.q) == null) {
            n.a(new Runnable(this) {
                final /* synthetic */ t b;

                public final void run() {
                    Camera camera = (Camera) this.b.d.get(Integer.valueOf(this.b.e));
                    if (camera != null) {
                        FLog.i("SkypeCameraInstance", "stopping camera preview (causeId " + causeId + ")");
                        camera.stopPreview();
                        this.b.m.set(false);
                    }
                }
            });
            FLog.i("SkypeCameraInstance", "stopping camera from release (causeId " + causeId + ")");
            a(false, causeId);
        }
    }

    private Camera a(int type, int causeId) {
        FLog.i("SkypeCameraInstance", "acquireCameraInstance (causeId " + causeId + ")");
        f();
        if (this.d.get(Integer.valueOf(type)) == null && this.c.get(Integer.valueOf(type)) != null) {
            try {
                Camera camera = Camera.open(((Integer) this.c.get(Integer.valueOf(type))).intValue());
                camera.setErrorCallback(this);
                this.d.put(Integer.valueOf(type), camera);
                c(type, causeId);
            } catch (Exception e) {
                FLog.e("SkypeCameraInstance", "acquireCameraInstance failed: " + e.getLocalizedMessage() + "(causeId " + causeId + ")");
            }
        }
        this.e = type;
        return (Camera) this.d.get(Integer.valueOf(type));
    }

    public final void a(final float x, final float y, final int causeId) {
        FLog.i("SkypeCameraInstance", "focusOnTouch (causeId " + causeId + ")");
        n.b(new Runnable(this) {
            final /* synthetic */ t d;

            public final void run() {
                Camera camera = this.d.g(causeId);
                if (camera != null) {
                    try {
                        Parameters parameters = camera.getParameters();
                        Rect focusRect = t.a(this.d, x, y);
                        if (this.d.g) {
                            if (!"auto".equals(parameters.getFocusMode())) {
                                parameters.setFocusMode("auto");
                            }
                            if (parameters.getMaxNumFocusAreas() > 0) {
                                FLog.i("SkypeCameraInstance", "will set focus area (causeId " + causeId + ")");
                                List<Area> mylist = new ArrayList();
                                mylist.add(new Area(focusRect, Constants.ONE_SECOND));
                                parameters.setFocusAreas(mylist);
                            }
                            try {
                                camera.cancelAutoFocus();
                                camera.setParameters(parameters);
                                if (!this.d.m.getAndSet(true)) {
                                    camera.startPreview();
                                }
                                camera.autoFocus(new AutoFocusCallback(this) {
                                    final /* synthetic */ AnonymousClass15 a;

                                    {
                                        this.a = this$1;
                                    }

                                    public final void onAutoFocus(boolean success, Camera camera) {
                                        FLog.i("SkypeCameraInstance", "auto focus succeeded (causeId " + causeId + ")");
                                    }
                                });
                                return;
                            } catch (Exception e) {
                                FLog.e("SkypeCameraInstance", "focus exception " + e.getLocalizedMessage() + " (causeId " + causeId + ")");
                                return;
                            }
                        }
                        FLog.w("SkypeCameraInstance", "camera does not have autofocus (causeId " + causeId + ")");
                    } catch (RuntimeException re) {
                        FLog.e("SkypeCameraInstance", "focusOnTouch exception %s (causeId %x)", re.getLocalizedMessage(), Integer.valueOf(causeId));
                    }
                }
            }
        });
    }

    private static int b(int x, int max) {
        if (x > max) {
            return max;
        }
        return x < NotificationManagerCompat.IMPORTANCE_UNSPECIFIED ? NotificationManagerCompat.IMPORTANCE_UNSPECIFIED : x;
    }

    public final void a(final boolean complete, final int d, final int causeId) {
        FLog.i("SkypeCameraInstance", "zoom (causeId " + causeId + ")");
        n.b(new Runnable(this) {
            final /* synthetic */ t d;

            public final void run() {
                Camera camera = this.d.g(causeId);
                if (camera != null) {
                    try {
                        Parameters params = camera.getParameters();
                        if (!params.isZoomSupported()) {
                            FLog.i("SkypeCameraInstance", "zoom is not supported (causeId " + causeId + ")");
                        } else if (this.d.f == -1) {
                            this.d.f = d;
                            FLog.i("SkypeCameraInstance", "setting previous zoom distance to " + d + " (causeId " + causeId + ")");
                        } else {
                            int zoom = params.getZoom();
                            int newZoom = zoom;
                            if (d > this.d.f) {
                                if (((Integer) params.getZoomRatios().get(zoom)).intValue() <= 300) {
                                    newZoom = Math.min(params.getMaxZoom(), zoom + 1);
                                    FLog.i("SkypeCameraInstance", "zoom bumped to " + newZoom + " (causeId " + causeId + ")");
                                } else {
                                    FLog.i("SkypeCameraInstance", "hit max zoom (causeId " + causeId + ")");
                                }
                            } else if (d < this.d.f) {
                                newZoom = Math.max(zoom - 1, 0);
                                FLog.i("SkypeCameraInstance", "zoom decreased to " + newZoom + " (causeId " + causeId + ")");
                            }
                            this.d.f = complete ? -1 : d;
                            params.setZoom(newZoom);
                            camera.setParameters(params);
                        }
                    } catch (RuntimeException re) {
                        FLog.e("SkypeCameraInstance", "zoom exception %s (causeId %x)", re.getLocalizedMessage(), Integer.valueOf(causeId));
                    }
                }
            }
        });
    }

    public final void a(float ms) {
        this.v = ms;
    }

    public final void b(float width) {
        this.w = width;
    }

    public final void c(float height) {
        this.x = height;
    }

    public final void d(float bitrate) {
        this.y = bitrate;
    }

    public final void e(float profile) {
        if (profile >= 1.0f && profile <= 64.0f) {
            this.z = profile;
        }
    }

    public final void a(boolean state) {
        this.A = state;
    }

    public final void a(ae promise, final int causeId) {
        SkypeCameraView myView = (SkypeCameraView) this.r.get();
        if (myView != null) {
            boolean z;
            FLog.i("SkypeCameraInstance", "startRecording (causeId " + causeId + ")");
            this.I.f = false;
            this.i = false;
            n.b(new Runnable(this) {
                final /* synthetic */ t b;

                public final void run() {
                    t.a(this.b, this.b.h, causeId);
                }
            });
            Context context = this.L;
            if (((h) this.b.get(Integer.valueOf(this.e))).a() % 180 == 90) {
                z = true;
            } else {
                z = false;
            }
            this.C = new s(context, myView, z, this.w, this.x, this.y, this.z, this.A, promise, causeId);
            if (this.H != null) {
                this.C.a(this.H, this.E, this.F, causeId);
                b(true, causeId);
            }
            Timer timer = new Timer();
            this.B = new TimerTask(this) {
                final /* synthetic */ t a;

                {
                    this.a = this$0;
                }

                public final void run() {
                    if (this.a.C != null) {
                        com.microsoft.skype.a.a.a.b(new b(this.a, this.a.B));
                    }
                }
            };
            timer.schedule(this.B, (long) ((int) this.v));
            return;
        }
        promise.a(new Throwable("SkypeCameraView is not set (causeId " + causeId + ")"));
    }

    public final void d(final int causeId) {
        FLog.i("SkypeCameraInstance", "finishRecording (causeId " + causeId + ")");
        if (this.C != null) {
            this.C.b(causeId);
            b(false, causeId);
            n.b(new Runnable(this) {
                final /* synthetic */ t b;

                public final void run() {
                    t.a(this.b, false, causeId);
                }
            });
        }
        if (this.B != null && this.B.cancel()) {
            this.B = null;
        }
    }

    public final void e(final int causeId) {
        FLog.i("SkypeCameraInstance", "cancelRecording (causeId " + causeId + ")");
        if (this.C != null) {
            this.C.a(causeId);
            b(false, causeId);
            n.b(new Runnable(this) {
                final /* synthetic */ t b;

                public final void run() {
                    t.a(this.b, false, causeId);
                }
            });
        }
    }

    private void b(final boolean isRecording, final int causeId) {
        FLog.i("SkypeCameraInstance", "toggleRecordingHint (causeId " + causeId + ")");
        n.b(new Runnable(this) {
            final /* synthetic */ t c;

            public final void run() {
                FLog.i("SkypeCameraInstance", "toggling recording hint to " + isRecording + " (causeId " + causeId + ")");
                Camera camera = this.c.g(causeId);
                if (camera != null) {
                    try {
                        Parameters parameters = camera.getParameters();
                        parameters.setRecordingHint(isRecording);
                        camera.setParameters(parameters);
                    } catch (RuntimeException re) {
                        FLog.e("SkypeCameraInstance", "toggleRecordingHint exception %s (causeId %x)", re.getLocalizedMessage(), Integer.valueOf(causeId));
                    }
                }
            }
        });
    }

    public final void onHostDestroy() {
        FLog.i("SkypeCameraInstance", "onHostDestroy");
    }

    public final void b(boolean sessionActive) {
        FLog.i("SkypeCameraInstance", "setSessionActive: " + sessionActive + " (causeId )");
        this.l = sessionActive;
    }

    public final synchronized void a() {
        if (this.C != null) {
            FLog.i("SkypeCameraInstance", "recording complete");
            this.C.a();
            this.C = null;
        } else {
            FLog.i("SkypeCameraInstance", "recording complete on null camcorder");
        }
    }

    public final int d() {
        h cameraInfo = (h) this.b.get(Integer.valueOf(this.e));
        if (cameraInfo == null) {
            return -1;
        }
        return cameraInfo.b;
    }

    public final int e() {
        h cameraInfo = (h) this.b.get(Integer.valueOf(this.e));
        if (cameraInfo == null) {
            return -1;
        }
        return cameraInfo.c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Size i(int type) {
        f();
        Camera camera = (Camera) this.d.get(Integer.valueOf(type));
        if (camera == null) {
            return null;
        }
        try {
            List<Size> supportedPreviewSizes = camera.getParameters().getSupportedPreviewSizes();
            Size size = null;
            for (Size size2 : supportedPreviewSizes) {
                if (size2.width <= 2048 && size2.height <= 2048 && size2.width != size2.height) {
                    if (size == null) {
                        size = size2;
                    }
                }
                Size size22 = size;
                size = size22;
            }
            if (size != null || supportedPreviewSizes.size() <= 0) {
                return size;
            }
            return (Size) supportedPreviewSizes.get(supportedPreviewSizes.size() - 1);
        } catch (RuntimeException re) {
            FLog.e("SkypeCameraInstance", "getBestPreviewSize exception %s", re.getLocalizedMessage());
            return null;
        }
    }

    public final void f(int flashMode) {
        switch (flashMode) {
            case 1:
                this.h = true;
                return;
            default:
                this.h = false;
                return;
        }
    }

    private int g() {
        switch (this.p) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    private void c(int type, int causeId) {
        FLog.i("SkypeCameraInstance", "adjustPreviewLayout (causeId " + causeId + ")");
        f();
        Camera camera = (Camera) this.d.get(Integer.valueOf(type));
        if (camera != null) {
            try {
                Parameters parameters = camera.getParameters();
                h cameraInfo = (h) this.b.get(Integer.valueOf(type));
                boolean isFront = cameraInfo.a.facing == 1;
                int orientation = cameraInfo.a.orientation;
                int g = g();
                if (isFront) {
                    g = (g + orientation) % 360;
                } else {
                    g = ((orientation - g) + 360) % 360;
                }
                cameraInfo.a(g);
                boolean isPortrait = g() % 180 == 0;
                FLog.i("SkypeCameraInstance", "adjusting preview layout isFront:" + (isFront ? "T" : "F") + " orientation:" + orientation + " rotation:" + cameraInfo.a() + " isPortrait:" + (isPortrait ? "T" : "F") + " (causeId " + causeId + ")");
                int displayRotation = cameraInfo.a();
                int cameraRotation = displayRotation;
                if (cameraInfo.a.facing == 1 && isPortrait) {
                    displayRotation += 180;
                }
                displayRotation %= 360;
                if (displayRotation == 0 || displayRotation == 90 || displayRotation == 180 || displayRotation == 270) {
                    camera.setDisplayOrientation(displayRotation);
                    parameters.setRotation(cameraRotation);
                    Size optimalPreviewSize = i(type);
                    if (optimalPreviewSize == null) {
                        FLog.w("SkypeCameraInstance", "adjustPreviewLayout optimalPreviewSize null (causeId %x)", Integer.valueOf(causeId));
                        return;
                    }
                    int width = optimalPreviewSize.width;
                    int height = optimalPreviewSize.height;
                    FLog.i("SkypeCameraInstance", "preview size " + width + " x " + height + " (causeId " + causeId + ")");
                    parameters.setPreviewSize(width, height);
                    try {
                        camera.setParameters(parameters);
                    } catch (Exception e) {
                        FLog.e("SkypeCameraInstance", "failure to set parameters : " + e.getLocalizedMessage() + " (causeId " + causeId + ")");
                    }
                    boolean changed = false;
                    if (cameraInfo.a() == 0 || cameraInfo.a() == 180) {
                        if (!(cameraInfo.b == width && cameraInfo.c == height)) {
                            changed = true;
                        }
                        cameraInfo.b = width;
                        cameraInfo.c = height;
                    } else {
                        if (!(cameraInfo.b == height && cameraInfo.c == width)) {
                            changed = true;
                        }
                        cameraInfo.b = height;
                        cameraInfo.c = width;
                    }
                    if (!changed) {
                        return;
                    }
                    if (((com.skpcamera.antediluvian.u.a) this.u.get()) != null) {
                        FLog.i("SkypeCameraInstance", "viewfinder layout callback (causeId " + causeId + ")");
                        final int i = cameraInfo.b;
                        final int i2 = cameraInfo.c;
                        final int i3 = causeId;
                        com.microsoft.skype.a.a.a.b(new Runnable(this) {
                            final /* synthetic */ t d;

                            public final void run() {
                                com.skpcamera.antediluvian.u.a myCallback = (com.skpcamera.antediluvian.u.a) this.d.u.get();
                                if (myCallback != null) {
                                    myCallback.a(i3);
                                }
                            }
                        });
                        return;
                    }
                    FLog.i("SkypeCameraInstance", "no viewfinder callback; preview change not propagated (causeId " + causeId + ")");
                    return;
                }
                throw new com.facebook.react.bridge.c("Display rotation was not a valid value : " + displayRotation + " (causeId " + causeId + ")");
            } catch (RuntimeException re) {
                FLog.e("SkypeCameraInstance", "adjustPreviewLayout exception %s (causeId %x)", re.getLocalizedMessage(), Integer.valueOf(causeId));
            }
        }
    }

    public final void a(File cacheDir, boolean freeze, ae promise, int causeId) {
        FLog.i("SkypeCameraInstance", "capture (causeId " + causeId + ")");
        this.i = false;
        final int i = causeId;
        final ae aeVar = promise;
        final File file = cacheDir;
        final boolean z = freeze;
        n.b(new Runnable(this) {
            final /* synthetic */ t e;

            public final void run() {
                FLog.i("SkypeCameraInstance", "capture callback (causeId " + i + ")");
                final Camera camera = this.e.g(i);
                if (camera == null) {
                    FLog.e("SkypeCameraInstance", "no camera for capture, rejecting capture promise (causeId " + i + ")");
                    aeVar.a("No camera found.");
                } else if (this.e.i) {
                    FLog.i("SkypeCameraInstance", "capture callback: capture was canceled (causeId " + i + ")");
                    this.e.i = false;
                    this.e.I.f = false;
                } else {
                    Runnable captureRunnable;
                    if (this.e.h) {
                        t.a(this.e, true, i);
                    }
                    final Runnable noAutoFocusRunnable = new Runnable(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = this$1;
                        }

                        public final void run() {
                            FLog.i("SkypeCameraInstance", "capturing without auto focus (causeId " + i + ")");
                            this.a.e.I.a(file, z, aeVar, i);
                        }
                    };
                    if (this.e.g) {
                        captureRunnable = new Runnable(this) {
                            final /* synthetic */ AnonymousClass8 c;
                            private AtomicBoolean d = new AtomicBoolean(false);

                            public final void run() {
                                try {
                                    final TimerTask task = new TimerTask(this) {
                                        final /* synthetic */ AnonymousClass2 a;

                                        {
                                            this.a = this$2;
                                        }

                                        public final void run() {
                                            if (!this.a.d.getAndSet(true)) {
                                                this.a.c.e.I.a(file, z, aeVar, i);
                                            }
                                        }
                                    };
                                    camera.autoFocus(new AutoFocusCallback(this) {
                                        final /* synthetic */ AnonymousClass2 b;

                                        public final void onAutoFocus(boolean success, Camera camera) {
                                            FLog.i("SkypeCameraInstance", "onAutoFocus " + success + " hasRun " + this.b.d + " (causeId " + i + ")");
                                            task.run();
                                        }
                                    });
                                    new Timer().schedule(task, 3000);
                                } catch (Exception e) {
                                    FLog.e("SkypeCameraInstance", "focus exception " + e.getLocalizedMessage() + " (causeId " + i + ")");
                                    if (!this.d.getAndSet(true)) {
                                        noAutoFocusRunnable.run();
                                    }
                                }
                            }
                        };
                    } else {
                        captureRunnable = noAutoFocusRunnable;
                    }
                    captureRunnable.run();
                }
            }
        });
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.H == null) {
            FLog.w("SkypeCameraInstance", "Received frame available with no surface, cannot call updateTexImage");
            return;
        }
        this.D.d();
        this.G.updateTexImage();
        u a = a(this.q);
        if (a == null) {
            return;
        }
        if (this.I.b || !this.I.f) {
            this.G.getTransformMatrix(this.J);
            GLES20.glViewport(0, 0, a.getWidth(), a.getHeight());
            this.E.a(this.F, this.J);
            if (this.I.b) {
                this.I.a();
            }
            this.D.e();
            if (this.C != null) {
                this.C.a(this.G.getTimestamp(), this.J);
                return;
            }
            return;
        }
        FLog.i("SkypeCameraInstance", "dropping frame with freeze");
    }

    private static u a(ArrayList<WeakReference<u>> viewFinders) {
        u firstViewFinder = null;
        Iterator<WeakReference<u>> iter = viewFinders.iterator();
        while (iter.hasNext()) {
            firstViewFinder = (u) ((WeakReference) iter.next()).get();
            if (firstViewFinder != null) {
                break;
            }
            iter.remove();
        }
        return firstViewFinder;
    }

    public final void onError(int error, Camera camera) {
        int causeId = o.nextInt();
        FLog.w("SkypeCameraInstance", "camera error " + error + " with causeId " + causeId);
        if (error == 100) {
            a(false, causeId);
            a(this.G, causeId);
        }
    }

    public final void onHostResume() {
        int causeId = o.nextInt();
        FLog.i("SkypeCameraInstance", "onHostResume shouldReleaseOnBackground:" + this.j + " sessionActive:" + this.l + " cameraTexture:" + (this.G != null) + " with causeId " + causeId);
        if (this.j && this.l && this.G != null) {
            a(this.G, causeId);
        }
    }

    public final void onHostPause() {
        int causeId = o.nextInt();
        FLog.i("SkypeCameraInstance", "onHostPause shouldReleaseOnBackground: " + this.j + " with causeId " + causeId);
        if (this.j) {
            a(true, causeId);
        }
    }

    static /* synthetic */ boolean b(t x0, final int x1) {
        int orientation = ((WindowManager) x0.L.getSystemService("window")).getDefaultDisplay().getOrientation();
        if (x0.t == orientation) {
            return false;
        }
        x0.t = orientation;
        orientation = x0.t;
        FLog.i("SkypeCameraInstance", "setDeviceRotation (causeId " + x1 + ")");
        n.b(new Runnable(x0) {
            final /* synthetic */ t c;

            public final void run() {
                FLog.i("SkypeCameraInstance", "device orientation changed to " + orientation + " (causeId " + x1 + ")");
                this.c.p = orientation;
                this.c.c(this.c.e, x1);
            }
        });
        return true;
    }

    static /* synthetic */ void b(t x0, int x1, int x2) {
        FLog.i("SkypeCameraInstance", "releaseCameraInstance (causeId " + x2 + ")");
        f();
        Camera camera = (Camera) x0.d.get(Integer.valueOf(x1));
        if (camera != null) {
            camera.stopPreview();
            x0.m.set(false);
            FLog.i("SkypeCameraInstance", "releasing camera (causeId " + x2 + ")");
            camera.release();
            x0.s.disable();
            x0.d.remove(Integer.valueOf(x1));
        }
    }

    static /* synthetic */ void a(t x0, boolean x1, int x2) {
        FLog.i("SkypeCameraInstance", "toggleTorch (causeId " + x2 + ")");
        f();
        Camera g = x0.g(x2);
        if (g != null) {
            try {
                Parameters parameters = g.getParameters();
                String str = x1 ? "torch" : "off";
                List supportedFlashModes = parameters.getSupportedFlashModes();
                if (supportedFlashModes == null || !supportedFlashModes.contains(str)) {
                    FLog.w("SkypeCameraInstance", "could not toggle torch (causeId " + x2 + ")");
                    return;
                }
                FLog.i("SkypeCameraInstance", "setting torch " + str + " (causeId " + x2 + ")");
                parameters.setFlashMode(str);
                g.setParameters(parameters);
                if (x1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        FLog.i("SkypeCameraInstance", "toggleTorch interrupted! (causeId " + x2 + ")");
                        Thread.currentThread().interrupt();
                    }
                }
                FLog.i("SkypeCameraInstance", "toggleTorch done (causeId " + x2 + ")");
            } catch (RuntimeException e2) {
                FLog.e("SkypeCameraInstance", "toggleTorch exception %s (causeId %x)", e2.getLocalizedMessage(), Integer.valueOf(x2));
            }
        }
    }

    static /* synthetic */ void b(t x0, SurfaceTexture x1, int x2) {
        FLog.i("SkypeCameraInstance", "createTexture (causeId " + x2 + ")");
        if (x1 == null) {
            FLog.i("SkypeCameraInstance", "will not create texture (causeId " + x2 + ")");
            return;
        }
        x0.H = new m(1);
        x0.D = new x(x0.H, x1);
        x0.D.d();
        x0.E = new o(new w(com.skpcamera.antediluvian.w.a.TEXTURE_EXT));
        x0.F = x0.E.b();
        x0.G = new SurfaceTexture(x0.F);
        x0.a(x0.G, x2);
    }

    static /* synthetic */ void a(t x0, u x1, int x2) {
        FLog.i("SkypeCameraInstance", "unsubscribeViewFinder (causeId " + x2 + ")");
        Iterator it = x0.q.iterator();
        while (it.hasNext()) {
            u uVar = (u) ((WeakReference) it.next()).get();
            if (uVar == null || uVar.equals(x1)) {
                it.remove();
            }
        }
        x0.c(x2);
    }
}
