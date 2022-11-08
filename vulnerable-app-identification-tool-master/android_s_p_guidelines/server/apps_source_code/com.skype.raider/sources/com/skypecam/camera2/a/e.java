package com.skypecam.camera2.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCaptureSession.CaptureCallback;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureRequest.Builder;
import android.hardware.camera2.TotalCaptureResult;
import android.media.ExifInterface;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Handler;
import android.util.Size;
import android.view.Surface;
import com.skype.Defines;
import com.skypecam.camera2.g;
import com.skypecam.camera2.k;
import com.skypecam.camera2.l;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.f;
import kotlin.jvm.b.d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J,\u0010.\u001a\u00020\u00172\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020%J\n\u00106\u001a\u0004\u0018\u00010%H\u0002J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0002J&\u0010\u0015\u001a\u00020\u00172\b\u0010;\u001a\u0004\u0018\u0001022\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J&\u0010@\u001a\u00020\u00172\b\u0010;\u001a\u0004\u0018\u0001022\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010C\u001a\u00020\u0017H\u0002J\u000e\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020FR\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0013\u0010*\u001a\u0004\u0018\u00010+8F¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006G"}, d2 = {"Lcom/skypecam/camera2/modules/CameraStillCapture;", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "backgroundHandler", "Landroid/os/Handler;", "cameraInfo", "Lcom/skypecam/camera2/modules/CameraInfo;", "(Landroid/os/Handler;Lcom/skypecam/camera2/modules/CameraInfo;)V", "IMAGE_BUFFER_SIZE", "", "getBackgroundHandler", "()Landroid/os/Handler;", "getCameraInfo", "()Lcom/skypecam/camera2/modules/CameraInfo;", "imageCapturedCallback", "Lcom/skypecam/camera2/ImageCapturedCallback;", "getImageCapturedCallback", "()Lcom/skypecam/camera2/ImageCapturedCallback;", "setImageCapturedCallback", "(Lcom/skypecam/camera2/ImageCapturedCallback;)V", "imageReader", "Landroid/media/ImageReader;", "onCaptureCompleted", "Lkotlin/Function0;", "", "getOnCaptureCompleted", "()Lkotlin/jvm/functions/Function0;", "setOnCaptureCompleted", "(Lkotlin/jvm/functions/Function0;)V", "onImageAvailableListener", "Landroid/media/ImageReader$OnImageAvailableListener;", "outputFile", "Ljava/io/File;", "getOutputFile", "()Ljava/io/File;", "setOutputFile", "(Ljava/io/File;)V", "previewTextureSize", "Landroid/util/Size;", "getPreviewTextureSize", "()Landroid/util/Size;", "setPreviewTextureSize", "(Landroid/util/Size;)V", "targetSurface", "Landroid/view/Surface;", "getTargetSurface", "()Landroid/view/Surface;", "capture", "cameraDevice", "Landroid/hardware/camera2/CameraDevice;", "captureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "cropRegion", "Landroid/graphics/Rect;", "previewSize", "cropImage", "getImageSaverRunnable", "Ljava/lang/Runnable;", "image", "Landroid/media/Image;", "session", "request", "Landroid/hardware/camera2/CaptureRequest;", "result", "Landroid/hardware/camera2/TotalCaptureResult;", "onCaptureFailed", "failure", "Landroid/hardware/camera2/CaptureFailure;", "refreshCaptureSize", "stillCaptureFailed", "reason", "", "react-native-camera2lib_release"}, k = 1, mv = {1, 1, 10})
public final class e extends CaptureCallback {
    private ImageReader a;
    private final OnImageAvailableListener b = new c(this);
    private final int c = 1;
    @Nullable
    private File d;
    @NotNull
    private kotlin.jvm.a.a<f> e = b.a;
    @Nullable
    private k f;
    @NotNull
    private Size g = new Size(0, 0);
    @NotNull
    private final Handler h;
    @NotNull
    private final a i;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 10})
    /* renamed from: com.skypecam.camera2.a.e$1 */
    static final class AnonymousClass1 extends d implements kotlin.jvm.a.a<f> {
        final /* synthetic */ e a;

        AnonymousClass1(e eVar) {
            this.a = eVar;
            super(0);
        }

        public final /* synthetic */ Object a() {
            e.b(this.a);
            return f.a;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 10})
    static final class a implements Runnable {
        final /* synthetic */ e a;
        final /* synthetic */ Image b;

        a(e eVar, Image image) {
            this.a = eVar;
            this.b = image;
        }

        public final void run() {
            IOException e;
            Throwable th;
            FileOutputStream output = null;
            try {
                Object obj = this.b.getPlanes()[0];
                kotlin.jvm.b.c.a(obj, "image.planes[0]");
                ByteBuffer buffer = obj.getBuffer();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                FileOutputStream output2 = new FileOutputStream(this.a.a());
                output2.write(bytes);
                output2.close();
                try {
                    this.b.close();
                    Size finalSize = this.a.f();
                    this.a.d().removeMessages(1);
                    k c;
                    if (finalSize != null) {
                        l.a("Image captured successfully");
                        g.a(finalSize);
                        c = this.a.c();
                        if (c != null) {
                            c.a(this.a.a(), finalSize.getWidth(), finalSize.getHeight());
                        }
                    } else {
                        kotlin.jvm.b.c.b("Image captured successfully but cropping failed", "message");
                        g.a(this.a.e().h());
                        c = this.a.c();
                        if (c != null) {
                            c.a(this.a.a(), this.a.e().h().getWidth(), this.a.e().h().getHeight());
                        }
                    }
                    try {
                        output2.close();
                        output = output2;
                    } catch (IOException e2) {
                        kotlin.jvm.b.c.b(e2.toString(), "message");
                        output = output2;
                    }
                } catch (IOException e3) {
                    e = e3;
                    output = output2;
                    try {
                        e.printStackTrace();
                        this.a.a("Unable to save file: " + e.getMessage());
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e22) {
                                kotlin.jvm.b.c.b(e22.toString(), "message");
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e4) {
                                kotlin.jvm.b.c.b(e4.toString(), "message");
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    output = output2;
                    if (output != null) {
                        output.close();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                e.printStackTrace();
                this.a.a("Unable to save file: " + e.getMessage());
                if (output != null) {
                    output.close();
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 10})
    static final class b extends d implements kotlin.jvm.a.a<f> {
        public static final b a = new b();

        b() {
            super(0);
        }

        public final /* bridge */ /* synthetic */ Object a() {
            return f.a;
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/media/ImageReader;", "kotlin.jvm.PlatformType", "onImageAvailable"}, k = 3, mv = {1, 1, 10})
    static final class c implements OnImageAvailableListener {
        final /* synthetic */ e a;

        c(e eVar) {
            this.a = eVar;
        }

        public final void onImageAvailable(ImageReader it) {
            Handler d = this.a.d();
            e eVar = this.a;
            Object acquireNextImage = it.acquireNextImage();
            kotlin.jvm.b.c.a(acquireNextImage, "it.acquireNextImage()");
            d.post(new a(eVar, acquireNextImage));
        }
    }

    public e(@NotNull Handler backgroundHandler, @NotNull a cameraInfo) {
        kotlin.jvm.b.c.b(backgroundHandler, "backgroundHandler");
        kotlin.jvm.b.c.b(cameraInfo, "cameraInfo");
        this.h = backgroundHandler;
        this.i = cameraInfo;
        this.i.a((kotlin.jvm.a.a) new AnonymousClass1(this));
    }

    @NotNull
    public final Handler d() {
        return this.h;
    }

    @NotNull
    public final a e() {
        return this.i;
    }

    @Nullable
    public final File a() {
        return this.d;
    }

    @Nullable
    public final Surface b() {
        ImageReader imageReader = this.a;
        return imageReader != null ? imageReader.getSurface() : null;
    }

    public final void a(@Nullable k <set-?>) {
        this.f = <set-?>;
    }

    @Nullable
    public final k c() {
        return this.f;
    }

    public final void a(@Nullable CameraDevice cameraDevice, @Nullable CameraCaptureSession captureSession, @Nullable Rect cropRegion, @NotNull Size previewSize) {
        Builder it = null;
        kotlin.jvm.b.c.b(previewSize, "previewSize");
        this.g = previewSize;
        if (cameraDevice == null) {
            a("Capture request failed: camera device not ready");
        } else if (captureSession == null) {
            a("Capture request failed: captureSession not ready");
        } else {
            try {
                Builder $receiver = cameraDevice.createCaptureRequest(2);
                if ($receiver != null) {
                    Surface it2;
                    l.a("Capturing image, rotation: " + this.i.d());
                    ImageReader imageReader = this.a;
                    if (imageReader != null) {
                        it2 = imageReader.getSurface();
                    }
                    $receiver.addTarget(it2);
                    $receiver.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(this.i.d()));
                    if (cropRegion != null) {
                        $receiver.set(CaptureRequest.SCALER_CROP_REGION, cropRegion);
                    }
                    $receiver.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(0));
                    $receiver.set(CaptureRequest.CONTROL_AE_LOCK, Boolean.valueOf(true));
                    it = $receiver;
                }
                if (it == null) {
                    kotlin.jvm.b.c.a();
                }
                com.skypecam.camera2.a.a.b bVar = a.a;
                com.skypecam.camera2.a.a.b.a(it, this.i.e());
                Builder captureBuilder = it;
                CameraCaptureSession $receiver2 = captureSession;
                this.d = File.createTempFile("capture_" + System.currentTimeMillis(), ".jpg");
                $receiver2.capture(captureBuilder.build(), this, this.h);
            } catch (Exception e) {
                e.printStackTrace();
                a("Capture request failed: " + e.getMessage());
            }
        }
    }

    public final void onCaptureCompleted(@Nullable CameraCaptureSession session, @Nullable CaptureRequest request, @Nullable TotalCaptureResult result) {
        this.e.a();
    }

    public final void onCaptureFailed(@Nullable CameraCaptureSession session, @Nullable CaptureRequest request, @Nullable CaptureFailure failure) {
        a("Capture failed: " + (failure != null ? Integer.valueOf(failure.getReason()) : null));
    }

    private final Size f() {
        Size outputSize = null;
        if (this.d != null) {
            File file = this.d;
            if (file == null) {
                kotlin.jvm.b.c.a();
            }
            if (file.exists()) {
                try {
                    int rotation;
                    int viewfinderWidth;
                    int viewfinderHeight;
                    boolean flipDimensions;
                    file = this.d;
                    if (file == null) {
                        kotlin.jvm.b.c.a();
                    }
                    String filePath = file.getPath();
                    switch (new ExifInterface(filePath).getAttributeInt("Orientation", 0)) {
                        case 3:
                            rotation = 180;
                            break;
                        case 6:
                            rotation = 90;
                            break;
                        case 8:
                            rotation = 270;
                            break;
                        default:
                            rotation = 0;
                            break;
                    }
                    Object bitmapImage = BitmapFactory.decodeFile(filePath);
                    kotlin.jvm.b.c.a(bitmapImage, "bitmapImage");
                    int bitmapWidth = bitmapImage.getWidth();
                    int bitmapHeight = bitmapImage.getHeight();
                    switch (rotation) {
                        case 90:
                        case 270:
                            viewfinderWidth = this.g.getHeight();
                            viewfinderHeight = this.g.getWidth();
                            flipDimensions = true;
                            break;
                        default:
                            viewfinderWidth = this.g.getWidth();
                            viewfinderHeight = this.g.getHeight();
                            flipDimensions = false;
                            break;
                    }
                    int finalWidth = Math.min(bitmapWidth, (bitmapHeight * viewfinderWidth) / viewfinderHeight);
                    int finalHeight = Math.min(bitmapHeight, (bitmapWidth * viewfinderHeight) / viewfinderWidth);
                    int i = (bitmapWidth - finalWidth) / 2;
                    int i2 = (bitmapHeight - finalHeight) / 2;
                    Matrix matrix = new Matrix();
                    matrix.postRotate((float) rotation);
                    Bitmap croppedBitmap = Bitmap.createBitmap(bitmapImage, i, i2, finalWidth, finalHeight, matrix, true);
                    FileOutputStream it = new FileOutputStream(this.d);
                    croppedBitmap.compress(CompressFormat.JPEG, 90, it);
                    FileOutputStream outputStream = it;
                    bitmapImage.recycle();
                    croppedBitmap.recycle();
                    if (flipDimensions) {
                        outputSize = new Size(finalHeight, finalWidth);
                    } else {
                        outputSize = new Size(finalWidth, finalHeight);
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        kotlin.jvm.b.c.b(e.toString(), "message");
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    g.d("IO exception while cropping captured image: " + e2.getMessage());
                    if (null != null) {
                        try {
                            null.close();
                        } catch (IOException e3) {
                            kotlin.jvm.b.c.b(e3.toString(), "message");
                        }
                    }
                } catch (OutOfMemoryError e4) {
                    e4.printStackTrace();
                    g.d("Out of memory while cropping captured image");
                    if (null != null) {
                        try {
                            null.close();
                        } catch (IOException e32) {
                            kotlin.jvm.b.c.b(e32.toString(), "message");
                        }
                    }
                } catch (Throwable th) {
                    if (null != null) {
                        try {
                            null.close();
                        } catch (IOException e5) {
                            kotlin.jvm.b.c.b(e5.toString(), "message");
                        }
                    }
                }
                return outputSize;
            }
        }
        g.d("File not found while cropping captured image");
        return outputSize;
    }

    public final void a(@NotNull String reason) {
        kotlin.jvm.b.c.b(reason, "reason");
        this.h.removeMessages(1);
        kotlin.jvm.b.c.b(reason, "message");
        g.b(reason);
        k kVar = this.f;
        if (kVar != null) {
            kVar.a(reason);
        }
    }

    public static final /* synthetic */ void b(e $this) {
        ImageReader newInstance = ImageReader.newInstance($this.i.g().getWidth(), $this.i.g().getHeight(), Defines.SKYLIB_CONVERSATION_MAX_TOPIC_SIZE, $this.c);
        newInstance.setOnImageAvailableListener($this.b, $this.h);
        $this.a = newInstance;
    }
}
