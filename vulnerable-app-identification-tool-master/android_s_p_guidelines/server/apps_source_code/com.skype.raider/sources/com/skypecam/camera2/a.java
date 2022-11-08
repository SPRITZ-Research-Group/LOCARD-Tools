package com.skypecam.camera2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.skypecam.camera2.a.d;
import com.skypecam.camera2.a.f;
import com.skypecam.camera2.a.g;
import kotlin.Metadata;
import kotlin.jvm.b.c;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001#\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u00105\u001a\u000201J\u000e\u00106\u001a\u0002012\u0006\u00107\u001a\u000208J\u000e\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020\u000bJ\b\u0010;\u001a\u000201H\u0002J\u0016\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020\u000b2\u0006\u0010>\u001a\u00020\u000bJ\u0012\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J \u0010C\u001a\u0002012\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u000bH\u0003J\u0006\u0010D\u001a\u000201J\u000e\u0010E\u001a\u0002012\u0006\u0010F\u001a\u00020GJ\u000e\u0010H\u001a\u0002012\u0006\u0010I\u001a\u00020JJ\u000e\u0010K\u001a\u0002012\u0006\u0010L\u001a\u00020MJ\u0010\u0010N\u001a\u0002012\b\u0010O\u001a\u0004\u0018\u00010PJ\u000e\u0010Q\u001a\u0002012\u0006\u0010R\u001a\u00020@J\u000e\u0010S\u001a\u0002012\u0006\u0010T\u001a\u00020\u000bJ\u000e\u0010U\u001a\u0002012\u0006\u00100\u001a\u00020\u000bJ\u000e\u0010V\u001a\u0002012\u0006\u0010W\u001a\u00020\u000bJ\u000e\u0010X\u001a\u0002012\u0006\u0010Y\u001a\u00020\u000bJ\u000e\u0010Z\u001a\u0002012\u0006\u0010/\u001a\u00020\u000bJ\u000e\u0010[\u001a\u0002012\u0006\u00107\u001a\u00020\\J\u0006\u0010]\u001a\u000201J\u001e\u0010^\u001a\u0002012\u0006\u0010_\u001a\u00020@2\u0006\u0010`\u001a\u00020\u000b2\u0006\u0010a\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8F@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0014\u0010\u0015R*\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\n\u001a\u0004\u0018\u00010\u00168F@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u00020#X\u0004¢\u0006\u0004\n\u0002\u0010$R\u000e\u0010%\u001a\u00020&X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R>\u0010+\u001a2\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002010,X\u0004¢\u0006\u0002\n\u0000R\u001c\u00102\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0019\"\u0004\b4\u0010\u001b¨\u0006b"}, d2 = {"Lcom/skypecam/camera2/Camera2;", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "Landroid/os/Handler$Callback;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "backgroundHandler", "Landroid/os/Handler;", "backgroundThread", "Landroid/os/HandlerThread;", "<set-?>", "", "cameraCount", "getCameraCount", "()I", "setCameraCount", "(I)V", "value", "Landroid/hardware/camera2/CameraDevice;", "cameraDevice", "setCameraDevice", "(Landroid/hardware/camera2/CameraDevice;)V", "", "cameraId", "getCameraId", "()Ljava/lang/String;", "setCameraId", "(Ljava/lang/String;)V", "cameraInfo", "Lcom/skypecam/camera2/modules/CameraInfo;", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "cameraPreview", "Lcom/skypecam/camera2/modules/CameraPreview;", "cameraStateCallback", "com/skypecam/camera2/Camera2$cameraStateCallback$1", "Lcom/skypecam/camera2/Camera2$cameraStateCallback$1;", "cameraVideoRecorder", "Lcom/skypecam/camera2/modules/CameraVideoRecorder;", "globalHardwareLevel", "Lcom/skypecam/camera2/HardwareLevel;", "getGlobalHardwareLevel", "()Lcom/skypecam/camera2/HardwareLevel;", "onPreviewSurfaceAvailable", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "width", "height", "", "pendingCameraIdRequest", "getPendingCameraIdRequest", "setPendingCameraIdRequest", "cancelRecording", "capture", "callback", "Lcom/skypecam/camera2/ImageCapturedCallback;", "chooseCamera", "cameraType", "closeCamera", "focus", "x", "y", "handleMessage", "", "msg", "Landroid/os/Message;", "openCamera", "release", "setCameraView", "cameraView", "Lcom/skypecam/camera2/CameraView;", "setFlashMode", "flashMode", "Lcom/skypecam/camera2/FlashMode;", "setMaxRecordingTime", "ms", "", "setQRCodeListener", "listener", "Lcom/skypecam/camera2/QRCodeListener;", "setUseSensorOrientation", "useSensorOrientation", "setVideoBitRate", "bitRate", "setVideoHeight", "setVideoThumbnailCompressionRate", "rate", "setVideoThumbnailMaxDimension", "size", "setVideoWidth", "startRecording", "Lcom/skypecam/camera2/VideoCapturedCallback;", "stopRecording", "zoom", "gestureCompleted", "distance", "initialDistance", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class a extends CaptureCallback implements Callback {
    private final com.skypecam.camera2.a.a a;
    private final d b;
    private final f c;
    private HandlerThread d;
    private Handler e;
    private CameraManager f;
    private CameraDevice g;
    @NotNull
    private final j h;
    @Nullable
    private String i;
    private final a j;
    private final kotlin.jvm.a.b<Integer, Integer, kotlin.f> k;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"com/skypecam/camera2/Camera2$cameraStateCallback$1", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "(Lcom/skypecam/camera2/Camera2;)V", "onClosed", "", "camera", "Landroid/hardware/camera2/CameraDevice;", "onDisconnected", "cameraDevice", "onError", "error", "", "onOpened", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
    public static final class a extends StateCallback {
        final /* synthetic */ a a;

        a(a $outer) {
            this.a = $outer;
        }

        public final void onClosed(@Nullable CameraDevice camera) {
            String id;
            StringBuilder stringBuilder = new StringBuilder("Camera ");
            CameraDevice a = this.a.g;
            if (a != null) {
                id = a.getId();
            } else {
                id = null;
            }
            l.a(stringBuilder.append(id).append(" closed").toString());
            super.onClosed(camera);
            a.a(this.a, null);
        }

        public final void onOpened(@NotNull CameraDevice cameraDevice) {
            c.b(cameraDevice, "cameraDevice");
            l.a("Camera " + cameraDevice.getId() + " opened");
            a.a(this.a, cameraDevice);
            this.a.b.a(cameraDevice);
        }

        public final void onDisconnected(@NotNull CameraDevice cameraDevice) {
            c.b(cameraDevice, "cameraDevice");
            l.a("Camera " + cameraDevice.getId() + " disconnected");
            cameraDevice.close();
        }

        public final void onError(@NotNull CameraDevice cameraDevice, int error) {
            String errorString;
            c.b(cameraDevice, "cameraDevice");
            c.b("Camera " + cameraDevice.getId() + " error " + error, "message");
            cameraDevice.close();
            switch (error) {
                case 1:
                    errorString = "Camera in use";
                    break;
                case 2:
                    errorString = "Too many open cameras";
                    break;
                case 3:
                    errorString = "Camera disabled";
                    break;
                case 4:
                    errorString = "Fatal error";
                    break;
                case 5:
                    errorString = "Service fatal error";
                    break;
                default:
                    errorString = "Unknown error";
                    break;
            }
            g.a("Camera open failed: " + errorString);
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "width", "", "height", "invoke"}, k = 3, mv = {1, 1, 10})
    static final class b extends kotlin.jvm.b.d implements kotlin.jvm.a.b<Integer, Integer, kotlin.f> {
        final /* synthetic */ a a;

        b(a aVar) {
            this.a = aVar;
            super(2);
        }

        public final /* synthetic */ Object a(Object obj, Object obj2) {
            int intValue = ((Number) obj).intValue();
            int intValue2 = ((Number) obj2).intValue();
            a aVar = this.a;
            if (aVar.b() != null) {
                l.a("Pending camera request for id " + aVar.b() + " found!");
                String b = aVar.b();
                if (b == null) {
                    c.a();
                }
                aVar.a(b, intValue, intValue2);
                aVar.c();
            } else if (aVar.a() != null) {
                CameraDevice a = aVar.g;
                if (a != null) {
                    aVar.b.a(a);
                }
            }
            return kotlin.f.a;
        }
    }

    public a(@NotNull Context context) {
        c.b(context, "context");
        l.a("Camera 2 new instance created");
        HandlerThread handlerThread = new HandlerThread("CameraBackground");
        handlerThread.start();
        this.d = handlerThread;
        handlerThread = this.d;
        this.e = new Handler(handlerThread != null ? handlerThread.getLooper() : null, this);
        this.a = new com.skypecam.camera2.a.a(context);
        this.b = new d(this.e, this.a);
        this.c = new f(this.e, this.a, this.b);
        this.f = (CameraManager) context.getSystemService("camera");
        this.h = c.a(context);
        e.a(this.h);
        this.j = new a(this);
        this.k = new b(this);
        this.b.a(this.k);
    }

    @Nullable
    public final String a() {
        return this.a.a();
    }

    @Nullable
    public final String b() {
        return this.i;
    }

    public final void c() {
        this.i = null;
    }

    public final int d() {
        CameraManager cameraManager = this.f;
        if (cameraManager != null) {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList != null) {
                return cameraIdList.length;
            }
        }
        return 0;
    }

    public final void a(@NotNull CameraView cameraView) {
        c.b(cameraView, "cameraView");
        this.b.a(cameraView);
        this.c.a(cameraView);
    }

    public final void a(@NotNull k callback) {
        c.b(callback, "callback");
        if (this.e.hasMessages(1)) {
            c.b("Still capture session already in progress", "message");
            return;
        }
        this.e.sendEmptyMessageDelayed(1, 2500);
        this.b.a(callback);
        this.b.g();
    }

    public final void a(@NotNull o callback) {
        c.b(callback, "callback");
        if (this.e.hasMessages(2)) {
            c.b("Video capture session already in progress", "message");
            return;
        }
        this.e.sendEmptyMessageDelayed(2, 2500);
        CameraDevice it = this.g;
        if (it != null) {
            this.c.a(it, callback);
        }
    }

    public final void e() {
        this.c.f();
    }

    public final void f() {
        this.c.g();
    }

    public final void a(float ms) {
        this.c.a((int) ms);
    }

    public final void a(int width) {
        this.a.b(width);
    }

    public final void b(int height) {
        this.a.c(height);
    }

    public final void c(int bitRate) {
        this.c.b(bitRate);
    }

    public final void d(int rate) {
        this.c.c(rate);
    }

    public final void e(int size) {
        this.c.d(size);
    }

    public final void a(int x, int y) {
        this.b.a(x, y);
    }

    public final void a(boolean gestureCompleted, int distance, int initialDistance) {
        this.b.a(gestureCompleted, distance, initialDistance);
    }

    public final void a(@Nullable m listener) {
        Context context = null;
        if (listener == null) {
            this.b.a(null);
            return;
        }
        d dVar = this.b;
        CameraView a = this.b.a();
        if (a != null) {
            context = a.getContext();
        }
        dVar.a(new g(context, listener));
    }

    public static void a(boolean useSensorOrientation) {
        com.skypecam.camera2.a.a.b bVar = com.skypecam.camera2.a.a.a;
        com.skypecam.camera2.a.a.w = useSensorOrientation;
    }

    @SuppressLint({"MissingPermission"})
    private final void a(String cameraId, int width, int height) {
        l.a("Open camera " + cameraId + " on surface size " + width + 'x' + height);
        CameraManager it = this.f;
        if (it != null) {
            try {
                this.a.a(it, cameraId, width, height);
                it.openCamera(this.a.a(), this.j, this.e);
            } catch (Exception e) {
                c.b("Camera setup failed", "message");
                e.printStackTrace();
                g.a("Camera setup failed: " + e.getMessage());
            }
        }
    }

    private final void h() {
        this.b.f();
        CameraDevice cameraDevice = this.g;
        if (cameraDevice != null) {
            cameraDevice.close();
        }
    }

    public final void a(@NotNull h flashMode) {
        c.b(flashMode, "flashMode");
        this.a.a(flashMode);
    }

    public final void f(int cameraType) {
        int i = 1;
        int requestedLensDirection;
        switch (cameraType) {
            case 1:
                l.a("Looking for a front facing camera");
                requestedLensDirection = 0;
                break;
            case 2:
                l.a("Looking for a back facing camera");
                requestedLensDirection = 1;
                break;
            default:
                c.b("Unknown camera type " + cameraType + ", defaulting to back camera", "message");
                requestedLensDirection = 1;
                break;
        }
        CameraManager it = this.f;
        if (it != null) {
            Object cameraIdList = it.getCameraIdList();
            c.a(cameraIdList, "it.cameraIdList");
            if (cameraIdList.length != 0) {
                i = 0;
            }
            if (i != 0) {
                c.b("No cameras available!", "message");
                return;
            }
            CameraView a;
            int width;
            CameraView a2;
            String selectedCameraId = null;
            String[] cameraIdList2 = it.getCameraIdList();
            int length = cameraIdList2.length;
            i = 0;
            while (i < length) {
                String id = cameraIdList2[i];
                Integer cameraDirection = (Integer) it.getCameraCharacteristics(id).get(CameraCharacteristics.LENS_FACING);
                if (cameraDirection == null || cameraDirection.intValue() != requestedLensDirection) {
                    i++;
                } else {
                    selectedCameraId = id;
                    if (selectedCameraId == null) {
                        l.a("Couldn't find matching camera, defaulting to first available");
                        selectedCameraId = it.getCameraIdList()[0];
                    }
                    if (c.a((Object) selectedCameraId, this.a.a())) {
                        if (this.b.a() != null) {
                            a = this.b.a();
                            if (a == null || a.getWidth() != 0) {
                                a = this.b.a();
                                if (a == null || a.getHeight() != 0) {
                                    h();
                                    if (selectedCameraId == null) {
                                        c.a();
                                    }
                                    a = this.b.a();
                                    if (a == null) {
                                        c.a();
                                    }
                                    width = a.getWidth();
                                    a2 = this.b.a();
                                    if (a2 == null) {
                                        c.a();
                                    }
                                    a(selectedCameraId, width, a2.getHeight());
                                    return;
                                }
                            }
                        }
                        l.a("Trying to open camera " + selectedCameraId + " but surface not available yet...");
                        this.i = selectedCameraId;
                        return;
                    }
                    l.a("Camera " + this.a.a() + " already opened!");
                }
            }
            if (selectedCameraId == null) {
                l.a("Couldn't find matching camera, defaulting to first available");
                selectedCameraId = it.getCameraIdList()[0];
            }
            if (c.a((Object) selectedCameraId, this.a.a())) {
                if (this.b.a() != null) {
                    a = this.b.a();
                    a = this.b.a();
                    h();
                    if (selectedCameraId == null) {
                        c.a();
                    }
                    a = this.b.a();
                    if (a == null) {
                        c.a();
                    }
                    width = a.getWidth();
                    a2 = this.b.a();
                    if (a2 == null) {
                        c.a();
                    }
                    a(selectedCameraId, width, a2.getHeight());
                    return;
                }
                l.a("Trying to open camera " + selectedCameraId + " but surface not available yet...");
                this.i = selectedCameraId;
                return;
            }
            l.a("Camera " + this.a.a() + " already opened!");
        }
    }

    public final void g() {
        l.a("Camera2 release");
        h();
        this.a.n();
        this.e.removeMessages(1);
        this.e.removeMessages(2);
        HandlerThread handlerThread = this.d;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        try {
            handlerThread = this.d;
            if (handlerThread != null) {
                handlerThread.join();
            }
            this.d = null;
        } catch (InterruptedException e) {
            e.toString();
        }
    }

    public final boolean handleMessage(@Nullable Message msg) {
        if (msg == null) {
            return false;
        }
        switch (msg.what) {
            case 1:
                c.b("Still capture timeout!", "message");
                g.b("Timeout");
                k d = this.b.d();
                if (d == null) {
                    return true;
                }
                d.a("Timeout");
                return true;
            case 2:
                c.b("Video capture timeout!", "message");
                g.c("Timeout");
                o c = this.c.c();
                if (c == null) {
                    return true;
                }
                c.a("Timeout");
                return true;
            case 3:
                this.b.a(null);
                return true;
            default:
                return false;
        }
    }

    public static final /* synthetic */ void a(a $this, @Nullable CameraDevice <set-?>) {
        $this.g = <set-?>;
        if (<set-?> == null) {
            $this.b.e();
        }
    }
}
