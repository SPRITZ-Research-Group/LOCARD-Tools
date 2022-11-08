package com.skypecam.camera2.a;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCaptureSession.StateCallback;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Handler;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.adjust.sdk.Constants;
import com.skypecam.camera2.CameraView;
import com.skypecam.camera2.g;
import com.skypecam.camera2.i;
import com.skypecam.camera2.k;
import com.skypecam.camera2.l;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010<\u001a\u00020,J\b\u0010=\u001a\u00020,H\u0002J\u0016\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020'2\u0006\u0010@\u001a\u00020'J\u0006\u0010A\u001a\u00020,J\u0010\u0010B\u001a\u00020,2\b\u0010C\u001a\u0004\u0018\u00010DJ\u0010\u0010E\u001a\u00020,2\b\u00105\u001a\u0004\u0018\u000106J\u000e\u0010F\u001a\u00020,2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010G\u001a\u00020,H\u0002J\u0006\u0010H\u001a\u00020,J\u001e\u0010I\u001a\u00020,2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020'2\u0006\u0010M\u001a\u00020'R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R(\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R(\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u001f8F@FX\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$RJ\u0010%\u001a2\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020,0&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0010\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u000e¢\u0006\u0002\n\u0000R\u0011\u00109\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b:\u0010;¨\u0006N"}, d2 = {"Lcom/skypecam/camera2/modules/CameraPreview;", "", "backgroundHandler", "Landroid/os/Handler;", "cameraInfo", "Lcom/skypecam/camera2/modules/CameraInfo;", "(Landroid/os/Handler;Lcom/skypecam/camera2/modules/CameraInfo;)V", "_targetSurface", "Landroid/view/Surface;", "getBackgroundHandler", "()Landroid/os/Handler;", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "getCameraInfo", "()Lcom/skypecam/camera2/modules/CameraInfo;", "cameraStillCapture", "Lcom/skypecam/camera2/modules/CameraStillCapture;", "value", "Lcom/skypecam/camera2/CameraView;", "cameraView", "getCameraView", "()Lcom/skypecam/camera2/CameraView;", "setCameraView", "(Lcom/skypecam/camera2/CameraView;)V", "captureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "captureSessionLock", "Ljava/util/concurrent/atomic/AtomicBoolean;", "cropRegionRect", "Landroid/graphics/Rect;", "firstFrameLock", "Lcom/skypecam/camera2/ImageCapturedCallback;", "imageCapturedCallback", "getImageCapturedCallback", "()Lcom/skypecam/camera2/ImageCapturedCallback;", "setImageCapturedCallback", "(Lcom/skypecam/camera2/ImageCapturedCallback;)V", "onPreviewSurfaceAvailable", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "width", "height", "", "getOnPreviewSurfaceAvailable", "()Lkotlin/jvm/functions/Function2;", "setOnPreviewSurfaceAvailable", "(Lkotlin/jvm/functions/Function2;)V", "previewRequest", "Landroid/hardware/camera2/CaptureRequest;", "previewRequestBuilder", "Landroid/hardware/camera2/CaptureRequest$Builder;", "qrCodeDetector", "Lcom/skypecam/camera2/modules/QRCodeDetector;", "savedZoomLevel", "", "targetSurface", "getTargetSurface", "()Landroid/view/Surface;", "capture", "initialize", "lockFocusOn", "screenX", "screenY", "resetCameraDevice", "setFocusTarget", "target", "Lcom/skypecam/camera2/FocusTarget;", "setQRCodeDetector", "startPreview", "startPreviewRequest", "stopPreview", "zoom", "complete", "", "distance", "initialDistance", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class d {
    private e a = new e(this.n, this.o);
    private CameraDevice b;
    private CameraCaptureSession c;
    private Builder d;
    private CaptureRequest e;
    @Nullable
    private CameraView f;
    @NotNull
    private kotlin.jvm.a.b<? super Integer, ? super Integer, f> g = c.a;
    private Surface h;
    private final AtomicBoolean i = new AtomicBoolean(true);
    private Rect j;
    private float k = 1.0f;
    private g l;
    private AtomicBoolean m = new AtomicBoolean(false);
    @NotNull
    private final Handler n;
    @NotNull
    private final a o;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000e¸\u0006\u0000"}, d2 = {"com/skypecam/camera2/modules/CameraPreview$initialize$1$1", "Landroid/view/TextureView$SurfaceTextureListener;", "(Lcom/skypecam/camera2/modules/CameraPreview$initialize$1;)V", "onSurfaceTextureAvailable", "", "texture", "Landroid/graphics/SurfaceTexture;", "width", "", "height", "onSurfaceTextureDestroyed", "", "onSurfaceTextureSizeChanged", "onSurfaceTextureUpdated", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a implements SurfaceTextureListener {
        final /* synthetic */ d a;

        a(d dVar) {
            this.a = dVar;
        }

        public final void onSurfaceTextureAvailable(@NotNull SurfaceTexture texture, int width, int height) {
            kotlin.jvm.b.c.b(texture, "texture");
            l.a("OnSurfaceTextureAvailable " + width + " x " + height);
            this.a.b().a(Integer.valueOf(width), Integer.valueOf(height));
            com.skypecam.camera2.CameraView.a aVar = CameraView.a;
            CameraView a = this.a.a();
            com.skypecam.camera2.CameraView.a.a(a != null ? a.a() : null, this.a.i().f(), this.a.i().b());
        }

        public final void onSurfaceTextureSizeChanged(@NotNull SurfaceTexture texture, int width, int height) {
            kotlin.jvm.b.c.b(texture, "texture");
            l.a("onSurfaceTextureSizeChanged " + width + " x " + height);
            com.skypecam.camera2.CameraView.a aVar = CameraView.a;
            CameraView a = this.a.a();
            com.skypecam.camera2.CameraView.a.a(a != null ? a.a() : null, this.a.i().f(), this.a.i().b());
            a = this.a.a();
            if (a != null) {
                a.b();
            }
        }

        public final boolean onSurfaceTextureDestroyed(@NotNull SurfaceTexture texture) {
            kotlin.jvm.b.c.b(texture, "texture");
            return true;
        }

        public final void onSurfaceTextureUpdated(@NotNull SurfaceTexture texture) {
            kotlin.jvm.b.c.b(texture, "texture");
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000G\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b¸\u0006\r"}, d2 = {"com/skypecam/camera2/modules/CameraPreview$lockFocusOn$1$1$2$1", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "(Lcom/skypecam/camera2/modules/CameraPreview$lockFocusOn$1$1$2;)V", "onCaptureCompleted", "", "session", "Landroid/hardware/camera2/CameraCaptureSession;", "request", "Landroid/hardware/camera2/CaptureRequest;", "result", "Landroid/hardware/camera2/TotalCaptureResult;", "react-native-camera2lib_release", "com/skypecam/camera2/modules/CameraPreview$$special$$inlined$apply$lambda$1", "com/skypecam/camera2/modules/CameraPreview$$special$$inlined$also$lambda$1"}, k = 1, mv = {1, 1, 10})
    public static final class b extends CaptureCallback {
        final /* synthetic */ MeteringRectangle a;
        final /* synthetic */ CameraView b;
        final /* synthetic */ d c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;

        b(MeteringRectangle meteringRectangle, CameraView cameraView, d dVar, int i, int i2) {
            this.a = meteringRectangle;
            this.b = cameraView;
            this.c = dVar;
            this.d = i;
            this.e = i2;
        }

        public final void onCaptureCompleted(@Nullable CameraCaptureSession session, @Nullable CaptureRequest request, @Nullable TotalCaptureResult result) {
            super.onCaptureCompleted(session, request, result);
            Integer num = result != null ? (Integer) result.get(CaptureResult.CONTROL_AF_STATE) : null;
            Builder $receiver;
            CameraCaptureSession b;
            i c;
            if (num != null && num.intValue() == 4) {
                $receiver = this.c.d;
                if ($receiver != null) {
                    $receiver.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                    $receiver.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(true));
                    b = this.c.c;
                    if (b != null) {
                        b.setRepeatingRequest($receiver.build(), null, this.c.h());
                    }
                }
                c = com.skypecam.camera2.e.c();
                if (c != null) {
                    c.a(com.skypecam.camera2.i.a.a.b);
                }
                this.c.h().sendEmptyMessageDelayed(3, 750);
            } else if (num != null && num.intValue() == 5) {
                $receiver = this.c.d;
                if ($receiver != null) {
                    $receiver.set(CaptureRequest.CONTROL_AF_REGIONS, null);
                    $receiver.set(CaptureRequest.CONTROL_AE_REGIONS, null);
                    $receiver.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(4));
                    $receiver.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                    b = this.c.c;
                    if (b != null) {
                        b.setRepeatingRequest($receiver.build(), null, this.c.h());
                    }
                }
                c = com.skypecam.camera2.e.c();
                if (c != null) {
                    c.a(com.skypecam.camera2.i.a.a.c);
                }
                this.c.h().sendEmptyMessageDelayed(3, 750);
                g.d("Tap to focus failed");
            } else {
                c = com.skypecam.camera2.e.c();
                if (c != null) {
                    c.a(com.skypecam.camera2.i.a.a.a);
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "<anonymous parameter 1>", "invoke"}, k = 3, mv = {1, 1, 10})
    static final class c extends kotlin.jvm.b.d implements kotlin.jvm.a.b<Integer, Integer, f> {
        public static final c a = new c();

        c() {
            super(2);
        }

        public final /* synthetic */ Object a(Object obj, Object obj2) {
            ((Number) obj).intValue();
            ((Number) obj2).intValue();
            return f.a;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"com/skypecam/camera2/modules/CameraPreview$startPreview$1", "Landroid/hardware/camera2/CameraCaptureSession$StateCallback;", "(Lcom/skypecam/camera2/modules/CameraPreview;Landroid/hardware/camera2/CameraDevice;)V", "onConfigureFailed", "", "session", "Landroid/hardware/camera2/CameraCaptureSession;", "onConfigured", "cameraCaptureSession", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class d extends StateCallback {
        final /* synthetic */ d a;
        final /* synthetic */ CameraDevice b;

        d(d $outer, CameraDevice $captured_local_variable$1) {
            this.a = $outer;
            this.b = $captured_local_variable$1;
        }

        public final void onConfigured(@NotNull CameraCaptureSession cameraCaptureSession) {
            kotlin.jvm.b.c.b(cameraCaptureSession, "cameraCaptureSession");
            if (this.a.b != null) {
                l.a("CaptureSession successfully created, starting preview");
                this.a.c = cameraCaptureSession;
                this.a.j();
            }
        }

        public final void onConfigureFailed(@NotNull CameraCaptureSession session) {
            kotlin.jvm.b.c.b(session, "session");
            if (this.a.m.getAndSet(true)) {
                kotlin.jvm.b.c.b("Failed to create capture session", "message");
                g.a("Camera preview failed: session configuration failed");
                return;
            }
            g.d("Camera preview failed: first session configuration attempt failed");
            this.a.a(this.b);
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"com/skypecam/camera2/modules/CameraPreview$startPreviewRequest$1", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "(Lcom/skypecam/camera2/modules/CameraPreview;)V", "onCaptureCompleted", "", "session", "Landroid/hardware/camera2/CameraCaptureSession;", "request", "Landroid/hardware/camera2/CaptureRequest;", "result", "Landroid/hardware/camera2/TotalCaptureResult;", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class e extends CaptureCallback {
        final /* synthetic */ d a;

        e(d $outer) {
            this.a = $outer;
        }

        public final void onCaptureCompleted(@Nullable CameraCaptureSession session, @Nullable CaptureRequest request, @Nullable TotalCaptureResult result) {
            if (this.a.i.getAndSet(false)) {
                g.b();
            }
        }
    }

    public d(@NotNull Handler backgroundHandler, @NotNull a cameraInfo) {
        kotlin.jvm.b.c.b(backgroundHandler, "backgroundHandler");
        kotlin.jvm.b.c.b(cameraInfo, "cameraInfo");
        this.n = backgroundHandler;
        this.o = cameraInfo;
    }

    @NotNull
    public final Handler h() {
        return this.n;
    }

    @NotNull
    public final a i() {
        return this.o;
    }

    @Nullable
    public final CameraView a() {
        return this.f;
    }

    public final void a(@Nullable CameraView value) {
        l.a("Initializing camera view " + value);
        this.f = value;
        CameraView cameraView = this.f;
        if (cameraView != null) {
            TextureView a = cameraView.a();
            if (a != null) {
                a.setSurfaceTextureListener(new a(this));
                if (a.isAvailable()) {
                    this.g.a(Integer.valueOf(a.getWidth()), Integer.valueOf(a.getHeight()));
                }
            }
        }
    }

    public final void a(@NotNull kotlin.jvm.a.b<? super Integer, ? super Integer, f> <set-?>) {
        kotlin.jvm.b.c.b(<set-?>, "<set-?>");
        this.g = <set-?>;
    }

    @NotNull
    public final kotlin.jvm.a.b<Integer, Integer, f> b() {
        return this.g;
    }

    @NotNull
    public final Surface c() {
        Surface surface = null;
        if (this.h == null) {
            SurfaceTexture cameraPreviewSurfaceTexture;
            d this;
            CameraView cameraView = this.f;
            if (cameraView != null) {
                TextureView a = cameraView.a();
                if (a != null) {
                    cameraPreviewSurfaceTexture = a.getSurfaceTexture();
                    if (cameraPreviewSurfaceTexture != null) {
                        cameraPreviewSurfaceTexture.setDefaultBufferSize(this.o.f().getWidth(), this.o.f().getHeight());
                    }
                    if (cameraPreviewSurfaceTexture == null) {
                        surface = new Surface(cameraPreviewSurfaceTexture);
                        this = this;
                    } else {
                        this = this;
                    }
                    this.h = surface;
                }
            }
            cameraPreviewSurfaceTexture = null;
            if (cameraPreviewSurfaceTexture != null) {
                cameraPreviewSurfaceTexture.setDefaultBufferSize(this.o.f().getWidth(), this.o.f().getHeight());
            }
            if (cameraPreviewSurfaceTexture == null) {
                this = this;
            } else {
                surface = new Surface(cameraPreviewSurfaceTexture);
                this = this;
            }
            this.h = surface;
        }
        surface = this.h;
        if (surface != null) {
            return surface;
        }
        throw new AssertionError("Set to null by another thread");
    }

    @Nullable
    public final k d() {
        return this.a.c();
    }

    public final void a(@Nullable k value) {
        this.a.a(value);
    }

    public final void e() {
        this.b = null;
    }

    public final void a(@NotNull CameraDevice cameraDevice) {
        TextureView textureView = null;
        kotlin.jvm.b.c.b(cameraDevice, "cameraDevice");
        this.b = cameraDevice;
        this.j = null;
        com.skypecam.camera2.CameraView.a aVar = CameraView.a;
        CameraView cameraView = this.f;
        if (cameraView != null) {
            textureView = cameraView.a();
        }
        com.skypecam.camera2.CameraView.a.a(textureView, this.o.f(), this.o.b());
        if (this.c != null) {
            j();
            return;
        }
        try {
            cameraDevice.createCaptureSession(Arrays.asList(new Surface[]{c(), this.a.b()}), new d(this, cameraDevice), null);
        } catch (Exception e) {
            g.a("Camera preview failed: " + e.getMessage());
        }
    }

    private final void j() {
        CaptureRequest captureRequest = null;
        try {
            CameraDevice cameraDevice = this.b;
            this.d = cameraDevice != null ? cameraDevice.createCaptureRequest(1) : null;
            Builder builder = this.d;
            if (builder != null) {
                builder.addTarget(c());
            }
            builder = this.d;
            if (builder != null) {
                builder.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(4));
            }
            a(null);
            com.skypecam.camera2.a.a.b bVar = a.a;
            com.skypecam.camera2.a.a.b.a(this.d, this.o.e());
            builder = this.d;
            if (builder != null) {
                captureRequest = builder.build();
            }
            this.e = captureRequest;
            CameraCaptureSession cameraCaptureSession = this.c;
            if (cameraCaptureSession != null) {
                cameraCaptureSession.setRepeatingRequest(this.e, new e(this), this.n);
            }
            g.a();
        } catch (Exception e) {
            kotlin.jvm.b.c.b("Unable to start preview", "message");
            e.printStackTrace();
            g.a("Camera preview request failed: " + e.getMessage());
        }
    }

    public final void f() {
        try {
            CameraCaptureSession $receiver = this.c;
            if ($receiver != null) {
                $receiver.stopRepeating();
                $receiver.abortCaptures();
                $receiver.close();
            }
        } catch (Exception e) {
            kotlin.jvm.b.c.b("Unable to stop preview", "message");
            e.printStackTrace();
            g.d("Unable to stop preview: " + e.getMessage());
        }
        this.c = null;
        this.h = null;
        this.m.set(false);
    }

    public final void a(int screenX, int screenY) {
        if (this.c == null) {
            kotlin.jvm.b.c.b("Preview not running, ignoring tap to focus", "message");
        } else if (this.o.j()) {
            CameraView cameraView = this.f;
            if (cameraView != null) {
                Rect sensorRect = this.o.k();
                if (sensorRect != null) {
                    kotlin.c cVar;
                    a(new i(new PointF((float) screenX, (float) screenY)));
                    com.skypecam.camera2.a.a.b bVar = a.a;
                    int width = cameraView.getWidth();
                    int height = cameraView.getHeight();
                    int width2 = sensorRect.width();
                    int height2 = sensorRect.height();
                    float f = ((float) screenX) / ((float) width);
                    float f2 = ((float) screenY) / ((float) height);
                    switch (this.o.d()) {
                        case 0:
                            cVar = new kotlin.c(Float.valueOf(f), Float.valueOf(f2));
                            break;
                        case 90:
                            cVar = new kotlin.c(Float.valueOf(f2), Float.valueOf(1.0f - f));
                            break;
                        case 180:
                            cVar = new kotlin.c(Float.valueOf(1.0f - f), Float.valueOf(1.0f - f2));
                            break;
                        case 270:
                            cVar = new kotlin.c(Float.valueOf(1.0f - f2), Float.valueOf(f));
                            break;
                        default:
                            cVar = new kotlin.c(Float.valueOf(f), Float.valueOf(f2));
                            break;
                    }
                    kotlin.c sensorPoint = new kotlin.c(Integer.valueOf((int) (((float) width2) * ((Number) cVar.a()).floatValue())), Integer.valueOf((int) (((Number) cVar.b()).floatValue() * ((float) height2))));
                    MeteringRectangle meteringRectangle = new MeteringRectangle(new Point(((Number) sensorPoint.a()).intValue(), ((Number) sensorPoint.b()).intValue()), new Size(300, 300), Constants.ONE_SECOND);
                    Builder $receiver = this.d;
                    if ($receiver != null) {
                        $receiver.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(2));
                        $receiver.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(0));
                        $receiver.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(false));
                        CameraCaptureSession cameraCaptureSession = this.c;
                        if (cameraCaptureSession != null) {
                            cameraCaptureSession.capture($receiver.build(), null, this.n);
                        }
                    }
                    $receiver = this.d;
                    if ($receiver != null) {
                        $receiver.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{meteringRectangle});
                        $receiver.set(CaptureRequest.CONTROL_AE_REGIONS, new MeteringRectangle[]{meteringRectangle});
                        $receiver.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(1));
                        $receiver.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(1));
                        $receiver.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(1));
                        CameraCaptureSession cameraCaptureSession2 = this.c;
                        if (cameraCaptureSession2 != null) {
                            CaptureRequest build;
                            Builder builder = this.d;
                            if (builder != null) {
                                build = builder.build();
                            } else {
                                build = null;
                            }
                            cameraCaptureSession2.setRepeatingRequest(build, new b(meteringRectangle, cameraView, this, screenX, screenY), this.n);
                        }
                    }
                }
            }
        } else {
            kotlin.jvm.b.c.b("Camera does not support touch to focus, ignoring event", "message");
            a(null);
        }
    }

    public final void g() {
        CameraView $receiver = this.f;
        if ($receiver != null) {
            Size previewSize;
            com.skypecam.camera2.a.a.b bVar = a.a;
            if (a.w) {
                switch (this.o.c()) {
                    case 1:
                    case 3:
                        previewSize = new Size($receiver.getHeight(), $receiver.getWidth());
                        break;
                    default:
                        previewSize = new Size($receiver.getWidth(), $receiver.getHeight());
                        break;
                }
            }
            previewSize = new Size($receiver.getWidth(), $receiver.getHeight());
            this.a.a(this.b, this.c, this.j, previewSize);
        }
    }

    public final void a(boolean complete, int distance, int initialDistance) {
        float currentZoomLevel = 1.0f;
        Rect $receiver = this.o.k();
        if ($receiver != null) {
            float f = (this.k * ((float) distance)) / ((float) initialDistance);
            float l = this.o.l();
            if (1.0f > l) {
                throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + l + " is less than minimum 1.0.");
            }
            if (f >= 1.0f) {
                currentZoomLevel = f > l ? l : f;
            }
            int cropRegionWidth = $receiver.width() - ((int) (((float) $receiver.width()) / currentZoomLevel));
            int cropRegionHeight = $receiver.height() - ((int) (((float) $receiver.height()) / currentZoomLevel));
            this.j = new Rect(cropRegionWidth / 2, cropRegionHeight / 2, $receiver.width() - (cropRegionWidth / 2), $receiver.height() - (cropRegionHeight / 2));
            $receiver = this.d;
            if ($receiver != null) {
                $receiver.set(CaptureRequest.SCALER_CROP_REGION, this.j);
                CameraCaptureSession cameraCaptureSession = this.c;
                if (cameraCaptureSession != null) {
                    cameraCaptureSession.setRepeatingRequest($receiver.build(), null, this.n);
                }
            }
            if (complete) {
                this.k = currentZoomLevel;
            }
        }
    }

    public final void a(@Nullable i target) {
        this.n.removeMessages(3);
        com.skypecam.camera2.e.a(target);
        CameraView cameraView = this.f;
        if (cameraView != null) {
            cameraView.postInvalidate();
        }
    }

    public final void a(@Nullable g qrCodeDetector) {
        this.l = qrCodeDetector;
    }
}
